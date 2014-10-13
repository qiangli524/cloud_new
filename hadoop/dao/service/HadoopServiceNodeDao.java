package dao.service;

import java.util.List;

import domain.service.DataNodeObj;
import domain.service.HadoopServiceNodeObj;
import domain.service.NodeManagerObj;

/**
 * <p>
 * Title: HadoopServiceNodeDao
 * </p>
 * <p>
 * Description: 服务节点持久层接口
 * </p>
 * <p>
 * Copyright: Copyright (c) 2014
 * </p>
 * <p>
 * Company: SI-TECH
 * </p>
 * 
 * @author lipengpeng
 * @version 1.0
 * @createtime 2014-1-6 下午3:17:22
 * 
 */
public interface HadoopServiceNodeDao {

	/**
	 * @Title: insertByObj
	 * @Description: 插入一条记录
	 * @param
	 * @return int
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2014-1-6 下午3:17:46
	 */
	public int insertByObj(HadoopServiceNodeObj hadoopServiceNodeObj);

	/**
	 * 
	 * @Title: queryForDataNodeList
	 * @Description: 查询datanode服务对应的服务实例状态列表
	 * @param
	 * @return List
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime 2014-1-11 下午7:58:03
	 */
	public List queryForDataNodeList(DataNodeObj data);

	/**
	 * 
	 * @Title: queryForNodeManagerList
	 * @Description: 查询nodemanager服务对应的服务实例状态列表
	 * @param
	 * @return List
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime 2014-1-11 下午7:58:58
	 */
	public List queryForNodeManagerList(NodeManagerObj node);
	
}
