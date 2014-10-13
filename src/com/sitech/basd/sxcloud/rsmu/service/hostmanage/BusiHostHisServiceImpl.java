package com.sitech.basd.sxcloud.rsmu.service.hostmanage;

import java.util.List;

import com.sitech.basd.sxcloud.rsmu.dao.hostmanage.TbBusiHostHisObjDao;
import com.sitech.basd.sxcloud.rsmu.domain.hostmanage.TbBusiHostHisObj;
import com.sitech.basd.sxcloud.rsmu.service.BaseService;

public class BusiHostHisServiceImpl extends BaseService implements
		BusiHostHisService {

	 /**
     * @Title:删除主机历史信息
     * @Copyright: Copyright (c) 201006
     * @Company: si-tech
     * @author yangwenchao
     * @version 1.0
    */
	public int deleteByObj(TbBusiHostHisObj obj) {
		// TODO Auto-generated method stub
		return tbBusiHostHisObjDao.deleteByObj(obj);
	}

	/**
     * @Title:插入主机历史信息
     * @Copyright: Copyright (c) 201006
     * @Company: si-tech
     * @author yangwenchao
     * @version 1.0
    */
	public int insertByObj(TbBusiHostHisObj obj) {
		// TODO Auto-generated method stub
		return tbBusiHostHisObjDao.insertByObj(obj);
	}

	 /**
     * @Title:查询出具体主机历史信息
     * @Copyright: Copyright (c) 201006
     * @Company: si-tech
     * @author yangwenchao
     * @version 1.0
    */
	public TbBusiHostHisObj queryByObj(TbBusiHostHisObj obj) {
		// TODO Auto-generated method stub
		return tbBusiHostHisObjDao.queryByObj(obj);
	}

	/**
     * @Title:根据主机部分信息查询匹配的所有主机历史信息
     * @Copyright: Copyright (c) 201006
     * @Company: si-tech
     * @author yangwenchao
     * @version 1.0
    */
	public List queryForListByObj(TbBusiHostHisObj obj) {
		// TODO Auto-generated method stub
		return tbBusiHostHisObjDao.queryForListByObj(obj);
	}
	
	private TbBusiHostHisObjDao tbBusiHostHisObjDao ;
	public void setTbBusiHostHisObjDao(TbBusiHostHisObjDao tbBusiHostHisObjDao) {
		this.tbBusiHostHisObjDao = tbBusiHostHisObjDao;
	}
	
}
