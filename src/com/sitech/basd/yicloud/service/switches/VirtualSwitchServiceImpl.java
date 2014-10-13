package com.sitech.basd.yicloud.service.switches;

import java.util.List;

import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;
import com.sitech.basd.yicloud.dao.switches.VirtualSwitchDao;
import com.sitech.basd.yicloud.domain.switches.VirtualSwitch;

public class VirtualSwitchServiceImpl implements VirtualSwitchService {
	private VirtualSwitchDao virtualSwitchDao;

	public void setVirtualSwitchDao(VirtualSwitchDao virtualSwitchDao) {
		this.virtualSwitchDao = virtualSwitchDao;
	}

	/**
	 * @Title:插入虚拟交换机信息
	 * @Copyright: Copyright (c) 2012-4
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public int insertByObj(VirtualSwitch obj) {
		return virtualSwitchDao.insertByObj(obj);
	}
	
	/**
	 * @Title:查询所有虚拟交换机信息
	 * @Copyright: Copyright (c) 2012-9
	 * @Company: si-tech
	 * @author hehui
	 * @version 1.0
	 */
	public List listVirtualSwitch(VirtualSwitch obj){
		return virtualSwitchDao.listVirtualSwitch(obj);
	}
	
	public List queryByObj(VirtualSwitch obj) {
		return virtualSwitchDao.queryByObj(obj);
	}
}
