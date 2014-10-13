package com.sitech.ssd.ah.paas.service.entity;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sitech.ssd.ah.paas.dao.entity.PaasEntityDao;
import com.sitech.ssd.ah.paas.domain.entity.PaasEntityObj;

@Service("paasEntityService")
public class PaasEntityServiceImpl implements PaasEntityService {

	@Autowired
	private PaasEntityDao paasEntityDao;

	@Override
	public List<PaasEntityObj> queryForEntityList(PaasEntityObj obj) {
		return paasEntityDao.queryForEntityList(obj);
	}

	@Override
	public PaasEntityObj queryByObj(PaasEntityObj obj) {
		return paasEntityDao.queryByObj(obj);
	}

	@Override
	public int insertByObj(PaasEntityObj obj) {
		return paasEntityDao.insertByObj(obj);
	}

	@Override
	public int updateByObj(PaasEntityObj obj) {
		return paasEntityDao.updateByObj(obj);
	}

	@Override
	public int deleteByObj(PaasEntityObj obj) {
		return paasEntityDao.deleteByObj(obj);
	}

	@Override
	public List<PaasEntityObj> queryForEntityProp(PaasEntityObj obj) {
		return paasEntityDao.queryForEntityProp(obj);
	}

	@Override
	public List<PaasEntityObj> queryDistinctEntityNameByType(PaasEntityObj obj) {
		// TODO Auto-generated method stub
		return paasEntityDao.queryDistinctEntityNameByType(obj);
	}
}
