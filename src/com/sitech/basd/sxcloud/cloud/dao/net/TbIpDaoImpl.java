package com.sitech.basd.sxcloud.cloud.dao.net;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientCallback;

import com.ibatis.sqlmap.client.SqlMapExecutor;
import com.sitech.basd.sxcloud.cloud.domain.net.TbCloud2IpInfoObj;
import com.sitech.basd.sxcloud.cloud.domain.net.TbCloud2NetInfoObj;
import com.sitech.basd.sxcloud.cloud.web.showresource.form.AlarmHostStatistics;
import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;
import com.sitech.basd.sxcloud.util.UUIDGenerator;

@SuppressWarnings("all")
public class TbIpDaoImpl extends BaseDao implements TbIpDao {
	/**
	 * @Title:查询已有IP列表
	 * @Copyright: Copyright (c) 2011-12-2
	 * @Company: si-tech
	 * @author liys
	 * @version 1.0
	 */
	public List<TbCloud2IpInfoObj> queryForListByObj(TbCloud2IpInfoObj obj) {
		List lst = null;
		try {
			if (obj.getPagination() != null) {
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj.getPagination().setTotalCount(
						((Integer) getSqlMap().queryForObject("TbIp.queryByObjForCount", obj))
								.intValue());
			}
			lst = getSqlMap().queryForList("TbIp.queryForListByObj", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("TbIp.queryForListByObj:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return lst;
	}

	/**
	 * @Title:查询已有IP列表
	 * @Copyright: Copyright (c) 2011-12-2
	 * @Company: si-tech
	 * @author liys
	 * @version 1.0
	 */
	@SuppressWarnings("unchecked")
	public List<TbCloud2IpInfoObj> queryIPForList(TbCloud2IpInfoObj obj) {
		List lst = null;
		try {
			lst = getSqlMap().queryForList("TbIp.queryForListByObj", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("TbIp.queryForListByObj:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return lst;
	}

	/**
	 * @Title:创建IP
	 * @Copyright: Copyright (c) 2011-12-2
	 * @Company: si-tech
	 * @author liys
	 * @version 1.0
	 */
	public int insertByObj(TbCloud2IpInfoObj obj) {
		UUIDGenerator uuid = new UUIDGenerator();
		obj.setIP_ID(uuid.getUUID());
		int ret = 0;
		try {
			Object o = getSqlMap().insert("TbIp.insertByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("TbIp.insertByObj:" + sqlexception.getMessage() + getClass().getName());
		}
		return ret;
	}

	/**
	 * @Title:创建多个IP
	 * @Copyright: Copyright (c) 2011-12-2
	 * @Company: si-tech
	 * @author liys
	 * @version 1.0
	 */
	public int insertManyIpByObj(TbCloud2IpInfoObj obj) {
		UUIDGenerator uuid = new UUIDGenerator();
		obj.setIP_ID(uuid.getUUID());
		int ret = 0;
		try {
			Object o = getSqlMap().insert("TbIp.insertByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("TbIp.insertByObj:" + sqlexception.getMessage() + getClass().getName());
		}
		return ret;
	}

	/**
	 * @Title:删除已有IP
	 * @Copyright: Copyright (c) 2011-12-2
	 * @Company: si-tech
	 * @author liys
	 * @version 1.0
	 */
	public int deleteByObj(TbCloud2IpInfoObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().delete("TbIp.deleteByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("TbIp.deleteByObj:" + sqlexception.getMessage() + getClass().getName());
		}
		return ret;
	}

	/**
	 * @Title:查询并返回一个IP对象
	 * @Copyright: Copyright (c) 2011-12-2
	 * @Company: si-tech
	 * @author liys
	 * @version 1.0
	 */
	public TbCloud2IpInfoObj queryByObj(TbCloud2IpInfoObj obj) {
		List lst = null;
		TbCloud2IpInfoObj tempObj = null;
		lst = queryForListByObj(obj);
		if (lst != null && lst.size() > 0) {
			tempObj = (TbCloud2IpInfoObj) lst.get(0);
		}
		return tempObj;
	}

	/**
	 * @Title:更新IP信息
	 * @Copyright: Copyright (c) 2011-12-2
	 * @Company: si-tech
	 * @author liys
	 * @version 1.0
	 */
	public int updateByObj(TbCloud2IpInfoObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().update("TbIp.updateByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("TbIp.updateByObj:" + sqlexception.getMessage() + getClass().getName());
		}
		return ret;
	}

	/**
	 * @Title:查询已有IP列表
	 * @Copyright: Copyright (c) 2011-12-2
	 * @Company: si-tech
	 * @author liys
	 * @version 1.0
	 */
	public List queryForListByNetObj(TbCloud2NetInfoObj obj) {
		List lst = null;
		try {
			lst = getSqlMap().queryForList("TbIp.queryForListByNetObj", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("TbIp.queryForListByNetObj:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return lst;
	}

	/**
	 * @Title:阻塞已有IP
	 * @Copyright: Copyright (c) 2011-12-2
	 * @Company: si-tech
	 * @author wangjian
	 * @version 1.0
	 */
	public int backupByObj(TbCloud2IpInfoObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().update("TbIp.backupByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("TbIp.backupByObj:" + sqlexception.getMessage() + getClass().getName());
		}
		return ret;
	}

	/**
	 * @Title:发布已有IP
	 * @Copyright: Copyright (c) 2011-12-2
	 * @Company: si-tech
	 * @author wangjian
	 * @version 1.0
	 */
	public int issuanceByObj(TbCloud2IpInfoObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().update("TbIp.issuanceByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("TbIp.issuanceByObj:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return ret;
	}

	/**
	 * 
	 * @Title: updateIPStat
	 * @Description: 更新IP状态
	 * @param
	 * @return int
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime Apr 28, 2012 11:41:41 AM
	 */
	public int updateIPStat(TbCloud2IpInfoObj obj) {
		return getSqlMapClientTemplate().update("TbIp.updateIPStat", obj);
	}

	/**
	 * 
	 * @Title: queryForListByIPObj
	 * @Description: 根据条件查询ip
	 * @param
	 * @return List
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime Mar 27, 2013 4:25:20 PM
	 */
	public List queryForListByIPObj(TbCloud2IpInfoObj obj) {
		List lst = null;
		try {
			lst = getSqlMap().queryForList("TbIp.queryForListByIPObj", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("TbIp.queryForListByIPObj:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return lst;
	}

	/**
	 * 查询可用ip
	 * 
	 * @author lipp
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<TbCloud2IpInfoObj> queryAchivableIpList(TbCloud2IpInfoObj obj) {
		List<TbCloud2IpInfoObj> list = new ArrayList<TbCloud2IpInfoObj>();
		try {
			list = getSqlMap().queryForList("TbIp.queryAchivableIpList", obj);
		} catch (Exception e) {
			LogHelper
					.error("TbIp.queryAchivableIpList: " + e.getMessage() + e.getClass().getName());
		}
		return list;
	}

	@Override
	public String findIpAddressById(String id) {
		// TODO Auto-generated method stub
		String ipAdd = null;
		try {
			Object obj = getSqlMap().queryForObject("TbIp.queryIpAddressById", id);
			if (obj != null) {
				ipAdd = (String) obj;
			}
		} catch (Exception e) {
			LogHelper.error("TbIp.queryIpAddressById: " + e.getMessage() + e.getClass().getName());
		}
		return ipAdd;
	}

	@Override
	public String findIdByIpAddress(String ip) {
		// TODO Auto-generated method stub
		String ipId = null;
		try {
			Object obj = getSqlMap().queryForObject("TbIp.findIdByIpAddress", ip);
			if (obj != null) {
				ipId = (String) obj;
			}
		} catch (Exception e) {
			LogHelper.error("TbIp.findIdByIpAddress: " + e.getMessage() + e.getClass().getName());
		}
		return ipId;
	}

	@Override
	public int updateIPByObj(TbCloud2IpInfoObj tbCloud2IpInfoObj) {
		return getSqlMapClientTemplate().update("TbIp.updateIPByObj", tbCloud2IpInfoObj);
	}

	/**
	 * @Title: queryByIpAddress
	 * @Description: 根据ip地址选择ip
	 * @param
	 * @return List<TbCloud2IpInfoObj>
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-9-7 下午5:49:36
	 */
	@Override
	public List<TbCloud2IpInfoObj> queryByIpAddress(TbCloud2IpInfoObj ipInfoObj) {
		List<TbCloud2IpInfoObj> list = new ArrayList<TbCloud2IpInfoObj>();
		try {
			list = getSqlMap().queryForList("TbIp.queryByIpAddress", ipInfoObj);
		} catch (Exception e) {
			LogHelper.error("TbIp.queryByIpAddress: " + e.getMessage() + e.getClass().getName());
		}
		return list;
	}

	/**
	 * @Title: queryForListUseNetIn
	 * @Description: 根据vla_id查询集合
	 * @param
	 * @return List<TbCloud2IpInfoObj>
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-9-13 上午8:24:02
	 */
	@Override
	public List<TbCloud2IpInfoObj> queryForListUseNetIn(TbCloud2IpInfoObj ipInfoObj) {
		List<TbCloud2IpInfoObj> list = new ArrayList<TbCloud2IpInfoObj>();
		try {
			if (ipInfoObj.getPagination() != null) {
				ipInfoObj.setFIRSTROWNUM(ipInfoObj.getPagination().getFirstRownum());
				ipInfoObj.setPAGESIZE(ipInfoObj.getPagination().getPageSize());
				ipInfoObj.getPagination().setTotalCount(
						((Integer) getSqlMap().queryForObject("TbIp.countByUseIn", ipInfoObj))
								.intValue());
			}
			list = getSqlMap().queryForList("TbIp.queryForListUseNetIn", ipInfoObj);
		} catch (Exception e) {
			LogHelper
					.error("TbIp.queryForListUseNetIn: " + e.getMessage() + e.getClass().getName());
		}
		return list;
	}

	/**
	 * @Title: queryIpUsageByVlanId
	 * @Description: 查询vlan下每个ip的利用率
	 * @param
	 * @return List<AlarmHostStatistics>
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-9-14 上午9:48:42
	 */
	@Override
	public List<AlarmHostStatistics> queryIpUsageByVlanId(TbCloud2IpInfoObj ipInfoObj) {
		List<AlarmHostStatistics> list = new ArrayList<AlarmHostStatistics>();
		try {
			list = getSqlMap().queryForList("TbIp.queryIpUsageByVlanId", ipInfoObj);
		} catch (Exception e) {
			LogHelper
					.error("TbIp.queryIpUsageByVlanId: " + e.getMessage() + e.getClass().getName());
		}
		return list;
	}

	/**
	 * @Title: queryIpForTree
	 * @Description: 根据net_id查询对应的ip、虚拟机、业务系统
	 * @param
	 * @return List<Map<String, String>>
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @createtime 2013-10-29 下午16:21
	 */
	public List<Map<String, String>> queryIpForTree(TbCloud2IpInfoObj ipInfoObj) {
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		try {
			if (ipInfoObj.getPagination() != null) {
				ipInfoObj.setFIRSTROWNUM(ipInfoObj.getPagination().getFirstRownum());
				ipInfoObj.setPAGESIZE(ipInfoObj.getPagination().getPageSize());
				ipInfoObj.getPagination().setTotalCount(
						((Integer) getSqlMap()
								.queryForObject("TbIp.queryIpListForCount", ipInfoObj)).intValue());
			}
			list = getSqlMap().queryForList("TbIp.queryIpListForTree", ipInfoObj);
		} catch (Exception e) {
			LogHelper.error("TbIp.queryIpListForTree: " + e.getMessage() + e.getClass().getName());
		}
		return list;
	}

	/**
	 * 
	 * @Title: updateIPStateForBatch
	 * @Description: 批量更新IP地址的使用状态
	 * @param
	 * @return int
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime 2014-5-10 上午11:40:24
	 */
	public int updateIPStateForBatch(final List<TbCloud2IpInfoObj> list) {
		int ret = 0;
		try {
			getSqlMapClientTemplate().execute(new SqlMapClientCallback<TbCloud2IpInfoObj>() {

				@Override
				public TbCloud2IpInfoObj doInSqlMapClient(SqlMapExecutor arg0) throws SQLException {
					arg0.startBatch();
					for (TbCloud2IpInfoObj ip : list) {
						arg0.update("TbIp.updateIPByObj", ip);
					}
					arg0.executeBatch();
					return null;
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
			ret = -1;
			LogHelper.error("TbIp.updateIPByObj: " + e.getMessage() + e.getClass().getName());
		}
		return ret;
	}

	/** (非 Javadoc) 
	* <p>Title: queryARandomByObj</p> 
	* <p>Description: 根据条件查询随机查询出一条记录 </p> 
	* @author wanglei_bj@si-tech.com.cn 
	* @param obj
	* @return 
	* @see com.sitech.basd.sxcloud.cloud.dao.net.TbIpDao#queryARandomByObj(com.sitech.basd.sxcloud.cloud.domain.net.TbCloud2IpInfoObj) 
	*/
	@Override
	public TbCloud2IpInfoObj queryARandomIp(TbCloud2IpInfoObj load_ip) {
		List lst = null;
		TbCloud2IpInfoObj tempObj = null;
		lst = queryForListByObj(load_ip);
		if (lst != null && lst.size() > 0) {
			tempObj = (TbCloud2IpInfoObj) lst.get(0);
		}
		return tempObj;
	}
}
