package dao.mapreduce.task;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;

import domain.mapreduce.task.HadoopTaskObj;

/**
 * 
 * <p>
 * Title: HadoopTaskDaoImpl
 * </p>
 * <p>
 * Description: 任务相关操作
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
@Repository("hadoopTaskDao")
public class HadoopTaskDaoImpl extends BaseDao implements HadoopTaskDao {
	/**
	 * 
	 * @Title: queryTaskListByObj
	 * @Description: 任务管理列表
	 * @param
	 * @return List<HadoopTaskObj>
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-1-11 下午4:55:16
	 */
	@Override
	public List<HadoopTaskObj> queryTaskListByObj(HadoopTaskObj obj) {
		List<HadoopTaskObj> list = null;
		try {
			if (obj.getPagination() != null) {
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj.getPagination().setTotalCount(
						((Integer) getSqlMap().queryForObject(
								"HadoopTask.queryTaskListByObjCount", obj))
								.intValue());
			}
			list = getSqlMap().queryForList("HadoopTask.queryTaskListByObj", obj);
		} catch (SQLException e) {
			logger.error("HadoopTask.queryTaskListByObj:" + e.getMessage()
					+ e.getClass().getName());
		}
		return list;
	}
}
