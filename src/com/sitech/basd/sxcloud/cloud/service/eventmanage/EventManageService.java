package com.sitech.basd.sxcloud.cloud.service.eventmanage;

import java.util.List;

import com.sitech.basd.sxcloud.cloud.domain.eventmanage.EventManageObj;

public interface EventManageService {

	/**
	 * @Title:查询事件信息
	 * @Copyright: Copyright (c) 201112
	 * @Company: si-tech
	 * @author wangjian
	 * @version 1.0
	 */
	@SuppressWarnings("unchecked")
	public List queryForListByObj(EventManageObj obj);

	/**
	 * @Title:查询出具体事件信息
	 * @Copyright: Copyright (c) 201112
	 * @Company: si-tech
	 * @author wangjian
	 * @version 1.0
	 */
	public EventManageObj queryByObj(EventManageObj obj);
}
