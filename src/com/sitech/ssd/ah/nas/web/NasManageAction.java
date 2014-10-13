package com.sitech.ssd.ah.nas.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.sitech.basd.sxcloud.rsmu.web.BaseAction;
import com.sitech.basd.sxcloud.util.Struts2Utils;
import com.sitech.ssd.ah.nas.domain.NasFileSystemObj;
import com.sitech.ssd.ah.nas.domain.NasKpiObj;
import com.sitech.ssd.ah.nas.domain.NasStorePoolObj;
import com.sitech.ssd.ah.nas.service.NasFileSysService;
import com.sitech.ssd.ah.nas.service.NasKpiService;
import com.sitech.ssd.ah.nas.service.NasStorePoolService;

@Controller("nasManageAction")
public class NasManageAction extends BaseAction{

	@Autowired
	private NasFileSysService fileSysService;
	@Autowired
	private NasStorePoolService storePoolService;
	@Autowired
	private NasKpiService kpiService;
	
	
	private NasKpiObj nasKpiObj;
	private NasFileSystemObj nasFileSystemObj;
	private NasStorePoolObj nasPoolObj;
	
	private String deviceId;//设备Id
	private String poolname;//存储池名称
	private List list;//谨供页面迭代
	
	/**
	 * @Title: showNasFileSys
	 * @Description: 根据存储池获取文件系统
	 * @param
	 * @return 
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @throws Exception 
	 * @createtime 2014年4月7日15:48:04
	 */
	public String showNasFileSys(){
		if(nasFileSystemObj==null){
			nasFileSystemObj = new NasFileSystemObj();
		}
		nasFileSystemObj.setPagination(this.getPaginater().initPagination(Struts2Utils.getRequest()));
		if(deviceId!=null && !"".equals(deviceId)){
			nasFileSystemObj.setNAS_DEVICE_ID(deviceId);
		}
		if(poolname!=null && !"".equals(poolname)){
			nasFileSystemObj.setFS_STORAGE_POOL(poolname);
		}
		if("-1".equals(nasFileSystemObj.getFS_STATUS())){
			nasFileSystemObj.setFS_STATUS("");
		}
		list = fileSysService.getFileSysList(nasFileSystemObj);
		return "showNasFileSys";
	}
	
	/**
	 * @Title: showMount
	 * @Description: 存储所在实体
	 * @param
	 * @return 
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @throws Exception 
	 * @createtime 2014年6月4日10:28:21
	 */
	public String showMount(){
		if(nasFileSystemObj!=null){
			nasFileSystemObj.setFS_NAME(nasFileSystemObj.getFS_NAME().replace("_", ""));
			list = fileSysService.queryMountBySys(nasFileSystemObj);
			nasFileSystemObj = new NasFileSystemObj();
		}
		return "showMount";
	}
	public NasKpiObj getNasKpiObj() {
		return nasKpiObj;
	}
	public void setNasKpiObj(NasKpiObj nasKpiObj) {
		this.nasKpiObj = nasKpiObj;
	}
	public NasFileSystemObj getNasFileSystemObj() {
		return nasFileSystemObj;
	}
	public void setNasFileSystemObj(NasFileSystemObj nasFileSystemObj) {
		this.nasFileSystemObj = nasFileSystemObj;
	}
	public NasStorePoolObj getNasPoolObj() {
		return nasPoolObj;
	}
	public void setNasPoolObj(NasStorePoolObj nasPoolObj) {
		this.nasPoolObj = nasPoolObj;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getPoolname() {
		return poolname;
	}

	public void setPoolname(String poolname) {
		this.poolname = poolname;
	}

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}
	
}
