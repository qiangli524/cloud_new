package com.sitech.basd.ibmmanager.service;

import java.util.List;
import java.util.Map;

import com.sitech.basd.exception.DataSynchroException;
import com.sitech.basd.ibmmanager.domain.HMCObj;
import com.sitech.basd.ibmmanager.domain.LparObj;
import com.sitech.basd.ibmmanager.domain.PowObj;
import com.sitech.vo.united.ResultSet;

/**
 * 
 * <p>
 * Title: IBMDataCompareService
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
 * @createtime 2013-11-9 下午12:54:27
 * 
 */
public interface IBMDataCompareService {
	/**
	 * 
	 * @Title: initIBMApiDataMap
	 * @Description: 查询IBM数据(SX_HMC,SX_POWER,SX_LOGICAL_PARTITION
	 *               )(key分别是IBM@uuid@HMC,IBM@uuid@Power,IBM@uuid@Lpar)(
	 *               Object是key拆分开相对应的对象)
	 * @param
	 * @return Map<String,Object>
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @throws DataSynchroException
	 * @createtime 2013-11-7 上午11:49:54
	 */
	public Map<String, Object> initIBMApiDataMap() throws DataSynchroException;

	/**
	 * 
	 * @Title: initIBMDbDataList
	 * @Description: 
	 *               查询出树表中IBM的数据(String是IBM@uuid@HMC,IBM@uuid@Power,IBM@uuid@Lpar)
	 * @param
	 * @return List<String>
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-11-7 下午2:44:04
	 */
	public List<String> initIBMDbDataList();

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

	/**
	 * 
	 * @Title: IBMDataCompare
	 * @Description: IBM 数据同步
	 * @param
	 * @return void
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-11-7 下午3:58:37
	 */
	public void IBMDataCompare() throws DataSynchroException;

	/**
	 * 
	 * @Title: updateTreeParentNode
	 * @Description: 更新当前父节点数据
	 * @param
	 * @return void
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-11-7 下午4:14:21
	 */
	public void updateTreeParentNode(ResultSet entityObj, String entityType, String parentId);

	/**
	 * 
	 * @Title: saveTreeNode
	 * @Description: 保存树节点
	 * @param
	 * @return void
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-11-7 下午5:03:29
	 */
	public void saveTreeNode(ResultSet entityObj, String entityType, String parentId);

	/**
	 * 
	 * @Title: deleteTreeNode
	 * @Description: 删除树节点
	 * @param
	 * @return void
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-11-8 下午3:01:10
	 */
	public void deleteTreeNode(String entityCode);

	/**
	 * 
	 * @Title: getParentNodeId
	 * @Description: 查询父节点ID
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-11-7 下午4:50:01
	 */
	public String getParentNodeId(String entityCode);

}
