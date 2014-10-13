package com.sitech.ssd.sc.os.domain;

import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;

/**
 * @ClassName HostModel
 * @Desc
 * @Author JamTau
 * @date May 21, 2014 6:46:11 PM
 */
public class HostModel extends BaseObj {

	private String id;
	private String host_name;
	private String mge_console_ip;
	private String mge_console_mac;
	private String mge_console_username;
	private String mge_console_pass;
	private String install_type;
	private String install_state;
	private String insert_date;
	private String install_date;
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
	private String eq_id;

	private String eq_mac1;
	private String eq_mac2;
	private String eq_mac3;
	private String eq_mac4;

	private String os_versions;
	private String os_digits;
	private String raid_information;
	private String bond_information;
	private String fibercard;
	private String shared_storage;
	private String stay_machroom;
	private String blade_groove;
	
	private String os_template;
	private String swap_type;

	public HostModel() {
	}

	public HostModel(String id) {
		this.id = id;
	}

	public String getMge_console_mac() {
		return mge_console_mac;
	}

	public void setMge_console_mac(String mge_console_mac) {
		this.mge_console_mac = mge_console_mac;
	}

	public String getEq_mac1() {
		return eq_mac1;
	}

	public void setEq_mac1(String eq_mac1) {
		this.eq_mac1 = eq_mac1;
	}

	public String getEq_mac2() {
		return eq_mac2;
	}

	public void setEq_mac2(String eq_mac2) {
		this.eq_mac2 = eq_mac2;
	}

	public String getEq_mac3() {
		return eq_mac3;
	}

	public void setEq_mac3(String eq_mac3) {
		this.eq_mac3 = eq_mac3;
	}

	public String getEq_mac4() {
		return eq_mac4;
	}

	public void setEq_mac4(String eq_mac4) {
		this.eq_mac4 = eq_mac4;
	}

	public String getEq_id() {
		return eq_id;
	}

	public void setEq_id(String eq_id) {
		this.eq_id = eq_id;
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

	public String getInstall_state() {
		return install_state;
	}

	public void setInstall_state(String install_state) {
		this.install_state = install_state;
	}

	public String getInsert_date() {
		return insert_date;
	}

	public void setInsert_date(String insert_date) {
		this.insert_date = insert_date;
	}

	public String getInstall_date() {
		return install_date;
	}

	public void setInstall_date(String install_date) {
		this.install_date = install_date;
	}

	public String getInstall_desc() {
		return install_desc;
	}

	public void setInstall_desc(String install_desc) {
		this.install_desc = install_desc;
	}

	public String getHost_name() {
		return host_name;
	}

	public void setHost_name(String host_name) {
		this.host_name = host_name;
	}

	public String getMge_console_ip() {
		return mge_console_ip;
	}

	public void setMge_console_ip(String mge_console_ip) {
		this.mge_console_ip = mge_console_ip;
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getInstall_type() {
		return install_type;
	}

	public void setInstall_type(String install_type) {
		this.install_type = install_type;
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

	public void setEq_type(String eq_type) {
		this.eq_type = eq_type;
	}

	public String getEq_type() {
		return eq_type;
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

	public String getOs_template() {
		return os_template;
	}

	public void setOs_template(String os_template) {
		this.os_template = os_template;
	}

	public String getSwap_type() {
		return swap_type;
	}

	public void setSwap_type(String swap_type) {
		this.swap_type = swap_type;
	}

}
