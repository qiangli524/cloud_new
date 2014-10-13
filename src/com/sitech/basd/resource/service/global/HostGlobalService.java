package com.sitech.basd.resource.service.global;

import java.util.List;
import java.util.Map;

import com.sitech.basd.resource.domain.global.HostGlobalObj;
import com.sitech.basd.resource.domain.top.TopTargetObj;
import com.sitech.basd.resource.domain.united.CMSObj;
import com.sitech.basd.yicloud.domain.datastore.DataStoreObj;

public interface HostGlobalService {

	/**
	 * 
	 * @Title: countHost
	 * @Description: 统计当前集群下主机的个数
	 * @param
	 * @return integer
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @createtime Jul 31, 2013 15:06 PM
	 */
	public Integer countHost(HostGlobalObj obj);

	/**
	 * 
	 * @Title: hostStorage
	 * @Description: 查询主机的存储列表
	 * @param
	 * @return String
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @createtime aug 1, 2013 11:50 PM
	 */
	public List<DataStoreObj> queryForListByObj(DataStoreObj obj);
	/**
	 * 
	 * @Title: queryHostByMap
	 * @Description: 查询主机通过集群uuid和connect_id
	 * @param
	 * @return List
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @throws Exception
	 * @createtime 2013-9-17
	 */
	public List queryHostByMap(Map<String, String> map);

	/**
	 * @Title: queryForListUseClusterIn
	 * @Description: 根据集群集合查询主机集合
	 * @param
	 * @return List<HostGlobalObj>
	 * @throws
	 * @author lipp
	 * @version 1.0
	 * @createtime 2013-12-2 上午9:51:47
	 */
	public List<HostGlobalObj> queryForListUseClusterIn(HostGlobalObj hostObj);

	/**
	 * @Title: queryForListUseClusterIn
	 * @Description: 根据数据中心集合查询主机集合
	 * @param
	 * @return List<HostGlobalObj>
	 * @throws
	 * @author lipp
	 * @version 1.0
	 * @createtime 2013-12-2 上午9:51:47
	 */
	public List<HostGlobalObj> queryForListUseCenterIn(HostGlobalObj hostObj);

	/**
	 * @Title: countResource
	 * @Description: 查询cpu、内存等
	 * @param
	 * @return CMSObj
	 * @throws
	 * @author lipp
	 * @version 1.0
	 * @createtime 2013-12-2 上午10:00:46
	 */
	public CMSObj countResourceForHost(HostGlobalObj hostObj);

	/**
	 * @Title: countResource
	 * @Description: 查询cpu、内存等
	 * @param
	 * @return CMSObj
	 * @throws
	 * @author lipp
	 * @version 1.0
	 * @createtime 2013-12-2 上午10:00:46
	 */
	public CMSObj countResourceForCluster(HostGlobalObj hostObj);

	/**
	 * @Title: countResource
	 * @Description: 查询cpu、内存等
	 * @param
	 * @return CMSObj
	 * @throws
	 * @author lipp
	 * @version 1.0
	 * @createtime 2013-12-2 上午10:00:46
	 */
	public CMSObj countResourceForCenter(HostGlobalObj hostObj);

	/**
	 * @Title: queryForClusterListByCenter
	 * @Description: 根据数据中心查询集群
	 * @param
	 * @return List<HostGlobalObj>
	 * @throws
	 * @author lipp
	 * @version 1.0
	 * @createtime 2013-12-2 上午11:51:58
	 */
	public List<HostGlobalObj> queryForClusterListByCenter(
			HostGlobalObj hostGlobalObj);

	/**
	 * @Title: queryForTopList
	 * @Description: 查询topN列表
	 * @param
	 * @return List<HostGlobalObj>
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2014-1-24 下午5:23:20
	 */
	public List<TopTargetObj> queryForTopList(Map<String, Object> paramMap);

}
