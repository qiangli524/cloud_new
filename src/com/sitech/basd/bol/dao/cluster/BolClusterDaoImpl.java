package com.sitech.basd.bol.dao.cluster;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.sitech.basd.bol.domain.cluster.BolClusterVO;
import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.utils.randomid.RandomUUID;

/**
 * 
 * <p>
 * Title: BolClusterDaoImpl
 * </p>
 * <p>
 * Description: BOL_CLUSTER表Dao 实现层
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
@Repository("bolClusterDao")
public class BolClusterDaoImpl extends BaseDao implements BolClusterDao {

	@Override
	public synchronized int insertByBolClusterVO(BolClusterVO bolClusterVO) throws SQLException {
		int ret = -1;
		bolClusterVO.setId(this.queryId());
		Object result = getSqlMapClient().insert("BolCluster.insertByBolClusterVO", bolClusterVO);
		if (result != null) {
			ret = Integer.parseInt(result.toString());
		}
		return ret;
	}

	@Override
	public List<BolClusterVO> queryForListByObj(BolClusterVO bolClusterVO) throws SQLException {
		List<BolClusterVO> list = null;
		if (bolClusterVO.getPagination() != null) {
			bolClusterVO.setFIRSTROWNUM(bolClusterVO.getPagination().getFirstRownum());
			bolClusterVO.setPAGESIZE(bolClusterVO.getPagination().getPageSize());
			bolClusterVO.getPagination()
					.setTotalCount(
							((Integer) getSqlMap().queryForObject("BolCluster.queryForCount",
									bolClusterVO)).intValue());
		}
		list = getSqlMapClient().queryForList("BolCluster.queryForListByObj", bolClusterVO);
		return list;
	}

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
	public int updateByBolClusterVO(BolClusterVO bolClusterVO) throws SQLException {
		int ret = -1;
		Object result = getSqlMapClient().update("BolCluster.updateByBolClusterVO", bolClusterVO);
		if (result != null) {
			ret = Integer.parseInt(result.toString());
		}
		return ret;
	}

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
	public int deleteByBolClusterVO(BolClusterVO bolClusterVO) throws SQLException {
		int ret = -1;
		Object result = getSqlMapClient().update("BolCluster.deleteByBolClusterVO", bolClusterVO);
		if (result != null) {
			ret = Integer.parseInt(result.toString());
		}
		return ret;
	}
	
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
	public int queryId() throws SQLException{
		Integer result = (Integer)getSqlMapClient().queryForObject("BolCluster.queryID");
		if(result==null){
			result = 1;
		}else{
			result++;
		}
		return result;
	}

}
