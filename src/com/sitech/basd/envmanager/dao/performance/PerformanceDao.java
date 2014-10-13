package com.sitech.basd.envmanager.dao.performance;

import java.util.List;

import com.sitech.basd.envmanager.domain.performance.AddressObj;
import com.sitech.basd.envmanager.domain.performance.PerformanceObj;

public interface PerformanceDao {
	/**
	 * @Title:查询所有资源使用情况
	 * @Copyright: Copyright (c) 2013-4
	 * @Company: si-tech
	 * @author xumq
	 * @version 1.0
	 */
	public List queryPerformanceObj(PerformanceObj obj);
	/**
	 * @Title:查询某个资源使用情况
	 * @Copyright: Copyright (c) 2013-4
	 * @Company: si-tech
	 * @author xumq
	 * @version 1.0
	 */
	public PerformanceObj queryPerformanceOne(PerformanceObj obj);
	/**
	 * @Title:增加资源使用情况
	 * @Copyright: Copyright (c) 2013-4
	 * @Company: si-tech
	 * @author xumq
	 * @version 1.0
	 */
	public int insertPerformanceObj(PerformanceObj obj);
	/**
	 * @Title:修改资源使用情况
	 * @Copyright: Copyright (c) 2013-4
	 * @Company: si-tech
	 * @author xumq
	 * @version 1.0
	 */
	public int updatePerformanceObj(PerformanceObj obj);
	/**
	 * @Title:删除资源使用情况
	 * @Copyright: Copyright (c) 2013-4
	 * @Company: si-tech
	 * @author xumq
	 * @version 1.0
	 */
	public int deletePerformanceObj(PerformanceObj obj);
	
	/**
	 * @Title:查询所有地址变动信息
	 * @Copyright: Copyright (c) 2013-4
	 * @Company: si-tech
	 * @author xumq
	 * @version 1.0
	 */
	public List queryAddressInfo(AddressObj obj);
	/**
	 * @Title:增加地址变动信息
	 * @Copyright: Copyright (c) 2013-4
	 * @Company: si-tech
	 * @author xumq
	 * @version 1.0
	 */
	public int insertAddressInfo(AddressObj obj);
	
	/**
	 * @Title:修改地址变动信息
	 * @Copyright: Copyright (c) 2013-4
	 * @Company: si-tech
	 * @author xumq
	 * @version 1.0
	 */
	public int updateAddressInfo(AddressObj obj);

}
