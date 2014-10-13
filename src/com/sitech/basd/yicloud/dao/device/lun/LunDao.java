package com.sitech.basd.yicloud.dao.device.lun;

import java.util.List;

import com.sitech.basd.yicloud.domain.device.disk.DiskInfoObj;
import com.sitech.basd.yicloud.domain.device.lun.LunObj;

/**
 * @Title DiskGroupDao
 * @Description 磁盘组持久层接口
 * @author lipp
 * @date 2014-6-1 下午5:15:31
 * @version 1.0
 * @Company si-tech
 */
public interface LunDao {

	 /**  
	  * @Title: queryForListByObj  
	  * @Description: 查询列表
	  * @return List<LunObj>   
	  * @throws  
	  * @Date 2014-6-1 下午5:17:07
	  * @author lipp
	  * @param paramObj
	  * @return
	  */
	public List<LunObj> queryForListByObj(LunObj paramObj);
	
	 /**  
	  * @Title: insertByObj  
	  * @Description: 插入记录 
	  * @return int   
	  * @throws  
	  * @Date 2014-6-1 下午5:17:40
	  * @author lipp
	  * @param paramObj
	  * @return
	  */
	public int insertByObj(LunObj paramObj);
	
	 /**  
	  * @Title: updateByObj  
	  * @Description: 更新记录
	  * @return int   
	  * @throws  
	  * @Date 2014-6-1 下午5:18:08
	  * @author lipp
	  * @param paramObj
	  * @return
	  */
	public int updateByObj(LunObj paramObj);
	
	 /**  
	  * @Title: deleteByObj  
	  * @Description: 删除记录
	  * @return int   
	  * @throws  
	  * @Date 2014-6-1 下午5:18:38
	  * @author lipp
	  * @param paramObj
	  * @return
	  */
	public int deleteByObj(LunObj paramObj);
	
	public int updateGroupIdByObj(LunObj paramObj);
}
