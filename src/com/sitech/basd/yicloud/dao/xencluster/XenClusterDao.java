package com.sitech.basd.yicloud.dao.xencluster;

import java.util.List;

import com.sitech.basd.yicloud.domain.xentree.XenClusterObj;

public interface XenClusterDao {
	/**
	 * @Title:保存集群信息
	 * @author：duangh
	 * @Company: si-tech
	 * @date : Apr 18, 2012 2:29:27 PM
	 * @version 1.0
	 */
	public int insertByObj(XenClusterObj obj);

	/**
	 * @Title:查询单个集群信息
	 * @author：duangh
	 * @Company: si-tech
	 * @date : Apr 18, 2012 2:29:27 PM
	 * @version 1.0
	 */
	public XenClusterObj queryByObj(XenClusterObj obj);

	/**
	 * @Title:更改集群信息
	 * @author：duangh
	 * @Company: si-tech
	 * @date : Apr 18, 2012 2:29:27 PM
	 * @version 1.0
	 */
	public int updateByObj(XenClusterObj obj);

	/**
	 * @Title:删除集群信息
	 * @author：duangh
	 * @Company: si-tech
	 * @date : Apr 18, 2012 2:29:27 PM
	 * @version 1.0
	 */
	public int deleteByObj(XenClusterObj obj);

	/**
	 * 
	 * @Title: queryForCollection
	 * @Description: 查询集群的uuid等信息
	 * @param
	 * @return XenClusterObj
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Oct 19, 2012 9:10:06 AM
	 */
	public XenClusterObj queryForCollection(XenClusterObj obj);

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
	public int insertForConnection(XenClusterObj obj);

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
	public int updateForConnection(XenClusterObj obj);

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
	public List queryListByObj(XenClusterObj obj);

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
	public int updateByObjUuid(XenClusterObj obj);
}
