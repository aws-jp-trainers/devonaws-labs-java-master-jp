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
 * プロジェクト: Lab3.1
 */
public class Lab31 {

	// TODO: 使用しているテーブルを含むリージョンを選択
	private static Region region = Region.getRegion(Regions.US_EAST_1);

	// NON-STUDENT CODE開始
	private static ILabCode labCode = new StudentCode();
	private static IOptionalLabCode optionalLabCode = new StudentCode();

	public static void main(String[] args) {
		try {
			// SQSクライアントの作成
			AmazonSQSClient sqsClient = new AmazonSQSClient(new ClasspathPropertiesFileCredentialsProvider());
			sqsClient.setRegion(region);

			// SNSクライアントの作成
			AmazonSNSClient snsClient = new AmazonSNSClient(new ClasspathPropertiesFileCredentialsProvider());
			snsClient.setRegion(region);

			String queueName = "Notifications";
			String topicName = "ClassroomEvent";

			// キューの作成は、単に削除して再作成する場合（コードエラーをトラックしている場合に起こりうる）失敗する
			// もしそれが発生した場合は、
			// 1分ほど停止して再試行する
			System.out.println("Creating " + queueName + " queue.");

			Boolean retry = true, notified = false;
			// 現在から60秒後のタイムアウトを作成する設定する
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
						// タイムアウトはまだ無効になっていないため、待って5秒後に再試行する
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

			// SQSキューをリストアップする
			System.out.println("Getting ARN for " + queueName + " queue.");
			String queueArn = labCode.getQueueArn(sqsClient, queueUrl);
			System.out.println("ARN for queue: " + queueArn);

			// SNSトピックを作成し、ARNを入手
			System.out.println("Creating " + topicName + " topic.");
			String topicArn = labCode.createTopic(snsClient, topicName);
			System.out.println("New topic ARN: " + topicArn);

			System.out.println("Granting the notification topic permission to post in the queue.");
			optionalLabCode.grantNotificationPermission(sqsClient, queueArn, queueUrl, topicArn);
			System.out.println("Permission granted.");

			// SNSサブスクリプションを作成
			System.out.println("Creating SNS subscription.");
			labCode.createSubscription(snsClient, queueArn, topicArn);
			System.out.println("Subscription created.");

			// トピックにメッセージを発行
			String messageText = "This is the SNS topic notification body.";
			String messageSubject = "SNSTopicNotification";

			System.out.println("Publishing SNS topic notification.");
			labCode.publishTopicMessage(snsClient, topicArn, messageSubject, messageText);
			System.out.println("Notification published.");

			// "Notifications"キューにメッセージを送る
			messageText = "This is the message posted to the queue directly.";
			System.out.println("Posting message to queue directly.");
			labCode.postToQueue(sqsClient, queueUrl, messageText);
			System.out.println("Message posted.");

			// レプリケートされたキューに送信結果が反映されることを期待し、少し待つ
			Thread.sleep(100);

			// キューからメッセージを読む
			System.out.println("Reading messages from queue.");

			List<Message> messages = labCode.readMessages(sqsClient, queueUrl);
			for(Message m : messages)
				System.out.println(m.getMessageId());
			// ここで2つまたは3つ (初回実行時は手で追加した notification がある) のメッセージが期待される
			if (messages.size() < 2) {
				// レプリケートされたキューに読み込み結果が反映されることを期待し、少し待つ
				Thread.sleep(100);
				// 再度読むことを試行し、一度に複数のメッセージを読み込めているか確認
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

			// SNSサブスクリプションを特定し、削除
			System.out.println("Removing provisioned resources.");
			labCode.deleteSubscriptions(snsClient, topicArn);
			System.out.println("Subscriptions removed.");

			// SNSトピックを削除
			labCode.deleteTopic(snsClient, topicArn);
			System.out.println("Topic deleted.");
			// 前に作成したキューを特定し、削除
			labCode.deleteQueue(sqsClient, queueUrl);
			System.out.println("Queue deleted.");
		} catch (Exception ex) {
			LabUtility.dumpError(ex);
		}
	}

	// コンソール画面に、メッセージ内容を表示
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
	// NON-STUDENT CODE終了

}
