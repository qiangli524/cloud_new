package com.sitech.basd.scheduler.service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import scheduler.SchedulerParameterObj;

import com.sitech.basd.scheduler.SchedulerConstant;
import com.sitech.basd.scheduler.dao.task.ResourceTaskDao;
import com.sitech.basd.scheduler.domain.task.ResourceTaskObj;
import com.sitech.basd.scheduler.domain.task.ResourceTaskObjExample;
import com.sitech.basd.scheduler.domain.task.ResourceTaskObjExample.Criteria;
import com.sitech.basd.scheduler.job.ResourceCreateJob;
import com.sitech.basd.scheduler.job.ResourcePowerOffJob;
import com.sitech.basd.scheduler.job.ResourcePowerOnJob;
import com.sitech.basd.scheduler.job.ResourceRecycleJob;
import com.sitech.basd.sxcloud.cloud.dao.vmhost.VMHostDao;
import com.sitech.basd.sxcloud.cloud.domain.vmhost.VMHostObj;
import com.sitech.basd.util.SchedulerUtil;
import com.sitech.ssd.sx.OperateVMObj;
import com.sitech.ssd.sx.OperateVMResultObj;
import com.sitech.utils.common.CommonUtil;
import com.sitech.utils.jackson.JacksonUtil;
import com.sitech.utils.randomid.RandomUUID;

/**
 * <p>Title: ResourceSchedulerService</p>
 * <p>Description: 资源定时调度</p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: SI-TECH</p>
 * @author liming_bj
 * @version 1.0
 * @createtime 2013-11-09 12:20:02
 * 
 */
@Service("resourceSchedulerService")
public class ResourceSchedulerService {

	@Autowired
	private ResourceTaskDao resourceTaskDao;

	@Autowired
	private VMHostDao vMHostDao;

	/**
	 * 
	 * @Title: receptionMessage
	 * @Description: 根据任务执行结果变更
	 * @param
	 * @return void
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-11-14 下午3:57:18
	 */
	public void receptionMessage(String processStr) {
		OperateVMResultObj operateVMResultObj = JacksonUtil.fromJSON(processStr, new JacksonUtil.TypeReference<OperateVMResultObj>() {});
		
		// 判断是否执行成功
		if (operateVMResultObj.getIsSuccess()) {

			// 任务表tb_resource_scheduler_task 任务执行状态 变更为已经执行 ResourceTaskObj resourceTaskObj = new ResourceTaskObj();
			ResourceTaskObj resourceTaskObj = new ResourceTaskObj();
			resourceTaskObj.setExecuteState("1");
			ResourceTaskObjExample resourceTaskObjExample = new ResourceTaskObjExample();
			resourceTaskObjExample.createCriteria().andTaskIdEqualTo(operateVMResultObj.getTaskId());
			resourceTaskDao.updateByExampleSelective(resourceTaskObj, resourceTaskObjExample);

			// 取得要执行的动作(假如可以返回动作可以去掉此操作)
			ResourceTaskObjExample resourceTaskObjExample2 = new ResourceTaskObjExample();
			resourceTaskObjExample2.createCriteria().andTaskIdEqualTo(operateVMResultObj.getTaskId());
			ResourceTaskObj resourceTaskObj2 = resourceTaskDao.selectByExample(resourceTaskObjExample2);

			VMHostObj vMHostObj = new VMHostObj();
			// 要更新的虚拟机VH_UUID
			vMHostObj.setVH_UUID(operateVMResultObj.getVmId());
			// 要更新的动作 0:关机 1:开机4  3
			                                   
			if(resourceTaskObj2.getExecuteAction().equals("3")){
				vMHostObj.setVH_STAT("1");
			}
			if(resourceTaskObj2.getExecuteAction().equals("4")){
				vMHostObj.setVH_STAT("0");
			}
			
			// 虚拟机表tb_cloud2_vmhost_info 变更相应的状态 0: 关闭 1: 运行中
			vMHostDao.updateVMHostInfo(vMHostObj);
		} else {
			ResourceTaskObj resourceTaskObj = new ResourceTaskObj();
			//执行失败
			resourceTaskObj.setExecuteState("error");
			ResourceTaskObjExample resourceTaskObjExample = new ResourceTaskObjExample();
			resourceTaskObjExample.createCriteria().andTaskIdEqualTo(operateVMResultObj.getTaskId());
			resourceTaskDao.updateByExampleSelective(resourceTaskObj, resourceTaskObjExample);
		}

	}
													 

	/**
	 * 
	 * @param jobMap 任务ID主键 (建议用UUID)
	 * @param TaskType 任务类型 0:创建虚拟机 2:回收虚拟资源 3:开机任务 4:关机任务
	 * @param 
	 * @throws SchedulerException
	 * @throws ParseException 
	 */
	public void createResourceTask(List<String> taskIdList, int TaskType, SchedulerParameterObj schedulerParameterObj) throws SchedulerException, ParseException {
		switch (TaskType) {
		// 新增创建虚拟机任务
		case 0:
			schedulerParameterObj.setJobClass(ResourceCreateJob.class);
			break;
		// 新增回收任务
		case 2:
			schedulerParameterObj.setJobClass(ResourceRecycleJob.class);
			break;
		// 新增开机任务
		case 3:
			schedulerParameterObj.setJobClass(ResourcePowerOnJob.class);
			break;
		// 新增关机任务
		case 4:
			schedulerParameterObj.setJobClass(ResourcePowerOffJob.class);
			break;

		default:
			break;
		}
		for (String id : taskIdList) {
			schedulerParameterObj.setParamValue(id);
			SchedulerUtil.addJob(id, SchedulerConstant.RESOURCE_GROUP, schedulerParameterObj);
		}
	}

	public void insert(ResourceTaskObj obj) throws Exception {
		//创建时间
		obj.setCreatedDate(new Date());
		resourceTaskDao.insertBySelective(obj);
	}

	/**
	 * 根据主键查找对象
	 * @param ByPrimaryKey 主键ID
	 * @return
	 */
	public ResourceTaskObj selectByPrimaryKey(String string) {
		return resourceTaskDao.selectByPrimaryKey(string);
	}




	/**
	 * 分页查询任务表中的记录
	 * @param obj 条件对象
	 * @return list
	 */
	public List <?>selectByExampleList(ResourceTaskObjExample obj) {
		return resourceTaskDao.selectByExampleList(obj);
	}


	/**
	 * 删除任务记录
	 * 同时删除调度器中任务
	 * @param id
	 * @throws Exception
	 */
	public void deleteByPrimaryKey(String id) throws Exception {

		// 删除调度器中的任务
		try {
			String[] taskid_uuid = id.split("_");
			SchedulerUtil.deleteJob(taskid_uuid[0], SchedulerConstant.RESOURCE_GROUP);
			// 删除任务表中的记录
			resourceTaskDao.deleteByPrimaryKey(taskid_uuid[0]);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}


	/**
	 * 获取发送队列的实体类
	 * @param taskId 任务表主键
	 * @return OperateVMObj
	 */
	public OperateVMObj getOperateVMObj(String taskId) {

		//查询任务对象
		ResourceTaskObjExample obj = new ResourceTaskObjExample();
		obj.createCriteria().andTaskIdEqualTo(taskId);
		ResourceTaskObj resourceTaskObj = resourceTaskDao.selectByExample(obj);
		// 队列实体
		OperateVMObj operateVMObj = new OperateVMObj();
		// 放入VH_UUID
		operateVMObj.setVmId(resourceTaskObj.getVhUuid());
		// 放入虚拟机类型
		operateVMObj.setType(resourceTaskObj.getVmType());
		//任务表主键
		operateVMObj.setTaskId(taskId);

		return operateVMObj;
	}


	/**
	 * 将选择的虚拟机增加到任务表
	 * @param uuid
	 * @throws Exception 
	 */
	@SuppressWarnings("all")
	public void addvm(String[] uuid) throws Exception {

		VMHostObj vmhob = new VMHostObj();
		// 选中的UUID
		List<String> selectList = CollectionUtils.arrayToList(uuid);
		vmhob.setUuidList(selectList);
		List<VMHostObj> vmInfoList = vMHostDao.queryForListByUUIDList(vmhob);
		ResourceTaskObj resourceTaskObj = new ResourceTaskObj();
		for (VMHostObj vMHostObj : vmInfoList) {
			resourceTaskObj.setTaskId(RandomUUID.getUuid());
			resourceTaskObj.setVhId(vMHostObj.getVH_ID());
			resourceTaskObj.setVhUuid(vMHostObj.getVH_UUID());
			resourceTaskObj.setVhName(vMHostObj.getVH_NAME());
			resourceTaskObj.setVhIp(CommonUtil.isNotEmpty(vMHostObj.getVH_IP())?vMHostObj.getVH_IP():"");
			resourceTaskObj.setVmType(vMHostObj.getVH_TYPE());
			resourceTaskObj.setCreatedDate(new Date());
			resourceTaskDao.insertBySelective(resourceTaskObj);
		}
	}

	@SuppressWarnings("all")
	public List exclude() {

		// 已经加入的list
		List yetList = new ArrayList<String>();
		// 创建条件实体
		ResourceTaskObjExample rtobj = new ResourceTaskObjExample();
		// 建立条件对象
		Criteria criteria = rtobj.createCriteria();
		criteria.andExecuteActionIsNull();
		// 查询数据
		List<ResourceTaskObj> resultList = resourceTaskDao.selectByExampleList(rtobj);
		for (ResourceTaskObj result : resultList) {
			yetList.add(result.getVhUuid());
		}
		return yetList;
	}

	/**
	 * 更新任务表
	 * @param rto
	 */
	public void update(ResourceTaskObj rto) {
		ResourceTaskObjExample resourceTaskObjExample=new ResourceTaskObjExample();
		resourceTaskObjExample.createCriteria().andTaskIdEqualTo(rto.getTaskId());
		resourceTaskDao.updateByExampleSelective(rto, resourceTaskObjExample);
	}

}
