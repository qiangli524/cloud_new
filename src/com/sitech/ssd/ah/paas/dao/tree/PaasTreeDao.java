package com.sitech.ssd.ah.paas.dao.tree;

import java.util.List;

import com.sitech.ssd.ah.paas.domain.entity.PaasEntityObj;
import com.sitech.ssd.ah.paas.domain.tree.PaasTreeObj;

/**
 * 
 * <p>
 * Title: PaasTreeDao
 * </p>
 * <p>
 * Description: paas资源树相关操作
 * </p>
 * <p>
 * Copyright: Copyright (c) 2014
 * </p>
 * <p>
 * Company: SI-TECH
 * </p>
 * 
 * @author yanggl
 * @version 1.0
 * @createtime 2014-3-28 上午9:14:18
 * 
 */
public interface PaasTreeDao {
	/**
	 * 
	 * @Title: queryForPaasTree
	 * @Description: 查询资源树
	 * @param
	 * @return List<PaasTreeObj>
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-3-17 下午5:34:20
	 */
	public List<PaasTreeObj> queryForPaasTree(PaasTreeObj obj);

	/**
	 * 
	 * @Title: insertByObj
	 * @Description: 添加
	 * @param
	 * @return int
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-3-25 下午4:30:24
	 */
	public int insertByObj(PaasTreeObj obj);

	/**
	 * 
	 * @Title: updateByObj
	 * @Description: 修改
	 * @param
	 * @return int
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-3-25 下午4:30:37
	 */
	public int updateByObj(PaasTreeObj obj);

	/**
	 * 
	 * @Title: deleteByObj
	 * @Description: 删除
	 * @param
	 * @return int
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-3-25 下午4:30:47
	 */
	public int deleteByObj(PaasTreeObj obj);

	/**
	 * 
	 * @Title: queryNodesForResourcePool
	 * @Description: 查询资源池集合
	 * @param
	 * @return List<PaasTreeObj>
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-3-28 上午9:18:31
	 */
	public List<PaasTreeObj> queryNodesForResourcePool(PaasTreeObj obj);

	/**
	 * 
	 * @Title: queryNodesForExamples
	 * @Description: 查询实例集合
	 * @param
	 * @return List<PaasTreeObj>
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-3-28 上午9:18:35
	 */
	public List<PaasTreeObj> queryNodesForExamples(PaasTreeObj obj);

	/**
	 * 
	 * @Title: insertByEntityObj
	 * @Description: 添加实例时插入实体表
	 * @param
	 * @return int
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-4-23 上午9:15:56
	 */
	public int insertByEntityObj(PaasEntityObj obj);

}
