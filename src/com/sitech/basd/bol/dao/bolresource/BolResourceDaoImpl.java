package com.sitech.basd.bol.dao.bolresource;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.sitech.basd.bol.domain.bolresource.BolResourceObj;
import com.sitech.basd.bol.domain.bolresource.BolResourcesRegisterObj;
import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;

/**
 * <p>Title: BolResourceDaoImpl</p>
 * <p>Description: 资源视图持久层接口实现类</p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: SI-TECH </p>
 * @author lipengpeng
 * @version 1.0
 * @createtime 2013-10-31 上午11:41:05
 *
 */
@Repository("bolResourceDao")
public class BolResourceDaoImpl extends BaseDao implements BolResourceDao{

	/**
	 * @Title: countForResource
	 * @Description: 统计资源数量
	 * @param
	 * @return int
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-11-6 上午10:05:58
	 */
	@Override
	public int countForResource(BolResourceObj bolResourceObj) {
		int ret = 0;
		try {
			Object obj = getSqlMap().queryForObject("BolResource.countForResource", bolResourceObj);
			if (obj != null) {
				ret = ((Integer) obj).intValue();
			}
		} catch (Exception e) {
			LogHelper.error("BolResource.countForResource: " + e.getMessage() + e.getClass().getName());
		}
		return ret;
	}

	/**
	 * @Title: countHostNum
	 * @Description: 统计主机数量
	 * @param
	 * @return Integer
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-11-6 上午10:33:26
	 */
	@Override
	public Integer countHostNum() {
		int ret = 0;
		try {
			Object obj = getSqlMap().queryForObject("BolResource.countHostNum");
			if (obj != null) {
				ret = ((Integer) obj).intValue();
			}
		} catch (Exception e) {
			LogHelper.error("BolResource.countHostNum: " + e.getMessage() + e.getClass().getName());
		}
		return ret;
	}

	/**
	 * @Title: queryForSum
	 * @Description: 查询各种汇总
	 * @param
	 * @return Double
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-11-6 上午10:44:24
	 */
	@Override
	public Double queryForSum(BolResourceObj bolResourceObj) {
		Double ret = 0.0;
		try {
			Object obj = getSqlMap().queryForObject("BolResource.queryForSum", bolResourceObj);
			if (obj != null) {
				ret = (Double) obj;
			}
		} catch (Exception e) {
			LogHelper.error("BolResource.queryForSum: " + e.getMessage() + e.getClass().getName());
		}
		return ret;
	}

	/**
	 * @Title: queryForUsed
	 * @Description: 查询资源使用量
	 * @param
	 * @return Integer
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-11-6 上午11:11:37
	 */
	@Override
	public Double queryForUsed(BolResourceObj bolResourceObj) {
		Double ret = 0.0;
		try {
			Object obj = getSqlMap().queryForObject("BolResource.queryForUsed", bolResourceObj);
			if (obj != null) {
				ret = (Double) obj;
			}
		} catch (Exception e) {
			LogHelper.error("BolResource.queryForUsed: " + e.getMessage() + e.getClass().getName());
		}	
		return ret;
	}

	/**
	 * @Title: queryForListByObj
	 * @Description: 查询资源列表
	 * @param
	 * @return List<BolResourceObj>
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-11-6 下午3:54:23
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<BolResourceObj> queryForListByObj(BolResourceObj bolResourceObj) {
		List<BolResourceObj> list = new ArrayList<BolResourceObj>();
		try {
			if (bolResourceObj.getPagination() != null) {
				bolResourceObj.setFIRSTROWNUM(bolResourceObj.getPagination().getFirstRownum());
				bolResourceObj.setPAGESIZE(bolResourceObj.getPagination().getPageSize());
				bolResourceObj.getPagination().setTotalCount(
						((Integer) getSqlMap().queryForObject("BolResource.countByObj", bolResourceObj)).intValue()
				);
			}
			list = getSqlMap().queryForList("BolResource.queryForListByObj", bolResourceObj);
		} catch (Exception e) {
			LogHelper.error("BolResource.queryForListByObj: " + e.getMessage() + e.getClass().getName());
		}
		return list;
	}

	/**
	 * @Title: queryResourceForProcess
	 * @Description: 展示某种资源关联的资源
	 * @param
	 * @return List<BolResourceObj>
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-11-7 下午2:02:52
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<BolResourceObj> queryResourceForProcess(BolResourceObj bolResourceObj) {
		List<BolResourceObj> list = new ArrayList<BolResourceObj>();
		try {
			if (bolResourceObj.getPagination() != null) {
				bolResourceObj.setFIRSTROWNUM(bolResourceObj.getPagination().getFirstRownum());
				bolResourceObj.setPAGESIZE(bolResourceObj.getPagination().getPageSize());
				bolResourceObj.getPagination().setTotalCount(
					((Integer) getSqlMap().queryForObject("BolResource.countResourceForProcess", bolResourceObj))
				);
				list = getSqlMap().queryForList("BolResource.queryResourceForProcess",bolResourceObj);
			}
		} catch (Exception e) {
			LogHelper.error("BolResource.queryResourceForProcess: " + e.getMessage() + e.getClass().getName());
		}
		return list;
	}
	
	/**
	 * 
	 * @Title: queryResourcesRegister
	 * @Description: 查询资源登记情况，即 每个进程的资源占用情况
	 * @param
	 * @return List<BolResourcesRegisterObj>
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime Jun 16, 2014 1:01:32 PM
	 */
	public List<BolResourcesRegisterObj> queryResourcesRegister(BolResourcesRegisterObj obj){
		List<BolResourcesRegisterObj> list = new ArrayList<BolResourcesRegisterObj>();
		try {
			list = getSqlMap().queryForList("BolResource.queryResourcesRegister",obj);
		} catch (Exception e) {
			LogHelper.error("BolResource.queryResourcesRegister: " + e.getMessage() + e.getClass().getName());
		}
		return list;
	}

}
