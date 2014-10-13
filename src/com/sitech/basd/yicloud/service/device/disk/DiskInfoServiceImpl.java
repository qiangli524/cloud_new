package com.sitech.basd.yicloud.service.device.disk;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sitech.basd.yicloud.dao.device.disk.DiskInfoDao;
import com.sitech.basd.yicloud.domain.device.disk.DiskInfoObj;

@Service("diskInfoService")
public class DiskInfoServiceImpl implements DiskInfoService {

	@Autowired
	private DiskInfoDao diskInfoDao;
	
	@Override
	public List<DiskInfoObj> queryForListByObj(DiskInfoObj paramObj) {
		return diskInfoDao.queryForListByObj(paramObj);
	}
	@Override
	public List<DiskInfoObj> queryForListByGroupIsNull(DiskInfoObj paramObj) {
		return diskInfoDao.queryForListByGroupIsNull(paramObj);
	}
	@Override
	public int insertByObj(DiskInfoObj paramObj) {
		return diskInfoDao.insertByObj(paramObj);
	}

	@Override
	public int updateByObj(DiskInfoObj paramObj) {
		return diskInfoDao.updateByObj(paramObj);
	}

	@Override
	public int updateGroupIdByObj(DiskInfoObj paramObj) {
		// TODO Auto-generated method stub
		return diskInfoDao.updateGroupIdByObj(paramObj);
	}
	@Override
	public int deleteByObj(DiskInfoObj paramObj) {
		return diskInfoDao.deleteByObj(paramObj);
	}

}
