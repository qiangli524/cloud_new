package com.sitech.basd.bol.dao.process;

import java.sql.SQLException;
import java.util.List;

import com.sitech.basd.bol.domain.nodetask.BolNodeTaskVO;
import com.sitech.basd.bol.domain.process.BolProcessVO;

@SuppressWarnings("all")
public interface BolProcessDao {
	/**
	 * 
	 * @Title: queryByObj
	 * @Description: 查询进程
	 * @param
	 * @return BolProcessVO
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime 2014-3-7 上午10:55:39
	 */
	public BolProcessVO queryByObj(BolProcessVO obj);
	
	/**
	 * 
	 * @Title: queryForListByObj
	 * @Description: 查询进程列表
	 * @param
	 * @return List<BolProcessVO>
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime 2014-3-7 上午10:56:32
	 */
	public List<BolProcessVO> queryForListByObj(BolProcessVO obj);
	
	/**
	 * 
	 * @Title: insertByObj
	 * @Description: 插入进程
	 * @param
	 * @return int
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime 2014-3-7 上午10:56:38
	 */
	public int insertByObj(BolProcessVO obj);
	
	/**
	 * 
	 * @Title: updateByObj
	 * @Description: 更新进程信息
	 * @param
	 * @return int
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime 2014-3-7 上午10:56:46
	 */
	public int updateByObj(BolProcessVO obj);
	
	/**
	 * 
	 * @Title: deleteByObj
	 * @Description: 删除进程
	 * @param
	 * @return int
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime 2014-3-7 上午10:56:51
	 */
	public int deleteByObj(BolProcessVO obj);
	
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
