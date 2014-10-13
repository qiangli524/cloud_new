package com.sitech.basd.component.service.process;

import java.util.List;

import com.sitech.basd.component.domain.process.ProcessObj;

public interface ProcessService {

	/**
	 * 查询进程的信息列表
	 * 
	 * @param obj
	 * @return
	 */
	public List<ProcessObj> queryProcessInfoList(ProcessObj obj);

	/**
	 * 查询进程和部署实例，应用关系表的数据
	 * 
	 * @param obj
	 * @return
	 */
	public List<ProcessObj> queryForDeployList(ProcessObj obj);

	/**
	 * 插入数据
	 * 
	 * @param obj
	 * @return
	 */
	public int insertByObj(ProcessObj obj);

	/**
	 * 删除数据
	 * 
	 * @param obj
	 * @return
	 */
	public int delByObj(ProcessObj obj);

	/**
	 * 更新部署实例中的进程信息
	 * 
	 * @param obj
	 * @return
	 */
	public int updateProcessByObj(ProcessObj obj);

	/**
	 * 查询基准应用下所有的进程信息
	 * 
	 * @param obj
	 * @return
	 */
	public List<ProcessObj> queryForAppProcess(ProcessObj obj);

	/**
	 * 查询进程数量
	 * 
	 * @param processObj
	 * @return
	 */
	public int countProcess(ProcessObj processObj);

	public List<ProcessObj> queryAllMappingsByObj(ProcessObj processObj);

	public List<ProcessObj> queryUnNormalProcess(ProcessObj obj);

	public int countProcessUnNor();

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
	public List<ProcessObj> queryAppProcessList(ProcessObj obj);
	
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
	public List<ProcessObj> queryHadoopHostNodeProcess(ProcessObj obj);

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
	public List<ProcessObj> queryHadoopOtherNodeProcess(ProcessObj obj);

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
	public int deleteRelation(ProcessObj obj);

	/**
	 * @Title: queryHadoopServiceNodeStatus
	 * @Description: 查询hadoop进程正常异常
	 * @param
	 * @return List<ProcessObj>
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @param pObj 
	 * @createtime 2014-1-18 下午5:18:52
	 */
	public List<ProcessObj> queryHadoopServiceNodeStatus(ProcessObj pObj);

}
