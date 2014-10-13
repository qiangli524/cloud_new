package com.sitech.basd.sxcloud.cloud.service.project;

import java.util.List;

import com.sitech.basd.sxcloud.cloud.domain.project.TbCloud2ProjectInfoObj;
import com.sitech.basd.sxcloud.cloud.domain.vusermanage.VuserManageObj;

public interface TbProjectService {

	/**
	 * @Title:查询已有项目列表
	 * @Copyright: Copyright (c) 2011-12-2
	 * @Company: si-tech
	 * @author liys
	 * @version 1.0
	 */

	@SuppressWarnings("unchecked")
	public List<TbCloud2ProjectInfoObj> queryForListByObj(
			TbCloud2ProjectInfoObj obj);

	/**
	 * @Title:查询并返回一个项目对象
	 * @Copyright: Copyright (c) 2011-12-2
	 * @Company: si-tech
	 * @author liys
	 * @version 1.0
	 */

	public TbCloud2ProjectInfoObj queryByObj(TbCloud2ProjectInfoObj obj);

	/**
	 * @Title:更新项目信息
	 * @Copyright: Copyright (c) 2011-12-2
	 * @Company: si-tech
	 * @author liys
	 * @version 1.0
	 */

	public int updateByObj(TbCloud2ProjectInfoObj obj);

	/**
	 * @Title:删除已有项目
	 * @Copyright: Copyright (c) 2011-12-2
	 * @Company: si-tech
	 * @author liys
	 * @version 1.0
	 */

	public int deleteByObj(TbCloud2ProjectInfoObj obj);

	/**
	 * @Title:创建项目
	 * @Copyright: Copyright (c) 2011-12-2
	 * @Company: si-tech
	 * @author liys
	 * @version 1.0
	 */

	public int insertByObj(TbCloud2ProjectInfoObj obj);

	/**
	 * @Title:根据项目名称查询项目管理表
	 * @Copyright: Copyright (c) 201112
	 * @Company: si-tech
	 * @author wangjian
	 * @version 1.0
	 */

	public TbCloud2ProjectInfoObj queryByName(TbCloud2ProjectInfoObj obj);

	/**
	 * @Title:为下拉列表提供的接口
	 * @Copyright: Copyright (c) 20120105
	 * @Company: si-tech
	 * @author wangjian
	 * @version 1.0
	 */

	@SuppressWarnings("unchecked")
	public List queryForListByProjectObj(TbCloud2ProjectInfoObj obj);

	/**
	 * @Title:创建项目中的用户
	 * @Copyright: Copyright (c) 2012-02-14
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */

	public int insertVuserByObj(TbCloud2ProjectInfoObj obj);

	/**
	 * @Title:删除项目中已有用户
	 * @Copyright: Copyright (c) 2012-02-17
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */

	public int deleteUserByObj(TbCloud2ProjectInfoObj obj);

	/**
	 * @Title:查询项目中已有用户
	 * @Copyright: Copyright (c) 2012-02-17
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */

	public List queryUserListByObj(VuserManageObj obj);
}
