package com.sitech.basd.sxcloud.rsmu.service.softmanage;

import java.util.List;

import com.sitech.basd.sxcloud.rsmu.domain.softmanage.TbSysAppHisObj;

public interface AppHisService {
	/**
	 * @Title 插入数据(捕获镜像)
	 * @author duangh
	 * @param obj
	 * @return int
	 */
	public int insertByObj(TbSysAppHisObj obj);

	/**
	 * @Title : 查看捕获的应用镜像历史版本信息
	 * 
	 */
	public List historyVersion(TbSysAppHisObj obj);
}
