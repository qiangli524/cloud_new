package com.sitech.basd.yicloud.service.globaltask;

import java.util.List;

import com.sitech.basd.yicloud.domain.globaltask.GlobalTaskObj;

public interface GlobalTaskService {
	/**
	 * 
	 * @Title: queryForListByObj
	 * @Description: 查询全局任务列表
	 * @author duangh
	 * @date Jun 22, 2013 5:56:15 PM
	 * @param obj
	 * @return List<GlobalTaskObj>
	 */
	public List<GlobalTaskObj> queryForListByObj(GlobalTaskObj obj);

	/**
	 * 
	 * @Title: queryListProccessing
	 * @Description:任务栏显示，显示未完成和最近一分钟内的任务
	 * @author duangh
	 * @date Jul 4, 2013 5:46:27 PM
	 * @param obj
	 * @return
	 */
	public List<GlobalTaskObj> queryListProccessing(GlobalTaskObj obj);

}
