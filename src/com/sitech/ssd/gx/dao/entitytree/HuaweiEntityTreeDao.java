package com.sitech.ssd.gx.dao.entitytree;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;
import com.sitech.basd.yicloud.domain.entitytree.EntityConObj;
import com.sitech.basd.yicloud.domain.entitytree.EntityTreeObj;
import com.sitech.basd.yicloud.domain.entitytree.SolutionObj;
import com.sitech.ssd.gx.domain.huaweientitytree.HuaweiEntityTreeObj;

public interface HuaweiEntityTreeDao {

	/**
	 * 
	 * @Title: queryForTree
	 * @Description: 查询数据中心，集群，主机，虚拟机等生成树
	 * @param
	 * @return List<HuaweiEntityTreeObj>
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime 2013-12-3 下午8:35:35
	 */
	public List<HuaweiEntityTreeObj> queryForTree(HuaweiEntityTreeObj obj);

	/**
	 * 
	 * @Title: insertTreeNode
	 * @Description: 向树中插入节点
	 * @param
	 * @return int
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime 2013-12-3 下午8:48:28
	 */
	public int insertTreeNode(HuaweiEntityTreeObj obj);

	/**
	 * 
	 * @Title: queryTreeNode
	 * @Description: 查询树节点的信息
	 * @param
	 * @return HuaweiEntityTreeObj
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime 2013-12-3 下午8:48:16
	 */
	public HuaweiEntityTreeObj queryTreeNode(HuaweiEntityTreeObj obj);
	
	/**
	 * 
	 * @Title: deleteAllData
	 * @Description:删除库中原有数据
	 * @param
	 * @return String
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime Otc 27, 2012 11:34:58 AM
	 */
	public int deleteAllData();
}
