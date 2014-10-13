package com.sitech.basd.yicloud.web.device.diskgroup;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sitech.basd.sxcloud.rsmu.web.BaseAction;
import com.sitech.basd.yicloud.domain.datastore.StoreDeviceObj;
import com.sitech.basd.yicloud.domain.device.disk.DiskInfoObj;
import com.sitech.basd.yicloud.domain.device.diskgroup.DiskGroupObj;
import com.sitech.basd.yicloud.domain.device.lun.LunObj;
import com.sitech.basd.yicloud.service.datastore.StoreDeviceService;
import com.sitech.basd.yicloud.service.device.disk.DiskInfoService;
import com.sitech.basd.yicloud.service.device.diskgroup.DiskGroupService;
import com.sitech.basd.yicloud.service.device.lun.LunService;
import com.sitech.utils.randomid.RandomUUID;
import com.sitech.utils.servlet.PrintWriterOut;
import com.sitech.utils.ssh.SSHUtil;
import com.sitech.utils.ssh.SshConnection;
/**
 * @Title DiskGroupAction
 * @Description 磁盘组控制层
 * @author lipp
 * @date 2014-6-1 下午6:16:42
 * @version 1.0
 * @Company si-tech
 */
@SuppressWarnings("serial")
@Controller("diskGroupAction")
@Scope("prototype")
public class DiskGroupAction extends BaseAction{

	@Autowired
	private DiskGroupService diskGroupService;
	@Autowired
	private DiskInfoService diskInfoService;
	@Autowired
	private StoreDeviceService storeDeviceService;
	@Autowired
	private LunService lunService;
	
	private DiskGroupObj diskGroupObj;
	
	private List<DiskGroupObj> resultList;//结果集
	
	private List<StoreDeviceObj> deviceList;//存储设备列表
	
	private List<DiskInfoObj> diskInfoList;
	
	private String flag;
	
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
		if (diskGroupObj == null) {
			diskGroupObj = new DiskGroupObj();
		}
		if ("-1".equals(diskGroupObj.getRaidLevel())) {
			diskGroupObj.setRaidLevel(null);
		}
		if ("-1".equals(diskGroupObj.getStoreDeviceId())) {
			diskGroupObj.setStoreDeviceId(null);
		}
		diskGroupObj.setPagination(getPaginater().initPagination(request));
		resultList = diskGroupService.queryForListByObj(diskGroupObj);
		
		//查询所有存储设备
		StoreDeviceObj storeDeviceObj = new StoreDeviceObj();
		deviceList = storeDeviceService.queryForList(storeDeviceObj);
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
		if (diskGroupObj == null) {
			diskGroupObj = new DiskGroupObj();
		}
		StoreDeviceObj storeDeviceObj = new StoreDeviceObj();
		storeDeviceObj.setId(diskGroupObj.getStoreDeviceId());
		deviceList = storeDeviceService.queryForList(storeDeviceObj);
		return "add";
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
		if (diskGroupObj == null) {
			diskGroupObj = new DiskGroupObj();
		}
		diskGroupObj = diskGroupService.queryForListByObj(diskGroupObj).get(0);
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
		if (diskGroupObj == null) {
			diskGroupObj = new DiskGroupObj();
		}
		int ret = 0;
		if (diskGroupObj.getUuid() == null || "".equals(diskGroupObj.getUuid())) {//新增
//			String disk_seql = diskGroupObj.getDisk_seq().substring(0, diskGroupObj.getDisk_seq().length()-1);
//			System.out.println(disk_seql+"===========");
//			SshConnection sc = SSHUtil.getSshConnection(false, "192.168.1.31", 22,"root","111111");
//			try {
//				SSHUtil.sendShellToSSHChannelReMess(
//						sc,true,"sh -x " + "/opt/IBM_DS/scripts/create_array.sh " + diskGroupObj.getName() +" "+ diskGroupObj.getRaidLevel() +" '"+disk_seql+"'");
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
			String uuid = RandomUUID.getUuid();
			diskGroupObj.setUuid(uuid);
			ret = diskGroupService.insertByObj(diskGroupObj);
			
			String[] disk_ids = diskGroupObj.getDisk_id().split(",");
			for(String str:disk_ids){
				DiskInfoObj diskInfoObj = new DiskInfoObj();
				diskInfoObj.setDisk_id(str);
				diskInfoObj.setDiskgroup_id(uuid);
				ret = diskInfoService.updateByObj(diskInfoObj);
			}
		} else {//修改
			ret = diskGroupService.updateByObj(diskGroupObj);
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
		if (diskGroupObj == null) {
			diskGroupObj = new DiskGroupObj();
		}
		String uuids = diskGroupObj.getUuid();
		String names = diskGroupObj.getName();
		String[] uuidArr = uuids.split(",");
		String[] nameArr = names.split(",");
		for(int i = 0;i < uuidArr.length;i++){
//			SshConnection sc = SSHUtil.getSshConnection(false, "192.168.1.31", 22,"root","111111");
//			try {
//				SSHUtil.sendShellToSSHChannelReMess(
//						sc,true,"sh " + "/opt/IBM_DS/scripts/delete_array.sh " + nameArr[i]);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
			diskGroupObj.setUuid(uuidArr[i]);
			int ret = diskGroupService.deleteByObj(diskGroupObj);
			DiskInfoObj infoOBj = new DiskInfoObj();
			infoOBj.setDiskgroup_id("");
			infoOBj.setDiskgroup_Id(uuidArr[i]);
			diskInfoService.updateGroupIdByObj(infoOBj);
			
			LunObj luObj = new LunObj();
			luObj.setDiskGroupId("");
			luObj.setDisk_group_id(uuidArr[i]);
			lunService.updateGroupIdByObj(luObj);
		}
	}
	
	public String addDisk(){
		DiskInfoObj diskInfoObj = new DiskInfoObj();
		diskInfoList = diskInfoService.queryForListByGroupIsNull(diskInfoObj);
		return "addDisk";
	}
	
	public DiskGroupObj getDiskGroupObj() {
		return diskGroupObj;
	}

	public void setDiskGroupObj(DiskGroupObj diskGroupObj) {
		this.diskGroupObj = diskGroupObj;
	}

	public List<DiskGroupObj> getResultList() {
		return resultList;
	}

	public void setResultList(List<DiskGroupObj> resultList) {
		this.resultList = resultList;
	}

	public List<StoreDeviceObj> getDeviceList() {
		return deviceList;
	}

	public void setDeviceList(List<StoreDeviceObj> deviceList) {
		this.deviceList = deviceList;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public List<DiskInfoObj> getDiskInfoList() {
		return diskInfoList;
	}

	public void setDiskInfoList(List<DiskInfoObj> diskInfoList) {
		this.diskInfoList = diskInfoList;
	}
	
}
