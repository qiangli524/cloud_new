package service.users;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.users.HadoopUserGroupDao;
import domain.cluster.HadoopClusterObj;
import domain.users.HadoopUserGroup;

/**
 * <p>
 * Title: HadoopUserGroupService
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
 * @createtime 2014-2-19 下午3:41:03
 * 
 */
@Service("hadoopUserGroupService")
public class HadoopUserGroupServiceImpl implements HadoopUserGroupService {

	@Autowired
	private HadoopUserGroupDao hadoopUserGroupDao;

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
	@Override
	public List<HadoopUserGroup> queryForListByObj(
			HadoopUserGroup hadoopUserGroup) {
		return hadoopUserGroupDao.queryForListByObj(hadoopUserGroup);
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
	@Override
	public List<HadoopClusterObj> queryForClusterList(
			HadoopUserGroup hadoopUserGroup) {
		return hadoopUserGroupDao.queryForClusterList(hadoopUserGroup);
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
	@Override
	public List<HadoopClusterObj> queryWholeClusterList(
			HadoopUserGroup userGroup) {
		return hadoopUserGroupDao.queryWholeClusterList(userGroup);
	}

	/**
	 * @Title: insertByObj
	 * @Description: 插入一条记录
	 * @param
	 * @return void
	 * @throws
	 * @author lipp
	 * @version 1.0
	 * @createtime 2014-2-21 下午2:56:21
	 */
	@Override
	public int insertByObj(HadoopUserGroup hadoopUserGroup) {
		return hadoopUserGroupDao.insertByObj(hadoopUserGroup);
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
	
	@Override
	public List<HadoopUserGroup> queryConnectUseGroupList(
			HadoopUserGroup hadoopUserGroup) {
		return hadoopUserGroupDao.queryConnectUseGroupList(hadoopUserGroup);
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
	@Override
	public List<HadoopUserGroup> queryForList(HadoopUserGroup hadoopUserGroup) {
		return hadoopUserGroupDao.queryForList(hadoopUserGroup);
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
	@Override
	public List<HadoopUserGroup> queryForSystemUser(
			HadoopUserGroup hadoopUserGroup) {
		return hadoopUserGroupDao.queryForSystemUser(hadoopUserGroup);
	}

}
