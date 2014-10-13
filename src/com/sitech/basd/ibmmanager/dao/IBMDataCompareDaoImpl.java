package com.sitech.basd.ibmmanager.dao;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.sitech.basd.ibmmanager.domain.HMCObj;
import com.sitech.basd.ibmmanager.domain.LparObj;
import com.sitech.basd.ibmmanager.domain.PowObj;
import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;

/**
 * 
 * <p>
 * Title: IBMDataCompareDaoImpl
 * </p>
 * <p>
 * Description: IBM数据对比相关操作
 * </p>
 * <p>
 * Copyright: Copyright (c) 2013
 * </p>
 * <p>
 * Company: SI-TECH
 * </p>
 * 
 * @author yanggl
 * @version 1.0
 * @createtime 2013-11-9 下午12:34:48
 * 
 */
@Repository("ibmDataCompareDao")
public class IBMDataCompareDaoImpl extends BaseDao implements IBMDataCompareDao {
	/**
	 * 
	 * @Title: getIBMUniqueEntity
	 * @Description: 获取IBM唯一实体（用于数据比对）
	 * @param
	 * @return List<String>
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-11-9 下午12:32:50
	 */
	@Override
	public List<String> getIBMUniqueEntity() {
		List list = null;
		try {
			list = getSqlMap().queryForList("IBMDataCompare.getIBMUniqueEntity");
		} catch (SQLException e) {
			LogHelper.error("IBMDataCompare.getIBMUniqueEntity:" + e.getMessage()
					+ getClass().getName());
		}
		return list;
	}

	/**
	 * 
	 * @Title: getHMCData
	 * @Description: 获取HMC数据
	 * @param
	 * @return List<HMCObj>
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-11-7 下午2:40:45
	 */
	@Override
	public List<HMCObj> getHMCData() {
		List<HMCObj> list = null;
		try {
			list = getSqlMap().queryForList("IBMDataCompare.getHMCData");
		} catch (SQLException e) {
			LogHelper.error("IBMDataCompare.getHMCData:" + e.getMessage() + getClass().getName());
		}
		return list;
	}

	/**
	 * 
	 * @Title: getPowerData
	 * @Description: 获取Power数据
	 * @param
	 * @return List<PowObj>
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-11-7 下午2:49:16
	 */
	@Override
	public List<PowObj> getPowerData() {
		List<PowObj> list = null;
		try {
			list = getSqlMap().queryForList("IBMDataCompare.getPowerData");
		} catch (SQLException e) {
			LogHelper.error("IBMDataCompare.getPowerData:" + e.getMessage() + getClass().getName());
		}
		return list;
	}

	/**
	 * 
	 * @Title: getLparData
	 * @Description: 获取Lpar数据
	 * @param
	 * @return List<LparObj>
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-11-7 下午2:49:35
	 */
	@Override
	public List<LparObj> getLparData() {
		List<LparObj> list = null;
		try {
			list = getSqlMap().queryForList("IBMDataCompare.getLparData");
		} catch (SQLException e) {
			LogHelper.error("IBMDataCompare.getLparData:" + e.getMessage() + getClass().getName());
		}
		return list;
	}

}
