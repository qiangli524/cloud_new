package com.sitech.ssd.ah.nas.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.ssd.ah.nas.domain.NasKpiObj;

@Repository("nasKpiDao")
public class NasKpiDaoImpl extends BaseDao implements NasKpiDao {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	/**
	 * @Title: queryNasKpiByObj
	 * @Description: 查询naskpi
	 * @param
	 * @return 
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @throws Exception 
	 * @createtime 2014年4月4日14:48:06
	 */
	public List<NasKpiObj> queryNasKpiByObj(NasKpiObj obj){
		List<NasKpiObj> list = new ArrayList<NasKpiObj>();
		try {
			list = sqlMapClient.queryForList("nasKpi.queryAllkpiByObj", obj);
		} catch (Exception sqlException) {
			logger.error("nasKpi.queryAllkpiByObj:" + sqlException.getMessage()
					+ getClass().getName());
		}
		return list;
	}
	
	
	/**
	 * @Title: insertKpiByObj
	 * @Description: 插入kpi指标
	 * @param
	 * @return 
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @throws Exception 
	 * @createtime 2014年3月26日9:19:51
	 */
	public void insertKpiByObj(NasKpiObj obj){
		try {
			sqlMapClient.insert("nasKpi.insertKpiByObj", obj);
		} catch (SQLException e) {
			logger.error("nasKpi.insertKpiByObj:" + e.getMessage()
					+ getClass().getName());
		}
	}
	
	
	/**
	 * @Title: updateKpiByObj
	 * @Description: 更新nasKpi
	 * @param
	 * @return 
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @throws Exception 
	 * @createtime 2014年3月26日9:19:51
	 */
	public void updateKpiByObj(NasKpiObj obj){
		try {
			sqlMapClient.update("nasKpi.updateKpiByObj", obj);
		} catch (SQLException e) {
			logger.error("nasKpi.updateKpiByObj:" + e.getMessage()
					+ getClass().getName());
		}
	}
}
