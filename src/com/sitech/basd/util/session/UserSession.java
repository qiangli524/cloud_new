package com.sitech.basd.util.session;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * <p>
 * Title: UserSession
 * </p>
 * <p>
 * Description: 用户Session，一般用于Service层获取Session
 * </p>
 * <p>
 * Copyright: Copyright (c) 2013
 * </p>
 * <p>
 * Company: SI-TECH
 * </p>
 * 
 * @author huojla
 * @version 1.0
 * @createtime 2013-8-28 下午11:00:01
 * 
 */
public class UserSession {
	private static final Logger LOG = LoggerFactory.getLogger(UserSession.class);
	/** * 保存变量的ThreadLocal，保持在同一线程中同步数据. */
	private static final ThreadLocal<HttpSession> HTTP_SESSION = new ThreadLocal<HttpSession>();

	protected UserSession() {
	}

	/**
	 * 
	 * @Title: getHttpSession
	 * @Description: 获取当前Session对象
	 * @param
	 * @return HttpSession
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-8-28 下午11:03:49
	 */
	public static HttpSession getHttpSession() {
		HttpSession session = HTTP_SESSION.get();
		if (session != null) {
			// LOG.info("获取当前Session：" + session.getId());
		}
		return session;
	}

	/**
	 * ]
	 * 
	 * @Title: getHttpSessionAttribute
	 * @Description: 获取Session中属性，一般只能获取一次
	 * @param
	 * @return Object
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-8-28 下午11:05:05
	 */
	public static <T> T getHttpSessionAttribute(String key, Class<T> clazz) {
		HttpSession session = HTTP_SESSION.get();
		if (session != null) {
			return (T) session.getAttribute(key);
		}
		return null;
	}

	/**
	 * 
	 * @Title: setHttpSession
	 * @Description: 保存当前最新Session
	 * @param
	 * @return void
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-8-28 下午11:09:35
	 */
	public static void setHttpSession(HttpSession session) {
		HTTP_SESSION.set(session);
	}
}
