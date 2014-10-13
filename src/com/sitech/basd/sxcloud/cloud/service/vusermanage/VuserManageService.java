package com.sitech.basd.sxcloud.cloud.service.vusermanage;

import java.util.List;

import com.sitech.basd.sxcloud.cloud.domain.vusermanage.VuserManageObj;
import com.sitech.basd.sxcloud.rsmu.domain.system.TbSysUserinfoObj;

public interface VuserManageService {

	/**
	 * @Title:查询V用户信息
	 * @Copyright: Copyright (c) 201112
	 * @Company: si-tech
	 * @author wangjian
	 * @version 1.0
	 */
	@SuppressWarnings("unchecked")
	public List queryForListByObj(VuserManageObj obj);

	/**
	 * @Title:查询出具体V用户信息
	 * @Copyright: Copyright (c) 201112
	 * @Company: si-tech
	 * @author wangjian
	 * @version 1.0
	 */
	public VuserManageObj queryByObj(VuserManageObj obj);

	/**
	 * @Title:更新V用户信息
	 * @Copyright: Copyright (c) 201112
	 * @Company: si-tech
	 * @author wangjian
	 * @version 1.0
	 */
	public int updateByObj(VuserManageObj obj);

	/**
	 * @Title:删除V用户信息
	 * @Copyright: Copyright (c) 201112
	 * @Company: si-tech
	 * @author wangjian
	 * @version 1.0
	 */
	public int deleteByObj(VuserManageObj obj);

	/**
	 * @Title:插入V用户信息
	 * @Copyright: Copyright (c) 201112
	 * @Company: si-tech
	 * @author wangjian
	 * @version 1.0
	 */
	public int insertByObj(VuserManageObj obj);;

	/**
	 * @Title:根据用户ID查询V用户信息
	 * @Copyright: Copyright (c) 201112
	 * @Company: si-tech
	 * @author wangjian
	 * @version 1.0
	 */
	@SuppressWarnings("unchecked")
	public List queryForListByUserObj(TbSysUserinfoObj obj);

}
