package com.sitech.basd.component.service.user;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sitech.basd.component.dao.user.UserDao;
import com.sitech.basd.component.domain.user.UserObj;

@Service("userService")
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDao userDao;
	private static final char[] PASS = new char[] { '*', '*', '*', '*', '*', '*', '*', '*', '*',
			'*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*',
			'*', '*', '*', '*', };

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
		List<UserObj> list = userDao.list(obj);
		list = encodeUserPass(list);
		return list;
	}
	/**
	 * @Title: listForProcess
	 * @Description: 用于进程验证身份，无需加密
	 * @param
	 * @return List
	 * @throws
	 * @author qism
	 * @version 1.0
	 * @createtime 2014-7-8 下午5:36:59
	 */
	public List listForProcess(UserObj obj) {
		List<UserObj> list = userDao.list(obj);
		return list;
	}
	@Override
	public UserObj queryUserObjById(UserObj obj) {
		return userDao.queryUserObjById(obj);
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
		return userDao.insertByObj(obj);
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
		List<UserObj> list = userDao.queryDeployUserList(map);
		list = encodeUserPass(list);
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
		return userDao.deleteByObj(obj);
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
		return userDao.updateByObj(obj);
	}

	/**
	 * 
	 * @Title: encodeUserPass
	 * @Description: 处理用户列表显示密码
	 * @param
	 * @return List<UserObj>
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-7-4 下午5:14:56
	 */
	private List<UserObj> encodeUserPass(List<UserObj> list) {
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				UserObj user = list.get(i);
				char[] passwordChar = user.getPassword().toCharArray();
				for (int j = 0; j < passwordChar.length; j++) {
					if (j > 0 && j < passwordChar.length) {
						passwordChar[j] = '*';
					}
				}
				user.setPassword(new String(passwordChar));
				list.set(i, user);
			}
		}
		return list;
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
		return userDao.queryForExampleUserListByObj(obj);
	}

	@Override
	public List<UserObj> queryForListByType(UserObj userObj) {
		// TODO Auto-generated method stub
		return userDao.queryForListByType(userObj);
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
		return userDao.queryAppUserList(map);
	}

	/**
	 * 
	 * @Title: validateNameByEdit
	 * @Description: 验证名字是否重复
	 * @param
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-8-15s
	 */
	public int validateNameByEdit(UserObj obj) {

		return this.userDao.validateNameByEdit(obj);
	}

}
