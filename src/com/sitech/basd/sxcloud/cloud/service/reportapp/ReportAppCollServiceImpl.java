package com.sitech.basd.sxcloud.cloud.service.reportapp;

import java.util.List;

import com.sitech.basd.sxcloud.cloud.dao.reportapp.TbAppCollDao;
import com.sitech.basd.sxcloud.cloud.domain.reportapp.TbAppCollObj;
import com.sitech.basd.sxcloud.rsmu.service.BaseService;

public class ReportAppCollServiceImpl extends BaseService implements
		ReportAppCollService {

	/**
	 * @Title:查询当天的应用监控采集信息
	 * @Copyright: Copyright (c) 20120328
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public List queryAppCollForToday(TbAppCollObj obj) {
		return tbAppCollDao.queryAppCollForToday(obj);
	}

	/**
	 * @Title:查询本周的应用监控采集信息
	 * @Copyright: Copyright (c) 20120328
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public List queryAppCollForWeek(TbAppCollObj obj) {
		return tbAppCollDao.queryAppCollForWeek(obj);
	}

	/**
	 * @Title:跨表查询上周的应用监控采集信息
	 * @Copyright: Copyright (c) 20120328
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public List queryUnionAppCollForWeek(TbAppCollObj obj) {
		return tbAppCollDao.queryUnionAppCollForWeek(obj);
	}

	/**
	 * @Title:跨表查询上周的应用监控采集信息
	 * @Copyright: Copyright (c) 20120328
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public List queryAppCollLastWeek(TbAppCollObj obj) {
		return tbAppCollDao.queryAppCollLastWeek(obj);
	}

	/**
	 * @Title:跨表查询上周的物理服务器采集信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author wangqxa
	 * @version 1.0
	 */
	public List queryUnionAppCollLastWeek(TbAppCollObj obj) {
		return tbAppCollDao.queryAppCollLastWeek(obj);
	}

	/**
	 * @Title:查询当月的应用监控采集信息
	 * @Copyright: Copyright (c) 20120328
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public List queryAppCollForMonth(TbAppCollObj obj) {
		return tbAppCollDao.queryAppCollForMonth(obj);
	}

	/**
	 * @Title:查询上月的应用监控采集信息
	 * @Copyright: Copyright (c) 20120328
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public List queryAppCollLastMonth(TbAppCollObj obj) {
		return tbAppCollDao.queryAppCollLastMonth(obj);
	}

	private TbAppCollDao tbAppCollDao;

	public void setTbAppCollDao(TbAppCollDao tbAppCollDao) {
		this.tbAppCollDao = tbAppCollDao;
	}

}
