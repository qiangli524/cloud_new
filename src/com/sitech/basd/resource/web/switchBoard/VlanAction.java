package com.sitech.basd.resource.web.switchBoard;

import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sitech.basd.resource.domain.switchBoard.SwitchInterfaceObj;
import com.sitech.basd.resource.domain.switchBoard.VlanObj;
import com.sitech.basd.resource.service.switchBoard.SwitchInterfaceService;
import com.sitech.basd.resource.service.switchBoard.VlanService;
import com.sitech.basd.sxcloud.cloud.domain.net.TbCloud2IpInfoObj;
import com.sitech.basd.sxcloud.cloud.service.net.IpService;
import com.sitech.basd.sxcloud.rsmu.web.BaseAction;
import com.sitech.basd.sxcloud.util.Struts2Utils;
import com.sitech.basd.util.PropertyUtil;
import com.sitech.utils.randomid.RandomUUID;
import com.sitech.utils.servlet.PrintWriterOut;
import com.sitech.utils.ssh.SSHUtil;
import com.sitech.utils.ssh.SshConnection;

@Controller("vlanAction")
@Scope("prototype")
public class VlanAction extends BaseAction {
	private static final Logger logger = Logger.getLogger(VlanAction.class);
	@Autowired
	private VlanService vlanService;
	@Autowired
	private IpService ipService;
	@Autowired
	private SwitchInterfaceService switchInterfaceService;
	private VlanObj theForm;
	private List<VlanObj> vlanList;
	private String switchId;
	private String vlanId;// 主键
	private String vlan_id;// VLAN ID
	private String interf_id;
	private String interf_name;
	private String ip_old;// 修改之前的IP地址
	private List ipList;
	private List<SwitchInterfaceObj> interfaceList;
	@Autowired
	private PropertyUtil sshProp;

	/**
	 * 
	 * @Title: vlanList
	 * @Description: 查询所有vlan
	 * @param
	 * @return String
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @createtime Jul 23, 2013
	 */
	public String list() {
		if (theForm == null) {
			theForm = new VlanObj();
		}
		VlanObj obj = new VlanObj();
		if (switchId != null && !"".equals(switchId)) {
			obj.setSwitch_id(switchId);
			theForm.setSwitch_id(switchId);
		}
		obj.setPagination(this.getPaginater().initPagination(Struts2Utils.getRequest()));
		if (!"".equals(theForm.getVlan_name()) && theForm.getVlan_name() != null) {
			obj.setVlan_name(theForm.getVlan_name());
		}
		if (!"".equals(theForm.getVlan_status()) && theForm.getVlan_status() != null) {
			obj.setVlan_status(theForm.getVlan_status());
		}
		if (!"".equals(theForm.getVlan_id()) && theForm.getVlan_id() != null) {
			obj.setVlan_id(theForm.getVlan_id());
		}
		vlanList = vlanService.queryVlanList(obj);
		return "list";
	}

	/**
	 * 
	 * @Title: addVlan
	 * @Description: 插入前获取switch的id
	 * @param
	 * @return String
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @createtime Jul23, 2013
	 */
	public String add() {
		if (theForm == null) {
			theForm = new VlanObj();
		}
		if (!"".equals(switchId) && switchId != null) {
			theForm.setSwitch_id(switchId);
		}
		TbCloud2IpInfoObj obj = new TbCloud2IpInfoObj();
		SwitchInterfaceObj interfaceObj = new SwitchInterfaceObj();
		interfaceObj.setSwitch_id(switchId);
		ipList = ipService.queryForListByIPObj(obj);
		interfaceList = switchInterfaceService.queryInterList(interfaceObj);
		return "add";
	}

	/**
	 * 
	 * @Title: modVlan
	 * @Description: 更新前查询单个vlan信息
	 * @param
	 * @return String
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @createtime Jul23, 2013
	 */
	public String mod() {
		if (theForm == null) {
			theForm = new VlanObj();
		}
		VlanObj obj = new VlanObj();
		if (!"".equals(vlanId) && vlanId != null) {
			obj.setId(vlanId);
			theForm = vlanService.queryVlanList(obj).get(0);
			TbCloud2IpInfoObj infoObj = new TbCloud2IpInfoObj();
			SwitchInterfaceObj interfaceObj = new SwitchInterfaceObj();
			interfaceObj.setSwitch_id(switchId);
			ipList = ipService.queryForListByIPObj(infoObj);
			interfaceList = switchInterfaceService.queryInterfaceList(interfaceObj);
		} else {
			theForm = null;
		}
		return "mod";
	}

	/**
	 * 
	 * @Title: save
	 * @Description: 更新或者插入vlan信息
	 * @param
	 * @return String
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @throws Exception
	 * @createtime Jul 23, 2013
	 */
	public String save() throws Exception {
		if (theForm == null) {
			theForm = new VlanObj();
		}
		VlanObj obj = new VlanObj();
		try {
			BeanUtils.copyProperties(obj, theForm);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}

		String creat_vlan = sshProp.getString("creat_vlan");// 创建vlan脚本路径
		String creat_ip = sshProp.getString("creat_ip");// 创建ip脚本路径
		String ch_port_vlan = sshProp.getString("ch_port_vlan");// 创建vlan和端口关系脚本路径
		String del_ip = sshProp.getString("del_ip");// 删除ip脚本路径
		String del_port_vlan = sshProp.getString("del_port_vlan");// 删除vlan和端口关系脚本路径
		String IP_USER_PASS = sshProp.getString("switch_ip") + " "
				+ sshProp.getString("switch_user") + " " + sshProp.getString("switch_pass");
//		SshConnection sc = SSHUtil.getSshConnection(false, sshProp.getString("ip"), 22,
//				sshProp.getString("username"), sshProp.getString("password"));

		if (vlanId != null && !"".equals(vlanId)) {
			logger.debug("IP 之前地址=" + ip_old);

			// TODO 删除Vlan和端口对应关系
			SwitchInterfaceObj interfaceObj = new SwitchInterfaceObj();
			interfaceObj.setVlan_id(vlanId);
			List<SwitchInterfaceObj> list = switchInterfaceService.queryInterfaceList(interfaceObj);
			obj.setId(vlanId);
			vlanService.updateVlan(obj);// 修改数据库

			// 修改vlan中的端口先把端口表中的vlan清空,在把已选择的端口和vlan对应
			if (list != null && list.size() > 0) {
				SwitchInterfaceObj inObj = new SwitchInterfaceObj();
				inObj.setVlanId("");
				inObj.setVlan_id(vlanId);
				switchInterfaceService.updateVlanIdNull(inObj);// 修改数据库操作
			}
		} else {
			// TODO 创建Vlan脚本
//			SSHUtil.sendShellToSSHChannelReMess(sc, true, "sh " + creat_vlan + " " + IP_USER_PASS
//					+ " " + obj.getVlan_id() + " " + obj.getVlan_name());

			obj.setId(RandomUUID.getUuid());
			obj.setSwitch_id(switchId);
			vlanService.insertVlan(obj);// 插入数据库

		}
		if (interf_id != null && !"".equals(interf_id)) {
			// TODO 创建Vlan和端口的对应关系
			String[] interf_names = interf_name.split(",");
//			for (String name : interf_names) {
//				SSHUtil.sendShellToSSHChannelReMess(sc, true, "sh " + ch_port_vlan + " "
//						//+ IP_USER_PASS + " " + obj.getVlan_id() + " Gi0/" + name);
//						+ IP_USER_PASS + " " + obj.getVlan_id() + " " + name);
//			}
			// 添加vlan和端口对应关系 (数据库操作)
			String[] intereIds = interf_id.split(",");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
			String date = sdf.format(new Date());
			for (String intereId : intereIds) {
				SwitchInterfaceObj interfaceObj = new SwitchInterfaceObj();
				interfaceObj.setVlan_id(obj.getId());
				interfaceObj.setId(intereId);
				interfaceObj.setUpdate_time(date);
				switchInterfaceService.updateInterface(interfaceObj);
			}
		}

		if (obj.getIP_ID() != null && !"".equals(obj.getIP_ID())) {
			TbCloud2IpInfoObj ipObj1 = new TbCloud2IpInfoObj();
			ipObj1.setIP_ID(obj.getIP_ID());
			ipObj1 = ipService.queryByObj(ipObj1);// 查询ip地址
			// TODO 创建IP脚本
//			SSHUtil.sendShellToSSHChannelReMess(
//					sc,
//					true,
//					"sh " + creat_ip + " " + IP_USER_PASS + " " + obj.getVlan_id() + " "
//							+ ipObj1.getIPADDRESS() + " " + sshProp.getString("subnet_mask"));
		}
		return "save";
	}

	/**
	 * 
	 * @Title: delvlan
	 * @Description: 删除一条vlan信息
	 * @param
	 * @return String
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @throws Exception
	 * @createtime Jul 23, 2013
	 */
	public String del() throws Exception {
		if (!"".equals(vlanId) && vlanId != null) {
			VlanObj obj = new VlanObj();
			obj.setId(vlanId);
			logger.debug("VLAN ID=" + vlan_id);
			VlanObj vlanObj = vlanService.queryIPAddrByVlanId(obj);// 查询IPAddr
			vlanService.deleteVlan(obj);
			// 删除vlan的同时把该vlan所对应的端口也删除 @yanggl
			SwitchInterfaceObj interfaceObj = new SwitchInterfaceObj();
			interfaceObj.setVlan_id(vlanId);
			List<SwitchInterfaceObj> list = switchInterfaceService.queryInterfaceList(interfaceObj);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
			String date = sdf.format(new Date());
			if (list != null && list.size() > 0) {
				for (SwitchInterfaceObj inObj : list) {
					SwitchInterfaceObj interObj = new SwitchInterfaceObj();
					interObj.setId(inObj.getId());
					interObj.setUpdate_time(date);
					interObj.setVlan_id("");
					switchInterfaceService.updateVlanId(interObj);
				}
			}

			// TODO 先在端口上删除端口与VLAN的关系（VLAN所包含的端口均需要删除）,删除IP，然后再删除VLAN 执行脚本
			String del_port_vlan = sshProp.getString("del_port_vlan");
			String del_vlan = sshProp.getString("del_vlan");
			String del_ip = sshProp.getString("del_ip");
			String IP_USER_PASS = sshProp.getString("switch_ip") + " "
					+ sshProp.getString("switch_user") + " " + sshProp.getString("switch_pass");
//			SshConnection sc = SSHUtil.getSshConnection(false, sshProp.getString("ip"), 22,
//					sshProp.getString("username"), sshProp.getString("password"));
			// 删除端口与VLAN的关系
//			if (list != null && list.size() > 0) {
//				for (SwitchInterfaceObj inObj : list) {
//					SSHUtil.sendShellToSSHChannelReMess(sc, true, "sh " + del_port_vlan + " "
//							//+ IP_USER_PASS + " " + vlan_id + " Gi0/" + inObj.getInterf_name());
//							+ IP_USER_PASS + " " + vlan_id + " " + inObj.getInterf_name());
//				}
//			}
			// 删除IP地址
//			if (vlanObj.getIPADDRESS() != null && !"".equals(vlanObj.getIPADDRESS())) {
//				SSHUtil.sendShellToSSHChannelReMess(
//						sc,
//						true,
//						"sh " + del_ip + " " + IP_USER_PASS + " " + vlan_id + " "
//								+ vlanObj.getIPADDRESS() + " " + sshProp.getString("subnet_mask"));
//			}
			// 删除Vlan
//			SSHUtil.sendShellToSSHChannelReMess(sc, true, "sh " + del_vlan + " " + IP_USER_PASS
//					+ " " + vlan_id);
		}
		return "del";
	}

	/**
	 * 
	 * @Title: delIPAddr
	 * @Description: 删除ip地址
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @throws Exception
	 * @createtime 2014-2-13 上午11:53:52
	 */
	public String delIPAddr() throws Exception {
		if (!"".equals(vlanId) && vlanId != null) {
			VlanObj obj = new VlanObj();
			obj.setId(vlanId);
			obj.setIP_ID("");
			logger.debug("VLAN ID=" + vlan_id);
			VlanObj vlanObj = vlanService.queryIPAddrByVlanId(obj);// 查询IPAddr
			vlanService.delIPAddr(obj);// 删除数据库中操作

			// TODO 删除vlan地址脚本
			String del_ip = sshProp.getString("del_ip");
			String IP_USER_PASS = sshProp.getString("switch_ip") + " "
					+ sshProp.getString("switch_user") + " " + sshProp.getString("switch_pass");
//			SshConnection sc = SSHUtil.getSshConnection(false, sshProp.getString("ip"), 22,
//					sshProp.getString("username"), sshProp.getString("password"));
//			if (vlanObj.getIPADDRESS() != null && !"".equals(vlanObj.getIPADDRESS())) {
//				SSHUtil.sendShellToSSHChannelReMess(
//						sc,
//						true,
//						"sh " + del_ip + " " + IP_USER_PASS + " " + vlan_id + " "
//								+ vlanObj.getIPADDRESS() + " " + sshProp.getString("subnet_mask"));
//			}
		}
		return "del";
	}

	/**
	 * 
	 * @Title: delvlan
	 * @Description: 判断vlanid是否已经存在
	 * @param
	 * @return String
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @createtime Jul 23, 2013
	 */
	public String adjustHasVlanId() {
		int flag = 0; // 不存在相同id
		if (theForm == null) {
			theForm = new VlanObj();
		}
		VlanObj obj = new VlanObj();
		if (switchId != null && !"".equals(switchId)) {
			obj.setSwitch_id(switchId);
		}
		if (vlanId != null && !"".equals(vlanId)) {
			obj.setVlan_id(vlanId);
		}
		vlanList = vlanService.queryVlanList(obj);
		if (vlanList.size() > 0) {
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

	public VlanObj getTheForm() {
		return theForm;
	}

	public void setTheForm(VlanObj theForm) {
		this.theForm = theForm;
	}

	public List<VlanObj> getVlanList() {
		return vlanList;
	}

	public void setVlanList(List<VlanObj> vlanList) {
		this.vlanList = vlanList;
	}

	public String getSwitchId() {
		return switchId;
	}

	public void setSwitchId(String switchId) {
		this.switchId = switchId;
	}

	public String getVlanId() {
		return vlanId;
	}

	public void setVlanId(String vlanId) {
		this.vlanId = vlanId;
	}

	public List getIpList() {
		return ipList;
	}

	public void setIpList(List ipList) {
		this.ipList = ipList;
	}

	public List<SwitchInterfaceObj> getInterfaceList() {
		return interfaceList;
	}

	public void setInterfaceList(List<SwitchInterfaceObj> interfaceList) {
		this.interfaceList = interfaceList;
	}

	public String getInterf_id() {
		return interf_id;
	}

	public void setInterf_id(String interf_id) {
		this.interf_id = interf_id;
	}

	public String getInterf_name() {
		return interf_name;
	}

	public void setInterf_name(String interf_name) {
		this.interf_name = interf_name;
	}

	public String getVlan_id() {
		return vlan_id;
	}

	public void setVlan_id(String vlan_id) {
		this.vlan_id = vlan_id;
	}

	public String getIp_old() {
		return ip_old;
	}

	public void setIp_old(String ip_old) {
		this.ip_old = ip_old;
	}

}
