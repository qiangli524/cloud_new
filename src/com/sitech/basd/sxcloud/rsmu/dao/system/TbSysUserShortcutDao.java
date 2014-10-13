package com.sitech.basd.sxcloud.rsmu.dao.system;

import java.util.List;

import com.sitech.basd.sxcloud.rsmu.domain.system.TbSysUserShortcutObj;
import com.sitech.basd.sxcloud.rsmu.domain.system.TempSysUserShortctObj;

public interface TbSysUserShortcutDao {
	/**
	 * @Title:插入收藏功能
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author zengls
	 * @version 1.0
	 */
	public int insertByObj(TbSysUserShortcutObj obj);

	/**
	 * @Title:根据账号ID查出所有收藏菜单
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author zengls
	 * @version 1.0
	 */
	public List findFavoriteList(int id);

	/**
	 * @Title:删除收藏菜单
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author zengls
	 * @version 1.0
	 */
	public int deleteForTbSysUserShortcut(TempSysUserShortctObj obj);
}
