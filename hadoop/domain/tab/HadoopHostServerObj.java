package domain.tab;

import java.util.List;

import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;

public class HadoopHostServerObj extends BaseObj {
	
	private List<GraphURL> graphURLs;

	public List<GraphURL> getGraphURLs() {
		return graphURLs;
	}

	public void setGraphURLs(List<GraphURL> graphURLs) {
		this.graphURLs = graphURLs;
	}
	
	
}
