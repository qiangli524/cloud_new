package com.sitech.ssd.ah.boss.dao.monitor;

import java.util.List;
import java.util.Map;

import com.sitech.basd.fusioncharts.vo.Data;
import com.sitech.ssd.ah.boss.domain.monitor.BossBusiMonitorObj;
import com.sitech.ssd.ah.busiMonitor.domain.MonitorCfgObj;

/**
 * <p>
 * Title: BossBusiMonitorDao
 * </p>
 * <p>
 * Description: Boss进程监控DAO
 * </p>
 * <p>
 * Copyright: Copyright (c) 2014
 * </p>
 * <p>
 * Company: SI-TECH
 * </p>
 * 
 * @author qism
 * @version 1.0
 * @createtime 2014-9-2 上午10:03:59
 * 
 */
public interface BossBusiMonitorDao {
	/**
	 * @Title: queryBusiMonitorObjList
	 * @Description: 查询集群下程序池的数据量(端口)
	 * @param
	 * @return List
	 * @throws
	 * @author qism
	 * @version 1.0
	 * @createtime 2014-9-2 上午11:29:41
	 */
	public List queryListForPort(BossBusiMonitorObj obj);

	/**
	 * @Title: queryListForProcess
	 * @Description: 查询某指定进程下业务监控指标数据(端口)
	 * @param
	 * @return List
	 * @throws
	 * @author qism
	 * @version 1.0
	 * @createtime 2014-9-2 上午10:10:01
	 */
	public List queryListForPortProcess(BossBusiMonitorObj obj);

	/**
	 * @Title: queryChartJSONOfPort
	 * @Description: 池子下端口数据收发返回data集合
	 * @param
	 * @return String
	 * @throws
	 * @author qism
	 * @version 1.0
	 * @createtime 2014-9-4 下午2:52:13
	 */
	public List<Data> queryChartJSONOfPort(BossBusiMonitorObj obj);

	/**
	 * @Title: queryBusiMonitorObjListFromHosi
	 * @Description: 历史:查询集群下池子的端口收发历史数据
	 * @param
	 * @return List
	 * @throws
	 * @author qism
	 * @version 1.0
	 * @createtime 2014-9-2 下午3:25:25
	 */
	public List queryHosiListForPort(BossBusiMonitorObj obj);

	/**
	 * @Title: queryHosiListForPortDesc
	 * @Description: 历史：详细
	 * @param
	 * @return List
	 * @throws
	 * @author qism
	 * @version 1.0
	 * @createtime 2014-9-5 上午10:33:04
	 */
	public List queryHosiListForPortDesc(BossBusiMonitorObj obj);

	/**
	 * @Title: queryChartJSONForPort
	 * @Description: 历史：池子折线图
	 * @param
	 * @return List<Data>
	 * @throws
	 * @author qism
	 * @version 1.0
	 * @createtime 2014-9-2 下午9:09:18
	 */
	public List<Data> queryHosiChartJSONForPool(BossBusiMonitorObj obj);

	/**
	 * @Title: queryListForErrorLog
	 * @Description: 返回错误日志列表
	 * @param
	 * @return List
	 * @throws
	 * @author qism
	 * @version 1.0
	 * @createtime 2014-9-9 下午2:44:51
	 */
	public List queryListForErrorLog(BossBusiMonitorObj obj);

	/**
	 * @Title: queryHosiChartJSONForLog
	 * @Description: 返回错误日志Data数据
	 * @param
	 * @return List<Data>
	 * @throws
	 * @author qism
	 * @version 1.0
	 * @createtime 2014-9-9 下午2:42:20
	 */
	public List<Data> queryHosiChartJSONForLog(BossBusiMonitorObj obj);

	/**
	 * @Title: queryForErrorLogDesc
	 * @Description: 错误日志的进程列表
	 * @param
	 * @return List
	 * @throws
	 * @author qism
	 * @version 1.0
	 * @createtime 2014-9-9 下午3:40:25
	 */
	public List queryForErrorLogDesc(BossBusiMonitorObj obj);

	/**
	 * @Title: queryListForDir
	 * @Description: 查询需要检测的主机和目录
	 * @param
	 * @return List
	 * @throws
	 * @author qism
	 * @version 1.0
	 * @createtime 2014-9-9 下午7:21:36
	 */
	public List queryListForMonitorDir(MonitorCfgObj obj);

	/**
	 * @Title: queryListForMonitorDirDesc
	 * @Description: 目录的监控数据
	 * @param
	 * @return List
	 * @throws
	 * @author qism
	 * @version 1.0
	 * @createtime 2014-9-10 上午10:15:39
	 */
	public List queryListForMonitorDirDesc(BossBusiMonitorObj obj);

	/**
	 * @Title: queryChartJSONForDir
	 * @Description: 获取目录积压Data数据
	 * @param
	 * @return List<Data>
	 * @throws
	 * @author qism
	 * @version 1.0
	 * @createtime 2014-9-9 下午7:36:44
	 */
	public List<Data> queryChartJSONForDirDesc(BossBusiMonitorObj obj);

	/**
	 * @Title: queryNctRatio
	 * @Description:获取话务量在线与离线量
	 * @param
	 * @return Map
	 * @throws
	 * @author qism
	 * @version 1.0
	 * @createtime 2014-9-20 上午11:03:54
	 */
	public Map queryNctRatio(BossBusiMonitorObj obj);

	/**
	 * @Title: queryHosiNctRatio
	 * @Description: 获取历史话务量在线与离线量
	 * @param
	 * @return Map
	 * @throws
	 * @author qism
	 * @version 1.0
	 * @createtime 2014-9-20 下午1:14:42
	 */
	public Map queryHosiNctRatio(BossBusiMonitorObj obj);
}
