package com.sitech.basd.bol.dao.program;

import java.sql.SQLException;
import java.util.List;

import com.sitech.basd.bol.domain.nodetask.BolNodeTaskVO;
import com.sitech.basd.bol.domain.process.BolProcessVO;
import com.sitech.basd.bol.domain.program.BolProgramVO;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;

@SuppressWarnings("all")
public interface BolProgramDao {
	
	/**
	 * 
	 * @Title: queryByObj
	 * @Description: 查询应用程序
	 * @param
	 * @return BolProgramVO
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime 2014-3-7 上午11:21:25
	 */
	public BolProgramVO queryByObj(BolProgramVO obj);
	
	/**
	 * 
	 * @Title: queryForListByObj
	 * @Description: 查询应用程序列表
	 * @param
	 * @return List<BolProgramVO>
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime 2014-3-7 上午11:21:51
	 */
	public List<BolProgramVO> queryForListByObj(BolProgramVO obj);
	
	/**
	 * 
	 * @Title: insertByObj
	 * @Description: 插入应用程序
	 * @param
	 * @return int
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime 2014-3-7 上午11:22:19
	 */
	public int insertByObj(BolProgramVO obj);
	
	/**
	 * 
	 * @Title: updateByObj
	 * @Description: 更新应用程序
	 * @param
	 * @return int
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime 2014-3-7 上午11:22:36
	 */
	public int updateByObj(BolProgramVO obj);
	
	/**
	 * 
	 * @Title: deleteByObj
	 * @Description: 删除应用程序
	 * @param
	 * @return int
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime 2014-3-7 上午11:22:50
	 */
	public int deleteByObj(BolProgramVO obj);
	
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
