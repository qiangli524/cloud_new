package com.sitech.basd.resource.web.top;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sitech.basd.resource.service.top.TopTargetService;
import com.sitech.basd.sxcloud.rsmu.web.BaseAction;

/**
 * 
 * <p>
 * Title: TopTargetAction
 * </p>
 * <p>
 * Description: topN
 * </p>
 * <p>
 * Copyright: Copyright (c) 2013
 * </p>
 * <p>
 * Company: SI-TECH
 * </p>
 * 
 * @author taoxue
 * @version 1.0
 * @createtime Jul 10, 2013 2:27:14 PM
 * 
 */
@Controller("topTargetAction")
@Scope("prototype")
public class TopTargetAction extends BaseAction {
	@Autowired
	private TopTargetService targetService;
	private String topName;
	private String top_num;
	private String type;
	private String[] categories;
	private Double[] y;
	private List resultList;

	private String uuid;

	private String connect_id;

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getConnect_id() {
		return connect_id;
	}

	public void setConnect_id(String connect_id) {
		this.connect_id = connect_id;
	}

	public List getResultList() {
		return resultList;
	}

	public void setResultList(List resultList) {
		this.resultList = resultList;
	}

	public Double[] getY() {
		return y;
	}

	public void setY(Double[] y) {
		this.y = y;
	}

	public String[] getCategories() {
		return categories;
	}

	public void setCategories(String[] categories) {
		this.categories = categories;
	}

	public String getTop_num() {
		return top_num;
	}

	public void setTop_num(String top_num) {
		this.top_num = top_num;
	}

	public String getTopName() {
		return topName;
	}

	public void setTopName(String topName) {
		this.topName = topName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	/**
	 * 
	 * @Title: topTabs
	 * @Description: 进入topN tabs页面
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Jul 12, 2013 2:51:12 PM
	 */
	public String topTabs() {
		return "top_tabs";
	}

	public String host() {
		return "host";
	}

	public String vm() {
		return "vm";
	}

	/**
	 * 
	 * @Title: HostTop
	 * @Description: 查询主机topN
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Jul 10, 2013 2:49:55 PM
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String hostTop() {
		Map map = new HashMap();
		if (top_num == null) {
			top_num = "5";
		}
		map.put("top_num", Integer.parseInt(top_num));
		map.put("entity_type", "1");
		if (type == null) {
			type = "cpu";
		}
		map.put("type", type);
		if (uuid == null || "".equals(uuid) || "null".equals(uuid)) {
			map.put("uuid", null);
		} else {
			map.put("uuid", uuid);
		}
		if (connect_id == null || "".equals(connect_id) || "null".equals(connect_id)) {
			map.put("connect_id", null);
		} else {
			map.put("connect_id", connect_id);
		}
		y = targetService.queryYData(map);
		categories = targetService.queryXData(map);
		resultList = targetService.queryTopList(map);
		return "host_top";

	}

	/**
	 * 
	 * @Title: VmTop
	 * @Description: 查询虚拟机topN
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Jul 10, 2013 2:50:39 PM
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String vmTop() {
		Map map = new HashMap();
		if (top_num == null) {
			top_num = "5";
		}
		map.put("top_num", Integer.parseInt(top_num));
		map.put("entity_type", "2");
		if (type == null) {
			type = "cpu";
		}
		map.put("type", type);
		if (uuid == null || "".equals(uuid) || "null".equals(uuid)) {
			map.put("uuid", null);
		} else {
			map.put("uuid", uuid);
		}
		if (connect_id == null || "".equals(connect_id) || "null".equals(connect_id)) {
			map.put("connect_id", null);
		} else {
			map.put("connect_id", connect_id);
		}
		y = targetService.queryYData(map);
		categories = targetService.queryXData(map);
		resultList = targetService.queryTopList(map);
		return "vm_top";
	}

	/**
	 * 
	 * @Title: clusterTop
	 * @Description: 查询集群topN
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Jul 10, 2013 2:51:18 PM
	 */
	public String clusterTop() {
		return null;
	}

	/**
	 * 
	 * @Title: validate
	 * @Description:struts2验证，传入的hyId不能为空
	 * @author duangh
	 * @date Jul 10, 2013 2:55:45 PM
	 */
	// public void validate() {
	// if (false) {
	// addFieldError("hyId", "host's uuid is requried");
	// }
	// }
	public String alarm() {
		return "alarm";
	}

	/**
	 * 告警topN
	 * 
	 * @author lipengpeng
	 * @return
	 */
	public String queryTopAlarm() {
		Map<String, Integer> map = new HashMap<String, Integer>();
		if (top_num == null) {
			top_num = "5";
		}
		map.put("top_num", Integer.parseInt(top_num));
		y = targetService.queryAlarmYData(map);
		categories = targetService.queryAlarmXData(map);
		return "topalarm";
	}
}
