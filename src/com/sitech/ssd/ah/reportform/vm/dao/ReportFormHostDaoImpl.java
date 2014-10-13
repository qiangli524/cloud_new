package com.sitech.ssd.ah.reportform.vm.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;
import com.sitech.basd.fusioncharts.vo.Data;
import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;
import com.sitech.ssd.ah.reportform.vm.domain.VmReportForm;

@Repository("reportFormHostDao")
public class ReportFormHostDaoImpl extends BaseDao implements ReportFormVmDao {
	//先确定表名，后续通过表名进行查询
	private VmReportForm convertMap(VmReportForm obj,String year, String month, String day,String flag) {
		String tableName = null;	
		if (flag.equals("day")) {//当天
			tableName = "TB_CLOUD2_HOST_COLL";
		}else if (flag.equals("userDefined")) {//查询月表
			//day = day.length() == 1?"0"+day:day;
			month = month.length() == 1?"0"+month:month;
			String temp = year + month;
			tableName = "TB_CLOUD2_HOST_COLL_" + temp;
		} else if (flag.equals("userDefinedMonth")) {//查询某月某天的表
			day = day.length() == 1?"0"+day:day;
			month = month.length() == 1?"0"+month:month;
			String temp = year + month + day;
			tableName = "TB_CLOUD2_HOST_COLL_" + temp;
		}
		System.err.println(tableName);
		obj.setTableName(tableName);
		return obj;
	}
	

	/**
	 * 从当天的天表中获取时间段内的值
	 */
	@Override
	public List<VmReportForm> queryDataByCurrentDay(VmReportForm obj) {
		List lst = new ArrayList();
		obj = convertMap(obj,null, null,null,"day");
		try {
			if (obj.getPagination() != null) {
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj.getPagination().setTotalCount(
						((Integer) getSqlMap().queryForObject("ReportFormHost.queryAllHostCount", obj))
								.intValue());
			}
			lst = getSqlMap().queryForList(
						"ReportFormHost.queryHostReportFormData", obj);
		} catch (Exception sqlexception) {
			sqlexception.printStackTrace();
			LogHelper.error("ReportFormHost.queryHostReportFormData:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;
	}

	/**
	 * 获取数据按月表查询
	 */
	@Override
	public List<VmReportForm> queryDataByMonth(VmReportForm obj,String year, String month) {
		List lst = new ArrayList();
		obj= convertMap(obj, year, month,null,"userDefined");
		try {
			if (obj.getPagination() != null) {
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj.getPagination().setTotalCount(
						((Integer) getSqlMap().queryForObject("ReportFormHost.queryAllHostCount", obj))
								.intValue());
			}
			lst = getSqlMap().queryForList(
					"ReportFormHost.queryHostReportFormData", obj);
		} catch (Exception sqlexception) {
			sqlexception.printStackTrace();
			LogHelper.error("ReportFormHost.queryHostReportFormData:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;
	}

	@Override
	public List<VmReportForm> queryDataByDay(VmReportForm obj,String year, String month, String day) {
		List lst = new ArrayList();
		obj = convertMap(obj, year, month,day,"userDefinedMonth");
		try {
			if (obj.getPagination() != null) {
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj.getPagination().setTotalCount(
						((Integer) getSqlMap().queryForObject("ReportFormHost.queryAllHostCount", obj))
								.intValue());
			}
			lst = getSqlMap().queryForList(
					"ReportFormHost.queryHostReportFormData", obj);
		} catch (Exception sqlexception) {
			sqlexception.printStackTrace();
			LogHelper.error("ReportFormHost.queryVMReportFormData:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;
	}
	

	/******************查询CPU，由于日期查询可分三种表********************/
	@Override
	public List<Data> queryDataByCurrentDayForChart_cpu(VmReportForm obj) {
		obj = convertMap(obj,null, null,null,"day");
		List<Data> list = new ArrayList<Data>();
		try {
			if("top".equals(obj.getTop_line())){
				list = getSqlMap().queryForList(
					"ReportFormHost.queryVMIndexNameLable_cpu", obj);
			}else{
				list = getSqlMap().queryForList(
						"ReportFormHost.queryVMIndexNameLable__zx",obj);
			}
		} catch (SQLException e) {
			LogHelper.error("ReportFormHost.queryVMIndexNameLable_cpu:"
					+ e.getMessage() + getClass().getName());
		}
		return list;
	}


	@Override
	public List<Data> queryDataByMonthForChart_cpu(VmReportForm obj, String year,
			String month) {
		obj= convertMap(obj, year, month,null,"userDefined");
		List<Data> list = new ArrayList<Data>();
		try {
			if("top".equals(obj.getTop_line())){
				list = getSqlMap().queryForList(
					"ReportFormHost.queryVMIndexNameLable_cpu", obj);
			}else{
				list = getSqlMap().queryForList(
					"ReportFormHost.queryVMIndexNameLable__zx", obj);	
			}
		} catch (SQLException e) {
			LogHelper.error("ReportFormHost.queryVMIndexNameLable_cpu:"
					+ e.getMessage() + getClass().getName());
		}
		return list;
	}


	@Override
	public List<Data> queryDataByDayForChart_cpu(VmReportForm obj, String year,
			String month, String day) {
		obj = convertMap(obj, year, month,day,"userDefinedMonth");
		List<Data> list = new ArrayList<Data>();
		try {
			if("top".equals(obj.getTop_line())){
				list = getSqlMap().queryForList(
						"ReportFormHost.queryVMIndexNameLable_cpu", obj);
				}else{
				list = getSqlMap().queryForList(
						"ReportFormHost.queryVMIndexNameLable__zx", obj);	
				}
		} catch (SQLException e) {
			LogHelper.error("ReportFormHost.queryVMIndexNameLable_cpu:"
					+ e.getMessage() + getClass().getName());
		}
		return list;
	}

	/******************查询内存,由于日期查询可分三种表********************/
	@Override
	public List<Data> queryDataByCurrentDayForChart_mem(VmReportForm obj) {
		obj = convertMap(obj,null, null,null,"day");
		List<Data> list = new ArrayList<Data>();
		try {
			
			list = getSqlMap().queryForList(
					"ReportFormHost.queryVMIndexNameLable_mem", obj);
		} catch (SQLException e) {
			LogHelper.error("ReportFormHost.queryVMIndexNameLable_mem:"
					+ e.getMessage() + getClass().getName());
		}
		return list;
	}


	@Override
	public List<Data> queryDataByMonthForChart_mem(VmReportForm obj,
			String year, String month) {
		obj= convertMap(obj, year, month,null,"userDefined");
		List<Data> list = new ArrayList<Data>();
		try {			
			list = getSqlMap().queryForList(
					"ReportFormHost.queryVMIndexNameLable_mem", obj);
		} catch (SQLException e) {
			LogHelper.error("ReportFormHost.queryVMIndexNameLable_mem:"
					+ e.getMessage() + getClass().getName());
		}
		return list;
	}


	@Override
	public List<Data> queryDataByDayForChart_mem(VmReportForm obj, String year,
			String month, String day) {
		obj = convertMap(obj, year, month,day,"userDefinedMonth");
		List<Data> list = new ArrayList<Data>();
		try {			
			list = getSqlMap().queryForList(
					"ReportFormHost.queryVMIndexNameLable_mem", obj);
		} catch (SQLException e) {
			LogHelper.error("ReportFormHost.queryVMIndexNameLable_mem:"
					+ e.getMessage() + getClass().getName());
		}
		return list;
	}


	@Override
	public List<VmReportForm> queryDataByCurrentDayForChartList(VmReportForm obj) {
		List lst = new ArrayList();
		obj = convertMap(obj,null, null,null,"day");
		try {
			lst = getSqlMap().queryForList(
					"ReportFormHost.queryVMReportFormDataForChartList", obj);
		} catch (Exception sqlexception) {
			sqlexception.printStackTrace();
			LogHelper.error("ReportFormHost.queryVMReportFormDataForChartList:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;
	}


	@Override
	public List<VmReportForm> queryDataByMonthForChartList(VmReportForm obj,
			String year, String month) {
		@SuppressWarnings("rawtypes")
		List lst = new ArrayList();
		obj= convertMap(obj, year, month,null,"userDefined");
		try {
			lst = getSqlMap().queryForList(
					"ReportFormHost.queryVMReportFormDataForChartList", obj);
		} catch (Exception sqlexception) {
			sqlexception.printStackTrace();
			LogHelper.error("ReportFormHost.queryVMReportFormDataForChartList:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;
	}


	@Override
	public List<VmReportForm> queryDataByDayForChartList(VmReportForm obj,
			String year, String month, String day) {
		List lst = new ArrayList();
		obj = convertMap(obj, year, month,day,"userDefinedMonth");
		try {
			lst = getSqlMap().queryForList(
					"ReportFormHost.queryVMReportFormDataForChartList", obj);
		} catch (Exception sqlexception) {
			sqlexception.printStackTrace();
			LogHelper.error("ReportFormHost.queryVMReportFormDataForChartList:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<Data> queryDataByCurrentDayForChart_time(VmReportForm obj) {
		obj = convertMap(obj,null, null,null,"day");
		List<Data> list = new ArrayList<Data>();
		try {
			list = getSqlMap().queryForList(
					"ReportFormHost.queryVMIndexNameLable_time", obj);
		} catch (SQLException e) {
			LogHelper.error("ReportFormHost.queryVMIndexNameLable_time:"
					+ e.getMessage() + getClass().getName());
		}
		return list;
	}


	@Override
	public List<Data> queryDataByMonthForChart_time(VmReportForm obj,
			String year, String month) {
		obj= convertMap(obj, year, month,null,"userDefined");
		List<Data> list = new ArrayList<Data>();
		try {			
			list = getSqlMap().queryForList(
					"ReportFormHost.queryVMIndexNameLable_time", obj);
		} catch (SQLException e) {
			LogHelper.error("ReportFormHost.queryVMIndexNameLable_time:"
					+ e.getMessage() + getClass().getName());
		}
		return list;
	}


	@Override
	public List<Data> queryDataByDayForChart_time(VmReportForm obj,
			String year, String month, String day) {
		obj = convertMap(obj, year, month,day,"userDefinedMonth");
		List<Data> list = new ArrayList<Data>();
		try {			
			list = getSqlMap().queryForList(
					"ReportFormHost.queryVMIndexNameLable_time", obj);
		} catch (SQLException e) {
			LogHelper.error("ReportFormHost.queryVMIndexNameLable_time:"
					+ e.getMessage() + getClass().getName());
		}
		return list;
	}

	/**
	 * 
	 * @Title: queryDataFromAgentData
	 * @Description: 查询agent采集的数据
	 * @param
	 * @return List<VmReportForm>
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @createtime 2014年6月5日17:23:00
	 */
	public List<VmReportForm> queryDataFromAgentData(VmReportForm obj){
		List<VmReportForm> list = new ArrayList<VmReportForm>();
		try {			
			list = getSqlMap().queryForList(
					"ReportFormHost.queryVMReportFormAgentData", obj);
		} catch (SQLException e) {
			LogHelper.error("ReportFormHost.queryVMReportFormAgentData:"
					+ e.getMessage() + getClass().getName());
		}
		return list;
	}
	//查询虚拟机列表
		@Override
		public List<VmReportForm> queryVmhostList(VmReportForm obj) {
			List<VmReportForm> list = new ArrayList<VmReportForm>();
			try {
				if (obj.getPagination() != null) {
					obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
					obj.setPAGESIZE(obj.getPagination().getPageSize());
					obj.getPagination().setTotalCount(
							((Integer) getSqlMap().queryForObject("ReportFormHost.queryVmhostListForCount", obj))
									.intValue());
				}
				list = getSqlMap().queryForList(
						"ReportFormHost.queryVmhostList", obj);
			} catch (SQLException e) {
				LogHelper.error("ReportFormHost.queryVmhostList:"
						+ e.getMessage() + getClass().getName());
			}
			return list;
		}

}
