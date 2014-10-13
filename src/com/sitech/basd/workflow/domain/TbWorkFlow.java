package com.sitech.basd.workflow.domain;

import java.io.Serializable;
import java.util.Date;

public class TbWorkFlow  implements Serializable{
	//主键递增
		private Integer id;
		//实例ID，对应工作流引擎中的实例
		private Long entryId;
		//工单编号  默认和实例ID一样
		private String orderNo;
		//工单标题
		private String orderTitle;
		//工单类型
		private String orderType ="01";
		//资源类型
		private String resType="01";
		//创建人
		private String creator;
		//创建时间
		private Date createTime;
		//下一步执行人
		private String nextUser;
		//下一步执行组
		private String nextGroup;
		//流程名称   重要属性，对应工作流引擎中的流程,不带版本号，如publicCloud
		private String resourceName; 
		//紧急程度 0123，默认普通
		private Integer orderLevel;
		//状态 0 草稿 1 有效 2删除
		private Integer orderStatus=1;
		//运行状态 0正在运行 1结束 5停止
		private Integer runStatus;
		//表单ID 北京公有云用到
		private String formId;
		public Integer getId() {
			return id;
		}
		public void setId(Integer id) {
			this.id = id;
		}
		public Long getEntryId() {
			return entryId;
		}
		public void setEntryId(Long entryId) {
			this.entryId = entryId;
		}
		public String getOrderNo() {
			return orderNo;
		}
		public void setOrderNo(String orderNo) {
			this.orderNo = orderNo;
		}
		public String getOrderTitle() {
			return orderTitle;
		}
		public void setOrderTitle(String orderTitle) {
			this.orderTitle = orderTitle;
		}
		public String getOrderType() {
			return orderType;
		}
		public void setOrderType(String orderType) {
			this.orderType = orderType;
		}
		public String getResType() {
			return resType;
		}
		public void setResType(String resType) {
			this.resType = resType;
		}
		public String getCreator() {
			return creator;
		}
		public void setCreator(String creator) {
			this.creator = creator;
		}
		public Date getCreateTime() {
			return createTime;
		}
		public void setCreateTime(Date createTime) {
			this.createTime = createTime;
		}
		public String getNextUser() {
			return nextUser;
		}
		public void setNextUser(String nextUser) {
			this.nextUser = nextUser;
		}
		public String getNextGroup() {
			return nextGroup;
		}
		public void setNextGroup(String nextGroup) {
			this.nextGroup = nextGroup;
		}
		public String getResourceName() {
			return resourceName;
		}
		public void setResourceName(String resourceName) {
			this.resourceName = resourceName;
		}
		public Integer getOrderLevel() {
			return orderLevel;
		}
		public void setOrderLevel(Integer orderLevel) {
			this.orderLevel = orderLevel;
		}
		public Integer getOrderStatus() {
			return orderStatus;
		}
		public void setOrderStatus(Integer orderStatus) {
			this.orderStatus = orderStatus;
		}
		public Integer getRunStatus() {
			return runStatus;
		}
		public void setRunStatus(Integer runStatus) {
			this.runStatus = runStatus;
		}
		public String getFormId() {
			return formId;
		}
		public void setFormId(String formId) {
			this.formId = formId;
		}
		public String getFormType() {
			return formType;
		}
		public void setFormType(String formType) {
			this.formType = formType;
		}
		public String getFormData() {
			return formData;
		}
		public void setFormData(String formData) {
			this.formData = formData;
		}
		public String getAttchmentId() {
			return attchmentId;
		}
		public void setAttchmentId(String attchmentId) {
			this.attchmentId = attchmentId;
		}
		public Integer getStepId() {
			return stepId;
		}
		public void setStepId(Integer stepId) {
			this.stepId = stepId;
		}
		public String getStepName() {
			return stepName;
		}
		public void setStepName(String stepName) {
			this.stepName = stepName;
		}
		public String getUserId() {
			return userId;
		}
		public void setUserId(String userId) {
			this.userId = userId;
		}
		//表单类型   北京公有云用到
		private String formType;
		//环节1的表单数据 查询用到
		private String formData;
		//附件id
		private String attchmentId;
		//步骤ID
		private Integer stepId;
		//步骤名称
		private String stepName;
		//工单归属人
		private String userId;
}
