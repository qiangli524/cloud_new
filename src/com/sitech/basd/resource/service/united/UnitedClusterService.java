package com.sitech.basd.resource.service.united;

import java.sql.SQLException;

import com.sitech.basd.resource.domain.united.UnitedTreeObj;
import com.sitech.utils.exception.HttpClientException;
import com.sitech.vo.united.ClusterUnitedVO;

public interface UnitedClusterService {
	/**
	 * 
	 * @Title: createCluster
	 * @Description: 创建集群
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Jul 23, 2013 9:33:49 AM
	 */
	public String createCluster(UnitedTreeObj obj, ClusterUnitedVO vo) throws Exception;

	/**
	 * 
	 * @Title: deleteCluster
	 * @Description: 删除集群
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @throws SQLException
	 * @throws HttpClientException
	 * @throws Exception
	 * @createtime Jul 26, 2013 7:44:26 PM
	 */
	public String deleteCluster(UnitedTreeObj obj) throws Exception;

	/**
	 * 
	 * @Title: syncCluster
	 * @Description: xen同步集群
	 * @param
	 * @return String
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @throws HttpClientException
	 * @throws SQLException
	 * @createtime 2013-9-18 上午11:17:33
	 */
	public String syncCluster(String parent_id) throws HttpClientException, SQLException;

	/**
	 * 
	 * @Title: getClusterInfo
	 * @Description: 获取指定集群的信息
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime 2014-4-1 下午2:37:06
	 */
	public ClusterUnitedVO getClusterInfo(UnitedTreeObj obj) throws HttpClientException;

	/**
	 * 
	 * @Title: editCluster
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime 2014-4-1 下午2:37:06
	 */
	public String editCluster(UnitedTreeObj obj, ClusterUnitedVO clusterUnitedVO)
			throws HttpClientException;
}
