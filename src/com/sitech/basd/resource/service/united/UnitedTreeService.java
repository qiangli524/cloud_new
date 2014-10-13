package com.sitech.basd.resource.service.united;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.sitech.basd.resource.domain.united.UnitedTree;
import com.sitech.basd.resource.domain.united.UnitedTreeObj;
import com.sitech.utils.exception.HttpClientException;
import com.sitech.vo.united.HostUnitedVO;

public interface UnitedTreeService {
	/**
	 * 
	 * @Title: initUnitedTree
	 * @Description: 获取统一树
	 * @param
	 * @return List
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Jul 17, 2013 4:42:32 PM
	 */
	public List queryForUnitedTree(HttpServletRequest request)
			throws HttpClientException, SQLException;

	/**
	 * 
	 * @Title: initUnitedTree
	 * @Description: 获取统一树BY worder
	 * @param
	 * @return List
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Jul 17, 2013 4:42:32 PM
	 */
	public List queryForUnitedTreeByWorker() throws SQLException;
	
	/**
	 * 
	 * @Title: queryForTreeList
	 * @Description: 查询树列表
	 * @param
	 * @return List
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Jul 31, 2013 1:01:44 PM
	 */
	public List queryForTreeList(UnitedTreeObj obj) throws SQLException;

	/**
	 * 
	 * @Title: insertByObj
	 * @Description: 插入数据
	 * @param
	 * @return String
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime Aug 2, 2013 4:35:49 PM
	 */
	public String insertByObj(UnitedTreeObj obj) throws SQLException;

	/**
	 * 
	 * @Title: queryByObj
	 * @Description: 查询单个节点
	 * @param
	 * @return UnitedTreeObj
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime Aug 2, 2013 4:40:43 PM
	 */
	public UnitedTreeObj queryByObj(UnitedTreeObj obj) throws SQLException;

	/**
	 * 
	 * @Title: updateByObj
	 * @Description: 更新一条数据
	 * @param
	 * @return int
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @throws Exception
	 * @createtime Jul 22, 2013 2:26:47 PM
	 */
	public int updateByObj(UnitedTreeObj obj) throws SQLException;

	/**
	 * 
	 * @Title: deleteByObj
	 * @Description: 删除一条数据
	 * @param
	 * @return int
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @throws Exception
	 * @createtime Jul 22, 2013 2:27:11 PM
	 */
	public int deleteByObj(UnitedTreeObj obj) throws SQLException;

	/**
	 * 
	 * @Title: queryForAbstract
	 * @Description: 统一树首页的摘要
	 * @param
	 * @return List<IndexAbstract>
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @throws SQLException
	 * @throws Exception
	 * @createtime 2013-8-2 下午11:13:58
	 */
	public List<Map<String, String>> queryForAbstract();

	/**
	 * @Title: queryForTreeListUseIn
	 * @Description: 查询出满足条件的实体集合
	 * @param
	 * @return List<UnitedTreeObj>
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-8-14 上午9:48:43
	 */
	public List<UnitedTreeObj> queryForTreeListUseIn(UnitedTreeObj unitedTreeObj);

	/**
	 * @Title: queryName
	 * @Description: 是否存在相同名字的虚拟机
	 * @param
	 * @return List<UnitedTreeObj>
	 * @throws
	 * @author yanggl_bj
	 * @version 1.0
	 * @throws SQLException
	 * @createtime 2013-8-15
	 */
	public int queryName(UnitedTreeObj unitedTreeObj) throws SQLException;

	/**
	 * 
	 * @Title: queryForUnitedTree
	 * @Description: 查询权限树
	 * @param
	 * @return List
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @throws Exception
	 * @createtime Jul 17, 2013 11:51:37 AM
	 */
	public List<UnitedTree> queryForAuthTree(HttpServletRequest request,
			int userId) throws SQLException;

	/**
	 * 
	 * @Title: queryTreeNodeByName
	 * @Description: 根据名字模糊查询树节点列表
	 * @param
	 * @return List<UnitedTreeObj>
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-9-13 上午10:46:41
	 */
	public List<UnitedTreeObj> queryTreeNodeByName(UnitedTreeObj obj)
			throws SQLException;

	/**
	 * 
	 * @Title: getExpandNodesForVM
	 * @Description: 查询展开虚拟机所需节点集合
	 * @param
	 * @return List
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime 2013-9-13 下午7:01:12
	 */
	public List getExpandNodesForVM(UnitedTreeObj obj) throws SQLException,
			HttpClientException;

	/**
	 * 
	 * @Title: getExpandNodesForVM
	 * @Description: 查询展开虚拟机所需节点集合
	 * @param
	 * @return List
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime 2013-9-13 下午7:01:12
	 */
	public List getExpandNodesForHost(UnitedTreeObj obj) throws SQLException,
			HttpClientException;

	/**
	 * 
	 * @Title: initUnitedTree
	 * @Description: 获取统一树
	 * @param
	 * @return List
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @createtime Jul 17, 2013 4:42:32 PM
	 */
	public List queryUnitedTree(UnitedTreeObj obj) throws HttpClientException,
			SQLException;

	/**
	 * @Title: queryForListForWorkOrder
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param
	 * @return List<UnitedTreeObj>
	 * @throws
	 * @author lipp
	 * @version 1.0
	 * @createtime 2013-12-19 下午3:49:40
	 */
	public List<UnitedTreeObj> queryForListForWorkOrder(
			UnitedTreeObj unitedTreeObj);

	/**
	 * @Title: queryForCenterTreeList
	 * @Description: 统计数据中心
	 * @param
	 * @return List<UnitedTreeObj>
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2014-1-3 下午6:52:09
	 */
	public List<UnitedTreeObj> queryForCenterTreeList(UnitedTreeObj unitedTreeObj);
	
	/**
	 * 
	 * @Title: enterMaintenanceMode
	 * @Description: 主机进入维护模式
	 * @param
	 * @return String
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime 2014-2-24 下午1:41:38
	 */
	public String enterMaintenanceMode(String vType,HostUnitedVO vo);
	
	/**
	 * 
	 * @Title: exitMaintenanceMode
	 * @Description: 主机退出维护模式
	 * @param
	 * @return String
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime 2014-2-24 下午1:43:38
	 */
	public String exitMaintenanceMode(String vType,HostUnitedVO vo);

	 /**  
	  * @Title: queryForListByObj  
	  * @Description: 查询列表
	  * @return List<UnitedTreeObj>   
	  * @throws  
	  * @Date 2014-6-1 上午10:52:53
	  * @author lipp
	  * @param parentObj
	  * @return
	  */
	public List<UnitedTreeObj> queryForListByObj(UnitedTreeObj obj);
	
	 /**  
	  * @Title: getUnitedTreeObj  
	  * @Description: 获取树对象
	  * @return UnitedTreeObj   
	  * @throws  
	  * @Date 2014-6-17 下午2:08:18
	  * @author lipp
	  * @param id 当前节点的id
	  * @param entityType  目标节点的类型
	  * @param count 默认0
	  * @return
	  */
	public UnitedTreeObj getUnitedTreeObj(String id, String entityType, int count);
}
