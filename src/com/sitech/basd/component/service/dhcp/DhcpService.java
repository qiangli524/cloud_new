package com.sitech.basd.component.service.dhcp;

import java.util.List;

import com.sitech.basd.component.domain.dhcp.DhcpObj;

/**
 * <p>Title: DhcpService</p>
 * <p>Description: dhcp业务层接口</p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: SI-TECH </p>
 * @author lipengpeng
 * @version 1.0
 * @createtime 2013-8-16 下午3:46:49
 *
 */
public interface DhcpService {

	/**
	 * @Title: insertDhcp
	 * @Description: 增加dhcp
	 * @param
	 * @return int
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-8-16 下午3:50:31
	 */
	public int insertDhcp(DhcpObj dhcpObj);
	
	/**
	 * @Title: deleteDhcp
	 * @Description: 删除dhcp
	 * @param
	 * @return int
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-8-16 下午3:50:43
	 */
	public int deleteDhcp(DhcpObj dhcpObj);
	
	/**
	 * @Title: updateDhcp
	 * @Description: 修改dhcp
	 * @param
	 * @return int
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-8-16 下午3:50:55
	 */
	public int updateDhcp(DhcpObj dhcpObj);
	
	/**
	 * @Title: queryForList
	 * @Description: 查询dhcp
	 * @param
	 * @return List<DhcpObj>
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-8-16 下午3:51:05
	 */
	public List<DhcpObj> queryForList(DhcpObj dhcpObj);
	
	/**
	 * @Title: countDhcp
	 * @Description:统计dhcp
	 * @param
	 * @return int
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-8-16 下午3:51:14
	 */
	public int countDhcp(DhcpObj dhcpObj);
}
