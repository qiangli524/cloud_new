package com.sitech.ssd.bj.chinatelcom.dao;

import java.util.List;

import com.sitech.ssd.bj.chinatelcom.domain.TbCloud2ApplicationObj;

/**
 * <p>Title: TbCloud2ApplicationDao</p>
 * <p>Description: 数据持久层接口，对应表tb_cloud2_application</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: SI-TECH </p>
 * @author lipp
 * @version 1.0
 * @createtime 2014-3-20 下午4:42:35
 *
 */
public interface TbCloud2ApplicationDao {

	/**
	 * @Title: queryForListByObj
	 * @Description: 查询列表
	 * @param
	 * @return List<TbCloud2ApplicationObj>
	 * @throws
	 * @author lipp
	 * @version 1.0
	 * @createtime 2014-3-20 下午4:45:44
	 */
	public List<TbCloud2ApplicationObj> queryForListByObj(TbCloud2ApplicationObj tbCloud2ApplicationObj);
	
	/**
	 * @Title: deleteByObj
	 * @Description: 删除记录，如果传入的是id的集合，则批量删除
	 * @param
	 * @return int
	 * @throws
	 * @author lipp
	 * @version 1.0
	 * @createtime 2014-3-20 下午4:45:57
	 */
	public int deleteByObj(TbCloud2ApplicationObj tbCloud2ApplicationObj);
	
	/**
	 * @Title: insertByObj
	 * @Description: 插入记录
	 * @param
	 * @return int
	 * @throws
	 * @author lipp
	 * @version 1.0
	 * @createtime 2014-3-20 下午4:46:27
	 */
	public int insertByObj(TbCloud2ApplicationObj tbCloud2ApplicationObj);
	
	/**
	 * @Title: updateByObj
	 * @Description: 更新记录
	 * @param
	 * @return int
	 * @throws
	 * @author lipp
	 * @version 1.0
	 * @createtime 2014-3-20 下午4:46:41
	 */
	public int updateByObj(TbCloud2ApplicationObj tbCloud2ApplicationObj);
	
	/**
	 * @Title: queryForCountByObj
	 * @Description: 查询记录条数
	 * @param
	 * @return int
	 * @throws
	 * @author lipp
	 * @version 1.0
	 * @createtime 2014-3-20 下午4:49:13
	 */
	public int queryForCountByObj(TbCloud2ApplicationObj tbCloud2ApplicationObj);
}
