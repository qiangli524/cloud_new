package com.sitech.basd.bol.dao.boltask;

import org.springframework.stereotype.Repository;

import com.sitech.basd.bol.domain.boltask.BolTaskObj;
import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;

/**
 * <p>Title: BolTaskDao</p>
 * <p>Description: 资源任务持久层接口实现类</p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: SI-TECH </p>
 * @author lipengpeng
 * @version 1.0
 * @createtime 2013-11-7 下午5:22:03
 *
 */
@Repository("bolTaskDao")
public class BolTaskDaoImpl extends BaseDao implements BolTaskDao{

	/**
	 * @Title: insertByObj
	 * @Description: 插入一条记录
	 * @param
	 * @return int
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-11-7 下午5:22:55
	 */
	@Override
	public int insertByObj(BolTaskObj bolTaskObj) {
		int ret = -1;
		try {
			getSqlMap().insert("BolTask.insertByObj", bolTaskObj);
			ret = 1;
		} catch (Exception e) {
			LogHelper.error("BolTask.insertByObj: " + e.getMessage() + e.getClass().getName());
		}
		return ret;
	}

}
