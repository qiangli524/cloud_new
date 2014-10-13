package com.sitech.shop.service.vm;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sitech.basd.resource.domain.united.UnitedTreeObj;
import com.sitech.basd.resource.util.RevertEntity;
import com.sitech.basd.resource.util.VirtualClient;
import com.sitech.basd.sxcloud.cloud.dao.vmhost.VMHostDao;
import com.sitech.basd.sxcloud.cloud.domain.vmhost.VMHostObj;
import com.sitech.shop.dao.vm.VirtualMachineDao;
import com.sitech.shop.webservice.dao.TbSysUserinfoShopDAO;
import com.sitech.shop.webservice.domain.TbSysUserinfo;
import com.sitech.shop.webservice.domain.TbSysUserinfoExample;
import com.sitech.ssd.ah.nas.domain.VmIpObj;
import com.sitech.ssd.billing.vo.entityevent.EntityEventVO;
import com.sitech.utils.exception.HttpClientException;
import com.sitech.utils.http.HttpClientCustomUtil;
import com.sitech.utils.jackson.JacksonUtil;
import com.sitech.utils.properties.PropertiesUtil;
import com.sitech.vo.united.VirtualMachinePowerStateUnitedVO;
import com.sitech.vo.united.VirtualMachinePowerStatus;
import com.sitech.vo.util.UnitedConstant;
import com.sitech.vo.util.VirtualConstant;

@Service("virtualMachineService")
public class VirtualMachineServiceImpl implements VirtualMachineService {
	@Autowired
	private VMHostDao vmHostDao;
	@Autowired
	private VirtualMachineDao virtualMachineDao;
	@Autowired
	private TbSysUserinfoShopDAO sysUserinfoShopDao;
	String billpath = PropertiesUtil.getString("properties/public_cloud", "billpath");

	/**
	 * 
	 * @Title: getVMList
	 * @Description: 查询某一用户对应的虚拟机列表
	 * @param
	 * @return List
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime 2014-4-8 下午8:42:00
	 */
	public List getVMList(VMHostObj obj) {
		return virtualMachineDao.getVMListByUser(obj);
	}

	/**
	 * 
	 * @Title: renameVM
	 * @Description: TODO(重命名)
	 * @param @param obj
	 * @param @return 设定文件
	 * @return int 返回类型
	 * @author wanglei_bj@si-tech.com.cn
	 * @throws
	 * @createtime 2014-6-30
	 */
	public int renameVM(VMHostObj obj) {
		return virtualMachineDao.renameVM(obj);
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
	 * @see service.vm.VirtualMachineService#queryForCount(com.sitech.basd.sxcloud.cloud.domain.vmhost.VMHostObj)
	 */
	@Override
	public Integer queryForCount(VMHostObj obj) {
		return virtualMachineDao.queryForCount(obj);
	}

	/**
	 * 
	 * @Title: putVMPowerState
	 * @Description: 修改虚拟机电源状态
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @throws HttpClientException
	 * @createtime Jul 27, 2013 5:19:43 PM
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public String putVMPowerState(UnitedTreeObj obj) throws Exception {
		String result = UnitedConstant.FAIL;
		String vtype = obj.getVtype();
		String url = "";
		VirtualMachinePowerStateUnitedVO vo = new VirtualMachinePowerStateUnitedVO();
		String state = obj.getOper();
		vo = RevertEntity.toRestVMPowerState(obj);
		vo.setVirtualMachinePowerOper(state);
		if (UnitedConstant.VMWARE.equals(vtype)) {
			if ("resume".equals(state)) {
				vo.setVirtualMachinePowerOper("powerOn");
			}
			url = "virtualmachine/" + VirtualConstant.VT_VMWARE + "/change/powerstate";
		}
		try {
			vo = VirtualClient.put(url, vo,
					new JacksonUtil.TypeReference<VirtualMachinePowerStateUnitedVO>() {
					});
			if (!vo.getIsSuccess()) {
				throw new Exception();
			} else {

				// 更新库中信息
				VMHostObj vmObj = new VMHostObj();
				vmObj.setVH_UUID(obj.getUuid());
				vmObj.setConnectId(obj.getConnect_id());
				if (VirtualMachinePowerStatus.powerOn.equals(state)
						|| VirtualMachinePowerStatus.reboot_guest.equals(state)
						|| VirtualMachinePowerStatus.reset.equals(state)) {// 开机
					vmObj.setVH_STAT("1");
				} else if (VirtualMachinePowerStatus.powerOff.equals(state)) {// 关机
					vmObj.setVH_STAT("0");
				} else {
					vmObj.setVH_STAT("2");
				}
				virtualMachineDao.updateVMHostObj(vmObj);
				result = UnitedConstant.SUCCESS;

				// 向计费接口通知开关机时间,只有开机和关机的时候才需要调用计费接口
				if (VirtualMachinePowerStatus.powerOn.equals(state)
						|| VirtualMachinePowerStatus.powerOff.equals(state)) {
					EntityEventVO en = this.generateBillEventVO(obj);
					String bill_url = billpath;
					EntityEventVO bill_result = HttpClientCustomUtil.post(bill_url, en,
							new JacksonUtil.TypeReference<EntityEventVO>() {
							});
					if (!bill_result.getIsSuccess()) {
						// 产生告警，预留
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		}

		return result;
	}

	/**
	 * 
	 * @Title: generateBillEventVO
	 * @Description: 生成计费对象
	 * @param
	 * @return EntityEventVO
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime 2014-6-11 下午3:43:54
	 */
	public EntityEventVO generateBillEventVO(UnitedTreeObj obj) {
		EntityEventVO vo = new EntityEventVO();
		String power_state = obj.getOper();
		vo.setEntityCode(obj.getUuid());
		vo.setConnectCode(obj.getConnect_id());
		if (VirtualMachinePowerStatus.powerOn.equals(power_state)) {// 开机
			vo.setPowerOnTime(Calendar.getInstance().getTime());
		} else if (VirtualMachinePowerStatus.powerOff.equals(power_state)) {// /关机
			vo.setPowerOffTime(Calendar.getInstance().getTime());
		}
		return vo;
	}

	/**
	 * 
	 * @Title: console
	 * @Description: 打开虚拟机控制台
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @throws HttpClientException
	 * @createtime Aug 1, 2013 4:06:10 PM
	 */
	public String console(VMHostObj obj) throws HttpClientException {
		String vtype = "1";
		String url = "";
		String ticket = "";
		if (UnitedConstant.VMWARE.equals(vtype)) {
			url = "virtualmachine/" + VirtualConstant.VT_VMWARE + "/ticket/" + obj.getConnectId();
			ticket = VirtualClient.get(url);
		}
		return ticket;
	}

	/**
	 * (非 Javadoc)
	 * <p>
	 * Title: getVMObj
	 * </p>
	 * <p>
	 * Description:查询某一用户一个虚拟机
	 * </p>
	 * 
	 * @author wanglei_bj@si-tech.com.cn
	 * @param obj
	 * @return VMHostObj
	 * @see service.vm.VirtualMachineService#getVMObj(com.sitech.basd.sxcloud.cloud.domain.vmhost.VMHostObj)
	 */
	@Override
	public VMHostObj getVMObj(VMHostObj obj) {
		List<VMHostObj> vmList = virtualMachineDao.getVMListByUser(obj);
		return vmList.size() > 0 ? vmList.get(0) : null;
	}

	/**
	 * (非 Javadoc)
	 * <p>
	 * Title: updateVmIpObj
	 * </p>
	 * <p>
	 * Description: 修改虚拟机对应的IP地址
	 * </p>
	 * 
	 * @author wanglei_bj@si-tech.com.cn
	 * @param obj
	 * @return
	 * @see service.vm.VirtualMachineService#updateVmIpObj(com.sitech.ssd.ah.nas.domain.VmIpObj)
	 */
	@Override
	public String updateVmIpObj(VmIpObj obj) {
		String result = UnitedConstant.FAIL;
		int ret;
		try {
			ret = virtualMachineDao.updateVmIpObj(obj);
			if (ret > 0) {
				result = UnitedConstant.SUCCESS;
			}
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return result;
		}
	}

}
