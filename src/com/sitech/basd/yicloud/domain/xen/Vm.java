package com.sitech.basd.yicloud.domain.xen;

import java.util.List;

import com.sitech.vo.united.VirtualNicUnitedVO;


public class Vm{
	
	private List<Vm> vmAnswers;
	
	private String vmName;
	
	private String vmUuid;
	
	private long cpuMax;
	
	private long cpuNum;

	private long memDynamicMax;
	
	private long memDynamicMin;
	
	private long memStaticMax;
	
	private long memStaticMin;
	
	private String description;
	
	private String powerState;
	
	private String osName;
	
	private String vmIps="";
	
	private long virtualSrSize;
	
	private boolean vState;
	
	private String vmType;
	
	private String affinityUuid;
	
	private String residentOnUuid;
	
	private boolean isShare;
	
	private long vifNum;
	
	// 虚拟网卡列表
	private List<VirtualNicUnitedVO> virtualNicList;
	
    public long getVifNum() {
		return vifNum;
	}

	public void setVifNum(long vifNum) {
		this.vifNum = vifNum;
	}

	public boolean isShare() {
		return isShare;
	}

	public void setShare(boolean isShare) {
		this.isShare = isShare;
	}

	public enum Types {
    	IsVm,
        IsTemplate,
        IsSnapshot;
        public String toString() {
            if (this == IsVm) return "IsVm";
            if (this == IsTemplate) return "IsTemplate";
            if (this == IsSnapshot) return "IsSnapshot";
        return "IsVm";
        }
    }

	public String getVmName() {
		return vmName;
	}

	public void setVmName(String vmName) {
		this.vmName = vmName;
	}

	public List<Vm> getVmAnswers() {
		return vmAnswers;
	}

	public void setVmAnswers(List<Vm> vmAnswers) {
		this.vmAnswers = vmAnswers;
	}


	public String getVmType() {
		return vmType;
	}

	public void setVmType(String vmType) {
		this.vmType = vmType;
	}

	public String getVmUuid() {
		return vmUuid;
	}

	public void setVmUuid(String vmUuid) {
		this.vmUuid = vmUuid;
	}

	public long getCpuMax() {
		return cpuMax;
	}

	public void setCpuMax(long cpuMax) {
		this.cpuMax = cpuMax;
	}

	public long getCpuNum() {
		return cpuNum;
	}

	public void setCpuNum(long cpuNum) {
		this.cpuNum = cpuNum;
	}

	public long getMemDynamicMax() {
		return memDynamicMax;
	}

	public void setMemDynamicMax(long memDynamicMax) {
		this.memDynamicMax = memDynamicMax;
	}

	public long getMemDynamicMin() {
		return memDynamicMin;
	}

	public void setMemDynamicMin(long memDynamicMin) {
		this.memDynamicMin = memDynamicMin;
	}

	public long getMemStaticMax() {
		return memStaticMax;
	}

	public void setMemStaticMax(long memStaticMax) {
		this.memStaticMax = memStaticMax;
	}

	public long getMemStaticMin() {
		return memStaticMin;
	}

	public void setMemStaticMin(long memStaticMin) {
		this.memStaticMin = memStaticMin;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPowerState() {
		return powerState;
	}

	public void setPowerState(String powerState) {
		this.powerState = powerState;
	}

	public String getOsName() {
		return osName;
	}

	public void setOsName(String osName) {
		this.osName = osName;
	}

	public String getVmIps() {
		return vmIps;
	}

	public void setVmIps(String vmIps) {
		this.vmIps = vmIps;
	}

	public long getVirtualSrSize() {
		return virtualSrSize;
	}

	public void setVirtualSrSize(long virtualSrSize) {
		this.virtualSrSize = virtualSrSize;
	}

	public boolean isvState() {
		return vState;
	}

	public void setvState(boolean vState) {
		this.vState = vState;
	}

	public String getAffinityUuid() {
		return affinityUuid;
	}

	public void setAffinityUuid(String affinityUuid) {
		this.affinityUuid = affinityUuid;
	}

	public String getResidentOnUuid() {
		return residentOnUuid;
	}

	public void setResidentOnUuid(String residentOnUuid) {
		this.residentOnUuid = residentOnUuid;
	}

	public List<VirtualNicUnitedVO> getVirtualNicList() {
		return virtualNicList;
	}

	public void setVirtualNicList(List<VirtualNicUnitedVO> virtualNicList) {
		this.virtualNicList = virtualNicList;
	}
    
}
