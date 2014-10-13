package com.sitech.basd.yicloud.dao.device;

import java.util.List;

import com.sitech.basd.yicloud.domain.device.ManageProtocolObj;

public interface ManageProtocolDao {
	public int insertByObj(ManageProtocolObj obj);

	public List queryForList(ManageProtocolObj obj);

	public ManageProtocolObj queryByObj(ManageProtocolObj obj);

	public int updateByObj(ManageProtocolObj obj);

	public int deleteByObj(ManageProtocolObj obj);
}
