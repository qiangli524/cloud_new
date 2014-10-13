package com.sitech.basd.yicloud.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.HttpHostConnectException;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

public class HttpClientUtil {

	/** post方式提交 */
	public static String post(String url, JSONObject json) {
		HttpClient client = new DefaultHttpClient();
		HttpPost post = new HttpPost(url);
		String response = "";
		try {
			post.setHeader("Accept", "application/json");
			post.setHeader("Content-Type", "application/json;charset=UTF-8");
			StringEntity s = new StringEntity(json.toString());
			post.setEntity(s);
			HttpResponse res = client.execute(post);
			HttpEntity entity = res.getEntity();
			response = EntityUtils.toString(entity);

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return response;
	}

	/** post方式提交JSONArray */
	public static String post(String url, JSONArray json) {
		HttpClient client = new DefaultHttpClient();
		HttpPost post = new HttpPost(url);
		String response = "";
		Map<String, String> map = new HashMap<String, String>();
		try {
			post.setHeader("Accept", "application/json");
			post.setHeader("Content-Type", "application/json;charset=UTF-8");
			StringEntity s = new StringEntity(json.toString());
			post.setEntity(s);
			HttpResponse res = client.execute(post);

			// response += "[responseCode:" +
			// res.getStatusLine().getStatusCode()
			// + "]";
			map.put("responseCode",
					String.valueOf(res.getStatusLine().getStatusCode()));
			HttpEntity entity = res.getEntity();
			// response += ".[responseEntity:" + EntityUtils.toString(entity)
			// + "]";
			map.put("responseEntity", EntityUtils.toString(entity));
			JSONObject responseJSON = JSONObject.fromObject(map);
			response = responseJSON.toString();
		} catch (HttpHostConnectException connEx) {
			map.put("responseCode", "Connect failed!");
			// response += "[responseCode:" + "Connect failed!" + "]";
			JSONObject responseJSON = JSONObject.fromObject(map);
			response = responseJSON.toString();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return response;
	}

	public static String post(String url) {
		HttpClient client = new DefaultHttpClient();
		HttpDelete post = new HttpDelete(url);
		String response = "";
		try {
			HttpResponse res = client.execute(post);
			HttpEntity entity = res.getEntity();
			response = EntityUtils.toString(entity);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return response;
	}

	/** 删除 */
	public static String del(String url) {
		HttpClient client = new DefaultHttpClient();
		HttpDelete delete = new HttpDelete(url);
		String response = "";
		try {
			HttpResponse res = client.execute(delete);
			HttpEntity entity = res.getEntity();
			response = EntityUtils.toString(entity);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return response;
	}

	/** get */
	public static String get(String url) {
		HttpClient client = new DefaultHttpClient();
		HttpGet get = new HttpGet(url);
		String response = "";
		try {
			HttpResponse res = client.execute(get);
			HttpEntity entity = res.getEntity();
			response = EntityUtils.toString(entity);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return response;
	}

	public static String filterURL(String url) {
		try {
			url = url.replace("[", URLEncoder.encode("[", "UTF-8"));
			url = url.replace("]", URLEncoder.encode("]", "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return url;
	}
	public static void main(String[] args) {
		// String url = "";
		// JSONObject json = new JSONObject();
		// json.put("deviceId", 11111);
		// json.put("ipAddr", "");
		// json.put("osManufacturer", "redhat");
		// json.put("physicalHostId", "172.21.1.105");
		// String result = post(url, json);
		// del("http://172.21.105.50:8080/WebService/ibnms/monitor/10-10-24:bomc6/stopHostCollect");
		String rs = "http://localhost:8080/cbms_portal/sitechUserLogin.action?username=guowei";
	}
}
