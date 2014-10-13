package com.sitech.basd.sxcloud.rsmu.dao;

import org.springframework.orm.ibatis.SqlMapClientTemplate;

public class BaseSpringDao {
	private SqlMapClientTemplate sqlMap;

	public SqlMapClientTemplate getSqlMap() {
		return sqlMap;
	}

	public void setSqlMap(SqlMapClientTemplate sqlMap) {
		this.sqlMap = sqlMap;
	}
}
