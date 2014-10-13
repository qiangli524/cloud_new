package service.tab;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.service.HadoopServiceNodeDao;
import domain.service.DataNodeObj;
import domain.service.NodeManagerObj;

@Service("hadoopServiceNodeService")
public class HadoopServiceNodeServiceImpl implements HadoopServiceNodeService {
	@Autowired
	private HadoopServiceNodeDao hadoopServiceNodeDao;

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
	public List listDatanodeList(DataNodeObj obj) {
		return hadoopServiceNodeDao.queryForDataNodeList(obj);
	}

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
	public List listNodemanagerList(NodeManagerObj obj) {
		return hadoopServiceNodeDao.queryForNodeManagerList(obj);
	}
}
