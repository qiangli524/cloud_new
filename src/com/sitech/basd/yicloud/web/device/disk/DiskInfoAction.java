package com.sitech.basd.yicloud.web.device.disk;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sitech.basd.sxcloud.rsmu.web.BaseAction;
import com.sitech.basd.yicloud.domain.device.disk.DiskInfoObj;
import com.sitech.basd.yicloud.domain.device.diskgroup.DiskGroupObj;
import com.sitech.basd.yicloud.service.device.disk.DiskInfoService;
import com.sitech.basd.yicloud.service.device.diskgroup.DiskGroupService;
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
@Controller("diskInfoAction")
@Scope("prototype")
public class DiskInfoAction extends BaseAction{

	@Autowired
	private DiskInfoService diskInfoService;
	@Autowired
	private DiskGroupService diskGroupService;
	
	private DiskInfoObj diskInfoObj;
	
	private List<DiskInfoObj> resultList;//结果集
	
	private List<DiskGroupObj> diskGroupList;//磁盘组列表
	
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
		if (diskInfoObj == null) {
			diskInfoObj = new DiskInfoObj();
		}
		diskInfoObj.setPagination(getPaginater().initPagination(request));
		resultList = diskInfoService.queryForListByObj(diskInfoObj);
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
		DiskGroupObj paramObj = new DiskGroupObj();
		diskGroupList = diskGroupService.queryForListByObj(paramObj);
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
		if (diskInfoObj == null) {
			diskInfoObj = new DiskInfoObj();
		}
		resultList = diskInfoService.queryForListByObj(diskInfoObj);
		if(resultList!=null && resultList.size()>0){
			diskInfoObj = resultList.get(0);
		}
		DiskGroupObj paramObj = new DiskGroupObj();
		diskGroupList = diskGroupService.queryForListByObj(paramObj);
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
		if (diskInfoObj == null) {
			diskInfoObj = new DiskInfoObj();
		}
		int ret = 0;
		if ("add".equals(flag)) {//新增
			ret = diskInfoService.insertByObj(diskInfoObj);
		} else {//修改
			ret = diskInfoService.updateByObj(diskInfoObj);
		}
		PrintWriterOut.printWirter(response, ret);
	}
	
	/**
	 * 
	 * @Title: createDisk
	 * @Description: 创建热备盘
	 * @param
	 * @return void
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-6-7 下午2:17:17
	 */
	 public void createDisk(){
		 if (diskInfoObj == null) {
			diskInfoObj = new DiskInfoObj();
		}
		diskInfoObj.setDisk_type("1");
//		SshConnection sc = SSHUtil.getSshConnection(false, "192.168.1.31", 22,"root","111111");
//		try {
//			SSHUtil.sendShellToSSHChannelReMess(sc,true,"sh " + "/opt/IBM_DS/scripts/create_hotspare.sh " + diskInfoObj.getDisk_number()+","+diskInfoObj.getDisk_id());
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		int ret = diskInfoService.updateByObj(diskInfoObj);
		PrintWriterOut.printWirter(response, ret);
	 }
	 
	 public void deleteDisk(){
		 if (diskInfoObj == null) {
				diskInfoObj = new DiskInfoObj();
		}
		diskInfoObj.setDisk_type("2");
//		SshConnection sc = SSHUtil.getSshConnection(false, "192.168.1.31", 22,"root","111111");
//		try {
//			SSHUtil.sendShellToSSHChannelReMess(sc,true,"sh " + "/opt/IBM_DS/scripts/delete_hotspare.sh " + diskInfoObj.getDisk_number()+","+diskInfoObj.getDisk_id());
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		int ret = diskInfoService.updateByObj(diskInfoObj);
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
		if (diskInfoObj == null) {
			diskInfoObj = new DiskInfoObj();
		}
		String uuids = diskInfoObj.getDisk_id();
		String[] uuidArr = uuids.split(",");
		for (String string : uuidArr) {
			diskInfoObj.setDisk_id(string);
			int ret = diskInfoService.deleteByObj(diskInfoObj);
		}
	}

	public DiskInfoObj getDiskInfoObj() {
		return diskInfoObj;
	}

	public void setDiskInfoObj(DiskInfoObj diskInfoObj) {
		this.diskInfoObj = diskInfoObj;
	}

	public List<DiskInfoObj> getResultList() {
		return resultList;
	}

	public void setResultList(List<DiskInfoObj> resultList) {
		this.resultList = resultList;
	}

	public List<DiskGroupObj> getDiskGroupList() {
		return diskGroupList;
	}

	public void setDiskGroupList(List<DiskGroupObj> diskGroupList) {
		this.diskGroupList = diskGroupList;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}
	
}
