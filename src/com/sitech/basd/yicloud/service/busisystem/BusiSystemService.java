package com.sitech.basd.yicloud.service.busisystem;

import java.util.List;

import com.sitech.basd.yicloud.domain.busisystem.BusiSystemObj;

/**
 * <p>Title: BusiSystemService</p>
 * <p>Description: 业务系统业务层接口</p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: SI-TECH </p>
 * @author lipengpeng
 * @version 1.0
 * @createtime 2013-9-24 下午7:30:22
 *
 */
public interface BusiSystemService {

	/**
	 * @Title: queryForListByObj
	 * @Description: 根据传进来的实体属性条件查询符合条件的记录集合
	 * @param
	 * @return List<BusiSystemObj>
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-9-24 下午7:29:40
	 */
	public List<BusiSystemObj> queryForListByObj(BusiSystemObj busiSystemObj);
}
