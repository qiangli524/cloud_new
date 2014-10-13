package service.users;

import java.util.List;

import domain.cluster.HadoopClusterObj;
import domain.users.HadoopUserObj;

/**
 * <p>
 * Title: HadoopUserService
 * </p>
 * <p>
 * Description: hadoop用户管理
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
 * @createtime 2014-2-24 下午3:37:18
 * 
 */
public interface HadoopUserService {

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
	public List<HadoopUserObj> queryForListByObj(HadoopUserObj hadoopUserObj);

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
	public List<HadoopClusterObj> queryForClusterList(HadoopUserObj userObj);

	/**
	 * @Title: insertByObj
	 * @Description: 插入一条记录
	 * @param
	 * @return int
	 * @throws
	 * @author lipp
	 * @version 1.0
	 * @createtime 2014-2-24 下午5:33:16
	 */
	public int insertByObj(HadoopUserObj hadoopUserObj);

	/**
	 * @Title: queryUserListUnAssoed
	 * @Description: 查询尚未被关联的用户
	 * @param
	 * @return List<HadoopUserObj>
	 * @throws
	 * @author lipp
	 * @version 1.0
	 * @param userObj
	 * @createtime 2014-3-3 下午7:13:03
	 */
	public List<HadoopUserObj> queryUserListUnAssoed(HadoopUserObj userObj);
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
	public List<HadoopUserObj> queryForUserList(HadoopUserObj hadoopUserObj);

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
	public List<HadoopUserObj> queryConnectUserList(HadoopUserObj hadoopUserObj);

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
	public List<HadoopUserObj> queryForList(HadoopUserObj hadoopUserObj);

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
	public List<HadoopUserObj> queryForSystemUser(HadoopUserObj hadoopUserObj);
	
	/**
	 * 
	 * @Title: queryUserName
	 * @Description: 查询用户名字
	 * @param
	 * @return HadoopUserObj
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-3-7 上午9:29:51
	 */
	public HadoopUserObj queryUserName(HadoopUserObj obj);
}
