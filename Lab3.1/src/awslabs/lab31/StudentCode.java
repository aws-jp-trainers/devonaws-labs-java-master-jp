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
package awslabs.lab31;

import java.util.List;

import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sqs.AmazonSQSClient;
import com.amazonaws.services.sqs.model.Message;

/**
 * �v���W�F�N�g: Lab3.1
 */
public class StudentCode extends SolutionCode {

	/**
	 * �^����ꂽ�L���[����p����SQS�L���[���쐬���A�V�����L���[��URL��Ԃ�
	 * �q���g�F�N���C�A���g�I�u�W�F�N�g��createQueue()���\�b�h���g�p���� URL�͖߂�l
	 * 
	 * @param sqsClient SQS�N���C�A���g�I�u�W�F�N�g
	 * @param queueName �쐬����L���[��
	 * @return �V���ɍ쐬���ꂽ�L���[��URL
	 */
	@Override
	public String createQueue(AmazonSQSClient sqsClient, String queueName) {
		// TODO: �X�[�p�[�N���X�̌Ăяo�����A�����̎������\�b�h�ƍ����ւ���
		return super.createQueue(sqsClient, queueName);
	}

	/**
	 * �w�肳�ꂽ�L���[��ARN�ɑ΂�SQS�T�[�r�X���N�G�����ĕԂ� 
	 * �q���g�F�N���C�A���g�I�u�W�F�N�g��getQueueAttributes()���\�b�h���g�p����
	 * ���N�G�X�g���鑮����QueueArn�Ɩ���
	 * @param sqsClient SQS�N���C�A���g�I�u�W�F�N�g
	 * @param queueUrl ��������L���[��URL
	 * @return �L���[��ARN���܂ޕ�����
	 */
	@Override
	public String getQueueArn(AmazonSQSClient sqsClient, String queueUrl) {
		// TODO: �X�[�p�[�N���X�̌Ăяo�����A�����̎������\�b�h�ƍ����ւ���
		return super.getQueueArn(sqsClient, queueUrl);
	}

	/**
	 * SNS�g�s�b�N���쐬���A�V���ɍ쐬�����g�s�b�N��ARN��Ԃ��B�q���g�F�N���C�A���g�I�u�W�F�N�g��createTopic() ���\�b�h���g�p����
	 * �߂�l�Ƀg�s�b�N��ARN���܂܂��
	 * 
	 * @param snsClient SNS�N���C�A���g�I�u�W�F�N�g
	 * @param topicName �쐬����g�s�b�N��
	 * @return �V���ɍ쐬�����g�s�b�N��ARN
	 */
	@Override
	public String createTopic(AmazonSNSClient snsClient, String topicName) {
		// TODO: �X�[�p�[�N���X�̌Ăяo�����A�����̎������\�b�h�ƍ����ւ���
		return super.createTopic(snsClient, topicName);
	}

	/**
	 * SQS�L���[�֒ʒm�𔭍s����SNS�T�u�X�N���v�V�������쐬����B�q���g�F�N���C�A���g�I�u�W�F�N�g�� subscribe()���\�b�h���g�p����
	 * �T�u�X�N���v�V�����G���h�|�C���g�Ƃ�queueArn�p�����[�^�ŗ^������
	 * 
	 * @param snsClient SNS�N���C�A���g�I�u�W�F�N�g
	 * @param queueArn �T�u�X�N���v�V�����G���h�|�C���g�Ŏg�p����L���[��ARN
	 * @param topicArn �T�u�X�N���C�u����g�s�b�N��ARN
	 */
	@Override
	public void createSubscription(AmazonSNSClient snsClient, String queueArn, String topicArn) {
		// TODO: �X�[�p�[�N���X�̌Ăяo�����A�����̎������\�b�h�ƍ����ւ���
		super.createSubscription(snsClient, queueArn, topicArn);
	}

	/**
	 * �w�肳�ꂽSNS�g�s�b�N�ւ̃��b�Z�[�W�𔭍s�B�q���g�F�N���C�A���g�I�u�W�F�N�g��publish() ���\�b�h���g�p����
	 * 
	 * @param snsClient SNS�N���C�A���g�I�u�W�F�N�g
	 * @param topicArn ���b�Z�[�W�𑗂�g�s�b�N��ARN
	 * @param subject ���s���郁�b�Z�[�W�̌���
	 * @param message ���s���郁�b�Z�[�W�̒��g
	 */
	@Override
	public void publishTopicMessage(AmazonSNSClient snsClient, String topicArn, String subject, String message) {
		// TODO: �X�[�p�[�N���X�̌Ăяo�����A�����̎������\�b�h�ƍ����ւ���
		super.publishTopicMessage(snsClient, topicArn, subject, message);
	}

	/**
	 * �w�肳�ꂽ�L���[�Ƀ��b�Z�[�W�𑗂�B�q���g�F�N���C�A���g�I�u�W�F�N�g��sendMessage() ���\�b�h���g�p����
	 * 
	 * @param sqsClient SQS�N���C�A���g�I�u�W�F�N�g
	 * @param queueUrl ���b�Z�[�W��u���L���[��URL
	 * @param messageText �L���[�ɒu�����b�Z�[�W�̒��g
	 */
	@Override
	public void postToQueue(AmazonSQSClient sqsClient, String queueUrl, String messageText) {
		// TODO: �X�[�p�[�N���X�̌Ăяo�����A�����̎������\�b�h�ƍ����ւ���
		super.postToQueue(sqsClient, queueUrl, messageText);
	}

	/**
	 * 1�̃��N�G�X�g�Ŏw�肳�ꂽSQS�L���[����10���b�Z�[�W���炢�܂œǂݍ��ށB�q���g�F�N���C�A���g�I�u�W�F�N�g��receiveMessage()���\�b�h���g�p����
	 * ���N�G�X�g�ł́A�ő僁�b�Z�[�W����10�ɐݒ�
	 * 
	 * @param sqsClient SQS�N���C�A���g�I�u�W�F�N�g
	 * @param queueUrl ���b�Z�[�W���܂ރL���[��URL
	 * @return �L���[����̃��b�Z�[�W�̃��X�g
	 */
	@Override
	public List<Message> readMessages(AmazonSQSClient sqsClient, String queueUrl) {
		// TODO: �X�[�p�[�N���X�̌Ăяo�����A�����̎������\�b�h�ƍ����ւ���
		return super.readMessages(sqsClient, queueUrl);
	}

	/**
	 * �w�肳�ꂽ�L���[����w�肳�ꂽ���b�Z�[�W���폜����B�q���g�F�N���C�A���g�I�u�W�F�N�g��deleteMessage()���\�b�h���g�p����
	 * 
	 * @param sqsClient SQS�N���C�A���g�I�u�W�F�N�g
	 * @param queueUrl ���b�Z�[�W���܂ރL���[URL
	 * @param receiptHandle �폜���郁�b�Z�[�W��receipt handle
	 */
	@Override
	public void removeMessage(AmazonSQSClient sqsClient, String queueUrl, String receiptHandle) {
		// TODO: �X�[�p�[�N���X�̌Ăяo�����A�����̎������\�b�h�ƍ����ւ���
		super.removeMessage(sqsClient, queueUrl, receiptHandle);
	}

	/**
	 * �w�肳�ꂽSNS�g�s�b�N�̃T�u�X�N���v�V���������ׂč폜����B Hint: �N���C�A���g�I�u�W�F�N�g�� getSubscriptions() ���\�b�h�ł��ׂẴT�u�X�N���v�V�������Ăяo���A
	 * �N���C�A���g�I�u�W�F�N�g��unsubscribe()���\�b�h��p���ďڍׂƂƂ��ɌJ��Ԃ�
	 * 
	 * @param snsClient SNS�N���C�A���g�I�u�W�F�N�g
	 * @param topicArn �T�u�X�N���v�V�������폜����SNS�g�s�b�N
	 */
	@Override
	public void deleteSubscriptions(AmazonSNSClient snsClient, String topicArn) {
		// TODO: �X�[�p�[�N���X�̌Ăяo�����A�����̎������\�b�h�ƍ����ւ���
		super.deleteSubscriptions(snsClient, topicArn);
	}

	/**
	 * �w�肵��SNS�g�s�b�N���폜����B Hint: Use the deleteTopic() method of the client object.
	 * 
	 * @param snsClient SNS�N���C�A���g�I�u�W�F�N�g
	 * @param topicArn �폜����g�s�b�N��ARN
	 */
	@Override
	public void deleteTopic(AmazonSNSClient snsClient, String topicArn) {
		// TODO: �X�[�p�[�N���X�̌Ăяo�����A�����̎������\�b�h�ƍ����ւ���
		super.deleteTopic(snsClient, topicArn);
	}

	/**
	 * �w�肵��SQS�L���[���폜����B Hint: �N���C�A���g�I�u�W�F�N�g��deleteQueue() ���\�b�h���g�p����
	 * 
	 * @param sqsClient SQS�N���C�A���g�I�u�W�F�N�g
	 * @param queueUrl �폜����L���[��URL
	 */
	@Override
	public void deleteQueue(AmazonSQSClient sqsClient, String queueUrl) {
		// TODO: �X�[�p�[�N���X�̌Ăяo�����A�����̎������\�b�h�ƍ����ւ���
		super.deleteQueue(sqsClient, queueUrl);
	}

	// �I�v�V�����^�X�N
	/**
	 * �^����ꂽSNS�g�s�b�N���A���g�̃L���[�Ƀ��b�Z�[�W�𔭍s����p�[�~�b�V������t�^����B������������邽�߂ɂ́A
	 * �K�؂Ɏw�肳�ꂽ�|���V�[�X�e�[�g�����g���쐬���A�L���[��Policy�����ɂ�������蓖�Ă�K�v������
	 * �i���������̃^�X�N�����s���邽�߂ɂ͒������K�v�ł��j
	 * 
	 * @param sqsClient SQS�N���C�A���g�I�u�W�F�N�g
	 * @param queueArn �L���[���`����ARN�B�|���V�[�X�e�[�g�����g�ɂ����āAResource�Ƃ��ėp������
	 * @param queueUrl �L���[��URL�B�|���V�[�������X�V����ړI�ŃL���[����肷��̂Ɏg�p�����
	 *            
	 * @param topicArn �L���[�ɔ��s����g�s�b�N��ARN�B �|���V�[�X�e�[�g�����g�ɂ�����ARN Condition�̃\�[�X�Ƃ��ėp������
	 *            .
	 */
	@Override
	public void grantNotificationPermission(AmazonSQSClient sqsClient, String queueArn, String queueUrl, String topicArn) {
		// TODO: �X�[�p�[�N���X�̌Ăяo�����A�����̎������\�b�h�ƍ����ւ���
		super.grantNotificationPermission(sqsClient, queueArn, queueUrl, topicArn);
	}
}
