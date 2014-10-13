package com.sitech.basd.resource.dao.switchBoard;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.sitech.basd.resource.domain.switchBoard.SwitchInterfaceObj;
import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;

@Repository("switchInterfaceDao")
public class SwitchInterfaceDaoImpl extends BaseDao implements SwitchInterfaceDao {
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
	@Override
	public List<SwitchInterfaceObj> queryInterfaceList(SwitchInterfaceObj obj) {
		List<SwitchInterfaceObj> list = new ArrayList<SwitchInterfaceObj>();
		try {

			if (obj.getPagination() != null) {
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj.getPagination()
						.setTotalCount(
								((Integer) getSqlMap().queryForObject(
										"switchInterface.queryForCount", obj)).intValue()); // 分页查询的基本信息
																							// }
			}
			list = getSqlMap().queryForList("switchInterface.queryInterfaceList", obj);
		} catch (Exception sqlException) {
			LogHelper.error("switchInterface.queryInterfaceList:" + sqlException.getMessage()
					+ getClass().getName());
		}
		return list;
	}

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
	@Override
	public int insertInterface(SwitchInterfaceObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().insert("switchInterface.insertInterface", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception e) {
			ret = -1;
			LogHelper.error("switchInterface.insertInterface:" + e.getMessage());
		}
		return ret;
	}

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
	@Override
	public int updateInterface(SwitchInterfaceObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().update("switchInterface.updateInterface", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception e) {
			ret = -1;
			LogHelper.error("switchInterface.updateInterface:" + e.getMessage());
		}
		return ret;
	}

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
	@Override
	public int deleteInterface(SwitchInterfaceObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().delete("switchInterface.deleteInterface", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception e) {
			ret = -1;
			LogHelper.error("switchInterface.deleteInterface:" + e.getMessage());
		}
		return ret;
	}

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
	@Override
	public int updateVlanId(SwitchInterfaceObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().update("switchInterface.updateVlanId",
					obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception e) {
			ret = -1;
			LogHelper.error("switchInterface.updateVlanId:" + e.getMessage());
		}
		return ret;
	}

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
	@Override
	public int updateVlanIdNull(SwitchInterfaceObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().update("switchInterface.updateVlanIdNull",
					obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception e) {
			ret = -1;
			LogHelper.error("switchInterface.updateVlanIdNull:"
					+ e.getMessage());
		}
		return ret;
	}

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
	@Override
	public int deleteVlanByInterfaceId(SwitchInterfaceObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().update("switchInterface.deleteVlanByInterfaceId",
					obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception e) {
			ret = -1;
			LogHelper.error("switchInterface.deleteVlanByInterfaceId:" + e.getMessage());
		}
		return ret;
	}
	
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
	@Override
	public SwitchInterfaceObj queryVlanIDandInterName(SwitchInterfaceObj obj) {
		SwitchInterfaceObj intObj = new SwitchInterfaceObj();
		try {
			intObj = (SwitchInterfaceObj) getSqlMap().queryForObject("switchInterface.queryVlanIDandInterName", obj);
		} catch (Exception e) {
			LogHelper.error("switchInterface.queryVlanIDandInterName:" + e.getMessage());
		}
		return intObj;
	}
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
	@Override
	public List<SwitchInterfaceObj> queryInterList(SwitchInterfaceObj obj) {
		List<SwitchInterfaceObj> list = new ArrayList<SwitchInterfaceObj>();
		try {
			list = getSqlMap().queryForList("switchInterface.queryInterList", obj);
		} catch (Exception sqlException) {
			LogHelper.error("switchInterface.queryInterList:" + sqlException.getMessage()
					+ getClass().getName());
		}
		return list;
	}
}
