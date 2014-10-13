package com.sitech.basd.sxcloud.cloud.dao.reportapp;

import java.util.List;

import com.sitech.basd.sxcloud.cloud.domain.reportapp.TbAppCollObj;

public interface TbAppCollDao {

	/**
	 * @Title:查询当天的应用监控采集信息
	 * @Copyright: Copyright (c) 20120328
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public List queryAppCollForToday(TbAppCollObj obj);

	/**
	 * @Title:查询本周的应用监控采集信息
	 * @Copyright: Copyright (c) 20120328
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public List queryAppCollForWeek(TbAppCollObj obj);

	/**
	 * @Title:跨表查询本周的应用监控采集信息
	 * @Copyright: Copyright (c) 20120328
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public List queryUnionAppCollForWeek(TbAppCollObj obj);

	/**
	 * @Title:查询本周的应用监控采集信息
	 * @Copyright: Copyright (c) 20120328
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public List queryAppCollLastWeek(TbAppCollObj obj);

	/**
	 * @Title:跨表查询上周的应用监控采集信息
	 * @Copyright: Copyright (c) 20120328
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public List queryUnionAppCollLastWeek(TbAppCollObj obj);

	/**
	 * @Title:查询当月的应用监控采集信息
	 * @Copyright: Copyright (c) 20120328
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public List queryAppCollForMonth(TbAppCollObj obj);

	/**
	 * @Title:查询上月的应用监控采集信息
	 * @Copyright: Copyright (c) 20120328
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public List queryAppCollLastMonth(TbAppCollObj obj);

}
