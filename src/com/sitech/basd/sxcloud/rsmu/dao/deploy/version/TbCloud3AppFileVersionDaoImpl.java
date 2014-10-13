package com.sitech.basd.sxcloud.rsmu.dao.deploy.version;

import java.sql.SQLException;
import java.util.List;

import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.domain.deploy.version.TbCloud3AppFileVersionVO;
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
public class TbCloud3AppFileVersionDaoImpl extends BaseDao implements TbCloud3AppFileVersionDao{

	
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
	public String insertByVO(TbCloud3AppFileVersionVO obj) {
		UUIDGenerator uuid = new UUIDGenerator();
		obj.setId(uuid.getUUID());
		String ret = "";
		try {
			Object o = getSqlMap()
					.insert("TbCloud3AppFileVersion.insertByVO", obj);
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
	public TbCloud3AppFileVersionVO queryVOByObj(TbCloud3AppFileVersionVO obj) {
		TbCloud3AppFileVersionVO vo = null;
		try {
			vo = (TbCloud3AppFileVersionVO) getSqlMap().queryForObject(
					"TbCloud3AppFileVersion.queryVOByObj", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("TbCloud3AppFileVersion.queryVOByObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return vo;
	}
	

	public TbCloud3AppFileVersionVO queryFileVersionForResumeByObj(TbCloud3AppFileVersionVO obj) {
		TbCloud3AppFileVersionVO vo = null;
		try {
			vo = (TbCloud3AppFileVersionVO) getSqlMap().queryForObject(
					"TbCloud3AppFileVersion.queryFileVersionForResumeByObj", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("TbCloud3AppFileVersion.queryFileVersionForResumeByObj:"
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
	public List queryListByObj(TbCloud3AppFileVersionVO obj) {
		List lst = null;
		try {
			lst = (List) getSqlMap().queryForList(
					"TbCloud3AppFileVersion.queryListByObj", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("TbCloud3AppFileVersion.queryListByObj:"
					+ sqlexception.getMessage() + getClass().getName());
			sqlexception.printStackTrace();
		}
		return lst;
	}

}
