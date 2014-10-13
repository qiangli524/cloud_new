package com.sitech.basd.alarm.dao;

import java.util.List;

import com.sitech.basd.alarm.domain.AlarmThreshold;

@SuppressWarnings("all")
public interface AlarmThresholdDao {
	/**
	 * @Title: queryForListByObj
	 * @Description: 查询所有信息
	 * @param
	 * @return List
	 * @throws
	 * @author qism
	 * @version 1.0
	 * @createtime 2014-6-20 下午12:47:49
	 */
	public List queryForListByObj(AlarmThreshold obj);
	/**
	 * @Title:插入信息
	 * @Copyright: Copyright (c) 201308
	 * @Company: si-tech
	 * @author qism
	 * @version 1.0
	 */
	public int addAlarmThresholdByObj(AlarmThreshold obj);
	/**
	 * @Title:修改:通过ID
	 * @Copyright: Copyright (c) 2014-6-20
	 * @Company: si-tech
	 * @author qism
	 * @version 1.0
	 */
	public List<AlarmThreshold> queryListById(AlarmThreshold obj);
	/**
	 * @Title:修改
	 * @Copyright: Copyright (c) 2014-6-20
	 * @Company: si-tech
	 * @author qism
	 * @version 1.0
	 */
	public void updateAlarmThresholdById(AlarmThreshold obj);
	/**
	 * @Title:删除
	 * @Copyright: Copyright (c) 2014-6-20
	 * @Company: si-tech
	 * @author qism
	 * @version 1.0
	 */
	public void deleteAlarmThresholdById(AlarmThreshold obj);
	/**
	 * 
	 * @Title: validateNameByEdit
	 * @Description: 编辑时验证名字是否重复
	 * @param
	 * @throws
	 * @author qism
	 * @version 1.0
	 * @createtime 2014-6-20
	 */
	public int validateNameByEdit(AlarmThreshold obj);
}
