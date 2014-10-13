package com.sitech.basd.sxcloud.cloud.service.net;

import java.util.List;
import java.util.Map;

import com.sitech.basd.sxcloud.cloud.domain.net.TbCloud2IpInfoObj;
import com.sitech.basd.sxcloud.cloud.domain.net.TbCloud2NetInfoObj;
import com.sitech.basd.sxcloud.cloud.web.showresource.form.AlarmHostStatistics;

public interface IpService {

	/**
	 * @Title:查询已有IP列表
	 * @Copyright: Copyright (c) 2011-12-2
	 * @Company: si-tech
	 * @author liys
	 * @version 1.0
	 */

	public List<TbCloud2IpInfoObj> queryForListByObj(TbCloud2IpInfoObj obj);

	/**
	 * @Title:查询已有IP列表
	 * @Copyright: Copyright (c) 2011-12-2
	 * @Company: si-tech
	 * @author liys
	 * @version 1.0
	 */
	public List<TbCloud2IpInfoObj> queryIPForList(TbCloud2IpInfoObj obj);

	/**
	 * @Title:查询并返回一个IP对象
	 * @Copyright: Copyright (c) 2011-12-2
	 * @Company: si-tech
	 * @author liys
	 * @version 1.0
	 */

	public TbCloud2IpInfoObj queryByObj(TbCloud2IpInfoObj obj);

	/**
	 * @Title:更新IP信息
	 * @Copyright: Copyright (c) 2011-12-2
	 * @Company: si-tech
	 * @author liys
	 * @version 1.0
	 */

	public int updateByObj(TbCloud2IpInfoObj obj);

	/**
	 * @Title:删除已有IP
	 * @Copyright: Copyright (c) 2011-12-2
	 * @Company: si-tech
	 * @author liys
	 * @version 1.0
	 */

	public int deleteByObj(TbCloud2IpInfoObj obj);

	/**
	 * @Title:创建IP
	 * @Copyright: Copyright (c) 2011-12-2
	 * @Company: si-tech
	 * @author liys
	 * @version 1.0
	 */

	public int insertByObj(TbCloud2IpInfoObj obj);

	/**
	 * @Title:创建多个IP
	 * @Copyright: Copyright (c) 2011-12-2
	 * @Company: si-tech
	 * @author taoxue
	 * @version 1.0
	 */
	public int insertManyIpByObj(TbCloud2IpInfoObj obj);

	/**
	 * @Title:查询NET表作为IP的下拉列表
	 * @Copyright: Copyright (c) 2011-12-2
	 * @Company: si-tech
	 * @author wangjian
	 * @version 1.0
	 */

	@SuppressWarnings("rawtypes")
	public List queryForListByNetObj(TbCloud2NetInfoObj obj);

	/**
	 * @Title:阻塞已有IP
	 * @Copyright: Copyright (c) 2011-12-2
	 * @Company: si-tech
	 * @author wangjian
	 * @version 1.0
	 */

	public int backupByObj(TbCloud2IpInfoObj obj);

	/**
	 * @Title:发布已有IP
	 * @Copyright: Copyright (c) 2011-12-2
	 * @Company: si-tech
	 * @author wangjian
	 * @version 1.0
	 */

	public int issuanceByObj(TbCloud2IpInfoObj obj);

	/**
	 * 
	 * @Title: updateIPStat
	 * @Description: 更新IP状态
	 * @param
	 * @return int
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime 2012-9-3
	 */

	public int updateIPStat(TbCloud2IpInfoObj obj);

	/**
	 * 
	 * @Title: queryForListByIPObj
	 * @Description: 根据条件查询ip
	 * @param
	 * @return List
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime Mar 27, 2013 4:25:20 PM
	 */
	@SuppressWarnings("rawtypes")
	public List queryForListByIPObj(TbCloud2IpInfoObj obj);

	/**
	 * @Title: queryUnUsedIpByObj
	 * @Description: 查询可用ip集合
	 * @param
	 * @return List<TbCloud2IpInfoObj>
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-8-15 下午8:22:32
	 */
	public List<TbCloud2IpInfoObj> queryAchivableIpList(TbCloud2IpInfoObj obj);

	public String findIpAddressById(String id);

	public String findIdByIpAddress(String ip);

	/**
	 * 更新ip
	 * 
	 * @author lipengpeng
	 * @param tbCloud2IpInfoObj
	 * @return
	 */
	public int updateIPByObj(TbCloud2IpInfoObj tbCloud2IpInfoObj);

	/**
	 * @Title: queryByIpAddress
	 * @Description: 根据ip地址选择ip
	 * @param
	 * @return List<TbCloud2IpInfoObj>
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-9-7 下午5:49:36
	 */
	public List<TbCloud2IpInfoObj> queryByIpAddress(TbCloud2IpInfoObj ipInfoObj);

	/**
	 * @Title: queryForListUseNetIn
	 * @Description: 根据vla_id查询集合
	 * @param
	 * @return List<TbCloud2IpInfoObj>
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-9-13 上午8:24:02
	 */
	public List<TbCloud2IpInfoObj> queryForListUseNetIn(
			TbCloud2IpInfoObj ipInfoObj);

	/**
	 * @Title: queryIpUsageByVlanId
	 * @Description: 查询vlan下每个ip的利用率
	 * @param
	 * @return List<AlarmHostStatistics>
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-9-14 上午9:48:42
	 */
	public List<AlarmHostStatistics> queryIpUsageByVlanId(
			TbCloud2IpInfoObj ipInfoObj);

	/**
	 * @Title: queryIpForTree
	 * @Description: 根据net_id查询对应的ip、虚拟机、业务系统
	 * @param
	 * @return List<Map<String, String>>
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @createtime 2013-10-29  下午16:21
	 */
	public List<Map<String, String>> queryIpForTree(TbCloud2IpInfoObj ipInfoObj);
	
	/** 
	*
	* @Title: getARandomIp 
	* @Description: TODO(根据条件查询随机查询出一条记录) 
	* @param @param ipInfoObj
	* @param @return    设定文件 
	* @return TbCloud2IpInfoObj    返回类型 
	* @author wanglei_bj@si-tech.com.cn 
	* @throws 
	*/
	public TbCloud2IpInfoObj getARandomIp(TbCloud2IpInfoObj ipInfoObj);

}


