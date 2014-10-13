package com.sitech.basd.sxcloud.rsmu.service.hostmanage;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.sitech.basd.component.dao.user.UserDao;
import com.sitech.basd.component.domain.user.UserObj;
import com.sitech.basd.sxcloud.rsmu.dao.hostmanage.TbBusiHostConfigDao;
import com.sitech.basd.sxcloud.rsmu.dao.hostmanage.TbBusiHostObjDao;
import com.sitech.basd.sxcloud.rsmu.domain.hostmanage.TbBusiHostConfigObj;
import com.sitech.basd.sxcloud.rsmu.domain.hostmanage.TbBusiHostObj;
import com.sitech.basd.sxcloud.rsmu.service.BaseService;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;

public class BusiHostConfigServiceImpl extends BaseService implements
		BusiHostConfigService {
	private TbBusiHostConfigDao tbBusiHostConfigDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private TbBusiHostObjDao tbBusiHostObjDao;

	public void setTbBusiHostConfigDao(TbBusiHostConfigDao tbBusiHostConfigDao) {
		this.tbBusiHostConfigDao = tbBusiHostConfigDao;
	}

	/**
	 * @Title:删除主机配置信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author yangwenchao
	 * @version 1.0
	 */
	public int deleteByObj(TbBusiHostConfigObj obj) {
		// TODO Auto-generated method stub
		return tbBusiHostConfigDao.deleteByObj(obj);
	}

	/**
	 * @Title:插入主机配置信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author yangwenchao
	 * @version 1.0
	 */
	public int insertByObj(TbBusiHostConfigObj obj) {
		// 保存公共组件用户信息到TB_USER_MANAGE表
//		saveComponentUserinfo(obj);
		// TODO Auto-generated method stub
		return tbBusiHostConfigDao.insertByObj(obj);
	}

	/**
	 * @Title:查询出具体主机配置信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author yangwenchao
	 * @version 1.0
	 */
	public TbBusiHostConfigObj queryByObj(TbBusiHostConfigObj obj) {
		// TODO Auto-generated method stub
		return tbBusiHostConfigDao.queryByObj(obj);
	}

	/**
	 * @Title:根据主机配置部分信息查询匹配的所有主机配置信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author yangwenchao
	 * @version 1.0
	 */
	public List queryForListByObj(TbBusiHostConfigObj obj) {
		// TODO Auto-generated method stub
		return tbBusiHostConfigDao.queryForListByObj(obj);
	}

	/**
	 * 
	 * @Title: queryForListByHostId
	 * @Description: TODO(通过主机编号查找用户信息)
	 * @param
	 * @return List
	 * @throws
	 * @author wangqxa
	 * @version 1.0
	 * @createtime 2013-4-12 下午7:31:05
	 */
	public List queryForListByHostId(TbBusiHostConfigObj obj) {
		return tbBusiHostConfigDao.queryForListByHostId(obj);
	}

	/**
	 * @Title:更新主机配置信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author yangwenchao
	 * @version 1.0
	 */
	public int updateByObj(TbBusiHostConfigObj obj) {
		// TODO Auto-generated method stub
		return tbBusiHostConfigDao.updateByObj(obj);
	}

	/**
	 * 
	 * @Title: updateHostConfigByUsername
	 * @Description: 更新用户关联基准应用
	 * @param
	 * @return String
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-4-13 下午1:06:44
	 */
	public int updateHostConfigByUsername(TbBusiHostConfigObj obj) {
		return tbBusiHostConfigDao.updateHostConfigByUsername(obj);
	}

	/**
	 * 
	 * @Title: saveComponentUserinfo
	 * @Description: 保存公共组件用户信息到TB_USER_MANAGE表
	 * @param
	 * @return int
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-5-29 上午11:06:56
	 */
	public int saveComponentUserinfo(TbBusiHostConfigObj hostConfig) {
		int result = 0;
		TbBusiHostObj hostObj = new TbBusiHostObj();
		hostObj.setID(hostConfig.getHOSTID());
		TbBusiHostObj resultHostObj = tbBusiHostObjDao.queryByObj(hostObj);
		String hostIp = resultHostObj.getIP();
		String username = hostConfig.getHOSTUSERNAME();
		String password = hostConfig.getSshPwd();
		UserObj userObj = new UserObj();
		userObj.setUsername(username);
		userObj.setPassword(password);
		userObj.setIp(hostIp);
		result = userDao.insertByObj(userObj);
		return result;
	}
	
	/**
	 * 
	 * @Title: updateHostConfigByAppId
	 * @Description: 根据应用的id修改主机的配置信息
	 * @param
	 * @return int
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime Jun 27, 2013 10:41:46 AM
	 */
	public int updateHostConfigByAppId(TbBusiHostConfigObj obj) {
		return tbBusiHostConfigDao.updateHostConfigByAppId(obj);
	}
	
	/**
	 * 
	 * @Title: queryForHostConfigAndDepListByObj
	 * @Description: 查询主机下实例的信息
	 * @param
	 * @return List
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime Jul 9, 2013 10:31:25 AM
	 */
	public List queryForHostConfigAndDepListByObj(TbBusiHostConfigObj obj) {
		return tbBusiHostConfigDao.queryForHostConfigAndDepListByObj(obj);
	}

}
