package com.sitech.basd.sxcloud.rsmu.service.system;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sitech.basd.sxcloud.rsmu.dao.system.TbSysGrpmemberDao;
import com.sitech.basd.sxcloud.rsmu.dao.system.TbSysUsergroupDao;
import com.sitech.basd.sxcloud.rsmu.domain.system.TbSysGrpmemberObj;
import com.sitech.basd.sxcloud.rsmu.domain.system.TbSysUsergroupObj;
import com.sitech.basd.sxcloud.rsmu.service.BaseService;
@Service("usergroupService")
public class UsergroupServiceImpl extends BaseService implements
		UsergroupService {
	/**
	 * @Title:根据用户组部分信息查询匹配的所有用户组信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author zengls
	 * @version 1.0
	 */
	public List queryForListByObj(TbSysUsergroupObj obj) {
		return tbSysUsergroupDao.queryForListByObj(obj);
	}

	/**
	 * @Title:查询出具体用户组信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author zengls
	 * @version 1.0
	 */
	public TbSysUsergroupObj queryByObj(TbSysUsergroupObj obj) {
		return tbSysUsergroupDao.queryByObj(obj);
	}

	/**
	 * @Title:更新用户组信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author zengls
	 * @version 1.0
	 */
	public int updateByObj(TbSysUsergroupObj obj) {
		return tbSysUsergroupDao.updateByObj(obj);
	}

	/**
	 * @Title:删除用户组信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author zengls
	 * @version 1.0
	 */
	public int deleteByObj(TbSysUsergroupObj obj) {
		return tbSysUsergroupDao.deleteByObj(obj);
	}

	/**
	 * @Title:插入用户组信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author zengls
	 * @version 1.0
	 */
	public int insertByObj(TbSysUsergroupObj obj) {
		return tbSysUsergroupDao.insertByObj(obj);
	}

	/**
	 * @Title:查询出所有的组成员
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author zengls
	 * @version 1.0
	 */
	public List queryForListByObj(TbSysGrpmemberObj obj) {
		return tbSysGrpmemberDao.queryForListByObj(obj);
	}

	/**
	 * @Title:删除用户组成员
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author zengls
	 * @version 1.0
	 */
	public int deleteByObj(TbSysGrpmemberObj obj) {
		return tbSysGrpmemberDao.deleteByObj(obj);
	}
	/**
	 * @Title:删除用户组成员 通过userID
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author zengls
	 * @version 1.0
	 */
	public int deleteUserByObj(TbSysGrpmemberObj obj){
		return tbSysGrpmemberDao.deleteUserByObj(obj);
	}
	/**
	 * @Title:插入组成员
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author zengls
	 * @version 1.0
	 */
	public int insertByObj(TbSysGrpmemberObj obj) {
		return tbSysGrpmemberDao.insertByObj(obj);
	}

	private TbSysUsergroupDao tbSysUsergroupDao;
	private TbSysGrpmemberDao tbSysGrpmemberDao;

	public void setTbSysUsergroupDao(TbSysUsergroupDao tbSysUsergroupDao) {
		this.tbSysUsergroupDao = tbSysUsergroupDao;
	}

	public void setTbSysGrpmemberDao(TbSysGrpmemberDao tbSysGrpmemberDao) {
		this.tbSysGrpmemberDao = tbSysGrpmemberDao;
	}

}
