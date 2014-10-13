package com.sitech.basd.cloud3.web.charts.action;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;

import com.opensymphony.xwork2.Action;
import com.sitech.basd.sxcloud.cloud.service.showresource.ShowResourceService;
import com.sitech.basd.sxcloud.rsmu.web.BaseAction;
import com.sitech.utils.servlet.PrintWriterOut;

/**
 * 
 * <p>
 * Title: HomePageResource
 * </p>
 * <p>
 * Description:首页展示资源信息 <action name="homePage_">
 * </p>
 * <p>
 * Company: si-tech
 * </p>
 * 
 * @author duangh
 * @date Jul 9, 2013
 */
@Controller("homePageResource")
public class HomePageResource extends BaseAction {
	private String type;
	private String rownum;
	@Resource
	private ShowResourceService showResourceService;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getRownum() {
		return rownum;
	}

	public void setRownum(String rownum) {
		this.rownum = rownum;
	}

	/**
	 * 
	 * @Title: totalResource
	 * @Description:首页展示资源信息
	 * @author duangh
	 * @date Jul 9, 2013 2:53:53 PM
	 * @return
	 */
	public String totalResource() {
		return Action.SUCCESS;
	}

	/**
	 * 
	 * @Title: getResourceData
	 * @Description:
	 * @author duangh
	 * @date Jul 9, 2013 4:33:26 PM
	 * @return
	 */
	public String getResourceData() {
		String highChartsData = showResourceService.getResourceData(type, rownum);
		// PrintWriter p = response.getWriter();
		// p.print(highChartsData);
		PrintWriterOut.printWirter(response, highChartsData);
		// p.flush();
		// p.close();
		return null;
	}

	/**
	 * 
	 * @Title: validate
	 * @Description:struts2验证,传入的type,rownum不能为空
	 * @author duangh
	 * @date Jul 9, 2013 6:12:09 PM
	 */
	public void validate() {
		Assert.notNull("type", "type is requried");
		Assert.notNull("rownum", "rownum is requried");
	}
}
