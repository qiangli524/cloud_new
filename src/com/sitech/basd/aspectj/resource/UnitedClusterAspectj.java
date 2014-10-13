package com.sitech.basd.aspectj.resource;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;

import com.sitech.basd.aspectj.BaseAspectj;
import com.sitech.basd.aspectj.vo.Operation4AConstant;
import com.sitech.basd.aspectj.vo.VirtualTypeEnum;
import com.sitech.basd.resource.domain.united.UnitedTreeObj;
import com.sitech.vo.log.OperationLogVO;
import com.sitech.vo.united.ClusterUnitedVO;
import com.sitech.vo.util.UnitedConstant;

/**
 * 
 * <p>
 * Title: UnitedClusterAspectj
 * </p>
 * <p>
 * Description: 统一树集群切面
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
public class UnitedClusterAspectj extends BaseAspectj {

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
	@AfterReturning(pointcut = "execution (* com.sitech.basd.resource.service.united.UnitedClusterServiceImpl.createCluster(..)) &&  args(obj,vo)", returning = "result")
	public void createCluster(UnitedTreeObj obj, ClusterUnitedVO vo, String result) {
		int resultInt = 1;
		if (UnitedConstant.SUCCESS.equals(result)) {
			resultInt = 0;
		}
		String vtype = obj.getVtype();
		VirtualTypeEnum virtualTypeEnum = getVirtualType(vtype);
		String operDesc = "创建" + virtualTypeEnum + "集群 " + vo.getClusterName() + "@"
				+ obj.getConnect_id();
		String modName = getModuleName("add_cluster");
		OperationLogVO logVo = initOperationLogVO(null, Operation4AConstant.SYS_OPER_MANAGE,
				Operation4AConstant.SYS_CONFIG_MANAGE, operDesc, resultInt, operDesc, modName);
		unitedResourceHandle.dealUnitedLogTo4A(logVo);
	}

	/**
	 * 
	 * @Title: deleteCluster
	 * @Description: 删除集群
	 * @param
	 * @return void
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-8-28 下午6:45:28
	 */
	@AfterReturning(pointcut = "execution (* com.sitech.basd.resource.service.united.UnitedClusterServiceImpl.deleteCluster(..)) &&  args(obj)", returning = "result")
	public void deleteCluster(UnitedTreeObj obj, String result) {
		int resultInt = 1;
		if (UnitedConstant.SUCCESS.equals(result)) {
			resultInt = 0;
		}
		String vtype = obj.getVtype();
		VirtualTypeEnum virtualTypeEnum = getVirtualType(vtype);
		String operDesc = "删除" + virtualTypeEnum + "集群 " + obj.getUuid() + "@"
				+ obj.getConnect_id();
		String modName = getModuleName("delete_cluster");
		OperationLogVO logVo = initOperationLogVO(null, Operation4AConstant.SYS_OPER_MANAGE,
				Operation4AConstant.SYS_CONFIG_MANAGE, operDesc, resultInt, operDesc, modName);
		unitedResourceHandle.dealUnitedLogTo4A(logVo);
	}
}
