package dao.users;

import domain.users.HadoopUserDealTaskObj;

public interface HadoopUserDealTaskDao {

	/**
	 * @Title: insertByObj
	 * @Description: 插入记录
	 * @param
	 * @return int
	 * @throws
	 * @author lipp
	 * @version 1.0
	 * @createtime 2014-2-24 上午10:46:30
	 */
	public int insertByObj(HadoopUserDealTaskObj taskObj);

	/**
	 * @Title: deleteByObj
	 * @Description: 删除记录
	 * @param
	 * @return int
	 * @throws
	 * @author lipp
	 * @version 1.0
	 * @createtime 2014-3-4 下午2:39:39
	 */
	public int deleteByObj(HadoopUserDealTaskObj taskObj);
}
