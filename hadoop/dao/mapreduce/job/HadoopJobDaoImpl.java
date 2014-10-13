package dao.mapreduce.job;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;

import domain.mapreduce.job.HadoopJobObj;

/**
 * 
 * <p>
 * Title: HadoopApplicationDaoImpl
 * </p>
 * <p>
 * Description: 作业管理相关操作
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
@Repository("hadoopJobDao")
public class HadoopJobDaoImpl extends BaseDao implements HadoopJobDao {
	/**
	 * 
	 * @Title: queryJobListByObj
	 * @Description: 查询作业管理列表
	 * @param
	 * @return List<HadoopJobObj>
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-1-11 下午4:55:16
	 */
	@Override
	public List<HadoopJobObj> queryJobListByObj(HadoopJobObj obj) {
		List<HadoopJobObj> list = null;
		try {
			if (obj.getPagination() != null) {
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj.getPagination().setTotalCount(
						((Integer) getSqlMap().queryForObject(
								"HadoopJob.queryJobListByObjCount", obj))
								.intValue());
			}
			list = getSqlMap().queryForList("HadoopJob.queryJobListByObj", obj);
		} catch (SQLException e) {
			logger.error("HadoopJob.queryJobListByObj:"
					+ e.getMessage() + e.getClass().getName());
		}
		return list;
	}
}
