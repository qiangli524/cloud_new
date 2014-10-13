package dao.cluster;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;

import domain.cluster.HadoopClusterObj;

/**
 * 
 * <p>
 * Title: HadoopClusterDaoImpl
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
 * @createtime 2014-1-13 下午4:33:51
 * 
 */
@Repository("hadoopClusterDao")
public class HadoopClusterDaoImpl extends BaseDao implements HadoopClusterDao {
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
	@Override
	public int insertByObj(HadoopClusterObj obj) {
		int ret = 0;
		try {
			Object ob = getSqlMap().insert("HadoopCluster.insertByObj", obj);
			if (ob != null) {
				ret = Integer.parseInt(ob.toString());
			}
		} catch (SQLException e) {
			ret = -1;
			logger.error("HadoopCluster.insertByObj:" + e.getMessage()
					+ e.getClass().getName());
		}
		return ret;
	}

	@Override
	public List<HadoopClusterObj> queryClusterList(HadoopClusterObj obj) {
		List<HadoopClusterObj> list=new ArrayList<HadoopClusterObj>();
		try {
			list = getSqlMap().queryForList("HadoopCluster.queryClusterList", obj);
		} catch (SQLException e) {
			logger.error("HadoopCluster.queryClusterList:" + e.getMessage()
					+ e.getClass().getName());
		}
		return list;
	};
}
