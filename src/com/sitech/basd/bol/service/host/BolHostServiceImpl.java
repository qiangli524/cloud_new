package com.sitech.basd.bol.service.host;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sitech.basd.bol.dao.host.BolHostDao;
import com.sitech.basd.bol.domain.host.BolHostVO;

@Service("bolHostService")
public class BolHostServiceImpl implements BolHostService {

	@Autowired
	private BolHostDao bolHostDao;

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
	@Override
	public int insertByBolHostVO(BolHostVO bolHostVO) throws SQLException {
		return bolHostDao.insertByBolHostVO(bolHostVO);
	}

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
	@Override
	public List<BolHostVO> queryForListByObj(BolHostVO bolHostVO) throws SQLException {
		return bolHostDao.queryForListByObj(bolHostVO);
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
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String date = sdf.format(new Date());
		bolHostVO.setLastUpdate(date);
		return bolHostDao.updateByBolHostVO(bolHostVO);
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
		return bolHostDao.deleteByBolHostVO(bolHostVO);
	}
}
