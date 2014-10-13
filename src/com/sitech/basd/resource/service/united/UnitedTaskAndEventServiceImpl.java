package com.sitech.basd.resource.service.united;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.sitech.basd.resource.domain.united.UnitedTreeObj;
import com.sitech.basd.resource.util.VirtualClient;
import com.sitech.utils.exception.HttpClientException;
import com.sitech.utils.jackson.JacksonUtil;
import com.sitech.vo.united.EventUnitedVO;
import com.sitech.vo.united.LogUnitedVO;
import com.sitech.vo.united.TaskUnitedVO;
import com.sitech.vo.util.UnitedConstant;
import com.sitech.vo.util.VMwareEntityType;
import com.sitech.vo.util.VirtualConstant;

/**
 * <p>
 * Title: UnitedTaskAndEventServiceImpl
 * </p>
 * <p>
 * Description: 任务时间接口实现类
 * <p>
 * Copyright: Copyright (c) 2013
 * </p>
 * <p>
 * Company: SI-TECH
 * </p>
 * 
 * @author lipengpeng
 * @version 1.0
 * @createtime 2013-8-2 下午5:04:50
 * 
 */
@Service("unitedTaskAndEventService")
public class UnitedTaskAndEventServiceImpl implements UnitedTaskAndEventService {

	/*
	 * <p>Title: attchEvent</p> <p>Description: 获取事件列表 @return
	 * 
	 * @see
	 * com.sitech.basd.resource.service.united.UnitedTaskAndEventService#attchEvent
	 * ()
	 */
	@Override
	public List<EventUnitedVO> attchEvent(UnitedTreeObj obj,Integer pageSize) {
		List<EventUnitedVO> list = new ArrayList<EventUnitedVO>();
		String vtype = obj.getVtype();
		String type = obj.getType();
		String virtype = "";
		String entityType = "";
		String url = "";
		int pagesize = pageSize == null ? 10:pageSize.intValue();
		if (UnitedConstant.VMWARE.equals(vtype)) {
			virtype = VirtualConstant.VT_VMWARE;
		} else if (UnitedConstant.XEN.equals(vtype)) {
			virtype = VirtualConstant.VT_XEN;
		}
		if (UnitedConstant.VM.equals(type)) {
			entityType = VMwareEntityType.VIRTUALMACHINE;
		} else if (UnitedConstant.HOST.equals(type)) {
			entityType = VMwareEntityType.HOSTSYSTEM;
		} else if (UnitedConstant.CLUSTER.equals(type)) {
			entityType = VMwareEntityType.CLUSTERCOMPUTERESOURCE;
		} else if (UnitedConstant.DATACENTER.equals(type)) {
			entityType = VMwareEntityType.DATACENTER;
		}
		url = "event/" + virtype + "/search/" + obj.getConnect_id() + "/" + entityType + "/"
				+ obj.getUuid() + "/" + pagesize;
		try {
			EventUnitedVO vo = VirtualClient.get(url,
					new JacksonUtil.TypeReference<EventUnitedVO>() {
					});
			list = vo.getEventList();
			String name = obj.getName();
			if (name != null && !"".equals(name)) {
				List<EventUnitedVO> subList = new ArrayList<EventUnitedVO>();
				if (list != null && list.size() > 0) {
					for (EventUnitedVO v : list) {
						if (v.getDescription().contains(name.trim())) {
							subList.add(v);
						}
					}
				}
				return subList;
			}
		} catch (HttpClientException e) {
			e.printStackTrace();
		}
		return list;
	}

	/*
	 * <p>Title: attchTask</p> <p>Description: 获取任务列表 @return
	 * 
	 * @see
	 * com.sitech.basd.resource.service.united.UnitedTaskAndEventService#attchTask
	 * ()
	 */
	@Override
	public List<TaskUnitedVO> attchTask(UnitedTreeObj obj,Integer pageSize) {
		List<TaskUnitedVO> list = new ArrayList<TaskUnitedVO>();
		String vtype = obj.getVtype();
		String type = obj.getType();
		String virtype = "";
		String entityType = "";
		String url = "";
		int pagesize = pageSize == null ? 10:pageSize.intValue();
		if (UnitedConstant.VMWARE.equals(vtype)) {
			virtype = VirtualConstant.VT_VMWARE;
		} else if (UnitedConstant.XEN.equals(vtype)) {
			virtype = VirtualConstant.VT_XEN;
		}
		if (UnitedConstant.VM.equals(type)) {
			entityType = VMwareEntityType.VIRTUALMACHINE;
		} else if (UnitedConstant.HOST.equals(type)) {
			entityType = VMwareEntityType.HOSTSYSTEM;
		} else if (UnitedConstant.CLUSTER.equals(type)) {
			entityType = VMwareEntityType.CLUSTERCOMPUTERESOURCE;
		} else if (UnitedConstant.DATACENTER.equals(type)) {
			entityType = VMwareEntityType.DATACENTER;
		}
		url = "task/" + virtype + "/search/" + obj.getConnect_id() + "/" + entityType + "/"
				+ obj.getUuid() + "/" + pagesize;
		try {
			TaskUnitedVO vo = VirtualClient.get(url, new JacksonUtil.TypeReference<TaskUnitedVO>() {
			});
			list = vo.getTaskList();
			String name = obj.getName();
			if (name != null && !"".equals(name)) {
				List<TaskUnitedVO> subList = new ArrayList<TaskUnitedVO>();
				if (list != null && list.size() > 0) {
					for (TaskUnitedVO v : list) {
						if (v.getInfo().contains(name.trim())) {
							subList.add(v);
						}
					}
				}
				return subList;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * @Title: attchLogsForXen
	 * @Description: xen获取日志列表
	 * @param
	 * @return List<TaskUnitedVO>
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @throws HttpClientException
	 * @createtime 2013-8-2 下午5:02:54
	 */
	public List<LogUnitedVO> attchLogs(UnitedTreeObj obj) {
		String vtype = "";
		String type = "";
		List<LogUnitedVO> list = new ArrayList<LogUnitedVO>();
		/******************* 判断虚拟化类型 **************************/
		//
		if ("1".equals(obj.getVtype())) {// vmware

		} else if ("2".equals(obj.getVtype())) {// xen
			vtype = VirtualConstant.VT_XEN;
		}
		/******************* 判断实体类型--待定，暂为虚拟机 **************************/
		if ("1".equals(obj.getType())) {// 数据中心（xen没有）

		} else if ("2".equals(obj.getType())) {// 集群
			type = com.sitech.vo.enumtype.Types.Cls.POOL.toString();
		} else if ("3".equals(obj.getType())) {// 主机
			type = com.sitech.vo.enumtype.Types.Cls.HOST.toString();
		} else if ("4".equals(obj.getType())) {// 虚拟机
			type = com.sitech.vo.enumtype.Types.Cls.VM.toString();
		}

		String url = "task/" + vtype + "/search/log/" + obj.getConnect_id() + "/" + type + "/"
				+ obj.getUuid();
		try {
			LogUnitedVO result = VirtualClient.get(url,
					new JacksonUtil.TypeReference<LogUnitedVO>() {
					});
			list = result.getTaskList();
		} catch (HttpClientException e) {
			e.printStackTrace();
		}
		return list;
	}
}
