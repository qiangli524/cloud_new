package com.sitech.basd.yicloud.util.zhejiang;

import java.util.HashMap;

import org.apache.log4j.Logger;

import com.sitech.basd.sxcloud.cloud.util.DES3;

public class CollIBMWithCMD {
	private HashMap params = null;

	public CollIBMWithCMD(HashMap params) {
		this.params = params;
	}

	private Logger logger = Logger.getLogger(CollIBMWithCMD.class);

	String PRE_UNITID = "10-10-20"; // aix主机的标识

	RomoteController tt = null;

	private String host_name = "";

	public boolean init(HashMap params) {
		boolean isConnect = false;
		String hostip = (String) params.get("IP_ADDR");
		String user = (String) params.get("USER_NAME");
		String passwd = (String) params.get("PASSWORD");

		String protol = (String) params.get("PROTOCOL");
		String name = (String) params.get("HOST_NAME");
		this.host_name = Formater.neatenunitid(name);
		String port = (String) params.get("PORT");
		int portInt = 22;
		this.host_name = Formater.neatenunitid(name);
		if ("".equals(port) || port == null) {
			portInt = 22;
		} else {
			portInt = Integer.valueOf(port);
		}
		tt = new SSHThread(hostip, portInt, user, passwd);

		try {
			tt.initial();
			isConnect = tt.isConnected();
			// isConnect = true;
		} catch (Exception e) {
			isConnect = false;
			e.printStackTrace();
		}
		logger.info("###############" + tt);
		return isConnect;
	}

	/**
	 * 
	 * @Title: stop
	 * @Description: 测试
	 * @param
	 * @return void
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime Aug 19, 2012 3:53:33 PM
	 */
	public void stop() {
		tt.doCommand("service tomcat stop");
		logger.debug("tomcat停止成功！");
		try {
			Thread.sleep(3 * 1000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		tt.close();
	}

	public void start() {
		tt.doCommand("service tomcat start");
		logger.debug("tomcat启动成功！");
		try {
			Thread.sleep(3 * 1000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		tt.close();
	}

	public static void main(String args[]) {
		// try {
		// Thread.sleep(40 * 1000);
		// } catch (InterruptedException e2) {
		// // TODO Auto-generated catch block
		// e2.printStackTrace();
		// }
		HashMap params = new HashMap();
		params.put("EQ_ID", "test33333333");
		params.put("IP_ADDR", "172.21.1.108");
		params.put("USER_NAME", "root");
		params.put("PASSWORD", DES3.encrypt("111111"));
		params.put("PROTOCOL", "ssh");
		params.put("PORT", "22");
		params.put("HOST_NAME", "cloud2");
		params.put("HMC_SN", "");

		CollIBMWithCMD cmd = new CollIBMWithCMD(params);
		cmd.init(params);
		cmd.start();
		try {
			Thread.sleep(10 * 1000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		cmd.init(params);
		cmd.start();
		try {
			Thread.sleep(1000L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
