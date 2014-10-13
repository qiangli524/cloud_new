package domain.mapreduce.job;

import java.util.Date;

import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;

/**
 * 
 * <p>
 * Title: HadoopJobObj
 * </p>
 * <p>
 * Description: Job相关属性
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
 * @createtime 2014-1-14 下午5:17:55
 * 
 */
public class HadoopJobObj extends BaseObj {
	private String id;//job的id
	private String name;//job的名字
	private String user;//用户名字
	private String state;//job的状态(NEW, INITED, RUNNING, SUCCEEDED, FAILED, KILL_WAIT, KILLED, ERROR)
	private String startTime;// job开始时间
	private String finishTime;// job的完成时间
	private String elapsedTime;// 启动后经过的时间
	private Integer mapsTotal;// map总数目
	private Integer mapsCompleted;// map完成的数目
	private Integer reducesTotal;// reduce总数目
	private Integer reducesCompleted;// reduce完成数目
	private String diagnostics;//信息
	private Boolean uberized;//是否是完整的
	private Integer mapsPending;// map未运行的个数
	private Integer mapsRunning;// map正在运行的个数
	private Integer reducesPending;// reduce未运行的个数
	private Integer reducesRunning;// reduce正在运行的个数
	private Integer newReduceAttempts;// 新的Reduce个数
	private Integer runningReduceAttempts;// 运行Reduce的个数
	private Integer failedReduceAttempts;// Reduce失败的个数
	private Integer killedReduceAttempts;// kill Reduce的个数
	private Integer successfulReduceAttempts;// 成功的Reduce个数
	private Integer newMapAttempts;//新的map个数
	private Integer runningMapAttempts;//正在运行map个数
	private Integer failedMapAttempts;//失败的map个数
	private Integer killedMapAttempts;// kill map的个数
	private Integer successfulMapAttempts;//成功的map个数
	private String application_id;//所属application的id
	private	String job_id;//job的标示（采集到的）
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
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
	public Integer getMapsTotal() {
		return mapsTotal;
	}
	public void setMapsTotal(Integer mapsTotal) {
		this.mapsTotal = mapsTotal;
	}
	public Integer getMapsCompleted() {
		return mapsCompleted;
	}
	public void setMapsCompleted(Integer mapsCompleted) {
		this.mapsCompleted = mapsCompleted;
	}
	public Integer getReducesTotal() {
		return reducesTotal;
	}
	public void setReducesTotal(Integer reducesTotal) {
		this.reducesTotal = reducesTotal;
	}
	public Integer getReducesCompleted() {
		return reducesCompleted;
	}
	public void setReducesCompleted(Integer reducesCompleted) {
		this.reducesCompleted = reducesCompleted;
	}
	public String getDiagnostics() {
		return diagnostics;
	}
	public void setDiagnostics(String diagnostics) {
		this.diagnostics = diagnostics;
	}
	public Boolean getUberized() {
		return uberized;
	}
	public void setUberized(Boolean uberized) {
		this.uberized = uberized;
	}
	public Integer getMapsPending() {
		return mapsPending;
	}
	public void setMapsPending(Integer mapsPending) {
		this.mapsPending = mapsPending;
	}
	public Integer getMapsRunning() {
		return mapsRunning;
	}
	public void setMapsRunning(Integer mapsRunning) {
		this.mapsRunning = mapsRunning;
	}
	public Integer getReducesPending() {
		return reducesPending;
	}
	public void setReducesPending(Integer reducesPending) {
		this.reducesPending = reducesPending;
	}
	public Integer getReducesRunning() {
		return reducesRunning;
	}
	public void setReducesRunning(Integer reducesRunning) {
		this.reducesRunning = reducesRunning;
	}
	public Integer getNewReduceAttempts() {
		return newReduceAttempts;
	}
	public void setNewReduceAttempts(Integer newReduceAttempts) {
		this.newReduceAttempts = newReduceAttempts;
	}
	public Integer getRunningReduceAttempts() {
		return runningReduceAttempts;
	}
	public void setRunningReduceAttempts(Integer runningReduceAttempts) {
		this.runningReduceAttempts = runningReduceAttempts;
	}
	public Integer getFailedReduceAttempts() {
		return failedReduceAttempts;
	}
	public void setFailedReduceAttempts(Integer failedReduceAttempts) {
		this.failedReduceAttempts = failedReduceAttempts;
	}
	public Integer getKilledReduceAttempts() {
		return killedReduceAttempts;
	}
	public void setKilledReduceAttempts(Integer killedReduceAttempts) {
		this.killedReduceAttempts = killedReduceAttempts;
	}
	public Integer getSuccessfulReduceAttempts() {
		return successfulReduceAttempts;
	}
	public void setSuccessfulReduceAttempts(Integer successfulReduceAttempts) {
		this.successfulReduceAttempts = successfulReduceAttempts;
	}
	public Integer getNewMapAttempts() {
		return newMapAttempts;
	}
	public void setNewMapAttempts(Integer newMapAttempts) {
		this.newMapAttempts = newMapAttempts;
	}
	public Integer getRunningMapAttempts() {
		return runningMapAttempts;
	}
	public void setRunningMapAttempts(Integer runningMapAttempts) {
		this.runningMapAttempts = runningMapAttempts;
	}
	public Integer getFailedMapAttempts() {
		return failedMapAttempts;
	}
	public void setFailedMapAttempts(Integer failedMapAttempts) {
		this.failedMapAttempts = failedMapAttempts;
	}
	public Integer getKilledMapAttempts() {
		return killedMapAttempts;
	}
	public void setKilledMapAttempts(Integer killedMapAttempts) {
		this.killedMapAttempts = killedMapAttempts;
	}
	public Integer getSuccessfulMapAttempts() {
		return successfulMapAttempts;
	}
	public void setSuccessfulMapAttempts(Integer successfulMapAttempts) {
		this.successfulMapAttempts = successfulMapAttempts;
	}
	public String getApplication_id() {
		return application_id;
	}
	public void setApplication_id(String application_id) {
		this.application_id = application_id;
	}
	public String getJob_id() {
		return job_id;
	}
	public void setJob_id(String job_id) {
		this.job_id = job_id;
	}
	
}
