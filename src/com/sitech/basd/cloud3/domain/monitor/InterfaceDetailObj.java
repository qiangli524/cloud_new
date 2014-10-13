package com.sitech.basd.cloud3.domain.monitor;

import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;

public class InterfaceDetailObj extends BaseObj {
	private int id;
	private String start_time;
	private String end_time;
	private String interval;
	private String inter_id;
	private String issuccess;
	private String runlog;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStart_time() {
		return start_time;
	}

	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}

	public String getEnd_time() {
		return end_time;
	}

	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}

	public String getInterval() {
		return interval;
	}

	public void setInterval(String interval) {
		this.interval = interval;
	}

	public String getInter_id() {
		return inter_id;
	}

	public void setInter_id(String inter_id) {
		this.inter_id = inter_id;
	}

	public String getIssuccess() {
		return issuccess;
	}

	public void setIssuccess(String issuccess) {
		this.issuccess = issuccess;
	}

	public String getRunlog() {
		return runlog;
	}

	public void setRunlog(String runlog) {
		this.runlog = runlog;
	}

}
