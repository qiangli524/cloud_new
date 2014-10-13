package com.sitech.basd.yicloud.service.opersystem;

import java.util.List;

import com.sitech.basd.yicloud.dao.opersystem.OperSystemDao;
import com.sitech.basd.yicloud.domain.opersystem.OperSystemObj;

public class OperSystemServiceImpl implements OperSystemService {
	/**
	 * @Title:查询已有操作系统列表
	 * @Copyright: Copyright (c) 2012-5
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public List queryForListByObj(OperSystemObj obj) {
		return operSystemDao.queryForListByObj(obj);
	}

	/**
	 * @Title:查询一条操作系统文件信息
	 * @Copyright: Copyright (c) 2012-5
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public OperSystemObj queryByObj(OperSystemObj obj) {
		return operSystemDao.queryByObj(obj);
	}

	/**
	 * @Title:插入新添加操作系统文件
	 * @Copyright: Copyright (c) 2012-5
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public int insertByObj(OperSystemObj obj) {
		return operSystemDao.insertByObj(obj);
	}

	/**
	 * @Title:更新操作系统信息
	 * @Copyright: Copyright (c) 2012-5
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public int updateByObj(OperSystemObj obj) {
		return operSystemDao.updateByObj(obj);
	}

	/**
	 * @Title:删除操作系统信息
	 * @Copyright: Copyright (c) 2012-5
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public int deleteByObj(OperSystemObj obj) {
		return operSystemDao.deleteByObj(obj);
	}

	OperSystemDao operSystemDao;

	public void setOperSystemDao(OperSystemDao operSystemDao) {
		this.operSystemDao = operSystemDao;
	}

}
