package com.sitech.shop.webservice.orderClient;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sitech.common.CodeFormatUtil;
import com.sitech.trade4.bean.MalltExtPrdctInstcBean;
import com.sitech.utils.jackson.JacksonUtil;
import com.sitech.utils.properties.PropertiesUtil;

import net.sf.json.JSONObject;

public class ClientUtil {
	
	static Logger logger = LoggerFactory.getLogger(ClientUtil.class);

    /**
     * 向商城发送订购关系
     * @param orderVO 订单关系对象
     * @param methodMark 请求的方法名称
     * @return 是否调用成功
     * @throws Exception 
     */
	public static boolean sendOrderRelationToShop(MalltExtPrdctInstcBean orderVO, String methodMark) throws Exception {
		logger.info("向商城发送竣工通知单_方法执行_Start");
		String restResult;
		String code = "";
		JSONObject busiParam = new JSONObject();
		busiParam.put("busiData", JSONObject.fromObject(orderVO));
		String method_mark = PropertiesUtil.getValue("properties/shop_client_info.properties", methodMark);
		String app_key = PropertiesUtil.getValue("properties/shop_client_info.properties", "app_key");
		String url = PropertiesUtil.getValue("properties/shop_client_info.properties", "url_01");
		String ip = PropertiesUtil.getValue("properties/shop_client_info.properties", "ip");
		String channel_id = PropertiesUtil.getValue("properties/shop_client_info.properties", "channel_id");
        logger.debug("发送商城调用参数：methodMark"+methodMark+"app_key"+app_key+"url:"+url+"ip"+ip+"channel_id:"+channel_id);
		restResult = connection_post(method_mark, busiParam.toString(), ip, url, app_key, channel_id);
		logger.debug("商城返回信息："+methodMark+restResult);
		code = (String) JSONObject.fromObject(restResult).getJSONObject("sysResp").get("code");
		logger.info("向商城发送竣工通知单_方法执行_End");
		return code.equals("000000");
	}

	/**
	 * 具体发给商城的方法
	 * 
	 * @param methodMark 请求的方法
	 * @param busiparam 数据字符串
	 * @param ip    服务地址
	 * @param url   服务URL
	 * @param key  验证密匙
	 * @param channel_id 系统ID
	 * @return 请求分返回值
	 * @throws Exception
	 */
	public static String connection_post(String methodMark, String busiparam, String ip, String url, String key, String channel_id) throws Exception {
		String restResult = "";
		String sysparam ;
		HttpURLConnection connection = null;
		URL postUrl;
		ByteArrayOutputStream bout = null;

		try {
			// 入参加密
			sysparam = createSysparam(methodMark, channel_id,key);
			sysparam = CodeFormatUtil.getEncrypt2(sysparam);
			busiparam = CodeFormatUtil.getEncrypt3(busiparam, key);

			// 请求url地址：
			url = ip + url;

			StringBuilder content = new StringBuilder();
			content.append("sysParam=").append(sysparam).append("&busiParam=").append(busiparam);

			// Post请求的url，与get不同的是不需要带参数
			postUrl = new URL(url);
			connection = (HttpURLConnection) postUrl.openConnection();

			bout = new ByteArrayOutputStream();
			bout.write(content.toString().getBytes("utf-8"));
			byte[] _b = bout.toByteArray();
			connection.setRequestProperty("Content-Length", String.valueOf(_b.length));
			connection.setRequestProperty("Content-Type", "text/plain;charset=utf-8");// 这一句也关键
			connection.setRequestMethod("POST");
			connection.setDoInput(true);
			connection.setDoOutput(true);
			OutputStream out = connection.getOutputStream();
			out.write(_b);
			out.flush();
			int code = connection.getResponseCode();
			out.close();
			if (200 == code) {
				InputStream in = connection.getInputStream();
				StringBuilder sb = new StringBuilder();
				byte[] b = new byte[4096];
				for (int n; (n = in.read(b)) != -1;) {
					sb.append(new String(b, 0, n));
				}
				restResult = sb.toString();
				restResult = CodeFormatUtil.getDecrypt3(restResult, key);
			} else {
				restResult = "{\"sysResp\":{\"code\":\"000500\",\"msg\":\"rest服务报" + code + "错误\",\"series\":\"\"},\"busiResp\":{}}";
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (bout != null) {
				bout.close();
			}

			// 断开连接
			if (connection != null) {
				connection.disconnect();
			}
		}
		return restResult;
	}

	private static String createSysparam(String methodMark, String channel_id,String key) {
		sysParam obj = new sysParam();
		obj.setMethod_mark(methodMark);
		obj.setV("4");
		obj.setApp_key(key);
		obj.setTimestamp(String.valueOf(System.currentTimeMillis()));
		obj.setChannel_id(channel_id);
		return JacksonUtil.toJson(obj);
	}
	
}
