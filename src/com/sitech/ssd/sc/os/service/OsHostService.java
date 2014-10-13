package com.sitech.ssd.sc.os.service;

import java.util.List;
import java.util.Map;

import com.sitech.ssd.sc.os.domain.HostModel;

/**
 * @ClassName OsHostService
 * @Desc 
 * @Author JamTau
 * @date May 22, 2014 5:48:01 PM
 */
public interface OsHostService {
	/**
	 * @Tittle queryForObject
	 * @Description 查询主机信息
	 * @Modify Date May 22, 2014 7:30:12 PM
	 * @param host
	 * @return HostModel
	 */
	public HostModel queryForObject(HostModel host);
	

	public List<HostModel> listOsHost(HostModel host);
	
	/**
	 * @Tittle deleteOsHost
	 * @Description 删除主机信息
	 * @Modify Date May 22, 2014 5:48:11 PM
	 * @param host
	 * @return int
	 */
	public int deleteOsHost(HostModel host);
	
	public int addOsHost(HostModel host);
	
	public int modifyOsHost(HostModel host);
	
	public int updateInstallState(HostModel host);	
	/**
	 * @Tittle customedInstall
	 * @Description 
	 * 	保存配置信息，并安装操作系统
	 * @Modify Date May 23, 2014 3:49:55 PM
	 * @param host
	 * @param users
	 * @param partions
	 * @return String
	 */
	public String customedInstall(HostModel host);
	
	public int clearOsHostConfig(HostModel host);
	
	public String batchCustomedInstall(String ids);
	
	public Map queryHostBuss(HostModel host);

}
