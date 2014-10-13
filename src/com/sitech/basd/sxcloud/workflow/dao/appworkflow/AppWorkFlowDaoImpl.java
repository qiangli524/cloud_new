package com.sitech.basd.sxcloud.workflow.dao.appworkflow;


import java.util.ArrayList;
import java.util.List;

import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;
import com.sitech.basd.sxcloud.workflow.domain.appdeployworkflow.AppNeedObj;
import com.sitech.basd.sxcloud.workflow.domain.appdeployworkflow.OrderInfoObj;
import com.sitech.basd.sxcloud.workflow.domain.engine.DisposeManObj;



public class AppWorkFlowDaoImpl extends BaseDao implements AppWorkFlowDao {
		
	/**
     * @Title:获取流程发起人
     * @Copyright: Copyright (c) 2012-02-28
     * @Company: si-tech
     * @author duangh
     * @version 1.0
    */
	@SuppressWarnings("unchecked")
	public List<DisposeManObj> getStartProcessDisposeMan(int process_id,String userId){
		DisposeManObj disposeManObj = new DisposeManObj() ;
		disposeManObj.setApplicationType(process_id);
		disposeManObj.setUserId(userId);
		List list = null ;
		try {
		 list = getSqlMap().queryForList("appDeployWorkFlow.getStartProcessDisposeMan",disposeManObj);
		} catch (Exception e) {
			LogHelper.error( "appDeployWorkFlow.getStartProcessDisposeMan:" +e.getMessage() + getClass().getName()) ;
			return null ;
		}
		return list ;
	}
	/**
	 * 
	* @Title: 查询应用部署申请
	* @Copyright: Copyright (c) 2012-02-29
	* @Company: si-tech
	* @author duangh
	* @version 1.0
	 */
	@SuppressWarnings("unchecked")
	public List<AppNeedObj> queryAppNeedList(AppNeedObj obj){
		List<AppNeedObj> list = new ArrayList<AppNeedObj>();
		try {
			if(obj.getPagination()!=null){
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj.getPagination().setTotalCount(((Integer) getSqlMap().queryForObject("appDeployWorkFlow.queryAppNeedForCount", obj)).intValue());
			}
			list = getSqlMap().queryForList("appDeployWorkFlow.queryAppNeedList",obj);
		} catch (Exception sqlexception) {
			sqlexception.printStackTrace();
			LogHelper.error( "appDeployWorkFlow.queryAppNeedListSql:" +sqlexception.getMessage() + getClass().getName()) ;
		}
		return list; 
	}
	
	/**
	 * 
	* @Title: 新增应用部署申请
	* @Copyright: Copyright (c) 2012-02-29
	* @Company: si-tech
	* @author duangh
	* @version 1.0
	 */
	public int addAppNeedInfo(AppNeedObj obj){
		int ret =0;
		try {  
            Object o = getSqlMap().insert("appDeployWorkFlow.addAppNeedInfo", obj);
            if (o != null) {
                ret = Integer.parseInt(o.toString());
            }
		} catch (Exception e) {
			LogHelper.debug("appDeployWorkFlow.addAppNeedInfo:"+e.getMessage()+getClass().getName());
			ret= -1;
		}
		return ret; 
	}
	/**
	 * 
	* @Title: 新增应用部署的信息
	* @Copyright: Copyright (c) 2012-02-29
	* @Company: si-tech
	* @author duangh
	* @version 1.0
	 */
	public int insertAppInfo(OrderInfoObj obj){
		int ret =0;
		try {  
            Object o = getSqlMap().insert("appDeployWorkFlow.insertAppInfo", obj);
            if (o != null) {
                ret = Integer.parseInt(o.toString());
            }
		} catch (Exception e) {
			LogHelper.debug("appDeployWorkFlow.insertAppInfo:"+e.getMessage()+getClass().getName());
			ret= -1;
		}
		return ret; 
	}
	/**
	 * 
	* @Title: 删除应用部署申请
	* @Copyright: Copyright (c) 2012-02-29
	* @Company: si-tech
	* @author duangh
	* @version 1.0
	 */
	public int deleteAppNeedInfo(String requestNo){
		int ret =0;
		try { 
            Object o = getSqlMap().insert("appDeployWorkFlow.deleteAppNeedInfo", requestNo);
            if (o != null) {
                ret = Integer.parseInt(o.toString());
            }
		} catch (Exception e) {
			LogHelper.debug("appDeployWorkFlow.deleteAppNeedInfo:"+e.getMessage()+getClass().getName());
			ret= -1;
		}
		return ret;
	}
	/**
	 * 
	* @Title: 删除应用部署申请的信息
	* @Copyright: Copyright (c) 2012-03-07
	* @Company: si-tech
	* @author duangh
	* @version 1.0
	 */
	public int deleteAppNeedOrder(String requestNo){
		int ret =0;
		try { 
            Object o = getSqlMap().insert("appDeployWorkFlow.deleteAppNeedOrder", requestNo);
            if (o != null) {
                ret = Integer.parseInt(o.toString());
            }
		} catch (Exception e) {
			LogHelper.debug("appDeployWorkFlow.deleteAppNeedOrder:"+e.getMessage()+getClass().getName());
			ret= -1;
		}
		return ret;
	}

	/**
	 * 
	* @Title: 修改草稿箱中的应用部署申请
	* @Copyright: Copyright (c) 2012-02-29
	* @Company: si-tech
	* @author duangh
	* @version 1.0
	 */
	public int alterAppNeedInfo(AppNeedObj obj){
		int ret = 0;
        try {
            Object o = getSqlMap().update("appDeployWorkFlow.alterAppNeedInfo", obj);
            if (o != null) {
                ret = Integer.parseInt(o.toString());
            }
        }
        catch (Exception sqlexception) {
            ret = -1;
            LogHelper.error( "appDeployWorkFlow.alterAppNeedInfo:" +sqlexception.getMessage() + getClass().getName()) ;
        }
        return ret;
	}
	
	/**
     * @Title:更新工单信息
     * @Copyright: Copyright (c) 2012-02-29
     * @Company: si-tech
     * @author duangh
     * @version 1.0
    */
	public int updateByObj(AppNeedObj obj){
		int ret = 0;
        try {
            Object o = getSqlMap().update("appDeployWorkFlow.updateByObj", obj);
            if (o != null) {
                ret = Integer.parseInt(o.toString());
            }
        }
        catch (Exception sqlexception) {
            ret = -1;
            LogHelper.error( "appDeployWorkFlow.updateByObj:" +sqlexception.getMessage() + getClass().getName()) ;
        }
        return ret;
	}
	
	/**
     * @Title:修改申请的应用部署信息
     * @Copyright: Copyright (c) 2012-03-01
     * @Company: si-tech
     * @author duangh
     * @version 1.0
    */
	public int updateOrderInfo(OrderInfoObj obj){
		int ret = 0;
        try {
            Object o = getSqlMap().update("appDeployWorkFlow.updateOrderInfo", obj);
            if (o != null) {
                ret = Integer.parseInt(o.toString());
            }
        }
        catch (Exception sqlexception) {
            ret = -1;
            LogHelper.error( "appDeployWorkFlow.updateOrderInfo:" +sqlexception.getMessage() + getClass().getName()) ;
        }
        return ret;
	}
	/**
	 * 
	* @Title: 查询应用部署信息
	* @Copyright: Copyright (c) 2012-03-05
	* @Company: si-tech
	* @author duangh
	* @version 1.0
	 */
	@SuppressWarnings("unchecked")
	public List<OrderInfoObj> queryAppInfoList(OrderInfoObj obj){
		List<OrderInfoObj> list = new ArrayList<OrderInfoObj>();
		try {
		//	if(obj.getPagination()!=null){
			//	obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
			//	obj.setPAGESIZE(obj.getPagination().getPageSize());
			//	obj.getPagination().setTotalCount(((Integer) getSqlMap().queryForObject("appDeployWorkFlow.queryAppNeedForCount", obj)).intValue());
			//}
			list = getSqlMap().queryForList("appDeployWorkFlow.queryAppInfoList",obj);
		} catch (Exception sqlexception) {
			sqlexception.printStackTrace();
			LogHelper.error( "appDeployWorkFlow.queryAppNeedListSql:" +sqlexception.getMessage() + getClass().getName()) ;
		}
		return list; 
	}
	/**
	 * 
	* @Title: 查询并返回一个具体的应用部署信息
	* @Copyright: Copyright (c) 2012-03-05
	* @Company: si-tech
	* @author duangh
	* @version 1.0
	 */
	@SuppressWarnings("unchecked")
	public OrderInfoObj queryAppInfo(OrderInfoObj obj){
		List lst = null;
		OrderInfoObj tempObj =null;
		lst = queryAppInfoList(obj) ;
		if(lst!=null&&lst.size()>0){
			tempObj =(OrderInfoObj)lst.get(0);
		}
		return tempObj ;
	}
	/**
	 * 
	* @Title: 查询并返回一个具体申请的应用部署信息
	* @Copyright: Copyright (c) 2012-03-07
	* @Company: si-tech
	* @author duangh
	* @version 1.0
	 */
	public AppNeedObj queryAppOrderInfo(AppNeedObj obj){
		List lst = null;
		AppNeedObj tempObj =null;
		lst = queryAppNeedList(obj) ;
		if(lst!=null&&lst.size()>0){
			tempObj =(AppNeedObj)lst.get(0);
		}
		return tempObj ;
	}
	/**
	 * 
	* @Title: 同意申请时将IP信息插入表中
	* @Copyright: Copyright (c) 2012-03-15
	* @Company: si-tech
	* @author duangh
	* @version 1.0
	 */
	public int updateAppIpInfo(AppNeedObj obj){
		int ret = 0;
        try {
            Object o = getSqlMap().update("appDeployWorkFlow.updateAppIpInfo", obj);
            if (o != null) {
                ret = Integer.parseInt(o.toString());
            }
        }
        catch (Exception sqlexception) {
            ret = -1;
            LogHelper.error( "appDeployWorkFlow.updateAppIpInfo:" +sqlexception.getMessage() + getClass().getName()) ;
        }
        return ret;
	}
	/**
	 * 
	* @Title: 同意申请时添加防篡改信息
	* @Copyright: Copyright (c) 2012-03-16
	* @Company: si-tech
	* @author duangh
	* @version 1.0
	 */
	public int updateDefend(AppNeedObj obj){
		int ret = 0;
        try {
            Object o = getSqlMap().update("appDeployWorkFlow.updateDefend", obj);
            if (o != null) {
                ret = Integer.parseInt(o.toString());
            }
        }
        catch (Exception sqlexception) {
            ret = -1;
            LogHelper.error( "appDeployWorkFlow.updateDefend:" +sqlexception.getMessage() + getClass().getName()) ;
        }
        return ret;
	}
	
}
