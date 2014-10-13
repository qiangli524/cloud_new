package com.sitech.basd.yicloud.service.vmware;

import java.util.List;
import java.util.Map;

import com.sitech.basd.sxcloud.cloud.domain.image.TbCloud2ImageInfoObj;
import com.sitech.basd.yicloud.domain.datastore.DataStoreInfo;
import com.sitech.basd.yicloud.domain.entitytree.EntityTreeObj;
import com.sitech.basd.yicloud.domain.kvm.TbYicloudDeviceHealthObj;
import com.sitech.basd.yicloud.domain.kvm.TbYicloudOsTypeObj;
import com.sitech.basd.yicloud.domain.kvm.VMManagerObj;
import com.sitech.basd.yicloud.web.vmware.form.VmwManForm;

/**
 * 
 * <p>
 * Title: VMWManService
 * </p>
 * <p>
 * Description: KVM管理Service接口
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
 * @createtime Jul 25, 2012 9:04:20 AM
 * 
 */
public interface VmwManService {

	/**
	 * @Title:创建VMware虚拟机
	 * @Copyright: Copyright (c) 20120413
	 * @Company: si-tech
	 * @author lzh
	 * @version 1.0
	 */
	public String createVMwareVirtual(String ID, VMManagerObj vObj);

	/**
	 * 
	 * @Title: putVirtualStat
	 * @Description: 修改vmware虚拟机状态(启动、停止、重启等)
	 * @param
	 * @return String
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime Apr 21, 2012 3:27:11 PM
	 */
	public String putVirtualStat(int id, String op, String ENTITY_ID);

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
	public String getConnectMsg(int hostId);

	/**
	 * @Title:查询设备主机数据结果集
	 * @Copyright: Copyright (c) 20120413
	 * @Company: si-tech
	 * @author lzh
	 * @version 1.0
	 */
	public List queryForListByObj(VMManagerObj obj);

	/**
	 * @Title:查询一个虚拟映像
	 * @Copyright: Copyright (c) 20120413
	 * @Company: si-tech
	 * @author lzh
	 * @version 1.0
	 */
	public TbCloud2ImageInfoObj queryByImageObj(TbCloud2ImageInfoObj obj);

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
	public String getVirtualInfo(String hostId, String NAME_EN);

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
	public VMManagerObj queryByObj(VMManagerObj obj);

	/**
	 * @Title:添加虚拟机(向数据库中)
	 * @Copyright: Copyright (c) 20120416
	 * @Company: si-tech
	 * @author lzh
	 * @version 1.0
	 */
	public int insertByObj(VMManagerObj obj);

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
	public int deleteByObj(VMManagerObj obj);

	/**
	 * 
	 * @Title: adjustVmwVirtualInfo
	 * @Description: 调整vmware虚拟机
	 * @param
	 * @return String
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime May 16, 2012 2:23:00 PM
	 */
	public String adjustVmwVirtualInfo(VMManagerObj obj);

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
	public List queryHealthStateList(TbYicloudDeviceHealthObj obj);

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
	public TbYicloudOsTypeObj queryDefaultConfig(TbYicloudOsTypeObj obj);

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
	public String getIdSequence();

	/**
	 * 
	 * @Title: queryOSList
	 * @Description: 获取操作系统列表
	 * @param
	 * @return List<TbYicloudOsTypeObj>
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime Jul 25, 2012 2:40:34 PM
	 */
	public List<TbYicloudOsTypeObj> queryOSList(TbYicloudOsTypeObj obj);

	/**
	 * 
	 * @Title: cloneVirtualMac
	 * @Description: 克隆虚拟机
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Jul 26, 2012 3:12:50 PM
	 */
	public String cloneVirtualMachine(String old_name, String new_name,
			String host_name);

	/**
	 * 
	 * @Title: getDSinfo
	 * @Description: 获取存储详细信息
	 * @param
	 * @return DataStoreInfo
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime Jul 28, 2012 3:05:16 PM
	 */
	public DataStoreInfo getDSinfo(String hostname, String dsname);

	/**
	 * 
	 * @Title: migrateVM
	 * @Description: 虚拟机迁移（在主机之间迁移）
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Jul 31, 2012 3:19:52 PM
	 */
	public String migrateVMByHost(String vmName, String newHostName);

	/**
	 * 
	 * @Title: migrateVMByDs
	 * @Description: 虚拟机迁移（在存储之间迁移）
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Aug 1, 2012 11:23:52 AM
	 */
	public String migrateVMByDs(String vmName, String dsName);

	/**
	 * @Title:创建VMware虚拟机
	 * @Copyright: Copyright (c) 20120413
	 * @Company: si-tech
	 * @author lzh
	 * @version 1.0
	 */
	public String cloneVMwareVirtual(int ID, VMManagerObj vObj);

	/**
	 * 
	 * @Title: cloneVmwarevm
	 * @Description: 克隆虚拟机
	 * @param
	 * @return String
	 * @throws
	 * @author duangh
	 * @version 1.0
	 * @createtime Jul 26, 2012 3:12:50 PM
	 */
	public String cloneVmwarevm(String vmCode,String type,VmwManForm theForm);

	/**
	 * 
	 * @Title: cloneToTem
	 * @Description: 克隆为模板
	 * @param
	 * @return String
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime Sep 18, 2012 1:35:01 PM
	 */
	public String cloneVmwareTem(String old_name, String new_name,
			String host_name, String entity_id, String parent_id, String dsname);

	/**
	 * 
	 * @Title: removeTemplet
	 * @Description: 删除vmware模板
	 * @param
	 * @return String
	 * @throws
	 * @author duangh
	 * @version 1.0
	 * @createtime Sep 18, 2012 1:35:01 PM
	 */
	public String removeTemplet(EntityTreeObj obj);

	/**
	 * 
	 * @Title: moveToDateCenter
	 * @Description: 将主机移动到数据中心
	 * @param
	 * @return String
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime Sep 18, 2012 1:35:01 PM
	 */
	public String moveToDateCenter(String dcName, String clName,
			EntityTreeObj obj);

	/**
	 * 
	 * @Title: cloneVirtualToCluster
	 * @Description: 克隆虚拟机到集群
	 * @param
	 * @return String
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime Sep 25, 2012 3:03:48 PM
	 */
	public String cloneVirtualToCluster(String vmCode, VmwManForm theForm);

	/**
	 * 
	 * @Title: cloneVMByTem
	 * @Description: 在主机节点通过模板克隆虚拟机
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime May 20, 2013 10:04:52 AM
	 */
	public String cloneVMByTem(Map map);
}
