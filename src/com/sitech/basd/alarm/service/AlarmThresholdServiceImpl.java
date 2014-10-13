package com.sitech.basd.alarm.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sitech.basd.alarm.dao.AlarmThresholdDao;
import com.sitech.basd.alarm.domain.AlarmThreshold;
import com.sitech.basd.sxcloud.rsmu.service.BaseService;

@SuppressWarnings("all")
@Service("alarmThresholdService")
public class AlarmThresholdServiceImpl extends BaseService implements AlarmThresholdService{
	
	@Autowired
	private AlarmThresholdDao alarmThresholdDao;
	
	/**
	 * @Title:查询出全部信息
	 * @Copyright: Copyright (c) 201308
	 * @Company: si-tech
	 * @author yanggl_bj
	 * @version 1.0
	 */
	public List queryForListByObj(AlarmThreshold obj) {
		return alarmThresholdDao.queryForListByObj(obj);
	}
	/**
	 * @Title:插入信息
	 * @Copyright: Copyright (c) 201308
	 * @Company: si-tech
	 * @author yanggl_bj
	 * @version 1.0
	 */
	public int addAlarmThresholdByObj(AlarmThreshold obj){
		
		return alarmThresholdDao.addAlarmThresholdByObj(obj);
	
	}
	/**
	 * @Title:修改:通过ID
	 * @Copyright: Copyright (c) 201308
	 * @Company: si-tech
	 * @author yanggl_bj
	 * @version 1.0
	 */
	public List<AlarmThreshold> queryAlarmThresholdById(AlarmThreshold obj){

		return alarmThresholdDao.queryListById(obj);
				
	}
	/**
	 * @Title:修改
	 * @Copyright: Copyright (c) 201308
	 * @Company: si-tech
	 * @author yanggl_bj
	 * @version 1.0
	 */
	public void updateAlarmThresholdById(AlarmThreshold obj) {
		alarmThresholdDao.updateAlarmThresholdById(obj);
	};
	/**
	 * @Title:删除
	 * @Copyright: Copyright (c) 201308
	 * @Company: si-tech
	 * @author yanggl_bj
	 * @version 1.0
	 */
	public void deleteAlarmThreshold(AlarmThreshold obj){
		//如果id不唯一时，则为批量删除
		String[] arr = obj.getId().split(",");
		if(arr.length >= 1){
			List<String> resultList = new ArrayList<String>();
			for (String str : arr) {
				resultList.add(str);
			}
			obj.setResultList(resultList);
		}
		alarmThresholdDao.deleteAlarmThresholdById(obj);
	}
	/**
	 * 
	 * @Title: validateNameByEdit
	 * @Description: 验证名字是否重复
	 * @param
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-8-15s
	 */
	public int validateNameByEdit(AlarmThreshold obj) {
		return this.alarmThresholdDao.validateNameByEdit(obj);
	}
}
