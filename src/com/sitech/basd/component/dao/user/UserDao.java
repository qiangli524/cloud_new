package com.sitech.basd.component.dao.user;

import java.util.List;
import java.util.Map;

import com.sitech.basd.component.domain.user.UserObj;

public interface UserDao {
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
	public List list(UserObj obj);

	/**
	 * 根据id查询用户主机信息
	 * 
	 * @param obj
	 * @return
	 */
	public UserObj queryUserObjById(UserObj obj);

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
	public int insertByObj(UserObj obj);

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
	public List queryDeployUserList(Map map);

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
	public int deleteByObj(UserObj obj);

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
	public int updateByObj(UserObj obj);

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
	public List queryForExampleUserListByObj(UserObj obj);

	public List<UserObj> queryForListByType(UserObj userObj);

	/**
	 * 
	 * @Title: queryAppUserList
	 * @Description: 查询基准应用的主机，用户信息，传值是map，返回有用信息的map
	 * @author duangh
	 * @date Aug 6, 2013 4:50:53 PM
	 * @param map
	 * @return
	 */
	public Map<String, String> queryAppUserList(Map map);

	/**
	 * 
	 * @Title: validateNameByEdit
	 * @Description: 编辑时验证名字是否重复
	 * @param
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-8-24
	 */
	public int validateNameByEdit(UserObj obj);

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
	public UserObj encryptPwd(UserObj obj) throws Exception;

	/**
	 * 
	 * @Title: decoderPwd
	 * @Description: 对用户的密码进行双侧解密
	 * @param
	 * @return void
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @throws Exception
	 * @createtime 2013-10-18 下午3:43:39
	 */
	public UserObj decryptPwd(UserObj obj) throws Exception;
}
