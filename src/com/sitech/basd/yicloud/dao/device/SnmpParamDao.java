package com.sitech.basd.yicloud.dao.device;

import java.util.List;

import com.sitech.basd.yicloud.domain.device.SnmpParamObj;

public interface SnmpParamDao {
	public int insertByObj(SnmpParamObj obj);

	public List queryForList(SnmpParamObj obj);

	public SnmpParamObj queryByObj(SnmpParamObj obj);

	public int updateByObj(SnmpParamObj obj);

	public int deleteByObj(SnmpParamObj obj);
}
