package com.sitech.basd.sxcloud.cloud.dao.showresource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sitech.basd.cloud3.domain.charts.DataPoint;
import com.sitech.basd.sxcloud.cloud.domain.showresource.ShowResourceObj;
import com.sitech.basd.sxcloud.cloud.web.showresource.form.AlarmHostStatistics;

public interface ShowResourceDao {
	/**
	 * 
	 * @Title: insertbyObj
	 * @Description: 插入一条记录
	 * @param
	 * @return int
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Feb 23, 2013 4:37:05 PM
	 */
	public int insertbyObj(ShowResourceObj obj);

	/**
	 * 
	 * @Title: getHostList
	 * @Description: 获取主机的统计数据
	 * @param
	 * @return List
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Feb 25, 2013 4:06:20 PM
	 */
	public List<HashMap<String, Integer>> getHostList();

	/**
	 * 
	 * @Title: getHostList
	 * @Description: 获取虚拟机的统计数据
	 * @param
	 * @return List
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Feb 25, 2013 4:06:20 PM
	 */

	public List getVmList();

	/**
	 * 
	 * @Title: getSwitchList
	 * @Description: 获取交换机的统计数据
	 * @param
	 * @return List
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Feb 25, 2013 4:07:51 PM
	 */
	public List getSwitchList();

	/**
	 * 
	 * @Title: getResourceList
	 * @Description: 获取资源监控数据，替换为getResourceData duangh
	 * @param params
	 *            :KPI_ID,rownum
	 * @return List
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime Feb 26, 2013 3:42:22 PM
	 */
	@Deprecated
	public List getResourceList(Map map);

	/**
	 * 
	 * @Title: getResourePool
	 * @Description:首页统计资源池信息
	 * @author duangh
	 * @date Jun 7, 2013 6:21:46 PM
	 * @return
	 */
	public Map<String, Double> getResourePool(String type);

	/**
	 * 
	 * @Title: getResourceData
	 * @Description:首页获取资源监控数据
	 * @author duangh
	 * @date Aug 13, 2013 9:29:56 PM
	 * @param map
	 * @return
	 */
	public List<DataPoint> getResourceData(Map map);
	/**
	 * 
	 * @Title: getAlarmAll
	 * @Description:数据中心展示告警信息
	 * @author 
	 * @date 2013-8-20
	 * @return
	 */
	public List<AlarmHostStatistics> getAlarmAll();
	/**
	 * 
	 * @Title: queryIpData
	 * @Description:数据中心展示IP信息
	 * @author 
	 * @date 2013-8-20
	 * @return
	 */
	public List<AlarmHostStatistics> queryIpData();
	/**
	 * 
	 * @Title: queryAllCPU
	 * @Description: 查询CPU总数
	 * @param
	 * @return obj
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-09-09
	 */
	public AlarmHostStatistics queryAllCPU();
	/**
	 * 
	 * @Title: queryUsedCPU
	 * @Description: 查询已用CPU
	 * @param
	 * @return obj
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-09-09
	 */
	public AlarmHostStatistics queryUsedCPU();
	/**
	 * 
	 * @Title: queryWOCount
	 * @Description: 查询工单总数
	 * @param
	 * @return obj
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-09-14
	 */
	public AlarmHostStatistics queryWOCount();
}
