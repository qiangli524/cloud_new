package com.sitech.basd.yicloud.dao.ipinfo;

import java.util.List;

import com.sitech.basd.yicloud.domain.dictionary.DictionaryObj;
import com.sitech.basd.yicloud.domain.ipinfo.IpinfoObj;

public interface IpinfoDao {
	
	/**
	 * @Title:获取ip数据结果集
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
	 * @Title:添加IP池信息
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
	/**
	 * 
	 *  @方法名称:
	 *  @方法描述:查询某一vlan下IP未使用和已使用的个数
	 *  @作者 
	 *	@时间 2014-9-5 下午2:00:48
	 *  @版权 si-tech 2014 All right reserved.
	 *  @param net_id
	 *  @return
	 */
	public String queryIpUnusedAndUsedCount(String net_id);
	/**
	 * 
	 *  @方法名称:
	 *  @方法描述:获取所有IP未使用和已使用的个数
	 *  @作者 
	 *	@时间 2014-9-5 下午2:11:53
	 *  @版权 si-tech 2014 All right reserved.
	 *  @return
	 */
	public String queryAllIpUnusedAndUsedCount();
	
	/**
	 * @Title:更改IP状态
	 * @author：JiangDi
	 * @Company: si-tech
	 * @date : 2014-10-09
	 * @version 1.0
	 */
	public void updateIpState();
	
	/**
	 * @Title:更改IP状态
	 * @author：JiangDi
	 * @Company: si-tech
	 * @date : 2014-10-09
	 * @version 1.0
	 */
	public void updateIpStateByIp(String ip);
}
