package com.sitech.basd.sxcloud.cloud.service.cubinetmanage;

import java.util.List;

import com.sitech.basd.sxcloud.cloud.dao.cubinetmanage.TbCubinetDao;
import com.sitech.basd.sxcloud.cloud.domain.cubinetmanage.TbCubinetObj;
import com.sitech.basd.sxcloud.rsmu.service.BaseService;

public class CubinetServiceImpl extends BaseService implements CubinetService {

	TbCubinetDao tbCubinetDao;

	/**
	 * @Title:根据机柜部分信息查询匹配的所有机柜信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author wangqxa
	 * @version 1.0
	 */
	public List queryForListByObj(TbCubinetObj obj) {
		return tbCubinetDao.queryForListByObj(obj);
	}

	/**
	 * @Title:查询出具体机柜信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author wangqxa
	 * @version 1.0
	 */
	public TbCubinetObj queryByObj(TbCubinetObj obj) {
		return tbCubinetDao.queryByObj(obj);
	}

	/**
	 * @Title:更新机柜信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author wangqxa
	 * @version 1.0
	 */
	public int updateByObj(TbCubinetObj obj) {
		return tbCubinetDao.updateByObj(obj);
	}

	/**
	 * @Title:删除机柜信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author wangqxa
	 * @version 1.0
	 */
	public int deleteByObj(TbCubinetObj obj) {
		return tbCubinetDao.deleteByObj(obj);
	}

	/**
	 * @Title:插入机柜信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author wangqxa
	 * @version 1.0
	 */
	public int insertByObj(TbCubinetObj obj) {
		return tbCubinetDao.insertByObj(obj);
	}

	/**
	 * @Title:取出房间下拉列表信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author wangqxa
	 * @version 1.0
	 */
	public List queryTbRoomForCubSelect() {
		return tbCubinetDao.queryTbRoomForCubSelect();
	}

	/**
	 * @Title:取出机柜里的主机个数
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author wangqxa
	 * @version 1.0
	 */
	public int queryTbHostCountForCanDelete(TbCubinetObj obj) {
		return this.tbCubinetDao.queryTbHostCountForCanDelete(obj);
	}
	
	/**
	 * 
	 * @Title: getSequence
	 * @Description: 求一个自增长的值作为Id
	 * @param
	 * @return String
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime 2013-1-15 下午04:58:38
	 */
	public String getSequence(){
		return tbCubinetDao.getSequence();
	}

	public void setTbCubinetDao(TbCubinetDao tbCubinetDao) {
		this.tbCubinetDao = tbCubinetDao;
	}
}
