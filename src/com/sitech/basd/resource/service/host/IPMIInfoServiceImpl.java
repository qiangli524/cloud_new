package com.sitech.basd.resource.service.host;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.sitech.basd.resource.dao.host.IPMIInfoDao;
import com.sitech.basd.resource.domain.host.IPMIInfo;
import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;

@Repository("ipmiInfoService")
public class IPMIInfoServiceImpl extends BaseDao implements IPMIInfoService {
	@Resource
	private IPMIInfoDao ipmiInfoDao;

	@Override
	public IPMIInfo getipmiInfo(IPMIInfo ipmi) {
		return ipmiInfoDao.getipmiInfo(ipmi);
	}

	@Override
	public void insertByObj(IPMIInfo ipmi) {
		ipmiInfoDao.insertByObj(ipmi);
	}

	@Override
	public void updateByObj(IPMIInfo ipmi) {
		ipmiInfoDao.updateByObj(ipmi);
	}

	@Override
	public void deleteByObj(IPMIInfo ipmi) {
		ipmiInfoDao.deleteByObj(ipmi);
	}

	@Override
	public List<IPMIInfo> queryForList(IPMIInfo ipmi) {
		return ipmiInfoDao.queryForList(ipmi);
	}
}
