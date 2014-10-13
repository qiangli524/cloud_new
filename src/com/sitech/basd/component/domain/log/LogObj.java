package com.sitech.basd.component.domain.log;

import java.io.Serializable;

import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;

/**
 * 
 * <p>
 * Title: LogObj
 * </p>
 * <p>
 * Description:配置的日志obj
 * </p>
 * <p>
 * Company: si-tech
 * </p>
 * 
 * @author duangh
 * @date May 22, 2013
 */
public class LogObj extends BaseObj implements Serializable, Cloneable {

	/** serialVersionUID */
	private static final long serialVersionUID = 4477263201704766162L;
	public String id;// ID，主键，用uuid做主键
	public String userId;// 主机用户id
	public String name;// 日志文件名称
	public String type; // 日志文件类型,1为文件，2为文件夹
	public String category;// 日志类别：0通用，1业务部署，2脚本执行日志，3其它
	public String description;// 文件描述
	public String path;// 文件路径
	public String extension;// 过滤文件的后缀名
	public String insDate;// 插入时间

	public String hostIP;// 主机IP地址
	public String hostUser;// 主机用户名
	public String userPwd;// 主机用户密码
	public Integer hostPort;//主机SSH端口

	public String appId;// 应用ID

	// 部署实例ID转码后拼接，已,分割，用于查询业务中心树中基准应用上脚本列表
	private String encodeExampleStr;

	public String getEncodeExampleStr() {
		return encodeExampleStr;
	}

	public void setEncodeExampleStr(String encodeExampleStr) {
		this.encodeExampleStr = encodeExampleStr;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	public String getHostIP() {
		return hostIP;
	}

	public void setHostIP(String hostIP) {
		this.hostIP = hostIP;
	}

	public String getHostUser() {
		return hostUser;
	}

	public void setHostUser(String hostUser) {
		this.hostUser = hostUser;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getInsDate() {
		return insDate;
	}

	public void setInsDate(String insDate) {
		this.insDate = insDate;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	/**
	 *
	 * @return the hostPort
	 */
	public Integer getHostPort() {
		return hostPort;
	}

	/**
	 *
	 * @param hostPort the hostPort to set
	 */
	public void setHostPort(Integer hostPort) {
		this.hostPort = hostPort;
	}

}
