package com.sitech.basd.resource.dao.united;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.sitech.basd.cloud3.domain.departproject.DepartInfoObj;
import com.sitech.basd.fusioncharts.vo.Data;
import com.sitech.basd.resource.domain.top.TopTargetObj;
import com.sitech.basd.resource.domain.united.ResourceOutlineObj;
import com.sitech.basd.sxcloud.cloud.domain.vmhost.VMHostObj;
import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;
import com.sitech.basd.yicloud.domain.busisystem.BusiSystemObj;

/**
 * 
 * <p>
 * Title: ResourceOutlineDaoImpl
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
 * @createtime 2014-1-22 下午2:46:20
 * 
 */
@Repository("resourceOutlineDao")
public class ResourceOutlineDaoImpl extends BaseDao implements
		ResourceOutlineDao {
	
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
	@SuppressWarnings("unchecked")
	@Override
	public List<ResourceOutlineObj> queryForNetList(ResourceOutlineObj resourceOutlineObj) {
		List<ResourceOutlineObj> netList = new ArrayList<ResourceOutlineObj>();
		try {
			netList = getSqlMap().queryForList("ResourceOutline.queryForNetList",resourceOutlineObj);
		} catch (Exception e) {
			LogHelper.error("ResourceOutline.queryForNetList: "  + e.getMessage() + e.getClass().getName());
		}
		return netList;
	}
	
	@Override
	public List<DepartInfoObj> queryForList(DepartInfoObj departInfoObj) {
		List<DepartInfoObj> list = new ArrayList<DepartInfoObj>();
		try {
			list = sqlMapClient.queryForList("DepartInfo.queryForList", departInfoObj);
		} catch (Exception e) {
			LogHelper.error("DepartInfo.queryForList: " + e.getMessage() + e.getClass().getName());
		}
		return list;
	}
	@Override
	public Data queryCpuResource(ResourceOutlineObj obj) {
		Data data = new Data();
		try {
			data = (Data) getSqlMap().queryForObject(
					"ResourceOutline.queryCpuResource", obj);
		} catch (SQLException e) {
			logger.error("ResourceOutline.queryCpuResource:" + e.getMessage()
					+ e.getClass().getName());
		}
		return data;
	}

	@Override
	public Data queryMemResource(ResourceOutlineObj obj) {
		Data data = new Data();
		try {
			data = (Data) getSqlMap().queryForObject(
					"ResourceOutline.queryMemResource", obj);
		} catch (SQLException e) {
			logger.error("ResourceOutline.queryMemResource:" + e.getMessage()
					+ e.getClass().getName());
		}
		return data;
	}

	
	@Override
	public Data queryStoreResource(ResourceOutlineObj obj) {
		Data data = new Data();
		try {
			data = (Data) getSqlMap().queryForObject(
					"ResourceOutline.queryStoreResource", obj);
		} catch (SQLException e) {
			logger.error("ResourceOutline.queryStoreResource:" + e.getMessage()
					+ e.getClass().getName());
		}
		return data;
	}

	/**
	 * @Title: queryStore
	 * @Description: 查询存储
	 * @param
	 * @return List<ResourceOutlineObj>
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2014-1-22 下午4:28:00
	 */
	@Override
	public ResourceOutlineObj queryStore(ResourceOutlineObj obj) {
		ResourceOutlineObj data = new ResourceOutlineObj();
		try {
			data = (ResourceOutlineObj) getSqlMap().queryForObject(
					"ResourceOutline.queryStore", obj);
		} catch (SQLException e) {
			logger.error("ResourceOutline.queryStore:" + e.getMessage()
					+ e.getClass().getName());
		}
		return data;
	}

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
	@SuppressWarnings("unchecked")
	@Override
	public List<Data> queryVMCountGroupByBusi(Map<String, Object> paramMap) {
		List<Data> list = new ArrayList<Data>();
		try {
			list = getSqlMap().queryForList("ResourceOutline.queryVMCountGroupByBusi", paramMap);
		} catch (Exception e) {
			LogHelper.error("ResourceOutline.queryVMCountGroupByBusi: " + e.getMessage() + e.getClass().getName());
		}
		return list;
	}

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
	@SuppressWarnings("unchecked")
	@Override
	public List<Data> queryStoreCountGroupByBusi(Map<String, Object> paramMap) {
		List<Data> list = new ArrayList<Data>();
		try {
			list = getSqlMap().queryForList("ResourceOutline.queryStoreCountGroupByBusi", paramMap);
		} catch (Exception e) {
			LogHelper.error("ResourceOutline.queryStoreCountGroupByBusi: " + e.getMessage() + e.getClass().getName());
		}
		return list;
	}

	/**
	 * @Title: queryBusiSystemStoreTopList
	 * @Description: 业务系统虚拟机topN列表
	 * @param
	 * @return List<BusiSystemObj>
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2014-1-24 下午2:05:07
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<BusiSystemObj> queryBusiSystemVmTopList(Map<String, Object> paramMap) {
		List<BusiSystemObj> list = new ArrayList<BusiSystemObj>();
		try {
			list = getSqlMap().queryForList("ResourceOutline.queryBusiSystemVmTopList", paramMap);
		} catch (Exception e) {
			LogHelper.error("ResourceOutline.queryBusiSystemVmTopList: " + e.getMessage() + e.getClass().getName());
		}
		return list;
	}

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
	@SuppressWarnings("unchecked")
	@Override
	public List<BusiSystemObj> queryBusiSystemStoreTopList(Map<String, Object> paramMap) {
		List<BusiSystemObj> list = new ArrayList<BusiSystemObj>();
		try {
			list = getSqlMap().queryForList("ResourceOutline.queryBusiSystemStoreTopList", paramMap);
		} catch (Exception e) {
			LogHelper.error("ResourceOutline.queryBusiSystemStoreTopList: " + e.getMessage() + e.getClass().getName());
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TopTargetObj> queryCpuTopList(TopTargetObj topTargetObj) {
		List<TopTargetObj> list = new ArrayList<TopTargetObj>();
		try {
			if (topTargetObj.getPagination() != null) {
				topTargetObj.setFIRSTROWNUM(topTargetObj.getPagination().getFirstRownum());
				topTargetObj.setPAGESIZE(topTargetObj.getPagination().getPageSize());
				topTargetObj.getPagination().setTotalCount(
						((Integer) getSqlMap().queryForObject("ResourceOutline.queryTopCount", topTargetObj))
								.intValue());
			}
			list = getSqlMap().queryForList("ResourceOutline.queryCpuTopList",topTargetObj);
		} catch (Exception e) {
			LogHelper.error("ResourceOutline.queryCpuTopList: " + e.getMessage() + e.getClass().getName());
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TopTargetObj> queryMemTopList(TopTargetObj topTargetObj) {
		List<TopTargetObj> list = new ArrayList<TopTargetObj>();
		try {
			if (topTargetObj.getPagination() != null) {
				topTargetObj.setFIRSTROWNUM(topTargetObj.getPagination().getFirstRownum());
				topTargetObj.setPAGESIZE(topTargetObj.getPagination().getPageSize());
				topTargetObj.getPagination().setTotalCount(
						((Integer) getSqlMap().queryForObject("ResourceOutline.queryTopCount", topTargetObj))
								.intValue());
			}
			list = getSqlMap().queryForList("ResourceOutline.queryMemTopList",topTargetObj);
		} catch (Exception e) {
			LogHelper.error("ResourceOutline.queryMemTopList: " + e.getMessage() + e.getClass().getName());
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TopTargetObj> queryStoreTopList(TopTargetObj topTargetObj) {
		List<TopTargetObj> list = new ArrayList<TopTargetObj>();
		try {
			if (topTargetObj.getPagination() != null) {
				topTargetObj.setFIRSTROWNUM(topTargetObj.getPagination().getFirstRownum());
				topTargetObj.setPAGESIZE(topTargetObj.getPagination().getPageSize());
				topTargetObj.getPagination().setTotalCount(
						((Integer) getSqlMap().queryForObject("ResourceOutline.queryTopCount", topTargetObj))
								.intValue());
			}
			list = getSqlMap().queryForList("ResourceOutline.queryStoreTopList",topTargetObj);
		} catch (Exception e) {
			LogHelper.error("ResourceOutline.queryStoreTopList: " + e.getMessage() + e.getClass().getName());
		}
		return list;
	}

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
	@Override
	public ResourceOutlineObj queryValidStore(ResourceOutlineObj resourceOutlineObj) {
		ResourceOutlineObj outlineObj = new ResourceOutlineObj();
		try {
			Object obj = getSqlMap().queryForObject("ResourceOutline.queryValidStore", resourceOutlineObj);
			if (obj != null) {
				outlineObj = (ResourceOutlineObj) obj;
			}
		} catch (Exception e) {
			LogHelper.error("ResourceOutline.queryValidStore: " + e.getMessage() + e.getClass().getName());
		}
		return outlineObj;
	}

	/**
	 * @Title: queryCpuTopListVM
	 * @Description: 虚拟机cpu topN
	 * @param
	 * @return List<TopTargetObj>
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2014-1-26 下午2:16:14
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<TopTargetObj> queryCpuTopListVM(TopTargetObj topTargetObj) {
		List<TopTargetObj> list = new ArrayList<TopTargetObj>();
		try {
			if (topTargetObj.getPagination() != null) {
				topTargetObj.setFIRSTROWNUM(topTargetObj.getPagination().getFirstRownum());
				topTargetObj.setPAGESIZE(topTargetObj.getPagination().getPageSize());
				topTargetObj.getPagination().setTotalCount(
						((Integer) getSqlMap().queryForObject("ResourceOutline.queryTopCountVM", topTargetObj))
								.intValue());
			}
			list = getSqlMap().queryForList("ResourceOutline.queryCpuTopListVM",topTargetObj);
		} catch (Exception e) {
			LogHelper.error("ResourceOutline.queryCpuTopListVM: " + e.getMessage() + e.getClass().getName());
		}
		return list;
	}

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
	@SuppressWarnings("unchecked")
	@Override
	public List<TopTargetObj> queryMemTopListVM(TopTargetObj topTargetObj) {
		List<TopTargetObj> list = new ArrayList<TopTargetObj>();
		try {
			if (topTargetObj.getPagination() != null) {
				topTargetObj.setFIRSTROWNUM(topTargetObj.getPagination().getFirstRownum());
				topTargetObj.setPAGESIZE(topTargetObj.getPagination().getPageSize());
				topTargetObj.getPagination().setTotalCount(
						((Integer) getSqlMap().queryForObject("ResourceOutline.queryTopCountVM", topTargetObj))
								.intValue());
			}
			list = getSqlMap().queryForList("ResourceOutline.queryMemTopListVM",topTargetObj);
		} catch (Exception e) {
			LogHelper.error("ResourceOutline.queryMemTopListVM: " + e.getMessage() + e.getClass().getName());
		}
		return list;
	}

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
	@Override
	public VMHostObj queryVmHostConfigInfo(TopTargetObj topTargetObj) {
		VMHostObj vmHostObj = new VMHostObj();
		try {
			Object obj = getSqlMap().queryForObject("ResourceOutline.queryVmHostConfigInfo", topTargetObj);
			if (obj != null) {
				vmHostObj = (VMHostObj) obj;
			}
		} catch (Exception e) {
			LogHelper.error("ResourceOutline.queryVmHostConfigInfo: " + e.getMessage() + e.getClass().getName());
		}
		return vmHostObj;
	}
}
