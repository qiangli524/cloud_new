package com.sitech.basd.envmanager.dao.condevice;

import java.util.List;
import java.util.Map;

import com.sitech.basd.envmanager.domain.condevice.CondeviceObj;
import com.sitech.basd.envmanager.domain.condevice.VirtualObj;

public interface CondeviceDao {
	/**
	 * @Title:删除虚拟IP
	 * @Copyright: Copyright (c) 20120416
	 * @Company: si-tech
	 * @author lzh
	 * @version 1.0
	 */
	public int deleteVirtualObj(VirtualObj obj);
	/**
	 * @Title:添加虚拟IP
	 * @Copyright: Copyright (c) 20120416
	 * @Company: si-tech
	 * @author lzh
	 * @version 1.0
	 */
	public int insertVirtualObj(VirtualObj obj);
	/**
	 * @Title:查询虚拟IP的结果集
	 * @Copyright: Copyright (c) 20120413
	 * @Company: si-tech
	 * @author Xumq
	 * @version 1.0
	 */
	public List queryVirtualObj(VirtualObj obj);
	/**
	 * @Title:查询设备主机数据结果集
	 * @Copyright: Copyright (c) 20120413
	 * @Company: si-tech
	 * @author lzh
	 * @version 1.0
	 */
	public List queryManageObj(CondeviceObj obj);

	/**
	 * @Title:查询设备主机数据结果集
	 * @Copyright: Copyright (c) 20120413
	 * @Company: si-tech
	 * @author lzh
	 * @version 1.0
	 */
	public List queryCondeviceObj(CondeviceObj obj);

	/**
	 * @Title:添加设备主机
	 * @Copyright: Copyright (c) 20120416
	 * @Company: si-tech
	 * @author lzh
	 * @version 1.0
	 */
	public int insertCondeviceObj(CondeviceObj obj);

	/**
	 * @Title:查询一条信息
	 * @Copyright: Copyright (c) 20120416
	 * @Company: si-tech
	 * @author lzh
	 * @version 1.0
	 */
	public CondeviceObj queryCondeviceObjOne(CondeviceObj obj);

	/**
	 * @Title:修改设备主机信息
	 * @Copyright: Copyright (c) 20120417
	 * @Company: si-tech
	 * @author lzh
	 * @version 1.0
	 */
	public int updateCondeviceObjOne(CondeviceObj obj);

	/**
	 * @Title:根据ID删除设备主机信息
	 * @Copyright: Copyright (c) 20120417
	 * @Company: si-tech
	 * @author lzh
	 * @version 1.0
	 */
	public int deleteCondeviceObjOne(CondeviceObj obj);

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
	public String getIdCondevice();

	/**
	 * 
	 * @Title: queryForExportList
	 * @Description: 用于导出数据获取列表
	 * @param
	 * @return List
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Apr 15, 2013 1:24:30 PM
	 */
	public List queryForExportList(Map map);

}
