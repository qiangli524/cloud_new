package dao.users;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;

import domain.users.HadoopGroupMember;

@Repository("hadoopGroupMemberDao")
public class HadoopGroupMemberDaoImpl extends BaseDao
		implements
			HadoopGroupMemberDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<HadoopGroupMember> queryForListByObj(
			HadoopGroupMember hadoopGroupMember) {
		List<HadoopGroupMember> list = new ArrayList<HadoopGroupMember>();
		try {
			list = getSqlMap().queryForList(
					"HadoopGroupMember.queryForListByObj", hadoopGroupMember);
		} catch (Exception e) {
			LogHelper.error("HadoopGroupMember.queryForListByObj: "
					+ e.getMessage() + e.getClass().getName());
		}
		return list;
	}

	@Override
	public int insertByObj(HadoopGroupMember hadoopGroupMember) {
		int ret = 0;
		try {
			getSqlMap().insert("HadoopGroupMember.insertByObj",
					hadoopGroupMember);
		} catch (Exception e) {
			LogHelper.error("HadoopGroupMember.insertByObj: " + e.getMessage()
					+ e.getClass().getName());
			ret = -1;
		}
		return ret;
	}

	@Override
	public int deleteByObj(HadoopGroupMember hadoopGroupMember) {
		int ret = 0;
		try {
			getSqlMap().delete("HadoopGroupMember.deleteByObj",
					hadoopGroupMember);
		} catch (Exception e) {
			LogHelper.error("HadoopGroupMember.deleteByObj: " + e.getMessage()
					+ e.getClass().getName());
			ret = -1;
		}
		return ret;
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
		int ret = 0;
		try {
			getSqlMap().update("HadoopGroupMember.updateByObj", member);
		} catch (Exception e) {
			LogHelper.error("HadoopGroupMember.updateByObj: " + e.getMessage()
					+ e.getClass().getName());
			ret = -1;
		}
		return ret;
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
	@SuppressWarnings("unchecked")
	@Override
	public List<HadoopGroupMember> queryUnDoneList(
			HadoopGroupMember hadoopGroupMember) {
		List<HadoopGroupMember> list = new ArrayList<HadoopGroupMember>();
		try {
			list = getSqlMap().queryForList(
					"HadoopGroupMember.queryUnDoneList", hadoopGroupMember);
		} catch (Exception e) {
			LogHelper.error("HadoopGroupMember.queryUnDoneList");
		}
		return list;
	}

}
