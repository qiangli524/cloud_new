package com.sitech.basd.component.dao.config;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.sitech.basd.component.domain.config.ConfigGroupObj;
import com.sitech.basd.component.domain.config.ConfigInfoObj;
import com.sitech.basd.component.domain.config.ConfigRelationObj;
import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;
import com.sitech.utils.encrypt.DoubleEncryptUtils;

@Repository("configDao")
public class ConfigDaoImpl extends BaseDao implements ConfigDao {
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
		List list = null;
		try {
			if (obj.getPagination() != null) {
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj.getPagination().setTotalCount(
						((Integer) getSqlMap().queryForObject(
								"Config.queryForCount", obj)).intValue());
			}
			list = getSqlMap().queryForList("Config.queryForList", obj);

			// 此处不解密
			// for (Object object : list) {
			// ConfigInfoObj oprObj = (ConfigInfoObj) object;
			// if (oprObj.getPassword() != null
			// && !"".equals(oprObj.getPassword())) {
			// oprObj.setPassword(DoubleEncryptUtils.decrypt(oprObj
			// .getPassword()));
			// }
			// }
		} catch (Exception sqlException) {
			LogHelper.error("Config.queryForList:" + sqlException.getMessage()
					+ getClass().getName());
		}
		return list;
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
		List list = null;
		try {
			if (obj.getPagination() != null) {
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj.getPagination().setTotalCount(
						((Integer) getSqlMap().queryForObject(
								"Config.queryForGroupCount", obj)).intValue());
			}
			list = getSqlMap().queryForList("Config.queryForGroupList", obj);
		} catch (Exception sqlException) {
			LogHelper.error("Config.queryForGroupList:"
					+ sqlException.getMessage() + getClass().getName());
		}
		return list;
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
		int ret = 0;
		try {
			Object o = getSqlMap().insert("Config.insertConfig", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception e) {
			ret = -1;
			LogHelper.error("Config.insertConfig:" + e.getMessage());
		}
		return ret;
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
		int ret = 0;
		try {
			Object o = getSqlMap().insert("Config.insertConfigGroup", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception e) {
			ret = -1;
			LogHelper.error("Config.insertConfigGroup:" + e.getMessage());
		}
		return ret;
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
		int ret = 0;
		try {
			Object o = getSqlMap().update("Config.updateConfig", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception e) {
			ret = -1;
			LogHelper.error("Config.updateConfig:" + e.getMessage());
		}
		return ret;
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
		int ret = 0;
		try {
			Object o = getSqlMap().update("Config.updateConfigGroup", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception e) {
			ret = -1;
			LogHelper.error("Config.updateConfigGroup:" + e.getMessage());
		}
		return ret;
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
		int ret = 0;
		try {
			Object o = getSqlMap().delete("Config.deleteConfig", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception e) {
			ret = -1;
			LogHelper.error("Config.deleteConfig:" + e.getMessage());
		}
		return ret;
	}

	/**
	 * 
	 * @Title: deleteConfigRelation
	 * @Description: 删除一条配置文件关系记录
	 * @param
	 * @return int
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @createtime Aug 29, 2013 9:41:09 PM
	 */
	public int deleteConfigRelation(ConfigInfoObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().delete("Config.deleteConfigRelation", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception e) {
			ret = -1;
			LogHelper.error("Config.deleteConfigRelation:" + e.getMessage());
		}
		return ret;
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
		int ret = 0;
		try {
			Object o = getSqlMap().delete("Config.deleteConfigGroup", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception e) {
			ret = -1;
			LogHelper.error("Config.deleteConfigGroup:" + e.getMessage());
		}
		return ret;
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
		List list = null;
		try {
			list = getSqlMap().queryForList("Config.querySelectedList", obj);
			for (Object object : list) {
				ConfigInfoObj oprObj = (ConfigInfoObj) object;
				if (oprObj.getPassword() != null
						&& !"".equals(oprObj.getPassword())) {
					oprObj.setPassword(DoubleEncryptUtils.decrypt(oprObj
							.getPassword()));
				}
			}
		} catch (Exception sqlException) {
			LogHelper.error("Config.querySelectedList:"
					+ sqlException.getMessage() + getClass().getName());
		}
		return list;
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
		List list = null;
		try {
			list = getSqlMap().queryForList("Config.queryRemainList", obj);
			for (Object object : list) {
				ConfigInfoObj oprObj = (ConfigInfoObj) object;
				if (oprObj.getPassword() != null
						&& !"".equals(oprObj.getPassword())) {
					oprObj.setPassword(DoubleEncryptUtils.decrypt(oprObj
							.getPassword()));
				}
			}
		} catch (Exception sqlException) {
			LogHelper.error("Config.queryRemainList:"
					+ sqlException.getMessage() + getClass().getName());
		}
		return list;
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
		int ret = 0;
		try {
			Object o = getSqlMap().delete("Config.deleteGroupMember", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception e) {
			ret = -1;
			LogHelper.error("Config.deleteGroupMember:" + e.getMessage());
		}
		return ret;
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
		int ret = 0;
		try {
			Object o = getSqlMap().insert("Config.insertGroupMember", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception e) {
			ret = -1;
			LogHelper.error("Config.insertGroupMember:" + e.getMessage());
		}
		return ret;
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
		List list = null;
		try {
			/*
			 * if (obj.getPagination() != null) {
			 * obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
			 * obj.setPAGESIZE(obj.getPagination().getPageSize());
			 * obj.getPagination().setTotalCount( ((Integer)
			 * getSqlMap().queryForObject( "Config.queryForCount",
			 * obj)).intValue()); }
			 */
			list = getSqlMap().queryForList("Config.queryForDeployList", obj);
			for (Object object : list) {
				ConfigInfoObj oprObj = (ConfigInfoObj) object;
				if (oprObj.getPassword() != null
						&& !"".equals(oprObj.getPassword())) {
					oprObj.setPassword(DoubleEncryptUtils.decrypt(oprObj
							.getPassword()));
				}
			}
		} catch (Exception sqlException) {
			LogHelper.error("Config.queryForDeployList:"
					+ sqlException.getMessage() + getClass().getName());
		}
		return list;
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
		List list = null;
		try {

			if (obj.getPagination() != null) {
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj.getPagination().setTotalCount(
						((Integer) getSqlMap().queryForObject(
								"Config.queryForAppCount", obj)).intValue());
			}

			list = getSqlMap().queryForList("Config.queryForAppConfig", obj);
			for (Object object : list) {
				ConfigInfoObj oprObj = (ConfigInfoObj) object;
				if (oprObj.getPassword() != null
						&& !"".equals(oprObj.getPassword())) {
					oprObj.setPassword(DoubleEncryptUtils.decrypt(oprObj
							.getPassword()));
				}
			}
		} catch (Exception sqlException) {
			LogHelper.error("Config.queryForAppConfig:"
					+ sqlException.getMessage() + getClass().getName());
		}
		return list;
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
		List list = null;
		try {
			if (obj.getPagination() != null) {
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj.getPagination().setTotalCount(
						((Integer) getSqlMap().queryForObject(
								"Config.queryHadoopConfigInfoListCount", obj))
								.intValue());
			}
			list = getSqlMap().queryForList("Config.queryHadoopConfigInfoList",
					obj);
		} catch (Exception sqlException) {
			LogHelper.error("Config.queryHadoopConfigInfoList:"
					+ sqlException.getMessage() + getClass().getName());
		}
		return list;
	}
}
