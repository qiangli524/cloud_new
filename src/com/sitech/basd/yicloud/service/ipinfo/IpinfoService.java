
package com.sitech.basd.yicloud.service.ipinfo;

import java.util.List;

import com.sitech.basd.yicloud.domain.dictionary.DictionaryObj;
import com.sitech.basd.yicloud.domain.ipinfo.IpinfoObj;

public interface IpinfoService {
	
	/**
	 * @Title:获取IP数据结果集
	 * @author：lzh
	 * @Company: si-tech
	 * @date : 2012-6-7
	 * @version 1.0
	 */
	public List queryForListByObj(IpinfoObj obj);
	
	/**
	 * @Title:获取是否使用下拉框的值
	 * @author：lzh
	 * @Company: si-tech
	 * @date : 2012-6-7
	 * @version 1.0
	 */
	public List queryForListByUsed(DictionaryObj obj);
	
	/**
	 * @Title:获取是否被阻塞下拉框的值
	 * @author：lzh
	 * @Company: si-tech
	 * @date : 2012-6-7
	 * @version 1.0
	 */
	public List queryForListByBlocked(DictionaryObj obj);
	
	/**
	 * @Title:获取IP类型下拉框的值
	 * @author：lzh
	 * @Company: si-tech
	 * @date : 2012-6-7
	 * @version 1.0
	 */
	public List queryForListByType(DictionaryObj obj);
	
	/**
	 * @Title:增加IP池信息
	 * @author：lzh
	 * @Company: si-tech
	 * @date : 2012-6-8
	 * @version 1.0
	 */
	public int insertByObj(IpinfoObj obj);
	
	/**
	 * @Title:根据ID查询IP池信息
	 * @author：lzh
	 * @Company: si-tech
	 * @date : 2012-6-12
	 * @version 1.0
	 */
	public IpinfoObj queryByObj(IpinfoObj obj);
	
	/**
	 * @Title:修改IP池信息
	 * @author：lzh
	 * @Company: si-tech
	 * @date : 2012-6-12
	 * @version 1.0
	 */
	public int updateByObj(IpinfoObj obj);
	
	/**
	 * @Title:删除IP池信息
	 * @author：lzh
	 * @Company: si-tech
	 * @date : 2012-6-12
	 * @version 1.0
	 */
	public int deleteByObj(IpinfoObj obj);
}
