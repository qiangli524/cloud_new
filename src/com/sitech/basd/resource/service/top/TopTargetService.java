package com.sitech.basd.resource.service.top;

import java.util.List;
import java.util.Map;

public interface TopTargetService {
	/**
	 * 
	 * @Title:
	 * @Description: 查询y值
	 * @param
	 * @return List
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Jul 12, 2013 2:15:38 PM
	 */
	public Double[] queryYData(Map map);

	/**
	 * 
	 * @Title: queryXData
	 * @Description: 查询x值
	 * @param
	 * @return Double[]
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Jul 19, 2013 10:45:05 AM
	 */
	public String[] queryXData(Map map);

	/**
	 * 查询告警topN的Y值
	 * 
	 * @param map
	 * @return
	 */
	public Double[] queryAlarmYData(Map<String, Integer> map);

	/**
	 * 查询告警topN的X值
	 * 
	 * @param map
	 * @return
	 */
	public String[] queryAlarmXData(Map<String, Integer> map);

	/**
	 * 
	 * @Title: queryTopList
	 * @Description: 查询主机topN所对应的列表
	 * @param
	 * @return List
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Jul 24, 2013 8:54:54 AM
	 */
	public List queryTopList(Map map);

}
