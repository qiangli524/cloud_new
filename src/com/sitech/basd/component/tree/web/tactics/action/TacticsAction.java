package com.sitech.basd.component.tree.web.tactics.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sitech.basd.component.tree.domain.tactics.TacticsObj;
import com.sitech.basd.component.tree.service.tactics.TacticsService;
import com.sitech.basd.sxcloud.rsmu.web.BaseAction;
import com.sitech.basd.sxcloud.util.Struts2Utils;
import com.sitech.utils.randomid.RandomUUID;
import com.sitech.utils.servlet.PrintWriterOut;

/**
 * <p>
 * Title: TacticsActionNew
 * </p>
 * <p>
 * Description: 策略action
 * </p>
 * <p>
 * Copyright: Copyright (c) 2013
 * </p>
 * <p>
 * Company: SI-TECH
 * </p>
 * 
 * @author lipengpeng
 * @version 1.0
 * @createtime 2013-8-26 下午7:23:05
 * 
 */
@SuppressWarnings("serial")
@Controller("tacticsAction")
@Scope("prototype")
public class TacticsAction extends BaseAction {

	private List<TacticsObj> resultList;

	private TacticsObj tacticsObj;

	private String tacticsId;

	private String oper;

	private String taskId;
	
	private String num;
	
	private String userType;//标示页面是从哪个父页面过来的。

	@Autowired
	private TacticsService tacticsService;

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getOper() {
		return oper;
	}

	public void setOper(String oper) {
		this.oper = oper;
	}

	public List<TacticsObj> getResultList() {
		return resultList;
	}

	public void setResultList(List<TacticsObj> resultList) {
		this.resultList = resultList;
	}

	public TacticsObj getTacticsObj() {
		return tacticsObj;
	}

	public void setTacticsObj(TacticsObj tacticsObj) {
		this.tacticsObj = tacticsObj;
	}

	public String getTacticsId() {
		return tacticsId;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public void setTacticsId(String tacticsId) {
		this.tacticsId = tacticsId;
	}

	/**
	 * @Title: insertTactics
	 * @Description: 添加策略
	 * @param
	 * @return String
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-8-26 下午7:24:07
	 */
	public String insertTacticsObj() {
		HttpServletRequest request = Struts2Utils.getRequest();
		request.setAttribute("num",num);
		request.setAttribute("userType",userType);
		return "add";
	}

	/**
	 * @Title: deleteTactics
	 * @Description: 删除策略
	 * @param
	 * @return void
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-8-26 下午7:26:30
	 */
	public void deleteTacticsObj() {
		if (tacticsObj == null) {
			tacticsObj = new TacticsObj();
		}
		int ret = -1;
		if (tacticsId != null && !"".equals(tacticsId)) {
			tacticsObj.setID(tacticsId);
			ret = tacticsService.deleteTacticsObj(tacticsObj);
		}
		try {
			// PrintWriter pw = response.getWriter();
			// pw.print(ret);
			// pw.flush();
			// pw.close();
			PrintWriterOut.printWirter(response, ret);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @Title: updateTactics
	 * @Description: 修改策略
	 * @param
	 * @return String
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-8-26 下午7:34:00
	 */
	public String updateTacticsObj() {
		if (tacticsObj == null) {
			tacticsObj = new TacticsObj();
		}
		if (tacticsId != null && !"".equals(tacticsId)) {
			tacticsObj.setID(tacticsId);
		}
		List<TacticsObj> list = tacticsService.queryForList(tacticsObj);
		if (list != null && list.size() > 0) {
			tacticsObj = list.get(0);
			taskId = tacticsObj.getTASKID();
		}
		return "modify";
	}

	/**
	 * @Title: listTacticsObjMappings
	 * @Description: 展示策略
	 * @param
	 * @return String
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-8-26 下午7:38:34
	 */
	public String listTacticsObjMappings() {
		if (tacticsObj == null) {
			tacticsObj = new TacticsObj();
		}
		tacticsObj.setTASKID(taskId);
		resultList = tacticsService.queryForList(tacticsObj);
		return "list";
	}

	/**
	 * @Title: saveTacticsObj
	 * @Description: 保存策略
	 * @param
	 * @return void
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-8-26 下午7:39:38
	 */
	public void saveTacticsObj() {
		int ret = -1;
		tacticsObj.setTASKID(taskId);
		if ("add".equals(oper)) {
			tacticsObj.setID(RandomUUID.getUuid());
			ret = tacticsService.insertTacticsObj(tacticsObj);
		} else {
			tacticsObj.setID(tacticsId);
			ret = tacticsService.updateTacticsObj(tacticsObj);
		}
		try {
			// PrintWriter pw = response.getWriter();
			// pw.print(ret);
			// pw.flush();
			// pw.close();
			PrintWriterOut.printWirter(response, ret);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @Title: countTactics
	 * @Description: 统计策略条数
	 * @param
	 * @return void
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-8-26 下午7:40:09
	 */
	public void countTactics() {
		if (tacticsObj == null) {
			tacticsObj = new TacticsObj();
		}
		tacticsObj.setTASKID(taskId);
		int count = tacticsService.countNum(tacticsObj);
		try {
			// PrintWriter pw = response.getWriter();
			// pw.print(count);
			// pw.flush();
			// pw.close();
			PrintWriterOut.printWirter(response, count);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
