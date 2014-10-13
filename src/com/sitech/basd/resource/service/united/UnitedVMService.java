package com.sitech.basd.resource.service.united;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.sitech.basd.resource.domain.united.UnitedTreeObj;
import com.sitech.ssd.ah.nas.domain.VmIpObj;
import com.sitech.utils.exception.HttpClientException;
import com.sitech.vo.united.HostUnitedVO;
import com.sitech.vo.united.NetworkUnitedVO;
import com.sitech.vo.united.SnapshotUnitedVO;
import com.sitech.vo.united.VirtualMachineConsole;
import com.sitech.vo.united.VirtualMachineUnitedVO;

public interface UnitedVMService {

	/**
	 * 
	 * @Title: saveRenameVM
	 * @Description: 针对虚拟机进行重命名
	 * @param
	 * @return String
	 * @throws
	 * @author liudan
	 * @version 1.0
	 * @createtime 2013-11-5 上午11:25:11
	 */
	public String renameVM(UnitedTreeObj unitedTreeObj) throws SQLException;

	/**
	 * 
	 * @Title: getDataCenterCode
	 * @Description: 获取主机所在数据中心的code
	 * @param
	 * @return VirtualMachineUnitedVO
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime 2013-11-2 下午12:16:40
	 */
	public String getDataCenterCode(String hostCode, String connectCode);

	/**
	 * 
	 * @Title: putVMPowerState
	 * @Description: 修改虚拟机电源状态
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Jul 27, 2013 5:19:43 PM
	 */
	public String putVMPowerState(UnitedTreeObj obj) throws HttpClientException;

	/**
	 * 
	 * @Title: putVMPowerState_BD
	 * @Description: 修改虚拟机电源状态(添加重启)-北京电信
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-2-15 下午2:41:44
	 */
	public String putVMPowerState_BD(UnitedTreeObj obj, HttpServletRequest request)
			throws HttpClientException;

	/**
	 * 
	 * @Title: deleteVM
	 * @Description: 删除虚拟机
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @throws SQLException
	 * @createtime Jul 29, 2013 10:12:11 AM
	 */
	public String deleteVM(UnitedTreeObj obj) throws HttpClientException, SQLException;

	/**
	 * 
	 * @Title: createVMByTem
	 * @Description: 通过模板创建虚拟机
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @throws SQLException
	 * @createtime Jul 30, 2013 8:16:45 PM
	 */
	public String createVMByTem(UnitedTreeObj obj, VirtualMachineUnitedVO vo,
			HttpServletRequest request) throws HttpClientException, SQLException, Exception;

	/**
	 * 
	 * @Title: createVMByVM
	 * @Description: 通过虚拟机创建虚拟机
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Jul 30, 2013 8:17:32 PM
	 */
	public String createVMByVM(UnitedTreeObj obj, VirtualMachineUnitedVO vo,
			HttpServletRequest request) throws HttpClientException, SQLException, Exception;

	/**
	 * 
	 * @Title: markAsTemplate
	 * @Description: 将虚拟机转化为模板
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime 2013-9-24 下午5:20:17
	 */
	public String markAsTemplate(UnitedTreeObj obj) throws HttpClientException, SQLException;

	/**
	 * 
	 * @Title: createSnapShot
	 * @Description: 创建虚拟机快照
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Aug 1, 2013 9:43:19 AM
	 */
	public String createSnapShot(UnitedTreeObj obj, SnapshotUnitedVO vo) throws HttpClientException;

	/**
	 * 
	 * @Title: getSnapShotList
	 * @Description: 获取虚拟机快照列表
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Aug 1, 2013 3:00:33 PM
	 */
	public SnapshotUnitedVO getSnapShotList(UnitedTreeObj obj) throws HttpClientException;

	/**
	 * 
	 * @Title: operSnapHost
	 * @Description: 对于快照进行操作
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Aug 1, 2013 7:19:47 PM
	 */
	public String operSnapShot(UnitedTreeObj obj, HttpServletRequest request)
			throws HttpClientException;

	/**
	 * 
	 * @Title: console
	 * @Description: 打开虚拟机控制台
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Aug 1, 2013 4:06:10 PM
	 */
	public String console(UnitedTreeObj obj) throws HttpClientException;

	/**
	 * 
	 * @Title: saveVMInfo
	 * @Description: 保存修改后的虚拟机信息
	 * @param
	 * @return String
	 * @throws
	 * @author lipp
	 * @version 1.0
	 * @createtime Jul 30, 2013 8:17:32 PM
	 */
	public String saveVMInfo(UnitedTreeObj obj, VirtualMachineUnitedVO vo)
			throws HttpClientException, SQLException;

	/**
	 * @Title: migrateVM
	 * @Description: 迁移虚拟机
	 * @param
	 * @return void
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-8-1 下午5:16:58
	 */
	public String migrateVM(UnitedTreeObj obj, VirtualMachineUnitedVO v) throws Exception;

	/**
	 * 
	 * @Title: relocateVM
	 * @Description: 重定位虚拟机
	 * @param
	 * @return String
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2014-6-24 下午3:12:57
	 */
	public String relocateVM(VirtualMachineUnitedVO v) throws Exception;

	/**
	 * 虚拟机控制台
	 * 
	 * @param vi
	 * @param vtype
	 * @return
	 * @throws HttpClientException
	 */
	public VirtualMachineConsole vmConsole(VirtualMachineUnitedVO vi, String vtype)
			throws HttpClientException;

	/**
	 * 
	 * @Title: getXenMigrateHost
	 * @Description: 获取xen虚拟机可以迁移的主机
	 * @param
	 * @return List<TbCloud2HostInfoObj>
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @throws HttpClientException
	 * @createtime Aug 9, 2013 2:54:35 PM
	 */
	public HostUnitedVO getXenMigrateHost(String connectCode, String vmCode)
			throws HttpClientException;

	/**
	 * 
	 * @Title: getVMNetWork
	 * @Description: 查看虚拟机的网络信息
	 * @param
	 * @return VirtualMachineConsole
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @createtime Aug 9, 2013 11:51:51 AM
	 */
	public NetworkUnitedVO getVMNetWork(VirtualMachineUnitedVO obj) throws HttpClientException;

	/**
	 * @Title: deleteVMByBomc
	 * @Description: 通过bomc一键式删除虚拟机
	 * @param
	 * @return String
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-8-29 下午7:52:11
	 */
	public String deleteVMByBomc(UnitedTreeObj uobj);

	/**
	 * 
	 * @Title: getVMDiskList
	 * @Description: 获取虚拟机对应的磁盘列表
	 * @param
	 * @return List
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime 2013-9-17 下午3:41:00
	 */
	public List getVMDiskList(UnitedTreeObj obj);

	/**
	 * 
	 * @Title: importOvf
	 * @Description: TODO(导入OVF文件-实际上就是根据OVF模板创建一个虚拟机)
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @author wanglei_bj@si-tech.com.cn
	 * @throws
	 */
	public String importOvf(UnitedTreeObj obj, VirtualMachineUnitedVO ovf);

	/**
	 * 
	 * @Title: queryVmIpRelation
	 * @Description: 查询虚拟机及IP关联关系
	 * @param
	 * @return List<VmIpObj>
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2014-6-23 下午7:11:32
	 */
	public List<VmIpObj> queryVmIpRelation(VmIpObj obj);

	/**
	 * 
	 * @Title: deleteVmDatastoreRelation
	 * @Description: 删除虚拟机与数据存储关联关系
	 * @param
	 * @return void
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2014-9-16 上午11:40:25
	 */
	public void deleteVmDatastoreRelation(String connectId, String vmCode, String vtype);

	/**
	 * 
	 * @Title: dealVmDatastoreRelation
	 * @Description: 处理虚拟机与数据存储关联关系
	 * @param
	 * @return void
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2014-9-16 上午11:40:25
	 */
	public void dealVmDatastoreRelation(VirtualMachineUnitedVO vo, String vtype);
}
