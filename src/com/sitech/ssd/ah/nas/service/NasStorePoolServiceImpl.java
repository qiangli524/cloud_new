package com.sitech.ssd.ah.nas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sitech.ssd.ah.nas.dao.NasStorePoolDao;
import com.sitech.ssd.ah.nas.domain.NasStorePoolObj;

@Service("nasStorePoolService")
public class NasStorePoolServiceImpl implements NasStorePoolService {

	@Autowired
	private NasStorePoolDao nasStorePoolDao;
	/**
	 * @Title: getStorePoolList
	 * @Description: 获取nas存储池列表
	 * @param
	 * @return 
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @throws Exception 
	 * @createtime 2014年4月7日15:48:04
	 */
	public List<NasStorePoolObj> getStorePoolList(NasStorePoolObj obj){
		return nasStorePoolDao.queryStorePoolByObj(obj);
	}
}
