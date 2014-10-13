package com.sitech.basd.sxcloud.cloud.service.workorder_sc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sitech.basd.cloud3.domain.departproject.DepartProjectObj;
import com.sitech.basd.sxcloud.cloud.dao.workorder_sc.WorkOrderDao;
import com.sitech.basd.sxcloud.cloud.domain.workorder_sc.WorkOrderAudit;
import com.sitech.basd.sxcloud.cloud.domain.workorder_sc.WorkOrderObj;

/**
 * 接收bocmc同步资源的工单，进行页面的展示
 * 
 * @author lipengpeng
 * 
 */
@Service("workOrderServiceSc")
public class WorkOrderServiceImpl implements WorkOrderService {

	@Autowired
	private WorkOrderDao workOrderDaoSc;

	/**
	 * @Title: queryForWholeListByObj
	 * @Description: 查询工单和资源的合体
	 * @param
	 * @return List<WorkOrderObj>
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-8-23 上午11:54:21
	 */
	@Override
	public List<WorkOrderObj> queryForWholeListByObj(WorkOrderObj workOrderObj) {
		return workOrderDaoSc.queryForWholeListByObj(workOrderObj);
	}

	/**
	 * @Title: updateByObj
	 * @Description: 更新一条记录
	 * @param
	 * @return
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-8-23 下午2:55:46
	 */
	@Override
	public int updateByObj(WorkOrderObj obj) {
		return workOrderDaoSc.updateByObj(obj);
	}

	/**
	 * @Title: updateWorkOrderTable
	 * @Description: 更新工单表
	 * @param
	 * @return void
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-8-23 下午3:04:30
	 */
	@Override
	public int updateWorkOrderTable(WorkOrderObj obj) {
		return workOrderDaoSc.updateWorkOrderTable(obj);
	}

	/**
	 * @Title: queryProjectList
	 * @Description: 查询项目集合
	 * @param
	 * @return List<DepartProjectObj>
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-8-29 上午10:40:10
	 */
	@Override
	public List<DepartProjectObj> queryProjectList() {
		return workOrderDaoSc.queryProjectList();
	}

	/**
	 * @Title: deleteResourceByObj
	 * @Description: 删除工单下的任务
	 * @param
	 * @return int
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-9-7 下午12:19:59
	 */
	@Override
	public int deleteResourceByObj(WorkOrderObj workOrderObj) {
		// TODO Auto-generated method stub
		return workOrderDaoSc.deleteResourceByObj(workOrderObj);
	}

	/**
	 * @Title: queryByProject
	 * @Description: 查询项目资源使用量
	 * @param
	 * @return List<WorkOrderObj>
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-9-7 下午8:59:52
	 */
	@Override
	public List<WorkOrderObj> queryUsedByProject(String project_ID) {
		// TODO Auto-generated method stub
		return workOrderDaoSc.queryUsedByProject(project_ID);
	}

	/**
	 * @Title: insertWorkOrderTable
	 * @Description: 插入工单表
	 * @param
	 * @return void
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-9-9 上午8:44:30
	 */
	@Override
	public int insertWorkOrderTable(WorkOrderObj obj) {
		// TODO Auto-generated method stub
		return workOrderDaoSc.insertWorkOrderTable(obj);
	}

	/**
	 * @Title: insertResource
	 * @Description: 插入资源表
	 * @param
	 * @return int
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-9-9 上午9:07:47
	 */
	@Override
	public int insertResource(WorkOrderObj workOrderObj) {
		return workOrderDaoSc.insertResource(workOrderObj);
	}

	/**
	 * @Title: queryByProject
	 * @Description: 查询已经创建成功的资源占用量
	 * @param
	 * @return List<WorkOrderObj>
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-9-11 下午7:24:21
	 */
	@Override
	public List<WorkOrderObj> queryByProject(String projectid) {
		// TODO Auto-generated method stub
		return workOrderDaoSc.queryByProject(projectid);
	}

	/**
	 * @Title: deleteWorkOrderByObj
	 * @Description: 删除工单记录
	 * @param
	 * @return int
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-9-12 下午8:04:25
	 */
	@Override
	public int deleteWorkOrderByObj(WorkOrderObj workOrderObj) {
		return workOrderDaoSc.deleteWorkOrderByObj(workOrderObj);
	}

	/**
	 * @Title: queryUnDealedResource
	 * @Description: 查询处理状态不是成功的资源
	 * @param
	 * @return List<WorkOrderObj>
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-10-16 下午2:28:11
	 */
	@Override
	public List<WorkOrderObj> queryUnDealedResource(WorkOrderObj workOrderObj) {
		return workOrderDaoSc.queryUnDealedResource(workOrderObj);
	}

	/**
	 * @Title: queruForHostInfo
	 * @Description: 查询指定主机的相关信息
	 * @param
	 * @return WorkOrderObj
	 * @throws
	 * @author lipp
	 * @version 1.0
	 * @createtime 2013-12-19 下午3:05:46
	 */
	@Override
	public WorkOrderObj queruForHostInfo(WorkOrderObj wobj) {
		return workOrderDaoSc.queruForHostInfo(wobj);
	}

	/**
	 * 查询工单信息
	 */
	@Override
	public WorkOrderObj queruForWorkOrderInfo(WorkOrderObj wobj) {
		return workOrderDaoSc.queruForWorkOrderInfo(wobj);
	}

	/**
	 * 查询所以审批信息
	 */
	@Override
	public WorkOrderObj queruForAuditInfo(WorkOrderObj wobj) {
		return workOrderDaoSc.queruForAuditInfo(wobj);
	}

	@Override
	public String savaAudit(WorkOrderObj wobj) {
		int ret = updateAudit(wobj);
		return null;
	}

	public int updateAudit(WorkOrderObj wobj) {
		return workOrderDaoSc.updateAudit(wobj);
	}

	/**
	 * 审批时插入审批信息
	 */
	@Override
	public int insertAudit(WorkOrderAudit wobj) {

		workOrderDaoSc.insertWorkOrderAudit(wobj);
		return 0;
	}

	/**
	 * 显示审批列表
	 * 
	 * @param host
	 * @return
	 */
	@Override
	public List<WorkOrderAudit> listAudit(WorkOrderAudit wobj) {
		return workOrderDaoSc.queryAuditForList(wobj);
	}

	/**
	 * @Title: queryUsedByWorkorder
	 * @Description: 查询工单中已使用
	 * @param
	 * @return List<WorkOrderObj>
	 * @throws
	 * @author lipp
	 * @version 1.0
	 * @createtime 2013-12-23 上午11:53:58
	 */
	@Override
	public List<WorkOrderObj> queryUsedByWorkorder(WorkOrderObj wobj) {
		return workOrderDaoSc.queryUsedByWorkorder(wobj);
	}

	@Override
	public List<WorkOrderObj> queryByObj(WorkOrderObj workOrderObj) {
		return workOrderDaoSc.queryByObj(workOrderObj);
	}

	@Override
	public List<WorkOrderObj> queryByObjByAudit(WorkOrderObj workOrderObj) {
		return workOrderDaoSc.queryByObjByAudit(workOrderObj);
	}

	@Override
	public List<WorkOrderObj> queryResourceList(WorkOrderObj workOrderObj) {
		return workOrderDaoSc.queryResourceList(workOrderObj);
	}

}
