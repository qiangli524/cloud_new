package com.sitech.basd.bol.service.cluster;

import java.sql.SQLException;
import java.util.List;

import com.sitech.basd.bol.domain.cluster.BolClusterVO;

/**
 * 
 * <p>
 * Title: BolClusterService
 * </p>
 * <p>
 * Description: BOL_CLUSTER业务逻辑层
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
public interface BolClusterService {
	/**
	 * 
	 * @Title: insertByBolClusterVO
	 * @Description:插入集群数据
	 * @param
	 * @return int
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2014-2-25 上午9:14:15
	 */
	public int insertByBolClusterVO(BolClusterVO bolClusterVO) throws SQLException;

	/**
	 * 
	 * @Title: queryForListByObj
	 * @Description: 查询集群列表
	 * @param
	 * @return List<BolClusterVO>
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2014-2-25 上午9:16:10
	 */
	public List<BolClusterVO> queryForListByObj(BolClusterVO bolClusterVO) throws SQLException;

	/**
	 * 
	 * @Title: updateByBolClusterVO
	 * @Description: 更新数据
	 * @param
	 * @return int
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2014-2-26 上午10:48:00
	 */
	public int updateByBolClusterVO(BolClusterVO bolClusterVO) throws SQLException;

	/**
	 * 
	 * @Title: deleteByBolClusterVO
	 * @Description: 删除数据
	 * @param
	 * @return int
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2014-2-26 上午10:48:00
	 */
	public int deleteByBolClusterVO(BolClusterVO bolClusterVO) throws SQLException;
}
