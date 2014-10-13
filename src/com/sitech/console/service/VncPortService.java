package com.sitech.console.service;

import java.sql.SQLException;
import java.util.List;

import com.sitech.console.domain.TbVncPortVO;

/**
 * 
 * <p>
 * Title: VncPortService
 * </p>
 * <p>
 * Description: VNC端口逻辑接口类
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
 * @createtime 2014-7-31 上午9:34:36
 * 
 */
public interface VncPortService {
	/**
	 * 
	 * @Title: queryVncPortList
	 * @Description: 查询列表
	 * @param
	 * @return List<TbVncPortVO>
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2014-7-31 上午10:01:24
	 */
	public List<TbVncPortVO> queryVncPortList(TbVncPortVO vo) throws SQLException;

	/**
	 * 
	 * @Title: updateVncPort
	 * @Description: 更新数据
	 * @param
	 * @return void
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2014-7-31 上午10:01:32
	 */
	public void updateVncPort(TbVncPortVO vo) throws SQLException;

	/**
	 * 
	 * @Title: saveVncPort
	 * @Description: 保存vnc端口
	 * @param
	 * @return void
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2014-7-31 下午1:31:47
	 */
	public void saveVncPort(TbVncPortVO vo) throws SQLException;

	/**
	 * 
	 * @Title: deleteVncPort
	 * @Description: 删除vnc端口
	 * @param
	 * @return void
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2014-7-31 下午1:31:47
	 */
	public void deleteVncPort(TbVncPortVO vo) throws SQLException;

	/**
	 * 
	 * @Title: initPortList
	 * @Description: 实例端口列表
	 * @param
	 * @return List<String>
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2014-7-31 下午1:37:56
	 */
	public List<String> initPortList();
}
