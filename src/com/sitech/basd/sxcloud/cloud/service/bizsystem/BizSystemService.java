package com.sitech.basd.sxcloud.cloud.service.bizsystem;

import java.util.List;

import com.sitech.basd.sxcloud.cloud.domain.bizsystem.BizSystemObj;
import com.sitech.basd.sxcloud.cloud.domain.bizsystem.DomainObj;

public interface BizSystemService {
	/**
	 * @Title:根据部分应用信息查询匹配的所有应用信息
	 * @Copyright: Copyright (c) 201208
	 * @Company: si-tech
	 * @version 1.0
	 */
	public List queryForListByObj(BizSystemObj obj);

	/**
	 * @Title:插入应用信息
	 * @Copyright: Copyright (c) 201208
	 * @Company: si-tech
	 * @version 1.0
	 */
	public String insertByObj(BizSystemObj obj);

	/**
	 * @Title:更新应用信息
	 * @Copyright: Copyright (c) 201208
	 * @Company: si-tech
	 * @version 1.0
	 */
	public int updateByObj(BizSystemObj obj);

	/**
	 * @Title:删除应用信息
	 * @Copyright: Copyright (c) 201208
	 * @Company: si-tech
	 * @version 1.0
	 */
	public int deleteByObj(BizSystemObj obj);

	/**
	 * @Title:查询地区信息列表
	 * @Copyright: Copyright (c) 201208
	 * @Company: si-tech
	 * @version 1.0
	 */
	public List queryForListByDomainObj(DomainObj obj);

	/**
	 * @Title:修改应用信息
	 * @Copyright: Copyright (c) 201208
	 * @Company: si-tech
	 * @version 1.0
	 */
	public BizSystemObj queryByObj(BizSystemObj obj);

	/*
	 * 查询业务系统下的信息，包括基准应用，部署实例个数
	 */
	public List busiSystemInfo(BizSystemObj obj);
}
