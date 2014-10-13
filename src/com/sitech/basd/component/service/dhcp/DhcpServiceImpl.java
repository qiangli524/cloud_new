package com.sitech.basd.component.service.dhcp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sitech.basd.component.dao.dhcp.DhcpDao;
import com.sitech.basd.component.domain.dhcp.DhcpObj;

@Service("dhcpService")
public class DhcpServiceImpl implements DhcpService{

	@Autowired
	private DhcpDao dhcpDao;
	
	@Override
	public int insertDhcp(DhcpObj dhcpObj) {
		return dhcpDao.insertDhcp(dhcpObj);
	}

	@Override
	public int deleteDhcp(DhcpObj dhcpObj) {
		return dhcpDao.deleteDhcp(dhcpObj);
	}

	@Override
	public int updateDhcp(DhcpObj dhcpObj) {
		return dhcpDao.updateDhcp(dhcpObj);
	}

	@Override
	public List<DhcpObj> queryForList(DhcpObj dhcpObj) {
		return dhcpDao.queryForList(dhcpObj);
	}

	@Override
	public int countDhcp(DhcpObj dhcpObj) {
		return dhcpDao.countDhcp(dhcpObj);
	}

}
