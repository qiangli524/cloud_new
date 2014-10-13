package com.sitech.basd.yicloud.dao.switches;

import java.util.List;

import com.sitech.basd.yicloud.domain.switches.SwitchObj;

public interface SwitchesDao {
	/**
	 * 
	 * @Title: queryForListByObj
	 * @Description:查询列表（包括路由器和交换机）
	 * @param
	 * @return List
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Aug 28, 2012 11:36:50 AM
	 */
	public List queryForListByObj(SwitchObj obj);

	/**
	 * 
	 * @Title: insertByObj
	 * @Description: 添加一条记录
	 * @param
	 * @return int
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Aug 28, 2012 11:20:10 AM
	 */
	public int insertByObj(SwitchObj obj);

	/**
	 * 
	 * @Title: updateByObj
	 * @Description: 修改一条记录
	 * @param
	 * @return int
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Aug 28, 2012 11:20:10 AM
	 */
	public int updateByObj(SwitchObj obj);

	/**
	 * 
	 * @Title: deleteByObj
	 * @Description: 删除一条记录
	 * @param
	 * @return int
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Aug 28, 2012 11:20:10 AM
	 */
	public int deleteByObj(SwitchObj obj);

	/**
	 * 
	 * @Title: queryByObj
	 * @Description: 查询一条记录
	 * @param
	 * @return SwitchObj
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Aug 28, 2012 11:22:30 AM
	 */
	public SwitchObj queryByObj(SwitchObj obj);

	/**
	 * 
	 * @Title: getIdSequence
	 * @Description: 查询路由器（交换机）的ID
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Jul 18, 2012 9:44:10 AM
	 */
	public String getIdSequence();
}
