package com.sitech.basd.component.dao.config;

import java.util.List;

import com.sitech.basd.component.domain.config.ConfigGroupObj;
import com.sitech.basd.component.domain.config.ConfigInfoObj;
import com.sitech.basd.component.domain.config.ConfigRelationObj;

public interface ConfigDao {
	/**
	 * 
	 * @Title: ConfigInfoList
	 * @Description: 查询配置文件列表
	 * @param
	 * @return List
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime May 22, 2013 10:04:03 AM
	 */
	public List queryConfigInfoList(ConfigInfoObj obj);

	/**
	 * 
	 * @Title: ConfigGroupList
	 * @Description: 查询配置文件组列表
	 * @param
	 * @return List
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime May 22, 2013 10:08:12 AM
	 */
	public List queryConfigGroupList(ConfigGroupObj obj);

	/**
	 * 
	 * @Title: insertConfig
	 * @Description: 增加一条配置文件记录
	 * @param
	 * @return int
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime May 22, 2013 6:27:26 PM
	 */
	public int insertConfig(ConfigInfoObj obj);

	/**
	 * 
	 * @Title: insertConfigGroup
	 * @Description: 增加一条组记录
	 * @param
	 * @return int
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime May 23, 2013 1:39:16 PM
	 */
	public int insertConfigGroup(ConfigGroupObj obj);

	/**
	 * 
	 * @Title: updateConfig
	 * @Description: 更新一条配置文件记录
	 * @param
	 * @return int
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime May 23, 2013 1:40:25 PM
	 */
	public int updateConfig(ConfigInfoObj obj);

	/**
	 * 
	 * @Title: updateConfig
	 * @Description: 更新一条组记录
	 * @param
	 * @return int
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime May 23, 2013 1:40:25 PM
	 */
	public int updateConfigGroup(ConfigGroupObj obj);

	/**
	 * 
	 * @Title: deleteConfig
	 * @Description: 删除一条配置文件记录
	 * @param
	 * @return int
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime May 23, 2013 4:41:09 PM
	 */
	public int deleteConfig(ConfigInfoObj obj);
	/**
	 * 
	 * @Title: deleteConfigRelation
	 * @Description: 删除一条配置文件与配置组的关系
	 * @param
	 * @return int
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @createtime aug 29, 2013 9:41:09 PM
	 */
	public int deleteConfigRelation(ConfigInfoObj obj);

	/**
	 * 
	 * @Title: deleteConfigGroup
	 * @Description: 删除一个组
	 * @param
	 * @return int
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime May 23, 2013 4:41:54 PM
	 */
	public int deleteConfigGroup(ConfigGroupObj obj);

	/**
	 * 
	 * @Title: querySelectedList
	 * @Description: 查询已选的配置文件列表
	 * @param
	 * @return List
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime May 23, 2013 6:28:06 PM
	 */
	public List querySelectedList(ConfigRelationObj obj);

	/**
	 * 
	 * @Title: queryRemainList
	 * @Description: 查询备选成员列表
	 * @param
	 * @return List
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime May 24, 2013 9:16:08 AM
	 */
	public List queryRemainList(ConfigRelationObj obj);

	/**
	 * 
	 * @Title: deleteGroupMember
	 * @Description: 删除组的所有成员
	 * @param
	 * @return int
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime May 24, 2013 11:29:14 AM
	 */
	public int deleteGroupMember(ConfigRelationObj obj);

	/**
	 * 
	 * @Title: deleteGroupMember
	 * @Description: 增加组的成员
	 * @param
	 * @return int
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime May 24, 2013 11:29:14 AM
	 */
	public int insertGroupMember(ConfigRelationObj obj);

	/**
	 * 
	 * @Title: queryForDeployList
	 * @Description: 查询部署实例对应的配置文件
	 * @param
	 * @return List
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime May 28, 2013 4:46:38 PM
	 */
	public List queryForDeployList(ConfigInfoObj obj);

	/**
	 * 
	 * @Title: queryForAppConfig
	 * @Description:查询基准应用下所有实例对应的配置文件
	 * @param
	 * @return List
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Jul 9, 2013 10:39:37 AM
	 */
	public List queryForAppConfig(ConfigInfoObj obj);

	/**
	 * 
	 * @Title: queryHadoopConfigInfoList
	 * @Description: 查询Hadoop配置文件列表分页
	 * @param
	 * @return List
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-1-10 下午7:42:47
	 */
	public List queryHadoopConfigInfoList(ConfigInfoObj obj);
}
