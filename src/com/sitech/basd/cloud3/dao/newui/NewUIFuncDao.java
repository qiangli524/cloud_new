package com.sitech.basd.cloud3.dao.newui;

import java.util.List;
import java.util.Map;

import com.sitech.basd.cloud3.domain.newui.HomePageSettingObj;

/**
 * 
 * <p>
 * Title: NewUIFuncDao
 * </p>
 * <p>
 * Description: 新UI接口Dao
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
public interface NewUIFuncDao {
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
	 * @Title: getGroupHomePageSetting
	 * @Description: 根据groupid查询角色自定义主页显示内容
	 * @param map
	 * @return	HomePageSettingObj
	 * @throws
	 * @author jiangdi
	 * @version 1.0
	 * @createtime 2014-6-20 上午10:23:50
	 */
	public List<HomePageSettingObj> getGroupHomePageSetting(Map<String, String> map);
}
