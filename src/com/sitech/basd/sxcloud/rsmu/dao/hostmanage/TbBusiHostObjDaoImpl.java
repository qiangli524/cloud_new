package com.sitech.basd.sxcloud.rsmu.dao.hostmanage;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.dao.softmanage.TbSysAppDao;
import com.sitech.basd.sxcloud.rsmu.domain.deploy.TbBusiDeployExampleObj;
import com.sitech.basd.sxcloud.rsmu.domain.hostmanage.TbBusiHostConfigObj;
import com.sitech.basd.sxcloud.rsmu.domain.hostmanage.TbBusiHostObj;
import com.sitech.basd.sxcloud.rsmu.domain.hostmanage.TbBusibusiSwitchPortObj;
import com.sitech.basd.sxcloud.rsmu.domain.softmanage.TbSysAppObj;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;

public class TbBusiHostObjDaoImpl extends BaseDao implements TbBusiHostObjDao {
	private TbSysAppDao tbSysAppDao;

	/**
	 * @Title:根据主机部分信息查询匹配的所有主机信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author liys
	 * @version 1.0
	 */
	static Logger log = Logger.getLogger("infoAppender");

	/**
	 * @Title:根据主机部分信息查询匹配的所有主机信息（除去已经生成部署实例的）。
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author zengls
	 * @version 1.0
	 */
	public List queryHostMinusExampleByObjAPPID(TbBusiHostObj obj) {
		List lst = null;

		try {
			lst = getSqlMap().queryForList("TbBusiHost.queryHostMinusExampleByObjAPPID", obj);
		} catch (SQLException sqlexception) {
			sqlexception.printStackTrace();
			LogHelper.error("TbBusiHost.queryHostMinusExampleByObjAPPID:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return lst;
	}

	public List queryForListByObj(TbBusiHostObj obj) {
		List lst = null;

		try {
			if (obj.getPagination() != null) {
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				if (obj.getAPPID() < 1) {
					obj.getPagination().setTotalCount(
							((Integer) getSqlMap().queryForObject("TbBusiHost.queryTbBusiHostByObjForCount", obj))
									.intValue());
					log.info("TbBusiHost.queryTbBusiHostByObjForCount:" + getClass().getName());
				} else {
					obj.getPagination().setTotalCount(
							((Integer) getSqlMap().queryForObject("TbBusiHost.queryTbBusiHostByObjAPPIDForCount", obj))
									.intValue());
					log.info("TbBusiHost.queryTbBusiHostByObjAPPIDForCount:" + getClass().getName());
				}
			}
			if (obj.getAPPID() < 1) {
				lst = getSqlMap().queryForList("TbBusiHost.queryTbBusiHostForListByObj", obj);
			} else {
				lst = getSqlMap().queryForList("TbBusiHost.queryTbBusiHostForListByObjAPPID", obj);
			}
		} catch (SQLException sqlexception) {
			if (obj.getAPPID() < 1) {
				LogHelper.error("TbBusiHost.queryTbBusiHostForListByObj:" + sqlexception.getMessage()
						+ getClass().getName());
			} else {
				LogHelper.error("TbBusiHost.queryTbBusiHostForListByObjAPPID:" + sqlexception.getMessage()
						+ getClass().getName());
			}
		}
		return lst;
	}

	/**
	 * @Title:查询出具体主机信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author liys
	 * @version 1.0
	 */
	public TbBusiHostObj queryByObj(TbBusiHostObj obj) {
		List lst = null;
		TbBusiHostObj tempObj = null;
		lst = queryForListByObj(obj);
		if (lst != null && lst.size() > 0) {
			tempObj = (TbBusiHostObj) lst.get(0);
		}
		return tempObj;
	}

	/**
	 * @Title:更新主机信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author liys
	 * @version 1.0
	 */
	public int updateByObj(TbBusiHostObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().update("TbBusiHost.updateTbBusiHostByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (SQLException sqlexception) {
			ret = -1;
			LogHelper.error("TbBusiHost.updateTbBusiHostByObj:" + sqlexception.getMessage() + getClass().getName());
		}
		return ret;
	}

	/**
	 * @Title:删除主机信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author liys
	 * @version 1.0
	 */
	public int deleteByObj(TbBusiHostObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().delete("TbBusiHost.deleteTbBusiHostByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (SQLException sqlexception) {
			ret = -1;
			LogHelper.error("TbBusiHost.deleteTbBusiHostByObj:" + sqlexception.getMessage() + getClass().getName());
		}
		return ret;
	}

	/**
	 * @Title:插入主机信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author liys
	 * @version 1.0
	 */
	public int insertByObj(TbBusiHostObj obj) {

		int ret = 0;
		try {
			Object o = getSqlMap().insert("TbBusiHost.insertTbBusiHostByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (SQLException sqlexception) {
			ret = -1;
			LogHelper.error("TbBusiHost.insertTbBusiHostByObj:" + sqlexception.getMessage() + getClass().getName());
		}
		return ret;
	}

	public List queryForList_Switch_port(TbBusibusiSwitchPortObj obj) {
		List lst = null;
		try {
			lst = getSqlMap().queryForList("TbBusiHost.queryTbBusibusiSwitchPortObj", obj);
		} catch (SQLException sqlexception) {
			LogHelper.error("TbBusiHost.queryTbBusibusiSwitchPortObj:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return lst;
	}

	public int updateVlanByObj(TbBusiHostObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().update("TbBusiHost.updateTbBusiHostVlanByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (SQLException sqlexception) {
			ret = -1;
			LogHelper.error("TbBusiHost.updateTbBusiHostVlanByObj:" + sqlexception.getMessage() + getClass().getName());
		}
		return ret;
	}

	/**
	 * @Title:根据主机IP查找该主机的KBP_CLASS和DEVICE_ID
	 * @Copyright: Copyright (c) 201011
	 * @Company: si-tech
	 * @author wangzca
	 * @version 1.0
	 */
	public TbBusiHostObj queryKbpClassAndDeviceId(TbBusiHostObj obj) {
		List lst = null;
		TbBusiHostObj tempObj = null;
		try {
			log.info("TbBusiHost.queryKbpClassAndDeviceId:" + getClass().getName());
			lst = getSqlMap().queryForList("TbBusiHost.queryKbpClassAndDeviceId", obj);
		} catch (SQLException sqlexception) {
			LogHelper.error("TbBusiHost.queryKbpClassAndDeviceId:" + sqlexception.getMessage() + getClass().getName());
		}
		if (lst != null && lst.size() > 0) {
			tempObj = (TbBusiHostObj) lst.get(0);
		}
		return tempObj;
	}

	/**
	 * @Title:根据主机KBP_CLASS和DEVICE_ID查找该主机的CPU个数
	 * @Copyright: Copyright (c) 201011
	 * @Company: si-tech
	 * @author wangzca
	 * @version 1.0
	 */
	public TbBusiHostObj queryCpu_Kpi(TbBusiHostObj obj) {
		List lst = null;

		try {
			log.info("TbBusiHost.queryCpu_Kpi:" + getClass().getName());
			lst = getSqlMap().queryForList("TbBusiHost.queryCpu_Kpi", obj);
			if (lst != null && lst.size() > 0) {
				obj = (TbBusiHostObj) lst.get(0);
			}
		} catch (SQLException sqlexception) {
			LogHelper.error("TbBusiHost.queryCpu_Kpi:" + sqlexception.getMessage() + getClass().getName());
		}
		return obj;
	}

	/**
	 * @Title:根据主机KBP_CLASS和DEVICE_ID查找该主机的内存大小
	 * @Copyright: Copyright (c) 201011
	 * @Company: si-tech
	 * @author wangzca
	 * @version 1.0
	 */
	public TbBusiHostObj queryMemory_Kpi(TbBusiHostObj obj) {
		List lst = null;

		try {
			log.info("TbBusiHost.queryMemory_kpi:" + getClass().getName());
			lst = getSqlMap().queryForList("TbBusiHost.queryMemory_kpi", obj);
			if (lst != null && lst.size() > 0) {
				obj = (TbBusiHostObj) lst.get(0);
			}
		} catch (SQLException sqlexception) {
			LogHelper.error("TbBusiHost.queryMemory_kpi:" + sqlexception.getMessage() + getClass().getName());
		}
		return obj;
	}

	/**
	 * @Title:审批应用部署时查询可用的主机IP
	 * @Copyright: Copyright (c) 20120316
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public List queryAppIp(TbBusiDeployExampleObj obj) {
		List lst = null;

		try {
			if (obj.getPagination() != null) {
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj.getPagination().setTotalCount(
						((Integer) getSqlMap().queryForObject("TbBusiHost.queryAppIpForCount", obj)).intValue());
			}
			log.info("TbBusiHost.queryAppIp:" + getClass().getName());
			lst = getSqlMap().queryForList("TbBusiHost.queryAppIp", obj);
		} catch (SQLException sqlexception) {
			LogHelper.error("TbBusiHost.queryAppIp:" + sqlexception.getMessage() + getClass().getName());
		}
		return lst;
	}

	/**
	 * @Title:根据IP查询Id
	 * @Copyright: Copyright (c) 20120316
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public List queryIDByIP(TbBusiHostObj obj) {
		List lst = null;

		try {
			lst = getSqlMap().queryForList("TbBusiHost.queryIDByIP", obj);
		} catch (SQLException sqlexception) {
			LogHelper.error("TbBusiHost.queryIDByIP:" + sqlexception.getMessage() + getClass().getName());
		}
		return lst;
	}

	/**
	 * @Title:通过excel导入批量插入数据
	 * @Copyright: Copyright (c) 201305
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public int insertByimport(List<TbBusiHostObj> hostList, List<TbBusiHostConfigObj> userList) {
		int ret = 0;
		SqlMapClient smc = this.getSqlMap();
		TbBusiHostObj tempObj = new TbBusiHostObj();
		try {
			smc.startTransaction();
			smc.startBatch();
			for (TbBusiHostObj obj : hostList) {
				smc.insert("TbBusiHost.insertTbBusiHostByObj", obj);
			}
			for (TbBusiHostConfigObj obj : userList) {
				tempObj.setIP(obj.getIP());
				List<TbBusiHostObj> lst = queryIDByIP(tempObj);
				tempObj = lst.get(0);
				obj.setHOSTID(tempObj.getID());
				obj.setHOSTNAME(tempObj.getHOSTNAME());
				smc.insert("TbBusiHostConfig.insertByObj", obj);
				// 如果用户配置中选择自动生成部署实例，则生成实例
				if (obj.getIfexample() != null && obj.getIfexample().equals("0")) {
					// 基准应用信息
					TbSysAppObj tbSysAppObj = new TbSysAppObj();
					tbSysAppObj.setID(Integer.parseInt(obj.getAPPID()));
					TbSysAppObj sysAppObj = tbSysAppDao.queryByObj(tbSysAppObj);
					// sysAppObj.setDEPLOYPATH(theForm.getDeploy_path().replace(
					// "\r\n", ""));
					// appService.updateByObj(sysAppObj);

					TbBusiDeployExampleObj tdeObj = new TbBusiDeployExampleObj();
					tdeObj.setAPPID(tbSysAppObj.getID());
					tdeObj.setDEPLOYPATH(obj.getDeploy_path());
					if (obj.getIsCredituser() == 0) {
						tdeObj.setHostUsername(obj.getHOSTUSERNAME());
					} else {
						tdeObj.setHostUsername(obj.getCREDITUSER());
					}
					tdeObj.setIsbackup(0);
					tdeObj.setIsrestart(0);
					tdeObj.setHOSTID(obj.getHOSTID());
					tdeObj.setSYS_ID(sysAppObj.getSYS_ID());
					smc.insert("TbBusiDeployExample.insertByObj", tdeObj);
				}

			}
			smc.executeBatch();
			ret = 1;
		} catch (Exception e) {
			ret = -1;
			LogHelper.error("TbBusiHost.insertByimport:" + e.getMessage() + getClass().getName());
		} finally {
			try {
				smc.commitTransaction();
				smc.endTransaction();
			} catch (SQLException e) {
				LogHelper.error("TbBusiHost.insertByimport:" + e.getMessage() + getClass().getName());
			}
		}
		return ret;
	}

	/**
	 * 
	 * @Title: updateHostState
	 * @Description: 更新主机的状态（供应用部署使用）
	 * @param
	 * @return int
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Jul 10, 2013 3:45:43 PM
	 */
	public int updateHostState(TbBusiHostObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().update("TbBusiHost.updateHostState", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (SQLException sqlexception) {
			ret = -1;
			LogHelper.error("TbBusiHost.updateHostState:" + sqlexception.getMessage() + getClass().getName());
		}
		return ret;
	}

	public void setTbSysAppDao(TbSysAppDao tbSysAppDao) {
		this.tbSysAppDao = tbSysAppDao;
	}

	@Override
	public int countBusiHost(TbBusiHostObj tbBusiHostObj) {
		int ret = -1;
		try {
			Object obj = getSqlMap().queryForObject("TbBusiHost.countBusiHost", tbBusiHostObj);
			if (obj != null) {
				ret = (Integer) obj;
			}
		} catch (Exception e) {
			LogHelper.error("TbBusiHost.countBusiHost: " + e.getMessage() + e.getClass().getName());
		}
		return ret;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TbBusiHostObj> queryAllMappingsByObj(TbBusiHostObj tbBusiHostObj) {
		List<TbBusiHostObj> list = new ArrayList<TbBusiHostObj>();
		try {
			if (tbBusiHostObj.getPagination() != null) {
				tbBusiHostObj.setFIRSTROWNUM(tbBusiHostObj.getPagination().getFirstRownum());
				tbBusiHostObj.setPAGESIZE(tbBusiHostObj.getPagination().getPageSize());
				tbBusiHostObj.getPagination().setTotalCount(
						((Integer) getSqlMap().queryForObject("TbBusiHost.countAllMappings", tbBusiHostObj))
								.intValue());
			}
			list = getSqlMap().queryForList("TbBusiHost.queryAllMappingsByObj", tbBusiHostObj);
		} catch (Exception e) {
			LogHelper.error("TbBusiHost.queryAllMappingsByObj: " + e.getMessage() + e.getClass().getName());
		}
		return list;
	}

	@Override
	public int countAll(TbBusiHostObj tbBusiHostObj) {
		int ret = -1;
		try {
			Object obj = getSqlMap().queryForObject("TbBusiHost.countAll", tbBusiHostObj);
			if (obj != null) {
				ret = (Integer) obj;
			}
		} catch (Exception e) {
			LogHelper.error("TbBusiHost.countAll: " + e.getMessage() + e.getClass().getName());
		}
		return ret;
	}
}
