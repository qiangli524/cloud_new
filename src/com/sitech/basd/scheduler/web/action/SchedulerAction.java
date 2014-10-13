package com.sitech.basd.scheduler.web.action;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import scheduler.SchedulerParameterObj;

import com.sitech.basd.scheduler.domain.task.ResourceTaskObj;
import com.sitech.basd.scheduler.domain.task.ResourceTaskObjExample;
import com.sitech.basd.scheduler.domain.task.ResourceTaskObjExample.Criteria;
import com.sitech.basd.scheduler.service.ResourceSchedulerService;
import com.sitech.basd.scheduler.web.form.SchedulerTaskForm;
import com.sitech.basd.sxcloud.rsmu.web.CRUDBaseAction;
import com.sitech.basd.sxcloud.rsmu.web.util.exception.BaseException;
import com.sitech.basd.sxcloud.util.Struts2Utils;
import com.sitech.utils.common.CommonUtil;
import com.sitech.utils.date.DateUtil;
import com.sitech.utils.quartz.CronUtil;
import com.sitech.utils.quartz.QuartzConstant;

@Controller("schedulerAction")
@Scope("prototype")
public class SchedulerAction extends CRUDBaseAction {

	private static final long serialVersionUID = 1L;

	private SchedulerTaskForm theForm;

	@Autowired
	private ResourceSchedulerService resourceSchedulerService;

	/**
	 * @Title:获取任务列表
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author liming_bj
	 * @version 1.0
	 */
	public String schedulerTaskList() throws BaseException {
		
		if (!CommonUtil.isNotEmpty(theForm)) {theForm = new SchedulerTaskForm();}
		
		// 创建条件实体
		ResourceTaskObjExample rtobj = new ResourceTaskObjExample();
		//排序条件
		rtobj.setOrderByClause("created_date DESC");
		// 建立条件对象
		Criteria criteria = rtobj.createCriteria();
		
		// 虚拟机名称
		if (CommonUtil.isNotEmpty(theForm.getVhName())) {
			criteria.andVhNameLike("%" + theForm.getVhName() + "%");
		}
		// 虚拟机IP
		if (CommonUtil.isNotEmpty(theForm.getVhIp())) {
			criteria.andVhIpEqualTo(theForm.getVhIp());
		}
		// 执行动作
		if (CommonUtil.isNotEmpty(theForm.getExecuteAction())) {
			criteria.andExecuteActionEqualTo(theForm.getExecuteAction());
		}
		// 执行开始时间
		if (CommonUtil.isNotEmpty(theForm.getExecuteStartDate())) {
			criteria.andExecuteStartDateGreaterThanOrEqualTo(DateUtil.dateStringParseDate(theForm.getExecuteStartDate()));
		}
		// 执行结束时间
		if (CommonUtil.isNotEmpty(theForm.getExecuteEndDate())) {
			criteria.andExecuteEndDateLessThanOrEqualTo(DateUtil.dateStringParseDate(theForm.getExecuteEndDate()));
		}
		// 执行状态
		if (CommonUtil.isNotEmpty(theForm.getExecuteState())) {
			criteria.andExecuteStateEqualTo(theForm.getExecuteState());
		}
		//设备类型
		if (CommonUtil.isNotEmpty(theForm.getVmType())) {
			criteria.andVmTypeEqualTo(theForm.getVmType());
		}
		criteria.andExecuteActionIsNotNull();
		// 分页信息
		rtobj.setPagination(this.getPaginater().initPagination(Struts2Utils.getRequest()));// 分页
		// 查询数据
		List<?> resultList = resourceSchedulerService.selectByExampleList(rtobj);
		// 存入结果集
		theForm.setResultList(resultList);
		return LIST;
	}

	
	/**
	 * 删除任务
	 * @return 
	 * @throws Exception 
	 */
	public String deleteSchedulerTask() throws Exception {

		// 删除类型 1：任务删除 2：任务草稿删除
		String deleteType = Struts2Utils.getRequest().getParameter("deleteType");

		if (!CommonUtil.isNotEmpty(theForm)) {theForm = new SchedulerTaskForm();}

		// 删除任务
		String[] taskid = theForm.getTaskId();
		for (String id : taskid) {
			resourceSchedulerService.deleteByPrimaryKey(id);
		}
		return deleteType.equals("1") ? REDIRECT : "redirectSetjob";
	}
	
	/**
	 * 已经添加设备但是无设定执行条件的列表
	 * @return 
	 * @throws Exception 
	 */
	public String setjob() throws Exception {
		if (!CommonUtil.isNotEmpty(theForm)) {theForm = new SchedulerTaskForm();}
		
		// 创建条件实体
		ResourceTaskObjExample rtobj = new ResourceTaskObjExample();
		// 建立条件对象
		Criteria criteria = rtobj.createCriteria();
		//添加条件
		criteria.andExecuteActionIsNull();
		// 分页信息
		rtobj.setPagination(this.getPaginater().initPagination(Struts2Utils.getRequest()));// 分页
		// 查询数据
		List<?> resultList = resourceSchedulerService.selectByExampleList(rtobj);
		// 存入结果集
		theForm.setResultList(resultList);

		return "setjob";
	}
	
	/**
	 * 将选择的虚拟机加入任务表（为设定执行条件）
	 * @return 
	 * @throws Exception 
	 */
	public String add() throws Exception {
		if (!CommonUtil.isNotEmpty(theForm)) {theForm = new SchedulerTaskForm();}
		String[] uuid = Struts2Utils.getRequest().getParameterValues("theForm.VH_UUID");
		//将选择的虚拟机增加到任务表
		resourceSchedulerService.addvm(uuid);
		return "showvm";
	}
	
	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public String setExecuteJob(){

		String executeStartDate = Struts2Utils.getRequest().getParameter("executeStartDate");
		String executeEndDate= Struts2Utils.getRequest().getParameter("executeEndDate");
		String executeAction= Struts2Utils.getRequest().getParameter("executeAction");
		String executeCronExpression= Struts2Utils.getRequest().getParameter("executeCronExpression");
		
		// 创建条件实体
		ResourceTaskObjExample rtobj = new ResourceTaskObjExample();
		// 建立条件对象
		Criteria criteria = rtobj.createCriteria();
		criteria.andExecuteActionIsNull();		
		// resultList未执行的任务
		List<?> resultList = resourceSchedulerService.selectByExampleList(rtobj);		
		//调度器参数对象
		SchedulerParameterObj schedulerParameterObj = new SchedulerParameterObj();
		//任务开始执行时间
		schedulerParameterObj.setStartDate(DateUtil.dateStringParseDate(executeStartDate));
		//任务结束执行时间
		schedulerParameterObj.setEndDate(DateUtil.dateStringParseDate(executeEndDate));
		//执行时间表达式(Cron表达式需要转换)
		schedulerParameterObj.setCronExpression(CronUtil.StringToCorn(executeCronExpression,QuartzConstant.EVERY_TYPE_DAY));
		
		List <String>taskIdList=new ArrayList<String>();
		for (Object resourceTaskObj : resultList) {
			ResourceTaskObj rto=(ResourceTaskObj) resourceTaskObj;
			rto.setExecuteStartDate(DateUtil.dateStringParseDate(executeStartDate));
			rto.setExecuteEndDate(DateUtil.dateStringParseDate(executeEndDate));
			rto.setExecuteAction(executeAction);
			//待执行
			rto.setExecuteState("0");
			rto.setExecuteCronExpression(executeCronExpression);
			resourceSchedulerService.update(rto);
			taskIdList.add(rto.getTaskId());
		}		
		
		//任务加入调度器
		try {
			resourceSchedulerService.createResourceTask(taskIdList, Integer.parseInt(executeAction),schedulerParameterObj);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (SchedulerException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
				
		return "redirectList";
	}
	
	
	
	
	
	
	
	public SchedulerTaskForm getTheForm() {
		return theForm;
	}

	public void setTheForm(SchedulerTaskForm theForm) {
		this.theForm = theForm;
	}

}
