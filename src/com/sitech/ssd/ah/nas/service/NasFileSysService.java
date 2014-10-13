package com.sitech.ssd.ah.nas.service;

import java.util.List;

import com.sitech.basd.yicloud.domain.datastore.DataStoreObj;
import com.sitech.ssd.ah.nas.domain.MountObj;
import com.sitech.ssd.ah.nas.domain.NasFileSystemObj;

public interface NasFileSysService {

	/**
	 * @Title: getFileSysList
	 * @Description: nas文件系统列表
	 * @param
	 * @return 
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @throws Exception 
	 * @createtime 2014年4月7日15:48:04
	 */
	public List<NasFileSystemObj> getFileSysList(NasFileSystemObj obj);
	
	
	/**
	 * @Title: queryVmBuSys
	 * @Description: 根据文件系统查找对应挂在实体
	 * @param
	 * @return 
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @throws Exception 
	 * @createtime 2014年6月4日11:19:45
	 */
	public List<MountObj> queryMountBySys(NasFileSystemObj obj);
}
