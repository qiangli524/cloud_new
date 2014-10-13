package com.sitech.basd.yicloud.service.xen;

import java.io.UnsupportedEncodingException;
import java.util.List;

import net.sf.json.JSONObject;

import com.sitech.basd.yicloud.domain.xentree.XenEntityTreeObj;
import com.sitech.basd.yicloud.domain.xen.Host;
import com.sitech.basd.yicloud.domain.xenconn.ConnectionInfo;

public interface XenManService {
	/**
	 * 
	 * @Title: createNakeMac
	 * @Description: 创建裸机
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Sep 9, 2012 10:02:38 AM
	 */
	public String createNakeMac(String osId, String vmName, Integer cpu,
			String storage, Long mem, XenEntityTreeObj e, String clusterId,
			String store_uuid, String net, String pool_uuid, String host_uuid ,String vidUuid);

	/**
	 * 
	 * @Title: listVMInfo
	 * @Description: 查询虚拟机详细信息
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Sep 9, 2012 10:35:42 AM
	 */
	public String listVMInfo(String VH_UUID, String pool_uuid);

	/**
	 * 
	 * @Title: adjustVM
	 * @Description: 调整虚拟机
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Sep 12, 2012 11:04:11 AM
	 */
	public String adjustVM(String cpu, String cpuMax, String VH_UUID,
			String pool_uuid, String host_uuid, long dynamicMax,
			long dynamicMin, long staticMax, long staticMin, long storage);

	/**
	 * 
	 * @Title: putVMState
	 * @Description: 修改虚拟机的状态
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Sep 10, 2012 8:49:23 AM
	 */
	public String putVMState(String VH_UUID, String state, XenEntityTreeObj e,
			String pool_uuid,String masterHost_uuid, String host_uuid);

	/**
	 * 
	 * @Title: listHostInfo
	 * @Description: 查询主机详细信息
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Sep 10, 2012 9:00:55 AM
	 */
	public String listHostInfo(String hostUuid, String pool_uuid);

	/**
	 * 
	 * @Title: createVMSnapShot
	 * @Description: 创建虚拟机快照
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Sep 10, 2012 9:12:30 AM
	 */
	public String createVMSnapShot(String VH_UUID, String shot_name,
			String desc, String pool_uuid, String host_uuid);

	/**
	 * 
	 * @Title: createTemByVm
	 * @Description: 通过虚拟机创建模板
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Sep 12, 2012 3:14:50 PM
	 */
	public String createTemByVm(String temName, String vh_uuid, int parent_id,
			String pool_uuid);

	/**
	 * 
	 * @Title: cloneVMByTem
	 * @Description: 通过模板创建虚拟机（带操作系统）
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Sep 10, 2012 9:23:06 AM
	 */
	public String createVMByTem(String tem_uuid, String cpu, String currentcpu,
			String staticMin, String dynamicMin, String dynamicMax,
			String staticMax, XenEntityTreeObj e,
			String clusterId, String pool_uuid, String host_uuid, String ipAddress);
	
	/**
	 * 
	 * @Title: cloneVMByTem
	 * @Description: 通过模板快速创建虚拟机（带操作系统）
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Sep 10, 2012 9:23:06 AM
	 */
	public String queryCreateVMByTem(String tem_uuid,XenEntityTreeObj e,String clusterId, String pool_uuid, String host_uuid);

	/**
	 * 
	 * @Title: cloneVMByVM
	 * @Description: 通过虚拟机克隆虚拟机
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @throws UnsupportedEncodingException
	 * @createtime Sep 13, 2012 2:44:14 PM
	 */
	public String cloneVMByVM(String vmUuid, XenEntityTreeObj e, String clusterId,
			String pool_uuid, String host_uuid, String store_uuid, String desc);

	/**
	 * 
	 * @Title: snapshotManager
	 * @Description: 查询某一虚拟机的快照列表
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Sep 14, 2012 9:14:11 AM
	 */
	public String snapshotManager(String vh_uuid, String pool_uuid,
			String host_uuid);

	/**
	 * 
	 * @Title: delSnapshot
	 * @Description: 删除虚拟机快照
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Sep 17, 2012 10:38:10 AM
	 */
	public String delSnapshot(String uuid, String pool_uuid, String host_uuid);

	/**
	 * 
	 * @Title: recoverSnapShot
	 * @Description: 恢复虚拟机快照
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Sep 17, 2012 10:48:16 AM
	 */
	public String recoverSnapShot(String uuid, String pool_uuid,
			String host_uuid);

	/**
	 * 
	 * @Title: listDefaultTem
	 * @Description: 查询所有默认模板
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Sep 10, 2012 9:31:20 AM
	 */
	public String listDefaultTem(String pool_uuid);

	/**
	 * 
	 * @Title: listAllVMByHost
	 * @Description: 获取指定主机下的所有虚拟机
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Sep 12, 2012 2:18:34 PM
	 */
	public String listAllVMByHost(int DataCenterId);

	/**
	 * 
	 * @Title: listTeminfo
	 * @Description: 获取模板的详细信息
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Sep 19, 2012 11:16:35 AM
	 */
	public String listTeminfo(String uuid, String pool_uuid, String host_uuid);

//	/**
//	 * 
//	 * @Title: listAllCluster
//	 * @Description: 查询所有集群
//	 * @param
//	 * @return void
//	 * @throws
//	 * @author taoxue
//	 * @version 1.0
//	 * @createtime Sep 19, 2012 1:20:39 PM
//	 */
//	public String listAllHostByCluster(int dataCenterId, String pool_uuid,
//			String ip);

	/**
	 * 
	 * @Title: saveCluster
	 * @Description: 更新集群信息
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Oct 10, 2012 10:43:29 AM
	 */
	public String updateByObj(String clusterName, String clusterType,
			String entity_id);

	/**
	 * 
	 * @Title: conXenCluster
	 * @Description: 链接至xen集群
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Oct 12, 2012 10:05:58 AM
	 */
	public String conXenCluster(String name, String username, String password,
			int DataCenterId);

	/**
	 * 
	 * @Title: saveHost
	 * @Description: 保存主机
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Oct 12, 2012 2:16:09 PM
	 */
	public String saveHost(String parent_id, String username, String password,
			String ip, String pool_uuid);

	/**
	 * 
	 * @Title: delHost
	 * @Description: 删除主机
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Oct 16, 2012 3:01:51 PM
	 */
	public String delHost(String ID, String entity_id, String pool_uuid,
			String host_uuid);

	/**
	 * 
	 * @Title: saveCluster
	 * @Description: 保存集群信息
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Oct 10, 2012 10:43:29 AM
	 */
	public String saveCluster(String clusterName, String clusterType,
			String parent_id, String hostName, String username, String password);

	/**
	 * 
	 * @Title: queryByObj
	 * @Description: 查询一条链接信息
	 * @param
	 * @return ConnectionInfo
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Oct 17, 2012 11:08:08 AM
	 */
	public ConnectionInfo queryByObj(ConnectionInfo obj);

	/**
	 * 
	 * @Title: connectCluster
	 * @Description: 连接xen集群
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Oct 17, 2012 10:45:11 AM
	 */
	public String connectCluster(String ip, String username, String password,String poolUuid);

	/**
	 * 
	 * @Title: migrate
	 * @Description: 迁移虚拟机
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Oct 22, 2012 4:32:10 PM
	 */
	public String migrate(int host_id);

	/**
	 * 
	 * @Title: getSRList
	 * @Description: 获取SR列表
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Oct 25, 2012 4:32:53 PM
	 */
	public List getSRList(String pool_uuid,  String path);

	/**
	 * 
	 * @Title: createSR
	 * @Description: 创建SR
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Oct 26, 2012 2:22:09 PM
	 */
	public String createSR(String pool_uuid, String host_uuid, String path,
			String name, String desc,  String host_id,
			String storageId,String createStyle,boolean share,String store_uuid,String bind_uuid,String iso_type,String username,String password);

	/**
	 * 
	 * @Title: operateStore
	 * @Description: 对共享存储进行操作
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Oct 30, 2012 11:47:27 AM
	 */
	public String operateStore(String pool_uuid, String host_uuid,
			String sr_uuid, String oper,String name);

	/**
	 * 
	 * @Title: delTem
	 * @Description: 删除模板
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Nov 2, 2012 10:52:52 AM
	 */
	public String delTem(String pool_uuid, String host_uuid, String vm_uuid,
			String id, String entity_id);

	/**
	 * 
	 * @Title: listAbility
	 * @Description: 虚拟机性能信息
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Nov 15, 2012 2:04:37 PM
	 */
	public String listAbility(String pool_uuid,String host_uuid, String[] vmUuids);
	

	/**
	 * 分析HOST和VM之间的关系，并返回list,主机的uui等于pooluuid的时候为虚拟机挂在池的下面
	 * @param hosts
	 * @param vms
	 * @return
	 */
	public List<Host> analysisHostAndVm(List<JSONObject> hosts,List<JSONObject> vms,List<JSONObject> srs,String poolUuid);
	
	/**
	 * 为虚拟机的光驱加载iso镜像文件
	 * @param vdiUuid
	 * @param poolUuid
	 * @param vmUuid
	 * @return
	 */
	public String loadVdiForDVD(String vdiUuid,String poolUuid,String vmUuid);
	
	/**
	 * 为虚拟机的光绪加载XenServer tools工具
	 * @param poolUuid
	 * @param vmUuid
	 * @return
	 */
	public String loadToolForDVD(String poolUuid,String vmUuid);
	
	/**
	 * 为光驱移除加载的镜像
	 * @param poolUuid
	 * @return
	 */
	public String removeVdiForDVD(String poolUuid,String vmUuid);
	
	/**
	 * 删除指定的虚拟磁盘镜像文件
	 * @param poolUuid
	 * @param vdiUuid
	 * @return
	 */
	public String deleteVdiVm(String poolUuid,String vdiUuid);
	
	/**
	 * 修改指定的虚拟磁盘镜像文件的大小
	 * @param poolUuid
	 * @param vdiUuid
	 * @param vdiSize
	 * @return
	 */
	
	public String editVdiForVm(String poolUuid,String vdiUuid,String vdiSize);
	
	/**
	 * 
	 * @Title: queryVMCount
	 * @Description: 查询集群下的所有虚拟机
	 * @param
	 * @return int
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime 2013-1-22 下午02:42:10
	 */
	public List<XenEntityTreeObj> queryXenVMCount(XenEntityTreeObj obj);
	
	/**
	 * 检查集群是否存在
	 * @param name
	 * @return
	 */
	public boolean checkConXenCluster(String name);
	
	/**
	 * 
	 * @Title: getOtherHostPifUuids
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param
	 * @return String
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime May 15, 2013 11:39:06 AM
	 */
	public String getOtherHostPifUuids(String poolUuid,String hostUuid,String nicName);

}
