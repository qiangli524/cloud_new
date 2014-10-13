package com.sitech.ssd.ah.paas.service.tab;

import java.util.List;

import com.sitech.ssd.ah.paas.domain.host.GreenPlumHostInfoObj;

/**
 * 
 * <p>
 * Title: GreenPlumTabService
 * </p>
 * <p>
 * Description: GP数据库相关操作
 * </p>
 * <p>
 * Copyright: Copyright (c) 2014
 * </p>
 * <p>
 * Company: SI-TECH
 * </p>
 * 
 * @author yanggl
 * @version 1.0
 * @createtime 2014-7-29 下午4:06:20
 * 
 */
public interface GreenPlumTabService {

	/**
	 * 
	 * @Title: queryGreenPlumHostList
	 * @Description: 查询gp主机列表
	 * @param
	 * @return List<GreenPlumHostInfoObj>
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-7-29 下午4:08:59
	 */
	public List<GreenPlumHostInfoObj> queryGreenPlumHostList(GreenPlumHostInfoObj obj);
}
