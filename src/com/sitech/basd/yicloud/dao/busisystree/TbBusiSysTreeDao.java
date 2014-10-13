package com.sitech.basd.yicloud.dao.busisystree;

import java.util.List;

import com.sitech.basd.yicloud.domain.busisystree.TbBusiSysTree;

/**
 * 
 * <p>
 * Title: TbBusiSysTreeDao
 * </p>
 * <p>
 * Description: 业务系统树数据库Dao
 * </p>
 * <p>
 * Copyright: Copyright (c) 2013
 * </p>
 * <p>
 * Company: SI-TECH
 * </p>
 * 
 * @author huojla
 * @version 1.0
 * @createtime 2013-5-20 下午1:44:34
 * 
 */
public interface TbBusiSysTreeDao {
	/**
	 * 
	 * @Title: queryForTree
	 * @Description: 查询业务中心，业务系统，应用等生成树
	 * @param
	 * @return List<TbBusiSysTree>
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-5-20 下午1:48:23
	 */
	public List<TbBusiSysTree> queryForTree(TbBusiSysTree obj);

	/**
	 * 
	 * @Title: queryForLimitTree
	 * @Description: 查询业务中心，业务系统，应用等生成权限树
	 * @param
	 * @return List<TbBusiSysTree>
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-5-20 下午1:48:23
	 */
	public List<TbBusiSysTree> queryForLimitTree(TbBusiSysTree obj);

	/**
	 * 
	 * @Title: insertTbBusiSysTree
	 * @Description: 向业务中心的树中插入数据
	 * @param
	 * @return String
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-5-20 下午1:48:30
	 */
	public String insertTbBusiSysTree(TbBusiSysTree obj);

	/**
	 * 
	 * @Title: deleteTbBusiSysTreeById
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param
	 * @return int
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-5-22 下午2:30:11
	 */
	public int deleteTbBusiSysTreeById(TbBusiSysTree obj);
	
	/**
	 * 
	 * @Title: updateTreeNode
	 * @Description: 更新树节点信息
	 * @param
	 * @return int
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime May 30, 2013 6:54:45 PM
	 */
	public int updateTbBusiSysTreeByObj(TbBusiSysTree obj);
	
	/**
	 * 
	 * @Title: queryBusiCenterSonNodesNum
	 * @Description: 查询业务中心下各类型子节点的个数
	 * @param
	 * @return TbBusiSysTree
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime Jun 22, 2013 11:39:18 AM
	 */
	public TbBusiSysTree queryBusiCenterSonNodesNum(TbBusiSysTree obj);

	/**
	 * @Title: countByObj
	 * @Description: 统计业务系统下各子节点个数
	 * @param
	 * @return int
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-8-14 下午5:42:53
	 */
	public int countByObj(TbBusiSysTree tbBusiSysTree);

	/**
	 * @Title: queryForListByParentIdList
	 * @Description: 根据节点类型和父节点的id集合查询节点集合
	 * @param
	 * @return List<TbBusiSysTree>
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-8-19 下午2:48:39
	 */
	public List<TbBusiSysTree> queryForListByParentIdList(
			TbBusiSysTree tbBusiSysTree);
}
