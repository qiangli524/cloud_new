package com.sitech.basd.sxcloud.rsmu.dao.system;

import java.util.List;

import com.sitech.basd.sxcloud.rsmu.domain.system.TbSysLoginLogObj;

public interface TbSysLoginLogDao {
	/**
	 * @Title:获得登录日志列表
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author zengls
	 * @version 1.0
	 */
	public List queryForListByObj(TbSysLoginLogObj obj);

	/**
	 * @Title:插入登录日志
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author zengls
	 * @version 1.0
	 */
	public int insertByObj(TbSysLoginLogObj obj);

	/**
	 * @Title:更新登录日志时间
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author zengls
	 * @version 1.0
	 */
	public int updateByObj(TbSysLoginLogObj obj);
}
