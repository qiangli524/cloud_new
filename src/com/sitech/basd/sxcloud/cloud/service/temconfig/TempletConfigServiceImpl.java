package com.sitech.basd.sxcloud.cloud.service.temconfig;

import java.util.List;

import com.sitech.basd.sxcloud.cloud.dao.temconfig.TempletConfigDaoImpl;
import com.sitech.basd.sxcloud.cloud.domain.temconfig.TempletConfigObj;
import com.sitech.basd.sxcloud.cloud.domain.temconfig.TempletTypeConfigObj;
import com.sitech.basd.sxcloud.rsmu.service.BaseService;


public class TempletConfigServiceImpl extends BaseService implements TempletConfigService {
	 
	/**
	 * @Title:查询已有服务列表
	 * @Copyright: Copyright (c) 2012-08-29
	 * @Company: si-tech
	 * @author hehui
	 * @version 1.0
	 */
	@SuppressWarnings("unchecked")
	public List queryForListByObj(TempletTypeConfigObj obj) {
		/*
		 * boolean flag = false; { //默认从接口获取数据 //获取数据成功，则flag设为true } if(!flag){
		 * return tbVirtualDao.queryForListByObj(obj); }
		 */
		return templetConfigDao.queryForListByObj(obj);
	}
	
	/**
	 * @Title:查询并返回一个服务对象
	 * @Copyright: Copyright (c) 2012-08-29
	 * @Company: si-tech
	 * @author hehui
	 * @version 1.0
	 */
	public TempletTypeConfigObj queryByObj(TempletTypeConfigObj obj) {
		/*
		 * boolean flag = false; { //默认从接口获取数据 //获取数据成功，则flag设为true } if(!flag){
		 * return templetDao.queryByObj(obj); }
		 */
		return templetConfigDao.queryByObj(obj);
	}
	
	/**
	 * @Title:创建服务
	 * @Copyright: Copyright (c) 2012-08-29
	 * @Company: si-tech
	 * @author hehui
	 * @version 1.0
	 */
	public int insertByObj(TempletTypeConfigObj obj) {
		/*
		 * boolean flag = false; { //默认从接口获取数据 //获取数据成功，则flag设为true } if(!flag){
		 * return templetDao.insertByObj(obj); }
		 */
		return templetConfigDao.insertByObj(obj);
	}
	
	/**
	 * @Title:更新服务信息
	 * @Copyright: Copyright (c) 2012-08-29
	 * @Company: si-tech
	 * @author hehui
	 * @version 1.0
	 */
	public int updateByObj(TempletTypeConfigObj obj) {
		/*
		 * boolean flag = false; { //默认从接口获取数据 //获取数据成功，则flag设为true } if(!flag){
		 * return tbVirtualDao.deleteByObj(obj); }
		 */
		return templetConfigDao.updateByObj(obj);

	}
	
	/**
	 * @Title:删除已有服务
	 * @Copyright: Copyright (c) 2012-08-29
	 * @Company: si-tech
	 * @author hehui
	 * @version 1.0
	 */
	public int deleteByObj(TempletTypeConfigObj obj) {
		/*
		 * boolean flag = false; { //默认从接口获取数据 //获取数据成功，则flag设为true } if(!flag){
		 * return templetDao.deleteByObj(obj); }
		 */
		return templetConfigDao.deleteByObj(obj);
	}
	
	/**
	 * @Title:插入新服务项
	 * @Copyright: Copyright (c) 2012-08-29
	 * @Company: si-tech
	 * @author hehui
	 * @version 1.0
	 */
	public int insertByConObj(TempletConfigObj obj){
		return templetConfigDao.insertByConObj(obj);
	}
	
	/**
	 * @Title:修改服务项
	 * @Copyright: Copyright (c) 2012-08-29
	 * @Company: si-tech
	 * @author hehui
	 * @version 1.0
	 */
	public int updateByConObj(TempletConfigObj obj){
		return templetConfigDao.updateByConObj(obj);
	}
	/**
	 * @Title:删除服务项
	 * @Copyright: Copyright (c) 2012-08-29
	 * @Company: si-tech
	 * @author hehui
	 * @version 1.0
	 */
	public int deleteConObj(TempletConfigObj obj){
		return templetConfigDao.deleteConObj(obj);
	}
	
	/**
	 * @Title:查询服务项
	 * @Copyright: Copyright (c) 2012-08-29
	 * @Company: si-tech
	 * @author hehui
	 * @version 1.0
	 */
	public TempletConfigObj queryByConObj(TempletConfigObj obj){
		return templetConfigDao.queryByConObj(obj);
	}
	
	TempletConfigDaoImpl templetConfigDao;

	public void setTempletConfigDao(TempletConfigDaoImpl templetConfigDao) {
		this.templetConfigDao = templetConfigDao;
	}


	@Override
	public List queryForListByConObj(TempletConfigObj obj) {
		// TODO Auto-generated method stub
		return templetConfigDao.queryForListByConObj(obj);
	}
	
}
