package com.sitech.basd.sxcloud.rsmu.service.deploy;

import java.util.List;

import com.sitech.basd.sxcloud.rsmu.dao.deploy.ExampleResumeDao;
import com.sitech.basd.sxcloud.rsmu.domain.deploy.ExampleResumeObj;
import com.sitech.basd.sxcloud.rsmu.service.BaseService;

public class ExampleResumeServiceImpl extends BaseService implements
		ExampleResumeService {

	public int deleteByObj(ExampleResumeObj obj) {
		// TODO Auto-generated method stub
		return exampleResumeDao.deleteByObj(obj);
	}

	
	@SuppressWarnings("unchecked")
	public List queryForListByObj(ExampleResumeObj obj) {
		// TODO Auto-generated method stub
		return exampleResumeDao.queryForListByObj(obj);
	}
    
	

	public int resumeByObj(ExampleResumeObj obj) {
		// TODO Auto-generated method stub
		return exampleResumeDao.resumeByObj(obj);
	}

	

	ExampleResumeDao exampleResumeDao;



	public void setExampleResumeDao(ExampleResumeDao exampleResumeDao) {
		this.exampleResumeDao = exampleResumeDao;
	}

}
