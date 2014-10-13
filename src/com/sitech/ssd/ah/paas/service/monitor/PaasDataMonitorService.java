package com.sitech.ssd.ah.paas.service.monitor;

import java.util.List;
import java.util.Map;

import com.sitech.ssd.ah.paas.domain.monitor.PaasResourceMonitorObj;

public interface PaasDataMonitorService {

	
	/**
	 * 
	 * @Title: getSlabLists
	 * @Description: 获取所有slab
	 * @param
	 * @return List<PaasResourceMonitorObj>
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @createtime 2014年4月22日19:42:47
	 */
	public List<PaasResourceMonitorObj> getSlabLists(PaasResourceMonitorObj obj);
	
}
