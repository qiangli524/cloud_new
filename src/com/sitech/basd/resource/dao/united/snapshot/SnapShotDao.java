package com.sitech.basd.resource.dao.united.snapshot;

import java.sql.SQLException;
import java.util.List;

import com.sitech.basd.resource.domain.united.SnapShotObj;

public interface SnapShotDao {
	/**
	 * 
	 * @Title: queryForSnapShotList
	 * @Description: 查询虚拟机快照列表
	 * @param
	 * @return List
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime 2014-4-10 下午6:06:33
	 */
	public List<SnapShotObj> queryForSnapShotList(SnapShotObj obj);

	/**
	 * 
	 * @Title: insertByObj
	 * @Description: 插入一条快照信息
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime 2014-4-10 下午7:31:34
	 */
	public String insertByObj(SnapShotObj obj);

	/**
	 * 
	 * @Title: deleteByObj
	 * @Description: 删除一条记录
	 * @param
	 * @return int
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime 2014-4-10 下午7:33:23
	 */
	public int deleteByObj(SnapShotObj obj);

	/** 
	*
	* @Title: getSnapshotCountByVm 
	* @Description: TODO(根据虚拟机ID或者其快照个数) 
	* @param @param vm_uuid
	* @param @return    设定文件 
	* @return Integer    返回类型 
	* @author wanglei_bj@si-tech.com.cn 
	* @throws 
	*/
	public Integer getSnapshotCountByVm(SnapShotObj obj);

	/**
	 * @throws SQLException  
	*
	* @Title: queryForCount 
	* @Description: TODO(查询地区内、用户的快照数量) 
	* @param @param obj
	* @param @return    设定文件 
	* @return Integer    返回类型 
	* @author wanglei_bj@si-tech.com.cn 
	* @throws 
	*/
	public Integer queryForCount(SnapShotObj obj) throws SQLException;

}
