package com.sitech.basd.yicloud.service.kvm;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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
import com.sitech.basd.yicloud.dao.entitytree.EntityTreeDao;
import com.sitech.basd.yicloud.dao.kvm.KvmManDao;
import com.sitech.basd.yicloud.dao.script.ScriptDao;
import com.sitech.basd.yicloud.domain.entitytree.EntityTreeObj;
import com.sitech.basd.yicloud.domain.kvm.TbYicloudDeviceHealthObj;
import com.sitech.basd.yicloud.domain.kvm.TbYicloudOsTypeObj;
import com.sitech.basd.yicloud.domain.kvm.VMManagerObj;
import com.sitech.basd.yicloud.domain.script.ScriptConObj;
import com.sitech.basd.yicloud.util.InvokeUtil;
import com.sitech.basd.yicloud.util.TypeConstant;

/**
 * 
 * <p>
 * Title: KvmManServiceImpl
 * </p>
 * <p>
 * Description: Kvm管理Service实现类
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
 * @createtime Jul 25, 2012 9:04:01 AM
 * 
 */
public class KvmManServiceImpl implements KvmManService {
	private TbImageInfoDao tbImageInfoDao;
	private EntityTreeDao entityTreeDao;
	private KvmManDao kvmManDao;
	private TbIpDao tbIpDao;
	private TbBusiHostObjDao tbBusiHostObjDao;
	private VMHostDao vmHostDao;
	private TbBusiHostConfigDao tbBusiHostConfigDao;
	private ScriptDao scriptDao;
	
	public void setScriptDao(ScriptDao scriptDao) {
		this.scriptDao = scriptDao;
	}

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

	public void setKvmManDao(KvmManDao kvmManDao) {
		this.kvmManDao = kvmManDao;
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
		VMManagerObj dObj = kvmManDao.queryByObj(obj);
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
		paramStr.append("[virtual-name:").append(NAME_EH).append("].[").append(
				"memory:").append(MEMORY).append("].[")
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
		String id = kvmManDao.getIdSequence();
		virtualObj.setID(Integer.parseInt(id));
		int rest = kvmManDao.insertByObj(virtualObj);
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
		vmObj.setVH_TYPE("0");
		int ret3 = vmHostDao.insertByVMhostObj(vmObj);

		// 向entity——tree中插入数据
		EntityTreeObj eObj = new EntityTreeObj();
		eObj.setEntityId(String.valueOf(ID));// 主机的entityId
		eObj.setName(hostName);
		EntityTreeObj e = entityTreeDao.queryTreeNode(eObj);// 通过主机的entityId查询其id
		eObj.setParentId(e.getId());
		eObj.setName(NAME_EH);
		eObj.setType(TypeConstant.KVM_VM);
		eObj.setEntityId(id);
		int ret4 = entityTreeDao.insertTreeNode(eObj);

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
		String id = kvmManDao.getIdSequence();
		virtualObj.setID(Integer.parseInt(id));
		int rest = kvmManDao.insertByObj(virtualObj);
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
		EntityTreeObj e = entityTreeDao.queryTreeNode(eObj);// 通过主机的entityId查询其id
		eObj.setParentId(e.getId());
		eObj.setName(NAME_EH);
		eObj.setType(TypeConstant.VMWARE_VM);
		eObj.setEntityId(id);
		int ret4 = entityTreeDao.insertTreeNode(eObj);

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
		dObj = kvmManDao.queryByObj(dObj);
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
		dObj = kvmManDao.queryByObj(dObj);
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
				vObj = kvmManDao.queryByObj(vObj);
				String ip = vObj.getIP();
				TbCloud2IpInfoObj ipObj = new TbCloud2IpInfoObj();
				ipObj.setISUSED("0");
				ipObj.setIPADDRESS(ip);
				// 更新IP使用状态
				tbIpDao.updateIPStat(ipObj);
				// 删除Device虚拟机
				kvmManDao.deleteByName_En(vObj);
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
	 * @Title: getVirtualInfo
	 * @Description: 获取虚拟机信息
	 * @param 主机Id，虚拟机英文名称（唯一标识）
	 * @return ActionForward
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Apr 24, 2012 9:05:59 AM
	 */
	public String getVirtualInfo(String hostId, String NAME_EN) {
		VMManagerObj dObj = new VMManagerObj();
		dObj.setID(Integer.parseInt(hostId));
		dObj = kvmManDao.queryByObj(dObj);
		String Ip = dObj.getIP();
		String url = "/kvm/domain/getMsg/" + Ip + "/" + NAME_EN + "/";
		String result = InvokeUtil.invoke(url);
		return result;
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
		return kvmManDao.queryHealthStateList(obj);
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
		return kvmManDao.queryDefaultConfig(obj);
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
		return kvmManDao.getIdSequence();
	}

	/**
	 * @Title:查询设备主机数据结果集
	 * @Copyright: Copyright (c) 20120413
	 * @Company: si-tech
	 * @author lzh
	 * @version 1.0
	 */
	public List queryForListByObj(VMManagerObj obj) {
		return kvmManDao.queryForListByObj(obj);
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
	 * @Description: 查询一条信息（从库中查）
	 * @param
	 * @return ActionForward
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Apr 24, 2012 9:05:59 AM
	 */
	public VMManagerObj queryByObj(VMManagerObj obj) {
		return kvmManDao.queryByObj(obj);
	}

	/**
	 * @Title:添加虚拟机(向数据库中)
	 * @Copyright: Copyright (c) 20120416
	 * @Company: si-tech
	 * @author lzh
	 * @version 1.0
	 */
	public int insertByObj(VMManagerObj obj) {
		return kvmManDao.insertByObj(obj);
	}

	/**
	 * 
	 * @Title: updateByObj
	 * @Description: 更新一条记录
	 * @param
	 * @return int
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Aug 2, 2012 2:12:04 PM
	 */
	public int updateByObj(VMManagerObj obj) {
		return kvmManDao.updateByObj(obj);
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
		return kvmManDao.deleteByObj(obj);

	}

	public void setEntityTreeDao(EntityTreeDao entityTreeDao) {
		this.entityTreeDao = entityTreeDao;
	}
	
	/**
	 * 
	 * @Title: saveConCluster
	 * @Description: 保存配置集群
	 * @param
	 * @return String
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime 2012-9-12
	 */
	public void saveConCluster(){
		
	}
}
