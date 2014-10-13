package com.sitech.basd.resource.util;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sitech.basd.sxcloud.config.Constant;
import com.sitech.basd.util.session.UserSession;
import com.sitech.utils.xml.XmlProperties;

/**
 * 
 * <p>
 * Title: ReourceInDomainUtil
 * </p>
 * <p>
 * Description: TODO(用一句话描述该文件做什么)
 * </p>
 * <p>
 * Copyright: Copyright (c) 2014
 * </p>
 * <p>
 * Company: SI-TECH
 * </p>
 * 
 * @author huojla
 * @version 1.0
 * @createtime 2014-8-5 上午10:33:36
 * 
 */
@Service("reourceInDomainUtil")
public class ReourceInDomainUtil {
	@Autowired
	private XmlProperties vmwareConnectionXml;
	@Autowired
	private XmlProperties xenConnectionXml;

	/**
	 * 
	 * @Title: initResourceDomain
	 * @Description: 实例资源所属于域
	 * @param
	 * @return void
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2014-8-5 上午10:34:57
	 */
	public String initResourceDomainBySession() {
		return UserSession.getHttpSession().getAttribute(Constant.USER_DOMAIN) + "";
	}

	/**
	 * 
	 * @Title: initResourceDomainBySessionAndConfig
	 * @Description: 通过Session及配置实例资源所属域
	 * @param
	 * @return String
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2014-8-5 上午10:36:47
	 */
	public String initResourceDomainBySessionAndConfig(String connectId) {
		String domain = UserSession.getHttpSession() == null ? null : UserSession.getHttpSession()
				.getAttribute(Constant.USER_DOMAIN) + "";
		if (domain == null) {// 从配置文件获取
			domain = getXmlMapValue(connectId);
		}
		return domain;
	}

	/**
	 * 
	 * @Title: getXmlMapValue
	 * @Description: 处理配置文件中Value
	 * @param
	 * @return String
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2014-8-5 上午10:49:06
	 */
	private String getXmlMapValue(String connectId) {
		/*
		 * VMware处理
		 */
		Map<String, String> vmwareConnectMap = vmwareConnectionXml.getMap();
		Set<String> vmwareConnectKeySet = vmwareConnectMap.keySet();
		Iterator<String> vmwareConnectIterator = vmwareConnectKeySet.iterator();
		while (vmwareConnectIterator.hasNext()) {
			String vmwareConnectId = vmwareConnectIterator.next();
			if (vmwareConnectId.equals(connectId)) {
				String[] keys = vmwareConnectMap.get(vmwareConnectId).split("_");
				if (keys.length == 2) {
					return vmwareConnectMap.get(vmwareConnectId).split("_")[0];
				}
			}
		}
		/*
		 * Xen处理
		 */
		Map<String, String> xenConnectMap = xenConnectionXml.getMap();
		Set<String> xenConnectKeySet = xenConnectMap.keySet();
		Iterator<String> xenConnectIterator = xenConnectKeySet.iterator();
		while (xenConnectIterator.hasNext()) {
			String xenConnectId = xenConnectIterator.next();
			if (xenConnectId.equals(connectId)) {
				String[] keys = xenConnectMap.get(xenConnectId).split("_");
				if (keys.length == 2) {
					return xenConnectMap.get(xenConnectId).split("_")[0];
				}
			}
		}
		return null;
	}
}
