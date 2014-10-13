package com.sitech.basd.aspectj.userinfo;

import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sitech.basd.cloud3.dao.newui.NewUIFuncDao;
import com.sitech.basd.sxcloud.rsmu.domain.system.TbSysUserinfoObj;
import com.sitech.utils.randomid.RandomUUID;

/**
 * 
 * <p>
 * Title: TbSysUserinfoAspectj
 * </p>
 * <p>
 * Description: 用户管理
 * </p>
 * <p>
 * Copyright: Copyright (c) 2013
 * </p>
 * <p>
 * Company: SI-TECH
 * </p>
 * 
 * @author huojla
 * @version 1.0
 * @createtime 2013-7-23 下午4:34:08
 * 
 */
@Component("tbSysUserinfoAspectj")
@Aspect
public class TbSysUserinfoAspectj {
	@Autowired
	private NewUIFuncDao newUIFuncDao;

	/**
	 * 
	 * @Title: addNewUiIcon
	 * @Description: --添加用户时，添加桌面综合管理菜单
	 * @param
	 * @return void
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-7-23 下午4:34:58
	 */
	@After(value = "execution(* com.sitech.basd.sxcloud.rsmu.dao.system.TbSysUserinfoDao.insertByObj(..)) && args(obj)")
	public void addNewUiIcon(TbSysUserinfoObj obj) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("id", RandomUUID.getUuid());
		params.put("LOGINID", obj.getACCOUNT());
		params.put("FUNCID", "9999999999");
		newUIFuncDao.insertNewUIUserFunc(params);
	}
}
