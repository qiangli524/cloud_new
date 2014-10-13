package com.sitech.basd.sxcloud.cloud.dao.vmhost;

import java.util.List;

import com.sitech.basd.sxcloud.cloud.domain.vmhost.VMHostObj;
import com.sitech.basd.sxcloud.cloud.domain.vmhost.VmRelationObj;

@SuppressWarnings("all")
public interface VMHostDao {
	/**
	 * @Title:根据模块ID模糊查询防篡改列表
	 * @Copyright: Copyright (c) 201112
	 * @Company: si-tech
	 * @author wangjian
	 * @version 1.0
	 */
	public List queryVMHostObjByAPPID(VMHostObj obj);

	/**
	 * @Title:根据防篡改部分信息查询匹配的所有防篡改信息
	 * @Copyright: Copyright (c) 201112
	 * @Company: si-tech
	 * @author wangjian
	 * @version 1.0
	 */
	public List queryForListByObj(VMHostObj obj);

	/**
	 * @Title:查询出具体防篡改信息
	 * @Copyright: Copyright (c) 201112
	 * @Company: si-tech
	 * @author wangjian
	 * @version 1.0
	 */
	public VMHostObj queryByObj(VMHostObj obj);

	/**
	 * @Title:更新防篡改信息
	 * @Copyright: Copyright (c) 201112
	 * @Company: si-tech
	 * @author wangjian
	 * @version 1.0
	 */
	public int updateByObj(VMHostObj obj);

	/**
	 * @Title:删除防篡改信息
	 * @Copyright: Copyright (c) 201112
	 * @Company: si-tech
	 * @author wangjian
	 * @version 1.0
	 */
	public int deleteByObj(VMHostObj obj);

	/**
	 * @Title:添加虚拟机信息
	 * @Copyright: Copyright (c) 201112
	 * @Company: si-tech
	 * @author wangjian
	 * @version 1.0
	 */
	public int insertByVMhostObj(VMHostObj obj);

	/**
	 * @Title:根据模块ID集查询所有补充信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author zengls
	 * @version 1.0
	 */
	public List queryForListByVMHostObj(VMHostObj obj);

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
	public int queryVhostIdSequence();

	/**
	 * 
	 * @Title: deleteVhostByObj
	 * @Description: 删除虚拟机信息
	 * @param
	 * @return String
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime Apr 21, 2012 3:27:11 PM
	 */
	public int deleteVhostByObj(VMHostObj obj);

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
	public int updateName(VMHostObj obj);

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
	public int updateCpuAndMem(VMHostObj obj);

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
	public int updateInterByObj(VMHostObj obj);

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
	public List vmResourceByType(VMHostObj obj);

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
	public List<VMHostObj> queryForVmList(VMHostObj obj);

	/**
	 * 更新虚拟机电源状态
	 * 
	 * @param obj
	 * @return
	 */
	public int updateVmStateByObj(VMHostObj obj);

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
	public int synServiceManageVm(VmRelationObj obj);

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
	public List queryByRelationObj(VmRelationObj obj);

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
	public int updateVMHostEqId(VMHostObj obj);

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
	public int deleteByRelationObj(VmRelationObj obj);

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
	public int updateVMHostMess(VMHostObj obj);

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
	public int updateVmhostType(VMHostObj obj);

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
	public int updateVMHostInfo(VMHostObj obj);

	/**
	 * @Title: queryForListByUUIDListString
	 * @Description: 查询hostcode
	 * @param
	 * @return List<String>
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-8-29 下午2:30:30
	 */
	public List<String> queryForListByUUIDListString(VMHostObj vmHostInfoObj);

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
	public List<VMHostObj> queryForListByUUIDList(VMHostObj vmHostObj);

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
	public List<VMHostObj> querySeriousList(VMHostObj vmHostObj);

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
	public List<VMHostObj> querySeriousVMList(VMHostObj vmHostObj);

	public List<VMHostObj> queryForListByPro(VMHostObj vmHostObj);

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
	public List<VMHostObj> queryVMListForBusi(VMHostObj vmHostObj);

	/**
	 * 
	 * @Title: insertVmhostHis
	 * @Description: 当进行创建虚拟机和删除虚拟机的操作时，要插入相应的历史记录表
	 * @param
	 * @return int
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime 2013-9-19 下午4:45:50
	 */
	public int insertVmhostHis(VMHostObj obj);

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
	public VMHostObj queryForWorkOrder(VMHostObj vmHostObj);

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
	public VMHostObj queryForObjByEntityID(VMHostObj paramObj);

	/**
	 * 
	 * @Title: insertIbmLogicalPartition
	 * @Description: 保存IBM逻辑分区数据
	 * @param
	 * @return int
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-11-15 上午10:51:38
	 */
	public int insertIbmLogicalPartition(VMHostObj obj);

	/**
	 * 
	 * @Title: updateIbmLogicalPartition
	 * @Description: 更新IBM逻辑分区数据
	 * @param
	 * @return int
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-11-15 上午10:51:38
	 */
	public int updateIbmLogicalPartition(VMHostObj obj);

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
	public List<VMHostObj> queryForListByProLeader(VMHostObj vmHostObj);

	/**
	 * 
	 * @Title: getVMListByUser
	 * @Description: 查询用户对应的虚拟机列表（北京电信使用）
	 * @param
	 * @return List
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime 2014-4-8 下午8:47:17
	 */
	public List getVMListByUser(VMHostObj vmHostObj);

	/**
	 * 
	 * @Title: queryForCount
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param obj
	 * @param @return 设定文件
	 * @return Integer 返回类型
	 * @author wanglei_bj@si-tech.com.cn
	 * @throws
	 */
	public Integer queryForCount(VMHostObj obj);

	/**
	 * 
	 * @Title: renameVM
	 * @Description: 重命名虚拟机（北京电信）
	 * @param
	 * @return int
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime 2014-6-11 上午10:36:25
	 */
	public int renameVM(VMHostObj obj);

	public List<VMHostObj> queryVMListByClusterOrHost(VMHostObj vmHostObj);

}
