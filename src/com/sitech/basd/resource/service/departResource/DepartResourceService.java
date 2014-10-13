package com.sitech.basd.resource.service.departResource;

import java.util.List;

import com.sitech.basd.cloud3.domain.departproject.DepartInfoObj;

public interface DepartResourceService {

	/**
	 * @Title: buildXmlData
	 * @Description: 组装报表数据
	 * @param
	 * @return String
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-11-17 下午9:00:23
	 */
	public String buildXmlData(String resourceType, List<DepartInfoObj> departList, String startTime, String endTime);

	/**
	 * @Title: queryForDepartList
	 * @Description: 加载所有部门信息
	 * @param
	 * @return List<DepartInfoObj>
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @param departInfoObj 
	 * @createtime 2013-11-18 上午9:59:11
	 */
	public List<DepartInfoObj> queryForDepartList(DepartInfoObj departInfoObj);

}
