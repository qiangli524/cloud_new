package com.sitech.shop.webservice.service;

import publiccloud.SystemIpAndPassword;

import com.sitech.ssd.billing.vo.resourceInfo.VmInfo;

public interface ResourceDealService {

	/**
	 * @param
	 * @return VmInfo
	 * @throws
	 * @Title: applyVM
	 * @Description: 申请虚拟机
	 * @author taoxue
	 * @version 1.0
	 * @createtime 2014-6-6 上午9:24:27
	 */
	public VmInfo applyVMProcess(VmInfo info) throws Exception;

	/**
	 * 
	 * @Title: allocateRescoure
	 * @Description: 分配资源
	 * @param
	 * @return void
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime 2014-8-21 上午10:47:25
	 */
	public void allocateRescoure(VmInfo info) throws Exception;

	/**
	 * 
	 * @Title: changeIPAndpassword
	 * @Description: 生成虚拟机的密码及IP地址操作类
	 * @param
	 * @return void
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime 2014-6-14 上午9:58:00
	 */
	public SystemIpAndPassword generateSystemIpAndPassword(String vlan_id, String vmCode,
			String connectCode, String vlan_type) throws Exception;

	/**
	 * 
	 * @Title: changeIpAndPassword
	 * @Description: 修改虚拟机的IP地址
	 * @param
	 * @return void
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime 2014-6-14 上午11:37:09
	 */
	public boolean changeIp(String system_type, SystemIpAndPassword sys) throws Exception;

	/**
	 * 
	 * @Title: changePassword
	 * @Description: 修改虚拟机密码
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime 2014-6-19 上午10:56:17
	 */
	public boolean changePassword(String system_type, SystemIpAndPassword sys) throws Exception;

	/**
	 * @param
	 * @return VMInfo
	 * @throws
	 * @Title: editVM
	 * @Description: 更改虚拟机配置
	 * @author taoxue
	 * @version 1.0
	 * @createtime 2014-6-6 上午9:25:28
	 */
	public VmInfo editVMProcess(VmInfo info) throws Exception;

	/**
	 * 
	 * @Title: delayVMProcess
	 * @Description: 虚拟机延时
	 * @param
	 * @return VmInfo
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime 2014-7-7 下午4:33:33
	 */
	public VmInfo delayVMProcess(VmInfo info) throws Exception;

	/**
	 * 
	 * @Title: putVMPowerStateProcess
	 * @Description: 修改虚拟机状态处理过程
	 * @param
	 * @return VmInfo
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime 2014-6-28 下午1:26:06
	 */
	public VmInfo putVMPowerStateProcess(VmInfo info) throws Exception;

	/**
	 * 
	 * @Title: applyPublicIpProcess
	 * @Description: 购买公网IP处理过程
	 * @param
	 * @return void
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime 2014-7-3 下午5:08:34
	 */
	public VmInfo applyPublicIPProcess(VmInfo info) throws Exception;

	/**
	 * 
	 * @Title: delayPublicIPProcess
	 * @Description: 公网IP地址延时
	 * @param
	 * @return VmInfo
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime 2014-7-7 下午4:38:16
	 */
	public VmInfo delayPublicIPProcess(VmInfo info) throws Exception;

	/**
	 * 
	 * @Title: delayPublicIPProcess
	 * @Description: 公网带宽延时
	 * @param
	 * @return VmInfo
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime 2014-7-7 下午4:38:16
	 */
	public VmInfo delayBandwidthProcess(VmInfo info) throws Exception;

	/**
	 * 
	 * @Title: applyBalanceProcess
	 * @Description:购买负载均衡
	 * @param
	 * @return VmInfo
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime 2014-7-7 下午1:13:07
	 */
	public VmInfo applyBalanceProcess(VmInfo info) throws Exception;

	/**
	 * 
	 * @Title: updateVMTime
	 * @Description: 更新虚拟机开始和到期时间等
	 * @param
	 * @return void
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime 2014-7-7 上午11:24:02
	 */
	public VmInfo updateServeTimeProcess(VmInfo info) throws Exception;

}
