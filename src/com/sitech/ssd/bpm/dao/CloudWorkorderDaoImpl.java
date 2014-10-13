package com.sitech.ssd.bpm.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;
import com.sitech.ssd.bpm.domain.CloudWorkorder;
import com.sitech.utils.randomid.RandomUUID;

@Repository("cloudWorkorderDao")
public class CloudWorkorderDaoImpl extends BaseDao implements CloudWorkorderDao {

	/** (非 Javadoc) 
	* <p>Title: insertByObj</p> 
	* <p>Description: </p> 
	* @author wanglei_bj@si-tech.com.cn 
	* @param t
	* @return 
	* @see dao.bpm.CloudWorkorderDao#insertByObj(com.sitech.ssd.bpm.domain.CloudWorkorder) 
	*/
	@Override
	public int insertByObj(CloudWorkorder obj) {
		int ret = 0;
		try {
			// 设置UUID
			obj.setWorkorderId(RandomUUID.getUuid());
			Object o = getSqlMap().insert("CloudWorkorder.insertByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlException) {
			ret = -1;
			LogHelper.error("CloudWorkorder.insertByObj:" + sqlException.getMessage()
					+ getClass().getName());
		}
		return ret;
	}

	/** (非 Javadoc) 
	* <p>Title: deleteByObj</p> 
	* <p>Description: </p> 
	* @author wanglei_bj@si-tech.com.cn 
	* @param t
	* @return 
	* @see dao.bpm.CloudWorkorderDao#deleteByObj(com.sitech.ssd.bpm.domain.CloudWorkorder) 
	*/
	@Override
	public int deleteByObj(CloudWorkorder obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().delete("CloudWorkorder.deleteByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlException) {
			ret = -1;
			LogHelper.error("CloudWorkorder.deleteByObj:" + sqlException.getMessage()
					+ getClass().getName());
		}
		return ret;
	}

	/** (非 Javadoc) 
	* <p>Title: updateByObj</p> 
	* <p>Description: </p> 
	* @author wanglei_bj@si-tech.com.cn 
	* @param t
	* @return 
	* @see dao.bpm.CloudWorkorderDao#updateByObj(com.sitech.ssd.bpm.domain.CloudWorkorder) 
	*/
	@Override
	public int updateByObj(CloudWorkorder obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().update("CloudWorkorder.updateByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlException) {
			ret = -1;
			LogHelper.error("CloudWorkorder.updateByObj:" + sqlException.getMessage()
					+ getClass().getName());
		}
		return ret;
	}

	/** (非 Javadoc) 
	* <p>Title: queryForList</p> 
	* <p>Description: </p> 
	* @author wanglei_bj@si-tech.com.cn 
	* @param t
	* @return 
	* @see dao.bpm.CloudWorkorderDao#queryForList(com.sitech.ssd.bpm.domain.CloudWorkorder) 
	*/
	@Override
	public List<CloudWorkorder> queryForList(CloudWorkorder obj) {
		List<CloudWorkorder> list = null;
		try {
			if (obj.getPagination() != null) {
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj.getPagination().setTotalCount(
						((Integer) getSqlMap()
								.queryForObject("CloudWorkorder.queryByObjForCount", obj)).intValue());
			}
			list = getSqlMap().queryForList("CloudWorkorder.queryForListByObj", obj);
		} catch (Exception sqlException) {
			LogHelper.error("CloudWorkorder.queryForListByObj:" + sqlException.getMessage()
					+ getClass().getName());
		}
		return list;
	}

	/** (非 Javadoc) 
	* <p>Title: queryForObj</p> 
	* <p>Description: </p> 
	* @author wanglei_bj@si-tech.com.cn 
	* @param t
	* @return 
	* @see dao.bpm.CloudWorkorderDao#queryForObj(com.sitech.ssd.bpm.domain.CloudWorkorder) 
	*/
	@Override
	public CloudWorkorder queryForObj(CloudWorkorder obj) {
		CloudWorkorder workorder = null;
		List<CloudWorkorder> tmpCloudWorkorders = null;
		tmpCloudWorkorders= queryForList(obj);
		if(tmpCloudWorkorders != null && tmpCloudWorkorders.size() > 0){
			workorder = tmpCloudWorkorders.get(0);
		}
		return workorder;
	}
	
}
