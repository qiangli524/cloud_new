package com.sitech.basd.yicloud.service.device.diskgroup;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sitech.basd.yicloud.dao.device.diskgroup.DiskGroupDao;
import com.sitech.basd.yicloud.domain.device.diskgroup.DiskGroupObj;

@Service("diskGroupService")
public class DiskGroupServiceImpl implements DiskGroupService {

	@Autowired
	private DiskGroupDao diskGroupDao;
	
	@Override
	public List<DiskGroupObj> queryForListByObj(DiskGroupObj paramObj) {
		return diskGroupDao.queryForListByObj(paramObj);
	}

	@Override
	public int insertByObj(DiskGroupObj paramObj) {
		return diskGroupDao.insertByObj(paramObj);
	}

	@Override
	public int updateByObj(DiskGroupObj paramObj) {
		return diskGroupDao.updateByObj(paramObj);
	}

	@Override
	public int deleteByObj(DiskGroupObj paramObj) {
		return diskGroupDao.deleteByObj(paramObj);
	}

}
