package com.sitech.basd.yicloud.dao.globaltask;

import java.util.List;

import com.sitech.basd.yicloud.domain.globaltask.GlobalTaskObj;

public interface GlobalTaskDao {
	/**
	 * 
	 * @Title: queryListByObj
	 * @Description:查询全局任务列表
	 * @author duangh
	 * @date Jun 22, 2013 4:34:25 PM
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

	/**
	 * 
	 * @Title: insertByObj
	 * @Description:插入全局任务表信息
	 * @author duangh
	 * @date Aug 11, 2013 2:47:26 PM
	 * @param obj
	 * @return
	 */
	public int insertByObj(GlobalTaskObj obj);

	/**
	 * 
	 * @Title: updateByObj
	 * @Description:更新全局任务表信息
	 * @author duangh
	 * @date Aug 11, 2013 2:47:44 PM
	 * @param obj
	 * @return
	 */

	public int updateByObj(GlobalTaskObj obj);
}
