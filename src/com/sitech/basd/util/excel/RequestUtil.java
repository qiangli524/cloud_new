package com.sitech.basd.util.excel;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import util.DomainUtil;

public class RequestUtil {
	/** Protect singleton */
	private RequestUtil() {
	}

	public static Map getRequestParameterMap(HttpServletRequest request) {
		return getRequestParameterMap(request, "");

	}

	public static Map getRequestParameterMap(HttpServletRequest request, String id) {
		int lenghtOfId = (id == null) ? 0 : id.length();

		Map parameters = new HashMap(request.getParameterMap().size());
		for (Enumeration keys = request.getParameterNames(); keys.hasMoreElements();) {
			String key = (String) keys.nextElement();
			String[] values = request.getParameterValues(key);
			// Object value = ((values == null) ? null : (values.length == 1 ?
			// (Object) values[0] : (Object) values));
			Object value = ((values == null) ? null : (Object) values[0]);

			if (lenghtOfId > 0) {
				if (key.endsWith(id)) {
					parameters.put(key.substring(0, key.length() - lenghtOfId), value);
				}
			} else {
				parameters.put(key, value);
			}

		}
		String domain = DomainUtil.getSessionStringDomain();
		// 2014年9月1日,如果查询条件中没有域信息,,则将域信息放入查询条件中
		if (!parameters.containsKey("domain") && null != domain
				&& !"domain.type.all".equals(domain)) {
			parameters.put("domain", domain);
		}
		return parameters;
	}

	public static String writeRequestString(Map map, int page, String sqlKey) {
		StringBuffer str = new StringBuffer();
		String operate = (String) map.get("operate");
		// if(operate==null || operate.startsWith("list"))
		// {
		Iterator iterator = map.keySet().iterator();
		while (iterator.hasNext()) {
			Object key = iterator.next();
			// ���ڲ�ѯҳ�����ȥ���ظ��ύ�����Ʊ�־
			if (key.toString().endsWith(".TOKEN"))
				continue;

			str.append("<input type=\"hidden\" name=\"");
			str.append(key);
			str.append("\"  value=\"");
			str.append(map.get(key));
			str.append("\">  ");
		}
		// }

		if (!map.containsKey("curpage")) {
			str.append("<input type=\"hidden\" name=\"curpage\" value=\"");
			str.append(page);
			str.append("\">");
			str.append("<input type=\"hidden\" name=\"key\" value=\"");
			str.append(sqlKey);
			str.append("\">");
		}
		return str.toString();
	}
}