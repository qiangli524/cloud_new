package com.sitech.basd.sxcloud.util;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class EncryptUtil {
	/*
	 * 加密算法
	 */
	public static String encode(String str) {
		BASE64Encoder encode = new BASE64Encoder();
		return encode.encode(str.getBytes());
	}

	/*
	 * 解密算法
	 */
	public static String decode(String str) {
		BASE64Decoder decode = new BASE64Decoder();
		byte[] b = null;
		try {
			b = decode.decodeBuffer(str);
		} catch (Exception e) {
		}
		return (new String(b)).toString();
	}

	public static void main(String[] args) {
		EncryptUtil.encode("123456");
	}
}
