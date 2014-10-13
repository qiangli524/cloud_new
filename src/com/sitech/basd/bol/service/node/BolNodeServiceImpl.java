package com.sitech.basd.bol.service.node;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sitech.basd.bol.dao.node.BolNodeDao;
import com.sitech.basd.bol.domain.node.BolNodeVO;

@Service("bolNodeService")
public class BolNodeServiceImpl implements BolNodeService {

	@Autowired
	private BolNodeDao bolNodeDao;

	/**
	 * 
	 * @Title: insertByBolNodeVO
	 * @Description:插入集群数据
	 * @param
	 * @return int
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2014-2-25 上午9:14:15
	 */
	@Override
	public int insertByBolNodeVO(BolNodeVO bolNodeVO) throws SQLException {
		return bolNodeDao.insertByBolNodeVO(bolNodeVO);
	}

	/**
	 * 
	 * @Title: queryForListByObj
	 * @Description: 查询集群列表
	 * @param
	 * @return List<BolNodeVO>
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2014-2-25 上午9:16:10
	 */
	@Override
	public List<BolNodeVO> queryForListByObj(BolNodeVO bolNodeVO) throws SQLException {
		return bolNodeDao.queryForListByObj(bolNodeVO);
	}
	
	
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
	public BolNodeVO queryByBolNodeVO(BolNodeVO bolNodeVO) throws SQLException{
		return bolNodeDao.queryByBolNodeVO(bolNodeVO);
	}
	
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
	public int updateByBolNodeVO(BolNodeVO bolNodeVO) throws SQLException{
		return bolNodeDao.updateByBolNodeVO(bolNodeVO);
	}
	
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
	public int deleteByBolNodeVO(BolNodeVO bolNodeVO) throws SQLException{
		return bolNodeDao.deleteByBolNodeVO(bolNodeVO);
	}

}
