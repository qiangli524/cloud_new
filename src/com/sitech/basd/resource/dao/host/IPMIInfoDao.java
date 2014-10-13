package com.sitech.basd.resource.dao.host;

import java.util.List;

import com.sitech.basd.resource.domain.host.IPMIInfo;

public interface IPMIInfoDao {
	public IPMIInfo getipmiInfo(IPMIInfo ipmi);

	public void insertByObj(IPMIInfo ipmi);

	public void updateByObj(IPMIInfo ipmi);

	public void deleteByObj(IPMIInfo ipmi);

	public List<IPMIInfo> queryForList(IPMIInfo ipmi);
}
