package com.sitech.basd.yicloud.dao.device.lun;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;
import com.sitech.basd.yicloud.domain.device.disk.DiskInfoObj;
import com.sitech.basd.yicloud.domain.device.lun.LunObj;

/**
 * @Title lunDaoImpl
 * @Description 磁盘组持久层实现类
 * @author lipp
 * @date 2014-6-1 下午5:19:17
 * @version 1.0
 * @Company si-tech
 */
@Repository("lunDao")
public class LunDaoImpl extends BaseDao implements LunDao {

	/**  
	  * @Title: queryForListByObj  
	  * @Description: 查询列表
	  * @throws  
	  * @Date 2014-6-1 下午5:19:41
	  * @author lipp
	  * @param paramObj
	  * @return
	  */
	@SuppressWarnings("unchecked")
	@Override
	public List<LunObj> queryForListByObj(LunObj paramObj) {
		List<LunObj> list = new ArrayList<LunObj>();
		try {
			if (paramObj.getPagination() != null) {
				paramObj.setFIRSTROWNUM(paramObj.getPagination().getFirstRownum());
				paramObj.setPAGESIZE(paramObj.getPagination().getPageSize());
				paramObj.getPagination().setTotalCount(
						((Integer) getSqlMap().queryForObject(
								"lun.queryForCountByObj", paramObj))
								.intValue());
			}
			list = getSqlMap().queryForList("lun.queryForListByObj", paramObj);
		} catch (Exception e) {
			e.printStackTrace();
			LogHelper.error("lun.queryForListByObj: 查询磁盘组列表失败，失败原因： " + e );
		}
		return list;
	}

	/**  
	  * @Title: insertByObj  
	  * @Description: 插入记录 
	  * @throws  
	  * @Date 2014-6-1 下午5:19:46
	  * @author lipp
	  * @param paramObj
	  * @return
	  */
	@Override
	public int insertByObj(LunObj paramObj) {
		int ret = 0;
		try {
			getSqlMap().insert("lun.insertByObj", paramObj);
		} catch (Exception e) {
			e.printStackTrace();
			LogHelper.error("lun.insertByObj: 插入磁盘组记录" + paramObj.getName() + "失败，失败原因： " + e );
			ret = -1;
		}
		return ret;
	}

	/**  
	  * @Title: updateByObj  
	  * @Description: 更新记录
	  * @throws  
	  * @Date 2014-6-1 下午5:19:48
	  * @author lipp
	  * @param paramObj
	  * @return
	  */
	@Override
	public int updateByObj(LunObj paramObj) {
		int ret = 0;
		try {
			getSqlMap().update("lun.updateByObj", paramObj);
		} catch (Exception e) {
			e.printStackTrace();
			LogHelper.error("lun.updateByObj: 更新磁盘组记录" + paramObj.getName() + "失败，失败原因： " + e );
			ret = -1;
		}
		return ret;
	}

	/**  
	  * @Title: deleteByObj  
	  * @Description: 删除记录
	  * @throws  
	  * @Date 2014-6-1 下午5:19:51
	  * @author lipp
	  * @param paramObj
	  * @return
	  */
	@Override
	public int deleteByObj(LunObj paramObj) {
		int ret = 0;
		try {
			getSqlMap().delete("lun.deleteByObj", paramObj);
		} catch (Exception e) {
			e.printStackTrace();
			LogHelper.error("lun.deleteByObj: 删除磁盘组" + paramObj.getName() + "失败，失败原因： " + e );
			ret = -1;
		}
		return ret;
	}
	@Override
	public int updateGroupIdByObj(LunObj paramObj) {
		int ret = 0;
		try {
			getSqlMap().update("lun.updateGroupIdByObj", paramObj);
		} catch (Exception e) {
			e.printStackTrace();
			LogHelper.error("lun.updateGroupIdByObj: 更新磁盘组记录" + paramObj.getName() + "失败，失败原因： " + e );
			ret = -1;
		}
		return ret;
	}

}
