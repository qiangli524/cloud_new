package com.sitech.basd.sxcloud.rsmu.service.softmanage;

import java.util.List;

import com.sitech.basd.sxcloud.rsmu.domain.softmanage.TbBusiAppPortObj;
import com.sitech.basd.sxcloud.rsmu.service.BaseService;
import com.sitech.basd.sxcloud.rsmu.dao.softmanage.TbBusiAppPortDao;
public class AppPortServiceImpl extends BaseService implements AppPortService {

	public int deleteByObj(TbBusiAppPortObj obj) {
		return tbBusiAppPortDao.deleteByObj(obj);
	}

	public int insertByObj(TbBusiAppPortObj obj) {
		return tbBusiAppPortDao.insertByObj(obj);
	}

	public TbBusiAppPortObj queryByObj(TbBusiAppPortObj obj) {
		// TODO Auto-generated method stub
		return tbBusiAppPortDao.queryByObj(obj);
	}

	public List queryForListByObj(TbBusiAppPortObj obj) {
		// TODO Auto-generated method stub
		return tbBusiAppPortDao.queryForListByObj(obj);
	}

	public int updateByObj(TbBusiAppPortObj obj) {
		// TODO Auto-generated method stub
		return tbBusiAppPortDao.updateByObj(obj);
	}
    private TbBusiAppPortDao tbBusiAppPortDao;
    
	public void setTbBusiAppPortDao(TbBusiAppPortDao tbBusiAppPortDao) {
		this.tbBusiAppPortDao = tbBusiAppPortDao;
	}
    
}
