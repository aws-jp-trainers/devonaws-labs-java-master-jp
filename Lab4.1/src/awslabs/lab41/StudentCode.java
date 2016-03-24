/** 
 * Copyright 2013 Amazon.com, Inc. or its affiliates. All Rights Reserved.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"). You may not 
 * use this file except in compliance with the License. A copy of the License 
 * is located at
 * 
 * 	http://aws.amazon.com/apache2.0/
 * 
 * or in the "LICENSE" file accompanying this file. This file is distributed 
 * on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either 
 * express or implied. See the License for the specific language governing 
 * permissions and limitations under the License.
 */
package awslabs.lab41;

import java.util.List;

import com.amazonaws.auth.BasicSessionCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.services.identitymanagement.AmazonIdentityManagementClient;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.securitytoken.AWSSecurityTokenServiceClient;
import com.amazonaws.services.securitytoken.model.Credentials;

/**
 * �v���W�F�N�g: Lab4.1
 */
public class StudentCode extends SolutionCode {
	/**
	 * �w�肳�ꂽ���[�U��ARN���������ĕԂ�
	 * Hint: �N���C�A���g�I�u�W�F�N�g��getUser()���\�b�h���g�p����B���[�U�[��ARN���߂�l
	 * 
	 * @param iamClient	IAM�N���C�A���g�I�u�W�F�N�g
	 * @param userName	�������郆�[�U�[�̖��O
	 * @return �w�肳�ꂽ���[�U��ARN
	 */
	@Override
	public String prepMode_GetUserArn(AmazonIdentityManagementClient iamClient, String userName) {
		
		//TODO: �X�[�p�[�N���X�̌Ăяo�����A�����̎������\�b�h�ƍ����ւ���
		return super.prepMode_GetUserArn(iamClient, userName);
	}

	/**
	 * �w�肳�ꂽ�|���V�[�ƐM���֌W�e�L�X�g���g�p���A�w�肳�ꂽ���[�����쐬�B���[��ARN��Ԃ�
	 * 
	 * @param iamClient				IAM�N���C�A���g�I�u�W�F�N�g
	 * @param roleName				�쐬���郍�[���̖��O
	 * @param policyText			���[���ɕt������|���V�[
	 * @param trustRelationshipText	���ꂪ���[���������p�����Ƃ��ł��邩���`����|���V�[
	 * @return �V�K�ɍ쐬�������[����ARN
	 */
	@Override
	public String prepMode_CreateRole(AmazonIdentityManagementClient iamClient, String roleName, String policyText,
			String trustRelationshipText) {

		//TODO: �X�[�p�[�N���X�̌Ăяo�����A�����̎������\�b�h�ƍ����ւ���
		return super.prepMode_CreateRole(iamClient, roleName, policyText, trustRelationshipText);
	}

	/**
	 * �w�肵�����[���������p��
	 * Hint: �N���C�A���g�I�u�W�F�N�g��assumeRole()���\�b�h���g�p����
	 * �I�v�V����: �����ŁA���ʐ������̖����݂�\��������܂��B. AssumeRole�p�[�~�b�V�����́A�V�X�e���S�̂ɐZ�����Ă��Ȃ��\��������A����ɂ�胍�[���������p�����Ƃ��j�Q�����\��������܂�
	 *�@"AmazonServiceException"�̃G���[�R�[�h"AccessDenied" ���m�F���A�����ҋ@������Ƀ��[������̈����p�����Ď��s����(�Ď��s��exponential back-off���g�p�j
	 * �Ď��s����߂�Ɣ��f�����ꍇ�́A�k����Ԃ�
	 * 	
	 * @param stsClient			STS�N���C�A���g�I�u�W�F�N�g
	 * @param roleArn			�����p�����[����ARN
	 * @param roleSessionName	���[���Z�b�V�������Ƃ��Ďg�p���閼�O
	 * @return ���[���F�؏��A�܂��͖�肪����ꍇ�̓k��
	 */
	@Override
	public Credentials appMode_AssumeRole(AWSSecurityTokenServiceClient stsClient, String roleArn,
			String roleSessionName) {
		
		//TODO: �X�[�p�[�N���X�̌Ăяo�����A�����̎������\�b�h�ƍ����ւ���
		return super.appMode_AssumeRole(stsClient, roleArn, roleSessionName);
	}

	/**
	 * �^����ꂽ�F�؏��i�O��assumeRole()���\�b�h�̌Ăяo���ŕԂ��ꂽ���́j���g�p���āA�Z�b�V����/�ꎞ�F�؏����쐬����
	 * �����āA�Z�b�V�����F�؏����g�p����S3�N���C�A���g�I�u�W�F�N�g���쐬����
	 * 
	 * @param credentials	�Z�b�V�����F�؏����쐬���邽�߂Ɏg�p����F�؏��
	 * @param region		�N���C�A���g�Ɏg�p���郊�[�W�����̃G���h�|�C���g
	 * @return S3�N���C�A���g�I�u�W�F�N�g
	 */
	@Override
	public AmazonS3Client appMode_CreateS3Client(Credentials credentials, Region region) {
		
		//TODO: �X�[�p�[�N���X�̌Ăяo�����A�����̎������\�b�h�ƍ����ւ���
		return super.appMode_CreateS3Client(credentials, region);
	}

	/**
	 * �쐬���悤�Ƃ��Ă��郍�[�����ƃ}�b�`���郍�[�����폜�B����̓��{����R�[�h����Ăяo����A���{���s�Ɏx����������\�������郊�\�[�X���N���[���A�b�v���邽�߂ɌĂяo����� 
	 * 
	 * @param iamClient	IAM�N���C�A���g�I�u�W�F�N�g
	 * @param roles		���[�����̃��X�g
	 */
	@Override
	public void prepMode_RemoveRoles(AmazonIdentityManagementClient iamClient, String... roles) {
		
		//TODO: �X�[�p�[�N���X�̌Ăяo�����A�����̎������\�b�h�ƍ����ւ���
		super.prepMode_RemoveRoles(iamClient, roles);
	}

	/**
	 * ���̃��{�Ō�Ŏg�p�����o�P�b�g���쐬����B���{���K�̊����������邽�߂̃R�[�h 
	 * 
	 * @param s3Client		S3�N���C�A���g�I�u�W�F�N�g
	 * @param bucketName	�쐬����o�P�b�g
	 */
	@Override
	public void prepMode_CreateBucket(AmazonS3Client s3Client, String bucketName, Region region) {

		//TODO: �X�[�p�[�N���X�̌Ăяo�����A�����̎������\�b�h�ƍ����ւ���
		super.prepMode_CreateBucket(s3Client, bucketName, region);
	}

	/**
	 * SNS�g�s�b�N�̃��X�g�����N�G�X�g���邱�ƂŁA�^����ꂽ�F�؏����g����SNS�T�[�r�X�ɃA�N�Z�X�ł��邩���e�X�g����
	 * �e�X�g�̎d���͖₢�܂���B�Ȃ�炩�̃��N�G�X�g�𑗐M���āA���s���m�F���Ă�������
	 * 
	 * @param region		�N���C�A���g�ڑ��Ɏg�p���郊�[�W�����G���h�|�C���g
	 * @param credentials	�g�p����F�؏��
	 * @return �T�[�r�X���A�N�Z�X�\�ȏꍇ��True�B�F�؏�񂪋��ۂ��ꂽ�ꍇ��False 
	 */
	@Override
	public Boolean appMode_TestSnsAccess(Region region, BasicSessionCredentials credentials) {

		//TODO: �X�[�p�[�N���X�̌Ăяo�����A�����̎������\�b�h�ƍ����ւ���
		return super.appMode_TestSnsAccess(region, credentials);
	}

	/**
	 * SQS�L���[�̃��X�g�����N�G�X�g���邱�ƂŁA�^����ꂽ�F�؏����g����SQS�T�[�r�X�ɃA�N�Z�X�ł��邩���e�X�g����
	 * �e�X�g�̎d���͖₢�܂���B�Ȃ�炩�̃��N�G�X�g�𑗐M���āA���s���m�F���Ă�������
	 * 
	 * @param region		�N���C�A���g�ڑ��Ɏg�p���郊�[�W�����G���h�|�C���g
	 * @param credentials	�g�p����F�؏��
	 * @return �T�[�r�X���A�N�Z�X�\�ȏꍇ��True�B�F�؏�񂪋��ۂ��ꂽ�ꍇ��False 
	 */
	@Override
	public Boolean appMode_TestSqsAccess(Region region, BasicSessionCredentials credentials) {
		
		//TODO: �X�[�p�[�N���X�̌Ăяo�����A�����̎������\�b�h�ƍ����ւ���
		return super.appMode_TestSqsAccess(region, credentials);
	}

	/**
	 * IAM���[�U�[�̃��X�g�����N�G�X�g���邱�ƂŁA�^����ꂽ�F�؏����g����IAM�T�[�r�X�ɃA�N�Z�X�ł��邩���e�X�g����
	 * �e�X�g�̎d���͖₢�܂���B�Ȃ�炩�̃��N�G�X�g�𑗐M���āA���s���m�F���Ă�������
	 * 
	 * @param region		�N���C�A���g�ڑ��Ɏg�p���郊�[�W�����G���h�|�C���g
	 * @param credentials	�g�p����F�؏��
	 * @return �T�[�r�X���A�N�Z�X�\�ȏꍇ��True�B�F�؏�񂪋��ۂ��ꂽ�ꍇ��False 
	 */
	@Override
	public Boolean appMode_TestIamAccess(Region region, BasicSessionCredentials credentials) {
		
		//TODO: �X�[�p�[�N���X�̌Ăяo�����A�����̎������\�b�h�ƍ����ւ���
		return super.appMode_TestIamAccess(region, credentials);
	}

	/**
	 * ���̃��{�ō쐬���ꂽ�o�P�b�g�̃N���[���A�b�v�ƍ폜
	 * 
	 * @param s3Client		S3�N���C�A���g�I�u�W�F�N�g
	 * @param bucketNames	�폜����o�P�b�g
	 */
	@Override
	public void removeLabBuckets(AmazonS3Client s3Client, List<String> bucketNames) {
		
		//TODO: �X�[�p�[�N���X�̌Ăяo�����A�����̎������\�b�h�ƍ����ւ���
		super.removeLabBuckets(s3Client, bucketNames);
	}
}
