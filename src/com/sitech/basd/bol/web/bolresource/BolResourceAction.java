package com.sitech.basd.bol.web.bolresource;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sitech.basd.bol.domain.bolresource.BolResourceObj;
import com.sitech.basd.bol.domain.bolresource.BolResourcesRegisterObj;
import com.sitech.basd.bol.domain.host.BolHostVO;
import com.sitech.basd.bol.domain.nodestatus.BolNodeStatusObj;
import com.sitech.basd.bol.service.bolresource.BolResourceService;
import com.sitech.basd.bol.service.host.BolHostService;
import com.sitech.basd.bol.service.node.BolNodeServiceImpl;
import com.sitech.basd.bol.service.nodestatus.BolNodeStatusService;
import com.sitech.basd.sxcloud.rsmu.web.BaseAction;
import com.sitech.vo.util.BolConstant;

@SuppressWarnings("serial")
@Controller("bolResourceAction")
@Scope("prototype")
public class BolResourceAction extends BaseAction {

	@Autowired
	private BolResourceService bolResourceService;
	@Autowired
	private BolNodeStatusService bolNodeStatusService;
	@Autowired
	private BolHostService bolHostService;

	private BolResourceObj bolResourceObj;

	private String host;

	private String type;

	private List<BolResourceObj> resultList;

	private String pid;

	private Integer hostCount;// 主机总数

	private Integer processCount;// 进程总数

	private Integer processUsedCount;

	private Integer fileCount;// 文件汇总（指定目录）

	private Integer fileUsedCount;

	private Integer fileDirCount;// 文件句柄数（指定目录）

	private Integer fileDirUsedCount;

	private Integer fileProcessCount;// 文件句柄数（指定进程）

	private Integer fileProcessUsedCount;

	private Integer cpuCount;// cpu总数

	private Integer cpuUsedCount;// cpu使用数

	private Double memoryCount;// 内存汇总

	private Double memoryUsedCount;// 内存使用量

	private Double storeCount;// 存储汇总

	private Double storeUsedCount;// 存储使用量

	private Integer messageQueneCount;// 消息队列总数

	private Integer messageQueneUsedCount;

	private Integer signalCount;// 信号总量

	private Integer signalUsedCount;

	private Integer dbConnCount;// 数据库连接数

	private Integer dbConnUsedCount;

	private Integer netConnCount;// 网络连接数

	private Integer netConnUsedCount;
	
	private List<BolResourcesRegisterObj> resourcesList;//资源登记
	
	private Integer status;
	
	private String name;
	
	private float nodeStatus;//节点状态
	
	private String nodeUpdateTime;//更新时间
	
	private int hostType; //主机类型

	public int getHostType() {
		return hostType;
	}

	public void setHostType(int hostType) {
		this.hostType = hostType;
	}

	public float getNodeStatus() {
		return nodeStatus;
	}

	public void setNodeStatus(float nodeStatus) {
		this.nodeStatus = nodeStatus;
	}

	public String getNodeUpdateTime() {
		return nodeUpdateTime;
	}

	public void setNodeUpdateTime(String nodeUpdateTime) {
		this.nodeUpdateTime = nodeUpdateTime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<BolResourcesRegisterObj> getResourcesList() {
		return resourcesList;
	}

	public void setResourcesList(List<BolResourcesRegisterObj> resourcesList) {
		this.resourcesList = resourcesList;
	}

	public Integer getProcessUsedCount() {
		return processUsedCount;
	}

	public void setProcessUsedCount(Integer processUsedCount) {
		this.processUsedCount = processUsedCount;
	}

	public Integer getFileUsedCount() {
		return fileUsedCount;
	}

	public void setFileUsedCount(Integer fileUsedCount) {
		this.fileUsedCount = fileUsedCount;
	}

	public Integer getFileDirUsedCount() {
		return fileDirUsedCount;
	}

	public void setFileDirUsedCount(Integer fileDirUsedCount) {
		this.fileDirUsedCount = fileDirUsedCount;
	}

	public Integer getFileProcessUsedCount() {
		return fileProcessUsedCount;
	}

	public void setFileProcessUsedCount(Integer fileProcessUsedCount) {
		this.fileProcessUsedCount = fileProcessUsedCount;
	}

	public Integer getMessageQueneUsedCount() {
		return messageQueneUsedCount;
	}

	public void setMessageQueneUsedCount(Integer messageQueneUsedCount) {
		this.messageQueneUsedCount = messageQueneUsedCount;
	}

	public Integer getSignalUsedCount() {
		return signalUsedCount;
	}

	public void setSignalUsedCount(Integer signalUsedCount) {
		this.signalUsedCount = signalUsedCount;
	}

	public Integer getDbConnUsedCount() {
		return dbConnUsedCount;
	}

	public void setDbConnUsedCount(Integer dbConnUsedCount) {
		this.dbConnUsedCount = dbConnUsedCount;
	}

	public Integer getNetConnUsedCount() {
		return netConnUsedCount;
	}

	public void setNetConnUsedCount(Integer netConnUsedCount) {
		this.netConnUsedCount = netConnUsedCount;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<BolResourceObj> getResultList() {
		return resultList;
	}

	public void setResultList(List<BolResourceObj> resultList) {
		this.resultList = resultList;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public Integer getHostCount() {
		return hostCount;
	}

	public void setHostCount(Integer hostCount) {
		this.hostCount = hostCount;
	}

	public Integer getProcessCount() {
		return processCount;
	}

	public void setProcessCount(Integer processCount) {
		this.processCount = processCount;
	}

	public Integer getFileCount() {
		return fileCount;
	}

	public void setFileCount(Integer fileCount) {
		this.fileCount = fileCount;
	}

	public Integer getFileDirCount() {
		return fileDirCount;
	}

	public void setFileDirCount(Integer fileDirCount) {
		this.fileDirCount = fileDirCount;
	}

	public Integer getFileProcessCount() {
		return fileProcessCount;
	}

	public void setFileProcessCount(Integer fileProcessCount) {
		this.fileProcessCount = fileProcessCount;
	}

	public Integer getCpuCount() {
		return cpuCount;
	}

	public void setCpuCount(Integer cpuCount) {
		this.cpuCount = cpuCount;
	}

	public Double getMemoryCount() {
		return memoryCount;
	}

	public void setMemoryCount(Double memoryCount) {
		this.memoryCount = memoryCount;
	}

	public Double getStoreCount() {
		return storeCount;
	}

	public void setStoreCount(Double storeCount) {
		this.storeCount = storeCount;
	}

	public Integer getMessageQueneCount() {
		return messageQueneCount;
	}

	public void setMessageQueneCount(Integer messageQueneCount) {
		this.messageQueneCount = messageQueneCount;
	}

	public Integer getSignalCount() {
		return signalCount;
	}

	public void setSignalCount(Integer signalCount) {
		this.signalCount = signalCount;
	}

	public Integer getDbConnCount() {
		return dbConnCount;
	}

	public void setDbConnCount(Integer dbConnCount) {
		this.dbConnCount = dbConnCount;
	}

	public Integer getNetConnCount() {
		return netConnCount;
	}

	public void setNetConnCount(Integer netConnCount) {
		this.netConnCount = netConnCount;
	}

	public Integer getCpuUsedCount() {
		return cpuUsedCount;
	}

	public void setCpuUsedCount(Integer cpuUsedCount) {
		this.cpuUsedCount = cpuUsedCount;
	}

	public Double getMemoryUsedCount() {
		return memoryUsedCount;
	}

	public void setMemoryUsedCount(Double memoryUsedCount) {
		this.memoryUsedCount = memoryUsedCount;
	}

	public Double getStoreUsedCount() {
		return storeUsedCount;
	}

	public void setStoreUsedCount(Double storeUsedCount) {
		this.storeUsedCount = storeUsedCount;
	}

	public BolResourceObj getBolResourceObj() {
		return bolResourceObj;
	}

	public void setBolResourceObj(BolResourceObj bolResourceObj) {
		this.bolResourceObj = bolResourceObj;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	// /**
	// * @Title: showRootInfo
	// * @Description: 展示资源视图根节点信息
	// * 主机总数 进程总数 内存总量 存储总量 文件句柄数（指定目录）
	// 文件句柄数（指定进程） 消息队列总数 信号量总数 数据库连接总数 网络连接总数
	// cpu总量 文件汇总（指定目录）
	// * @param
	// * @return String
	// * @throws
	// * @author lipengpeng
	// * @version 1.0
	// * @createtime 2013-11-5 下午3:38:01
	// */
	// public String showRootInfo(){
	// //主机总数 进程总数 文件汇总（指定目录） 文件句柄数（指定目录） 文件句柄数（指定进程）
	// hostCount = bolResourceService.countHostNum();
	// BolResourceObj bolResourceObj = new BolResourceObj();
	// this.showInfo(bolResourceObj);
	// return "showRootInfo";
	// }
	//
	// /**
	// * @Title: showHostInfo
	// * @Description: 显示主机信息
	// * @param
	// * @return String
	// * @throws
	// * @author lipengpeng
	// * @version 1.0
	// * @createtime 2013-11-6 下午2:33:51
	// */
	// public String showHostInfo(){
	// BolResourceObj bolResourceObj = new BolResourceObj();
	// bolResourceObj.setHOST(host);
	// this.showInfo(bolResourceObj);
	// return "showHostInfo";
	// }

	/**
	 * @Title: showInfo
	 * @Description: 展示信息
	 * @param
	 * @return void
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-11-6 下午2:43:02
	 */
	public String showInfo() {
		if (bolResourceObj == null) {
			bolResourceObj = new BolResourceObj();
		}
		if (BolConstant.HOST.equals(type)) {
			bolResourceObj.setHOST(host);
		} else {
			hostCount = bolResourceService.countHostNum();
		}

		bolResourceObj.setTYPE("PT");
		processCount = bolResourceService.queryForSum(bolResourceObj)
				.intValue();
		BolResourceObj countObj = new BolResourceObj();
		countObj.setHOST(host);
		countObj.setTYPE("HLA");
		countObj.setSTATUS(1);
		processUsedCount = bolResourceService.countForResource(countObj);

		bolResourceObj.setTYPE("CHT");
		fileCount = bolResourceService.queryForSum(bolResourceObj).intValue();
		fileUsedCount = bolResourceService.queryForUsed(bolResourceObj)
				.intValue();

		bolResourceObj.setTYPE("DHT");
		fileDirCount = bolResourceService.queryForSum(bolResourceObj)
				.intValue();
		fileDirUsedCount = bolResourceService.queryForUsed(bolResourceObj)
				.intValue();

		bolResourceObj.setTYPE("PHT");
		fileProcessCount = bolResourceService.queryForSum(bolResourceObj)
				.intValue();
		fileProcessUsedCount = bolResourceService.queryForUsed(bolResourceObj)
				.intValue();

		// cpu、内存、存储
		bolResourceObj.setTYPE("CT");
		cpuUsedCount = bolResourceService.queryForUsed(bolResourceObj)
				.intValue();
		cpuCount = bolResourceService.queryForSum(bolResourceObj).intValue();

		bolResourceObj.setTYPE("MT");
		memoryUsedCount = bolResourceService.queryForUsed(bolResourceObj)
				.doubleValue();
		memoryCount = bolResourceService.queryForSum(bolResourceObj)
				.doubleValue();

		bolResourceObj.setTYPE("ST");
		storeUsedCount = bolResourceService.queryForUsed(bolResourceObj)
				.doubleValue();
		storeCount = bolResourceService.queryForSum(bolResourceObj)
				.doubleValue();

		// 消息队列总数 信号量总数 数据库连接总数 网络连接总数
		bolResourceObj.setTYPE("QT");
		messageQueneCount = bolResourceService.queryForSum(bolResourceObj)
				.intValue();
		messageQueneUsedCount = bolResourceService.queryForUsed(bolResourceObj)
				.intValue();

		bolResourceObj.setTYPE("SNT");
		signalCount = bolResourceService.queryForSum(bolResourceObj).intValue();
		signalUsedCount = bolResourceService.queryForUsed(bolResourceObj)
				.intValue();

		bolResourceObj.setTYPE("DBT");
		dbConnCount = bolResourceService.queryForSum(bolResourceObj).intValue();
		dbConnUsedCount = bolResourceService.queryForUsed(bolResourceObj)
				.intValue();

		bolResourceObj.setTYPE("NT");
		netConnCount = bolResourceService.queryForSum(bolResourceObj)
				.intValue();
		netConnUsedCount = bolResourceService.queryForUsed(bolResourceObj)
				.intValue();
		
		//查询状态和更新时间
		if(type != null && type.equals("1")){
			BolNodeStatusObj statusObj = new BolNodeStatusObj();
			statusObj.setNodeCode(host);
			statusObj.setNodeField("STATUS");
			statusObj = bolNodeStatusService.queryNodeStatus(statusObj);
			if(statusObj != null){
				nodeStatus = statusObj.getNodeValue();
				SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
				SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				try {
					nodeUpdateTime = format1.format(format.parse(statusObj.getTime()));
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}else{
				nodeUpdateTime = "--";
			}
			
			BolHostVO hostVo = new BolHostVO();
			hostVo.setHostCode(host);
			List<BolHostVO> hostList = null;
			try {
				hostList = bolHostService.queryForListByObj(hostVo);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			if(hostList != null && hostList.size()>0){
				hostType = hostList.get(0).getHostType();
			}else{
				hostType = 0;
			}
		}
		
		return "showInfo";
	}

	/**
	 * @Title: listProcess
	 * @Description: 展示进程
	 * @param
	 * @return String
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-11-6 下午3:36:24
	 */
	public String listProcess() {
		if (bolResourceObj == null) {
			bolResourceObj = new BolResourceObj();
		}
		if (bolResourceObj.getSTATUS() != null
				&& -1 == bolResourceObj.getSTATUS()) {
			bolResourceObj.setSTATUS(null);
		}
		bolResourceObj.setHOST(host);
		bolResourceObj.setTYPE(type);
		bolResourceObj.setPagination(this.getPaginater()
				.initPagination(request));
		resultList = bolResourceService.queryForListByObj(bolResourceObj);
		return "listProcess";
	}

	/**
	 * @Title: listMemory
	 * @Description: 展示内存信息
	 * @param
	 * @return String
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-11-6 下午3:37:48
	 */
	public String listMemory() {
		return null;
	}

	/**
	 * @Title: listStore
	 * @Description: 展示存储信息
	 * @param
	 * @return String
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-11-6 下午3:38:11
	 */
	public String listStore() {
		return null;
	}

	/**
	 * @Title: listResourceForProcess
	 * @Description: 展示进程所占用的资源
	 * @param
	 * @return String
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-11-7 下午1:45:17
	 */
	public String listResourceForProcess() {
		if (bolResourceObj == null) {
			bolResourceObj = new BolResourceObj();
		}
		bolResourceObj.setPROG(pid);
		bolResourceObj.setTYPE(type);
		bolResourceObj.setPagination(this.getPaginater()
				.initPagination(request));
		resultList = bolResourceService.queryResourceForProcess(bolResourceObj);
		return "listResource";
	}
	
	/**
	 * 
	 * @Title: resourcesRegister
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param
	 * @return String
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime Jun 13, 2014 4:10:30 PM
	 */
	public String resourcesRegister(){
		BolResourcesRegisterObj obj = new BolResourcesRegisterObj();
		obj.setHost(host);
		if(name != null && !name.equals("")){
			obj.setName(name.trim());
		}
		obj.setStatus(1);
		resourcesList = bolResourceService.queryResourcesRegister(obj);
		return "resourcesRegister";
	}
}
