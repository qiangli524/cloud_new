package dao.users;

import domain.users.HadoopUserServiceRelationObj;

/**
 * <p>Title: HadoopUserServiceRelationDao</p>
 * <p>Description: 用户或用户组与服务关系</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: SI-TECH </p>
 * @author lipp
 * @version 1.0
 * @createtime 2014-2-21 下午2:41:01
 *
 */
public interface HadoopUserServiceRelationDao {

	/**
	 * @Title: insertByObj
	 * @Description: 插入一条记录
	 * @param
	 * @return int
	 * @throws
	 * @author lipp
	 * @version 1.0
	 * @createtime 2014-2-21 下午2:41:52
	 */
	public int insertByObj(HadoopUserServiceRelationObj hadoopUserServiceRelationObj);
	
	/**
	 * @Title: deleteByObj
	 * @Description: 删除记录
	 * @param
	 * @return int
	 * @throws
	 * @author lipp
	 * @version 1.0
	 * @createtime 2014-2-21 下午2:42:24
	 */
	public int deleteByObj(HadoopUserServiceRelationObj hadoopUserServiceRelationObj);

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
	public int updateStatus(
			HadoopUserServiceRelationObj hadoopUserServiceRelationObj);

}
