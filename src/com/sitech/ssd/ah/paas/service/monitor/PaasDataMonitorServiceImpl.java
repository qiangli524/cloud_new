package com.sitech.ssd.ah.paas.service.monitor;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sitech.ssd.ah.paas.dao.monitor.PaasResourceMonitorDao;
import com.sitech.ssd.ah.paas.domain.monitor.PaasResourceMonitorObj;


@Service("paasDataMonitorService")
public class PaasDataMonitorServiceImpl implements PaasDataMonitorService {

	@Autowired
	private PaasResourceMonitorDao paasResourceMonitorDao;
	
	
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
	public List<PaasResourceMonitorObj> getSlabLists(PaasResourceMonitorObj obj){
		return paasResourceMonitorDao.queryForSlabList(obj);
	}
	
	
}
