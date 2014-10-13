package com.sitech.basd.yicloud.dao.busitree;

import java.util.List;

import com.sitech.basd.yicloud.domain.busitree.BusiTreeObj;

/**
 * 
 * <p>
 * Title: BusiTreeDao
 * </p>
 * <p>
 * Description: 业务中心ztree管理接口
 * </p>
 * <p>
 * Copyright: Copyright (c) 2012
 * </p>
 * <p>
 * Company: SI-TECH
 * </p>
 * 
 * @author duangh
 * @version 1.0
 * @createtime Apr 18, 2012 11:14:17 AM
 * 
 */
public interface BusiTreeDao {

	/**
	 * 
	 * @Title: queryForTree
	 * @Description: 查询业务中心，业务系统，应用等生成树
	 * @param
	 * @return BusiTreeObj
	 * @throws
	 * @author duangh
	 * @version 1.0
	 */
	public List<BusiTreeObj> queryForTree(BusiTreeObj obj);

	/**
	 * 通过基准应用的实例id直接找到孩子节点的内容
	 * @param obj
	 * @return
	 */
	public List<BusiTreeObj> queryExampleListByAppBustID(BusiTreeObj obj);
	
	/**
	 * 
	 * @Title: insertByObj
	 * @Description: 向业务中心的树中插入数据
	 * @param
	 * @return BusiTreeObj
	 * @throws
	 * @author duangh
	 * @version 1.0
	 */
	public int insertByObj(BusiTreeObj obj);
}
