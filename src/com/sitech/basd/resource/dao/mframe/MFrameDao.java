package com.sitech.basd.resource.dao.mframe;

import java.util.List;

import com.sitech.basd.resource.domain.mframe.MFrameObj;

public interface MFrameDao {

	/**
	 * 
	 * @Title: FrameList
	 * @Description: 查询机框列表
	 * @param
	 * @return List<MFrameObj>
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 */
	public List<MFrameObj> queryFrameList(MFrameObj obj);

	/**
	 * 
	 * @Title: insert
	 * @Description: 增加一条机框记录
	 * @param
	 * @return int
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 */
	public int insertFrame(MFrameObj obj);

	/**
	 * 
	 * @Title: insert
	 * @Description: 更新一条机框记录
	 * @param
	 * @return int
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 */
	public int updateFrame(MFrameObj obj);

	/**
	 * 
	 * @Title: deleteConfig
	 * @Description: 删除一条机框记录
	 * @param
	 * @return int
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @createtime May 23, 2013 4:41:09 PM
	 */
	public int deleteFrame(MFrameObj obj);

}
