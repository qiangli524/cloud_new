package com.sitech.basd.sxcloud.rsmu.dao.system;

import java.util.List;

import com.sitech.basd.sxcloud.rsmu.domain.system.TbSysGrpmemberObj;

public interface TbSysGrpmemberDao {
	/**
	 * @Title:查询出所有的组成员
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author zengls
	 * @version 1.0
	 */
	public List queryForListByObj(TbSysGrpmemberObj obj);

	/**
	 * @Title:删除用户组成员
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author zengls
	 * @version 1.0
	 */
	public int deleteByObj(TbSysGrpmemberObj obj);
	/**
	 * @Title:删除用户组成员 通过userID
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author zengls
	 * @version 1.0
	 */
	public int deleteUserByObj(TbSysGrpmemberObj obj);
	/**
	 * @Title:插入组成员
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author zengls
	 * @version 1.0
	 */
	public int insertByObj(TbSysGrpmemberObj obj);
}
