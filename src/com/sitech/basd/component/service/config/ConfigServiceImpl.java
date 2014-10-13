package com.sitech.basd.component.service.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sitech.basd.component.dao.config.ConfigDao;
import com.sitech.basd.component.domain.config.ConfigGroupObj;
import com.sitech.basd.component.domain.config.ConfigInfoObj;
import com.sitech.basd.component.domain.config.ConfigRelationObj;
import com.sitech.basd.component.service.script.ScriptsService;

@Service("configService")
public class ConfigServiceImpl implements ConfigService {
	@Autowired
	private ConfigDao configDao;
	@Autowired
	private ScriptsService scriptsService;

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
	public List queryConfigInfoList(ConfigInfoObj obj) {
		return configDao.queryConfigInfoList(obj);
	}

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
	public List queryConfigGroupList(ConfigGroupObj obj) {
		return configDao.queryConfigGroupList(obj);
	}

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
	public int insertConfig(ConfigInfoObj obj) {
		return configDao.insertConfig(obj);
	}

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
	public int insertConfigGroup(ConfigGroupObj obj) {
		return configDao.insertConfigGroup(obj);
	}

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
	public int updateConfig(ConfigInfoObj obj) {
		return configDao.updateConfig(obj);
	}

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
	public int updateConfigGroup(ConfigGroupObj obj) {
		return configDao.updateConfigGroup(obj);
	}

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
	public int deleteConfig(ConfigInfoObj obj) {
		return configDao.deleteConfig(obj);
	}

	/**
	 * 
	 * @Title: deleteConfigRelation
	 * @Description: 删除一条配置文件记录
	 * @param
	 * @return int
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @createtime Aug 29, 2013 9:41:09 PM
	 */
	public int deleteConfigRelation(ConfigInfoObj obj){
		return configDao.deleteConfigRelation(obj);
	}
	
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
	public int deleteConfigGroup(ConfigGroupObj obj) {
		return configDao.deleteConfigGroup(obj);
	}

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
	public List querySelectedList(ConfigRelationObj obj) {
		return configDao.querySelectedList(obj);
	}

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
	public List queryRemainList(ConfigRelationObj obj) {
		return configDao.queryRemainList(obj);
	}

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
	public int deleteGroupMember(ConfigRelationObj obj) {
		return configDao.deleteGroupMember(obj);
	}

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
	public int insertGroupMember(ConfigRelationObj obj) {
		return configDao.insertGroupMember(obj);
	}

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
	public List queryForDeployList(ConfigInfoObj obj) {
		return configDao.queryForDeployList(obj);
	}

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
	public List queryForAppConfig(ConfigInfoObj obj) {
		String sysAppId = obj.getExample_id();
		String sysAppChildIdStr = scriptsService.getSysAppChildIdStr(sysAppId);
		obj.setEncodeExampleStr(sysAppChildIdStr);
		return configDao.queryForAppConfig(obj);
	}

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
	@Override
	public List queryHadoopConfigInfoList(ConfigInfoObj obj) {
		return configDao.queryHadoopConfigInfoList(obj);
	}
}
