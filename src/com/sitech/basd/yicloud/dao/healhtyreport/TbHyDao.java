package com.sitech.basd.yicloud.dao.healhtyreport;

import java.util.List;

import com.sitech.basd.sxcloud.cloud.domain.reportvirtual.TbHyObj;
import com.sitech.basd.sxcloud.cloud.domain.virtual.TbCloud2VirtualInfoObj;

public interface TbHyDao {

	/**
	 * @Title:查询当天的虚拟服务器采集信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author wangqxa
	 * @version 1.0
	 */
	public List queryTbHyForToday(TbHyObj obj);

	/**
	 * @Title:查询本周的虚拟服务器采集信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author wangqxa
	 * @version 1.0
	 */
	public List queryTbHyForWeek(TbHyObj obj);

	/**
	 * @Title:跨表查询本周的虚拟服务器采集信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author wangqxa
	 * @version 1.0
	 */
	public List queryUnionTbHyForWeek(TbHyObj obj);

	/**
	 * @Title:查询本周的虚拟服务器采集信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author wangqxa
	 * @version 1.0
	 */
	public List queryTbHyForLastWeek(TbHyObj obj);

	/**
	 * @Title:跨表查询上周的虚拟服务器采集信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author wangqxa
	 * @version 1.0
	 */
	public List queryUnionTbHyForLastWeek(TbHyObj obj);

	/**
	 * @Title:查询当月的虚拟服务器采集信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author wangqxa
	 * @version 1.0
	 */
	public List queryTbHyForMonth(TbHyObj obj);

	/**
	 * @Title:查询上月的虚拟服务器采集信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author wangqxa
	 * @version 1.0
	 */
	public List queryTbHyForLastMonth(TbHyObj obj);

	/**
	 * @Title: 查询已经监控的虚拟机的个数
	 * @Copyright: Copyright (c) 20120406
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public int queryMonitorVirtualCount(TbCloud2VirtualInfoObj obj);

	/**
	 * @Title: 查询已经监控的虚拟机的个数
	 * @Copyright: Copyright (c) 20120406
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public int queryAllVirtualCount(TbCloud2VirtualInfoObj obj);

}
