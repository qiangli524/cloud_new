package action.tab.mapreduce.application;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import service.mapreduce.application.HadoopApplicationService;

import com.sitech.basd.sxcloud.rsmu.web.BaseAction;

import domain.mapreduce.application.HadoopApplicationObj;
import domain.tree.HadoopTreeObj;

/**
 * 
 * <p>
 * Title: HadoopApplicationAction
 * </p>
 * <p>
 * Description: 应用相关操作
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
@Controller("hadoopApplicationAction")
@Scope("prototype")
public class HadoopApplicationAction extends BaseAction {
	@Autowired
	private HadoopApplicationService hadoopApplicationService;

	private HadoopApplicationObj applicationObj;
	
	private HadoopTreeObj obj;
	
	public HadoopTreeObj getObj() {
		return obj;
	}

	public void setObj(HadoopTreeObj obj) {
		this.obj = obj;
	}


	
	public List<HadoopApplicationObj> resultList;

	/**
	 * 
	 * @Title: queryApplicationListByObj
	 * @Description: 查询作业管理列表
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-1-11 下午5:42:47
	 */
	public String queryApplicationListByObj() {
		if (obj == null) {
			obj = new HadoopTreeObj();
		}
		System.out.println("集群的id是： "+obj.getId());
		if (applicationObj == null) {
			applicationObj = new HadoopApplicationObj();
		}
		applicationObj.setCluster_id(obj.getId());
		applicationObj.setPagination(this.getPaginater()
				.initPagination(request));
		resultList = hadoopApplicationService
				.queryApplicationListByObj(applicationObj);
		return "list";
	}

	public HadoopApplicationObj getApplicationObj() {
		return applicationObj;
	}

	public void setApplicationObj(HadoopApplicationObj applicationObj) {
		this.applicationObj = applicationObj;
	}

	public List<HadoopApplicationObj> getResultList() {
		return resultList;
	}

	public void setResultList(List<HadoopApplicationObj> resultList) {
		this.resultList = resultList;
	}

}
