package com.sitech.basd.bol.service.cluster;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sitech.basd.bol.dao.cluster.BolClusterDao;
import com.sitech.basd.bol.domain.cluster.BolClusterVO;

@Service("bolClusterService")
public class BolClusterServiceImpl implements BolClusterService {

	@Autowired
	private BolClusterDao bolClusterDao;

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
	@Override
	public int insertByBolClusterVO(BolClusterVO bolClusterVO) throws SQLException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String date = sdf.format(new Date());
		bolClusterVO.setLastUpdate(date);
		return bolClusterDao.insertByBolClusterVO(bolClusterVO);
	}

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
	@Override
	public List<BolClusterVO> queryForListByObj(BolClusterVO bolClusterVO) throws SQLException {
		return bolClusterDao.queryForListByObj(bolClusterVO);
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
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String date = sdf.format(new Date());
		bolClusterVO.setLastUpdate(date);
		return bolClusterDao.updateByBolClusterVO(bolClusterVO);
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
		return bolClusterDao.deleteByBolClusterVO(bolClusterVO);
	}

}
