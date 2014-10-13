package com.sitech.basd.sxcloud.cloud.service.appmessage;

import java.util.List;

import com.sitech.basd.sxcloud.cloud.domain.appmessage.TbCloudAppInfoObj;

public interface TbCloudAppInfoService {
	/**
	 * @Title:查询业务系统下的应用
	 * @Copyright: Copyright (c) 201112
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public List queryBizsysAppList(TbCloudAppInfoObj obj);

	/**
	 * @Title:插入应用信息
	 * @Copyright: Copyright (c) 201112
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public int insertByObj(TbCloudAppInfoObj obj);

	/**
	 * @Title:查询和应用关联的基准应用信息
	 * @Copyright: Copyright (c) 201112
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public List queryAppListBase(TbCloudAppInfoObj obj);

	/**
	 * 
	 * @Title: getHostByBusi
	 * @Description: 根据业务系统获取对应应用
	 * @param
	 * @return TbCloudAppInfoObj
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Feb 26, 2013 3:28:15 PM
	 */
	public List getAppByBusi(TbCloudAppInfoObj obj);
}
