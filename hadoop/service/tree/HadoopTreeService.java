package service.tree;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import dao.tree.HadoopTreeDao;
import domain.service.ServiceObj;
import domain.tree.HadoopTreeObj;

public interface HadoopTreeService {
	/**
	 * 
	 * @Title: queryForTree
	 * @Description: 查询树
	 * @param
	 * @return List
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime 2014-1-4 下午8:56:56
	 */
	public List queryForTree(HadoopTreeObj obj);

	/**
	 * @Title: insertByObj
	 * @Description: 插入一条记录
	 * @param
	 * @return int
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2014-1-5 下午2:07:36
	 */
	public int insertByObj(HadoopTreeObj hadoopTreeObj);

	/**
	 * @Title: updateByObj
	 * @Description: 更新一条记录
	 * @param
	 * @return int
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2014-1-5 下午2:07:59
	 */
	public int updateByObj(HadoopTreeObj hadoopTreeObj);

	/**
	 * @Title: deleteByObj
	 * @Description: 删除一条记录
	 * @param
	 * @return int
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2014-1-5 下午2:08:14
	 */
	public int deleteByObj(HadoopTreeObj hadoopTreeObj);

	/**
	 * 
	 * @Title: queryForTopoTree
	 * @Description: 用于生成拓扑图，返回特殊list
	 * @param
	 * @return List
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime 2014-1-7 上午8:38:43
	 */
	/**
	 * 
	 * @Title: recursiveTree
	 * @Description: 用于生成拓扑图，返回特殊obj
	 * @param
	 * @return List
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime 2014-1-7 上午8:38:43
	 */

	public HadoopTreeObj recursiveTree(String parentId,
			HadoopTreeDao hadoopTreeDao) throws SQLException;

	/**
	 * @Title: queryForListByObj
	 * @Description: 根据条件查询集合
	 * @param
	 * @return List<HadoopTreeObj>
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2014-1-7 下午9:29:51
	 */
	public List<HadoopTreeObj> queryForListByObj(HadoopTreeObj hadoopTreeObj);

	/**
	 * @Title: getExpandNodes
	 * @Description: 查询扩展节点，获取节点id
	 * @param
	 * @return List<String>
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @createtime 2014-1-7 下午9:29:51
	 */
	public List<String> getExpandNodes(HadoopTreeObj hadoopTreeObj);

	/**
	 * @Title: queryForServiceList
	 * @Description: 查询服务集合
	 * @param
	 * @return List<ServiceNodeObj>
	 * @throws
	 * @author lipp
	 * @version 1.0
	 * @createtime 2014-2-20 下午4:08:59
	 */
	public List<ServiceObj> queryForServiceList(HadoopTreeObj treeObj);

	/**
	 * @Title: acquireChildNode
	 * @Description: 根据子节点类型查询子节点集合，递归
	 * @param
	 * @return List<HadoopTreeObj>
	 * @throws
	 * @author lipp
	 * @version 1.0
	 * @createtime 2014-2-27 下午4:31:40
	 */
	public List<HadoopTreeObj> acquireChildNode(String childNodeType,
			String parentNodeServiceType, HadoopTreeObj hadoopTreeObj,
			List<HadoopTreeObj> retList);
	
	/**
	 * 
	 * @Title: queryForHostNodeList
	 * @Description: 查询主机节点(用于服务类型是namenode的节点)
	 * @param
	 * @return List<HadoopTreeObj>
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-4-14 下午5:48:25
	 */
	public List<HadoopTreeObj> queryForHostNodeList(HadoopTreeObj obj);
	
	/**
	 * 
	 * @Title: queryTopoData
	 * @Description: 获取拓扑图数据
	 * @param
	 * @return String
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime Apr 23, 2014 4:14:16 PM
	 */
	public String queryTopoData(String currentNodeId,String showType);

	/**
	 * 
	 * @Title: queryNodeType
	 * @Description: 查询节点类型判断资源池有哪些节点
	 * @param
	 * @return Map<String,String>
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-4-29 下午4:13:25
	 */
	public Map<String, String> queryNodeType(HadoopTreeObj obj);
	
	/**
	 * @Title: setStatus
	 * @Description: 设置节点状态
	 * @param
	 * @return List<HadoopTreeObj>
	 * @throws
	 * @author lipp
	 * @version 1.0
	 * @createtime 2014-2-26 下午3:45:40
	 */
	public List<HadoopTreeObj> setStatus(List<HadoopTreeObj> resultList);
}
