package com.sitech.basd.yicloud.dao.nic;

import java.util.List;

import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;
import com.sitech.basd.yicloud.domain.nic.NicObj;
import com.sitech.basd.yicloud.domain.nic.NicRelationObj;

public class NicDaoImpl extends BaseDao implements NicDao {
	
	/**
	 * 
	 * @Title: insertByObj
	 * @Description: 插入一个物理网卡
	 * @param
	 * @return int
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime Apr 18, 2013 11:18:10 AM
	 */
	public int insertByObj(NicObj obj){
		int ret = 0;
		try {
			Object o = getSqlMap().insert("vmwareNic.insertByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("vmwareNic.insertByObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return ret;
	}
	
	/**
	 * 
	 * @Title: queryForListByObj
	 * @Description: 查询符合条件的物理网卡
	 * @param
	 * @return List
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime Apr 19, 2013 11:56:57 AM
	 */
	public NicObj queryByObj(NicObj obj){
		NicObj nicObj = null;
		List lst = queryForListByObj(obj);
		if(lst!=null && lst.size()>0){
			nicObj = (NicObj)lst.get(0);
		}else{
			nicObj = new NicObj();
		}
		return nicObj;
	}
	
	/**
	 * 
	 * @Title: queryForListByObj
	 * @Description: 查询符合条件的物理网卡
	 * @param
	 * @return List
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime Apr 19, 2013 11:56:57 AM
	 */
	public List queryForListByObj(NicObj obj){
		List list = null;
		try {
			list = getSqlMap().queryForList("vmwareNic.queryForListByObj", obj);
		} catch (Exception e) {
			LogHelper.error("vmwareNic.queryForListByObj:" + e.getMessage()
					+ getClass().getName());
		}
		return list;
	}

}
