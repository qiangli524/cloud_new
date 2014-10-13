package com.sitech.basd.yicloud.dao.busitree;

import java.util.List;

import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;
import com.sitech.basd.yicloud.domain.busitree.BusiTreeObj;

/**
 * 
 * <p>
 * Title: BusiTreeDaoImpl
 * </p>
 * <p>
 * Description: 业务中心ztree管理接口实现类
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
 * @createtime Apr 18, 2012 11:14:01 AM
 * 
 */
public class BusiTreeDaoImpl extends BaseDao implements BusiTreeDao {

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
	public List<BusiTreeObj> queryForTree(BusiTreeObj obj) {
		List lst = null;
		try {
			lst = getSqlMap().queryForList("BusiTree.queryForTree", obj);
		} catch (Exception e) {
			LogHelper.error("BusiTree.queryForTree:" + e.getMessage()
					+ getClass().getName());
		}
		return lst;
	}

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
	public int insertByObj(BusiTreeObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().insert("BusiTree.insertByobj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlException) {
			ret = -1;
			LogHelper.error("BusiTree.insertByobj:" + sqlException.getMessage()
					+ getClass().getName());
		}
		return ret;
	}

	@Override
	public List<BusiTreeObj> queryExampleListByAppBustID(BusiTreeObj obj) {
		List lst = null;
		try {
			lst = getSqlMap().queryForList("BusiTree.queryExampleListByAppBustID", obj);
		} catch (Exception e) {
			e.printStackTrace();
			LogHelper.error("BusiTree.queryExampleListByAppBustID:" + e.getMessage()
					+ getClass().getName());
		}
		return lst;
	}
}
