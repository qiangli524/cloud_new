/**
 * SyncData.java
 * com.sitech.ssd.gx.sync
 *
 * Function： TODO 
 *
 *   ver     date      		author
 * ──────────────────────────────────
 *   		 2013 十一月 21 		duangh
 *
 * Copyright (c) 2013, TNT All Rights Reserved.
 */

package com.sitech.ssd.gx.service.sync;

import java.util.List;

import com.sitech.utils.exception.HttpClientException;
import com.sitech.vo.huawei.ClusterVO;
import com.sitech.vo.huawei.HostVO;
import com.sitech.vo.huawei.SiteVO;
import com.sitech.vo.huawei.VMDetailVO;

/**
 * ClassName:SyncData Function:调用云管理平台接口同步查询华为数据接口
 * 
 * @author duangh
 * @version
 * @since Ver 1.0
 * @Date 2013 十一月 21 16:10:57
 * 
 * @see
 */
public interface SyncHuaweiData {
	
	/**
	 * 
	 * @Title: initRestEntities
	 * @Description: 实例化华为实体
	 * @param
	 * @return void
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime 2013-12-3 下午10:39:40
	 */
	public void initRestEntities() throws HttpClientException;
}
