package com.sitech.basd.workflow.dao;

import java.util.List;

import com.sitech.basd.sxcloud.cloud.domain.workorder.WorkOrderObj;
import com.sitech.basd.workflow.domain.WorkOrderResourceObj;

public interface WorkOrderResourceDao {
	/**
	 * @Title: queryForListByObj
	 * @Description: 查询一批记录
	 * @param
	 * @return List<WorkOrderResourceObj>
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-8-21 上午10:10:26
	 */
	public List<WorkOrderResourceObj> queryForListByObj(
			WorkOrderResourceObj workOrderResourceObj) throws Exception;

	/**
	 * @Title: insertByObj
	 * @Description: 根据对象插入
	 * @param
	 * @return int
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-8-21 上午10:10:48
	 */
	public int insertByObj(WorkOrderResourceObj workOrderResourceObj)
			throws Exception;

	/**
	 * @Title: updateByObj
	 * @Description: 根据对象更改
	 * @param
	 * @return int
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-8-21 上午10:10:52
	 */
	public int updateByObj(WorkOrderResourceObj workOrderResourceObj)
			throws Exception;

	/**
	 * @Title: insertWorkOrderTable
	 * @Description: 插入工单表
	 * @param
	 * @return int
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-8-21 下午12:28:40
	 */
	public int insertWorkOrderTable(WorkOrderObj workOrderObj)
			throws Exception;

	/**
	 * @Title: updateWorkOrderTable
	 * @Description: 更新工单表
	 * @param
	 * @return int
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-8-21 下午12:30:12
	 */
	public int updateWorkOrderTable(WorkOrderResourceObj workOrderResourceObj)
			throws Exception;
	/**
	 * @Title: queryByProject
	 * @Description: 通过项目id查对象
	 * @param
	 * @return List<VmHostInfoObj>
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @createtime 2013-8-20 上午16:48:30
	 */
	public List<WorkOrderResourceObj> queryByProject(String projectId)
			throws Exception;

	/**
	 * @Title: queryUsedByProject
	 * @Description: 查询项目已使用资源
	 * @param
	 * @return List<WorkOrderResourceObj>
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-9-5 下午9:33:27
	 */
	public List<WorkOrderResourceObj> queryUsedByProject(String projectId);

	/**
	 * @Title: queryForHostIdByUuid
	 * @Description: 通过工单uuid查询该工单已经占用过哪些主机
	 * @param
	 * @return List<String>
	 * @throws
	 * @author lipp
	 * @version 1.0
	 * @createtime 2013-12-6 上午8:50:12
	 */
	public List<String> queryForHostIdByUuid(WorkOrderResourceObj obj);
}
