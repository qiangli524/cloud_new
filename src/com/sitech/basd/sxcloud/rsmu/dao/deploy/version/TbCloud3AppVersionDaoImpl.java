package com.sitech.basd.sxcloud.rsmu.dao.deploy.version;

import java.sql.SQLException;
import java.util.List;

import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.domain.deploy.version.TbCloud3AppVersionVO;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;
import com.sitech.basd.sxcloud.util.UUIDGenerator;

/**
 * 
 * <p>
 * Title: TbCloud3AppVersionDao
 * </p>
 * <p>
 * Description: 应用版本Dao
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
 * @createtime 2013-3-23 上午10:20:56
 * 
 */
public class TbCloud3AppVersionDaoImpl  extends BaseDao implements TbCloud3AppVersionDao {

	

	/**
	 * 
	 * @Title: insertByVO
	 * @Description: 插入数据
	 * @param
	 * @return String
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-3-23 上午10:21:45
	 */
	@SuppressWarnings("static-access")
	public String insertByVO(TbCloud3AppVersionVO obj) {
		UUIDGenerator uuid = new UUIDGenerator();
		obj.setId(uuid.getUUID());
		String ret = "";
		try {
			Object o = getSqlMap().insert("TbCloud3AppVersion.insertByVO", obj);
			if (o != null) {
				ret = o.toString();
			}
		} catch (SQLException e) {
			ret = "-1";
			LogHelper.error("TbCloud3AppVersion.insertByVO:" + e.getMessage());
		}
		return ret;
	}

	/**
	 * 
	 * @Title: queryVOByObj
	 * @Description: 查询数据
	 * @param
	 * @return TbCloud3AppVersionDao
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-3-23 上午10:21:33
	 */
	public TbCloud3AppVersionVO queryVOByObj(TbCloud3AppVersionVO obj) {
		TbCloud3AppVersionVO vo = null;
		try {
			vo = (TbCloud3AppVersionVO) getSqlMap().queryForObject(
					"TbCloud3AppVersion.queryVOByObj", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("TbCloud3AppVersion.queryVOByObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return vo;
	}
	
	/**
	 * 
	 * @Title: queryVOByObj
	 * @Description: 查询数据
	 * @param
	 * @return TbCloud3AppVersionDao
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-3-23 上午10:21:33
	 */
	public List<TbCloud3AppVersionVO> queryListByObj(TbCloud3AppVersionVO obj) {
		List<TbCloud3AppVersionVO> lst = null;
		try {
			lst = (List<TbCloud3AppVersionVO>) getSqlMap().queryForList("TbCloud3AppVersion.queryListByObj", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("TbCloud3AppVersion.queryListByObj:"
					+ sqlexception.getMessage() + getClass().getName());
			sqlexception.printStackTrace();
		}
		return lst;
	}

	/**
	 * 
	 * @Title: queryCollListByObj
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param
	 * @return List<TbCloud3AppVersionVO>
	 * @throws
	 * @author wangqxa
	 * @version 1.0
	 * @createtime 2013-3-29 上午11:47:57
	 */
	public List<TbCloud3AppVersionVO> queryCollListByObj(TbCloud3AppVersionVO obj) {
		List<TbCloud3AppVersionVO> lst = null;
		try {
			lst = (List<TbCloud3AppVersionVO>) getSqlMap().queryForList("TbCloud3AppVersion.queryCollListByObj", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("TbCloud3AppVersion.queryCollListByObj:"
					+ sqlexception.getMessage() + getClass().getName());
			sqlexception.printStackTrace();
		}
		return lst;
	}
	
	/**
	 * 
	 * @Title: queryMinAppVersion
	 * @Description: TODO(查询初始化版本)
	 * @param
	 * @return String
	 * @throws
	 * @author wangqxa
	 * @version 1.0
	 * @createtime 2013-4-9 下午4:20:34
	 */
	public String queryMinAppVersion(TbCloud3AppVersionVO obj) {
		String vo = "";
		try {
			vo = (String) getSqlMap().queryForObject(
					"TbCloud3AppVersion.queryMinAppVersion", obj);
			if (vo == null) {
				vo = "";
			}
		} catch (Exception sqlexception) {
			vo = "";
			LogHelper.error("TbCloud3AppVersion.queryMinAppVersion:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return vo;
	}

}
