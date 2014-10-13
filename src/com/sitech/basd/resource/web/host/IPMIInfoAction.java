package com.sitech.basd.resource.web.host;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.sitech.basd.resource.domain.host.IPMIInfo;
import com.sitech.basd.resource.service.host.IPMIInfoService;
import com.sitech.basd.sxcloud.rsmu.web.CRUDBaseAction;

/**
 * 
 * <p>
 * Title: IPMIInfoAction
 * </p>
 * <p>
 * Description:主机IPMI信息管理
 * </p>
 * <p>
 * Company: si-tech
 * </p>
 * 
 * @author duangh
 * @date 2013 九月 11
 */
@SuppressWarnings("serial")
@Controller("ipmiInfoAction")
public class IPMIInfoAction extends CRUDBaseAction {
	@Resource
	private IPMIInfoService ipmiInfoService;

	/**
	 * 
	 * @Title: listIPMIInfo
	 * @Description:信息列表
	 * @author duangh
	 * @date 2013 九月 11 12:00:03
	 * @return
	 */
	public String listIPMIInfo() {
		IPMIInfo ipmi = new IPMIInfo();
		ipmi.setPagination(this.getPaginater().initPagination(request));
		List<IPMIInfo> resultList = ipmiInfoService.queryForList(ipmi);
		ActionContext.getContext().getValueStack().push(resultList);
		return LIST;
	}

	/**
	 * 
	 * @Title: addIPMIInfo
	 * @Description:进入ipmi信息增加页面
	 * @author duangh
	 * @date 2013 九月 11 13:14:35
	 * @return
	 */
	public String addIPMIInfo() {
		return ADD;
	}

	/**
	 * 
	 * @Title: updateIPMIInfo
	 * @Description:进入IPMI修改页面
	 * @author duangh
	 * @date 2013 九月 11 13:15:18
	 * @return
	 */
	public String updateIPMIInfo() {
		return MODIFY;
	}

	/**
	 * 
	 * @Title: deleteIPMIInfo
	 * @Description:
	 * @author duangh
	 * @date 2013 九月 11 13:15:53
	 * @return
	 */
	public String deleteIPMIInfo() {
		return DEL;
	}
}
