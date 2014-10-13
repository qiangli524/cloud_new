package com.sitech.basd.yicloud.web.device.action;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.sitech.basd.sxcloud.cloud.domain.leader.TbCloud2CubinetInfoObj;
import com.sitech.basd.sxcloud.cloud.domain.resource.TbCloud2HostInfoObj;
import com.sitech.basd.sxcloud.cloud.service.leader.LeaderViewBusinessService;
import com.sitech.basd.sxcloud.cloud.service.resource.HostInfoService;
import com.sitech.basd.sxcloud.rsmu.web.CRUDBaseAction;
import com.sitech.basd.sxcloud.rsmu.web.util.exception.BaseException;
import com.sitech.basd.sxcloud.util.Struts2Utils;
import com.sitech.basd.yicloud.domain.device.DeviceObj;
import com.sitech.basd.yicloud.domain.switches.SwitchObj;
import com.sitech.basd.yicloud.service.device.DeviceService;
import com.sitech.basd.yicloud.service.switches.SwitchesService;
import com.sitech.basd.yicloud.web.device.form.DeviceForm;

public class DeviceAction extends CRUDBaseAction {

	private DeviceService deviceService;
	private LeaderViewBusinessService leaderViewBusinessService;
	private DeviceForm theForm;
	private HostInfoService hostInfoService;
	private SwitchesService switchesService;

	public DeviceForm getTheForm() {
		return theForm;
	}

	public void setTheForm(DeviceForm theForm) {
		this.theForm = theForm;
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
			theForm = new DeviceForm();
		}
		DeviceObj obj = new DeviceObj();
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
		obj.setPagination(this.getPaginater().initPagination(
				Struts2Utils.getRequest()));// 分页
		List resultList = deviceService.queryForListByObj(obj);

		theForm.setResultList(resultList);
		return SUCCESS;

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
			theForm = new DeviceForm();
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
			theForm = new DeviceForm();
		}
		String type = theForm.getDEVICE_TYPE();// 获取设备类型
		DeviceObj obj = new DeviceObj();

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
			String device_id = deviceService.getIdSequence();
			obj.setDEVICE_ID(Integer.parseInt(device_id));
			ret = deviceService.insertByObj(obj);
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
				 * eq_id = cq_id + "_" + formatter.format(hostId);
				 *  } else { eq_id = cq_id + "_01"; }
				 */

				TbCloud2HostInfoObj host = new TbCloud2HostInfoObj();
				host.setId(temp_id);
				try {
					BeanUtils.copyProperties(host, theForm);// 将form中的主机信息存在obj中
					host.setDevice_id(Integer.parseInt(device_id));
					host.setEq_id("0");// 设置新增主机编号
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}
				ret1 = hostInfoService.insertByObj(host);
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
			}

		} else {
			obj.setDEVICE_ID(theForm.getDEVICE_ID());
			ret = deviceService.updateByObj(obj);
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
				ret1 = hostInfoService.updateByObj(tHost);
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
			theForm = new DeviceForm();
		}
		DeviceObj obj = new DeviceObj();
		obj.setDEVICE_ID(theForm.getDEVICE_ID());
		obj = deviceService.queryByObj(obj);
		String type = obj.getDEVICE_TYPE();
		if (type.equals("1")) {
			TbCloud2HostInfoObj t = new TbCloud2HostInfoObj();
			t.setDevice_id(theForm.getDEVICE_ID());
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
			theForm = new DeviceForm();
		}
		DeviceObj obj = new DeviceObj();
		obj.setDEVICE_ID(theForm.getDEVICE_ID());
		obj = deviceService.queryByObj(obj);
		String type = obj.getDEVICE_TYPE();
		int ret = deviceService.deleteByObj(obj);
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
			theForm = new DeviceForm();
		}
		HttpServletRequest request = Struts2Utils.getRequest();
		HttpServletResponse response = Struts2Utils.getResponse();
		String device_id = request.getParameter("device_id");
		DeviceObj d = new DeviceObj();
		int DEVICE_ID = Integer.parseInt(device_id);
		d.setDEVICE_ID(DEVICE_ID);
		d = deviceService.queryByObj(d);
		String result = null;
		String device_type = d.getDEVICE_TYPE();
		if (device_type.equals("1")) {
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
			SwitchObj s = new SwitchObj();
			s.setDEVICE_ID(DEVICE_ID);
			s = switchesService.queryByObj(s);
			theForm.setNAME(s.getNAME());
			theForm.setTYPE(s.getTYPE());
			return "raid";
		}

	}

	public void setSwitchesService(SwitchesService switchesService) {
		this.switchesService = switchesService;
	}

	public void setLeaderViewBusinessService(
			LeaderViewBusinessService leaderViewBusinessService) {
		this.leaderViewBusinessService = leaderViewBusinessService;
	}

	public void setHostInfoService(HostInfoService hostInfoService) {
		this.hostInfoService = hostInfoService;
	}

	public void setDeviceService(DeviceService deviceService) {
		this.deviceService = deviceService;
	}

}
