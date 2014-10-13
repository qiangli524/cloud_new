package com.sitech.basd.resource.dao.united;

import java.util.List;
import java.util.Map;

import com.sitech.basd.cloud3.domain.departproject.DepartInfoObj;
import com.sitech.basd.fusioncharts.vo.Data;
import com.sitech.basd.resource.domain.top.TopTargetObj;
import com.sitech.basd.resource.domain.united.ResourceOutlineObj;
import com.sitech.basd.sxcloud.cloud.domain.vmhost.VMHostObj;
import com.sitech.basd.yicloud.domain.busisystem.BusiSystemObj;

/**
 * 
 * <p>
 * Title: ResourceOutlineDao
 * </p>
 * <p>
 * Description: 首页展示相关操作
 * </p>
 * <p>
 * Copyright: Copyright (c) 2014
 * </p>
 * <p>
 * Company: SI-TECH
 * </p>
 * 
 * @author yanggl
 * @version 1.0
 * @createtime 2014-1-22 下午2:45:56
 * 
 */
public interface ResourceOutlineDao {

	/**
	 * @Title: queryForNetList
	 * @Description: 查询网络总量和使用量列表
	 * @param
	 * @return List<ResourceOutlineObj>
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2014-1-22 下午4:28:00
	 */
	public List<ResourceOutlineObj> queryForNetList(ResourceOutlineObj resourceOutlineObj);
	
	public List<DepartInfoObj> queryForList(DepartInfoObj departInfoObj);
	
	/**
	 * 
	 * @Title: queryCpuResource
	 * @Description: 查询CPU
	 * @param
	 * @return Data
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-1-22 下午3:04:17
	 */
	public Data queryCpuResource(ResourceOutlineObj obj);

	/**
	 * 
	 * @Title: queryMemResource
	 * @Description: 查询内存
	 * @param
	 * @return Data
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-1-22 下午5:23:32
	 */
	public Data queryMemResource(ResourceOutlineObj obj);

	/**
	 * 
	 * @Title: queryStoreResource
	 * @Description: 查询存储
	 * @param
	 * @return Data
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-1-22 下午5:24:06
	 */
	public Data queryStoreResource(ResourceOutlineObj obj);

	/**
	 * 
	 * @Title: queryStore
	 * @Description: 查询存储总量和使用量
	 * @param
	 * @return ResourceOutlineObj
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-1-23 下午8:09:24
	 */
	public ResourceOutlineObj queryStore(ResourceOutlineObj obj);

	/**
	 * @Title: queryVMCountGroupByBusi
	 * @Description: 查询每个业务系统下各有多少虚拟机
	 * @param
	 * @return List<Data>
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2014-1-24 上午9:59:27
	 */
	public List<Data> queryVMCountGroupByBusi(Map<String, Object> paramMap);

	/**
	 * @Title: queryStoreCountGroupByBusi
	 * @Description: 查询各个业务系统下各有多少存储
	 * @param
	 * @return List<Data>
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2014-1-24 上午10:00:41
	 */
	public List<Data> queryStoreCountGroupByBusi(Map<String, Object> paramMap);

	/**
	 * @Title: queryBusiSystemVmTopList
	 * @Description: 业务系统虚拟机topN列表
	 * @param
	 * @return List<BusiSystemObj>
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2014-1-24 下午2:04:47
	 */
	public List<BusiSystemObj> queryBusiSystemVmTopList(Map<String, Object> paramMap);

	/**
	 * @Title: queryBusiSystemStoreTopList
	 * @Description: 业务系统存储topN列表
	 * @param
	 * @return List<BusiSystemObj>
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2014-1-24 下午2:05:07
	 */
	public List<BusiSystemObj> queryBusiSystemStoreTopList(Map<String, Object> paramMap);

	/**
	 * @Title: queryCpuTopList
	 * @Description: 根据cpu查询
	 * @param
	 * @return List<TopTargetObj>
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2014-1-24 下午6:06:08
	 */
	public List<TopTargetObj> queryCpuTopList(TopTargetObj topTargetObj);

	/**
	 * @Title: queryMemTopList
	 * @Description: 根据内存查询
	 * @param
	 * @return List<TopTargetObj>
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2014-1-24 下午6:06:08
	 */
	public List<TopTargetObj> queryMemTopList(TopTargetObj topTargetObj);

	/**
	 * @Title: queryStoreTopList
	 * @Description: 根据存储查询
	 * @param
	 * @return List<TopTargetObj>
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2014-1-24 下午6:06:08
	 */
	public List<TopTargetObj> queryStoreTopList(TopTargetObj topTargetObj);

	/**
	 * @Title: queryValidStore
	 * @Description: 查询有效存储
	 * @param
	 * @return ResourceOutlineObj
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2014-1-26 下午12:54:13
	 */
	public ResourceOutlineObj queryValidStore(ResourceOutlineObj resourceOutlineObj);

	/**
	 * @Title: queryCpuTopListVM
	 * @Description: 虚拟机cpuTopN
	 * @param
	 * @return List<TopTargetObj>
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2014-1-26 下午2:15:53
	 */
	public List<TopTargetObj> queryCpuTopListVM(TopTargetObj topTargetObj);

	/**
	 * @Title: queryMemTopListVM
	 * @Description: 虚拟机内存topN
	 * @param
	 * @return List<TopTargetObj>
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2014-1-26 下午2:16:14
	 */
	public List<TopTargetObj> queryMemTopListVM(TopTargetObj topTargetObj);

	/**
	 * @Title: queryVmHostConfigInfo
	 * @Description: 查询虚拟机配置信息
	 * @param
	 * @return VMHostObj
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2014-1-26 下午6:44:49
	 */
	public VMHostObj queryVmHostConfigInfo(TopTargetObj topTargetObj);
}
