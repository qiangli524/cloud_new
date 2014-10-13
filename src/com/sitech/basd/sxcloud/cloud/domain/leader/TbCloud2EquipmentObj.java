package com.sitech.basd.sxcloud.cloud.domain.leader;

public class TbCloud2EquipmentObj {

	private int type = 0;

	private int count = 0;
	
	private String roomId;

	public String getRoomId() {
		return roomId;
	}

	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

}
