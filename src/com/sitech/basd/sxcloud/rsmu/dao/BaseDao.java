package com.sitech.basd.sxcloud.rsmu.dao;

import java.sql.Connection;

import javax.annotation.Resource;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Service;

import com.ibatis.sqlmap.client.SqlMapClient;

/**
 * 
 * <p>
 * Title: BaseDao
 * </p>
 * <p>
 * Description: 采用SqlMap自身管理数据库连接
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
 * @createtime Oct 14, 2012 9:56:21 AM
 * 
 */
@Service("baseDao")
public class BaseDao extends SqlMapClientDaoSupport {
	public static SqlMapClient sqlMapClient;

	public SqlMapClient getSqlMap() {
		return getSqlMapClientTemplate().getSqlMapClient();
	}

	public  SqlMapClient getStaticSqlMap() {
		return sqlMapClient;
	}

	@Resource
	public void setSqlMapClientBase(SqlMapClient cloudIbatisSqlMapClient) {
		sqlMapClient = cloudIbatisSqlMapClient;
		super.setSqlMapClient(cloudIbatisSqlMapClient);
	}

	public static Connection getJDBCInstance() {
		try {
			return sqlMapClient.getDataSource().getConnection();
		} catch (Exception exception) {
			exception.printStackTrace();
			throw new RuntimeException("Error initializing SqlMap! cause:" + exception.getMessage());
		}
	}

	// public static SqlMapClient getSqlMap() {
	// return sqlMapClient;
	// }
	//
	// @Autowired
	// public void setSqlMapClient(final SqlMapClient cloudIbatisSqlMapClient) {
	// this.sqlMapClient = cloudIbatisSqlMapClient;
	// }

	// static {
	// try {
	// ResourceBundle dbConfig = ResourceBundle
	// .getBundle("com/sitech/basd/config/sqlmap/DBConfig");
	// String driverClassName = dbConfig.getString("jdbc.driverClassName");
	// String url = dbConfig.getString("jdbc.url");
	// String username = dbConfig.getString("jdbc.username");
	// String password = dbConfig.getString("jdbc.password");
	// password = EncryptUtil.decode(password);
	// java.util.Properties properties = new java.util.Properties();
	// properties.put("jdbc.driverClassName", driverClassName);
	// properties.put("jdbc.url", url);
	// properties.put("jdbc.username", username);
	// properties.put("jdbc.password", password);
	// String s = "com/sitech/basd/config/sqlmap/sqlmap-mysql-config.xml";
	// // String s = "com/sitech/basd/config/sqlmap/sqlmap-config.xml";
	// Reader reader;
	// try {
	// Resources.setCharset(Charset.forName("utf-8"));
	// reader = Resources.getResourceAsReader(s);
	// sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader, properties);
	// } catch (IOException e) {
	// LogHelper.error("加载sqlmap-config.xml异常");
	// }
	// } catch (Exception exception) {
	// LogHelper.error("连接数据库异常：" + exception.getMessage());
	// exception.printStackTrace();
	// }
	// }
}
