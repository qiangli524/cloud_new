package service.tab;

import java.util.List;

import domain.service.DataNodeObj;
import domain.service.NodeManagerObj;

public interface HadoopServiceNodeService {

	/**
	 * 
	 * @Title: listDatanodeList
	 * @Description: 获取datanode状态列表
	 * @param
	 * @return List
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime 2014-1-17 上午10:50:59
	 */
	public List listDatanodeList(DataNodeObj obj);

	/**
	 * 
	 * @Title: listNodemanagerList
	 * @Description: 获取nodemanager状态列表
	 * @param
	 * @return List
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime 2014-1-17 上午10:51:25
	 */
	public List listNodemanagerList(NodeManagerObj obj);
}
