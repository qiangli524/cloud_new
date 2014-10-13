package com.sitech.ssd.ah.nas.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sitech.basd.yicloud.dao.datastore.DataStoreDao;
import com.sitech.basd.yicloud.domain.datastore.DataStoreObj;
import com.sitech.ssd.ah.nas.dao.NasFileSysDao;
import com.sitech.ssd.ah.nas.domain.MountObj;
import com.sitech.ssd.ah.nas.domain.NasFileSystemObj;

@Service("nasFileSysService")
public class NasFileSysServiceImpl implements NasFileSysService {

	@Autowired
	private NasFileSysDao nasFileSysDao;
	@Autowired
	private DataStoreDao dataStoreDao;
	/**
	 * @Title: getFileSysList
	 * @Description: nas文件系统列表
	 * @param
	 * @return 
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @throws Exception 
	 * @createtime 2014年4月7日15:48:04
	 */
	public List<NasFileSystemObj> getFileSysList(NasFileSystemObj obj){
		List<NasFileSystemObj> list = new ArrayList<NasFileSystemObj>();
		
		
		return nasFileSysDao.queryFile(obj);
	}
	/**
	 * @Title: queryVmBuSys
	 * @Description: 根据文件系统查找对应挂在实体
	 * @param
	 * @return 
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @throws Exception 
	 * @createtime 2014年6月4日11:19:45
	 */
	public List<MountObj> queryMountBySys(NasFileSystemObj obj){
		List<MountObj> list = new ArrayList<MountObj>();
		List<MountObj> listForHost = nasFileSysDao.queryHostBySys(obj);
		List<MountObj> listForVm = nasFileSysDao.queryVmBySys(obj);
		if(listForVm!=null&&listForVm.size()>0){
			for(MountObj o : listForVm){
				MountObj mount = new MountObj();
				mount.setMountName(o.getMountName());
				mount.setIp(o.getIp());
				mount.setNasIp(o.getNasIp());
				mount.setBusi(o.getBusi());
				mount.setType("虚拟机");
				list.add(mount);
			}
		}
		if(list.size()==0){
			if(listForHost!=null&&listForHost.size()>0){
				for(MountObj o : listForHost){
					MountObj mount = new MountObj();
					mount.setMountName(o.getMountName());
					mount.setIp(o.getIp());
					mount.setNasIp(o.getNasIp());
					mount.setBusi(o.getBusi());
					mount.setType("宿主机");
					list.add(mount);
				}
			}
		}
		return list;
	}
}
