package com.sitech.console.util;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * <p>
 * Title: OsIpaddressUtil
 * </p>
 * <p>
 * Description: 获取操作系统IP
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
 * @createtime 2014-7-30 下午1:44:53
 * 
 */
public class OsIpaddressUtil {
	private static final Logger LOG = LoggerFactory.getLogger(OsIpaddressUtil.class);

	/**
	 * 
	 * @Title: getLocalIps
	 * @Description: 获取本机IP地址
	 * @param
	 * @return List<String>
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @throws Exception
	 * @createtime 2014-7-30 下午1:45:03
	 */
	public static List<String> getLocalIps() throws Exception {
		List<String> ipList = new ArrayList<String>();
		try {
			Enumeration<NetworkInterface> nis = NetworkInterface.getNetworkInterfaces();
			while (nis.hasMoreElements()) {
				NetworkInterface ni = nis.nextElement();
				Enumeration<InetAddress> ips = ni.getInetAddresses();
				while (ips.hasMoreElements()) {
					InetAddress ip = ips.nextElement();
					if (ip != null && ip instanceof Inet4Address) {
						ipList.add(ip.getHostAddress());
					}
				}
			}
		} catch (SocketException e) {
			LOG.error("获取本机IP地址异常！" + e.getMessage(), e);
			throw new Exception("获取本机IP地址异常！" + e.getMessage(), e);
		}
		return ipList;
	}
}
