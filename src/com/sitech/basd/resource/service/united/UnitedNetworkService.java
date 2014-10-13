package com.sitech.basd.resource.service.united;

import java.sql.SQLException;
import java.util.List;

import com.sitech.basd.resource.domain.united.UnitedTreeObj;
import com.sitech.vo.united.HostPnicVO;
import com.sitech.vo.united.HostVSwitchVO;

public interface UnitedNetworkService {
	/**
	 * 
	 * @Title:添加网络域
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime 2013-9-11 上午11:01:20
	 */
	public String addDomain(UnitedTreeObj obj) throws SQLException;

	/**
	 * 
	 * @Title:删除
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-9-12
	 */
	public int delOperate(UnitedTreeObj obj) throws SQLException;

	/**
	 * 
	 * @Title:修改
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-9-13
	 */
	public int updOperate(UnitedTreeObj obj) throws SQLException;

	/**
	 * 
	 * @Title: queryNetDomain
	 * @Description: 查询网络域
	 * @param
	 * @return list
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-09-14
	 */
	public List queryNetDomain();

	/**
	 * 
	 * @Title: querySubNet
	 * @Description: 查询子网络域
	 * @param
	 * @return list
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-09-14
	 */
	public List querySubNet(UnitedTreeObj obj);

	public List<HostPnicVO> getpnicInfo(String connect_id, String hostId, String vtype);
	
	public List<HostVSwitchVO> getVswitchInfo(String connect_id, String hostId);
	//湖北移动投标添加，从接口查询标准交换机下的端口组信息
	public List<HostVSwitchVO> getStandandVswitchPG(String connect_id, String hostId, String vsName);
	//湖北移动投标移动添加，设置vlanID
	public String setVlan(String connect_id, String hostId, String pgName,String vlanId);
	
}
