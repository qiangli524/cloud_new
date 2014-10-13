/**
 * JAXBUnmarshaller.java
 * com.sitech.ssd.sx.aaaa.util
 *
 * Function： TODO 
 *
 *   ver     date      		author
 * ──────────────────────────────────
 *   		 2014 三月 31 		duangh
 *
 * Copyright (c) 2014, TNT All Rights Reserved.
 */

package com.sitech.ssd.sx.aaaa.util;

import java.io.StringReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apache.log4j.Logger;

/**
 * ClassName:JAXBUnmarshaller Function: JAXB将xml转为java对象，利用CXF自动处理报错，手动处理
 * 
 * @author duangh
 * @since Ver 1.0
 * @Date 2014 三月 31 15:12:46
 */
public class JAXBUnmarshaller {
	private static final Logger logger = Logger.getLogger(JAXBUnmarshaller.class);

	public static <T> T xml2java(Class<T> clazz, String xml) {
		T t = null;
		try {
			JAXBContext context = JAXBContext.newInstance(clazz);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			t = (T) unmarshaller.unmarshal(new StringReader(xml));
		} catch (JAXBException e) {
			logger.error("convert xml to java object error,error:" + e.getMessage());
		}
		return t;
	}
}
