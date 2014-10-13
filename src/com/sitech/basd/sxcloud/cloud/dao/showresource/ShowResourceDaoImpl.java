package com.sitech.basd.sxcloud.cloud.dao.showresource;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import util.DomainUtil;

import com.sitech.basd.cloud3.domain.charts.DataPoint;
import com.sitech.basd.sxcloud.cloud.domain.showresource.ShowResourceObj;
import com.sitech.basd.sxcloud.cloud.web.showresource.form.AlarmHostStatistics;
import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;
import com.sitech.utils.properties.PropertiesUtil;

public class ShowResourceDaoImpl extends BaseDao implements ShowResourceDao {
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
	public int insertbyObj(ShowResourceObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().insert("ShowResource.insertbyObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("ShowResource.insertbyObj:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return ret;
	}

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
	public List<HashMap<String, Integer>> getHostList() {
		List lst = null;
		try {
			lst = getSqlMap().queryForList("ShowResource.getHostList");
		} catch (Exception sqlexception) {
			LogHelper.error("ShowResource.getHostList:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return lst;
	}

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

	public List getVmList() {
		List lst = null;
		try {
			lst = getSqlMap().queryForList("ShowResource.getVmList");
		} catch (Exception sqlexception) {
			LogHelper.error("ShowResource.getVmList:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return lst;
	}

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
	public List getSwitchList() {
		List lst = null;
		try {
			lst = getSqlMap().queryForList("ShowResource.getSwitchList");
		} catch (Exception sqlexception) {
			LogHelper.error("ShowResource.getSwitchList:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return lst;
	}

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
	public List getResourceList(Map map) {
		List lst = null;
		try {
			lst = getSqlMap().queryForList("ShowResource.getResourceList", map);
		} catch (Exception sqlexception) {
			LogHelper.error("ShowResource.getResourceList:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return lst;
	}

	@Override
	public Map<String, Double> getResourePool(String type) {
		Map<String, Double> map = new HashMap<String, Double>();
		try {
			Map<String, String> temp = new HashMap<String, String>();
			temp.put("type", type);
			temp.put("modulus", this.getModulus() + "");
			List list = getSqlMap().queryForList("ShowResource.queryHostCPU", temp);
			if (list != null && list.size() > 0) {
				map = (Map) list.get(0);
			}
		} catch (Exception e) {
			LogHelper.error("ShowResource.queryHostCPU:" + e.getMessage() + getClass().getName());
		}
		return map;
	}

	@Override
	public List<DataPoint> getResourceData(Map map) {
		List lst = null;
		try {
			lst = getSqlMap().queryForList("ShowResource.getResourceData", map);
		} catch (Exception sqlexception) {
			LogHelper.error("ShowResource.getResourceData:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return lst;
	}

	/**
	 * 
	 * @Title: getAlarmAll
	 * @Description:数据中心展示告警信息
	 * @author
	 * @date 2013-8-20
	 * @return
	 */
	@Override
	public List<AlarmHostStatistics> getAlarmAll() {
		List lst = null;
		try {
			AlarmHostStatistics obj = new AlarmHostStatistics();
			obj.setDomain(DomainUtil.getSessionStringDomain());
			lst = getSqlMap().queryForList("ShowResource.getAlarmAll",obj);
		} catch (Exception sqlexception) {
			LogHelper.error("ShowResource.getAlarmAll:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return lst;
	}

	/**
	 * 
	 * @Title: queryIpData
	 * @Description:数据中心展示IP信息
	 * @author
	 * @date 2013-8-20
	 * @return
	 */
	public List<AlarmHostStatistics> queryIpData() {
		List<AlarmHostStatistics> list = null;
		AlarmHostStatistics statistics = new AlarmHostStatistics();
		try {
			list = getSqlMap().queryForList("ShowResource.queryIpData", statistics);
		} catch (SQLException e) {
			LogHelper.error("ShowResource.queryIpData:" + e.getMessage() + e.getClass().getName());
		}
		return list;
	}

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
	public AlarmHostStatistics queryAllCPU() {
		AlarmHostStatistics obj = null;
		try {
			obj = (AlarmHostStatistics) getSqlMap().queryForObject("ShowResource.queryAllCPU");
		} catch (Exception e) {
			LogHelper.error("ShowResource.queryAllCPU" + e.getMessage() + getClass().getName());
		}
		return obj;
	}

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
	public AlarmHostStatistics queryUsedCPU() {
		AlarmHostStatistics obj = null;
		try {
			obj = (AlarmHostStatistics) getSqlMap().queryForObject("ShowResource.queryUsedCPU");
		} catch (Exception e) {
			LogHelper.error("ShowResource.queryUsedCPU" + e.getMessage() + getClass().getName());
		}
		return obj;
	}

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
	public AlarmHostStatistics queryWOCount() {
		AlarmHostStatistics obj = null;
		try {
			obj = (AlarmHostStatistics) getSqlMap().queryForObject("ShowResource.queryWOCount");
		} catch (Exception e) {
			LogHelper.error("ShowResource.queryWOCount" + e.getMessage() + getClass().getName());
		}
		return obj;
	}

	private int getModulus() {
		int modulus = 1;
		String modulusStr = PropertiesUtil.getString("properties/modulus", "modulus");
		if (modulusStr != null) {
			modulus = Integer.parseInt(modulusStr);
		}
		return modulus;
	}
}
