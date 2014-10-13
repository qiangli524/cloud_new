package com.sitech.ssd.ah.zookeeper.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sitech.ssd.ah.zookeeper.dao.ZookeeperTreeDao;
import com.sitech.ssd.ah.zookeeper.domain.ZookeeperTreeObj;

@Service("zookeeperTreeService")
public class ZookeeperTreeServiceImpl implements ZookeeperTreeService {
	@Autowired
	ZookeeperTreeDao zookeeperTreeDao;

	@Override
	public ZookeeperTreeObj queryTreeByObj(ZookeeperTreeObj obj) {
		return zookeeperTreeDao.queryTreeByObj(obj);
	}

	@Override
	public void updateZookeeperTreeObj(ZookeeperTreeObj obj) {
		zookeeperTreeDao.updateZookeeperTreeObj(obj);
	}

	@Override
	public void deleteZookeeperTreeObj(ZookeeperTreeObj obj) {
		zookeeperTreeDao.deleteZookeeperTreeObj(obj);
	}
}
