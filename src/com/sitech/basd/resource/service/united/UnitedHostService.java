package com.sitech.basd.resource.service.united;

import java.sql.SQLException;
import java.util.List;

import com.sitech.basd.resource.domain.united.UnitedTreeObj;
import com.sitech.basd.yicloud.domain.datastore.DataStoreObj;
import com.sitech.utils.exception.HttpClientException;
import com.sitech.vo.united.DatastoreUnitedVO;
import com.sitech.vo.united.HostUnitedVO;
import com.sitech.vo.united.PortGroupUnitedVO;

public interface UnitedHostService {

	/**
	 * 
	 * @Title: createHost
	 * @Description: 创建主机
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @throws SQLException
	 * @createtime Jul 23, 2013 11:54:09 AM
	 */
	public String createHost(UnitedTreeObj obj, HostUnitedVO vo) throws HttpClientException,
			SQLException;

	/**
	 * 
	 * @Title: deleteHost
	 * @Description: 删除主机
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @throws SQLException
	 * @createtime Jul 27, 2013 11:00:52 AM
	 */
	public String deleteHost(UnitedTreeObj obj) throws HttpClientException, SQLException;
	/**
	 * 
	 * @Title: getHostPortGroup
	 * @Description: 查看主机的网络信息
	 * @param
	 * @return 
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-08-21
	 */
	public PortGroupUnitedVO getHostPortGroup(UnitedTreeObj obj) throws HttpClientException;
	
	/**
	 * 
	 * @Title: getHostDataStoreForXen
	 * @Description: xen查看主机的存储列表
	 * @param
	 * @return 
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @createtime 2013-08-21
	 */
	public  List<DataStoreObj> getHostDataStoreForXen(DataStoreObj obj) throws Exception;
	
	/**  
	  * @Title: disConnectHost  
	  * @Description: 断开主机连接
	  * @return String   
	  * @throws  
	  * @Date 2014-4-28 下午3:08:15
	  * @author lipp
	  * @param vtype
	  * @param hostUnitedVO
	  * @return
	  */
	public String disConnectHost(String vtype, HostUnitedVO hostUnitedVO);

	 /**  
	  * @Title: changeHostPowerState  
	  * @Description: 更改主机电源状态
	  * @return String   
	  * @throws  
	  * @Date 2014-4-28 下午3:30:04
	  * @author lipp
	  * @param vtype
	  * @param hostUnitedVO
	  * @return
	  */
	public String changeHostPowerState(String vtype, HostUnitedVO hostUnitedVO);

	 /**  
	  * @Title: enterAwaitModeHost  
	  * @Description: 主机进入待机模式
	  * @return String   
	  * @throws  
	  * @Date 2014-4-28 下午3:52:24
	  * @author lipp
	  * @param vtype
	  * @param hostUnitedVO
	  * @return
	  */
	public String enterAwaitModeHost(String vtype, HostUnitedVO hostUnitedVO);

	 /**  
	  * @Title: exitAwaitModeHost  
	  * @Description: 主机退出待机模式
	  * @return String   
	  * @throws  
	  * @Date 2014-4-28 下午3:52:40
	  * @author lipp
	  * @param vtype
	  * @param hostUnitedVO
	  * @return
	  */
	public String exitAwaitModeHost(String vtype, HostUnitedVO hostUnitedVO);
	/**  
	  * @Title: saveHostDataStore  
	  * @Description: 主机添加存储 
	  * @return String   
	  * @throws  
	  * @Date 2014-6-1 下午14:52:53
	  * @author liwq
	  * @param vtype
	  * @param datastoreUnitedVO
	  * @return
	  */
	public String saveHostDataStore(String vtype, DatastoreUnitedVO datastoreUnitedVO);
}
