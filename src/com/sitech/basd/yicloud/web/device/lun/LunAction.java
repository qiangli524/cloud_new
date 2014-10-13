package com.sitech.basd.yicloud.web.device.lun;

import java.text.DecimalFormat;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sitech.basd.sxcloud.rsmu.web.BaseAction;
import com.sitech.basd.yicloud.domain.datastore.StoreDeviceObj;
import com.sitech.basd.yicloud.domain.device.diskgroup.DiskGroupObj;
import com.sitech.basd.yicloud.domain.device.lun.LunObj;
import com.sitech.basd.yicloud.service.datastore.StoreDeviceService;
import com.sitech.basd.yicloud.service.device.diskgroup.DiskGroupService;
import com.sitech.basd.yicloud.service.device.lun.LunService;
import com.sitech.utils.randomid.RandomUUID;
import com.sitech.utils.servlet.PrintWriterOut;
import com.sitech.utils.ssh.SSHUtil;
import com.sitech.utils.ssh.SshConnection;

/**
 * @Title LunAction
 * @Description Lun块控制层
 * @author lipp
 * @date 2014-6-1 下午6:17:15
 * @version 1.0
 * @Company si-tech
 */
@SuppressWarnings("serial")
@Scope("prototype")
@Controller("lunAction")
public class LunAction extends BaseAction{

	@Autowired
	private LunService lunService;
	
	@Autowired
	private StoreDeviceService storeDeviceService;
	
	@Autowired
	private DiskGroupService diskGroupService;
	
	private LunObj lunObj;
	
	private List<LunObj> resultList;//结果集
	
	private List<StoreDeviceObj> deviceList;//存储设备集合
	
	 /**  
	  * @Title: list  
	  * @Description: 展示列表
	  * @return String   
	  * @throws  
	  * @Date 2014-6-1 下午6:19:08
	  * @author lipp
	  * @return
	  */
	public String list() {
		if (lunObj == null) {
			lunObj = new LunObj();
		}
		if (lunObj.getHealthy() != null && lunObj.getHealthy() == -1) {
			lunObj.setHealthy(null);
		}
		lunObj.setPagination(getPaginater().initPagination(request));
		resultList = lunService.queryForListByObj(lunObj);
		return "list";
	}

	 /**  
	  * @Title: add  
	  * @Description: 进入添加页面
	  * @return String   
	  * @throws  
	  * @Date 2014-6-1 下午6:24:11
	  * @author lipp
	  * @return
	  */
	public String add(){
		//查询所有存储设备
		if (lunObj == null || lunObj.getDiskGroupId() == null || "".equals(lunObj.getDiskGroupId())) {
			StoreDeviceObj storeDeviceObj = new StoreDeviceObj();
			deviceList = storeDeviceService.queryForList(storeDeviceObj);
		}
		return "add";
	}
	
	 /**  
	  * @Title: queryDiskGroupList  
	  * @Description: 查询磁盘组列表
	  * @return void   
	  * @throws  
	  * @Date 2014-6-2 下午2:10:10
	  * @author lipp
	  */
	public void queryDiskGroupList(){
		if (lunObj == null) {
			lunObj = new LunObj();
		}
		DiskGroupObj paramObj = new DiskGroupObj();
		paramObj.setStoreDeviceId(lunObj.getDeviceId());
		List<DiskGroupObj> diskList = diskGroupService.queryForListByObj(paramObj);
		JSONArray jsonArray = new JSONArray();
		jsonArray = JSONArray.fromObject(diskList);
		PrintWriterOut.printWirter(response, jsonArray);
	}
	
	 /**  
	  * @Title: queryDiskInfo  
	  * @Description: 查询磁盘组信息
	  * @return void   
	  * @throws  
	  * @Date 2014-6-2 下午2:15:10
	  * @author lipp
	  */
	public void queryDiskInfo(){
		if (lunObj == null) {
			lunObj = new LunObj();
		}
		DiskGroupObj diskGroupObj = new DiskGroupObj();
		diskGroupObj.setUuid(lunObj.getDiskGroupId());
		List<DiskGroupObj> diskList = diskGroupService.queryForListByObj(diskGroupObj);
		diskGroupObj = diskList.get(0);
		JSONObject json = new JSONObject();
		json = JSONObject.fromObject(diskGroupObj);
		PrintWriterOut.printWirter(response, json);
	}
	
	 /**  
	  * @Title: mod  
	  * @Description: 进入修改页面
	  * @return String   
	  * @throws  
	  * @Date 2014-6-1 下午6:25:05
	  * @author lipp
	  * @return
	  */
	public String mod(){
		if (lunObj == null) {
			lunObj = new LunObj();
		}
		lunObj = lunService.queryForListByObj(lunObj).get(0);
		return "mod";
	}
	
	 /**  
	  * @Title: save  
	  * @Description: 保存编辑信息
	  * @return void   
	  * @throws  
	  * @Date 2014-6-1 下午6:30:08
	  * @author lipp
	  */
	public void save() {
		if (lunObj == null) {
			lunObj = new LunObj();
		}
		int ret = 0;
		if (lunObj.getUuid() == null || "".equals(lunObj.getUuid())) {//新增
			//int num = lunObj.getNum().intValue();
			//for (int i = 0; i < num; i++) {
			DiskGroupObj diskGroupObj = new DiskGroupObj();
			diskGroupObj.setUuid(lunObj.getDiskGroupId());
			List<DiskGroupObj> diskList = diskGroupService.queryForListByObj(diskGroupObj);
			if(diskList!=null && diskList.size()>0){
				diskGroupObj = diskList.get(0);
			}
			long capacity = (long)(lunObj.getCapacity()*1024*1024*1024);
//			SshConnection sc = SSHUtil.getSshConnection(false, "192.168.1.31", 22,"root","111111");
//			try {
//				SSHUtil.sendShellToSSHChannelReMess(
//						sc,true,"sh " + "/opt/IBM_DS/scripts/cr_vg.sh " + diskGroupObj.getName()+" "+lunObj.getLun_type() +" "+ lunObj.getDepth()+" "+ capacity +" "+ lunObj.getName());
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
			String uuid = RandomUUID.getUuid();
			lunObj.setUuid(uuid);
			ret = lunService.insertByObj(lunObj);
			//}
		} else {//修改
			ret = lunService.updateByObj(lunObj);
		}
		PrintWriterOut.printWirter(response, ret);
	}
	
	 /**  
	  * @Title: delete  
	  * @Description: 删除 
	  * @return void   
	  * @throws  
	  * @Date 2014-6-1 下午6:30:34
	  * @author lipp
	  */
	public void delete() {
		if (lunObj == null) {
			lunObj = new LunObj();
		}
		String uuids = lunObj.getUuid();
		String names = lunObj.getName();
		String[] uuidArr = uuids.split(",");
		String[] nameArr = names.split(",");
		for(int i=0;i<uuidArr.length;i++){
//			SshConnection sc = SSHUtil.getSshConnection(false, "192.168.1.31", 22,"root","111111");
//			try {
//				SSHUtil.sendShellToSSHChannelReMess(
//						sc,true,"sh " + "/opt/IBM_DS/scripts/del_vg.sh " +nameArr[i]);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
			lunObj.setUuid(uuidArr[i]);
			int ret = lunService.deleteByObj(lunObj);
		}
	}
	
	public LunObj getLunObj() {
		return lunObj;
	}

	public void setLunObj(LunObj lunObj) {
		this.lunObj = lunObj;
	}

	public List<StoreDeviceObj> getDeviceList() {
		return deviceList;
	}
	
	public List<LunObj> getResultList() {
		return resultList;
	}

	public void setResultList(List<LunObj> resultList) {
		this.resultList = resultList;
	}

	public void setDeviceList(List<StoreDeviceObj> deviceList) {
		this.deviceList = deviceList;
	}

}
