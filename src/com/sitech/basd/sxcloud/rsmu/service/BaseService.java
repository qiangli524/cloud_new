package com.sitech.basd.sxcloud.rsmu.service;

import java.sql.Connection;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;

@Service("baseService")
public class BaseService {
	@Resource
	private BaseDao baseDao;

	public SqlMapClient getSqlMap() {
		return baseDao.getSqlMap();
	}

	public Connection getJDBCInstance() {
		return baseDao.getJDBCInstance();
	}
}
