package com.sitech.basd.sxcloud.rsmu.service.system;

import java.util.List;

import com.sitech.basd.sxcloud.rsmu.domain.system.TbSysLoginLogObj;
import com.sitech.basd.sxcloud.rsmu.domain.system.TbSysUserShortcutObj;
import com.sitech.basd.sxcloud.rsmu.domain.system.TbSysUserinfoObj;
import com.sitech.basd.sxcloud.rsmu.domain.system.TempSysUserShortctObj;

public interface LoginService {

	/**
	 * @Title:根据模块ID集查询所有补充信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author zengls
	 * @version 1.0
	 */
	public List getNaviParam(List getNaviParam);

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

	/**
	 * @Title:查询出具体账户信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author zengls
	 * @version 1.0
	 */
	public TbSysUserinfoObj queryByObj(TbSysUserinfoObj obj);

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

	/**
	 * 
	 * @Title: 通过用户id查询用户所在的用户组leader属性
	 * @Copyright: Copyright (c) 2012-2-22
	 * @Company: si-tech
	 * @author zhangwj
	 * @version 1.0
	 */
	public String queryLeaderOrNotByUid(int uid);
}
