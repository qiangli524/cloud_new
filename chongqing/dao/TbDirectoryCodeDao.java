package dao;

import java.sql.SQLException;
import java.util.List;

import domain.TbDirectoryCodeVO;

/**
 * 
 * <p>
 * Title: TbDirectoryCodeDao
 * </p>
 * <p>
 * Description: 数据字典编码表Dao
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
 * @createtime 2014-5-5 上午11:23:56
 * 
 */
public interface TbDirectoryCodeDao {
	/**
	 * 
	 * @Title: queryForList
	 * @Description: 查询数据字典编码列表
	 * @param
	 * @return List<TbDirectoryCodeVO>
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2014-5-5 上午11:24:37
	 */
	public List<TbDirectoryCodeVO> queryForList(TbDirectoryCodeVO vo) throws SQLException;
}
