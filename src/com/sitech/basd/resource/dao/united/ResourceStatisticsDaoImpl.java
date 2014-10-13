package com.sitech.basd.resource.dao.united;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.sitech.basd.resource.domain.united.ResourceStatisticsObj;
import com.sitech.basd.sxcloud.cloud.domain.resource.TbCloud2HostInfoObj;
import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.utils.properties.PropertiesUtil;

@Repository("resourceStatisticsDao")
public class ResourceStatisticsDaoImpl extends BaseDao implements ResourceStatisticsDao{

	private Logger logger = LoggerFactory.getLogger(getClass());
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
	@SuppressWarnings("unchecked")
	@Override
	public List<ResourceStatisticsObj> queryForListByObj(
			ResourceStatisticsObj resourceStatisticsObj) {
		List<ResourceStatisticsObj> list = new ArrayList<ResourceStatisticsObj>();
		try {
			list = this.getSqlMap().queryForList("ResourceStatistics.queryForListByObj", resourceStatisticsObj);
		} catch (Exception e) {
			logger.error("ResourceStatistics.queryForListByObj: " + e.getMessage(), e);
		}
		return list;
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
		obj.setModulus(this.getModulus());
		List<Map<String, String>> list = null;
		Map<String, String> map = null;
		try {
			list = this.getSqlMap().queryForList("ResourceStatistics.queryForUnVir", obj);
		} catch (Exception e) {
			logger.error("ResourceStatistics.queryForUnVir: " + e.getMessage(), e);
		}
		if(list!=null&& list.size()>0){
			map = list.get(0);
		}
		return map;
	}
	
	private int getModulus(){
		int modulus = 1;
		String modulusStr = PropertiesUtil.getString("properties/modulus", "modulus");
		if (modulusStr != null) {
			modulus = Integer.parseInt(modulusStr);
		}
		return modulus;
	}
}
