package com.sitech.basd.yicloud.service.switches;

import java.util.List;

import com.sitech.basd.yicloud.domain.switches.VirtualSwitch;

public interface VirtualSwitchService {
	/**
	 * @Title:插入虚拟交换机信息
	 * @Copyright: Copyright (c) 2012-4
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public int insertByObj(VirtualSwitch obj);
	
	/**
	 * @Title:查询所有虚拟交换机信息
	 * @Copyright: Copyright (c) 2012-9
	 * @Company: si-tech
	 * @author hehui
	 * @version 1.0
	 */
	public List listVirtualSwitch(VirtualSwitch obj);
	
	public List queryByObj(VirtualSwitch obj);
}
