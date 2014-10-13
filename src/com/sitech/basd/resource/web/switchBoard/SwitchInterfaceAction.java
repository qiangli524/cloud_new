package com.sitech.basd.resource.web.switchBoard;

import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sitech.basd.resource.domain.switchBoard.RouterConfigObj;
import com.sitech.basd.resource.domain.switchBoard.SwitchInterfaceObj;
import com.sitech.basd.resource.domain.switchBoard.VlanObj;
import com.sitech.basd.resource.service.switchBoard.SwitchInterfaceService;
import com.sitech.basd.resource.service.switchBoard.VlanService;
import com.sitech.basd.sxcloud.rsmu.web.BaseAction;
import com.sitech.basd.sxcloud.util.Struts2Utils;
import com.sitech.basd.util.PropertyUtil;
import com.sitech.utils.randomid.RandomUUID;
import com.sitech.utils.servlet.PrintWriterOut;
import com.sitech.utils.ssh.SSHUtil;
import com.sitech.utils.ssh.SshConnection;

@Controller("switchInterfaceAction")
@Scope("prototype")
public class SwitchInterfaceAction extends BaseAction {

	@Autowired
	private SwitchInterfaceService switchInterfaceService;
	@Autowired
	private VlanService vlanService;
	@Autowired
	private PropertyUtil sshProp;
	private List<SwitchInterfaceObj> interfaceList;
	private SwitchInterfaceObj theForm;
	private String switchId;
	private String interfaceId;
	private String interName;
	private String vlanId;
	private List vlanList;
	private String vlanId_old;// 未修改之前的vlanId

	private String location;
	private RouterConfigObj router;

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	/**
	 * 
	 * @Title: interfaceList
	 * @Description: 查询所有interface
	 * @param
	 * @return String
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @createtime Jul 24, 2013
	 */
	public String list() {
		if (theForm == null) {
			theForm = new SwitchInterfaceObj();
		}
		SwitchInterfaceObj obj = new SwitchInterfaceObj();
		if (switchId != null && !"".equals(switchId)) {
			obj.setSwitch_id(switchId);
			theForm.setSwitch_id(switchId);
		}
		if (vlanId != null && !"".equals(vlanId)) {
			obj.setVlan_id(vlanId);
			theForm.setVlan_id(vlanId);
		}
		obj.setPagination(this.getPaginater().initPagination(Struts2Utils.getRequest()));
		if (!"".equals(theForm.getInterf_name()) && theForm.getInterf_name() != null) {
			obj.setInterf_name(theForm.getInterf_name());
		}
		if (!"".equals(theForm.getInterf_status()) && theForm.getInterf_status() != null) {
			obj.setInterf_status(theForm.getInterf_status());
		}
		interfaceList = switchInterfaceService.queryInterfaceList(obj);
		return "list";
	}

	// ah
	public String ah_list() {
		if (theForm == null) {
			theForm = new SwitchInterfaceObj();
		}
		SwitchInterfaceObj obj = new SwitchInterfaceObj();
		if (switchId != null && !"".equals(switchId)) {
			obj.setSwitch_id(switchId);
			theForm.setSwitch_id(switchId);
		}
		if (vlanId != null && !"".equals(vlanId)) {
			obj.setVlan_id(vlanId);
			theForm.setVlan_id(vlanId);
		}
		obj.setPagination(this.getPaginater().initPagination(Struts2Utils.getRequest()));
		if (!"".equals(theForm.getInterf_name()) && theForm.getInterf_name() != null) {
			obj.setInterf_name(theForm.getInterf_name());
		}
		if (!"".equals(theForm.getInterf_status()) && theForm.getInterf_status() != null) {
			obj.setInterf_status(theForm.getInterf_status());
		}
		interfaceList = switchInterfaceService.queryInterfaceList(obj);
		return "ah_list";
	}

	/**
	 * 
	 * @Title: addInterface
	 * @Description: 插入前获取switch的id
	 * @param
	 * @return String
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @createtime Jul 24 , 2013
	 */
	public String add() {
		if (theForm == null) {
			theForm = new SwitchInterfaceObj();
		}
		VlanObj vlanObj = new VlanObj();
		if (!"".equals(switchId) && switchId != null) {
			theForm.setSwitch_id(switchId);
			vlanObj.setSwitch_id(switchId);
		}
		vlanList = vlanService.queryVlanList(vlanObj);
		return "add";
	}

	/**
	 * 
	 * @Title: modinterface
	 * @Description: 更新前查询单个interface信息
	 * @param
	 * @return String
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @createtime Jul24, 2013
	 */
	public String mod() {
		if (theForm == null) {
			theForm = new SwitchInterfaceObj();
		}
		SwitchInterfaceObj obj = new SwitchInterfaceObj();
		if (!"".equals(interfaceId) && interfaceId != null) {
			obj.setId(interfaceId);
			theForm = switchInterfaceService.queryInterfaceList(obj).get(0);
			VlanObj vlanObj = new VlanObj();
			if (!"".equals(switchId) && switchId != null) {
				vlanObj.setSwitch_id(switchId);
			}
			vlanList = vlanService.queryVlanList(vlanObj);
		} else {
			theForm = null;
		}
		return "mod";
	}

	/**
	 * 
	 * @Title: insertInterface
	 * @Description: 更新或者插入interface信息
	 * @param
	 * @return String
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @throws Exception
	 * @createtime Jul 24, 2013
	 */
	public String save() throws Exception {
		if (theForm == null) {
			theForm = new SwitchInterfaceObj();
		}
		SwitchInterfaceObj obj = new SwitchInterfaceObj();
		try {
			BeanUtils.copyProperties(obj, theForm);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		String ch_port_vlan = sshProp.getString("ch_port_vlan");// 创建vlan和端口关系脚本路径
		String del_port_vlan = sshProp.getString("del_port_vlan");// 删除vlan和端口关系脚本路径
		String IP_USER_PASS = sshProp.getString("switch_ip") + " "
				+ sshProp.getString("switch_user") + " " + sshProp.getString("switch_pass");
//		SshConnection sc = SSHUtil.getSshConnection(false, sshProp.getString("ip"), 22,
//				sshProp.getString("username"), sshProp.getString("password"));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
		String date = sdf.format(new Date());
		if (interfaceId != null && !"".equals(interfaceId)) {
			if (vlanId_old != null && !"".equals(vlanId_old)) {
				if (vlanId_old != obj.getVlan_id()) {
					// TODO 删除Vlan和端口对应关系
					VlanObj vlanObj = new VlanObj();
					vlanObj.setId(vlanId_old);
					vlanObj = vlanService.queryIPAddrByVlanId(vlanObj);
//					SSHUtil.sendShellToSSHChannelReMess(sc, true,
//							"sh " + del_port_vlan + " " + IP_USER_PASS + " " + vlanObj.getVlan_id()
//									//+ " Gi0/" + obj.getInterf_name());//湖北测试 端口名称前面没有 Gi0
//									+ " " + obj.getInterf_name());
				}
			}
			obj.setId(interfaceId);
			obj.setUpdate_time(date);
			switchInterfaceService.updateInterface(obj);
		} else {
			obj.setId(RandomUUID.getUuid());
			obj.setInsert_time(date);
			obj.setSwitch_id(switchId);
			switchInterfaceService.insertInterface(obj);
		}

		if (obj.getVlan_id() != null && !"".equals(obj.getVlan_id())) {
			VlanObj vlanObj = new VlanObj();
			vlanObj.setId(obj.getVlan_id());
			vlanObj = vlanService.queryIPAddrByVlanId(vlanObj);
			// TODO 创建Vlan和端口的对应关系
//			SSHUtil.sendShellToSSHChannelReMess(sc, true, "sh " + ch_port_vlan + " " + IP_USER_PASS
//					//+ " " + vlanObj.getVlan_id() + " Gi0/" + obj.getInterf_name());//湖北测试 端口名称前面没有 Gi0
//					+ " " + vlanObj.getVlan_id() + " " + obj.getInterf_name());
		}
		return "save";
	}

	public String ah_save() {
		if (theForm == null) {
			theForm = new SwitchInterfaceObj();
		}
		SwitchInterfaceObj obj = new SwitchInterfaceObj();
		try {
			BeanUtils.copyProperties(obj, theForm);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
		String date = sdf.format(new Date());
		if (interfaceId != null && !"".equals(interfaceId)) {
			if (vlanId_old != null && !"".equals(vlanId_old)) {
				if (vlanId_old != obj.getVlan_id()) {
					// TODO 删除Vlan和端口对应关系
					VlanObj vlanObj = new VlanObj();
					vlanObj.setId(vlanId_old);
					vlanObj = vlanService.queryIPAddrByVlanId(vlanObj);
				}
			}
			obj.setId(interfaceId);
			obj.setUpdate_time(date);
			switchInterfaceService.updateInterface(obj);

		} else {
			obj.setId(RandomUUID.getUuid());
			obj.setInsert_time(date);
			obj.setSwitch_id(switchId);
			switchInterfaceService.insertInterface(obj);
		}
		return "ah_save";
	}

	/**
	 * 
	 * @Title: delinterface
	 * @Description: 删除一条interface信息
	 * @param
	 * @return String
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @throws Exception
	 * @createtime Jul 24, 2013
	 */
	public String del() throws Exception {
		if (!"".equals(interfaceId) && interfaceId != null) {
			SwitchInterfaceObj obj = new SwitchInterfaceObj();
			obj.setId(interfaceId);
			SwitchInterfaceObj inObj = switchInterfaceService.queryVlanIDandInterName(obj);// 查询vlanid和端口
			switchInterfaceService.deleteInterface(obj);
			this.delPortAndVlan(inObj);
		}
		return "del";
	}

	public String ah_del() {
		if (!"".equals(interfaceId) && interfaceId != null) {
			SwitchInterfaceObj obj = new SwitchInterfaceObj();
			obj.setId(interfaceId);
			SwitchInterfaceObj inObj = switchInterfaceService.queryVlanIDandInterName(obj);// 查询vlanid和端口
			switchInterfaceService.deleteInterface(obj);

		}
		return "ah_del";
	}

	/**
	 * 
	 * @Title: delVlan
	 * @Description: 删除vlan_id
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @throws Exception
	 * @createtime 2014-2-18 上午9:34:36
	 */
	public String delVlan() throws Exception {
		if (!"".equals(interfaceId) && interfaceId != null) {
			SwitchInterfaceObj obj = new SwitchInterfaceObj();
			obj.setId(interfaceId);
			obj.setVlan_id("");
			SwitchInterfaceObj inObj = switchInterfaceService.queryVlanIDandInterName(obj);// 查询vlanid和端口
			switchInterfaceService.deleteVlanByInterfaceId(obj);// 操作数据库
			this.delPortAndVlan(inObj);
		}
		return "del";
	}

	/**
	 * 
	 * @Title: delPortAndVlan
	 * @Description: 删除端口和Vlan对应关系
	 * @param
	 * @return void
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @throws Exception
	 * @createtime 2014-2-18 下午2:45:38
	 */
	public void delPortAndVlan(SwitchInterfaceObj inObj) throws Exception {
		String del_port_vlan = sshProp.getString("del_port_vlan");// 删除vlan和端口关系脚本路径
		String IP_USER_PASS = sshProp.getString("switch_ip") + " "
				+ sshProp.getString("switch_user") + " " + sshProp.getString("switch_pass");
//		SshConnection sc = SSHUtil.getSshConnection(false, sshProp.getString("ip"), 22,
//				sshProp.getString("username"), sshProp.getString("password"));
		// TODO 删除Vlan和端口对应关系
		if (inObj.getVlan_id() != null && !"".equals(inObj.getVlan_id())) {
//			SSHUtil.sendShellToSSHChannelReMess(sc, true, "sh " + del_port_vlan + " "
//					//+ IP_USER_PASS + " " + inObj.getVlan_id() + " Gi0/" + inObj.getInterf_name());//湖北测试 端口名称前面没有 Gi0
//					+ IP_USER_PASS + " " + inObj.getVlan_id() + " " + inObj.getInterf_name());
		}
	}

	/**
	 * 
	 * @Title: delinterface
	 * @Description: 删除一条interface信息
	 * @param
	 * @return String
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @createtime Jul 24, 2013
	 */
	public String adjustInterfaceName() {
		int flag = 0;
		SwitchInterfaceObj obj = new SwitchInterfaceObj();
		if (switchId != null && !"".equals(switchId)) {
			obj.setSwitch_id(switchId);
		}
		if (interName != null && !"".equals(interName)) {
			obj.setInterf_name(interName);
		}
		if (switchInterfaceService.queryInterfaceList(obj).size() == 1
				|| switchInterfaceService.queryInterfaceList(obj).size() > 1) {
			flag = 1;
		}
		HttpServletResponse response = Struts2Utils.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		// p = response.getWriter();
		// p.print(String.valueOf(flag));
		// p.close();
		PrintWriterOut.printWirter(response, String.valueOf(flag));
		return null;
	}

	/**
	 * 
	 * @Title: staticRouterConfig
	 * @Description: 进入交换机静态路由配置页面
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime 2014-6-1 下午2:59:56
	 */
	public String staticRouterConfigPage() {
		return "static_config";
	}

	/**
	 * 
	 * @Title: staticRouterConfig
	 * @Description: 配置静态路由页面
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime 2014-6-1 下午3:22:37
	 */
	public String staticRouterConfig() {
		if (router == null) {
			router = new RouterConfigObj();
		}
		int ret = switchInterfaceService.staticRouterConfig(router);
		PrintWriterOut.printWirter(response, ret);
		return null;
	}

	public RouterConfigObj getRouter() {
		return router;
	}

	public void setRouter(RouterConfigObj router) {
		this.router = router;
	}

	public List<SwitchInterfaceObj> getInterfaceList() {
		return interfaceList;
	}

	public void setInterfaceList(List<SwitchInterfaceObj> interfaceList) {
		this.interfaceList = interfaceList;
	}

	public SwitchInterfaceObj getTheForm() {
		return theForm;
	}

	public void setTheForm(SwitchInterfaceObj theForm) {
		this.theForm = theForm;
	}

	public String getSwitchId() {
		return switchId;
	}

	public void setSwitchId(String switchId) {
		this.switchId = switchId;
	}

	public String getInterfaceId() {
		return interfaceId;
	}

	public void setInterfaceId(String interfaceId) {
		this.interfaceId = interfaceId;
	}

	public String getInterName() {
		return interName;
	}

	public void setInterName(String interName) {
		this.interName = interName;
	}

	public String getVlanId() {
		return vlanId;
	}

	public void setVlanId(String vlanId) {
		this.vlanId = vlanId;
	}

	public List getVlanList() {
		return vlanList;
	}

	public void setVlanList(List vlanList) {
		this.vlanList = vlanList;
	}

	public String getVlanId_old() {
		return vlanId_old;
	}

	public void setVlanId_old(String vlanId_old) {
		this.vlanId_old = vlanId_old;
	}

}
