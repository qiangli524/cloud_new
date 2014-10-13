package com.sitech.basd.component.dao.process;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.sitech.basd.component.domain.process.ProcessObj;
import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;

import domain.tree.HadoopTreeObj;

@Repository("processDao")
public class ProcessDaoImpl extends BaseDao implements ProcessDao {
	/**
	 * 
	 * @Title: ProcessInfoList
	 * @Description: 查询配置文件列表
	 * @param
	 * @return List
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime May 22, 2013 10:04:03 AM
	 */
	@Override
	public List<ProcessObj> queryProcessInfoList(ProcessObj obj) {
		List<ProcessObj> list = new ArrayList<ProcessObj>();
		try {
			if (obj.getPagination() != null) {
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj.getPagination().setTotalCount(
						((Integer) getSqlMap().queryForObject("Process.queryForCount", obj))
								.intValue());
			}
			list = getSqlMap().queryForList("Process.queryForList", obj);
		} catch (Exception sqlException) {
			sqlException.printStackTrace();
			LogHelper.error("Process.queryForList:" + sqlException.getMessage()
					+ getClass().getName());
		}
		return list;
	}

	@Override
	public int insertByObj(ProcessObj obj) {
		int ent = -1;
		try {
			Object obj1 = getSqlMap().insert("Process.insertByObj", obj);
			if (obj1 != null) {
				ent = 0;
			}
		} catch (Exception sqlException) {
			sqlException.printStackTrace();
			LogHelper.error("Process.insertByObj:" + sqlException.getMessage()
					+ getClass().getName());
		}
		return ent;
	}

	@Override
	public int delByObj(ProcessObj obj) {
		int ent = -1;
		try {
			Object obj1 = getSqlMap().delete("Process.deleteByObj", obj);
			if (obj1 != null) {
				ent = 0;
			}
		} catch (Exception sqlException) {
			sqlException.printStackTrace();
			LogHelper.error("Process.deleteByObj:" + sqlException.getMessage()
					+ getClass().getName());
		}
		return ent;
	}

	@Override
	public List<ProcessObj> queryForDeployList(ProcessObj obj) {
		List<ProcessObj> list = new ArrayList<ProcessObj>();
		try {
			if (obj.getPagination() != null) {
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj.getPagination().setTotalCount(
						((Integer) getSqlMap().queryForObject("Process.queryForDeployListCount",
								obj)).intValue());
			}
			list = getSqlMap().queryForList("Process.queryForDeployList", obj);
		} catch (Exception sqlException) {
			sqlException.printStackTrace();
			LogHelper.error("Process.queryForDeployList:" + sqlException.getMessage()
					+ getClass().getName());
		}
		return list;
	}

	@Override
	public int updateProcessByObj(ProcessObj obj) {
		int ent = -1;
		try {
			Object obj1 = getSqlMap().update("Process.updateProcessByObj", obj);
			if (obj1 != null) {
				ent = 0;
			}
		} catch (Exception sqlException) {
			sqlException.printStackTrace();
			LogHelper.error("Process.updateProcessByObj:" + sqlException.getMessage()
					+ getClass().getName());
		}
		return ent;
	}

	@Override
	public List<ProcessObj> queryForAppProcess(ProcessObj obj) {
		List<ProcessObj> list = new ArrayList<ProcessObj>();
		try {
			if (obj.getPagination() != null) {
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj.getPagination().setTotalCount(
						((Integer) getSqlMap().queryForObject("Process.queryProcessAppCount", obj))
								.intValue());
			}
			list = getSqlMap().queryForList("Process.queryForAppProcess", obj);
		} catch (Exception sqlException) {
			sqlException.printStackTrace();
			LogHelper.error("Process.queryForAppProcess:" + sqlException.getMessage()
					+ getClass().getName());
		}
		return list;
	}

	@Override
	public int countProcess(ProcessObj processObj) {
		int ret = -1;
		try {
			Object obj = getSqlMap().queryForObject("Process.queryForCount", processObj);
			if (obj != null) {
				ret = (Integer) obj;
			}
		} catch (Exception e) {
			LogHelper.error("Process.queryForCount: " + e.getMessage() + e.getClass().getName());
		}
		return ret;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ProcessObj> queryAllMappingsByObj(ProcessObj processObj) {
		List<ProcessObj> list = null;
		try {
			list = getSqlMap().queryForList("Process.queryAllMappingsByObj", processObj);
		} catch (Exception e) {
			LogHelper.error("Process.queryAllMappingsByObj: " + e.getMessage()
					+ e.getClass().getName());
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ProcessObj> queryUnNormalProcess(ProcessObj obj) {
		// TODO Auto-generated method stub
		List<ProcessObj> list = new ArrayList<ProcessObj>();
		try {
			if (obj.getPagination() != null) {
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj.getPagination().setTotalCount(
						((Integer) getSqlMap().queryForObject("Process.countProcessUnNor"))
								.intValue());
			}
			list = getSqlMap().queryForList("Process.queryUnNormalProcess", obj);
		} catch (Exception e) {
			LogHelper.error("Process.queryUnNormalProcess: " + e.getMessage()
					+ e.getClass().getName());
		}
		return list;
	}

	@Override
	public int countProcessUnNor() {
		int ret = -1;
		try {
			Object obj = getSqlMap().queryForObject("Process.countProcessUnNor");
			if (obj != null) {
				ret = (Integer) obj;
			}
		} catch (Exception e) {
			LogHelper
					.error("Process.countProcessUnNor: " + e.getMessage() + e.getClass().getName());
		}
		return ret;
	}

	/**
	 * 
	 * @Title: queryAppProcessList
	 * @Description: 查询进程列表，一般用于Excel
	 * @param
	 * @return List<ProcessObj>
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2014-1-5 下午4:08:54
	 */
	@Override
	public List<ProcessObj> queryAppProcessList(ProcessObj obj) {
		List<ProcessObj> list = new ArrayList<ProcessObj>();
		try {
			list = getSqlMap().queryForList("Process.queryAppProcessList", obj);
		} catch (Exception sqlException) {
			sqlException.printStackTrace();
			LogHelper.error("Process.queryAppProcessList:" + sqlException.getMessage()
					+ getClass().getName());
		}
		return list;
	}

	/**
	 * 
	 * @Title: queryHadoopHostNodeProcess
	 * @Description: 查询Hadoop主机节点进程列表
	 * @param
	 * @return List<ProcessObj>
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-1-9 下午6:16:33
	 */
	@Override
	public List<ProcessObj> queryHadoopHostNodeProcess(ProcessObj obj) {
		List<ProcessObj> list = new ArrayList<ProcessObj>();
		try {
			if (obj.getPagination() != null) {
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj.getPagination().setTotalCount(
						((Integer) getSqlMap().queryForObject(
												"Process.queryHadoopHostNodeProcessCount",
												obj))
								.intValue());
			}
			list = getSqlMap().queryForList("Process.queryHadoopHostNodeProcess",
					obj);
		} catch (Exception sqlException) {
			sqlException.printStackTrace();
			LogHelper.error("Process.queryHadoopHostNodeProcess:"
					+ sqlException.getMessage() + getClass().getName());
		}
		return list;
	}

	/**
	 * 
	 * @Title: queryHadoopOtherNodeProcess
	 * @Description: 查询Hadoop其他节点的进程列表
	 * @param
	 * @return List<ProcessObj>
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-1-9 下午6:18:41
	 */
	@Override
	public List<ProcessObj> queryHadoopOtherNodeProcess(ProcessObj obj) {
		List<ProcessObj> list = new ArrayList<ProcessObj>();
		try {
			if (obj.getPagination() != null) {
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj.getPagination().setTotalCount(
						((Integer) getSqlMap().queryForObject(
								"Process.queryHadoopOtherNodeProcessCount", obj))
								.intValue());
			}
			list = getSqlMap().queryForList("Process.queryHadoopOtherNodeProcess",
					obj);
		} catch (Exception sqlException) {
			sqlException.printStackTrace();
			LogHelper.error("Process.queryHadoopOtherNodeProcess:"
					+ sqlException.getMessage() + getClass().getName());
		}
		return list;
	}

	/**
	 * 
	 * @Title: deleteRelation
	 * @Description: 删除节点和进程的关系
	 * @param
	 * @return int
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-1-9 下午6:20:38
	 */
	@Override
	public int deleteRelation(ProcessObj obj) {
		int ent = -1;
		try {
			Object obj1 = getSqlMap().delete("Process.deleteRelation", obj);
			if (obj1 != null) {
				ent = 0;
			}
		} catch (Exception sqlException) {
			sqlException.printStackTrace();
			LogHelper.error("Process.deleteRelation:"
					+ sqlException.getMessage()
					+ getClass().getName());
		}
		return ent;
	}

	/**
	 * @Title: queryHadoopServiceNodeStatus
	 * @Description: 查询hadoop进程正常异常
	 * @param
	 * @return List<ProcessObj>
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2014-1-18 下午5:18:52
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<ProcessObj> queryHadoopServiceNodeStatus(ProcessObj pObj) {
		List<ProcessObj> list = new ArrayList<ProcessObj>();
		try {
			list = getSqlMap().queryForList("Process.queryHadoopServiceNodeStatus",pObj);
		} catch (Exception e) {
			LogHelper.error("Process.queryHadoopServiceNodeStatus: " + e.getMessage() + e.getClass().getName());
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ProcessObj> querySeriousHadoopProcess(HadoopTreeObj treeObj) {
		List<ProcessObj> list = new ArrayList<ProcessObj>();
		try {
			list = getSqlMap().queryForList("Process.querySeriousHadoopProcess",treeObj);
		} catch (Exception e) {
			LogHelper.error("Process.querySeriousHadoopProcess: " + e.getMessage() + e.getClass().getName());
		}
		return list;
	}
}
