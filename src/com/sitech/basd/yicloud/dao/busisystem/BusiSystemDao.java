package com.sitech.basd.yicloud.dao.busisystem;

import java.util.List;

import com.sitech.basd.yicloud.domain.busisystem.BusiSystemObj;

/**
 * <p>Title: BusiSystemDao</p>
 * <p>Description: 业务系统持久层接口</p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: SI-TECH </p>
 * @author lipengpeng
 * @version 1.0
 * @createtime 2013-9-24 下午5:17:59
 *
 */
public interface BusiSystemDao {

	/**
	 * @Title: queryForListByObj
	 * @Description: 根据传入的对象要求查询符合条件的记录集合
	 * @param
	 * @return List<BusiSystemObj>
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-9-24 下午5:19:14
	 */
	public List<BusiSystemObj> queryForListByObj(BusiSystemObj busiSystemObj);
}
