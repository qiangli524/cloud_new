package com.sitech.shop.dao.vm;

import java.util.List;

import com.sitech.basd.sxcloud.cloud.domain.vmhost.VMHostObj;
import com.sitech.ssd.ah.nas.domain.VmIpObj;

public interface VirtualMachineDao {
	/**
	 * 
	 * @Title: getVMListForFree
	 * @Description: 获取指定操作系统名称的未被使用的虚拟机
	 * @param
	 * @return List
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime 2014-6-12 上午11:25:23
	 */
	public List getVMListForFree(VMHostObj obj);

	/**
	 * 
	 * @Title: queryOneTemManObj
	 * @Description: 查询单个实体信息
	 * @param
	 * @return VMHostObj
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-8-7 上午11:51:21
	 */
	public VMHostObj queryByObj(VMHostObj obj);

	/**
	 * 
	 * @Title: updateVMHostObj
	 * @Description: 更新虚拟机的相关配置信息
	 * @param
	 * @return int
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime 2014-6-12 下午3:52:46
	 */
	public void updateVMHostObj(VMHostObj ipObj);

	/**
	 * 
	 * @Title: queryIpListByVm
	 * @Description: 查询虚拟机的IP地址列表
	 * @param
	 * @return List
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime 2014-6-14 上午11:06:00
	 */
	public List queryIpListByVm(VmIpObj ip);

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

	/**
	 * 
	 * @Title: updateVmIpObj
	 * @Description: TODO(修改虚拟机对应的IP地址)
	 * @param @param obj
	 * @param @return 设定文件
	 * @return int 返回类型
	 * @author wanglei_bj@si-tech.com.cn
	 * @throws
	 * @createtime 2014-6-19
	 */
	public int updateVmIpObj(VmIpObj obj);
}
