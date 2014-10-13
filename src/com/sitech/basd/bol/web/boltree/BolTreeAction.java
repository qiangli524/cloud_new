package com.sitech.basd.bol.web.boltree;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sitech.basd.bol.domain.boltree.BolTreeObj;
import com.sitech.basd.bol.service.boltree.BolTreeService;
import com.sitech.basd.sxcloud.rsmu.web.BaseAction;
import com.sitech.basd.sxcloud.rsmu.web.util.exception.BaseException;
import com.sitech.basd.yicloud.util.systemlog.MethodLog;

@SuppressWarnings("serial")
@Controller("bolTreeAction")
@Scope("prototype")
public class BolTreeAction extends BaseAction {

	@Autowired
	private BolTreeService bolTreeService;

	private String type;
	
	private String uuid;
	
	private String name;
	
	private List<BolTreeObj> resultList;
			
	public List<BolTreeObj> getResultList() {
		return resultList;
	}

	public void setResultList(List<BolTreeObj> resultList) {
		this.resultList = resultList;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	/**
	 * @Title: listBolTree
	 * @Description: 进入资源视图树界面
	 * @param
	 * @return String
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-11-2 上午10:46:01
	 */
	public String listBolTree() {
		return "list";
	}

	/**
	 * @Title: loadingTreeAsync
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param
	 * @return String
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-11-2 上午10:55:06
	 */
	@MethodLog(remark = "rolTreeAction-loadingTreeAsync", type = 4, message = "异步方式生成树")
	public String loadingTreeAsync() throws BaseException {
		resultList = bolTreeService.queryForBolTreeList(request);
		return "tree";
	}
	
	/**
	 * @Title: tabs
	 * @Description: 进入tab页
	 * @param
	 * @return String
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-11-6 下午4:23:01
	 */
	public String tabs(){
		return "tabs";
	}
}
