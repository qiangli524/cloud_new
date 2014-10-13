package com.sitech.basd.sxcloud.cloud.service.showview;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sitech.basd.fusioncharts.util.JacksonUtil;
import com.sitech.basd.fusioncharts.vo.FusionCharts;

public interface ShowViewService {
	
	/**
	 * 
	 * @Title: queryAllCollHost
	 * @Description: 获取所有采集的主机
	 * @param
	 * @return void
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime 2013-10-29 下午7:12:53
	 */
	public List queryAllCollHost();
	
	/**
	 * 
	 * @Title: queryChartDayData
	 * @Description: 查询天表数据
	 * @param
	 * @return FusionCharts
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime 2013-10-29 下午8:17:12
	 */
	public FusionCharts queryChartDayData(String machineType,String collType,String collDataType,String machineId);
	
	
	/**
	 * 
	 * @Title: queryChartWeekData
	 * @Description: 查询周表数据
	 * @param
	 * @return String
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime 2013-10-30 上午10:04:58
	 */
	public FusionCharts queryChartWeekData(String machineType,String collType,String collDataType,String machineId);
	
	/**
	 * 
	 * @Title: queryChartWeekData
	 * @Description: 查询月表数据
	 * @param
	 * @return String
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime 2013-10-30 上午10:04:58
	 */
	public FusionCharts queryChartMonthData(String machineType,String collType,String collDataType,String machineId);
	
	/**
	 * 
	 * @Title: queryChartWeekData
	 * @Description: 查询年表数据
	 * @param
	 * @return String
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime 2013-10-30 上午10:04:58
	 */
	public FusionCharts queryChartYearData(String machineType,String collType,String collDataType,String machineId);
	
	/**
	 * 
	 * @Title: queryAllCollVm
	 * @Description: 查询所有虚拟机
	 * @param
	 * @return List
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime 2013-10-30 上午8:45:25
	 */
	public List queryAllCollVm();
}
