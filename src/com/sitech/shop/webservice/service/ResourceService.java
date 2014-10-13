package com.sitech.shop.webservice.service;

import java.util.List;

import com.sitech.ssd.billing.vo.resourceInfo.VmInfo;
import com.sitech.vo.united.VirtualDiskUnitedVO;

public interface ResourceService {
	/**
	 * 
	 * @Title: applyVM
	 * @Description:申请虚拟机
	 * @param
	 * @return void
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @throws Exception
	 * @createtime 2014-6-18 上午10:06:30
	 */

	public void applyVM(VmInfo info) throws Exception;

	/**
	 * 
	 * @Title: mountDisk
	 * @Description: 挂载磁盘
	 * @param
	 * @return void
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime 2014-6-25 下午3:30:35
	 */
	public void mountDisk(List<VirtualDiskUnitedVO> diskList);

	/**
	 * 
	 * @Title: unmountDisk
	 * @Description: 卸载磁盘
	 * @param
	 * @return void
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime 2014-6-25 下午3:31:02
	 */
	public void unmountDisk(List<VirtualDiskUnitedVO> diskList);

	/**
	 * 
	 * @Title: putVMPowerState
	 * @Description:修改虚拟机电源状态
	 * @param
	 * @return void
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime 2014-6-26 下午8:24:10
	 */
	public VmInfo putResourceState(VmInfo info) throws Exception;;

	/**
	 * 
	 * @Title: editVM
	 * @Description: 重新配置虚拟机
	 * @param
	 * @return void
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime 2014-6-28 上午11:54:40
	 */
	public void editVM(VmInfo info) throws Exception;;

	/**
	 * 
	 * @Title: applyPublicIp
	 * @Description: 购买公网IP
	 * @param
	 * @return void
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime 2014-7-3 下午5:08:34
	 */
	public void applyPublicIP(VmInfo info) throws Exception;;

	/**
	 * 
	 * @Title: applyBandwidth
	 * @Description: 购买公网带宽
	 * @param
	 * @return void
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime 2014-7-3 下午5:52:31
	 */
	public void applyBandwidth(VmInfo info) throws Exception;;

	/**
	 * 
	 * @Title: expandBandwidth
	 * @Description:更改带宽
	 * @param
	 * @return void
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime 2014-7-8 上午10:05:20
	 */
	public void expandBandwidth(VmInfo info) throws Exception;;

	/**
	 * 
	 * @Title: applayBalance
	 * @Description:申请负载均衡
	 * @param
	 * @return void
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime 2014-7-7 上午11:52:43
	 */
	public void applyBalance(VmInfo info) throws Exception;;

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
	public void updateServeTime(VmInfo info) throws Exception;;
}
