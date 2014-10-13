package com.sitech.basd.resource.service.resourcehisrecord;

/**
 * <p>
 * Title: ResourceHisRecordService
 * </p>
 * <p>
 * Description: 资源历史记录业务层接口
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
 * @createtime 2013-10-20 上午11:48:30
 * 
 */
public interface ResourceHisRecordService {

	/**
	 * @Title: buildDataXml
	 * @Description: 生成报表数据
	 * @param
	 * @return String
	 * @throws
	 * @author lipp
	 * @version 1.0
	 * @createtime 2013-11-29 下午2:21:32
	 */
	String buildDataXml(String resourceType, String startTime, String endTime);

}
