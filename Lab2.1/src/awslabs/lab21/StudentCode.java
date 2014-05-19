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
package awslabs.lab21;

import java.io.File;
import java.net.URL;
import java.util.Date;

import com.amazonaws.regions.Region;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.CreateBucketRequest;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.amazonaws.services.s3.model.ListObjectsRequest;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3ObjectSummary;

/**
 * Project: Lab2.1
 * 
 * ���̃��{�̎�ȖړI�́AS3���v���O�����ő��삷��o���𓾂邱�Ƃł�
 */
@SuppressWarnings("unused")
public class StudentCode extends SolutionCode {
    /**
     * S3�N���C�A���g�I�u�W�F�N�g���g�p���āA�w�肵���o�P�b�g���쐬���� 
     * �q���g�F�N���C�A���g�I�u�W�F�N�g�� createBucket()���\�b�h���g�p����
     * 		 ���[�W������us-east-1�ȊO�̏ꍇ�A
     * 		 ���N�G�X�g�Ƀ��[�W�����𖾎��I�Ɏw�肷��K�v������܂�
     * 
     * @param s3Client	 S3�N���C�A���g�I�u�W�F�N�g	
     * @param bucketName �쐬����o�P�b�g�̖��O
     */
    @Override
    public void createBucket(AmazonS3 s3Client, String bucketName, Region region) {
	//TODO: �X�[�p�[�N���X�̌Ăяo�����A�����̎������\�b�h�ƍ����ւ���
	super.createBucket(s3Client, bucketName, region);
    }

    /**
     * �w�肵���o�P�b�g�ɗ^����ꂽ�A�C�e�����A�b�v���[�h����
     * �q���g: �N���C�A���g�I�u�W�F�N�g��putObject() ���\�b�h���g�p����
     * 
     * @param s3Client	 S3�N���C�A���g�I�u�W�F�N�g	
     * @param bucketName �^�[�Q�b�g�o�P�b�g�̖��O
     * @param sourceFile �A�b�v���[�h����t�@�C���̖��O
     * @param objectKey  �V����S3�I�u�W�F�N�g�Ɋ��蓖�Ă�L�[
     */
    @Override
    public void putObject(AmazonS3 s3Client, String bucketName, String sourceFile, String objectKey) {
	//TODO: �X�[�p�[�N���X�̌Ăяo�����A�����̎������\�b�h�ƍ����ւ���
	super.putObject(s3Client, bucketName, sourceFile, objectKey);
    }

    /**
     * �R���\�[���ɃI�u�W�F�N�g�L�[�ƃA�C�e���T�C�Y��\�����āA�w�肵���o�P�b�g�̒��g�����X�g�A�b�v
     * �q���g: �N���C�A���g�I�u�W�F�N�g��listObjects()���\�b�h���g�p����
     * 
     * @param s3Client	 S3�N���C�A���g�I�u�W�F�N�g	
     * @param bucketName ���X�g�A�b�v���钆�g���i�[����o�P�b�g�̖��O
     */
    @Override
    public void listObjects(AmazonS3 s3Client, String bucketName) {
	//TODO: �X�[�p�[�N���X�̌Ăяo�����A�����̎������\�b�h�ƍ����ւ���
	super.listObjects(s3Client, bucketName);
    }

    /**
     * Change the ACL for the specified object to make it publicly readable.
     * Hint: Call the setObjectAcl() method of the client object. Use the CannedAccessControlList 
     * enumeration to set the ACL for the object to PublicRead.
     * 
     * @param s3Client	 S3�N���C�A���g�I�u�W�F�N�g	
     * @param bucketName �I�u�W�F�N�g���i�[����o�P�b�g�̖��O
     * @param key        The key used to identify the object.
     */
    @Override
    public void makeObjectPublic(AmazonS3 s3Client, String bucketName, String key) {
	//TODO: �X�[�p�[�N���X�̌Ăяo�����A�����̎������\�b�h�ƍ����ւ���
	super.makeObjectPublic(s3Client, bucketName, key);
    }

    /**
     * �w�肵���A�C�e���̎��O����URL���쐬���ĕԂ��BURL�̗L�������𐶐�����ꎞ�ԂƐݒ肷�� 
     * URL�̗L��������URL�𐶐����Ă���1���Ԃɐݒ肷�� 
     * �q���g: �N���C�A���g�I�u�W�F�N�g��generatePresignedUrl()���g�p����
     * 
     * @param s3Client	 S3�N���C�A���g�I�u�W�F�N�g	
     * @param bucketName �I�u�W�F�N�g���i�[����o�P�b�g�̖��O
     * @param key	 �I�u�W�F�N�g����肷��L�[
     * @return 		 �I�u�W�F�N�g�̎��O����URL
     */
    @Override
    public String generatePreSignedUrl(AmazonS3 s3Client, String bucketName, String key) {
	//TODO: �X�[�p�[�N���X�̌Ăяo�����A�����̎������\�b�h�ƍ����ւ���
	return super.generatePreSignedUrl(s3Client, bucketName, key);
    }

    /**
     * �w�肵���o�P�b�g���폜����B �N���C�A���g�I�u�W�F�N�g��deleteBucket()���\�b�h���g�p���ăo�P�b�g���폜���邪�A 
     * �܂��͂��߂Ƀo�P�b�g�̒��g���폜����K�v������B�A 
     * ���g���폜����ɂ́A�I�u�W�F�N�g��񋓂��A�X�ɍ폜���邩(DeleteObject() ���\�b�h) �A 
     * �o�b�`�ō폜����(DeleteObjects() ���\�b�h)
     * 
     * ���̃^�X�N�̖ړI�́A�g�p���Ă��Ȃ�AWS���\�[�X�̎�����������悤�ȃA�v���P�[�V�����̋L�q���������Ƃł�
     * 
     * 
     * @param s3Client	 S3�N���C�A���g�I�u�W�F�N�g	
     * @param bucketName �폜����o�P�b�g�̖��O
     */
    @Override
    public void deleteBucket(AmazonS3 s3Client, String bucketName) {
	//TODO: �X�[�p�[�N���X�̌Ăяo�����A�����̎������\�b�h�ƍ����ւ���.
	super.deleteBucket(s3Client, bucketName);
    }
}
