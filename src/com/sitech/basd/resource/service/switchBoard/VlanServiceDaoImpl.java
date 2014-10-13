package com.sitech.basd.resource.service.switchBoard;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sitech.basd.resource.dao.switchBoard.VlanDao;
import com.sitech.basd.resource.domain.switchBoard.VlanObj;

@Service("vlanService")
public class VlanServiceDaoImpl implements VlanService {

	@Autowired
	private VlanDao vlanDao;

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
	@Override
	public List<VlanObj> queryVlanList(VlanObj obj) {
		return vlanDao.queryVlanList(obj);
	}

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
	@Override
	public int insertVlan(VlanObj obj) {
		return vlanDao.insertVlan(obj);
	}

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
	@Override
	public int updateVlan(VlanObj obj) {
		return vlanDao.updateVlan(obj);
	}

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
	@Override
	public int deleteVlan(VlanObj obj) {
		return vlanDao.deleteVlan(obj);
	}

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
	@Override
	public int delIPAddr(VlanObj obj) {
		return vlanDao.delIPAddr(obj);
	}

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
	@Override
	public VlanObj queryIPAddrByVlanId(VlanObj obj) {
		return vlanDao.queryIPAddrByVlanId(obj);
	}

}
