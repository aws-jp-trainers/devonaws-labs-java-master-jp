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

import java.util.HashMap;
import java.util.List;

import com.amazonaws.auth.policy.Policy;
import com.amazonaws.auth.policy.Principal;
import com.amazonaws.auth.policy.Resource;
import com.amazonaws.auth.policy.Statement;
import com.amazonaws.auth.policy.Statement.Effect;
import com.amazonaws.auth.policy.actions.SQSActions;
import com.amazonaws.auth.policy.conditions.ConditionFactory;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.model.CreateTopicRequest;
import com.amazonaws.services.sns.model.CreateTopicResult;
import com.amazonaws.services.sns.model.DeleteTopicRequest;
import com.amazonaws.services.sns.model.ListSubscriptionsByTopicRequest;
import com.amazonaws.services.sns.model.ListSubscriptionsByTopicResult;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.SubscribeRequest;
import com.amazonaws.services.sns.model.Subscription;
import com.amazonaws.services.sns.model.UnsubscribeRequest;
import com.amazonaws.services.sqs.AmazonSQSClient;
import com.amazonaws.services.sqs.model.CreateQueueRequest;
import com.amazonaws.services.sqs.model.CreateQueueResult;
import com.amazonaws.services.sqs.model.DeleteMessageRequest;
import com.amazonaws.services.sqs.model.DeleteQueueRequest;
import com.amazonaws.services.sqs.model.GetQueueAttributesRequest;
import com.amazonaws.services.sqs.model.GetQueueAttributesResult;
import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.ReceiveMessageRequest;
import com.amazonaws.services.sqs.model.ReceiveMessageResult;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.amazonaws.services.sqs.model.SetQueueAttributesRequest;

/**
 * �v���W�F�N�g: Lab3.1
 */
public abstract class SolutionCode implements ILabCode, IOptionalLabCode {

	@Override
	public String createQueue(AmazonSQSClient sqsClient, String queueName) {
		// TODO: �^����ꂽ�L���[�����g�p���āACreateQueueRequest�I�u�W�F�N�g�𐶐�
		CreateQueueRequest createQueueRequest = new CreateQueueRequest().withQueueName(queueName);

		// TODO: sqsClient�I�u�W�F�N�g��createQueue���\�b�h���g�p���ă��N�G�X�g�𑗐M.
		CreateQueueResult createQueueResult = sqsClient.createQueue(createQueueRequest);

		// TODO: ���N�G�X�g�̌��ʂ���A�L���[URL��Ԃ�
		return createQueueResult.getQueueUrl();
	}

	@Override
	public String getQueueArn(AmazonSQSClient sqsClient, String queueUrl) {
		// TODO: �w�肵���L���[�����"QueueArn"�Ƃ������O�̑�����GetQueueAttributesRequest�𐶐�����
		GetQueueAttributesRequest getQueueAttributesRequest = new GetQueueAttributesRequest().withQueueUrl(queueUrl)
				.withAttributeNames("QueueArn");

		// TODO: sqsClient��getQueueAttributes���\�b�h��p���ă��N�G�X�g�𑗐M.
		GetQueueAttributesResult getQueueAttributesResult = sqsClient.getQueueAttributes(getQueueAttributesRequest);

		// TODO: QueueArn�����l��Ԃ�
		return getQueueAttributesResult.getAttributes().get("QueueArn");
	}

	@Override
	public String createTopic(AmazonSNSClient snsClient, String topicName) {
		// TODO: �w�肳�ꂽ�g�s�b�N����CreateTopicRequest�I�u�W�F�N�g�𐶐�
		CreateTopicRequest createTopicRequest = new CreateTopicRequest().withName(topicName);

		// TODO: snsClient�I�u�W�F�N�g��createTopic���\�b�h���g�p���ă��N�G�X�g�𑗐M.
		CreateTopicResult createTopicResult = snsClient.createTopic(createTopicRequest);

		// TODO: ���N�G�X�g�̌��ʂ���g�s�b�NARN��Ԃ�.
		return createTopicResult.getTopicArn();
	}

	@Override
	public void createSubscription(AmazonSNSClient snsClient, String queueArn, String topicArn) {
		// TODO: �w�肳�ꂽ�L���[ARN�G���h�|�C���g����уg�s�b�NARN��"sqs"�v���g�R����p����SubscribeRequest�I�u�W�F�N�g�𐶐�
		// 
		SubscribeRequest subscribeRequest = new SubscribeRequest().withEndpoint(queueArn).withProtocol("sqs")
				.withTopicArn(topicArn);

		// TODO: snsClient�I�u�W�F�N�g��subscribe���\�b�h��p���ă��N�G�X�g�𑗐M
		snsClient.subscribe(subscribeRequest);
	}

	@Override
	public void publishTopicMessage(AmazonSNSClient snsClient, String topicArn, String subject, String message) {
		// TODO: �^����ꂽ����(subject)�A���b�Z�[�W(message�j�A����уg�s�b�NARN(topic ARN)��p����PublishRequestConstruct�I�u�W�F�N�g�𐶐�
		PublishRequest publishRequest = new PublishRequest().withMessage(message).withSubject(subject)
				.withTopicArn(topicArn);

		// TODO: snsClient�I�u�W�F�N�g��publish���\�b�h��p���ă��N�G�X�g�𑗐M
		snsClient.publish(publishRequest);
	}

	@Override
	public void postToQueue(AmazonSQSClient sqsClient, String queueUrl, String messageText) {
		// TODO: �^����ꂽURL����у��b�Z�[�W��p����SendMessageRequest�I�u�W�F�N�g�𐶐�
		SendMessageRequest sendMessageRequest = new SendMessageRequest().withMessageBody(messageText).withQueueUrl(
				queueUrl);

		// TODO: sqsClient�I�u�W�F�N�g��sendMessage���\�b�h��p���ă��N�G�X�g�𑗐M
		sqsClient.sendMessage(sendMessageRequest);
	}

	@Override
	public List<Message> readMessages(AmazonSQSClient sqsClient, String queueUrl) {
		ReceiveMessageResult receiveMessageResult;

		// TODO: �^����ꂽ�L���[URL��p���āAReceiveMessageRequest�I�u�W�F�N�g�𐶐�
		// �ő僁�b�Z�[�W����10�ɐݒ�
		ReceiveMessageRequest receiveMessageRequest = new ReceiveMessageRequest().withMaxNumberOfMessages(10)
				.withQueueUrl(queueUrl);

		// TODO: sqsClient�I�u�W�F�N�g��receiveMessage���\�b�h��p���ă��N�G�X�g�𑗐M
		// ���ɒ�`����Ă���receiveMessageResult�I�u�W�F�N�g�Ɍ��ʂ��i�[
		receiveMessageResult = sqsClient.receiveMessage(receiveMessageRequest);

		return receiveMessageResult.getMessages();
	}

	@Override
	public void removeMessage(AmazonSQSClient sqsClient, String queueUrl, String receiptHandle) {
		// TODO: �^����ꂽ�L���[URL�����receipt handle��p���āADeleteMessageRequest�I�u�W�F�N�g�𐶐�
		DeleteMessageRequest deleteMessageRequest = new DeleteMessageRequest().withQueueUrl(queueUrl)
				.withReceiptHandle(receiptHandle);

		// TODO: sqsClient�I�u�W�F�N�g��deleteMessage���\�b�h��p���ă��N�G�X�g�𑗐M
		sqsClient.deleteMessage(deleteMessageRequest);
	}

	@Override
	public void deleteSubscriptions(AmazonSNSClient snsClient, String topicArn) {
		// TODO: �^����ꂽ�g�s�b�NARN��p���āAListSubscriptionsByTopicRequest�I�u�W�F�N�g�𐶐�
		ListSubscriptionsByTopicRequest listSubscriptionsByTopicRequest = new ListSubscriptionsByTopicRequest()
				.withTopicArn(topicArn);

		// TODO: snsClient�I�u�W�F�N�g��listSubscriptionsByTopic���\�b�h��p���ă��N�G�X�g�𑗐M
		ListSubscriptionsByTopicResult listSubscriptionsByTopicResult = snsClient
				.listSubscriptionsByTopic(listSubscriptionsByTopicRequest);

		// TODO: ���N�G�X�g���ʂ̃T�u�X�N���v�V�����̌J��Ԃ�
		// TODO: �e�T�u�X�N���v�V�����ɑ΂��A�T�u�X�N���v�V����ARN��p����UnsubscribeRequest�I�u�W�F�N�g�𐶐�
		// TODO: �e�T�u�X�N���v�V�����ɑ΂��AsnsClient�I�u�W�F�N�g��unsubscribe���\�b�h��p���đ��M
		for (Subscription subscription : listSubscriptionsByTopicResult.getSubscriptions()) {
			UnsubscribeRequest unsubscribeRequest = new UnsubscribeRequest().withSubscriptionArn(subscription
					.getSubscriptionArn());
			snsClient.unsubscribe(unsubscribeRequest);
		}
	}

	@Override
	public void deleteTopic(AmazonSNSClient snsClient, String topicArn) {
		// TODO: �^����ꂽ�g�s�b�NARN��p����DeleteTopicRequest�I�u�W�F�N�g�𐶐�
		DeleteTopicRequest deleteTopicRequest = new DeleteTopicRequest().withTopicArn(topicArn);

		// TODO: snsClient�I�u�W�F�N�g��deleteTopic���\�b�h��p���ă��N�G�X�g�𑗐M
		snsClient.deleteTopic(deleteTopicRequest);
	}

	@Override
	public void deleteQueue(AmazonSQSClient sqsClient, String queueUrl) {
		// TODO: �^����ꂽ�L���[URL��p����DeleteQueueRequest�I�u�W�F�N�g�𐶐�
		DeleteQueueRequest deleteQueueRequest = new DeleteQueueRequest().withQueueUrl(queueUrl);

		// TODO: sqsClient�I�u�W�F�N�g��deleteQueue���\�b�h��p���ă��N�G�X�g�𑗐M
		sqsClient.deleteQueue(deleteQueueRequest);
	}

	@Override
	public void grantNotificationPermission(AmazonSQSClient sqsClient, String queueArn, String queueUrl, String topicArn) {

		Statement statement = new Statement(Effect.Allow).withActions(SQSActions.SendMessage)
				.withPrincipals(new Principal("*")).withConditions(ConditionFactory.newSourceArnCondition(topicArn))
				.withResources(new Resource(queueArn));

		Policy policy = new Policy("SubscriptionPermission").withStatements(statement);

		HashMap<String, String> attributes = new HashMap<String, String>();
		attributes.put("Policy", policy.toJson());

		// �|���V�[�̃L���[�������Z�b�g���邽�߂Ƀ��N�G�X�g���쐬
		SetQueueAttributesRequest setQueueAttributesRequest = new SetQueueAttributesRequest().withQueueUrl(queueUrl)
				.withAttributes(attributes);

		// �L���[�|���V�[���Z�b�g
		sqsClient.setQueueAttributes(setQueueAttributesRequest);
	}
}
