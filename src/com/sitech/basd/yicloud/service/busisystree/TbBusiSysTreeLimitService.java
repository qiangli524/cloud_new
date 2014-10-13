package com.sitech.basd.yicloud.service.busisystree;

import java.util.List;

import com.sitech.basd.yicloud.domain.busisystree.TbBusiSysTree;
import com.sitech.basd.yicloud.domain.busisystree.TbBusiSysTreeLimit;
import com.sitech.basd.yicloud.web.busisystree.form.BusiSysTree;

/**
 * 
 * <p>
 * Title: TbBusiSysTreeLimitService
 * </p>
 * <p>
 * Description:TB_BUSI_SYS_TREE-业务系统树-用户权限关联表-Service实现类
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
 * @createtime 2013-5-21 下午2:37:29
 * 
 */
public interface TbBusiSysTreeLimitService {
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
	 * @Title: ifTreeNodeHasLimitByUser
	 * @Description: 树节点是否存在用户权限
	 * @param
	 * @return boolean
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-5-21 下午2:43:33
	 */
	public boolean ifTreeNodeHasLimitByUser(String treeNodeId, String username);

	/**
	 * 
	 * @Title: initBusiSysTreelist
	 * @Description: 实例树业务系统树数据List
	 * @param
	 * @return List<BusiSysTree>
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-5-21 上午11:24:41
	 */
	public List<BusiSysTree> initBusiSysTreeLimitlist(String username, List<TbBusiSysTree> resultList);

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
