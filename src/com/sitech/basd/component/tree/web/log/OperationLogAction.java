package com.sitech.basd.component.tree.web.log;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sitech.basd.component.domain.log.TbOperationLogObj;
import com.sitech.basd.component.service.log.LogService;
import com.sitech.basd.sxcloud.rsmu.web.BaseAction;

/**
 * <p>
 * Title: OperationLogAction
 * </p>
 * <p>
 * Description: 查询实例 操作日志
 * </p>
 * <p>
 * Copyright: Copyright (c) 2013
 * </p>
 * <p>
 * Company: SI-TECH
 * </p>
 * 
 * @author yanggl
 * @version 1.0
 * @createtime 2013-12-20 下午10:10:59
 * 
 */
@Controller("operationLogAction")
@Scope("prototype")
public class OperationLogAction extends BaseAction {
	@Autowired
	private LogService logService;

	private List<TbOperationLogObj> resultList;
	private String entityId;// 实例 ID
	private TbOperationLogObj obj = new TbOperationLogObj();

	public List<TbOperationLogObj> getResultList() {
		return resultList;
	}

	public void setResultList(List<TbOperationLogObj> resultList) {
		this.resultList = resultList;
	}

	public String getEntityId() {
		return entityId;
	}

	public void setEntityId(String entityId) {
		this.entityId = entityId;
	}

	public TbOperationLogObj getObj() {
		return obj;
	}

	public void setObj(TbOperationLogObj obj) {
		this.obj = obj;
	}

	/**
	 * 
	 * @Title: listOperationLog
	 * @Description: 查询实例 操作日志
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-12-20 下午3:43:44
	 */
	public String listOperationLog() {
		obj.setExampleId(entityId);
		obj.setPagination(this.getPaginater().initPagination(request));
		resultList = logService.queryOperationLog(obj);
		return "list";
	}
}
