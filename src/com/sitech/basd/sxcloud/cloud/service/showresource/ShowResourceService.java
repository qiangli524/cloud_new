package com.sitech.basd.sxcloud.cloud.service.showresource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sitech.basd.sxcloud.cloud.domain.showresource.ShowResourceObj;
import com.sitech.basd.sxcloud.cloud.web.showresource.form.AlarmHostStatistics;
import com.sitech.basd.sxcloud.cloud.web.showresource.form.OtherHostStatistics;
import com.sitech.basd.sxcloud.cloud.web.showresource.form.PowerHostStatistics;
import com.sitech.basd.sxcloud.cloud.web.showresource.form.VmwareHostStatistics;
import com.sitech.basd.sxcloud.cloud.web.showresource.form.XenHostStatistics;

public interface ShowResourceService {
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
	 * @Description: 获取资源监控数据
	 * @param params
	 *            :KPI_ID,rownum
	 * @return List
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime Feb 26, 2013 3:42:22 PM
	 */
	public List getResourceList(Map map);

	/**
	 * 
	 * @Title: getResourePool
	 * @Description:首页统计资源池信息
	 * @author duangh
	 * @date Jun 7, 2013 7:50:41 PM
	 * @param type主机类型
	 * @return 包含总cpu个数，已使用cpu个数的map
	 */
	public Map<String, Double> getResourePool(String type);

	/**
	 * 
	 * @Title: xenHostStatistics
	 * @Description:统计xen主机cpu,内存等信息
	 * @author duangh
	 * @date Jun 7, 2013 8:12:16 PM
	 * @return
	 */
	public XenHostStatistics xenHostStatistics();

	/**
	 * 
	 * @Title: vmwareHostStatistics
	 * @Description:统计vmware主机cpu,内存等信息
	 * @author duangh
	 * @date Jun 7, 2013 9:18:10 PM
	 * @return
	 */
	public VmwareHostStatistics vmwareHostStatistics();

	/**
	 * 
	 * @Title: powerHostStatictics
	 * @Description:统计IBM power主机cpu，内存，存储等信息
	 * @author duangh
	 * @date Jun 7, 2013 9:22:35 PM
	 * @return
	 */
	public PowerHostStatistics powerHostStatictics();

	/**
	 * 
	 * @Title: otherHostStatictics
	 * @Description:统计KVM等其他主机cpu,内存等信息
	 * @author duangh
	 * @date Jun 7, 2013 9:23:32 PM
	 * @return
	 */
	public OtherHostStatistics otherHostStatictics();

	/**
	 * 
	 * @Title: getResourceData
	 * @Description:首页展示资源监控情况
	 * @author duangh
	 * @date Jul 9, 2013 4:40:38 PM
	 * @return
	 */
	public String getResourceData(String type, String rownum);
	/**
	 * 
	 * @Title: alarmHostStatistics
	 * @Description:数据中心展示告警信息
	 * @author 
	 * @date 2013-8-20
	 * @return
	 */
	public List<AlarmHostStatistics> alarmHostStatistics();
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
