package com.sitech.basd.yicloud.web.portgroup.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.sitech.basd.sxcloud.cloud.domain.globalconfig.TbGlobalConfigObj;
import com.sitech.basd.sxcloud.cloud.domain.net.TbCloud2NetInfoObj;
import com.sitech.basd.sxcloud.cloud.service.globalconfig.TbGlobalConfigService;
import com.sitech.basd.sxcloud.rsmu.web.CRUDBaseAction;
import com.sitech.basd.sxcloud.util.Struts2Utils;
import com.sitech.basd.yicloud.domain.entitytree.EntityTreeObj;
import com.sitech.basd.yicloud.domain.nic.NicRelationObj;
import com.sitech.basd.yicloud.domain.portgroup.PortGroup;
import com.sitech.basd.yicloud.domain.switches.VirtualSwitch;
import com.sitech.basd.yicloud.service.entitytree.EntityTreeService;
import com.sitech.basd.yicloud.service.nic.NicRelationService;
import com.sitech.basd.yicloud.service.portgroup.PortGroupService;
import com.sitech.basd.yicloud.service.switches.VirtualSwitchService;
import com.sitech.basd.yicloud.util.InvokeUtil;
import com.sitech.basd.yicloud.util.TypeConstant;
import com.sitech.basd.yicloud.web.portgroup.form.PortGroupForm;
import com.sitech.basd.yicloud.web.portgroup.form.VirtualSwitchForm;
import com.sitech.utils.servlet.PrintWriterOut;

@SuppressWarnings("all")
public class PortGroupAction extends CRUDBaseAction {
	private PortGroupService portGroupService;
	private VirtualSwitchService virtualSwitchService;
	private VirtualSwitchForm theVSForm;
	private PortGroupForm thePGForm;
	private TbGlobalConfigService tbGlobalConfigService;
	private EntityTreeService entityTreeService;
	private NicRelationService nicRelationService;

	public void setNicRelationService(NicRelationService nicRelationService) {
		this.nicRelationService = nicRelationService;
	}

	public void setEntityTreeService(EntityTreeService entityTreeService) {
		this.entityTreeService = entityTreeService;
	}

	public PortGroupForm getThePGForm() {
		return thePGForm;
	}

	public void setTbGlobalConfigService(TbGlobalConfigService tbGlobalConfigService) {
		this.tbGlobalConfigService = tbGlobalConfigService;
	}

	public void setThePGForm(PortGroupForm thePGForm) {
		this.thePGForm = thePGForm;
	}

	public void setVirtualSwitchService(VirtualSwitchService virtualSwitchService) {
		this.virtualSwitchService = virtualSwitchService;
	}

	public void setPortGroupService(PortGroupService portGroupService) {
		this.portGroupService = portGroupService;
	}

	/**
	 * @Title:显示所有虚拟交换机
	 * @Copyright: Copyright (c) 2012-9
	 * @Company: si-tech
	 * @author hehui
	 * @version 1.0
	 */
	public String listVirtualSwitch() {
		if (theVSForm == null) {
			theVSForm = new VirtualSwitchForm();
		}
		HttpServletRequest request = Struts2Utils.getRequest();
		String name = request.getParameter("name");
		String host_code = request.getParameter("host_code");
		String oper = request.getParameter("oper");
		VirtualSwitch vsObj = new VirtualSwitch();
		if (name != null && !"".equals(name)) {
			vsObj.setName(name);
		}
		if (host_code != null && !"".equals(host_code)) {
			vsObj.setVssuuid(host_code);
		}
		vsObj.setPagination(this.getPaginater().initPagination(Struts2Utils.getRequest()));// 分页
		List lst = virtualSwitchService.listVirtualSwitch(vsObj);
		for (Object o : lst) {
			VirtualSwitch vssObj = (VirtualSwitch) o;
			if (vssObj.getType().equals("1")) {
				// 分布式交换机
				NicRelationObj nicRaObj = new NicRelationObj();
				nicRaObj.setType("DVSS_NIC");
				nicRaObj.setFromUuid(vssObj.getVssuuid());
				List relationlst = nicRelationService.queryForListByObj(nicRaObj);
				if (relationlst != null && relationlst.size() > 0) {
					vssObj.setNumNic(relationlst.size());
				} else {
					vssObj.setNumNic(0);
				}
			} else {
				NicRelationObj nicRaObj = new NicRelationObj();
				nicRaObj.setType("NIC_VSS");
				nicRaObj.setToUuid(vssObj.getVssuuid());
				List relationlst = nicRelationService.queryForListByObj(nicRaObj);
				if (relationlst != null && relationlst.size() > 0) {
					vssObj.setNumNic(relationlst.size());
				} else {
					vssObj.setNumNic(0);
				}
			}
		}
		theVSForm.setResultList(lst);
		request.setAttribute("hostCode", host_code);
		if ("1".equals(oper)) {
			return "switch_tab";
		} else {
			return "listVirtualSwitch";
		}

	}

	/**
	 * @Title:显示端口组
	 * @Copyright: Copyright (c) 2012-9
	 * @Company: si-tech
	 * @author hehui
	 * @version 1.0
	 */
	public String listPortGroup() {
		if (thePGForm == null) {
			thePGForm = new PortGroupForm();
		}
		PortGroup pgObj = new PortGroup();
		HttpServletRequest request = Struts2Utils.getRequest();
		String id = request.getParameter("ID");
		String vssUuid = request.getParameter("vssUuid");
		String vsname = request.getParameter("NAME");
		String hostName = request.getParameter("hostName");// 主机名
		vssType = request.getParameter("vssType");
		int vsId = Integer.parseInt(id);
		if (vssUuid == null || vssUuid.equals("")) {
			VirtualSwitch vs = new VirtualSwitch();
			vs.setId(Integer.parseInt(id == null ? "0" : id));
			List lst = virtualSwitchService.queryByObj(vs);
			if (lst != null && lst.size() > 0) {
				vs = (VirtualSwitch) lst.get(0);
				vssUuid = vs.getVssuuid();
			} else {
				vssUuid = "0";
			}
		}
		List<PortGroup> pgLst = new ArrayList<PortGroup>();
		if (vssType.equals("0")) {
			NicRelationObj nicRaObj = new NicRelationObj();
			nicRaObj.setType("PORTGROUP_VSS");
			nicRaObj.setToUuid(vssUuid);
			List nicRaLst = nicRelationService.queryForListByObj(nicRaObj);
			if (nicRaLst != null && nicRaLst.size() > 0) {
				for (Object obj : nicRaLst) {
					NicRelationObj nr = (NicRelationObj) obj;
					PortGroup pg = new PortGroup();

					pg.setPguuid(nr.getFromUuid());
					pg = portGroupService.queryPortGroupById(pg);
					if (pg != null) {
						NicRelationObj portgroup_vm = new NicRelationObj();
						portgroup_vm.setType("PORTGROUP_VM");
						portgroup_vm.setFromUuid(pg.getPguuid());
						List pgVmLst = nicRelationService.queryForListByObj(portgroup_vm);
						if (pgVmLst != null && pgVmLst.size() > 0) {
							pg.setNumVm(pgVmLst.size());
						} else {
							pg.setNumVm(0);
						}
						pgLst.add(pg);
					}
				}
			}
		} else {
			NicRelationObj nicRaObj = new NicRelationObj();
			nicRaObj.setType("DVSS_DVPORTGROUP");
			nicRaObj.setFromUuid(vssUuid);
			List nicRaLst = nicRelationService.queryForListByObj(nicRaObj);
			if (nicRaLst != null && nicRaLst.size() > 0) {
				for (Object obj : nicRaLst) {
					NicRelationObj nr = (NicRelationObj) obj;
					PortGroup pg = new PortGroup();
					pg.setPguuid(nr.getToUuid());
					pg = portGroupService.queryPortGroupById(pg);
					if (pg != null) {
						NicRelationObj portgroup_vm = new NicRelationObj();
						portgroup_vm.setType("DVPG_VM");
						portgroup_vm.setFromUuid(pg.getPguuid());
						List pgVmLst = nicRelationService.queryForListByObj(portgroup_vm);
						if (pgVmLst != null && pgVmLst.size() > 0) {
							pg.setNumVm(pgVmLst.size());
						} else {
							pg.setNumVm(0);
						}
						pgLst.add(pg);
					}
				}
			}
		}
		thePGForm.setResultList(pgLst);
		thePGForm.setVSwitchId(vsId);
		request.setAttribute("NAME", vsname);
		request.setAttribute("hostName", hostName);
		request.setAttribute("vssType", vssType);
		return "listPortGroup";
	}

	/**
	 * @Title:显示端口组详细信息
	 * @Copyright: Copyright (c) 2012-9
	 * @Company: si-tech
	 * @author hehui
	 * @version 1.0
	 */
	public String listPortGroupDetail() {
		return "listPGDetail";
	}

	/**
	 * @Title:增加端口组
	 * @Copyright: Copyright (c) 2012-9
	 * @Company: si-tech
	 * @author hehui
	 * @version 1.0
	 */
	public String addPortGroup() {
		HttpServletRequest request = Struts2Utils.getRequest();
		String name = request.getParameter("NAME");
		String hostName = request.getParameter("hostName");
		String vssType = request.getParameter("vssType");
		TbGlobalConfigObj configObj = new TbGlobalConfigObj();
		configObj.setKEY("VLAN");
		configObj = tbGlobalConfigService.queryByObj(configObj);
		String[] values = null;
		if (configObj.getVALUE() != null) {
			String value = configObj.getVALUE();
			values = value.split(",");
		}
		request.setAttribute("vlans", values);
		request.setAttribute("hostName", hostName);
		request.setAttribute("NAME", name);
		request.setAttribute("vssType", vssType);
		return "addPortGroup";
	}

	/**
	 * 
	 * @Title: testVLAN
	 * @Description: 检查VLAN是否存在
	 * @param
	 * @return String
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime 2013-1-7 下午01:38:46
	 */
	public String testVLAN() {
		TbGlobalConfigObj configObj = new TbGlobalConfigObj();
		configObj.setKEY("VLAN");
		List lst = tbGlobalConfigService.queryForListByObj(configObj);
		String result = "";
		if (lst.size() == 0) {
			result = "{\"result\":\"" + 0 + "\"}";
		} else {
			result = "{\"result\":\"" + 1 + "\"}";
		}
		PrintWriter out;
		try {
			out = Struts2Utils.getResponse().getWriter();
			out.println(result);
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * @Title:修改端口组
	 * @Copyright: Copyright (c) 2012-9
	 * @Company: si-tech
	 * @author hehui
	 * @version 1.0
	 */
	public String modPortGroup() {
		if (thePGForm == null) {
			thePGForm = new PortGroupForm();
		}
		HttpServletRequest request = Struts2Utils.getRequest();
		PortGroup pgObj = new PortGroup();
		pgObj.setId(thePGForm.getId());
		PortGroup reObj = portGroupService.queryPortGroupById(pgObj);
		TbGlobalConfigObj configObj = new TbGlobalConfigObj();
		configObj.setKEY("VLAN");
		configObj = tbGlobalConfigService.queryByObj(configObj);
		String[] values = null;
		if (configObj.getVALUE() != null) {
			String value = configObj.getVALUE();
			values = value.split(",");
		}
		request.setAttribute("vlans", values);
		thePGForm.setName(reObj.getName());
		thePGForm.setVlanId(reObj.getVlanId());
		thePGForm.setId(reObj.getId());
		String name = request.getParameter("NAME");
		String hostName = request.getParameter("hostName");
		String vssType = request.getParameter("vssType");
		request.setAttribute("NAME", name);
		request.setAttribute("hostName", hostName);
		request.setAttribute("vssType", vssType);
		return "modPortGroup";
	}

	/**
	 * @Title:删除端口组
	 * @Copyright: Copyright (c) 2012-9
	 * @Company: si-tech
	 * @author hehui
	 * @version 1.0
	 */
	public String delPortGroup() {
		if (thePGForm == null) {
			thePGForm = new PortGroupForm();
		}
		try {
			PortGroup pgObj = new PortGroup();
			HttpServletRequest request = Struts2Utils.getRequest();
			String vSwitchId = request.getParameter("vSwitchId");
			String pgname = URLDecoder.decode(request.getParameter("pgname"), "utf-8");
			String pguuid = request.getParameter("pguuid");
			vssType = request.getParameter("vssType");
			pgObj.setId(thePGForm.getId());
			pgObj.setName(pgname);
			pgObj.setPguuid(pguuid);
			int ret = portGroupService.deleteByObj(pgObj, vSwitchId);
			vsId = thePGForm.getVSwitchId();
			name = URLDecoder.decode(request.getParameter("NAME"), "UTF-8");
			host = Struts2Utils.getRequest().getParameter("hostName");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "delPortGroup";
	}

	/**
	 * @Title:保存端口组
	 * @Copyright: Copyright (c) 2012-9
	 * @Company: si-tech
	 * @author hehui
	 * @version 1.0
	 */
	public String savePortGroup() {
		if (thePGForm == null) {
			thePGForm = new PortGroupForm();
		}
		String hostName = Struts2Utils.getRequest().getParameter("hostName");
		name = Struts2Utils.getRequest().getParameter("NAME");
		vssType = Struts2Utils.getRequest().getParameter("vssType");
		int flag = thePGForm.getFlag();
		PortGroup pgObj = new PortGroup();
		pgObj.setName(thePGForm.getName());
		pgObj.setVlanId(thePGForm.getVlanId());
		pgObj.setId(thePGForm.getId());
		pgObj.setVswitchName(name);
		vsId = thePGForm.getVSwitchId();
		host = hostName;
		pgObj.setVswitchId(vsId);
		int ret;
		if (flag == 0) {
			ret = portGroupService.insertByObj(true, hostName, pgObj);
		} else {
			ret = portGroupService.updateByObj(hostName, pgObj);
		}
		return "savePortGroup";
	}

	/**
	 * 
	 * @Title: synPortGroup
	 * @Description: 通过接口同步端口组和虚拟交换机数据
	 * @param
	 * @return String
	 * @throws
	 * @author duangh
	 * @version 1.0
	 * @createtime Sep 6, 2012 1:49:11 PM
	 */
	public String synPortGroup() throws Exception {
		try {
			String hostName = Struts2Utils.getParameter("hostName");
			// 端口组列表
			String portgroupUrl = "/vmware/network/getportgroup/[hostName:" + hostName + "]/";
			String getResult = InvokeUtil.invoke(portgroupUrl);
			List<PortGroup> portGroupList = new ArrayList<PortGroup>();
			if (getResult != null) {
				portGroupList = JSONArray.toList(JSONArray.fromObject(getResult), PortGroup.class);
			}
			// 虚拟交换机列表
			String vswitchUrl = "/vmware/network/getvirtualswitch/[hostName:" + hostName + "]/";
			String getvSwitch = InvokeUtil.invoke(vswitchUrl);
			if (getvSwitch != null) {
				int ret = -1;
				List<VirtualSwitch> switchList = new ArrayList<VirtualSwitch>();
				switchList = JSONArray
						.toList(JSONArray.fromObject(getvSwitch), VirtualSwitch.class);
				for (VirtualSwitch vs : switchList) {
					ret = virtualSwitchService.insertByObj(vs);
					if (ret != -1) {
						for (PortGroup pg : portGroupList) {
							if (pg.getVswitchName().equals(vs.getName())) {
								PortGroup obj = new PortGroup();
								obj.setVswitchId(ret);
								obj.setName(pg.getName());
								obj.setType(pg.getType());
								obj.setVswitchName(pg.getVswitchName());
								portGroupService.insertByObj(false, hostName, obj);
							}
						}
					}
				}
			}

		} catch (Exception e) {

		}
		return null;
	}

	/**
	 * 
	 * @Title: addNetWork
	 * @Description: 进入创建vmware网络页面
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Mar 14, 2013 3:50:11 PM
	 */
	public String addNetWork() {
		if (theVSForm == null) {
			theVSForm = new VirtualSwitchForm();
		}
		EntityTreeObj obj = new EntityTreeObj();
		String hostCode = request.getParameter("hostCode");
		obj.setType(TypeConstant.VMWARE_HOST);
		List<EntityTreeObj> hostList = entityTreeService.queryForTree(obj);
		if (hostList != null && hostList.size() != 0) {
			theVSForm.setHostList(hostList);
		}
		/*
		 * List<TbCloud2NetInfoObj> netList = new
		 * ArrayList<TbCloud2NetInfoObj>(); String param =
		 * "/vmware/domain/synVCenter/[TYPE:HOST].[CODE:" +
		 * hostList.get(0).getEntityId() + "]/"; String result =
		 * InvokeUtil.invoke(param); JSONArray js =
		 * JSONArray.fromObject(result); List<JSONObject> nicList =
		 * JSONArray.toList(js, JSONObject.class); for (JSONObject json :
		 * nicList) { if (json.getString("TYPE") != null) { if
		 * ("VSS".equals(json.getString("TYPE"))) { TbCloud2NetInfoObj t = new
		 * TbCloud2NetInfoObj(); t.setNAME(json.getString("NAME"));
		 * t.setNet_uuid(json.getString("CODE")); netList.add(t); } } }
		 * theVSForm.setResultList(netList);
		 */
		if (hostCode != null) {
			request.setAttribute("hostCode", hostCode);
		}
		return "add_net";
	}

	/**
	 * 
	 * @Title: getNetList
	 * @Description: 获取网卡列表
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Mar 18, 2013 8:59:25 AM
	 */
	public String getNicList() {
		HttpServletRequest request = Struts2Utils.getRequest();
		String host_code = request.getParameter("host_code");

		// please change ,this is wrong --note by duangh
		String param = "/vmware/domain/synVCenterTemp/[TYPE:HOST].[CODE:" + host_code
				+ "].[FILTER:+VSS+]/";
		String result = InvokeUtil.invoke(param);
		JSONArray js = JSONArray.fromObject(result);
		List<TbCloud2NetInfoObj> netList = new ArrayList<TbCloud2NetInfoObj>();
		List<JSONObject> nicList = JSONArray.toList(js, JSONObject.class);
		for (JSONObject json : nicList) {
			if (json.getString("TYPE") != null) {
				if ("VSS".equals(json.getString("TYPE"))) {
					TbCloud2NetInfoObj t = new TbCloud2NetInfoObj();
					t.setNAME(json.getString("NAME"));
					t.setNet_uuid(json.getString("CODE"));
					netList.add(t);
				}
			}

		}
		TbCloud2NetInfoObj tb = new TbCloud2NetInfoObj();
		tb.setNAME("创建vSphere标准交换机");
		tb.setNet_uuid("vswitch");
		netList.add(tb);
		StringBuffer buff = new StringBuffer("{");
		String info = "";
		int j = 0;
		for (int i = 0; i < netList.size(); i++) {
			TbCloud2NetInfoObj os = new TbCloud2NetInfoObj();
			os = netList.get(i);
			if (j != 0) {
				buff.append(",");
			}
			buff.append("'");
			buff.append((String) os.getNet_uuid());
			buff.append("':'");
			buff.append((String) os.getNAME());
			buff.append("'");
			j++;
		}

		buff.append("}");
		info = buff.toString();
		request.setAttribute("info", info);
		return SUCCESS;
	}

	/**
	 * 
	 * @Title: createNetWork
	 * @Description: 创建vmware网络
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @throws UnsupportedEncodingException
	 * @createtime Mar 18, 2013 10:05:11 AM
	 */
	public String createNetWork() throws UnsupportedEncodingException {
		HttpServletRequest request = Struts2Utils.getRequest();
		HttpServletResponse response = Struts2Utils.getResponse();
		String host_code = request.getParameter("host_code");
		String type = request.getParameter("type");
		String nic = request.getParameter("nic");
		String name = request.getParameter("name");
		String vlan = request.getParameter("vlan");
		String ip_type = request.getParameter("ip_type");
		String ip = request.getParameter("ip");
		String sub_net = request.getParameter("sub_net");
		String vm_kernel = request.getParameter("vm_kernel");
		nic = URLDecoder.decode(nic, "UTF-8");
		name = URLDecoder.decode(name, "UTF-8");
		Map paramMap = new HashMap();
		paramMap.put("type", type);
		paramMap.put("host_code", host_code);
		paramMap.put("name", name);
		paramMap.put("vlan", vlan);
		paramMap.put("nic", nic);
		paramMap.put("ip_type", ip_type);
		paramMap.put("ip", ip);
		paramMap.put("sub_net", sub_net);
		paramMap.put("vm_kernel", vm_kernel);
		String result = portGroupService.createNetWork(paramMap);
		response.setContentType("type/html;charset=UTF-8");
		// out = response.getWriter();
		// out.println(result);
		// out.close();
		PrintWriterOut.printWirter(response, result);
		return null;
	}

	/**
	 * 
	 * @Title: removeVirtualSwitch
	 * @Description: 删除虚拟交换机
	 * @return String
	 * @author duangh
	 * @version 1.0
	 */
	public String removeVirtualSwitch() {
		HttpServletRequest request = Struts2Utils.getRequest();
		String hostCode = request.getParameter("hostCode");
		String vsName = request.getParameter("name");
		String vsId = request.getParameter("vsId");
		VirtualSwitch vs = new VirtualSwitch();
		vs.setId(Integer.parseInt(vsId));
		vs.setHostCode(hostCode);
		vs.setName(vsName);
		String result = portGroupService.removeVirtualSwitch(vs);
		try {
			PrintWriter p = Struts2Utils.getResponse().getWriter();
			p.print(result);
			p.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public VirtualSwitchForm getTheVSForm() {
		return theVSForm;
	}

	public void setTheVSForm(VirtualSwitchForm theVSForm) {
		this.theVSForm = theVSForm;
	}

	private int vsId;

	public int getVsId() {
		return vsId;
	}

	public void setVsId(int vsId) {
		this.vsId = vsId;
	}

	private String name;
	private String host;
	private String vssType;

	public String getVssType() {
		return vssType;
	}

	public void setVssType(String vssType) {
		this.vssType = vssType;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
