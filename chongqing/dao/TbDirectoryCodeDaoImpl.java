package dao;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;

import domain.TbDirectoryCodeVO;

/**
 * 
 * <p>
 * Title: TbDirectoryCodeDaoImpl
 * </p>
 * <p>
 * Description: 实现类
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
 * @createtime 2014-5-5 上午11:25:09
 * 
 */
@Repository("tbDirectoryCodeDao")
public class TbDirectoryCodeDaoImpl extends BaseDao implements TbDirectoryCodeDao {
	/**
	 * 
	 * @Title: queryForList
	 * @Description: 查询数据字典编码列表
	 * @param
	 * @return List<TbDirectoryCodeVO>
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @throws SQLException
	 * @createtime 2014-5-5 上午11:24:37
	 */
	@Override
	public List<TbDirectoryCodeVO> queryForList(TbDirectoryCodeVO vo) throws SQLException {
		List<TbDirectoryCodeVO> list = null;
		list = this.getSqlMapClient().queryForList("TbDirectoryCode.queryForList", vo);
		return list;
	}

}
