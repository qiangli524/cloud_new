package com.sitech.basd.yicloud.service.device;

import java.util.List;

import com.sitech.basd.yicloud.domain.device.ManageProtocolObj;

public interface ManageProtocolService {
	public int insertByObj(ManageProtocolObj obj);

	public List queryForList(ManageProtocolObj obj);

	public ManageProtocolObj queryByObj(ManageProtocolObj obj);

	public int updateByObj(ManageProtocolObj obj);

	public int deleteByObj(ManageProtocolObj obj);
}
