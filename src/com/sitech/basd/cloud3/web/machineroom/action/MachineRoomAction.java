package com.sitech.basd.cloud3.web.machineroom.action;

import java.util.List;

import com.sitech.basd.cloud3.domain.machineroom.MachineRoomObj;
import com.sitech.basd.cloud3.service.machineroom.RoomService;
import com.sitech.basd.cloud3.web.machineroom.form.MachineRoomForm;
import com.sitech.basd.sxcloud.rsmu.web.BaseAction;
import com.sitech.basd.sxcloud.util.Struts2Utils;
import com.sitech.basd.sxcloud.util.UUIDGenerator;

@SuppressWarnings("serial")
public class MachineRoomAction  extends BaseAction {
	
	private RoomService roomService;
	
	private MachineRoomForm theForm;
	
	/**
	 * 
	 * @Title: 查询所有机房信息
	 * @Copyright: Copyright (c) 2013-03-14
	 * @Company: si-tech
	 * @author xmq
	 * @version 1.0
	 */
	public String queryRoomList(){
		if(theForm==null){
			theForm =new MachineRoomForm();
		}
		MachineRoomObj obj = new MachineRoomObj();
		if(theForm.getROOM_NAME()!=null&&!"".equals(theForm.getROOM_NAME())){
			obj.setROOM_NAME(theForm.getROOM_NAME().trim());
		}
		if(theForm.getROOM_CODE()!=null&&!"".equals(theForm.getROOM_CODE())){
			obj.setROOM_CODE(theForm.getROOM_CODE().trim());
		}
		if(theForm.getROOM_TYPE()!=null&&!"".equals(theForm.getROOM_TYPE())){
			obj.setROOM_TYPE(theForm.getROOM_TYPE().trim());
		}
		obj.setPagination(this.getPaginater().initPagination(
				Struts2Utils.getRequest()));// 分页
		List resultList=roomService.queryRoomList(obj);
		theForm.setRoomList(resultList);
		return "success";
	}
	/**
	 * 
	 * @Title: 删除所选机房信息
	 * @Copyright: Copyright (c) 2013-03-14
	 * @Company: si-tech
	 * @author xmq
	 * @version 1.0
	 */
	public String deleteRoom(){
		if(theForm==null){
			theForm =new MachineRoomForm();
		}
		MachineRoomObj obj = new MachineRoomObj();
		int result = 0;
		obj.setROOM_ID(theForm.getROOM_ID());
		result=roomService.deleteRoomObj(obj);
		return "del";
	}
	/**
	 * 
	 * @Title: 进入修改机房页面
	 * @Copyright: Copyright (c) 2013-03-14
	 * @Company: si-tech
	 * @author xmq
	 * @version 1.0
	 */
	public String updateRoom(){
		if(theForm==null){
			theForm =new MachineRoomForm();
		}
		MachineRoomObj mobj = new MachineRoomObj();
		mobj.setROOM_ID(theForm.getROOM_ID());
		MachineRoomObj obj =roomService.queryRoomOne(mobj);
		theForm.setROOM_ID(obj.getROOM_ID());
		theForm.setROOM_NAME(obj.getROOM_NAME());
		theForm.setROOM_CODE(obj.getROOM_CODE());
		theForm.setROOM_CITY(obj.getROOM_CITY());
		theForm.setROOM_ADDRESS(obj.getROOM_ADDRESS());
		theForm.setLINK_MAN(obj.getLINK_MAN());
		theForm.setPHONE(obj.getPHONE());
		theForm.setROOM_TYPE(obj.getROOM_TYPE());
		theForm.setROOM_SIZE(obj.getROOM_SIZE());
		theForm.setFlag(1);
		return "modify";
	}
	public String insertRoom(){
		
		return "add";
	}
	/**
	 * 
	 * @Title: 保存与修改机房页面
	 * @Copyright: Copyright (c) 2013-03-14
	 * @Company: si-tech
	 * @author xmq
	 * @version 1.0
	 */
	public String saveRoom(){
		if(theForm==null){
			theForm =new MachineRoomForm();
		}
		int flag = theForm.getFlag();
		MachineRoomObj obj = new MachineRoomObj();
		UUIDGenerator uuid =new UUIDGenerator();
		if(flag==1){
			obj.setROOM_ID(theForm.getROOM_ID());
		}
		else{
			obj.setROOM_ID(uuid.getUUID());
		}
		obj.setROOM_NAME(theForm.getROOM_NAME());
		obj.setROOM_CODE(theForm.getROOM_CODE());
		obj.setROOM_ADDRESS(theForm.getROOM_ADDRESS());
		obj.setROOM_CITY(theForm.getROOM_CITY());
		obj.setLINK_MAN(theForm.getLINK_MAN());
		obj.setPHONE(theForm.getPHONE());
		obj.setROOM_TYPE(theForm.getROOM_TYPE());
		obj.setROOM_SIZE(theForm.getROOM_SIZE());
		if(flag==1){
			roomService.updateRoomObj(obj);
		}
		else{
			roomService.insertRoomObj(obj);
		}
		return "save";
	}
	
	public RoomService getRoomService() {
		return roomService;
	}




	public void setRoomService(RoomService roomService) {
		this.roomService = roomService;
	}


	public MachineRoomForm getTheForm() {
		return theForm;
	}


	public void setTheForm(MachineRoomForm theForm) {
		this.theForm = theForm;
	}







	

}
