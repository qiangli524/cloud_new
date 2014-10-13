package com.sitech.shop.service;

import ip.IpType;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import publiccloud.OperateSystemType;
import publiccloud.QuotaConstant;
import publiccloud.SystemIpAndPassword;
import publiccloud.VirtualEthernetConstant;
import publiccloud.VlanType;

import com.sitech.basd.resource.util.VirtualClient;
import com.sitech.basd.sxcloud.cloud.dao.net.TbIpDao;
import com.sitech.basd.sxcloud.cloud.dao.net.TbNetDao;
import com.sitech.basd.sxcloud.cloud.domain.net.TbCloud2IpInfoObj;
import com.sitech.basd.sxcloud.cloud.domain.net.TbCloud2NetInfoObj;
import com.sitech.basd.sxcloud.cloud.domain.vmhost.VMHostObj;
import com.sitech.basd.util.PropertyUtil;
import com.sitech.shop.dao.vlan.PhysicalVlanDao;
import com.sitech.shop.domain.vlan.PhysicalVlanObj;
import com.sitech.ssd.ah.nas.dao.NasFileSysDao;
import com.sitech.ssd.ah.nas.domain.VmIpObj;
import com.sitech.utils.jackson.JacksonUtil;
import com.sitech.utils.publicShop.PsexecOperator;
import com.sitech.vo.united.VirtualMachineUnitedVO;
import com.sitech.vo.united.VirtualNicUnitedVO;
import com.sitech.vo.util.UnitedConstant;
import com.sitech.vo.util.VirtualConstant;

/**
 * @ClassName: PhysicalVlanServiceImpl
 * @Description: TODO(物理Vlan Service实现类)
 * @author wanglei_bj@si-tech.com.cn
 * @date 2014-4-23 下午1:21:53
 * @version 1.0
 */
@Service("physicalVlanService")
public class PhysicalVlanServiceImpl implements PhysicalVlanService {
	@Autowired
	private PhysicalVlanDao physicalVlanDao;
	@Autowired
	private TbNetDao tbNetDao;
	@Autowired
	private TbIpDao tbIpDao;
	@Autowired
	private PropertyUtil sshProp;
	@Autowired
	private NasFileSysDao nasFileSysDao;

	/**
	 * (非 Javadoc)
	 * <p>
	 * Title: getPhysicalVlanList
	 * </p>
	 * <p>
	 * Description: 查询物理Vlan列表
	 * </p>
	 * 
	 * @author wanglei_bj@si-tech.com.cn
	 * @param obj
	 * @return
	 * @see service.vlan.PhysicalVlanService#getPhysicalVlanList(com.sitech.basd.resource.domain.united.vlan.PhysicalVlanObj)
	 */
	@Override
	public List<PhysicalVlanObj> getPhysicalVlanList(PhysicalVlanObj obj) {
		try {
			return physicalVlanDao.queryForListByObj(obj);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 
	 * @Title: getPhysicalVlanList
	 * @Description: 获取一条记录
	 * @param @param obj
	 * @param @return 设定文件
	 * @return List<PhysicalVlanObj> 返回类型
	 * @author wanglei_bj@si-tech.com.cn
	 * @throws
	 */
	public PhysicalVlanObj getPhysicalVlanObj(PhysicalVlanObj obj) {
		try {
			return physicalVlanDao.getPhysicalVlanObj(obj);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	/**
	 * (非 Javadoc)
	 * <p>
	 * Title: createPhysicalVlan
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @author wanglei_bj@si-tech.com.cn
	 * @param obj
	 * @return
	 * @see service.vlan.PhysicalVlanService#createPhysicalVlan(com.sitech.basd.resource.domain.united.vlan.PhysicalVlanObj)
	 */
	@Override
	public String createPhysicalVlan(PhysicalVlanObj obj) {
		String result = UnitedConstant.FAIL;
		int ret;
		try {
			ret = physicalVlanDao.insertByObj(obj);
			if (ret == 0) {
				result = UnitedConstant.SUCCESS;
			}
			return result;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return result;
		}

	}

	/**
	 * (非 Javadoc)
	 * <p>
	 * Title: updatePhysicalVlan
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @author wanglei_bj@si-tech.com.cn
	 * @param obj
	 * @return
	 * @see service.vlan.PhysicalVlanService#updatePhysicalVlan(com.sitech.basd.resource.domain.united.vlan.PhysicalVlanObj)
	 */
	@Override
	public String updatePhysicalVlan(PhysicalVlanObj obj) {
		String result = UnitedConstant.FAIL;
		int ret;
		try {
			ret = physicalVlanDao.updateByObj(obj);
			if (ret > 0) {
				result = UnitedConstant.SUCCESS;
			}
			return result;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return result;
		}

	}

	/**
	 * (非 Javadoc)
	 * <p>
	 * Title: deletePhysicalVlan
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @author wanglei_bj@si-tech.com.cn
	 * @param obj
	 * @return
	 * @see service.vlan.PhysicalVlanService#deletePhysicalVlan(com.sitech.basd.resource.domain.united.vlan.PhysicalVlanObj)
	 */
	@Override
	public String deletePhysicalVlan(PhysicalVlanObj obj) {
		String result = UnitedConstant.FAIL;
		int ret;
		try {
			ret = physicalVlanDao.deleteByObj(obj);
			if (ret > 0) {
				result = UnitedConstant.SUCCESS;
			}
			return result;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return result;
		}

	}

	/**
	 * (非 Javadoc)
	 * <p>
	 * Title: collectPhysicalVlan
	 * </p>
	 * <p>
	 * Description: 回收Vlan
	 * </p>
	 * 
	 * @author wanglei_bj@si-tech.com.cn
	 * @param obj
	 * @return
	 * @see service.vlan.PhysicalVlanService#collectPhysicalVlan(com.sitech.basd.resource.domain.united.vlan.PhysicalVlanObj)
	 */
	@Override
	public String collectPhysicalVlan(PhysicalVlanObj obj) {
		String result = UnitedConstant.FAIL;
		int ret;
		try {
			ret = physicalVlanDao.setUserNull(obj);
			if (ret > 0) {
				result = UnitedConstant.SUCCESS;
			}
			return result;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return result;
		}

	}

	/**
	 * (非 Javadoc)
	 * <p>
	 * Title: getPhysicalVlan
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @author wanglei_bj@si-tech.com.cn
	 * @param obj
	 * @return
	 * @see service.vlan.PhysicalVlanService#getPhysicalVlan(com.sitech.basd.resource.domain.united.vlan.PhysicalVlanObj)
	 */
	@Override
	public PhysicalVlanObj getPhysicalVlan(PhysicalVlanObj obj) {
		try {
			return physicalVlanDao.getPhysicalVlanByObj(obj);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * (非 Javadoc)
	 * <p>
	 * Title: getARandomVlan
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @author wanglei_bj@si-tech.com.cn
	 * @param obj
	 * @return
	 * @see service.vlan.PhysicalVlanService#getARandomVlan(com.sitech.basd.resource.domain.united.vlan.PhysicalVlanObj)
	 */
	@Override
	public PhysicalVlanObj getARandomVlan(PhysicalVlanObj obj) {

		try {
			return physicalVlanDao.getANoAssignByObj(obj);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		/*
		 * Random random = new Random(); List<PhysicalVlanObj> vlanList; try {
		 * vlanList = physicalVlanDao.queryNoAssignForListByObj(obj); if
		 * (!vlanList.isEmpty()) { return
		 * vlanList.get(random.nextInt(vlanList.size())); } return null; } catch
		 * (Exception e) { // TODO Auto-generated catch block
		 * e.printStackTrace();
		 * 
		 * return null; } // 从List中随机选择一个
		 */
	}

	/**
	 * 
	 * @Title: controlVlanFlow
	 * @Description:vlan流量控制
	 * @param
	 * @return Boolean
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime 2014-5-1 上午10:42:13
	 */
	public Boolean controlVlanFlow(String path, PhysicalVlanObj obj) {
		Boolean result = false;
		return result;
	}

	/**
	 * 
	 * @Title: adjustVlanFlow
	 * @Description: 修改流量控制
	 * @param
	 * @return Boolean
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime 2014-5-1 上午10:42:28
	 */
	public Boolean adjustVlanFlow(String path, PhysicalVlanObj obj) {
		Boolean result = false;
		return result;
	}

	/**
	 * (非 Javadoc)
	 * <p>
	 * Title: queryForCount
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @author wanglei_bj@si-tech.com.cn
	 * @param obj
	 * @return
	 * @see service.vlan.PhysicalVlanService#queryForCount(com.sitech.basd.resource.domain.united.vlan.PhysicalVlanObj)
	 */
	@Override
	public Integer queryForCount(PhysicalVlanObj obj) {
		try {
			return physicalVlanDao.queryForCount(obj);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	/**
	 * 
	 * @Title: changeIp
	 * @Description: 修改虚拟机的IP地址
	 * @param
	 * @return void
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime 2014-6-14 上午11:37:09
	 */
	public boolean changeIp(String system_type, SystemIpAndPassword sys) throws Exception {
		boolean change_result = false;
		PsexecOperator px = new PsexecOperator();
		// 根据不同的操作系统类型需要调用不同的方法
		if (OperateSystemType.linux.equals(system_type)) {
			// 调用linux改脚本的方法
			change_result = px.psexecForLinuxCIP(sys);
		} else if (OperateSystemType.windows.equals(system_type)) {
			// 调用window改IP及用户名的方法
			change_result = px.psexecForWinCIP(sys);
		}
		if (!change_result) {
			throw new Exception("修改虚拟机IP地址失败，回滚之前所有操作");
		}
		return change_result;

	}
}
