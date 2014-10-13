package com.sitech.basd.aspectj.resource;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;

import com.sitech.basd.aspectj.BaseAspectj;
import com.sitech.basd.aspectj.vo.Operation4AConstant;
import com.sitech.basd.aspectj.vo.VirtualTypeEnum;
import com.sitech.basd.resource.domain.united.UnitedTreeObj;
import com.sitech.vo.log.OperationLogVO;
import com.sitech.vo.util.UnitedConstant;

/**
 * 
 * <p>
 * Title: UnitedDataCenterAspectj
 * </p>
 * <p>
 * Description: 统一树数据中心操作切面
 * </p>
 * <p>
 * Copyright: Copyright (c) 2013
 * </p>
 * <p>
 * Company: SI-TECH
 * </p>
 * 
 * @author huojla
 * @version 1.0
 * @createtime 2013-8-27 下午1:58:22
 * 
 */
@Aspect
public class UnitedDataCenterAspectj extends BaseAspectj {

	/**
	 * 
	 * @Title: createCluster
	 * @Description: 创建集群切面
	 * @param
	 * @return void
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-8-28 下午6:45:28
	 */
	@AfterReturning(pointcut = "execution (* com.sitech.basd.resource.service.united.UnitedDataCenterServiceImpl.createDataCenter(..)) &&  args(obj)", returning = "result")
	public void createDataCenter(UnitedTreeObj obj, String result) {
		int resultInt = 1;
		if (UnitedConstant.SUCCESS.equals(result)) {
			resultInt = 0;
		}
		String vtype = obj.getVtype();
		VirtualTypeEnum virtualTypeEnum = getVirtualType(vtype);
		String operDesc = "创建" + virtualTypeEnum + "数据中心 " + obj.getName() + "@"
				+ obj.getConnect_id();
		String modName = getModuleName("add_datacenter");
		OperationLogVO logVo = initOperationLogVO(null, Operation4AConstant.SYS_OPER_MANAGE,
				Operation4AConstant.SYS_CONFIG_MANAGE, operDesc, resultInt, operDesc, modName);
		unitedResourceHandle.dealUnitedLogTo4A(logVo);
	}

	/**
	 * 
	 * @Title: delDataCenter
	 * @Description: 删除数据中心
	 * @param
	 * @return void
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-8-28 下午6:45:28
	 */
	@AfterReturning(pointcut = "execution (* com.sitech.basd.resource.service.united.UnitedDataCenterServiceImpl.delDataCenter(..)) &&  args(obj)", returning = "result")
	public void delDataCenter(UnitedTreeObj obj, String result) {
		int resultInt = 1;
		if (UnitedConstant.SUCCESS.equals(result)) {
			resultInt = 0;
		}
		String vtype = obj.getVtype();
		VirtualTypeEnum virtualTypeEnum = getVirtualType(vtype);
		String operDesc = "删除" + virtualTypeEnum + "数据中心 " + obj.getUuid() + "@"
				+ obj.getConnect_id();
		String modName = getModuleName("delete_datacenter");
		OperationLogVO logVo = initOperationLogVO(null, Operation4AConstant.SYS_OPER_MANAGE,
				Operation4AConstant.SYS_CONFIG_MANAGE, operDesc, resultInt, operDesc, modName);
		unitedResourceHandle.dealUnitedLogTo4A(logVo);
	}
}
