package dao.monitor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.sitech.basd.fusioncharts.vo.Data;
import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;

import domain.kpi.HadoopKpiObj;
import domain.monitor.HadoopMonitorObj;
import domain.tab.MapDataSet;

@Repository("hadoopMonitorDao")
public class HadoopMonitorDaoImpl extends BaseDao implements HadoopMonitorDao {
	/**
	 * 
	 * @Title: queryForListByObj
	 * @Description: 查询列表
	 * @param
	 * @return List
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime 2014-1-5 下午5:18:53
	 */
	@SuppressWarnings("rawtypes")
	public List queryForListByObj(HadoopMonitorObj obj) {
		List list = null;
		try {
			list = getSqlMap().queryForList("HadoopMonitor.queryForListByObj", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("HadoopMonitor.queryForListByObj:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return list;
	}

	/**
	 * @Title: queryForLatestKpi
	 * @Description: 查询监控指标最新监控值
	 * @param
	 * @return List<HadoopMonitorObj>
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2014-1-6 上午10:45:17
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<HadoopMonitorObj> queryForLatestKpi(Map<String, Object> paramMap) {
		List<HadoopMonitorObj> list = new ArrayList<HadoopMonitorObj>();
		try {
			list = getSqlMap().queryForList("HadoopMonitor.queryForLatestKpi", paramMap);
		} catch (Exception e) {
			LogHelper.error("HadoopMonitor.queryForLatestKpi: " + e.getMessage()
					+ e.getClass().getName());
		}
		return list;
	}

	/**
	 * @Title: queryHostKpiListByServiceNode
	 * @Description: 查询主机的kpi集合
	 * @param
	 * @return List<HadoopMonitorObj>
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2014-1-7 上午10:12:44
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<HadoopMonitorObj> queryHostKpiListByServiceNode(HadoopMonitorObj hadoopMonitorObj) {
		List<HadoopMonitorObj> list = new ArrayList<HadoopMonitorObj>();
		try {
			list = getSqlMap().queryForList("HadoopMonitor.queryHostKpiListByServiceNode",
					hadoopMonitorObj);
		} catch (Exception e) {
			LogHelper.error("HadoopMonitor.queryHostKpiListByServiceNode: " + e.getMessage()
					+ e.getClass().getName());
		}
		return list;
	}

	/**
	 * @Title: queryLastestValueForKpi
	 * @Description: 查询每个监控指标的最新监控值
	 * @param
	 * @return List<HadoopMonitorObj>
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2014-1-7 下午6:11:05
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<HadoopMonitorObj> queryLastestValueForKpi(HadoopMonitorObj hadoopMonitorObj) {
		List<HadoopMonitorObj> list = new ArrayList<HadoopMonitorObj>();
		try {
			list = getSqlMap().queryForList("HadoopMonitor.queryLastestValueForKpi",
					hadoopMonitorObj);
		} catch (Exception e) {
			LogHelper.error("HadoopMonitor.queryLastestValueForKpi: " + e.getMessage()
					+ e.getClass().getName());
		}
		return list;
	}

	@Override
	public List<Data> queryHostMonitorDataByInterval(MapDataSet map) {
		List<Data> datas = new ArrayList<Data>();
		try {
			datas = getSqlMap().queryForList("HadoopMonitor.queryHostMonitorDataByInterval", map);
		} catch (Exception e) {
			LogHelper.error("HadoopMonitor.queryHostMonitorDataByInterval: " + e.getMessage()
					+ e.getClass().getName());
		}
		return datas;
	}

	@Override
	public List<Data> queryHostMonitorDataByPot(MapDataSet map) {
		List<Data> datas = new ArrayList<Data>();
		try {
			datas = getSqlMap().queryForList("HadoopMonitor.queryHostMonitorDataByPot", map);
		} catch (Exception e) {
			LogHelper.error("HadoopMonitor.queryHostMonitorDataByPot: " + e.getMessage()
					+ e.getClass().getName());
		}
		return datas;
	}

	@Override
	public List<Data> queryChartData(Map<String, String> map) {
		List<Data> datas = new ArrayList<Data>();
		try {
			datas = getSqlMap().queryForList("HadoopMonitor.queryHostMonitorDataByInterval", map);
		} catch (Exception e) {
			LogHelper.error("HadoopMonitor.queryHostMonitorDataByInterval: " + e.getMessage()
					+ e.getClass().getName());
		}
		return datas;
	}

	@Override
	public List<HadoopKpiObj> queryKpiIdDataByHost(HadoopKpiObj obj) {
		List<HadoopKpiObj> kpiIds = new ArrayList<HadoopKpiObj>();
		try {
			if (obj.getPagination() != null) {
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj.getPagination().setTotalCount(
						((Integer) getSqlMap().queryForObject("HadoopMonitor.queryHostKpiIdByHostCount", obj))
								.intValue());
			}
			kpiIds = getSqlMap().queryForList("HadoopMonitor.queryHostKpiIdByHost", obj);
		} catch (Exception e) {
			e.printStackTrace();
			LogHelper.error("HadoopMonitor.queryHostKpiIdOrValueByHost: " + e.getMessage()
					+ e.getClass().getName());
		}
		return kpiIds;
	}

	/**
	 * @Title: queryLatestValueByKpi
	 * @Description: 查询指标的最新监控值
	 * @param
	 * @return List<HadoopMonitorObj>
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2014-1-9 下午9:44:29
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<HadoopMonitorObj> queryLatestValueByKpi(HadoopMonitorObj hadoopMonitorObj) {
		List<HadoopMonitorObj> list = new ArrayList<HadoopMonitorObj>();
		try {
			list = getSqlMap()
					.queryForList("HadoopMonitor.queryLatestValueByKpi", hadoopMonitorObj);
		} catch (Exception e) {
			LogHelper.error("HadoopMonitor.queryLatestValueByKpi: " + e.getMessage()
					+ e.getClass().getName());
		}
		return list;
	}

	/**
	 * @Title: queryForListByObjInMonthTable
	 * @Description: 查月表
	 * @param
	 * @return List<HadoopMonitorObj>
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2014-1-10 上午9:49:02
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<HadoopMonitorObj> queryForListByObjInMonthTable(HadoopMonitorObj hadoopMonitorObj) {
		List<HadoopMonitorObj> list = new ArrayList<HadoopMonitorObj>();
		try {
			list = getSqlMap().queryForList("HadoopMonitor.queryForListByObjInMonthTable",
					hadoopMonitorObj);
		} catch (Exception e) {
			LogHelper.error("HadoopMonitor.queryForListByObjInMonthTable: " + e.getMessage()
					+ e.getClass().getName());
		}
		return list;
	}

	/**
	 * @Title: queryKpiValueByHostList
	 * @Description: 通过主机查询kpi值
	 * @param
	 * @return List<HadoopMonitorObj>
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2014-1-11 上午10:25:38
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<HadoopMonitorObj> queryKpiValueByHostList(HadoopMonitorObj hadoopMonitorObj) {
		List<HadoopMonitorObj> list = new ArrayList<HadoopMonitorObj>();
		try {
			list = getSqlMap().queryForList("HadoopMonitor.queryKpiValueByHostList", hadoopMonitorObj);
		} catch (Exception e) {
			LogHelper.error("HadoopMonitor.queryKpiValueByHostList: " + e.getMessage() + e.getClass().getName());
		}
		return list;
	}
	/**
	 * 
	 * @Title: queryMonitorDataForHost
	 * @Description:查询主机对应的相关指标信息
	 * @param
	 * @return List<HadoopMonitorObj>
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime 2014-1-11 上午11:41:28
	 */
	public List<HadoopMonitorObj> queryMonitorDataForHost(HadoopMonitorObj hadoopMonitorObj) {
		List<HadoopMonitorObj> list = new ArrayList<HadoopMonitorObj>();
		try {
			list = getSqlMap().queryForList("HadoopMonitor.queryMonitorDataForHost",
					hadoopMonitorObj);
		} catch (Exception e) {
			LogHelper.error("HadoopMonitor.queryMonitorDataForHost: " + e.getMessage()
					+ e.getClass().getName());
		}
		return list;
	}

	/**
	 * @Title: queryForListOrderByValueDesc
	 * @Description: 根据值降序
	 * @param
	 * @return List<HadoopMonitorObj>
	 * @throws
	 * @author lipp
	 * @version 1.0
	 * @createtime 2014-2-26 下午4:36:30
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<HadoopMonitorObj> queryForListOrderByValueDesc(
			HadoopMonitorObj hadoopMonitorObj) {
		List<HadoopMonitorObj> list = new ArrayList<HadoopMonitorObj>();
		try {
			list = getSqlMap().queryForList("HadoopMonitor.queryForListOrderByValueDesc", hadoopMonitorObj);
		} catch (Exception e) {
			LogHelper.error("HadoopMonitor.queryForListOrderByValueDesc: " + e.getMessage() + e.getClass().getName());
		}
		return list;
	}

	/**
	 * 
	 * @Title: queryHostLastData
	 * @Description: 查询最一条数据
	 * @param
	 * @return HadoopMonitorObj
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-5-7 下午2:16:59
	 */
	public HadoopMonitorObj queryHostLastData(MapDataSet map) {
		HadoopMonitorObj obj = new HadoopMonitorObj();
		try {
			obj = (HadoopMonitorObj) getSqlMap().queryForObject(
					"HadoopMonitor.queryHostLastData", map);
		} catch (Exception e) {
			LogHelper.error("HadoopMonitor.queryHostLastData: "
					+ e.getMessage() + e.getClass().getName());
		}
		return obj;
	}
}
