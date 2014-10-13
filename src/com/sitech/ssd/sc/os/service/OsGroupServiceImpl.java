package com.sitech.ssd.sc.os.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.ssd.sc.os.dao.OsGroupDao;
import com.sitech.ssd.sc.os.domain.OsGroupModel;

@Service("osGroupService")
public class OsGroupServiceImpl extends BaseDao implements OsGroupService {

	@Autowired
	private OsGroupDao osGroupDao;

	@Override
	public int addOsGroup(OsGroupModel model) {
		return osGroupDao.insertOsGroup(model);
	}

	@Override
	public int copyOsTemplateGroup(OsGroupModel model) {
		return osGroupDao.copyOsTemplateGroup(model);
	}

	@Override
	public int batchAddOsGroup(List<OsGroupModel> list) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int modifyOsGroup(OsGroupModel model) {
		return osGroupDao.updateOsGroup(model);
	}

	@Override
	public int deleteOsGroup(OsGroupModel model) {
		int _ret = -1;
		if(model.getId() != null && !"".equals(model.getId())){
			osGroupDao.deleteOsGroup(model);
			_ret = 1;
		}
		return _ret;
	}

	@Override
	public int deleteOsGroupList(OsGroupModel model) {
		return osGroupDao.deleteOsGroup(model);
	}
	
	@Override
	public OsGroupModel queryOsGroup(OsGroupModel model) {
		return osGroupDao.selectOsGroup(model);
	}

	@Override
	public List<OsGroupModel> queryOsGroupList(OsGroupModel model) {
		return osGroupDao.selectOsGroupList(model);
	}

	@Override
	public List<OsGroupModel> unionOsGroupList(OsGroupModel model) {
		return osGroupDao.unionOsGroupList(model);
	}
	
}
