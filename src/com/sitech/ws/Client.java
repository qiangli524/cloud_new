package com.sitech.ws;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;

import com.sitech.basd.yicloud.util.CfgUtil;
import com.sitech.basd.yicloud.util.HttpClientUtil;

/**
 * 
 * <p>
 * Title: Client
 * </p>
 * <p>
 * Description: 客户端工具类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2012
 * </p>
 * <p>
 * Company: SI-TECH
 * </p>
 * 
 * @author huojla
 * @version 1.0
 * @createtime Oct 10, 2012 11:17:47 AM
 * 
 */
public class Client {
	private static Client client = new Client();
	/** CMDB通知链接 */
	private static String cmdbUrl = "http://" + CfgUtil.getString("cmdb.ip") + ":" + CfgUtil.getString("cmdb.port")
			+ "/" + CfgUtil.getString("cmdb.web") + "/rest/vmSynByIcrement/callerCMDB";
	/** 服务管理通知链接 */
	private static String servmanUrl = "http://" + CfgUtil.getString("servman.ip") + ":"
			+ CfgUtil.getString("servman.port") + "/" + CfgUtil.getString("servman.web")
			+ "/WebService/service/incrementalResources";
	/** 监控通知链接 */
	private static String monitorUrl = "http://" + CfgUtil.getString("monitor.ip") + ":"
			+ CfgUtil.getString("monitor.port") + "/" + CfgUtil.getString("monitor.web")
			+ "/ibnms/incrementalResources";

	/** 全局通知 */
	@Deprecated
	private static String globalUrl = "http://" + CfgUtil.getString("cloudglobal.ip") + ":"
			+ CfgUtil.getString("cloudglobal.port") + "/" + CfgUtil.getString("cloudglobal.web")
			+ "/vmware/domain/synVCenter/[]/";

	private Client() {

	}

	/**
	 * 
	 * @Title: getInstance
	 * @Description: 单例模式
	 * @param
	 * @return Client
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime Oct 10, 2012 11:23:47 AM
	 */
	public static synchronized Client getInstance() {
		if (client == null) {
			client = new Client();
		}
		return client;
	}

	/**
	 * 
	 * @Title: noticeOthers
	 * @Description: 通知其他WebService系统方法
	 * @param
	 * @return String
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime Oct 10, 2012 11:02:46 AM
	 */
	public String noticeOthers(String... param) {
		Map<String, String> obj = new LinkedHashMap<String, String>();
		// 创建传输实体map
		obj = makeServMap(param);
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		list.add(obj);
		// cmdb通知
		// String cmdbResult = HttpClientUtil.post(cmdbUrl,
		// JSONArray.fromObject(list));
		// 服务管理通知
		String servmanResult = HttpClientUtil.post(servmanUrl, JSONArray.fromObject(list));
		// 监控通知
		// String monitorResult = HttpClientUtil.post(monitorUrl, JSONArray
		// .fromObject(list));
		// 总体通知接口
		// String result = servmanResult;
		// String result = servmanResult;
		return null;
	}

	/**
	 * 
	 * @Title: noticeCMDB
	 * @Description: 通知CMDB
	 * @param
	 * @return String
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime Oct 10, 2012 11:02:46 AM
	 */
	public String noticeCMDB(String... param) {
		Map<String, String> obj = new LinkedHashMap<String, String>();
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		obj.put("TTYPE", param[0]);
		obj.put("CODE", param[1]);
		obj.put("TYPE", param[2]);
		obj.put("OPERATE", param[3]);
		list.add(obj);
		String cmdbResult = "";
		String monitorResult = "";
		// cmdb通知
		try {
			cmdbResult = HttpClientUtil.post(cmdbUrl, JSONArray.fromObject(list));
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			// 监控管理
			monitorResult = HttpClientUtil.post(monitorUrl, JSONArray.fromObject(list));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "[cmdbResult:" + cmdbResult + "].[monitorResult:" + monitorResult + "]";
		// return null;
	}

	/**
	 * 
	 * @Title: noticeCMDBAndMonitor
	 * @Description: 只通知cmdb和监控管理
	 * @param
	 * @return String
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime Oct 10, 2012 11:02:46 AM
	 */
	@Deprecated
	public String noticeCMDBAndMonitor(String... param) {
		// Map<String, String> obj = new LinkedHashMap<String, String>();
		// // 创建传输实体map
		// obj = makeServMap(param);
		// List<Map<String, String>> list = new ArrayList<Map<String,
		// String>>();
		// list.add(obj);
		// // cmdb通知
		// String cmdbResult = HttpClientUtil.post(cmdbUrl, JSONArray
		// .fromObject(list));
		// // 监控通知
		// String monitorResult = HttpClientUtil.post(monitorUrl, JSONArray
		// .fromObject(list));
		// // 总体通知接口
		// String result = "[cmdbResult:" + cmdbResult + "].[monitorResult:"
		// + monitorResult + "]";
		// return result;
		return null;
	}

	/**
	 * 
	 * @Title: globalSynchNotice
	 * @Description: 通知其他WebService系统方法
	 * @param
	 * @return String
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime Oct 10, 2012 11:02:46 AM
	 */
	public String globalSynchNotice(String... param) {
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		for (int i = 0; i < param.length; i++) {
			Map<String, String> map = new LinkedHashMap<String, String>();
			map.put("TTYPE", Type.TTYPE_CI);
			map.put("CODE", param[i]);
			map.put("TYPE", Type.CTYPE_VDC);
			map.put("OPERATE", Operation.OPER_ADD);
			list.add(map);
		}
		String cmdbResult = "";
		String servmanResult = "";
		String monitorResult = "";
		// 通知开始同步数据
		try {
			String postResult = HttpClientUtil.post(cmdbUrl, JSONArray.fromObject(list));
			if (postResult != null && !"".equals(postResult)) {
				JSONObject json = JSONObject.fromObject(postResult);
				String entity = (String) json.getString("responseCode");
				if (entity != null && entity.equals("200")) {
					cmdbResult = (String) json.get("responseEntity");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			String postResult = HttpClientUtil.post(servmanUrl, JSONArray.fromObject(list));
			if (postResult != null && !"".equals(postResult)) {
				JSONObject json = JSONObject.fromObject(postResult);
				String entity = (String) json.getString("responseCode");
				if (entity != null && entity.equals("200")) {
					servmanResult = (String) json.get("responseEntity");
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		try {
			String postResult = HttpClientUtil.post(monitorUrl, JSONArray.fromObject(list));
			if (postResult != null && !"".equals(postResult)) {
				JSONObject json = JSONObject.fromObject(postResult);
				String entity = (String) json.getString("responseCode");
				if (entity != null && entity.equals("200")) {
					monitorResult = (String) json.get("responseEntity");
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		Map<String, String> result = new HashMap<String, String>();
		result.put("cmdbResult", cmdbResult);
		result.put("servmanResult", servmanResult);
		result.put("monitorResult", monitorResult);
		// String result = "{\"cmdbResult\":\"" + cmdbResult
		// + "\",\"servmanResult\":\"" + servmanResult
		// + "\",\"monitorResult\":\"" + monitorResult + "\"}";
		JSONObject json = JSONObject.fromObject(result);
		return json.toString();
	}

	/**
	 * 
	 * @Title: makeServMap
	 * @Description: 创建数据传输map
	 * @param param[0]:
	 *            标示-NAME、CODE param[1]：类型-host/vm等 param[2]：操作-增、删、改
	 * @return Map
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime Sep 25, 2012 1:30:47 PM
	 */
	public Map<String, String> makeServMap(String... param) {
		Map<String, String> obj = new LinkedHashMap<String, String>();
		obj.put("TTYPE", param[0]);
		obj.put("CODE", param[1]);
		/** 由于目前Name和Code一致，所以暂时写成一样 */
		obj.put("NAME", param[2]);
		obj.put("TYPE", param[3]);
		obj.put("OPERATE", param[4]);
		return obj;
	}

	@SuppressWarnings("unchecked")
	/*
	 * public static void main(String[] args) { Map obj = new LinkedHashMap(); //
	 * obj = makeServMap("1234567", Type.CTYPE_VDC, Operation.OPER_ADD); //
	 * obj.put("TTYPE", Type.TTYPE_CI); // obj.put("CODE", "1234567"); //
	 * obj.put("NAME", "1234567"); // obj.put("TYPE", Type.CTYPE_VDC); //
	 * obj.put("OPERRATE", Operation.OPER_ADD); List list = new ArrayList();
	 * list.add(obj);
	 * 
	 * String result = HttpClientUtil .post(
	 * "http://localhost:8088/cloud/WebService/service/incrementalResources",
	 * JSONArray.fromObject(list)); 
	 * //String globalResult =
	 * HttpClientUtil.get(globalUrl); String monitorResult = null; List<String>
	 * alldcName = new ArrayList<String>(3); alldcName.add("dc1");
	 * alldcName.add("dc2"); alldcName.add("dc3"); String[] a = new String[3];
	 * try {
	 * monitorResult = HttpClientUtil.post(monitorUrl); } catch (Exception ex) {
	 */
	public static void main(String[] args) {
		Logger logger = Logger.getLogger(Client.class);
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		// Map<String, String> map11 = new HashMap<String, String>();
		Map<String, String> map22 = new HashMap<String, String>();
		// // map11.put("TTYPE", Type.TTYPE_CI);
		// // map11.put("CODE", "cluster1");
		// // map11.put("TYPE", Type.CTYPE_CLUSTER);
		// // map11.put("OPERATE", Operation.OPER_REL_MODI);
		//
		map22.put("TTYPE", Type.TTYPE_CI);
		map22.put("CODE", "vm-339");
		map22.put("TYPE", Type.CTYPE_VM);
		map22.put("OPERATE", Operation.OPER_DEL);
		// list.add(map11);
		list.add(map22);
		logger.debug(HttpClientUtil.post(
				"http://172.21.105.230:8080/WsServer/WebService/service/incrementalResources", JSONArray
						.fromObject(list)));
	}
}
