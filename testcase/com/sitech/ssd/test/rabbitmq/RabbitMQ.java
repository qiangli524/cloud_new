package com.sitech.ssd.test.rabbitmq;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Map;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.GetResponse;

public class RabbitMQ implements Runnable {

	public static String sendMessage(String message) {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("172.21.3.119");
		factory.setPort(5673);
		Connection conn = null;
		Channel channel = null;
		try {
			conn = factory.newConnection();
			channel = conn.createChannel();
			// 创建队列,重启后重新建立,最后一个消费者断开时不自动删除
			channel.queueDeclare("socket.io.task", true, false, false, null);
			// String message = "恰饭!";

			// 创建名称为EXCHANGE ,类型为fanout的交换机
			// channel.exchangeDeclare("task_socket", "topic", true, false,
			// null);

			// AMQP.BasicProperties.Builder bob = new
			// AMQP.BasicProperties.Builder();
			// AMQP.BasicProperties minBasic = bob.build();
			// AMQP.BasicProperties minPersistentBasic =
			// bob.deliveryMode(2).build();
			// AMQP.BasicProperties persistentBasic
			// =
			// bob.priority(0).contentType("application/octet-stream").build();
			// AMQP.BasicProperties persistentTextPlain =
			// bob.contentType("text/plain").build();

			// BasicProperties properties = new BasicProperties();
			// BasicProperties.Builder builder = properties.builder();
			// BasicProperties persistentTextPlain =
			// bob.contentType("text/plain").build();

			// builder.contentEncoding("UTF-8");
			// builder.contentType("text/plain");
			// 发送消息
			channel.basicPublish("", "socket.io.task", new AMQP.BasicProperties.Builder()
					.contentType("application/json").contentEncoding("utf-8").build(),
					message.getBytes("utf-8"));
			System.out.println("send message " + message);
			channel.close();
			conn.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				channel.close();
				conn.close();
			} catch (Exception e) {

			}
		}
		return null;
	}

	public static String getMessage() {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("172.21.3.17");
		factory.setPort(5673);
		Connection conn = null;
		Channel channel = null;
		try {
			conn = factory.newConnection();
			channel = conn.createChannel();
			GetResponse response = channel.basicGet("socket.io.task", true);
			// byte[] bytes = response.getBody();
			// String message = new String(b);
			// bytearray to object
			// ByteArrayInputStream bai = new ByteArrayInputStream(bytes);
			// ObjectInputStream oi = new ObjectInputStream(bai);
			// Object obj = oi.readObject();
			// Map<String, String> map = null;
			// if (obj instanceof Map) {
			// map = (Map<String, String>) obj;
			// }
			System.out.println("get message " + new String(response.getBody(), "utf-8"));
			channel.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				channel.close();
				conn.close();
			} catch (Exception e) {

			}
		}
		return null;
	}

	public void run() {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("172.21.3.119");
		factory.setPort(5673);
		Connection conn = null;
		Channel channel = null;
		try {
			conn = factory.newConnection();
			channel = conn.createChannel();
			// String str = channel.basicConsume("Hello", true, "tag", null);
			GetResponse response = null;
			while (true) {
				response = channel.basicGet("Hello", true);
				if (response != null) {
					break;
				}

			}
			byte[] bytes = response.getBody();
			// String message = new String(b);
			// bytearray to object
			ByteArrayInputStream bai = new ByteArrayInputStream(bytes);
			ObjectInputStream oi = new ObjectInputStream(bai);
			Object obj = oi.readObject();
			Map<String, String> map = null;
			if (obj instanceof Map) {
				map = (Map<String, String>) obj;
			}
			System.out.println("get message " + map.get("duangh"));
			channel.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				channel.close();
				conn.close();
			} catch (Exception e) {

			}
		}
	}

	public static void main(String[] args) {
		for (int i = 0; i < 1; i++) {
			String message1 = "{\"creater\":\"admin\",\"message\":\"60\",\"type\":\"start\",\"uuid\":\"00d0c1d45e4f44ad9028a4573c7323a5\"}";
			String message2 = "{\"creater\":\"admin\",\"message\":\"70\",\"type\":\"process\",\"uuid\":\"00d0c1d45e4f44ad9028a4573c7323a5\",\"content\":\"变了没有?\",\"progress\":\"70\"}";
			String message3 = "{\"creater\":\"admin\",\"message\":\"80\",\"type\":\"process\",\"uuid\":\"00d0c1d45e4f44ad9028a4573c7323a5\",\"content\":\"又变了？?\",\"progress\":\"80\"}";
			String message4 = "{\"creater\":\"admin\",\"message\":\"90\",\"type\":\"process\",\"uuid\":\"00d0c1d45e4f44ad9028a4573c7323a5\",\"content\":\"看见变化了吗?\",\"progress\":\"90\"}";
			String message5 = "{\"creater\":\"admin\",\"message\":\"100\",\"type\":\"process\",\"uuid\":\"00d0c1d45e4f44ad9028a4573c7323a5\",\"content\":\"再变一次\",\"progress\":\"100\"}";
			String message6 = "{\"creater\":\"admin\",\"content\":\"部署实例完成!\",\"type\":\"finished\",\"uuid\":\"89c1cbd0-2293-48b1-a5b4-2d0e42798c39_1251\"}";
			try {
				sendMessage(message1);
				sendMessage(message2);
				sendMessage(message3);
				sendMessage(message4);
				sendMessage(message5);
				sendMessage(message6);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		// jsonp
		// try {
		// Thread.sleep(3000);
		// } catch (InterruptedException e) {
		// e.printStackTrace();
		// }
		// getMessage();
		// RabbitMQ rabbit = new RabbitMQ();
		// Thread thread = new Thread(rabbit);
		// thread.start();

	}
}
