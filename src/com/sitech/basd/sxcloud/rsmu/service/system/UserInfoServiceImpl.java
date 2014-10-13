package com.sitech.basd.sxcloud.rsmu.service.system;

import java.util.List;

import com.sitech.basd.sxcloud.rsmu.dao.system.TbSysUserinfoDao;
import com.sitech.basd.sxcloud.rsmu.domain.system.TbSysUserinfoObj;
import com.sitech.basd.sxcloud.rsmu.service.BaseService;

public class UserInfoServiceImpl extends BaseService implements UserInfoService {
	private TbSysUserinfoDao tbSysUserinfoDao;

	public void setTbSysUserinfoDao(TbSysUserinfoDao tbSysUserinfoDao) {
		this.tbSysUserinfoDao = tbSysUserinfoDao;
	}

	/**
	 * @Title:����˻�������Ϣ��ѯƥ��������˻���Ϣ
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author zengls
	 * @version 1.0
	 */
	public List queryForListByObj(TbSysUserinfoObj obj) {
		return tbSysUserinfoDao.queryForListByObj(obj);
	}

	/**
	 * @Title:����˻�������Ϣ��ѯƥ��������˻���Ϣ
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author zengls
	 * @version 1.0
	 */
	public List queryLikeForListByObj(TbSysUserinfoObj obj) {
		return tbSysUserinfoDao.queryLikeForListByObj(obj);
	}

	/**
	 * @Title:��ѯ�������˻���Ϣ
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author zengls
	 * @version 1.0
	 */
	public TbSysUserinfoObj queryByObj(TbSysUserinfoObj obj) {
		return tbSysUserinfoDao.queryByObj(obj);
	}

	/**
	 * @Title:�����˻���Ϣ
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author zengls
	 * @version 1.0
	 */
	public int updateByObj(TbSysUserinfoObj obj) {
		return tbSysUserinfoDao.updateByObj(obj);
	}

	/**
	 * @Title:ɾ���˻���Ϣ
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author zengls
	 * @version 1.0
	 */
	public int deleteByObj(TbSysUserinfoObj obj) {
		return tbSysUserinfoDao.deleteByObj(obj);
	}

	/**
	 * @Title:�����˻���Ϣ
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author zengls
	 * @version 1.0
	 */
	public int insertByObj(TbSysUserinfoObj obj) {
		return tbSysUserinfoDao.insertByObj(obj);
	}

	/**
	 * @Title:�����˻����Ȩ����Ϣ
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author zengls
	 * @version 1.0
	 */
	public int updateForDATAAUTHORITYByObj(TbSysUserinfoObj obj) {
		return tbSysUserinfoDao.updateForDATAAUTHORITYByObj(obj);
	}

	/**
	 * @Title: queryForUserByObj
	 * @Description: 查询用户信息
	 * @param
	 * @return TbSysUserinfoObj
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-9-26 下午2:52:07
	 */
	@Override
	public List<TbSysUserinfoObj> queryForUserByObj(TbSysUserinfoObj userObj) {
		return tbSysUserinfoDao.queryForUserByObj(userObj);
	}
}
