package com.sitech.basd.yicloud.service.dictionary;

import java.util.List;

import com.sitech.basd.yicloud.dao.dictionary.YiDataDictionaryDao;
import com.sitech.basd.yicloud.domain.dictionary.DictionaryObj;

/**
 * 
 * @Title: 数据字典操作
 * @Copyright: Copyright (c) 2012-2
 * @Company: si-tech
 * @author taoxue
 * @version 1.0
 */
public class YiDataDictionaryServiceImpl implements YiDataDictionaryService {
	/**
	 * @Title:查询已有IP列表
	 * @Copyright: Copyright (c) 2012-4
	 * @Company: si-tech
	 * @author taoxue
	 * @version 1.0
	 */
	public List queryForListByObj(DictionaryObj obj) {
		return yiDataDictionaryDao.queryForListByObj(obj);
	}

	/**
	 * @Title:查询一条信息
	 * @Copyright: Copyright (c) 2012-4
	 * @Company: si-tech
	 * @author taoxue
	 * @version 1.0
	 */
	public DictionaryObj queryByObj(DictionaryObj obj) {
		return yiDataDictionaryDao.queryByObj(obj);
	}

	/**
	 * @Title:插入新添加的信息
	 * @Copyright: Copyright (c) 2012-4
	 * @Company: si-tech
	 * @author taoxue
	 * @version 1.0
	 */
	public int insertByObj(DictionaryObj obj) {
		return yiDataDictionaryDao.insertByObj(obj);
	}

	/**
	 * @Title:更新信息
	 * @Copyright: Copyright (c) 2012-4
	 * @Company: si-tech
	 * @author taoxue
	 * @version 1.0
	 */
	public int updateByObj(DictionaryObj obj) {
		return yiDataDictionaryDao.updateByObj(obj);
	}

	/**
	 * @Title:删除信息
	 * @Copyright: Copyright (c) 2012-4
	 * @Company: si-tech
	 * @author taoxue
	 * @version 1.0
	 */
	public int deleteByObj(DictionaryObj obj) {
		return yiDataDictionaryDao.deleteByObj(obj);
	}

	/**
	 * @Title:查询所需信息列表(主业务，子业务等)
	 * @Copyright: Copyright (c) 2012-4
	 * @Company: si-tech
	 * @author taoxue
	 * @version 1.0
	 */
	public List queryBusinessType(DictionaryObj obj) {
		return yiDataDictionaryDao.queryBusinessType(obj);
	}

	YiDataDictionaryDao yiDataDictionaryDao;

	public void setYiDataDictionaryDao(YiDataDictionaryDao yiDataDictionaryDao) {
		this.yiDataDictionaryDao = yiDataDictionaryDao;
	}

}
