package com.sitech.basd.yicloud.domain.globaltask;

import java.io.Serializable;

import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;

public class GlobalTaskObj extends BaseObj implements Serializable, Cloneable {

	/** serialVersionUID */
	private static final long serialVersionUID = -1234283457587347425L;
	private String id;// 主键uuid
	private String createrName;// 任务创建人
	private String name;// 任务名称
	private String type;// 任务类型：1:部署任务，2：创建虚拟机任务 。。。。
	private String progress;// 任务进度: 10,30,40...
	private String status;// 任务状态：0：未执行，1：执行中，2：执行完成
	private String content;// 任务内容
	private String createTime;// 任务创建日期
	private String endTime;// 任务完成时间

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCreaterName() {
		return createrName;
	}

	public void setCreaterName(String createrName) {
		this.createrName = createrName;
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

	public String getProgress() {
		return progress;
	}

	public void setProgress(String progress) {
		this.progress = progress;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

}
