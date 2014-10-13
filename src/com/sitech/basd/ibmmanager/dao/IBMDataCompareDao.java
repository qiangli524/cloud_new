package com.sitech.basd.ibmmanager.dao;

import java.util.List;

import com.sitech.basd.ibmmanager.domain.HMCObj;
import com.sitech.basd.ibmmanager.domain.LparObj;
import com.sitech.basd.ibmmanager.domain.PowObj;

/**
 * 
 * <p>
 * Title: IBMDataCompareDao
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
 * @createtime 2013-11-9 下午12:33:06
 * 
 */
public interface IBMDataCompareDao {
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
	public List<String> getIBMUniqueEntity();

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
	public List<HMCObj> getHMCData();

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
	public List<PowObj> getPowerData();

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
	public List<LparObj> getLparData();

}
