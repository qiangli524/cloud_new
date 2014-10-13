package com.sitech.basd.cloud3.service.machineroom;

import java.util.List;

import com.sitech.basd.cloud3.domain.machineroom.MachineRoomObj;
import com.sitech.basd.cloud3.domain.machineroom.RoomAreaObj;

public interface RoomService {
	/**
	 * @Title:查询所在机房
	 * @Copyright: Copyright (c) 2013-03-14
	 * @Company: si-tech
	 * @author xmq
	 * @version 1.0
	 */
	public List<MachineRoomObj> queryRoomObj(MachineRoomObj obj);
	/**
	 * @Title:查询机房所有信息
	 * @Copyright: Copyright (c) 2013-03-14
	 * @Company: si-tech
	 * @author xmq
	 * @version 1.0
	 */

	@SuppressWarnings("unchecked")
	public List queryRoomList(MachineRoomObj obj);
	
	/**
	 * @Title:增加机房信息
	 * @Copyright: Copyright (c) 2013-03-14
	 * @Company: si-tech
	 * @author xmq
	 * @version 1.0
	 */
	public int insertRoomObj(MachineRoomObj obj);
	/**
	 * @Title:删除机房信息
	 * @Copyright: Copyright (c) 2013-03-14
	 * @Company: si-tech
	 * @author xmq
	 * @version 1.0
	 */
	public int deleteRoomObj(MachineRoomObj obj);
	/**
	 * @Title:修改机房信息
	 * @Copyright: Copyright (c) 2013-03-14
	 * @Company: si-tech
	 * @author xmq
	 * @version 1.0
	 */
	public int updateRoomObj(MachineRoomObj obj);
	/**
	 * @Title:查询一条机房信息
	 * @Copyright: Copyright (c) 2013-03-14
	 * @Company: si-tech
	 * @author xmq
	 * @version 1.0
	 */
	public MachineRoomObj queryRoomOne(MachineRoomObj obj);

}
