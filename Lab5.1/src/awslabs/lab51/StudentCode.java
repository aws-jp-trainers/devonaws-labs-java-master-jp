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
package awslabs.lab51;

import java.util.List;
import java.util.Map;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.TableDescription;
import com.amazonaws.services.s3.AmazonS3Client;

/**
 * �v���W�F�N�g: Lab5.1
 */
public class StudentCode extends SolutionCode {
	public StudentCode(Lab51 lab) {
		super(lab);
	}

	/**
	 * getUrlForItem - �p�ӂ��ꂽS3�N���C�A���g��p���āA�w�肳�ꂽ�o�P�b�g�ƃL�[�ɑ΂��Ď��O�����t��URL�𐶐�����
	 * URL��1���Ŋ������؂��悤�ɂ���
	 * 
	 * @param s3Client	S3�N���C�A���g�I�u�W�F�N�g
	 * @param key		�����N��p�ӂ���I�u�W�F�N�g�̃L�[
	 * @param bucket	�I�u�W�F�N�g���i�[����o�P�b�g
	 * @return 			�I�u�W�F�N�g�̎��O�����t��URL
	 */
	@Override
	public String getUrlForItem(AmazonS3Client s3Client, String key, String bucket) {
		//TODO: �X�[�p�[�N���X�̌Ăяo�����A�����̎������\�b�h�ƍ����ւ���
		return super.getUrlForItem(s3Client, key, bucket);
	}

	/**
	 * getImageItems - DynamoDB����A�y�[�W�ɕ\������C���[�W�̏ڍׂ��܂ރA�C�e���R���N�V������Ԃ�
	 * �A�C�e�����܂ރe�[�u���̖��O�́ASeSSIONTABLE�̒l������肷�� 
	 * PARAM3�ɒ�`���ꂽ�L�[�v���t�B�b�N�X����Ɍ��ʂ��t�B���^�[����B�X�L����������g�p���ăA�C�e������肷��悤�ɂ��� 
	 * �A�C�e���R���N�V�����͌��ʂ̃I�u�W�F�N�g�ɓ���
	 * 
	 * @param dynamoDbClient	DynamoDB�N���C�A���g�I�u�W�F�N�g
	 * @return	�}�b�`���O�A�C�e���R���N�V����
	 */
	@Override
	public List<Map<String, AttributeValue>> getImageItems(AmazonDynamoDBClient dynamoDbClient) {
		//TODO: �X�[�p�[�N���X�̌Ăяo�����A�����̎������\�b�h�ƍ����ւ���
		return super.getImageItems(dynamoDbClient);
	}

	/**
	 * createS3Client - REGION�ݒ�Ƀ��[�W���������K�p����S3�N���C�A���g�I�u�W�F�N�g�𐶐����ĕԂ�
	 * 
	 * @param credentials	�N���C�A���g�I�u�W�F�N�g�Ɏg�p����F�؏��
	 * @return	�N���C�A���g�I�u�W�F�N�g
	 */
	@Override
	public AmazonS3Client createS3Client(AWSCredentials credentials) {
		//TODO: �X�[�p�[�N���X�̌Ăяo�����A�����̎������\�b�h�ƍ����ւ���
		return super.createS3Client(credentials);
	}

	/**
	 * createDynamoDbClient - REGION�ݒ�Ƀ��[�W���������K�p����DynamoDB�N���C�A���g�I�u�W�F�N�g�𐶐����ĕԂ�
	 * 
	 * @param credentials 	�N���C�A���g�I�u�W�F�N�g�Ɏg�p����F�؏��
	 * @return �N���C�A���g�I�u�W�F�N�g
	 */
	@Override
	public AmazonDynamoDBClient createDynamoDbClient(AWSCredentials credentials) {
		//TODO: �X�[�p�[�N���X�̌Ăяo�����A�����̎������\�b�h�ƍ����ւ���
		return super.createDynamoDbClient(credentials);
	}

	/**
	 * addItemsToPage - ���̃��\�b�h�́ADynamoDB�̃A�C�e���R���N�V�������A�E�F�u�y�[�W�ɕ\���ł���v�f�ɕϊ����邽�߂Ɏg�p����
	 * ���̃^�X�N����������ɂ́A�ȉ����s��
	 * (1) �R���N�V�������̃A�C�e�������[�v���A "Key" ����� "Bucket"�����l�𒊏o
	 * (2) "Key" ����� "Bucket"�l���g�p���āA�e�I�u�W�F�N�g�̎��O�����t��URL�𐶐��BURL�𐶐�����ɂ́AGetUrlForItem()���\�b�h�̎������Ăяo���A�߂�l��͂�
	 * (3) �e�A�C�e���ɂ��āA_Default.AddImageToPage()���Ăяo���A���\�b�h�p�����[�^�Ƃ��ăL�[�A�o�P�b�g�A�����URL�̒l��n��
	 * 
	 * @param s3Client	S3�N���C�A���g�I�u�W�F�N�g
	 * @param items		�y�[�W�ɒǉ�����A�C�e���R���N�V����
	 */
	@Override
	public void addItemsToPage(AmazonS3Client s3Client, List<Map<String, AttributeValue>> items) {
		//TODO: �X�[�p�[�N���X�̌Ăяo�����A�����̎������\�b�h�ƍ����ւ���
		super.addItemsToPage(s3Client, items);
	}

	/**
	 * isImageInDynamo - DynamoDB�e�[�u�����������A�w�肵���n�b�V���L�[�Ƀ}�b�`����A�C�e�����܂ނ��ǂ�������肷��
	 * 
	 * @param dynamoDbClient	DynamoDB�N���C�A���g�I�u�W�F�N�g
	 * @param tableName			��������e�[�u����
	 * @param key				���肷��A�C�e���̃L�[
	 * @return �A�C�e�������݂��Ă���ΐ^�A�Ȃ���΋U 
	 */
	@Override
	public Boolean isImageInDynamo(AmazonDynamoDBClient dynamoDbClient, String tableName, String key) {
		//TODO: �X�[�p�[�N���X�̌Ăяo�����A�����̎������\�b�h�ƍ����ւ���
		return super.isImageInDynamo(dynamoDbClient, tableName, key);
	}

	/**
	 * getTableDescription - �w�肵���e�[�u���̃e�[�u�����������N�G�X�g���A������Ăяo�����ɕԂ�
	 * 
	 * @param dynamoDbClient	DynamoDB�N���C�A���g�I�u�W�F�N�g
	 * @param tableName			�e�[�u����
	 * @return �e�[�u�������I�u�W�F�N�g�B�e�[�u�����Ȃ���΃k��
	 */
	@Override
	public TableDescription getTableDescription(AmazonDynamoDBClient ddbClient, String tableName) {
		//TODO: �X�[�p�[�N���X�̌Ăяo�����A�����̎������\�b�h�ƍ����ւ���
		return super.getTableDescription(ddbClient, tableName);
	}

	/**
	 * validateSchema - tableDescription�p�����[�^�Œ�`�����X�L�[�}��L��������
	 * �e�[�u���͈ȉ��̓����������Ƃ����߂���
	 *   �X�L�[�} - "Key" ����� "Bucket"�̍Œ�ł�2�̑����i�o���Ƃ��ɕ�����^�j
	 *   �n�b�V���L�[ -  "Key"�Ƃ������O�̕�����^��1�̑���
	 *   �����W�L�[ - "Bucket"�Ƃ������O�̕�����^��1�̑���
	 * 
	 * @param tableDescription	�e�[�u����`
	 * @return �X�L�[�}�����҂ƈ�v����Ƃ��͐^�A�X�L�[�}�������܂��͗�O��������ꂽ�Ƃ��͋U
	 */
	@Override
	public Boolean validateSchema(TableDescription tableDescription) {
		//TODO: �X�[�p�[�N���X�̌Ăяo�����A�����̎������\�b�h�ƍ����ւ���
		return super.validateSchema(tableDescription);
	}
	
	/**
	 * getTableStatus - �w�肵���e�[�u���Ɋ֘A����e�[�u���X�e�[�^�X�̕������Ԃ� 
	 * �e�[�u���X�e�[�^�X��TableDescription�I�u�W�F�N�g�̃v���p�e�B
	 * 
	 * @param dynamoDbClient	DynamoDB�N���C�A���g�I�u�W�F�N�g
	 * @param tableName			��������e�[�u����
	 * @return �e�[�u���X�e�[�^�X������B�e�[�u�������݂��Ȃ��A�܂��͓���ł��Ȃ��ꍇ�� "NOTFOUND" 
	 */
	@Override
	public String getTableStatus(AmazonDynamoDBClient ddbClient, String tableName) {
		//TODO: �X�[�p�[�N���X�̌Ăяo�����A�����̎������\�b�h�ƍ����ւ���
		return super.getTableStatus(ddbClient, tableName);
	}

	/**
	 * waitForStatus - �e�[�u���X�e�[�^�X���A�^����ꂽ�X�e�[�^�X�̕�����ƃ}�b�`����܂ŁA���̃X���b�h�̎��s���~����
	 * 
	 * @param dynamoDbClient	DynamoDB�N���C�A���g�I�u�W�F�N�g
	 * @param tableName			�e�[�u����
	 * @param status			�]�ރe�[�u���X�e�[�^�X
	 */
	@Override
	public void waitForStatus(AmazonDynamoDBClient ddbClient, String tableName, String status) {
		//TODO: �X�[�p�[�N���X�̌Ăяo�����A�����̎������\�b�h�ƍ����ւ���
		super.waitForStatus(ddbClient, tableName, status);
	}

	/**
	 * deleteTable - �w�肵���e�[�u�����폜�B���̃��\�b�h�́A���{�R���g���[���[�R�[�h�������̃e�[�u�������̃��{�Ō������Ɣ��f�����ꍇ�ɌĂяo����� 
	 * 
	 * @param dynamoDbClient	DynamoDB�N���C�A���g�I�u�W�F�N�g
	 * @param tableName			�폜����e�[�u����
	 */
	@Override
	public void deleteTable(AmazonDynamoDBClient ddbClient, String tableName) {
		//TODO: �X�[�p�[�N���X�̌Ăяo�����A�����̎������\�b�h�ƍ����ւ���
		super.deleteTable(ddbClient, tableName);
	}

	/**
	 * addImage - �w�肳�ꂽ�C���[�W��S3�ɃA�b�v���[�h���ADynamoDB�̃C���[�W�ւ̎Q�Ƃ������� 
	 * �C���[�W��\��DynamoDB�̃A�C�e���́A�ȉ���2�̑����������Ă���ׂ��ł���
	 *   Key - S3�̃I�u�W�F�N�g�ւ̃L�[
	 *   Bucket - ���̃I�u�W�F�N�g�̑��݂���o�P�b�g
	 *   
	 * S3�̃I�u�W�F�N�g�ɂ͉����p�[�~�b�V�����̐ݒ�͉����s�킸�A�������ꂽ�f�t�H���g��ۂ�
	 * ���̃��\�b�h�́A���{����R�[�h�����{�Ŏg����C���[�W���Ȃ��A�܂���DynamoDB�Ő������Q�Ƃ���Ȃ��Ɣ��f���ꂽ�ꍇ�ɌĂяo�����
	 * �Œ�1��͎��s�����
	 * 
	 * @param dynamoDbClient	DynamoDB�N���C�A���g�I�u�W�F�N�g
	 * @param tableName			�A�C�e��������e�[�u����
	 * @param s3Client			S3�N���C�A���g�I�u�W�F�N�g
	 * @param bucketName		S3�I�u�W�F�N�g�ɂ����o�P�b�g�̖��O 
	 * @param imageKey			S3�I�u�W�F�N�g�Ɏg�p����L�[ 
	 * @param filePath			�A�b�v���[�h����C���[�W�ւ̃p�X
	 */
	@Override
	public void addImage(AmazonDynamoDBClient dynamoDbClient, String tableName, AmazonS3Client s3Client,
			String bucketName, String imageKey, String filePath) {
		//TODO: �X�[�p�[�N���X�̌Ăяo�����A�����̎������\�b�h�ƍ����ւ���
		super.addImage(dynamoDbClient, tableName, s3Client, bucketName, imageKey, filePath);
	}

	/**
	 * buildTable - ���{�Ŏg����e�[�u�����쐬�B�e�[�u���X�e�[�^�X��"ACTIVE"�ƂȂ�܂ł��̃��\�b�h����߂�Ȃ�
	 * 
	 * ���L�p�����[�^�Ƀ}�b�`����e�[�u�����\�z����
	 *   ���� - "Key" ������A"Bucket" ������
	 *   �n�b�V���L�[���� - "Key"
	 *   �����W�L�[���� - "Bucket"
	 *   �v���r�W�����h�L���p�V�e�B - 5 Reads/5 Writes
	 *   
	 * ���̃��\�b�h�́A���{���������邽�߂ɍŒ�1��̓��{����R�[�h����Ăяo�����B
	 * �܂��A���{����R�[�h���A�e�[�u�����č\�z����K�v������Ɣ��f�����ꍇ�ɂ��Ăяo����� (��F�X�L�[�}�����҂ƃ}�b�`���Ȃ��j
	 * 
	 * @param dynamoDbClient	DynamoDB�N���C�A���g�I�u�W�F�N�g
	 * @param tableName			�쐬����e�[�u����
	 */
	@Override
	public void buildTable(AmazonDynamoDBClient ddbClient, String tableName) {
		//TODO:�X�[�p�[�N���X�̌Ăяo�����A�����̎������\�b�h�ƍ����ւ���
		super.buildTable(ddbClient, tableName);
	}

}
