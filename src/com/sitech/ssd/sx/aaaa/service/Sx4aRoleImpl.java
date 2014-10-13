/**
 * Sx4aRoleServiceImpl.java
 * com.sitech.ssd.sx.aaaa.service
 *
 * Function： TODO 
 *
 *   ver     date      		author
 * ──────────────────────────────────
 *   		 2014 三月 26 		duangh
 *
 * Copyright (c) 2014, TNT All Rights Reserved.
 */

package com.sitech.ssd.sx.aaaa.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.jws.WebService;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.sitech.basd.sxcloud.rsmu.dao.system.TbSysUsergroupDao;
import com.sitech.basd.sxcloud.rsmu.domain.system.TbSysUsergroupObj;
import com.sitech.ssd.sx.aaaa.domain.AddRoleRequest;
import com.sitech.ssd.sx.aaaa.domain.AddRoleResponse;
import com.sitech.ssd.sx.aaaa.domain.DelRoleRequest;
import com.sitech.ssd.sx.aaaa.domain.DelRoleResponse;
import com.sitech.ssd.sx.aaaa.domain.EditRoleRequest;
import com.sitech.ssd.sx.aaaa.domain.EditRoleResponse;
import com.sitech.ssd.sx.aaaa.domain.Ret;
import com.sitech.ssd.sx.aaaa.domain.Role;
import com.sitech.ssd.sx.aaaa.domain.SelRoleResponse;
import com.sitech.ssd.sx.aaaa.util.JAXBMarshaller;
import com.sitech.ssd.sx.aaaa.util.JAXBUnmarshaller;

/**
 * ClassName:Sx4aRoleServiceImpl Function: 4A用户角色管理类实现类
 * 
 * @author duangh
 * @since Ver 1.0
 * @Date 2014 三月 26 11:56:19
 */
@Service("sx4aRole")
@WebService(endpointInterface = "com.sitech.ssd.sx.aaaa.service.Sx4aRole")
public class Sx4aRoleImpl implements Sx4aRole {
	@Resource
	private TbSysUsergroupDao tbSysUsergroupDao;

	@Override
	public String addRole(String addRoleRequestXml) {
		AddRoleRequest addRoleRequest = JAXBUnmarshaller.xml2java(AddRoleRequest.class, addRoleRequestXml);
		Role role = addRoleRequest.getRole();
		TbSysUsergroupObj groupObj = new TbSysUsergroupObj();
		groupObj = role2Obj(role, groupObj);
		int result = tbSysUsergroupDao.insertByObj(groupObj);
		AddRoleResponse response = new AddRoleResponse();
		Ret ret = new Ret();
		if (result != -1) {
			ret.setCode("true");
			ret.setMessage("创建成功!");
		} else {
			ret.setCode("false");
			ret.setMessage("创建失败!");
		}
		response.setRet(ret);
		return JAXBMarshaller.java2xml(response);

	}

	@Override
	public String delRole(String delRoleRequestXml) {
		DelRoleRequest delRoleRequest = JAXBUnmarshaller.xml2java(DelRoleRequest.class, delRoleRequestXml);
		String id = delRoleRequest.getRoleid();
		Assert.notNull(id);
		TbSysUsergroupObj obj = new TbSysUsergroupObj();
		obj.setID(Integer.valueOf(id));
		int result = tbSysUsergroupDao.deleteByObj(obj);
		Ret ret = new Ret();
		if (result != -1) {
			ret.setCode("true");
			ret.setMessage("删除成功!");
		} else {
			ret.setCode("false");
			ret.setMessage("删除失败!");
		}
		DelRoleResponse response = new DelRoleResponse();
		response.setRet(ret);
		return JAXBMarshaller.java2xml(response);

	}

	@Override
	public String editRole(String editRoleRequestXml) {
		EditRoleRequest editRoleRequest = JAXBUnmarshaller.xml2java(EditRoleRequest.class, editRoleRequestXml);
		Role role = editRoleRequest.getRole();
		TbSysUsergroupObj obj = new TbSysUsergroupObj();
		obj = role2Obj(role, obj);
		int result = tbSysUsergroupDao.updateByObj(obj);
		Ret ret = new Ret();
		if (result != -1) {
			ret.setCode("true");
			ret.setMessage("修改成功!");
		} else {
			ret.setCode("false");
			ret.setMessage("修改失败!");
		}
		EditRoleResponse response = new EditRoleResponse();
		response.setRet(ret);
		return JAXBMarshaller.java2xml(response);

	}

	@Override
	public String selRole(String selRoleRequestXml) {
		// SelRoleRequest selRoleRequest =
		// JAXBUnmarshaller.xml2java(SelRoleRequest.class, selRoleRequestXml);
		TbSysUsergroupObj groupObj = new TbSysUsergroupObj();
		List<TbSysUsergroupObj> resultList = tbSysUsergroupDao.queryForListByObj(groupObj);
		List<Role> roleList = new ArrayList<Role>();
		for (TbSysUsergroupObj obj : resultList) {
			Role role = new Role();
			role = obj2role(obj, role);
			roleList.add(role);
		}
		SelRoleResponse response = new SelRoleResponse();
		response.setRole(roleList);
		return JAXBMarshaller.java2xml(response);

	}

	/**
	 * 
	 * role2Obj:将4A角色信息对象属性转化为云平台管理的组对象的属性
	 * 
	 * @since duangh Ver 1.0
	 */
	private TbSysUsergroupObj role2Obj(Role role, TbSysUsergroupObj obj) {
		obj.setGROUPNAME(role.getRoleName());
		obj.setREMARK(role.getRoleDes());
		return obj;
	}

	/**
	 * 
	 * obj2role:将云管理平台组的对象转化为4A角色信息对象
	 * 
	 * @since duangh Ver 1.0
	 */
	private Role obj2role(TbSysUsergroupObj obj, Role role) {
		role.setRoleId(String.valueOf(obj.getID()));
		role.setRoleName(obj.getGROUPNAME());
		role.setRoleDes(obj.getREMARK());
		return role;
	}
}
