package com.sitech.basd.resource.service.global;

import java.util.List;
import java.util.Map;

import com.sitech.basd.resource.domain.global.VmGlobalObj;
import com.sitech.basd.resource.domain.top.TopTargetObj;

public interface VmGlobalService {

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
	public Integer countVm(VmGlobalObj obj);

	/**
	 * @Title: queryForListByHost
	 * @Description: 根据主机查询记录
	 * @param
	 * @return List<VmGlobalObj>
	 * @throws
	 * @author lipp
	 * @version 1.0
	 * @createtime 2013-12-1 下午7:05:41
	 */
	public List<VmGlobalObj> queryForListByHost(VmGlobalObj vmGlobalObj);

	/**
	 * @Title: queryForListByCluster
	 * @Description: 根据集群查询记录
	 * @param
	 * @return List<VmGlobalObj>
	 * @throws
	 * @author lipp
	 * @version 1.0
	 * @createtime 2013-12-1 下午7:05:41
	 */
	public List<VmGlobalObj> queryForListByCluster(VmGlobalObj vmGlobalObj);

	/**
	 * @Title: queryForListByCenter
	 * @Description: 根据数据中心查询记录
	 * @param
	 * @return List<VmGlobalObj>
	 * @throws
	 * @author lipp
	 * @version 1.0
	 * @createtime 2013-12-1 下午7:05:41
	 */
	public List<VmGlobalObj> queryForListByCenter(VmGlobalObj vmGlobalObj);

	/**
	 * @Title: queryForTopList
	 * @Description: 查询topN
	 * @param
	 * @return List<TopTargetObj>
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2014-1-26 下午2:08:04
	 */
	public List<TopTargetObj> queryForTopList(Map<String, Object> paramMap);

}
