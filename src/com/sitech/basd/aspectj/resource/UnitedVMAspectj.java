package com.sitech.basd.aspectj.resource;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;

import com.sitech.basd.aspectj.BaseAspectj;
import com.sitech.basd.aspectj.vo.Operation4AConstant;
import com.sitech.basd.aspectj.vo.VirtualTypeEnum;
import com.sitech.basd.resource.domain.united.UnitedTreeObj;
import com.sitech.basd.resource.util.RevertEntity;
import com.sitech.vo.log.OperationLogVO;
import com.sitech.vo.united.SnapshotUnitedVO;
import com.sitech.vo.united.VirtualMachineUnitedVO;
import com.sitech.vo.util.UnitedConstant;

/**
 * 
 * <p>
 * Title: UnitedVMAspectj
 * </p>
 * <p>
 * Description: 统一树虚拟机操作切面
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
public class UnitedVMAspectj extends BaseAspectj {
	/**
	 * 
	 * @Title: putVMPowerState
	 * @Description: 修改虚拟机电源状态
	 * @param
	 * @return OperationLogVO
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-8-28 下午6:45:28
	 */
	@AfterReturning(pointcut = "execution (* com.sitech.basd.resource.service.united.UnitedVMServiceImpl.putVMPowerState(..)) &&  args(obj,request)", returning = "result")
	public void putVMPowerState(UnitedTreeObj obj, HttpServletRequest request, String result) {
		int resultInt = 1;
		if (UnitedConstant.SUCCESS.equals(result)) {
			resultInt = 0;
		}
		String vtype = obj.getVtype();
		VirtualTypeEnum virtualTypeEnum = getVirtualType(vtype);
		String operDesc = "修改" + virtualTypeEnum + "虚拟机状态 " + obj.getUuid() + "@"
				+ obj.getConnect_id() + " To " + request.getParameter("state");
		String modName = getModuleName("put_vm_powerstatus");
		OperationLogVO logVo = initOperationLogVO(null, Operation4AConstant.SYS_OPER_MANAGE,
				Operation4AConstant.SYS_CONFIG_MANAGE, operDesc, resultInt, operDesc, modName);
		
		unitedResourceHandle.dealUnitedLogTo4A(logVo);
	}

	/**
	 * 
	 * @Title: deleteVM
	 * @Description: 删除虚拟机
	 * @param
	 * @return OperationLogVO
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-8-28 下午6:45:28
	 */
	@AfterReturning(pointcut = "execution (* com.sitech.basd.resource.service.united.UnitedVMServiceImpl.deleteVM(..)) &&  args(obj)", returning = "result")
	public void deleteVM(UnitedTreeObj obj, String result) {
		int resultInt = 1;
		if (UnitedConstant.SUCCESS.equals(result)) {
			resultInt = 0;
		}
		String vtype = obj.getVtype();
		VirtualTypeEnum virtualTypeEnum = getVirtualType(vtype);
		String operDesc = "删除" + virtualTypeEnum + "虚拟机 " + obj.getUuid() + "@"
				+ obj.getConnect_id();
		String modName = getModuleName("delete_virtualmachine");
		OperationLogVO logVo = initOperationLogVO(null, Operation4AConstant.SYS_OPER_MANAGE,
				Operation4AConstant.SYS_CONFIG_MANAGE, operDesc, resultInt, operDesc, modName);
		
		unitedResourceHandle.dealUnitedLogTo4A(logVo);
	}

	/**
	 * 
	 * @Title: createVMByTem
	 * @Description: 根据模板创建虚拟机
	 * @param
	 * @return OperationLogVO
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-8-28 下午6:45:28
	 */
	@AfterReturning(pointcut = "execution (* com.sitech.basd.resource.service.united.UnitedVMServiceImpl.createVMByTem(..)) &&  args(obj,vo,request)", returning = "result")
	public void createVMByTem(UnitedTreeObj obj, VirtualMachineUnitedVO vo,
			HttpServletRequest request, String result) {
		int resultInt = 1;
		if (UnitedConstant.SUCCESS.equals(result)) {
			resultInt = 0;
		}
		String vtype = obj.getVtype();
		VirtualTypeEnum virtualTypeEnum = getVirtualType(vtype);
		String operDesc = "创建" + virtualTypeEnum + "虚拟机 " + vo.getNewVmName() + "@"
				+ obj.getConnect_id() + " By模板Code : " + vo.getVmCode();
		String modName = getModuleName("create_vm_by_template");
		OperationLogVO logVo = initOperationLogVO(null, Operation4AConstant.SYS_OPER_MANAGE,
				Operation4AConstant.SYS_CONFIG_MANAGE, operDesc, resultInt, operDesc, modName);
		
		unitedResourceHandle.dealUnitedLogTo4A(logVo);
	}

	/**
	 * 
	 * @Title: createVMByVM
	 * @Description: 根据虚拟机克隆虚拟机
	 * @param
	 * @return OperationLogVO
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-8-28 下午6:45:28
	 */
	@AfterReturning(pointcut = "execution (* com.sitech.basd.resource.service.united.UnitedVMServiceImpl.createVMByVM(..)) &&  args(obj,vo,request)", returning = "result")
	public void createVMByVM(UnitedTreeObj obj, VirtualMachineUnitedVO vo,
			HttpServletRequest request, String result) {
		int resultInt = 1;
		if (UnitedConstant.SUCCESS.equals(result)) {
			resultInt = 0;
		}
		String vtype = obj.getVtype();
		VirtualTypeEnum virtualTypeEnum = getVirtualType(vtype);
		String operDesc = "创建" + virtualTypeEnum + "虚拟机 " + vo.getNewVmName() + "@"
				+ obj.getConnect_id() + " By虚拟机Code : " + vo.getVmCode();
		String modName = getModuleName("create_vm_by_vm");
		OperationLogVO logVo = initOperationLogVO(null, Operation4AConstant.SYS_OPER_MANAGE,
				Operation4AConstant.SYS_CONFIG_MANAGE, operDesc, resultInt, operDesc, modName);
		
		unitedResourceHandle.dealUnitedLogTo4A(logVo);
	}

	/**
	 * 
	 * @Title: createSnapShot
	 * @Description: 创建虚拟机快照
	 * @param
	 * @return OperationLogVO
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-8-28 下午6:45:28
	 */
	@AfterReturning(pointcut = "execution (* com.sitech.basd.resource.service.united.UnitedVMServiceImpl.createSnapShot(..)) &&  args(obj,vo)", returning = "result")
	public void createSnapShot(UnitedTreeObj obj, SnapshotUnitedVO vo, String result) {
		int resultInt = 1;
		if (UnitedConstant.SUCCESS.equals(result)) {
			resultInt = 0;
		}
		String vtype = obj.getVtype();
		VirtualTypeEnum virtualTypeEnum = getVirtualType(vtype);
		String operDesc = "创建" + virtualTypeEnum + "虚拟机快照  " + obj.getUuid() + "@"
				+ obj.getConnect_id();
		String modName = getModuleName("create_vm_snapshot");
		OperationLogVO logVo = initOperationLogVO(null, Operation4AConstant.SYS_OPER_MANAGE,
				Operation4AConstant.SYS_CONFIG_MANAGE, operDesc, resultInt, operDesc, modName);
		
		unitedResourceHandle.dealUnitedLogTo4A(logVo);
	}

	/**
	 * 
	 * @Title: operSnapShot
	 * @Description: 虚拟机快照操作
	 * @param
	 * @return OperationLogVO
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-8-28 下午6:45:28
	 */
	@AfterReturning(pointcut = "execution (* com.sitech.basd.resource.service.united.UnitedVMServiceImpl.operSnapShot(..)) &&  args(obj,request)", returning = "result")
	public void operSnapShot(UnitedTreeObj obj, HttpServletRequest request, String result) {
		int resultInt = 1;
		if (UnitedConstant.SUCCESS.equals(result)) {
			resultInt = 0;
		}
		String vtype = obj.getVtype();
		VirtualTypeEnum virtualTypeEnum = getVirtualType(vtype);
		String oper = request.getParameter("oper");
		String snapshotCode = request.getParameter("snapshotCode");
		String operDesc = "";
		if ("recover".equals(oper)) {
			operDesc = "" + virtualTypeEnum + "虚拟机快照恢复  " + obj.getUuid() + "@"
					+ obj.getConnect_id() + " 快照Code ： " + snapshotCode;
		} else if ("delete".equals(oper)) {
			operDesc = "" + virtualTypeEnum + "虚拟机快照移除  " + obj.getUuid() + "@"
					+ obj.getConnect_id() + " 快照Code ： " + snapshotCode;
		}
		String modName = getModuleName("oper_vm_snapshot");
		OperationLogVO logVo = initOperationLogVO(null, Operation4AConstant.SYS_OPER_MANAGE,
				Operation4AConstant.SYS_CONFIG_MANAGE, operDesc, resultInt, operDesc, modName);
		
		unitedResourceHandle.dealUnitedLogTo4A(logVo);
	}

	/**
	 * 
	 * @Title: console
	 * @Description: 虚拟机控制台
	 * @param
	 * @return OperationLogVO
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-8-28 下午6:45:28
	 */
	@AfterReturning(pointcut = "execution (* com.sitech.basd.resource.service.united.UnitedVMServiceImpl.console(..)) &&  args(obj)", returning = "result")
	public void console(UnitedTreeObj obj, String result) {
		int resultInt = 1;
		if (UnitedConstant.SUCCESS.equals(result)) {
			resultInt = 0;
		}
		String vtype = obj.getVtype();
		VirtualTypeEnum virtualTypeEnum = getVirtualType(vtype);
		String operDesc = "" + virtualTypeEnum + "虚拟机控制台打开  " + obj.getConnect_id();
		String modName = getModuleName("vm_console");
		OperationLogVO logVo = initOperationLogVO(null, Operation4AConstant.SYS_OPER_MANAGE,
				Operation4AConstant.SYS_CONFIG_MANAGE, operDesc, resultInt, operDesc, modName);
		
		unitedResourceHandle.dealUnitedLogTo4A(logVo);
	}

	/**
	 * 
	 * @Title: saveVMInfo
	 * @Description: 编辑虚拟机配置
	 * @param
	 * @return OperationLogVO
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-8-28 下午6:45:28
	 */
	@AfterReturning(pointcut = "execution (* com.sitech.basd.resource.service.united.UnitedVMServiceImpl.saveVMInfo(..)) &&  args(obj,vo)", returning = "result")
	public void saveVMInfo(UnitedTreeObj obj, VirtualMachineUnitedVO vo, String result) {
		int resultInt = 1;
		if (UnitedConstant.SUCCESS.equals(result)) {
			resultInt = 0;
		}
		String vtype = obj.getVtype();
		VirtualTypeEnum virtualTypeEnum = getVirtualType(vtype);
		VirtualMachineUnitedVO unitedVo = RevertEntity.toVirtualMachineUnitedVO(obj, vo);
		String operDesc = "" + virtualTypeEnum + "虚拟机" + unitedVo.getVmCode() + "@"
				+ unitedVo.getConnectCode() + "调整  " + "内存 ： " + unitedVo.getMemoryMB() + "Cpu : "
				+ unitedVo.getNumCPUs() + "存储 ： " + unitedVo.getStorageSizeInMb();
		String modName = getModuleName("vm_edit");
		OperationLogVO logVo = initOperationLogVO(null, Operation4AConstant.SYS_OPER_MANAGE,
				Operation4AConstant.SYS_CONFIG_MANAGE, operDesc, resultInt, operDesc, modName);
		
		unitedResourceHandle.dealUnitedLogTo4A(logVo);
	}

	/**
	 * 
	 * @Title: migrateVM
	 * @Description: 迁移虚拟机
	 * @param
	 * @return OperationLogVO
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-8-28 下午6:45:28
	 */
	@AfterReturning(pointcut = "execution (* com.sitech.basd.resource.service.united.UnitedVMServiceImpl.migrateVM(..)) &&  args(obj)", returning = "result")
	public void migrateVM(UnitedTreeObj obj, String result) {
		int resultInt = 1;
		if (UnitedConstant.SUCCESS.equals(result)) {
			resultInt = 0;
		}
		String vtype = obj.getVtype();
		VirtualTypeEnum virtualTypeEnum = getVirtualType(vtype);
		String operDesc = "" + virtualTypeEnum + "虚拟机" + obj.getName() + " Code : " + obj.getUuid()
				+ "@" + obj.getConnect_id() + " 迁移到宿主机 " + obj.getParent_uuid();
		String modName = getModuleName("vm_edit");
		OperationLogVO logVo = initOperationLogVO(null, Operation4AConstant.SYS_OPER_MANAGE,
				Operation4AConstant.SYS_CONFIG_MANAGE, operDesc, resultInt, operDesc, modName);
		unitedResourceHandle.dealUnitedLogTo4A(logVo);
	}
}
