package com.sitech.ssd.sc.os.dao;

import java.util.List;
import java.util.Map;

import com.sitech.ssd.sc.os.domain.HostModel;

/**
 * @ClassName OsHostDao
 * @Desc 
 * @Author JamTau
 * @date May 21, 2014 6:48:57 PM
 */
public interface OsHostDao {

	/**
	 * @Tittle insertHose
	 * @Description 
	 * @Modify Date May 21, 2014 6:53:44 PM
	 * @param host
	 * @return int
	 */
	public int insertHost(HostModel host);
	
	/**
	 * @Tittle deleteHost
	 * @Description 
	 * @Modify Date May 22, 2014 7:32:35 PM
	 * @param host
	 * @return int
	 */
	public int deleteHost(HostModel host);
	
	/**
	 * @Tittle updateOsHost
	 * @Description 
	 * @Modify Date May 23, 2014 10:06:14 AM
	 * @param host
	 * @return int
	 */
	public int updateOsHost(HostModel host);
	
	public int updateInstallState(HostModel host);
	
	/**
	 * @Tittle queryForList
	 * @Description 
	 * @Modify Date May 21, 2014 6:53:56 PM
	 * @param host
	 * @return List<HostModel>
	 */
	public List<HostModel> queryForList(HostModel host);
	
	/**
	 * @Tittle queryForObject
	 * @Description 
	 * @Modify Date May 22, 2014 7:19:35 PM
	 * @param host
	 * @return HostModel
	 */
	public HostModel queryForObject(HostModel host);
	
	public Map queryHostBuss(HostModel host);
}
