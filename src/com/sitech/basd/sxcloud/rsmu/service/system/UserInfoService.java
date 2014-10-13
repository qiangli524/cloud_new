package com.sitech.basd.sxcloud.rsmu.service.system;

import java.util.List;

import com.sitech.basd.sxcloud.rsmu.domain.system.TbSysUserinfoObj;

public interface UserInfoService {
	/**
	 * @Title:����˻�������Ϣ��ѯƥ��������˻���Ϣ
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author zengls
	 * @version 1.0
	 */
	public List queryForListByObj(TbSysUserinfoObj obj);
	/**
	 * @Title:模糊查询获取账户列表
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author zengls
	 * @version 1.0
	 */
	public List queryLikeForListByObj(TbSysUserinfoObj obj);
	/**
	 * @Title:��ѯ�������˻���Ϣ
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author zengls
	 * @version 1.0
	 */
	public TbSysUserinfoObj queryByObj(TbSysUserinfoObj obj);

	/**
	 * @Title:�����˻���Ϣ
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author zengls
	 * @version 1.0
	 */
	public int updateByObj(TbSysUserinfoObj obj);

	/**
	 * @Title:ɾ���˻���Ϣ
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author zengls
	 * @version 1.0
	 */
	public int deleteByObj(TbSysUserinfoObj obj);

	/**
	 * @Title:�����˻���Ϣ
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author zengls
	 * @version 1.0
	 */
	public int insertByObj(TbSysUserinfoObj obj);

	/**
	 * @Title:�����˻����Ȩ����Ϣ
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author zengls
	 * @version 1.0
	 */
	public int updateForDATAAUTHORITYByObj(TbSysUserinfoObj obj);
	
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
	public List<TbSysUserinfoObj> queryForUserByObj(TbSysUserinfoObj userObj);
}
