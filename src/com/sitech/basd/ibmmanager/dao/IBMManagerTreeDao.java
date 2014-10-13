package com.sitech.basd.ibmmanager.dao;

import java.util.List;

import com.sitech.basd.ibmmanager.domain.IBMManagerTreeObj;

/**
 * 
 * <p>
 * Title: IBMManagerTreeDao
 * </p>
 * <p>
 * Description: IBM小型机相关操作
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
 * @createtime 2013-11-2 下午5:24:15
 * 
 */
public interface IBMManagerTreeDao {
	/**
	 * 
	 * @Title: queryForTree
	 * @Description: 查询左侧树节点
	 * @param
	 * @return List<IBMManagerTreeObj>
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-11-2 下午5:25:34
	 */
	public List<IBMManagerTreeObj> queryForTree(IBMManagerTreeObj obj);

	/**
	 * 
	 * @Title: insertTree
	 * @Description: 插入操作
	 * @param
	 * @return void
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-11-2 下午5:26:58
	 */
	public void insertTree(IBMManagerTreeObj obj);

	/**
	 * 
	 * @Title: deleteTreeById
	 * @Description: 删除操作
	 * @param
	 * @return int
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-11-2 下午5:27:25
	 */
	public int deleteTreeById(IBMManagerTreeObj obj);

	/**
	 * 
	 * @Title: updateTreeByObj
	 * @Description: 修改操作
	 * @param
	 * @return int
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-11-2 下午5:27:38
	 */
	public int updateTreeByObj(IBMManagerTreeObj obj);

	/**
	 * 
	 * @Title: queryExpandNodesForPower
	 * @Description: 查询展开树所需结点(整机)
	 * @param
	 * @return List<IBMManagerTreeObj>
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-11-4 下午5:42:37
	 */
	public List<IBMManagerTreeObj> queryExpandNodesForPower(
			IBMManagerTreeObj obj);

	/**
	 * 
	 * @Title: queryExpandNodesForLP
	 * @Description: 查询展开树所需节点(逻辑分区)
	 * @param
	 * @return List<IBMManagerTreeObj>
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-11-4 下午5:41:41
	 */
	public List<IBMManagerTreeObj> queryExpandNodesForLP(IBMManagerTreeObj obj);

	/**
	 * 
	 * @Title: queryForTreeByName
	 * @Description: 通过名字查询左侧树节点
	 * @param
	 * @return List<IBMManagerTreeObj>
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-11-4 下午6:01:20
	 */
	public List<IBMManagerTreeObj> queryForTreeByName(IBMManagerTreeObj obj);
}
