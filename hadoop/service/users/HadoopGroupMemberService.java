package service.users;

import java.util.List;

import domain.users.HadoopGroupMember;

/**
 * <p>
 * Title: HadoopGroupMemberService
 * </p>
 * <p>
 * Description: 用户和用户组关系
 * </p>
 * <p>
 * Copyright: Copyright (c) 2014
 * </p>
 * <p>
 * Company: SI-TECH
 * </p>
 * 
 * @author lipp
 * @version 1.0
 * @createtime 2014-2-24 上午11:01:56
 * 
 */
public interface HadoopGroupMemberService {

	/**
	 * @Title: queryForListByObj
	 * @Description: 查询列表
	 * @param
	 * @return List<HadoopGroupMember>
	 * @throws
	 * @author lipp
	 * @version 1.0
	 * @createtime 2014-2-24 上午11:02:48
	 */
	public List<HadoopGroupMember> queryForListByObj(
			HadoopGroupMember hadoopGroupMember);

	/**
	 * @Title: insertByObj
	 * @Description: 插入记录
	 * @param
	 * @return int
	 * @throws
	 * @author lipp
	 * @version 1.0
	 * @createtime 2014-2-24 上午11:03:27
	 */
	public int insertByObj(HadoopGroupMember hadoopGroupMember);

	/**
	 * @Title: deleteByObj
	 * @Description: 删除记录
	 * @param
	 * @return int
	 * @throws
	 * @author lipp
	 * @version 1.0
	 * @createtime 2014-2-24 上午11:04:03
	 */
	public int deleteByObj(HadoopGroupMember hadoopGroupMember);

	/**
	 * @Title: updateByObj
	 * @Description: 更新记录
	 * @param
	 * @return void
	 * @throws
	 * @author lipp
	 * @version 1.0
	 * @createtime 2014-3-4 上午10:01:34
	 */
	public int updateByObj(HadoopGroupMember member);

	/**
	 * @Title: queryUnDoneList
	 * @Description: 查询状态不符合的列表
	 * @param
	 * @return List<HadoopGroupMember>
	 * @throws
	 * @author lipp
	 * @version 1.0
	 * @createtime 2014-3-5 上午8:36:00
	 */
	public List<HadoopGroupMember> queryUnDoneList(
			HadoopGroupMember hadoopGroupMember);
}
