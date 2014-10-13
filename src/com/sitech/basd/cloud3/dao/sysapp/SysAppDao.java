package com.sitech.basd.cloud3.dao.sysapp;

import java.util.List;

import com.sitech.basd.cloud3.domain.sysapp.SysAppObj;

/**
 * <p>Title: SysAppDao</p>
 * <p>Description: 基准应用持久层接口
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: SI-TECH </p>
 * @author lipengpeng
 * @version 1.0
 * @createtime 2013-8-28 上午9:37:34
 *
 */
public interface SysAppDao {

	/**
	 * @Title: queryAllAttach
	 * @Description: 查询所有未配置的应用
	 * @param
	 * @return List<SysAppObj>
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-8-28 上午9:38:01
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
	 * @createtime 2013-8-28 上午9:38:04
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
	 * @createtime 2013-8-28 上午11:39:31
	 */
	public List<SysAppObj> querySysAppConfiged();

}
