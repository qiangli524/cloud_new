package com.sitech.taokeeper.dao.impl;

import static common.toolkit.java.constant.EmptyObjectConstant.EMPTY_STRING;
import static common.toolkit.java.constant.SymbolConstant.COMMA;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;
import com.sitech.basd.sxcloud.util.EncryptUtil;
import com.sitech.taokeeper.dao.ZooKeeperClusterDAO;
import com.sitech.taokeeper.model.ZooKeeperCluster;
import common.toolkit.java.exception.DaoException;
import common.toolkit.java.util.StringUtil;
import common.toolkit.java.util.collection.ArrayUtil;
import common.toolkit.java.util.collection.CollectionUtil;

/**
 * Description: Access DB for zookeeper cluster
 * 
 * @author yinshi.nc
 * @Date 2011-10-28
 */
@Repository("zooKeeperClusterDAO")
public class ZooKeeperClusterDAOImpl extends BaseDao implements ZooKeeperClusterDAO {

	@Override
	public ZooKeeperCluster getZooKeeperClusterByCulsterId(int clusterId) throws DaoException {
		ZooKeeperCluster zookeeperCluster = null;
		try {

			ZooKeeperCluster obj = new ZooKeeperCluster();
			obj.setClusterId(clusterId);
			zookeeperCluster = (ZooKeeperCluster) getSqlMapClient().queryForObject(
					"ZookeeperMonitorForCluster.queryZooKeeperClusterObjList", obj);
			if (null == zookeeperCluster)
				throw new DaoException("没有返回结果");
			List<String> serverList = null;
			if (!StringUtil.isBlank(zookeeperCluster.getServerListStr())) {
				String[] serverListArray = zookeeperCluster.getServerListStr().split(COMMA);
				serverList = ArrayUtil.toArrayList(serverListArray);
			}
			zookeeperCluster.setServerList(serverList);
			// 解密
			zookeeperCluster.setSsh_passwd(EncryptUtil.decode(zookeeperCluster.getSsh_passwd()));
			return zookeeperCluster;
		} catch (Exception e) {
			throw new DaoException("Error when query zookeeper cluster by cluster_id: " + clusterId
					+ ", Error: " + e.getMessage(), e);
		}
	}

	@Override
	public List<ZooKeeperCluster> getAllDetailZooKeeperCluster() throws DaoException {

		List<ZooKeeperCluster> zookeeperClusterList = new ArrayList<ZooKeeperCluster>();
		try {
			ZooKeeperCluster obj = new ZooKeeperCluster();
			zookeeperClusterList = getSqlMapClient().queryForList(
					"ZookeeperMonitorForCluster.queryZooKeeperClusterObjList", obj);
			if (null == zookeeperClusterList && zookeeperClusterList.size() > 0)
				throw new DaoException("没有返回结果");
			List<String> serverList = null;
			for (ZooKeeperCluster zookeeperCluster : zookeeperClusterList) {
				if (!StringUtil.isBlank(zookeeperCluster.getServerListStr())) {
					String[] serverListArray = zookeeperCluster.getServerListStr().split(COMMA);
					serverList = ArrayUtil.toArrayList(serverListArray);
				}
				zookeeperCluster.setServerList(serverList);
			}
			return zookeeperClusterList;
		} catch (Exception e) {
			throw new DaoException("Error when query all zookeeper cluster, Error: "
					+ e.getMessage(), e);
		}
	}

	@Override
	public List<ZooKeeperCluster> getAllZooKeeperClusterIdAndName() throws DaoException {
		List<ZooKeeperCluster> zookeeperClusterList = new ArrayList<ZooKeeperCluster>();
		try {
			ZooKeeperCluster obj = new ZooKeeperCluster();
			zookeeperClusterList = getSqlMapClient().queryForList(
					"ZookeeperMonitorForCluster.queryZooKeeperClusterObjList", obj);
			if (null == zookeeperClusterList && zookeeperClusterList.size() > 0)
				throw new DaoException("没有返回结果");
			return zookeeperClusterList;
		} catch (Exception e) {
			throw new DaoException("Error when query all zookeeper cluster id and name, Error: "
					+ e.getMessage(), e);
		}
	}

	@Override
	public boolean updateZooKeeperSettingsByClusterId(ZooKeeperCluster zooKeeperCluster)
			throws DaoException {

		if (null == zooKeeperCluster)
			return false;

		// 从数据库中获取指定zookeeper集群中所有机器
		try {
			String serverListString = EMPTY_STRING;
			List<String> serverList = zooKeeperCluster.getServerList();
			if (null != serverList && !serverList.isEmpty()) {
				for (String server : serverList) {
					serverListString += server + COMMA;
				}
				serverListString = StringUtil.replaceLast(serverListString, COMMA, EMPTY_STRING);
				zooKeeperCluster.setServerListStr(serverListString);
			}
			int num = getSqlMapClient().update("ZookeeperMonitorForCluster.updateZooKeeperCluster",
					zooKeeperCluster);
			if (1 == num) {
				return true;
			}
			return false;
		} catch (Exception e) {
			throw new DaoException("Error when update zooKeeperCluster by cluster_id: "
					+ zooKeeperCluster + ", Error: " + e.getMessage(), e);
		}
	}

	@Override
	public int addZooKeeper(ZooKeeperCluster zooKeeperCluster) throws DaoException {
		if (null == zooKeeperCluster)
			return -1;

		try {
			String serverListString = CollectionUtil.toString(zooKeeperCluster.getServerList());

			zooKeeperCluster.setServerListStr(serverListString);
			int key = (Integer) getSqlMapClient().insert(
					"ZookeeperMonitorForCluster.insertZooKeeperCluster", zooKeeperCluster);
			return key;
		} catch (Exception e) {
			throw new DaoException("Error when add zooKeeperCluster" + zooKeeperCluster
					+ ", Error: " + e.getMessage(), e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ZooKeeperCluster> queryClusterList(ZooKeeperCluster obj) {
		List<ZooKeeperCluster> lst = null;
		try {
			if (obj.getPagination() != null) {
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj.getPagination().setTotalCount(
						((Integer) getSqlMap().queryForObject(
								"ZookeeperMonitorForCluster.queryClusterListForCount", obj))
								.intValue());
			}
			lst = getSqlMap().queryForList("ZookeeperMonitorForCluster.queryClusterList", obj);
			lst = this.encodeUserPass(lst);
		} catch (Exception sqlexception) {
			LogHelper.error("ZookeeperMonitorForCluster.queryClusterList:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;
	}

	/**
	 * @Title: encodeUserPass
	 * @Description: 用户密码显示加密
	 * @param
	 * @return List<UserObj>
	 * @throws
	 * @author qism
	 * @version 1.0
	 * @createtime 2014-9-15 上午9:19:07
	 */
	private List<ZooKeeperCluster> encodeUserPass(List<ZooKeeperCluster> list) {
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				ZooKeeperCluster user = list.get(i);
				char[] passwordChar = EncryptUtil.decode(user.getSsh_passwd()).toCharArray();
				for (int j = 0; j < passwordChar.length; j++) {
					if (j > 0 && j < passwordChar.length) {
						passwordChar[j] = '*';
					}
				}
				user.setSsh_passwd(new String(passwordChar));
				list.set(i, user);
			}
		}
		return list;
	}

	@Override
	public void deleteZooKeeperCluster(ZooKeeperCluster obj) {
		try {
			Object ob = getSqlMap()
					.delete("ZookeeperMonitorForCluster.deleteZooKeeperCluster", obj);
		} catch (Exception e) {
			LogHelper.error("ZookeeperMonitorForCluster.deleteZooKeeperCluster: " + e.getMessage()
					+ e.getClass().getName());
		}
	}
}
