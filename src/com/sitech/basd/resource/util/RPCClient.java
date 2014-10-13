package com.sitech.basd.resource.util;

import java.util.UUID;

import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.QueueingConsumer;
import com.sitech.utils.rabbitmq.RabbitMQUtil;

@Service("rpcClient")
public class RPCClient {
	private Connection connection;
	private Channel channel;
	private String requestQueueName = "distribute.mac.dhcp";
	private String replyQueueName;
	private QueueingConsumer consumer;
	@Autowired
	private RabbitMQUtil rabbitmqUtil;
	public String call(String type,String mac) throws Exception {
		init();
		String response = null;
		String corrId = UUID.randomUUID().toString();

		BasicProperties props = new BasicProperties();
		props.setReplyTo(replyQueueName);
		props.setCorrelationId(corrId);

		JSONObject message = new JSONObject();
		message.put("type", type);
		if(mac!=null&&!"".equals(mac)){
			message.put("mac", mac);
		}
		channel.basicPublish("", requestQueueName, props, message.toString().getBytes());

		while (true) {
			QueueingConsumer.Delivery delivery = consumer.nextDelivery();
			if (delivery.getProperties().getCorrelationId().equals(corrId)) {
				response = new String(delivery.getBody(), "UTF-8");
				break;
			}
		}
		return response;
	}
	public void init(){
		try{
			Connection connection = rabbitmqUtil.getConnection();
			channel = connection.createChannel();

			replyQueueName = channel.queueDeclare().getQueue();
			consumer = new QueueingConsumer(channel);
			channel.basicConsume(replyQueueName, true, consumer);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void close() throws Exception {
		connection.close();
	}

	public static void main(String[] argv) {
		RPCClient fibonacciRpc = null;
		String response = null;
		try {
//			fibonacciRpc = new RPCClient();
			String cmd="{\"type\":\"get-leisure-ip\"}";
//			String cmd="{\"type\":\"set-leisure-ip\",\"mac\":\"00:FF:AE:AF:C1:7C\"}";
//			response = fibonacciRpc.call(cmd);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (fibonacciRpc != null) {
				try {
					fibonacciRpc.close();
				} catch (Exception ignore) {
				}
			}
		}
	}
}
