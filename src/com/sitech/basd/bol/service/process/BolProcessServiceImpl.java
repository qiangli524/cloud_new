package com.sitech.basd.bol.service.process;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sitech.basd.bol.dao.nodetask.NodeTaskDao;
import com.sitech.basd.bol.dao.process.BolProcessDao;
import com.sitech.basd.bol.domain.nodetask.BolNodeTaskVO;
import com.sitech.basd.bol.domain.process.BolProcessVO;

@Service("bolProcessService")
public class BolProcessServiceImpl implements BolProcessService {

	@Autowired
	private BolProcessDao bolProcessDao;
	
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
	public BolProcessVO queryByObj(BolProcessVO obj){
		return bolProcessDao.queryByObj(obj);
	}
	
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
	public List<BolProcessVO> queryForListByObj(BolProcessVO obj){
		return bolProcessDao.queryForListByObj(obj);
	}
	
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
	public int insertByObj(BolProcessVO obj){
		return bolProcessDao.insertByObj(obj);
	}
	
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
	public int updateByObj(BolProcessVO obj){
		return bolProcessDao.updateByObj(obj);
	}
	
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
	public int deleteByObj(BolProcessVO obj){
		return bolProcessDao.deleteByObj(obj);
	}
	
}
