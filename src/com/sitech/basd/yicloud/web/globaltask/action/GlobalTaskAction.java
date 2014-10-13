package com.sitech.basd.yicloud.web.globaltask.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;

import com.sitech.basd.sxcloud.rsmu.web.CRUDBaseAction;
import com.sitech.basd.yicloud.domain.globaltask.GlobalTaskObj;
import com.sitech.basd.yicloud.service.globaltask.GlobalTaskService;

@Controller("globalTaskAction")
@Scope("prototype")
public class GlobalTaskAction extends CRUDBaseAction {
	@Resource
	private GlobalTaskService globalTaskService;
	private List resultList;
	private String uuid;
	private String taskName;
	private String taskType;
	private String taskStatus;

	public String getTaskStatus() {
		return taskStatus;
	}

	public void setTaskStatus(String taskStatus) {
		this.taskStatus = taskStatus;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getTaskType() {
		return taskType;
	}

	public void setTaskType(String taskType) {
		this.taskType = taskType;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public List getResultList() {
		return resultList;
	}

	public void setResultList(List resultList) {
		this.resultList = resultList;
	}

	/**
	 * 
	 * @Title: listGlobalTask
	 * @Description:任务列表
	 * @author duangh
	 * @date Jun 24, 2013 10:06:25 AM
	 * @return
	 */
	public String listGlobalTask() {
		GlobalTaskObj obj = new GlobalTaskObj();
		String[] user = new String[] { session.get("id").toString(), session.get("account").toString(),
				session.get("name").toString() };
		Assert.notNull(user, "用户不能为空!");
		obj.setCreaterName(user[1]);
		obj.setPagination(this.getPaginater().initPagination(this.request));// 分页
		if (taskName != null && !taskName.equals("")) {
			obj.setName(taskName);
		}
		if (taskType != null && !taskType.equals("")) {
			obj.setType(taskType);
		}
		if (taskStatus != null && !taskStatus.equals("")) {
			obj.setStatus(taskStatus);
		}
		resultList = globalTaskService.queryForListByObj(obj);
		return LIST;
	}

	/**
	 * 
	 * @Title: listProccessing
	 * @Description:首页展示未完成任务列表
	 * @author duangh
	 * @date Jun 24, 2013 10:07:35 AM
	 * @return
	 */
	public String listProccessing() {
		GlobalTaskObj obj = new GlobalTaskObj();
		obj.setStatus("1");
		String[] user = new String[] { session.get("id").toString(), session.get("account").toString(),
				session.get("name").toString() };
		Assert.notNull(user, "用户不能为空!");
		obj.setCreaterName(user[1]);
		resultList = globalTaskService.queryListProccessing(obj);
		return "processing";
	}

	/**
	 * 
	 * @Title: ifCreater
	 * @Description:查询一个任务是否属于创建人
	 * @author duangh
	 * @date Jun 24, 2013 6:25:25 PM
	 * @return
	 */
	public String ifCreater() {
		String[] user = new String[] { session.get("id").toString(), session.get("account").toString(),
				session.get("name").toString() };
		Assert.notNull(user, "用户不能为空!");
		GlobalTaskObj obj = new GlobalTaskObj();
		obj.setId(uuid);
		obj.setCreaterName(user[1]);
		resultList = globalTaskService.queryForListByObj(obj);
		return "ifcreater";
	}
}
