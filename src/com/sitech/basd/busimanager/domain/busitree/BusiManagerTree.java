package com.sitech.basd.busimanager.domain.busitree;

import com.sitech.basd.common.domain.BasePrivilegeObj;

/**
 * 业务
 * 
 * @author AMCC
 * 
 */
public class BusiManagerTree extends BasePrivilegeObj {
	private String id;
	private String number;// 编号或简称
	private String name;
	private Integer type = -1;
	private String parent_id;
	private String connect_id;
	private String desc;
	private String entity_id;
	private String insertdate;
	private String oem;
	private String user_id;
	private String userName; // 用户名.用以辅助显示

	private String icon;// 生成树的图标路径
	private Boolean isParent = true; // 是否是父节点
	private Boolean highlight = false;// 判断节点是否被选中
	private String tree_uuid;// 树节点的uuid
	private Boolean nocheck = true;
	private Boolean checked = false;// 是否处于选中状态

	/**
	 * 统计摘要信息
	 */
	private String id1;// 一级系统
	private String name1;
	private String id2;// 二级系统
	private String name2;
	private String id3;// 三级 系统
	private String name3;
	private String type3;
	private String id4;
	private String name4;

	/**
	 * 虚拟机相关信息
	 */
	private String state;// 虚拟机的状态信息 1关机 2运行 3挂启，4不存在

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getId1() {
		return id1;
	}

	public void setId1(String id1) {
		this.id1 = id1;
	}

	public String getName1() {
		return name1;
	}

	public void setName1(String name1) {
		this.name1 = name1;
	}

	public String getId2() {
		return id2;
	}

	public void setId2(String id2) {
		this.id2 = id2;
	}

	public String getName2() {
		return name2;
	}

	public void setName2(String name2) {
		this.name2 = name2;
	}

	public String getId3() {
		return id3;
	}

	public void setId3(String id3) {
		this.id3 = id3;
	}

	public String getName3() {
		return name3;
	}

	public void setName3(String name3) {
		this.name3 = name3;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Boolean getIsParent() {
		return isParent;
	}

	public void setIsParent(Boolean isParent) {
		this.isParent = isParent;
	}

	public Boolean getHighlight() {
		return highlight;
	}

	public void setHighlight(Boolean highlight) {
		this.highlight = highlight;
	}

	public String getTree_uuid() {
		return tree_uuid;
	}

	public void setTree_uuid(String tree_uuid) {
		this.tree_uuid = tree_uuid;
	}

	public Boolean getNocheck() {
		return nocheck;
	}

	public void setNocheck(Boolean nocheck) {
		this.nocheck = nocheck;
	}

	public Boolean getChecked() {
		return checked;
	}

	public void setChecked(Boolean checked) {
		this.checked = checked;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getParent_id() {
		return parent_id;
	}

	public void setParent_id(String parent_id) {
		this.parent_id = parent_id;
	}

	public String getConnect_id() {
		return connect_id;
	}

	public void setConnect_id(String connect_id) {
		this.connect_id = connect_id;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getEntity_id() {
		return entity_id;
	}

	public void setEntity_id(String entity_id) {
		this.entity_id = entity_id;
	}

	public String getInsertdate() {
		return insertdate;
	}

	public void setInsertdate(String insertdate) {
		this.insertdate = insertdate;
	}

	public String getOem() {
		return oem;
	}

	public void setOem(String oem) {
		this.oem = oem;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getType3() {
		return type3;
	}

	public void setType3(String type3) {
		this.type3 = type3;
	}

	public String getId4() {
		return id4;
	}

	public void setId4(String id4) {
		this.id4 = id4;
	}

	public String getName4() {
		return name4;
	}

	public void setName4(String name4) {
		this.name4 = name4;
	}

}
