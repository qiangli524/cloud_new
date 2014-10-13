package action.tab.mapreduce.task;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import service.mapreduce.task.HadoopTaskService;

import com.sitech.basd.sxcloud.rsmu.web.BaseAction;

import domain.mapreduce.task.HadoopTaskObj;

/**
 * 
 * <p>
 * Title: HadoopTaskAction
 * </p>
 * <p>
 * Description: 任务相关操作
 * </p>
 * <p>
 * Copyright: Copyright (c) 2014
 * </p>
 * <p>
 * Company: SI-TECH
 * </p>
 * 
 * @author yanggl
 * @version 1.0
 * @createtime 2014-1-11 下午4:57:43
 * 
 */
@Controller("hadoopTaskAction")
@Scope("prototype")
public class HadoopTaskAction extends BaseAction {
	@Autowired
	private HadoopTaskService hadoopTaskService;

	private HadoopTaskObj TaskObj = new HadoopTaskObj();

	public List<HadoopTaskObj> resultList;

	/**
	 * 
	 * @Title: queryTaskListByObj
	 * @Description: 查询任务列表
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-1-11 下午5:42:47
	 */
	public String queryTaskListByObj() {
		TaskObj.setPagination(this.getPaginater().initPagination(request));
		resultList = hadoopTaskService.queryTaskListByObj(TaskObj);
		return "list";
	}

	public HadoopTaskObj getTaskObj() {
		return TaskObj;
	}

	public void setTaskObj(HadoopTaskObj taskObj) {
		TaskObj = taskObj;
	}
	
}
