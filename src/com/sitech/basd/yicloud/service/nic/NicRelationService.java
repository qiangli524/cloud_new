package com.sitech.basd.yicloud.service.nic;

import java.util.List;

import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;
import com.sitech.basd.yicloud.domain.nic.NicObj;
import com.sitech.basd.yicloud.domain.nic.NicRelationObj;

public interface NicRelationService {
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
	public int insertByObj(NicRelationObj obj);
	
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
	public NicRelationObj queryByObj(NicRelationObj obj);
	
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
	public List queryForListByObj(NicRelationObj obj);
	
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
	public int deleteByObj(NicRelationObj obj);
}
