package com.sitech.basd.yicloud.service.vmman;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;

import com.sitech.basd.sxcloud.cloud.dao.image.TbImageInfoDao;
import com.sitech.basd.sxcloud.cloud.dao.net.TbIpDao;
import com.sitech.basd.sxcloud.cloud.dao.vmhost.VMHostDao;
import com.sitech.basd.sxcloud.cloud.domain.image.TbCloud2ImageInfoObj;
import com.sitech.basd.sxcloud.cloud.domain.net.TbCloud2IpInfoObj;
import com.sitech.basd.sxcloud.cloud.domain.vmhost.VMHostObj;
import com.sitech.basd.sxcloud.rsmu.config.Constant;
import com.sitech.basd.sxcloud.rsmu.dao.hostmanage.TbBusiHostConfigDao;
import com.sitech.basd.sxcloud.rsmu.dao.hostmanage.TbBusiHostObjDao;
import com.sitech.basd.sxcloud.rsmu.domain.hostmanage.TbBusiHostConfigObj;
import com.sitech.basd.sxcloud.rsmu.domain.hostmanage.TbBusiHostObj;
import com.sitech.basd.yicloud.dao.vmman.VMManagerDao;
import com.sitech.basd.yicloud.domain.vmman.CollObj;
import com.sitech.basd.yicloud.domain.vmman.EntityConObj;
import com.sitech.basd.yicloud.domain.vmman.EntityTreeObj;
import com.sitech.basd.yicloud.domain.vmman.TbYicloudDeviceHealthObj;
import com.sitech.basd.yicloud.domain.vmman.TbYicloudOsTypeObj;
import com.sitech.basd.yicloud.domain.vmman.VMManagerObj;
import com.sitech.basd.yicloud.util.HttpClientUtil;
import com.sitech.basd.yicloud.util.InvokeUtil;
import com.sitech.basd.yicloud.util.JSONUtil;
import com.sitech.basd.yicloud.util.TypeConstant;

/**
 * 
 * <p>
 * Title: VMManagerServiceImpl
 * </p>
 * <p>
 * Description: 虚拟机管理实现类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2012
 * </p>
 * <p>
 * Company: SI-TECH
 * </p>
 * 
 * @author taoxue
 * @version 1.0
 * @createtime Apr 18, 2012 11:08:57 AM
 * 
 */
public class VMManagerServiceImpl implements VMManagerService {
	private TbImageInfoDao tbImageInfoDao;
	private VMManagerDao vmManagerDao;
	private TbIpDao tbIpDao;
	private TbBusiHostObjDao tbBusiHostObjDao;
	private VMHostDao vmHostDao;
	private TbBusiHostConfigDao tbBusiHostConfigDao;

	public void setTbBusiHostObjDao(TbBusiHostObjDao tbBusiHostObjDao) {
		this.tbBusiHostObjDao = tbBusiHostObjDao;
	}

	public void setVmHostDao(VMHostDao vmHostDao) {
		this.vmHostDao = vmHostDao;
	}

	public void setTbBusiHostConfigDao(TbBusiHostConfigDao tbBusiHostConfigDao) {
		this.tbBusiHostConfigDao = tbBusiHostConfigDao;
	}

	public void setTbIpDao(TbIpDao tbIpDao) {
		this.tbIpDao = tbIpDao;
	}

	public void setTbImageInfoDao(TbImageInfoDao tbImageInfoDao) {
		this.tbImageInfoDao = tbImageInfoDao;
	}

	public void setVmManagerDao(VMManagerDao vmManagerDao) {
		this.vmManagerDao = vmManagerDao;
	}

	/**
	 * @Title:部署虚拟机
	 * @Copyright: Copyright (c) 20120413
	 * @Company: si-tech
	 * @author taoxue
	 * @version 1.0
	 */
	public String createVirtual(int ID, String IM_ID, VMManagerObj vObj) {
		// 设置进度条进度
		Constant.PROCESS_LEVEL = 10;
		// 占用IP
		String ip = vObj.getIP();
		TbCloud2IpInfoObj ipObj = new TbCloud2IpInfoObj();
		ipObj.setISUSED("1");
		ipObj.setIPADDRESS(ip);
		// 更新IP使用状态
		tbIpDao.updateIPStat(ipObj);
		VMManagerObj obj = new VMManagerObj();
		TbCloud2ImageInfoObj tObj = new TbCloud2ImageInfoObj();
		obj.setID(ID);
		tObj.setIM_ID(IM_ID);
		VMManagerObj dObj = vmManagerDao.queryByObj(obj);
		TbCloud2ImageInfoObj tbObj = tbImageInfoDao.queryByObj(tObj);
		String TEM_ID = tbObj.getTEM_ID();
		String NAME_ZH = vObj.getNAME_ZH();// 虚拟机名称
		String NAME_EH = vObj.getNAME_EN();
		String MEMORY = vObj.getMEMORY();
		String CPU = vObj.getCPU();
		String CURRENT_CPU = vObj.getCURRENTCPU();
		String CURRENT_MEMORY = vObj.getCURRENTMEMORY();
		String NAME_IMAGE = tbObj.getIM_NAME();
		// 主机相关信息
		String hostIp = dObj.getIP();
		// 主机名称
		String hostName = dObj.getNAME_EN();
		String hostUsername = "root";
		String hostPassword = "111111";
		int hostPort = 22;
		long timeOut = 60000l;
		String shellUrl = "/shell/command/exec/" + hostIp + "/" + hostUsername
				+ "/" + hostPassword + "/" + hostPort + "/" + timeOut + "/"
				+ "image-clone" + "/[old:" + NAME_IMAGE + ".img].[new:"
				+ NAME_EH + ".img]" + "/";
		String re = InvokeUtil.invoke(shellUrl);
		// 设置进度条进度
		Constant.PROCESS_LEVEL = 90;
		/** 判断镜像是否创建成功 */
		String sshUrl = "/ssh/filesize/compare/" + hostIp + "/" + hostUsername
				+ "/" + hostPassword + "/" + hostPort + "/" + timeOut
				+ "/[old:" + NAME_IMAGE + ".img].[new:" + NAME_EH + ".img]"
				+ "/";
		long now = new Date().getTime();
		while (((new Date().getTime()) - now) < 5 * 60 * 1000) {
			String res = InvokeUtil.invoke(sshUrl);
			if ("0".equals(res)) {
				break;
			}
			try {
				Thread.sleep(30 * 1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		// 设置进度条进度
		Constant.PROCESS_LEVEL = 100;
		/** 修改IP */
		String ipUrl = "/shell/command/exec/" + hostIp + "/" + hostUsername
				+ "/" + hostPassword + "/" + hostPort + "/" + timeOut + "/"
				+ "ifcfg-eth0-change" + "/[image_name:" + NAME_EH
				+ ".img].[ip:" + ip + "].[gateway:172.21.0.1]" + "/";
		String ipres = InvokeUtil.invoke(ipUrl);
		try {
			Thread.sleep(5 * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		/** 构造创建虚拟机所需参数 */
		StringBuffer paramStr = new StringBuffer();
		paramStr.append("[virtual-name:").append(NAME_EH).append("].[")
				.append("memory:").append(MEMORY).append("].[")
				.append("currentMemory:").append(CURRENT_MEMORY).append("].[")
				.append("currentVcpu:").append(CURRENT_CPU).append("].[")
				.append("vcpu:").append(CPU).append("].[").append("domainImg:")
				.append(NAME_EH).append("]");
		String param = "/kvm/domain/create/" + hostIp + "/" + TEM_ID + "/"
				+ paramStr.toString() + "/";
		String result = InvokeUtil.invoke(param);
		// 判断虚拟机是否创建成功
		try {
			int res = Integer.parseInt(result);
		} catch (NumberFormatException e) {
			ipObj.setISUSED("0");
			ipObj.setIPADDRESS(ip);
			// 更新IP使用状态
			tbIpDao.updateIPStat(ipObj);
			result = "error";
			return "{'result':'" + result + "'}";
		}
		result = "null";
		// 设置进度条进度
		// 插Divice表数据
		VMManagerObj virtualObj = new VMManagerObj();
		virtualObj.setNAME_EN(NAME_EH);
		virtualObj.setNAME_ZH(NAME_ZH);
		virtualObj.setIP(vObj.getIP());
		virtualObj.setTYPE("3");
		virtualObj.setVH_HOST(String.valueOf(ID));
		virtualObj.setCPU(CPU);
		virtualObj.setMEMORY(MEMORY);
		String id = vmManagerDao.getIdSequence();
		virtualObj.setID(Integer.parseInt(id));
		int rest = vmManagerDao.insertByObj(virtualObj);
		// 插入主机信息
		TbBusiHostObj tbHostObj = new TbBusiHostObj();
		tbHostObj.setIP(ip);
		tbHostObj.setSTATUS("1");
		tbHostObj.setHOSTNAME(NAME_EH);
		int ret1 = tbBusiHostObjDao.insertByObj(tbHostObj);
		TbBusiHostObj tbBusiObj = tbBusiHostObjDao.queryByObj(tbHostObj);
		// 插入主机配置信息
		TbBusiHostConfigObj tbHostCinfigObj = new TbBusiHostConfigObj();
		tbHostCinfigObj.setHOSTID(tbBusiObj.getID());
		tbHostCinfigObj.setHOSTUSERNAME(hostUsername);
		tbHostCinfigObj
				.setHOSRPWD("5db9bb02c17f90eef555060f647d9656b977926b44de0c05");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm24:ss");
		tbHostCinfigObj.setUPDATETTIME(sdf.format(new Date()));
		tbHostCinfigObj.setTYPE(0);
		int ret2 = tbBusiHostConfigDao.insertByObj(tbHostCinfigObj);
		// 插入虚拟机信息
		VMHostObj vmObj = new VMHostObj();
		vmObj.setVH_IP(ip);
		vmObj.setVH_NAME(NAME_EH);
		vmObj.setEQ_ID("1_01_001_0029_01");
		String VH_ID = createNewVhostId2(vmObj.getEQ_ID());
		vmObj.setVH_ID(VH_ID);
		vmObj.setVH_TYPE("KVM");
		int ret3 = vmHostDao.insertByVMhostObj(vmObj);

		// 向entity——tree中插入数据
		EntityTreeObj eObj = new EntityTreeObj();
		eObj.setEntityId(String.valueOf(ID));// 主机的entityId
		eObj.setName(hostName);
		EntityTreeObj e = vmManagerDao.queryTreeNode(eObj);// 通过主机的entityId查询其id
		eObj.setParentId(e.getId());
		eObj.setName(NAME_EH);
		eObj.setType(TypeConstant.KVM_VM);
		eObj.setEntityId(id);
		int ret4 = vmManagerDao.insertTreeNode(eObj);

		return "{'result':'" + result + "'}";
	}

	/**
	 * @Title:创建VMware虚拟机
	 * @Copyright: Copyright (c) 20120413
	 * @Company: si-tech
	 * @author lzh
	 * @version 1.0
	 */
	public String createVMwareVirtual(int ID, VMManagerObj vObj) {
		// 设置进度条进度
		Constant.PROCESS_LEVEL = 10;
		/** 暂未使用 */
		// 占用IP
		String ip = vObj.getIP();
		TbCloud2IpInfoObj ipObj = new TbCloud2IpInfoObj();
		ipObj.setISUSED("1");
		ipObj.setIPADDRESS(ip);
		// 更新IP使用状态
		tbIpDao.updateIPStat(ipObj);
		String result = "";
		String hostUsername = "root";// 虚拟机用户名
		String dcName = "si-tech";// 数据中心名称
		String NAME_ZH = vObj.getNAME_ZH();// 虚拟机名称
		String NAME_EH = vObj.getNAME_EN();
		String MEMORY = vObj.getMEMORY();
		String CPU = vObj.getCPU();
		String CURRENT_CPU = vObj.getCURRENTCPU();
		String CURRENT_MEMORY = vObj.getCURRENTMEMORY();
		String datastoreName = "datastore1";// 存储名称
		String diskMode = "persistent";// 硬盘模式
		String guestOsId = "winXPProGuest";// 客户机操作系统类型
		String netName = "VM Network";// 网络名称
		String nicName = "Network Adapter 1";// 网卡名称
		long diskSizeKB = 16000000;
		// 创建VMWare虚拟机
		String createVMUrl = "/vmware/domain/create/[dcName:" + dcName
				+ "].[vmName:" + NAME_EH + "].[memorySizeMB:"
				+ Integer.parseInt(CURRENT_MEMORY) / 1024 + "].[cupCount:"
				+ CURRENT_CPU + "].[datastoreName:" + datastoreName
				+ "].[diskSizeKB:" + diskSizeKB + "].[diskMode:" + diskMode
				+ "].[guestOsId:" + guestOsId + "].[netName:" + netName
				+ "].[nicName:" + nicName + "]/";
		String createResult = InvokeUtil.invoke(createVMUrl);

		// 挂载DVD驱动器
		String mountCdUrl = "/vmware/domain/reconfig/[vmName:" + NAME_EH
				+ "].[device:cd].[op:Add].[value:" + NAME_EH + "Cd]/";
		String mountResult = InvokeUtil.invoke(mountCdUrl);

		// 设置进度条进度
		// 插Divice表数据
		VMManagerObj virtualObj = new VMManagerObj();
		virtualObj.setNAME_EN(NAME_EH);
		virtualObj.setNAME_ZH(NAME_ZH);
		virtualObj.setIP(vObj.getIP());
		virtualObj.setTYPE("3");
		virtualObj.setVH_HOST(String.valueOf(ID));
		virtualObj.setCPU(CPU);
		virtualObj.setMEMORY(MEMORY);
		String id = vmManagerDao.getIdSequence();
		virtualObj.setID(Integer.parseInt(id));
		int rest = vmManagerDao.insertByObj(virtualObj);
		// 插入主机信息
		TbBusiHostObj tbHostObj = new TbBusiHostObj();
		tbHostObj.setIP(ip);
		tbHostObj.setSTATUS("1");
		tbHostObj.setHOSTNAME(NAME_EH);
		int ret1 = tbBusiHostObjDao.insertByObj(tbHostObj);
		TbBusiHostObj tbBusiObj = tbBusiHostObjDao.queryByObj(tbHostObj);
		// 插入主机配置信息
		TbBusiHostConfigObj tbHostCinfigObj = new TbBusiHostConfigObj();
		tbHostCinfigObj.setHOSTID(tbBusiObj.getID());
		tbHostCinfigObj.setHOSTUSERNAME(hostUsername);
		tbHostCinfigObj
				.setHOSRPWD("5db9bb02c17f90eef555060f647d9656b977926b44de0c05");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm24:ss");
		tbHostCinfigObj.setUPDATETTIME(sdf.format(new Date()));
		tbHostCinfigObj.setTYPE(0);
		int ret2 = tbBusiHostConfigDao.insertByObj(tbHostCinfigObj);
		// 插入虚拟机信息
		VMHostObj vmObj = new VMHostObj();
		vmObj.setVH_IP(ip);
		vmObj.setVH_NAME(NAME_EH);
		vmObj.setEQ_ID("1_01_001_0029_01");
		String VH_ID = createNewVhostId2(vmObj.getEQ_ID());
		vmObj.setVH_ID(VH_ID);
		vmObj.setVH_TYPE("KVM");
		int ret3 = vmHostDao.insertByVMhostObj(vmObj);

		// 向entity——tree中插入数据
		EntityTreeObj eObj = new EntityTreeObj();
		eObj.setEntityId(String.valueOf(ID));// 主机的entityId
		// eObj.setName(hostName);
		EntityTreeObj e = vmManagerDao.queryTreeNode(eObj);// 通过主机的entityId查询其id
		eObj.setParentId(e.getId());
		eObj.setName(NAME_EH);
		eObj.setType(TypeConstant.VMWARE_VM);
		eObj.setEntityId(id);
		int ret4 = vmManagerDao.insertTreeNode(eObj);

		Constant.PROCESS_LEVEL = 100;
		try {
			Thread.sleep(1100);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		return "{'result':'" + result + "'}";
	}

	/**
	 * 
	 * @Title: ftpUploadDomainInfo
	 * @Description: ftp生成文件
	 * @param
	 * @return boolean
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime Apr 27, 2012 2:00:33 PM
	 */
	public boolean ftpUploadDomainInfo(HttpServletRequest request) {
		String path = request.getSession().getServletContext().getRealPath("/");
		path = path + "yicloud/vmmanager/domain.info";
		VMManagerObj vmo = new VMManagerObj();
		vmo.setTYPE("3");
		vmo.setVH_HOST("49");
		StringBuffer domainStr = new StringBuffer();
		List<VMManagerObj> oList = vmManagerDao.queryForListByObj(vmo);
		for (VMManagerObj obj : oList) {
			String NAME_EN = obj.getNAME_EN();
			String s = getVirtualInfo("49", NAME_EN);
			String state = "";
			if (s.indexOf("state") != -1) {
				String sta = s.substring(s.indexOf("state"));
				String[] stat = sta.split(":");
				state = stat[1];
				if ("VIR_DOMAIN_RUNNING".equals(state)) {
					state = "run";
				} else if ("VIR_DOMAIN_PAUSED".equals(state)) {
					state = "pause";
				} else if ("VIR_DOMAIN_SHUTDOWN".equals(state)) {
					state = "shutdown";
				} else if ("VIR_DOMAIN_SHUTOFF".equals(state)) {
					state = "shutoff";
				}
				if (obj.getIP() != null && !"".equals(obj.getIP())) {
					domainStr.append(obj.getNAME_EN()).append(" ")
							.append(state).append(" ").append(obj.getIP())
							.append(" ").append("ssh").append(" ").append("22")
							.append("\n");
				}
			}
		}
		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new FileWriter(new File(path)));
			writer.write(domainStr.toString());
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	/**
	 * 
	 * @Title: getConnectMsg
	 * @Description: 查询宿主机名称、freeMem、maxVCpu信息
	 * @param
	 * @return String
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime May 9, 2012 3:57:12 PM
	 */
	public String getConnectMsg(int hostId) {
		VMManagerObj dObj = new VMManagerObj();
		dObj.setID(hostId);
		dObj = vmManagerDao.queryByObj(dObj);
		String url = "/kvm/connect/getMsg/" + dObj.getIP() + "/" + "kvm" + "/";
		String result = InvokeUtil.invoke(url);
		return result;
	}

	/**
	 * 
	 * @Title: putVirtualStat
	 * @Description: 修改虚拟机状态(启动、停止、重启等)--删除虚拟机待测试修改
	 * @param
	 * @return String
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime Apr 21, 2012 3:27:11 PM
	 */
	public String putVirtualStat(int hostId, String NAME_EN, String stat) {
		VMManagerObj dObj = new VMManagerObj();
		dObj.setID(hostId);
		dObj = vmManagerDao.queryByObj(dObj);
		String url = "";
		String result = "";
		if ("destroy".equals(stat)) {
			// 检验虚拟机是否已经关闭
			String statUrl = "/kvm/domain/getMsg/" + dObj.getIP() + "/"
					+ NAME_EN + "/";
			String res = "";
			String shutdownResult = "";
			res = InvokeUtil.invoke(statUrl);
			if (!res.contains("VIR_DOMAIN_SHUTOFF")) {
				// 先关闭虚拟机
				url = "/kvm/domain/putstat/" + dObj.getIP() + "/" + NAME_EN
						+ "/" + "shutdown" + "/";
				shutdownResult = InvokeUtil.invoke(url);
				long now = new Date().getTime();
				while (((new Date().getTime()) - now) < 3 * 60 * 1000) {
					res = InvokeUtil.invoke(statUrl);
					if (res.contains("VIR_DOMAIN_SHUTOFF")) {
						break;
					}
					try {
						Thread.sleep(10 * 1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				if (res.contains("VIR_DOMAIN_SHUTOFF")) {
					url = "/kvm/domain/putstat/" + dObj.getIP() + "/" + NAME_EN
							+ "/" + "undefine" + "/";
					result = InvokeUtil.invoke(url);
				}
			} else
			// 若关闭虚拟机成功，则将虚拟机消除定义
			{
				url = "/kvm/domain/putstat/" + dObj.getIP() + "/" + NAME_EN
						+ "/" + "undefine" + "/";
				result = InvokeUtil.invoke(url);
			}
			// 如果虚拟机消除定义成功，则删除虚拟机镜像
			if ("null".equals(result)) {
				url = "/shell/command/exec/" + dObj.getIP()
						+ "/root/111111/22/60000/image-delete/[image:"
						+ NAME_EN + ".img]/";
				String delResult = InvokeUtil.invoke(url);
				/** 查询虚拟机IP，若删除成功，则释放其IP */
				VMManagerObj vObj = new VMManagerObj();
				vObj.setVH_HOST(String.valueOf(hostId));
				vObj.setNAME_EN(NAME_EN);
				vObj = vmManagerDao.queryByObj(vObj);
				String ip = vObj.getIP();
				TbCloud2IpInfoObj ipObj = new TbCloud2IpInfoObj();
				ipObj.setISUSED("0");
				ipObj.setIPADDRESS(ip);
				// 更新IP使用状态
				tbIpDao.updateIPStat(ipObj);
				// 删除Device虚拟机
				vmManagerDao.deleteByName_En(vObj);
				// 虚拟机IP
				// 删除虚拟机信息
				VMHostObj vmObj = new VMHostObj();
				vmObj.setVH_IP(ip);
				int ret3 = vmHostDao.deleteVhostByObj(vmObj);
				// 删除主机配置信息
				TbBusiHostObj tbHostObj = new TbBusiHostObj();
				tbHostObj.setIP(ip);
				TbBusiHostObj tbBusiObj = tbBusiHostObjDao
						.queryByObj(tbHostObj);
				TbBusiHostConfigObj tbHostCinfigObj = new TbBusiHostConfigObj();
				tbHostCinfigObj.setHOSTID(tbBusiObj.getID());
				tbHostCinfigObj = tbBusiHostConfigDao
						.queryByObj(tbHostCinfigObj);
				int ret2 = tbBusiHostConfigDao.deleteByObj(tbHostCinfigObj);
				// 删除主机信息
				int ret1 = tbBusiHostObjDao.deleteByObj(tbBusiObj);
			}
		} else {
			url = "/kvm/domain/putstat/" + dObj.getIP() + "/" + NAME_EN + "/"
					+ stat + "/";
			result = InvokeUtil.invoke(url);
		}
		return result;
	}

	/**
	 * 
	 * @Title: putVirtualSetting
	 * @Description: 修改虚拟机信息(内存、CPU等)
	 * @param
	 * @return String
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime Apr 21, 2012 3:31:42 PM
	 */
	public String putVirtualSetting(int hostId, int virtualId, VMManagerObj vObj) {
		return null;
	}

	/**
	 * @Title:查询设备主机数据结果集
	 * @Copyright: Copyright (c) 20120413
	 * @Company: si-tech
	 * @author lzh
	 * @version 1.0
	 */
	public List queryForListByObj(VMManagerObj obj) {
		return vmManagerDao.queryForListByObj(obj);
	}

	/**
	 * @Title:查询一个虚拟映像
	 * @Copyright: Copyright (c) 20120413
	 * @Company: si-tech
	 * @author lzh
	 * @version 1.0
	 */
	public TbCloud2ImageInfoObj queryByImageObj(TbCloud2ImageInfoObj obj) {
		return tbImageInfoDao.queryByObj(obj);
	}

	/**
	 * 
	 * @Title: getVirtualInfo
	 * @Description: 获取虚拟机信息
	 * @param 主机Id
	 *            ，虚拟机英文名称（唯一标识）
	 * @return ActionForward
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Apr 24, 2012 9:05:59 AM
	 */
	public String getVirtualInfo(String hostId, String NAME_EN) {
		VMManagerObj dObj = new VMManagerObj();
		dObj.setID(Integer.parseInt(hostId));
		dObj = vmManagerDao.queryByObj(dObj);
		String Ip = dObj.getIP();
		String url = "/kvm/domain/getMsg/" + Ip + "/" + NAME_EN + "/";
		String result = InvokeUtil.invoke(url);
		return result;
	}

	/**
	 * 
	 * @Title: getVirtualInfo
	 * @Description: 查询一条信息（从库中查）
	 * @param
	 * @return ActionForward
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Apr 24, 2012 9:05:59 AM
	 */
	public VMManagerObj queryByObj(VMManagerObj obj) {
		return vmManagerDao.queryByObj(obj);
	}

	/**
	 * @Title:添加虚拟机(向数据库中)
	 * @Copyright: Copyright (c) 20120416
	 * @Company: si-tech
	 * @author lzh
	 * @version 1.0
	 */
	public int insertByObj(VMManagerObj obj) {
		return vmManagerDao.insertByObj(obj);
	}

	/**
	 * 
	 * @Title: deleteByObj
	 * @Description: 删除主机
	 * @param
	 * @return int
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Jul 18, 2012 10:24:14 AM
	 */
	public int deleteByObj(VMManagerObj obj) {
		return vmManagerDao.deleteByObj(obj);

	}

	/**
	 * 
	 * @Title: createNewVhostId2
	 * @Description: 创建虚拟机ID
	 * @param
	 * @return String
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime Apr 28, 2012 3:24:59 PM
	 */
	public String createNewVhostId2(String eqId) {
		String vhostId = null;
		if (null != eqId && !"".equals(eqId)) {
			int seq = vmHostDao.queryVhostIdSequence();
			NumberFormat formatter = NumberFormat.getNumberInstance(); // 设置数据格式
			formatter.setMinimumIntegerDigits(3); // 设置最小长度
			formatter.setMaximumIntegerDigits(3); // 设置最大长度
			vhostId = eqId + "_" + formatter.format(seq);
		}
		return vhostId;
	}

	/**
	 * 
	 * @Title: adjustKVMVirtualInfo
	 * @Description: 调整虚拟机
	 * @param
	 * @return String
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime May 16, 2012 2:23:00 PM
	 */
	public String adjustKVMVirtualInfo(VMManagerObj obj) {
		String url = "";
		String result = "";
		if (obj.getMEMORY() != null) {
			url = "/kvm/domain/put/" + obj.getIP() + "/" + obj.getNAME_EN()
					+ "/" + "[MEM:" + obj.getMEMORY() + "]/";
			result = InvokeUtil.invoke(url);
		}
		if (!"null".equals(result)) {
			return "{'error':'Mem Adjust Error'}";
		}
		if (obj.getCPU() != null) {
			url = "/kvm/domain/put/" + obj.getIP() + "/" + obj.getNAME_EN()
					+ "/" + "[VCPU:" + obj.getCPU() + "]/";
			result = InvokeUtil.invoke(url);
		}
		if (!"null".equals(result)) {
			return "{'error':'CPU Adjust Error'}";
		}
		return null;
	}

	/**
	 * 
	 * @Title: queryHealthStateList
	 * @Description: 查询主机或虚拟机健康状态信息
	 * @param
	 * @return List
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime May 21, 2012 10:34:05 AM
	 */
	public List queryHealthStateList(TbYicloudDeviceHealthObj obj) {
		return vmManagerDao.queryHealthStateList(obj);
	}

	/**
	 * 
	 * @Title: queryDefaultConfig
	 * @Description: 查询各个操作系统的默认配置值
	 * @param
	 * @return TbYicloudOsTypeObj
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Jul 10, 2012 1:14:31 PM
	 */
	public TbYicloudOsTypeObj queryDefaultConfig(TbYicloudOsTypeObj obj) {
		return vmManagerDao.queryDefaultConfig(obj);
	}

	/**
	 * 
	 * @Title: queryForTree
	 * @Description: 查询数据中心，集群，主机，虚拟机等生成树
	 * @param
	 * @return EntityTreeObj
	 * @throws
	 * @author duangh
	 * @version 1.0
	 */
	public List<EntityTreeObj> queryForTree(EntityTreeObj obj) {
		return vmManagerDao.queryForTree(obj);
	}

	/**
	 * 
	 * @Title: insertTreeNode
	 * @Description: 向树中插入节点
	 * @param
	 * @return int
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Jul 17, 2012 11:07:09 AM
	 */
	public int insertTreeNode(EntityTreeObj obj) {
		return vmManagerDao.insertTreeNode(obj);
	}

	/**
	 * 
	 * @Title: updateTreeNode
	 * @Description: 修改树节点信息
	 * @param
	 * @return int
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Jul 17, 2012 11:21:24 AM
	 */
	public int updateTreeNode(EntityTreeObj obj) {
		return vmManagerDao.updateTreeNode(obj);
	}

	/**
	 * 
	 * @Title: updateTreeNode
	 * @Description: 删除树节点信息
	 * @param
	 * @return int
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Jul 17, 2012 11:21:24 AM
	 */
	public int delTreeNode(EntityTreeObj obj) {
		return vmManagerDao.delTreeNode(obj);
	}

	/**
	 * 
	 * @Title: delSubNode
	 * @Description: 删除某一节点对应的子节点
	 * @param
	 * @return int
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Jul 18, 2012 10:28:29 AM
	 */
	public int delSubNode(EntityTreeObj obj) {
		return vmManagerDao.delSubNode(obj);
	}

	/**
	 * 
	 * @Title: queryTreeNode
	 * @Description: 查询树节点的信息
	 * @param
	 * @return EntityTreeObj
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Jul 17, 2012 4:31:28 PM
	 */
	public EntityTreeObj queryTreeNode(EntityTreeObj obj) {
		return vmManagerDao.queryTreeNode(obj);
	}

	/**
	 * 
	 * @Title: getIdSequence
	 * @Description: 查询device表的ID
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Jul 18, 2012 9:44:10 AM
	 */
	public String getIdSequence() {
		return vmManagerDao.getIdSequence();
	}

	/**
	 * 
	 * @Title: queryHostCount
	 * @Description: 查询主机的个数
	 * @param
	 * @return int
	 * @throws
	 * @author duangh
	 * @version 1.0
	 * @createtime Jul 17, 2012 4:31:28 PM
	 */
	public int queryHostCount(EntityConObj obj) {
		return vmManagerDao.queryHostCount(obj);
	}

	/**
	 * 
	 * @Title: queryVmCount
	 * @Description: 查询虚拟机的个数
	 * @param
	 * @return int
	 * @throws
	 * @author duangh
	 * @version 1.0
	 * @createtime Jul 17, 2012 4:31:28 PM
	 */
	public int queryVmCount(EntityConObj obj) {
		return vmManagerDao.queryVmCount(obj);
	}

	/**
	 * 
	 * @Title: queryClusterCount
	 * @Description: 查询集群的个数
	 * @param
	 * @return int
	 * @throws
	 * @author duangh
	 * @version 1.0
	 * @createtime Jul 17, 2012 4:31:28 PM
	 */
	public int queryClusterCount(EntityConObj obj) {
		return vmManagerDao.queryClusterCount(obj);
	}

	/**
	 * 
	 * @Title: queryNetOrStoreCount
	 * @Description: 查询网络或存储的个数
	 * @param
	 * @return int
	 * @throws
	 * @author duangh
	 * @version 1.0
	 * @createtime Jul 17, 2012 4:31:28 PM
	 */
	public int queryNetOrStoreCount(EntityConObj obj) {
		return vmManagerDao.queryNetOrStoreCount(obj);
	}

	/**
	 * 
	 * @Title: queryEntityInfo
	 * @Description: 查询主机，虚拟机等实体信息
	 * @param
	 * @return int
	 * @throws
	 * @author duangh
	 * @version 1.0
	 * @createtime Jul 17, 2012 4:31:28 PM
	 */
	public List<EntityConObj> queryEntityInfo(EntityConObj obj) {

		// return vmManagerDao.queryEntityInfo(obj);
		List<EntityConObj> lst = vmManagerDao.queryEntityInfo(obj);
		for (EntityConObj conObj : lst) {
			Class c = null;
			try {
				c = Class
						.forName("com.sitech.basd.yicloud.domain.vmman.CollObj");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// 调用webservice接口
			String param = "/10-10-24:cs_100/kpivalue.json?kpi=CM-00-01-001-35&kpi=CM-00-01-001-32&kpi=PM-00-01-001-09&kpi=PM-00-01-001-10&kpi=CM-00-01-001-36";
			String json = HttpClientUtil.get(param);
			if (!json.equals("error")) {
				// 得到返回的map
				LinkedHashMap<String, ArrayList<String>> map = (LinkedHashMap) JSONUtil
						.fromJSON(json, LinkedHashMap.class);

				// 遍历map,得到其中的value,其中value是数组
				Collection<ArrayList<String>> coll = map.values();
				for (Iterator<ArrayList<String>> it = coll.iterator(); it
						.hasNext();) {
					List<CollObj> list = JSONArray.toList(
							JSONArray.fromObject(it.next()), c);
					if (list != null && list.size() == 1) {
						CollObj cObj = list.get(0);
						if (cObj.getKpiId().equals("CM-00-01-001-35")) {// 置备的空间
							conObj.setProvisionStore(cObj.getKpiValue());
						} else if (cObj.getKpiId().equals("CM-00-01-001-32")) {// 已用空间
							conObj.setStore(cObj.getKpiValue());
						} else if (cObj.getKpiId().equals("PM-00-01-001-09")) {// 已使用cpu
							conObj.setCpu(cObj.getKpiValue());
						} else if (cObj.getKpiId().equals("PM-00-01-001-10")) {// 已消耗内存
							conObj.setMem(cObj.getKpiValue());
						} else if (cObj.getKpiId().equals("CM-00-01-001-36")) {// 活动客户机内存
							conObj.setClientMem(cObj.getKpiValue());
						}

					}
				}
			}
		}
		return lst;
	}

	@Override
	public int updateByObj(VMManagerObj obj) {
		return 0;
	}
}
