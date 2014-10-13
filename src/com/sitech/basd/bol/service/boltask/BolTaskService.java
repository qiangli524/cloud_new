package com.sitech.basd.bol.service.boltask;

import com.sitech.basd.bol.domain.boltask.BolTaskObj;

/**
 * <p>Title: BolTaskService</p>
 * <p>Description: 资源任务业务层接口</p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: SI-TECH </p>
 * @author lipengpeng
 * @version 1.0
 * @createtime 2013-11-7 下午5:31:40
 *
 */
public interface BolTaskService {

	/**
	 * @Title: insertByObj
	 * @Description: 插入一条记录
	 * @param
	 * @return int
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-11-7 下午5:32:04
	 */
	public int insertByObj(BolTaskObj bolTaskObj);
}
