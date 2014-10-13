package com.sitech.basd.sxcloud.rsmu.dao.upgrade;

import java.util.List;

import com.sitech.basd.sxcloud.rsmu.domain.upgrade.UpgradeAlarmObj;

public interface UpgradeAlarmDao {

	/**
	 * @Title:根据真实服务器部分信息查询匹配的所有真实服务器信息
	 * @Copyright: Copyright (c) 201007
	 * @Company: si-tech
	 * @author chenjl
	 * @version 1.0
	 */
	public List queryForListByObj(UpgradeAlarmObj obj);;
}
