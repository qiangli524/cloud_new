package com.sitech.basd.sxcloud.cloud.web.ip.action;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.beanutils.BeanUtils;

import com.sitech.basd.sxcloud.cloud.domain.net.TbCloud2IpInfoObj;
import com.sitech.basd.sxcloud.cloud.domain.net.TbCloud2NetInfoObj;
import com.sitech.basd.sxcloud.cloud.domain.resource.TbCloud2HostInfoObj;
import com.sitech.basd.sxcloud.cloud.domain.vmhost.VMHostObj;
import com.sitech.basd.sxcloud.cloud.service.net.IpService;
import com.sitech.basd.sxcloud.cloud.service.net.NetService;
import com.sitech.basd.sxcloud.cloud.service.resource.HostInfoService;
import com.sitech.basd.sxcloud.cloud.service.vmhost.VMHostService;
import com.sitech.basd.sxcloud.cloud.web.ip.form.IpForm;
import com.sitech.basd.sxcloud.cloud.web.resource.form.HostManageForm;
import com.sitech.basd.sxcloud.rsmu.domain.system.TbSysOperationLogObj;
import com.sitech.basd.sxcloud.rsmu.service.deploy.DeployExampleService;
import com.sitech.basd.sxcloud.rsmu.service.hostmanage.BusiHostService;
import com.sitech.basd.sxcloud.rsmu.web.CRUDBaseAction;
import com.sitech.basd.sxcloud.rsmu.web.util.exception.BaseException;
import com.sitech.basd.sxcloud.util.Struts2Utils;
import com.sitech.basd.yicloud.web.showVm.form.ShowVmForm;
import com.sitech.utils.servlet.PrintWriterOut;
import com.sitech.utils.ssh.SshPingIp;

@SuppressWarnings("all")
public class IpAction extends CRUDBaseAction {
	private IpForm theForm;

	private HostManageForm hostForm;

	private ShowVmForm vmForm;

	private HostInfoService hostInfoService;

	private VMHostService vmHostService;

	private BusiHostService busiHostService;

	private DeployExampleService deployExampleService;

	private IpService ipService;

	private NetService netService;

	private String reMess;

	public String getReMess() {
		return reMess;
	}

	public void setReMess(String reMess) {
		this.reMess = reMess;
	}

	public void setNetService(NetService netService) {
		this.netService = netService;
	}

	public void setIpService(IpService ipService) {
		this.ipService = ipService;
	}

	public ShowVmForm getVmForm() {
		return vmForm;
	}

	public void setVmForm(ShowVmForm vmForm) {
		this.vmForm = vmForm;
	}

	public void setBusiHostService(BusiHostService busiHostService) {
		this.busiHostService = busiHostService;
	}

	public void setDeployExampleService(DeployExampleService deployExampleService) {
		this.deployExampleService = deployExampleService;
	}

	public HostManageForm getHostForm() {
		return hostForm;
	}

	public void setHostForm(HostManageForm hostForm) {
		this.hostForm = hostForm;
	}

	public void setVmHostService(VMHostService vmHostService) {
		this.vmHostService = vmHostService;
	}

	public void setHostInfoService(HostInfoService hostInfoService) {
		this.hostInfoService = hostInfoService;
	}

	public IpForm getTheForm() {
		return theForm;
	}

	public void setTheForm(IpForm theForm) {
		this.theForm = theForm;
	}

	/**
	 * 
	 * @Title:查询系统IP信息
	 * @Copyright: Copyright (c) 2011-12-13
	 * @Company: si-tech
	 * @author liys
	 * @version 1.0
	 */

	public String listIpInfo() throws BaseException {
		if (theForm == null) {
			theForm = new IpForm();
		}
		HttpServletRequest request = Struts2Utils.getRequest();
		TbCloud2IpInfoObj obj = new TbCloud2IpInfoObj();
		String netId = request.getParameter("netId");
		if (netId != null && !"".equals(netId)) {
			request.setAttribute("net_id", netId);
			theForm.setNET_ID(netId);
			obj.setNET_ID(netId);
		} else {
			if (theForm.getNET_ID() != null && !theForm.getNET_ID().equals("0")) {
				request.setAttribute("net_id", theForm.getNET_ID());
				theForm.setNET_ID(theForm.getNET_ID());
				obj.setNET_ID(theForm.getNET_ID());
			}
		}
		if (theForm.getQueryNetId() != null && !"".equals(theForm.getQueryNetId())) {
			obj.setNET_ID(theForm.getQueryNetId());
		}
		if (theForm.getQUERYIPADDRESS() != null && !"".equals(theForm.getQUERYIPADDRESS())) {
			obj.setIPADDRESS(theForm.getQUERYIPADDRESS().trim());
		}
		if (theForm.getISUSED() != null && !"".equals(theForm.getISUSED())
				&& !theForm.getISUSED().equals("-1")) {
			obj.setISUSED(theForm.getISUSED());
		}
		if (theForm.getISBLOCKED() != null && !"".equals(theForm.getISBLOCKED())
				&& !theForm.getISBLOCKED().equals("-1")) {
			obj.setISBLOCKED(theForm.getISBLOCKED());
		}
		obj.setPagination(this.getPaginater().initPagination(request));
		List ipList = ipService.queryForListByObj(obj);
		TbCloud2NetInfoObj netObj = new TbCloud2NetInfoObj();
		List netLst = netService.queryForListByObj(netObj);
		theForm.setNetList(netLst);
		theForm.setResultList(ipList);
		return LIST;
	}

	/**
	 * @Title:增加IP管理模块信息
	 * @Copyright: Copyright (c) 20111210
	 * @Company: si-tech
	 * @author liys
	 * @version 1.0
	 */
	@SuppressWarnings("unchecked")
	public String addIpInfo() throws BaseException {
		if (theForm == null) {
			theForm = new IpForm();
		}
		HttpServletRequest request = Struts2Utils.getRequest();
		String netId = request.getParameter("netId");
		List nnList = new ArrayList();
		TbCloud2NetInfoObj tbCloud2NetInfoObj = new TbCloud2NetInfoObj();
		if (netId == null || netId.equals("") || netId.equals("null")) {

			nnList = ipService.queryForListByNetObj(tbCloud2NetInfoObj);
		} else {
			tbCloud2NetInfoObj.setNET_ID(netId);
			nnList = ipService.queryForListByNetObj(tbCloud2NetInfoObj);
		}
		theForm.setNetList(nnList);
		theForm.setIPADDRESS("");
		// form.reset(mapping, request);// 清空ActionForm
		return ADD;
		// return new String("/listIpInfo.do?netId=" +
		// theForm.getNET_ID());
	}

	/**
	 * @Title:保存IP信息请求
	 * @Copyright: Copyright (c) 20111210
	 * @Company: si-tech
	 * @author liys
	 * @version 1.0
	 */
	public String saveIpInfo() throws BaseException {
		if (theForm == null) {
			theForm = new IpForm();
		}
		HttpServletRequest request = Struts2Utils.getRequest();
		TbCloud2IpInfoObj obj = new TbCloud2IpInfoObj();
		String netId = request.getParameter("netId");
		if (netId != null && !"".equals(netId)) {
			request.setAttribute("net_id", netId);
			theForm.setNET_ID(netId);
			obj.setNET_ID(netId);
		} else {
			request.setAttribute("net_id", theForm.getNET_ID());
			theForm.setNET_ID(theForm.getNET_ID());
			obj.setNET_ID(theForm.getNET_ID());
		}

		// obj.setNET_ID(netId);
		theForm.setFlag(theForm.getFlag());
		try {
			BeanUtils.copyProperties(obj, theForm);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		TbSysOperationLogObj operObj = this.getTbSysOperationLogObj(request);
		operObj.setREMARK("");

		String startIp = theForm.getSTARTIPADDRESS();// .substring(theForm.getSTARTIPADDRESS().lastIndexOf(".")
		// + 1);
		// //开发IP最后一个.后边的数值
		String endIp = theForm.getENDIPADDRESS();// .substring(theForm.getENDIPADDRESS().lastIndexOf(".")
		// + 1); //结束IP最后一个.后边的数值
		int ret = 0;
		int result = 0;
		if (theForm.getFlag() == 0) {
			if (theForm.getChoose() == 0) {
				// 一次增加单个IP地址
				// BeanUtils.copyProperties(obj,theForm);
				ret = ipService.insertByObj(obj);
			} else {
				// 一次增加多个IP地址，即增加IP段。
				String stip = startIp;// "255.255.255.11";
				String enip = endIp;// "255.255.255.254";
				obj.setSTARTIPADDRESS(stip);
				obj.setENDIPADDRESS(enip);
				ret = ipService.insertManyIpByObj(obj);
			}
			operObj.setOPERTYPE(1);
			operObj.setMESSAGE("新增功能信息");
		} else {
			ret = ipService.updateByObj(obj);
			operObj.setOPERTYPE(3);
			operObj.setMESSAGE("修改功能信息");
		}
		if (ret > 0) {
			result = 1;
		}
		operObj.setRESULT(result);
		@SuppressWarnings("unused")
		int retOper = operationService.insertByObj(operObj);// 写操作日志
		try {
			Struts2Utils.getResponse()
					.sendRedirect("ip_listIpInfo.do?netId=" + theForm.getNET_ID());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
		// return SUCCESS;
	}

	/**
	 * @Title:修改IP管理模块信息
	 * @Copyright: Copyright (c) 20111210
	 * @Company: si-tech
	 * @author liys
	 * @version 1.0
	 */
	@SuppressWarnings("unchecked")
	public String modIpInfo() throws BaseException {
		if (theForm == null) {
			theForm = new IpForm();
		}
		TbCloud2IpInfoObj obj = new TbCloud2IpInfoObj();
		obj.setIP_ID(theForm.getIP_ID());
		TbCloud2IpInfoObj tempObj = ipService.queryByObj(obj);
		TbCloud2NetInfoObj tbCloud2NetInfoObj = new TbCloud2NetInfoObj();
		List nnList = ipService.queryForListByNetObj(tbCloud2NetInfoObj);
		theForm.setNetList(nnList);

		// tempObj.setIP_ID(theForm.getIP_ID()); 测试用语句
		try {
			BeanUtils.copyProperties(theForm, tempObj);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return "edit";
	}

	/**
	 * @Title:删除IP管理模块请求
	 * @Copyright: Copyright (c) 20111210
	 * @Company: si-tech
	 * @author liys
	 * @version 1.0
	 */
	public String delIpInfo() throws BaseException {
		if (theForm == null) {
			theForm = new IpForm();
		}
		TbCloud2IpInfoObj obj = new TbCloud2IpInfoObj();

		String ip = request.getParameter("ip");
		int ret = 0;
		if (ip != null && ip.length() > 0) {
			String[] address = ip.split(",");

			int result = 0;
			// 删除多个IP地址
			if (address.length > 1) {
				for (int i = 0; i < address.length; i++) {
					obj.setIPADDRESS(address[i]);
					ret = ipService.deleteByObj(obj);
				}
			} else {
				obj.setIPADDRESS(ip);
				ret = ipService.deleteByObj(obj);
			}
		}
		int result = 0;
		if (theForm.getIP_ID() != null & !"".equals(theForm.getIP_ID())) {
			obj.setIP_ID(theForm.getIP_ID());
			ret = ipService.deleteByObj(obj);
		}

		if (ret > 0) {
			result = 1;
		}
		TbSysOperationLogObj operObj = this.getTbSysOperationLogObj(request);
		operObj.setOPERTYPE(2);
		operObj.setMESSAGE("删除功能信息");
		operObj.setREMARK("");
		operObj.setRESULT(result);
		@SuppressWarnings("unused")
		int retOper = operationService.insertByObj(operObj);// 写操作日志
		try {
			Struts2Utils.getResponse()
					.sendRedirect("ip_listIpInfo.do?netId=" + theForm.getNET_ID());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return listIpInfo();
		// return SUCCESS;
	}

	/**
	 * @Title:
	 * @Copyright: Copyright (c) Dec 31, 2011
	 * @Company: si-tech
	 * @author liys
	 * @version 1.0
	 */

	public static String[] createIps(String startIp, String endIp) {
		// String [] ips =null; 测试用语句
		int start = Integer.valueOf(startIp.split("\\.")[3]); // 开始IP最后一个.后边的串转换INT后的数值
		int end = Integer.valueOf(endIp.split("\\.")[3]); // 结束IP最后一个.后边的串转换INT后的数值
		String[] ips = new String[end - start + 1]; // 设置返回数组的大小
		String head = startIp.substring(0, startIp.lastIndexOf(".") + 1); // 取IP地址前边三个数值，包括点号。即：*.*.*.
		try {
			for (int k = 0; k <= end - start; k++) {
				ips[k] = head + (start + k);
			}
			return ips;
		} catch (ArrayIndexOutOfBoundsException aioobe) {
			aioobe.printStackTrace();
		}
		return ips;
	}

	/**
	 * @Title:阻塞IP
	 * @Copyright: Copyright (c) 20111210
	 * @Company: si-tech
	 * @author wangjian
	 * @version 1.0
	 */
	@SuppressWarnings("unchecked")
	public String backupIpInfo() throws BaseException {
		if (theForm == null) {
			theForm = new IpForm();
		}
		String ipAddress = request.getParameter("ip");
		TbCloud2IpInfoObj obj = new TbCloud2IpInfoObj();
		int result = 0;
		obj.setIPADDRESS(ipAddress);
		int ret = ipService.backupByObj(obj);
		TbSysOperationLogObj operObj = this.getTbSysOperationLogObj(request);
		operObj.setOPERTYPE(2);
		operObj.setMESSAGE("阻塞IP");
		operObj.setREMARK("");
		operObj.setRESULT(result);
		@SuppressWarnings("unused")
		int retOper = operationService.insertByObj(operObj);// 写操作日志
		try {
			Struts2Utils.getResponse()
					.sendRedirect("ip_listIpInfo.do?netId=" + theForm.getNET_ID());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * @Title:发布IP
	 * @Copyright: Copyright (c) 20111210
	 * @Company: si-tech
	 * @author wangjian
	 * @version 1.0
	 */
	@SuppressWarnings("unchecked")
	public String issuanceIpInfo() throws BaseException {
		if (theForm == null) {
			theForm = new IpForm();
		}
		TbCloud2IpInfoObj obj = new TbCloud2IpInfoObj();
		int result = 0;
		String ipAddress = request.getParameter("ip");
		obj.setIPADDRESS(ipAddress);
		int ret = ipService.issuanceByObj(obj);
		if (ret > 0) {
			result = 1;
		}
		TbSysOperationLogObj operObj = this.getTbSysOperationLogObj(request);
		operObj.setOPERTYPE(2);
		operObj.setMESSAGE("发布IP");
		operObj.setREMARK("");
		operObj.setRESULT(result);
		@SuppressWarnings("unused")
		int retOper = operationService.insertByObj(operObj);// 写操作日志
		try {
			Struts2Utils.getResponse()
					.sendRedirect("ip_listIpInfo.do?netId=" + theForm.getNET_ID());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * @Title: checkStatus
	 * @Description: 手动检测ip状态
	 * @param
	 * @return String
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-8-30 下午2:47:05
	 */
	public String checkStatus() {
		JSONArray ja = new JSONArray();
		TbCloud2HostInfoObj tbCloud2HostInfoObj = new TbCloud2HostInfoObj();
		VMHostObj vmHostObj = new VMHostObj();
		TbCloud2IpInfoObj tbCloud2IpInfoObj = new TbCloud2IpInfoObj();
		boolean flag = false;

		String ipstr = request.getParameter("ip");
		String[] iparr = ipstr.split(",");
		for (String ip : iparr) {
			if (!"".equals(ip)) {
				JSONObject jo = new JSONObject();
				jo.put("ipaddress", ip);
				tbCloud2HostInfoObj.setEq_ip(ip);
				TbCloud2HostInfoObj hobj = hostInfoService.queryByObj(tbCloud2HostInfoObj);

				vmHostObj.setVH_IP(ip);
				VMHostObj vobj = vmHostService.queryByObj(vmHostObj);

				tbCloud2IpInfoObj.setIPADDRESS(ip);
				TbCloud2IpInfoObj obj = ipService.queryByObj(tbCloud2IpInfoObj);
				if (obj != null) {
					if (hobj == null && vobj == null) {
						jo.put("isused", "0");
						if (!"0".equals(obj.getISUSED())) {
							obj.setISUSED("0");
							flag = true;
						}
					} else {
						jo.put("isused", "1");
						if (!"1".equals(obj.getISUSED())) {
							obj.setISUSED("1");
							flag = true;
						}
						if (SshPingIp.pingOtherServer(ip)) {
							jo.put("isblock", "0");
							if (!"0".equals(obj.getISBLOCKED())) {
								obj.setISBLOCKED("0");
								flag = true;
							}
						} else {
							jo.put("isblock", "1");
							if (!"1".equals(obj.getISBLOCKED())) {
								obj.setISBLOCKED("1");
								flag = true;
							}
						}
					}
					if (flag) {
						ipService.updateIPStat(obj);
					}
				}
				ja.add(jo);
			}
		}

		try {
			// PrintWriter pw = response.getWriter();
			// pw.print(ja.toString());
			// pw.flush();
			// pw.close();
			PrintWriterOut.printWirter(response, ja.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 若ip被占用，显示占用该ip的主机或虚拟机的详细信息
	 * 
	 * @author lipengpeng
	 * @return
	 */
	public String showMachineInfo() {
		String ipaddress = request.getParameter("ipaddress");
		TbCloud2HostInfoObj cloud2HostInfoObj = new TbCloud2HostInfoObj();
		cloud2HostInfoObj.setEq_ip(ipaddress);
		TbCloud2HostInfoObj hostInfoObj = hostInfoService.queryByObj(cloud2HostInfoObj);
		if (hostInfoObj == null) {
			VMHostObj vmHostObj = new VMHostObj();
			vmHostObj.setVH_IP(ipaddress);
			vmHostObj.setUSER_ID(session.get("id").toString());
			List<VMHostObj> vmList = vmHostService.queryForVmList(vmHostObj);
			if (vmForm == null) {
				vmForm = new ShowVmForm();
			}
			if (vmList == null) {
				vmList = new ArrayList<VMHostObj>();
			}
			vmForm.setResultList(vmList);
			return "listvm";
			/*
			 * if (vmHostObj.getVH_SYSTEM() == null ||
			 * vmHostObj.getVH_SYSTEM().equals("")) {
			 * vmHostObj.setVH_SYSTEM("未提取到数据"); } if
			 * (vmHostObj.getVH_TYPE().equals("1")) { DecimalFormat nf = new
			 * DecimalFormat("0.0"); if (vmHostObj.getVH_STORAGE() != null &&
			 * !vmHostObj.getVH_STORAGE().equals("")) {
			 * vmHostObj.setVH_STORAGE(nf
			 * .format(Double.parseDouble(vmHostObj.getVH_STORAGE()) / 1024 /
			 * 1024)); } else { vmHostObj.setVH_STORAGE(""); }
			 * vmHostObj.setNICNUM("1"); if (vmHostObj.getVH_IP() == null ||
			 * vmHostObj.getVH_IP().equals("")) { vmHostObj.setVH_IP("-"); }
			 * vmHostObj.setVH_TYPE("VMWARE"); } else if
			 * (vmHostObj.getVH_TYPE().equals("3")) { DecimalFormat nf = new
			 * DecimalFormat("0.0"); if (vmHostObj.getVH_STORAGE() != null &&
			 * !vmHostObj.getVH_STORAGE().equals("")) {
			 * vmHostObj.setVH_STORAGE(nf
			 * .format(Double.parseDouble(vmHostObj.getVH_STORAGE()) / 1024 /
			 * 1024)); } else { vmHostObj.setVH_STORAGE(""); }
			 * vmHostObj.setNICNUM("1"); if (vmHostObj.getVH_IP() == null ||
			 * vmHostObj.getVH_IP().equals("")) { vmHostObj.setVH_IP("-"); }
			 * vmHostObj.setVH_TYPE("XEN"); }
			 * 
			 * String VH_IP = vmHostObj.getVH_IP(); if (VH_IP == null ||
			 * VH_IP.equals("")) { vmHostObj.setAPPNUM(0); } else {
			 * TbBusiHostObj hostObj = new TbBusiHostObj();
			 * hostObj.setIP(VH_IP); List HostLst =
			 * busiHostService.queryIDByIP(hostObj); if (HostLst != null &&
			 * HostLst.size() > 0) { hostObj = (TbBusiHostObj) HostLst.get(0);
			 * vmHostObj.setHOST_ID(hostObj.getID()); TbBusiDeployExampleObj
			 * deployObj = new TbBusiDeployExampleObj();
			 * deployObj.setHOSTID(hostObj.getID()); List deployList =
			 * deployExampleService.queryForListByObj(deployObj); if (deployList
			 * != null && deployList.size() > 0) {
			 * vmHostObj.setAPPNUM(deployList.size()); } else {
			 * vmHostObj.setAPPNUM(0); } } else { vmHostObj.setAPPNUM(0); } }
			 */

		} else {
			Map<String, String> map = new HashMap<String, String>();
			map.put("hostId", hostInfoObj.getEq_id());
			if ("3".equals(hostInfoObj.getHasvertual())) {
				map.put("type", "3");
			} else if ("4".equals(hostInfoObj.getHasvertual())) {
				map.put("type", "1");
			} else {
				map.put("type", "");
			}
			hostInfoObj.setVm_num(hostInfoService.countvmNum(map));// 主机下虚拟机的个数
			Map<String, String> map2 = new HashMap<String, String>();
			map2.put("hostId", hostInfoObj.getH_uuid());
			hostInfoObj.setStorage_num(hostInfoService.countStorageNum(map2));// 主机下存储的个数
			List<TbCloud2HostInfoObj> resultList = new ArrayList<TbCloud2HostInfoObj>();
			resultList.add(hostInfoObj);
			if (hostForm == null) {
				hostForm = new HostManageForm();
			}
			hostForm.setResultList(resultList);
			return "allHost";
		}
	}

	/**
	 * 
	 * @Title: validateIpInfo
	 * @Description: 验证IP地址是否重复
	 * @param
	 * @return String
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime 2013-9-4 上午10:56:04
	 */
	public String validateIpInfo() {
		String creatType = request.getParameter("creatType");
		if (creatType.equals("single")) {
			String ipAddr = request.getParameter("ipAddr");
			TbCloud2IpInfoObj ipObj = new TbCloud2IpInfoObj();
			ipObj.setIPADDRESS(ipAddr);
			ipObj = ipService.queryByObj(ipObj);
			if (ipObj == null) {
				reMess = "true";
			} else {
				reMess = "该IP地址已存在！";
			}
		} else {
			String ipStart = request.getParameter("ipStart");
			String ipEnd = request.getParameter("ipEnd");
			String ipAddr = ipStart.substring(0, ipStart.lastIndexOf("."));
			TbCloud2IpInfoObj ipObj = new TbCloud2IpInfoObj();
			ipObj.setIPADDRESS(ipAddr);
			List<TbCloud2IpInfoObj> ipList = ipService.queryForListByObj(ipObj);
			if (ipList == null || ipList.size() == 0) {
				reMess = "true";
			} else {
				StringBuffer rePeatIps = new StringBuffer();
				for (int i = 0; i < ipList.size(); i++) {
					int start = Integer.parseInt(ipStart.substring(ipStart.lastIndexOf(".") + 1));
					int end = Integer.parseInt(ipEnd.substring(ipEnd.lastIndexOf(".") + 1));
					String ip = "";
					for (int j = start; j <= end; j++) {
						ip = ipAddr + "." + j;
						if (ip.equals(ipList.get(i).getIPADDRESS())) {
							rePeatIps.append(ipList.get(i).getIPADDRESS()).append(",");
						}
					}
				}
				if (rePeatIps.length() == 0) {
					reMess = "true";
				} else {
					reMess = rePeatIps + "已存在！";
				}
			}
		}
		return "validate";
	}
}
