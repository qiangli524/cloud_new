package com.sitech.basd.yicloud.util;

import org.apache.log4j.Logger;
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
public class InvokeUtil {
	private static final String REST_IP = CfgUtil.getString("rest.ip");
	private static final String REST_PORT = CfgUtil.getString("rest.port");

	public static String invoke(String param) {
		String result = "";
		try {
			String url = "http://" + REST_IP + ":" + REST_PORT + "/"
					+ CfgUtil.getString("rest.web") + param;
			// Set the Accept header for "application/json"
			// HttpHeaders requestHEADERS = NEW HTTPHEADERS();
			// LIST<MEDIATYPE> ACCEPTABLEMEDIATYPES = NEW
			// ARRAYLIST<MEDIATYPE>();
			// ACCEPTABLEMEDIATYPES.ADD(MEDIATYPE.APPLICATION_JSON);
			// REQUESTHEADERS.SETACcept(acceptableMediaTypes);
			// Create a new RestTemplate instance
			RestTemplate restTemplate = new RestTemplate();
			result = restTemplate.getForObject(url, String.class);
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		return result;
	}

	public static void main(String[] args) {
		Logger logger = Logger.getLogger(InvokeUtil.class);
		logger.debug(invoke("/vmware/domain/recenttasks/[hostName:host-28].[pageRows:200].[pages:1]/"));
	}
}
