package com.sitech.basd.cloud3.service.departproject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sitech.basd.cloud3.dao.departproject.DepartHisInfoDao;
import com.sitech.basd.cloud3.domain.departproject.DepartHisInfoObj;

@Service("departHisInfoService")
public class DepartHisInfoServiceImpl implements DepartHisInfoService {

	@Autowired
	private DepartHisInfoDao departHisInfoDao;
	
	@Override
	public DepartHisInfoObj queryOneObj(DepartHisInfoObj departHisInfoObj) {
		return departHisInfoDao.queryOneObj(departHisInfoObj);
	}

}
