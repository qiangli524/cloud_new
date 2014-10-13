package com.sitech.basd.yicloud.dao.device.disk;

import java.util.List;

import com.sitech.basd.yicloud.domain.device.disk.DiskInfoObj;

public interface DiskInfoDao {

	public List<DiskInfoObj> queryForListByObj(DiskInfoObj paramObj);
	
	public List<DiskInfoObj> queryForListByGroupIsNull(DiskInfoObj paramObj);
	
	public int insertByObj(DiskInfoObj paramObj);
	
	public int updateByObj(DiskInfoObj paramObj);
	
	public int updateGroupIdByObj(DiskInfoObj paramObj);
	
	public int deleteByObj(DiskInfoObj paramObj);
}
