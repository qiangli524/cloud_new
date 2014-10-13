package com.sitech.basd.workflow.dao;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.sitech.basd.util.ReflectionUtils;

/**
 * 
 * <p>
 * Title: CloudIbatisBaseDao
 * </p>
 * <p>
 * Description: 云平台IbatisDao父类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2013
 * </p>
 * <p>
 * Company: SI-TECH
 * </p>
 * 
 * @author huojla
 * @version 1.0
 * @createtime 2013-6-20 下午10:32:53
 * 
 */
public class CloudIbatisBaseDaoImpl<T, PK extends Serializable> implements
		CloudIbatisBaseDao<T, PK> {
	protected Logger logger = LoggerFactory.getLogger(getClass());

	protected SqlMapClient sqlMapClient;

	protected Class<T> entityClass;

	/**
	 * 
	 * <p>
	 * Title:
	 * </p>
	 * <p>
	 * Description: 用于Dao层子类使用的构造函数. 通过子类的泛型定义取得对象类型Class. eg. public class
	 * UserDao extends CloudIbatisBaseDaoImpl<User, Long>
	 * </p>
	 */
	public CloudIbatisBaseDaoImpl() {
		this.entityClass = ReflectionUtils.getSuperClassGenricType(getClass());
	}

	/**
	 * 用于用于省略Dao层, 在Service层直接使用通用CloudIbatisBaseDaoImpl的构造函数.
	 * 在构造函数中定义对象类型Class. eg. CloudIbatisBaseDaoImpl<User, Long> userDao = new
	 * CloudIbatisBaseDaoImpl<User, Long>(sqlMapClient, User.class);
	 */
	public CloudIbatisBaseDaoImpl(final SqlMapClient sqlMapClient, final Class<T> entityClass) {
		this.sqlMapClient = sqlMapClient;
		this.entityClass = entityClass;
	}

	/*
	 * <p>Title: getSqlMapClient</p> <p>Description: </p>
	 * 
	 * @return
	 * 
	 * @see com.sitech.bkg.base.dao.CloudIbatisBaseDao#getSqlMapClient()
	 */
	@Override
	public SqlMapClient getSqlMapClient() {
		return sqlMapClient;
	}

	/*
	 * <p>Title: setSqlMapClient</p> <p>Description: </p>
	 * 
	 * @param sqlMapClient
	 * 
	 * @see
	 * com.sitech.bkg.base.dao.CloudIbatisBaseDao#setSqlMapClient(com.ibatis
	 * .sqlmap.client.SqlMapClient)
	 */
	@Override
	@Autowired
	public void setSqlMapClient(final SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}

	/*
	 * <p>Title: save</p> <p>Description: </p>
	 * 
	 * @param mapKey
	 * 
	 * @param entity
	 * 
	 * @return
	 * 
	 * @see com.sitech.bkg.base.dao.CloudIbatisBaseDao#save(java.lang.String, T)
	 */
	@Override
	public Serializable save(final String mapKey, final T entity) {
		Assert.notNull(mapKey, "Save mapKey不能为空!");
		Assert.notNull(entity, "entity不能为空!");
		Serializable id = -1;
		try {
			id = (Serializable) getSqlMapClient().insert(mapKey, entity);
		} catch (SQLException e) {
			logger.error("Save Class " + entity.getClass().getName() + "Error By Mapkey : "
					+ mapKey + "!", e);
//			throw new IbatisDaoException("Save Class " + entity.getClass().getName()
//					+ "Error By Mapkey : " + mapKey + "!", e);
		}
		logger.info("save entity: {}", mapKey + " Save :" + entity);
		return id;
	}

	/*
	 * <p>Title: updateOne</p> <p>Description: </p>
	 * 
	 * @param mapKey
	 * 
	 * @param entity
	 * 
	 * @return
	 * 
	 * @see
	 * com.sitech.bkg.base.dao.CloudIbatisBaseDao#updateOne(java.lang.String, T)
	 */
	@Override
	public Integer updateOne(final String mapKey, final T entity) {
		Assert.notNull(mapKey, "Update mapKey不能为空!");
		Assert.notNull(entity, "entity不能为空!");
		Integer result = -1;
		try {
			result = getSqlMapClient().update(mapKey, entity);
		} catch (SQLException e) {
			logger.error("Update Class " + entity.getClass().getName() + "Error By Mapkey : "
					+ mapKey + "!", e);
//			throw new IbatisDaoException("Update Class " + entity.getClass().getName()
//					+ "Error By Mapkey : " + mapKey + "!", e);
		}
		logger.info("Update entity: {}", mapKey + " Update :" + entity);
		return result;
	}

	/*
	 * <p>Title: delete</p> <p>Description: </p>
	 * 
	 * @param mapKey
	 * 
	 * @param entity
	 * 
	 * @return
	 * 
	 * @see com.sitech.bkg.base.dao.CloudIbatisBaseDao#delete(java.lang.String,
	 * T)
	 */
	@Override
	public Integer delete(final String mapKey, final T entity) {
		Assert.notNull(mapKey, "Delete mapKey不能为空!");
		Assert.notNull(entity, "entity不能为空");
		Integer result = -1;
		try {
			result = getSqlMapClient().delete(mapKey, entity);
		} catch (SQLException e) {
			logger.error("Delete Class " + entity.getClass().getName() + "Error By Mapkey : "
					+ mapKey + "!", e);
//			throw new IbatisDaoException("Delete Class " + entity.getClass().getName()
//					+ "Error By Mapkey : " + mapKey + "!", e);
		}
		logger.info("Delete entity: {}", mapKey + " Delete :" + entity);
		return result;
	}

	/*
	 * <p>Title: get</p> <p>Description: </p>
	 * 
	 * @param mapKey
	 * 
	 * @param id
	 * 
	 * @return
	 * 
	 * @see com.sitech.bkg.base.dao.CloudIbatisBaseDao#get(java.lang.String, PK)
	 */
	@Override
	public T get(final String mapKey, final PK id) {
		Assert.notNull(mapKey, "Get mapKey不能为空!");
		Assert.notNull(id, "id不能为空");
		T t = null;
		try {
			t = (T) getSqlMapClient().queryForObject(mapKey, id);
		} catch (SQLException e) {
			logger.error("Select Class By Id " + id + "Error !", e);
//			throw new IbatisDaoException("Select Class By Id " + id + "Error !", e);
		}
		return t;
	}

	/*
	 * <p>Title: getAll</p> <p>Description: </p>
	 * 
	 * @param mapKey
	 * 
	 * @param entity
	 * 
	 * @return
	 * 
	 * @see com.sitech.bkg.base.dao.CloudIbatisBaseDao#getAll(java.lang.String,
	 * T)
	 */
	@Override
	public List<T> getAll(final String mapKey, final T entity) {
		Assert.notNull(mapKey, "Get mapKey不能为空!");
		Assert.notNull(entity, "entity不能为空");
		List<T> list = new ArrayList<T>();
		try {
			list = (List<T>) getSqlMapClient().queryForList(mapKey, entity);
		} catch (SQLException e) {
			logger.error("Select List Class By entity " + entity.getClass().getName() + "Error !",
					e);
//			throw new IbatisDaoException("Select List Class By entity "
//					+ entity.getClass().getName() + "Error !", e);
		}
		return list;
	}

	@Override
	public int getRecordCount(final String mapKey, final T entity) {
		int t = -1;
		try {
			Object o = getSqlMapClient().queryForObject(mapKey, entity);
			if (o != null) {
				t = Integer.parseInt(o.toString());
			}
		} catch (SQLException e) {
			t = -1;
			logger.error("Select Record Count Error !" + mapKey, e);
//			throw new IbatisDaoException("Select Record Count Error !" + mapKey, e);
		}
		return t;
	}

}
