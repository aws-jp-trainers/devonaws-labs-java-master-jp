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

import java.util.Date;
import java.util.List;
import java.util.Map.Entry;

import awslabs.labutility.LabUtility;

import com.amazonaws.auth.ClasspathPropertiesFileCredentialsProvider;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sqs.AmazonSQSClient;
import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.QueueDeletedRecentlyException;

/**
 * �v���W�F�N�g: Lab3.1
 */
public class Lab31 {

	// TODO: �g�p���Ă���e�[�u�����܂ރ��[�W������I��
	private static Region region = Region.getRegion(Regions.US_EAST_1);

	// NON-STUDENT CODE�J�n
	private static ILabCode labCode = new StudentCode();
	private static IOptionalLabCode optionalLabCode = new StudentCode();

	public static void main(String[] args) {
		try {
			// SQS�N���C�A���g�̍쐬
			AmazonSQSClient sqsClient = new AmazonSQSClient(new ClasspathPropertiesFileCredentialsProvider());
			sqsClient.setRegion(region);

			// SNS�N���C�A���g�̍쐬
			AmazonSNSClient snsClient = new AmazonSNSClient(new ClasspathPropertiesFileCredentialsProvider());
			snsClient.setRegion(region);

			String queueName = "Notifications";
			String topicName = "ClassroomEvent";

			// �L���[�̍쐬�́A�P�ɍ폜���čč쐬����ꍇ�i�R�[�h�G���[���g���b�N���Ă���ꍇ�ɋN���肤��j���s����
			// �������ꂪ���������ꍇ�́A
			// 1���قǒ�~���čĎ��s����
			System.out.println("Creating " + queueName + " queue.");

			Boolean retry = true, notified = false;
			// ���݂���60�b��̃^�C���A�E�g���쐬����ݒ肷��
			Date timeout = new Date(System.currentTimeMillis() + 60000L);
			String queueUrl = "";

			while (retry) {
				try {
					queueUrl = labCode.createQueue(sqsClient, queueName);
					retry = false;
				} catch (QueueDeletedRecentlyException ex) {
					if (new Date().before(timeout)) {
						if (!notified) {
							System.out
									.println("The attempt to recreate the queue failed because the queue was deleted too");
							System.out.println("recently. Waiting and retrying for up to 1 minute.");
							notified = true;
						}
						// �^�C���A�E�g�͂܂������ɂȂ��Ă��Ȃ����߁A�҂���5�b��ɍĎ��s����
						System.out.print(".");
						Thread.sleep(5000);
					} else {
						System.out.println("Retry timeout expired. Aborting.");
						throw ex;
					}
				}

			}
			if (notified) {
				System.out.println("Recovered.");
			}

			System.out.println("URL for new queue: " + queueUrl);

			// SQS�L���[�����X�g�A�b�v����
			System.out.println("Getting ARN for " + queueName + " queue.");
			String queueArn = labCode.getQueueArn(sqsClient, queueUrl);
			System.out.println("ARN for queue: " + queueArn);

			// SNS�g�s�b�N���쐬���AARN�����
			System.out.println("Creating " + topicName + " topic.");
			String topicArn = labCode.createTopic(snsClient, topicName);
			System.out.println("New topic ARN: " + topicArn);

			System.out.println("Granting the notification topic permission to post in the queue.");
			optionalLabCode.grantNotificationPermission(sqsClient, queueArn, queueUrl, topicArn);
			System.out.println("Permission granted.");

			// SNS�T�u�X�N���v�V�������쐬
			System.out.println("Creating SNS subscription.");
			labCode.createSubscription(snsClient, queueArn, topicArn);
			System.out.println("Subscription created.");

			// �g�s�b�N�Ƀ��b�Z�[�W�𔭍s
			String messageText = "This is the SNS topic notification body.";
			String messageSubject = "SNSTopicNotification";

			System.out.println("Publishing SNS topic notification.");
			labCode.publishTopicMessage(snsClient, topicArn, messageSubject, messageText);
			System.out.println("Notification published.");

			// "Notifications"�L���[�Ƀ��b�Z�[�W�𑗂�
			messageText = "This is the message posted to the queue directly.";
			System.out.println("Posting message to queue directly.");
			labCode.postToQueue(sqsClient, queueUrl, messageText);
			System.out.println("Message posted.");

			// ���v���P�[�g���ꂽ�L���[�ɑ��M���ʂ����f����邱�Ƃ����҂��A�����҂�
			Thread.sleep(100);

			// �L���[���烁�b�Z�[�W��ǂ�
			System.out.println("Reading messages from queue.");

			List<Message> messages = labCode.readMessages(sqsClient, queueUrl);
			for(Message m : messages)
				System.out.println(m.getMessageId());
			// ������2�܂���3�� (������s���͎�Œǉ����� notification ������) �̃��b�Z�[�W�����҂����
			if (messages.size() < 2) {
				// ���v���P�[�g���ꂽ�L���[�ɓǂݍ��݌��ʂ����f����邱�Ƃ����҂��A�����҂�
				Thread.sleep(100);
				// �ēx�ǂނ��Ƃ����s���A��x�ɕ����̃��b�Z�[�W��ǂݍ��߂Ă��邩�m�F
				messages.addAll(labCode.readMessages(sqsClient, queueUrl));
				for(Message m : messages)
					System.out.println(m.getMessageId());
				if (messages.size() < 2) {
					System.err.println(">>WARNING<< We didn't receive the expected number of messages. Too little thread sleep? Investigate.");
				} else {
					StringBuilder sb = new StringBuilder();
					String nl = System.getProperty("line.separator");
					sb.append(nl);
					sb.append("============================================================================" + nl);
					sb.append("PROBLEM: ReadMessages() had to be called twice to collect all the messages." + nl);
					sb.append("         Did you remember to set the MaxNumberOfMessages property in the " + nl);
					sb.append("         ReceiveMessageRequest object?" + nl);
					sb.append("============================================================================" + nl);
					sb.append(nl);
					System.err.print(sb);

				}
			}
			PrintAndRemoveMessagesInResponse(sqsClient, messages, queueUrl);

			// SNS�T�u�X�N���v�V��������肵�A�폜
			System.out.println("Removing provisioned resources.");
			labCode.deleteSubscriptions(snsClient, topicArn);
			System.out.println("Subscriptions removed.");

			// SNS�g�s�b�N���폜
			labCode.deleteTopic(snsClient, topicArn);
			System.out.println("Topic deleted.");
			// �O�ɍ쐬�����L���[����肵�A�폜
			labCode.deleteQueue(sqsClient, queueUrl);
			System.out.println("Queue deleted.");
		} catch (Exception ex) {
			LabUtility.dumpError(ex);
		}
	}

	// �R���\�[����ʂɁA���b�Z�[�W���e��\��
	private static void PrintAndRemoveMessagesInResponse(AmazonSQSClient sqsClient, List<Message> messages,
			String queueUrl) {

		for (Message message : messages) {

			System.out.println("\nQueue Message:");

			System.out.println("\tMessageId : " + message.getMessageId());
			System.out.println("\tMD5OfBody : " + message.getMD5OfBody());
			System.out.println("\tBody : " + message.getBody());

			if (message.getAttributes().size() > 0) {
				System.out.println("\tMessage Attributes");

				for (Entry<String, String> entry : message.getAttributes().entrySet()) {
					System.out.println("\t\t" + entry.getKey() + " : " + entry.getValue());
				}
			}

			System.out.println("\nDeleting message.");
			labCode.removeMessage(sqsClient, queueUrl, message.getReceiptHandle());
			System.out.println("Message deleted.");
		}
	}
	// NON-STUDENT CODE�I��

}
