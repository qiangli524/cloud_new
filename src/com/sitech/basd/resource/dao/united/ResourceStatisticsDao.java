package com.sitech.basd.resource.dao.united;

import java.util.List;
import java.util.Map;

import com.sitech.basd.resource.domain.united.ResourceStatisticsObj;
import com.sitech.basd.sxcloud.cloud.domain.resource.TbCloud2HostInfoObj;

/**
 * <p>Title: ResourceStatisticsDao</p>
 * <p>Description: 统一资源接口
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: SI-TECH </p>
 * @author lipengpeng
 * @version 1.0
 * @createtime 2013-8-12 下午2:02:07
 *
 */
public interface ResourceStatisticsDao {

	/**
	 * @Title: queryForObjByObj
	 * @Description: 统一资源信息展示实体类
	 * @param
	 * @return ResourceStatisticsObj
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-8-12 下午2:03:13
	 */
	public List<ResourceStatisticsObj> queryForListByObj(ResourceStatisticsObj resourceStatisticsObj);
	
	/**
	 * @Title: queryForUnVirHost
	 * @Description: 统计首页tab非虚拟化主机信息
	 * @param
	 * @return Map<String, String>
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @createtime 2013-9-0  pm 21:37
	 */
	public Map<String, String> queryForUnVirHost(TbCloud2HostInfoObj obj);
}
