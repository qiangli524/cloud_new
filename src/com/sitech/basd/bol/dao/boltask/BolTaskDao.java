package com.sitech.basd.bol.dao.boltask;

import com.sitech.basd.bol.domain.boltask.BolTaskObj;

/**
 * <p>Title: BolTaskDao</p>
 * <p>Description: 资源任务持久层接口</p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: SI-TECH </p>
 * @author lipengpeng
 * @version 1.0
 * @createtime 2013-11-7 下午5:22:03
 *
 */
public interface BolTaskDao {

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
	public int insertByObj(BolTaskObj bolTaskObj);

}
