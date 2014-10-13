package com.sitech.ssd.ah.paas.action.tab;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sitech.basd.sxcloud.rsmu.web.BaseAction;
import com.sitech.ssd.ah.paas.domain.host.GreenPlumHostInfoObj;
import com.sitech.ssd.ah.paas.domain.tree.PaasTreeObj;
import com.sitech.ssd.ah.paas.service.tab.GreenPlumTabService;
import com.sitech.ssd.ah.paas.service.tree.PaasTreeService;

/**
 * 
 * <p>
 * Title: GreenPlumTabAction
 * </p>
 * <p>
 * Description: GreenPlum数据库相关操作
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
 * @createtime 2014-7-29 下午4:05:51
 * 
 */
@Controller("greenPlumTabAction")
@Scope("prototype")
public class GreenPlumTabAction extends BaseAction {

	@Autowired
	private GreenPlumTabService greenPlumTabService;
	@Autowired
	private PaasTreeService paasTreeService;

	private GreenPlumHostInfoObj obj;

	private PaasTreeObj paasTreeObj;

	private String id;

	private List resultList;

	public GreenPlumHostInfoObj getObj() {
		return obj;
	}

	public void setObj(GreenPlumHostInfoObj obj) {
		this.obj = obj;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List getResultList() {
		return resultList;
	}

	public void setResultList(List resultList) {
		this.resultList = resultList;
	}

	public PaasTreeObj getPaasTreeObj() {
		return paasTreeObj;
	}

	public void setPaasTreeObj(PaasTreeObj paasTreeObj) {
		this.paasTreeObj = paasTreeObj;
	}

	/**
	 * 
	 * @Title: showGreenPlumHost
	 * @Description: GP主机
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-7-29 下午4:04:10
	 */
	public String showGreenPlumHost() {
		if (paasTreeObj == null) {
			paasTreeObj = new PaasTreeObj();
		}
		paasTreeObj.setId(id);
		List<PaasTreeObj> treeList = paasTreeService.queryTreeNodeByObj(paasTreeObj);
		if (treeList != null && treeList.size() > 0) {
			paasTreeObj = treeList.get(0);// 节点名称
		}
		return "showGreenPlumHost";
	}

	/**
	 * 
	 * @Title: showGPHostList
	 * @Description:
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-7-31 下午7:21:28
	 */
	public String showGPHostList() {
		if (obj == null) {
			obj = new GreenPlumHostInfoObj();
		}
		obj.setId(id);
		obj.setPagination(this.getPaginater().initPagination(request));
		resultList = greenPlumTabService.queryGreenPlumHostList(obj);
		return "gpHostsList";
	}

}
