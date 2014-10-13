package com.sitech.basd.yicloud.service.mytstat;

import com.sitech.basd.yicloud.dao.mytstat.MysqlTableStatusDao;
import com.sitech.basd.yicloud.domain.mytstat.MysqlTableStatusObj;

public class MysqlTableStatusServiceImpl implements MysqlTableStatusService {
	private MysqlTableStatusDao mysqlTableStatusDao;

	public void setMysqlTableStatusDao(MysqlTableStatusDao mysqlTableStatusDao) {
		this.mysqlTableStatusDao = mysqlTableStatusDao;
	}

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
	@Override
	public MysqlTableStatusObj getMysqlTableStatus(String tableName) {
		return mysqlTableStatusDao.getMysqlTableStatus(tableName);
	}

	public int getMysqlTableStatusString(String tableName) {
		return mysqlTableStatusDao.getMysqlTableStatusString(tableName);
	}
}
