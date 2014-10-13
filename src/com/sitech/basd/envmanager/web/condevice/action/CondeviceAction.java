package com.sitech.basd.envmanager.web.condevice.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import org.apache.commons.beanutils.BeanUtils;

import com.sitech.basd.envmanager.domain.condevice.CondeviceObj;
import com.sitech.basd.envmanager.domain.condevice.VirtualObj;
import com.sitech.basd.envmanager.domain.configure.ConfigureObj;
import com.sitech.basd.envmanager.domain.ledger.LedgerObj;
import com.sitech.basd.envmanager.domain.performance.AddressObj;
import com.sitech.basd.envmanager.service.condevice.CondeviceService;
import com.sitech.basd.envmanager.service.configure.ConfigureService;
import com.sitech.basd.envmanager.service.ledger.LedgerService;
import com.sitech.basd.envmanager.service.performance.PerformanceService;
import com.sitech.basd.envmanager.util.device.DeviceExcelWriter;
import com.sitech.basd.envmanager.util.month.ExcelWriter;
import com.sitech.basd.envmanager.web.condevice.form.CondeviceForm;
import com.sitech.basd.sxcloud.cloud.domain.leader.TbCloud2CubinetInfoObj;
import com.sitech.basd.sxcloud.cloud.domain.resource.TbCloud2HostInfoObj;
import com.sitech.basd.sxcloud.cloud.service.leader.LeaderViewBusinessService;
import com.sitech.basd.sxcloud.cloud.service.resource.HostInfoService;
import com.sitech.basd.sxcloud.rsmu.web.CRUDBaseAction;
import com.sitech.basd.sxcloud.rsmu.web.util.exception.BaseException;
import com.sitech.basd.sxcloud.util.EncryptUtil;
import com.sitech.basd.sxcloud.util.Struts2Utils;
import com.sitech.basd.yicloud.domain.switches.SwitchObj;
import com.sitech.basd.yicloud.service.switches.SwitchesService;

public class CondeviceAction extends CRUDBaseAction {

	private HostInfoService hostInfoService;
	private SwitchesService switchesService;
	private LeaderViewBusinessService leaderViewBusinessService;
	private CondeviceService condeviceService;

	private LedgerService ledgerService;

	private CondeviceForm theForm;

	ConfigureService configureService;

	private PerformanceService performanceService;

	public PerformanceService getPerformanceService() {
		return performanceService;
	}

	public void setPerformanceService(PerformanceService performanceService) {
		this.performanceService = performanceService;
	}

	public ConfigureService getConfigureService() {
		return configureService;
	}

	public void setConfigureService(ConfigureService configureService) {
		this.configureService = configureService;
	}

	public LedgerService getLedgerService() {
		return ledgerService;
	}

	public void setLedgerService(LedgerService ledgerService) {
		this.ledgerService = ledgerService;
	}

	public HostInfoService getHostInfoService() {
		return hostInfoService;
	}

	public void setHostInfoService(HostInfoService hostInfoService) {
		this.hostInfoService = hostInfoService;
	}

	public SwitchesService getSwitchesService() {
		return switchesService;
	}

	public void setSwitchesService(SwitchesService switchesService) {
		this.switchesService = switchesService;
	}

	public CondeviceService getCondeviceService() {
		return condeviceService;
	}

	public void setCondeviceService(CondeviceService condeviceService) {
		this.condeviceService = condeviceService;
	}

	public CondeviceForm getTheForm() {
		return theForm;
	}

	public void setTheForm(CondeviceForm theForm) {
		this.theForm = theForm;
	}

	public LeaderViewBusinessService getLeaderViewBusinessService() {
		return leaderViewBusinessService;
	}

	public void setLeaderViewBusinessService(
			LeaderViewBusinessService leaderViewBusinessService) {
		this.leaderViewBusinessService = leaderViewBusinessService;
	}

	/**
	 * @Title:查询设备主机数据结果集
	 * @Copyright: Copyright (c) 20120413
	 * @Company: si-tech
	 * @author lzh
	 * @version 1.0
	 */
	public String listDevice() throws BaseException {

		if (theForm == null) {
			theForm = new CondeviceForm();
		}
		CondeviceObj obj = new CondeviceObj();
		if (theForm.getDEVICE_TYPE() != null
				&& !"".equals(theForm.getDEVICE_TYPE())) {
			obj.setDEVICE_TYPE(theForm.getDEVICE_TYPE());
		}
		if (theForm.getDEVICE_NAME() != null
				&& !"".equals(theForm.getDEVICE_NAME())) {
			obj.setDEVICE_NAME(theForm.getDEVICE_NAME());
		}
		if (theForm.getDEVICE_NAME_EN() != null
				&& !"".equals(theForm.getDEVICE_NAME_EN())) {
			obj.setDEVICE_NAME_EN(theForm.getDEVICE_NAME_EN());
		}
		// 归属域
		if (theForm.getUSE_DOMAN() != null
				&& !"".equals(theForm.getUSE_DOMAN())) {
			obj.setUSE_DOMAN(theForm.getUSE_DOMAN().trim());
		}
		// 物理IP
		if (theForm.getIP_PHYSICS() != null
				&& !"".equals(theForm.getIP_PHYSICS())) {
			obj.setIP_PHYSICS(theForm.getIP_PHYSICS().trim());
		}
		if (theForm.getUSE_RES() != null && !"".equals(theForm.getUSE_RES())) {
			obj.setUSE_RES(theForm.getUSE_RES().trim());
		}

		obj.setPagination(this.getPaginater().initPagination(
				Struts2Utils.getRequest()));// 分页
		List resultList = condeviceService.queryCondeviceObj(obj);

		theForm.setResultList(resultList);
		return SUCCESS;

	}

	/**
	 * @Title:查询设备主机数据结果集
	 * @Copyright: Copyright (c) 20120413
	 * @Company: si-tech
	 * @author lzh
	 * @version 1.0
	 */
	public String queryManage() {
		if (theForm == null) {
			theForm = new CondeviceForm();
		}

		CondeviceObj obj = new CondeviceObj();
		if (theForm.getDEVICE_NAME() != null
				&& !"".equals(theForm.getDEVICE_NAME())) {
			obj.setDEVICE_NAME(theForm.getDEVICE_NAME().trim());
		}
		if (theForm.getIP_PHYSICS() != null
				&& !"".equals(theForm.getIP_PHYSICS())) {
			obj.setIP_PHYSICS(theForm.getIP_PHYSICS().trim());
		}
		if (theForm.getUSE_DOMAN() != null
				&& !"".equals(theForm.getUSE_DOMAN())) {
			obj.setUSE_DOMAN(theForm.getUSE_DOMAN().trim());
		}
		if (theForm.getUSE_RES() != null && !"".equals(theForm.getUSE_RES())) {
			obj.setUSE_RES(theForm.getUSE_RES().trim());
		}
		obj.setPagination(this.getPaginater().initPagination(
				Struts2Utils.getRequest()));// 分页
		List resultList = condeviceService.queryManageObj(obj);
		theForm.setResultList(resultList);
		return "manage";
	}

	/**
	 * @Title:添加设备主机信息
	 * @Copyright: Copyright (c) 20120413
	 * @Company: si-tech
	 * @author lzh
	 * @version 1.0
	 */
	public String addDevice() throws BaseException {
		if (theForm == null) {
			theForm = new CondeviceForm();
		}
		TbCloud2CubinetInfoObj t = new TbCloud2CubinetInfoObj();
		List cubinetList = leaderViewBusinessService.queryCubListByRoomId(t);
		theForm.setCabinetList(cubinetList);
		return ADD;
	}

	/**
	 * @Title:保存设备主机信息
	 * @Copyright: Copyright (c) 20120416
	 * @Company: si-tech
	 * @author lzh
	 * @version 1.0
	 */
	public String saveDevice() throws BaseException {
		if (theForm == null) {
			theForm = new CondeviceForm();
		}
		String type = theForm.getDEVICE_TYPE();// 获取设备类型
		CondeviceObj obj = new CondeviceObj();

		try {
			BeanUtils.copyProperties(obj, theForm);
			obj.setSTATUSE("1");
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}

		int ret = 0;
		int ret1 = 0;
		if (theForm.getFlag() == 0) {
			String device_id = condeviceService.getIdCondevice();
			obj.setDEVICE_ID(Integer.parseInt(device_id));
			ret = condeviceService.insertCondeviceObj(obj);
			// 新增主机设备
			if (type.equals("1")) {
				String eq_id = null;
				String cq_id = theForm.getCq_id();// 获取机柜ID
				// 确定设备ID
				int temp_id = hostInfoService.getIdSequence();
				/*
				 * if (null != temp_id && !"".equals(temp_id)) { NumberFormat
				 * formatter = NumberFormat.getNumberInstance(); // 设置数据格式
				 * formatter.setMinimumIntegerDigits(2); // 设置最小长度
				 * formatter.setMaximumIntegerDigits(2); // 设置最大长度 int hostId =
				 * Integer.parseInt(temp_id.substring(cq_id .length() + 1)) + 1;
				 * eq_id = cq_id + "_" + formatter.format(hostId); } else {
				 * eq_id = cq_id + "_01"; }
				 */

				TbCloud2HostInfoObj host = new TbCloud2HostInfoObj();
				host.setId(temp_id);
				try {
					BeanUtils.copyProperties(host, theForm);// 将form中的主机信息存在obj中
					host.setDevice_id(Integer.parseInt(device_id));
					// host.setEq_id("0");// 设置新增主机编号
					host.setEq_id(device_id);//
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}

				host.setEq_name(theForm.getSYS_HOSTNAME().trim());
				host.setEq_ip(theForm.getIP_PHYSICS());
				ret1 = hostInfoService.insertByObj(host);

				LedgerObj lj = new LedgerObj();
				lj.setMECH_ROOM(theForm.getMECH_ROOM().replace(",", "").trim());
				lj.setCAPITAL_TYPE(theForm.getCAPITAL_TYPE().replace(",", "")
						.trim());
				lj.setMECH_ID(theForm.getMECH_ID().replace(",", "").trim());
				lj.setCAPITAL_ID(theForm.getCAPITAL_ID().replace(",", "")
						.trim());
				lj.setMECH_TYPE(theForm.getMECH_TYPE().replace(",", "").trim());
				lj.setMECH_CONF(theForm.getMECH_CONF().replace(",", "").trim());
				lj.setSYS_SYSTEM(theForm.getSYS_SYSTEM().replace(",", "")
						.trim());
				lj.setSYS_HOSTNAME(theForm.getSYS_HOSTNAME().replace(",", "")
						.trim());
				lj.setSYS_VM(theForm.getSYS_VM().replace(",", "").trim());
				lj.setIP_PHYSICS(theForm.getIP_PHYSICS().replace(",", "")
						.trim());

				// lj.setIP_VIRTUAL(theForm.getIP_VIRTUAL().replace(",", "")
				// .trim());
				lj.setIP_ILO(theForm.getIP_ILO().replace(",", "").trim());
				lj.setPWD_SYSTEM(theForm.getPWD_SYSTEM().replace(",", "")
						.trim());
				lj.setPWD_CONSOLE(theForm.getPWD_CONSOLE().replace(",", "")
						.trim());
				lj.setSTORE(theForm.getSTORE().replace(",", "").trim());
				lj.setUSE_DOMAN(theForm.getUSE_DOMAN().replace(",", "").trim());
				lj.setUSE_RES(theForm.getUSE_RES().replace(",", "").trim());
				// lj.setUSE_DEPART(theForm.getUSE_DEPART().replace(",", "")
				// .trim());
				lj.setUSE_DESCRIP(theForm.getUSE_DESCRIP().replace(",", "")
						.trim());
				lj.setMANAG_PERSON(theForm.getMANAG_PERSON().replace(",", "")
						.trim());
				lj.setMANAG_RECORD(theForm.getMANAG_RECORD().replace(",", "")
						.trim());
				lj.setMANAG_REPAIR(theForm.getMANAG_REPAIR().replace(",", "")
						.trim());
				lj.setMANAG_USABLE(theForm.getMANAG_USABLE().replace(",", "")
						.trim());
				lj.setMANAG_USE(theForm.getMANAG_USE().replace(",", "").trim());
				lj.setMANAG_DESCRIP(theForm.getMANAG_DESCRIP().replace(",", "")
						.trim());

				lj.setLD_ID(Integer.parseInt(device_id));
				ledgerService.insertLedgerObj(lj);

				AddressObj aobj = new AddressObj();
				aobj.setDevice_id(Integer.parseInt(device_id));
				aobj.setIP_NEW(theForm.getIP_PHYSICS());
				aobj.setMAC_NEW(theForm.getMAC());
				performanceService.insertAddressInfo(aobj);
			}

			// 新增交换机或路由器信息
			if (type.equals("2") || type.equals("3") || type.equals("4")) {
				SwitchObj s = new SwitchObj();
				try {
					BeanUtils.copyProperties(s, theForm);// 将form中的主机信息存在obj中
					String id = switchesService.getIdSequence();
					s.setID(Integer.parseInt(id));
					s.setDEVICE_ID(Integer.parseInt(device_id));
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}
				ret1 = switchesService.insertByObj(s);

				LedgerObj lj = new LedgerObj();
				lj.setMECH_ROOM(theForm.getMECH_ROOM().replace(",", "").trim());
				lj.setCAPITAL_TYPE(theForm.getCAPITAL_TYPE().replace(",", "")
						.trim());
				lj.setMECH_ID(theForm.getMECH_ID().replace(",", "").trim());
				lj.setCAPITAL_ID(theForm.getCAPITAL_ID().replace(",", "")
						.trim());
				lj.setMECH_TYPE(theForm.getMECH_TYPE().replace(",", "").trim());
				lj.setMECH_CONF(theForm.getMECH_CONF().replace(",", "").trim());
				lj.setSYS_SYSTEM(theForm.getSYS_SYSTEM().replace(",", "")
						.trim());
				lj.setSYS_HOSTNAME(theForm.getSYS_HOSTNAME().replace(",", "")
						.trim());
				lj.setSYS_VM(theForm.getSYS_VM().replace(",", "").trim());
				// lj.setIP_PHYSICS(theForm.getIP_PHYSICS().replace(",", "")
				// .trim());

				lj.setIP_VIRTUAL(theForm.getIP_VIRTUAL().replace(",", "")
						.trim());
				lj.setIP_ILO(theForm.getIP_ILO().replace(",", "").trim());
				lj.setPWD_SYSTEM(theForm.getPWD_SYSTEM().replace(",", "")
						.trim());
				lj.setPWD_CONSOLE(theForm.getPWD_CONSOLE().replace(",", "")
						.trim());
				lj.setSTORE(theForm.getSTORE().replace(",", "").trim());
				lj.setUSE_DOMAN(theForm.getUSE_DOMAN().replace(",", "").trim());
				lj.setUSE_RES(theForm.getUSE_RES().replace(",", "").trim());
				// lj.setUSE_DEPART(theForm.getUSE_DEPART().replace(",", "")
				// .trim());
				lj.setUSE_DESCRIP(theForm.getUSE_DESCRIP().replace(",", "")
						.trim());
				lj.setMANAG_PERSON(theForm.getMANAG_PERSON().replace(",", "")
						.trim());
				lj.setMANAG_RECORD(theForm.getMANAG_RECORD().replace(",", "")
						.trim());
				lj.setMANAG_REPAIR(theForm.getMANAG_REPAIR().replace(",", "")
						.trim());
				lj.setMANAG_USABLE(theForm.getMANAG_USABLE().replace(",", "")
						.trim());
				lj.setMANAG_USE(theForm.getMANAG_USE().replace(",", "").trim());
				lj.setMANAG_DESCRIP(theForm.getMANAG_DESCRIP().replace(",", "")
						.trim());

				lj.setLD_ID(Integer.parseInt(device_id));
				ledgerService.insertLedgerObj(lj);
			}

		} else {
			obj.setDEVICE_ID(theForm.getDEVICE_ID());
			ret = condeviceService.updateCondeviceObjOne(obj);
			if (type.equals("1")) {
				TbCloud2HostInfoObj tHost = new TbCloud2HostInfoObj();
				tHost.setDevice_id(theForm.getDEVICE_ID());
				List<TbCloud2HostInfoObj> list = hostInfoService
						.queryForListByObj(tHost);
				tHost = list.get(0);

				try {
					BeanUtils.copyProperties(tHost, theForm);
					tHost.setCq_id(theForm.getCq_id());
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}
				// 把设备中主机名设为主机中的主机名--根据使用人员的需求需要设定
				tHost.setEq_name(theForm.getSYS_HOSTNAME().trim());
				// 把设备中物理IP设为主机中的IP--根据使用人员的需求需要设定
				tHost.setEq_ip(theForm.getIP_PHYSICS());
				ret1 = hostInfoService.updateByObj(tHost);

				AddressObj aobj = new AddressObj();
				aobj.setDevice_id(theForm.getDEVICE_ID());
				aobj.setIP_NEW(theForm.getIP_PHYSICS());
				aobj.setMAC_NEW(theForm.getMAC());
				performanceService.updateAddressInfo(aobj);

			}
			if (type.equals("2") || type.equals("3") || type.equals("4")) {
				SwitchObj s = new SwitchObj();
				try {
					BeanUtils.copyProperties(s, theForm);
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}
				ret1 = switchesService.updateByObj(s);
			}
			LedgerObj lj = new LedgerObj();
			try {
				BeanUtils.copyProperties(lj, theForm);
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			lj.setLD_ID(theForm.getDEVICE_ID());
			ledgerService.updateLedgerObj(lj);
		}

		return REDIRECT;
	}

	/**
	 * @Title:修改设备主机信息
	 * @Copyright: Copyright (c) 20120416
	 * @Company: si-tech
	 * @author lzh
	 * @version 1.0
	 */
	public String modDevice() throws BaseException {
		if (theForm == null) {
			theForm = new CondeviceForm();
		}
		// obj.setDEVICE_ID(Integer.parseInt(device_id));
		CondeviceObj obj = new CondeviceObj();
		obj.setDEVICE_ID(theForm.getDEVICE_ID());
		obj = condeviceService.queryCondeviceObjOne(obj);
		String type = obj.getDEVICE_TYPE();
		if (type.equals("1")) {
			TbCloud2HostInfoObj t = new TbCloud2HostInfoObj();
			t.setDevice_id(theForm.getDEVICE_ID());
			// t.setDevice_id(Integer.parseInt(device_id));
			List<TbCloud2HostInfoObj> list = hostInfoService
					.queryForListByObj(t);
			t = list.get(0);
			try {
				BeanUtils.copyProperties(theForm, obj);
				BeanUtils.copyProperties(theForm, t);
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
			TbCloud2CubinetInfoObj tb = new TbCloud2CubinetInfoObj();
			List cubinetList = leaderViewBusinessService
					.queryCubListByRoomId(tb);
			theForm.setCabinetList(cubinetList);

		}
		if (type.equals("2") || type.equals("3") || type.equals("4")) {
			SwitchObj s = new SwitchObj();
			s.setDEVICE_ID(theForm.getDEVICE_ID());
			List<SwitchObj> list = switchesService.queryForListByObj(s);
			s = list.get(0);
			try {
				BeanUtils.copyProperties(theForm, obj);
				BeanUtils.copyProperties(theForm, s);
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		}
		LedgerObj lj = new LedgerObj();
		lj.setLD_ID(theForm.getDEVICE_ID());
		lj = ledgerService.queryLedgerOne(lj);
		try {
			BeanUtils.copyProperties(theForm, lj);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		theForm.setFlag(1);// 直接跳到修改页面保存时设置的控制值
		return MODIFY;
	}

	/**
	 * 
	 * @Title:删除设备主机信息
	 * @Copyright: Copyright (c) 20120417
	 * @Company: si-tech
	 * @author lzh
	 * @version 1.0
	 */
	public String delDevice() throws BaseException {
		if (theForm == null) {
			theForm = new CondeviceForm();
		}
		CondeviceObj obj = new CondeviceObj();
		LedgerObj lj = new LedgerObj();

		obj.setDEVICE_ID(theForm.getDEVICE_ID());
		obj = condeviceService.queryCondeviceObjOne(obj);
		String type = obj.getDEVICE_TYPE();
		int ret = condeviceService.deleteCondeviceObjOne(obj);
		lj.setLD_ID(theForm.getDEVICE_ID());
		ledgerService.deleteLedgerObj(lj);
		int ret1 = 0;
		int device_id = theForm.getDEVICE_ID();
		if (type.equals("1")) {
			TbCloud2HostInfoObj t = new TbCloud2HostInfoObj();
			t.setDevice_id(device_id);
			ret1 = hostInfoService.deleteByDeviceId(t);
		}
		if (type.equals("2") || type.equals("3")) {
			SwitchObj s = new SwitchObj();
			s.setDEVICE_ID(device_id);
			ret1 = switchesService.deleteByObj(s);
		}
		return REDIRECT;
	}

	/**
	 * 
	 * @Title: getDeviceInfo
	 * @Description: 获取设备的详细信息
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Aug 27, 2012 9:58:34 AM
	 */
	public String getDeviceInfo() {
		if (theForm == null) {
			theForm = new CondeviceForm();
		}
		HttpServletRequest request = Struts2Utils.getRequest();
		HttpServletResponse response = Struts2Utils.getResponse();
		String device_id = request.getParameter("DEVICE_ID");
		CondeviceObj d = new CondeviceObj();
		int DEVICE_ID = Integer.parseInt(device_id);
		if (device_id == null) {
			DEVICE_ID = theForm.getDEVICE_ID();
		}
		d.setDEVICE_ID(DEVICE_ID);
		d = condeviceService.queryCondeviceObjOne(d);

		theForm.setDEVICE_ID(DEVICE_ID);
		// 虚拟IP地址
		VirtualObj v = new VirtualObj();
		v.setDevice_id(DEVICE_ID);
		List ipList = condeviceService.queryVirtualObj(v);
		theForm.setIpList(ipList);

		String result = null;
		String device_type = d.getDEVICE_TYPE();
		if (device_type.equals("1")) {
			LedgerObj lj = new LedgerObj();
			lj.setLD_ID(Integer.parseInt(device_id));
			lj = ledgerService.queryLedgerOne(lj);
			try {
				BeanUtils.copyProperties(theForm, lj);
			} catch (IllegalAccessException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (InvocationTargetException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			TbCloud2HostInfoObj t = new TbCloud2HostInfoObj();
			t.setDevice_id(DEVICE_ID);
			t = hostInfoService.queryByObj(t);
			TbCloud2CubinetInfoObj tb = new TbCloud2CubinetInfoObj();
			List cubinetList = leaderViewBusinessService
					.queryCubListByRoomId(tb);
			theForm.setCabinetList(cubinetList);
			try {
				BeanUtils.copyProperties(theForm, t);
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
			return "host";
		} else if (device_type.equals("2") || device_type.equals("3")) {
			LedgerObj lj = new LedgerObj();
			lj.setLD_ID(Integer.parseInt(device_id));
			lj = ledgerService.queryLedgerOne(lj);
			try {
				BeanUtils.copyProperties(theForm, lj);
			} catch (IllegalAccessException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (InvocationTargetException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			SwitchObj s = new SwitchObj();
			s.setDEVICE_ID(DEVICE_ID);
			s = switchesService.queryByObj(s);
			try {
				BeanUtils.copyProperties(theForm, s);
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
			return "switch";
		} else {
			LedgerObj lj = new LedgerObj();
			lj.setLD_ID(Integer.parseInt(device_id));
			lj = ledgerService.queryLedgerOne(lj);
			try {
				BeanUtils.copyProperties(theForm, lj);
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			SwitchObj s = new SwitchObj();
			s.setDEVICE_ID(DEVICE_ID);
			s = switchesService.queryByObj(s);
			theForm.setNAME(s.getNAME());
			theForm.setTYPE(s.getTYPE());
			return "raid";
		}

	}

	/**
	 * 
	 * @Title: addVirtualObj
	 * @Description: 添加虚拟IP地址
	 * @param
	 * @return String
	 * @throws
	 * @author xumq
	 * @version 1.0
	 * @createtime 2013-4-7 9:58:34 AM
	 */
	public String deleteVirtual() {
		if (theForm == null) {
			theForm = new CondeviceForm();
		}
		HttpServletRequest request = Struts2Utils.getRequest();
		HttpServletResponse response = Struts2Utils.getResponse();
		String id = request.getParameter("ip_id");
		VirtualObj obj = new VirtualObj();
		// ip_id = Integer.parseInt(ip_id);
		int ip_id = Integer.parseInt(id);
		obj.setIp_id(ip_id);
		condeviceService.deleteVirtualObj(obj);

		CondeviceObj d = new CondeviceObj();

		int DEVICE_ID = theForm.getDEVICE_ID();

		d.setDEVICE_ID(DEVICE_ID);
		d = condeviceService.queryCondeviceObjOne(d);
		theForm.setDEVICE_ID(DEVICE_ID);
		// 虚拟IP地址
		VirtualObj v = new VirtualObj();
		v.setDevice_id(DEVICE_ID);
		List ipList = condeviceService.queryVirtualObj(v);
		theForm.setIpList(ipList);
		theForm.setVIRTUAL_IP(null);
		theForm.setDEPART(null);

		String device_type = d.getDEVICE_TYPE();

		LedgerObj lj = new LedgerObj();
		lj.setLD_ID(DEVICE_ID);
		lj = ledgerService.queryLedgerOne(lj);
		try {
			BeanUtils.copyProperties(theForm, lj);
		} catch (IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InvocationTargetException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		TbCloud2HostInfoObj t = new TbCloud2HostInfoObj();
		t.setDevice_id(DEVICE_ID);
		t = hostInfoService.queryByObj(t);
		TbCloud2CubinetInfoObj tb = new TbCloud2CubinetInfoObj();
		List cubinetList = leaderViewBusinessService.queryCubListByRoomId(tb);
		theForm.setCabinetList(cubinetList);
		try {
			BeanUtils.copyProperties(theForm, t);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return "host";
	}

	/**
	 * 
	 * @Title: addVirtualObj
	 * @Description: 添加虚拟IP地址
	 * @param
	 * @return String
	 * @throws
	 * @author xumq
	 * @version 1.0
	 * @createtime 2013-4-7 9:58:34 AM
	 */
	public String addVirtualObj() {
		if (theForm == null) {
			theForm = new CondeviceForm();
		}
		VirtualObj obj = new VirtualObj();
		if (theForm.getVIRTUAL_IP() != null
				&& !"".equals(theForm.getVIRTUAL_IP())) {
			obj.setVIRTUAL_IP(theForm.getVIRTUAL_IP().trim());
		}
		if (theForm.getDEPART() != null && !"".equals(theForm.getDEPART())) {
			obj.setDEPART(theForm.getDEPART());
		}
		obj.setDevice_id(theForm.getDEVICE_ID());
		condeviceService.insertVirtualObj(obj);

		CondeviceObj d = new CondeviceObj();

		int DEVICE_ID = theForm.getDEVICE_ID();

		d.setDEVICE_ID(DEVICE_ID);
		d = condeviceService.queryCondeviceObjOne(d);
		theForm.setDEVICE_ID(DEVICE_ID);
		// 虚拟IP地址
		VirtualObj v = new VirtualObj();
		v.setDevice_id(DEVICE_ID);
		List ipList = condeviceService.queryVirtualObj(v);
		theForm.setIpList(ipList);
		theForm.setVIRTUAL_IP(null);
		theForm.setDEPART(null);

		String device_type = d.getDEVICE_TYPE();

		LedgerObj lj = new LedgerObj();
		lj.setLD_ID(DEVICE_ID);
		lj = ledgerService.queryLedgerOne(lj);
		try {
			BeanUtils.copyProperties(theForm, lj);
		} catch (IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InvocationTargetException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		TbCloud2HostInfoObj t = new TbCloud2HostInfoObj();
		t.setDevice_id(DEVICE_ID);
		t = hostInfoService.queryByObj(t);
		TbCloud2CubinetInfoObj tb = new TbCloud2CubinetInfoObj();
		List cubinetList = leaderViewBusinessService.queryCubListByRoomId(tb);
		theForm.setCabinetList(cubinetList);
		try {
			BeanUtils.copyProperties(theForm, t);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}

		return "host";
	}

	/**
	 * 
	 * @Title: getConfigureInfo
	 * @Description: 获取使用环境的详细信息
	 * @param
	 * @return String
	 * @throws
	 * @author xumq
	 * @version 1.0
	 * @createtime 2013-4-7 9:58:34 AM
	 */
	public String getConfigureInfo() {
		if (theForm == null) {
			theForm = new CondeviceForm();
		}
		HttpServletRequest request = Struts2Utils.getRequest();
		HttpServletResponse response = Struts2Utils.getResponse();
		String device_id = request.getParameter("DEVICE_ID");
		int DEVICE_ID = Integer.parseInt(device_id);
		theForm.setDEVICE_ID(DEVICE_ID);
		ConfigureObj obj = new ConfigureObj();
		// obj.setEnv_id(DEVICE_ID);
		obj = configureService.queryConfigureOne(obj);
		if (obj == null) {

		} else {
			try {
				BeanUtils.copyProperties(theForm, obj);
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return "info";
	}

	/**
	 * 
	 * @Title: exportData
	 * @Description: 导出月报数据
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @throws Exception
	 * @createtime Apr 12, 2013 9:53:00 AM
	 */
	public void exportData() throws Exception {

		HttpServletRequest request = Struts2Utils.getRequest();
		HttpServletResponse response = Struts2Utils.getResponse();
		String path = request.getParameter("path");
		String tname = "ENV_CONFIGURE";
		ResourceBundle dbConfig = ResourceBundle
				.getBundle("com/sitech/basd/config/sqlmap/DBConfig");
		String driverClassName = dbConfig.getString("jdbc.driverClassName");
		String url = dbConfig.getString("jdbc.url");
		String username = dbConfig.getString("jdbc.username");
		String password = dbConfig.getString("jdbc.password");
		password = EncryptUtil.decode(password);
		String outputfile = "/envmanager/report/download/temp";
		String output = request.getRealPath(outputfile);
		File d = new File(output);
		if (!d.exists()) {
			d.mkdirs();
		}
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String curr = df.format(new Date());
		String outfilename = "思特奇研发生产系统月报@SSD@" + curr + ".xls";
		String filename = outputfile + "/" + outfilename + ".xls";
		String filepath = request.getRealPath(filename);
		File file = new File(filepath);

		String xmlPath = "/envmanager/report/download/config.xml";
		xmlPath = request.getRealPath(xmlPath);
		ExcelWriter obj = new ExcelWriter(url, username, password, tname);
		obj.writeExcel(xmlPath, file);
		if (file == null || file.length() <= 0) {
			response.getWriter().println("No file!");
			return;
		}

		Locale locale = request.getLocale();// 获取客户端的地区

		Hashtable supportedEncodings = new Hashtable();// 设置一个已知的地区和编码映射表
		supportedEncodings.put(Locale.SIMPLIFIED_CHINESE, "GBK");
		supportedEncodings.put(Locale.CHINESE, "GBK");
		supportedEncodings.put(Locale.TRADITIONAL_CHINESE, "BIG5");
		supportedEncodings.put(Locale.ENGLISH, "ISO8859-1");

		String encoding = (String) supportedEncodings.get(locale);
		if (encoding == null) {
			encoding = "UTF-8";
		}

		response.setContentType("application/x-msdownload");
		response.addHeader("Content-Disposition", "attachment; filename="
				+ new String(outfilename.getBytes("GBK"), "ISO8859-1")); //
		response.getWriter().flush();
		if (file.exists() && file.canRead() && file.isFile()) {
			response.setContentLength((int) file.length());
			java.io.BufferedInputStream fileInputStream = new java.io.BufferedInputStream(
					new java.io.FileInputStream(file));
			int i;
			while ((i = fileInputStream.read()) != -1)
				response.getOutputStream().write(i);

			fileInputStream.close();
			file.delete();
		}
		response.getWriter().flush();
		return;
	}

	/**
	 * 
	 * @Title: exportDeviceTable
	 * @Description: 导出设备报表信息
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @throws Exception
	 * @createtime Apr 24, 2013 10:42:15 AM
	 */
	public void exportDeviceTable() throws Exception {
		HttpServletRequest request = Struts2Utils.getRequest();
		HttpServletResponse response = Struts2Utils.getResponse();
		String tname = "env_ledger";
		ResourceBundle dbConfig = ResourceBundle
				.getBundle("com/sitech/basd/config/sqlmap/DBConfig");
		String driverClassName = dbConfig.getString("jdbc.driverClassName");
		String url = dbConfig.getString("jdbc.url");
		String username = dbConfig.getString("jdbc.username");
		String password = dbConfig.getString("jdbc.password");
		password = EncryptUtil.decode(password);
		String outputfile = "/envmanager/report/device/temp";
		outputfile = request.getRealPath(outputfile);
		File d = new File(outputfile);
		if (!d.exists())
			d.mkdirs();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String curr = df.format(new Date());
		String outfilename = "思特奇研发生产系统设备表@SSD@" + curr + ".xls";
		String filename = outputfile + "/" + outfilename + ".xls";
		File file = new File(filename);

		String xmlPath = "/envmanager/report/device/config.xml";
		xmlPath = request.getRealPath(xmlPath);
		DeviceExcelWriter obj = new DeviceExcelWriter(url, username, password,
				tname);
		obj.writeExcel(xmlPath, file);

		if (file == null || file.length() <= 0) {
			response.getWriter().println("No file!");
			return;
		}

		Locale locale = request.getLocale();// 获取客户端的地区

		Hashtable supportedEncodings = new Hashtable();// 设置一个已知的地区和编码映射表
		supportedEncodings.put(Locale.SIMPLIFIED_CHINESE, "GBK");
		supportedEncodings.put(Locale.CHINESE, "GBK");
		supportedEncodings.put(Locale.TRADITIONAL_CHINESE, "BIG5");
		supportedEncodings.put(Locale.ENGLISH, "ISO8859-1");

		String encoding = (String) supportedEncodings.get(locale);
		if (encoding == null) {
			encoding = "UTF-8";
		}

		response.setContentType("application/x-msdownload");
		response.addHeader("Content-Disposition", "attachment; filename="
				+ new String(outfilename.getBytes(), "ISO8859-1"));

		response.getWriter().flush();
		if (file.exists() && file.canRead() && file.isFile()) {
			response.setContentLength((int) file.length());
			java.io.BufferedInputStream fileInputStream = new java.io.BufferedInputStream(
					new java.io.FileInputStream(file));
			int i;
			while ((i = fileInputStream.read()) != -1)
				response.getOutputStream().write(i);

			fileInputStream.close();
			file.delete();
		}
		response.getWriter().flush();
		return;
	}

	/**
	 * 
	 * @Title: importData
	 * @Description: 导入数据
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @throws BaseException
	 * @throws IOException
	 * @throws BiffException
	 * @createtime Apr 17, 2013 3:04:25 PM
	 */
	public String importData() throws BaseException, BiffException, IOException {
		HttpServletRequest request = Struts2Utils.getRequest();
		String path = request.getParameter("path");
		path = URLDecoder.decode(path, "utf-8");
		List<String[]> dataList = readExcelFile(path);
		for (String[] obj : dataList) {
			CondeviceObj de = new CondeviceObj();
			String device_id = condeviceService.getIdCondevice();
			de.setDEVICE_TYPE("1");
			de.setDEVICE_NAME(obj[0]);
			de.setTYPE_NAME(obj[1]);
			de.setDEVICE_ID(Integer.parseInt(device_id));
			condeviceService.insertCondeviceObj(de);
			TbCloud2HostInfoObj host = new TbCloud2HostInfoObj();
			int host_id = hostInfoService.getIdSequence();
			host.setEq_name(obj[0]);
			host.setId(host_id);
			host.setEq_id("0");
			host.setEq_type("3");
			host.setEq_ip(obj[10]);
			host.setEq_hostname(obj[0]);
			host.setDevice_id(Integer.parseInt(device_id));
			hostInfoService.insertByObj(host);
			LedgerObj le = new LedgerObj();
			le.setLD_ID(Integer.parseInt(device_id));
			le.setMECH_ROOM(obj[2]);
			le.setCAPITAL_TYPE(obj[3]);
			le.setMECH_ID(obj[4]);
			le.setCAPITAL_ID(obj[5]);
			le.setSYS_HOSTNAME(obj[6]);
			le.setSYS_VM(obj[7]);
			le.setMECH_CONF(obj[8]);
			le.setMECH_ID(obj[9]);
			le.setIP_PHYSICS(obj[10]);
			le.setIP_ILO(obj[11]);
			le.setPWD_SYSTEM(obj[12]);
			le.setPWD_CONSOLE(obj[13]);
			le.setSTORE(obj[14]);
			le.setUSE_DOMAN(obj[15]);
			le.setUSE_RES(obj[16]);
			ledgerService.insertLedgerObj(le);
		}
		return listDevice();
	}

	/**
	 * 
	 * @Title: readExcelFile
	 * @Description: 读取excel文件
	 * @param
	 * @return List
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @throws IOException
	 * @throws BiffException
	 * @createtime Apr 24, 2013 1:40:50 PM
	 */
	public List readExcelFile(String path) throws BiffException, IOException {
		// 创建一个list 用来存储读取的内容
		List list = new ArrayList();
		Workbook rwb = null;
		Cell cell = null;
		// 创建输入流
		InputStream stream = new FileInputStream(path);
		// 获取Excel文件对象
		rwb = Workbook.getWorkbook(stream);
		// 获取文件的指定工作表 默认的第一个
		Sheet sheet = rwb.getSheet(0);
		// 行数(表头的目录不需要，从1开始)
		for (int i = 1; i < sheet.getRows(); i++) {
			// 创建一个数组 用来存储每一列的值
			String[] str = new String[sheet.getColumns()];

			// 列数
			for (int j = 0; j < sheet.getColumns(); j++) {
				// 获取第i行，第j列的值
				cell = sheet.getCell(j, i);
				str[j] = cell.getContents();
			}
			// 把刚获取的列存入list
			list.add(str);
		}
		return list;
	}

	public static void main(String[] args) {

	}
}
