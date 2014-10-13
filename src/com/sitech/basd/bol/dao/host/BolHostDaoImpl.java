package com.sitech.basd.bol.dao.host;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.sitech.basd.bol.domain.host.BolHostVO;
import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.utils.randomid.RandomUUID;

/**
 * 
 * <p>
 * Title: BolHostDaoImpl
 * </p>
 * <p>
 * Description: BOL_Host表Dao 实现层
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
@Repository("bolHostDao")
public class BolHostDaoImpl extends BaseDao implements BolHostDao {

	@Override
	public synchronized int  insertByBolHostVO(BolHostVO bolHostVO) throws SQLException {
		int hostId = this.queryId();
		bolHostVO.setId(hostId);
		Object result = getSqlMapClient().insert("BolHost.insertByBolHostVO", bolHostVO);
		return hostId;
	}

	@Override
	public List<BolHostVO> queryForListByObj(BolHostVO bolHostVO) throws SQLException {
		List<BolHostVO> list = null;
		if (bolHostVO.getPagination() != null) {
			bolHostVO.setFIRSTROWNUM(bolHostVO.getPagination().getFirstRownum());
			bolHostVO.setPAGESIZE(bolHostVO.getPagination().getPageSize());
			bolHostVO.getPagination().setTotalCount(
					((Integer) getSqlMap().queryForObject("BolHost.queryForCount", bolHostVO))
							.intValue());
		}
		list = getSqlMapClient().queryForList("BolHost.queryForListByObj", bolHostVO);
		return list;
	}

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
	public int updateByBolHostVO(BolHostVO bolHostVO) throws SQLException {
		int ret = -1;
		Object result = getSqlMapClient().update("BolHost.updateByBolHostVO", bolHostVO);
		if (result != null) {
			ret = Integer.parseInt(result.toString());
		}
		return ret;
	}

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
	public int deleteByBolHostVO(BolHostVO bolHostVO) throws SQLException {
		int ret = -1;
		Object result = getSqlMapClient().delete("BolHost.deleteByBolHostVO", bolHostVO);
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
		Integer result = (Integer)getSqlMapClient().queryForObject("BolHost.queryID");
		if(result==null){
			result = 1;
		}else{
			result++;
		}
		return result;
	}
}
