package com.sitech.basd.sxcloud.rsmu.service.softmanage;

import java.util.List;

import com.sitech.basd.sxcloud.rsmu.dao.softmanage.TbSysAppHisDao;
import com.sitech.basd.sxcloud.rsmu.domain.softmanage.TbSysAppHisObj;

public class AppHisServiceImpl implements AppHisService {
	private TbSysAppHisDao tbSysAppHisDao;

	public void setTbSysAppHisDao(TbSysAppHisDao tbSysAppHisDao) {
		this.tbSysAppHisDao = tbSysAppHisDao;
	}

	/**
	 * @Title 插入数据
	 * @author duangh
	 * @param obj
	 * @return int
	 */
	public int insertByObj(TbSysAppHisObj obj) {
		return tbSysAppHisDao.insertByObj(obj);
	}

	/**
	 * @Title : 查看捕获的应用镜像历史版本信息
	 * 
	 */
	public List historyVersion(TbSysAppHisObj obj) {
		return tbSysAppHisDao.historyVersion(obj);
	}
}
