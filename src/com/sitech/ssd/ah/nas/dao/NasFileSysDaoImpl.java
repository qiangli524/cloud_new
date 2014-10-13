package com.sitech.ssd.ah.nas.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.sitech.basd.sxcloud.cloud.domain.vmhost.VMHostObj;
import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.ssd.ah.nas.domain.MountObj;
import com.sitech.ssd.ah.nas.domain.NasFileSystemObj;
import com.sitech.ssd.ah.nas.domain.VmIpObj;

@Repository("nasFileSysDao")
public class NasFileSysDaoImpl extends BaseDao implements NasFileSysDao {

	private Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * @Title: insertFile
	 * @Description: nas文件系统入库
	 * @param
	 * @return
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @throws Exception
	 * @createtime 2014年3月26日9:19:51
	 */
	public void insertFile(NasFileSystemObj obj) {
		try {
			sqlMapClient.insert("nasFileSys.insertByObj", obj);
		} catch (SQLException e) {
			logger.error("文件系统插入出错" + e.getMessage());
		}
	}

	/**
	 * @Title: queryFile
	 * @Description: 查询filesystem列表
	 * @param
	 * @return
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @throws Exception
	 * @createtime 2014年3月26日9:19:51
	 */
	public List<NasFileSystemObj> queryFile(NasFileSystemObj obj) {
		List<NasFileSystemObj> list = null;
		try {
			if (obj.getPagination() != null) {
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj.getPagination().setTotalCount(
						((Integer) getSqlMap().queryForObject(
								"nasFileSys.queryFileSysByObjForCount", obj)).intValue());
			}
			list = sqlMapClient.queryForList("nasFileSys.queryFileSysByObj", obj);
		} catch (Exception sqlException) {
			logger.error("nasFileSys.queryFileSysByObj:" + sqlException.getMessage()
					+ getClass().getName());
		}
		return list;
	}

	/**
	 * @Title: queryFile
	 * @Description: 更新文件系统的IP地址
	 * @param
	 * @return
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @throws Exception
	 * @createtime 2014年3月26日9:19:51
	 */
	public void updateFileByObj(NasFileSystemObj obj) {
		try {
			sqlMapClient.update("nasFileSys.updateFileByObj", obj);
		} catch (SQLException e) {
			logger.error("nasFileSys.updateFileByObj:" + e.getMessage() + getClass().getName());
		}
	}

	/**
	 * @Title: delFileByObj
	 * @Description: 删除指定的文件系统
	 * @param
	 * @return
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @throws Exception
	 * @createtime 2014年3月26日9:19:51
	 */
	public void delFileByObj(NasFileSystemObj obj) {
		try {
			sqlMapClient.delete("nasFileSys.deleteFileByObj", obj);
		} catch (SQLException e) {
			logger.error("nasFileSys.deleteFileByObj:" + e.getMessage() + getClass().getName());
		}
	}

	/**
	 * @Title: insertFlieSysIp
	 * @Description: 文件系统对应的IP地址入库
	 * @param
	 * @return
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @throws Exception
	 * @createtime 2014年3月27日11:00:18
	 */
	public void insertFlieSysIp(NasFileSystemObj obj) {
		try {
			sqlMapClient.insert("nasFileSys.insertFileSysIpByObj", obj);
		} catch (SQLException e) {
			logger.error("nasFileSys.insertFileSysIpByObj:" + e.getMessage() + getClass().getName());
		}
	}

	/**
	 * @Title: queryFlieSysIp
	 * @Description: 文件系统IP地址列表
	 * @param
	 * @return
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @throws Exception
	 * @createtime 2014年3月27日11:00:35
	 */
	public List<NasFileSystemObj> queryFlieSysIp(NasFileSystemObj obj) {
		List<NasFileSystemObj> list = new ArrayList<NasFileSystemObj>();
		try {
			list = sqlMapClient.queryForList("nasFileSys.queryFileSysIpByObj", obj);
		} catch (SQLException e) {
			logger.error("nasFileSys.queryFileSysIpByObj:" + e.getMessage() + getClass().getName());
		}
		return list;
	}

	/**
	 * @Title: delFlieSysIpByObj
	 * @Description: 删除指定的文件系统对应IP地址记录
	 * @param
	 * @return
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @throws Exception
	 * @createtime 2014年3月27日11:00:54
	 */
	public void delFlieSysIpByObj(NasFileSystemObj obj) {
		try {
			sqlMapClient.delete("nasFileSys.deleteFileSysIpByObj", obj);
		} catch (SQLException e) {
			logger.error("nasFileSys.deleteFileSysIpByObj:" + e.getMessage() + getClass().getName());
		}
	}

	/******************* 根据NASIP查找虚拟机相关信息 *********************************/
	/**
	 * @Title: queryVmByIps
	 * @Description: 根据文件系统IP地址查找对应虚拟机
	 * @param
	 * @return VmHostInfoObj
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @throws Exception
	 * @createtime 2014年3月27日11:00:35
	 */
	public VMHostObj queryVmByIp(NasFileSystemObj obj) {
		VMHostObj vmObj = new VMHostObj();
		try {
			vmObj = (VMHostObj) sqlMapClient.queryForObject("nasFileSys.queryVmByIp", obj);
		} catch (SQLException e) {
			logger.error("nasFileSys.queryVmByIp:" + e.getMessage() + getClass().getName());
		}
		return vmObj;
	}

	/**
	 * @Title: queryVmInfoByVm
	 * @Description: 根据虚拟机查找其主机、集群、数据中心信息
	 * @param
	 * @return
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @throws Exception
	 * @createtime 2014年3月27日11:00:54
	 */
	public String queryVmInfoByVm(VMHostObj obj) {
		String info = "";
		try {
			info = (String) sqlMapClient.queryForObject("nasFileSys.queryVmInfoByVm", obj);
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("nasFileSys.queryVmInfoByVm:" + e.getMessage() + getClass().getName());
		}
		return info;

	}

	/**
	 * @Title: insertVmIpRelation
	 * @Description: 虚拟机与IP关系入库
	 * @param
	 * @return
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @throws Exception
	 * @createtime 2014年3月27日11:00:18
	 */
	public void insertVmIpRelation(VmIpObj obj) {
		try {
			getSqlMap().insert("nasFileSys.insertVmIpByObj", obj);
		} catch (SQLException e) {
			logger.error("nasFileSys.insertVmIpByObj:" + e.getMessage() + getClass().getName());
		}
	}

	/**
	 * @Title: queryVmIpRelation
	 * @Description: 虚拟机与IP对应关系列表
	 * @param
	 * @return
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @throws Exception
	 * @createtime 2014年3月27日11:00:35
	 */
	public List<VmIpObj> queryVmIpRelation(VmIpObj obj) {
		List<VmIpObj> list = new ArrayList<VmIpObj>();
		try {
			list = getSqlMap().queryForList("nasFileSys.queryVmIpByObj", obj);
		} catch (SQLException e) {
			logger.error("nasFileSys.queryVmIpByObj:" + e.getMessage() + getClass().getName());
		}
		return list;

	}

	/**
	 * 
	 * @Title: updatVmIpObj
	 * @Description: 更新虚拟机IP地址
	 * @param
	 * @return int
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime 2014-7-9 上午11:08:33
	 */
	public int updatVmIpObj(VmIpObj obj) {
		int rows = getSqlMapClientTemplate().update("nasFileSys.updatVmIpObj", obj);
		return rows;
	}

	/**
	 * @Title: delVmIpRelationByObj
	 * @Description: 删除指定虚拟机与IP对应关系
	 * @param
	 * @return
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @throws Exception
	 * @createtime 2014年3月27日11:00:54
	 */
	public void delVmIpRelationByObj(VmIpObj obj) {
		try {
			getSqlMap().delete("nasFileSys.delVmIpByObj", obj);
		} catch (SQLException e) {
			logger.error("nasFileSys.delVmIpByObj:" + e.getMessage() + getClass().getName());
		}
	}

	/**
	 * @Title: queryVmBuSys
	 * @Description: 根据文件系统查找对应挂在实体
	 * @param
	 * @return
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @throws Exception
	 * @createtime 2014年6月4日11:19:45
	 */
	public List<MountObj> queryHostBySys(NasFileSystemObj obj) {
		List<MountObj> list = new ArrayList<MountObj>();
		try {
			list = getSqlMap().queryForList("nasFileSys.queryHostByFIlesys", obj);
		} catch (SQLException e) {
			logger.error("nasFileSys.queryHostByFIlesys:" + e.getMessage() + getClass().getName());
		}
		return list;
	}

	/**
	 * @Title: queryVmBuSys
	 * @Description: 根据文件系统查找对应挂载虚拟机
	 * @param
	 * @return
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @throws Exception
	 * @createtime 2014年6月4日11:19:45
	 */
	public List<MountObj> queryVmBySys(NasFileSystemObj obj) {
		List<MountObj> list = new ArrayList<MountObj>();
		try {
			list = getSqlMap().queryForList("nasFileSys.queryVmByFIlesys", obj);
		} catch (SQLException e) {
			logger.error("nasFileSys.queryVmByFIlesys:" + e.getMessage() + getClass().getName());
		}
		return list;
	}

}
