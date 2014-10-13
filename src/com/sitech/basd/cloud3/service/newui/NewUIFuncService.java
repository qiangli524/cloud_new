package com.sitech.basd.cloud3.service.newui;

import java.util.List;
import java.util.Map;

import com.sitech.basd.cloud3.domain.newui.HomePageSettingObj;
import com.sitech.basd.sxcloud.rsmu.domain.system.TbSysUserinfoObj;

/**
 * 
 * <p>
 * Title: NewUIFuncService
 * </p>
 * <p>
 * Description: 新UI接口Service
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
 * @createtime Feb 28, 2013 5:45:50 PM
 * 
 */
public interface NewUIFuncService {
	/**
	 * 
	 * @Title: getNewUIFunc_new
	 * @Description: 查询用户自定义首页图标
	 * @param
	 * @return List<Map<String,String>>
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime Feb 28, 2013 6:46:12 PM
	 */
	public List<Map<String, String>> getNewUIFunc_new(Map<String, String> params);

	/**
	 * 
	 * @Title: getNewUIAllowAdd
	 * @Description: 获取当前用户允许添加的图标
	 * @param
	 * @return List<Map<String,String>>
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime Feb 28, 2013 6:46:12 PM
	 */
	public List<Map<String, String>> getNewUIAllowAdd(Map<String, String> params);

	/**
	 * 
	 * @Title: insertNewUIUserFunc
	 * @Description: 插入自定义用户图标
	 * @param
	 * @return int
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime Mar 1, 2013 1:09:23 PM
	 */
	public int insertNewUIUserFunc(Map<String, String> params);

	/**
	 * 
	 * @Title: deleteNewUIUserFunc
	 * @Description: 删除自定义用户图标
	 * @param
	 * @return int
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime Mar 1, 2013 1:09:23 PM
	 */
	public int deleteNewUIUserFunc(Map<String, String> params);

	/**
	 * 
	 * @Title: insertNewUIPicFunc
	 * @Description: 插入菜单图标
	 * @param
	 * @return int
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Apr 17, 2013 4:13:45 PM
	 */
	public int insertNewUIPicFunc(Map map);

	/**
	 * 
	 * @Title: updateNewUIUserFunc
	 * @Description: 更新userFunc表
	 * @param
	 * @return int
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Apr 19, 2013 3:29:04 PM
	 */
	public int updateNewUIUserFunc(Map map);

	/**
	 * 
	 * @Title: updateNewUIPicFunc
	 * @Description: 更新PicFunc表
	 * @param
	 * @return int
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Apr 19, 2013 3:29:38 PM
	 */
	public int updateNewUIPicFunc(Map map);

	/**
	 * 
	 * @Title: deleteNewUIPicFunc
	 * @Description: 删除一条PicFunc记录
	 * @param
	 * @return int
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime May 2, 2013 11:23:57 AM
	 */
	public int deleteNewUIPicFunc(Map map);

	/**
	 * 
	 * 
	 * @Title: queryUserInfo
	 * @Description: 查询用户信息
	 * @param
	 * @return TbSysUserinfoObj
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2014-5-25 下午5:13:01
	 */
	public TbSysUserinfoObj queryUserInfo(String username);

	/**
	 * 
	 * @Title: queryCasUrlByGlobalConfig
	 * @Description: 全局配置表中查询CASURL
	 * @param
	 * @return String
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2014-5-25 下午8:36:00
	 */
	public String queryCasUrlByGlobalConfig();
	
	/**
	 * @Title: getGroupHomePageSetting
	 * @Description: 根据groupid查询角色自定义主页显示内容
	 * @param groupId
	 * @return	HomePageSettingObj
	 * @throws
	 * @author jiangdi
	 * @version 1.0
	 * @param width 
	 * @createtime 2014-6-20 上午10:23:50
	 */
	public List<HomePageSettingObj> getGroupHomePageSetting(String groupId, String width);
}
