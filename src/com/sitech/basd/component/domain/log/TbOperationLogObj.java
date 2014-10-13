package com.sitech.basd.component.domain.log;

import java.io.Serializable;

import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;

/**
 * 
 * <p>
 * Title: TbOperationLogObj
 * </p>
 * <p>
 * Description: 操作 实例日志对象
 * </p>
 * <p>
 * Copyright: Copyright (c) 2013
 * </p>
 * <p>
 * Company: SI-TECH
 * </p>
 * 
 * @author yanggl
 * @version 1.0
 * @createtime 2013-12-20 下午4:02:22
 * 
 */
public class TbOperationLogObj extends BaseObj implements Serializable, Cloneable {
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;
	private String exampleId;// 实例ID
	private String exampleName;// 实例名称
	private String operationType;// 操作类型(0 部署,1 上线,2 回滚)
	private String isSuccess;// 是否成功(0 成功,1 失败)
	private String description;// 描述
	private String update_time;// 时间

	public String getExampleId() {
		return exampleId;
	}

	public void setExampleId(String exampleId) {
		this.exampleId = exampleId;
	}

	public String getExampleName() {
		return exampleName;
	}

	public void setExampleName(String exampleName) {
		this.exampleName = exampleName;
	}

	public String getOperationType() {
		return operationType;
	}

	public void setOperationType(String operationType) {
		this.operationType = operationType;
	}

	public String getIsSuccess() {
		return isSuccess;
	}

	public void setIsSuccess(String isSuccess) {
		this.isSuccess = isSuccess;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(String update_time) {
		this.update_time = update_time;
	}

}
