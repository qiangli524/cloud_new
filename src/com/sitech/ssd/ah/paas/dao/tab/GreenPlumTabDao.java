package com.sitech.ssd.ah.paas.dao.tab;

import java.util.List;

import com.sitech.ssd.ah.paas.domain.host.GreenPlumHostInfoObj;

/**
 * 
 * <p>
 * Title: GreenPlumTabDao
 * </p>
 * <p>
 * Description: gp数据库相关操作
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
 * @createtime 2014-7-29 下午4:10:00
 * 
 */
public interface GreenPlumTabDao {
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
