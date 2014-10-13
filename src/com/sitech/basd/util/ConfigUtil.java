package com.sitech.basd.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigUtil {

	public static String getPaths(String name) {
		Properties props = new Properties();
		InputStream in = null;
		String str = "";
		try {
			props.load(ConfigUtil.class.getResourceAsStream("/paths.properties"));
			str = (String) props.get(name);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return str;
	}
}
