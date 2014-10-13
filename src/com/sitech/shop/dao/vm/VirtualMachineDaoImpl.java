package com.sitech.shop.dao.vm;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Repository;

import com.sitech.basd.sxcloud.cloud.domain.vmhost.VMHostObj;
import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.ssd.ah.nas.domain.VmIpObj;

@Repository("virtualMachineDao")
public class VirtualMachineDaoImpl extends BaseDao implements VirtualMachineDao {
	/**
	 * 
	 * @Title: getVMListForFree
	 * @Description: 获取指定操作系统名称的未被使用的虚拟机
	 * @param
	 * @return List
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime 2014-6-12 上午11:25:23
	 */
	public List getVMListForFree(VMHostObj obj) {
		List<VMHostObj> list = new ArrayList<VMHostObj>();
		list = getSqlMapClientTemplate().queryForList("VirtualMachine.queryForVmListForFree", obj);
		return list;
	}

	/**
	 * 
	 * @Title: queryOneTemManObj
	 * @Description: 查询单个实体信息
	 * @param
	 * @return VMHostObj
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-8-7 上午11:51:21
	 */
	public VMHostObj queryByObj(VMHostObj obj) {
		VMHostObj result = null;
		List<VMHostObj> list = getVMListForFree(obj);
		if (list != null && list.size() > 0) {
			Random r = new Random();
			result = list.get(r.nextInt(list.size()));
		}
		return result;
	}

	/**
	 * 
	 * @Title: updateVMHostObj
	 * @Description: 更新虚拟机的相关配置信息
	 * @param
	 * @return int
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime 2014-6-12 下午3:52:46
	 */
	public void updateVMHostObj(VMHostObj ipObj) {
		getSqlMapClientTemplate().update("VirtualMachine.updateVmHostObj", ipObj);
	}

	/**
	 * 
	 * @Title: queryIpListByVm
	 * @Description: 查询虚拟机的IP地址列表
	 * @param
	 * @return List
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime 2014-6-14 上午11:06:00
	 */
	public List queryIpListByVm(VmIpObj ip) {
		List<VmIpObj> list = new ArrayList<VmIpObj>();
		list = getSqlMapClientTemplate().queryForList("VirtualMachine.queryIpListByVm", ip);
		return list;
	}

	/**
	 * 
	 * @Title: updateVmIpObj
	 * @Description: TODO(修改虚拟机对应的IP地址)
	 * @param @param obj
	 * @param @return 设定文件
	 * @return int 返回类型
	 * @author wanglei_bj@si-tech.com.cn
	 * @throws
	 * @createtime 2014-6-19
	 */
	public int updateVmIpObj(VmIpObj obj) {
		return getSqlMapClientTemplate().update("VirtualMachine.updateVmIpObj", obj);
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
		if (vmHostObj.getPagination() != null) {
			vmHostObj.setFIRSTROWNUM(vmHostObj.getPagination().getFirstRownum());
			vmHostObj.setPAGESIZE(vmHostObj.getPagination().getPageSize());
			vmHostObj.getPagination().setTotalCount(
					((Integer) getSqlMapClientTemplate().queryForObject(
							"VirtualMachine.countVMByUser", vmHostObj)).intValue());
		}
		list = getSqlMapClientTemplate().queryForList("VirtualMachine.getVMListByUser", vmHostObj);
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
		return (Integer) (getSqlMapClientTemplate().queryForObject("VirtualMachine.countVMByUser",
				obj));
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
		return getSqlMapClientTemplate().update("VirtualMachine.renameVM", obj);
	}

}
