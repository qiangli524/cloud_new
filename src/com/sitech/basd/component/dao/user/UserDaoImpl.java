package com.sitech.basd.component.dao.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.sitech.basd.component.domain.user.UserObj;
import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;
import com.sitech.utils.encrypt.DoubleEncryptUtils;

@Repository("userDao")
public class UserDaoImpl extends BaseDao implements UserDao {
	/**
	 * 
	 * @Title: list
	 * @Description: 查询用户管理列表
	 * @param
	 * @return List
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime May 22, 2013 11:36:27 AM
	 */
	public List list(UserObj obj) {
		List list = new ArrayList();
		try {
			if (obj.getPagination() != null) {
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj.getPagination().setTotalCount(
						((Integer) getSqlMap().queryForObject("UserManage.queryForCount", obj))
								.intValue());
			}
			list = getSqlMap().queryForList("UserManage.queryForList", obj);
			if (list != null) {
				// 对用用户密码解密
				for (int i = 0; i < list.size(); i++) {
					UserObj oprObj = (UserObj) list.get(i);
					oprObj = decryptPwd(oprObj);
					list.set(i, oprObj);
				}
			}
		} catch (Exception sqlException) {
			sqlException.printStackTrace();
			LogHelper.error("UserManage.queryForList:" + sqlException.getMessage()
					+ getClass().getName());
		}
		return list;
	}

	@Override
	public UserObj queryUserObjById(UserObj obj) {
		UserObj userObj = null;
		try {
			userObj = (UserObj) getSqlMap().queryForObject("UserManage.queryForObjByID", obj);
			// 对用户密码解密
			userObj = decryptPwd(userObj);
		} catch (Exception sqlException) {
			sqlException.printStackTrace();
			LogHelper.error("UserManage.queryForObjByID:" + sqlException.getMessage()
					+ getClass().getName());
		}
		return userObj;
	}

	/**
	 * 
	 * @Title: insertByObj
	 * @Description: 增加一条IP用户记录
	 * @param
	 * @return int
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime May 27, 2013 8:53:12 AM
	 */
	public int insertByObj(UserObj obj) {
		int ret = 0;
		try {
			// 对用户密码加密
			obj = encryptPwd(obj);
			Object o = getSqlMap().insert("UserManage.insertByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception e) {
			ret = -1;
			LogHelper.error("UserManage.insertByObj:" + e.getMessage());
		}
		return ret;
	}

	/**
	 * 
	 * @Title: queryDeployUserList
	 * @Description: 查询部署实例及基准应用主机对应的用户信息
	 * @param
	 * @return List
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime May 29, 2013 10:49:12 AM
	 */
	public List queryDeployUserList(Map map) {
		List list = new ArrayList();
		try {
			list = getSqlMap().queryForList("UserManage.queryDeployUserList", map);
			// 对用户密码解密
			for (Object object : list) {
				UserObj oprObj = (UserObj) object;
				decryptPwd(oprObj);
			}
		} catch (Exception sqlException) {
			sqlException.printStackTrace();
			LogHelper.error("UserManage.queryDeployUserList:" + sqlException.getMessage()
					+ getClass().getName());
		}
		return list;
	}

	/**
	 * 
	 * @Title: deleteByObj
	 * @Description: 删除一条记录
	 * @param
	 * @return int
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Jun 26, 2013 3:54:15 PM
	 */
	public int deleteByObj(UserObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().delete("UserManage.deleteByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception e) {
			ret = -1;
			LogHelper.error("UserManage.deleteByObj:" + e.getMessage());
		}
		return ret;
	}

	/**
	 * 
	 * @Title: insertByObj
	 * @Description: 增加一条IP用户记录
	 * @param
	 * @return int
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime May 27, 2013 8:53:12 AM
	 */
	public int updateByObj(UserObj obj) {
		int ret = 0;
		try {
			// 对用户密码加密
			obj = encryptPwd(obj);
			Object o = getSqlMap().update("UserManage.updateByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception e) {
			ret = -1;
			LogHelper.error("UserManage.updateByObj:" + e.getMessage());
		}
		return ret;
	}

	/**
	 * 
	 * @Title: queryForExampleUserListByObj
	 * @Description: 查询部署实例的用户
	 * @param
	 * @return List
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime Jul 9, 2013 10:16:38 AM
	 */
	public List queryForExampleUserListByObj(UserObj obj) {
		List list = new ArrayList();
		try {
			list = getSqlMap().queryForList("UserManage.queryForExampleUserListByObj", obj);
			if (list != null) {
				// 对用用户密码解密
				for (int i = 0; i < list.size(); i++) {
					UserObj oprObj = (UserObj) list.get(i);
					oprObj = decryptPwd(oprObj);
					list.set(i, oprObj);
				}
			}
		} catch (Exception sqlException) {
			sqlException.printStackTrace();
			LogHelper.error("UserManage.queryForExampleUserListByObj:" + sqlException.getMessage()
					+ getClass().getName());
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserObj> queryForListByType(UserObj userObj) {
		List<UserObj> list = new ArrayList<UserObj>();
		try {
			list = getSqlMap().queryForList("UserManage.queryForListByType", userObj);
			// 对用户密码进行解密
			if (list != null) {
				// 对用用户密码解密
				for (int i = 0; i < list.size(); i++) {
					UserObj oprObj = (UserObj) list.get(i);
					oprObj = decryptPwd(oprObj);
					list.set(i, oprObj);
				}
			}
		} catch (Exception e) {
			LogHelper.error("UserManage.queryForListByType: " + e.getMessage()
					+ e.getClass().getName());
		}
		return list;
	}

	/**
	 * 
	 * @Title: queryAppUserList
	 * @Description: 查询基准应用的主机，用户信息，传值是map，返回有用信息的map
	 * @author duangh
	 * @date Aug 6, 2013 4:50:53 PM
	 * @param map
	 * @return
	 */
	@Override
	public Map<String, String> queryAppUserList(Map map) {
		Map<String, String> result = null;
		try {
			result = (Map<String, String>) getSqlMap().queryForObject(
					"UserManage.queryAppUserList", map);
			// 对用户密码进行解密
			result.put("PASSWORD", DoubleEncryptUtils.decrypt(result.get("PASSWORD")));
		} catch (Exception e) {
			LogHelper.error("UserManage.queryAppUserList: " + e.getMessage()
					+ e.getClass().getName());
		}
		return result;
	}

	/**
	 * 
	 * @Title: validateNameByEdit
	 * @Description: 验证名字是否重复
	 * @param
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-8-15
	 */
	public int validateNameByEdit(UserObj obj) {
		int ret = 0;
		try {
			Object ob = getSqlMap().queryForObject("UserManage.validateNameByEdit", obj);
			if (ob != null) {
				ret = Integer.parseInt(ob.toString());
			}
		} catch (Exception e) {
			ret = -1;
			LogHelper.error("UserManage.validateNameByEdit:" + e.getMessage()
					+ e.getClass().getName());
		}
		return ret;
	}

	/**
	 * 
	 * @Title: encryptPwd
	 * @Description: 对用户的密码进行双层加密
	 * @param
	 * @return void
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime 2013-10-18 下午3:43:03
	 */
	public UserObj encryptPwd(UserObj obj) throws Exception {
		if (obj.getPassword() != null && !"".equals(obj.getPassword())) {
			obj.setPassword(DoubleEncryptUtils.encrypt(obj.getPassword()));
		}
		return obj;
	}

	/**
	 * 
	 * @Title: decoderPwd
	 * @Description: 对用户的密码进行双层解密
	 * @param
	 * @return void
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @throws Exception
	 * @createtime 2013-10-18 下午3:43:39
	 */
	public UserObj decryptPwd(UserObj obj) throws Exception {
		if (obj.getPassword() != null && !"".equals(obj.getPassword())) {
			obj.setPassword(DoubleEncryptUtils.decrypt(obj.getPassword()));
		}
		return obj;
	}
}
