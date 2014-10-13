package com.sitech.basd.resource.web.united;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.sitech.basd.resource.domain.united.UnitedTreeObj;
import com.sitech.basd.resource.service.united.UnitedNetworkService;
import com.sitech.basd.sxcloud.cloud.domain.leader.TbCloud2CubinetInfoObj;
import com.sitech.basd.sxcloud.cloud.domain.resource.TbCloud2HostInfoObj;
import com.sitech.basd.sxcloud.cloud.service.leader.LeaderViewBusinessService;
import com.sitech.basd.sxcloud.cloud.service.resource.HostInfoService;
import com.sitech.basd.yicloud.domain.datastore.StoreDeviceObj;
import com.sitech.basd.yicloud.service.datastore.StoreDeviceService;
import com.sitech.utils.randomid.RandomUUID;
/**
 * 
 * <p>
 * Title: UnitedDeviceAction
 * </p>
 * <p>
 * Description: 设备管理（包括添加未分配主机和存储设备）
 * </p>
 * <p>
 * Copyright: Copyright (c) 2013
 * </p>
 * <p>
 * Company: SI-TECH
 * </p>
 * 
 * @author taoxue
 * @version 1.0
 * @createtime 2013-9-11 下午5:55:11
 * 
 */
@Component("unitedDeviceAction")
@Scope("prototype")
public class UnitedDeviceAction {
	private UnitedTreeObj obj;
	private StoreDeviceObj storeObj;
	private TbCloud2HostInfoObj hostObj;
	private TbCloud2CubinetInfoObj netObj = new TbCloud2CubinetInfoObj();
	private String result;
	@Autowired
	private UnitedNetworkService unitedNetworkService;
	@Autowired
	private HostInfoService hostInfoService;
	@Autowired
	private LeaderViewBusinessService leaderViewBusinessService;
	@Autowired
	private StoreDeviceService storeDeviceService;
	
	/**
	 * 
	 * @Title: addHostDevice
	 * @Description: 添加主机(用于添加未分配主机设备)
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime 2013-9-11 下午5:55:48
	 */
	public String addHostDevice() {
		if (hostObj == null) {
			hostObj = new TbCloud2HostInfoObj();
		}
		List cubinetList = leaderViewBusinessService.queryCubListByRoomId(netObj);
		hostObj.setCubinetList(cubinetList);
		return "addHostDevice";
	}
	/**
	 * 
	 * @Title: updHost
	 * @Description: 修改主机
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-9-13
	 */
	public String updHost() {
		if (hostObj == null) {
			hostObj = new TbCloud2HostInfoObj();
		}
		hostObj = hostInfoService.queryByObj(hostObj);
		List cubinetList = leaderViewBusinessService.queryCubListByRoomId(netObj);
		hostObj.setCubinetList(cubinetList);
		return "addHostDevice";
	}
	/**
	 * 
	 * @Title: saveHostDevice
	 * @Description:保存主机
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-9-12
	 */
	public String saveHostDevice() {
		if (obj == null) {
			obj = new UnitedTreeObj();
		}
		if (hostObj == null) {
			hostObj = new TbCloud2HostInfoObj();
		}
		if(hostObj.getEq_id() == null || "".equals(hostObj.getEq_id())){
			try {
				int id = hostInfoService.getIdSequence();
				obj.setUuid(id+"");
				obj.setName(hostObj.getEq_name());
				unitedNetworkService.addDomain(obj);
				
				hostObj.setEq_id(id+"");
				hostObj.setId(id);
				hostInfoService.insertByObj(hostObj);
			} catch (SQLException e) {
				result = e.getMessage();
				e.printStackTrace();
			}
		}else{
			try {
				obj.setUuid(hostObj.getEq_id());
				obj.setName(hostObj.getEq_name());
				obj.setParent_id(obj.getPratentId());
				unitedNetworkService.updOperate(obj);
				hostInfoService.updateByObj(hostObj);
			} catch (SQLException e) {
				result = e.getMessage();
				e.printStackTrace();
			}
		}
		return result;
	}
	/**
	 * 
	 * @Title: delHost
	 * @Description: 删除Host
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-9-11
	 */
	public String delHost() {
		if (obj == null) {
			obj = new UnitedTreeObj();
		}
		if (hostObj == null) {
			hostObj = new TbCloud2HostInfoObj();
		}
		try {
			hostObj.setEq_id(obj.getUuid());
			unitedNetworkService.delOperate(obj);
			hostInfoService.deleteByObj(hostObj);
		} catch (SQLException e) {
			result = e.getMessage();
			e.printStackTrace();
		}
		return result;
	}
	/**
	 * 
	 * @Title: addStorageDevice
	 * @Description: 添加存储设备
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime 2013-9-11 下午5:56:42
	 */
	public String addStorageDevice() {
		return "addStorageDevice";
	}
	/**
	 * 
	 * @Title: editStorageDevice
	 * @Description: 修改存储设备
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-9-13
	 */
	public String editStorageDevice() {
		if (storeObj == null) {
			storeObj = new StoreDeviceObj();
		}
		storeObj = storeDeviceService.queryByObj(storeObj);
		return "addStorageDevice";
	}
	/**
	 * 
	 * @Title: saveStorageDevice
	 * @Description:保存存储设备
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-9-12
	 */
	public String saveStorageDevice() {
		if (obj == null) {
			obj = new UnitedTreeObj();
		}
		if (storeObj == null) {
			storeObj = new StoreDeviceObj();
		}
		if(storeObj.getId() == null || "".equals(storeObj.getId())){
			try {
				String id = RandomUUID.getUuid();
				storeObj.setId(id);
				storeDeviceService.insertStoreDevice(storeObj);
				obj.setUuid(id);
				obj.setName(storeObj.getName());
				unitedNetworkService.addDomain(obj);
			} catch (SQLException e) {
				result = e.getMessage();
				e.printStackTrace();
			}
		}else{
			try {
				obj.setUuid(storeObj.getId());
				obj.setName(storeObj.getName());
				obj.setParent_id(obj.getPratentId());
				unitedNetworkService.updOperate(obj);
				storeDeviceService.updateByObj(storeObj);
			} catch (SQLException e) {
				result = e.getMessage();
				e.printStackTrace();
			}
		}
		return result;
	}
	/**
	 * 
	 * @Title: delStorageDevice
	 * @Description: 删除存储设备
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-9-11
	 */
	public String delStorageDevice() {
		if (obj == null) {
			obj = new UnitedTreeObj();
		}
		if (storeObj == null) {
			storeObj = new StoreDeviceObj();
		}
		try {
			storeObj.setId(obj.getUuid());
			unitedNetworkService.delOperate(obj);
			storeDeviceService.deleteByObj(storeObj);
		} catch (SQLException e) {
			result = e.getMessage();
			e.printStackTrace();
		}
		return result;
	}
	public UnitedTreeObj getObj() {
		return obj;
	}

	public void setObj(UnitedTreeObj obj) {
		this.obj = obj;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
	public TbCloud2HostInfoObj getHostObj() {
		return hostObj;
	}
	public void setHostObj(TbCloud2HostInfoObj hostObj) {
		this.hostObj = hostObj;
	}
	public TbCloud2CubinetInfoObj getNetObj() {
		return netObj;
	}
	public void setNetObj(TbCloud2CubinetInfoObj netObj) {
		this.netObj = netObj;
	}
	public StoreDeviceObj getStoreObj() {
		return storeObj;
	}
	public void setStoreObj(StoreDeviceObj storeObj) {
		this.storeObj = storeObj;
	}
	
}
