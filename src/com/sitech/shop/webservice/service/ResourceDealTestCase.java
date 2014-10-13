package com.sitech.shop.webservice.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import publiccloud.OperateSystemType;
import publiccloud.SystemIpAndPassword;
import publiccloud.UserType;
import publiccloud.VlanType;

import com.sitech.basd.util.AppContext;
import com.sitech.shop.domain.vlan.PhysicalVlanObj;
import com.sitech.ssd.billing.vo.resourceInfo.VmInfo;
import com.sitech.utils.date.DateUtil;
import com.sitech.vo.united.VirtualDiskUnitedVO;

public class ResourceDealTestCase {
	// @Test
	public void testTime() {
		Date date = DateUtil.addYear(DateUtil.getCurrentDate(), 100);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println(format.format(date));
	}

	// @Test
	public void testApplyVM() throws Exception {
		ResourceService res = AppContext.getBean("resourceService", ResourceService.class);
		VmInfo info = new VmInfo();
		info.setUser_id("1");
		info.setOper_system("Red Hat Enterprise Linux 6 (64 位)1");
		info.setCpu(4);
		info.setMemoryInMb(8 * 1024);
		VirtualDiskUnitedVO disk1 = new VirtualDiskUnitedVO();
		disk1.setCapacityInMB((long) 2048);
		disk1.setVmdkProduceInstaceId("1");
		VirtualDiskUnitedVO disk2 = new VirtualDiskUnitedVO();
		disk2.setCapacityInMB((long) 2048);
		disk2.setVmdkProduceInstaceId("2");
		List<VirtualDiskUnitedVO> diskList = new ArrayList<VirtualDiskUnitedVO>();
		diskList.add(disk1);
		diskList.add(disk2);
		info.setVmdkList(diskList);
		info.setOs_type(OperateSystemType.linux);
		// info.if_Init = true;
		info.setUser_id("1");
		info.setUser_Type(UserType.afterpay_user);
		res.applyVM(info);
	}

	/**
	 * 
	 * @Title: testGetVlanInfo
	 * @Description: 获取vlan信息
	 * @param
	 * @return void
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @throws Exception
	 * @createtime 2014-9-16 上午10:05:45
	 */
	@Test
	public void testGetVlanInfo() throws Exception {
		ResourceDealServiceImpl res = AppContext.getBean("resourceDealService",
				ResourceDealServiceImpl.class);
		PhysicalVlanObj vlan1 = res.getVlanInfo("1", VlanType.inner_vlan);
		PhysicalVlanObj vlan2 = res.getVlanInfo("1", VlanType.public_vlan);
	}

	/**
	 * 
	 * @Title: testReconfigVM
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param
	 * @return void
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @throws Exception
	 * @createtime 2014-6-16 下午2:18:32
	 */
	// @Test
	public void testReconfigVM() throws Exception {
		UpgradeVMListener res = AppContext.getBean("upgradeVMListener", UpgradeVMListener.class);
		res.handleMessage("");
	}

	/**
	 * 
	 * @Title: testChangeIPAndPassword
	 * @Description: 修改主机IP地址和密码
	 * @param
	 * @return void
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime 2014-6-17 下午4:17:25
	 */
	// @Test
	public void testChangePassword() throws Exception {
		ResourceDealServiceImpl res = AppContext.getBean("resourceDealService",
				ResourceDealServiceImpl.class);
		// SystemIpAndPassword sys = res.generateSystemIpAndPassword(400 + "",
		// "vm-62", "VCENTER.m0",
		// null);
		// sys.setPass("Admin1234");
		// sys.setNewPass(PublicCloudUtil.getPassword(8));
		SystemIpAndPassword sys = new SystemIpAndPassword();
		sys.setIp("172.21.1.80");
		sys.setOldPass("76CE5snh");
		sys.setPass("76CE5snh");
		sys.setNewPass("111111");
		System.out.println(sys.getNewPass());
		sys.setUser("root");
		boolean change_result = res.changePassword(OperateSystemType.linux, sys);
	}

	/**
	 * 
	 * @Title: testChangeIP
	 * @Description: 修改虚拟机IP地址
	 * @param
	 * @return void
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime 2014-6-21 上午11:53:02
	 */
	// @Test
	public void testChangeIP() throws Exception {
		ResourceDealServiceImpl res = AppContext.getBean("resourceDealService",
				ResourceDealServiceImpl.class);
		SystemIpAndPassword sys = new SystemIpAndPassword();
		sys.setIp("172.21.1.80");
		sys.setPass("111111");
		sys.setNewIp("172.21.1.79");
		System.out.println(sys.getNewPass());
		sys.setUser("root");
		sys.setConnectName("eth1");
		sys.setSubnetMask("255.255.255.0");
		sys.setGateway("172.21.1.1");
		boolean change_result = res.changeIp(OperateSystemType.linux, sys);
	}

	/**
	 * 
	 * @Title: testAllocateIp
	 * @Description:分配vlan
	 * @param
	 * @return void
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime 2014-6-21 下午3:16:02
	 */
	// @Test
	public void testAllocateVlan() throws Exception {
		ResourceDealServiceImpl res = AppContext.getBean("resourceDealService",
				ResourceDealServiceImpl.class);
		VmInfo info = new VmInfo();
		info.setUser_id("1");
		info.setIf_Init(true);
		info.setUser_Type(UserType.prepay_user);
		List vlanList = res.checkVlan(info);
		res.allocateVlan(vlanList, info, "");
	}

	/**
	 * 
	 * @Title: testAllocateVlan
	 * @Description: 分配公网IP地址
	 * @param
	 * @return void
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime 2014-6-21 下午3:36:46
	 */
	// @Test
	public void testAllocateIp() throws Exception {
		ResourceDealServiceImpl res = AppContext.getBean("resourceDealService",
				ResourceDealServiceImpl.class);
		VmInfo info = new VmInfo();
		info.setUser_id("1");
		info.setIf_Init(true);
		info.setUser_Type(UserType.prepay_user);
		List vlanList = res.checkPublicIP(info);
		res.allocatePublicIp(vlanList, info, "");
	}

	/**
	 * 
	 * @Title: testResourceCheck
	 * @Description: 资源检查
	 * @param
	 * @return void
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime 2014-6-21 下午4:46:09
	 */
	// @Test
	public void testResourceCheck() throws Exception {
		ResourceDealServiceImpl res = AppContext.getBean("resourceDealService",
				ResourceDealServiceImpl.class);
		VmInfo info = new VmInfo();
		info.setUser_id("1");
		info.setIf_Init(true);
		info.setUser_Type(UserType.prepay_user);
		info.setOper_system("Microsoft Windows Server 2008 R2 (64 位)");
		boolean result = res.resourceCheck(info);
		System.out.println(result);
	}

	/**
	 * 
	 * @Title: testAddMonth
	 * @Description: 在当前时间累加两个自然月
	 * @param
	 * @return void
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime 2014-6-30 下午2:28:27
	 */
	// @Test
	public void testAddMonth() {
		Date date = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.setLenient(true);
		c.set(Calendar.DATE, c.get(Calendar.DATE) + 1);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		c.add(Calendar.MONTH, 2);
		System.out.println(new DateUtil().formatDate(c.getTime(), "yyyy-MM-dd HH:mm:ss"));

	}

	/**
	 * 
	 * @Title: testUpdatServeTime
	 * @Description: 更新服务时间
	 * @param
	 * @return void
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @throws Exception
	 * @createtime 2014-7-30 下午6:30:34
	 */
	// @Test
	public void testUpdatServeTime() throws Exception {
		ResourceDealServiceImpl res = AppContext.getBean("resourceDealService",
				ResourceDealServiceImpl.class);
		VmInfo info = new VmInfo();
		/*
		 * info.setVlan_id("500"); info.setvLanProductInstanceId("1");
		 */
		/*
		 * info.setInternet_ip("1.1.1.2"); info.setIpProductInstanceId("1");
		 */
		info.setLoadProductInstanceId("1");
		info.setLoadId("lb-1404821254683");
		info.setService_begin_time(Calendar.getInstance().getTime());
		info.setService_end_time(Calendar.getInstance().getTime());
		res.updateServeTimeProcess(info);
	}
}
