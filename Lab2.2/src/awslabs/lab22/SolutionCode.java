/**
 * Copyright 2013 Amazon.com, Inc. or its affiliates. All Rights Reserved.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"). You may not use this file except in compliance with
 * the License. A copy of the License is located at
 * 
 * http://aws.amazon.com/apache2.0/
 * 
 * or in the "LICENSE" file accompanying this file. This file is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR
 * CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions
 * and limitations under the License.
 */
package awslabs.lab22;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.model.AttributeDefinition;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.AttributeValueUpdate;
import com.amazonaws.services.dynamodbv2.model.Condition;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.DeleteTableRequest;
import com.amazonaws.services.dynamodbv2.model.DescribeTableRequest;
import com.amazonaws.services.dynamodbv2.model.DescribeTableResult;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
import com.amazonaws.services.dynamodbv2.model.KeySchemaElement;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.amazonaws.services.dynamodbv2.model.PutItemRequest;
import com.amazonaws.services.dynamodbv2.model.QueryRequest;
import com.amazonaws.services.dynamodbv2.model.QueryResult;
import com.amazonaws.services.dynamodbv2.model.TableDescription;
import com.amazonaws.services.dynamodbv2.model.UpdateItemRequest;

/**
 * �v���W�F�N�g: Lab2.2
 */
public abstract class SolutionCode implements ILabCode, IOptionalLabCode {
	@Override
	public void createAccountItem(AmazonDynamoDBClient ddbClient, String tableName, Account account) {
		// HashMap<String, AttributeValue>�I�u�W�F�N�g�𐶐����A�ǉ�����A�C�e���̑�������������
		Map<String, AttributeValue> itemAttributes = new HashMap<String, AttributeValue>();
		// �A�J�E���g�p�����[�^���A�K�v�ȃA�C�e��(Company �� Email)��HashMap�����ɒǉ�����
		itemAttributes.put("Company", new AttributeValue().withS(account.getCompany()));
		itemAttributes.put("Email", new AttributeValue().withS(account.getEmail()));

		// �A�J�E���g�p�����[�^���m�F���A��̕�����("") �ł͂Ȃ��l�̂��ׂĂ� HashMap�����ɒǉ�����
		if (!account.getFirst().equals("")) {
			itemAttributes.put("First", new AttributeValue().withS(account.getFirst()));
		}
		if (!account.getLast().equals("")) {
			itemAttributes.put("Last", new AttributeValue().withS(account.getLast()));
		}
		if (!account.getAge().equals("")) {
			itemAttributes.put("Age", new AttributeValue().withN(account.getAge()));
		}

		// PutItemRequest�I�u�W�F�N�g���쐬���A�w�肵���e�[�u���ɑ�����������
		PutItemRequest putItemRequest = new PutItemRequest().withTableName(tableName).withItem(itemAttributes);

		// ddbClient�N���C�A���g��putItem���\�b�h��p���ă��N�G�X�g�𑗐M
		ddbClient.putItem(putItemRequest);
	}

	@Override
	public QueryResult lookupByHashKey(AmazonDynamoDBClient ddbClient, String tableName, String company) {
		// �w�肵����Ж�(company name)���܂�AttributeValue�I�u�W�F�N�g�𐶐�����
		AttributeValue attributeValue = new AttributeValue().withS(company);
		// desired comparison ("EQ")�A����сA
		// ��Ж�(company name)�����l������Condition�I�u�W�F�N�g�𐶐�
		Condition condition = new Condition().withComparisonOperator("EQ").withAttributeValueList(attributeValue);

		// QueryRequest�𐶐����A�O�ɐ������������̃e�[�u���ɑ΂���
		// consistent read�����s����
		QueryRequest queryRequest = new QueryRequest().withTableName(tableName).withConsistentRead(true);
		queryRequest.addKeyConditionsEntry("Company", condition);

		// ddbClient�I�u�W�F�N�g��query���\�b�h���Ăяo���ăN�G�������s���A���ʂ�Ԃ�
		return ddbClient.query(queryRequest);
	}

	@Override
	public void updateIfMatch(AmazonDynamoDBClient ddbClient, String tableName, String email, String company,
			String firstNameTarget, String firstNameMatch) {
		// �w�肵���e�[�u���ɑ΂�UpdateItemRequest�I�u�W�F�N�g�𐶐�����
		UpdateItemRequest updateItemRequest = new UpdateItemRequest().withTableName(tableName);

		// company name��email address������AttributeValue�I�u�W�F�N�g���܂ރ��N�G�X�g��KeyEntry�G�������g��ǉ�����
		// 
		updateItemRequest.addKeyEntry("Company", new AttributeValue().withS(company));
		updateItemRequest.addKeyEntry("Email", new AttributeValue().withS(email));

		// firstNameMatch�p�����[�^�ɒl������ExpectedAttributeValue�I�u�W�F�N�g���܂ރ��N�G�X�g�ɁAExpectedEntry�G�������g��ǉ�����
		// 
		updateItemRequest.addExpectedEntry("First",
				new ExpectedAttributeValue().withValue(new AttributeValue().withS(firstNameMatch)));

		// firstNameTarget�p�����[�^�ɒl������AttributeValueUpdate�I�u�W�F�N�g���܂ރ��N�G�X�g�ɁAAttributeUpdatesEntry�G�������g��ǉ�����
		// 
		updateItemRequest.addAttributeUpdatesEntry("First",
				new AttributeValueUpdate().withAction("PUT").withValue(new AttributeValue().withS(firstNameTarget)));

		// ddbClient��updateItem���g�p���ă��N�G�X�g�𑗐M
		ddbClient.updateItem(updateItemRequest);
	}

	@Override
	public void deleteTable(AmazonDynamoDBClient ddbClient, String tableName) {
		String tableStatus = getTableStatus(ddbClient, tableName);
		if (tableStatus.equals("ACTIVE")) {
			System.out.println("Deleting pre-existing table.");
			DeleteTableRequest deleteTableRequest = new DeleteTableRequest().withTableName(tableName);
			ddbClient.deleteTable(deleteTableRequest);
			waitForStatus(ddbClient, tableName, "NOTFOUND");

			System.out.println("Table deletion confirmed.");
		} else if (tableStatus.equals("NOTFOUND")) {
			System.out.println("Skipped deletion operation. Table not found.");
		} else {
			System.out.println("Skipped deletion operation. Table not in correct state.");
		}
	}

	@Override
	public void buildTable(AmazonDynamoDBClient ddbClient, String tableName) {
		System.out.println("Creating table.");
		CreateTableRequest createTableRequest = new CreateTableRequest().withTableName(tableName);
		createTableRequest.setAttributeDefinitions(new ArrayList<AttributeDefinition>());
		// �������`
		createTableRequest.getAttributeDefinitions().add(
				new AttributeDefinition().withAttributeName("Company").withAttributeType("S"));
		createTableRequest.getAttributeDefinitions().add(
				new AttributeDefinition().withAttributeName("Email").withAttributeType("S"));
		// �L�[�X�L�[�}���`
		createTableRequest.setKeySchema(new ArrayList<KeySchemaElement>());
		createTableRequest.getKeySchema().add(new KeySchemaElement().withAttributeName("Company").withKeyType("HASH"));
		createTableRequest.getKeySchema().add(new KeySchemaElement().withAttributeName("Email").withKeyType("RANGE"));
		// �v���r�W�����h�X���[�v�b�g���`
		createTableRequest.setProvisionedThroughput(new ProvisionedThroughput().withReadCapacityUnits(10L)
				.withWriteCapacityUnits(5L));

		// create���N�G�X�g�𑗐M
		ddbClient.createTable(createTableRequest);
		// �e�[�u�����A�N�e�B�u�ɂȂ�܂Œ�~
		waitForStatus(ddbClient, tableName, "ACTIVE");
		System.out.println("Table created and active.");
	}

	@Override
	public String getTableStatus(AmazonDynamoDBClient ddbClient, String tableName) {
		TableDescription tableDescription = getTableDescription(ddbClient, tableName);
		if (tableDescription == null) {
			return "NOTFOUND";
		}
		return tableDescription.getTableStatus();
	}

	@Override
	public TableDescription getTableDescription(AmazonDynamoDBClient ddbClient, String tableName) {
		try {
			DescribeTableRequest describeTableRequest = new DescribeTableRequest().withTableName(tableName);

			DescribeTableResult describeTableResult = ddbClient.describeTable(describeTableRequest);

			return describeTableResult.getTable();
		} catch (AmazonServiceException ase) {
			// �e�[�u����������Ȃ���Ζ��Ȃ�
			// �G���[������ȊO�������ꍇ�A��O���ăX���[
			if (!ase.getErrorCode().equals("ResourceNotFoundException")) {
				throw ase;
			}
			return null;
		}
	}

	@Override
	public void waitForStatus(AmazonDynamoDBClient ddbClient, String tableName, String status) {
		while (!getTableStatus(ddbClient, tableName).equals(status)) {
			// 1�b�ԃX���[�v
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// ��O����
			}
		}
	}

}
