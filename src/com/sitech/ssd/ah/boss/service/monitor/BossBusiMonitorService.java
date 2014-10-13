package com.sitech.ssd.ah.boss.service.monitor;

import java.util.List;
import java.util.Map;

import com.sitech.basd.fusioncharts.vo.FusionCharts;
import com.sitech.ssd.ah.boss.domain.monitor.BossBusiMonitorObj;
import com.sitech.ssd.ah.busiMonitor.domain.MonitorCfgObj;

/**
 * <p>
 * Title: BossBusiMonitorService
 * </p>
 * <p>
 * Description: Boss进程监控服务接口
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
 * @createtime 2014-9-2 上午10:07:03
 * 
 */
public interface BossBusiMonitorService {
	/**
	 * @Title: queryBusiMonitorObjListForPort
	 * @Description: 查询集群下程序池的数据量(端口)
	 * @param
	 * @return List
	 * @throws
	 * @author qism
	 * @version 1.0
	 * @createtime 2014-9-2 上午11:48:11
	 */
	public List queryListForPort(BossBusiMonitorObj obj);

	/**
	 * @Title: queryListForProcessForPort
	 * @Description: 查询某指定进程下的业务监控指标数据(端口)
	 * @param
	 * @return List
	 * @throws
	 * @author qism
	 * @version 1.0
	 * @createtime 2014-9-2 上午11:48:19
	 */
	public List queryListForPortProcess(BossBusiMonitorObj obj);

	/**
	 * @Title: queryChartJSONOfPort
	 * @Description 当前池子数据展示(端口)
	 * @param
	 * @return String
	 * @throws
	 * @author qism
	 * @version 1.0
	 * @createtime 2014-9-4 下午2:49:36
	 */
	public String queryChartJSONOfPort(BossBusiMonitorObj obj);

	/**
	 * @Title: queryBusiMonitorObjListFromHosi
	 * @Description: 历史：集群下池子的端口收发历史数据(按天)
	 * @param
	 * @return List
	 * @throws
	 * @author qism
	 * @version 1.0
	 * @createtime 2014-9-2 下午7:39:15
	 */
	public List queryHosiListForPort(BossBusiMonitorObj obj);

	/**
	 * @Title: queryHosiListForPortDesc
	 * @Description: 历史: 某天某池子下每小时详细 列表
	 * @param
	 * @return List
	 * @throws
	 * @author qism
	 * @version 1.0
	 * @createtime 2014-9-5 上午10:30:09
	 */
	public List queryHosiListForPortDesc(BossBusiMonitorObj obj);

	/**
	 * @Title: queryChartJSONForPort
	 * @Description: 历史： 池子下端口数据收发折线图
	 * @param
	 * @return String
	 * @throws
	 * @author qism
	 * @version 1.0
	 * @createtime 2014-9-2 下午9:07:29
	 */
	public String queryHosiChartJSONForPool(BossBusiMonitorObj obj);

	/**
	 * @Title: queryHosiChartJSONForLog
	 * @Description: 获取错误日志列表
	 * @param
	 * @return List
	 * @throws
	 * @author qism
	 * @version 1.0
	 * @createtime 2014-9-9 下午2:41:29
	 */
	public String queryHosiChartJSONForLog(BossBusiMonitorObj obj);

	/**
	 * @Title: queryListForLog
	 * @Description: 获取错误日志列表
	 * @param
	 * @return List
	 * @throws
	 * @author qism
	 * @version 1.0
	 * @createtime 2014-9-9 下午2:43:59
	 */
	public List queryListForErrorLog(BossBusiMonitorObj obj);

	/**
	 * @Title: queryForErrorLogDesc
	 * @Description: 获取错误日志详细进程列表
	 * @param
	 * @return List
	 * @throws
	 * @author qism
	 * @version 1.0
	 * @createtime 2014-9-9 下午3:38:28
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
	 * @createtime 2014-9-9 下午7:18:21
	 */
	public List queryListForMonitorDir(MonitorCfgObj obj);

	/**
	 * @Title: queryListForMonitorDirDesc
	 * @Description: 查询目录对应的监控数据
	 * @param
	 * @return List
	 * @throws
	 * @author qism
	 * @version 1.0
	 * @createtime 2014-9-10 上午10:11:31
	 */
	public List queryListForMonitorDirDesc(BossBusiMonitorObj obj);

	/**
	 * @Title: queryChartJSONForDir
	 * @Description: 得到目录文件积压图形数据
	 * @param
	 * @return String
	 * @throws
	 * @author qism
	 * @version 1.0
	 * @createtime 2014-9-9 下午7:35:23
	 */
	public String queryChartJSONForDirDesc(BossBusiMonitorObj obj);

	/**
	 * @Title: queryNctRatio
	 * @Description: 获取话务量在线与离线量
	 * @param
	 * @return Map
	 * @throws
	 * @author qism
	 * @version 1.0
	 * @createtime 2014-9-20 上午11:04:31
	 */
	public Map queryNctRatio(BossBusiMonitorObj obj);

	/**
	 * @Title: queryNctRatioChartJSONF
	 * @Description: 获取话务量在线、离线的JSON数据
	 * @param
	 * @return FusionCharts
	 * @throws
	 * @author qism
	 * @version 1.0
	 * @createtime 2014-9-20 上午11:06:13
	 */
	public FusionCharts queryNctRatioChartJSONF(BossBusiMonitorObj obj);

	/**
	 * @Title: queryHosiNctRatio
	 * @Description: 获取历史话务量在线与离线量
	 * @param
	 * @return Map
	 * @throws
	 * @author qism
	 * @version 1.0
	 * @createtime 2014-9-20 下午1:12:41
	 */
	public Map queryHosiNctRatio(BossBusiMonitorObj obj);

	/**
	 * @Title: queryHosiNctRatioChartJSONF
	 * @Description: 获取历史话务量在线、离线的JSON数据
	 * @param
	 * @return FusionCharts
	 * @throws
	 * @author qism
	 * @version 1.0
	 * @createtime 2014-9-20 下午1:13:02
	 */
	public FusionCharts queryHosiNctRatioChartJSONF(BossBusiMonitorObj obj);
}
