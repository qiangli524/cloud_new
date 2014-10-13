package com.sitech.basd.fusioncharts.action;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sitech.basd.fusioncharts.service.HostSystemTopNService;
import com.sitech.basd.fusioncharts.util.JacksonUtil;
import com.sitech.basd.fusioncharts.vo.FusionCharts;
import com.sitech.basd.resource.domain.united.UnitedTreeObj;
import com.sitech.basd.resource.service.united.UnitedTreeService;
import com.sitech.basd.sxcloud.rsmu.web.BaseAction;
import com.sitech.utils.servlet.PrintWriterOut;
import com.sitech.vo.util.UnitedConstant;

/**
 * 
 * <p>
 * Title: EntityTopNAction
 * </p>
 * <p>
 * Description: 实体TopN报表Action
 * </p>
 * <p>
 * Copyright: Copyright (c) 2013
 * </p>
 * <p>
 * Company: SI-TECH
 * </p>
 * 
 * @author huojla
 * @version 1.0
 * @createtime 2013-9-14 上午9:35:10
 * 
 */
@SuppressWarnings("serial")
@Controller("entityTopNFusionchartsAction")
@Scope("prototype")
public class EntityTopNFusionchartsAction extends BaseAction {

	@Autowired
	private HostSystemTopNService hostSystemTopNService;

	@Autowired
	private UnitedTreeService unitedTreeService;

	private String id;// 树上节点id

	private int top_num;// N值

	private String type;// 资源类型

	private List<UnitedTreeObj> treeList;// 树节点集合

	/**
	 * @Title: topTabs
	 * @Description: 跳转到tabs页
	 * @param
	 * @return String
	 * @throws
	 * @author lipp
	 * @version 1.0
	 * @createtime 2013-12-2 下午4:14:32
	 */
	public String topTabs() {
		return "top_tabs";
	}

	/**
	 * @Title: hostTopN
	 * @Description: 跳转到主机topN页面
	 * @param
	 * @return String
	 * @throws
	 * @author lipp
	 * @version 1.0
	 * @createtime 2013-12-2 下午4:17:04
	 */
	public String hostTopN() {
		if (id == null || "".equals(id)) {
			this.initTreeList();
		}
		return "hosttopn";
	}

	/**
	 * @Title: vmTopN
	 * @Description: 跳转到虚拟机topN页面
	 * @param
	 * @return String
	 * @throws
	 * @author lipp
	 * @version 1.0
	 * @createtime 2013-12-2 下午4:17:24
	 */
	public String vmTopN() {
		if (id == null || "".equals(id)) {
			this.initTreeList();
		}
		return "vmtopn";
	}

	@SuppressWarnings("unchecked")
	private void initTreeList() {
		UnitedTreeObj unitedTreeObj = new UnitedTreeObj();
		unitedTreeObj.setType(UnitedConstant.DATACENTER);
		try {
			List<UnitedTreeObj> utreeList = unitedTreeService.queryForTreeList(unitedTreeObj);
			for (UnitedTreeObj unitedTreeObj2 : utreeList) {
				if (UnitedConstant.VMWARE.equals(unitedTreeObj2.getVtype())
						|| UnitedConstant.XEN.equals(unitedTreeObj2.getVtype())) {
					if (treeList == null) {
						treeList = new ArrayList<UnitedTreeObj>();
					}
					treeList.add(unitedTreeObj2);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @Title: queryHostTopN
	 * @Description: 查询主机topN报表
	 * @param
	 * @return void
	 * @throws
	 * @author lipp
	 * @version 1.0
	 * @createtime 2013-12-2 下午4:19:05
	 */
	public void queryHostTopN() {
		UnitedTreeObj unitedTreeObj = new UnitedTreeObj();
		unitedTreeObj.setId(id);
		try {
			unitedTreeObj = unitedTreeService.queryByObj(unitedTreeObj);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Map<String, Object> paramMap = this.initParamMap(unitedTreeObj, "1");
		FusionCharts fusionCharts = new FusionCharts();
		try {
			fusionCharts = hostSystemTopNService.queryEntityTopN(paramMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String dataXml = JacksonUtil.toJson(fusionCharts);
		printToScreen(dataXml);
	}

	/**
	 * @Title: queryVmTopN
	 * @Description: 查询虚拟机topN报表
	 * @param
	 * @return void
	 * @throws
	 * @author lipp
	 * @version 1.0
	 * @createtime 2013-12-2 下午4:18:41
	 */
	public void queryVmTopN() {
		UnitedTreeObj unitedTreeObj = new UnitedTreeObj();
		unitedTreeObj.setId(id);
		try {
			unitedTreeObj = unitedTreeService.queryByObj(unitedTreeObj);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Map<String, Object> paramMap = this.initParamMap(unitedTreeObj, "2");
		FusionCharts fusionCharts = new FusionCharts();
		try {
			fusionCharts = hostSystemTopNService.queryEntityTopN(paramMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String dataXml = JacksonUtil.toJson(fusionCharts);
		printToScreen(dataXml);
	}

	/**
	 * @Title: queryHostTopNList
	 * @Description: 查询主机topN列表
	 * @param
	 * @return void
	 * @throws
	 * @author lipp
	 * @version 1.0
	 * @createtime 2013-12-2 下午4:20:22
	 */
	@SuppressWarnings("rawtypes")
	public void queryHostTopNList() {
		UnitedTreeObj unitedTreeObj = new UnitedTreeObj();
		unitedTreeObj.setId(id);
		try {
			unitedTreeObj = unitedTreeService.queryByObj(unitedTreeObj);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Map<String, Object> paramMap = this.initParamMap(unitedTreeObj, "1");
		List resultList = hostSystemTopNService.queryTopList(paramMap);
		String resultListJson = JacksonUtil.toJson(resultList);
		printToScreen(resultListJson);
	}

	/**
	 * @Title: queryVmTopNList
	 * @Description: 查询虚拟机topN列表
	 * @param
	 * @return void
	 * @throws
	 * @author lipp
	 * @version 1.0
	 * @createtime 2013-12-2 下午4:21:05
	 */
	@SuppressWarnings("rawtypes")
	public void queryVmTopNList() {
		UnitedTreeObj unitedTreeObj = new UnitedTreeObj();
		unitedTreeObj.setId(id);
		try {
			unitedTreeObj = unitedTreeService.queryByObj(unitedTreeObj);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Map<String, Object> paramMap = this.initParamMap(unitedTreeObj, "2");
		List resultList = hostSystemTopNService.queryTopList(paramMap);
		String resultListJson = JacksonUtil.toJson(resultList);
		printToScreen(resultListJson);
	}

	/**
	 * @Title: printToScreen
	 * @Description: 将数据返回到页面
	 * @param
	 * @return void
	 * @throws
	 * @author lipp
	 * @version 1.0
	 * @createtime 2013-12-2 下午5:07:20
	 */
	private void printToScreen(String dataXml) {
		response.setCharacterEncoding("UTF-8");
		try {
			// PrintWriter pw = response.getWriter();
			// pw.print(dataXml);
			PrintWriterOut.printWirter(response, dataXml);
			// pw.flush();
			// pw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @Title: initParamMap
	 * @Description: 构造参数map
	 * @param
	 * @return Map<String,String>
	 * @throws
	 * @author lipp
	 * @version 1.0
	 * @createtime 2013-12-2 下午4:53:12
	 */
	private Map<String, Object> initParamMap(UnitedTreeObj unitedTreeObj, String entityType) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (top_num == 0) {
			top_num = 5;
		}
		map.put("top_num", top_num);
		map.put("entity_type", entityType);
		if (isBlank(type)) {
			type = "cpu";
		}
		map.put("type", type);
		map.put("uuid", unitedTreeObj.getUuid());
		map.put("connect_id", unitedTreeObj.getConnect_id());
		map.put("treeType", unitedTreeObj.getType());
		return map;
	}

	/**
	 * @Title: isBlank
	 * @Description: 判断是否为空值
	 * @param
	 * @return boolean
	 * @throws
	 * @author lipp
	 * @version 1.0
	 * @createtime 2013-12-2 下午4:56:29
	 */
	private boolean isBlank(String value) {
		boolean flag = false;
		if (value == null || "".equals(value) || "null".equals(value)) {
			flag = true;
		}
		return flag;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getTop_num() {
		return top_num;
	}

	public void setTop_num(int top_num) {
		this.top_num = top_num;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<UnitedTreeObj> getTreeList() {
		return treeList;
	}

	public void setTreeList(List<UnitedTreeObj> treeList) {
		this.treeList = treeList;
	}

}
