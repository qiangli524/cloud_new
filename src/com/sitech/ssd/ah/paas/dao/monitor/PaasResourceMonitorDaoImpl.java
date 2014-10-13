package com.sitech.ssd.ah.paas.dao.monitor;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.ssd.ah.paas.domain.monitor.PaasResourceMonitorObj;


@Repository("paasResourceMonitorDao")
public class PaasResourceMonitorDaoImpl extends BaseDao implements PaasResourceMonitorDao {
	
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
	@Override
	public List<PaasResourceMonitorObj> queryForList(PaasResourceMonitorObj obj) {
		List<PaasResourceMonitorObj> list = new ArrayList<PaasResourceMonitorObj>();
		try {
			list = getSqlMap().queryForList("PaasResourceMonitor.queryForList", obj);
		} catch (SQLException e) {
			logger.error("PaasResourceMonitor.queryForList:" + e.getMessage()
					+ e.getClass().getName());
		}
		return list;
	}


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
	@Override
	public List<PaasResourceMonitorObj> queryForSlabList(PaasResourceMonitorObj obj) {
		List<PaasResourceMonitorObj> list = new ArrayList<PaasResourceMonitorObj>();
		try {
			list = getSqlMap().queryForList("PaasResourceMonitor.queryForSlabList", obj);
		} catch (SQLException e) {
			logger.error("PaasResourceMonitor.queryForSlabList:" + e.getMessage()
					+ e.getClass().getName());
		}
		return list;
	}
	
}
