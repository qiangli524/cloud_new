package dao.cluster;

import java.util.List;

import domain.cluster.HadoopClusterObj;

/**
 * 
 * <p>
 * Title: HadoopClusterDao
 * </p>
 * <p>
 * Description: 对集群表的相关操作
 * </p>
 * <p>
 * Copyright: Copyright (c) 2014
 * </p>
 * <p>
 * Company: SI-TECH
 * </p>
 * 
 * @author yanggl
 * @version 1.0
 * @createtime 2014-1-13 下午4:32:30
 * 
 */
public interface HadoopClusterDao {
	/**
	 * 
	 * @Title: insertByObj
	 * @Description: 插入一条数据
	 * @param
	 * @return int
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-1-13 下午4:32:15
	 */
	public int insertByObj(HadoopClusterObj obj);
	
	/**
	 * 查询集群的所有列表
	 * @Title: queryClusterList
	 * @param
	 * @return List<HadoopClusterObj>
	 * @throws
	 * @author shijc
	 * @version 1.0
	 * @createtime 2014-1-14 上午11:17:37
	 */
	public List<HadoopClusterObj> queryClusterList(HadoopClusterObj obj);
}
