package com.sitech.ssd.ah.nas.dao;

import java.util.List;

import com.sitech.ssd.ah.nas.domain.NasStorePoolObj;




/**
 * Title: NasStorePoolDao
 * Copyright: Copyright (c) 2014
 * Company: SI-TECH 
 * @author siweichao
 * @version 1.0
 * @createtime 2014年3月26日10:26:06
 *
 */
public interface NasStorePoolDao {
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
	public void insertStorePool(NasStorePoolObj obj);
	
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
	public List<NasStorePoolObj> queryStorePoolByObj(NasStorePoolObj obj);
	
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
	public void updateStorePoolByObj(NasStorePoolObj obj);
	
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
	public void delStorePoolByObj(NasStorePoolObj obj);
}
