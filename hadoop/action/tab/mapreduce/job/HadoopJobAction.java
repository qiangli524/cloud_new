package action.tab.mapreduce.job;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import service.mapreduce.job.HadoopJobService;
import service.mapreduce.task.HadoopTaskService;

import com.sitech.basd.sxcloud.rsmu.web.BaseAction;

import domain.mapreduce.job.HadoopJobObj;
import domain.mapreduce.task.HadoopTaskObj;

/**
 * 
 * <p>
 * Title: HadoopJobAction
 * </p>
 * <p>
 * Description: 作业管理相关操作
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
@Controller("hadoopJobAction")
@Scope("prototype")
public class HadoopJobAction extends BaseAction {
	@Autowired
	private HadoopJobService hadoopJobService;
	@Autowired
	private HadoopTaskService hadoopTaskService;

	private HadoopJobObj jobObj = new HadoopJobObj();
	
	private String id;
	
	public List<HadoopJobObj> resultList;

	/**
	 * 
	 * @Title: queryJobListByObj
	 * @Description: 查询作业管理列表
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-1-11 下午5:42:47
	 */
	public String queryJobListByObj() {
		/*************根据application_id查找job相关信息******************/
//		jobObj.setPagination(this.getPaginater().initPagination(request));
		resultList = hadoopJobService.queryJobListByObj(jobObj);
		if(resultList!=null&&resultList.size()>0){
			jobObj = resultList.get(0);
		}
		return "list";
	}

	public HadoopJobObj getJobObj() {
		return jobObj;
	}

	public void setJobObj(HadoopJobObj jobObj) {
		this.jobObj = jobObj;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
}
