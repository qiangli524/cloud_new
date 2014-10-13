package com.sitech.basd.yicloud.domain.vmman;

/**
 * 推送虚拟机信息给CMBD
*    
* 项目名称：YiCloudBkg   
* 类名称：VMHostSimple   
* 类描述：   
* 创建人：shijc   
* 创建时间：2012-8-2 上午10:01:19   
* 修改人：shijc   
* 修改时间：2012-8-2 上午10:01:19   
* 修改备注：   
* @version    
*
 */
public class VMHostInterface {
	private String vh_ID; // '虚拟机编号';

	private String vh_NAME; // '虚拟机名称';

	private String vh_STAT; // '虚拟机状态';

	private String vh_DESC; // '虚拟机描述';

	private String vh_CPU; // 'CPU个数';

	private String vh_MEM; // '内存大小';

	private String vh_STORAGE; // '存储大小';

	private String vh_IP; // '虚拟机IP';

	private String vh_TYPE; // '虚拟机类型：IBM、KVm、vmware等';

	private String vh_CID;

	public String getVh_CID() {
		return vh_CID;
	}

	public void setVh_CID(String vhCID) {
		vh_CID = vhCID;
	}

	public VMHostInterface(String vhID, String vhNAME, String vhSTAT,
			String vhDESC, String vhCPU, String vhMEM, String vhSTORAGE,
			String vhIP, String vhTYPE, String vh_ID) {
		super();
		vh_ID = vhID;
		vh_NAME = vhNAME;
		vh_STAT = vhSTAT;
		vh_DESC = vhDESC;
		vh_CPU = vhCPU;
		vh_MEM = vhMEM;
		vh_STORAGE = vhSTORAGE;
		vh_IP = vhIP;
		vh_TYPE = vhTYPE;
		vh_CID = vh_ID;
	}

	public VMHostInterface() {
		super();
	}

	public String getVh_CPU() {
		return vh_CPU;
	}

	public void setVh_CPU(String vh_CPU) {
		this.vh_CPU = vh_CPU;
	}

	public String getVh_DESC() {
		return vh_DESC;
	}

	public void setVh_DESC(String vh_DESC) {
		this.vh_DESC = vh_DESC;
	}

	public String getVh_ID() {
		return vh_ID;
	}

	public void setVh_ID(String vh_ID) {
		this.vh_ID = vh_ID;
	}

	public String getVh_IP() {
		return vh_IP;
	}

	public void setVh_IP(String vh_IP) {
		this.vh_IP = vh_IP;
	}

	public String getVh_MEM() {
		return vh_MEM;
	}

	public void setVh_MEM(String vh_MEM) {
		this.vh_MEM = vh_MEM;
	}

	public String getVh_NAME() {
		return vh_NAME;
	}

	public void setVh_NAME(String vh_NAME) {
		this.vh_NAME = vh_NAME;
	}

	public String getVh_STAT() {
		return vh_STAT;
	}

	public void setVh_STAT(String vh_STAT) {
		this.vh_STAT = vh_STAT;
	}

	public String getVh_STORAGE() {
		return vh_STORAGE;
	}

	public void setVh_STORAGE(String vh_STORAGE) {
		this.vh_STORAGE = vh_STORAGE;
	}

	public String getVh_TYPE() {
		return vh_TYPE;
	}

	public void setVh_TYPE(String vh_TYPE) {
		this.vh_TYPE = vh_TYPE;
	}

}
