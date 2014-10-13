package com.sitech.basd.sxcloud.rsmu.dao.deploy;

import java.util.ArrayList;
import java.util.List;

import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.domain.deploy.TbBusiDeployExampleObj;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;

public class TbBusiDeployExampleDaoImpl extends BaseDao implements TbBusiDeployExampleDao {

	/**
	 * @Title:删除部署实例管理信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author yangwenchao
	 * @version 1.0
	 */
	public int deleteByObj(TbBusiDeployExampleObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().delete("TbBusiDeployExample.deleteByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("TbBusiDeployExample.deleteByObj:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return ret;
	}

	/**
	 * @Title:插入部署实例管理信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author yangwenchao
	 * @version 1.0
	 */
	public int insertByObj(TbBusiDeployExampleObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().insert("TbBusiDeployExample.insertByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("TbBusiDeployExample.insertByObj:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return ret;
	}

	/**
	 * @Title:查询出具体部署实例管理信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author yangwenchao
	 * @version 1.0
	 */
	public TbBusiDeployExampleObj queryByObj(TbBusiDeployExampleObj obj) {
		List lst = null;
		TbBusiDeployExampleObj tempObj = null;
		lst = queryForListByObj(obj);
		if (lst != null && lst.size() > 0) {
			tempObj = (TbBusiDeployExampleObj) lst.get(0);
		}
		return tempObj;
	}

	/**
	 * @Title:根据部署实例管理信息查询匹配的所有部署实例管理信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author yangwenchao
	 * @version 1.0
	 */
	public List queryForListByObj(TbBusiDeployExampleObj obj) {
		List lst = null;
		try {
			if (obj.getPagination() != null) {
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj.getPagination().setTotalCount(
						((Integer) getSqlMap().queryForObject(
								"TbBusiDeployExample.queryByObjForCount", obj)).intValue());
			}
			lst = getSqlMap().queryForList("TbBusiDeployExample.queryForListByObj", obj);
		} catch (Exception sqlexception) {
			sqlexception.printStackTrace();
			LogHelper.error("TbBusiDeployExample.queryForListByObj:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return lst;
	}

	/**
	 * 
	 * @Title: queryForBizListByObj
	 * @Description: 业务视图展示部署实例
	 * @param
	 * @return List
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime Mar 7, 2013 3:01:24 PM
	 */
	@SuppressWarnings("unchecked")
	public List queryForBizListByObj(TbBusiDeployExampleObj obj) {
		List lst = null;
		try {
			if (obj.getPagination() != null) {
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj.getPagination().setTotalCount(
						((Integer) getSqlMap().queryForObject(
								"TbBusiDeployExample.queryForBizListByObjForCount", obj))
								.intValue());
			}
			lst = getSqlMap().queryForList("TbBusiDeployExample.queryForBizListByObj", obj);
		} catch (Exception sqlexception) {
			sqlexception.printStackTrace();
			LogHelper.error("TbBusiDeployExample.queryForBizListByObj:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return lst;
	}

	public List queryListIDByObj(TbBusiDeployExampleObj obj) {
		List lst = null;
		try {
			lst = getSqlMap().queryForList("TbBusiDeployExample.queryListIDByObj", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("TbBusiDeployExample.queryListIDByObj:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return lst;
	}

	/**
	 * @Title:更新部署实例管理信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author yangwenchao
	 * @version 1.0
	 */
	public int updateByObj(TbBusiDeployExampleObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().update("TbBusiDeployExample.updateByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("TbSysApp.updateByObj:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return ret;
	}

	public int StartAndStopByObj(TbBusiDeployExampleObj obj) {
		// TODO Auto-generated method stub
		int ret = 0;
		try {
			Object o = getSqlMap().update("TbBusiDeployExample.startandstopByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("TbSysApp.startandstopByObj:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return ret;
	}

	public int deployRequest(TbBusiDeployExampleObj obj) {
		// TODO Auto-generated method stub
		int ret = 0;
		try {
			Object o = getSqlMap().update("TbBusiDeployExample.deployRequestByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("TbBusiDeployExample.deployRequestByObj:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return ret;
	}

	/**
	 * @Title:更改部署或卸载的完成百分比
	 * @Copyright: Copyright (c) 201009
	 * @Company: si-tech
	 * @author wangzca
	 * @version 1.0
	 */
	public void updateDEPLOY_PERCENT(TbBusiDeployExampleObj obj) {
		try {
			getSqlMap().update("TbBusiDeployExample.updateDEPLOY_PERCENT", obj);
		} catch (Exception sqlexception) {
			LogHelper
					.error("TbBusiDeployExample.updateDEPLOY_PERCENT:" + sqlexception.getMessage());
		}
	}

	/**
	 * @Title:更改启动或停止的完成百分比
	 * @Copyright: Copyright (c) 201009
	 * @Company: si-tech
	 * @author wangzca
	 * @version 1.0
	 */
	@SuppressWarnings("unchecked")
	public void updateSTART_STOP_PERCENT(TbBusiDeployExampleObj obj) {
		try {
			getSqlMap().update("TbBusiDeployExample.updateSTART_STOP_PERCENT", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("TbBusiDeployExample.updateSTART_STOP_PERCENT:"
					+ sqlexception.getMessage());
		}
	}

	/**
	 * 
	 * @Title: updateExampleIsRestart
	 * @Description: 更新部署实例-是否重新启动
	 * @param
	 * @return void
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-4-1 上午10:18:44
	 */
	public void updateExampleIsRestart(TbBusiDeployExampleObj obj) {
		try {
			getSqlMap().update("TbBusiDeployExample.updateExampleIsRestart", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("TbBusiDeployExample.updateExampleIsRestart:"
					+ sqlexception.getMessage());
		}
	}

	/**
	 * 
	 * @Title: updateExampleIsBackup
	 * @Description: 更新部署实例--是否全量备份
	 * @param
	 * @return void
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-4-1 上午10:20:45
	 */
	public void updateExampleIsBackup(TbBusiDeployExampleObj obj) {
		try {
			getSqlMap().update("TbBusiDeployExample.updateExampleIsBackup", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("TbBusiDeployExample.updateExampleIsBackup:"
					+ sqlexception.getMessage());
		}
	}

	/**
	 * 
	 * @Title: queryOnlineExampleList
	 * @Description: 查询部署实例列表
	 * @param
	 * @return void
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-4-1 上午10:21:08
	 */
	public List<TbBusiDeployExampleObj> queryOnlineExampleList(TbBusiDeployExampleObj obj) {
		return null;
	}

	@Override
	public List<TbBusiDeployExampleObj> queryByObjJoinTaskList(TbBusiDeployExampleObj obj) {
		List<TbBusiDeployExampleObj> lst = new ArrayList<TbBusiDeployExampleObj>();
		try {
			if (obj.getPagination() != null) {
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj.getPagination().setTotalCount(
						((Integer) getSqlMap().queryForObject(
								"TbBusiDeployExample.queryByObjJoinTaskListCount", obj)).intValue());
			}
			lst = getSqlMap().queryForList("TbBusiDeployExample.queryByObjJoinTaskList", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("TbBusiDeployExample.queryByObjJoinTaskList:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;
	}

	@Override
	public int countExample(TbBusiDeployExampleObj tbBusiDeployExampleObj) {
		int ret = -1;
		try {
			Object obj = getSqlMap().queryForObject("TbBusiDeployExample.countExample",
					tbBusiDeployExampleObj);
			if (obj != null) {
				ret = (Integer) obj;
			}
		} catch (Exception e) {
			LogHelper.error("TbBusiDeployExample.countExample: " + e.getMessage()
					+ e.getClass().getName());
		}
		return ret;
	}
	
	/**
	 * 
	 * @Title: queryAppSonNum
	 * @Description: 查询基准应用下部署实例的个数
	 * @param
	 * @return String
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime 2013-8-27 下午7:32:54
	 */
	public int queryAppSonNum(TbBusiDeployExampleObj tbBusiDeployExampleObj){
		int ret = -1;
		try {
			Object obj = getSqlMap().queryForObject("TbBusiDeployExample.queryDepExampleNumOfApp",
					tbBusiDeployExampleObj);
			if (obj != null) {
				ret = (Integer) obj;
			}
		} catch (Exception e) {
			LogHelper.error("TbBusiDeployExample.queryDepExampleNumOfApp: " + e.getMessage()
					+ e.getClass().getName());
		}
		return ret;
	}
}
