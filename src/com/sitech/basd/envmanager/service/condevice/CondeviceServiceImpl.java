package com.sitech.basd.envmanager.service.condevice;

import java.util.List;
import java.util.Map;

import com.sitech.basd.envmanager.dao.condevice.CondeviceDao;
import com.sitech.basd.envmanager.domain.condevice.CondeviceObj;
import com.sitech.basd.envmanager.domain.condevice.VirtualObj;
import com.sitech.basd.sxcloud.rsmu.service.BaseService;

public class CondeviceServiceImpl extends BaseService implements
		CondeviceService {

	CondeviceDao condeviceDao;

	public CondeviceDao getCondeviceDao() {
		return condeviceDao;
	}

	public void setCondeviceDao(CondeviceDao condeviceDao) {
		this.condeviceDao = condeviceDao;
	}

	/**
	 * @Title:根据ID删除设备主机信息
	 * @Copyright: Copyright (c) 20120417
	 * @Company: si-tech
	 * @author lzh
	 * @version 1.0
	 */
	@Override
	public int deleteCondeviceObjOne(CondeviceObj obj) {
		// TODO Auto-generated method stub
		return condeviceDao.deleteCondeviceObjOne(obj);
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
	@Override
	public String getIdCondevice() {
		// TODO Auto-generated method stub
		return condeviceDao.getIdCondevice();
	}

	/**
	 * @Title:添加设备主机
	 * @Copyright: Copyright (c) 20120416
	 * @Company: si-tech
	 * @author lzh
	 * @version 1.0
	 */
	@Override
	public int insertCondeviceObj(CondeviceObj obj) {
		// TODO Auto-generated method stub
		return condeviceDao.insertCondeviceObj(obj);
	}

	/**
	 * @Title:查询设备主机数据结果集
	 * @Copyright: Copyright (c) 20120413
	 * @Company: si-tech
	 * @author lzh
	 * @version 1.0
	 */
	@Override
	public List queryCondeviceObj(CondeviceObj obj) {
		// TODO Auto-generated method stub
		return condeviceDao.queryCondeviceObj(obj);
	}

	/**
	 * @Title:查询一条信息
	 * @Copyright: Copyright (c) 20120416
	 * @Company: si-tech
	 * @author lzh
	 * @version 1.0
	 */
	@Override
	public CondeviceObj queryCondeviceObjOne(CondeviceObj obj) {
		// TODO Auto-generated method stub
		return condeviceDao.queryCondeviceObjOne(obj);
	}

	/**
	 * @Title:修改设备主机信息
	 * @Copyright: Copyright (c) 20120417
	 * @Company: si-tech
	 * @author lzh
	 * @version 1.0
	 */
	@Override
	public int updateCondeviceObjOne(CondeviceObj obj) {
		// TODO Auto-generated method stub
		return condeviceDao.updateCondeviceObjOne(obj);
	}

	@Override
	public List queryManageObj(CondeviceObj obj) {
		// TODO Auto-generated method stub
		return condeviceDao.queryManageObj(obj);
	}

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
	public List queryForExportList(Map map) {
		return condeviceDao.queryForExportList(map);
	}
	/**
	 * @Title:查询虚拟IP的结果集
	 * @Copyright: Copyright (c) 20120413
	 * @Company: si-tech
	 * @author Xumq
	 * @version 1.0
	 */
	
	public List queryVirtualObj(VirtualObj obj) {
		// TODO Auto-generated method stub
		return condeviceDao.queryVirtualObj(obj);
	}

	/**
	 * @Title 添加虚拟IP
	 * @Copyright: Copyright (c) 20120413
	 * @Company: si-tech
	 * @author Xumq
	 * @version 1.0
	 */
	public int insertVirtualObj(VirtualObj obj) {
		// TODO Auto-generated method stub
		return condeviceDao.insertVirtualObj(obj);
	}

	/**
	 * @Title:删除虚拟IP
	 * @Copyright: Copyright (c) 20120416
	 * @Company: si-tech
	 * @author lzh
	 * @version 1.0
	 */
	public int deleteVirtualObj(VirtualObj obj) {
		// TODO Auto-generated method stub
		return condeviceDao.deleteVirtualObj(obj);
	}
}
