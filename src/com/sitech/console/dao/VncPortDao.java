package com.sitech.console.dao;

import java.sql.SQLException;
import java.util.List;

import com.sitech.console.domain.TbVncPortVO;

/**
 * 
 * <p>
 * Title: TbVncPortDao
 * </p>
 * <p>
 * Description: tb_vnc_port 数据库操作接口
 * </p>
 * <p>
 * Copyright: Copyright (c) 2014
 * </p>
 * <p>
 * Company: SI-TECH
 * </p>
 * 
 * @author huojla
 * @version 1.0
 * @createtime 2014-7-30 下午4:39:02
 * 
 */
public interface VncPortDao {
	/**
	 * 
	 * @Title: queryForListByObj
	 * @Description: 查询列表
	 * @param
	 * @return List<TbVncPortVO>
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2014-7-30 下午4:37:09
	 */
	public List<TbVncPortVO> queryForListByObj(TbVncPortVO vo) throws SQLException;

	/**
	 * 
	 * @Title: insertByObj
	 * @Description: 插入数据
	 * @param
	 * @return void
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2014-7-30 下午4:38:04
	 */
	public void insertByObj(TbVncPortVO vo) throws SQLException;

	/**
	 * 
	 * @Title: updateByObj
	 * @Description: 更新数据
	 * @param
	 * @return void
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2014-7-30 下午4:38:13
	 */
	public void updateByObj(TbVncPortVO vo) throws SQLException;

	/**
	 * 
	 * @Title: deleteByObj
	 * @Description: 删除数据
	 * @param
	 * @return void
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2014-7-30 下午4:38:24
	 */
	public void deleteByObj(TbVncPortVO vo) throws SQLException;

	/**
	 * 
	 * @Title: releaseVncPost
	 * @Description: 释放端口
	 * @param
	 * @return void
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2014-7-30 下午4:38:13
	 */
	public void releaseVncPost(TbVncPortVO vo) throws SQLException;
}
