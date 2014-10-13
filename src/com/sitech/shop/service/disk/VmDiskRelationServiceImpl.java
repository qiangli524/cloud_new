package com.sitech.shop.service.disk;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sitech.shop.dao.disk.VmDiskRelationDao;
import com.sitech.shop.domain.disk.VmDiskRelationObj;

/**
 * <p>
 * Title: VmDiskRelationService
 * </p>
 * <p>
 * Description: 虚拟机与虚拟磁盘关系
 * </p>
 * <p>
 * Copyright: Copyright (c) 2014
 * </p>
 * <p>
 * Company: SI-TECH
 * </p>
 * 
 * @author lipp
 * @version 1.0
 * @createtime 2014-4-18 下午3:37:49
 * 
 */
@Service("vmDiskRelationService")
public class VmDiskRelationServiceImpl implements VmDiskRelationService {

	@Autowired
	private VmDiskRelationDao vmDiskRelationDao;

	/**
	 * @Title: insertByObj
	 * @Description: 插入记录
	 * @param
	 * @return int
	 * @throws
	 * @author lipp
	 * @version 1.0
	 * @throws Exception
	 * @createtime 2014-4-18 下午3:26:10
	 */
	@Override
	public void insertByObj(VmDiskRelationObj relationObj) throws Exception {
		vmDiskRelationDao.insertByObj(relationObj);
	}

	/**
	 * @Title: deleteByObj
	 * @Description: 删除记录
	 * @param
	 * @return int
	 * @throws
	 * @author lipp
	 * @version 1.0
	 * @createtime 2014-4-18 下午3:26:06
	 */
	@Override
	public int deleteByObj(VmDiskRelationObj relationObj) {
		return vmDiskRelationDao.deleteByObj(relationObj);
	}

}
