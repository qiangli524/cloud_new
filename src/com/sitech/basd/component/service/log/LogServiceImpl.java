package com.sitech.basd.component.service.log;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sitech.basd.component.dao.log.LogDao;
import com.sitech.basd.component.domain.log.LogObj;
import com.sitech.basd.component.domain.log.TbOperationLogObj;
import com.sitech.basd.component.service.script.ScriptsService;

@Service("logService")
public class LogServiceImpl implements LogService {
	@Resource
	private LogDao logDao;
	@Autowired
	private ScriptsService scriptsService;

	/**
	 * 
	 * @Title: listLog
	 * @Description:查询配置的日志信息列表
	 * @author duangh
	 * @date May 22, 2013 11:03:53 AM
	 * @return 成功List,失败null
	 */
	public List<LogObj> queryListByObj(LogObj obj) {
		return logDao.queryListByObj(obj);
	}

	/**
	 * 
	 * @Title: insertByObj
	 * @Description:增加配置日志信息
	 * @author duangh
	 * @date May 22, 2013 11:05:18 AM
	 * @return 失败-1
	 */
	public int insertByObj(LogObj obj) {
		return logDao.insertByObj(obj);
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
	public int updateByObj(LogObj obj) {
		return logDao.updateByObj(obj);
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
	public int deleteByObj(LogObj obj) {
		return logDao.deleteByObj(obj);
	}

	@Override
	public LogObj queryForObj(LogObj obj) {
		return logDao.queryForObj(obj);
	}

	/**
	 * 
	 * @Title: queryLogDeploy
	 * @Description:应用部署查询日志信息
	 * @author duangh
	 * @date May 28, 2013 5:04:53 PM
	 * @param obj
	 * @return
	 */
	public List<LogObj> queryLogDeploy(LogObj obj) {
		return logDao.queryLogDeploy(obj);
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
		String sysAppId = obj.getAppId();
		String sysAppChildIdStr = scriptsService.getSysAppChildIdStr(sysAppId);
		obj.setEncodeExampleStr(sysAppChildIdStr);
		return logDao.queryLogAppDeploy(obj);
	}

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
	@Override
	public List<TbOperationLogObj> queryOperationLog(TbOperationLogObj obj) {
		return logDao.queryOperationLog(obj);
	}

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
	@Override
	public int deleteOperationLog(TbOperationLogObj obj) {
		return logDao.deleteOperationLog(obj);
	}
}
