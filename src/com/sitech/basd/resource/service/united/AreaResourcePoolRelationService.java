package com.sitech.basd.resource.service.united;

public interface AreaResourcePoolRelationService {
	/**
	 * 
	 * @Title: getResourcePoolInfo
	 * @Description: 通过区域名称来查询其对应的资源池的信息
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime 2013-11-13 上午11:16:59
	 */
	public String getAreaId(String resourceCode,String pid);

	/**
	 * 
	 * @Title: getConnectCodeList
	 * @Description:通过地域ID查询其对应的vCenter标示
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime 2014-4-17 下午7:01:17
	 */
	public String getConnectCodeList(String areaId);
}
