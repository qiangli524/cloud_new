package com.sitech.basd.component.dao.log;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.sitech.basd.component.domain.log.LogObj;
import com.sitech.basd.component.domain.log.TbOperationLogObj;
import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;
import com.sitech.utils.encrypt.DoubleEncryptUtils;

@Repository("logDao")
public class LogDaoImpl extends BaseDao implements LogDao {
	/**
	 * 
	 * @Title: listLog
	 * @Description:查询配置的日志信息列表
	 * @author duangh
	 * @date May 22, 2013 11:03:53 AM
	 * @return 成功List,失败null
	 */
	@Override
	public List<LogObj> queryListByObj(LogObj obj) {
		List<LogObj> lst = null;
		try {
			if (obj.getPagination() != null) {
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj.getPagination().setTotalCount(
						((Integer) getSqlMap().queryForObject("hostLog.queryForCount", obj))
								.intValue());
			}
			lst = getSqlMap().queryForList("hostLog.queryForList", obj);
			for (LogObj logObj : lst) {
				if (logObj.getUserPwd() != null && !"".equals(logObj.getUserPwd())) {
					logObj.setUserPwd(DoubleEncryptUtils.decrypt(logObj.getUserPwd()));
				}
			}
		} catch (Exception e) {
			LogHelper.error("hostLog.queryForList:" + e.getMessage() + getClass().getName());
		}
		return lst;
	}

	/**
	 * 
	 * @Title: insertByObj
	 * @Description:增加配置日志信息
	 * @author duangh
	 * @date May 22, 2013 11:05:18 AM
	 * @return 失败-1
	 */
	@Override
	public int insertByObj(LogObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().insert("hostLog.insertByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception e) {
			ret = -1;
			LogHelper.error("hostLog.insertByObj:" + e.getMessage() + getClass().getName());
		}

		return ret;
	}

	/**
	 * 
	 * @Title: updateByObj
	 * @Description:更新配置的日志信息
	 * @author duangh
	 * @date May 22, 2013 11:17:32 AM
	 * @param LogObj
	 * @return 失败-1
	 */
	@Override
	public int updateByObj(LogObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().update("hostLog.updateByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception e) {
			ret = -1;
			LogHelper.error("hostLog.updateByObj:" + e.getMessage() + getClass().getName());
		}

		return ret;
	}

	/**
	 * 
	 * @Title: deleteByObj
	 * @Description:删除配置的日志信息
	 * @author duangh
	 * @date May 22, 2013 11:18:45 AM
	 * @param LogObj
	 * @return 失败-1
	 */
	@Override
	public int deleteByObj(LogObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().delete("hostLog.deleteByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception e) {
			ret = -1;
			LogHelper.error("hostLog.deleteByObj:" + e.getMessage() + getClass().getName());
		}
		return ret;
	}

	/**
	 * 
	 * @Title: queryForObj
	 * @Description:查询一个对象
	 * @author duangh
	 * @date May 23, 2013 1:24:59 PM
	 * @param obj
	 * @return
	 */
	@Override
	public LogObj queryForObj(LogObj obj) {
		LogObj tempObj = null;
		try {
			List<LogObj> list = queryListByObj(obj);
			if (list != null && list.size() > 0) {
				tempObj = list.get(0);
			}
		} catch (Exception e) {
			LogHelper.error("hostLog.queryForObj:" + e.getMessage() + getClass().getName());
		}
		return tempObj;
	}

	/**
	 * 
	 * @Title: queryLogDeploy
	 * @Description:应用部署查询日志信息
	 * @author duangh
	 * @date May 28, 2013 4:59:42 PM
	 * @param obj
	 * @return
	 */
	@Override
	public List<LogObj> queryLogDeploy(LogObj obj) {
		List lst = null;
		try {
			lst = getSqlMap().queryForList("hostLog.queryLogDeploy", obj);
		} catch (Exception e) {
			LogHelper.error("hostLog.queryLogDeploy:" + e.getMessage() + getClass().getName());
		}
		return lst;
	}

	/**
	 * 
	 * @Title: queryLogAppDeploy
	 * @Description:查询一个基准应用下所有实例的日志信息
	 * @author duangh
	 * @date Jul 1, 2013 9:03:52 PM
	 * @param obj
	 * @return
	 */
	@Override
	public List<LogObj> queryLogAppDeploy(LogObj obj) {
		List lst = null;
		try {
			if (obj.getPagination() != null) {
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj.getPagination().setTotalCount(
						((Integer) getSqlMap().queryForObject("hostLog.queryForAppCount", obj))
								.intValue());
			}
			lst = getSqlMap().queryForList("hostLog.queryLogAppDeploy", obj);
		} catch (Exception e) {
			LogHelper.error("hostLog.queryLogAppDeploy:" + e.getMessage() + getClass().getName());
		}
		return lst;
	}

	/**
	 * 
	 * @Title: queryOperationLog
	 * @Description: 查询 实例 操作日志
	 * @param
	 * @return List<TbExampleOperationLogObj>
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-12-20 下午3:56:41
	 */
	@Override
	public List<TbOperationLogObj> queryOperationLog(TbOperationLogObj obj) {
		List<TbOperationLogObj> list = null;
		try {
			if (obj.getPagination() != null) {
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj.getPagination().setTotalCount(
						((Integer) getSqlMap()
								.queryForObject("hostLog.queryOperationLogCount", obj)).intValue());
			}
			list = getSqlMap().queryForList("hostLog.queryOperationLog", obj);
		} catch (SQLException e) {
			logger.error("hostLog.queryOperationLog:" + e.getMessage() + e.getClass().getName());
		}
		return list;
	}

	/**
	 * 
	 * @Title: deleteOperationLog
	 * @Description: 删除实例时删除操作日志表中数据
	 * @param
	 * @return void
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-12-20 下午9:23:05
	 */
	@Override
	public int deleteOperationLog(TbOperationLogObj obj) {
		int ret = 0;
		try {
			Object ob = getSqlMap().delete("hostLog.deleteOperationLog", obj);
			if (ob != null) {
				ret = Integer.parseInt(ob.toString());
			}
		} catch (SQLException e) {
			ret = -1;
			logger.error("hostLog.deleteOperationLog:" + e.getMessage() + e.getClass().getName());
		}
		return ret;
	}
}
