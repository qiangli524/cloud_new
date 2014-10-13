package com.sitech.basd.resource.dao.switchBoard;

import java.util.List;

import com.sitech.basd.resource.domain.switchBoard.SwitchInterfaceObj;

public interface SwitchInterfaceDao {

	/**
	 * 
	 * @Title: interfaceList
	 * @Description: 查询Interface列表
	 * @param
	 * @return List<SwitchInterfaceObj>
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @createtime jul 24, 2013
	 */
	public List<SwitchInterfaceObj> queryInterfaceList(SwitchInterfaceObj obj);

	/**
	 * 
	 * @Title: insertinterface
	 * @Description: 插入interface
	 * @param
	 * @return int
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @createtime jul 24, 2013
	 */
	public int insertInterface(SwitchInterfaceObj obj);

	/**
	 * 
	 * @Title: updateSwitch
	 * @Description: 更新interface
	 * @param
	 * @return int
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @createtime jul 24, 2013
	 */
	public int updateInterface(SwitchInterfaceObj obj);

	/**
	 * 
	 * @Title: deleteSwitch
	 * @Description: 删除interface
	 * @param
	 * @return int
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @createtime jul 24, 2013
	 */
	public int deleteInterface(SwitchInterfaceObj obj);

	/**
	 * 
	 * @Title: updateVlanId
	 * @Description: 修改VlanId
	 * @param
	 * @return int
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-2-12 下午5:11:31
	 */
	public int updateVlanId(SwitchInterfaceObj obj);

	/**
	 * 
	 * @Title: updateVlanIdNull
	 * @Description: 把VlanId修改为空
	 * @param
	 * @return int
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-2-13 上午11:15:11
	 */
	public int updateVlanIdNull(SwitchInterfaceObj obj);

	/**
	 * 
	 * @Title: deleteVlanByInterfaceId
	 * @Description: 删除Vlan 通过端口id
	 * @param
	 * @return int
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-2-18 上午9:30:33
	 */
	public int deleteVlanByInterfaceId(SwitchInterfaceObj obj);
	
	/**
	 * 
	 * @Title: queryVlanIDandInterName
	 * @Description: 查询vlanid和端口名称
	 * @param
	 * @return SwitchInterfaceObj
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-2-18 下午2:27:54
	 */
	public SwitchInterfaceObj queryVlanIDandInterName(SwitchInterfaceObj obj);
	
	/**
	 * 
	 * @Title: queryInterList
	 * @Description: 查询端口集合
	 * @param
	 * @return List<SwitchInterfaceObj>
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-2-20 下午4:34:26
	 */
	public List<SwitchInterfaceObj> queryInterList(SwitchInterfaceObj obj);
}
