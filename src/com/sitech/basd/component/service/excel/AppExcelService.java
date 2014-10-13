package com.sitech.basd.component.service.excel;

import java.util.List;

import com.sitech.basd.component.domain.excel.BaseAppVO;
import com.sitech.basd.component.domain.process.ProcessObj;

/**
 * '
 * <p>
 * Title: AppExcelService
 * </p>
 * <p>
 * Description: 智能部署Excel逻辑处理层接口
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
 * @createtime 2014-1-3 下午2:53:37
 * 
 */
public interface AppExcelService {
	/**
	 * 
	 * @Title: initAppExcelData
	 * @Description: 实例应用Excel数据
	 * @param appId
	 *            - 基准应用ID
	 * @return List<BaseAppVO>
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2014-1-3 下午5:33:18
	 */
	public List<BaseAppVO> initAppExcelData(Integer appId);

	/**
	 * 
	 * @Title: initAppProcessExcelData
	 * @Description: 实例应用进程Excel数据
	 * @param appId
	 *            基准应用ID
	 * @return List<ProcessObj>
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2014-1-4 下午4:26:19
	 */
	public List<ProcessObj> initAppProcessExcelData(Integer appId);

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
	public List<String> queryExampleNameList(Integer appId);

	/**
	 * 
	 * @Title: saveAppProcessData
	 * @Description: 保存应用进程信息
	 * @param
	 * @return void
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2014-1-6 下午7:07:38
	 */
	public void saveAppProcessData(List<ProcessObj> processList);
}
