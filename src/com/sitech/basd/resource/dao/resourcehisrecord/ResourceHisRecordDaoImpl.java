package com.sitech.basd.resource.dao.resourcehisrecord;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.sitech.basd.resource.domain.resourcehisrecord.ResourceHisRecordObj;
import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;

/**
 * <p>
 * Title: ResourceHisRecordDaoImpl
 * </p>
 * <p>
 * Description: 资源历史记录持久层接口实现类
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
 * @createtime 2013-10-20 上午11:47:04
 * 
 */
@Repository("resourceHisRecordDao")
public class ResourceHisRecordDaoImpl extends BaseDao
		implements
			ResourceHisRecordDao {

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
	@SuppressWarnings("unchecked")
	@Override
	public List<ResourceHisRecordObj> queryForListByMap(
			Map<String, String> paramMap) {
		List<ResourceHisRecordObj> list = new ArrayList<ResourceHisRecordObj>();
		try {
			list = getSqlMap().queryForList(
					"ResourceHisRecord.queryForListByMap", paramMap);
		} catch (Exception e) {
			LogHelper.error("ResourceHisRecord.queryForListByMap: "
					+ e.getMessage() + e.getClass().getName());
		}
		return list;
	}

}
