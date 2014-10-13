package com.sitech.ssd.ah.busiMon.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.sitech.basd.alarm.domain.AlarmThreshold;
import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;
import com.sitech.basd.util.AppContext;
import com.sitech.ssd.ah.busiMon.domain.TbBossBusiMonAlarmCfg;
import com.sitech.ssd.ah.busiMonitor.domain.MonitorCfgObj;
import com.sitech.utils.monitor.bossBusiMon.BossBusiMonConstant;
import com.sitech.utils.rabbitmq.RabbitMQUtil;
import com.sitech.utils.randomid.RandomUUID;

/**
 * <p>Title:BossBusiMonAlarmCfgDaoImpl</p>
 * <p>Description:boss业务监控配置数据库持久化接口实现类</p>
 * <p>Copyright:Copyright (c) 2014</p>
 * <p>Company:SI-TECH </p>
 * @author wangjl_cmi_jl
 * @version 1.0
 * @createtime Sep 11, 2014 7:42:04 PM
 */
@SuppressWarnings("all")
@Repository("bossBusiMonAlarmCfgDao")
public class BossBusiMonAlarmCfgDaoImpl extends BaseDao implements BossBusiMonAlarmCfgDao{
	
	/* (non-Javadoc)
	 * @see com.sitech.ssd.ah.busiMon.dao.BossBusiMonAlarmCfgDao#queryForListByObj(com.sitech.ssd.ah.busiMon.domain.TbBossBusiMonAlarmCfg)
	 */
	public List queryForListByObj(TbBossBusiMonAlarmCfg tbBossBusiMonAlarmCfg) {
		List list = null;
		try {
			if(tbBossBusiMonAlarmCfg.getPagination() != null){
				tbBossBusiMonAlarmCfg.setFIRSTROWNUM(tbBossBusiMonAlarmCfg.getPagination().getFirstRownum());
				tbBossBusiMonAlarmCfg.setPAGESIZE(tbBossBusiMonAlarmCfg.getPagination().getPageSize());
				tbBossBusiMonAlarmCfg.getPagination().setTotalCount(
						((Integer)getSqlMap().queryForObject(
								"BossBusiMonAlarmCfg.queryByObjForCount",tbBossBusiMonAlarmCfg)).intValue());
				list = getSqlMap().queryForList("BossBusiMonAlarmCfg.queryForListByObj",tbBossBusiMonAlarmCfg);
			}
		} catch (Exception sqlexception) {
			sqlexception.printStackTrace();
		}
		return list;
	}
	
	/* (non-Javadoc)
	 * @see com.sitech.ssd.ah.busiMon.dao.BossBusiMonAlarmCfgDao#addBossBusiMonAlarmCfg(com.sitech.ssd.ah.busiMon.domain.TbBossBusiMonAlarmCfg)
	 */
	public int addBossBusiMonAlarmCfg(TbBossBusiMonAlarmCfg tbBossBusiMonAlarmCfg){
		String id = RandomUUID.getUuid();
		tbBossBusiMonAlarmCfg.setID(id);
		int ret = 0;
		try {
			Object ob = getSqlMap().insert("BossBusiMonAlarmCfg.insertByObj",tbBossBusiMonAlarmCfg);
			if(ob != null){
				ret = Integer.parseInt(ob.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("BossBusiMonAlarmCfg.insertByObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return ret;
	}
	
	/* (non-Javadoc)
	 * @see com.sitech.ssd.ah.busiMon.dao.BossBusiMonAlarmCfgDao#updateBossBusiMonAlarmCfgById(com.sitech.ssd.ah.busiMon.domain.TbBossBusiMonAlarmCfg)
	 */
	public void updateBossBusiMonAlarmCfgById(TbBossBusiMonAlarmCfg tbBossBusiMonAlarmCfg){
		try {
			Object ob = getSqlMap().update("BossBusiMonAlarmCfg.update",tbBossBusiMonAlarmCfg);
		} catch (SQLException e) {
			LogHelper.error("BossBusiMonAlarmCfg.update:" + e.getMessage());
		}
	}
	
	/* (non-Javadoc)
	 * @see com.sitech.ssd.ah.busiMon.dao.BossBusiMonAlarmCfgDao#queryListById(com.sitech.ssd.ah.busiMon.domain.TbBossBusiMonAlarmCfg)
	 */
	public List<TbBossBusiMonAlarmCfg> queryListById(TbBossBusiMonAlarmCfg tbBossBusiMonAlarmCfg) {
		List<TbBossBusiMonAlarmCfg> tbBossBusiMonAlarmCfgs = null;
		try {
			tbBossBusiMonAlarmCfgs = getSqlMap().queryForList("BossBusiMonAlarmCfg.queryById",tbBossBusiMonAlarmCfg);
		} catch (Exception sqlexception) {
			LogHelper.error("BossBusiMonAlarmCfg.queryById:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return tbBossBusiMonAlarmCfgs;
	}
	
	/* (non-Javadoc)
	 * @see com.sitech.ssd.ah.busiMon.dao.BossBusiMonAlarmCfgDao#deleteBossBusiMonAlarmCfgById(com.sitech.ssd.ah.busiMon.domain.TbBossBusiMonAlarmCfg)
	 */
	public void deleteBossBusiMonAlarmCfgById(TbBossBusiMonAlarmCfg tbBossBusiMonAlarmCfg){
		int ret = 0;
		try {
			getSqlMap().delete("BossBusiMonAlarmCfg.delete", tbBossBusiMonAlarmCfg);
		} catch (Exception e) {
			ret = -1;
			LogHelper.error("BossBusiMonAlarmCfg.delete:" + e.getMessage());
		}
	}
	
	/* (non-Javadoc)
	 * @see com.sitech.ssd.ah.busiMon.dao.BossBusiMonAlarmCfgDao#openOrCloseBossBusiMon(com.sitech.ssd.ah.busiMon.domain.TbBossBusiMonAlarmCfg)
	 */
	@Override
	public void openOrCloseBossBusiMon(TbBossBusiMonAlarmCfg tbBossBusiMonAlarmCfg) {
		try {
			Object ob = getSqlMap().update("BossBusiMonAlarmCfg.openOrCloseBossBusiMon",tbBossBusiMonAlarmCfg);
		} catch (SQLException e) {
			LogHelper.error("BossBusiMonAlarmCfg.openOrCloseBossBusiMon:" + e.getMessage());
		}
	}

	/* (non-Javadoc)
	 * @see com.sitech.ssd.ah.busiMon.dao.BossBusiMonAlarmCfgDao#checkAlarmCfgIsExist(com.sitech.ssd.ah.busiMon.domain.TbBossBusiMonAlarmCfg)
	 */
	@Override
	public List<TbBossBusiMonAlarmCfg> checkAlarmCfgIsExist(
			TbBossBusiMonAlarmCfg tbBossBusiMonAlarmCfg) throws Exception {
		List<TbBossBusiMonAlarmCfg> tbBossBusiMonAlarmCfgs = null;
		try {
			tbBossBusiMonAlarmCfgs = getSqlMap().queryForList("BossBusiMonAlarmCfg.queryById",tbBossBusiMonAlarmCfg);
		} catch (Exception sqlexception) {
			LogHelper.error("BossBusiMonAlarmCfg.queryById:"+ sqlexception.getMessage() + getClass().getName());
			throw new Exception(sqlexception);
		}
		return tbBossBusiMonAlarmCfgs;
	}

	/* (non-Javadoc)
	 * @see com.sitech.ssd.ah.busiMon.dao.BossBusiMonAlarmCfgDao#queryPathByIpList(java.lang.String, java.lang.String)
	 */
	@Override
	public List<MonitorCfgObj> queryPathByIpList(String hostIp, String dieKpiId)
			throws Exception {
		List<MonitorCfgObj> monitorCfgObjs = null;
		HashMap<String, String> hashMap = new HashMap<String, String>();
		hashMap.put("hostIp", hostIp);
		hashMap.put("dieKpiId", dieKpiId);
		try {
			monitorCfgObjs = getSqlMap().queryForList("BossBusiMonAlarmCfg.queryPathByIp",hashMap);
		} catch (Exception sqlexception) {
			LogHelper.error("BossBusiMonAlarmCfg.queryPathByIp:"+ sqlexception.getMessage() + getClass().getName());
			throw new Exception(sqlexception);
		}
		return monitorCfgObjs;
	}
}
