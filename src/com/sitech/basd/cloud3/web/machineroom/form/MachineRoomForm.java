package com.sitech.basd.cloud3.web.machineroom.form;

import java.util.List;

public class MachineRoomForm {
	
private String ROOM_ID ;//机房id
	
	private String ROOM_NAME ;//机房名称
	
	private String ROOM_CODE ;//机房编码
	
	private String ROOM_CITY ;//机房所在城市
	
	private String ROOM_ADDRESS ;//机房详细地址
	
	private String LINK_MAN ;//联系人
	
	private String PHONE ;//联系人电话
	
	private String ROOM_TYPE ;//机房性质
	
	private String ROOM_SIZE ;//机房面积
	
	private String INS_DATE;//创建时间
	
	private int flag;
	
	private int shu;
	
	

	public int getShu() {
		return shu;
	}

	public void setShu(int shu) {
		this.shu = shu;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public String getINS_DATE() {
		return INS_DATE;
	}

	public void setINS_DATE(String ins_date) {
		INS_DATE = ins_date;
	}
	
	private List RoomList;
	
	

	public List getRoomList() {
		return RoomList;
	}

	public void setRoomList(List roomList) {
		RoomList = roomList;
	}

	public String getROOM_ID() {
		return ROOM_ID;
	}

	public void setROOM_ID(String room_id) {
		ROOM_ID = room_id;
	}

	public String getROOM_NAME() {
		return ROOM_NAME;
	}

	public void setROOM_NAME(String room_name) {
		ROOM_NAME = room_name;
	}

	public String getROOM_CODE() {
		return ROOM_CODE;
	}

	public void setROOM_CODE(String room_code) {
		ROOM_CODE = room_code;
	}

	public String getROOM_CITY() {
		return ROOM_CITY;
	}

	public void setROOM_CITY(String room_city) {
		ROOM_CITY = room_city;
	}

	public String getROOM_ADDRESS() {
		return ROOM_ADDRESS;
	}

	public void setROOM_ADDRESS(String room_address) {
		ROOM_ADDRESS = room_address;
	}

	public String getLINK_MAN() {
		return LINK_MAN;
	}

	public void setLINK_MAN(String link_man) {
		LINK_MAN = link_man;
	}

	public String getPHONE() {
		return PHONE;
	}

	public void setPHONE(String phone) {
		PHONE = phone;
	}

	public String getROOM_TYPE() {
		return ROOM_TYPE;
	}

	public void setROOM_TYPE(String room_type) {
		ROOM_TYPE = room_type;
	}

	public String getROOM_SIZE() {
		return ROOM_SIZE;
	}

	public void setROOM_SIZE(String room_size) {
		ROOM_SIZE = room_size;
	}
	
	
	
	
	
	


}
