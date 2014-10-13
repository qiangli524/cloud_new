package com.sitech.basd.resource.dao.host;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.sitech.basd.resource.domain.host.IPMIInfo;
import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.utils.randomid.RandomUUID;

@Repository("ipmiInfoDao")
public class IPMIInfoDaoImpl extends BaseDao implements IPMIInfoDao {
	@Override
	public IPMIInfo getipmiInfo(IPMIInfo ipmi) {
		List<IPMIInfo> list = queryForList(ipmi);
		if (list != null && list.size() > 0) {
			ipmi = list.get(0);
		}
		return ipmi;
	}

	@Override
	public void insertByObj(IPMIInfo ipmi) {
		ipmi.setId(RandomUUID.getUuid());
		getSqlMapClientTemplate().insert("ipmi.insertByObj", ipmi);
	}

	@Override
	public void updateByObj(IPMIInfo ipmi) {
		getSqlMapClientTemplate().update("", ipmi);
	}

	@Override
	public void deleteByObj(IPMIInfo ipmi) {
		getSqlMapClientTemplate().delete("", ipmi);
	}

	@Override
	public List<IPMIInfo> queryForList(IPMIInfo ipmi) {
		if (ipmi.getPagination() != null) {
			ipmi.setFIRSTROWNUM(ipmi.getPagination().getFirstRownum());
			ipmi.setPAGESIZE(ipmi.getPagination().getPageSize());
			ipmi.getPagination().setTotalCount(
					((Integer) getSqlMapClientTemplate().queryForObject("ipmi.queryForCount", ipmi)).intValue());
		}
		List<IPMIInfo> list = getSqlMapClientTemplate().queryForList("ipmi.queryForList", ipmi);
		return list;
	}
}
