/**
 * Users4AServiceImpl.java
 * com.sitech.ssd.sx.aaaa
 *
 * Function： TODO 
 *
 *   ver     date      		author
 * ──────────────────────────────────
 *   		 2014 三月 25 		duangh
 *
 * Copyright (c) 2014, TNT All Rights Reserved.
 */

package com.sitech.ssd.sx.aaaa.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.jws.WebService;

import org.springframework.stereotype.Service;

import com.sitech.basd.sxcloud.rsmu.dao.system.TbSysUserinfoDao;
import com.sitech.basd.sxcloud.rsmu.domain.system.TbSysUserinfoObj;
import com.sitech.ssd.sx.aaaa.domain.AddUserRequest;
import com.sitech.ssd.sx.aaaa.domain.AddUserResponse;
import com.sitech.ssd.sx.aaaa.domain.DelUserRequest;
import com.sitech.ssd.sx.aaaa.domain.DelUserResponse;
import com.sitech.ssd.sx.aaaa.domain.EditUserRequest;
import com.sitech.ssd.sx.aaaa.domain.EditUserResponse;
import com.sitech.ssd.sx.aaaa.domain.OperationUserPwdResponse;
import com.sitech.ssd.sx.aaaa.domain.OperationUserRequest;
import com.sitech.ssd.sx.aaaa.domain.OperationUserResponse;
import com.sitech.ssd.sx.aaaa.domain.Ret;
import com.sitech.ssd.sx.aaaa.domain.SelAllUserResponse;
import com.sitech.ssd.sx.aaaa.domain.SelUserRequest;
import com.sitech.ssd.sx.aaaa.domain.SelUserResponse;
import com.sitech.ssd.sx.aaaa.domain.User;
import com.sitech.ssd.sx.aaaa.util.JAXBMarshaller;
import com.sitech.ssd.sx.aaaa.util.JAXBUnmarshaller;

/**
 * ClassName:Users4AServiceImpl Function: 4A用户管理实现类
 * 
 * @author duangh
 * @version
 * @since Ver 1.0
 * @Date 2014 三月 25 14:11:31
 * 
 * @see
 */
@Service("sx4aUser")
@WebService(endpointInterface = "com.sitech.ssd.sx.aaaa.service.Sx4aUser")
public class Sx4aUserImpl implements Sx4aUser {
	@Resource
	private TbSysUserinfoDao tbSysUserinfoDao;

	@Override
	public String operationUser(String operationUserRequestXml) {

		OperationUserRequest request = JAXBUnmarshaller.xml2java(OperationUserRequest.class, operationUserRequestXml);
		TbSysUserinfoObj obj = new TbSysUserinfoObj();
		obj.setACCOUNT(request.getUid());
		obj.setSTATUS(Integer.valueOf(request.getEnable()));
		int result = tbSysUserinfoDao.updateByObj(obj);
		OperationUserResponse response = new OperationUserResponse();
		Ret ret = new Ret();
		if (result != -1) {
			ret.setCode("true");
			ret.setMessage("操作成功!");
		} else {
			ret.setCode("false");
			ret.setMessage("操作失败!");
		}
		response.setRet(ret);
		return JAXBMarshaller.java2xml(response);

	}

	@Override
	public String selAllUser(String selAllUserRequestXml) {
		// SelAllUserRequest request =
		// JAXBUnmarshaller.xml2java(SelAllUserRequest.class,
		// selAllUserRequestXml);
		TbSysUserinfoObj userObj = new TbSysUserinfoObj();
		List<TbSysUserinfoObj> objList = tbSysUserinfoDao.queryForListByObj(userObj);
		SelAllUserResponse response = new SelAllUserResponse();
		List<User> userList = new ArrayList<User>();
		for (TbSysUserinfoObj obj : objList) {
			User user = new User();
			user = userObjRevert(user, obj);
			userList.add(user);
		}
		response.setUser(userList);
		return JAXBMarshaller.java2xml(response);

	}

	@Override
	public String selUser(String selUserRequestXml) {
		SelUserRequest request = JAXBUnmarshaller.xml2java(SelUserRequest.class, selUserRequestXml);
		TbSysUserinfoObj userObj = new TbSysUserinfoObj();
		userObj.setACCOUNT(request.getUid());
		userObj = tbSysUserinfoDao.queryByObj(userObj);
		SelUserResponse response = new SelUserResponse();
		User user = new User();
		response.setUser(userObjRevert(user, userObj));
		return JAXBMarshaller.java2xml(response);
	}

	/**
	 * 将云管理平台的用户对应为4A用户相应的类
	 */
	private User userObjRevert(User user, TbSysUserinfoObj obj) {
		user.setUid(obj.getACCOUNT());
		user.setEmployeenumber(String.valueOf(obj.getID()));
		user.setCn(obj.getNAME());
		user.setMobile(obj.getMOBILE());
		user.setMail(obj.getEMAIL());
		user.setRole(obj.getGROUP_NAME());
		user.setEnable(String.valueOf(obj.getSTATUS()));
		user.setStartdate(obj.getCREATETIME());
		// 其他信息云管理平台暂时没有
		return user;
	}

	/**
	 * 将4A接口用户请求处理的类对应为云管理平台用户类
	 */
	private TbSysUserinfoObj objUserRevert(TbSysUserinfoObj obj, User user) {
		obj.setACCOUNT(user.getUid());
		obj.setNAME(user.getCn());
		obj.setMOBILE(user.getMobile());
		obj.setEMAIL(user.getMail());
		obj.setGROUP_NAME(user.getRole());
		obj.setSTATUS(Integer.valueOf(user.getEnable()));
		return obj;
	}

	@Override
	public String addUser(String addUserRequestXml) {
		AddUserRequest addUserRequest = JAXBUnmarshaller.xml2java(AddUserRequest.class, addUserRequestXml);
		User user = addUserRequest.getUser();
		TbSysUserinfoObj obj = new TbSysUserinfoObj();
		obj = objUserRevert(obj, user);
		int result = tbSysUserinfoDao.insertByObj(obj);
		AddUserResponse response = new AddUserResponse();
		Ret ret = new Ret();
		if (result != -1) {
			ret.setCode("true");
			ret.setMessage("创建帐号成功!");

		} else {
			ret.setCode("false");
			ret.setMessage("创建帐号失败!");
		}
		response.setRet(ret);
		return JAXBMarshaller.java2xml(response);
	}

	@Override
	public String editUser(String editUserRequestXml) {
		EditUserRequest editUserRequest = JAXBUnmarshaller.xml2java(EditUserRequest.class, editUserRequestXml);
		User user = editUserRequest.getUser();
		TbSysUserinfoObj obj = new TbSysUserinfoObj();
		obj = objUserRevert(obj, user);
		int result = tbSysUserinfoDao.updateByObj(obj);
		EditUserResponse response = new EditUserResponse();
		Ret ret = new Ret();
		if (result != -1) {
			ret.setCode("true");
			ret.setMessage("修改帐号成功!");

		} else {
			ret.setCode("false");
			ret.setMessage("修改帐号失败!");
		}
		response.setRet(ret);
		return JAXBMarshaller.java2xml(response);
	}

	@Override
	public String operationUserPwd(String operationUserPwdRequestXml) {
		OperationUserPwdResponse response = new OperationUserPwdResponse();
		Ret ret = new Ret();
		ret.setCode("true");
		ret.setMessage("操作成功！");
		response.setRet(ret);
		return JAXBMarshaller.java2xml(response);

	}

	@Override
	public String delUser(String delUserRequestXml) {
		DelUserRequest delUserRequest = JAXBUnmarshaller.xml2java(DelUserRequest.class, delUserRequestXml);
		String accout = delUserRequest.getUid();
		TbSysUserinfoObj obj = new TbSysUserinfoObj();
		obj.setACCOUNT(accout);
		int result = tbSysUserinfoDao.deleteByObj(obj);
		DelUserResponse response = new DelUserResponse();
		Ret ret = new Ret();
		if (result != -1) {
			ret.setCode("true");
			ret.setMessage("删除帐号成功!");

		} else {
			ret.setCode("false");
			ret.setMessage("删除帐号失败!");
		}
		response.setRet(ret);
		return JAXBMarshaller.java2xml(response);
	}
}
