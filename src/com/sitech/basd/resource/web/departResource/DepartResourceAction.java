package com.sitech.basd.resource.web.departResource;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sitech.basd.cloud3.domain.departproject.DepartInfoObj;
import com.sitech.basd.resource.service.departResource.DepartResourceService;
import com.sitech.basd.sxcloud.rsmu.web.BaseAction;
import com.sitech.utils.servlet.PrintWriterOut;

@Controller("departResourceAction")
@Scope("prototype")
@SuppressWarnings("serial")
public class DepartResourceAction extends BaseAction {

	@Autowired
	private DepartResourceService departResourceService;

	private String resourceType;// 资源类型 1cpu 2mem 3store 4ip

	private String startTime;// 起始时间

	private String endTime;// 截止时间

	private List<DepartInfoObj> departList;

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

	public List<DepartInfoObj> getDepartList() {
		return departList;
	}

	public void setDepartList(List<DepartInfoObj> departList) {
		this.departList = departList;
	}

	/**
	 * @Title: showDepartResourceTrend
	 * @Description: 显示部门预算分配走势图，默认展示第一个部门10天内cpu的历史走势
	 * @param
	 * @return void
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-11-13 下午4:01:59
	 */
	public void showDepartResourceTrend() {
		// 预设展示的资源类型
		if (resourceType == null || "".equals(resourceType)) {
			resourceType = "1";
		}
		// 预设时间
		if (endTime == null || "".equals(endTime)) {
			endTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
			if (startTime == null || "".equals(startTime)) {
				startTime = this.getPreTenDay();
			}
		}
		// 查询所有部门
		DepartInfoObj departInfoObj = new DepartInfoObj();
		departInfoObj.setType("1");
		departList = departResourceService.queryForDepartList(departInfoObj);
		String chartXml = departResourceService.buildXmlData(resourceType, departList, startTime,
				endTime);
		try {
			response.setCharacterEncoding("UTF-8");
			// PrintWriter pw = response.getWriter();
			// pw.write(chartXml);
			PrintWriterOut.printWirter(response, chartXml);
			// pw.flush();
			// pw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @Title: getPreTenDay
	 * @Description: 获取10天前
	 * @param
	 * @return String
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-11-17 下午8:15:58
	 */
	private String getPreTenDay() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) - 10);
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(calendar.getTime());
	}

	/**
	 * @Title: showDetail
	 * @Description: 跳转到弹出页
	 * @param
	 * @return String
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-11-17 下午11:11:11
	 */
	public String showDetail() {
		return "showDetail";
	}

}
