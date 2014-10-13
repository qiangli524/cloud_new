package com.sitech.basd.resource.service.host;

import java.util.List;

import com.sitech.basd.resource.domain.host.IPMIInfo;

public interface IPMIInfoService {
	public List<IPMIInfo> queryForList(IPMIInfo ipmi);

	public IPMIInfo getipmiInfo(IPMIInfo ipmi);

	public void insertByObj(IPMIInfo ipmi);

	public void updateByObj(IPMIInfo ipmi);

	public void deleteByObj(IPMIInfo ipmi);
}
