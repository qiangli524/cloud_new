package com.sitech.basd.resource.web.switchBoard;

import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sitech.basd.resource.domain.switchBoard.SwitchObj;
import com.sitech.basd.resource.service.switchBoard.SwitchService;
import com.sitech.basd.sxcloud.rsmu.web.BaseAction;
import com.sitech.basd.sxcloud.util.Struts2Utils;
import com.sitech.basd.yicloud.domain.device.ManageProtocolObj;
import com.sitech.basd.yicloud.domain.device.SnmpParamObj;
import com.sitech.basd.yicloud.service.device.ManageProtocolService;
import com.sitech.basd.yicloud.service.device.SnmpParamService;
import com.sitech.utils.randomid.RandomUUID;

@Controller("switchAction")
@Scope("prototype")
public class SwitchAction extends BaseAction {

	@Autowired
	private SwitchService switchService;
	@Autowired
	private SnmpParamService snmpParamService;
	@Autowired
	private ManageProtocolService manageProtocolService;
	private List<SwitchObj> switchList; // 交换机信息集合
	private SwitchObj theForm;
	private String switchId;
	private SnmpParamObj snmp;
	private ManageProtocolObj protocol;

	/**
	 * 
	 * @Title: switchList
	 * @Description: 查询所有交换机
	 * @param
	 * @return String
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @createtime Jul 19, 2013
	 */
	public String list() {
		if (theForm == null) {
			theForm = new SwitchObj();
		}
		SwitchObj obj = new SwitchObj();
		obj.setPagination(this.getPaginater().initPagination(Struts2Utils.getRequest()));
		if (!"".equals(theForm.getSwitch_name()) && theForm.getSwitch_name() != null) {
			obj.setSwitch_name(theForm.getSwitch_name());
		}
		if (!"".equals(theForm.getSwitch_status())) {
			obj.setSwitch_status(theForm.getSwitch_status());
		}
		switchList = switchService.querySwitchList(obj);
		return "list";
	}

	public String ah_list() {
		if (theForm == null) {
			theForm = new SwitchObj();
		}
		SwitchObj obj = new SwitchObj();
		obj.setPagination(this.getPaginater().initPagination(Struts2Utils.getRequest()));
		if (!"".equals(theForm.getSwitch_name()) && theForm.getSwitch_name() != null) {
			obj.setSwitch_name(theForm.getSwitch_name());
		}
		if (!"".equals(theForm.getSwitch_status())) {
			obj.setSwitch_status(theForm.getSwitch_status());
		}
		switchList = switchService.querySwitchList(obj);
		return "ah_list";
	}

	/**
	 * 
	 * @Title: switchList
	 * @Description: 查询单个交换机
	 * @param
	 * @return String
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @createtime Jul 19, 2013
	 */
	public String detail() {
		if (theForm == null) {
			theForm = new SwitchObj();
		}
		SwitchObj obj = new SwitchObj();
		if (!"".equals(switchId) && switchId != null) {
			obj.setId(switchId);
			theForm = switchService.querySwitchList(obj).get(0);
		} else {
			theForm = null;
		}
		return "detail";
	}

	/**
	 * 
	 * @Title: insertSwitch
	 * @Description: 插入一条交换机信息
	 * @param
	 * @return String
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @createtime Jul 19, 2013
	 */
	public String insert() {
		if (theForm == null) {
			theForm = new SwitchObj();
		}
		SwitchObj obj = new SwitchObj();
		try {
			BeanUtils.copyProperties(obj, theForm);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
		String date = sdf.format(new Date());
		String uuid = RandomUUID.getUuid();
		obj.setId(uuid);
		obj.setInsert_time(date.toString());
		switchService.insertSwitch(obj);
		// /插入snmp及ssh相关参数 taoxue修改
		snmp.setEntity_id(uuid);
		snmpParamService.insertByObj(snmp);
		protocol.setEntity_id(uuid);
		manageProtocolService.insertByObj(protocol);
		return "insert";
	}

	/**
	 * 
	 * @Title: modSwitch
	 * @Description: 更新前查询单个交换机信息
	 * @param
	 * @return String
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @createtime Jul 19, 2013
	 */
	public String mod() {
		if (theForm == null) {
			theForm = new SwitchObj();
		}
		SwitchObj obj = new SwitchObj();
		if (!"".equals(switchId) && switchId != null) {
			obj.setId(switchId);
			theForm = switchService.querySwitchList(obj).get(0);
			SnmpParamObj sn = new SnmpParamObj();
			sn.setEntity_id(switchId);
			snmp = snmpParamService.queryByObj(sn);
			ManageProtocolObj pro = new ManageProtocolObj();
			pro.setEntity_id(switchId);
			protocol = manageProtocolService.queryByObj(pro);
		} else {
			theForm = null;
		}
		return "mod";
	}

	public String ah_mod() {
		if (theForm == null) {
			theForm = new SwitchObj();
		}
		SwitchObj obj = new SwitchObj();
		if (!"".equals(switchId) && switchId != null) {
			obj.setId(switchId);
			theForm = switchService.querySwitchList(obj).get(0);
		} else {
			theForm = null;
		}
		return "ah_mod";
	}

	/**
	 * 
	 * @Title: insertSwitch
	 * @Description: 更新交换机信息
	 * @param
	 * @return String
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @createtime Jul 19, 2013
	 */
	public String update() {
		if (theForm == null) {
			theForm = new SwitchObj();
		}
		if (snmp == null) {
			snmp = new SnmpParamObj();
		}
		if (protocol == null) {
			protocol = new ManageProtocolObj();
		}
		SwitchObj obj = new SwitchObj();
		try {
			BeanUtils.copyProperties(obj, theForm);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
		String date = sdf.format(new Date());
		obj.setUpdate_time(date.toString());
		obj.setId(switchId);
		switchService.updateSwitch(obj);
		// 更新snmp及ssh参数信息
		// /插入snmp及ssh相关参数 taoxue修改
		snmp.setEntity_id(switchId);
		snmpParamService.updateByObj(snmp);
		protocol.setEntity_id(switchId);
		manageProtocolService.updateByObj(protocol);
		return "update";
	}

	/**
	 * 
	 * @Title: delSwitch
	 * @Description: 删除一条交换机信息
	 * @param
	 * @return String
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @createtime Jul 19, 2013
	 */
	public String del() {
		if (!"".equals(switchId) && switchId != null) {
			SwitchObj obj = new SwitchObj();
			obj.setId(switchId);
			switchService.deleteSwitch(obj);
			// 删除snmp及ssh参数信息
			SnmpParamObj sn = new SnmpParamObj();
			sn.setEntity_id(switchId);
			snmpParamService.deleteByObj(sn);
			ManageProtocolObj pro = new ManageProtocolObj();
			pro.setEntity_id(switchId);
			manageProtocolService.deleteByObj(pro);
		}
		return "del";
	}

	public List<SwitchObj> getSwitchList() {
		return switchList;
	}

	public void setSwitchList(List<SwitchObj> switchList) {
		this.switchList = switchList;
	}

	public SwitchObj getTheForm() {
		return theForm;
	}

	public void setTheForm(SwitchObj theForm) {
		this.theForm = theForm;
	}

	public String getSwitchId() {
		return switchId;
	}

	public void setSwitchId(String switchId) {
		this.switchId = switchId;
	}

	public SwitchService getSwitchService() {
		return switchService;
	}

	public void setSwitchService(SwitchService switchService) {
		this.switchService = switchService;
	}

	public SnmpParamObj getSnmp() {
		return snmp;
	}

	public void setSnmp(SnmpParamObj snmp) {
		this.snmp = snmp;
	}

	public ManageProtocolObj getProtocol() {
		return protocol;
	}

	public void setProtocol(ManageProtocolObj protocol) {
		this.protocol = protocol;
	}

}
