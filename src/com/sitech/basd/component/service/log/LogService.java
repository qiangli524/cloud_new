package com.sitech.basd.component.service.log;

import java.util.List;

import com.sitech.basd.component.domain.log.LogObj;
import com.sitech.basd.component.domain.log.TbOperationLogObj;

public interface LogService {
	/**
	 * 
	 * @Title: listLog
	 * @Description:查询配置的日志信息列表
	 * @author duangh
	 * @date May 22, 2013 11:03:53 AM
	 * @return 成功List,失败null
	 */
	public List<LogObj> queryListByObj(LogObj obj);

	/**
	 * 
	 * @Title: insertByObj
	 * @Description:增加配置日志信息
	 * @author duangh
	 * @date May 22, 2013 11:05:18 AM
	 * @return 失败-1
	 */
	public int insertByObj(LogObj obj);

	/**
	 * 
	 * @Title: updateByObj
	 * @Description:更新配置的日志信息
	 * @author duangh
	 * @date May 22, 2013 11:17:32 AM
	 * @param LogObj
	 * @return 失败-1
	 */
	public int updateByObj(LogObj obj);

	/**
	 * 
	 * @Title: deleteByObj
	 * @Description:删除配置的日志信息
	 * @author duangh
	 * @date May 22, 2013 11:18:45 AM
	 * @param LogObj
	 * @return 失败-1
	 */
	public int deleteByObj(LogObj obj);

	/**
	 * 
	 * @Title: queryForObj
	 * @Description:查询一个对象
	 * @author duangh
	 * @date May 23, 2013 1:24:59 PM
	 * @param obj
	 * @return
	 */
	public LogObj queryForObj(LogObj obj);

	/**
	 * 
	 * @Title: queryLogDeploy
	 * @Description:应用部署查询日志信息
	 * @author duangh
	 * @date May 28, 2013 5:04:53 PM
	 * @param obj
	 * @return
	 */
	public List<LogObj> queryLogDeploy(LogObj obj);

	/**
	 * 
	 * @Title: queryLogAppDeploy
	 * @Description:查询一个基准应用下所有实例的日志信息
	 * @author duangh
	 * @date Jul 1, 2013 9:03:52 PM
	 * @param obj
	 * @return
	 */
	public List<LogObj> queryLogAppDeploy(LogObj obj);

	/**
	 * 
	 * @Title: queryOperationLog
	 * @Description: 查询 实例 操作日志
	 * @param
	 * @return List<TbOperationLogObj>
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-12-20 下午3:56:41
	 */
	public List<TbOperationLogObj> queryOperationLog(TbOperationLogObj obj);

	/**
	 * 
	 * @Title: deleteOperationLog
	 * @Description: 删除实例时删除操作日志表中数据
	 * @param
	 * @return int
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-12-20 下午9:23:05
	 */
	public int deleteOperationLog(TbOperationLogObj obj);
}
