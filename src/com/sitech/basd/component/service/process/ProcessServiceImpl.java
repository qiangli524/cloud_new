package com.sitech.basd.component.service.process;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sitech.basd.component.dao.process.ProcessDao;
import com.sitech.basd.component.domain.process.ProcessObj;
import com.sitech.basd.component.service.script.ScriptsService;

@Service("processService")
public class ProcessServiceImpl implements ProcessService {
	@Autowired
	private ProcessDao processDao;
	@Autowired
	private ScriptsService scriptsService;

	@Override
	public List<ProcessObj> queryProcessInfoList(ProcessObj obj) {
		return processDao.queryProcessInfoList(obj);
	}

	@Override
	public int insertByObj(ProcessObj obj) {
		return processDao.insertByObj(obj);
	}

	@Override
	public int delByObj(ProcessObj obj) {
		return processDao.delByObj(obj);
	}

	@Override
	public List<ProcessObj> queryForDeployList(ProcessObj obj) {
		return processDao.queryForDeployList(obj);
	}

	@Override
	public int updateProcessByObj(ProcessObj obj) {
		return processDao.updateProcessByObj(obj);
	}

	@Override
	public List<ProcessObj> queryForAppProcess(ProcessObj obj) {
		String sysAppId = obj.getEXAMPLE_ID();
		String sysAppChildIdStr = scriptsService.getSysAppChildIdStr(sysAppId);
		obj.setEncodeExampleStr(sysAppChildIdStr);
		return processDao.queryForAppProcess(obj);
	}

	@Override
	public int countProcess(ProcessObj processObj) {
		return processDao.countProcess(processObj);
	}

	@Override
	public List<ProcessObj> queryAllMappingsByObj(ProcessObj processObj) {
		// TODO Auto-generated method stub
		return processDao.queryAllMappingsByObj(processObj);
	}

	@Override
	public List<ProcessObj> queryUnNormalProcess(ProcessObj obj) {
		// TODO Auto-generated method stub
		return processDao.queryUnNormalProcess(obj);
	}

	@Override
	public int countProcessUnNor() {
		// TODO Auto-generated method stub
		return processDao.countProcessUnNor();
	}

	/**
	 * 
	 * @Title: queryAppProcessList
	 * @Description: 查询进程列表，一般用于Excel
	 * @param
	 * @return List<ProcessObj>
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2014-1-5 下午4:08:54
	 */
	@Override
	public List<ProcessObj> queryAppProcessList(ProcessObj obj) {
		String sysAppId = obj.getEXAMPLE_ID();
		String sysAppChildIdStr = scriptsService.getSysAppChildIdStr(sysAppId);
		obj.setEncodeExampleStr(sysAppChildIdStr);
		return processDao.queryAppProcessList(obj);
	}

	/**
	 * 
	 * @Title: queryHadoopHostNodeProcess
	 * @Description: 查询Hadoop主机节点进程列表
	 * @param
	 * @return List<ProcessObj>
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-1-9 下午6:16:33
	 */
	@Override
	public List<ProcessObj> queryHadoopHostNodeProcess(ProcessObj obj) {
		return processDao.queryHadoopHostNodeProcess(obj);
	}

	/**
	 * 
	 * @Title: queryHadoopOtherNodeProcess
	 * @Description: 查询Hadoop其他节点的进程列表
	 * @param
	 * @return List<ProcessObj>
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-1-9 下午6:18:41
	 */
	@Override
	public List<ProcessObj> queryHadoopOtherNodeProcess(ProcessObj obj) {
		return processDao.queryHadoopOtherNodeProcess(obj);
	}

	/**
	 * 
	 * @Title: deleteRelation
	 * @Description: 删除节点和进程的关系
	 * @param
	 * @return int
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-1-9 下午6:20:38
	 */
	@Override
	public int deleteRelation(ProcessObj obj) {
		return processDao.deleteRelation(obj);
	}

	/**
	 * @Title: queryHadoopServiceNodeStatus
	 * @Description: 查询hadoop进程正常异常
	 * @param
	 * @return List<ProcessObj>
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2014-1-18 下午5:18:52
	 */
	@Override
	public List<ProcessObj> queryHadoopServiceNodeStatus(ProcessObj pObj) {
		return processDao.queryHadoopServiceNodeStatus(pObj);
	}
}
