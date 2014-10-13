package com.sitech.basd.yicloud.service.busitree;

import java.util.List;

import com.sitech.basd.yicloud.dao.busitree.BusiTreeDao;
import com.sitech.basd.yicloud.domain.busitree.BusiTreeObj;

/**
 * 
 * <p>
 * Title: EntityTreeServiceImpl
 * </p>
 * <p>
 * Description: ztree树管理实现类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2012
 * </p>
 * <p>
 * Company: SI-TECH
 * </p>
 * 
 * @author taoxue
 * @version 1.0
 * @createtime Apr 18, 2012 11:08:57 AM
 * 
 */
public class BusiTreeServiceImpl implements BusiTreeService {
	private BusiTreeDao busiTreeDao;

	public void setBusiTreeDao(BusiTreeDao busiTreeDao) {
		this.busiTreeDao = busiTreeDao;
	}

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
		return busiTreeDao.queryForTree(obj);
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
		return busiTreeDao.insertByObj(obj);
	}

	@Override
	public List<BusiTreeObj> queryExampleListByAppBustID(BusiTreeObj obj) {
		return busiTreeDao.queryExampleListByAppBustID(obj);
	}
}
