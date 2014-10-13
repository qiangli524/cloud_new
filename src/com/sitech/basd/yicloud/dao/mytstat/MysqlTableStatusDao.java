package com.sitech.basd.yicloud.dao.mytstat;

import com.sitech.basd.yicloud.domain.mytstat.MysqlTableStatusObj;

/**
 * 
 * <p>
 * Title: MysqlTableStatusDao
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
 * @createtime Jun 15, 2012 2:44:03 PM
 * 
 */
public interface MysqlTableStatusDao {
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
	public int getMysqlTableStatusString(String tableName);
}
