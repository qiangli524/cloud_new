package com.sitech.basd.yicloud.service.mytstat;

import com.sitech.basd.yicloud.domain.mytstat.MysqlTableStatusObj;

/**
 * 
 * <p>
 * Title: MysqlTableStatusService
 * </p>
 * <p>
 * Description: Mysql数据库表状态信息接口
 * </p>
 * <p>
 * Copyright: Copyright (c) 2012
 * </p>
 * <p>
 * Company: SI-TECH
 * </p>
 * 
 * @author huojla
 * @version 1.0
 * @createtime Jun 15, 2012 2:49:47 PM
 * 
 */
public interface MysqlTableStatusService {
	/**
	 * 
	 * @Title: getMysqlTableStatus
	 * @Description: Mysql数据库表状态信息接口
	 * @param
	 * @return MysqlTableStatusObj
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime Jun 15, 2012 2:50:21 PM
	 */
	public MysqlTableStatusObj getMysqlTableStatus(String tableName);

	public int getMysqlTableStatusString(String tableName);
}
