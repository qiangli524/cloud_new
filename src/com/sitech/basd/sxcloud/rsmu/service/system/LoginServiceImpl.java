package com.sitech.basd.sxcloud.rsmu.service.system;

import java.util.List;

import com.sitech.basd.sxcloud.rsmu.dao.system.TbSysFunctionsDao;
import com.sitech.basd.sxcloud.rsmu.dao.system.TbSysLoginLogDao;
import com.sitech.basd.sxcloud.rsmu.dao.system.TbSysUserShortcutDao;
import com.sitech.basd.sxcloud.rsmu.dao.system.TbSysUserinfoDao;
import com.sitech.basd.sxcloud.rsmu.domain.system.TbSysLoginLogObj;
import com.sitech.basd.sxcloud.rsmu.domain.system.TbSysUserShortcutObj;
import com.sitech.basd.sxcloud.rsmu.domain.system.TbSysUserinfoObj;
import com.sitech.basd.sxcloud.rsmu.domain.system.TempSysUserShortctObj;
import com.sitech.basd.sxcloud.rsmu.service.BaseService;

public class LoginServiceImpl extends BaseService implements LoginService {
	/**
	 * @Title:根据模块ID集查询所有补充信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author zengls
	 * @version 1.0
	 */
	public List getNaviParam(List getNaviParam) {
		return tbSysFunctionsDao.getNaviParam(getNaviParam);
	}

	/**
	 * @Title:插入收藏功能
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author zengls
	 * @version 1.0
	 */
	public int insertByObj(TbSysUserShortcutObj obj) {
		return tbSysUserShortcutDao.insertByObj(obj);
	}

	/**
	 * @Title:根据账号ID查出所有收藏菜单
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author zengls
	 * @version 1.0
	 */
	public List findFavoriteList(int id) {
		return tbSysUserShortcutDao.findFavoriteList(id);
	}

	/**
	 * @Title:删除收藏菜单
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author zengls
	 * @version 1.0
	 */
	public int deleteForTbSysUserShortcut(TempSysUserShortctObj obj) {
		return tbSysUserShortcutDao.deleteForTbSysUserShortcut(obj);
	}

	/**
	 * @Title:查询出具体账户信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author zengls
	 * @version 1.0
	 */
	public TbSysUserinfoObj queryByObj(TbSysUserinfoObj obj) {
		return tbSysUserinfoDao.queryByObj(obj);
	}

	/**
	 * @Title:插入登录日志
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author zengls
	 * @version 1.0
	 */
	public int insertByObj(TbSysLoginLogObj obj) {
		return tbSysLoginLogDao.insertByObj(obj);
	}

	/**
	 * @Title:更新登录日志时间
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author zengls
	 * @version 1.0
	 */

	public int updateByObj(TbSysLoginLogObj obj) {
		return tbSysLoginLogDao.updateByObj(obj);
	}

	private TbSysFunctionsDao tbSysFunctionsDao;
	private TbSysUserShortcutDao tbSysUserShortcutDao;
	private TbSysLoginLogDao tbSysLoginLogDao;
	private TbSysUserinfoDao tbSysUserinfoDao;

	public void setTbSysUserinfoDao(TbSysUserinfoDao tbSysUserinfoDao) {
		this.tbSysUserinfoDao = tbSysUserinfoDao;
	}

	public void setTbSysFunctionsDao(TbSysFunctionsDao tbSysFunctionsDao) {
		this.tbSysFunctionsDao = tbSysFunctionsDao;
	}

	public void setTbSysLoginLogDao(TbSysLoginLogDao tbSysLoginLogDao) {
		this.tbSysLoginLogDao = tbSysLoginLogDao;
	}

	public void setTbSysUserShortcutDao(
			TbSysUserShortcutDao tbSysUserShortcutDao) {
		this.tbSysUserShortcutDao = tbSysUserShortcutDao;
	}

	/**
	 * 
	 * @Title: 通过用户id查询用户所在的用户组leader属性
	 * @Copyright: Copyright (c) 2012-2-22
	 * @Company: si-tech
	 * @author zhangwj
	 * @version 1.0
	 */
	public String queryLeaderOrNotByUid(int uid) {
		return tbSysUserinfoDao.queryLeaderOrNotByUid(uid);
	}
}
