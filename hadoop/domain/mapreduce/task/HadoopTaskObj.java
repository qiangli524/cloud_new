package domain.mapreduce.task;

import java.util.Date;

import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;

/**
 * 
 * <p>
 * Title: HadoopTaskObj
 * </p>
 * <p>
 * Description: 任务相关属性
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
 * @createtime 2014-1-14 下午5:18:15
 * 
 */
public class HadoopTaskObj extends BaseObj {
	private String id;//task的id
	private String state;// task的状态
	private String type;// task的类型(MAP or REDUCE)
	private String successfulAttempt;// 最后成功的ID
	private Float progress;// 进度
	private String startTime;// 开始时间
	private String finishTime;// 完成时间
	private String elapsedTime;// 启动后经过的时间
	private String task_id;//任务的唯一标识（采集到的）
	private String job_id;//job表中的id

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSuccessfulAttempt() {
		return successfulAttempt;
	}

	public void setSuccessfulAttempt(String successfulAttempt) {
		this.successfulAttempt = successfulAttempt;
	}

	public Float getProgress() {
		return progress;
	}

	public void setProgress(Float progress) {
		this.progress = progress;
	}
	
	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getFinishTime() {
		return finishTime;
	}

	public void setFinishTime(String finishTime) {
		this.finishTime = finishTime;
	}

	public String getElapsedTime() {
		return elapsedTime;
	}

	public void setElapsedTime(String elapsedTime) {
		this.elapsedTime = elapsedTime;
	}

	public String getTask_id() {
		return task_id;
	}

	public void setTask_id(String task_id) {
		this.task_id = task_id;
	}

	public String getJob_id() {
		return job_id;
	}

	public void setJob_id(String job_id) {
		this.job_id = job_id;
	}

}
