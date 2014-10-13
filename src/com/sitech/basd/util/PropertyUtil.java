package com.sitech.basd.util;

import java.util.ResourceBundle;

/**
 * 
 * <p>
 * Title: PropertiesUtil
 * </p>
 * <p>
 * Description: Properties文件工具类
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
 * @createtime 2013-4-21 下午2:57:38
 * 
 */
public class PropertyUtil {
	private ResourceBundle basicConfig;

	public PropertyUtil(String filePath) {
		basicConfig = ResourceBundle.getBundle(filePath);
	}

	/**
	 * 
	 * @Title: getString
	 * @Description: 读取配置文件
	 * @param
	 * @return String
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-3-23 下午4:51:32
	 */
	public String getString(String key) {
		return basicConfig.getString(key);
	}
}
