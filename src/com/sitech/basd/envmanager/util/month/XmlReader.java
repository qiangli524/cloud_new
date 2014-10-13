package com.sitech.basd.envmanager.util.month;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class XmlReader {
	private Logger logger = LoggerFactory.getLogger(getClass());
	public HashMap readXML(String fileName) {
		File inputXml = new File(fileName);
		SAXReader saxReader = new SAXReader();
		HashMap smap = null;
		try {
			Document document = saxReader.read(inputXml);
			Element employees = document.getRootElement();
			smap = new HashMap();
			ArrayList clist = null;
			for (Iterator i = employees.elementIterator(); i.hasNext();) {
				clist = new ArrayList();
				Element employee = (Element) i.next();
				String skey = employee.attributeValue("name");
				if (skey == null || skey.isEmpty())
					break;
				for (Iterator j = employee.elementIterator(); j.hasNext();) {
					Element node = (Element) j.next();
					if (node != null)
						clist.add(node);
				}
				smap.put(skey, clist);

			}
		} catch (DocumentException e) {
			logger.debug(e.getMessage());
		}
		return smap;
	}
}
