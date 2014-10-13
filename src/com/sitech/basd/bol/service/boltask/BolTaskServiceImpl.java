package com.sitech.basd.bol.service.boltask;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sitech.basd.bol.dao.boltask.BolTaskDao;
import com.sitech.basd.bol.domain.boltask.BolTaskObj;

@Service("bolTaskService")
public class BolTaskServiceImpl implements BolTaskService{

	@Autowired
	private BolTaskDao bolTaskDao;
	
	/**
	 * @Title: insertByObj
	 * @Description: 插入一条记录
	 * @param
	 * @return int
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-11-7 下午5:32:04
	 */
	public int insertByObj(BolTaskObj bolTaskObj){
		return bolTaskDao.insertByObj(bolTaskObj);
	}
}
