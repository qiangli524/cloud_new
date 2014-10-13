package com.sitech.basd.yicloud.util;

import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 
 * <p>
 * Title: JSONUtil
 * </p>
 * <p>
 * Description: JSON转换工具类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2012
 * </p>
 * <p>
 * Company: SI-TECH
 * </p>
 * 
 * @author huojla
 * @version 1.0
 * @createtime Jan 4, 2012 9:27:19 AM
 * 
 */
public class JsonUtils {
	/**
	 * 
	 * @Title: jsonToList
	 * @Description: 将List<MorphDynaBean>转换成为List<c>(自定义对象List)
	 * @param
	 * @return List<?>
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime Jan 4, 2012 9:27:46 AM
	 */
	public static List<?> jsonToList(List<?> list, Class c) {
		return JSONArray.toList(JSONArray.fromObject(list), c);
	}

	/**
	 * 
	 * @Title: jsonToList
	 * @Description: 将JSONString转换成为List<c>(自定义对象List)
	 * @param
	 * @return List<?>
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime Jan 4, 2012 9:27:46 AM
	 */
	public static List<?> jsonToList(String entity, Class c) {
		return JSONArray.toList(JSONArray.fromObject(entity), c);
	}

	/**
	 * 
	 * @Title: jsonToBean
	 * @Description: 将JSON字符串转换为自定义对象
	 * @param
	 * @return Object
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime Jan 4, 2012 9:35:39 AM
	 */
	public static Object jsonToBean(String entity, Class c) {
		return JSONObject.toBean(JSONObject.fromObject(entity), c);
	}
}
