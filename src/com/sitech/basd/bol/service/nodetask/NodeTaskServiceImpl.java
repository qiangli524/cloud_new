package com.sitech.basd.bol.service.nodetask;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sitech.basd.bol.dao.nodetask.NodeTaskDao;
import com.sitech.basd.bol.domain.nodetask.BolNodeTaskVO;

@Service("nodeTaskService")
public class NodeTaskServiceImpl implements NodeTaskService {

	@Autowired
	private NodeTaskDao nodeTaskDao;
	
	public BolNodeTaskVO queryByObj(BolNodeTaskVO obj) {
		return nodeTaskDao.queryByObj(obj);
	}

	public List<BolNodeTaskVO> queryForListByObj(BolNodeTaskVO obj) {
		return nodeTaskDao.queryForListByObj(obj);
	}

	public int insertByObj(BolNodeTaskVO obj) {
		return nodeTaskDao.insertByObj(obj);
	}

	public int updateByObj(BolNodeTaskVO obj) {
		return nodeTaskDao.updateByObj(obj);
	}

	public int deleteByObj(BolNodeTaskVO obj) {
		return nodeTaskDao.deleteByObj(obj);
	}
	
}
