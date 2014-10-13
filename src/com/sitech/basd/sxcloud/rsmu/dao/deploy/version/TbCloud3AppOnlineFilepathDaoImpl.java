package com.sitech.basd.sxcloud.rsmu.dao.deploy.version;

import java.sql.SQLException;
import java.util.List;

import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.domain.deploy.version.TbCloud3AppOnlineFilepathVO;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;

/**
 * 
 * <p>
 * Title: TbCloud3AppCurrentFilepathDaoImpl
 * </p>
 * <p>
 * Description: TODO(用一句话描述该文件做什么)
 * </p>
 * <p>
 * Copyright: Copyright (c) 2013
 * </p>
 * <p>
 * Company: SI-TECH
 * </p>
 * 
 * @author wangqxa
 * @version 1.0
 * @createtime 2013-3-28 下午5:12:58
 * 
 */
public class TbCloud3AppOnlineFilepathDaoImpl extends BaseDao implements
		TbCloud3AppOnlineFilepathDao {

	/**
	 * 
	 */
	public String insertByVO(TbCloud3AppOnlineFilepathVO obj) {
		String ret = "";
		try {
			Object o = getSqlMap().insert(
					"TbCloud3AppOnlineFilepath.insertByVO", obj);
			if (o != null) {
				ret = o.toString();
			}
		} catch (SQLException e) {
			ret = "-1";
			LogHelper.error("TbCloud3AppOnlineFilepath.insertByVO:"
					+ e.getMessage());
		}
		return ret;
	}

	/**
	 * 
	 */
	public String updateByVO(TbCloud3AppOnlineFilepathVO obj) {
		String ret = "";
		try {
			Object o = getSqlMap().insert(
					"TbCloud3AppOnlineFilepath.updateByVO", obj);
			if (o != null) {
				ret = o.toString();
			}
		} catch (SQLException e) {
			ret = "-1";
			LogHelper.error("TbCloud3AppOnlineFilepath.updateByVO:"
					+ e.getMessage());
		}
		return ret;
	}

	public TbCloud3AppOnlineFilepathVO queryVOByObj(
			TbCloud3AppOnlineFilepathVO obj) {
		TbCloud3AppOnlineFilepathVO vo = null;
		try {
			vo = (TbCloud3AppOnlineFilepathVO) getSqlMap().queryForObject(
					"TbCloud3AppOnlineFilepath.queryVOByObj", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("TbCloud3AppOnlineFilepath.queryVOByObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return vo;
	}

	/**
	 * 
	 * @Title: queryCollListByObj
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param
	 * @return List<TbCloud3AppOnlineFilepathVO>
	 * @throws
	 * @author wangqxa
	 * @version 1.0
	 * @createtime 2013-3-29 上午11:47:57
	 */
	public List<TbCloud3AppOnlineFilepathVO> queryListByObj(
			TbCloud3AppOnlineFilepathVO obj) {
		List<TbCloud3AppOnlineFilepathVO> lst = null;
		try {
			lst = (List<TbCloud3AppOnlineFilepathVO>) getSqlMap().queryForList(
					"TbCloud3AppOnlineFilepath.queryListByObj", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("TbCloud3AppOnlineFilepath.queryListByObj:"
					+ sqlexception.getMessage() + getClass().getName());
			sqlexception.printStackTrace();
		}
		return lst;
	}

	/**
	 * 
	 * @Title: queryListByAppId
	 * @Description: TODO(根据appid查找上线文件List)
	 * @param
	 * @return List<TbCloud3AppOnlineFilepathVO>
	 * @throws
	 * @author wangqxa
	 * @version 1.0
	 * @createtime 2013-3-29 上午11:47:57
	 */
	public List<TbCloud3AppOnlineFilepathVO> queryListByAppId(
			TbCloud3AppOnlineFilepathVO obj) {
		List<TbCloud3AppOnlineFilepathVO> lst = null;
		try {
			lst = (List<TbCloud3AppOnlineFilepathVO>) getSqlMap().queryForList(
					"TbCloud3AppOnlineFilepath.queryListByAppId", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("TbCloud3AppOnlineFilepath.queryListByAppId:"
					+ sqlexception.getMessage() + getClass().getName());
			sqlexception.printStackTrace();
		}
		return lst;
	}

}
