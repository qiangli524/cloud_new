/**
 * Copyright (c) 2013 SI-TECH Software, Inc.
 * All right reserved. 
 * 
 * 云管理平台
 */
package com.sitech.ssd.bpm.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sitech.basd.sxcloud.rsmu.dao.system.TbSysGrpmemberDao;
import com.sitech.basd.sxcloud.rsmu.dao.system.TbSysUsergroupDao;
import com.sitech.basd.sxcloud.rsmu.dao.system.TbSysUserinfoDao;
import com.sitech.basd.sxcloud.rsmu.domain.system.TbSysGrpmemberObj;
import com.sitech.basd.sxcloud.rsmu.domain.system.TbSysUsergroupObj;
import com.sitech.basd.sxcloud.rsmu.domain.system.TbSysUserinfoObj;
import com.sitech.ssd.bpm.service.WorkFlowUserService;


/** 
* @ClassName: WorkFlowUserServiceImpl 
* @Description: TODO(为bpm提供的权限实现类) 
* @author wanglei_bj@si-tech.com.cn 
* @date 2014-5-29 下午1:20:39 
* @version 1.0 
*/
@Service("workFlowUserService")
public class WorkFlowUserServiceImpl implements WorkFlowUserService {
	@Resource
	private TbSysUsergroupDao tbSysUsergroupDao;
	@Resource
	private TbSysUserinfoDao tbSysUserinfoDao ;
	@Resource
	private TbSysGrpmemberDao tbSysGrpmemberDao;
	
	TbSysUserinfoObj user;
	TbSysUsergroupObj group;
	TbSysGrpmemberObj gu;
	
	/**
	 *
	 * @see com.sitech.ssd.bpm.service.WorkFlowUserService#getUsersByRoleId(java.lang.String)
	 */
	public List<Map<String, String>> getUsersByGroupId(String roleId) {
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		if (roleId != null && !roleId.equals("")) {
			gu = new TbSysGrpmemberObj();
			gu.setGROUPID(Integer.parseInt(roleId));
			List<TbSysGrpmemberObj> users = tbSysGrpmemberDao.queryForListByObj(gu);
			if(users != null && users.size() > 0){
				for(TbSysGrpmemberObj usr : users){
					Map<String, String> map = new HashMap<String, String>();
					map.put("account", usr.getUSERID()+"");
					map.put("userName", usr.getUSERNAME());
					list.add(map);
				}
			}
		}
		return list;
	}
	/**
	 *
	 * @see com.sitech.ssd.bpm.service.WorkFlowUserService#getUserByUserId(java.lang.String)
	 */
	public Map<String, String> getUserById(String userId) {
		Map<String, String> map = null;
		user = new TbSysUserinfoObj();
		user.setID(Integer.parseInt(userId));
		user = tbSysUserinfoDao.queryByObj(user);
		if(user!=null){
			map	= new HashMap<String, String>();
			map.put("userName", user.getNAME());
		}
		return map;
	}
	/**
	 *
	 * @see com.sitech.ssd.bpm.service.WorkFlowUserService#getRoleById(java.lang.String)
	 */
	public Map<String, String> getGroupById(String groupId) {
		Map<String, String> map = null;
		if (groupId != "" && groupId != null) {
			group = new TbSysUsergroupObj();
			group.setID(Integer.parseInt(groupId));
			group = tbSysUsergroupDao.queryByObj(group);
			if(group != null){
				map	= new HashMap<String, String>();
				map.put("groupName", group.getGROUPNAME());
			}
		}
		return map;
	}
	/**
	 * 
	 *
	 * @see com.sitech.ssd.bpm.service.WorkFlowUserService#getGroupList()
	 */
	public List<Map<String, String>>  getGroupList() {
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		List<TbSysUsergroupObj> groups = new ArrayList<TbSysUsergroupObj>();
		group = new TbSysUsergroupObj();
		groups = tbSysUsergroupDao.queryForListByObj(group);
		if(groups != null && groups.size() > 0){
			for (TbSysUsergroupObj grp : groups) {
				Map<String, String> map = new HashMap<String, String>();
				map.put("groupId",grp.getID()+"");
				map.put("groupName", grp.getGROUPNAME());
				list.add(map);
			}
		}
		return list;
	}
	/**
	 * 
	 *
	 * @see com.sitech.ssd.bpm.service.WorkFlowUserService#getUserList()
	 */
	public List<Map<String, String>>  getUserList() {		
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		List<TbSysUserinfoObj> users = new ArrayList<TbSysUserinfoObj>();
		user = new TbSysUserinfoObj();
		users = tbSysUserinfoDao.queryForListByObj(user);
		for(TbSysUserinfoObj usr : users){
			Map<String, String> map = new HashMap<String, String>();
			map.put("account",usr.getID()+"");
			map.put("userName", usr.getNAME());
			list.add(map);
		}
		return list;
	}
	/** (非 Javadoc) 
	* <p>Title: getGroupIdListByUserId</p> 
	* <p>Description: 根据用户Id查询用户所属组ID</p> 
	* @author wanglei_bj@si-tech.com.cn 
	* @param account
	* @return {1,2,3}
	* @see com.sitech.ssd.bpm.service.WorkFlowUserService#getGroupIdListByUserId(java.lang.String) 
	*/
	@Override
	public List<String> getGroupIdListByUserId(String account) {
		List<String> list = new ArrayList<String>();
		if (account != null && !account.equals("")) {
			gu = new TbSysGrpmemberObj();
			gu.setUSERID(Integer.parseInt(account));
			List<TbSysGrpmemberObj> groups = tbSysGrpmemberDao.queryForListByObj(gu);
			if(groups != null && groups.size() > 0){
				for (TbSysGrpmemberObj grp : groups) {
					list.add(grp.getGROUPID()+"");
				}
			}
		}
		return list;
	}
}
