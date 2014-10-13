package com.sitech.basd.yicloud.dao.nic;

import java.util.List;

import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;
import com.sitech.basd.yicloud.domain.nic.NicObj;
import com.sitech.basd.yicloud.domain.nic.NicRelationObj;
import com.sitech.basd.yicloud.domain.nic.VirtualNicObj;

public class VirtualNicDaoImpl extends BaseDao implements VirtualNicDao {
	
	/**
	 * 
	 * @Title: insertByObj
	 * @Description: 插入一个虚拟网卡
	 * @param
	 * @return int
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime Apr 18, 2013 11:18:10 AM
	 */
	public int insertByObj(VirtualNicObj obj){
		int ret = 0;
		try {
			Object o = getSqlMap().insert("vmwareVirtualNic.insertByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("vmwareVirtualNic.insertByObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return ret;
	}
	
	/**
	 * 
	 * @Title: queryByObj
	 * @Description: 查询符合条件的虚拟网卡
	 * @param
	 * @return VirtualNicObj
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime Apr 24, 2013 11:36:43 AM
	 */
	public VirtualNicObj queryByObj(VirtualNicObj obj){
		VirtualNicObj vnicObj = null;
		List lst = queryForListByObj(obj);
		if(lst!=null && lst.size()>0){
			vnicObj = (VirtualNicObj)lst.get(0);
		}else{
			vnicObj = new VirtualNicObj();
		}
		return vnicObj;
	}
	
	/**
	 * 
	 * @Title: queryForListByObj
	 * @Description: 查询符合条件的虚拟网卡
	 * @param
	 * @return List
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime Apr 24, 2013 11:36:20 AM
	 */
	public List queryForListByObj(VirtualNicObj obj){
		List list = null;
		try {
			list = getSqlMap().queryForList("vmwareVirtualNic.queryForListByObj", obj);
		} catch (Exception e) {
			LogHelper.error("vmwareVirtualNic.queryForListByObj:" + e.getMessage()
					+ getClass().getName());
		}
		return list;
	}
	
	/**
	 * 
	 * @Title: deleteByObj
	 * @Description: 删除虚拟网卡
	 * @param
	 * @return int
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime May 23, 2013 11:40:42 AM
	 */
	public int deleteByObj(VirtualNicObj obj){
		int ret = 0;
		try {
			ret = getSqlMap().delete("vmwareVirtualNic.deleteByObj", obj);
		} catch (Exception e) {
			ret = -1;
			LogHelper.error("vmwareVirtualNic.deleteByObj:"
					+ e.getMessage() + getClass().getName());
		}
		return ret;
	}

}
