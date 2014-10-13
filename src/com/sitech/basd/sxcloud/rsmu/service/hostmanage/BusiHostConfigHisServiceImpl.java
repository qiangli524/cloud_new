package com.sitech.basd.sxcloud.rsmu.service.hostmanage;

import java.util.List;

import com.sitech.basd.sxcloud.rsmu.dao.hostmanage.TbBusiHostConfigHisDao;
import com.sitech.basd.sxcloud.rsmu.domain.hostmanage.TbBusiHostConfigHisObj;
import com.sitech.basd.sxcloud.rsmu.service.BaseService;

public class BusiHostConfigHisServiceImpl extends BaseService implements
		BusiHostConfigHisService {

	/**
     * @Title:删除主机历史配置信息
     * @Copyright: Copyright (c) 201006
     * @Company: si-tech
     * @author yangwenchao
     * @version 1.0
    */
	public int deleteByObj(TbBusiHostConfigHisObj obj) {
		// TODO Auto-generated method stub
		return tbBusiHostConfigHisDao.deleteByObj(obj);
	}
	
	/**
     * @Title:插入主机历史配置信息
     * @Copyright: Copyright (c) 201006
     * @Company: si-tech
     * @author yangwenchao
     * @version 1.0
    */
	public int insertByObj(TbBusiHostConfigHisObj obj) {
		// TODO Auto-generated method stub
		return tbBusiHostConfigHisDao.insertByObj(obj);
	}

	/**
     * @Title:查询出具体主机历史配置信息
     * @Copyright: Copyright (c) 201006
     * @Company: si-tech
     * @author yangwenchao
     * @version 1.0
    */
	public TbBusiHostConfigHisObj queryByObj(TbBusiHostConfigHisObj obj) {
		// TODO Auto-generated method stub
		return tbBusiHostConfigHisDao.queryByObj(obj);
	}

	/**
     * @Title:根据主机配置部分信息查询匹配的所有主机历史配置信息
     * @Copyright: Copyright (c) 201006
     * @Company: si-tech
     * @author yangwenchao
     * @version 1.0
    */
	public List queryForListByObj(TbBusiHostConfigHisObj obj) {
		// TODO Auto-generated method stub
		return tbBusiHostConfigHisDao.queryForListByObj(obj);
	}

	public int updateByObj(TbBusiHostConfigHisObj obj) {
		// TODO Auto-generated method stub
		return tbBusiHostConfigHisDao.updateByObj(obj);
	}
	                               
	private TbBusiHostConfigHisDao tbBusiHostConfigHisDao ;

	public void setTbBusiHostConfigHisDao(
			TbBusiHostConfigHisDao tbBusiHostConfigHisDao) {
		this.tbBusiHostConfigHisDao = tbBusiHostConfigHisDao;
	}
	
	
}
