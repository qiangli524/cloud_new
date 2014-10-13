package com.sitech.basd.bol.dao.program;

import java.sql.SQLException;
import java.util.List;

import com.sitech.basd.bol.domain.nodetask.BolNodeTaskVO;
import com.sitech.basd.bol.domain.process.BolProcessVO;
import com.sitech.basd.bol.domain.program.BolProgramCompositeVO;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;

@SuppressWarnings("all")
public interface BolProgramCompositeDao {
	/**
	 * 
	 * @Title: queryByObj
	 * @Description: 查询应用程序组成
	 * @param
	 * @return BolProgramCompositeVO
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime 2014-3-7 上午11:39:34
	 */
	public BolProgramCompositeVO queryByObj(BolProgramCompositeVO obj);
	
	/**
	 * 
	 * @Title: queryForListByObj
	 * @Description: 查询应用程序组成列表
	 * @param
	 * @return List<BolProgramCompositeVO>
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime 2014-3-7 上午11:39:53
	 */
	public List<BolProgramCompositeVO> queryForListByObj(BolProgramCompositeVO obj);
	
	/**
	 * 
	 * @Title: insertByObj
	 * @Description: 插入应用程序组成信息
	 * @param
	 * @return int
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime 2014-3-7 上午11:40:20
	 */
	public int insertByObj(BolProgramCompositeVO obj);
	
	/**
	 * 
	 * @Title: updateByObj
	 * @Description: 更新应用程序组成信息
	 * @param
	 * @return int
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime 2014-3-7 上午11:40:41
	 */
	public int updateByObj(BolProgramCompositeVO obj);
	
	/**
	 * 
	 * @Title: deleteByObj
	 * @Description: 删除应用程序组成信息
	 * @param
	 * @return int
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime 2014-3-7 上午11:41:01
	 */
	public int deleteByObj(BolProgramCompositeVO obj);
	
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
