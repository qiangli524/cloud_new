package com.sitech.basd.yicloud.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;

public class CfgUtil {
	private final static Logger logger = Logger.getLogger(CfgUtil.class.getName());
	private final static String resourceFile = URLDecoder.decode(CfgUtil.class.getResource("/")
			.getFile()
			+ "weburl.properties");
	private final static Map map = new HashMap();
	private static Properties prop = new Properties();
	private final static CfgUtil monitorAgentCfg = new CfgUtil();

	/**
	 * 
	 * <p>
	 * Title: CfgUtil构造函数
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 */
	private CfgUtil() {
		this(resourceFile);
	}

	/**
	 * 
	 * <p>
	 * Title: CfgUtil构造函数，为prop和map附初始值
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param resourceFile
	 */
	private CfgUtil(String resourceFile) {
		File file = new File(resourceFile);
		if (file.exists()) {// 判断文件是否存在
			try {
				prop.load(new FileInputStream(file.getCanonicalPath()));
				Enumeration enumeration = prop.propertyNames();
				while (enumeration.hasMoreElements()) {
					String key = (String) enumeration.nextElement();
					map.put(key, prop.get(key));
				}
			} catch (FileNotFoundException e) {
				logger.error("FileNotFoundException:" + e.getMessage());
			} catch (IOException e) {
				logger.error("IOException:" + e.getMessage());
			}
		} else {
			logger.error("rest.properties,文件不存在！");
		}
	}

	public static CfgUtil getInstance() {
		return monitorAgentCfg;
	}

	/**
	 * 
	 * @Title: getString
	 * @Description: 通过key值获取配置文件中对应的值
	 * @param
	 * @return String
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime Aug 17, 2011 9:46:56 AM
	 */
	public static String getString(String key) {
		return getString(key, null);
	}

	/**
	 * 
	 * @Title: getString
	 * @Description: 通过key值获取配置文件中对应的值,若不存在，则给予默认值
	 * @param
	 * @return String
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime Aug 17, 2011 9:47:26 AM
	 */
	public static String getString(String key, String defaultValue) {
		String value = (String) map.get(key);
		if (value != null) {
			return value;
		} else {
			return defaultValue;
		}
	}

	/**
	 * 
	 * @Title: getInt
	 * @Description: 通过key值获取配置文件中对应的整数值
	 * @param
	 * @return int
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime Aug 17, 2011 9:48:21 AM
	 */
	public static int getInt(String key) {
		return getInt(key, 0);
	}

	/**
	 * 
	 * @Title: getInt
	 * @Description: 通过key值获取配置文件中对应的整数值,若不存在，则给予默认值
	 * @param
	 * @return int
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime Aug 17, 2011 9:52:18 AM
	 */
	public static int getInt(String key, int defaultValue) {
		String value = (String) map.get(key);
		if (value != null) {
			return Integer.parseInt(value);
		} else {
			return defaultValue;
		}
	}

	/**
	 * 
	 * @Title: setPropValue
	 * @Description: 在配置文件中写入行
	 * @param
	 * @return void
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime Aug 17, 2011 9:54:39 AM
	 */
	public static void setPropValue(String key, String value) {
		prop.setProperty(key, value);
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(resourceFile);
			prop.store(fos, "Monitor Agent Configration");
			fos.close();
		} catch (FileNotFoundException e) {
			logger.error(e.getMessage());
		} catch (IOException ie) {
			logger.error(ie.getMessage());
		}
		map.put(key, value);
	}

	public static void main(String[] args) {
		logger.debug(CfgUtil.class.getResource("/").getFile());
		logger.debug(getString("rest.ip"));
	}
}
