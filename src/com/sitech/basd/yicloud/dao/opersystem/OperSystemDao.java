package com.sitech.basd.yicloud.dao.opersystem;

import java.util.List;

import com.sitech.basd.yicloud.domain.opersystem.OperSystemObj;

public interface OperSystemDao {

	/**
	 * 获得所有操作系统信息列表
	 * 
	 * @Title:
	 * @author： duangh
	 * @Company: si-tech
	 * @date : May 14, 2012
	 * @version 1.0
	 */
	public List queryForListByObj(OperSystemObj obj);

	/**
	 * 插入操作系统文件信息
	 * 
	 * @Title:
	 * @author： duangh
	 * @Company: si-tech
	 * @date : May 14, 2012
	 * @version 1.0
	 */
	public int insertByObj(OperSystemObj obj);

	/**
	 * 查询并获得一个操作系统文件信息对象
	 * 
	 * @Title:
	 * @author： duangh
	 * @Company: si-tech
	 * @date : May 14, 2012
	 * @version 1.0
	 */
	public OperSystemObj queryByObj(OperSystemObj obj);

	/**
	 * 更新操作系统信息
	 * 
	 * @Title:
	 * @author： duangh
	 * @Company: si-tech
	 * @date : May 14, 2012
	 * @version 1.0
	 */
	public int updateByObj(OperSystemObj obj);

	/**
	 * 删除操作系统信息
	 * 
	 * @Title:
	 * @author： duangh
	 * @Company: si-tech
	 * @date : May 14, 2012
	 * @version 1.0
	 */
	public int deleteByObj(OperSystemObj obj);
}
