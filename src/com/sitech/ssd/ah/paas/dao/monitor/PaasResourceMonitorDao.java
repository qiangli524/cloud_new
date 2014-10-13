package com.sitech.ssd.ah.paas.dao.monitor;

import java.util.List;

import com.sitech.ssd.ah.paas.domain.monitor.PaasResourceMonitorObj;


public interface PaasResourceMonitorDao {
	
	/**
	 * 
	 * @Title: queryForList
	 * @Description: 查询监控信息
	 * @param
	 * @return List<PaasResourceMonitorObj>
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime 2014-4-1 下午6:52:36
	 */
	public List<PaasResourceMonitorObj> queryForList(PaasResourceMonitorObj obj);
	
	/**
	 * 
	 * @Title: queryForSlabList
	 * @Description: 获取所有slab
	 * @param
	 * @return List<PaasResourceMonitorObj>
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @createtime 2014年4月22日19:37:25
	 */
	public List<PaasResourceMonitorObj> queryForSlabList(PaasResourceMonitorObj obj);
}
