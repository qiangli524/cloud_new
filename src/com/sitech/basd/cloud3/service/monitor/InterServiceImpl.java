package com.sitech.basd.cloud3.service.monitor;

import java.util.List;

import com.sitech.basd.cloud3.dao.monitor.InterDao;
import com.sitech.basd.cloud3.domain.monitor.InterfaceDetailObj;
import com.sitech.basd.cloud3.domain.monitor.InterfaceInfoObj;

public class InterServiceImpl implements InterService {
	private InterDao interDao;

	public void setInterDao(InterDao interDao) {
		this.interDao = interDao;
	}

	/**
	 * 
	 * @Title: queryForListByObj
	 * @Description: ��ѯ�ӿ�ִ�е���ϸ��Ϣ
	 * @param
	 * @return List
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Jan 9, 2013 4:35:26 PM
	 */
	public List queryForListByObj(InterfaceDetailObj obj) {
		return interDao.queryForListByObj(obj);
	}

	/**
	 * 
	 * @Title: queryForList
	 * @Description: ��ʾ���ֽӿ������Ϣ
	 * @param
	 * @return List
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Jan 9, 2013 7:02:48 PM
	 */
	public List queryForList(InterfaceInfoObj obj) {
		return interDao.queryForList(obj);
	}
}
