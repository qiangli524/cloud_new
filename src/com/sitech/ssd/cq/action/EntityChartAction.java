package com.sitech.ssd.cq.action;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sitech.basd.resource.domain.united.UnitedTreeObj;
import com.sitech.basd.resource.service.united.UnitedTreeService;
import com.sitech.basd.sxcloud.util.Struts2Utils;
import com.sitech.basd.util.PropertyUtil;
import com.sitech.basd.yicloud.domain.vmauthority.AuthorityTreeObj;
import com.sitech.basd.yicloud.domain.vmauthority.VmAuthorityObj;
import com.sitech.basd.yicloud.service.vmauthority.VmAuthorityService;
import com.sitech.utils.servlet.PrintWriterOut;
import com.sitech.vo.util.UnitedConstant;

/**
 * 
 * <p>
 * Title: EntityChartAction
 * </p>
 * <p>
 * Description: 实体报表Action
 * </p>
 * <p>
 * Copyright: Copyright (c) 2014
 * </p>
 * <p>
 * Company: SI-TECH
 * </p>
 * 
 * @author huojla
 * @version 1.0
 * @createtime 2014-6-26 下午10:08:17
 * 
 */
@Controller("entityChartAction")
@Scope("prototype")
public class EntityChartAction {
	@Autowired
	private PropertyUtil unitedTreeIconProp;
	@Autowired
	private VmAuthorityService vmAuthorityService;
	@Autowired
	private UnitedTreeService unitedTreeService;

	// 获取分配主机还是虚拟机的标识 host为分配主机 vm为分配虚拟机
	private String flag;

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	/**
	 * 
	 * @Title: gotoVmChainChartPage
	 * @Description: 进入虚拟机环比报表界面
	 * @param
	 * @return String
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2014-6-26 下午5:15:33
	 */
	public String gotoVmChainChartPage() {
		return "gotoVmChainChartPage";
	}

	/**
	 * 
	 * @Title: intoEntityTreePage
	 * @Description: 进入实体树界面
	 * @param
	 * @return String
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2014-6-26 下午10:33:06
	 */
	public String intoEntityTreePage() {
		return "intoEntityTree";
	}

	/**
	 * 
	 * @Title: asyncForTree
	 * @Description: 异步加载树
	 * @param
	 * @return void
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2014-6-26 下午10:11:54
	 */
	public String asyncForTree() throws SQLException {
		UnitedTreeObj treeObj = new UnitedTreeObj();
		HttpServletRequest request = Struts2Utils.getRequest();
		String id = request.getParameter("id");
		String userId = request.getParameter("userId");
		if (id == null || "".equals(id)) {
			treeObj.setType(UnitedConstant.ROOT);
		} else {
			treeObj.setParent_id(id);
			if (flag.equals("host")) {
				// 只显示到主机那一层
				treeObj.setFlag("host");
			}
		}
		List<UnitedTreeObj> resultList = unitedTreeService.queryForTreeList(treeObj);
		List<AuthorityTreeObj> list = new ArrayList<AuthorityTreeObj>();
		if (list != null) {
			UnitedTreeObj tempObj = new UnitedTreeObj();
			for (UnitedTreeObj u : resultList) {
				if (u.getVtype() != null) {
					if (!"1".equals(u.getVtype()) && !"2".equals(u.getVtype())) {
						continue;
					}
				}
				AuthorityTreeObj tObj = new AuthorityTreeObj();
				tObj.setId(u.getId());
				tObj.setName(u.getName());
				tObj.setType(u.getType());
				tObj.setVtype(u.getVtype());
				tObj.setUuid(u.getUuid());
				tObj.setConnect_id(u.getConnect_id());
				// 判断是不是父节点
				tempObj.setParent_id(u.getId());
				List<UnitedTreeObj> lst = unitedTreeService.queryForTreeList(tempObj);
				if (lst == null || lst.size() == 0) {
					tObj.setIsParent(false);
				}
				// 设置图标
				String type = u.getType();
				if (UnitedConstant.ROOT.equals(type)) {// 安徽移动私有云管理平台名称
					tObj.setIcon(unitedTreeIconProp.getString("anhui"));
				} else if (UnitedConstant.DATACENTER.equals(type)) { // 数据中心
					tObj.setIcon(unitedTreeIconProp.getString("datacenter"));
				} else if (UnitedConstant.CLUSTER.equals(type)) { // 集群
					tObj.setIcon(unitedTreeIconProp.getString("cluster"));
				} else if (UnitedConstant.HOST.equals(type)) { // 主机
					tObj.setIcon(unitedTreeIconProp.getString("host"));
				} else if (UnitedConstant.VM.equals(type)) {
					tObj.setIcon(unitedTreeIconProp.getString("vm_stop"));
				}
				// 判断checkbox是否处于选中状使,若在表中查询到信息，说明具有权限，否则不具有
				VmAuthorityObj vObj = new VmAuthorityObj();
				vObj.setENTITY_TYPE(tObj.getType());
				vObj.setENTITY_ID(tObj.getUuid());
				vObj.setCONNECT_ID(tObj.getConnect_id());
				// 若是虚拟机权限配置，只显示虚拟机的是否check
				if (userId != null && !"".equals(userId) && !"null".equals(userId)) {
					vObj.setUSERID(Integer.parseInt(userId));
				}
				List<VmAuthorityObj> vList = vmAuthorityService.queryForList(vObj);
				if (vList != null && vList.size() > 0) {
					tObj.setChecked(true);
					vObj = (VmAuthorityObj) vList.get(0);
					tObj.setAuthorityId(vObj.getID());
				}
				/*
				 * 根据类型判断是否展示复选框
				 */
				if (UnitedConstant.HOST.equals(u.getType())) {
					if ("vm".equals(flag)) {
						tObj.setNocheck(true);
					} else if ("host".equals(flag)) {
						tObj.setIsParent(false);
					}
				}
				list.add(tObj);
			}
		}
		HttpServletResponse response = Struts2Utils.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		JSONArray json = JSONArray.fromObject(list);
		try {
			PrintWriterOut.printWirter(response, json.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
