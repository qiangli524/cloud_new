package com.sitech.basd.cloud3.web.machineroom.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.sitech.basd.cloud3.domain.machineroom.MachineRoomObj;
import com.sitech.basd.cloud3.domain.machineroom.RoomAreaObj;
import com.sitech.basd.cloud3.service.machineroom.AreaService;
import com.sitech.basd.cloud3.service.machineroom.RoomService;
import com.sitech.basd.cloud3.web.machineroom.form.RoomAreaForm;
import com.sitech.basd.sxcloud.rsmu.web.BaseAction;
import com.sitech.basd.sxcloud.util.Struts2Utils;
import com.sitech.basd.sxcloud.util.UUIDGenerator;

public class RoomAreaAction extends BaseAction {
	
	private AreaService areaService;
	
	private RoomAreaForm theForm;
	
	private RoomService  roomService;
	
	private String ROOM_ID;
	
	/**
	 * 
	 * @Title: 查询所有机房区域列表
	 * @Copyright: Copyright (c) 2013-03-14
	 * @Company: si-tech
	 * @author xmq
	 * @version 1.0
	 */
	public String queryAreaList(){
		if(theForm==null){
			theForm = new RoomAreaForm();
		}
		RoomAreaObj obj = new RoomAreaObj();
		if(theForm.getAREA_NAME()!=null&&!"".equals(theForm.getAREA_NAME()))
		{
			obj.setAREA_NAME(theForm.getAREA_NAME().trim());
		}
		if(ROOM_ID!=null&&!"".equals(ROOM_ID)){
			obj.setROOM_ID(ROOM_ID.trim());
		}
		obj.setPagination(this.getPaginater().initPagination(
				Struts2Utils.getRequest()));// 分页
		List areaList = areaService.queryAreaList(obj);
		theForm.setAREA_LIST(areaList);
		return "success";
	}
	/**
	 * 
	 * @Title: 删除机房区域列表
	 * @Copyright: Copyright (c) 2013-03-14
	 * @Company: si-tech
	 * @author xmq
	 * @version 1.0
	 */
	public String deleteArea(){
		if(theForm==null){
			theForm = new RoomAreaForm();
		}
		int result = 0;
		RoomAreaObj obj = new RoomAreaObj();
		obj.setAREA_ID(theForm.getAREA_ID());
		result = areaService.deleteAreaObj(obj);
		return "del";
	}
	/**
	 * 
	 * @Title: 进入修改机房区域页面
	 * @Copyright: Copyright (c) 2013-03-14
	 * @Company: si-tech
	 * @author xmq
	 * @version 1.0
	 */
	public String updateArea(){
		if(theForm==null){
			theForm = new RoomAreaForm();
		}
		MachineRoomObj mobj = new MachineRoomObj();
		RoomAreaObj obj = new RoomAreaObj();
		obj.setAREA_ID(theForm.getAREA_ID());
		
		RoomAreaObj rObj = areaService.queryOneArea(obj);
		
		theForm.setAREA_ID(rObj.getAREA_ID());
		theForm.setAREA_NAME(rObj.getAREA_NAME());
		theForm.setAREA_ROOM(rObj.getAREA_ROOM());
		theForm.setLINK_MAN(rObj.getLINK_MAN());
		theForm.setAREA_CODE(rObj.getAREA_CODE());
		theForm.setPHONE(rObj.getPHONE());
		theForm.setAREA_SIZE(rObj.getAREA_SIZE());
		theForm.setFlag(1);
		theForm.setROOM_NAME(rObj.getROOM_NAME());
		List roomList = roomService.queryRoomList(mobj);
		
		theForm.setRoomList(roomList);
		return "modify";
	}
	/**
	 * 
	 * @Title: 保存修改与增加机房区域
	 * @Copyright: Copyright (c) 2013-03-14
	 * @Company: si-tech
	 * @author xmq
	 * @version 1.0
	 */
	public String saveAreaObj(){
		if(theForm==null){
			theForm = new RoomAreaForm();
		}
//		HttpServletRequest request = Struts2Utils.getRequest();
//		String roomid = request.getParameter("ROOM_ID") ;
		int flag = theForm.getFlag();
		RoomAreaObj obj = new RoomAreaObj();
		UUIDGenerator uuid =new UUIDGenerator();
		if(flag==1){
			obj.setAREA_ID(theForm.getAREA_ID());
		}
		else{
			obj.setAREA_ID(uuid.getUUID());
		}
		obj.setAREA_NAME(theForm.getAREA_NAME());
		obj.setAREA_ROOM(theForm.getAREA_ROOM());
		obj.setAREA_CODE(theForm.getAREA_CODE());
		obj.setLINK_MAN(theForm.getLINK_MAN());
		obj.setAREA_SIZE(theForm.getAREA_SIZE());
		obj.setPHONE(theForm.getPHONE());
		obj.setROOM_ID(theForm.getROOM_ID());
		if(flag==1){
			areaService.updateAreaObj(obj);
		}
		else{
			areaService.insertAreaObj(obj);
		}
		return "save";
	}
	/**
	 * 
	 * @Title: 进入增加机房区域页面
	 * @Copyright: Copyright (c) 2013-03-14
	 * @Company: si-tech
	 * @author xmq
	 * @version 1.0
	 */
	public String modifyArea(){
		MachineRoomObj mobj = new MachineRoomObj();
		List roomList = roomService.queryRoomList(mobj);
		
		theForm.setRoomList(roomList);
		return "add";
	}
	

	public AreaService getAreaService() {
		return areaService;
	}



	public void setAreaService(AreaService areaService) {
		this.areaService = areaService;
	}



	public RoomAreaForm getTheForm() {
		return theForm;
	}

	public void setTheForm(RoomAreaForm theForm) {
		this.theForm = theForm;
	}
	public RoomService getRoomService() {
		return roomService;
	}
	public void setRoomService(RoomService roomService) {
		this.roomService = roomService;
	}
	public String getROOM_ID() {
		return ROOM_ID;
	}
	public void setROOM_ID(String room_id) {
		ROOM_ID = room_id;
	}
	
	

}
