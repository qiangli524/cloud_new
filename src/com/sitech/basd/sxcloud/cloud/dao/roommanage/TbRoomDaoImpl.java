package com.sitech.basd.sxcloud.cloud.dao.roommanage;

import java.util.List;

import org.apache.log4j.Logger;

import com.sitech.basd.sxcloud.cloud.domain.roommanage.TbRoomObj;
import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;

public class TbRoomDaoImpl extends BaseDao implements TbRoomDao {
	/**
	 * @Title:根据主机部分信息查询匹配的所有机柜信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author wangqxa
	 * @version 1.0
	 */
	static Logger log = Logger.getLogger("infoAppender");

	public List queryForCubSelect(TbRoomObj obj) {
		List lst = null;

		try {
			// log.info( "TbCubinet.queryTbCubinetAllForListByObj:" +
			// getClass().getName()) ;
			lst = getSqlMap().queryForList("TbRoomObj.queryTbRoomForCubSelect");
		} catch (Exception sqlexception) {
			sqlexception.printStackTrace();
			LogHelper.error("TbCubinet.queryTbCubinetAllForListByObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;
	}

}
