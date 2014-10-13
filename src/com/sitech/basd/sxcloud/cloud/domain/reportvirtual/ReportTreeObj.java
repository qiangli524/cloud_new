package com.sitech.basd.sxcloud.cloud.domain.reportvirtual;

import com.sitech.basd.sxcloud.cloud.domain.resource.TbCloud2HostInfoObj;
import com.sitech.basd.sxcloud.cloud.domain.virtual.TbCloud2VirtualInfoObj;
import com.sitech.basd.sxcloud.cloud.domain.virtual.TbCloud2VirtualPoolObj;

public class ReportTreeObj {

	private String c_addr;
	private String mName;
	private TbCloud2VirtualInfoObj[] virtualObjs;
	private TbCloud2VirtualPoolObj[] virPoolObjs;
	private TbCloud2HostInfoObj[] hostObjs;

	public String getC_addr() {
		return c_addr;
	}

	public void setC_addr(String c_addr) {
		this.c_addr = c_addr;
	}

	/**
	 * @return Returns the mName.
	 */
	public String getMName() {
		return mName;
	}

	/**
	 * @param name
	 *            The mName to set.
	 */
	public void setMName(String name) {
		mName = name;
	}

	public TbCloud2VirtualInfoObj[] getVirtualObjs() {
		return virtualObjs;
	}

	public void setVirtualObjs(TbCloud2VirtualInfoObj[] virtualObjs) {
		this.virtualObjs = virtualObjs;
	}

	public TbCloud2HostInfoObj[] getHostObjs() {
		return hostObjs;
	}

	public void setHostObjs(TbCloud2HostInfoObj[] hostObjs) {
		this.hostObjs = hostObjs;
	}

	public TbCloud2VirtualPoolObj[] getVirPoolObjs() {
		return virPoolObjs;
	}

	public void setVirPoolObjs(TbCloud2VirtualPoolObj[] virPoolObjs) {
		this.virPoolObjs = virPoolObjs;
	}
}
