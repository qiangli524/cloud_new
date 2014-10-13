package com.sitech.basd.bol.service.host;

import java.sql.SQLException;
import java.util.List;

import com.sitech.basd.bol.domain.host.BolHostVO;

/**
 * 
 * <p>
 * Title: BolHostService
 * </p>
 * <p>
 * Description: BOL_Host业务逻辑层
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
 * @createtime 2014-2-25 上午9:33:50
 * 
 */
public interface BolHostService {
	/**
	 * 
	 * @Title: insertByBolHostVO
	 * @Description:插入主机数据
	 * @param
	 * @return int
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2014-2-25 上午9:14:15
	 */
	public int insertByBolHostVO(BolHostVO bolHostVO) throws SQLException;

	/**
	 * 
	 * @Title: queryForListByObj
	 * @Description: 查询主机列表
	 * @param
	 * @return List<BolHostVO>
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2014-2-25 上午9:16:10
	 */
	public List<BolHostVO> queryForListByObj(BolHostVO bolHostVO) throws SQLException;

	/**
	 * 
	 * @Title: updateByBolHostVO
	 * @Description: 更新数据
	 * @param
	 * @return int
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2014-2-26 上午10:48:00
	 */
	public int updateByBolHostVO(BolHostVO bolHostVO) throws SQLException;

	/**
	 * 
	 * @Title: deleteByBolHostVO
	 * @Description: 删除数据
	 * @param
	 * @return int
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2014-2-26 上午10:48:00
	 */
	public int deleteByBolHostVO(BolHostVO bolHostVO) throws SQLException;
}
