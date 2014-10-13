package com.sitech.basd.yicloud.dao.xenconn;

import java.util.ArrayList;
import java.util.List;

import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.yicloud.domain.xenconn.ConnectionInfo;

public class ConnInfoDaoImpl extends BaseDao implements ConnInfoDao {
	/**
	 * 插入一条信息
	 */
	public int insertConnInfoByObj(ConnectionInfo obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().insert("ConnInfo.insertConnInfoByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			sqlexception.printStackTrace();
		}
		return ret;
	}

	/**
	 * 查询一条信息
	 */
	public ConnectionInfo queryConnInfoByObj(ConnectionInfo obj) {
		Object o = null;
		try {
			o = getSqlMap().queryForObject("ConnInfo.queryConnInfoByPoolUuid",
					obj);
		} catch (Exception sqlexception) {
			sqlexception.printStackTrace();
		}
		return (ConnectionInfo) o;
	}
	
	/**
	 * 
	 * @Title: queryAllConn
	 * @Description: 查询所有链接信息
	 * @param
	 * @return List
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime 2013-1-22 下午02:02:25
	 */
	public List queryAllConn(ConnectionInfo obj){
		List lst = new ArrayList();
		try {
			lst = getSqlMap().queryForList("ConnInfo.queryConnInfoByPoolUuid",
					obj);
		} catch (Exception sqlexception) {
			sqlexception.printStackTrace();
		}
		return lst;
		
	}

	/**
	 * 更新一条信息
	 */
	public int updateConnInfoByObj(ConnectionInfo obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().update("ConnInfo.updateConnInfoByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			sqlexception.printStackTrace();
		}
		return ret;
	}
}
