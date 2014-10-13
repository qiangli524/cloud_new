package com.sitech.basd.resource.service.mframe;

import java.util.List;

import com.sitech.basd.resource.domain.mframe.MFrameObj;

public interface MFrameService {

	/**
	 * 
	 * @Title: FrameList
	 * @Description: 查询机框列表
	 * @param
	 * @return List
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 */
	public List<MFrameObj> queryFrameList(MFrameObj obj);

	/**
	 * 
	 * @Title: insertFrame
	 * @Description: 增加一条机框记录
	 * @param
	 * @return List
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 */
	public int insertFrame(MFrameObj obj);

	/**
	 * 
	 * @Title: updateFrame
	 * @Description: 修改机框信息
	 * @param
	 * @return List
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 */
	public int updateFrame(MFrameObj obj);

	/**
	 * 
	 * @Title: deleteFrame
	 * @Description: 删除一条机框记录
	 * @param
	 * @return int
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @createtime jul 15, 2013
	 */
	public int deleteFrame(MFrameObj obj);

}
