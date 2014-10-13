package com.sitech.ssd.ah.boss.service.monitor;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sitech.ssd.ah.boss.dao.monitor.BossProcessMonitorDao;
import com.sitech.ssd.ah.boss.domain.common.CommonObj;
import com.sitech.ssd.ah.boss.domain.monitor.BossMonitorObj;

/**
 * <p>
 * Title: BossProcessMonitorServiceImpl
 * </p>
 * <p>
 * Description: boss进程监控服务实现类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2014
 * </p>
 * <p>
 * Company: SI-TECH
 * </p>
 * 
 * @author qism
 * @version 1.0
 * @createtime 2014-8-4 下午4:50:03
 * 
 */
@Service("bossProcessMonitorService")
public class BossProcessMonitorServiceImpl implements BossProcessMonitorService {
	@Autowired
	BossProcessMonitorDao bossProcessMonitorDao;

	@Override
	public List<BossMonitorObj> queryMonitorObjList(BossMonitorObj obj) {
		String clus = obj.getCluster_id();
		String pools = obj.getApp_pool();
		String ips = obj.getHost_ip();
		if (!"".equals(clus) && clus != null) {
			List<String> cluList = new ArrayList<String>();
			String[] cluArr = clus.split(",");
			for (String clu : cluArr) {
				cluList.add(clu);
			}
			obj.setCluster_ids(cluList);
		}
		if (!"".equals(pools) && pools != null) {
			List<String> poolList = new ArrayList<String>();
			String[] poolArr = pools.split(",");
			for (String pool : poolArr) {
				poolList.add(pool);
			}
			obj.setApp_pools(poolList);
		}
		if (!"".equals(ips) && ips != null) {
			List<String> ipList = new ArrayList<String>();
			String[] ipArr = ips.split(",");
			for (String ip : ipArr) {
				ipList.add(ip);
			}
			obj.setHost_ips(ipList);
		}
		return bossProcessMonitorDao.queryMonitorObjList(obj);
	}

	@Override
	public void updateMonitorObj(BossMonitorObj obj) {
		String[] cluArr = obj.getCluster_id().split(",");
		String[] pNameArr = obj.getProgram_name().split(",");
		int i = 0;
		for (String cluster_id : cluArr) {
			String pName = pNameArr[i];
			BossMonitorObj mObj = new BossMonitorObj();
			mObj.setCluster_id(cluster_id);
			mObj.setProgram_name(pName);
			mObj.setOperator_id(obj.getOperator_id());
			bossProcessMonitorDao.updateMonitorObj(mObj);
			i++;
		}
	}

	@Override
	public List<BossMonitorObj> queryMonitorObjListByObj(BossMonitorObj obj) {
		List<BossMonitorObj> objList = new ArrayList<BossMonitorObj>();
		String[] cluArr = obj.getCluster_id().split(",");
		String[] pNameArr = obj.getProgram_name().split(",");
		int i = 0;
		for (String clu : cluArr) {
			String pName = pNameArr[i];
			BossMonitorObj oo = new BossMonitorObj();
			oo.setCluster_id(clu);
			oo.setProgram_name(pName);
			oo = bossProcessMonitorDao.queryMonitorObj(oo);
			objList.add(oo);
			i++;
		}
		return objList;
	}

	@Override
	public List<CommonObj> queryCommonObjList(CommonObj obj) {
		return bossProcessMonitorDao.queryCommonObjList(obj);
	}
}
