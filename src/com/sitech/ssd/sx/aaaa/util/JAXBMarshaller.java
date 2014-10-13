/**
 * JAXBMarshaller.java
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

import java.io.ByteArrayOutputStream;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import org.apache.log4j.Logger;

/**
 * ClassName:JAXBMarshaller Function:
 * 将java对象转化为xml字符串，CXF支持jaxb,但是转为对象后，取得的属性为空，现在手动处理
 * 
 * @author duangh
 * @since Ver 1.0
 * @Date 2014 三月 31 15:09:56
 */
public class JAXBMarshaller {
	private static final Logger logger = Logger.getLogger(JAXBMarshaller.class);

	public static <T> String java2xml(T t) {
		String xmlString = null;
		try {
			JAXBContext context = JAXBContext.newInstance(t.getClass());
			StringWriter stringWriter = new StringWriter();
			Marshaller marshaller = context.createMarshaller();
			marshaller.marshal(t, stringWriter);
			xmlString = stringWriter.toString();
		} catch (Exception e) {
			logger.error("convert java object to xml error,error:" + e.getMessage());
		}
		return xmlString;
	}
	

	/**
	 * xml头去掉standalone="yes"
	 * @author chenyu:下午3:33:18
	 * @param t
	 * @return
	 */
	public static <T> String java2xmlNoStandalone(T t){
		String xmlString = null;
		try {
			XMLOutputFactory xmlOutputFactory = XMLOutputFactory.newInstance();
			JAXBContext context = JAXBContext.newInstance(t.getClass());
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            XMLStreamWriter xmlStreamWriter = xmlOutputFactory.createXMLStreamWriter(baos, (String) marshaller.getProperty(Marshaller.JAXB_ENCODING));
            xmlStreamWriter.writeStartDocument((String) marshaller.getProperty(Marshaller.JAXB_ENCODING), "1.0");
			marshaller.marshal(t, xmlStreamWriter);
            xmlStreamWriter.writeEndDocument();
            xmlStreamWriter.close();
            xmlString = new String(baos.toByteArray(),"UTF-8");
		} catch (JAXBException e) {
			e.printStackTrace();
			logger.error("convert java object to xml error,error:" + e.getMessage());
		} catch (XMLStreamException e) {
			e.printStackTrace();
			logger.error("convert java object to xml error,error:" + e.getMessage());
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			logger.error("convert java object to xml error,error:" + e.getMessage());
		}
		return xmlString;
	}
}
