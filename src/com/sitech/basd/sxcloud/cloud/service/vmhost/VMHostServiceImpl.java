package com.sitech.basd.sxcloud.cloud.service.vmhost;

import java.text.NumberFormat;
import java.util.List;

import com.sitech.basd.sxcloud.cloud.dao.vmhost.VMHostDao;
import com.sitech.basd.sxcloud.cloud.domain.vmhost.VMHostObj;
import com.sitech.basd.sxcloud.cloud.domain.vmhost.VmRelationObj;
import com.sitech.basd.sxcloud.rsmu.service.BaseService;

public class VMHostServiceImpl extends BaseService implements VMHostService {

	@SuppressWarnings({"unused", "unchecked"})
	private static List DefendList = null;

	/**
	 * @Title:删除虚拟机信息
	 * @Copyright: Copyright (c) 201112
	 * @Company: si-tech
	 * @author wangjian
	 * @version 1.0
	 */
	public int deleteByObj(VMHostObj obj) {
		return vmHostDao.deleteByObj(obj);
	}

	/**
	 * @Title:查询出具体虚拟机信息
	 * @Copyright: Copyright (c) 201112
	 * @Company: si-tech
	 * @author wangjian
	 * @version 1.0
	 */
	public VMHostObj queryByObj(VMHostObj obj) {
		return vmHostDao.queryByObj(obj);
	}

	/**
	 * @Title:根据虚拟机部分信息查询匹配的所有虚拟机信息
	 * @Copyright: Copyright (c) 201112
	 * @Company: si-tech
	 * @author wangjian
	 * @version 1.0
	 */
	public List queryForListByObj(VMHostObj obj) {

		return vmHostDao.queryForListByObj(obj);
	}

	/**
	 * @Title：查询虚拟主机作为列表显示
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author wangjian
	 * @version 1.0
	 */
	@SuppressWarnings("unchecked")
	public List queryForListByVMHostObj(VMHostObj obj) {

		return vmHostDao.queryForListByVMHostObj(obj);
	}

	/**
	 * @Title:更新虚拟机信息
	 * @Copyright: Copyright (c) 201112
	 * @Company: si-tech
	 * @author wangjian
	 * @version 1.0
	 */
	public int updateByObj(VMHostObj obj) {
		return vmHostDao.updateByObj(obj);
	}

	/**
	 * 更新虚拟机的电源信息
	 * 
	 * @param obj
	 * @return
	 */
	public int updateVmStateByObj(VMHostObj obj) {
		return vmHostDao.updateVmStateByObj(obj);
	}

	/**
	 * @Title:根据应用ID查询虚拟机信息
	 * @Copyright: Copyright (c) 201112
	 * @Company: si-tech
	 * @author wangjian
	 * @version 1.0
	 */
	@SuppressWarnings("unchecked")
	public List queryVMHostObjByAPPID(VMHostObj obj) {
		return vmHostDao.queryVMHostObjByAPPID(obj);
	}

	/**
	 * 
	 * @Title: updateName
	 * @Description: 只更新虚拟机名称
	 * @param
	 * @return int
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Aug 2, 2012 2:55:14 PM
	 */
	public int updateName(VMHostObj obj) {
		return vmHostDao.updateName(obj);
	}

	/**
	 * 
	 * @Title: queryVhostIdSequence
	 * @Description: 查询虚拟机序列号
	 * @param
	 * @return int
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime Apr 28, 2012 3:28:54 PM
	 */
	public int queryVhostIdSequence() {
		return vmHostDao.queryVhostIdSequence();
	}

	/**
	 * @Title:添加虚拟机信息
	 * @Copyright: Copyright (c) 201112
	 * @Company: si-tech
	 * @author wangjian
	 * @version 1.0
	 */
	public int insertByVMhostObj(VMHostObj obj) {
		return vmHostDao.insertByVMhostObj(obj);
	}

	/**
	 * 
	 * @Title: updateInterByObj
	 * @Description: 更新虚拟机数据--接口采集数据
	 * @param
	 * @return int
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime Oct 26, 2012 5:41:33 PM
	 */
	public int updateInterByObj(VMHostObj obj) {
		return vmHostDao.updateInterByObj(obj);
	}

	/**
	 * 
	 * @Title: updateCpuAndMem
	 * @Description: 只更新虚拟机内存或cpu
	 * @param
	 * @return int
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Aug 2, 2012 2:55:14 PM
	 */
	public int updateCpuAndMem(VMHostObj obj) {
		return vmHostDao.updateCpuAndMem(obj);
	}

	private VMHostDao vmHostDao;

	public void setVmHostDao(VMHostDao vmHostDao) {
		this.vmHostDao = vmHostDao;
	}

	/**
	 * 
	 * @Title: vmResourceByType
	 * @Description: 查询虚拟机资源信息
	 * @param
	 * @return StatisticObj
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Feb 20, 2013 3:10:41 PM
	 */
	public List vmResourceByType(VMHostObj obj) {
		return vmHostDao.vmResourceByType(obj);
	}

	/**
	 * 
	 * @Title: queryForVmList
	 * @Description: 查询所有虚拟机，分页显示
	 * @param
	 * @return List
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime Feb 23, 2013 3:06:54 PM
	 */
	public List<VMHostObj> queryForVmList(VMHostObj obj) {
		List<VMHostObj> lst = vmHostDao.queryForVmList(obj);
		NumberFormat nf = NumberFormat.getIntegerInstance();
		for (VMHostObj vmHostObj : lst) {
			vmHostObj.setVH_STORAGE(nf.format(Double.parseDouble(vmHostObj
					.getVH_STORAGE() == null
					|| "null".equals(vmHostObj.getVH_STORAGE())
					? "1"
					: vmHostObj.getVH_STORAGE()) / 1024));
			vmHostObj.setVH_MEM(vmHostObj.getVH_MEM() == null
					|| vmHostObj.getVH_MEM().equals("null") ? "0" : vmHostObj
					.getVH_MEM());
			vmHostObj.setVH_CPU(vmHostObj.getVH_CPU() == null
					|| vmHostObj.getVH_CPU().equals("null") ? "0" : vmHostObj
					.getVH_CPU());
		}
		return lst;
	}

	/**
	 * 
	 * @Title: synServiceManageVm
	 * @Description: 同步服务管理流程的虚拟机
	 * @param
	 * @return int
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Mar 2, 2013 3:44:51 PM
	 */
	public int synServiceManageVm(VmRelationObj obj) {
		return vmHostDao.synServiceManageVm(obj);
	}

	/**
	 * 
	 * @Title: queryByRelationObj
	 * @Description: 查询一条记录
	 * @param
	 * @return VmRelationObj
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Mar 9, 2013 6:53:36 PM
	 */
	public List queryByRelationObj(VmRelationObj obj) {
		return vmHostDao.queryByRelationObj(obj);
	}

	/**
	 * 
	 * @Title: updateVMHostEqId
	 * @Description: 更新虚拟机关联主机
	 * @param
	 * @return int
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime Mar 12, 2013 1:08:16 PM
	 */
	public int updateVMHostEqId(VMHostObj obj) {
		return vmHostDao.updateVMHostEqId(obj);
	}

	/**
	 * 
	 * @Title: deleteByRelationObj
	 * @Description: 删除一条关系记录
	 * @param
	 * @return int
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Mar 13, 2013 8:41:35 AM
	 */
	public int deleteByRelationObj(VmRelationObj obj) {
		return vmHostDao.deleteByRelationObj(obj);
	}

	/**
	 * 
	 * @Title: updateVMHostMess
	 * @Description: 更新虚拟机的所有信息
	 * @param
	 * @return int
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime Mar 26, 2013 10:57:11 AM
	 */
	public int updateVMHostMess(VMHostObj obj) {
		return vmHostDao.updateVMHostMess(obj);
	}

	/**
	 * 
	 * @Title: updateVmhostType
	 * @Description: 更新虚拟机的类型
	 * @param
	 * @return int
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime May 8, 2013 9:56:17 AM
	 */
	public int updateVmhostType(VMHostObj obj) {
		return vmHostDao.updateVmhostType(obj);
	}

	/**
	 * 
	 * @Title: updateVMHostUnitedInfo
	 * @Description: 统一树更新虚拟机信息
	 * @param
	 * @return int
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-7-31 下午4:31:59
	 */
	public int updateVMHostInfo(VMHostObj obj) {
		return vmHostDao.updateVMHostInfo(obj);
	}

	/**
	 * @Title: queryForListByUUIDList
	 * @Description: 通过uuid集合查询
	 * @param
	 * @return List<VMHostObj>
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-9-9 下午10:41:25
	 */
	@Override
	public List<VMHostObj> queryForListByUUIDList(VMHostObj vmHostObj) {
		// TODO Auto-generated method stub
		return vmHostDao.queryForListByUUIDList(vmHostObj);
	}

	/**
	 * @Title: querySeriousList
	 * @Description: 查询异常
	 * @param
	 * @return List<VMHostObj>
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-9-18 上午9:18:09
	 */
	@Override
	public List<VMHostObj> querySeriousList(VMHostObj vmHostObj) {
		// TODO Auto-generated method stub
		return vmHostDao.querySeriousList(vmHostObj);
	}

	/**
	 * @Title: querySeriousVMList
	 * @Description: 查询异常虚拟机列表
	 * @param
	 * @return List<VMHostObj>
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-9-18 上午10:42:49
	 */
	@Override
	public List<VMHostObj> querySeriousVMList(VMHostObj vmHostObj) {
		// TODO Auto-generated method stub
		return vmHostDao.querySeriousVMList(vmHostObj);
	}

	@Override
	public List<VMHostObj> queryForListByPro(VMHostObj vmHostObj) {
		// TODO Auto-generated method stub
		return vmHostDao.queryForListByPro(vmHostObj);
	}

	/**
	 * @Title: queryVMListForBusi
	 * @Description: 业务中心查询虚拟机列表
	 * @param
	 * @return List<VMHostObj>
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @createtime 2013-9-18 上午10:42:49
	 */
	public List<VMHostObj> queryVMListForBusi(VMHostObj vmHostObj) {
		return vmHostDao.queryVMListForBusi(vmHostObj);
	}

	/**
	 * @Title: queryForWorkOrder
	 * @Description: 查询出符合条件的虚拟机信息
	 * @param
	 * @return VMHostObj
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-9-23 上午9:11:09
	 */
	@Override
	public VMHostObj queryForWorkOrder(VMHostObj vmHostObj) {
		return vmHostDao.queryForWorkOrder(vmHostObj);
	}

	/**
	 * @Title: queryForObjByEntityID
	 * @Description: 根据connectid_vh_uuid查询
	 * @param
	 * @return VMHostObj
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-9-24 下午2:07:22
	 */
	@Override
	public VMHostObj queryForObjByEntityID(VMHostObj paramObj) {
		return vmHostDao.queryForObjByEntityID(paramObj);
	}

	/**
	 * @Title: queryForListByProLeader
	 * @Description: 根据项目负责人查询虚拟机列表
	 * @param
	 * @return List<VMHostObj>
	 * @throws
	 * @author lipp
	 * @version 1.0
	 * @createtime 2013-12-10 下午7:16:24
	 */
	@Override
	public List<VMHostObj> queryForListByProLeader(VMHostObj vmHostObj) {
		return vmHostDao.queryForListByProLeader(vmHostObj);
	}

	@Override
	public List<VMHostObj> queryVMListByClusterOrHost(VMHostObj vmHostObj) {
		return vmHostDao.queryVMListByClusterOrHost(vmHostObj);
	}
}
