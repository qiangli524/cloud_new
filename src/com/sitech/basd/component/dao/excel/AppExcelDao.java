package com.sitech.basd.component.dao.excel;

import java.util.List;

import com.sitech.basd.component.domain.excel.DeployAppVO;
import com.sitech.basd.component.domain.excel.StandardAppVO;

/**
 * 
 * <p>
 * Title: AppExcelDao
 * </p>
 * <p>
 * Description: 智能部署ExcelDao类
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
 * @createtime 2014-1-3 下午5:35:03
 * 
 */
public interface AppExcelDao {
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
	public StandardAppVO queryStandardAppVO(StandardAppVO baseAppVO);

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
	public List<DeployAppVO> queryDeployAppVOList(DeployAppVO baseAppVO);

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
	public List<String> queryExampleNameList(String appId);

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
	public DeployAppVO queryDeployAppInfoByExample(DeployAppVO baseAppVO);

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
	public String queryScriptIdByProcess(String processId);
}
