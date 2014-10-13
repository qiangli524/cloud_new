package com.sitech.basd.yicloud.service.device.lun;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sitech.basd.yicloud.dao.device.lun.LunDao;
import com.sitech.basd.yicloud.domain.device.lun.LunObj;

@Service("lunService")
public class LunServiceImpl implements LunService{

	@Autowired
	private LunDao lunDao;
	
	@Override
	public List<LunObj> queryForListByObj(LunObj paramObj) {
		return lunDao.queryForListByObj(paramObj);
	}

	@Override
	public int insertByObj(LunObj paramObj) {
		return lunDao.insertByObj(paramObj);
	}

	@Override
	public int updateByObj(LunObj paramObj) {
		return lunDao.updateByObj(paramObj);
	}

	@Override
	public int deleteByObj(LunObj paramObj) {
		return lunDao.deleteByObj(paramObj);
	}
	@Override
	public int updateGroupIdByObj(LunObj paramObj) {
		return lunDao.updateGroupIdByObj(paramObj);
	}
	
}
