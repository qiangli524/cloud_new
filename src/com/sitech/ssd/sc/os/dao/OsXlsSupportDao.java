package com.sitech.ssd.sc.os.dao;

import java.util.List;

import com.sitech.ssd.sc.os.domain.HostModel;
import com.sitech.ssd.sc.os.domain.XlsHostModel;

public interface OsXlsSupportDao {

	/**
	 * @Tittle insertHose
	 * @Description
	 * @Modify Date 2014年7月4日09:20:56
	 * @param hostModels
	 *            ，xlshostModels
	 */
	public String insert(List<HostModel> hostModels);
}
