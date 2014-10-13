package domain.tab;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sitech.basd.fusioncharts.vo.Data;

import domain.kpi.HadoopKpiObj;

public class MapDataSet{
	
	private String kpiId;
	
	private String Caption;
	
	private List<Data> datas=new ArrayList<Data>();
	
	private int datasSize;
	
	private Map<String,HadoopKpiObj> kpiIds=new HashMap<String,HadoopKpiObj>();
	
	private String hostName;
	
	private int interval;//间隔时间
	
	private int potCount;//查询的点数
	
	private String startData;//开始时间
	
	private String endData;//结束时间
	
	private String clusterName;
	
	public List<Data> getDatas() {
		return datas;
	}

	public void setDatas(List<Data> datas) {
		this.datas = datas;
	}

	public int getDatasSize() {
		return datasSize;
	}

	public void setDatasSize(int datasSize) {
		this.datasSize = datasSize;
	}

	public int getPotCount() {
		return potCount;
	}

	public void setPotCount(int potCount) {
		this.potCount = potCount;
	}

	public String getHostName() {
		return hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	public String getKpiId() {
		return kpiId;
	}

	public void setKpiId(String kpiId) {
		this.kpiId = kpiId;
	}

	public Map<String, HadoopKpiObj> getKpiIds() {
		return kpiIds;
	}

	public void setKpiIds(Map<String, HadoopKpiObj> kpiIds) {
		this.kpiIds = kpiIds;
	}

	public int getInterval() {
		return interval;
	}

	public void setInterval(int interval) {
		this.interval = interval;
	}

	public String getStartData() {
		return startData;
	}

	public void setStartData(String startData) {
		this.startData = startData;
	}

	public String getEndData() {
		return endData;
	}

	public String getCaption() {
		return Caption;
	}

	public void setCaption(String caption) {
		Caption = caption;
	}

	public void setEndData(String endData) {
		this.endData = endData;
	}

	public String getClusterName() {
		return clusterName;
	}

	public void setClusterName(String clusterName) {
		this.clusterName = clusterName;
	}
	
	
}


