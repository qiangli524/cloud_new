package com.sitech.basd.sxcloud.rsmu.dao.system;

import java.util.List;

import com.sitech.basd.sxcloud.rsmu.domain.system.TbSysUsergroupObj;

public interface TbSysUsergroupDao {
	/**
	 * @Title:根据用户组部分信息查询匹配的所有用户组信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author zengls
	 * @version 1.0
	 */
	public List queryForListByObj(TbSysUsergroupObj obj);

	/**
	 * @Title:查询出具体用户组信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author zengls
	 * @version 1.0
	 */
	public TbSysUsergroupObj queryByObj(TbSysUsergroupObj obj);

	/**
	 * @Title:更新用户组信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author zengls
	 * @version 1.0
	 */
	public int updateByObj(TbSysUsergroupObj obj);

	/**
	 * @Title:删除用户组信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author zengls
	 * @version 1.0
	 */
	public int deleteByObj(TbSysUsergroupObj obj);

	/**
	 * @Title:插入用户组信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author zengls
	 * @version 1.0
	 */
	public int insertByObj(TbSysUsergroupObj obj);
}
