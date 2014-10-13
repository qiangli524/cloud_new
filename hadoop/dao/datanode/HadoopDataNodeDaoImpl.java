package dao.datanode;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;

import domain.datanode.HadoopDataNodeObj;

@Repository("hadoopDataNodeDao")
public class HadoopDataNodeDaoImpl extends BaseDao implements HadoopDataNodeDao{

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
	@SuppressWarnings("unchecked")
	@Override
	public List<HadoopDataNodeObj> queryForListJoinHostTable(HadoopDataNodeObj hadoopDataNodeObj) {
		List<HadoopDataNodeObj> list = new ArrayList<HadoopDataNodeObj>();
		try {
			if (hadoopDataNodeObj.getPagination() != null) {
				hadoopDataNodeObj.setFIRSTROWNUM(hadoopDataNodeObj.getPagination().getFirstRownum());
				hadoopDataNodeObj.setPAGESIZE(hadoopDataNodeObj.getPagination().getPageSize());
				hadoopDataNodeObj.getPagination().setTotalCount(
						((Integer) getSqlMap().queryForObject("hadoopDataNode.countJoinHostTable", hadoopDataNodeObj))
								.intValue());
			}
			list = getSqlMap().queryForList("hadoopDataNode.queryForListJoinHostTable", hadoopDataNodeObj);
		} catch (Exception e) {
			LogHelper.error("hadoopDataNode.queryForListJoinHostTable: " + e.getMessage() + e.getClass().getName());
		}
		return list;
	}

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
	@SuppressWarnings("unchecked")
	@Override
	public List<HadoopDataNodeObj> queryForStatisticsList(HadoopDataNodeObj hadoopDataNodeObj) {
		List<HadoopDataNodeObj> list = new ArrayList<HadoopDataNodeObj>();
		try {
			list = getSqlMap().queryForList("hadoopDataNode.queryForStatisticsList", hadoopDataNodeObj);
		} catch (Exception e) {
			LogHelper.error("hadoopDataNode.queryForStatisticsList: " + e.getMessage() + e.getClass().getName());
		}
		return list;
	}

	@Override
	public String queryClusterIdByNodeId(String nodeId) {
		// TODO Auto-generated method stub
		String clusterId="" ;
		try {
			clusterId = (String) getSqlMap().queryForObject("hadoopDataNode.queryClusterIdByNodeId", nodeId);
		} catch (Exception e) {
			LogHelper.error("hadoopDataNode.queryClusterIdByNodeId: " + e.getMessage() + e.getClass().getName());
		}
		return clusterId;
	}


}
