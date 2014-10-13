package com.sitech.basd.resource.dao.switchBoard;

import java.util.List;

import com.sitech.basd.resource.domain.switchBoard.VlanObj;

public interface VlanDao {

	/**
	 * 
	 * @Title: VlanList
	 * @Description: 查询vlan列表
	 * @param
	 * @return List<Vlan>
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @createtime jul 23, 2013
	 */
	public List<VlanObj> queryVlanList(VlanObj obj);

	/**
	 * 
	 * @Title: insertVlan
	 * @Description: 插入vlan
	 * @param
	 * @return int
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @createtime jul 23, 2013
	 */
	public int insertVlan(VlanObj obj);

	/**
	 * 
	 * @Title: updateVlan
	 * @Description: 更新交换机
	 * @param
	 * @return int
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @createtime jul 23, 2013
	 */
	public int updateVlan(VlanObj obj);

	/**
	 * 
	 * @Title: deleteVlan
	 * @Description: 删除vlan
	 * @param
	 * @return int
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @createtime jul 23, 2013
	 */
	public int deleteVlan(VlanObj obj);

	/**
	 * 
	 * @Title: delIPAddr
	 * @Description: 删除IP地址
	 * @param
	 * @return int
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-2-13 上午11:44:39
	 */
	public int delIPAddr(VlanObj obj);

	/**
	 * 
	 * @Title: queryIPAddrByVlanId
	 * @Description: 查询IP地址通过vlanid
	 * @param
	 * @return VlanObj
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-2-17 下午5:15:02
	 */
	public VlanObj queryIPAddrByVlanId(VlanObj obj);

}
