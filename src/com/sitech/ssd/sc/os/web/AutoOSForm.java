package com.sitech.ssd.sc.os.web;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.sitech.ssd.sc.os.domain.HostAdapter;
import com.sitech.ssd.sc.os.domain.OsFileSystemModel;
import com.sitech.ssd.sc.os.domain.OsGroupModel;
import com.sitech.ssd.sc.os.domain.OsGroupUserModel;
import com.sitech.ssd.sc.os.domain.OsPartModel;
import com.sitech.ssd.sc.os.domain.OsSoftModel;
import com.sitech.ssd.sc.os.domain.OsUserModel;
import com.sitech.ssd.sc.os.domain.OsVolGroupModel;
import com.sitech.ssd.sc.ostempl.domain.OsTemplate;

public class AutoOSForm implements Serializable, Cloneable {

	private static final long serialVersionUID = 1L;

	private List resultList;
	private List sysList;
	private String mge_console_ip;

	private String id;
	private String host_name;
	private String mge_console_username;
	private String mge_console_pass;
	private String ip_address;
	private String mac;
	private String subnet;
	private String gateway;
	private String net_static;
	private String net_interface;
	private String install_type;
	private String install_state;
	private String start_date;
	private String end_date;
	private String install_desc;
	private String cpu_cl;
	private String memory;
	private String storage_num;
	private int nic_num;
	private String eq_type;
	private String serial_num;
	private String host_type_num;
	private String host_physical_position;
	private String store;
	private String os_versions;
	private String os_digits;
	private String raid_information;
	private String bond_information;
	private String fibercard;
	private String shared_storage;
	private String stay_machroom;
	private String blade_groove;
	private String os_template;
	private String eq_id;
	private String swap_type;
	
	private String ownResPoolid;
	private String ownResPool;
	private String bussId;
	private String bussName;
	
	private String template_id;//模版编号
	private List<OsTemplate> templList;
	private List<HostAdapter> adptList = null;
	private List<OsFileSystemModel> fsList = null;
	private List<OsGroupModel> groupList = null;
	private List<OsUserModel> userList = null;
	private List<OsGroupUserModel> guList = null;
	private List<OsSoftModel> softList = null;
	private List<OsPartModel> partList = null;
	private List<OsVolGroupModel> vgList = null;

	public String getOs_template() {
		return os_template;
	}

	public void setOs_template(String os_template) {
		this.os_template = os_template;
	}

	public String getEq_id() {
		return eq_id;
	}

	public void setEq_id(String eq_id) {
		this.eq_id = eq_id;
	}


	public String getStay_machroom() {
		return stay_machroom;
	}

	public void setStay_machroom(String stayMachroom) {
		stay_machroom = stayMachroom;
	}

	public String getBlade_groove() {
		return blade_groove;
	}

	public void setBlade_groove(String bladeGroove) {
		blade_groove = bladeGroove;
	}

	public String getOs_versions() {
		return os_versions;
	}

	public void setOs_versions(String osVersions) {
		os_versions = osVersions;
	}

	public String getOs_digits() {
		return os_digits;
	}

	public void setOs_digits(String osDigits) {
		os_digits = osDigits;
	}

	public String getRaid_information() {
		return raid_information;
	}

	public void setRaid_information(String raidInformation) {
		raid_information = raidInformation;
	}

	public String getBond_information() {
		return bond_information;
	}

	public void setBond_information(String bondInformation) {
		bond_information = bondInformation;
	}

	public String getFibercard() {
		return fibercard;
	}

	public void setFibercard(String fibercard) {
		this.fibercard = fibercard;
	}

	public String getShared_storage() {
		return shared_storage;
	}

	public void setShared_storage(String sharedStorage) {
		shared_storage = sharedStorage;
	}

	public String getSerial_num() {
		return serial_num;
	}

	public void setSerial_num(String serial_num) {
		this.serial_num = serial_num;
	}

	public String getHost_type_num() {
		return host_type_num;
	}

	public void setHost_type_num(String host_type_num) {
		this.host_type_num = host_type_num;
	}

	public String getHost_physical_position() {
		return host_physical_position;
	}

	public void setHost_physical_position(String host_physical_position) {
		this.host_physical_position = host_physical_position;
	}

	public String getStore() {
		return store;
	}

	public void setStore(String store) {
		this.store = store;
	}

	public List getResultList() {
		return resultList;
	}

	public void setResultList(List resultList) {
		this.resultList = resultList;
	}

	public List getSysList() {
		return sysList;
	}

	public void setSysList(List sysList) {
		this.sysList = sysList;
	}

	public String getMge_console_ip() {
		return mge_console_ip;
	}

	public void setMge_console_ip(String mge_console_ip) {
		this.mge_console_ip = mge_console_ip;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMge_console_username() {
		return mge_console_username;
	}

	public void setMge_console_username(String mge_console_username) {
		this.mge_console_username = mge_console_username;
	}

	public String getMge_console_pass() {
		return mge_console_pass;
	}

	public void setMge_console_pass(String mge_console_pass) {
		this.mge_console_pass = mge_console_pass;
	}

	public String getInstall_type() {
		return install_type;
	}

	public void setInstall_type(String install_type) {
		this.install_type = install_type;
	}

	public String getHost_name() {
		return host_name;
	}

	public void setHost_name(String host_name) {
		this.host_name = host_name;
	}

	public String getIp_address() {
		return ip_address;
	}

	public void setIp_address(String ip_address) {
		this.ip_address = ip_address;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public String getSubnet() {
		return subnet;
	}

	public void setSubnet(String subnet) {
		this.subnet = subnet;
	}

	public String getGateway() {
		return gateway;
	}

	public void setGateway(String gateway) {
		this.gateway = gateway;
	}

	public String getNet_static() {
		return net_static;
	}

	public void setNet_static(String net_static) {
		this.net_static = net_static;
	}

	public String getNet_interface() {
		return net_interface;
	}

	public void setNet_interface(String net_interface) {
		this.net_interface = net_interface;
	}

	public void setInstall_state(String install_state) {
		this.install_state = install_state;
	}

	public String getInstall_state() {
		return install_state;
	}

	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}

	public String getStart_date() {
		return start_date;
	}

	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}

	public String getEnd_date() {
		return end_date;
	}

	public String getCpu_cl() {
		return cpu_cl;
	}

	public void setCpu_cl(String cpu_cl) {
		this.cpu_cl = cpu_cl;
	}

	public String getMemory() {
		return memory;
	}

	public void setMemory(String memory) {
		this.memory = memory;
	}

	public String getStorage_num() {
		return storage_num;
	}

	public void setStorage_num(String storage_num) {
		this.storage_num = storage_num;
	}

	public int getNic_num() {
		return nic_num;
	}

	public void setNic_num(int nic_num) {
		this.nic_num = nic_num;
	}

	public void setInstall_desc(String install_desc) {
		this.install_desc = install_desc;
	}

	public String getInstall_desc() {
		return install_desc;
	}

	public void setEq_type(String eq_type) {
		this.eq_type = eq_type;
	}

	public String getEq_type() {
		return eq_type;
	}

	public String getOwnResPoolid() {
		return ownResPoolid;
	}

	public void setOwnResPoolid(String ownResPoolid) {
		this.ownResPoolid = ownResPoolid;
	}

	public String getOwnResPool() {
		return ownResPool;
	}

	public void setOwnResPool(String ownResPool) {
		this.ownResPool = ownResPool;
	}

	public String getBussId() {
		return bussId;
	}

	public void setBussId(String bussId) {
		this.bussId = bussId;
	}

	public String getBussName() {
		return bussName;
	}

	public void setBussName(String bussName) {
		this.bussName = bussName;
	}

	public List<OsTemplate> getTemplList() {
		return templList;
	}

	public void setTemplList(List<OsTemplate> templList) {
		this.templList = templList;
	}

	public List<HostAdapter> getAdptList() {
		return adptList;
	}

	public void setAdptList(List<HostAdapter> adptList) {
		this.adptList = adptList;
	}

	public List<OsFileSystemModel> getFsList() {
		return fsList;
	}

	public void setFsList(List<OsFileSystemModel> fsList) {
		this.fsList = fsList;
	}

	public List<OsGroupModel> getGroupList() {
		return groupList;
	}

	public void setGroupList(List<OsGroupModel> groupList) {
		this.groupList = groupList;
	}

	public List<OsUserModel> getUserList() {
		return userList;
	}

	public void setUserList(List<OsUserModel> userList) {
		this.userList = userList;
	}

	public List<OsGroupUserModel> getGuList() {
		return guList;
	}

	public void setGuList(List<OsGroupUserModel> guList) {
		this.guList = guList;
	}

	public String getSwap_type() {
		return swap_type;
	}

	public void setSwap_type(String swap_type) {
		this.swap_type = swap_type;
	}

	public String getTemplate_id() {
		return template_id;
	}

	public void setTemplate_id(String template_id) {
		this.template_id = template_id;
	}

	public List<OsSoftModel> getSoftList() {
		return softList;
	}

	public void setSoftList(List<OsSoftModel> softList) {
		this.softList = softList;
	}

	public List<OsPartModel> getPartList() {
		return partList;
	}

	public void setPartList(List<OsPartModel> partList) {
		this.partList = partList;
	}

	public List<OsVolGroupModel> getVgList() {
		return vgList;
	}

	public void setVgList(List<OsVolGroupModel> vgList) {
		this.vgList = vgList;
	}
}
