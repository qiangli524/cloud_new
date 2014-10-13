package com.sitech.basd.sxcloud.cloud.dao.ibmcoll;

import java.util.List;

import com.sitech.basd.sxcloud.cloud.domain.globalconfig.TbGlobalConfigObj;
import com.sitech.basd.sxcloud.cloud.domain.ibmcoll.IBMCollObj;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;

public interface IBMCollDao {

	/**
	 * 
	 * @Title: queryPowerDayTimeLabel
	 * @Description: 查询天表的横坐标
	 * @param
	 * @return List
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime 2013-10-29 上午9:57:20
	 */
	public List queryPowerDayTimeLabel(IBMCollObj obj);
	
	/**
	 * 
	 * @Title: queryPowerDayTimeLabel
	 * @Description: 查询天的数据
	 * @param
	 * @return List
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime 2013-10-29 上午9:57:20
	 */
	public List queryPowerDayData(IBMCollObj obj);
	
	/**
	 * 
	 * @Title: queryPowerWeekData
	 * @Description: 查询主机周的数据
	 * @param
	 * @return List
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime 2013-10-29 上午9:57:20
	 */
	public List queryPowerWeekData(IBMCollObj obj);
	
	/**
	 * 
	 * @Title: queryPowerMonthData
	 * @Description: 查询主机月的数据
	 * @param
	 * @return List
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime 2013-10-29 上午9:57:20
	 */
	public List queryPowerMonthData(IBMCollObj obj);
	
	/**
	 * 
	 * @Title: queryPowerYearData
	 * @Description: 查询主机年的数据
	 * @param
	 * @return List
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime 2013-10-29 上午9:57:20
	 */
	public List queryPowerYearData(IBMCollObj obj);
	
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
	
	/**
	 * 
	 * @Title: queryLparDayData
	 * @Description: 查询虚拟机上一天的数据
	 * @param
	 * @return List
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime 2013-10-30 上午9:06:19
	 */
	public List queryLparDayData(IBMCollObj obj);
	
	/**
	 * 
	 * @Title: queryLparWeekData
	 * @Description: 查询虚拟机上一周的数据
	 * @param
	 * @return List
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime 2013-10-30 上午9:06:03
	 */
	public List queryLparWeekData(IBMCollObj obj);
	
	/**
	 * 
	 * @Title: queryLparMonthData
	 * @Description: 查询虚拟机上一月的数据
	 * @param
	 * @return List
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime 2013-10-30 上午9:05:45
	 */
	public List queryLparMonthData(IBMCollObj obj);
	
	/**
	 * 
	 * @Title: queryLparYearData
	 * @Description: 查询虚拟机上一年的数据
	 * @param
	 * @return List
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime 2013-10-30 上午9:05:21
	 */
	public List queryLparYearData(IBMCollObj obj);
	
	/**
	 * 
	 * @Title: queryLparDayTimeLabel
	 * @Description: 查询虚拟机天表横坐标数据
	 * @param
	 * @return List
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime 2013-10-30 上午9:21:17
	 */
	public List queryLparDayTimeLabel(IBMCollObj obj);
	
	/**
	 * 
	 * @Title: queryLparWeekTimeLabel
	 * @Description: 查询虚拟机周表横坐标数据
	 * @param
	 * @return List
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime 2013-10-30 上午10:10:37
	 */
	public List queryLparWeekTimeLabel(IBMCollObj obj);
	
	/**
	 * 
	 * @Title: queryLparMonthTimeLabel
	 * @Description: 查询虚拟机月表横坐标数据
	 * @param
	 * @return List
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime 2013-10-30 上午10:10:37
	 */
	public List queryLparMonthTimeLabel(IBMCollObj obj);
	
	/**
	 * 
	 * @Title: queryLparYearTimeLabel
	 * @Description: 查询虚拟机年表横坐标数据
	 * @param
	 * @return List
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime 2013-10-30 上午10:10:37
	 */
	public List queryLparYearTimeLabel(IBMCollObj obj);
	
	/**
	 * 
	 * @Title: queryPowerWeekTimeLabel
	 * @Description: 查询主机年周横坐标数据
	 * @param
	 * @return List
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime 2013-10-30 上午10:10:37
	 */
	public List queryPowerWeekTimeLabel(IBMCollObj obj);
	
	/**
	 * 
	 * @Title: queryPowerMonthTimeLabel
	 * @Description: 查询主机年月横坐标数据
	 * @param
	 * @return List
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime 2013-10-30 上午10:10:37
	 */
	public List queryPowerMonthTimeLabel(IBMCollObj obj);
	
	/**
	 * 
	 * @Title: queryPowerYearTimeLabel
	 * @Description: 查询主机年横坐标数据
	 * @param
	 * @return List
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime 2013-10-30 上午10:10:37
	 */
	public List queryPowerYearTimeLabel(IBMCollObj obj);
	
	/**
	 * 
	 * @Title: queryPowerDayTrendData
	 * @Description: 查询主机天表使用率数据
	 * @param
	 * @return List
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime 2013-10-30 下午2:38:28
	 */
	public List queryPowerDayTrendData(IBMCollObj obj);
	
	/**
	 * 
	 * @Title: queryPowerWeekTrendData
	 * @Description: 查询主机周表使用率数据
	 * @param
	 * @return List
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime 2013-10-30 下午2:38:28
	 */
	public List queryPowerWeekTrendData(IBMCollObj obj);
	
	/**
	 * 
	 * @Title: queryPowerMonthTrendData
	 * @Description: 查询主机月表使用率数据
	 * @param
	 * @return List
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime 2013-10-30 下午2:38:28
	 */
	public List queryPowerMonthTrendData(IBMCollObj obj);
	
	/**
	 * 
	 * @Title: queryPowerYearTrendData
	 * @Description: 查询主机年表使用率数据
	 * @param
	 * @return List
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime 2013-10-30 下午2:38:28
	 */
	public List queryPowerYearTrendData(IBMCollObj obj);
	
	/**
	 * 
	 * @Title: queryLparDayTrendData
	 * @Description: 查询虚拟机天表使用率数据
	 * @param
	 * @return List
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime 2013-10-30 下午2:38:28
	 */
	public List queryLparDayTrendData(IBMCollObj obj);
	
	/**
	 * 
	 * @Title: queryLparWeekTrendData
	 * @Description: 查询虚拟机周表使用率数据
	 * @param
	 * @return List
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime 2013-10-30 下午2:38:28
	 */
	public List queryLparWeekTrendData(IBMCollObj obj);
	
	/**
	 * 
	 * @Title: queryLparMonthTrendData
	 * @Description: 查询虚拟机月表使用率数据
	 * @param
	 * @return List
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime 2013-10-30 下午2:38:28
	 */
	public List queryLparMonthTrendData(IBMCollObj obj);
	
	/**
	 * 
	 * @Title: queryLparYearTrendData
	 * @Description: 查询虚拟机年表使用率数据
	 * @param
	 * @return List
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime 2013-10-30 下午2:38:28
	 */
	public List queryLparYearTrendData(IBMCollObj obj);
}
