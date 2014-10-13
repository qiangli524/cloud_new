package com.sitech.basd.yicloud.service.nic;

import java.util.List;

import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;
import com.sitech.basd.yicloud.domain.nic.NicObj;

public interface NicService {
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
	public int insertByObj(NicObj obj);
	
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
	public NicObj queryByObj(NicObj obj);
	
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
	public List queryForListByObj(NicObj obj);
}
