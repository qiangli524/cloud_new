package com.sitech.basd.yicloud.dao.kvm;

import java.util.List;

import com.sitech.basd.yicloud.domain.kvm.TbYicloudDeviceHealthObj;
import com.sitech.basd.yicloud.domain.kvm.TbYicloudOsTypeObj;
import com.sitech.basd.yicloud.domain.kvm.VMManagerObj;

/**
 * 
 * <p>
 * Title: KvmManDao
 * </p>
 * <p>
 * Description: Kvm管理Dao
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
 * @createtime Jul 25, 2012 9:20:35 AM
 * 
 */
public interface KvmManDao {
	/**
	 * @Title:查询设备主机数据结果集
	 * @Copyright: Copyright (c) 20120413
	 * @Company: si-tech
	 * @author huojla
	 * @version 1.0
	 */
	public List queryForListByObj(VMManagerObj obj);

	/**
	 * @Title:添加设备主机
	 * @Copyright: Copyright (c) 20120416
	 * @Company: si-tech
	 * @author huojla
	 * @version 1.0
	 */
	public int insertByObj(VMManagerObj obj);

	/**
	 * @Title:查询一条信息
	 * @Copyright: Copyright (c) 20120416
	 * @Company: si-tech
	 * @author huojla
	 * @version 1.0
	 */
	public VMManagerObj queryByObj(VMManagerObj obj);

	/**
	 * @Title:修改设备主机信息
	 * @Copyright: Copyright (c) 20120417
	 * @Company: si-tech
	 * @author huojla
	 * @version 1.0
	 */
	public int updateByObj(VMManagerObj obj);

	/**
	 * @Title:根据ID删除设备主机信息
	 * @Copyright: Copyright (c) 20120417
	 * @Company: si-tech
	 * @author huojla
	 * @version 1.0
	 */
	public int deleteByObj(VMManagerObj obj);

	/**
	 * @Title:根据英文名称删除设备主机信息
	 * @Copyright: Copyright (c) 20120417
	 * @Company: si-tech
	 * @author huojla
	 * @version 1.0
	 */
	public int deleteByName_En(VMManagerObj obj);

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
	 * @Title: insertByOsType
	 * @Description: 增加一条信息
	 * @param
	 * @return int
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Sep 10, 2012 1:36:46 PM
	 */
	public int insertByOsType(TbYicloudOsTypeObj obj);

}
