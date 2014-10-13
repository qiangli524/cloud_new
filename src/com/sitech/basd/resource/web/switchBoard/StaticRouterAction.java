package com.sitech.basd.resource.web.switchBoard;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sitech.basd.resource.domain.switchBoard.StaticRouterObj;
import com.sitech.basd.resource.service.switchBoard.StaticRouterService;
import com.sitech.basd.sxcloud.rsmu.web.BaseAction;
import com.sitech.basd.sxcloud.util.Struts2Utils;
import com.sitech.basd.util.PropertyUtil;
import com.sitech.utils.randomid.RandomUUID;
import com.sitech.utils.ssh.SSHUtil;
import com.sitech.utils.ssh.SshConnection;

/**
 * 
 * <p>Title: StaticRouterAction</p>
 * <p>Description: 静态路由相关操作</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: SI-TECH </p>
 * @author yanggl
 * @version 1.0
 * @createtime 2014-6-8 下午4:30:49
 *
 */
@Controller("staticRouterAction")
@Scope("prototype")
public class StaticRouterAction extends BaseAction {

	@Autowired
	private StaticRouterService staticRouterService;
	@Autowired
	private PropertyUtil sshProp;
	
	private List resultList;
	
	private StaticRouterObj obj; 
	
	private String switchId;
	
	/**
	 * 
	 * @Title: list
	 * @Description: 查询
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-6-8 下午4:31:15
	 */
	public String list() {
		if (obj == null) {
			obj = new StaticRouterObj();
		}
		if (switchId != null && !"".equals(switchId)) {
			obj.setSwitch_id(switchId);
		}
		obj.setPagination(this.getPaginater().initPagination(Struts2Utils.getRequest()));
		resultList = staticRouterService.queryForList(obj);
		return "list";
	}

	/**
	 * 
	 * @Title: add
	 * @Description: 展示添加页面
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-6-8 下午4:31:33
	 */
	public String add() {
		return "add";
	}

	/**
	 * 
	 * @Title: mod
	 * @Description: 展示更新页面
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-6-8 下午4:31:54
	 */
	public String mod() {
		if (obj == null) {
			obj = new StaticRouterObj();
		}
		if (switchId != null && !"".equals(switchId)) {
			obj.setSwitch_id(switchId);
		}
		resultList = staticRouterService.queryForList(obj);
		if(resultList!=null && resultList.size()>0){
			obj = (StaticRouterObj) resultList.get(0);
		}
		return "mod";
	}
	/**
	 * 
	 * @Title: save
	 * @Description: 保存
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-6-8 下午4:32:10
	 */
	public String save() throws Exception {
		if (obj == null) {
			obj = new StaticRouterObj();
		}
		String cmd = sshProp.getString("static_router_add");
		String IP_USER_PASS = sshProp.getString("switch_ip") + " "
				+ sshProp.getString("switch_user") + " " + sshProp.getString("switch_pass");
//		SshConnection sc = SSHUtil.getSshConnection(false, sshProp.getString("ip"), 22,
//				sshProp.getString("username"), sshProp.getString("password"));
		if (obj.getId() != null && !"".equals(obj.getId())) {
			obj.setSwitch_id(switchId);
			staticRouterService.updateByObj(obj);
		} else {
//			SSHUtil.sendShellToSSHChannelReMess(
//					sc,true,"sh " + cmd + " " + IP_USER_PASS + " " + obj.getSource_ip() + " "
//							+ obj.getSubnet_mask() + " " + obj.getIp());
			obj.setId(RandomUUID.getUuid());
			obj.setSwitch_id(switchId);
			staticRouterService.insertByObj(obj);
		}
		return "save";
	}

	/**
	 * 
	 * @Title: del
	 * @Description: 删除
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-6-8 下午4:32:28
	 */
	public String del() throws Exception {
		if (obj == null) {
			obj = new StaticRouterObj();
		}
		String cmd = sshProp.getString("static_router_del");
		String IP_USER_PASS = sshProp.getString("switch_ip") + " "
				+ sshProp.getString("switch_user") + " " + sshProp.getString("switch_pass");
//		SshConnection sc = SSHUtil.getSshConnection(false, sshProp.getString("ip"), 22,
//				sshProp.getString("username"), sshProp.getString("password"));
//		SSHUtil.sendShellToSSHChannelReMess(
//				sc,true,"sh " + cmd + " " + IP_USER_PASS + " " + obj.getSource_ip() + " "
//						+ obj.getSubnet_mask() + " " + obj.getIp());
		obj.setSwitch_id(switchId);
		staticRouterService.deleteByObj(obj);
		return "del";
	}

	public List getResultList() {
		return resultList;
	}


	public void setResultList(List resultList) {
		this.resultList = resultList;
	}

	public String getSwitchId() {
		return switchId;
	}

	public void setSwitchId(String switchId) {
		this.switchId = switchId;
	}


	public StaticRouterObj getObj() {
		return obj;
	}


	public void setObj(StaticRouterObj obj) {
		this.obj = obj;
	}

}
