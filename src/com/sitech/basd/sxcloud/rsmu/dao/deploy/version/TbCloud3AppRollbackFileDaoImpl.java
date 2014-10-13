package com.sitech.basd.sxcloud.rsmu.dao.deploy.version;

import java.sql.SQLException;
import java.util.List;

import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.domain.deploy.version.TbCloud3AppRollbackFileVO;
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
public class TbCloud3AppRollbackFileDaoImpl extends BaseDao implements TbCloud3AppRollbackFileDao{

	
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
	public String insertByVO(TbCloud3AppRollbackFileVO obj) {
		UUIDGenerator uuid = new UUIDGenerator();
		obj.setId(uuid.getUUID());
		String ret = "";
		try {
			Object o = getSqlMap()
					.insert("TbCloud3AppRollbackFile.insertByVO", obj);
			if (o != null) {
				ret = o.toString();
			}
		} catch (SQLException e) {
			ret = "-1";
			LogHelper.error("TbCloud3AppFileVersion.insertByVO:" + e.getMessage());
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
	public TbCloud3AppRollbackFileVO queryVOByObj(TbCloud3AppRollbackFileVO obj) {
		TbCloud3AppRollbackFileVO vo = null;
		try {
			vo = (TbCloud3AppRollbackFileVO) getSqlMap().queryForObject(
					"TbCloud3AppRollbackFile.queryVOByObj", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("TbCloud3AppRollbackFile.queryVOByObj:"
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
	public List queryListByObj(TbCloud3AppRollbackFileVO obj) {
		List lst = null;
		try {
			lst = (List) getSqlMap().queryForList(
					"TbCloud3AppRollbackFile.queryListByObj", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("TbCloud3AppRollbackFile.queryListByObj:"
					+ sqlexception.getMessage() + getClass().getName());
			sqlexception.printStackTrace();
		}
		return lst;
	}

}
