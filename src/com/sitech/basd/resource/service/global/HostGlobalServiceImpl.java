package com.sitech.basd.resource.service.global;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sitech.basd.resource.dao.global.HostGlobalDao;
import com.sitech.basd.resource.domain.global.HostGlobalObj;
import com.sitech.basd.resource.domain.top.TopTargetObj;
import com.sitech.basd.resource.domain.united.CMSObj;
import com.sitech.basd.yicloud.dao.datastore.DataStoreDao;
import com.sitech.basd.yicloud.domain.datastore.DataStoreObj;

@Service("hostGlobalService")
public class HostGlobalServiceImpl implements HostGlobalService {

	@Autowired
	private HostGlobalDao hostGlobalDao;
	@Autowired
	private DataStoreDao dataStoreDao;

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
	public Integer countHost(HostGlobalObj obj) {
		return hostGlobalDao.countHost(obj);
	}

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
	public List<DataStoreObj> queryForListByObj(DataStoreObj obj) {
		List<DataStoreObj> list = dataStoreDao.queryForListByObj(obj);
		if (list != null) {
			DecimalFormat df = new DecimalFormat("#.00");
			for (DataStoreObj data : list) {
				Double capacityM = Double.valueOf(data.getCAPACITY());
				Double capacityG = capacityM / 1024.00;
				data.setCAPACITY(df.format(capacityG));
				Double free_spaceM = Double.valueOf(data.getFREE_SPACE());
				Double free_spaceG = free_spaceM / 1024;
				data.setFREE_SPACE(df.format(free_spaceG));
			}
		}
		return list;
	}
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
	public List queryHostByMap(Map<String, String> map) {
		return hostGlobalDao.queryHostByMap(map);
	}

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
	@Override
	public List<HostGlobalObj> queryForListUseClusterIn(HostGlobalObj hostObj) {
		return hostGlobalDao.queryForListUseClusterIn(hostObj);
	}

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
	@Override
	public List<HostGlobalObj> queryForListUseCenterIn(HostGlobalObj hostObj) {
		return hostGlobalDao.queryForListUseCenterIn(hostObj);
	}

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
	@Override
	public CMSObj countResourceForHost(HostGlobalObj hostObj) {
		return hostGlobalDao.countResourceForHost(hostObj);
	}

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
	@Override
	public CMSObj countResourceForCluster(HostGlobalObj hostObj) {
		return hostGlobalDao.countResourceForCluster(hostObj);
	}

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
	@Override
	public CMSObj countResourceForCenter(HostGlobalObj hostObj) {
		return hostGlobalDao.countResourceForCenter(hostObj);
	}

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
	@Override
	public List<HostGlobalObj> queryForClusterListByCenter(
			HostGlobalObj hostGlobalObj) {
		return hostGlobalDao.queryForClusterListByCenter(hostGlobalObj);
	}

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
	@SuppressWarnings("unchecked")
	@Override
	public List<TopTargetObj> queryForTopList(Map<String, Object> paramMap) {
		List<TopTargetObj> list = new ArrayList<TopTargetObj>();
		String resourceType = (String) paramMap.get("resourceType");
		if ("1".equals(resourceType)) {
			list = hostGlobalDao.queryCPUList(paramMap);
		} else if ("2".equals(resourceType)) {
			list = hostGlobalDao.queryMemList(paramMap);
		} else {
			list = hostGlobalDao.queryStoreList(paramMap);
		}
		DecimalFormat df = new DecimalFormat(".00");
		for (TopTargetObj topTargetObj : list) {
			topTargetObj.setStore_usage(Double.parseDouble(df.format(topTargetObj.getStore_usage())));
		}
		return list;
	}

}
