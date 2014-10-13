package com.sitech.basd.workflow.service;

import java.util.List;

import com.sitech.basd.workflow.domain.TbWorkFlow;
import com.sitech.basd.workflow.domain.TbWorkFlowData;
import com.sitech.ssd.bpm.domain.CloudStep;
import com.sitech.ssd.bpm.domain.WorkFlow;

/**
 * 流程使用service，主要用于流程数据的存取
 * @author jiangdi
 *
 */
public interface WorkFlowService {

	/**
	 * 保存流程数据
	 * @param obj
	 * @param step
	 * @param proxy
	 * @return
	 */
	public void addWorkFlow(WorkFlow obj,List<TbWorkFlowData> datas, CloudStep step);
	
	/**
	 * 根据workFlowId获取workFlowData数据
	 * @param workFlowId
	 * @return	list
	 */
	public List<TbWorkFlowData> getWorkFlowData(int workFlowId);
	
	/**
	 * 获取当前登录用户所属流程申请，创建成功的虚拟机。用于资源变更流程，资源回收流程
	 * @param creator		流程创建人
	 * @return
	 */
	public List<TbWorkFlowData> getVMList(String creator);
	
	/**
	 * 获取所有过期虚拟机
	 * @return
	 */
	public List getVmForValidate();
	
	/**
	 * 获取流程统计信息
	 * @param creator
	 * @return
	 */
	public List getWorkFlowStatisticInfo(String creator);
	
	public void addValidateWorkFlow(int workflowid);
	
	/**
	 * 通过id获取tb_workflow数据
	 * @param id
	 */
	public TbWorkFlow getTbWorkFlowObj(int id);
	
	/**
	 * 删除过期虚拟机待办工单
	 * @param workFlowId
	 * @param entryId
	 */
	public void deleteOutDataWorkFlow(int workFlowId, long entryId);
	
	public String getEntryIdByVmName(String vmName);
}
