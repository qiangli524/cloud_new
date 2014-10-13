package service.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.users.HadoopUserDealTaskDao;
import domain.users.HadoopUserDealTaskObj;

@Service("hadoopUserDealTaskService")
public class HadoopUserDealTaskServiceImpl implements HadoopUserDealTaskService {

	@Autowired
	private HadoopUserDealTaskDao hadoopUserDealTaskDao;
	/**
	 * @Title: insertByObj
	 * @Description: 插入记录
	 * @param
	 * @return int
	 * @throws
	 * @author lipp
	 * @version 1.0
	 * @createtime 2014-2-24 上午10:52:45
	 */
	@Override
	public int insertByObj(HadoopUserDealTaskObj taskObj) {
		return hadoopUserDealTaskDao.insertByObj(taskObj);
	}

	/**
	 * @Title: deleteByObj
	 * @Description: 删除记录
	 * @param
	 * @return void
	 * @throws
	 * @author lipp
	 * @version 1.0
	 * @createtime 2014-3-4 下午2:32:58
	 */
	@Override
	public int deleteByObj(HadoopUserDealTaskObj taskObj) {
		return hadoopUserDealTaskDao.deleteByObj(taskObj);
	}

}
