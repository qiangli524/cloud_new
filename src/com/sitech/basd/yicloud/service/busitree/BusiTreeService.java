package com.sitech.basd.yicloud.service.busitree;

import java.util.List;

import com.sitech.basd.yicloud.domain.busitree.BusiTreeObj;

/**
 * 
 * <p>
 * Title: EntityTreeService
 * </p>
 * <p>
 * Description: ztree生成虚拟机，主机等生成树管理Service
 * </p>
 * <p>
 * Copyright: Copyright (c) 2012
 * </p>
 * <p>
 * Company: SI-TECH
 * </p>
 * 
 * @author huojla
 * @version 1.0
 * @createtime Apr 18, 2012 11:08:32 AM
 * 
 */
public interface BusiTreeService {
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
	 * 通过应用的bustid直接找到属于这个基准应用的所有实例
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
