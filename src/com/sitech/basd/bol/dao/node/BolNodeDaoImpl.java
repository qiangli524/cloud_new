package com.sitech.basd.bol.dao.node;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.sitech.basd.bol.domain.node.BolNodeVO;
import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.utils.randomid.RandomUUID;

/**
 * 
 * <p>
 * Title: BolNodeDaoImpl
 * </p>
 * <p>
 * Description: BOL_Node表Dao 实现层
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
@Repository("bolNodeDao")
public class BolNodeDaoImpl extends BaseDao implements BolNodeDao {

	@Override
	public synchronized int insertByBolNodeVO(BolNodeVO bolNodeVO) throws SQLException {
		int ret = -1;
		bolNodeVO.setId(this.queryId());
		Object result = getSqlMapClient().insert("BolNode.insertByBolNodeVO", bolNodeVO);
		if (result != null) {
			ret = Integer.parseInt(result.toString());
		}
		return ret;
	}

	@Override
	public List<BolNodeVO> queryForListByObj(BolNodeVO bolNodeVO) throws SQLException {
		List<BolNodeVO> list = null;
		if (bolNodeVO.getPagination() != null) {
			bolNodeVO.setFIRSTROWNUM(bolNodeVO.getPagination().getFirstRownum());
			bolNodeVO.setPAGESIZE(bolNodeVO.getPagination().getPageSize());
			bolNodeVO.getPagination().setTotalCount(
					((Integer) getSqlMap().queryForObject("BolNode.queryForCount", bolNodeVO))
							.intValue());
		}
		list = getSqlMapClient().queryForList("BolNode.queryForListByObj", bolNodeVO);
		return list;
	}
	
	@Override
	public int deleteByBolNodeVO(BolNodeVO bolNodeVO) throws SQLException {
		int ret = -1;
		Object result = getSqlMapClient().delete("BolNode.deleteByBolNodeVO", bolNodeVO);
		if (result != null) {
			ret = Integer.parseInt(result.toString());
		}
		return ret;
	}
	
	@Override
	public BolNodeVO queryByBolNodeVO(BolNodeVO bolNodeVO) throws SQLException {
		BolNodeVO obj = new BolNodeVO();
		obj = (BolNodeVO) getSqlMapClient().queryForObject("BolNode.queryByBolNodeVO", bolNodeVO);
		return obj;
	}
	
	@Override
	public int updateByBolNodeVO(BolNodeVO bolNodeVO) throws SQLException {
		int ret = -1;
		Object result = getSqlMapClient().update("BolNode.updateByBolNodeVO", bolNodeVO);
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
		Integer result = (Integer)getSqlMapClient().queryForObject("BolNode.queryID");
		if(result==null){
			result = 1;
		}else{
			result++;
		}
		return result;
	}

}
