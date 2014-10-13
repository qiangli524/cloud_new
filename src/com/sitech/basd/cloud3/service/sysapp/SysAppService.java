package com.sitech.basd.cloud3.service.sysapp;

import java.util.List;

import com.sitech.basd.cloud3.domain.sysapp.SysAppObj;

public interface SysAppService {

	/**
	 * @Title: queryAllAttach
	 * @Description: 查询所有未配置的应用
	 * @param
	 * @return List<SysAppObj>
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-8-28 上午9:35:11
	 */
	public List<SysAppObj> queryAllAttach();

	/**
	 * @Title: queryOne
	 * @Description: 查询一条记录
	 * @param
	 * @return SysAppObj
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-8-28 上午9:35:34
	 */
	public SysAppObj queryOne(SysAppObj sysAppObj);

	/**
	 * @Title: querySysAppConfiged
	 * @Description: 查询已配置的应用
	 * @param
	 * @return List<SysAppObj>
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-8-28 上午11:38:46
	 */
	public List<SysAppObj> querySysAppConfiged();
}
