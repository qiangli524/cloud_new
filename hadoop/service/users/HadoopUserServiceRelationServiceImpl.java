package service.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.users.HadoopUserServiceRelationDao;
import domain.users.HadoopUserServiceRelationObj;

@Service("hadoopUserServiceRelationService")
public class HadoopUserServiceRelationServiceImpl implements HadoopUserServiceRelationService{

	@Autowired
	private HadoopUserServiceRelationDao hadoopUserServiceRelationDao;
	/**
	 * @Title: insertByObj
	 * @Description: 插入一条记录
	 * @param
	 * @return int
	 * @throws
	 * @author lipp
	 * @version 1.0
	 * @createtime 2014-2-21 下午3:09:10
	 */
	@Override
	public int insertByObj(
			HadoopUserServiceRelationObj hadoopUserServiceRelationObj) {
		return hadoopUserServiceRelationDao.insertByObj(hadoopUserServiceRelationObj);
	}

	/**
	 * @Title: deleteByObj
	 * @Description: 删除记录
	 * @param
	 * @return int
	 * @throws
	 * @author lipp
	 * @version 1.0
	 * @createtime 2014-2-21 下午3:09:46
	 */
	@Override
	public int deleteByObj(
			HadoopUserServiceRelationObj hadoopUserServiceRelationObj) {
		return hadoopUserServiceRelationDao.deleteByObj(hadoopUserServiceRelationObj);
	}

	/**
	 * @Title: updateStatus
	 * @Description: 更新状态
	 * @param
	 * @return int
	 * @throws
	 * @author lipp
	 * @version 1.0
	 * @createtime 2014-2-24 下午2:28:55
	 */
	@Override
	public int updateStatus(
			HadoopUserServiceRelationObj hadoopUserServiceRelationObj) {
		return hadoopUserServiceRelationDao.updateStatus(hadoopUserServiceRelationObj);
	}

}
