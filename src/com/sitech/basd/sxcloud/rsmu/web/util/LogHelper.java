package com.sitech.basd.sxcloud.rsmu.web.util;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import sun.reflect.Reflection;

import com.sitech.utils.properties.PropertiesUtil;

public class LogHelper {
	private static Logger log = Logger.getLogger("");

	public LogHelper() {
	}

	public static void debug(String message) {
		try {
			Class className = Reflection.getCallerClass(2);
			Throwable t = new Throwable();
			log.debug(getInvokeName(t, className.getName()) + ":"
					+ new String(message.getBytes("GB2312")));
		} catch (Exception e) {

		}
	}

	public static void info(String message) {
		try {
			Class className = Reflection.getCallerClass(2);
			Throwable t = new Throwable();
			log.info(getInvokeName(t, className.getName()) + ":"
					+ new String(message.getBytes("GB2312")));
		} catch (Exception e) {

		}
	}

	public static void error(String message) {
		try {
			Class className = Reflection.getCallerClass(2);
			Throwable t = new Throwable();
			log.error(getInvokeName(t, className.getName()) + ":"
					+ new String(message.getBytes("GB2312")));
		} catch (Exception e) {
		}
	}

	public static void fatal(String message) {
		try {
			Class className = Reflection.getCallerClass(2);
			Throwable t = new Throwable();
			log.fatal(getInvokeName(t, className.getName()) + ":"
					+ new String(message.getBytes("GB2312")));
		} catch (Exception e) {
		}
	}

	public static void warn(String message) {
		try {
			Class className = Reflection.getCallerClass(2);
			Throwable t = new Throwable();
			log.warn(getInvokeName(t, className.getName()) + ":"
					+ new String(message.getBytes("GB2312")));
		} catch (Exception e) {
		}
	}

	private static String getInvokeName(Throwable throwable, String className) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		throwable.printStackTrace(pw);
		StringBuffer out = new StringBuffer(sw.toString());
		return out.substring(out.indexOf(className), out.indexOf("\n", out.indexOf(className)));
	}

	static {
		Properties prop = new Properties();
		try {
			prop = PropertiesUtil.getProperties("log4j.properties");
			PropertyConfigurator.configure(prop);
		} catch (Exception e) {

		}
	}

	public static void main(String[] arg) {

		LogHelper.info("11111");
		LogHelper.debug("11111");
		LogHelper.warn("11111");
		LogHelper.fatal("11111");
		LogHelper.error("11111");
	}
}
