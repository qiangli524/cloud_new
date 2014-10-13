package com.sitech.basd.deployfile.dao;

import java.util.List;

import com.sitech.basd.deployfile.domain.DeployFileTreeObj;

/**
 * 
 * <p>
 * Title: DeployFileTreeDao
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
 * @createtime 2013-12-16 上午11:22:14
 * 
 */
public interface DeployFileTreeDao {
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
}
