package com.sitech.basd.yicloud.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

public class ParamParser {
	private static final String SPLIT_STRING = "\\]\\.\\[";
	public static final String PLACEHOLDER = "##";

	public static Map makeup(final String params) {
		Map<String, String> result = new HashMap<String, String>();

		if (StringUtils.hasLength(params) && params.length() > 2) {
			String tmp = params.substring(1, params.length() - 1);

			String[] ps = tmp.split(SPLIT_STRING);

			if (ps != null && ps.length > 0) {

				for (int i = 0; i < ps.length; i++) {
					String kv = ps[i];

					if (StringUtils.hasLength(kv)) {
						String[] kvs = kv.split(":");

						if (kvs != null && kvs.length == 2) {
							result.put(kvs[0], kvs[1]);
						}

					}
				}
			}
		}

		return result;
	}

	public static Map parse(final String params) {
		Map<String, String> result = new HashMap<String, String>();

		if (StringUtils.hasLength(params) && params.length() > 2) {
			String tmp = params.substring(1, params.length() - 1);

			String[] ps = tmp.split(SPLIT_STRING);

			if (ps != null && ps.length > 0) {

				for (int i = 0; i < ps.length; i++) {
					String kv = ps[i];

					if (StringUtils.hasLength(kv)) {
						String[] kvs = kv.split(":");

						if (kvs != null && kvs.length == 2) {
							result.put(PLACEHOLDER + kvs[0] + PLACEHOLDER,
									kvs[1]);
						}

					}
				}
			}
		}

		return result;
	}

	public static String replace(final String original, Map params) {
		String result = new String(original);

		if (StringUtils.hasLength(result) && !CollectionUtils.isEmpty(params)) {
			Set<String> keys = params.keySet();

			Iterator<String> it = keys.iterator();
			while (it.hasNext()) {
				String key = (String) it.next();
				result = result.replaceAll(key, params.get(key).toString());
			}

		}

		return result;
	}
}
