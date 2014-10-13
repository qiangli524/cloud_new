package dao.relation;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;

import domain.relation.ServiceKpiRelationObj;
/**
 * 
 * <p>
 * Title: ServiceKpiRelationDao
 * </p>
 * <p>
 * Description: 服务和KPI关系相关
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
 * @createtime 2014-1-7 下午7:39:46
 * 
 */
@Repository("serviceKpiRelationDao")
public class ServiceKpiRelationDaoImpl extends BaseDao implements ServiceKpiRelationDao {
	/**
	 * 
	 * @Title: queryServiceKpiRelatonList
	 * @Description: 查询服务和KPI的关系
	 * @param
	 * @return List<ServiceKpiRelationObj>
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-1-6 上午9:43:09
	 */
	@Override
	public List<ServiceKpiRelationObj> queryServiceKpiRelatonList(ServiceKpiRelationObj obj) {
		List<ServiceKpiRelationObj> list = new ArrayList<ServiceKpiRelationObj>();
		try {
			if(obj.getPagination() != null){
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj.getPagination().setTotalCount(
						((Integer)getSqlMap().queryForObject(
								"ServiceKpiRelation.queryServiceKpiRelatonListCount",obj)).intValue());
				list = getSqlMap().queryForList("ServiceKpiRelation.queryServiceKpiRelatonList", obj);
			}
		} catch (SQLException e) {
			logger.error("ServiceKpiRelation.queryServiceKpiRelatonList:" + e.getMessage()
					+ e.getClass().getName());
		}
		return list;
	}

	/**
	 * 
	 * @Title: queryServiceNameList
	 * @Description: 获取服务名称
	 * @param
	 * @return List<ServiceKpiRelationObj>
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-1-6 下午10:09:41
	 */
	@Override
	public List<ServiceKpiRelationObj> queryServiceNameList(
			ServiceKpiRelationObj obj) {
		List<ServiceKpiRelationObj> list = new ArrayList<ServiceKpiRelationObj>();
		try {
				list = getSqlMap().queryForList("ServiceKpiRelation.queryServiceNameList", obj);
		} catch (SQLException e) {
			logger.error("ServiceKpiRelation.queryServiceNameList:" + e.getMessage()
					+ e.getClass().getName());
		}
		return list;
	}

	/**
	 * 
	 * @Title: queryKpiNameList
	 * @Description: 获取KPI描述
	 * @param
	 * @return List<ServiceKpiRelationObj>
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-1-7 下午7:43:36
	 */
	@Override
	public List<ServiceKpiRelationObj> queryKpiNameList(
			ServiceKpiRelationObj obj) {
		List<ServiceKpiRelationObj> list = new ArrayList<ServiceKpiRelationObj>();
		try {
				list = getSqlMap().queryForList("ServiceKpiRelation.queryKpiNameList", obj);
		} catch (SQLException e) {
			logger.error("ServiceKpiRelation.queryKpiNameList:" + e.getMessage()
					+ e.getClass().getName());
		}
		return list;
	}

	/**
	 * 
	 * @Title: queryServiceKpiRelaton
	 * @Description: 查询一条服务和KPI的关系
	 * @param
	 * @return ServiceKpiRelationObj
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-1-7 下午7:45:58
	 */
	@Override
	public ServiceKpiRelationObj queryServiceKpiRelaton(
			ServiceKpiRelationObj obj) {
		ServiceKpiRelationObj relationObj = new ServiceKpiRelationObj();
		try {
			relationObj = (ServiceKpiRelationObj) getSqlMap().queryForObject(
					"ServiceKpiRelation.queryServiceKpiRelatonList", obj);
		} catch (SQLException e) {
			logger.error("ServiceKpiRelation.queryServiceKpiRelatonList:"
					+ e.getMessage() + e.getClass().getName());
		}
		return relationObj;
	}

	/**
	 * 
	 * @Title: saveServiceNameKpiRelation
	 * @Description: 插入服务名字与kpi的关系
	 * @param
	 * @return int
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-4-16 下午7:14:44
	 */
	public int saveServiceNameKpiRelation(ServiceKpiRelationObj obj) {
		int ret = 0;
		try {
			Object ob = getSqlMap().insert(
					"ServiceKpiRelation.saveServiceNameKpiRelation", obj);
			if (ob != null) {
				ret = Integer.parseInt(ob.toString());
			}
		} catch (SQLException e) {
			ret = -1;
			logger.error("ServiceKpiRelation.saveServiceNameKpiRelation:"
					+ e.getMessage() + e.getClass().getName());
		}
		return ret;
	}

	/**
	 * 
	 * @Title: delServiceNameKpiRelation
	 * @Description: 删除服务名称和KPI的关系
	 * @param
	 * @return int
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-4-17 上午11:47:18
	 */
	@Override
	public int delServiceNameKpiRelation(ServiceKpiRelationObj obj) {
		int ret = 0;
		try {
			Object ob = getSqlMap().delete(
					"ServiceKpiRelation.delServiceNameKpiRelation", obj);
			if (ob != null) {
				ret = Integer.parseInt(ob.toString());
			}
		} catch (SQLException e) {
			ret = -1;
			logger.error("ServiceKpiRelation.delServiceNameKpiRelation:"
					+ e.getMessage() + e.getClass().getName());
		}
		return ret;
	}
}
