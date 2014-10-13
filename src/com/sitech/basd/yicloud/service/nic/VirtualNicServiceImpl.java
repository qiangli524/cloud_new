package com.sitech.basd.yicloud.service.nic;

import java.util.List;

import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;
import com.sitech.basd.yicloud.dao.nic.VirtualNicDao;
import com.sitech.basd.yicloud.domain.nic.VirtualNicObj;


public class VirtualNicServiceImpl implements VirtualNicService {
	private VirtualNicDao virtualNicDao;
	
	public void setVirtualNicDao(VirtualNicDao virtualNicDao) {
		this.virtualNicDao = virtualNicDao;
	}

	/**
	 * 
	 * @Title: insertByObj
	 * @Description: 插入一个虚拟网卡
	 * @param
	 * @return int
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime Apr 18, 2013 11:18:10 AM
	 */
	public int insertByObj(VirtualNicObj obj){
		return virtualNicDao.insertByObj(obj);
	}
	
	/**
	 * 
	 * @Title: queryByObj
	 * @Description: 查询符合条件的虚拟网卡
	 * @param
	 * @return VirtualNicObj
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime Apr 24, 2013 11:36:43 AM
	 */
	public VirtualNicObj queryByObj(VirtualNicObj obj){
		return virtualNicDao.queryByObj(obj);
	}
	
	/**
	 * 
	 * @Title: queryForListByObj
	 * @Description: 查询符合条件的虚拟网卡
	 * @param
	 * @return List
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime Apr 24, 2013 11:36:20 AM
	 */
	public List queryForListByObj(VirtualNicObj obj){
		return virtualNicDao.queryForListByObj(obj);
	}
	
	/**
	 * 
	 * @Title: deleteByObj
	 * @Description: 删除虚拟网卡
	 * @param
	 * @return int
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime May 23, 2013 11:40:42 AM
	 */
	public int deleteByObj(VirtualNicObj obj){
		return virtualNicDao.deleteByObj(obj);
	}

}
