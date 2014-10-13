package com.sitech.basd.resource.dao.switchBoard;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.sitech.basd.resource.domain.switchBoard.SwitchObj;
import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;

@Repository("SwitchDao")
public class SwitchDaoImpl extends BaseDao implements SwitchDao {

	/**
	 * 
	 * @Title: SwitchList
	 * @Description: 查询机框列表
	 * @param
	 * @return List<Switch>
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @createtime jul 19, 2013
	 */
	@SuppressWarnings("unchecked")
	public List<SwitchObj> querySwitchList(SwitchObj obj) {
		List<SwitchObj> list = new ArrayList<SwitchObj>();
		try {

			if (obj.getPagination() != null) {
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj.getPagination().setTotalCount(
						((Integer) getSqlMap().queryForObject("switch.queryForCount", obj))
								.intValue()); // 分页查询的基本信息 }
			}
			list = getSqlMap().queryForList("switch.querySwitchList", obj);
		} catch (Exception sqlException) {
			LogHelper.error("switch.querySwitchList:" + sqlException.getMessage()
					+ getClass().getName());
		}
		return list;
	}

	/**
	 * 
	 * @Title: insertSwitch
	 * @Description: 插入交换机
	 * @param
	 * @return int
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @createtime jul 19, 2013
	 */
	public int insertSwitch(SwitchObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().insert("switch.insertSwitch", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception e) {
			ret = -1;
			LogHelper.error("switch.insertSwitch:" + e.getMessage());
		}
		return ret;
	}

	/**
	 * 
	 * @Title: updateSwitch
	 * @Description: 更新交换机
	 * @param
	 * @return int
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @createtime jul 19, 2013
	 */
	public int updateFrame(SwitchObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().update("switch.updateSwitch", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception e) {
			ret = -1;
			LogHelper.error("mFrame.updateSwitch:" + e.getMessage());
		}
		return ret;
	}

	/**
	 * 
	 * @Title: deleteSwitch
	 * @Description: 删除交换机
	 * @param
	 * @return int
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @createtime jul 19, 2013
	 */
	public int deleteFrame(SwitchObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().delete("switch.deleteSwitch", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception e) {
			ret = -1;
			LogHelper.error("switch.deleteSwitch:" + e.getMessage());
		}
		return ret;
	}

}
