package com.sitech.shop.action.bpm;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.sitech.basd.sxcloud.rsmu.web.BaseAction;
import com.sitech.shop.webservice.domain.TbOrderRelationInstance;
import com.sitech.shop.webservice.domain.TbOrderRelationInstanceExample;
import com.sitech.shop.webservice.service.OrderRelation;
import com.sitech.ssd.bpm.domain.CloudStep;
import com.sitech.ssd.bpm.domain.CloudWorkorder;
import com.sitech.ssd.bpm.domain.WorkFlow;
import com.sitech.ssd.bpm.service.CloudWorkOrderService;

/** 
* @ClassName: MaintenanceWorkOrderAction 
* @Description: 运维工单处理Action 
* @author wanglei_bj@si-tech.com.cn 
* @date 2014-8-3 下午4:21:47 
* @version 1.0 
*/
@Component("maintenanceWorkOrderAction")
@Scope("prototype")
public class MaintenanceWorkOrderAction extends BaseAction {
	private static final long serialVersionUID = 5491610489962030062L;
	Logger logger = LoggerFactory.getLogger(MaintenanceWorkOrderAction.class);
	//订购关系接口服务
	@Autowired
	private OrderRelation orderRelationService;
	@Autowired
	private CloudWorkOrderService workorderService;
	private CloudWorkorder workorder;
	private CloudStep step;
	private WorkFlow obj;
	private String info;
	private Long workflowId;
	private String statusType = "0";
	
 
	/**
	 * 运维客服查看工单界面
	 * @return String
	 */
	public String view(){
		return "view";
	}

	
	/**
	 * 运维查看运维工单界面
	 * @return String    返回类型 
	 * @author wanglei_bj@si-tech.com.cn 
	 * @throws 
	 * @createtime 2014-8-3
	*/
	public String maintenanceCheck(){
		return "maintenanceCheck";
	}
	
	/** 
	*
	* @Title: maintenanceDone 
	* @Description: 回调自服务门户订单执行方法
	* @param @return    设定文件 
	* @return String    返回类型 
	* @author wanglei_bj@si-tech.com.cn 
	* @throws 
	* @createtime 2014-8-3
	*/
	public String maintenanceDone(){
		logger.info("回调自服务门户订单执行方法:开始,订单ID："+obj.getFormId());
		try {
			TbOrderRelationInstanceExample obj2 = new TbOrderRelationInstanceExample();
			obj2.createCriteria().andOrderIdEqualTo(obj.getFormId());
			List<TbOrderRelationInstance> list2 = orderRelationService.selectOrderRelationList(obj2);
			String is_Init2 = list2.get(0).getBoattr5();
			orderRelationService.reset(obj.getFormId());
			orderRelationService.sendCloud(obj.getOrderNo(), (is_Init2.equals("1") ? true : false),false);
		} catch (Exception e) {
			logger.error("回调自服务门户订单执行方法:失败",e);
		}
		logger.info("回调自服务门户订单执行方法:结束");
		return "maintenance";
	}
	/**
	 * 运维查看运维工单界面
	 * @return String    返回类型 
	 * @author wanglei_bj@si-tech.com.cn 
	 * @throws 
	 * @createtime 2014-8-3
	*/
	public String maintenance(){
		return "maintenance";
	}
	
	
	public CloudStep getStep() {
		return step;
	}

	public void setStep(CloudStep step) {
		this.step = step;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public WorkFlow getObj() {
		return obj;
	}

	public void setObj(WorkFlow obj) {
		this.obj = obj;
	}

	public Long getWorkflowId() {
		return workflowId;
	}

	public void setWorkflowId(Long workflowId) {
		this.workflowId = workflowId;
	}

	public CloudWorkOrderService getWorkorderService() {
		return workorderService;
	}


	public void setWorkorderService(CloudWorkOrderService workorderService) {
		this.workorderService = workorderService;
	}

	public CloudWorkorder getWorkorder() {
		return workorder;
	}

	public void setWorkorder(CloudWorkorder workorder) {
		this.workorder = workorder;
	}


	public String getStatusType() {
		return statusType;
	}

	public void setStatusType(String statusType) {
		this.statusType = statusType;
	}
}
