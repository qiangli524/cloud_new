package dao.host;

import java.util.List;

import domain.host.HadoopHostInfoObj;
import domain.tree.HadoopTreeObj;

/**
 * <p>
 * Title: HadoopHostInfoDao
 * </p>
 * <p>
 * Description: hadoop主机持久层接口
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
 * @createtime 2014-1-6 下午10:29:54
 * 
 */
public interface HadoopHostInfoDao {

	/**
	 * @Title: queryForHostList
	 * @Description: 查询主机列表
	 * @param
	 * @return List<HadoopHostInfoObj>
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @createtime 2014-1-6 下午10:31:57
	 */
	public List<HadoopHostInfoObj> queryForHostList(HadoopHostInfoObj obj);

	/**
	 * @Title: queryForListUnderServiceNode
	 * @Description: 查询服务节点下的主机
	 * @param
	 * @return List<HadoopHostInfoObj>
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2014-1-6 下午10:31:57
	 */
	public List<HadoopHostInfoObj> queryForListUnderServiceNode(
			HadoopTreeObj hadoopTreeObj);

	/**
	 * 
	 * @Title: insertByObj
	 * @Description: 插入一条数据
	 * @param
	 * @return int
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-1-7 上午11:20:09
	 */
	public int insertByObj(HadoopHostInfoObj obj);

	/**
	 * 
	 * @Title: queryByObj
	 * @Description: 查询一条主机信息
	 * @param
	 * @return HadoopHostInfoObj
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-1-8 上午11:32:55
	 */
	public HadoopHostInfoObj queryByObj(HadoopHostInfoObj obj);
	/**
	 * 通过ID等查询条件查询主机列表
	 * 
	 * @Title: queryForHadoopHostLists
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param
	 * @return List<HadoopHostInfoObj>
	 * @throws
	 * @author shijc
	 * @version 1.0
	 * @createtime 2014-1-9 下午3:40:06
	 */
	public List<HadoopHostInfoObj> queryForHadoopHostLists(HadoopHostInfoObj obj);

	/**
	 * 
	 * @Title: updateByObj
	 * @Description:修改主机信息
	 * @param
	 * @return int
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-1-10 下午3:58:59
	 */
	public int updateByObj(HadoopHostInfoObj obj);

	/**
	 * 
	 * @Title: updHostInfoByObj
	 * @Description: 修改主机管理信息
	 * @param
	 * @return int
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-1-11 下午2:35:39
	 */
	public int updHostInfoByObj(HadoopHostInfoObj obj);

	/**
	 * 
	 * @Title: deleteByObj
	 * @Description: 删除主机信息
	 * @param
	 * @return int
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-1-11 下午2:58:40
	 */
	public int deleteByObj(HadoopHostInfoObj obj);

	/**
	 * 查询主机和集群的关系
	 * 
	 * @Title: queryForHadoopHostAndClusterInfoObj
	 * @param
	 * @return HadoopHostInfoObj
	 * @throws
	 * @author shijc
	 * @version 1.0
	 * @createtime 2014-1-15 下午4:31:40
	 */
	public HadoopHostInfoObj queryForHadoopHostAndClusterInfoObj(
			HadoopHostInfoObj obj);

	/**
	 * 
	 * @Title: queryHostInfoByNode
	 * @Description: 通过主机节点查询主机列表
	 * @param
	 * @return List<HadoopHostInfoObj>
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-1-16 上午9:57:21
	 */
	public List<HadoopHostInfoObj> queryHostInfoByHostNode(HadoopHostInfoObj obj);

	/**
	 * 
	 * @Title: queryHostInfoByOtherNode
	 * @Description: 通过其他节点查询主机列表
	 * @param
	 * @return List<HadoopHostInfoObj>
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-1-16 上午11:33:49
	 */
	public List<HadoopHostInfoObj> queryHostInfoByOtherNode(
			HadoopHostInfoObj obj);
	/**
	 * 
	 * @Title: queryHostInfoByDCNode
	 * @Description: 查询数据中心节点的主机列表
	 * @param
	 * @return List<HadoopHostInfoObj>
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-1-16 下午3:46:58
	 */
	public List<HadoopHostInfoObj> queryHostInfoByDCNode(HadoopHostInfoObj obj);

	/**
	 * 
	 * @Title: queryLogHostInfoList
	 * @Description: jvm 中查询 log输出 error和fatal的次数的主机列表
	 * @param
	 * @return List<HadoopHostInfoObj>
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-1-18 下午5:28:26
	 */
	public List<HadoopHostInfoObj> queryLogHostInfoList(HadoopHostInfoObj obj);

	/**
	 * @Title: queryForExampleList
	 * @Description: 查询节点列表
	 * @param
	 * @return List<HadoopHostInfoObj>
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2014-1-20 下午7:34:43
	 */
	public List<HadoopHostInfoObj> queryForExampleList(
			HadoopHostInfoObj hadoopHostInfoObj);

	/**
	 * @Title: queryForHostListJoinColl
	 * @Description: 联合监控表查询
	 * @param
	 * @return List
	 * @throws
	 * @author lipp
	 * @version 1.0
	 * @createtime 2014-2-26 下午7:28:34
	 */
	public List queryForHostListJoinColl(HadoopHostInfoObj hostForm);

	/**
	 * 
	 * @Title: queryHostList
	 * @Description: 查询主机信息
	 * @param
	 * @return List<HadoopHostInfoObj>
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-4-9 下午7:48:00
	 */
	public List<HadoopHostInfoObj> queryHostList(HadoopHostInfoObj obj);

	/**
	 * 
	 * @Title: queryAllHostForTree
	 * @Description: 查询树上的所有主机
	 * @param
	 * @return List<HadoopHostInfoObj>
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-4-15 下午5:27:34
	 */
	public List<HadoopHostInfoObj> queryAllHostForTree(HadoopTreeObj obj);
}
