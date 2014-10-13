package com.sitech.basd.yicloud.dao.busisystree;

import java.util.List;

import com.sitech.basd.yicloud.domain.busisystree.TbBusiSysTreeLimit;

/**
 * 
 * <p>
 * Title: TbBusiSysTreeLimitDao
 * </p>
 * <p>
 * Description: B_BUSI_SYS_TREE-业务系统树-用户权限关联表-Dao
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
 * @createtime 2013-5-20 下午3:16:19
 * 
 */
public interface TbBusiSysTreeLimitDao {
	/**
	 * 
	 * @Title: reInitSysTreeLimitByUser
	 * @Description: 重新实例用户权限
	 * @param
	 * @return int
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-5-20 下午3:32:34
	 */
	public int reInitSysTreeLimitByUser(List<TbBusiSysTreeLimit> list, String username);

	/**
	 * 
	 * @Title: queryOneTbBusiSysTreeLimit
	 * @Description: 查询一条记录
	 * @param
	 * @return List<TbBusiSysTree>
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-5-20 下午1:48:23
	 */
	public TbBusiSysTreeLimit queryOneTbBusiSysTreeLimit(TbBusiSysTreeLimit obj);

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
	public List<TbBusiSysTreeLimit> queryTbBusiSysTreeLimit(TbBusiSysTreeLimit obj);

	/**
	 * 
	 * insertTbBusiSysTreeLimit:权限表中插入一条记录
	 * 
	 * @param TbBusiSysTreeLimit
	 * @since duangh Ver 1.0
	 */
	public void insertTbBusiSysTreeLimit(TbBusiSysTreeLimit obj);

	/**
	 * 
	 * deleteOneTbBusiSysTreeLimit: 删除一个节点的权限信息
	 * 
	 * @param TbBusiSysTreeLimit
	 * @since duangh Ver 1.0
	 */
	public void deleteOneTbBusiSysTreeLimit(TbBusiSysTreeLimit obj);
}
