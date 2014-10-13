package com.sitech.basd.resource.util;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import com.sitech.basd.yicloud.domain.xen.Host;
import com.sitech.basd.yicloud.domain.xen.Sr;
import com.sitech.basd.yicloud.domain.xen.Vm;
import com.sitech.vo.united.DatastoreUnitedVO;
import com.sitech.vo.united.HostUnitedVO;
import com.sitech.vo.united.VirtualMachineUnitedVO;

public class AnalysisXenDataRelation {

	/**
	 * 
	 * @Title: analysisHostAndVm
	 * @Description: 分析HOST和VM之间的关系，并返回list,主机的uui等于pooluuid的时候为虚拟机挂在池的下面
	 * @param
	 * @return List<Host>
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime Aug 1, 2013 2:41:32 PM
	 */
	public static List<Host> analysisHostAndVm(List<HostUnitedVO> hosts,
			List<VirtualMachineUnitedVO> vms, List<DatastoreUnitedVO> srs, String poolUuid) {
		List<Host> hosts1 = new ArrayList<Host>();
		Host poolHost = new Host();
		poolHost.setHostUuid(poolUuid);

		for (int i = 0; i < hosts.size(); i++) {
			HostUnitedVO host = hosts.get(i);

			String hostUuid = host.getHostCode();
			String hostName = host.getHostname();
			Host h = new Host();
			h.setHostUuid(hostUuid);
			h.setHostName(hostName);
			h.setHostCpuDesc(host.getHostCpuDesc());
			int cpuNum = host.getHostCpuNum() == null ? 0 : host.getHostCpuNum();
			h.setHostCpuNum(cpuNum);
			h.setHostCpuSpeed(Double.parseDouble(new DecimalFormat("0.00")
					.format(host.getCpuHz() == null ? 0 : host.getCpuHz())));
			h.setHostIp(host.getHostIp());
			h.setMemSize(host.getHostMemInMb() == null ? 0 : new Double(host.getHostMemInMb())
					.longValue());
			h.setMaxHostRunningVms(host.getMaxHostRunningVms() == null ? 0 : host
					.getMaxHostRunningVms());
			h.setMaxHostSupportedVcpus(cpuNum * 25);
			h.setUsedCpuMHz(host.getUsedCpuMHz() == null ? 0 : host.getUsedCpuMHz());
			h.setUserMemMb(host.getUserMemMb() == null ? 0 : host.getUserMemMb());
			h.setMaxCpuMHz(host.getMaxCpuMHz() == null ? 0 : host.getMaxCpuMHz());

			for (int k = 0; k < vms.size(); k++) {
				VirtualMachineUnitedVO vmUni = vms.get(k);
				/**
				 * 找出所有有驻留主机的虚拟机
				 */
				String srHostUuid = vmUni.getSrHostUuid();
				String vmType = vmUni.getVmType();
				String residentUuid = vmUni.getResidentOnUuid();
				Vm v = new Vm();
				v.setResidentOnUuid(residentUuid);// 当前驻留的主机
				v.setVmType(vmType);
				v.setVifNum(vmUni.getVifNum());
				v.setVmName(vmUni.getVmName());
				v.setVmUuid(vmUni.getVmCode());
				v.setShare(vmUni.isVdiShare());
				v.setCpuNum(vmUni.getNumCPUs() == null ? 0 : vmUni.getNumCPUs());
				v.setOsName(vmUni.getOperationSystemName());
				v.setMemDynamicMax(vmUni.getMemDynamicMax() == null ? 0 : vmUni.getMemDynamicMax());
				v.setVirtualSrSize(new Double(vmUni.getStorageSizeInMb()).longValue());
				v.setPowerState(vmUni.getPowerState() == null ? "" : vmUni.getPowerState());
				v.setVmIps(vmUni.getIp() == null ? "" : vmUni.getIp());
				v.setVirtualNicList(vmUni.getVirtualNicList());
				if (vmType.equals("IsTemplate")) {
					poolHost.getVms().add(v);
					vms.remove(k);
					k--;
				} else if (vmType.equals("IsVm") && hostUuid.equals(residentUuid)) {
					h.getVms().add(v);
					vms.remove(k);
					k--;
				}
			}
			hosts1.add(h);
		}
		for (int i = 0; i < hosts1.size(); i++) {
			Host h = hosts1.get(i);
			String hostUuid = h.getHostUuid();
			for (int k = 0; k < vms.size(); k++) {
				VirtualMachineUnitedVO vmUni = vms.get(k);
				String affinityUuid = vmUni.getAffinityUuid();// 优先启动的主机
				if (hostUuid.equals(affinityUuid)) {
					/**
					 * 找出所有有驻留主机的虚拟机
					 */
					String residentUuid = vmUni.getResidentOnUuid();
					String vmType = vmUni.getVmType();
					Vm v = new Vm();
					v.setResidentOnUuid(residentUuid);// 当前驻留的主机
					v.setVmType(vmType);
					v.setVifNum(vmUni.getVifNum());
					v.setVmName(vmUni.getVmName());
					v.setVmUuid(vmUni.getVmCode());
					v.setShare(vmUni.isVdiShare());
					v.setCpuNum(vmUni.getNumCPUs() == null ? 0 : vmUni.getNumCPUs());
					v.setOsName(vmUni.getOperationSystemName());
					v.setMemDynamicMax(vmUni.getMemDynamicMax() == null ? 0 : vmUni
							.getMemDynamicMax());
					v.setVirtualSrSize(new Double(vmUni.getStorageSizeInMb()).longValue());
					v.setPowerState(vmUni.getPowerState() == null ? "" : vmUni.getPowerState());
					v.setVmIps(vmUni.getIp() == null ? "" : vmUni.getIp());
					v.setVirtualNicList(vmUni.getVirtualNicList());
					h.getVms().add(v);
					vms.remove(k);
					k--;
				}
			}
		}
		for (VirtualMachineUnitedVO vmUni : vms) {
			Vm v = new Vm();
			v.setVmType(vmUni.getVmType());
			v.setVifNum(vmUni.getVifNum());
			v.setVmName(vmUni.getVmName());
			v.setVmUuid(vmUni.getVmCode());
			v.setShare(vmUni.isVdiShare());
			v.setCpuNum(vmUni.getNumCPUs() == null ? 0 : vmUni.getNumCPUs());
			v.setOsName(vmUni.getOperationSystemName());
			v.setMemDynamicMax(vmUni.getMemDynamicMax() == null ? 0 : vmUni.getMemDynamicMax());
			v.setVirtualSrSize(new Double(vmUni.getStorageSizeInMb()).longValue());
			v.setPowerState(vmUni.getPowerState() == null ? "" : vmUni.getPowerState());
			v.setVmIps(vmUni.getIp() == null ? "" : vmUni.getIp());
			v.setVirtualNicList(vmUni.getVirtualNicList());
			poolHost.getVms().add(v);
		}

		for (int a = 0; a < srs.size(); a++) {
			DatastoreUnitedVO dsUni = srs.get(a);
			boolean isShared = dsUni.getShared() == null ? false : Boolean.parseBoolean(dsUni
					.getShared());
			Sr sr = new Sr();
			String hostUuids = dsUni.getHostCode();
			String[] arrays = hostUuids.split(",");
			List<String> strs = new ArrayList<String>();
			for (String str : arrays) {
				strs.add(str);
			}
			sr.setHostUuid(strs);
			sr.setSrDesc(dsUni.getDatastoreDesc());
			sr.setSrName(dsUni.getDatastoreName());
			sr.setSrPhysicalSize(new Double(dsUni.getCapacityInMb()).longValue());
			sr.setSrShared(isShared);
			sr.setSrPhysicalUtilisation(dsUni.getDatastorePhyUtiInMB());
			sr.setSrState(dsUni.getDatastoreState() == null ? "" : dsUni.getDatastoreState());
			sr.setSrType(dsUni.getDatastoreType() == null ? "" : dsUni.getDatastoreType());
			sr.setSrUuid(dsUni.getDatastoreCode());
			sr.setVirtualSrSize(dsUni.getDatastoreVirAllocationInMB());
			sr.setSrUrl(dsUni.getDatastoreUrl() == null ? "" : dsUni.getDatastoreUrl());
			if (isShared) {
				poolHost.getSrs().add(sr);
			} else {
				String tempHostUuid = strs.get(0);
				for (int j = 0; j < hosts1.size(); j++) {
					Host host = hosts1.get(j);
					if (host.getHostUuid().equals(tempHostUuid)) {
						hosts1.get(j).getSrs().add(sr);
					}
				}
			}
		}
		hosts1.add(poolHost);
		// 计算主机已使用的vcpu个数
		for (Host host : hosts1) {
			int usedVCpu = 0;
			List<Vm> hostvms = host.getVms();
			for (Vm vm : hostvms) {
				usedVCpu += vm.getCpuNum();
			}
			host.setUsedHostVcpus(usedVCpu);
		}
		return hosts1;
	}
}
