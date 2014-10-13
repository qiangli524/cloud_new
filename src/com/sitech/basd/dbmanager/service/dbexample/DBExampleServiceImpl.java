package com.sitech.basd.dbmanager.service.dbexample;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sitech.basd.dbmanager.dao.dbexample.DBExampleDao;
import com.sitech.basd.dbmanager.domain.dbexample.DBExampleObj;

@Service("dBExampleService")
public class DBExampleServiceImpl implements DBExampleService {

	@Autowired
	private DBExampleDao dBExampleDao;

	/**
	 * 
	 * @Title: exampleList
	 * @Description: 查询example
	 * @param
	 * @return List<DBExampleObj>
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 */
	public List<DBExampleObj> queryExampleList(DBExampleObj obj) {
		return dBExampleDao.queryExampleList(obj);
	}

	/**
	 * 
	 * @Title: insertexample
	 * @Description: 增加一条Example记录
	 * @param
	 * @return int
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 */
	public int insertExample(DBExampleObj obj) {
		return dBExampleDao.insertExample(obj);
	}

	/**
	 * 
	 * @Title: updateexample
	 * @Description: 更新一条Example记录
	 * @param
	 * @return int
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 */
	public int updateExample(DBExampleObj obj) {
		return dBExampleDao.updateExample(obj);
	}

	/**
	 * 
	 * @Title: deleteexample
	 * @Description: 删除一条Example记录
	 * @param
	 * @return int
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 */
	public int deleteExample(DBExampleObj obj) {
		return dBExampleDao.deleteExample(obj);
	}

	@Override
	public List<DBExampleObj> queryExampleAndUserManagerBy(DBExampleObj obj) {
		return dBExampleDao.queryExampleAndUserManagerBy(obj);
	}
}
