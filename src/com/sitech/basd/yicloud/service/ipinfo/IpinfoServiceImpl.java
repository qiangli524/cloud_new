package com.sitech.basd.yicloud.service.ipinfo;

import java.util.List;

import com.sitech.basd.yicloud.dao.ipinfo.IpinfoDao;
import com.sitech.basd.yicloud.domain.dictionary.DictionaryObj;
import com.sitech.basd.yicloud.domain.ipinfo.IpinfoObj;

public class IpinfoServiceImpl implements IpinfoService {

	private IpinfoDao ipinfoDao;

	/**
	 * @Title:获取IP数据结果集
	 * @author：lzh
	 * @Company: si-tech
	 * @date : 2012-6-7
	 * @version 1.0
	 */
	public List queryForListByObj(IpinfoObj obj) {
		return ipinfoDao.queryForListByObj(obj);
	}
	
	/**
	 * @Title:获取是否使用下拉框的值
	 * @author：lzh
	 * @Company: si-tech
	 * @date : 2012-6-7
	 * @version 1.0
	 */
	public List queryForListByUsed(DictionaryObj obj){
		return ipinfoDao.queryForListByUsed(obj);
	}
	
	/**
	 * @Title:获取是否被阻塞下拉框的值
	 * @author：lzh
	 * @Company: si-tech
	 * @date : 2012-6-7
	 * @version 1.0
	 */
	public List queryForListByBlocked(DictionaryObj obj){
		return ipinfoDao.queryForListByBlocked(obj);
	}
	
	/**
	 * @Title:获取IP类型下拉框的值
	 * @author：lzh
	 * @Company: si-tech
	 * @date : 2012-6-7
	 * @version 1.0
	 */
	public List queryForListByType(DictionaryObj obj){
		return ipinfoDao.queryForListByType(obj);
	}
	
	/**
	 * @Title:增加IP池信息
	 * @author：lzh
	 * @Company: si-tech
	 * @date : 2012-6-8
	 * @version 1.0
	 */
	public int insertByObj(IpinfoObj obj){
		return ipinfoDao.insertByObj(obj);
	}
	
	/**
	 * @Title:根据ID查询IP池信息
	 * @author：lzh
	 * @Company: si-tech
	 * @date : 2012-6-12
	 * @version 1.0
	 */
	public IpinfoObj queryByObj(IpinfoObj obj){
		return ipinfoDao.queryByObj(obj);
	}
	
	/**
	 * @Title:修改IP池信息
	 * @author：lzh
	 * @Company: si-tech
	 * @date : 2012-6-12
	 * @version 1.0
	 */
	public int updateByObj(IpinfoObj obj){
		return ipinfoDao.updateByObj(obj);
	}
	
	/**
	 * @Title:删除IP池信息
	 * @author：lzh
	 * @Company: si-tech
	 * @date : 2012-6-12
	 * @version 1.0
	 */
	public int deleteByObj(IpinfoObj obj){
		return ipinfoDao.deleteByObj(obj);
	}

	public void setIpinfoDao(IpinfoDao ipinfoDao) {
		this.ipinfoDao = ipinfoDao;
	}

}
