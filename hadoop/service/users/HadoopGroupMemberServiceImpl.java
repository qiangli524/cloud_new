package service.users;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.users.HadoopGroupMemberDao;
import domain.users.HadoopGroupMember;

/**
 * <p>
 * Title: HadoopGroupMemberServiceImpl
 * </p>
 * <p>
 * Description: 用户和用户组关系处理
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
 * @createtime 2014-2-24 上午11:04:49
 * 
 */
@Service("hadoopGroupMemberService")
public class HadoopGroupMemberServiceImpl implements HadoopGroupMemberService {

	@Autowired
	private HadoopGroupMemberDao hadoopGroupMemberDao;

	@Override
	public List<HadoopGroupMember> queryForListByObj(
			HadoopGroupMember hadoopGroupMember) {
		return hadoopGroupMemberDao.queryForListByObj(hadoopGroupMember);
	}

	@Override
	public int insertByObj(HadoopGroupMember hadoopGroupMember) {
		return hadoopGroupMemberDao.insertByObj(hadoopGroupMember);
	}

	@Override
	public int deleteByObj(HadoopGroupMember hadoopGroupMember) {
		return hadoopGroupMemberDao.deleteByObj(hadoopGroupMember);
	}

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
	@Override
	public int updateByObj(HadoopGroupMember member) {
		return hadoopGroupMemberDao.updateByObj(member);
	}

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
	@Override
	public List<HadoopGroupMember> queryUnDoneList(
			HadoopGroupMember hadoopGroupMember) {
		return hadoopGroupMemberDao.queryUnDoneList(hadoopGroupMember);
	}

}
