package com.sitech.shop.action.bpm;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.sitech.basd.sxcloud.rsmu.domain.system.TbSysUserinfoObj;
import com.sitech.basd.sxcloud.rsmu.service.system.UserInfoService;
import com.sitech.basd.sxcloud.rsmu.web.BaseAction;
import com.sitech.shop.webservice.util.SendMessageUtil;
import com.sitech.ssd.billing.vo.sendMessage.MailSenderInfo;
import com.sitech.ssd.bpm.domain.CloudStep;
import com.sitech.ssd.bpm.domain.CloudWorkorder;
import com.sitech.ssd.bpm.domain.WorkFlow;
import com.sitech.ssd.bpm.service.CloudWorkOrderService;
import com.sitech.ssd.bpm.service.WorkFlowUserService;
import com.sitech.utils.jackson.JacksonUtil;
import com.sitech.utils.publicShop.MapUtil;
/** 
* @ClassName: CloudWorkOrderAction 
* @Description: TODO(工单流程中的相关操作) 
* @author wanglei_bj@si-tech.com.cn 
* @date 2014-5-28 下午6:23:11 
* @version 1.0 
*/
@Component("cloudWorkOrderAction")
@Scope("prototype")
public class CloudWorkOrderAction extends BaseAction {
	private static final long serialVersionUID = 5491610489962030062L;
	Logger logger = LoggerFactory.getLogger(CloudWorkOrderAction.class);
	@Autowired
	private CloudWorkOrderService workorderService;
	@Autowired
	private WorkFlowUserService workFlowUserService;
	@Autowired
	private UserInfoService userInfoService;
	private CloudWorkorder workorder = new CloudWorkorder();
	private CloudStep step;
	private WorkFlow obj;
	private String info;
	private Long workflowId;
	private List userList;
	private List groupList;
	private String statusType = "0";
	private String type;
	
	private TbSysUserinfoObj customer = new TbSysUserinfoObj();
	private List customerList;
	
	//故障类型
    private String malfunctionType;
    //故障停机时间
    private Float dateRange;
    //时间单位
    private String dateUnit;
    //是否转赔付
    private Integer isMakeUp = 0;
    
    private String user_id;
    
    /** 
    *
    * @Title: apply 
    * @Description: TODO(客户提交工单页面) 
    * @param @return    设定文件 
    * @return String    返回类型 
    * @author wanglei_bj@si-tech.com.cn 
    * @throws 
    */
    public String apply(){
    	return "apply";
    }
    
    /** 
    *
    * @Title: helpapply 
    * @Description: TODO(客服帮客户代填工单) 
    * @param @return    设定文件 
    * @return String    返回类型 
    * @author wanglei_bj@si-tech.com.cn 
    * @throws 
    */
    public String helpapply(){
    	return "helpapply";
    }
    
	/**
	 * 保存新建工单
	 * @return
	 */
	public String save() {
		logger.info("客户新建工单开始");
		user_id = session.get("id").toString();
		workorder = new CloudWorkorder();
		@SuppressWarnings("unchecked")
		Map<String,String> workorderMap = MapUtil.LStringCastToString(step.getObjData());
		workorder.setWorkorderName(obj.getResourceName());
		workorder.setQuestionType(workorderMap.get("type"));
		workorder.setQuestionContent(workorderMap.get("question"));
		workorder.setQuestionContent(workorderMap.get("content"));
		workorder.setContactPhone(workorderMap.get("phone"));
		workorder.setContactEmail(workorderMap.get("email"));
		workorder.setWorkflowId(workflowId);
		workorder.setUser_id(user_id);
		workorder.setCommitDate(new Date());
		try {
			workorderService.add(workorder);			
		} catch (Exception e) {
			logger.error("客户新建工单保存失败:",e);	
		}
		logger.debug(JacksonUtil.formatJson(JacksonUtil.toJson(workorder)));
		logger.info("客户新建工单结束");
		return "customer";
	}
	
	//客服代填工单
	public String helpSave() {
		logger.info("客服代填工单开始");
		user_id = session.get("id").toString();
		workorder = new CloudWorkorder();
		@SuppressWarnings("unchecked")
		Map<String,String> workorderMap = MapUtil.LStringCastToString(step.getObjData());
		workorder.setWorkorderName(obj.getResourceName());
		workorder.setQuestionType(workorderMap.get("type"));
		workorder.setQuestionContent(workorderMap.get("question"));
		workorder.setQuestionContent(workorderMap.get("content"));
		workorder.setContactPhone(workorderMap.get("phone"));
		workorder.setContactEmail(workorderMap.get("email"));
		workorder.setWorkflowId(workflowId);
		workorder.setUser_id(user_id);
		workorder.setApply_user_id(step.getCaller());
		workorder.setCommitDate(new Date());
		logger.debug(JacksonUtil.formatJson(JacksonUtil.toJson(workorder)));
		try {
			workorderService.add(workorder);			
		} catch (Exception e) {
			logger.debug("客服帮助填写工单保存失败:",e);			
		}
		logger.debug(JacksonUtil.formatJson(JacksonUtil.toJson(workorder)));
		logger.info("客服代填工单结束");
		return "service";
	}
	
	/**
	 * 客服审核点击工单已完成
	 * 给客户发短信
	 */
	public String workDoneSendMsg(){
		logger.info("工单处理完成，正在发送消息给客户");
		workorder = workorderService.query(workorder);		
		MailSenderInfo mailInfo = new MailSenderInfo(); 
		String content = workorder.getWorkflowId().toString();
		mailInfo.setPhoneNum(workorder.getContactPhone());
		mailInfo.setSmsContent(content);
		mailInfo.setContent(content);
		mailInfo.setToAddress(workorder.getContactEmail());	
		SendMessageUtil.sendMessage_template(mailInfo);
		logger.info("工单处理完成，消息发送完成");
		return "service";
	}
	/**
	 * 客服点击转赔付，更新工单信息。
	 * @return String
	 */
	public String updateMakeupInfo(){
		workorder = new CloudWorkorder();
		workorder.setWorkflowId(obj.getEntryId());
		workorder = workorderService.query(workorder);
		workorder.setIsMakeUp(isMakeUp);
		workorderService.update(workorder);
		return "evaluate";
	}
	
	/**
	 * 用户点击确定问题解决，更新工单信息。
	 * 并调转到评价界面
	 * @return String
	 */
	public String updateConfirmInfo(){
		Map<String,String> workorderMap = MapUtil.LStringCastToString(step.getObjData());		
		workorder = new CloudWorkorder();
		workorder.setWorkflowId(obj.getEntryId());
		workorder = workorderService.query(workorder);
		workorder.setIsOk(Integer.parseInt(workorderMap.get("isOk")));
		workorder.setConformDate(new Date());
		workorderService.update(workorder);
		return "evaluate";
	}
	
	/**
	 * 运维点击已处理问题解决，更新工单信息。
	 * 并调转-代办工单列表
	 * @return String
	 */
	public String updateMalfunctionInfo(){
		Map<String,String> workorderMap = MapUtil.LStringCastToString(step.getObjData());		
		workorder = new CloudWorkorder();
		workorder.setWorkflowId(obj.getEntryId());
		workorder = workorderService.query(workorder);
		workorder.setMalfunctionType(workorderMap.get("malfunctionType"));
		workorder.setDateRange(Float.parseFloat((workorderMap.get("dateRange"))));
		workorder.setDateUnit(workorderMap.get("dateUnit"));
		workorderService.update(workorder);
		return "mechanic";
	}
	
	/**
	 * 用户评价工单，更新工单信息。
	 * 评价成功
	 * @return String
	 */
	public String updateEvaluateInfo(){
		Map<String,String> workorderMap = MapUtil.LStringCastToString(step.getObjData());		
		workorder = new CloudWorkorder();
		workorder.setWorkflowId(obj.getEntryId());
		workorder = workorderService.query(workorder);
		workorder.setMalfunctionType(workorderMap.get("evaluateText"));
		workorder.setSatisfaction(Integer.parseInt(workorderMap.get("satisfaction")));
		workorder.setEvaluateDate(new Date());
		workorderService.update(workorder);
		return "customer";
	}
	
	

	/**
	 * 客户  我的工单界面
	 * @return String
	 */
	public String customer_my(){
		return "customer";
	}
	
	/**
	 * 客服  我的工单界面
	 * @return String
	 */
	public String service_my(){
		//userList = workFlowUserService.getUserList();
		groupList = workFlowUserService.getGroupList();
		return "service";
	}
	/**
	 * 运维  我的工单界面
	 * @return String
	 */
	public String mechanic_my(){
		return "mechanic";
	}
	
	/**
	 * 客户查看工单界面
	 * @return String
	 */
	public String customerView(){
		return "customerView";
	}
	/**
	 * 运维客服查看工单界面
	 * @return String
	 */
	public String view(){
		return "view";
	}
	/**
	 * 客户确定工单界面
	 * @return String
	 */
	public String confirm(){
		return "confirm";
	}
	
	/**
	 * 客户评价工单界面
	 * @return String
	 */
	public String evaluate(){
		return "evaluate";
	}
	
	/**
	 * 客户反馈工单界面
	 * @return String
	 */
	public String feedback(){
		return "feedback";
	}
	
	/**
	 * 客服查看工单界面
	 * @return String
	 */
	public String serviceCheck(){
		groupList = workFlowUserService.getGroupList();
		return "serviceCheck";
	}
	
	/**
	 * 客服验证工单界面
	 * @return String
	 */
	public String serviceVerify(){
		groupList = workFlowUserService.getGroupList();
		return "serviceVerify";
	}
	
	
	/**
	 * 运维查看工单界面
	 * @return String
	 */
	public String mechanicCheck(){
		groupList = workFlowUserService.getGroupList();
		return "mechanicCheck";
	}
	
	
	/** 
	*
	* @Title: queryCustomerList 
	* @Description: TODO(工作流使用，查询客户信息) 
	* @param @return    设定文件 
	* @return String    返回类型 
	* @author wanglei_bj@si-tech.com.cn 
	* @throws 
	* @createtime 2014-7-31
	*/
	public String queryCustomerList(){
		if(!"nothing".equals(customer.getACCOUNT())){
			customerList = userInfoService.queryLikeForListByObj(customer);
		}
		return "customer_list";
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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

	public String getMalfunctionType() {
		return malfunctionType;
	}

	public void setMalfunctionType(String malfunctionType) {
		this.malfunctionType = malfunctionType;
	}

	public Float getDateRange() {
		return dateRange;
	}

	public void setDateRange(Float dateRange) {
		this.dateRange = dateRange;
	}

	public String getDateUnit() {
		return dateUnit;
	}

	public void setDateUnit(String dateUnit) {
		this.dateUnit = dateUnit;
	}

	public Integer getIsMakeUp() {
		return isMakeUp;
	}

	public void setIsMakeUp(Integer isMakeUp) {
		this.isMakeUp = isMakeUp;
	}

	public List getUserList() {
		return userList;
	}

	public void setUserList(List userList) {
		this.userList = userList;
	}

	public List getGroupList() {
		return groupList;
	}

	public void setGroupList(List groupList) {
		this.groupList = groupList;
	}

	public String getStatusType() {
		return statusType;
	}

	public void setStatusType(String statusType) {
		this.statusType = statusType;
	}

	public TbSysUserinfoObj getCustomer() {
		return customer;
	}

	public void setCustomer(TbSysUserinfoObj customer) {
		this.customer = customer;
	}

	public List getCustomerList() {
		return customerList;
	}

	public void setCustomerList(List customerList) {
		this.customerList = customerList;
	}
	
}
