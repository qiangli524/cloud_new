package com.sitech.basd.resource.dao.resourcehisrecord;

import java.util.List;
import java.util.Map;

import com.sitech.basd.resource.domain.resourcehisrecord.ResourceHisRecordObj;

/**
 * <p>
 * Title: ResourceHisRecordDao
 * </p>
 * <p>
 * Description: 资源历史记录持久层接口
 * </p>
 * <p>
 * Copyright: Copyright (c) 2013
 * </p>
 * <p>
 * Company: SI-TECH
 * </p>
 * 
 * @author lipengpeng
 * @version 1.0
 * @createtime 2013-10-20 上午11:44:28
 * 
 */
public interface ResourceHisRecordDao {

	/**
	 * @Title: queryForListByMap
	 * @Description: 查询记录
	 * @param
	 * @return List<ResourceHisRecordObj>
	 * @throws
	 * @author lipp
	 * @version 1.0
	 * @createtime 2013-11-29 下午3:42:54
	 */
	public List<ResourceHisRecordObj> queryForListByMap(
			Map<String, String> paramMap);

}
