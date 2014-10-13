package com.sitech.basd.sxcloud.cloud.service.workorder;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.sitech.basd.cloud3.domain.departproject.DepartProjectObj;
import com.sitech.basd.sxcloud.cloud.domain.resource.TbCloud2HostInfoObj;
import com.sitech.basd.sxcloud.cloud.domain.resource.TbCloudEntityUser;
import com.sitech.basd.sxcloud.cloud.domain.workorder.WorkOrderObj;

/**
 * <p>
 * Title: WorkOrderService
 * </p>
 * <p>
 * Description: bomc工单展示逻辑接口
 * </p>
 * <p>
 * Copyright: Copyright (c) 2013
 * </p>
 * <p>
 * Company: SI-TECH
 * </p>
 * 
 * @author lipengpeng
 * @version 1.0
 * @createtime 2013-8-22 下午2:50:07
 * 
 */
public interface WorkOrderService {

	/**
	 * @Title: queryByObj
	 * @Description: 展示工单
	 * @param
	 * @return List<WorkOrderObj>
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-8-22 下午2:50:27
	 */
	public List<WorkOrderObj> queryByObj(WorkOrderObj workOrderObj);

	/**
	 * @Title: queryResourceList
	 * @Description: 展示任务
	 * @param
	 * @return List<WorkOrderObj>
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-8-22 下午2:50:38
	 */
	public List<WorkOrderObj> queryResourceList(WorkOrderObj workOrderObj);

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
	public List<WorkOrderObj> queryForWholeListByObj(WorkOrderObj workOrderObj);

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
	public int updateByObj(WorkOrderObj obj);

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
	public int updateWorkOrderTable(WorkOrderObj obj);

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
	public List<DepartProjectObj> queryProjectList();

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
	public int deleteResourceByObj(WorkOrderObj workOrderObj);

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
	public List<WorkOrderObj> queryUsedByProject(String project_ID);

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
	public int insertWorkOrderTable(WorkOrderObj obj);

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
	public int insertResource(WorkOrderObj workOrderObj);

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
	public List<WorkOrderObj> queryByProject(String projectid);

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
	public int deleteWorkOrderByObj(WorkOrderObj workOrderObj);

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
	public List<WorkOrderObj> queryUnDealedResource(WorkOrderObj workOrderObj);

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
	public WorkOrderObj queruForHostInfo(WorkOrderObj wobj);

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
	public List<WorkOrderObj> queryUsedByWorkorder(WorkOrderObj wobj);

	/**
	 * 
	 * @Title: lockIpStats
	 * @Description:根据工单传递IP进行IP锁定，更新IP使用状态
	 * @param
	 * @return String
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @throws Exception
	 * @createtime 2014-6-28 上午11:31:30
	 */
	public WorkOrderObj lockIpStats(WorkOrderObj workOrderObj, HttpServletRequest request)
			throws Exception;
	/**add by qism **/
	/**
	 * @Title: queryHostObjByIOS
	 * @Description: 通过操作系统查找主机
	 * @param
	 * @return List<TbCloud2HostInfoObj>
	 * @throws
	 * @author qism
	 * @version 1.0
	 * @createtime 2014-7-14 下午7:28:24
	 */
	public List<TbCloud2HostInfoObj> queryHostObjByIOS(String ios);
	/**
	 * @Title: insertTbCloudEntityUser
	 * @Description: 执行插入
	 * @param
	 * @return void
	 * @throws
	 * @author qism
	 * @version 1.0
	 * @createtime 2014-7-14 下午7:54:55
	 */
	public void insertTbCloudEntityUser(TbCloudEntityUser entityObj);
}
