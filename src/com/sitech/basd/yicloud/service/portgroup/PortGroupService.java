package com.sitech.basd.yicloud.service.portgroup;

import java.util.List;
import java.util.Map;

import com.sitech.basd.yicloud.domain.nic.NicRelationObj;
import com.sitech.basd.yicloud.domain.portgroup.PortGroup;
import com.sitech.basd.yicloud.domain.switches.VirtualSwitch;

public interface PortGroupService {
	/**
	 * @Title:插入端口组信息
	 * @Copyright: Copyright (c) 2012-4
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public int insertByObj(Boolean b, String hostName, PortGroup obj);

	/**
	 * @Title:查询虚拟交换机对应的端口组
	 * @Copyright: Copyright (c) 2012-9
	 * @Company: si-tech
	 * @author hehui
	 * @version 1.0
	 */
	public List queryPGListByPortGroup(PortGroup obj);

	/**
	 * @Title:修改端口组信息
	 * @Copyright: Copyright (c) 2012-9
	 * @Company: si-tech
	 * @author hehui
	 * @version 1.0
	 */
	public int updateByObj(String hostName, PortGroup obj);

	/**
	 * @Title:删除端口组信息
	 * @Copyright: Copyright (c) 2012-9
	 * @Company: si-tech
	 * @author hehui
	 * @version 1.0
	 */
	public int deleteByObj(PortGroup obj,String vSwitchId);

	/**
	 * @Title:根据端口组ID查询端口组
	 * @Copyright: Copyright (c) 2012-9
	 * @Company: si-tech
	 * @author hehui
	 * @version 1.0
	 */
	public PortGroup queryPortGroupById(PortGroup obj);

	/**
	 * @Title:查询主机对应的端口组
	 * @Copyright: Copyright (c) 2012-9
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public List<NicRelationObj> queryHostPortGroup(VirtualSwitch obj);

	/**
	 * 
	 * @Title: createNetWork
	 * @Description: 创建vmware网络
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Mar 18, 2013 5:54:15 PM
	 */
	public String createNetWork(Map map);

	/**
	 * 
	 * @Title: removeVirtualSwitch
	 * @Description: 删除虚拟交换机
	 * @return String
	 * @author duangh
	 * @version 1.0
	 */
	public String removeVirtualSwitch(VirtualSwitch vs);
}
