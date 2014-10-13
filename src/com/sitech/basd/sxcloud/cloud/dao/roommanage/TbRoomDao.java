package com.sitech.basd.sxcloud.cloud.dao.roommanage;

import java.util.List;

import com.sitech.basd.sxcloud.cloud.domain.roommanage.TbRoomObj;

public interface TbRoomDao {
	/**
	 * @Title:根据主机部分信息查询匹配的所有主机信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author zengls
	 * @version 1.0
	 */
	public List queryForCubSelect(TbRoomObj obj);

}
