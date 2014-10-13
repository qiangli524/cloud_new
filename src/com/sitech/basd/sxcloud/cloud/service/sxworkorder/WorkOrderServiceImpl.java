package com.sitech.basd.sxcloud.cloud.service.sxworkorder;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sitech.basd.cloud3.domain.departproject.DepartProjectObj;
import com.sitech.basd.sxcloud.cloud.dao.sxworkorder.WorkOrderDao;
import com.sitech.basd.sxcloud.cloud.domain.sxworkorder.OrderTaskObj;
import com.sitech.basd.sxcloud.cloud.domain.sxworkorder.WorkOrderObj;

/**
 * 接收bocmc同步资源的工单，进行页面的展示
 * 
 * @author lipengpeng
 * 
 */
@Service("sxworkOrderService")
public class WorkOrderServiceImpl implements WorkOrderService {

	@Autowired
	private WorkOrderDao workOrderDao;

	@Override
	public List<WorkOrderObj> queryWorkOrderList(WorkOrderObj workOrderObj) {
		return workOrderDao.queryWorkOrderList(workOrderObj);
	}

	@Override
	public List<OrderTaskObj> queryResourceList(OrderTaskObj orderTaskObj) {
		return workOrderDao.queryResourceList(orderTaskObj);
	}

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
		return workOrderDao.queryForWholeListByObj(workOrderObj);
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
	public int updateResourceByObj(OrderTaskObj obj) {
		return workOrderDao.updateResourceByObj(obj);
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
		return workOrderDao.updateWorkOrderTable(obj);
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
		return workOrderDao.queryProjectList();
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
	public int deleteResourceByObj(OrderTaskObj orderTaskObj) {
		// TODO Auto-generated method stub
		return workOrderDao.deleteResourceByObj(orderTaskObj);
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
		return workOrderDao.queryUsedByProject(project_ID);
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
		return workOrderDao.insertWorkOrderTable(obj);
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
	public int insertResource(OrderTaskObj orderTaskObj) {
		return workOrderDao.insertResource(orderTaskObj);
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
		return workOrderDao.queryByProject(projectid);
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
		return workOrderDao.deleteWorkOrderByObj(workOrderObj);
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
		return workOrderDao.queryUnDealedResource(workOrderObj);
	}

}
