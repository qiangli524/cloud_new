package com.sitech.basd.yicloud.service.globaltask;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sitech.basd.yicloud.dao.globaltask.GlobalTaskDao;
import com.sitech.basd.yicloud.domain.globaltask.GlobalTaskObj;

@Service("globalTaskService")
public class GlobalTaskServiceImpl implements GlobalTaskService {
	@Resource
	private GlobalTaskDao globalTaskDao;

	@Override
	public List<GlobalTaskObj> queryForListByObj(GlobalTaskObj obj) {
		// TODO Auto-generated method stub
		return globalTaskDao.queryForListByObj(obj);
	}

	/**
	 * 
	 * @Title: queryListProccessing
	 * @Description:任务栏显示，显示未完成和最近一分钟内的任务
	 * @author duangh
	 * @date Jul 4, 2013 5:46:27 PM
	 * @param obj
	 * @return
	 */
	@Override
	public List<GlobalTaskObj> queryListProccessing(GlobalTaskObj obj) {
		return globalTaskDao.queryListProccessing(obj);
	}
}
