package com.sitech.basd.cloud3.web.machineroom.form;

import java.util.List;

import com.sitech.basd.cloud3.domain.machineroom.MachineRoomObj;

public class RoomAreaForm {

	
private String AREA_ID; //机房区域id
	
	private String AREA_NAME; //机房区域名称
	
	private String AREA_CODE;  //区或编码
	
	private String AREA_ROOM; //机房所在城市
	
	private String LINK_MAN; //联系人
	
	private String PHONE; //联系人电话
	
	private String AREA_SIZE; //机房区域面积
	
	private List AREA_LIST;
	
	private String INS_DATE;
	
	private int flag;
	
	private String ROOM_ID;
	
	private String ROOM_NAME;
	
	private List roomList = null;
	
	private MachineRoomObj mob;
	
	public MachineRoomObj getMob() {
		return mob;
	}

	public void setMob(MachineRoomObj mob) {
		this.mob = mob;
	}

	public List getRoomList() {
		return roomList;
	}

	public void setRoomList(List roomList) {
		this.roomList = roomList;
	}

	public String getROOM_NAME() {
		return ROOM_NAME;
	}

	public void setROOM_NAME(String room_name) {
		ROOM_NAME = room_name;
	}

	public String getROOM_ID() {
		return ROOM_ID;
	}

	public void setROOM_ID(String room_id) {
		ROOM_ID = room_id;
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
	
	

	public List getAREA_LIST() {
		return AREA_LIST;
	}

	public void setAREA_LIST(List area_list) {
		AREA_LIST = area_list;
	}

	public String getAREA_ID() {
		return AREA_ID;
	}

	public void setAREA_ID(String area_id) {
		AREA_ID = area_id;
	}

	public String getAREA_NAME() {
		return AREA_NAME;
	}

	public void setAREA_NAME(String area_name) {
		AREA_NAME = area_name;
	}

	public String getAREA_CODE() {
		return AREA_CODE;
	}

	public void setAREA_CODE(String area_code) {
		AREA_CODE = area_code;
	}

	public String getAREA_ROOM() {
		return AREA_ROOM;
	}

	public void setAREA_ROOM(String area_room) {
		AREA_ROOM = area_room;
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

	public String getAREA_SIZE() {
		return AREA_SIZE;
	}

	public void setAREA_SIZE(String area_size) {
		AREA_SIZE = area_size;
	}
	
	
	

}
