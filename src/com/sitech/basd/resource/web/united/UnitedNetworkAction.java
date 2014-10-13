package com.sitech.basd.resource.web.united;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.sitech.basd.resource.domain.united.UnitedTreeObj;
import com.sitech.basd.resource.service.united.UnitedNetworkService;
import com.sitech.basd.sxcloud.cloud.domain.net.TbCloud2NetInfoObj;
import com.sitech.basd.sxcloud.cloud.service.net.NetService;
import com.sitech.basd.sxcloud.util.Struts2Utils;
import com.sitech.utils.servlet.PrintWriterOut;

@Component("unitedNetworkAction")
@Scope("prototype")
public class UnitedNetworkAction {
	private UnitedTreeObj obj;
	private TbCloud2NetInfoObj netObj;
	private String result;
	private String name;
	private String description;
	private String netDomId;
	private String subID;
	@Autowired
	private UnitedNetworkService unitedNetworkService;
	@Autowired
	private NetService netService;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 
	 * @Title: addDomian
	 * @Description: 添加网络域
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime 2013-9-11 上午9:50:27
	 */
	public String addDomain() {
		return "addDomain";
	}

	/**
	 * 
	 * @Title: saveDomain
	 * @Description:保存网络域
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @throws UnsupportedEncodingException
	 * @createtime 2013-9-11 上午10:53:43
	 */
	public String saveDomain() throws UnsupportedEncodingException {
		if (obj == null) {
			obj = new UnitedTreeObj();
		}
		try {
			unitedNetworkService.addDomain(obj);
		} catch (SQLException e) {
			result = e.getMessage();
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 
	 * @Title: addVlan
	 * @Description: 添加VLAN
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-9-11
	 */
	public String addVlan() {
		List netDomList = unitedNetworkService.queryNetDomain();
		obj.setNetDomList(netDomList);
		return "addVlan";
	}

	/**
	 * 
	 * @Title: saveVlan
	 * @Description: 保存VLAN
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @throws UnsupportedEncodingException
	 * @createtime 2013-9-11
	 */
	public String saveVlan() throws UnsupportedEncodingException {
		if (obj == null) {
			obj = new UnitedTreeObj();
		}
		if (netObj == null) {
			netObj = new TbCloud2NetInfoObj();
		}
		netObj.setNAME(URLDecoder.decode(name, "utf-8"));
		netObj.setDESCRIPTION(URLDecoder.decode(description, "utf-8"));
		if (netObj.getNET_ID() == null || "".equals(netObj.getNET_ID())) {
			try {
				String uuid = netService.insertNet(netObj);
				obj.setUuid(uuid);
				obj.setName(netObj.getNAME());
				obj.setParent_id(obj.getPratentId());
				unitedNetworkService.addDomain(obj);
			} catch (SQLException e) {
				result = e.getMessage();
				e.printStackTrace();
			}
		} else {
			try {
				obj.setUuid(netObj.getNET_ID());
				obj.setName(netObj.getNAME());
				obj.setParent_id(obj.getPratentId());
				unitedNetworkService.updOperate(obj);
				netService.updateByObj(netObj);
			} catch (SQLException e) {
				result = e.getMessage();
				e.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * 
	 * @Title: editVlan
	 * @Description: 修改VLAN
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-9-11
	 */
	public String editVlan() throws SQLException {
		if (netObj == null) {
			netObj = new TbCloud2NetInfoObj();
		}
		netObj = netService.queryByObj(netObj);
		List netDomList = unitedNetworkService.queryNetDomain();
		obj.setNetDomList(netDomList);
		subID = obj.getPratentId();
		netDomId = obj.getDomPratentId();
		return "addVlan";
	}

	/**
	 * 
	 * @Title: delOperate
	 * @Description:移除网络域和子网络域操作
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-9-11
	 */
	public String delOperate() {
		if (obj == null) {
			obj = new UnitedTreeObj();
		}
		try {
			unitedNetworkService.delOperate(obj);
		} catch (SQLException e) {
			result = e.getMessage();
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 
	 * @Title: delVlan
	 * @Description: 删除VLAN
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-9-11
	 */
	public String delVlan() {
		if (obj == null) {
			obj = new UnitedTreeObj();
		}
		if (netObj == null) {
			netObj = new TbCloud2NetInfoObj();
		}
		try {
			netObj.setNET_ID(obj.getUuid());
			unitedNetworkService.delOperate(obj);
			netService.deleteByObj(netObj);

		} catch (SQLException e) {
			result = e.getMessage();
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 
	 * @Title: querySubNet
	 * @Description: 添加时通过网络域查询出子网络域
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-9-11
	 */
	public String querySubNet() {
		if (obj == null) {
			obj = new UnitedTreeObj();
		}
		HttpServletResponse response = Struts2Utils.getResponse();
		response.setContentType("text/xml;charset=UTF-8");
		// PrintWriter out = null;
		try {
			// out = response.getWriter();
			StringBuffer sbf = new StringBuffer();
			List<UnitedTreeObj> subNetList = unitedNetworkService.querySubNet(obj);
			sbf.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
			sbf.append("<Init>");
			if (null != subNetList && subNetList.size() > 0) {
				for (UnitedTreeObj objT : subNetList) {
					sbf.append("<subDom value = \"" + objT.getId() + "\" text = \""
							+ objT.getName() + "\"/>");
				}
			}
			sbf.append("</Init>");
			// out.print(sbf.toString());
			PrintWriterOut.printWirter(response, sbf.toString());
		} finally {
			// out.flush();
			// out.close();
		}
		return null;
	}

	/**
	 * 
	 * @Title: selectSub
	 * @Description: 修改时通过网络域查询出子网络域
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-9-11
	 */
	public void selectSub() {
		if (obj == null) {
			obj = new UnitedTreeObj();
		}
		obj.setParent_id(netDomId);
		List subNetList = unitedNetworkService.querySubNet(obj);
		JSONArray json = JSONArray.fromObject(subNetList);
		try {
			HttpServletResponse response = Struts2Utils.getResponse();
			response.setCharacterEncoding("UTF-8");
			// PrintWriter pw = response.getWriter();
			// pw.print(json.toString());
			// pw.flush();
			// pw.close();
			PrintWriterOut.printWirter(response, json.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public UnitedTreeObj getObj() {
		return obj;
	}

	public void setObj(UnitedTreeObj obj) {
		this.obj = obj;
	}

	public TbCloud2NetInfoObj getNetObj() {
		return netObj;
	}

	public void setNetObj(TbCloud2NetInfoObj netObj) {
		this.netObj = netObj;
	}

	public String getNetDomId() {
		return netDomId;
	}

	public void setNetDomId(String netDomId) {
		this.netDomId = netDomId;
	}

	public String getSubID() {
		return subID;
	}

	public void setSubID(String subID) {
		this.subID = subID;
	}

}
