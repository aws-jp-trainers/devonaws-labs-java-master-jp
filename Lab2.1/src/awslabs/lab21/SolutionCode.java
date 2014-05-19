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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.amazonaws.regions.Region;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.CreateBucketRequest;
import com.amazonaws.services.s3.model.DeleteBucketRequest;
import com.amazonaws.services.s3.model.DeleteObjectsRequest;
import com.amazonaws.services.s3.model.DeleteObjectsRequest.KeyVersion;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.amazonaws.services.s3.model.ListObjectsRequest;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3ObjectSummary;

/**
 * Project: Lab2.1
 */
public abstract class SolutionCode implements ILabCode, IOptionalLabCode {

    @Override
    public void createBucket(AmazonS3 s3Client, String bucketName, Region region) {
    	// �^����ꂽ�o�P�b�g�����܂�CreateBucketRequest�I�u�W�F�N�g���쐬
		// ���[�W������us-east-1�ȊO�̏ꍇ�A���[�W�����̐�����w�肷��K�v������
    	CreateBucketRequest createBucketRequest;
		if (region.getName().equals("us-east-1")) {
			createBucketRequest = new CreateBucketRequest(bucketName);
		}
		else {
			createBucketRequest = new CreateBucketRequest(bucketName, com.amazonaws.services.s3.model.Region.fromValue(region.getName()));
		}

        //  s3Client�I�u�W�F�N�g��createBucket���\�b�h��p���ă��N�G�X�g�𑗐M
        s3Client.createBucket(createBucketRequest);
        
    }

    @Override
    public void putObject(AmazonS3 s3Client, String bucketName, String sourceFileName, String objectKey) {
        File sourceFile = new File(sourceFileName);

        // ���\�b�h�̃p�����[�^�Ŏw�肳�ꂽ�l��p���āAPutObjectRequest�I�u�W�F�N�g�𐶐� 
    	PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, objectKey, sourceFile);
    	
        // s3Client�I�u�W�F�N�g��putObject���\�b�h��p���ă��N�G�X�g�𑗐M���A�I�u�W�F�N�g���A�b�v���[�h
        s3Client.putObject(putObjectRequest);
    }

    @Override
    public void listObjects(AmazonS3 s3Client, String bucketName) {
        // �w�肳�ꂽ�o�P�b�g���ŁAListObjectsRequest�I�u�W�F�N�g�𐶐�
    	ListObjectsRequest listObjectsRequest = new ListObjectsRequest().withBucketName(bucketName);

        // s3Client�I�u�W�F�N�g��listObjects���\�b�h��p���ă��N�G�X�g�𑗐M
        ObjectListing objectListing = s3Client.listObjects(listObjectsRequest);

        // �R���\�[���ɃI�u�W�F�N�g�L�[�ƃT�C�Y��\�� 
        for (S3ObjectSummary objectSummary : objectListing.getObjectSummaries()) {
        	System.out.println(objectSummary.getKey() + " (size: " + objectSummary.getSize() + ")");
        }
    }

    @Override
    public void makeObjectPublic(AmazonS3 s3Client, String bucketName, String key) {

    	// s3Client�I�u�W�F�N�g��setObjectAcl���\�b�h ��p���āA�w�肵���I�u�W�F�N�g��ACL��
    	// CannedAccessControlList.PublicRead�ɐݒ�
        s3Client.setObjectAcl(bucketName, key, CannedAccessControlList.PublicRead);
    }

    @Override
    public String generatePreSignedUrl(AmazonS3 s3Client, String bucketName, String key) {
    	Date nowPlusOneHour = new Date(System.currentTimeMillis() + 3600000L);
    	
    	// �w�肵���I�u�W�F�N�g��GeneratePresignedUrlRequest�I�u�W�F�N�g�𐶐�
    	GeneratePresignedUrlRequest generatePresignedUrlRequest = new GeneratePresignedUrlRequest(bucketName, key);
    	// nowPlusOneHour�I�u�W�F�N�g�ւ̃��N�G�X�g�Ɋ܂߂�L�������̒l��ݒ� 
    	// (����ɂ��A������ꎞ�Ԍオ�w�肳���). 
    	generatePresignedUrlRequest.setExpiration(nowPlusOneHour);
    	
        // s3Client��generatePresignedUrl��p���āA���N�G�X�g�𑗐M
    	URL url = s3Client.generatePresignedUrl(generatePresignedUrlRequest);
    	// ������Ƃ���URL��Ԃ�
        return url.toString();
    }
    
    @Override
    public void deleteBucket(AmazonS3 s3Client, String bucketName) {
    	// �܂��͂��߂ɁA�o�P�b�g�̍폜�����݂�
    	DeleteBucketRequest deleteBucketRequest = new DeleteBucketRequest(bucketName);
    	
    	try {
    	    s3Client.deleteBucket(deleteBucketRequest);
    	    // �����ɓ��B�����ꍇ�A�G���[����������Ă��Ȃ����߃o�P�b�g�͍폜���ꂽ�Ƃ݂Ȃ��ă��^�[��
    	    return;
    	}
    	catch (AmazonS3Exception ex) {
    		if (!ex.getErrorCode().equals("BucketNotEmpty")) {
    			// �B�ꈵ����O��BucketNotEmpty�Ȃ̂ŁA����ȊO���ׂĂ͍ăX���[����
    			throw ex; 
    	    }
    	}
    	
    	// �����ɓ��B�����ꍇ�A�o�P�b�g�͋�ł͂Ȃ����߁A���g���폜���čĎ��s
    	List<KeyVersion> keys = new ArrayList<KeyVersion>();
    	for (S3ObjectSummary obj : s3Client.listObjects(bucketName).getObjectSummaries()) {
    	    // �I�u�W�F�N�g�̃��X�g�ɃL�[��ǉ�
    	    keys.add(new KeyVersion(obj.getKey()));
    	}
    	// ���N�G�X�g�𐶐����ăI�u�W�F�N�g���폜
    	DeleteObjectsRequest deleteObjectsRequest = new DeleteObjectsRequest(bucketName);
    	deleteObjectsRequest.withKeys(keys);
    	// �I�u�W�F�N�g�폜���N�G�X�g�𑗐M
    	s3Client.deleteObjects(deleteObjectsRequest);
    	
    	// �o�P�b�g�������ŋ�ƂȂ����̂ŁA�o�P�b�g�̍폜���Ď��s
    	s3Client.deleteBucket(deleteBucketRequest);
    }
}
