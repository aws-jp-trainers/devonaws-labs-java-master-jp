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

import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.model.QueryResult;
import com.amazonaws.services.dynamodbv2.model.TableDescription;

/**
 * �v���W�F�N�g: Lab2.2
 */

public class StudentCode extends SolutionCode {

	/**
	 * �A�J�E���g�p�����[�^�Ɏw�肵���l����DynamoDB�A�C�e���𐶐�����
	 * �A�C�e���̑������͑Ή�����Account�I�u�W�F�N�g�̃v���p�e�B���Ƀ}�b�`����K�v������
	 * Account�I�u�W�F�N�g�̋�̃t�B�[���h�ɑ����͒ǉ����Ȃ�����
	 * 
	 * Company�����Email�����̓e�[�u���L�[�̈ꕔ�Ȃ��߁A�����͂��̃��\�b�h���Ă΂��ہA���Account�I�u�W�F�N�g�ɓn�����
	 * 
	 * 
	 * �d�v: Account.Age�v���p�e�B��������Ƃ��ĕԂ���Ă��A�����Ƃ��ăA�C�e���ɒǉ����邱��
	 * 
	 * 
	 * @param ddbClient DynamoDB�N���C�A���g�I�u�W�F�N�g
	 * @param tableName �A�C�e����ǉ�����e�[�u����
	 * @param account �ǉ�����f�[�^���܂�Account�I�u�W�F�N�g
	 */
	@Override
	public void createAccountItem(AmazonDynamoDBClient ddbClient, String tableName, Account account) {
		// TODO: �X�[�p�[�N���X�̌Ăяo�����A�����̎������\�b�h�ƍ����ւ���
		super.createAccountItem(ddbClient, tableName, account);
	}

	/**
	 * Construct a query using the criteria specified and return the result object. Hint: Use the query() method of the
	 * client object.
	 * 
	 * @param ddbClient TDynamoDB�N���C�A���g�I�u�W�F�N�g
	 * @param tableName �N�G������e�[�u����
	 * @param company ��������company name
	 * @return ���N�G�X�g�̌��ʂ��܂�QueryResult�I�u�W�F�N�g
	 */
	@Override
	public QueryResult lookupByHashKey(AmazonDynamoDBClient ddbClient, String tableName, String company) {
		// TODO: �X�[�p�[�N���X�̌Ăяo�����A�����̎������\�b�h�ƍ����ւ���
		return super.lookupByHashKey(ddbClient, tableName, company);
	}

	/**
	 * company�����email�̃p�����[�^�l���}�b�`����A�C�e�����e�[�u�����ɒT���B �����l��firstNameMatch �p�����[�^�Ƀ}�b�`����ꍇ�Ɍ���AFirst������firstNameTarget�p�����[�^�ɒl���Z�b�g����
	 * 
	 * �q���g:�N���C�A���g�I�u�W�F�N�g��updateItem() ���\�b�h���g�p�����V���O�����N�G�X�g�ŒB���\
	 * 
	 * @param ddbClient DynamoDB�N���C�A���g�I�u�W�F�N�g
	 * @param tableName �A�C�e�����܂ރe�[�u����
	 * @param email Email�����Ƀ}�b�`����l
	 * @param company Company�����Ƀ}�b�`����l
	 * @param firstNameTarget �}�b�`�����������ꍇ��First�����̐V�����l
	 * @param firstNameMatch First�����Ƀ}�b�`����l
	 */
	@Override
	public void updateIfMatch(AmazonDynamoDBClient ddbClient, String tableName, String email, String company,
			String firstNameTarget, String firstNameMatch) {
		// TODO: �X�[�p�[�N���X�̌Ăяo�����A�����̎������\�b�h�ƍ����ւ���
		super.updateIfMatch(ddbClient, tableName, email, company, firstNameTarget, firstNameMatch);
	}

	// �I�v�V�����ۑ�J�n
	/**
	 * �w�肵���e�[�u���̃e�[�u�����������N�G�X�g���A�Ăяo�����ɕԂ��B 
	 * �q���g�F�N���C�A���g�I�u�W�F�N�g��describeTable()���\�b�h���g�p����
	 * 
	 * @param ddbClient DynamoDB�N���C�A���g�I�u�W�F�N�g
	 * @param tableName �e�[�u����
	 * @return The TableDescription object for the table. Null if the table wasn't found.
	 */
	@Override
	public TableDescription getTableDescription(AmazonDynamoDBClient ddbClient, String tableName) {
		// TODO: �X�[�p�[�N���X�̌Ăяo�����A�����̎������\�b�h�ƍ����ւ���
		return super.getTableDescription(ddbClient, tableName);
	}

	/**
	 * �w�肵���e�[�u���Ɋ֘A�t����ꂽ�e�[�u���X�e�[�^�X�̕������Ԃ��B�e�[�u���X�e�[�^�X�́ATableDescription�I�u�W�F�N�g�̃v���p�e�B
	 * 
	 * Hint: getTableDescription()���Ăяo����TableDescription�����B ���\�b�h���k����Ԃ����ꍇ�A���̃��\�b�h����
	 *  "NOTFOUND" ��Ԃ�
	 * 
	 * @param ddbClient DynamoDB�N���C�A���g�I�u�W�F�N�g
	 * @param tableName �e�[�u����
	 * @return �e�[�u���X�e�[�^�X������B�e�[�u�������݂��Ȃ��ꍇ�܂��͌�����Ȃ��ꍇ�� "NOTFOUND"
	 */
	@Override
	public String getTableStatus(AmazonDynamoDBClient ddbClient, String tableName) {
		// TODO: �X�[�p�[�N���X�̌Ăяo�����A�����̎������\�b�h�ƍ����ւ���
		return super.getTableStatus(ddbClient, tableName);
	}

	/**
	 * �e�[�u���X�e�[�^�X���^����ꂽ�X�e�[�^�X�X�g�����O�ƃ}�b�`����܂ŁA���̃X���b�h�̎��s���ꎞ��~���� 
	 * �q���g�F�X�e�[�^�X���J��Ԃ����N�G�X�g���A���N�G�X�g�Ԃ̓X���b�h���X���[�v�ɂ���K�v������B
	 * ���̃��{�ł͒�~���鎞�Ԃ͔C�ӂŎw��\�����A1�b��蒷�����邱��
	 * 
	 * @param ddbClient DynamoDB�N���C�A���g�I�u�W�F�N�g
	 * @param tableName ��������e�[�u����
	 * @param status �]�ރX�e�[�^�X
	 */
	@Override
	public void waitForStatus(AmazonDynamoDBClient ddbClient, String tableName, String status) {
		// TODO: �X�[�p�[�N���X�̌Ăяo�����A�����̎������\�b�h�ƍ����ւ���
		super.waitForStatus(ddbClient, tableName, status);
	}

	/**
	 * ���̃��{�Ŏg�p����e�[�u�����쐬����B�e�[�u���X�e�[�^�X��"ACTIVE"�ɂȂ�܂ŁA���̃��\�b�h����͕Ԃ�Ȃ�
	 * �q���g�F��L�Ŏ��s�����҂��߂�waitForStatus()���\�b�h���Ăяo��
	 * �p�����[�^�Ƀ}�b�`����e�[�u�����\�z����
	 * -- Attributes - "Company" ������, ����� "Email" ������������ -- Hash Key Attribute - "Company" --Range Key Attribute - "Email" -- Provisioned Capacity - 5 reads/5 writes
	 * 
	 * ���̃��\�b�h�́A�e�[�u���̍č\�z���K�v�Ɣ��f���ꂽ�ꍇ�ɁA���{�R���g���[���[�R�[�h����Ăяo����܂�
	 * ��F�X�L�[�}�����҂ƃ}�b�`���Ȃ�
	 * 
	 * ���̃^�X�N���������邽�߂ɂ́ADynamoDB�Ńe�[�u�����쐬������@�𒲂ׂ�K�v������i���̃R�[�X�����ł̓J�o�[���Ă��܂���j
	 * 
	 * 
	 * @param ddbClient DynamoDB�N���C�A���g�I�u�W�F�N�g
	 * @param tableName �쐬����e�[�u����
	 */
	@Override
	public void buildTable(AmazonDynamoDBClient ddbClient, String tableName) {
		// TODO: �X�[�p�[�N���X�̌Ăяo�����A�����̎������\�b�h�ƍ����ւ���
		super.buildTable(ddbClient, tableName);
	}

	/**
	 * �w�肵���e�[�u�����폜����B���̃��\�b�h�́A���݂��Ă���e�[�u�������̃��{�ɕs�K���Ɣ��f���ꂽ�ۂɁA���{�R���g���[���[�R�[�h����Ăяo�����
	 * 
	 * 
	 * @param ddbClient DynamoDB�N���C�A���g�I�u�W�F�N�g
	 * @param tableName �e�[�u����
	 */
	@Override
	public void deleteTable(AmazonDynamoDBClient ddbClient, String tableName) {
		// TODO: �X�[�p�[�N���X�̌Ăяo�����A�����̎������\�b�h�ƍ����ւ���
		super.deleteTable(ddbClient, tableName);
	}

	// �I�v�V�����ۑ�I��
}
