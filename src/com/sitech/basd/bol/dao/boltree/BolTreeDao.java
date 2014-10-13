package com.sitech.basd.bol.dao.boltree;

import java.util.List;

import com.sitech.basd.bol.domain.boltree.BolTreeObj;

/**
 * <p>Title: RolTreeDao</p>
 * <p>Description: 资源视图树持久层接口</p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: SI-TECH </p>
 * @author lipengpeng
 * @version 1.0
 * @createtime 2013-10-31 上午11:33:12
 *
 */
public interface BolTreeDao {

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
	List<BolTreeObj> queryForBolTreeList(BolTreeObj bolTreeObj);

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
	List<BolTreeObj> queryForAuthTree(BolTreeObj bolTreeObj);

}
