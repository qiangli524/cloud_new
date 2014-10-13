package com.sitech.basd.component.dao.dhcp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.sitech.basd.component.domain.dhcp.DhcpObj;
import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;

@Repository("dhcpDao")
public class DhcpDaoImpl extends BaseDao implements DhcpDao{

	/**
	 * @Title: insertDhcp
	 * @Description: 增加dhcp
	 * @param
	 * @return int
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-8-16 下午3:50:31
	 */
	@Override
	public int insertDhcp(DhcpObj dhcpObj) {
		int ret = 0;
		try {
			Object obj = getSqlMap().insert("dhcp.insertDhcp", dhcpObj);
			if (obj != null) {
				ret = (Integer)obj;
			}
		} catch (Exception e) {
			LogHelper.error("dhcp.insertDhcp: " + e.getMessage());
			ret = -1;
		}
		return ret;
	}

	/**
	 * @Title: deleteDhcp
	 * @Description: 删除dhcp
	 * @param
	 * @return int
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-8-16 下午3:50:43
	 */
	@Override
	public int deleteDhcp(DhcpObj dhcpObj) {
		int ret = 0;
		try {
			Object obj = getSqlMap().delete("dhcp.deleteDhcp", dhcpObj);
			if (obj != null) {
				ret = (Integer)obj;
			}
		} catch (Exception e) {
			LogHelper.error("dhcp.deleteDhcp: " + e.getMessage());
		}
		return ret;
	}

	/**
	 * @Title: updateDhcp
	 * @Description: 修改dhcp
	 * @param
	 * @return int
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-8-16 下午3:50:55
	 */
	@Override
	public int updateDhcp(DhcpObj dhcpObj) {
		int ret = 0;
		try {
			Object obj = getSqlMap().update("dhcp.updateDhcp", dhcpObj);
			if (obj != null) {
				ret = (Integer)obj;
			}
		} catch (Exception e) {
			LogHelper.error("dhcp.updateDhcp: " + e.getMessage());
			ret = -1;
		}
		return ret;
	}

	/**
	 * @Title: queryForList
	 * @Description: 查询dhcp
	 * @param
	 * @return List<DhcpObj>
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-8-16 下午3:51:05
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<DhcpObj> queryForList(DhcpObj dhcpObj) {
		List<DhcpObj> list = new ArrayList<DhcpObj>();
		try {
			if (dhcpObj.getPagination() != null) {
				dhcpObj.setFIRSTROWNUM(dhcpObj.getPagination().getFirstRownum());
				dhcpObj.setPAGESIZE(dhcpObj.getPagination().getPageSize());
				dhcpObj.getPagination().setTotalCount(
						((Integer) getSqlMap().queryForObject("dhcp.countDhcp", dhcpObj))
								.intValue());
			}
			list = getSqlMap().queryForList("dhcp.queryForList", dhcpObj);
		} catch (Exception e) {
			LogHelper.error("dhcp.queryForList: " + e.getMessage());
		}
		return list;
	}

	/**
	 * @Title: countDhcp
	 * @Description:统计dhcp
	 * @param
	 * @return int
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-8-16 下午3:51:14
	 */
	@Override
	public int countDhcp(DhcpObj dhcpObj) {
		int ret = -1;
		try {
			Object obj = getSqlMap().queryForObject("dhcp.countDhcp", dhcpObj);
			if (obj != null) {
				ret = ((Integer)obj).intValue();
			}
		} catch (Exception e) {
			LogHelper.error("dhcp.countDhcp: " + e.getMessage());
		}
		return ret;
	}

}
