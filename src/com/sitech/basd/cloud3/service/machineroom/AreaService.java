package com.sitech.basd.cloud3.service.machineroom;

import java.util.List;

import com.sitech.basd.cloud3.domain.machineroom.RoomAreaObj;

public interface AreaService {
	/**
	 * @Title:查询机房区域所有信息
	 * @Copyright: Copyright (c) 2013-03-15
	 * @Company: si-tech
	 * @author xmq
	 * @version 1.0
	 */
	public List queryAreaList(RoomAreaObj obj);
	/**
	 * @Title:删除机房区域信息
	 * @Copyright: Copyright (c) 2013-03-15
	 * @Company: si-tech
	 * @author xmq
	 * @version 1.0
	 */
	
	public int deleteAreaObj(RoomAreaObj obj);
	/**
	 * @Title:修改机房区域信息
	 * @Copyright: Copyright (c) 2013-03-15
	 * @Company: si-tech
	 * @author xmq
	 * @version 1.0
	 */
	public int updateAreaObj(RoomAreaObj obj);
	/**
	 * @Title:增加机房区域信息
	 * @Copyright: Copyright (c) 2013-03-15
	 * @Company: si-tech
	 * @author xmq
	 * @version 1.0
	 */
	public int insertAreaObj(RoomAreaObj obj);
	/**
	 * @Title:查一条机房区域信息
	 * @Copyright: Copyright (c) 2013-03-15
	 * @Company: si-tech
	 * @author xmq
	 * @version 1.0
	 */
	public RoomAreaObj queryOneArea(RoomAreaObj obj);

}
