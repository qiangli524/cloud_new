package service.relation;

import java.io.InputStream;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import util.HadoopConstant;

import com.sitech.basd.util.data.ReadExcel;

import dao.relation.ServiceKpiRelationDao;
import domain.relation.ServiceKpiRelationObj;

/**
 * 
 * <p>
 * Title: ServiceKpiRelationServiceImpl
 * </p>
 * <p>
 * Description: 服务和KPI关系相关操作
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
 * @createtime 2014-1-7 下午7:42:57
 * 
 */
@Service("serviceKpiRelationService")
public class ServiceKpiRelationServiceImpl implements ServiceKpiRelationService {
	private static final Logger logger = Logger
			.getLogger(ServiceKpiRelationService.class);
	@Autowired
	private ServiceKpiRelationDao serviceKpiRelationDao;

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
	public List<ServiceKpiRelationObj> queryServiceKpiRelatonList(
			ServiceKpiRelationObj obj) {
		return serviceKpiRelationDao.queryServiceKpiRelatonList(obj);
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
		return serviceKpiRelationDao.queryServiceNameList(obj);
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
		return serviceKpiRelationDao.queryKpiNameList(obj);
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
		return serviceKpiRelationDao.queryServiceKpiRelaton(obj);
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
		return serviceKpiRelationDao.saveServiceNameKpiRelation(obj);
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
	public int delServiceNameKpiRelation(ServiceKpiRelationObj obj){
		return serviceKpiRelationDao.delServiceNameKpiRelation(obj);
	}

	/**
	 * 
	 * @Title: importFromXls
	 * @Description: 从excel导入数据(服务与kpi关系数据)
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-5-12 下午2:42:01
	 */
	@Override
	public String importFromXls(InputStream is) {
		String result = "";
		try {
			List<List<String>>[] listArray = ReadExcel.readExcelFile(is);
			for (int j = 0; j < listArray.length; j++) {// sheet页
				for (int i = 1; i < listArray[j].size(); i++) { // column
					List<String> stringList = listArray[j].get(i);// row
					if (stringList != null && stringList.size() > 0) {
						ServiceKpiRelationObj obj = new ServiceKpiRelationObj();
						obj.setKpi_id(stringList.get(0).toString().trim());
						ServiceKpiRelationObj kpiObj = new ServiceKpiRelationObj();
						kpiObj.setNodeType(HadoopConstant.serviceNode);
						if (j == 0) {
							kpiObj.setService_type(HadoopConstant.nameNode);
						} else if (j == 1) {
							kpiObj.setService_type(HadoopConstant.dataNode);
						} else if (j == 2) {
							kpiObj.setService_type(HadoopConstant.journalNode);
						} else if (j == 3) {
							kpiObj.setService_type(HadoopConstant.nodeManager);
						} else if (j == 4) {
							kpiObj.setService_type(HadoopConstant.reduceManager);
						} else if (j == 5) {
							kpiObj.setService_type(HadoopConstant.hmaster);
						} else if (j == 6) {
							kpiObj.setService_type(HadoopConstant.regionServer);
						} else if (j == 7) {
							kpiObj.setService_type(HadoopConstant.hbase_thirftServer);
						} else if (j == 8) {
							kpiObj.setService_type(HadoopConstant.znode);
						} else if (j == 9) {
							kpiObj.setService_type(HadoopConstant.hive_thirftServer);
						} else if (j == 10) {
							kpiObj.setService_type(HadoopConstant.impalaxx);
						} else if (j == 11) {
							kpiObj.setService_type(HadoopConstant.DFSzkFailoverController);
						}
						List<ServiceKpiRelationObj> nameList = serviceKpiRelationDao
								.queryServiceNameList(kpiObj);
						if (nameList != null && nameList.size() > 0) {
							kpiObj = nameList.get(0);
							obj.setService_name(kpiObj.getService_name());
						}
						if (obj.getKpi_id() != null
								&& !"".equals(obj.getKpi_id())
								&& obj.getService_name() != null
								&& !"".equals(obj.getService_name())) {
							serviceKpiRelationDao
									.saveServiceNameKpiRelation(obj);
						}
					}
				}
			}
			result = "success";
		} catch (Exception e) {
			result = "error";
			logger.error("from excel service kpi error:"
					+ e.getMessage());
		}
		return result;
	}

}
