package com.sitech.cas.login;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

@Component("sysSingleSignOnUtil")
public class SysSingleSignOnUtil {
	private static final long serialVersionUID = -2096730223578871319L;
	private static final Logger log = Logger.getLogger(SysSingleSignOnUtil.class);
	final String server = "http://localhost:8080/cas_sso/login";
	private String username = "admin";
	private String password = "admin1234!";
	/*
	 * 隐藏域字段
	 */
	private static final String LT = "lt";
	private static final String EXECUTION = "execution";

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * 
	 * @Title: initHttpCookie
	 * @Description: 实例HttpCookie
	 * @param
	 * @return javax.servlet.http.Cookie
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2014-5-21 下午1:59:19
	 */
	public javax.servlet.http.Cookie initHttpCookie() throws IOException {
		Cookie cookie = getTicketGrantingTicket(server, username, password);
		javax.servlet.http.Cookie httpCookie = null;
		if (cookie != null) {
			httpCookie = convertToServletCookie(cookie);
			log.info("单点服务" + server + "登录成功！");
		} else {
			log.info("单点服务" + server + "登录失败！");
		}
		return httpCookie;
	}

	private Cookie getTicketGrantingTicket(final String server, final String username,
			final String password) throws IOException {
		DefaultHttpClient client = new DefaultHttpClient();
		HttpPost post = new HttpPost(server);

		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("username", username));
		nvps.add(new BasicNameValuePair("password", password));
		Map<String, String> hiddenKeyMap = doCasLoginRequest(client, server);
		nvps.add(new BasicNameValuePair(LT, hiddenKeyMap.get(LT)));
		nvps.add(new BasicNameValuePair(EXECUTION, hiddenKeyMap.get(EXECUTION)));
		nvps.add(new BasicNameValuePair("_eventId", "submit"));
		post.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
		try {
			HttpResponse response = client.execute(post);
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				Cookie cookie = getCookieValue(client, "CASTGC");
				entity.consumeContent();
				return cookie;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private Cookie getCookieValue(DefaultHttpClient httpclient, String name) {
		List<Cookie> cookies = httpclient.getCookieStore().getCookies();
		if (cookies.isEmpty()) {
			return null;
		} else {
			for (int i = 0; i < cookies.size(); i++) {
				Cookie cookie = cookies.get(i);
				if (cookie.getName().equalsIgnoreCase(name)) {
					return cookie;
				}
			}
		}
		return null;
	}

	/**
	 * 
	 * @Title: doCasLoginRequest
	 * @Description: 获取登录票据ID
	 * @param
	 * @return Map<String,String>
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2014-5-21 上午11:29:08
	 */
	private Map<String, String> doCasLoginRequest(DefaultHttpClient httpclient, String url)
			throws IOException {
		Map<String, String> result = new HashMap<String, String>();
		HttpGet httpget = new HttpGet(url);
		HttpResponse response = httpclient.execute(httpget);
		HttpEntity entity = response.getEntity();
		BufferedReader rd = new BufferedReader(new InputStreamReader(entity.getContent(), "UTF-8"));
		String tempLine = rd.readLine();
		String ltString = "<input type=\"hidden\" name=\"" + LT + "\" value=\"";
		String executionString = "<input type=\"hidden\" name=\"" + EXECUTION + "\" value=\"";
		while (tempLine != null) {
			/*
			 * 获取LT参数
			 */
			int ltIndex = tempLine.indexOf(ltString);
			if (ltIndex != -1) {
				String ltResult = "";
				String s1 = tempLine.substring(ltIndex + ltString.length());
				int index1 = s1.indexOf("\"");
				if (index1 != -1)
					ltResult = s1.substring(0, index1);
				result.put(LT, ltResult);
			}
			/**
			 * 获取execution参数
			 */
			int executionIndex = tempLine.indexOf(executionString);
			if (executionIndex != -1) {
				String executionResult = "";
				String s1 = tempLine.substring(executionIndex + executionString.length());
				int index1 = s1.indexOf("\"");
				if (index1 != -1)
					executionResult = s1.substring(0, index1);
				result.put(EXECUTION, executionResult);
			}
			tempLine = rd.readLine();
		}
		if (entity != null) {
			entity.consumeContent();
		}
		return result;
	}

	/**
	 * 
	 * @Title: convertToServletCookie
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param
	 * @return javax.servlet.http.Cookie
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2014-5-21 上午10:17:46
	 */
	private javax.servlet.http.Cookie convertToServletCookie(Cookie cookie) {
		javax.servlet.http.Cookie retCookie = new javax.servlet.http.Cookie(cookie.getName(),
				cookie.getValue());
		retCookie.setComment(cookie.getComment());
		retCookie.setDomain(cookie.getDomain());
		// retCookie.setHttpOnly(false);
		retCookie.setSecure(false);
		retCookie.setPath(cookie.getPath());
		retCookie.setVersion(cookie.getVersion());
		/*
		 * 
		 */
		retCookie.setMaxAge((int) ((cookie.getExpiryDate() != null ? (cookie.getExpiryDate()
				.getTime() - System.currentTimeMillis() / 1000) : -1)));
		return retCookie;
	}
}