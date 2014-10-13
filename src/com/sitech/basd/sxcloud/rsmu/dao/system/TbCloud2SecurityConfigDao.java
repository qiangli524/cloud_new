package com.sitech.basd.sxcloud.rsmu.dao.system;

import java.util.List;

import com.sitech.basd.sxcloud.rsmu.domain.system.TbCloud2SecurityConfigObj;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;

public interface TbCloud2SecurityConfigDao {

	/**
	 * 
	 * @Title: 查询配置列表
	 * @Copyright: Copyright (c) 2011-12-20
	 * @Company: si-tech
	 * @author zhangwj
	 * @version 1.0
	 */
	public List<TbCloud2SecurityConfigObj> queryForListByObj(
			TbCloud2SecurityConfigObj obj);

	/**
	 * 
	 * @Title: 查询全局配置表
	 * @Copyright: Copyright (c) 2011-12-20
	 * @Company: si-tech
	 * @author zhangwj
	 * @version 1.0
	 */
	public String queryForObjByObj(String obj);

	/**
	 * 
	 * @Title: 更新安全鉴权配置表
	 * @Copyright: Copyright (c) 2011-12-21
	 * @Company: si-tech
	 * @author zhangwj
	 * @version 1.0
	 */
	public int updateObjByObj(TbCloud2SecurityConfigObj obj);
	
	/**
	 * 
	 * @Title: deleteByObj
	 * @Description: 删除安全配置
	 * @param
	 * @return int
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime 2013-9-3 下午4:47:09
	 */
	public int deleteByObj(TbCloud2SecurityConfigObj obj);
	
	/**
	 * 
	 * @Title: queryForAllList
	 * @Description: 查询安全配置
	 * @param
	 * @return List
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime 2013-9-5 下午12:30:29
	 */
	public List<TbCloud2SecurityConfigObj> queryForAllList(TbCloud2SecurityConfigObj obj);
}
