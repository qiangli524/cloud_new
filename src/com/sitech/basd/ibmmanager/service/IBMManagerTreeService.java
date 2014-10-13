package com.sitech.basd.ibmmanager.service;

import java.util.List;

import com.sitech.basd.ibmmanager.domain.IBMManagerTreeObj;

/**
 * 
 * <p>
 * Title: IBMManagerTreeService
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
 * @createtime 2013-11-2 下午5:45:41
 * 
 */
public interface IBMManagerTreeService {
	/**
	 * 
	 * @Title: queryForTree
	 * @Description: 查询树节点
	 * @param
	 * @return List<IBMManagerTreeObj>
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-11-2 下午5:46:13
	 */
	public List<IBMManagerTreeObj> queryForTree(IBMManagerTreeObj obj);

	/**
	 * 
	 * @Title: initTreelist
	 * @Description: 初始化树节点
	 * @param
	 * @return List<IBMManagerTreeObj>
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-11-2 下午5:46:28
	 */
	public List<IBMManagerTreeObj> initTreelist(
			List<IBMManagerTreeObj> resultList);

	/**
	 * 
	 * @Title: insertTree
	 * @Description: 插入操作
	 * @param
	 * @return void
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-11-2 下午5:46:56
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
	 * @createtime 2013-11-2 下午5:47:09
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
	 * @createtime 2013-11-2 下午5:47:19
	 */
	public int updateTreeByObj(IBMManagerTreeObj obj);

	/**
	 * 
	 * @Title: queryExpandNodesForPower
	 * @Description: 查询展开树所需结点(整机)
	 * @param
	 * @return List
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-11-4 下午5:42:37
	 */
	public List queryExpandNodesForPower(IBMManagerTreeObj obj);

	/**
	 * 
	 * @Title: queryExpandNodesForLP
	 * @Description: 查询展开树所需节点(逻辑分区)
	 * @param
	 * @return List
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-11-4 下午5:41:41
	 */
	public List queryExpandNodesForLP(IBMManagerTreeObj obj);

	/**
	 * 
	 * @Title: queryForHostTreeByName
	 * @Description: 通过名字查询左侧树节点(主机)
	 * @param
	 * @return List<IBMManagerTreeObj>
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-11-4 下午6:01:20
	 */
	public List<IBMManagerTreeObj> queryForHostTreeByName(IBMManagerTreeObj obj);

	/**
	 * 
	 * @Title: queryForVMTreeByName
	 * @Description: 通过名字查询左侧树节点(虚拟机)
	 * @param
	 * @return List<IBMManagerTreeObj>
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-11-26 下午6:34:59
	 */
	public List<IBMManagerTreeObj> queryForVMTreeByName(IBMManagerTreeObj obj);
}
