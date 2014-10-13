package service.datanode;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.datanode.HadoopDataNodeDao;
import domain.datanode.HadoopDataNodeObj;

@Service("hadoopDataNodeService")
public class HadoopDataNodeServiceImpl implements HadoopDataNodeService{

	@Autowired
	private HadoopDataNodeDao hadoopDataNodeDao;
	
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
	@Override
	public List<HadoopDataNodeObj> queryForListJoinHostTable(HadoopDataNodeObj hadoopDataNodeObj) {
		return hadoopDataNodeDao.queryForListJoinHostTable(hadoopDataNodeObj);
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
	@Override
	public List<HadoopDataNodeObj> queryForStatisticsList(HadoopDataNodeObj hadoopDataNodeObj) {
		return hadoopDataNodeDao.queryForStatisticsList(hadoopDataNodeObj);
	}

}
