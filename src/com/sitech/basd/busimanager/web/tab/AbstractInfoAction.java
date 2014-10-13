package com.sitech.basd.busimanager.web.tab;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sitech.basd.busimanager.domain.busitree.BusiManagerTree;
import com.sitech.basd.busimanager.service.busitree.BusiManagerTreeService;
import com.sitech.basd.sxcloud.cloud.domain.vmhost.VMHostObj;
import com.sitech.basd.sxcloud.cloud.service.vmhost.VMHostService;
import com.sitech.basd.sxcloud.util.Struts2Utils;
import com.sitech.utils.servlet.PrintWriterOut;

@Controller("abstractInfoAction")
@Scope("prototype")
public class AbstractInfoAction {

	@Autowired
	private BusiManagerTreeService busiManagerTreeService;

	@Autowired
	private VMHostService vmHostService;

	private VMHostObj vmObj; // 虚拟机信息

	private String id;

	private long allCount;// 业务中心的数量

	private long firstCount;// 业务系统

	private long secondCount;// 子业务系统

	private long resourceCount;// 虚拟机

	private BusiManagerTree treeObj;

	/**
	 * 0，一级节点，1业务系统，2，子业务系统，3虚拟机
	 */
	private String type;

	private String entity_id;

	private String connect_id;

	private String operType;

	private String name;

	private String monitor_id;// 用以显示cpu、内存、I/O的监控

	public String treeTabs() {
		if (connect_id != null && !"".equals(connect_id)) {
			monitor_id = connect_id + "_";
			if (entity_id != null && !"".equals(entity_id)) {
				monitor_id = monitor_id + entity_id;
			} else {
				monitor_id = null;
			}
			monitor_id = monitor_id.trim();
		}
		return "tabs";
	}

	/**
	 * 获取根节点的所有的统计信息
	 * 
	 * @return
	 */
	public String getAllAbstractInfo() {
		return "all_chart_info_center";
	}

	public String getFirstAbstractInfo() {
		BusiManagerTree tempObj1 = new BusiManagerTree();
		tempObj1.setId(id);
		List<BusiManagerTree> objs = busiManagerTreeService
				.queryForTree(tempObj1);
		treeObj = objs.get(0);
		BusiManagerTree tempObj = new BusiManagerTree();
		tempObj.setId1(id);
		Map<String, Long> maps = busiManagerTreeService
				.getTypeCountsByPoint(tempObj);
		secondCount = maps.get("2") == null ? 0 : maps.get("2");
		resourceCount = maps.get("3") == null ? 0 : maps.get("3");
		long lCount = maps.get("4") == null ? 0 : maps.get("4");
		resourceCount += lCount;

		operType = "first_info";
		return "first_info";
	}

	public String getSecondAbstractInfo() {
		BusiManagerTree tempObj1 = new BusiManagerTree();
		tempObj1.setId(id);
		List<BusiManagerTree> objs = busiManagerTreeService
				.queryForTree(tempObj1);
		treeObj = objs.get(0);
		BusiManagerTree tempObj = new BusiManagerTree();
		tempObj.setId2(id);
		Map<String, Long> maps = busiManagerTreeService
				.getTypeCountsByPoint(tempObj);
		resourceCount = maps.get("3") == null ? 0 : maps.get("3");
		resourceCount += maps.get("4");
		operType = "second_info";
		return "second_info";
	}

	/**
	 * 获取所有的
	 * 
	 * @Title: getAllChartInfo
	 * @param
	 * @return String
	 * @throws
	 * @author shijc
	 * @version 1.0
	 * @createtime 2013-9-16 下午5:51:12
	 */
	public String getAllChartInfo() {
		return "all_chart_info";
	}

	public String vmInfo() {
		VMHostObj obj = new VMHostObj();
		obj.setVH_UUID(entity_id);
		obj.setConnectId(connect_id);
		vmObj = vmHostService.queryByObj(obj);
		// 本地存储转化为G
		DecimalFormat df = new DecimalFormat("#.00");
		if (vmObj != null) {
			if (vmObj.getVH_STORAGE() != null
					&& !"".equals(vmObj.getVH_STORAGE())) {
				// Double storageM = Double.parseDouble(vmObj.getVH_STORAGE());
				// Double storageG = storageM / 1024.00;
				// vmObj.setVH_STORAGE(df.format(storageG).toString());
			}
		}
		return "vmInfo";
	}

	public String getAllChartInfoSync() {
		HttpServletResponse response = Struts2Utils.getResponse();
		response.setCharacterEncoding("UTF-8");
		try {
			BusiManagerTree obj = new BusiManagerTree();
			// PrintWriter out = response.getWriter();
			obj.setType(0);
			List<BusiManagerTree> objs = busiManagerTreeService
					.queryForTree(obj);
			JSONArray arrays = new JSONArray();
			for (BusiManagerTree o : objs) {
				String name = o.getName();
				BusiManagerTree obj1 = new BusiManagerTree();
				obj1.setId(o.getId());
				Map<String, Long> maps = busiManagerTreeService
						.getTypeCountsByPoint(obj1);
				long fCount = maps.get("1") == null ? 0 : maps.get("1");
				long sCount = maps.get("2") == null ? 0 : maps.get("2");
				long rCount = maps.get("3") == null ? 0 : maps.get("3");
				long lCount = maps.get("4") == null ? 0 : maps.get("4");
				JSONObject json = new JSONObject();
				json.put("name", name);
				json.put(
						"data",
						new Integer[] { Integer.parseInt(fCount + ""),
								Integer.parseInt(sCount + ""),
								Integer.parseInt(rCount + lCount + "") });
				arrays.add(json);
			}
			PrintWriterOut.printWirter(response, arrays);
		} catch (Exception e) {
		}
		return null;
	}

	/**
	 * 通过数据中心查询下面所有的系统和资源
	 * 
	 * @Title: getChartInfoSyncByCenter
	 * @param
	 * @return String
	 * @throws
	 * @author shijc
	 * @version 1.0
	 * @createtime 2013-9-16 下午5:45:22
	 */
	public String getChartInfoSyncByCenter() {
		HttpServletResponse response = Struts2Utils.getResponse();
		response.setCharacterEncoding("UTF-8");
		try {
			BusiManagerTree obj = new BusiManagerTree();
			// PrintWriter out = response.getWriter();
			/**
			 * 通过id判断是从哪里进入页面的
			 */
			if (id != null) {
				obj.setParent_id(id);
			}
			List<BusiManagerTree> objs = busiManagerTreeService
					.queryForTree(obj);
			JSONArray arrays = new JSONArray();
			for (BusiManagerTree o : objs) {
				String name = o.getName();
				BusiManagerTree obj1 = new BusiManagerTree();
				obj1.setId1(o.getId());
				Map<String, Long> maps = busiManagerTreeService
						.getTypeCountsByPoint(obj1);
				long sCount = maps.get("2") == null ? 0 : maps.get("2");
				long rCount = maps.get("3") == null ? 0 : maps.get("3");
				long lCount = maps.get("4") == null ? 0 : maps.get("4");
				JSONObject json = new JSONObject();
				json.put("name", name);
				json.put("data", new Integer[] { Integer.parseInt(sCount + ""),
						Integer.parseInt(rCount + lCount + "") });
				arrays.add(json);
			}
			PrintWriterOut.printWirter(response, arrays);
			id = null;
		} catch (Exception e) {
		}
		return null;
	}

	public String businessInfo() {
		BusiManagerTree obj = new BusiManagerTree();
		obj.setId(id);
		List<BusiManagerTree> objs = busiManagerTreeService.queryForTree(obj); // 本地存储转化为G
		if (objs != null && objs.size() > 0) {
			treeObj = objs.get(0);
		}
		BusiManagerTree obj1 = new BusiManagerTree();
		obj1.setParent_id(id);
		obj1.setType(3);
		List<BusiManagerTree> objs1 = busiManagerTreeService.queryForTree(obj1); // 本地存储转化为G
		int i = 0;
		if (objs != null) {
			for (BusiManagerTree tObj : objs1) {
				i++;
			}
			resourceCount = i;
		}
		return "businessInfo";
	}

	public String getResourceAbstractInfo() {

		operType = "resource_info";
		return "resource_info";
	}

	public long getAllCount() {
		return allCount;
	}

	public void setAllCount(long allCount) {
		this.allCount = allCount;
	}

	public long getFirstCount() {
		return firstCount;
	}

	public void setFirstCount(long firstCount) {
		this.firstCount = firstCount;
	}

	public long getSecondCount() {
		return secondCount;
	}

	public void setSecondCount(long secondCount) {
		this.secondCount = secondCount;
	}

	public long getResourceCount() {
		return resourceCount;
	}

	public void setResourceCount(long resourceCount) {
		this.resourceCount = resourceCount;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getConnect_id() {
		return connect_id;
	}

	public String getOperType() {
		return operType;
	}

	public String getEntity_id() {
		return entity_id;
	}

	public void setEntity_id(String entity_id) {
		this.entity_id = entity_id;
	}

	public void setOperType(String operType) {
		this.operType = operType;
	}

	public VMHostObj getVmObj() {
		return vmObj;
	}

	public void setVmObj(VMHostObj vmObj) {
		this.vmObj = vmObj;
	}

	public BusiManagerTree getTreeObj() {
		return treeObj;
	}

	public void setTreeObj(BusiManagerTree treeObj) {
		this.treeObj = treeObj;
	}

	public void setConnect_id(String connect_id) {
		this.connect_id = connect_id;
	}

	public String getMonitor_id() {
		return monitor_id;
	}

	public void setMonitor_id(String monitor_id) {
		this.monitor_id = monitor_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
