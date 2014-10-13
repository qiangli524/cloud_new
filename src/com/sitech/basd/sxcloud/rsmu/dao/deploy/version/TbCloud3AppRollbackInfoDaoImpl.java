package com.sitech.basd.sxcloud.rsmu.dao.deploy.version;

import java.sql.SQLException;
import java.util.List;

import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.domain.deploy.version.TbCloud3AppRollbackInfoVO;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;
import com.sitech.basd.sxcloud.util.UUIDGenerator;

/** 
 * 
 * <p>Title: TbCloud3AppFileVersionDao</p>
 * <p>Description: TODO(用一句话描述该文件做什么)</p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: SI-TECH </p>
 * @author wangqxa
 * @version 1.0
 * @createtime 2013-3-22 上午10:12:56
 *
 */
public class TbCloud3AppRollbackInfoDaoImpl extends BaseDao implements TbCloud3AppRollbackInfoDao{

	
	/**
	 * 
	 * @Title: insertByVO
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param
	 * @return String
	 * @throws
	 * @author wangqxa
	 * @version 1.0
	 * @createtime 2013-3-22 上午10:02:50
	 */
	@SuppressWarnings("static-access")
	public String insertByVO(TbCloud3AppRollbackInfoVO obj) {
		UUIDGenerator uuid = new UUIDGenerator();
		obj.setId(uuid.getUUID());
		String ret = "";
		try {
			Object o = getSqlMap()
					.insert("TbCloud3AppRollbackInfo.insertByVO", obj);
			if (o != null) {
				ret = o.toString();
			}
		} catch (SQLException e) {
			ret = "-1";
			LogHelper.error("TbCloud3AppRollbackInfo.insertByVO:" + e.getMessage());
		}
		return ret;
	}

	

	/**
	 * 
	 * @Title: queryVOById
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param
	 * @return TbCloud3AppFileVersionDao
	 * @throws
	 * @author wangqxa
	 * @version 1.0
	 * @createtime 2013-3-22 上午10:02:41
	 */
	public TbCloud3AppRollbackInfoVO queryVOByObj(TbCloud3AppRollbackInfoVO obj) {
		TbCloud3AppRollbackInfoVO vo = null;
		try {
			vo = (TbCloud3AppRollbackInfoVO) getSqlMap().queryForObject(
					"TbCloud3AppRollbackInfo.queryVOByObj", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("TbCloud3AppRollbackInfo.queryVOByObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return vo;
	}
	
	/**
	 * 
	 * @Title: queryVOById
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param
	 * @return TbCloud3AppFileVersionDao
	 * @throws
	 * @author wangqxa
	 * @version 1.0
	 * @createtime 2013-3-22 上午10:02:41
	 */
	public List queryListByObj(TbCloud3AppRollbackInfoVO obj) {
		List lst = null;
		try {
			lst = (List) getSqlMap().queryForList(
					"TbCloud3AppRollbackInfo.queryListByObj", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("TbCloud3AppRollbackInfo.queryListByObj:"
					+ sqlexception.getMessage() + getClass().getName());
			sqlexception.printStackTrace();
		}
		return lst;
	}

}
