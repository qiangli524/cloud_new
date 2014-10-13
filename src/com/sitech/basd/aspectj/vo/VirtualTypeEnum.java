package com.sitech.basd.aspectj.vo;

/**
 * 
 * <p>
 * Title: VirtualTypeEnum
 * </p>
 * <p>
 * Description: 虚拟化类型枚举
 * </p>
 * <p>
 * Copyright: Copyright (c) 2013
 * </p>
 * <p>
 * Company: SI-TECH
 * </p>
 * 
 * @author huojla
 * @version 1.0
 * @createtime 2013-8-29 上午9:57:16
 * 
 */
public enum VirtualTypeEnum {
	VMWARE, XEN, KVM, POWERVM, ERROR;

	public String toString() {
		if (this == VMWARE) {
			return "VMWARE";
		} else if (this == XEN) {
			return "XEN";
		} else if (this == KVM) {
			return "KVM";
		} else if (this == POWERVM) {
			return "POWERVM";
		}
		return "ERROR";
	}
}
