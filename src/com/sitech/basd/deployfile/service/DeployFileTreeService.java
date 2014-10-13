package com.sitech.basd.deployfile.service;

import java.util.List;

import com.sitech.basd.deployfile.domain.DeployFileTreeObj;

/**
 * 
 * <p>
 * Title: DeployFileTreeService
 * </p>
 * <p>
 * Description: 部署文件树备份和回滚操作
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
 * @createtime 2013-12-16 下午12:11:04
 * 
 */
public interface DeployFileTreeService {
	/**
	 * 
	 * @Title: queryForTree
	 * @Description: 查询树节点
	 * @param
	 * @return List<DeployFileTreeObj>
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-12-16 下午1:43:17
	 */
	public List<DeployFileTreeObj> queryForTree(DeployFileTreeObj obj);

	/**
	 * 
	 * @Title: initTreelist
	 * @Description: 初始化树节点
	 * @param
	 * @return List<DeployFileTreeObj>
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-12-16 下午2:00:35
	 */
	public List<DeployFileTreeObj> initTreelist(List<DeployFileTreeObj> resultList);
}
