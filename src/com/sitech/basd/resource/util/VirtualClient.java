package com.sitech.basd.resource.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.poi.ss.formula.functions.T;

import com.sitech.utils.exception.HttpClientException;
import com.sitech.utils.http.HttpClientCustomUtil;
import com.sitech.utils.http.HttpClientUtil;
import com.sitech.utils.jackson.JacksonUtil;
import com.sitech.utils.properties.PropertiesUtil;
import com.sitech.utils.xml.XmlProperties;
import com.sitech.vo.util.UnitedConstant;

/**
 * 
 * <p>
 * Title: VirtualClient
 * </p>
 * <p>
 * Description: TODO(用一句话描述该文件做什么)
 * </p>
 * <p>
 * Copyright: Copyright (c) 2013
 * </p>
 * <p>
 * Company: SI-TECH
 * </p>
 * 
 * @author taoxue
 * @version 1.0
 * @createtime Jul 24, 2013 10:55:46 AM
 * 
 */
public class VirtualClient {
	private static final String PROPERTIES_PATH = "src/weburl.properties";

	private static String getBaseUrl() {
		String result = PropertiesUtil.getString("weburl", "restpath");
		return result;
	}

	/**
	 * 
	 * @Title: get
	 * @Description: 用于 查询
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Jul 24, 2013 10:57:58 AM
	 */
	public static String get(String url) throws HttpClientException {
		url = getBaseUrl() + url;
		String result = "";
		result = HttpClientUtil.get(url);
		return result;
	}

	/**
	 * 
	 * @Title: post
	 * @Description: 用于新建
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @throws HttpClientException
	 * @createtime Jul 24, 2013 11:07:41 AM
	 */
	public static <T> String post(String url, T clazz) throws HttpClientException {
		url = getBaseUrl() + url;
		String result = "";
		result = HttpClientUtil.post(url, clazz);
		return result;
	}

	/**
	 * 
	 * @Title: put
	 * @Description: 用于修改
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @throws HttpClientException
	 * @createtime Jul 24, 2013 11:08:47 AM
	 */
	public static <T> String put(String url, T clazz) throws HttpClientException {
		url = getBaseUrl() + url;
		String result = "";
		result = HttpClientUtil.put(url, clazz);
		return result;
	}

	/**
	 * 
	 * @Title: delete
	 * @Description: 用于删除
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Jul 24, 2013 11:09:56 AM
	 */
	public static String delete(String url) throws HttpClientException {
		url = getBaseUrl() + url;
		String result = "";
		result = HttpClientUtil.delete(url);
		return result;
	}

	/**
	 * 
	 * @Title: get
	 * @Description: Get方式提交，一般用于查询
	 * @param
	 * @return S
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-7-26 上午11:00:37
	 */
	public static <T> T get(String url, JacksonUtil.TypeReference<T> typeReference)
			throws HttpClientException {
		String baseUrl = getResourceCodeApiUrl("DELETE", null, url);
		if ("error".equals(baseUrl)) {
			throw new HttpClientException("在配置文件" + PROPERTIES_PATH + "获取资源池api连接失败,请联系管理员！(" + url
					+ ")");
		}
		url = baseUrl + url;
		T result = null;
		result = HttpClientCustomUtil.get(url, typeReference);
		return result;
	}

	/**
	 * 
	 * @Title: post
	 * @Description: Post方式提交，一般用于新建
	 * @param
	 * @return S
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-7-26 上午11:00:20
	 */
	public static <T> T post(String url, T clazz, JacksonUtil.TypeReference<T> typeReference)
			throws HttpClientException {
		String baseUrl = getResourceCodeApiUrl("POST", clazz, null);
		if ("error".equals(baseUrl)) {
			throw new HttpClientException("在配置文件" + PROPERTIES_PATH + "获取资源池api连接失败,请联系管理员！(" + url
					+ ")");
		}
		url = baseUrl + url;
		T result = null;
		result = HttpClientCustomUtil.post(url, clazz, typeReference);
		return result;
	}

	/**
	 * 
	 * @Title: put
	 * @Description: Put方式提交，一般用于修改
	 * @param
	 * @return S
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-7-26 上午11:01:17
	 */
	public static <T> T put(String url, T clazz, JacksonUtil.TypeReference<T> typeReference)
			throws HttpClientException {
		String baseUrl = getResourceCodeApiUrl("PUT", clazz, null);
		if ("error".equals(baseUrl)) {
			throw new HttpClientException("在配置文件" + PROPERTIES_PATH + "获取资源池api连接失败,请联系管理员！(" + url
					+ ")");
		}
		url = baseUrl + url;
		T result = null;
		result = HttpClientCustomUtil.put(url, clazz, typeReference);
		return result;
	}

	/**
	 * 
	 * @Title: delete
	 * @Description: 用于删除
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Jul 24, 2013 11:09:56 AM
	 */
	public static <T> T delete(String url, JacksonUtil.TypeReference<T> typeReference)
			throws HttpClientException {
		String baseUrl = getResourceCodeApiUrl("DELETE", null, url);
		if ("error".equals(baseUrl)) {
			throw new HttpClientException("在配置文件" + PROPERTIES_PATH + "获取资源池api连接失败,请联系管理员！(" + url
					+ ")");
		}
		url = baseUrl + url;
		T result = null;
		result = HttpClientCustomUtil.delete(url, typeReference);
		return result;
	}

	/**
	 * 
	 * @Title: getResourceCodeApiUrl
	 * @Description: 获取资源池Url
	 * @param
	 * @return String
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2014-8-24 上午11:26:14
	 */
	public static <T> String getResourceCodeApiUrl(String type, T clazz, String url) {
		String rpKey = null;
			XmlProperties vmwareConnectionXml = new XmlProperties("xml/vmware_in_control.xml");
			Map<String, String> map = vmwareConnectionXml.getMap();
			
			XmlProperties xenConnectionXml = new XmlProperties("xml/xen_in_control.xml");
			Map<String, String> xenMap = xenConnectionXml.getMap();
			
			map.putAll(xenMap);
			Set<String> keySet = map.keySet();
			Iterator<String> iterator = keySet.iterator();
			while (iterator.hasNext()) {
				String key = iterator.next();
				if ("POST".equals(type) || "PUT".equals(type)) {
					rpKey = getResourcePoolCodeByClass(clazz);
					if (rpKey!=null&&!"null".equals(rpKey)&&!"error".equals(rpKey)) {
						return PropertiesUtil.getString("weburl", rpKey);
					}
				} else if ("GET".equals(type) || "DELETE".equals(type)) {
					rpKey = getResourcePoolCodeByString("vmware", key, url);
					if (rpKey!=null&&!"null".equals(rpKey)&&!"error".equals(rpKey)) {
						String temp="";
						try{
							temp=PropertiesUtil.getString("weburl", rpKey);
						}catch (Exception e) {
							rpKey = getResourcePoolCodeByString("xen", key, url);
							if (rpKey!=null&&!"null".equals(rpKey)&&!"error".equals(rpKey)) {
								temp=PropertiesUtil.getString("weburl", rpKey);
							}else{
								/**
								 * 但同步集群的时候，是没有pooluuid的
								 */
								rpKey = "restpath";
								return PropertiesUtil.getString("weburl", rpKey);
							}
						}
						return temp;
					}else{
						/**
						 * 但同步集群的时候，是没有pooluuid的
						 */
						rpKey = "restpath";
						return PropertiesUtil.getString("weburl", rpKey);
					}
				}
			}
		return "error";
	}

	/**
	 * 
	 * @Title: getResourcePoolCodeByClass
	 * @Description: 获取资源池编码（一般用于Post/Put）
	 * @param
	 * @return String
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2014-8-22 下午4:29:02
	 */
	private static <T> String getResourcePoolCodeByClass(T clazz) {
		String connectCode = "error";
		Class<?> clazzclass = clazz.getClass();
		Field[] fields = clazzclass.getDeclaredFields();
		for (Field field : fields) {
			String fieldName = field.getName();
			if ("connectCode".equals(fieldName)) {
				String str = fieldName.charAt(0) + "";
				str = str.toUpperCase();
				String methodName = "get" + str + fieldName.substring(1);
				try {
					Object value = clazzclass.getMethod(methodName).invoke(clazz);
					connectCode = value + "";
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			}
		}
		return connectCode;
	}

	/**
	 * 获取虚拟化类型
	 * @Title: getFieldValueByName
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param
	 * @return Object
	 * @throws
	 * @author shijc
	 * @version 1.0
	 * @createtime 2014-9-16 下午1:50:14
	 */
	private static <T> String getVTypeValue(T clazz) {  
	      try {    
	    	  String fieldName="vType";
	          String firstLetter = fieldName.substring(0, 1).toUpperCase();    
	          String getter = "get" + firstLetter + fieldName.substring(1);    
	          Method method =clazz.getClass().getMethod(getter, new Class[] {});    
	          Object value = method.invoke(clazz);    
	          return value.toString();    
	      } catch (Exception e) {    
	          return null;    
	      }    
	  }  

	
	/**
	 * 
	 * @Title: getResourcePoolCodeByString
	 * @Description: 获取资源池编码（一般用于get/delete）
	 * @param
	 * @return String
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2014-8-22 下午4:29:02
	 */
	private static <T> String getResourcePoolCodeByString(String vtype, String key, String url) {
		String connectCode = "error";
		final String CODE_PREFIX = key;
		if (url.indexOf(CODE_PREFIX) != -1) {
			int index = url.indexOf(CODE_PREFIX);
			if ("vmware".equals(vtype)) {
				connectCode = url.substring(index, index + 10);
			} else if ("xen".equals(vtype)) {
				connectCode = url.substring(index, index + 36);
			}
		}
		return connectCode;
	}
}
