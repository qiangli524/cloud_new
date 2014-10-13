package dao.users;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;
import com.sitech.utils.encrypt.DoubleEncryptUtils;

import domain.cluster.HadoopClusterObj;
import domain.users.HadoopUserObj;

@Repository("hadoopUserDao")
public class HadoopUserDaoImpl extends BaseDao implements HadoopUserDao {

	/**
	 * @Title: queryForListByObj
	 * @Description: 查询列表
	 * @param
	 * @return List<HadoopUserObj>
	 * @throws
	 * @author lipp
	 * @version 1.0
	 * @createtime 2014-2-24 下午3:51:29
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<HadoopUserObj> queryForListByObj(HadoopUserObj hadoopUserObj) {
		List<HadoopUserObj> list = new ArrayList<HadoopUserObj>();
		try {
			if (hadoopUserObj.getPagination() != null) {
				hadoopUserObj.setFIRSTROWNUM(hadoopUserObj.getPagination()
						.getFirstRownum());
				hadoopUserObj.setPAGESIZE(hadoopUserObj.getPagination()
						.getPageSize());
				hadoopUserObj.getPagination().setTotalCount(
						((Integer) getSqlMapClientTemplate().queryForObject(
								"HadoopUsers.queryForCount", hadoopUserObj))
								.intValue());
			}
			list = getSqlMap().queryForList("HadoopUsers.queryForListByObj",
					hadoopUserObj);
		} catch (Exception e) {
			LogHelper.error("HadoopUsers.queryForListByObj: " + e.getMessage()
					+ e.getClass().getName());
		}
		return list;
	}

	/**
	 * @Title: queryForClusterList
	 * @Description: 查询集群的集合
	 * @param
	 * @return List<HadoopClusterObj>
	 * @throws
	 * @author lipp
	 * @version 1.0
	 * @createtime 2014-2-24 下午4:36:35
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<HadoopClusterObj> queryForClusterList(HadoopUserObj userObj) {
		List<HadoopClusterObj> list = new ArrayList<HadoopClusterObj>();
		try {
			list = getSqlMap().queryForList("HadoopUsers.queryForClusterList",
					userObj);
		} catch (Exception e) {
			LogHelper.error("HadoopUsers.queryForClusterList: "
					+ e.getMessage() + e.getClass().getName());
		}
		return list;
	}

	@Override
	public int insertByObj(HadoopUserObj hadoopUserObj) {
		int ret = 0;
		try {
			getSqlMap().insert("HadoopUsers.insertByObj", hadoopUserObj);
		} catch (Exception e) {
			LogHelper.error("HadoopUsers.insertByObj: " + e.getMessage()
					+ e.getClass().getName());
			ret = -1;
		}
		return ret;
	}

	/**
	 * @Title: queryUserListUnAssoed
	 * @Description: 查询尚未被关联的用户
	 * @param
	 * @return List<HadoopUserObj>
	 * @throws
	 * @author lipp
	 * @version 1.0
	 * @createtime 2014-3-3 下午7:13:03
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<HadoopUserObj> queryUserListUnAssoed(HadoopUserObj userObj) {
		List<HadoopUserObj> list = new ArrayList<HadoopUserObj>();
		try {
			if (userObj.getPagination() != null) {
				userObj.setFIRSTROWNUM(userObj.getPagination().getFirstRownum());
				userObj.setPAGESIZE(userObj.getPagination().getPageSize());
				userObj.getPagination().setTotalCount(
						((Integer) getSqlMapClientTemplate().queryForObject(
								"HadoopUsers.queryForCountUnAssoed", userObj))
								.intValue());
			}
			list = getSqlMap().queryForList(
					"HadoopUsers.queryUserListUnAssoed", userObj);
		} catch (Exception e) {
			LogHelper.error("HadoopUsers.queryUserListUnAssoed: "
					+ e.getMessage() + e.getClass().getName());
		}
		return list;
	}

	/**
	 * 
	 * @Title: queryForUserList
	 * @Description: 查询用户
	 * @param
	 * @return List<HadoopUserObj>
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-3-4 下午5:35:59
	 */
	@Override
	public List<HadoopUserObj> queryForUserList(HadoopUserObj hadoopUserObj) {
		List<HadoopUserObj> list = new ArrayList<HadoopUserObj>();
		try {
			if (hadoopUserObj.getPagination() != null) {
				hadoopUserObj.setFIRSTROWNUM(hadoopUserObj.getPagination()
						.getFirstRownum());
				hadoopUserObj.setPAGESIZE(hadoopUserObj.getPagination()
						.getPageSize());
				hadoopUserObj.getPagination().setTotalCount(
						((Integer) getSqlMapClientTemplate().queryForObject(
								"HadoopUsers.queryForUserListCount",
								hadoopUserObj)).intValue());
			}
			list = getSqlMap().queryForList("HadoopUsers.queryForUserList",
					hadoopUserObj);
		} catch (Exception e) {
			LogHelper.error("HadoopUsers.queryForUserList: " + e.getMessage()
					+ e.getClass().getName());
		}
		return list;
	}

	/**
	 * 
	 * @Title: queryConnectUserList
	 * @Description: 查询管理用户
	 * @param
	 * @return List<HadoopUserObj>
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-3-5 下午6:27:33
	 */
	@Override
	public List<HadoopUserObj> queryConnectUserList(HadoopUserObj hadoopUserObj) {
		List<HadoopUserObj> list = new ArrayList<HadoopUserObj>();
		try {
			if (hadoopUserObj.getPagination() != null) {
				hadoopUserObj.setFIRSTROWNUM(hadoopUserObj.getPagination()
						.getFirstRownum());
				hadoopUserObj.setPAGESIZE(hadoopUserObj.getPagination()
						.getPageSize());
				hadoopUserObj.getPagination().setTotalCount(
						((Integer) getSqlMapClientTemplate().queryForObject(
								"HadoopUsers.queryConnectUserListCount",
								hadoopUserObj)).intValue());
			}
			list = getSqlMap().queryForList("HadoopUsers.queryConnectUserList",
					hadoopUserObj);
		} catch (Exception e) {
			LogHelper.error("HadoopUsers.queryConnectUserList: "
					+ e.getMessage() + e.getClass().getName());
		}
		return list;
	}

	/**
	 * @Title: queryForList
	 * @Description: 查询列表，单表查询
	 * @param
	 * @return List<HadoopUserObj>
	 * @throws
	 * @author lipp
	 * @version 1.0
	 * @createtime 2014-3-6 下午4:29:41
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<HadoopUserObj> queryForList(HadoopUserObj hadoopUserObj) {
		List<HadoopUserObj> list = new ArrayList<HadoopUserObj>();
		try {
			list = getSqlMap().queryForList("HadoopUsers.queryForList",
					hadoopUserObj);
		} catch (Exception e) {
			LogHelper.error("HadoopUsers.queryForList: " + e.getMessage()
					+ e.getClass().getName());
		}
		return list;
	}

	/**
	 * @Title: queryForSystemUser
	 * @Description: 查询系统用户
	 * @param
	 * @return List<HadoopUserObj>
	 * @throws
	 * @author lipp
	 * @version 1.0
	 * @createtime 2014-3-6 下午4:38:31
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<HadoopUserObj> queryForSystemUser(HadoopUserObj hadoopUserObj) {
		List<HadoopUserObj> list = new ArrayList<HadoopUserObj>();
		try {
			list = getSqlMap().queryForList("HadoopUsers.queryForSystemUser",
					hadoopUserObj);
			for (HadoopUserObj userObj : list) {
				userObj = decryptPwd(userObj);
			}
		} catch (Exception e) {
			LogHelper.error("HadoopUsers.queryForSystemUser: " + e.getMessage()
					+ e.getClass().getName());
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
	private HadoopUserObj decryptPwd(HadoopUserObj userObj) {
		if (userObj.getPassword() != null && !"".equals(userObj.getPassword())) {
			try {
				userObj.setPassword(DoubleEncryptUtils.decrypt(userObj
						.getPassword()));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return userObj;
	}
	
	/**
	 * 
	 * @Title: queryUserName
	 * @Description: 查询用户名字
	 * @param
	 * @return HadoopQueueRelationObj
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-3-7 上午9:29:51
	 */
	public HadoopUserObj queryUserName(HadoopUserObj obj){
		HadoopUserObj userObj = new HadoopUserObj();
		try {
			userObj = (HadoopUserObj) getSqlMap().queryForObject("HadoopUsers.queryUserName", obj);
		} catch (Exception e) {
			LogHelper.error("HadoopUsers.queryUserName: " + e.getMessage() + e.getClass().getName());
		}
		return userObj;
	}
}
