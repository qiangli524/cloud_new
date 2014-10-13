package com.sitech.basd.component.dao.excel;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.sitech.basd.component.domain.excel.DeployAppVO;
import com.sitech.basd.component.domain.excel.StandardAppVO;
import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;

/**
 * 
 * <p>
 * Title: AppExcelDaoImpl
 * </p>
 * <p>
 * Description: 智能部署ExcelDao实现类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2014
 * </p>
 * <p>
 * Company: SI-TECH
 * </p>
 * 
 * @author huojla
 * @version 1.0
 * @createtime 2014-1-3 下午5:35:41
 * 
 */
@Repository("appExcelDao")
public class AppExcelDaoImpl extends BaseDao implements AppExcelDao {
	/**
	 * 
	 * @Title: queryStandardAppVO
	 * @Description: 查询基准应用信息
	 * @param
	 * @return StandardAppVO
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2014-1-3 下午5:46:58
	 */
	public StandardAppVO queryStandardAppVO(StandardAppVO baseAppVO) {
		StandardAppVO standardAppVO = new StandardAppVO();
		try {
			standardAppVO = (StandardAppVO) getSqlMap().queryForObject(
					"AppExcel.queryStandardAppInfo", baseAppVO);
		} catch (Exception sqlException) {
			sqlException.printStackTrace();
			LogHelper.error("AppExcel.queryStandardAppInfo:" + sqlException.getMessage()
					+ getClass().getName());
		}
		return standardAppVO;
	}

	/**
	 * 
	 * @Title: queryDeployAppVOList
	 * @Description: 查询部署实例列表
	 * @param
	 * @return List<DeployAppVO>
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2014-1-3 下午5:48:31
	 */
	public List<DeployAppVO> queryDeployAppVOList(DeployAppVO baseAppVO) {
		List<DeployAppVO> list = new ArrayList<DeployAppVO>();
		try {
			list = getSqlMap().queryForList("AppExcel.queryDeployAppInfo", baseAppVO);
		} catch (Exception sqlException) {
			sqlException.printStackTrace();
			LogHelper.error("AppExcel.queryDeployAppInfo:" + sqlException.getMessage()
					+ getClass().getName());
		}
		return list;
	}

	/**
	 * 
	 * @Title: queryExampleNameList
	 * @Description: 查询部署实例名称列表
	 * @param
	 * @return List<DeployAppVO>
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2014-1-3 下午5:48:31
	 */
	public List<String> queryExampleNameList(String appId) {
		List<String> list = new ArrayList<String>();
		try {
			list = getSqlMap().queryForList("AppExcel.queryExampleNameList", appId);
		} catch (Exception sqlException) {
			sqlException.printStackTrace();
			LogHelper.error("AppExcel.queryExampleNameList:" + sqlException.getMessage()
					+ getClass().getName());
		}
		return list;
	}

	/**
	 * 
	 * @Title: queryDeployAppInfoByExample
	 * @Description: 查询部署实例信息
	 * @param
	 * @return List<DeployAppVO>
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2014-1-3 下午5:48:31
	 */
	public DeployAppVO queryDeployAppInfoByExample(DeployAppVO baseAppVO) {
		DeployAppVO deployAppVO = new DeployAppVO();
		try {
			deployAppVO = (DeployAppVO) getSqlMap().queryForObject(
					"AppExcel.queryDeployAppInfoByExample", baseAppVO);
		} catch (Exception sqlException) {
			sqlException.printStackTrace();
			LogHelper.error("AppExcel.queryDeployAppInfoByExample:" + sqlException.getMessage()
					+ getClass().getName());
		}
		return deployAppVO;
	}

	/**
	 * 
	 * @Title: queryScriptIdByProcess
	 * @Description: 查询进程关联脚本ID
	 * @param
	 * @return String
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2014-1-8 上午10:01:22
	 */
	public String queryScriptIdByProcess(String processId) {
		String result = "";
		try {
			result = (String) getSqlMap().queryForObject("AppExcel.queryScriptIdByProcess",
					processId);
		} catch (Exception sqlException) {
			sqlException.printStackTrace();
			LogHelper.error("AppExcel.queryScriptIdByProcess:" + sqlException.getMessage()
					+ getClass().getName());
		}
		return result;
	}
}
