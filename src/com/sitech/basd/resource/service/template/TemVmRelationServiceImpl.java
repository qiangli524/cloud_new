package com.sitech.basd.resource.service.template;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sitech.basd.resource.dao.template.TemVmRelationDao;
import com.sitech.basd.resource.domain.template.TemVmRelationObj;

@Service("temVmRelationService")
public class TemVmRelationServiceImpl implements TemVmRelationService{

	@Autowired
	private TemVmRelationDao temVmRelationDao;
	
	@Override
	public int insertByObj(TemVmRelationObj obj) {
		return temVmRelationDao.insertByObj(obj);
	}

	@Override
	public void deleteByObj(TemVmRelationObj relation) {
		temVmRelationDao.deleteByObj(relation);
	}

}
