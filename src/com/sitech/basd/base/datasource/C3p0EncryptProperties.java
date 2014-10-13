package com.sitech.basd.base.datasource;

import java.util.Properties;

import org.springframework.beans.factory.FactoryBean;

import com.sitech.utils.encrypt.BASE64Util;

/**
 * 
 * <p>
 * Title: C3p0EncryptProperties
 * </p>
 * <p>
 * Description: C3p0连接数据库密码加密
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
 * @createtime 2013-7-19 上午10:31:28
 * 
 */
public class C3p0EncryptProperties implements FactoryBean<Properties> {

	private Properties properties;

	public Properties getObject() throws Exception {
		return getProperties();
	}

	public Class<Properties> getObjectType() {
		return java.util.Properties.class;
	}

	public boolean isSingleton() {
		return true;
	}

	public Properties getProperties() {
		return properties;
	}

	public void setProperties(Properties inProperties) {
		this.properties = inProperties;
		String originalPassword = properties.getProperty("jdbc.password");
		String originalUser = properties.getProperty("jdbc.username");
		if (originalPassword != null) {
			String newPassword = passwordDecode(originalPassword);
			properties.put("password", newPassword);
			properties.put("user", originalUser);
		}
	}

	/**
	 * 
	 * @Title: passwordDecode
	 * @Description: 密码解析
	 * @param
	 * @return String
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-7-19 上午10:48:25
	 */
	private String passwordDecode(String originalPassword) {
		return base64DecodeString(originalPassword);
	}

	/**
	 * 
	 * @Title: base64EncodeString
	 * @Description: 密码使用base64转码
	 * @param
	 * @return String
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-7-19 上午10:43:30
	 */
	private String base64DecodeString(String originalString) {
		return BASE64Util.decode(originalString);
	}
}
