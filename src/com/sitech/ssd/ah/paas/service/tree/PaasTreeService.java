package com.sitech.ssd.ah.paas.service.tree;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.sitech.ssd.ah.paas.domain.tree.PaasTreeObj;

/**
 * 
 * <p>
 * Title: PaasTreeService
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
 * @createtime 2014-3-28 上午9:16:30
 * 
 */
public interface PaasTreeService {

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
	public List<PaasTreeObj> queryForPaasTree(HttpServletRequest request);

	/**
	 * 
	 * @Title: queryForAuthTree
	 * @Description: 查询资源权限树
	 * @param
	 * @return List<PaasTreeObj>
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-3-17 下午6:24:11
	 */
	public List<PaasTreeObj> queryForAuthTree(HttpServletRequest request,
			int userId);

	/**
	 * 
	 * @Title: insertByObj
	 * @Description: 添加
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-3-25 下午4:30:24
	 */
	public String insertByObj(PaasTreeObj obj);

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
	 * @Title: queryTreeNodeByObj
	 * @Description: 通过名字查询树节点
	 * @param
	 * @return List<PaasTreeObj>
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-3-27 下午5:53:30
	 */
	public List<PaasTreeObj> queryTreeNodeByObj(PaasTreeObj obj);

	/**
	 * 
	 * @Title: queryForPaasTreeObj
	 * @Description: 查询单条数据
	 * @param
	 * @return PaasTreeObj
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-9-2 下午4:13:09
	 */
	public PaasTreeObj queryForPaasTreeObj(PaasTreeObj obj);

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
	 * @Title: queryEntityIdList
	 * @Description: 查询entity_id
	 * @param
	 * @return List<String>
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-6-18 下午2:54:32
	 */
	public List<String> queryEntityIdList(PaasTreeObj obj);

	/**
	 * 
	 * @Title: acquireListForType
	 * @Description: 通过类型递归查询
	 * @param
	 * @return List<PaasTreeObj>
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-6-18 下午3:22:09
	 */
	public List<PaasTreeObj> acquireListForType(String node_type,
			String server_type, PaasTreeObj obj, List<PaasTreeObj> resultList)
			throws Exception;
}
