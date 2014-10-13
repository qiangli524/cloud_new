package com.sitech.basd.component.web.dhcp;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sitech.basd.component.domain.dhcp.DhcpObj;
import com.sitech.basd.component.service.dhcp.DhcpService;
import com.sitech.basd.sxcloud.cloud.domain.net.TbCloud2IpInfoObj;
import com.sitech.basd.sxcloud.cloud.domain.net.TbCloud2NetInfoObj;
import com.sitech.basd.sxcloud.cloud.service.net.IpService;
import com.sitech.basd.sxcloud.cloud.service.net.NetService;
import com.sitech.basd.sxcloud.rsmu.web.CRUDBaseAction;
import com.sitech.basd.sxcloud.util.Struts2Utils;
import com.sitech.utils.randomid.RandomUUID;
import com.sitech.utils.servlet.PrintWriterOut;

@SuppressWarnings("serial")
@Controller("dhcpAction")
@Scope("prototype")
public class DhcpAction extends CRUDBaseAction {

	@Autowired
	private DhcpService dhcpService;

	@Autowired
	private IpService ipService;
	@Autowired
	private NetService netService;

	private String oper;// 操作

	private String id;// dhcp 的id

	private String netId;// ip池的id

	private List<DhcpObj> resultList;// 结果集

	private List<TbCloud2NetInfoObj> ipPoolList;

	private Map<String, List<TbCloud2IpInfoObj>> listMap;

	private DhcpObj dhcpObj;

	public DhcpObj getDhcpObj() {
		return dhcpObj;
	}

	public void setDhcpObj(DhcpObj dhcpObj) {
		this.dhcpObj = dhcpObj;
	}

	public String getOper() {
		return oper;
	}

	public void setOper(String oper) {
		this.oper = oper;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<DhcpObj> getResultList() {
		return resultList;
	}

	public void setResultList(List<DhcpObj> resultList) {
		this.resultList = resultList;
	}

	public List<TbCloud2NetInfoObj> getIpPoolList() {
		return ipPoolList;
	}

	public void setIpPoolList(List<TbCloud2NetInfoObj> ipPoolList) {
		this.ipPoolList = ipPoolList;
	}

	public String getNetId() {
		return netId;
	}

	public void setNetId(String netId) {
		this.netId = netId;
	}

	public Map<String, List<TbCloud2IpInfoObj>> getListMap() {
		return listMap;
	}

	public void setListMap(Map<String, List<TbCloud2IpInfoObj>> listMap) {
		this.listMap = listMap;
	}

	/**
	 * @Title: insertDhcp
	 * @Description: 进入添加页面
	 * @param
	 * @return String
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-8-16 下午4:51:32
	 */
	@SuppressWarnings("unchecked")
	public String insertDhcp() {
		TbCloud2NetInfoObj netInfoObj = new TbCloud2NetInfoObj();
		ipPoolList = netService.queryNetListByObj(netInfoObj);
		/*
		 * for(int i = 0;i<ipPoolList.size();i++){ TbCloud2IpInfoObj obj = new
		 * TbCloud2IpInfoObj(); obj.setNET_ID(ipPoolList.get(i).getNET_ID());
		 * List<TbCloud2IpInfoObj> ipList = ipService.queryAchivableIpList(obj);
		 * listMap.put(ipPoolList.get(i).getNET_ID(), ipList); }
		 */
		return "add";
	}

	/**
	 * @Title: updateDhcp
	 * @Description: 进入修改页面
	 * @param
	 * @return String
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-8-16 下午5:00:35
	 */
	public String updateDhcp() {
		DhcpObj obj = new DhcpObj();
		obj.setID(id);
		List<DhcpObj> list = dhcpService.queryForList(obj);
		if (list != null && list.size() > 0) {
			dhcpObj = list.get(0);
		}
		return "edit";
	}

	/**
	 * @Title: deleteDhcp
	 * @Description: 删除操作
	 * @param
	 * @return void
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-8-16 下午5:02:33
	 */
	public void deleteDhcp() {
		DhcpObj obj = new DhcpObj();
		obj.setID(id);
		List<DhcpObj> list = dhcpService.queryForList(obj);
		if (list.size() > 0) {
			dhcpObj = list.get(0);
		}
		int ret = dhcpService.deleteDhcp(obj);

		// 删除成功之后，释放ip
		if (ret != 0) {
			String ip = dhcpObj.getIP();
			TbCloud2IpInfoObj tbCloud2IpInfoObj = new TbCloud2IpInfoObj();
			tbCloud2IpInfoObj.setISUSED("0");
			tbCloud2IpInfoObj.setIPADDRESS(ip);
			ipService.updateIPByObj(tbCloud2IpInfoObj);
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
	 * @Title: queryDhcpList
	 * @Description: 展示dhcp列表
	 * @param
	 * @return String
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-8-16 下午5:23:43
	 */
	public String queryDhcpList() {
		if (dhcpObj == null) {
			dhcpObj = new DhcpObj();
		}
		dhcpObj.setPagination(this.getPaginater().initPagination(request));
		resultList = dhcpService.queryForList(dhcpObj);
		return "list";
	}

	/**
	 * @Title: saveDhcp
	 * @Description:保存编辑的dhcp信息
	 * @param
	 * @return String
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-8-16 下午5:29:13
	 */
	public String saveDhcp() {
		int ret = -1;
		if ("add".equals(oper)) {
			String dhcpid = RandomUUID.getUuid();
			dhcpObj.setID(dhcpid);
			ret = dhcpService.insertDhcp(dhcpObj);

			// 添加成功，锁定ip
			if (ret != -1) {
				TbCloud2IpInfoObj ipObj = new TbCloud2IpInfoObj();
				ipObj.setIPADDRESS(dhcpObj.getIP());
				ipObj.setISUSED("1");
				ipService.updateIPByObj(ipObj);
			}

		} else {
			ret = dhcpService.updateDhcp(dhcpObj);
		}

		JSONObject jo = new JSONObject();
		if (ret != -1) {
			jo.put("obj", dhcpObj);
			try {
				// PrintWriter pw = response.getWriter();
				// pw.print(jo);
				// pw.flush();
				// pw.close();
				PrintWriterOut.printWirter(response, jo);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * @Title: getIpListByNet
	 * @Description: 获取指定ip池下的ip地址
	 * @param
	 * @return String
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @createtime 2013-8-29 下午14:29:13
	 */
	public String getIpListByNet() {
		TbCloud2IpInfoObj obj = new TbCloud2IpInfoObj();
		obj.setNET_ID(netId);
		List<TbCloud2IpInfoObj> ipList = ipService.queryAchivableIpList(obj);

		HttpServletResponse response = Struts2Utils.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		JSONArray jo = new JSONArray();
		jo.addAll(ipList);
		// PrintWriter p = response.getWriter();
		// p.print(jo);
		// p.flush();
		// p.close();
		PrintWriterOut.printWirter(response, jo);
		return null;
	}

}
