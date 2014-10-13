package com.sitech.basd.sxcloud.cloud.service.virtual;

import java.util.List;

import com.sitech.basd.sxcloud.cloud.domain.virtual.TbCloud2VirtualInfoObj;

public interface VirtualService {

	/**
	 * @Title:查询已有虚拟机列表
	 * @Copyright: Copyright (c) 2011-12-2
	 * @Company: si-tech
	 * @author liys
	 * @version 1.0
	 */

	@SuppressWarnings("unchecked")
	public List queryForListByObj(TbCloud2VirtualInfoObj obj);

	/**
	 * @Title:查询并返回一个虚拟机对象
	 * @Copyright: Copyright (c) 2011-12-2
	 * @Company: si-tech
	 * @author liys
	 * @version 1.0
	 */

	public TbCloud2VirtualInfoObj queryByObj(TbCloud2VirtualInfoObj obj);

	/**
	 * @Title:更新虚拟机信息
	 * @Copyright: Copyright (c) 2011-12-2
	 * @Company: si-tech
	 * @author liys
	 * @version 1.0
	 */

	public int updateByObj(TbCloud2VirtualInfoObj obj);

	/**
	 * @Title:删除已有虚拟机
	 * @Copyright: Copyright (c) 2011-12-2
	 * @Company: si-tech
	 * @author liys
	 * @version 1.0
	 */

	public int deleteByObj(TbCloud2VirtualInfoObj obj);

	/**
	 * @Title:创建虚拟机
	 * @Copyright: Copyright (c) 2011-12-2
	 * @Company: si-tech
	 * @author liys
	 * @version 1.0
	 */

	public int insertByObj(TbCloud2VirtualInfoObj obj);

	/**
	 * @Title:调整大小预留接口
	 * @Copyright: Copyright (c) 201112
	 * @Company: si-tech
	 * @author wangjian
	 * @version 1.0
	 */
	public int adjustVirtualInfo(TbCloud2VirtualInfoObj obj);

	/**
	 * @Title:添加存储预留接口
	 * @Copyright: Copyright (c) 201112
	 * @Company: si-tech
	 * @author taoxue
	 * @version 1.0
	 */
	public int addMemInfo(TbCloud2VirtualInfoObj obj);

	/**
	 * @Title:移至项目更新PROJECT_ID
	 * @Copyright: Copyright (c) 2011-12-2
	 * @Company: si-tech
	 * @author wangjian
	 * @version 1.0
	 */

	public int updateByProjectIdObj(TbCloud2VirtualInfoObj obj);

	/**
	 * @Title:启动停止虚拟机
	 * @Copyright: Copyright (c) 2012-02-15
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */

	public int StartAndStopByObj(TbCloud2VirtualInfoObj obj);

	/**
	 * @Title:查询虚拟机状态信息
	 * @Copyright: Copyright (c) 2012-02-15
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */

	public List queryListIDByObj(TbCloud2VirtualInfoObj obj);

	/**
	 * @Title:查询虚拟机詳細信息
	 * @Copyright: Copyright (c) 2012-02-15
	 * @Company: si-tech
	 * @author taoxue
	 * @version 1.0
	 */
	public TbCloud2VirtualInfoObj queryVirtualServerByObj(
			TbCloud2VirtualInfoObj obj);

	/**
	 * 
	 * @Title: 获取所有虚拟机列表
	 * @Copyright: Copyright (c) Mar 20, 2012
	 * @Company: si-tech
	 * @author liys
	 * @version 1.0
	 */
	public List queryForAllListByObj(TbCloud2VirtualInfoObj obj);

	/**
	 * 
	 * @Title:通过多个Id获取对应虚拟机列表
	 * @Copyright: Copyright (c) Mar 20, 2012
	 * @Company: si-tech
	 * @author taoxue
	 * @version 1.0
	 */
	public List<TbCloud2VirtualInfoObj> queryVirtualList(String id);

}
