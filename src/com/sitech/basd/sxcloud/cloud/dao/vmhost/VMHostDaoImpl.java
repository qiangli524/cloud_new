package com.sitech.basd.sxcloud.cloud.dao.vmhost;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.sitech.basd.resource.util.ReourceInDomainUtil;
import com.sitech.basd.sxcloud.cloud.domain.globalconfig.TbGlobalConfigObj;
import com.sitech.basd.sxcloud.cloud.domain.vmhost.VMHostObj;
import com.sitech.basd.sxcloud.cloud.domain.vmhost.VmRelationObj;
import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;
import com.sitech.utils.randomid.RandomUUID;

@SuppressWarnings("all")
public class VMHostDaoImpl extends BaseDao implements VMHostDao {
	@Autowired
	private ReourceInDomainUtil reourceInDomainUtil;

	/**
	 * @Title:根据模块ID模糊查询防篡改列表gf
	 * @Copyright: Copyright (c) 201112
	 * @Company: si-tech
	 * @author wangjian
	 * @version 1.0
	 */

	public List queryVMHostObjByAPPID(VMHostObj obj) {
		List lst = null;
		try {
			lst = getSqlMap().queryForList("VMHost.queryVMHostObjByAPPID", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("VMHost.queryVMHostObjByAPPID:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return lst;
	}

	/**
	 * @Title:查询出具体防篡改信息
	 * @Copyright: Copyright (c) 201112
	 * @Company: si-tech
	 * @author wangjian
	 * @version 1.0
	 */
	public VMHostObj queryByObj(VMHostObj obj) {
		List lst = null;
		VMHostObj tempObj = null;
		lst = queryForListByObj(obj);
		if (lst != null && lst.size() > 0) {
			tempObj = (VMHostObj) lst.get(0);
		}
		return tempObj;
	}

	/**
	 * @Title:查询虚拟机信息
	 * @Copyright: Copyright (c) 201112
	 * @Company: si-tech
	 * @author wangjian
	 * @version 1.0
	 */
	public List<VMHostObj> queryForListByObj(VMHostObj obj) {
		List lst = null;
		try {
			/*
			 * if (obj.getPagination() != null) {
			 * obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
			 * obj.setPAGESIZE(obj.getPagination().getPageSize());
			 * obj.getPagination().setTotalCount( ((Integer)
			 * getSqlMap().queryForObject( "VMHostInfo.queryByObjForCount",
			 * obj)) .intValue()); }
			 */
			lst = getSqlMap().queryForList("VMHostInfo.queryForListByObj", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("VMHostInfo.queryForListByObj:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return lst;
	}

	/**
	 * @Title:添加虚拟机信息
	 * @Copyright: Copyright (c) 201112
	 * @Company: si-tech
	 * @author wangjian
	 * @version 1.0
	 */
	public int insertByVMhostObj(VMHostObj obj) {
		int ret = 0;
		try {
			if (obj.getDomain() == null || "".equals(obj.getDomain())) {
				/*
				 * 用于自动同步数据使用
				 */
				obj.setDomain(reourceInDomainUtil.initResourceDomainBySessionAndConfig(obj
						.getConnectId()));
			}
			Object o = getSqlMap().insert("VMHostInfo.insertByVMhostObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("VMHostInfo.insertByVMhostObj:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return ret;
	}

	/**
	 * @Title:更新防篡改信息
	 * @Copyright: Copyright (c) 201112
	 * @Company: si-tech
	 * @author wangjian
	 * @version 1.0
	 */
	public int updateByObj(VMHostObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().update("VMHostInfo.updateByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("VMHostInfo.updateByObj:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return ret;
	}

	/**
	 * 更新虚拟机电源状态
	 */
	public int updateVmStateByObj(VMHostObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().update("VMHostInfo.updateVmStateByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("VMHostInfo.updateVmStateByObj:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return ret;
	}

	/**
	 * @Title:删除功能管理模块信息
	 * @Copyright: Copyright (c) 201007
	 * @Company: si-tech
	 * @author yangwenchao
	 * @version 1.0
	 */
	public int deleteByObj(VMHostObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().delete("VMHostInfo.deleteByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("VMHostInfo.deleteByObj:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return ret;
	}

	/**
	 * @Title:下拉列表防篡改信息
	 * @Copyright: Copyright (c) 201112
	 * @Company: si-tech
	 * @author wangjian
	 * @version 1.0
	 */
	public List queryForListByVMHostObj(VMHostObj obj) {
		List lst = null;
		try {
			lst = getSqlMap().queryForList("VMHostInfo.queryForListByVMHostObj", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("VMHostInfo.queryForListByVMHostObj:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return lst;
	}

	/**
	 * 
	 * @Title: queryVhostIdSequence
	 * @Description: 查询虚拟机序列号
	 * @param
	 * @return int
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime Apr 28, 2012 3:28:54 PM
	 */
	public int queryVhostIdSequence() {
		int sequence = 0;
		try {
			TbGlobalConfigObj globalObj = new TbGlobalConfigObj();
			globalObj.setKEY("TB_CLOUD2_VMHOST_INFO_SEQUENCE");
			Object obj = getSqlMap().queryForObject("TbGlobalConfig.queryForObjByObj", globalObj);
			TbGlobalConfigObj reObj = null;
			if (obj != null) {
				reObj = (TbGlobalConfigObj) obj;
				sequence = Integer.parseInt(reObj.getVALUE());
			}
			if (obj == null) {
				String id = RandomUUID.getUuid();
				globalObj.setVALUE("2");
				globalObj.setID(id);
				Object insertObj = getSqlMap().insert("TbGlobalConfig.insertByObj", globalObj);
				sequence = 1;
			} else if (sequence >= 999999) {
				globalObj.setVALUE("2");
				globalObj.setID(reObj.getID());
				Object updateObj = getSqlMap().insert("TbGlobalConfig.updateByObj", globalObj);
				sequence = 1;
			} else {
				VMHostObj vmObj = new VMHostObj();
				while (vmObj != null) {
					vmObj.setID(sequence);
					vmObj = queryByObj(vmObj);
					if (vmObj == null) {
						break;
					} else {
						sequence += 1;
					}
					vmObj = new VMHostObj();
				}

				globalObj.setVALUE((sequence + 1) + "");
				globalObj.setID(reObj.getID());
				Object updateObj = getSqlMap().insert("TbGlobalConfig.updateByObj", globalObj);
			}
		} catch (Exception e) {
			LogHelper.error("TbGlobalConfig.operate:" + e.getMessage() + getClass().getName());
		}
		return sequence;
	}

	/**
	 * 
	 * @Title: deleteVhostByObj
	 * @Description: 删除虚拟机信息
	 * @param
	 * @return String
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime Apr 21, 2012 3:27:11 PM
	 */
	public int deleteVhostByObj(VMHostObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().delete("VMHostInfo.deleteVhostByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("VMHostInfo.deleteVhostByObj:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return ret;
	}

	/**
	 * 
	 * @Title: updateName
	 * @Description: 只更新虚拟机名称
	 * @param
	 * @return int
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Aug 2, 2012 2:55:14 PM
	 */
	public int updateName(VMHostObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().update("VMHostInfo.updateName", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("VMHostInfo.updateName:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return ret;
	}

	/**
	 * 
	 * @Title: updateCpuAndMem
	 * @Description: 只更新虚拟机内存或cpu
	 * @param
	 * @return int
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Aug 2, 2012 2:55:14 PM
	 */
	public int updateCpuAndMem(VMHostObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().update("VMHostInfo.updateCpuAndMem", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlException) {
			ret = -1;
			LogHelper.error("VMHostInfo.updateCpuAndMem:" + sqlException.getMessage()
					+ getClass().getName());
		}
		return ret;
	}

	/**
	 * 
	 * @Title: updateInterByObj
	 * @Description: 更新虚拟机数据--接口采集数据
	 * @param
	 * @return int
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime Oct 26, 2012 5:41:33 PM
	 */
	public int updateInterByObj(VMHostObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().update("VMHostInfo.updateInterByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlException) {
			ret = -1;
			LogHelper.error("VMHostInfo.updateInterByObj:" + sqlException.getMessage()
					+ getClass().getName());
		}
		return ret;
	}

	/**
	 * 
	 * @Title: vmResourceByType
	 * @Description: 查询虚拟机资源信息
	 * @param
	 * @return StatisticObj
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Feb 20, 2013 3:10:41 PM
	 */
	public List vmResourceByType(VMHostObj obj) {
		List result = new ArrayList();
		try {
			result = (List) getSqlMap().queryForList("VMHostInfo.vmResourceByType", obj);
		} catch (Exception sqlException) {
			LogHelper.error("VMHostInfo.updateInterByObj:" + sqlException.getMessage()
					+ getClass().getName());
		}
		return result;
	}

	/**
	 * 
	 * @Title: queryForVmList
	 * @Description: 查询所有虚拟机，分页显示
	 * @param
	 * @return List
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime Feb 23, 2013 3:06:54 PM
	 */
	public List<VMHostObj> queryForVmList(VMHostObj obj) {
		List lst = null;
		// 超级系统管理员时
		if (("1").equals(obj.getUSER_ID())) {
			try {
				if (obj.getPagination() != null) {
					obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
					obj.setPAGESIZE(obj.getPagination().getPageSize());
					obj.getPagination().setTotalCount(
							((Integer) getSqlMap().queryForObject("VMHostInfo.queryVmListForCount",
									obj)).intValue());
				}
				lst = getSqlMap().queryForList("VMHostInfo.queryForVmList", obj);
			} catch (Exception sqlexception) {
				LogHelper.error("VMHostInfo.queryForVmList:" + sqlexception.getMessage()
						+ getClass().getName());
			}
		} else {
			// 其他角色时
			try {
				if (obj.getPagination() != null) {
					obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
					obj.setPAGESIZE(obj.getPagination().getPageSize());
					obj.getPagination().setTotalCount(
							((Integer) getSqlMap().queryForObject(
									"VMHostInfo.queryForVmListByUserForCount", obj)).intValue());
				}
				lst = getSqlMap().queryForList("VMHostInfo.queryForVmListByUser", obj);
			} catch (Exception sqlexception) {
				LogHelper.error("VMHostInfo.queryForVmListByUser:" + sqlexception.getMessage()
						+ getClass().getName());
			}
		}

		return lst;
	}

	/**
	 * 
	 * @Title: synServiceManageVm
	 * @Description: 同步服务管理流程的虚拟机
	 * @param
	 * @return int
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Mar 2, 2013 3:44:51 PM
	 */
	public int synServiceManageVm(VmRelationObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().insert("VMHostInfo.synServiceManageVm", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlException) {
			ret = -1;
			LogHelper.error("VMHostInfo.synServiceManageVm:" + sqlException.getMessage()
					+ getClass().getName());
		}
		return ret;
	}

	/**
	 * 
	 * @Title: queryByRelationObj
	 * @Description: 查询一条记录
	 * @param
	 * @return VmRelationObj
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Mar 9, 2013 6:53:36 PM
	 */
	public List queryByRelationObj(VmRelationObj obj) {
		List vObj = null;
		try {
			vObj = getSqlMap().queryForList("VMHostInfo.queryByRelationObj", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("VMHostInfo.queryByRelationObj:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return vObj;
	}

	/**
	 * 
	 * @Title: updateVMHostEqId
	 * @Description: 更新虚拟机关联主机
	 * @param
	 * @return int
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime Mar 12, 2013 1:08:16 PM
	 */
	public int updateVMHostEqId(VMHostObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().update("VMHostInfo.updateVMHostEqId", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlException) {
			ret = -1;
			LogHelper.error("VMHostInfo.updateVMHostEqId:" + sqlException.getMessage()
					+ getClass().getName());
		}
		return ret;
	}

	/**
	 * 
	 * @Title: deleteByRelationObj
	 * @Description: 删除一条关系记录
	 * @param
	 * @return int
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Mar 13, 2013 8:41:35 AM
	 */
	public int deleteByRelationObj(VmRelationObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().delete("VMHostInfo.deleteByRelationObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("VMHostInfo.deleteByRelationObj:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return ret;
	}

	/**
	 * 
	 * @Title: updateVMHostMess
	 * @Description: 更新虚拟机的所有信息
	 * @param
	 * @return int
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime Mar 26, 2013 10:57:11 AM
	 */
	public int updateVMHostMess(VMHostObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().update("VMHostInfo.updateVMHostMess", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("VMHostInfo.updateVMHostMess:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return ret;
	}

	/**
	 * 
	 * @Title: updateVmhostType
	 * @Description: 更新虚拟机的类型
	 * @param
	 * @return int
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime May 8, 2013 9:56:17 AM
	 */
	public int updateVmhostType(VMHostObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().update("VMHostInfo.updateVMType", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("VMHostInfo.updateVMType:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return ret;
	}

	/**
	 * 
	 * @Title: updateVMHostUnitedInfo
	 * @Description: 统一树更新虚拟机信息
	 * @param
	 * @return int
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-7-31 下午4:31:59
	 */
	public int updateVMHostInfo(VMHostObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().update("VMHostInfo.updateVMHostUnitedInfo", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("VMHostInfo.updateVMHostUnitedInfo:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return ret;
	}

	/**
	 * @Title: queryForListByUUIDListString
	 * @Description: 查询hostcode
	 * @param
	 * @return List<String>
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-8-29 下午2:30:30
	 */
	@Override
	public List<String> queryForListByUUIDListString(VMHostObj vmHostInfoObj) {
		List<String> list = new ArrayList<String>();
		try {
			list = getSqlMapClient().queryForList("VMHostInfo.queryForListByUUIDListString",
					vmHostInfoObj);
		} catch (Exception e) {
			LogHelper.error("VMHostInfo.queryForListByUUIDListString: " + e.getMessage()
					+ e.getClass().getName());
		}
		return list;
	}

	/**
	 * @Title: queryForListByUUIDList
	 * @Description: 通过uuid集合查询
	 * @param
	 * @return List<VMHostObj>
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-9-9 下午10:41:25
	 */
	@Override
	public List<VMHostObj> queryForListByUUIDList(VMHostObj vmHostObj) {
		List<VMHostObj> list = new ArrayList<VMHostObj>();
		try {
			list = getSqlMap().queryForList("VMHostInfo.queryForListByUUIDList", vmHostObj);
		} catch (Exception e) {
			LogHelper.error("VMHostInfo.queryForListByUUIDList: " + e.getMessage()
					+ e.getClass().getName());
		}
		return list;
	}

	/**
	 * @Title: querySeriousList
	 * @Description: 查询异常
	 * @param
	 * @return List<VMHostObj>
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-9-18 上午9:18:09
	 */
	@Override
	public List<VMHostObj> querySeriousList(VMHostObj vmHostObj) {
		List<VMHostObj> list = new ArrayList<VMHostObj>();
		try {
			list = getSqlMap().queryForList("VMHostInfo.querySeriousList", vmHostObj);
		} catch (Exception e) {
			LogHelper.error("VMHostInfo.querySeriousList: " + e.getMessage()
					+ e.getClass().getName());
		}
		return list;
	}

	/**
	 * @Title: querySeriousVMList
	 * @Description: 查询异常虚拟机列表
	 * @param
	 * @return List<VMHostObj>
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-9-18 上午10:42:49
	 */
	@Override
	public List<VMHostObj> querySeriousVMList(VMHostObj vmHostObj) {
		List<VMHostObj> list = new ArrayList<VMHostObj>();
		try {
			if (vmHostObj.getPagination() != null) {
				vmHostObj.setFIRSTROWNUM(vmHostObj.getPagination().getFirstRownum());
				vmHostObj.setPAGESIZE(vmHostObj.getPagination().getPageSize());
				vmHostObj.getPagination().setTotalCount(
						((Integer) getSqlMap().queryForObject("VMHostInfo.countSeriousVM",
								vmHostObj)).intValue());
			}
			list = getSqlMap().queryForList("VMHostInfo.querySeriousVMList", vmHostObj);
		} catch (Exception e) {
			LogHelper.error("VMHostInfo.querySeriousVMList: " + e.getMessage()
					+ e.getClass().getName());
		}
		return list;
	}

	@Override
	public List<VMHostObj> queryForListByPro(VMHostObj vmHostObj) {
		List<VMHostObj> list = new ArrayList<VMHostObj>();
		try {
			if (vmHostObj.getPagination() != null) {
				vmHostObj.setFIRSTROWNUM(vmHostObj.getPagination().getFirstRownum());
				vmHostObj.setPAGESIZE(vmHostObj.getPagination().getPageSize());
				vmHostObj.getPagination().setTotalCount(
						((Integer) getSqlMap().queryForObject("VMHostInfo.countByPro", vmHostObj))
								.intValue());
			}
			list = getSqlMap().queryForList("VMHostInfo.queryForListByPro", vmHostObj);
		} catch (Exception e) {
			LogHelper.error("VMHostInfo.queryForListByPro: " + e.getMessage()
					+ e.getClass().getName());
		}
		return list;
	}

	/**
	 * @Title: queryVMListForBusi
	 * @Description: 业务中心查询虚拟机列表
	 * @param
	 * @return List<VMHostObj>
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @createtime 2013-9-18 上午10:42:49
	 */
	public List<VMHostObj> queryVMListForBusi(VMHostObj vmHostObj) {
		List<VMHostObj> list = new ArrayList<VMHostObj>();
		try {
			if (vmHostObj.getPagination() != null) {
				vmHostObj.setFIRSTROWNUM(vmHostObj.getPagination().getFirstRownum());
				vmHostObj.setPAGESIZE(vmHostObj.getPagination().getPageSize());
				vmHostObj.getPagination()
						.setTotalCount(
								((Integer) getSqlMap().queryForObject("VMHostInfo.countForbusi",
										vmHostObj)).intValue());
			}
			list = getSqlMap().queryForList("VMHostInfo.queryVmList", vmHostObj);
		} catch (Exception e) {
			LogHelper.error("VMHostInfo.queryVmList: " + e.getMessage() + e.getClass().getName());
		}
		return list;
	}

	/**
	 * 
	 * @Title: insertVmhostHis
	 * @Description: 当进行创建虚拟机和删除虚拟机的操作时，要插入相应的历史记录表
	 * @param
	 * @return int
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime 2013-9-19 下午4:45:50
	 */
	public int insertVmhostHis(VMHostObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().insert("VMHostInfo.insertVmhostHis", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("VMHostInfo.insertVmhostHis:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return ret;
	}

	/**
	 * @Title: queryForWorkOrder
	 * @Description: 查询出符合条件的虚拟机信息
	 * @param
	 * @return VMHostObj
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-9-23 上午9:11:09
	 */
	@Override
	public VMHostObj queryForWorkOrder(VMHostObj vmHostObj) {
		VMHostObj obj = new VMHostObj();
		try {
			Object o = getSqlMap().queryForObject("VMHostInfo.queryForWorkOrder", vmHostObj);
			if (o != null) {
				obj = (VMHostObj) o;
			}
		} catch (Exception e) {
			LogHelper.error("VMHostInfo.queryForWorkOrder: " + e.getMessage()
					+ e.getClass().getName());
		}
		return obj;
	}

	/**
	 * @Title: queryForObjByEntityID
	 * @Description: 根据connectid_vh_uuid查询
	 * @param
	 * @return VMHostObj
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-9-24 下午2:07:22
	 */
	@Override
	public VMHostObj queryForObjByEntityID(VMHostObj paramObj) {
		VMHostObj vmHostObj = new VMHostObj();
		try {
			Object obj = getSqlMap().queryForObject("VMHostInfo.queryForObjByEntityID", paramObj);
			if (obj != null) {
				vmHostObj = (VMHostObj) obj;
			}
		} catch (Exception e) {
			LogHelper.error("VMHostInfo.queryForObjByEntityID: " + e.getMessage()
					+ e.getClass().getName());
		}
		return vmHostObj;
	}

	/**
	 * 
	 * @Title: insertIbmLogicalPartition
	 * @Description: 保存IBM逻辑分区数据
	 * @param
	 * @return int
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-11-15 上午10:51:38
	 */
	public int insertIbmLogicalPartition(VMHostObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().insert("VMHostInfo.insertIbmLogicalPartition", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("VMHostInfo.insertIbmLogicalPartition:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return ret;
	}

	/**
	 * 
	 * @Title: updateIbmLogicalPartition
	 * @Description: 更新IBM逻辑分区数据
	 * @param
	 * @return int
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-11-15 上午10:51:38
	 */
	public int updateIbmLogicalPartition(VMHostObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().update("VMHostInfo.updateIbmLogicalPartition", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("VMHostInfo.updateIbmLogicalPartition:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return ret;
	}

	/**
	 * @Title: queryForListByProLeader
	 * @Description: 根据项目负责人查询虚拟机列表
	 * @param
	 * @return List<VMHostObj>
	 * @throws
	 * @author lipp
	 * @version 1.0
	 * @createtime 2013-12-10 下午7:16:24
	 */
	@Override
	public List<VMHostObj> queryForListByProLeader(VMHostObj vmHostObj) {
		List<VMHostObj> list = new ArrayList<VMHostObj>();
		try {
			if (vmHostObj.getPagination() != null) {
				vmHostObj.setFIRSTROWNUM(vmHostObj.getPagination().getFirstRownum());
				vmHostObj.setPAGESIZE(vmHostObj.getPagination().getPageSize());
				vmHostObj.getPagination().setTotalCount(
						((Integer) getSqlMap().queryForObject("VMHostInfo.countByProLeader",
								vmHostObj)).intValue());
			}
			list = getSqlMap().queryForList("VMHostInfo.queryForListByProLeader", vmHostObj);
		} catch (Exception e) {
			LogHelper.error("VMHostInfo.queryForListByProLeader: " + e.getMessage()
					+ e.getClass().getName());
		}
		return list;
	}

	/**
	 * 
	 * @Title: getVMListByUser
	 * @Description: 查询用户对应的虚拟机列表（北京电信使用）
	 * @param
	 * @return List
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime 2014-4-8 下午8:47:17
	 */
	public List getVMListByUser(VMHostObj vmHostObj) {
		List<VMHostObj> list = new ArrayList<VMHostObj>();
		try {
			if (vmHostObj.getPagination() != null) {
				vmHostObj.setFIRSTROWNUM(vmHostObj.getPagination().getFirstRownum());
				vmHostObj.setPAGESIZE(vmHostObj.getPagination().getPageSize());
				vmHostObj.getPagination().setTotalCount(
						((Integer) getSqlMap()
								.queryForObject("VMHostInfo.countVMByUser", vmHostObj)).intValue());
			}
			list = getSqlMap().queryForList("VMHostInfo.getVMListByUser", vmHostObj);
		} catch (Exception e) {
			LogHelper.error("VMHostInfo.getVMListByUser: " + e.getMessage()
					+ e.getClass().getName());
		}
		return list;
	}

	/**
	 * (非 Javadoc)
	 * <p>
	 * Title: queryForCount
	 * </p>
	 * <p>
	 * Description: 查询用户、区域内对应的虚拟机数量（北京电信使用）
	 * 
	 * @author wanglei_bj@si-tech.com.cn
	 * @param obj
	 * @return
	 * @see com.sitech.basd.sxcloud.cloud.dao.vmhost.VMHostDao#queryForCount(com.sitech.basd.sxcloud.cloud.domain.vmhost.VMHostObj)
	 */
	@Override
	public Integer queryForCount(VMHostObj obj) {
		try {
			return (Integer) (getSqlMap().queryForObject("VMHostInfo.countVMByUser", obj));
		} catch (SQLException e) {
			LogHelper.error("VMHostInfo.countVMByUser: " + e.getMessage() + e.getClass().getName());
			return -1;
		}
	}

	/**
	 * 
	 * @Title: renameVM
	 * @Description: 重命名虚拟机（北京电信）
	 * @param
	 * @return int
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime 2014-6-11 上午10:36:25
	 */
	public int renameVM(VMHostObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().update("VMHostInfo.renameVM", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("VMHostInfo.renameVM:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return ret;
	}

	@Override
	public List<VMHostObj> queryVMListByClusterOrHost(VMHostObj vmHostObj) {
		List<VMHostObj> list = new ArrayList<VMHostObj>();
		try {
			if (vmHostObj.getPagination() != null) {
				vmHostObj.setFIRSTROWNUM(vmHostObj.getPagination().getFirstRownum());
				vmHostObj.setPAGESIZE(vmHostObj.getPagination().getPageSize());
				vmHostObj.getPagination().setTotalCount(
						((Integer) getSqlMap().queryForObject(
								"VMHostInfo.countVMListByClusterOrHost", vmHostObj)).intValue());
			}
			list = getSqlMap().queryForList("VMHostInfo.queryVMListByClusterOrHost", vmHostObj);
		} catch (Exception e) {
			LogHelper.error("VMHostInfo.queryVMListByClusterOrHost: " + e.getMessage()
					+ e.getClass().getName());
		}
		return list;
	}
}
