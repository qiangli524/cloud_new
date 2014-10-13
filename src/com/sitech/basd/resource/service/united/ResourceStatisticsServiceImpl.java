package com.sitech.basd.resource.service.united;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sitech.basd.resource.dao.united.ResourceStatisticsDao;
import com.sitech.basd.resource.domain.united.ResourceStatisticsObj;
import com.sitech.basd.sxcloud.cloud.domain.resource.TbCloud2HostInfoObj;

@Service("resourceStatisticsService")
public class ResourceStatisticsServiceImpl implements ResourceStatisticsService{

	@Autowired
	private ResourceStatisticsDao resourceStatisticsDao;
	
	/**
	 * @Title: queryForObjByObj
	 * @Description: 查询统计信息实体类
	 * @param
	 * @return ResourceStatisticsObj
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-8-12 下午1:59:16
	 */
	@Override
	public List<ResourceStatisticsObj> queryForListByObj(
			ResourceStatisticsObj resourceStatisticsObj) {
		return resourceStatisticsDao.queryForListByObj(resourceStatisticsObj);
	}
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
	public Map<String, String> queryForUnVirHost(TbCloud2HostInfoObj obj){
		return resourceStatisticsDao.queryForUnVirHost(obj);
	}
}
