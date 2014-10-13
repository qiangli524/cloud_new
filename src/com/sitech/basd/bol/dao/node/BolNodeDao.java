package com.sitech.basd.bol.dao.node;

import java.sql.SQLException;
import java.util.List;

import com.sitech.basd.bol.domain.node.BolNodeVO;

/**
 * 
 * <p>
 * Title: BolNodeDao
 * </p>
 * <p>
 * Description: BOL_Node表Dao层
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
 * @createtime 2014-2-25 上午9:33:15
 * 
 */
public interface BolNodeDao {
	/**
	 * 
	 * @Title: insertByBolNodeVO
	 * @Description:插入节点数据
	 * @param
	 * @return int
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2014-2-25 上午9:14:15
	 */
	public int insertByBolNodeVO(BolNodeVO bolNodeVO) throws SQLException;

	/**
	 * 
	 * @Title: queryForListByObj
	 * @Description: 查询节点列表
	 * @param
	 * @return List<BolNodeVO>
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2014-2-25 上午9:16:10
	 */
	public List<BolNodeVO> queryForListByObj(BolNodeVO bolNodeVO) throws SQLException;

	/**
	 * 
	 * @Title: queryByBolNodeVO
	 * @Description: 查询单条数据
	 * @param
	 * @return BolNodeVO
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-2-26 上午10:28:54
	 */
	public BolNodeVO queryByBolNodeVO(BolNodeVO bolNodeVO) throws SQLException;
	
	/**
	 * 
	 * @Title: updateByBolNodeVO
	 * @Description: 修改
	 * @param
	 * @return int
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-2-26 上午10:30:42
	 */
	public int updateByBolNodeVO(BolNodeVO bolNodeVO) throws SQLException;
	
	/**
	 * 
	 * @Title: deleteByBolNodeVO
	 * @Description: 删除
	 * @param
	 * @return int
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-2-26 上午10:31:12
	 */
	public int deleteByBolNodeVO(BolNodeVO bolNodeVO) throws SQLException;
	
	/**
	 * 
	 * @Title: queryId
	 * @Description: 查询实体的id
	 * @param
	 * @return int
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime Jun 9, 2014 4:06:26 PM
	 */
	public int queryId() throws SQLException;
}
