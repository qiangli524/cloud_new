package com.sitech.basd.sxcloud.cloud.dao.ibmcoll;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.List;
import java.util.ResourceBundle;

import org.junit.Test;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;
import com.sitech.basd.component.domain.user.UserObj;
import com.sitech.basd.sxcloud.cloud.domain.globalconfig.TbGlobalConfigObj;
import com.sitech.basd.sxcloud.cloud.domain.ibmcoll.IBMCollObj;
import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;
import com.sitech.basd.sxcloud.util.EncryptUtil;
import com.sitech.basd.sxcloud.util.UUIDGenerator;

@SuppressWarnings("all")
public class IBMCollDaoImpl extends BaseDao implements IBMCollDao {
	
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
	public List queryPowerDayTimeLabel(IBMCollObj obj) {
		List lst = null;
		try {
			lst = getSqlMap().queryForList("IBMColl.queryPowerDayTimeLabel", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("IBMColl.queryPowerDayTimeLabel:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return lst;
	}
	
	/**
	 * 
	 * @Title: queryPowerDayTimeLabel
	 * @Description: 查询主机天的数据
	 * @param
	 * @return List
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime 2013-10-29 上午9:57:20
	 */
	public List queryPowerDayData(IBMCollObj obj) {
		List lst = null;
		try {
			lst = getSqlMap().queryForList("IBMColl.queryPowerDayData", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("IBMColl.queryPowerDayData:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return lst;
	}
	
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
	public List queryPowerWeekData(IBMCollObj obj) {
		List lst = null;
		try {
			lst = getSqlMap().queryForList("IBMColl.queryPowerWeekData", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("IBMColl.queryPowerWeekData:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return lst;
	}
	
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
	public List queryPowerMonthData(IBMCollObj obj) {
		List lst = null;
		try {
			lst = getSqlMap().queryForList("IBMColl.queryPowerMonthData", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("IBMColl.queryPowerMonthData:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return lst;
	}
	
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
	public List queryPowerYearData(IBMCollObj obj) {
		List lst = null;
		try {
			lst = getSqlMap().queryForList("IBMColl.queryPowerYearData", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("IBMColl.queryPowerYearData:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return lst;
	}
	
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
	public List queryAllCollHost(){
		List lst = null;
		try {
			lst = getSqlMap().queryForList("IBMColl.queryAllCollHost");
		} catch (Exception sqlexception) {
			LogHelper.error("IBMColl.queryPowerDayData:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return lst;
	}
	
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
	public List queryAllCollVm(){
		List lst = null;
		try {
			lst = getSqlMap().queryForList("IBMColl.queryAllCollVm");
		} catch (Exception sqlexception) {
			LogHelper.error("IBMColl.queryAllCollVm:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return lst;
	}
	
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
	public List queryLparDayData(IBMCollObj obj){
		List lst = null;
		try {
			lst = getSqlMap().queryForList("IBMColl.queryLparDayData",obj);
		} catch (Exception sqlexception) {
			LogHelper.error("IBMColl.queryLparDayData:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return lst;
	}
	
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
	public List queryLparWeekData(IBMCollObj obj){
		List lst = null;
		try {
			lst = getSqlMap().queryForList("IBMColl.queryLparWeekData",obj);
		} catch (Exception sqlexception) {
			LogHelper.error("IBMColl.queryLparWeekData:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return lst;
	}
	
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
	public List queryLparMonthData(IBMCollObj obj){
		List lst = null;
		try {
			lst = getSqlMap().queryForList("IBMColl.queryLparMonthData",obj);
		} catch (Exception sqlexception) {
			LogHelper.error("IBMColl.queryLparMonthData:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return lst;
	}
	
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
	public List queryLparYearData(IBMCollObj obj){
		List lst = null;
		try {
			lst = getSqlMap().queryForList("IBMColl.queryLparYearData",obj);
		} catch (Exception sqlexception) {
			LogHelper.error("IBMColl.queryLparYearData:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return lst;
	}
	
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
	public List queryLparDayTimeLabel(IBMCollObj obj){
		List lst = null;
		try {
			lst = getSqlMap().queryForList("IBMColl.queryLparDayTimeLabel",obj);
		} catch (Exception sqlexception) {
			LogHelper.error("IBMColl.queryLparDayTimeLabel:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return lst;
	}
	
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
	public List queryLparWeekTimeLabel(IBMCollObj obj){
		List lst = null;
		try {
			lst = getSqlMap().queryForList("IBMColl.queryLparWeekTimeLabel",obj);
		} catch (Exception sqlexception) {
			LogHelper.error("IBMColl.queryLparWeekTimeLabel:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return lst;
	}
	
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
	public List queryLparMonthTimeLabel(IBMCollObj obj){
		List lst = null;
		try {
			lst = getSqlMap().queryForList("IBMColl.queryLparMonthTimeLabel",obj);
		} catch (Exception sqlexception) {
			LogHelper.error("IBMColl.queryLparMonthTimeLabel:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return lst;
	}
	
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
	public List queryLparYearTimeLabel(IBMCollObj obj){
		List lst = null;
		try {
			lst = getSqlMap().queryForList("IBMColl.queryLparYearTimeLabel",obj);
		} catch (Exception sqlexception) {
			LogHelper.error("IBMColl.queryLparYearTimeLabel:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return lst;
	}
	
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
	public List queryPowerWeekTimeLabel(IBMCollObj obj){
		List lst = null;
		try {
			lst = getSqlMap().queryForList("IBMColl.queryPowerWeekTimeLabel",obj);
		} catch (Exception sqlexception) {
			LogHelper.error("IBMColl.queryPowerWeekTimeLabel:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return lst;
	}
	
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
	public List queryPowerMonthTimeLabel(IBMCollObj obj){
		List lst = null;
		try {
			lst = getSqlMap().queryForList("IBMColl.queryPowerMonthTimeLabel",obj);
		} catch (Exception sqlexception) {
			LogHelper.error("IBMColl.queryPowerMonthTimeLabel:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return lst;
	}
	
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
	public List queryPowerYearTimeLabel(IBMCollObj obj){
		List lst = null;
		try {
			lst = getSqlMap().queryForList("IBMColl.queryPowerYearTimeLabel",obj);
		} catch (Exception sqlexception) {
			LogHelper.error("IBMColl.queryPowerYearTimeLabel:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return lst;
	}
	
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
	public List queryPowerDayTrendData(IBMCollObj obj){
		List lst = null;
		try {
			lst = getSqlMap().queryForList("IBMColl.queryPowerDayTrendData",obj);
		} catch (Exception sqlexception) {
			LogHelper.error("IBMColl.queryPowerDayTrendData:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return lst;
	}
	
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
	public List queryPowerWeekTrendData(IBMCollObj obj){
		List lst = null;
		try {
			lst = getSqlMap().queryForList("IBMColl.queryPowerWeekTrendData",obj);
		} catch (Exception sqlexception) {
			LogHelper.error("IBMColl.queryPowerWeekTrendData:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return lst;
	}
	
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
	public List queryPowerMonthTrendData(IBMCollObj obj){
		List lst = null;
		try {
			lst = getSqlMap().queryForList("IBMColl.queryPowerMonthTrendData",obj);
		} catch (Exception sqlexception) {
			LogHelper.error("IBMColl.queryPowerMonthTrendData:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return lst;
	}
	
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
	public List queryPowerYearTrendData(IBMCollObj obj){
		List lst = null;
		try {
			lst = getSqlMap().queryForList("IBMColl.queryPowerYearTrendData",obj);
		} catch (Exception sqlexception) {
			LogHelper.error("IBMColl.queryPowerYearTrendData:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return lst;
	}
	
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
	public List queryLparDayTrendData(IBMCollObj obj){
		List lst = null;
		try {
			lst = getSqlMap().queryForList("IBMColl.queryLparDayTrendData",obj);
		} catch (Exception sqlexception) {
			LogHelper.error("IBMColl.queryLparDayTrendData:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return lst;
	}
	
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
	public List queryLparWeekTrendData(IBMCollObj obj){
		List lst = null;
		try {
			lst = getSqlMap().queryForList("IBMColl.queryLparWeekTrendData",obj);
		} catch (Exception sqlexception) {
			LogHelper.error("IBMColl.queryLparWeekTrendData:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return lst;
	}
	
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
	public List queryLparMonthTrendData(IBMCollObj obj){
		List lst = null;
		try {
			lst = getSqlMap().queryForList("IBMColl.queryLparMonthTrendData",obj);
		} catch (Exception sqlexception) {
			LogHelper.error("IBMColl.queryLparMonthTrendData:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return lst;
	}
	
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
	public List queryLparYearTrendData(IBMCollObj obj){
		List lst = null;
		try {
			lst = getSqlMap().queryForList("IBMColl.queryLparYearTrendData",obj);
		} catch (Exception sqlexception) {
			LogHelper.error("IBMColl.queryLparYearTrendData:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return lst;
	}
	
}
