package com.sitech.basd.yicloud.web.portgroup.action;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;

import com.sitech.basd.yicloud.domain.portgroup.PortGroup;
import com.sitech.basd.yicloud.domain.switches.VirtualSwitch;
import com.sitech.basd.yicloud.service.portgroup.PortGroupService;
import com.sitech.basd.yicloud.service.switches.VirtualSwitchService;
import com.sitech.basd.yicloud.util.InvokeUtil;

public class PortGroupSynchron {
	private PortGroupService portGroupService;
	private VirtualSwitchService virtualSwitchService;

	public void setVirtualSwitchService(
			VirtualSwitchService virtualSwitchService) {
		this.virtualSwitchService = virtualSwitchService;
	}

	public void setPortGroupService(PortGroupService portGroupService) {
		this.portGroupService = portGroupService;
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
	public String synPortGroup(String hostName) throws Exception {
		try {
			//端口组列表
			String portgroupUrl = "/vmware/network/getportgroup/[hostName:"
					+ hostName + "]/";
			String getResult = InvokeUtil.invoke(portgroupUrl);
			List<PortGroup> portGroupList = new ArrayList<PortGroup>();
			if (getResult != null) {
				portGroupList = JSONArray.toList(JSONArray
						.fromObject(getResult), PortGroup.class);
			}
			//虚拟交换机列表
			String vswitchUrl = "/vmware/network/getvirtualswitch/[hostName:"
					+ hostName + "]/";
			String getvSwitch = InvokeUtil.invoke(vswitchUrl);
			if (getvSwitch != null) {
				int ret = -1;
				List<VirtualSwitch> switchList = new ArrayList<VirtualSwitch>();
				switchList = JSONArray.toList(JSONArray.fromObject(getvSwitch),
						VirtualSwitch.class);
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
								portGroupService.insertByObj(false, hostName,
										obj);
							}
						}
					}
				}
			}

		} catch (Exception e) {

		}
		return null;
	}

	public static void main(String[] args) {
		PortGroupSynchron p = new PortGroupSynchron();
		try {
			p.synPortGroup("172.21.2.226");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
