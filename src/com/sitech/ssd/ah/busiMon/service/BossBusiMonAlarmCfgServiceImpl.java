package com.sitech.ssd.ah.busiMon.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sitech.basd.alarm.dao.AlarmThresholdDao;
import com.sitech.basd.alarm.domain.AlarmThreshold;
import com.sitech.basd.sxcloud.rsmu.service.BaseService;
import com.sitech.ssd.ah.busiMon.dao.BossBusiMonAlarmCfgDao;
import com.sitech.ssd.ah.busiMon.domain.TbBossBusiMonAlarmCfg;
import com.sitech.ssd.ah.busiMonitor.domain.MonitorCfgObj;

/**
 * <p>Title:BossBusiMonAlarmCfgServiceImpl</p>
 * <p>Description:boss业务监控报警配置服务接口实现类</p>
 * <p>Copyright:Copyright (c) 2014</p>
 * <p>Company:SI-TECH </p>
 * @author wangjl_cmi_jl
 * @version 1.0
 * @createtime Sep 11, 2014 7:51:09 PM
 */
@SuppressWarnings("all")
@Service("bossBusiMonAlarmCfgService")
public class BossBusiMonAlarmCfgServiceImpl extends BaseService implements BossBusiMonAlarmCfgService{
	
	@Autowired
	private BossBusiMonAlarmCfgDao bossBusiMonAlarmCfgDao;
	
	/* (non-Javadoc)
	 * @see com.sitech.ssd.ah.busiMon.service.BossBusiMonAlarmCfgService#queryForListByObj(com.sitech.ssd.ah.busiMon.domain.TbBossBusiMonAlarmCfg)
	 */
	public List queryForListByObj(TbBossBusiMonAlarmCfg tbBossBusiMonAlarmCfg) {
		List queryForListByObj = bossBusiMonAlarmCfgDao.queryForListByObj(tbBossBusiMonAlarmCfg);
		for (Iterator iterator = queryForListByObj.iterator(); iterator
				.hasNext();) {
			TbBossBusiMonAlarmCfg name = (TbBossBusiMonAlarmCfg) iterator.next();
			
		}
		return queryForListByObj;
	}
	
	/* (non-Javadoc)
	 * @see com.sitech.ssd.ah.busiMon.service.BossBusiMonAlarmCfgService#addBossBusiMonAlarmCfg(com.sitech.ssd.ah.busiMon.domain.TbBossBusiMonAlarmCfg)
	 */
	public int addBossBusiMonAlarmCfg(TbBossBusiMonAlarmCfg tbBossBusiMonAlarmCfg){
		return bossBusiMonAlarmCfgDao.addBossBusiMonAlarmCfg(tbBossBusiMonAlarmCfg);
	}
	
	/* (non-Javadoc)
	 * @see com.sitech.ssd.ah.busiMon.service.BossBusiMonAlarmCfgService#updateBossBusiMonAlarmCfgById(com.sitech.ssd.ah.busiMon.domain.TbBossBusiMonAlarmCfg)
	 */
	public void updateBossBusiMonAlarmCfgById(TbBossBusiMonAlarmCfg tbBossBusiMonAlarmCfg) {
		bossBusiMonAlarmCfgDao.updateBossBusiMonAlarmCfgById(tbBossBusiMonAlarmCfg);
	};
	
	
	/* (non-Javadoc)
	 * @see com.sitech.ssd.ah.busiMon.service.BossBusiMonAlarmCfgService#queryBossBusiMonAlarmCfgById(com.sitech.ssd.ah.busiMon.domain.TbBossBusiMonAlarmCfg)
	 */
	public List<TbBossBusiMonAlarmCfg> queryBossBusiMonAlarmCfgById(TbBossBusiMonAlarmCfg tbBossBusiMonAlarmCfg){
		return bossBusiMonAlarmCfgDao.queryListById(tbBossBusiMonAlarmCfg);
	}
	
	/* (non-Javadoc)
	 * @see com.sitech.ssd.ah.busiMon.service.BossBusiMonAlarmCfgService#deleteBossBusiMonAlarmCfg(com.sitech.ssd.ah.busiMon.domain.TbBossBusiMonAlarmCfg)
	 */
	public void deleteBossBusiMonAlarmCfg(TbBossBusiMonAlarmCfg tbBossBusiMonAlarmCfg){
		//如果id不唯一时，则为批量删除
		String[] arr = tbBossBusiMonAlarmCfg.getID().split(",");
		if(arr.length >= 1){
			List<String> resultList = new ArrayList<String>();
			for (String str : arr) {
				resultList.add(str);
			}
			tbBossBusiMonAlarmCfg.setResultList(resultList);
		}
		bossBusiMonAlarmCfgDao.deleteBossBusiMonAlarmCfgById(tbBossBusiMonAlarmCfg);
	}
	
	/* (non-Javadoc)
	 * @see com.sitech.ssd.ah.busiMon.service.BossBusiMonAlarmCfgService#openOrCloseBossBusiMon(com.sitech.ssd.ah.busiMon.domain.TbBossBusiMonAlarmCfg)
	 */
	@Override
	public void openOrCloseBossBusiMon(TbBossBusiMonAlarmCfg tbBossBusiMonAlarmCfg) {
		bossBusiMonAlarmCfgDao.openOrCloseBossBusiMon(tbBossBusiMonAlarmCfg);
	}

	/* (non-Javadoc)
	 * @see com.sitech.ssd.ah.busiMon.service.BossBusiMonAlarmCfgService#checkAlarmCfgIsExist(com.sitech.ssd.ah.busiMon.domain.TbBossBusiMonAlarmCfg)
	 */
	@Override
	public List<TbBossBusiMonAlarmCfg> checkAlarmCfgIsExist(
			TbBossBusiMonAlarmCfg tbBossBusiMonAlarmCfg) throws Exception{
		return bossBusiMonAlarmCfgDao.checkAlarmCfgIsExist(tbBossBusiMonAlarmCfg);
	}

	@Override
	public List<MonitorCfgObj> queryPathByIpList(String hostIp, String dieKpiId)
			throws Exception {
		return bossBusiMonAlarmCfgDao.queryPathByIpList(hostIp,dieKpiId);
	}
}
