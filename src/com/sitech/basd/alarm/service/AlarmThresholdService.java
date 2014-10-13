package com.sitech.basd.alarm.service;

import java.util.List;

import com.sitech.basd.alarm.domain.AlarmThreshold;

@SuppressWarnings("all")
public interface AlarmThresholdService {
	/**
	 * @Title:查询出全部信息
	 * @Copyright: Copyright (c) 201308
	 * @Company: si-tech
	 * @author yanggl_bj
	 * @version 1.0
	 */
	public List queryForListByObj(AlarmThreshold obj);
	/**
	 * @Title:添加信息
	 * @Copyright: Copyright (c) 201308
	 * @Company: si-tech
	 * @author yanggl_bj
	 * @version 1.0
	 */
	public int addAlarmThresholdByObj(AlarmThreshold obj);
	/**
	 * @Title:修改查询:通过ID
	 * @Copyright: Copyright (c) 201308
	 * @Company: si-tech
	 * @author yanggl_bj
	 * @version 1.0
	 */
	public List<AlarmThreshold> queryAlarmThresholdById(AlarmThreshold obj);
	/**
	 * @Title:修改
	 * @Copyright: Copyright (c) 201308
	 * @Company: si-tech
	 * @author yanggl_bj
	 * @version 1.0
	 */
	public void updateAlarmThresholdById(AlarmThreshold obj);
	/**
	 * @Title:删除
	 * @Copyright: Copyright (c) 201308
	 * @Company: si-tech
	 * @author yanggl_bj
	 * @version 1.0
	 */
	public void deleteAlarmThreshold(AlarmThreshold obj);
	/**
	 * @Title: validateNameByEdit
	 * @Description: 验证名字是否重复
	 * @param
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-8-15s
	 */
	public int validateNameByEdit(AlarmThreshold obj);
}
