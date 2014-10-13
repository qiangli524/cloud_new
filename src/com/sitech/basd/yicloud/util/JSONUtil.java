package com.sitech.basd.yicloud.util;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSON;

import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;

import com.sitech.basd.sxcloud.util.Struts2Utils;
import com.sitech.utils.servlet.PrintWriterOut;

/**
 * 
 * @author liujhc
 */
public class JSONUtil {
	private static final ObjectMapper mapper = new ObjectMapper();

	static {
		mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
		mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
		mapper.getDeserializationConfig().set(
				DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	}

	/**
	 * 将java对象转换成json字符串
	 * 
	 * @param obj
	 * @return
	 */
	public static String toJSON(Object obj) {
		try {
			if (obj == null) {
				return null;
			} else {
				return mapper.writeValueAsString(obj);
			}
		} catch (Exception ex) {
			throw new RuntimeException("转换Json异常!", ex);
		}
	}

	/**
	 * 将json字符串数组转换成java的List对象，对象转换成java的Map对象
	 * 
	 * @param json
	 * @return
	 */
	public static Object fromJSON(String json) {
		return fromJSON(json, Object.class);
	}

	/**
	 * 将json字符串数组转换成指定类型的对象
	 * 
	 * @param json
	 * @param type
	 * @return
	 */
	public static <T> T fromJSON(String json, Class<T> type) {
		try {
			if (json == null) {
				return null;
			} else {
				return mapper.readValue(json, type);
			}
		} catch (Exception ex) {
			throw new RuntimeException("解析Json异常!", ex);
		}
	}

	/**
	 * 将json字符串数组转换成指定类型的对象,支持泛型</br>
	 * 
	 * <p>
	 * Map&lt;String,User&gt; result = JSONUtil.fromJSON(jsonStr, new
	 * TypeReference&lt;Map&lt;String,User&gt;&gt;(){ });
	 * </p>
	 * 
	 * @param json
	 * @param typeReference
	 * @return
	 */
	public static <T> T fromJSON(String json, JSONUtil.TypeReference<T> typeReference) {
		try {
			if (json == null) {
				return null;
			} else {
				return (T) mapper.readValue(json, typeReference);
			}
		} catch (Exception ex) {
			throw new RuntimeException("解析Json异常!", ex);
		}
	}

	public static class TypeReference<T extends Object> extends
			org.codehaus.jackson.type.TypeReference {

		public int compareTo(Object o) {
			// TODO Auto-generated method stub
			return 0;
		}

	}

	public static void printJSON(JSON jo) throws Exception {
		try {
			HttpServletResponse response = Struts2Utils.getResponse();
			response.setContentType("type/html;charset=UTF-8");
			// PrintWriter w = response.getWriter();
			// w.println(jo);
			// w.close();
			PrintWriterOut.printWirter(response, jo);
		} catch (Exception e) {
			throw new Exception(e);
		}
	}

	public static void main(String[] args) {
		Logger logger = Logger.getLogger(JSONUtil.class);
		String json = "[{'ID':0,'NAME':'si-tech'},{'ID:0,'NAME':'test'}]";
		logger.debug(fromJSON(json,
				new JSONUtil.TypeReference<LinkedHashMap<String, ArrayList<HashMap>>>() {
				}));
	}
}
