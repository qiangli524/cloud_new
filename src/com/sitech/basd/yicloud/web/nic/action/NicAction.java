package com.sitech.basd.yicloud.web.nic.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.sitech.basd.sxcloud.cloud.domain.vmhost.VMHostObj;
import com.sitech.basd.sxcloud.cloud.service.vmhost.VMHostService;
import com.sitech.basd.sxcloud.rsmu.web.CRUDBaseAction;
import com.sitech.basd.sxcloud.util.Struts2Utils;
import com.sitech.basd.yicloud.domain.nic.NicObj;
import com.sitech.basd.yicloud.domain.nic.NicRelationObj;
import com.sitech.basd.yicloud.service.nic.NicRelationService;
import com.sitech.basd.yicloud.service.nic.NicService;
import com.sitech.basd.yicloud.web.nic.form.NicForm;

public class NicAction extends CRUDBaseAction {
	private NicForm theForm;
	private NicRelationService nicRelationService;
	private NicService nicService;
	private VMHostService vmHostService;
	
	public void setVmHostService(VMHostService vmHostService) {
		this.vmHostService = vmHostService;
	}

	public NicForm getTheForm() {
		return theForm;
	}

	public void setTheForm(NicForm theForm) {
		this.theForm = theForm;
	}

	public void setNicService(NicService nicService) {
		this.nicService = nicService;
	}

	public void setNicRelationService(NicRelationService nicRelationService) {
		this.nicRelationService = nicRelationService;
	}

	/**
	 * 
	 * @Title: listNic
	 * @Description: 展示虚拟网卡
	 * @param
	 * @return List
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime Apr 19, 2013 11:44:06 AM
	 */
	public String listNic(){
		if(theForm==null){
			theForm = new NicForm();
		}
		HttpServletRequest request = Struts2Utils.getRequest();
		String vssuuid = request.getParameter("vssUuid");
		String vssName = request.getParameter("vssName");
		String vssType = request.getParameter("vssType");
		List<NicObj> nicLst = null;
		List<NicObj> reList = new ArrayList<NicObj>(); 
		if(vssType.equals("0")){
			//标准交换机下网卡
			NicRelationObj nicRaObj = new NicRelationObj();
			nicRaObj.setType("NIC_VSS");
			nicRaObj.setToUuid(vssuuid);
			List nicRaLst = nicRelationService.queryForListByObj(nicRaObj);
			if(nicRaLst!=null && nicRaLst.size()>0){
				for (Object o : nicRaLst) {
					NicRelationObj nr = (NicRelationObj)o;
					NicObj nicObj = new NicObj();
					nicObj.setUuid(nr.getFromUuid());
					nicLst = nicService.queryForListByObj(nicObj);
					if(nicLst!=null && nicLst.size()>0){
						for (Object obj : nicLst) {
							NicObj nic =(NicObj)obj;
							if(nic.getStatus().equals("CONNECTED")){
								nic.setStatus("已连接");
								if(nic.getLinkduplex()!=null && nic.getLinkduplex().equals("TRUE")){
									nic.setLinkduplex("全");
								}else{
									nic.setLinkduplex("半");
								}
							}else{
								nic.setStatus("已连接");
								nic.setLinkduplex("-");
								nic.setLinkspeed(0);
							}
							String mac = nic.getUuid().split("_")[1];
							nic.setMac(mac);
							reList.add(nic);
						}
					}
				}
			}
		}else{
			//分布式交换机下网卡
			NicRelationObj nicRaObj = new NicRelationObj();
			nicRaObj.setType("DVSS_NIC");
			nicRaObj.setFromUuid(vssuuid);
			List nicRaLst = nicRelationService.queryForListByObj(nicRaObj);
			if(nicRaLst!=null && nicRaLst.size()>0){
				for (Object o : nicRaLst) {
					NicRelationObj nr = (NicRelationObj)o;
					NicObj nicObj = new NicObj();
					nicObj.setUuid(nr.getToUuid());
					nicLst = nicService.queryForListByObj(nicObj);
					if(nicLst!=null && nicLst.size()>0){
						for (Object obj : nicLst) {
							NicObj nic =(NicObj)obj;
							if(nic.getStatus().equals("CONNECTED")){
								nic.setStatus("已连接");
								if(nic.getLinkduplex()!=null && nic.getLinkduplex().equals("TRUE")){
									nic.setLinkduplex("全");
								}else{
									nic.setLinkduplex("半");
								}
							}else{
								nic.setStatus("已连接");
								nic.setLinkduplex("-");
								nic.setLinkspeed(0);
							}
							String mac = nic.getUuid().split("_")[1];
							nic.setMac(mac);
							reList.add(nic);
						}
					}
				}
			}
		}
		
		request.setAttribute("vssName", vssName);
		theForm.setResultList(reList);
		return "listNic";
	}
	
	public String listVm(){
		if(theForm==null){
			theForm = new NicForm();
		}
		HttpServletRequest request  = Struts2Utils.getRequest();
		String pguuid = request.getParameter("pguuid");
		String pgType = request.getParameter("pgType");
		String vssName = request.getParameter("pgType");
		List<VMHostObj> vmLst = new ArrayList<VMHostObj>();
		List nicRalst = null;
		NicRelationObj nicRaObj = new NicRelationObj();
		if(pgType.equals("0")){
			nicRaObj.setType("PORTGROUP_VM");
			nicRaObj.setFromUuid(pguuid);
			nicRalst = nicRelationService.queryForListByObj(nicRaObj);
		}else{
			nicRaObj.setType("DVPG_VM");
			nicRaObj.setFromUuid(pguuid);
			nicRalst = nicRelationService.queryForListByObj(nicRaObj);
		}
		if(nicRalst!=null && nicRalst.size()>0){
			for (Object obj : nicRalst) {
				nicRaObj = (NicRelationObj)obj;
				VMHostObj vmObj = new VMHostObj();
				vmObj.setVH_UUID(nicRaObj.getToUuid());
				vmObj = vmHostService.queryByObj(vmObj);
				vmObj.setVH_STORAGE(vmObj.getVH_STORAGE()==null?"0":Integer.parseInt(vmObj.getVH_STORAGE())/1024/1024+"");
				if(vmObj.getVH_TYPE().equals("3")){
					vmObj.setVH_TYPE("xen");
				}else if(vmObj.getVH_TYPE().equals("1")){
					vmObj.setVH_TYPE("vmware");
				}else{
					vmObj.setVH_TYPE("-");
				}
				if(vmObj.getVH_IP()==null || vmObj.getVH_IP().equals("")){
					vmObj.setVH_IP("-");
				}
				if(vmObj!=null){
					vmLst.add(vmObj);
				}
			}
		}
		
		theForm.setResultList(vmLst);
		request.setAttribute("vssName", vssName);
		return "listVm";
	}
}
