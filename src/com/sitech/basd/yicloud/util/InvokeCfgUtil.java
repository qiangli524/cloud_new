package com.sitech.basd.yicloud.util;

import org.springframework.web.client.RestTemplate;

/**
 * 
 * @Title: invoke
 * @Description: Spring模拟Http访问接口
 * @param
 * @return void
 * @throws
 * @author huojla
 * @version 1.0
 * @createtime Apr 19, 2012 2:55:45 PM
 */
public class InvokeCfgUtil {
	private static final String REST_IP = CfgUtil.getString("cloud.ip");
	private static final String REST_PORT = CfgUtil.getString("cloud.port");

	public static void invoke(String param) {
		try {
			String url = "http://" + REST_IP + ":" + REST_PORT + "/"
					+ CfgUtil.getString("cloud.web") + param;
			RestTemplate restTemplate = new RestTemplate();
			restTemplate.getForObject(url, String.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		invoke("/datacom_get.do");
	}
}
