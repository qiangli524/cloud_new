package com.sitech.basd.cloud3.service.newui;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.sitech.basd.cloud3.dao.newui.NewUIFuncDao;
import com.sitech.basd.cloud3.domain.newui.HomePageSettingObj;
import com.sitech.basd.sxcloud.cloud.dao.globalconfig.TbGlobalConfigDao;
import com.sitech.basd.sxcloud.cloud.domain.globalconfig.TbGlobalConfigObj;
import com.sitech.basd.sxcloud.config.Constant;
import com.sitech.basd.sxcloud.rsmu.dao.system.TbSysUserinfoDao;
import com.sitech.basd.sxcloud.rsmu.domain.system.TbSysUserinfoObj;
import com.sitech.basd.sxcloud.util.EncryptUtil;

public class NewUIFuncServiceImpl implements NewUIFuncService {
	private NewUIFuncDao newUIFuncDao;
	@Autowired
	private TbSysUserinfoDao tbSysUserinfoDao;
	@Autowired
	private TbGlobalConfigDao tbGlobalConfigDao;

	public void setNewUIFuncDao(NewUIFuncDao newUIFuncDao) {
		this.newUIFuncDao = newUIFuncDao;
	}

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
	public List<Map<String, String>> getNewUIFunc_new(Map<String, String> params) {
		return newUIFuncDao.getNewUIFunc_new(params);
	}

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
	public List<Map<String, String>> getNewUIAllowAdd(Map<String, String> params) {
		return newUIFuncDao.getNewUIAllowAdd(params);
	}

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
	public int insertNewUIUserFunc(Map<String, String> params) {
		return newUIFuncDao.insertNewUIUserFunc(params);
	}

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
	public int deleteNewUIUserFunc(Map<String, String> params) {
		return newUIFuncDao.deleteNewUIUserFunc(params);
	}

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
	public int insertNewUIPicFunc(Map map) {
		return newUIFuncDao.insertNewUIPicFunc(map);
	}

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
	public int updateNewUIUserFunc(Map map) {
		return newUIFuncDao.updateNewUIUserFunc(map);
	}

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
	public int updateNewUIPicFunc(Map map) {
		return newUIFuncDao.updateNewUIPicFunc(map);
	}

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
	public int deleteNewUIPicFunc(Map map) {
		return newUIFuncDao.deleteNewUIPicFunc(map);
	}

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
	public TbSysUserinfoObj queryUserInfo(String username) {
		TbSysUserinfoObj obj = new TbSysUserinfoObj();
		obj.setACCOUNT(username);
		TbSysUserinfoObj tbSysUserinfoObj = tbSysUserinfoDao.queryByObj(obj);
		if (tbSysUserinfoObj == null) {
			tbSysUserinfoObj = new TbSysUserinfoObj();
			tbSysUserinfoObj.setACCOUNT("error username");
		} else {
			tbSysUserinfoObj.setPASSWORD(EncryptUtil.decode(tbSysUserinfoObj.getPASSWORD()));
		}
		return tbSysUserinfoObj;
	}

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
	public String queryCasUrlByGlobalConfig() {
		TbGlobalConfigObj obj = new TbGlobalConfigObj();
		obj.setKEY(Constant.CAS_URL);
		TbGlobalConfigObj result = tbGlobalConfigDao.queryByObj(obj);
		return result != null ? result.getVALUE() : "http://localhost:8080/cas_sso";
	}

	@Override
	public List<HomePageSettingObj> getGroupHomePageSetting(String groupId,String width) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("gourpid", groupId);
		map.put("width", width);
		return newUIFuncDao.getGroupHomePageSetting(map);
	}
}
