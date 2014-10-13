package com.sitech.basd.deployfile.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.sitech.basd.deployfile.domain.DeployFileTreeObj;
import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;

/**
 * 
 * <p>
 * Title: DeployFileTreeDaoImpl
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
 * @createtime 2013-12-16 上午11:26:29
 * 
 */
@Repository("deployFileTreeDao")
public class DeployFileTreeDaoImpl extends BaseDao implements DeployFileTreeDao {
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
	@Override
	public List<DeployFileTreeObj> queryForTree(DeployFileTreeObj obj) {
		List<DeployFileTreeObj> list = new ArrayList<DeployFileTreeObj>();
		try {
			list = getSqlMap().queryForList("DeployFileTree.queryForTree", obj);
		} catch (SQLException e) {
			logger.error("DeployFileTree.queryForTree:" + e.getMessage() + e.getClass().getName());
		}
		return list;
	}
}
