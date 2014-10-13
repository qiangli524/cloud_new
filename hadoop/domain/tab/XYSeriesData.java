package domain.tab;

import java.util.ArrayList;
import java.util.List;

import com.sitech.basd.fusioncharts.vo.Data;

public class XYSeriesData {
	private String xySeriesTitle;
	private String serverNodeID;
	private String hostName;
	private String clusterName;
	private List<Data> datas=new ArrayList<Data>();
	
	public String getServerNodeID() {
		return serverNodeID;
	}
	public void setServerNodeID(String serverNodeID) {
		this.serverNodeID = serverNodeID;
	}
	public String getHostName() {
		return hostName;
	}
	public void setHostName(String hostName) {
		this.hostName = hostName;
	}
	public String getClusterName() {
		return clusterName;
	}
	public void setClusterName(String clusterName) {
		this.clusterName = clusterName;
	}
	public String getXySeriesTitle() {
		return xySeriesTitle;
	}
	public void setXySeriesTitle(String xySeriesTitle) {
		this.xySeriesTitle = xySeriesTitle;
	}
	public List<Data> getDatas() {
		return datas;
	}
	public void setDatas(List<Data> datas) {
		this.datas = datas;
	}

}
