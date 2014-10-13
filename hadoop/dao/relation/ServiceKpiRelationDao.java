package dao.relation;

import java.util.List;

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
public interface ServiceKpiRelationDao {
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
	public List<ServiceKpiRelationObj> queryServiceKpiRelatonList(ServiceKpiRelationObj obj);
	
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
	public List<ServiceKpiRelationObj> queryServiceNameList(ServiceKpiRelationObj obj);

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
	public List<ServiceKpiRelationObj> queryKpiNameList(ServiceKpiRelationObj obj);

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
	public ServiceKpiRelationObj queryServiceKpiRelaton(ServiceKpiRelationObj obj);

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
	public int saveServiceNameKpiRelation(ServiceKpiRelationObj obj);

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
	public int delServiceNameKpiRelation(ServiceKpiRelationObj obj);
}
