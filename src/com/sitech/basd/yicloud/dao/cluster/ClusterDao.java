package com.sitech.basd.yicloud.dao.cluster;

import java.util.List;
import java.util.Map;

import com.sitech.basd.yicloud.domain.cluster.ClusterObj;

public interface ClusterDao {
	/**
	 * @Title:保存集群信息
	 * @author：duangh
	 * @Company: si-tech
	 * @date : Apr 18, 2012 2:29:27 PM
	 * @version 1.0
	 */
	public int insertByObj(ClusterObj obj);

	/**
	 * @Title:查询单个集群信息
	 * @author：duangh
	 * @Company: si-tech
	 * @date : Apr 18, 2012 2:29:27 PM
	 * @version 1.0
	 */
	public ClusterObj queryByObj(ClusterObj obj);

	/**
	 * @Title:更改集群信息
	 * @author：duangh
	 * @Company: si-tech
	 * @date : Apr 18, 2012 2:29:27 PM
	 * @version 1.0
	 */
	public int updateByObj(ClusterObj obj);

	/**
	 * @Title:删除集群信息
	 * @author：duangh
	 * @Company: si-tech
	 * @date : Apr 18, 2012 2:29:27 PM
	 * @version 1.0
	 */
	public int deleteByObj(ClusterObj obj);

	/**
	 * 
	 * @Title: queryForCollection
	 * @Description: 查询集群的uuid等信息
	 * @param
	 * @return ClusterObj
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Oct 19, 2012 9:10:06 AM
	 */
	public ClusterObj queryForCollection(ClusterObj obj);

	/**
	 * 
	 * @Title: queryForConn
	 * @Description: 插入集群信息（xen）
	 * @param
	 * @return EntityTreeObj
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Oct 19, 2012 9:50:41 AM
	 */
	public int insertForConnection(ClusterObj obj);

	/**
	 * 
	 * @Title: getIdSequence
	 * @Description: 获取集群ID序列
	 * @param
	 * @return int
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Oct 22, 2012 3:21:14 PM
	 */
	public int getIdSequence();

	/**
	 * 
	 * @Title: updateForConnection
	 * @Description:更新集群的信息
	 * @param
	 * @return int
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Oct 23, 2012 11:34:20 AM
	 */
	public int updateForConnection(ClusterObj obj);

	/**
	 * 
	 * @Title: queryListByObj
	 * @Description: 查询集群信息列表
	 * @param
	 * @return List
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime Oct 25, 2012 10:09:37 PM
	 */
	public List queryListByObj(ClusterObj obj);

	/**
	 * 
	 * @Title: updateByObjUuid
	 * @Description:通过Uuid更新数据
	 * @param
	 * @return int
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime Oct 26, 2012 11:35:08 AM
	 */
	public int updateByObjUuid(ClusterObj obj);

	/**
	 * 
	 * @Title: queryForGroupId
	 * @Description: 生成一个DRS组编号
	 * @param
	 * @return int
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Mar 20, 2013 4:39:16 PM
	 */
	public int queryForGroupId();

	/**
	 * 
	 * @Title: addDRSGroup
	 * @Description: 增加一个DRS组
	 * @param
	 * @return int
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Mar 20, 2013 4:33:29 PM
	 */
	public int addDRSGroup(Map map);

	/**
	 * 
	 * @Title: addDRSGroup
	 * @Description: 增加一个DRS组
	 * @param
	 * @return int
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Mar 20, 2013 4:33:29 PM
	 */
	public int addDRSGroupDetail(Map map);

	/**
	 * 
	 * @Title: queryGroupList
	 * @Description: 查询DRS列表
	 * @param
	 * @return List
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Mar 21, 2013 10:50:04 AM
	 */
	public List queryGroupList(ClusterObj obj);

	/**
	 * 
	 * @Title: deleteGroup
	 * @Description: 删除DRS组
	 * @param
	 * @return int
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Mar 25, 2013 4:04:28 PM
	 */
	public int deleteGroup(Map map);

	/**
	 * 
	 * @Title: deleteGroupDetail
	 * @Description: 删除DRS成员
	 * @param
	 * @return int
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Mar 25, 2013 4:04:42 PM
	 */
	public int deleteGroupDetail(Map map);

	/**
	 * 
	 * @Title: deleteAllGroup
	 * @Description: 删除所有的组（用于同步数据）
	 * @param
	 * @return int
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Apr 2, 2013 2:39:27 PM
	 */
	public int deleteAllGroup();

	/**
	 * 
	 * @Title: listGroupMember
	 * @Description: 查询组成员
	 * @param
	 * @return List
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Apr 2, 2013 3:49:00 PM
	 */
	public List listGroupMember(Map map);
}
