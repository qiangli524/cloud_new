package dao.monitor;

import java.util.List;
import java.util.Map;

import com.sitech.basd.fusioncharts.vo.Data;

import domain.kpi.HadoopKpiObj;
import domain.monitor.HadoopMonitorObj;
import domain.tab.MapDataSet;

public interface HadoopMonitorDao {
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
	public List queryForListByObj(HadoopMonitorObj obj);

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
	public List<HadoopMonitorObj> queryForLatestKpi(Map<String, Object> paramMap);

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
	public List<HadoopMonitorObj> queryHostKpiListByServiceNode(HadoopMonitorObj hadoopMonitorObj);

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
	public List<HadoopMonitorObj> queryLastestValueForKpi(HadoopMonitorObj hadoopMonitorObj);

	/**
	 * 查询某个集群某个主机某段时间的性能监控
	 * 
	 * @Title: queryHostMonitorDataByInterval
	 * @param
	 * @return List<Data>
	 * @throws
	 * @author shijc
	 * @version 1.0
	 * @createtime 2014-1-7 下午10:30:32
	 */
	public List<Data> queryHostMonitorDataByInterval(MapDataSet map);

	/**
	 * 通过服务ID 主机名称，集群名称查询一个主机所对应的服务下的所有kpiId
	 * 
	 * @Title: queryChartData
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param
	 * @return List<Data>
	 * @throws
	 * @author shijc
	 * @version 1.0
	 * @createtime 2014-1-8 下午3:27:30
	 */
	public List<Data> queryChartData(Map<String, String> map);

	/**
	 * 通过主机获取主机下所有的kpi指标
	 * 
	 * @Title: queryKpiIdDataByHost
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param
	 * @return List<String>
	 * @throws
	 * @author shijc
	 * @version 1.0
	 * @createtime 2014-1-8 下午4:11:44
	 */
	public List<HadoopKpiObj> queryKpiIdDataByHost(HadoopKpiObj obj);

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
	public List<HadoopMonitorObj> queryLatestValueByKpi(HadoopMonitorObj hadoopMonitorObj);

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
	public List<HadoopMonitorObj> queryForListByObjInMonthTable(HadoopMonitorObj hadoopMonitorObj);

	/**
	 * 按照时间倒序，并查询固定的点数
	 * 
	 * @Title: queryHostMonitorDataByPot
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param
	 * @return List<Data>
	 * @throws
	 * @author shijc
	 * @version 1.0
	 * @createtime 2014-1-10 下午1:31:00
	 */
	public List<Data> queryHostMonitorDataByPot(MapDataSet map);
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
	public List<HadoopMonitorObj> queryKpiValueByHostList(HadoopMonitorObj hadoopMonitorObj);
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
	public List<HadoopMonitorObj> queryMonitorDataForHost(HadoopMonitorObj hadoopMonitorObj);

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
	public List<HadoopMonitorObj> queryForListOrderByValueDesc(
			HadoopMonitorObj hadoopMonitorObj);

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
	public HadoopMonitorObj queryHostLastData(MapDataSet map);
}
