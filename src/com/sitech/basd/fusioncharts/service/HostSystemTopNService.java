package com.sitech.basd.fusioncharts.service;

import java.util.List;
import java.util.Map;

import com.sitech.basd.fusioncharts.vo.FusionCharts;

/**
 * 
 * <p>
 * Title: HostSystemTopNService
 * </p>
 * <p>
 * Description: 宿主机TopNservice
 * </p>
 * <p>
 * Copyright: Copyright (c) 2013
 * </p>
 * <p>
 * Company: SI-TECH
 * </p>
 * 
 * @author huojla
 * @version 1.0
 * @createtime 2013-9-14 上午9:26:56
 * 
 */
public interface HostSystemTopNService {
	/**
	 * 
	 * @Title: queryHostTopN
	 * @Description: 查询宿主机TopN
	 * @param
	 * @return FusionCharts
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @throws Exception
	 * @createtime 2013-9-14 上午9:30:25
	 */
	public FusionCharts queryEntityTopN(Map<String, Object> paramMap)
			throws Exception;

	/**
	 * 
	 * @Title: queryTopList
	 * @Description: 获取TopN实体列表
	 * @param
	 * @return List
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-9-15 下午3:20:24
	 */
	@SuppressWarnings("rawtypes")
	public List queryTopList(Map<String, Object> paramMap);
}
