package com.sitech.basd.bol.dao.boltree;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.sitech.basd.bol.domain.boltree.BolTreeObj;
import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;

/**
 * <p>Title: RolTreeDaoImpl</p>
 * <p>Description: 资源视图持久层接口实现类</p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: SI-TECH </p>
 * @author lipengpeng
 * @version 1.0
 * @createtime 2013-10-31 上午11:33:39
 *
 */
@Repository("bolTreeDao")
public class BolTreeDaoImpl extends BaseDao implements BolTreeDao{

	/**
	 * @Title: queryForUnitedTreeList
	 * @Description: 查询树对象集合
	 * @param
	 * @return List<UnitedTreeObj>
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-11-2 上午11:44:18
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<BolTreeObj> queryForBolTreeList(BolTreeObj bolTreeObj) {
		List<BolTreeObj> list = new ArrayList<BolTreeObj>();
		try {
			list = getSqlMap().queryForList("BolTree.queryForBolTreeList", bolTreeObj);
		} catch (Exception e) {
			LogHelper.error("BolTree.queryForBolTreeList: " + e.getMessage() + e.getClass().getName());
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * @Title: queryForAuthTree
	 * @Description: 根据权限查询树节点集合
	 * @param
	 * @return List<BolTreeObj>
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-11-2 下午1:53:47
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<BolTreeObj> queryForAuthTree(BolTreeObj bolTreeObj) {
		List<BolTreeObj> list = new ArrayList<BolTreeObj>();
		try {
			list = getSqlMap().queryForList("BolTree.queryForAuthTree", bolTreeObj);
		} catch (Exception e) {
			LogHelper.error("BolTree.queryForAuthTree: " + e.getMessage() + e.getClass().getName());
			e.printStackTrace();
		}
		return list;
	}
}
