package com.sitech.basd.sxcloud.cloud.service.vusermanage;

import java.util.List;

import com.sitech.basd.rest.user.domain.UserInfo;
import com.sitech.basd.rest.user.operation.UserOperation;
import com.sitech.basd.sxcloud.cloud.dao.alarm.TbCloud2MonitorAlarmDao;
import com.sitech.basd.sxcloud.cloud.dao.vusermanage.VuserManageDao;
import com.sitech.basd.sxcloud.cloud.domain.vusermanage.VuserManageObj;
import com.sitech.basd.sxcloud.rsmu.domain.system.TbSysUserinfoObj;
import com.sitech.basd.sxcloud.rsmu.service.BaseService;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;
import com.sitech.basd.sxcloud.util.EncryptUtil;

public class VuserManageServiceImpl extends BaseService implements
		VuserManageService {

	@SuppressWarnings( { "unchecked", "unused" })
	private static List VuserManageList = null;

	/**
	 * @Title:删除V用户信息
	 * @Copyright: Copyright (c) 201112
	 * @Company: si-tech
	 * @author taoxue
	 * @version 1.0
	 */
	public int deleteByObj(VuserManageObj obj) {
		boolean u = UserOperation.deleteUserById(obj.getUSER_ID());
		if (u == true) {
			return vuserManageDao.deleteByObj(obj);
		} else {
			return 0;
		}
	}

	/**
	 * @Title:插入V用户信息
	 * @Copyright: Copyright (c) 201112
	 * @Company: si-tech
	 * @author taoxue
	 * @version 1.0
	 */
	public int insertByObj(VuserManageObj obj) {
		UserInfo info = new UserInfo();
		info.setUsername(obj.getUSER_ID());
		info.setName(obj.getUSER_NAME());
		info.setPassword(EncryptUtil.decode(obj.getPASSWORD()));
		info.setEmail(obj.getEMAIL());
		info.setRole(obj.getROLE());
		info.setLocale("zh");
		info.setTimezone("Asia/Shanghai");
		if ("0".equals(obj.getEMAILNOTIFICATIONS())) {
			info.setEmailNotifications(false);
		} else if ("1".equals(obj.getEMAILNOTIFICATIONS())) {
			info.setEmailNotifications(true);
		}
		if ("0".equals(obj.getISAPPROVER())) {
			info.setIsApprover(false);
		} else if ("1".equals(obj.getISAPPROVER())) {
			info.setIsApprover(true);
		}
		if ("0".equals(obj.getISADMIN())) {
			info.setAdmin(false);
		} else if ("1".equals(obj.getISADMIN())) {
			info.setAdmin(true);
		}
		UserInfo uInfo = null;
		try {
			uInfo = UserOperation.addUser(info);
		} catch (Exception e) {
			LogHelper.error("KVM Or Director Connect Error：" + e.getMessage());
			/** 插入告警 */
			tbCloud2MonitorAlarmDao.insertAlarmWhenConnError();
		}
		if (uInfo == null) {
			return -1;
		} else {
			return vuserManageDao.insertByObj(obj);
		}

	}

	/**
	 * @Title:查询出具体V用户信息
	 * @Copyright: Copyright (c) 201112
	 * @Company: si-tech
	 * @author taoxue
	 * @version 1.0
	 */
	public VuserManageObj queryByObj(VuserManageObj obj) {
		return vuserManageDao.queryByObj(obj);
	}

	/**
	 * @Title:查询所有V用户信息
	 * @Copyright: Copyright (c) 201112
	 * @Company: si-tech
	 * @author wangjian
	 * @version 1.0
	 */
	@SuppressWarnings("unchecked")
	public List queryForListByObj(VuserManageObj obj) {
		List<UserInfo> list = null;
		try {
			list = UserOperation.getAllUsers();
		} catch (Exception e) {
			LogHelper.error("KVM Or Director Connect Error：" + e.getMessage());
			/** 插入告警 */
			tbCloud2MonitorAlarmDao.insertAlarmWhenConnError();
			return null;
		}
		if (list != null) {
			for (UserInfo info : list) {
				VuserManageObj vObj = new VuserManageObj();
				vObj.setUSER_ID(info.getUsername());
				VuserManageObj rObj = vuserManageDao.queryByObj(vObj);

				if (rObj != null) {
					rObj.setROLE(info.getRole());
					boolean isAdmin = info.getIsAdmin();
					if (isAdmin) {
						rObj.setISADMIN("1");
					} else {
						rObj.setISADMIN("0");
					}
					boolean isApprover = info.getIsApprover();
					if (isApprover) {
						rObj.setISAPPROVER("1");
					} else {
						rObj.setISAPPROVER("0");
					}
					boolean emailNotifications = info.getEmailNotifications();
					if (emailNotifications) {
						rObj.setEMAILNOTIFICATIONS("1");
					} else {
						rObj.setEMAILNOTIFICATIONS("0");
					}
					rObj.setEMAIL(info.getEmail());
					rObj.setNAME(info.getName());
					vuserManageDao.updateByObj(rObj);
				} else {
					rObj = new VuserManageObj();
					rObj.setUSER_ID(info.getUsername());
					rObj.setROLE(info.getRole());
					boolean isAdmin = info.getIsAdmin();
					if (isAdmin) {
						rObj.setISADMIN("1");
					} else {
						rObj.setISADMIN("0");
					}
					boolean isApprover = info.getIsApprover();
					if (isApprover) {
						rObj.setISAPPROVER("1");
					} else {
						rObj.setISAPPROVER("0");
					}
					boolean emailNotifications = info.getEmailNotifications();
					if (emailNotifications) {
						rObj.setEMAILNOTIFICATIONS("1");
					} else {
						rObj.setEMAILNOTIFICATIONS("0");
					}
					rObj.setEMAIL(info.getEmail());
					rObj.setNAME(info.getName());
					vuserManageDao.insertByObj(rObj);
				}
			}
		}
		return vuserManageDao.queryForListByObj(obj);
	}

	/**
	 * @Title:更新V用户信息
	 * @Copyright: Copyright (c) 201112
	 * @Company: si-tech
	 * @author taoxue
	 * @version 1.0
	 */
	public int updateByObj(VuserManageObj obj) {
		/*
		 * boolean flag = false; { //默认从接口获取数据 //获取数据成功，则flag设为true
		 * obj.setPASSWORD(obj.decode.getPASSWORD()) 预留接口，在传PASSWORD的时候改成明文 }
		 * if(!flag){ return vuserManageDao.updateByObj(obj); }
		 */
		UserInfo info = new UserInfo();
		info.setUsername(obj.getUSER_ID());
		info.setName(obj.getUSER_NAME());
		info.setPassword(obj.getPASSWORD());
		info.setEmail(obj.getEMAIL());
		info.setRole(obj.getROLE());
		if ("0".equals(obj.getEMAILNOTIFICATIONS())) {
			info.setEmailNotifications(false);
		} else if ("1".equals(obj.getEMAILNOTIFICATIONS())) {
			info.setEmailNotifications(true);
		}
		if ("0".equals(obj.getISAPPROVER())) {
			info.setIsApprover(false);
		} else if ("1".equals(obj.getISAPPROVER())) {
			info.setIsApprover(true);
		}
		if ("0".equals(obj.getISADMIN())) {
			info.setAdmin(false);
		} else if ("1".equals(obj.getISADMIN())) {
			info.setAdmin(true);
		}
		boolean uInfo = false;
		try {
			uInfo = UserOperation.updateUserById(info.getUsername(), info);
		} catch (Exception e) {
			LogHelper.error("KVM Or Director Connect Error：" + e.getMessage());
			/** 插入告警 */
			tbCloud2MonitorAlarmDao.insertAlarmWhenConnError();
		}

		if (uInfo == false) {
			return 0;
		} else {
			return vuserManageDao.updateByObj(obj);
		}
	}

	/**
	 * @Title:根据用户ID查询V用户信息
	 * @Copyright: Copyright (c) 201112
	 * @Company: si-tech
	 * @author taoxue
	 * @version 1.0
	 */
	@SuppressWarnings("unchecked")
	public List queryForListByUserObj(TbSysUserinfoObj obj) {
		return vuserManageDao.queryForListByUserObj(obj);
	}

	private VuserManageDao vuserManageDao;
	private TbCloud2MonitorAlarmDao tbCloud2MonitorAlarmDao;

	public void setTbCloud2MonitorAlarmDao(
			TbCloud2MonitorAlarmDao tbCloud2MonitorAlarmDao) {
		this.tbCloud2MonitorAlarmDao = tbCloud2MonitorAlarmDao;
	}

	public void setVuserManageDao(VuserManageDao vuserManageDao) {
		this.vuserManageDao = vuserManageDao;
	}

}
