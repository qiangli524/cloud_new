package com.sitech.ssd.ah.nas.dao;

import java.util.List;

import com.sitech.basd.sxcloud.cloud.domain.vmhost.VMHostObj;
import com.sitech.ssd.ah.nas.domain.MountObj;
import com.sitech.ssd.ah.nas.domain.NasFileSystemObj;
import com.sitech.ssd.ah.nas.domain.VmIpObj;

/**
 * Title: NasDao Copyright: Copyright (c) 2014 Company: SI-TECH
 * 
 * @author siweichao
 * @version 1.0
 * @createtime 2014年3月25日17:57:01
 * 
 */
public interface NasFileSysDao {

	/***************** 文件系统的对应操作 **************************/
	/**
	 * @Title: insertFile
	 * @Description: nas文件系统入库
	 * @param
	 * @return
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @throws Exception
	 * @createtime 2014年3月26日9:19:51
	 */
	public void insertFile(NasFileSystemObj obj);

	/**
	 * @Title: queryFile
	 * @Description: 查询文件系统列表
	 * @param
	 * @return
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @throws Exception
	 * @createtime 2014年3月26日9:19:51
	 */
	public List<NasFileSystemObj> queryFile(NasFileSystemObj obj);

	/**
	 * @Title: updateFileByObj
	 * @Description: 更新文件系统的IP地址
	 * @param
	 * @return
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @throws Exception
	 * @createtime 2014年3月26日9:19:51
	 */
	public void updateFileByObj(NasFileSystemObj obj);

	/**
	 * @Title: delFileByObj
	 * @Description: 删除指定的文件系统
	 * @param
	 * @return
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @throws Exception
	 * @createtime 2014年3月26日9:19:51
	 */
	public void delFileByObj(NasFileSystemObj obj);

	/***************** 文件系统对应的IP地址的对应操作 **************************/
	/**
	 * @Title: insertFlieSysIp
	 * @Description: 文件系统对应的IP地址入库
	 * @param
	 * @return
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @throws Exception
	 * @createtime 2014年3月27日11:00:18
	 */
	public void insertFlieSysIp(NasFileSystemObj obj);

	/**
	 * @Title: queryFlieSysIp
	 * @Description: 文件系统IP地址列表
	 * @param
	 * @return
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @throws Exception
	 * @createtime 2014年3月27日11:00:35
	 */
	public List<NasFileSystemObj> queryFlieSysIp(NasFileSystemObj obj);

	/**
	 * @Title: delFlieSysIpByObj
	 * @Description: 删除指定的文件系统对应IP地址记录
	 * @param
	 * @return
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @throws Exception
	 * @createtime 2014年3月27日11:00:54
	 */
	public void delFlieSysIpByObj(NasFileSystemObj obj);

	/******************* 根据NASIP查找虚拟机相关信息 *********************************/
	/**
	 * @Title: queryVmByIps
	 * @Description: 根据文件系统IP地址查找对应虚拟机
	 * @param
	 * @return VmHostInfoObj
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @throws Exception
	 * @createtime 2014年3月27日11:00:35
	 */
	public VMHostObj queryVmByIp(NasFileSystemObj obj);

	/**
	 * @Title: queryVmInfoByVm
	 * @Description: 根据虚拟机查找其主机、集群、数据中心信息
	 * @param
	 * @return
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @throws Exception
	 * @createtime 2014年3月27日11:00:54
	 */
	public String queryVmInfoByVm(VMHostObj obj);

	/************** 虚拟机与IP地址对应关系的维护 ********************/

	/**
	 * @Title: insertVmIpRelation
	 * @Description: 虚拟机与IP关系入库
	 * @param
	 * @return
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @throws Exception
	 * @createtime 2014年3月27日11:00:18
	 */
	public void insertVmIpRelation(VmIpObj obj);

	/**
	 * @Title: queryVmIpRelation
	 * @Description: 虚拟机与IP对应关系列表
	 * @param
	 * @return
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @throws Exception
	 * @createtime 2014年3月27日11:00:35
	 */
	public List<VmIpObj> queryVmIpRelation(VmIpObj obj);

	/**
	 * 
	 * @Title: updatVmIpObj
	 * @Description: 更新虚拟机IP地址
	 * @param
	 * @return int
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime 2014-7-9 上午11:08:33
	 */
	public int updatVmIpObj(VmIpObj obj);

	/**
	 * @Title: delVmIpRelationByObj
	 * @Description: 删除指定虚拟机与IP对应关系
	 * @param
	 * @return
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @throws Exception
	 * @createtime 2014年3月27日11:00:54
	 */
	public void delVmIpRelationByObj(VmIpObj obj);

	/**
	 * @Title: queryVmBuSys
	 * @Description: 根据文件系统查找对应挂载主机
	 * @param
	 * @return
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @throws Exception
	 * @createtime 2014年6月4日11:19:45
	 */
	public List<MountObj> queryHostBySys(NasFileSystemObj obj);

	/**
	 * @Title: queryVmBuSys
	 * @Description: 根据文件系统查找对应挂载虚拟机
	 * @param
	 * @return
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @throws Exception
	 * @createtime 2014年6月4日11:19:45
	 */
	public List<MountObj> queryVmBySys(NasFileSystemObj obj);

}
