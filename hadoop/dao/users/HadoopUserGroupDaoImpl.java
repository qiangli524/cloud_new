package dao.users;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;
import com.sitech.utils.encrypt.DoubleEncryptUtils;

import domain.cluster.HadoopClusterObj;
import domain.users.HadoopUserGroup;

/**
 * <p>
 * Title: HadoopUserGroupDaoImpl
 * </p>
 * <p>
 * Description: hadoop应用用户组管理
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
 * @createtime 2014-2-19 下午3:47:55
 * 
 */
@Repository("hadoopUserGroupDao")
public class HadoopUserGroupDaoImpl extends BaseDao
		implements
			HadoopUserGroupDao {

	/**
	 * @Title: queryForListByObj
	 * @Description: 查询列表
	 * @param
	 * @return List<HadoopUserGroup>
	 * @throws
	 * @author lipp
	 * @version 1.0
	 * @createtime 2014-2-19 下午3:44:02
	 */
	@SuppressWarnings("unchecked")
	public List<HadoopUserGroup> queryForListByObj(
			HadoopUserGroup hadoopUserGroup) {
		List<HadoopUserGroup> list = new ArrayList<HadoopUserGroup>();
		try {
			if (hadoopUserGroup.getPagination() != null) {
				hadoopUserGroup.setFIRSTROWNUM(hadoopUserGroup.getPagination()
						.getFirstRownum());
				hadoopUserGroup.setPAGESIZE(hadoopUserGroup.getPagination()
						.getPageSize());
				hadoopUserGroup.getPagination().setTotalCount(
						((Integer) getSqlMapClientTemplate().queryForObject(
								"HadoopUserGroup.queryForCount",
								hadoopUserGroup)).intValue());
			}
			list = getSqlMap().queryForList(
					"HadoopUserGroup.queryForListByObj", hadoopUserGroup);
		} catch (Exception e) {
			LogHelper.error("HadoopUserGroup.queryForListByObj: "
					+ e.getMessage() + e.getClass().getName());
		}
		return list;
	}

	/**
	 * @Title: queryForClusterList
	 * @Description: 查询集群的集
	 * @param
	 * @return List<HadoopClusterObj>
	 * @throws
	 * @author lipp
	 * @version 1.0
	 * @createtime 2014-2-19 下午4:40:14
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<HadoopClusterObj> queryForClusterList(
			HadoopUserGroup hadoopUserGroup) {
		List<HadoopClusterObj> list = new ArrayList<HadoopClusterObj>();
		try {
			list = getSqlMap().queryForList(
					"HadoopUserGroup.queryForClusterList", hadoopUserGroup);
		} catch (Exception e) {
			LogHelper.error("HadoopUserGroup.queryForClusterList: "
					+ e.getMessage() + e.getClass().getName());
		}
		return list;
	}

	/**
	 * @Title: queryWholeClusterList
	 * @Description: 查询出所有集群
	 * @param
	 * @return List<HadoopClusterObj>
	 * @throws
	 * @author lipp
	 * @version 1.0
	 * @createtime 2014-2-20 下午2:41:45
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<HadoopClusterObj> queryWholeClusterList(
			HadoopUserGroup userGroup) {
		List<HadoopClusterObj> list = new ArrayList<HadoopClusterObj>();
		try {
			list = getSqlMap().queryForList(
					"HadoopUserGroup.queryWholeClusterList", userGroup);
		} catch (Exception e) {
			LogHelper.error("HadoopUserGroup.queryWholeClusterList: "
					+ e.getMessage() + e.getClass().getName());
		}
		return list;
	}

	/**
	 * @Title: insertByObj
	 * @Description: 插入一条记录
	 * @param
	 * @return int
	 * @throws
	 * @author lipp
	 * @version 1.0
	 * @createtime 2014-2-21 下午2:58:20
	 */
	@Override
	public int insertByObj(HadoopUserGroup hadoopUserGroup) {
		int ret = 0;
		try {
			getSqlMap().insert("HadoopUserGroup.insertByObj", hadoopUserGroup);
		} catch (Exception e) {
			LogHelper.error("HadoopUserGroup.insertByObj: " + e.getMessage()
					+ e.getClass().getName());
			ret = -1;
		}
		return ret;
	}

	/**
	 * 
	 * @Title: queryConnectUseGroupList
	 * @Description: 查询关联用户组
	 * @param
	 * @return List<HadoopUserGroup>
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-3-6 上午10:18:53
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<HadoopUserGroup> queryConnectUseGroupList(
			HadoopUserGroup hadoopUserGroup) {
		List<HadoopUserGroup> list = new ArrayList<HadoopUserGroup>();
		try {
			if (hadoopUserGroup.getPagination() != null) {
				hadoopUserGroup.setFIRSTROWNUM(hadoopUserGroup.getPagination()
						.getFirstRownum());
				hadoopUserGroup.setPAGESIZE(hadoopUserGroup.getPagination()
						.getPageSize());
				hadoopUserGroup
						.getPagination()
						.setTotalCount(
								((Integer) getSqlMapClientTemplate()
										.queryForObject(
												"HadoopUserGroup.queryConnectUseGroupListCount",
												hadoopUserGroup)).intValue());
			}
			list = getSqlMap()
					.queryForList("HadoopUserGroup.queryConnectUseGroupList",
							hadoopUserGroup);
		} catch (Exception e) {
			LogHelper.error("HadoopUserGroup.queryConnectUseGroupList: "
					+ e.getMessage() + e.getClass().getName());
		}
		return list;
	}

	/**
	 * @Title: queryForList
	 * @Description: 查询组列表，单表查询
	 * @param
	 * @return List<HadoopUserGroup>
	 * @throws
	 * @author lipp
	 * @version 1.0
	 * @createtime 2014-3-6 下午3:09:02
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<HadoopUserGroup> queryForList(HadoopUserGroup hadoopUserGroup) {
		List<HadoopUserGroup> list = new ArrayList<HadoopUserGroup>();
		try {
			list = getSqlMap().queryForList("HadoopUserGroup.queryForList",
					hadoopUserGroup);
		} catch (Exception e) {
			LogHelper.error("HadoopUserGroup.queryForList: " + e.getMessage()
					+ e.getClass().getName());
		}
		return list;
	}

	/**
	 * @Title: queryForSystemUser
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param
	 * @return HadoopUserGroup
	 * @throws
	 * @author lipp
	 * @version 1.0
	 * @createtime 2014-3-6 下午3:43:37
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<HadoopUserGroup> queryForSystemUser(
			HadoopUserGroup hadoopUserGroup) {
		List<HadoopUserGroup> list = new ArrayList<HadoopUserGroup>();
		try {
			list = getSqlMap().queryForList(
					"HadoopUserGroup.queryForSystemUser", hadoopUserGroup);
			for (HadoopUserGroup userGroup : list) {
				userGroup = this.decryptPwd(userGroup);
			}
		} catch (Exception e) {
			LogHelper.error("HadoopUserGroup.queryForSystemUser: "
					+ e.getMessage() + e.getClass().getName());
		}
		return list;
	}

	/**
	 * @Title: decryptPwd
	 * @Description: 密码解密
	 * @param
	 * @return HadoopHostUserObj
	 * @throws
	 * @author lipp
	 * @version 1.0
	 * @createtime 2014-3-4 下午5:12:19
	 */
	private HadoopUserGroup decryptPwd(HadoopUserGroup userGroupObj) {
		if (userGroupObj.getPassword() != null
				&& !"".equals(userGroupObj.getPassword())) {
			try {
				userGroupObj.setPassword(DoubleEncryptUtils
						.decrypt(userGroupObj.getPassword()));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return userGroupObj;
	}
}
