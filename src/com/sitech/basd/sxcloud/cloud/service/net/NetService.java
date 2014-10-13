package com.sitech.basd.sxcloud.cloud.service.net;

import java.util.List;

import com.sitech.basd.sxcloud.cloud.domain.net.TbCloud2NetInfoObj;

@SuppressWarnings("all")
public interface NetService {

	/**
	 * @Title:查询已有网络列表
	 * @Copyright: Copyright (c) 2011-12-2
	 * @Company: si-tech
	 * @author liys
	 * @version 1.0
	 */

	public List queryForListByObj(TbCloud2NetInfoObj obj);

	/**
	 * @Title:查询并返回一个网络对象
	 * @Copyright: Copyright (c) 2011-12-2
	 * @Company: si-tech
	 * @author liys
	 * @version 1.0
	 */

	public TbCloud2NetInfoObj queryByObj(TbCloud2NetInfoObj obj);

	/**
	 * @Title:更新网络信息
	 * @Copyright: Copyright (c) 2011-12-2
	 * @Company: si-tech
	 * @author liys
	 * @version 1.0
	 */

	public int updateByObj(TbCloud2NetInfoObj obj);

	/**
	 * @Title:删除已有网络
	 * @Copyright: Copyright (c) 2011-12-2
	 * @Company: si-tech
	 * @author liys
	 * @version 1.0
	 */

	public int deleteByObj(TbCloud2NetInfoObj obj);

	/**
	 * @Title:创建网络
	 * @Copyright: Copyright (c) 2011-12-2
	 * @Company: si-tech
	 * @author liys
	 * @version 1.0
	 */

	public int insertByObj(TbCloud2NetInfoObj obj);

	/**
	 * @Title:为下拉列表查询NET表
	 * @Copyright: Copyright (c) 20120104
	 * @Company: si-tech
	 * @author wangjian
	 * @version 1.0
	 */

	public List queryByNetObjForList(TbCloud2NetInfoObj obj);
	
	/**
	 * 
	 * @Title: queryNetListByObj
	 * @Description: 查询net列表
	 * @param
	 * @return List
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime May 17, 2013 6:17:58 PM
	 */
	public List queryNetListByObj(TbCloud2NetInfoObj obj);
	/**
	 * 
	 * @Title: getNetResource
	 * @Description: 获取网络资源信息
	 * @param
	 * @return Map
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Feb 21, 2013 2:00:58 PM
	 */
	public List getNetResource();
	/**
	 * @Title:创建网络
	 * @Copyright: Copyright (c) 2013-09-12
	 * @Company: si-tech
	 * @author yanggl
	 * @version 1.0
	 */
	public String insertNet(TbCloud2NetInfoObj obj);

	/**
	 * @Title: queryForListByDomainId
	 * @Description: 通过网络域id查询vlan信息
	 * @param
	 * @return List<TbCloud2NetInfoObj>
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-9-16 上午9:52:38
	 */
	public List<TbCloud2NetInfoObj> queryForListByDomainId(String domainid);
}
