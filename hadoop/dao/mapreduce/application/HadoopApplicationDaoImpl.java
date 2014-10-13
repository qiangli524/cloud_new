package dao.mapreduce.application;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;

import domain.mapreduce.application.HadoopApplicationObj;

/**
 * 
 * <p>
 * Title: HadoopApplicationDaoImpl
 * </p>
 * <p>
 * Description: 应用相关操作
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
 * @createtime 2014-1-11 下午4:56:31
 * 
 */
@Repository("hadoopApplicationDao")
public class HadoopApplicationDaoImpl extends BaseDao implements
		HadoopApplicationDao {
	/**
	 * 
	 * @Title: queryApplicationListByObj
	 * @Description: 查询应用列表
	 * @param
	 * @return List<HadoopApplicationObj>
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-1-11 下午4:55:16
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<HadoopApplicationObj> queryApplicationListByObj(
			HadoopApplicationObj obj) {
		List<HadoopApplicationObj> list = null;
		try {
			if (obj.getPagination() != null) {
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj.getPagination()
						.setTotalCount(
								((Integer) getSqlMap()
										.queryForObject(
												"HadoopApplication.queryApplicationListByObjCount",
												obj)).intValue());
			}
			list = getSqlMap().queryForList(
					"HadoopApplication.queryApplicationListByObj", obj);
		} catch (SQLException e) {
			logger.error("HadoopApplication.queryApplicationListByObj:"
					+ e.getMessage() + e.getClass().getName());
		}
		return list;
	}
}
