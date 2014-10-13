package com.sitech.basd.sxcloud.cloud.service.resource;

import java.util.List;

import com.sitech.basd.sxcloud.cloud.domain.resource.TbCloud2HostPoolObj;

public interface HostPoolService {

	/**
	 * 
	 * @Title: 查询主机池里的主机信息
	 * @Copyright: Copyright (c) 2013-02-27
	 * @Company: si-tech
	 * @author xmq
	 * @version 1.0
	 */
	public List queryHostName(TbCloud2HostPoolObj obj);

	/**
	 * 
	 * @Title: 查询主机池信息
	 * @Copyright: Copyright (c) 2013-02-27
	 * @Company: si-tech
	 * @author xmq
	 * @version 1.0
	 */
	public List queryHostForPool(TbCloud2HostPoolObj obj);

	/**
	 * 
	 * @Title: 查询一条主机池信息
	 * @Copyright: Copyright (c) 2013-02-27
	 * @Company: si-tech
	 * @author xmq
	 * @version 1.0
	 */
	public TbCloud2HostPoolObj queryHostObj(TbCloud2HostPoolObj obj);

	/**
	 * 
	 * @Title: 增加主机池信息
	 * @Copyright: Copyright (c) 2013-02-27
	 * @Company: si-tech
	 * @author xmq
	 * @version 1.0
	 */
	public int insertHostForPool(TbCloud2HostPoolObj obj);

	/**
	 * 
	 * @Title: 修改主机池信息
	 * @Copyright: Copyright (c) 2013-02-27
	 * @Company: si-tech
	 * @author xmq
	 * @version 1.0
	 */
	public int updateHostForPool(TbCloud2HostPoolObj obj);

	/**
	 * 
	 * @Title: 删除主机池信息
	 * @Copyright: Copyright (c) 2013-02-27
	 * @Company: si-tech
	 * @author xmq
	 * @version 1.0
	 */
	public int deleteHostForPool(TbCloud2HostPoolObj obj);

	/**
	 * 
	 * @Title: 相同主机名称的个数统计
	 * @Copyright: Copyright (c) 2013-02-27
	 * @Company: si-tech
	 * @author xmq
	 * @version 1.0
	 */
	public int getHostPoolCount(TbCloud2HostPoolObj obj);

	/**
	 * 
	 * @Title: 删除主机池信息
	 * @Copyright: Copyright (c) 2013-02-27
	 * @Company: si-tech
	 * @author xmq
	 * @version 1.0
	 */
	public int deleteHostRelation(TbCloud2HostPoolObj obj);
}
