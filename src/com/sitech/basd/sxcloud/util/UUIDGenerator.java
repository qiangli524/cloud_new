package com.sitech.basd.sxcloud.util;

import java.util.UUID;

/**
 * java生成uuid
 */
public class UUIDGenerator {
	public static String getUUID() {
		return UUID.randomUUID().toString().replace("-", "");
	}

	public static void main(String[] arg) {
	}
}
