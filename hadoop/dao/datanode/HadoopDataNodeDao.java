package dao.datanode;

import java.util.List;

import domain.datanode.HadoopDataNodeObj;

public interface HadoopDataNodeDao {

	/**
	 * @Title: queryForListJoinHostTable
	 * @Description: 关联主机表查询
	 * @param
	 * @return List<HadoopDataNodeObj>
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2014-1-14 下午7:31:32
	 */
	public List<HadoopDataNodeObj> queryForListJoinHostTable(HadoopDataNodeObj hadoopDataNodeObj);

	/**
	 * @Title: queryForStatisticsList
	 * @Description: 联合cluster表和主机表查询符合条件的记录
	 * @param
	 * @return List<HadoopDataNodeObj>
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2014-1-15 上午9:54:33
	 */
	public List<HadoopDataNodeObj> queryForStatisticsList(HadoopDataNodeObj hadoopDataNodeObj);
	/**
	 * @Title: queryClusterIdByNodeId
	 * @Description: 根据nodeId查询出该集群hdfsID或MapReduceId
	 * @param
	 * @return String
	 * @throws
	 * @author qism
	 * @version 1.0
	 * @createtime 2014-6-19 下午6:34:21
	 */
	public String queryClusterIdByNodeId(String nodeId);
}
