package com.sitech.basd.yicloud.util;

import org.apache.http.Header;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;

import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;

public class HttpRequestUtil {
	

	/**
	 * 发送json到服务器
	  
	* requestPostREJson(这里用一句话描述这个方法的作用)    
	  
	* @param   name   
	  
	* @param  @return    设定文件   
	  
	* @return String    DOM对象   
	  
	* @Exception 异常对象   
	  
	* @since  CodingExample　Ver(编码范例查看) 1.1
	 */
	public static String requestPostREJson(String url, String json) {
		int code = 0;
		try {
			HttpPost post = new HttpPost(url);
			post.setHeader("Accept", "application/json");
			post.setHeader("Content-Type","application/json;charset=UTF-8");
			StringEntity s = new StringEntity(json);
			post.setEntity(s);
			HttpClient client = new DefaultHttpClient();
			HttpResponse response = client.execute(post);
			code = response.getStatusLine().getStatusCode();
			if (code == 200) {
				String temp = EntityUtils.toString(response.getEntity());
				return temp;
			}else{
				LogHelper.error(url+"     "+"HttpCode:"+code);
			}
		} catch (Exception e) {
			LogHelper.error("HttpCode:"+code+"      "+e.getMessage());
		}
		return "";
	}

	/**
	 * 发送xml到服务器
	  
	* requestPostREXml(这里用一句话描述这个方法的作用)    
	  
	* @param   name   
	  
	* @param  @return    设定文件   
	  
	* @return String    DOM对象   
	  
	* @Exception 异常对象   
	  
	* @since  CodingExample　Ver(编码范例查看) 1.1
	 */
	public static String requestPostREXml(String url, String xml) {
		int code=0;
		try {
			HttpPost post = new HttpPost(url);
			Header header1 = new BasicHeader(HttpHeaders.ACCEPT,
					"application/xml");
			Header header2 = new BasicHeader(HttpHeaders.CONTENT_TYPE,
					"application/xml;charset=UTF-8");
			post.addHeader(header1);
			post.addHeader(header2);
			StringEntity s = new StringEntity(xml);
			post.setEntity(s);
			HttpClient client = new DefaultHttpClient();
			HttpResponse response = client.execute(post);
			code = response.getStatusLine().getStatusCode();
			if (code == 200) {
				String temp = EntityUtils.toString(response.getEntity());
				return temp;
			}
		} catch (Exception e) {
			LogHelper.error("HttpCode:" + code + "      " + e.getMessage());
		}
		return "";
	}
}
