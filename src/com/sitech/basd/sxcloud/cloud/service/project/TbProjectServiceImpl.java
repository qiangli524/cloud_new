package com.sitech.basd.sxcloud.cloud.service.project;

import java.util.ArrayList;
import java.util.List;

import com.sitech.basd.rest.project.domain.ProjectInfo;
import com.sitech.basd.rest.project.domain.UserInfo;
import com.sitech.basd.rest.project.operation.ProjectOperation;
import com.sitech.basd.sxcloud.cloud.dao.alarm.TbCloud2MonitorAlarmDao;
import com.sitech.basd.sxcloud.cloud.dao.project.TbProjectDao;
import com.sitech.basd.sxcloud.cloud.domain.project.TbCloud2ProjectInfoObj;
import com.sitech.basd.sxcloud.cloud.domain.vusermanage.VuserManageObj;
import com.sitech.basd.sxcloud.rsmu.service.BaseService;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;

public class TbProjectServiceImpl extends BaseService implements
		TbProjectService {
	private TbCloud2MonitorAlarmDao tbCloud2MonitorAlarmDao;

	public void setTbCloud2MonitorAlarmDao(
			TbCloud2MonitorAlarmDao tbCloud2MonitorAlarmDao) {
		this.tbCloud2MonitorAlarmDao = tbCloud2MonitorAlarmDao;
	}

	/**
	 * @Title:删除已有项目
	 * @Copyright: Copyright (c) 2011-12-2
	 * @Company: si-tech
	 * @author taoxue
	 * @version 1.0
	 */
	public int deleteByObj(TbCloud2ProjectInfoObj obj) {
		/*
		 * boolean flag = false; { //默认从接口获取数据 //获取数据成功，则flag设为true } if(!flag){
		 * return tbProjectDao.deleteByObj(obj); }
		 */
		boolean flag = false;
		try {
			flag = ProjectOperation.deleteProject(obj.getPROJECT_ID());
		} catch (Exception e) {
			LogHelper.error("KVM Or Director Connect Error：" + e.getMessage());
			/** 插入告警 */
			tbCloud2MonitorAlarmDao.insertAlarmWhenConnError();
		}
		if (flag == true) {
			return tbProjectDao.deleteByObj(obj);
		} else {
			return 0;
		}

	}

	/**
	 * @Title:创建项目
	 * @Copyright: Copyright (c) 2011-12-2
	 * @Company: si-tech
	 * @author taoxue
	 * @version 1.0
	 */
	public int insertByObj(TbCloud2ProjectInfoObj obj) {
		/*
		 * boolean flag = false; { //默认从接口获取数据 //获取数据成功，则flag设为true } if(!flag){
		 * return tbProjectDao.insertByObj(obj); }
		 */
		ProjectInfo info = new ProjectInfo();
		info.setName(obj.getNAME());
		info.setDescription(obj.getDESCRIPTION());
		info.setId(obj.getPROJECT_ID());
		ProjectInfo pInfo = null;
		try {
			pInfo = ProjectOperation.addProject(info);
		} catch (Exception e) {
			LogHelper.error("KVM Or Director Connect Error：" + e.getMessage());
			/** 插入告警 */
			tbCloud2MonitorAlarmDao.insertAlarmWhenConnError();
		}
		if (pInfo != null) {
			return tbProjectDao.insertByObj(obj);
		} else {
			return 0;
		}

	}

	/**
	 * @Title:查询并返回一个项目对象
	 * @Copyright: Copyright (c) 2011-12-2
	 * @Company: si-tech
	 * @author taoxue
	 * @version 1.0
	 */
	public TbCloud2ProjectInfoObj queryByObj(TbCloud2ProjectInfoObj obj) {
		ProjectInfo pInfo = null;
		try {
			pInfo = ProjectOperation.getProject(obj.getPROJECT_ID());
		} catch (Exception e) {
			LogHelper.error("KVM Or Director Connect Error：" + e.getMessage());
			/** 插入告警 */
			tbCloud2MonitorAlarmDao.insertAlarmWhenConnError();
			return null;
		}
		TbCloud2ProjectInfoObj tObj = new TbCloud2ProjectInfoObj();
		tObj.setPROJECT_ID(pInfo.getId());
		tObj.setNAME(pInfo.getName());
		tObj.setDESCRIPTION(pInfo.getDescription());
		if (tObj == null) {
			return tbProjectDao.queryByObj(obj);
		} else {
			return tObj;
		}

	}

	/**
	 * @Title:查询已有项目列表
	 * @Copyright: Copyright (c) 2011-12-2
	 * @Company: si-tech
	 * @author taoxue
	 * @version 1.0
	 */
	@SuppressWarnings("unchecked")
	public List<TbCloud2ProjectInfoObj> queryForListByObj(
			TbCloud2ProjectInfoObj obj) {
		/*
		 * boolean flag = false; { //默认从接口获取数据 //获取数据成功，则flag设为true } if(!flag){
		 * return tbProjectDao.queryForListByObj(obj); }
		 */
		List<ProjectInfo> pList = null;
		try {
			pList = ProjectOperation.getAllProject();
		} catch (Exception e) {
			LogHelper.error("KVM Or Director Connect Error：" + e.getMessage());
			/** 插入告警 */
			tbCloud2MonitorAlarmDao.insertAlarmWhenConnError();
			return null;
		}
		if (obj.getPagination() != null) {
			obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
			obj.setPAGESIZE(obj.getPagination().getPageSize());
			obj.getPagination().setTotalCount(pList.size());
		}
		int pageSize = 0;
		if ((obj.getPAGESIZE() + obj.getFIRSTROWNUM()) < pList.size()) {
			pageSize = obj.getPAGESIZE() + obj.getFIRSTROWNUM();
		} else {
			pageSize = pList.size();
		}
		List<ProjectInfo> psList = pList
				.subList(obj.getFIRSTROWNUM(), pageSize);
		List<TbCloud2ProjectInfoObj> list = new ArrayList<TbCloud2ProjectInfoObj>();
		for (ProjectInfo i : psList) {
			TbCloud2ProjectInfoObj tObj = new TbCloud2ProjectInfoObj();
			tObj.setPROJECT_ID(i.getId());
			tObj.setNAME(i.getName());
			tObj.setDESCRIPTION(i.getDescription());
			list.add(tObj);
		}
		if (list == null) {
			return tbProjectDao.queryForListByObj(obj);
		} else {
			return list;

		}

	}

	/**
	 * @Title:更新项目信息
	 * @Copyright: Copyright (c) 2011-12-2
	 * @Company: si-tech
	 * @author taoxue
	 * @version 1.0
	 */
	public int updateByObj(TbCloud2ProjectInfoObj obj) {
		/*
		 * boolean flag = false; { //默认从接口获取数据 //获取数据成功，则flag设为true } if(!flag){
		 * return tbProjectDao.updateByObj(obj); }
		 */

		ProjectInfo info = new ProjectInfo();
		info.setId(obj.getPROJECT_ID());
		info.setName(obj.getNAME());
		info.setDescription(obj.getDESCRIPTION());
		List<UserInfo> userInfoList = new ArrayList<UserInfo>();
		boolean flag = false;
		try {
			flag = ProjectOperation.updateProject(info, userInfoList);
		} catch (Exception e) {
			LogHelper.error("KVM Or Director Connect Error：" + e.getMessage());
			/** 插入告警 */
			tbCloud2MonitorAlarmDao.insertAlarmWhenConnError();
		}
		if (flag == true) {
			return tbProjectDao.updateByObj(obj);
		} else {
			return 0;
		}
	}

	private TbProjectDao tbProjectDao;

	public void setTbProjectDao(TbProjectDao tbProjectDao) {
		this.tbProjectDao = tbProjectDao;
	}

	/**
	 * @Title:根据项目名称查询项目管理表
	 * @Copyright: Copyright (c) 201112
	 * @Company: si-tech
	 * @author taoxue
	 * @version 1.0
	 */
	public TbCloud2ProjectInfoObj queryByName(TbCloud2ProjectInfoObj obj) {
		/*
		 * boolean flag = false; { //默认从接口获取数据 //获取数据成功，则flag设为true } if(!flag){
		 * return tbProjectDao.queryByObj(obj); }
		 */
		return tbProjectDao.queryByObj(obj);
	}

	/**
	 * @Title:查询已有项目列表
	 * @Copyright: Copyright (c) 20120105
	 * @Company: si-tech
	 * @author taoxue
	 * @version 1.0
	 */
	@SuppressWarnings("unchecked")
	public List queryForListByProjectObj(TbCloud2ProjectInfoObj obj) {
		/*
		 * boolean flag = false; { //默认从接口获取数据 //获取数据成功，则flag设为true } if(!flag){
		 * return tbProjectDao.queryForListByProjectObj(obj); }
		 */
		List<ProjectInfo> pList = null;
		try {
			pList = ProjectOperation.getAllProject();
		} catch (Exception e) {
			LogHelper.error("KVM Or Director Connect Error：" + e.getMessage());
			/** 插入告警 */
			tbCloud2MonitorAlarmDao.insertAlarmWhenConnError();
		}
		if (pList == null) {
			return tbProjectDao.queryForListByProjectObj(obj);
		} else {
			List<TbCloud2ProjectInfoObj> list = new ArrayList<TbCloud2ProjectInfoObj>();
			for (ProjectInfo info : pList) {
				TbCloud2ProjectInfoObj tObj = new TbCloud2ProjectInfoObj();
				tObj.setNAME(info.getName());
				tObj.setPROJECT_ID(info.getId());
				list.add(tObj);
			}
			return list;
		}
	}

	/**
	 * @Title:编辑项目中的用户
	 * @Copyright: Copyright (c) 2012-02-14
	 * @Company: si-tech
	 * @author taoxue
	 * @version 1.0
	 */
	public int insertVuserByObj(TbCloud2ProjectInfoObj obj) {
		/** 对接SKC */
		ProjectInfo info = new ProjectInfo();
		info.setName(obj.getNAME());
		info.setDescription(obj.getDESCRIPTION());
		info.setId(obj.getPROJECT_ID());
		List<UserInfo> userInfoList = new ArrayList<UserInfo>();
		if (obj.getUSER_STAT_ID() != null && !"".equals(obj.getUSER_STAT_ID())) {
			if (obj.getUSER_STAT_ID().lastIndexOf(",") != -1) {
				String userIdRoleStr = obj.getUSER_STAT_ID().substring(0,
						obj.getUSER_STAT_ID().lastIndexOf(","));
				String[] userid_role = userIdRoleStr.split(",");
				for (int i = 0; i < userid_role.length; i++) {
					String[] id_roleStr = userid_role[i].split(":");
					String userId = id_roleStr[0];
					String role = id_roleStr[1];
					UserInfo uInfo = new UserInfo();
					uInfo.setUsername(userId);
					if ("1".equals(role)) {
						uInfo.setRole("OWNER");
					} else if ("0".equals(role)) {
						uInfo.setRole("VIEWER");
					} else if ("2".equals(role)) {
						uInfo.setRole("USER");
					} else {
						uInfo.setRole("OWNER");
					}
					userInfoList.add(uInfo);
				}
			}
		}
		boolean updateResult = false;
		try {
			updateResult = ProjectOperation.updateProject(info, userInfoList);
		} catch (Exception e) {
			LogHelper.error("KVM Or Director Connect Error：" + e.getMessage());
			/** 插入告警 */
			tbCloud2MonitorAlarmDao.insertAlarmWhenConnError();
		}
		if (updateResult) {
			tbProjectDao.deleteUserByObj(obj);
			if (userInfoList != null && userInfoList.size() > 0) {
				for (UserInfo ui : userInfoList) {
					TbCloud2ProjectInfoObj tObj = new TbCloud2ProjectInfoObj();
					tObj.setPROJECT_ID(obj.getPROJECT_ID());
					tObj.setUSER_ID(ui.getUsername());
					tObj.setUSER_ROLE(ui.getRole());
					tObj.setUSER_STATUS("0");
					tbProjectDao.insertVuserByObj(tObj);
				}
			}
			return 1;
		} else {
			return -1;
		}
	}

	/**
	 * @Title:删除项目中已有用户
	 * @Copyright: Copyright (c) 2012-02-17
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */

	public int deleteUserByObj(TbCloud2ProjectInfoObj obj) {
		return tbProjectDao.deleteUserByObj(obj);
	}

	/**
	 * @Title:查询项目中已有用户
	 * @Copyright: Copyright (c) 2012-02-17
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */

	public List queryUserListByObj(VuserManageObj obj) {
		return tbProjectDao.queryUserListByObj(obj);
	}

}
