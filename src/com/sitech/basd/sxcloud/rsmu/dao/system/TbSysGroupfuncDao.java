package com.sitech.basd.sxcloud.rsmu.dao.system;

import java.util.List;

import com.sitech.basd.sxcloud.rsmu.domain.system.TbSysGroupfuncObj;

public interface TbSysGroupfuncDao {
	/**
	 * @Title:根据部分用户组功能关联信息查询匹配的所有用户组功能关联信息
	 * @Copyright: Copyright (c) 201007
	 * @Company: si-tech
	 * @author zengls
	 * @version 1.0
	 */
	public List queryForListByObj(TbSysGroupfuncObj obj);

	/**
	 * @Title:插入用户组功能关联信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author zengls
	 * @version 1.0
	 */
	public int insertByObj(TbSysGroupfuncObj obj);

	/**
	 * @Title:删除用户组功能关联信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author zengls
	 * @version 1.0
	 */
	public int deleteByObj(TbSysGroupfuncObj obj);

	/**
	 * @Title: queryForListUseIn
	 * @Description: 根据传入的属性集合查询符合条件的集合
	 * @param
	 * @return List<TbSysGroupfuncObj>
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-9-26 下午3:12:20
	 */
	public List<TbSysGroupfuncObj> queryForListUseIn(TbSysGroupfuncObj funcObj);
}
