package com.sitech.basd.yicloud.service.device;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sitech.basd.yicloud.dao.device.SnmpParamDao;
import com.sitech.basd.yicloud.domain.device.SnmpParamObj;

@Service("snmpParamService")
public class SnmpParamServiceImpl implements SnmpParamService {
	@Autowired
	private SnmpParamDao snmpParamDao;

	public int insertByObj(SnmpParamObj obj) {
		return snmpParamDao.insertByObj(obj);
	}

	public List queryForList(SnmpParamObj obj) {
		return snmpParamDao.queryForList(obj);
	}

	public SnmpParamObj queryByObj(SnmpParamObj obj) {
		return snmpParamDao.queryByObj(obj);
	}

	public int updateByObj(SnmpParamObj obj) {
		return snmpParamDao.updateByObj(obj);
	}

	public int deleteByObj(SnmpParamObj obj) {
		return snmpParamDao.deleteByObj(obj);
	}
}
