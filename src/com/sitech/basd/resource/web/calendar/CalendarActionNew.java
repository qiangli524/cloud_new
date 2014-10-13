package com.sitech.basd.resource.web.calendar;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sitech.basd.fusioncharts.util.JacksonUtil;
import com.sitech.basd.fusioncharts.vo.FusionCharts;
import com.sitech.basd.resource.domain.calendar.CalendarObj;
import com.sitech.basd.resource.service.calendar.CalendarServiceNew;
import com.sitech.basd.sxcloud.rsmu.web.BaseAction;
import com.sitech.utils.common.CommonUtil;
import com.sitech.utils.servlet.PrintWriterOut;
import com.sitech.utils.xml.XmlProperties;

/**
 * 
 * <p>
 * Title: CalendarActionNew
 * </p>
 * <p>
 * Description: 资源日历 统计资源信息
 * </p>
 * <p>
 * Copyright: Copyright (c) 2014
 * </p>
 * <p>
 * Company: SI-TECH
 * </p>
 * 
 * @author qism
 * @version 2.0
 * @createtime 2014-05-15 上午09:53:04
 * 
 */
@Controller("calendarActionNew")
@Scope("prototype")
public class CalendarActionNew extends BaseAction {
	private static final long serialVersionUID = 1L;
	private String nowTime;
	@Autowired
	public CalendarServiceNew calendarServiceNew;

	public CalendarObj calendarObj = new CalendarObj();

	private String fusionChartsString;
	private List<String> resultList = new ArrayList<String>();

	/**
	 * 
	 * @Title: showResource
	 * @Description: 类型(IBM,X86) 查询 xml文件
	 * @param
	 * @return String
	 * @throws
	 * @author qism
	 * @version 1.0
	 * @createtime 2014-05-15 上午10:49:18
	 */
	public String showResource() {
		//获取当前时间
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		nowTime =df.format(new Date());
		System.out.println("当前时间："+df);
		
		return "showResource";
	}

	public String getNowTime() {
		return nowTime;
	}

	public void setNowTime(String nowTime) {
		this.nowTime = nowTime;
	}

	/**
	 * 
	 * @Title: showCPUResource
	 * @Description: 查询CPU资源使用情况
	 * @param
	 * @return String
	 * @throws
	 * @author qism
	 * @version 1.0
	 * @createtime 2014-05-15 上午10:51:58
	 */
	public String showCPUResource() {
		FusionCharts fusionCharts = new FusionCharts();
		try {
			calendarObj.setProportion(8);
			fusionCharts = calendarServiceNew.queryCpuList(calendarObj);
		} catch (Exception e) {
			LOG.error("查询失败！" + e.getMessage() + e, e);
			fusionCharts = initQueryErrorCharts();
		}
		fusionChartsString = JacksonUtil.toJson(fusionCharts);
		// 传输json数据到界面
		printJson(fusionChartsString);
		return null;
	}

	/**
	 * 
	 * @Title: showMemResource
	 * @Description: 查询内存资源使用情况
	 * @param
	 * @return String
	 * @throws
	 * @author qism
	 * @version 1.0
	 * @createtime 2013-11-21 上午10:52:14
	 */
	public String showMemResource() {
		FusionCharts fusionCharts = new FusionCharts();
		try {
			fusionCharts = calendarServiceNew.queryMemList(calendarObj);
		} catch (Exception e) {
			LOG.error("查询失败！" + e.getMessage() + e, e);
			fusionCharts = initQueryErrorCharts();
		}
		fusionChartsString = JacksonUtil.toJson(fusionCharts);
		// 传输json数据到界面
		printJson(fusionChartsString);
		return null;
	}

	/**
	 * 
	 * @Title: showStorageResource
	 * @Description: 查询存储资源使用情况
	 * @param
	 * @return String
	 * @throws
	 * @author qism
	 * @version 1.0
	 * @createtime 2014-5-14 上午10:52:25
	 */
	public String showStorageResource() {
		FusionCharts fusionCharts = new FusionCharts();
		try {
			fusionCharts = calendarServiceNew.queryStorageList(calendarObj);
		} catch (Exception e) {
			LOG.error("查询失败！" + e.getMessage() + e, e);
			fusionCharts = initQueryErrorCharts();
		}
		fusionChartsString = JacksonUtil.toJson(fusionCharts);
		// 传输json数据到界面
		printJson(fusionChartsString);
		return null;
	}

	/**
	 * 
	 * @Title: showStorageResource
	 * @Description: 查询IP资源使用情况
	 * @param
	 * @return String
	 * @throws
	 * @author qism
	 * @version 1.0
	 * @createtime 2014-5-14  上午10:52:25
	 */
	public String showNetResource(){
		FusionCharts fusionCharts = new FusionCharts();
		try {
			fusionCharts = calendarServiceNew.queryNetList(calendarObj);
		} catch (Exception e) {
			LOG.error("查询失败！" + e.getMessage() + e, e);
			fusionCharts = initQueryErrorCharts();
		}
		fusionChartsString = JacksonUtil.toJson(fusionCharts);
		// 传输json数据到界面
		printJson(fusionChartsString);
		return null;
		
	}
	/**
	 * 
	 * @Title: printJson
	 * @Description: 传输Json数据到界面
	 * @param
	 * @return void
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-9-16 上午10:61:00
	 */
	private synchronized void printJson(String... params) {
		response.setCharacterEncoding("UTF-8");
		// out = response.getWriter();
		if (params != null) {
			for (int i = 0; i < params.length; i++) {
				// out.println(params[i]);
				PrintWriterOut.printWirter(response, params[i]);
			}
		}
		// out.close();
	}

	/**
	 * 
	 * @Title: initQueryErrorCharts
	 * @Description: 当查询失败时，显示报表
	 * @param
	 * @return FusionCharts
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-9-14 上午9:45:04
	 */
	private FusionCharts initQueryErrorCharts() {
		FusionCharts fusionCharts = new FusionCharts();
		return fusionCharts;
	}

	public CalendarObj getCalendarObj() {
		return calendarObj;
	}

	public void setCalendarObj(CalendarObj calendarObj) {
		this.calendarObj = calendarObj;
	}

	public String getFusionChartsString() {
		return fusionChartsString;
	}

	public void setFusionChartsString(String fusionChartsString) {
		this.fusionChartsString = fusionChartsString;
	}

	public List<String> getResultList() {
		return resultList;
	}

	public void setResultList(List<String> resultList) {
		this.resultList = resultList;
	}

}
