package com.sitech.basd.resource.dao.switchBoard;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.sitech.basd.resource.domain.switchBoard.VlanObj;
import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;

@Repository("vlanDao")
public class VlanDaoImpl extends BaseDao implements VlanDao {

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
		List<VlanObj> list = new ArrayList<VlanObj>();
		try {

			if (obj.getPagination() != null) {
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj.getPagination().setTotalCount(
						((Integer) getSqlMap().queryForObject("vlan.queryForCount", obj))
								.intValue()); // 分页查询的基本信息 }
			}
			list = getSqlMap().queryForList("vlan.queryVlanList", obj);
		} catch (Exception sqlException) {
			LogHelper.error("vlan.querySwitchList:" + sqlException.getMessage()
					+ getClass().getName());
		}
		return list;
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
		int ret = 0;
		try {
			Object o = getSqlMap().insert("vlan.insertVlan", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception e) {
			ret = -1;
			LogHelper.error("vlan.insertVlan:" + e.getMessage());
		}
		return ret;
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
		int ret = 0;
		try {
			Object o = getSqlMap().update("vlan.updateVlan", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception e) {
			ret = -1;
			LogHelper.error("vlan.updateVlan:" + e.getMessage());
		}
		return ret;
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
		int ret = 0;
		try {
			Object o = getSqlMap().delete("vlan.deleteVlan", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception e) {
			ret = -1;
			LogHelper.error("vlan.deleteVlan:" + e.getMessage());
		}
		return ret;
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
		int ret = 0;
		try {
			Object o = getSqlMap().delete("vlan.delIPAddr", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception e) {
			ret = -1;
			LogHelper.error("vlan.delIPAddr:" + e.getMessage());
		}
		return ret;
	}

	@Override
	public VlanObj queryIPAddrByVlanId(VlanObj obj) {
		VlanObj vlanObj = new VlanObj();
		try {
			vlanObj = (VlanObj) getSqlMap().queryForObject(
					"vlan.queryIPAddrByVlanId", obj);
		} catch (Exception e) {
			LogHelper.error("vlan.queryIPAddrByVlanId:" + e.getMessage());
		}
		return vlanObj;
	}

}
