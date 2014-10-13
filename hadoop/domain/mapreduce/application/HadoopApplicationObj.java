package domain.mapreduce.application;

import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;

/**
 * 
 * <p>
 * Title: HadoopApplicationObj
 * </p>
 * <p>
 * Description: 应用相关属性
 * </p>
 * <p>
 * Copyright: Copyright (c) 2014
 * </p>
 * <p>
 * Company: SI-TECH
 * </p>
 * 
 * @author yanggl
 * @version 1.0
 * @createtime 2014-1-11 下午4:24:23
 * 
 */
public class HadoopApplicationObj extends BaseObj {
	private String id;// 物理主键
	private String user;// 用户标识
	private String name;// job名称
	private String queue;// job所在队列
	private String start_time;// 开始时间
	private String finish_time;// 完成时间
	private String state;// job状态
	private String final_status;// job最终状态
	private Double progress;// job进度
	private String tracking_ui;// job日志链接
	private String cluster_id;// 集群ID
	private String host_id;// 主机ID
	private String app_id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getQueue() {
		return queue;
	}

	public void setQueue(String queue) {
		this.queue = queue;
	}

	public String getStart_time() {
		return start_time;
	}

	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}

	public String getFinish_time() {
		return finish_time;
	}

	public void setFinish_time(String finish_time) {
		this.finish_time = finish_time;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getFinal_status() {
		return final_status;
	}

	public void setFinal_status(String final_status) {
		this.final_status = final_status;
	}

	public Double getProgress() {
		return progress;
	}

	public void setProgress(Double progress) {
		this.progress = progress;
	}

	public String getTracking_ui() {
		return tracking_ui;
	}

	public void setTracking_ui(String tracking_ui) {
		this.tracking_ui = tracking_ui;
	}

	public String getCluster_id() {
		return cluster_id;
	}

	public void setCluster_id(String cluster_id) {
		this.cluster_id = cluster_id;
	}

	public String getHost_id() {
		return host_id;
	}

	public void setHost_id(String host_id) {
		this.host_id = host_id;
	}

	public String getApp_id() {
		return app_id;
	}

	public void setApp_id(String app_id) {
		this.app_id = app_id;
	}

}
