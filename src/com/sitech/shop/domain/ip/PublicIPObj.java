package com.sitech.shop.domain.ip;

import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;

/**
 * @Title PublicIPObj
 * @Description 公网ip实体类
 * @author lipp
 * @date 2014-4-25 上午10:26:41
 * @version 1.0
 */
public class PublicIPObj extends BaseObj {

	// 表字段
	private String id;// 主键
	private String user_id;// 所属用户标识
	private String ipaddress;// ip地址
	private String icp_id;// icp备案标识
	private Integer status;// 绑定状态
	private String entity_id;// 绑定的实体机标识
	private String description;// 描述
	private String intranet_ip;// 映射到的内网ip地址
	private String region_id;// 区域标识
	private String create_time;// 创建时间
	private String end_time;// 到期时间
	private String addr_group;// 端口组
	private String acl_num;// acl策略号
	private String connect_id;// 链接标识
	private String IP_ID;
	private String IP_TYPE;
	private String ISUSED;
	private String VH_DESC;
	private String VH_NAME;
	private String type;// 服务状态（已过期，即将过期等）
	private String area_id;// 地域标识
	private String payment_type;// 计费方式，1.按量（小时）2.包年包月
	private String obtain_way;// 获取方式 0.是赠送 1.是购买

	public void setObtain_way(String obtain_way) {
		this.obtain_way = obtain_way;
	}

	public String getObtain_way() {
		return obtain_way;
	}

	public String getPayment_type() {
		return payment_type;
	}

	public void setPayment_type(String payment_type) {
		this.payment_type = payment_type;
	}

	public String getEnd_time() {
		return end_time;
	}

	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}

	public String getArea_id() {
		return area_id;
	}

	public void setArea_id(String area_id) {
		this.area_id = area_id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	// 自定义属性
	private String entity_name;// 实体机名称
	private int ipNums;// ip个数

	public String getIP_ID() {
		return IP_ID;
	}

	public void setIP_ID(String iP_ID) {
		IP_ID = iP_ID;
	}

	public String getIP_TYPE() {
		return IP_TYPE;
	}

	public void setIP_TYPE(String iP_TYPE) {
		IP_TYPE = iP_TYPE;
	}

	public String getISUSED() {
		return ISUSED;
	}

	public void setISUSED(String iSUSED) {
		ISUSED = iSUSED;
	}

	public String getVH_DESC() {
		return VH_DESC;
	}

	public void setVH_DESC(String vH_DESC) {
		VH_DESC = vH_DESC;
	}

	public String getVH_NAME() {
		return VH_NAME;
	}

	public void setVH_NAME(String vH_NAME) {
		VH_NAME = vH_NAME;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getIpaddress() {
		return ipaddress;
	}

	public void setIpaddress(String ipaddress) {
		this.ipaddress = ipaddress;
	}

	public String getIcp_id() {
		return icp_id;
	}

	public void setIcp_id(String icp_id) {
		this.icp_id = icp_id;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getEntity_id() {
		return entity_id;
	}

	public void setEntity_id(String entity_id) {
		this.entity_id = entity_id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCreate_time() {
		return create_time;
	}

	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}

	public String getIntranet_ip() {
		return intranet_ip;
	}

	public void setIntranet_ip(String intranet_ip) {
		this.intranet_ip = intranet_ip;
	}

	public String getEntity_name() {
		return entity_name;
	}

	public void setEntity_name(String entity_name) {
		this.entity_name = entity_name;
	}

	public String getRegion_id() {
		return region_id;
	}

	public void setRegion_id(String region_id) {
		this.region_id = region_id;
	}

	public int getIpNums() {
		return ipNums;
	}

	public void setIpNums(int ipNums) {
		this.ipNums = ipNums;
	}

	public String getAddr_group() {
		return addr_group;
	}

	public void setAddr_group(String addr_group) {
		this.addr_group = addr_group;
	}

	public String getAcl_num() {
		return acl_num;
	}

	public void setAcl_num(String acl_num) {
		this.acl_num = acl_num;
	}

	public String getConnect_id() {
		return connect_id;
	}

	public void setConnect_id(String connect_id) {
		this.connect_id = connect_id;
	}

}
