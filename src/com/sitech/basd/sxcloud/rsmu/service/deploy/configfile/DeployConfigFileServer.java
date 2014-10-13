package com.sitech.basd.sxcloud.rsmu.service.deploy.configfile;

import java.util.List;

import com.sitech.basd.sxcloud.rsmu.domain.deploy.configfile.DeployConfigFile;

public interface DeployConfigFileServer {
	/**
	 * 删除修改配置文件的记录
	 * @param obj
	 * @return
	 */
	public int deleteByObj(DeployConfigFile obj);
	
	/**
	 * 插入修改配置文件的记录
	 * @param obj
	 * @return
	 */
	public int insertByObj(DeployConfigFile obj);

	/**
	 * 查询修改配置文件的记录
	 * @param obj
	 * @return
	 */
	public List queryForListByObj(DeployConfigFile obj);
	
}
