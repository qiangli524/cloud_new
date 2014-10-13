package com.sitech.basd.yicloud.service.vmman;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.sitech.basd.sxcloud.cloud.domain.image.TbCloud2ImageInfoObj;
import com.sitech.basd.yicloud.domain.vmman.EntityConObj;
import com.sitech.basd.yicloud.domain.vmman.EntityTreeObj;
import com.sitech.basd.yicloud.domain.vmman.TbYicloudDeviceHealthObj;
import com.sitech.basd.yicloud.domain.vmman.TbYicloudOsTypeObj;
import com.sitech.basd.yicloud.domain.vmman.VMManagerObj;

/**
 * 
 * <p>
 * Title: VMManagerService
 * </p>
 * <p>
 * Description: 虚拟机管理Service
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
 * @createtime Apr 18, 2012 11:08:32 AM
 * 
 */
public interface VMManagerService {
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
	public String putVirtualSetting(int hostId, int virtualId, VMManagerObj vObj);

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
	 * @Description:更新主机信息
	 * @param
	 * @return int
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Jul 24, 2012 2:48:10 PM
	 */
	public int updateByObj(VMManagerObj obj);

	/**
	 * 
	 * @Title: ftpUploadDomainInfo
	 * @Description: 文件上传
	 * @param
	 * @return boolean
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime Apr 27, 2012 2:00:58 PM
	 */
	public boolean ftpUploadDomainInfo(HttpServletRequest request);

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
	 * @Title: queryForTree
	 * @Description: 查询数据中心，集群，主机，虚拟机等生成树
	 * @param
	 * @return EntityTreeObj
	 * @throws
	 * @author duangh
	 * @version 1.0
	 */
	public List<EntityTreeObj> queryForTree(EntityTreeObj obj);

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
	public int insertTreeNode(EntityTreeObj obj);

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
	public int updateTreeNode(EntityTreeObj obj);

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
	public int delTreeNode(EntityTreeObj obj);

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
	public int delSubNode(EntityTreeObj obj);

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
	public EntityTreeObj queryTreeNode(EntityTreeObj obj);

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
	 * @Title: queryHostCount
	 * @Description: 查询主机的个数
	 * @param
	 * @return int
	 * @throws
	 * @author duangh
	 * @version 1.0
	 * @createtime Jul 17, 2012 4:31:28 PM
	 */
	public int queryHostCount(EntityConObj obj);

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
	public int queryVmCount(EntityConObj obj);

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
	public int queryClusterCount(EntityConObj obj);

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
	public int queryNetOrStoreCount(EntityConObj obj);

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
	public List<EntityConObj> queryEntityInfo(EntityConObj obj);
}
