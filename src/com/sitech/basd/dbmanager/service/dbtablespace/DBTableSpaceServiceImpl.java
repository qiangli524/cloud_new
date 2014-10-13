package com.sitech.basd.dbmanager.service.dbtablespace;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sitech.basd.dbmanager.dao.dbtablespace.DBTabelSpaceDao;
import com.sitech.basd.dbmanager.domain.dbtablespace.DBTableSpaceObj;

@Service("dBTableSpaceService")
public class DBTableSpaceServiceImpl implements DBTableSpaceService {

	@Autowired
	private DBTabelSpaceDao dBTabelSpaceDao;

	@Override
	public List<DBTableSpaceObj> queryTableSpaceList(DBTableSpaceObj obj) {
		return dBTabelSpaceDao.queryTableSpaceList(obj);
	}

	@Override
	public int insertTableSpace(DBTableSpaceObj obj) {
		return dBTabelSpaceDao.insertTableSpace(obj);
	}

	@Override
	public int updateTableSpace(DBTableSpaceObj obj) {
		// TODO Auto-generated method stub
		return dBTabelSpaceDao.updateTableSpace(obj);
	}

	@Override
	public int deleteTableSpace(DBTableSpaceObj obj) {
		// TODO Auto-generated method stub
		return dBTabelSpaceDao.deleteTableSpace(obj);
	}

	@Override
	public int updateTableSpaceExecute(DBTableSpaceObj obj) {
		// TODO Auto-generated method stub
		return dBTabelSpaceDao.updateTableSpaceExecute(obj);
	}

}
