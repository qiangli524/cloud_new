package com.sitech.ssd.sc.os.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sitech.ssd.sc.os.dao.OsVolGroupDao;
import com.sitech.ssd.sc.os.domain.OsVolGroupModel;

@Service("osVolGroupService")
public class OsVolGroupServiceImpl implements OsVolGroupService {

	@Autowired
	private OsVolGroupDao osVolGroupDao;
	
	@Override
	public int copyOsTemplateVolGroup(OsVolGroupModel vg) {
		return osVolGroupDao.copyOsTemplateVolGroup(vg);
	}

	@Override
	public int deleteAllOsVolGroup(OsVolGroupModel vg) {
		if(vg == null || vg.getOs_host_id() == null
				|| "".equals(vg.getOs_host_id())){
			return -1;
		}
		return osVolGroupDao.deleteOsVolGroup(vg);
	}

	@Override
	public int deleteOsTemplateVolGroup(OsVolGroupModel vg) {
		if(vg == null || vg.getOs_host_id() == null || vg.getFlag() == null
				|| "".equals(vg.getOs_host_id()) || "".equals(vg.getFlag())){
			return -1;
		}
		return osVolGroupDao.deleteOsVolGroup(vg);
	}

	@Override
	public List<OsVolGroupModel> queryOsVolGroupList(OsVolGroupModel vg) {
		return osVolGroupDao.selectOsVolGroupList(vg);
	}
	
	@Override
	public List<OsVolGroupModel> unionOsVolGroupList(OsVolGroupModel vg) {
		return osVolGroupDao.unionOsVolGroupList(vg);
	}

}
