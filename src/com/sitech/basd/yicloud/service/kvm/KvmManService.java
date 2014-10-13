package com.sitech.basd.yicloud.service.kvm;

import java.util.List;

import com.sitech.basd.sxcloud.cloud.domain.image.TbCloud2ImageInfoObj;
import com.sitech.basd.yicloud.domain.kvm.TbYicloudDeviceHealthObj;
import com.sitech.basd.yicloud.domain.kvm.TbYicloudOsTypeObj;
import com.sitech.basd.yicloud.domain.kvm.VMManagerObj;

/**
 * 
 * <p>
 * Title: KvmManService
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
public interface KvmManService {
	/**
	 * @Title:创建虚拟机
	 * @Copyright: Copyright (c) 20120413
	 * @Company: si-tech
	 * @author lzh
	 * @version 1.0
	 */
	public String createVirtual(int ID, String IM_ID, VMManagerObj vObj);

	/**
	 * @Title:创建VMware虚拟机
	 * @Copyright: Copyright (c) 20120413
	 * @Company: si-tech
	 * @author lzh
	 * @version 1.0
	 */
	public String createVMwareVirtual(int ID, VMManagerObj vObj);

	/**
	 * 
	 * @Title: putVirtualStat
	 * @Description: 修改虚拟机状态(启动、停止、重启等)
	 * @param
	 * @return String
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime Apr 21, 2012 3:27:11 PM
	 */
	public String putVirtualStat(int hostId, String NAME_EN, String stat);

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
	 * @Title: updateByObj
	 * @Description: 更新一条记录
	 * @param
	 * @return int
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Aug 2, 2012 2:12:04 PM
	 */
	public int updateByObj(VMManagerObj obj);

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
	public String adjustKVMVirtualInfo(VMManagerObj obj);

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
	 * @Title: saveConCluster
	 * @Description: 保存配置集群
	 * @param
	 * @return String
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime 2012-9-12
	 */
	public void saveConCluster();
}
