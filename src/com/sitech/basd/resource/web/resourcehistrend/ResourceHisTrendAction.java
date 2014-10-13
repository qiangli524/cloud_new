package com.sitech.basd.resource.web.resourcehistrend;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sitech.basd.resource.service.resourcehisrecord.ResourceHisRecordService;
import com.sitech.basd.sxcloud.rsmu.web.BaseAction;

/**
 * <p>
 * Title: ResourceHisTrendAction
 * </p>
 * <p>
 * Description: 资源总量和使用量历史走势图， 分类：网络 vmware虚拟机 xen虚拟机 主机
 * </p>
 * <p>
 * Copyright: Copyright (c) 2013
 * </p>
 * <p>
 * Company: SI-TECH
 * </p>
 * 
 * @author lipengpeng
 * @version 1.0
 * @createtime 2013-10-20 上午11:25:23
 * 
 */
@SuppressWarnings("serial")
@Scope("prototype")
@Controller("resourceHisTrendAction")
public class ResourceHisTrendAction extends BaseAction {

	private String resourceType;// 资源类型

	private String startTime;// 起始时间

	private String endTime;// 截止时间

	@Autowired
	private ResourceHisRecordService resourceHisRecordService;

	/**
	 * @Title: showHisTrend
	 * @Description: 跳转到走势图页面
	 * @param
	 * @return String
	 * @throws
	 * @author lipp
	 * @version 1.0
	 * @createtime 2013-11-29 下午5:29:14
	 */
	public String showHisTrend() {
		return "showHisTrend";
	}

	/**
	 * @Title: queryResourceHisReports
	 * @Description: 获取资源历史走势的报表，默认展示网络一个月的走势
	 * @param
	 * @return void
	 * @throws
	 * @author lipp
	 * @version 1.0
	 * @createtime 2013-11-29 上午11:35:04
	 */
	public void queryResourceHisReports() {
		// 默认资源类型
		if (isBlank(resourceType)) {
			resourceType = "1";
		}
		// 默认时间
		if (isBlank(endTime)) {
			endTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
			if (isBlank(startTime)) {
				startTime = this.getPreThirtyDays();
			}
		}
		String dataXml = resourceHisRecordService.buildDataXml(resourceType, startTime, endTime);
		try {
			response.setCharacterEncoding("UTF-8");
			// PrintWriter pw = response.getWriter();
			// pw.print(dataXml);
			// pw.flush();
			// pw.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @Title: getPreThirtyDays
	 * @Description: 获取30天前
	 * @param
	 * @return String
	 * @throws
	 * @author lipp
	 * @version 1.0
	 * @createtime 2013-11-29 下午12:47:10
	 */
	private String getPreThirtyDays() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) - 30);
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(calendar.getTime());
	}

	/**
	 * @Title: isBlank
	 * @Description: 判断是否为空
	 * @param
	 * @return boolean
	 * @throws
	 * @author lipp
	 * @version 1.0
	 * @createtime 2013-11-29 上午11:40:00
	 */
	private boolean isBlank(String value) {
		boolean flag = false;
		if (value == null || "".equals(value) || "null".equals(value)) {
			flag = true;
		}
		return flag;
	}

	public String getResourceType() {
		return resourceType;
	}

	public void setResourceType(String resourceType) {
		this.resourceType = resourceType;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
}
