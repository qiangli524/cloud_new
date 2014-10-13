package com.sitech.ssd.ah.nas.dao;

import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;


import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.ssd.ah.nas.domain.NasStorePoolObj;

@Repository("nasStorePoolDao")
public class NasStorePoolDaoImpl extends BaseDao
		implements NasStorePoolDao {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	/**
	 * @Title: insertFile
	 * @Description: nas存储池入库
	 * @param
	 * @return 
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @throws Exception 
	 * @createtime 2014年3月26日9:19:51
	 */
	public void insertStorePool(NasStorePoolObj obj){
		try {
			sqlMapClient.insert("nasStorePool.insertByObj", obj);
		} catch (SQLException e) {
			logger.error("nas存储池入库失败:"+e.getMessage());
		}
	}
	
	
	/**
	 * @Title: queryFile
	 * @Description: 查询存储池列表
	 * @param
	 * @return 
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @throws Exception 
	 * @createtime 2014年3月26日9:19:51
	 */
	public List<NasStorePoolObj> queryStorePoolByObj(NasStorePoolObj obj){
		List<NasStorePoolObj> list = null;
		try {
			list = sqlMapClient.queryForList("nasStorePool.queryStorePoolByObj", obj);
		} catch (Exception sqlException) {
			logger.error("nasStorePool.queryStorePoolByObj:" + sqlException.getMessage()
					+ getClass().getName());
		}
		return list;
	}
	
	/**
	 * @Title: updateFileByObj
	 * @Description: 更新存储池
	 * @param
	 * @return 
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @throws Exception 
	 * @createtime 2014年3月26日9:19:51
	 */
	public void updateStorePoolByObj(NasStorePoolObj obj){
		try {
			sqlMapClient.update("nasStorePool.updateStorePoolByObj", obj);
		} catch (SQLException e) {
			logger.error("nasStorePool.updateStorePoolByObj:" + e.getMessage()
					+ getClass().getName());
		}
	}
	
	/**
	 * @Title: delFileByObj
	 * @Description: 删除指定的存储池
	 * @param
	 * @return 
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @throws Exception 
	 * @createtime 2014年3月26日9:19:51
	 */
	public void delStorePoolByObj(NasStorePoolObj obj){
		try {
			sqlMapClient.delete("nasStorePool.deleteStorePoolByObj", obj);
		} catch (SQLException e) {
			logger.error("nasStorePool.deleteStorePoolByObj:" + e.getMessage()
					+ getClass().getName());
		}
	}
	
}
