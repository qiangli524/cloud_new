package com.sitech.basd.sxcloud.cloud.dao.bizsystem;

import java.util.List;

import com.sitech.basd.sxcloud.cloud.domain.bizsystem.BizSystemObj;
import com.sitech.basd.sxcloud.cloud.domain.bizsystem.DomainObj;

public interface BizSystemDao {

	public List queryForListByObj(BizSystemObj obj);

	public String insertByObj(BizSystemObj obj);

	public int updateByObj(BizSystemObj obj);

	public List queryForListByDomainObj(DomainObj obj);

	public int deleteByObj(BizSystemObj obj);

	public BizSystemObj queryByObj(BizSystemObj obj);

	/*
	 * 查询业务系统下的信息，包括基准应用，部署实例个数
	 */
	public List busiSystemInfo(BizSystemObj obj);
}
