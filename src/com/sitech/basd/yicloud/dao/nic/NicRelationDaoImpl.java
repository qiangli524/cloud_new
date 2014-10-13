package com.sitech.basd.yicloud.dao.nic;

import java.util.List;

import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;
import com.sitech.basd.yicloud.domain.nic.NicRelationObj;

public class NicRelationDaoImpl extends BaseDao implements NicRelationDao {
	
	/**
	 * 
	 * @Title: insertByObj
	 * @Description: 插入vmware网络内部关系
	 * @param
	 * @return int
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime Apr 18, 2013 11:18:10 AM
	 */
	public int insertByObj(NicRelationObj obj){
		int ret = 0;
		try {
			Object o = getSqlMap().insert("nicRelation.insertByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("nicRelation.insertByObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return ret;
	}
	
	/**
	 * 
	 * @Title: queryByObj
	 * @Description: 查询符合条件的关系
	 * @param
	 * @return NicRelationObj
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime Apr 18, 2013 7:00:35 PM
	 */
	public NicRelationObj queryByObj(NicRelationObj obj){
		NicRelationObj nicre = null;
		List lst = queryForListByObj(obj);
		if(lst!=null && lst.size()>0){
			nicre = (NicRelationObj)lst.get(0);
		}else{
			nicre = new NicRelationObj();
		}
		return nicre;
	}
	
	/**
	 * 
	 * @Title: queryForListByObj
	 * @Description: 查询符合条件的关系
	 * @param
	 * @return List
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime Apr 18, 2013 7:00:02 PM
	 */
	public List queryForListByObj(NicRelationObj obj){
		List list = null;
		try {
			list = getSqlMap().queryForList("nicRelation.queryForListByObj", obj);
		} catch (Exception e) {
			LogHelper.error("nicRelation.queryForListByObj:" + e.getMessage()
					+ getClass().getName());
		}
		return list;
	}
	
	/**
	 * 
	 * 
	 * @Title: deleteByObj
	 * @Description: 删除关系
	 * @param
	 * @return int
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime May 6, 2013 5:34:18 PM
	 */
	public int deleteByObj(NicRelationObj obj){
		int ret = 0;
		try {
			ret = getSqlMap().delete("nicRelation.deleteByObj", obj);
		} catch (Exception e) {
			ret = -1;
			LogHelper.error("nicRelation.deleteByObj:"
					+ e.getMessage() + getClass().getName());
		}
		return ret;
	}

}
