package com.sitech.basd.sxcloud.rsmu.dao.softmanage;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.domain.softmanage.TbSysAppObj;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;

public class TbSysAppDaoImpl extends BaseDao implements TbSysAppDao {

	/**
	 * @Title:删除应用信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author yangwenchao
	 * @version 1.0
	 */
	public int deleteByObj(TbSysAppObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().delete("TbSysApp.deleteByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (SQLException sqlexception) {
			ret = -1;
			LogHelper.error("TbSysApp.deleteByObj:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return ret;
	}

	/**
	 * @Title:插入应用信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author yangwenchao
	 * @version 1.0
	 */
	public int insertByObj(TbSysAppObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().insert("TbSysApp.insertByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (SQLException sqlexception) {
			ret = -1;
			LogHelper.error("TbSysApp.insertByObj:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return ret;
	}

	/**
	 * @Title:查询出具体应用信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author yangwenchao
	 * @version 1.0
	 */
	public TbSysAppObj queryByObj(TbSysAppObj obj) {
		List lst = null;
		TbSysAppObj tempObj = null;
		lst = queryForListByObj(obj);
		if (lst != null && lst.size() > 0) {
			tempObj = (TbSysAppObj) lst.get(0);
		}
		return tempObj;
	}

	/**
	 * @Title:根据应用部分信息查询匹配的所有应用信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author yangwenchao
	 * @version 1.0
	 */
	public List queryForListByObj(TbSysAppObj obj) {
		List lst = new ArrayList();
		try {
			if (obj.getPagination() != null) {
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj.getPagination().setTotalCount(
						((Integer) getSqlMap().queryForObject("TbSysApp.queryByObjForCount", obj))
								.intValue());
			}
			lst = getSqlMap().queryForList("TbSysApp.queryForListByObj", obj);
		} catch (SQLException sqlexception) {
			LogHelper.error("TbSysApp.queryForListByObj:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return lst;
	}

	/**
	 * @Title:更新应用信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author yangwenchao
	 * @version 1.0
	 */
	public int updateByObj(TbSysAppObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().update("TbSysApp.updateByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (SQLException sqlexception) {
			ret = -1;
			LogHelper.error("TbSysApp.updateByObj:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return ret;
	}

	/**
	 * @Title:启动或停止服务状态
	 * @Copyright: Copyright (c) 201008
	 * @Company: si-tech
	 * @author wangzca
	 * @version 1.0
	 */

	public int StartAndStopByObj(TbSysAppObj obj) {
		// TODO Auto-generated method stub
		int ret = 0;
		try {
			Object o = getSqlMap().update("TbSysApp.startandstopByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (SQLException sqlexception) {
			ret = -1;
			LogHelper.error("TbSysApp.startandstopByObj:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return ret;
	}

	/**
	 * @Title:更新捕获状态
	 * @Copyright: Copyright (c) 201008
	 * @Company: si-tech
	 * @author wangzca
	 * @version 1.0
	 */
	public int catchRequestByObj(TbSysAppObj obj) {
		// TODO Auto-generated method stub
		int ret = 0;
		try {
			Object o = getSqlMap().update("TbSysApp.catchRequestByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (SQLException sqlexception) {
			ret = -1;
			LogHelper.error("TbSysApp.catchRequestByObj:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return ret;
	}

	/**
	 * @Title:更改应用部署状态
	 * @Copyright: Copyright (c) 201008
	 * @Company: si-tech
	 * @author wangzca
	 * @version 1.0
	 */
	public int deployRequest(TbSysAppObj obj) {
		// TODO Auto-generated method stub
		int ret = 0;
		try {
			Object o = getSqlMap().update("TbSysApp.deployRequestByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (SQLException sqlexception) {
			ret = -1;
			LogHelper.error("TbSysApp.deployRequestByObj:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return ret;
	}

	/**
	 * @Title:查询出当前页面的应用信息
	 * @Copyright: Copyright (c) 201007
	 * @Company: si-tech
	 * @author yangwenchao
	 * @version 1.0
	 */

	public List queryListIDByObj(TbSysAppObj obj) {
		List lst = null;
		try {
			lst = getSqlMap().queryForList("TbSysApp.queryListIDByObj", obj);
		} catch (SQLException sqlexception) {
			LogHelper.error("TbSysApp.queryListIDByObj:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return lst;
	}

	/**
	 * 
	 * @Title: updateOnlinePath
	 * @Description: 更新上线或回滚文件列表
	 * @param
	 * @return int
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-4-15 下午2:38:47
	 */
	public int updateOnlinePath(TbSysAppObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().update("TbSysApp.updateOnlinePath", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (SQLException sqlexception) {
			ret = -1;
			LogHelper.error("TbSysApp.updateOnlinePath:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return ret;
	}

	/**
	 * 
	 * @Title: queryForSheet
	 * @Description:导入主机数据前下载模板查询一些基准应用的基本信息写到excel的sheet页中
	 * @author duangh
	 * @date Jun 15, 2013 6:13:59 PM
	 * @return
	 */
	@Override
	public List<Map<String, String>> queryForSheet() {
		List lst = null;
		try {
			lst = getSqlMap().queryForList("TbSysApp.queryForSheet");
		} catch (SQLException sqlexception) {
			LogHelper.error("TbSysApp.queryForSheet:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return lst;
	}

	/**
	 * 根据id集合获取所有的基准应用
	 * 
	 * @author lipengpeng
	 * @param appIds
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<TbSysAppObj> queryForListByIds(String appid) {
		List<TbSysAppObj> list = null;
		try {
			list = getSqlMap().queryForList("TbSysApp.queryForListByIds", appid);
		} catch (Exception e) {
			LogHelper.error("TbSysApp.queryForListByIds: " + e.getMessage()
					+ e.getClass().getName());
		}
		return list;
	}

	@Override
	public int countApp(TbSysAppObj tbSysAppObj) {
		int ret = -1;
		try {
			Object obj = getSqlMap().queryForObject("TbSysApp.countApp", tbSysAppObj);
			if (obj != null) {
				ret = (Integer) obj;
			}
		} catch (Exception e) {
			LogHelper.error("TbSysApp.countApp: " + e.getMessage() + e.getClass().getName());
		}
		return ret;
	}

	/**
	 * @Title: queryForListUseIn
	 * @Description: 根据id的集合查询应用
	 * @param
	 * @return List<TbSysAppObj>
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-8-18 下午9:08:30
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<TbSysAppObj> queryForListUseIn(TbSysAppObj tbSysAppObj) {
		List<TbSysAppObj> list = new ArrayList<TbSysAppObj>();
		try {
			list = getSqlMap().queryForList("TbSysApp.queryForListUseIn", tbSysAppObj);
		} catch (Exception e) {
			LogHelper.error("TbSysApp.queryForListUseIn: " + e.getMessage() + e.getClass().getName());
		}
		return list;
	}
}
