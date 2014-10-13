package com.sitech.ssd.gx.dao.entitytree;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;
import com.sitech.basd.yicloud.domain.entitytree.EntityConObj;
import com.sitech.basd.yicloud.domain.entitytree.EntityTreeObj;
import com.sitech.basd.yicloud.domain.entitytree.SolutionObj;
import com.sitech.ssd.gx.domain.huaweientitytree.HuaweiEntityTreeObj;

public class HuaweiEntityTreeDaoImpl extends BaseDao implements HuaweiEntityTreeDao {

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
	public List<HuaweiEntityTreeObj> queryForTree(HuaweiEntityTreeObj obj) {
		List lst = null;
		try {
			lst = getSqlMap().queryForList("huaweiEntityTree.queryForTree", obj);
		} catch (Exception e) {
			LogHelper.error("huaweiEntityTree.queryForTree:" + e.getMessage()
					+ getClass().getName());
		}
		return lst;
	}

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
	public int insertTreeNode(HuaweiEntityTreeObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().insert("huaweiEntityTree.insertTreeNode", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("huaweiEntityTree.insertTreeNode:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return ret;
	}

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
	public HuaweiEntityTreeObj queryTreeNode(HuaweiEntityTreeObj obj) {
		try {
			obj = (HuaweiEntityTreeObj) getSqlMap().queryForObject(
					"huaweiEntityTree.queryTreeNode", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("huaweiEntityTree.queryTreeNode:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return obj;
	}
	
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
	public int deleteAllData() {
		int ret = 0;
		try {
			getSqlMap().startTransaction();
			getSqlMap().delete("huaweiEntityTree.deleteHuaweiEntityTree");
			getSqlMap().delete("huaweiEntityTree.deleteHuaweihostInfo");
			getSqlMap().delete("huaweiEntityTree.deleteHuaweiVMInfo");
			ret = 1;
			getSqlMap().commitTransaction();
		} catch (Exception sqlException) {
			ret = -1;
		} finally {
			try {
				getSqlMap().endTransaction();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return ret;
	}

}
