package com.sitech.basd.sxcloud.rsmu.dao.system;

import java.util.List;

import com.sitech.basd.sxcloud.rsmu.domain.system.TbSysUserinfoObj;

public interface TbSysUserinfoDao {	 
	/**
     * @Title:����˻�������Ϣ��ѯƥ��������˻���Ϣ
     * @Copyright: Copyright (c) 201006
     * @Company: si-tech
     * @author zengls
     * @version 1.0
    */
	public List queryForListByObj(TbSysUserinfoObj obj);
	
	/**
     * @Title:模糊查询账号列表
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
     * 
     * @Title: ͨ���û�id��ѯ�û����ڵ��û���leader����
     * @Copyright: Copyright (c) 2012-2-22
     * @Company: si-tech
     * @author zhangwj
     * @version 1.0
     */
	public String queryLeaderOrNotByUid(int uid);

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

