package com.sitech.ssd.ah.reportform.vm.service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sitech.basd.fusioncharts.vo.Data;
import com.sitech.basd.sxcloud.rsmu.service.BaseService;
import com.sitech.ssd.ah.reportform.vm.dao.ReportFormVmDao;
import com.sitech.ssd.ah.reportform.vm.domain.VmReportForm;
import com.sitech.utils.date.TimeformatCommon;

@Service("reportformVM")
public class ReportformVMImpl extends BaseService implements
		ReportformVM {

	@Autowired
	private ReportFormVmDao reportFormVmDao;
	
	
	/**
	 * 
	 * @Title: queryHyTimeLabelAndData
	 * @Description: 查询虚拟机横坐标和监控数据
	 * @param
	 * @return List<Data>
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-11-25 下午4:17:13
	 */
	public List<VmReportForm> queryHyTimeLabelAndData(VmReportForm obj) {
		String startDate = obj.getStartDate();
		String endDate = obj.getEndDate();
		List<VmReportForm> datat = new ArrayList<VmReportForm>();
		if (StringUtils.isNotEmpty(startDate) && StringUtils.isNotEmpty(endDate)) {
			try {
				Date date1 = TimeformatCommon.TimeStringToDate(startDate,"yyyy-MM-dd hh:mm:ss");
				Date date2 = TimeformatCommon.TimeStringToDate(endDate,"yyyy-MM-dd hh:mm:ss");
				if ((date2.getTime() - date1.getTime()) > 0) {// 结束时间大于开始时间，可以执行查询
					Calendar c1 = Calendar.getInstance();
					c1.setTime(date1);
					Calendar c2 = Calendar.getInstance();
					c2.setTime(date2);
					String startTime = "",endTime = "";
					String year = c1.get(Calendar.YEAR) + "";
					String month = "",day = "";
					if (c1.get(Calendar.YEAR) == c2.get(Calendar.YEAR)) {// 目前不支持跨年查询，只可以查询一年之内的
						if (c1.get(Calendar.MONTH) == c2.get(Calendar.MONTH)) {// 查询范围在一个月内
							startTime = startDate;
							endTime = endDate;
							month = c2.get(Calendar.MONTH) + 1 + "";// Calendar.MONTH获取的月份是从0开始计算，需要加1
							//增加判断月份是一位，补全为两位月份
							month = month.length() == 1?"0"+month:month;
							if (c1.get(Calendar.DAY_OF_MONTH) == c2.get(Calendar.DAY_OF_MONTH)) {// 是同一天，按照天表计算
								Date dateCurrend = new Date();
								Calendar c = Calendar.getInstance();
								c.setTime(dateCurrend);
								if (c.get(Calendar.DAY_OF_MONTH) == c2.get(Calendar.DAY_OF_MONTH)) {// 如果是当天的，需要查询当天的表
									obj.setStartDate(startTime);
									obj.setEndDate(endTime);
									datat = reportFormVmDao.queryDataByCurrentDay(obj);
								}else {// 不是当天就查询那一天的表（表名拼接而成）
									day = c2.get(Calendar.DAY_OF_MONTH)+ "";
									day = new DecimalFormat("00").format(Long.parseLong(day + "")).toString();
									obj.setStartDate(startTime);
									obj.setEndDate(endTime);
									datat = reportFormVmDao.queryDataByDay(obj, year, month,day);
								}
							} else {// 不是同一天，按照月表计算
								obj.setStartDate(startTime);
								obj.setEndDate(endTime);
								datat = reportFormVmDao.queryDataByMonth(obj, year, month);
							}
						} else {
							// 跨月查询，c1是小月，c2是大月
							int m1 = c1.get(Calendar.MONTH) + 1;
							int m2 = c2.get(Calendar.MONTH) + 1;
							startTime = startDate;
							endTime = endDate;
							obj.setStartDate(startTime);
							obj.setEndDate(endTime);
							/**
							 * 需要从年表中获取数据，目前不支持跨月获取数据-------------------------------------//待开发
							 */
							for (int i = m1; i <= m2; i++) {
								List<VmReportForm> data1 = reportFormVmDao.queryDataByMonth(obj, year, i + "");
								datat.addAll(data1);
							}
						}
					} else {
						/**
						 * 待开发---
						 */
						System.out.println("跨年查询");
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else{//如过没有选择时间则查询当天的表
			datat = reportFormVmDao.queryDataByCurrentDay(obj);
		}
		return datat;
	}
	
	/**
	 * 
	 * @Title: queryHyTimeLabelAndData1
	 * @Description: 综合查询监控数据
	 * @param
	 * @return List<Data>
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-11-25 下午4:17:13
	 */
	public List<VmReportForm> queryHyTimeLabelAndData1(VmReportForm obj) {
		String startDate = obj.getStartDate();
		String endDate = obj.getEndDate();
		VmReportForm vm = new VmReportForm();
		vm.setStartDate(startDate);
		vm.setEndDate(endDate);
		/*********agent监控数据************/
		List<VmReportForm> agentData = reportFormVmDao.queryDataFromAgentData(vm);
		/*********虚拟化软件监控数据（仅需windows的）************/
		vm.setSysType("Microsoft");
		List<VmReportForm> vmData = queryHyTimeLabelAndData(vm);
		for(VmReportForm vmdata : vmData){
			for(VmReportForm agent : agentData){
				if(vmdata.getVmId().equals(agent.getVmId())){
					agent.setCpu_maxKpiValue(vmdata.getCpu_maxKpiValue());
					agent.setMem_maxKpiValue(vmdata.getMem_maxKpiValue());
					agent.setStoreUsed_maxKpiValue(vmdata.getStoreUsed_maxKpiValue());
				}
			}
		}
		return agentData;
	}
	
	/**
	 * 查询图形显示数据的列表
	 */
	public List<VmReportForm> queryHyTimeLabelAndDataForChartList(VmReportForm obj) {
		String startDate = obj.getStartDate();
		String endDate = obj.getEndDate();
		List<VmReportForm> datat = new ArrayList<VmReportForm>();
		if(!"".equals(obj.getVmId())){
			String[] arr = obj.getVmId().split(",");
			List<String> resultList = new ArrayList<String>();
			for (String str : arr) {
				resultList.add(str);
			}
			obj.setResultList(resultList);
			}
		if (StringUtils.isNotEmpty(startDate) && StringUtils.isNotEmpty(endDate)) {
			try {
				Date date1 = TimeformatCommon.TimeStringToDate(startDate,"yyyy-MM-dd hh:mm:ss");
				Date date2 = TimeformatCommon.TimeStringToDate(endDate,"yyyy-MM-dd hh:mm:ss");
				if ((date2.getTime() - date1.getTime()) > 0) {// 结束时间大于开始时间，可以执行查询
					Calendar c1 = Calendar.getInstance();
					c1.setTime(date1);
					Calendar c2 = Calendar.getInstance();
					c2.setTime(date2);
					String startTime = "",endTime = "";
					String year = c1.get(Calendar.YEAR) + "";
					String month = "",day = "";
					if (c1.get(Calendar.YEAR) == c2.get(Calendar.YEAR)) {// 目前不支持跨年查询，只可以查询一年之内的
						if (c1.get(Calendar.MONTH) == c2.get(Calendar.MONTH)) {// 查询范围在一个月内
							startTime = startDate;
							endTime = endDate;
							month = c2.get(Calendar.MONTH) + 1 + "";// Calendar.MONTH获取的月份是从0开始计算，需要加1
							//增加判断月份是一位，补全为两位月份
							month = month.length() == 1?"0"+month:month;
							if (c1.get(Calendar.DAY_OF_MONTH) == c2.get(Calendar.DAY_OF_MONTH)) {// 是同一天，按照天表计算
								Date dateCurrend = new Date();
								Calendar c = Calendar.getInstance();
								c.setTime(dateCurrend);
								if (c.get(Calendar.DAY_OF_MONTH) == c2.get(Calendar.DAY_OF_MONTH)) {// 如果是当天的，需要查询当天的表
									obj.setStartDate(startTime);
									obj.setEndDate(endTime);
									datat = reportFormVmDao.queryDataByCurrentDayForChartList(obj);
								}else {// 不是当天就查询那一天的表（表名拼接而成）
									day = c2.get(Calendar.DAY_OF_MONTH)+ "";
									day = new DecimalFormat("00").format(Long.parseLong(day + "")).toString();
									obj.setStartDate(startTime);
									obj.setEndDate(endTime);
									datat = reportFormVmDao.queryDataByDayForChartList(obj, year, month,day);
								}
							} else {// 不是同一天，按照月表计算
								obj.setStartDate(startTime);
								obj.setEndDate(endTime);
								datat = reportFormVmDao.queryDataByMonthForChartList(obj, year, month);
							}
						} else {
							// 跨月查询，c1是小月，c2是大月
							int m1 = c1.get(Calendar.MONTH) + 1;
							int m2 = c2.get(Calendar.MONTH) + 1;
							startTime = startDate;
							endTime = endDate;
							obj.setStartDate(startTime);
							obj.setEndDate(endTime);
							/**
							 * 需要从年表中获取数据，目前不支持跨月获取数据-------------------------------------//待开发
							 */
							for (int i = m1; i <= m2; i++) {
								List<VmReportForm> data1 = reportFormVmDao.queryDataByMonthForChartList(obj, year, i + "");
								datat.addAll(data1);
							}
						}
					} else {
						/**
						 * 待开发---
						 */
						System.out.println("跨年查询");
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else{//如过没有选择时间则查询当天的表
			datat = reportFormVmDao.queryDataByCurrentDayForChartList(obj);
		}
		return datat;
	}
	

	@Override
	public List<Data> queryHyTimeLabelAndDataForChart_cpu(VmReportForm obj) {
		String startDate = obj.getStartDate();
		String endDate = obj.getEndDate();
		List<Data> datat = new ArrayList<Data>();
		if (StringUtils.isNotEmpty(startDate) && StringUtils.isNotEmpty(endDate)) {
			try {
				Date date1 = TimeformatCommon.TimeStringToDate(startDate,"yyyy-MM-dd hh:mm:ss");
				Date date2 = TimeformatCommon.TimeStringToDate(endDate,"yyyy-MM-dd hh:mm:ss");
				if ((date2.getTime() - date1.getTime()) > 0) {// 结束时间大于开始时间，可以执行查询
					Calendar c1 = Calendar.getInstance();
					c1.setTime(date1);
					Calendar c2 = Calendar.getInstance();
					c2.setTime(date2);
					String startTime = "",endTime = "";
					String year = c1.get(Calendar.YEAR) + "";
					String month = "",day = "";
					if (c1.get(Calendar.YEAR) == c2.get(Calendar.YEAR)) {// 目前不支持跨年查询，只可以查询一年之内的
						if (c1.get(Calendar.MONTH) == c2.get(Calendar.MONTH)) {// 查询范围在一个月内
							startTime = startDate;
							endTime = endDate;
							month = c2.get(Calendar.MONTH) + 1 + "";// Calendar.MONTH获取的月份是从0开始计算，需要加1
							//增加判断月份是一位，补全为两位月份
							month = month.length() == 1?"0"+month:month;
							if (c1.get(Calendar.DAY_OF_MONTH) == c2.get(Calendar.DAY_OF_MONTH)) {// 是同一天，按照天表计算
								Date dateCurrend = new Date();
								Calendar c = Calendar.getInstance();
								c.setTime(dateCurrend);
								if (c.get(Calendar.DAY_OF_MONTH) == c2.get(Calendar.DAY_OF_MONTH)) {// 如果是当天的，需要查询当天的表
									obj.setStartDate(startTime);
									obj.setEndDate(endTime);
									datat = reportFormVmDao.queryDataByCurrentDayForChart_cpu(obj);
								}else {// 不是当天就查询那一天的表（表名拼接而成）
									day = c2.get(Calendar.DAY_OF_MONTH)+ "";
									day = new DecimalFormat("00").format(Long.parseLong(day + "")).toString();
									obj.setStartDate(startTime);
									obj.setEndDate(endTime);
									datat = reportFormVmDao.queryDataByDayForChart_cpu(obj, year, month,day);
								}
							} else {// 不是同一天，按照月表计算
								obj.setStartDate(startTime);
								obj.setEndDate(endTime);
								datat = reportFormVmDao.queryDataByMonthForChart_cpu(obj, year, month);
							}
						} else {
							// 跨月查询，c1是小月，c2是大月
							int m1 = c1.get(Calendar.MONTH) + 1;
							int m2 = c2.get(Calendar.MONTH) + 1;
							startTime = startDate;
							endTime = endDate;
							obj.setStartDate(startTime);
							obj.setEndDate(endTime);
							/**
							 * 需要从年表中获取数据，目前不支持跨越获取数据-------------------------------------//待开发
							 */
							for (int i = m1; i <= m2; i++) {
								List<Data> data1 = reportFormVmDao.queryDataByMonthForChart_cpu(obj, year, i + "");
								datat.addAll(data1);
							}
						}
					} else {
						/**
						 * 待开发---
						 */
						System.out.println("跨年查询");
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else{//如过没有选择时间则查询当天的表
			datat = reportFormVmDao.queryDataByCurrentDayForChart_cpu(obj);
		}
		return datat;
	}

	@Override
	public List<Data> queryHyTimeLabelAndDataForChart_men(VmReportForm obj) {
		String startDate = obj.getStartDate();
		String endDate = obj.getEndDate();
		List<Data> datat = new ArrayList<Data>();
		if (StringUtils.isNotEmpty(startDate) && StringUtils.isNotEmpty(endDate)) {
			try {
				Date date1 = TimeformatCommon.TimeStringToDate(startDate,"yyyy-MM-dd hh:mm:ss");
				Date date2 = TimeformatCommon.TimeStringToDate(endDate,"yyyy-MM-dd hh:mm:ss");
				if ((date2.getTime() - date1.getTime()) > 0) {// 结束时间大于开始时间，可以执行查询
					Calendar c1 = Calendar.getInstance();
					c1.setTime(date1);
					Calendar c2 = Calendar.getInstance();
					c2.setTime(date2);
					String startTime = "",endTime = "";
					String year = c1.get(Calendar.YEAR) + "";
					String month = "",day = "";
					if (c1.get(Calendar.YEAR) == c2.get(Calendar.YEAR)) {// 目前不支持跨年查询，只可以查询一年之内的
						if (c1.get(Calendar.MONTH) == c2.get(Calendar.MONTH)) {// 查询范围在一个月内
							startTime = startDate;
							endTime = endDate;
							month = c2.get(Calendar.MONTH) + 1 + "";// Calendar.MONTH获取的月份是从0开始计算，需要加1
							//增加判断月份是一位，补全为两位月份
							month = month.length() == 1?"0"+month:month;
							if (c1.get(Calendar.DAY_OF_MONTH) == c2.get(Calendar.DAY_OF_MONTH)) {// 是同一天，按照天表计算
								Date dateCurrend = new Date();
								Calendar c = Calendar.getInstance();
								c.setTime(dateCurrend);
								if (c.get(Calendar.DAY_OF_MONTH) == c2.get(Calendar.DAY_OF_MONTH)) {// 如果是当天的，需要查询当天的表
									obj.setStartDate(startTime);
									obj.setEndDate(endTime);
									datat = reportFormVmDao.queryDataByCurrentDayForChart_mem(obj);
								}else {// 不是当天就查询那一天的表（表名拼接而成）
									day = c2.get(Calendar.DAY_OF_MONTH)+ "";
									day = new DecimalFormat("00").format(Long.parseLong(day + "")).toString();
									obj.setStartDate(startTime);
									obj.setEndDate(endTime);
									datat = reportFormVmDao.queryDataByDayForChart_mem(obj, year, month,day);
								}
							} else {// 不是同一天，按照月表计算
								obj.setStartDate(startTime);
								obj.setEndDate(endTime);
								datat = reportFormVmDao.queryDataByMonthForChart_mem(obj, year, month);
							}
						} else {
							// 跨月查询，c1是小月，c2是大月
							int m1 = c1.get(Calendar.MONTH) + 1;
							int m2 = c2.get(Calendar.MONTH) + 1;
							startTime = startDate;
							endTime = endDate;
							obj.setStartDate(startTime);
							obj.setEndDate(endTime);
							/**
							 * 需要从年表中获取数据，目前不支持跨越获取数据-------------------------------------//待开发
							 */
							for (int i = m1; i <= m2; i++) {
								List<Data> data1 = reportFormVmDao.queryDataByMonthForChart_mem(obj, year, i + "");
								datat.addAll(data1);
							}
						}
					} else {
						/**
						 * 待开发---
						 */
						System.out.println("跨年查询");
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else{//如过没有选择时间则查询当天的表
			datat = reportFormVmDao.queryDataByCurrentDayForChart_mem(obj);
		}
		return datat;
	}
	@Override
	public List<Data> queryHyTimeLabelAndDataForChart_time(VmReportForm obj) {
		String startDate = obj.getStartDate();
		String endDate = obj.getEndDate();
		List<Data> datat = new ArrayList<Data>();
		if (StringUtils.isNotEmpty(startDate) && StringUtils.isNotEmpty(endDate)) {
			try {
				Date date1 = TimeformatCommon.TimeStringToDate(startDate,"yyyy-MM-dd hh:mm:ss");
				Date date2 = TimeformatCommon.TimeStringToDate(endDate,"yyyy-MM-dd hh:mm:ss");
				if ((date2.getTime() - date1.getTime()) > 0) {// 结束时间大于开始时间，可以执行查询
					Calendar c1 = Calendar.getInstance();
					c1.setTime(date1);
					Calendar c2 = Calendar.getInstance();
					c2.setTime(date2);
					String startTime = "",endTime = "";
					String year = c1.get(Calendar.YEAR) + "";
					String month = "",day = "";
					if (c1.get(Calendar.YEAR) == c2.get(Calendar.YEAR)) {// 目前不支持跨年查询，只可以查询一年之内的
						if (c1.get(Calendar.MONTH) == c2.get(Calendar.MONTH)) {// 查询范围在一个月内
							startTime = startDate;
							endTime = endDate;
							month = c2.get(Calendar.MONTH) + 1 + "";// Calendar.MONTH获取的月份是从0开始计算，需要加1
							//增加判断月份是一位，补全为两位月份
							month = month.length() == 1?"0"+month:month;
							if (c1.get(Calendar.DAY_OF_MONTH) == c2.get(Calendar.DAY_OF_MONTH)) {// 是同一天，按照天表计算
								Date dateCurrend = new Date();
								Calendar c = Calendar.getInstance();
								c.setTime(dateCurrend);
								if (c.get(Calendar.DAY_OF_MONTH) == c2.get(Calendar.DAY_OF_MONTH)) {// 如果是当天的，需要查询当天的表
									obj.setStartDate(startTime);
									obj.setEndDate(endTime);
									datat = reportFormVmDao.queryDataByCurrentDayForChart_time(obj);
								}else {// 不是当天就查询那一天的表（表名拼接而成）
									day = c2.get(Calendar.DAY_OF_MONTH)+ "";
									day = new DecimalFormat("00").format(Long.parseLong(day + "")).toString();
									obj.setStartDate(startTime);
									obj.setEndDate(endTime);
									datat = reportFormVmDao.queryDataByDayForChart_time(obj, year, month,day);
								}
							} else {// 不是同一天，按照月表计算
								obj.setStartDate(startTime);
								obj.setEndDate(endTime);
								datat = reportFormVmDao.queryDataByMonthForChart_time(obj, year, month);
							}
						} else {
							// 跨月查询，c1是小月，c2是大月
							int m1 = c1.get(Calendar.MONTH) + 1;
							int m2 = c2.get(Calendar.MONTH) + 1;
							startTime = startDate;
							endTime = endDate;
							obj.setStartDate(startTime);
							obj.setEndDate(endTime);
							/**
							 * 需要从年表中获取数据，目前不支持跨越获取数据-------------------------------------//待开发
							 */
							for (int i = m1; i <= m2; i++) {
								List<Data> data1 = reportFormVmDao.queryDataByMonthForChart_time(obj, year, i + "");
								datat.addAll(data1);
							}
						}
					} else {
						/**
						 * 待开发---
						 */
						System.out.println("跨年查询");
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else{//如过没有选择时间则查询当天的表
			datat = reportFormVmDao.queryDataByCurrentDayForChart_time(obj);
		}
		return datat;
	}

	@Override
	public List<VmReportForm> queryVmhostList(VmReportForm obj) {
		return reportFormVmDao.queryVmhostList(obj);
	}
	
}
