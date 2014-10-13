package service.datanode;

import java.util.List;

import domain.datanode.HadoopDataNodeObj;

/**
 * <p>Title: HadoopDataNodeService</p>
 * <p>Description: datanode逻辑接口</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: SI-TECH </p>
 * @author lipengpeng
 * @version 1.0
 * @createtime 2014-1-14 下午7:28:08
 *
 */
public interface HadoopDataNodeService {

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
}
