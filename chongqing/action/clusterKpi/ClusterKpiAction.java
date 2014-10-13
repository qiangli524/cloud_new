package action.clusterKpi;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import com.sitech.basd.fusioncharts.util.JacksonUtil;
import com.sitech.basd.fusioncharts.vo.FusionCharts;
import com.sitech.basd.sxcloud.config.Constant;
import com.sitech.basd.sxcloud.rsmu.web.BaseAction;
import com.sitech.utils.servlet.PrintWriterOut;



import domain.clusterKpi.ClusterKpiObj;
import service.clusterChartService.ClusterChartService;


@SuppressWarnings("serial")
@Controller("clusterKpiAction")
@Scope("prototype")
public class ClusterKpiAction  extends BaseAction {
	
	
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private ClusterChartService clusterChartService;
	
	public String viewCluster() {
    	
	 	   return "viewCluster";
	     }
	
	public String viewCpu() {
		
		return "viewCpu";
	}
	
   public String viewMem() {
		
		return "viewMem";
	}

    public String viewStore() {
	
	   return "viewStore";
    }
	
	public void showCpu() {
		
		ClusterKpiObj clusterKpiObj = new ClusterKpiObj();
		clusterKpiObj.setKpiid("PM-00-23-001-07");
		String domain = session.get(Constant.USER_DOMAIN)==null ? "" : session.get(Constant.USER_DOMAIN).toString();
		clusterKpiObj.setDomain(domain);
		
		
		
		FusionCharts fusionCharts = new FusionCharts();
		try {
			fusionCharts = clusterChartService.queryClusterKpi(clusterKpiObj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String dataXml = JacksonUtil.toJson(fusionCharts);
		printToScreen(dataXml);
		
		
	}
	
	public void showMem() {
		ClusterKpiObj clusterKpiObj = new ClusterKpiObj();
		clusterKpiObj.setKpiid("PM-00-23-001-10");
		String domain = session.get(Constant.USER_DOMAIN)==null ? "" : session.get(Constant.USER_DOMAIN).toString();
		clusterKpiObj.setDomain(domain);
		
		FusionCharts fusionCharts = new FusionCharts();
		try {
			fusionCharts = clusterChartService.queryClusterKpi(clusterKpiObj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String dataXml = JacksonUtil.toJson(fusionCharts);
		printToScreen(dataXml);
	}
	
	public void showStore() {
		ClusterKpiObj clusterKpiObj = new ClusterKpiObj();
		clusterKpiObj.setKpiid("PM-00-23-001-13");
		String domain = session.get(Constant.USER_DOMAIN)==null ? "" : session.get(Constant.USER_DOMAIN).toString();
		clusterKpiObj.setDomain(domain);
		
		FusionCharts fusionCharts = new FusionCharts();
		try {
			fusionCharts = clusterChartService.queryClusterKpi(clusterKpiObj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String dataXml = JacksonUtil.toJson(fusionCharts);
		printToScreen(dataXml);
	}
	
	
	/**
	 * @Title: printToScreen
	 * @Description: 将数据返回到页面
	 * @param
	 * @return void
	 * @throws
	 * @author lipp
	 * @version 1.0
	 * @createtime 2013-12-2 下午5:07:20
	 */
	private void printToScreen(String dataXml) {
		response.setCharacterEncoding("UTF-8");
		try {
			// PrintWriter pw = response.getWriter();
			// pw.print(dataXml);
			PrintWriterOut.printWirter(response, dataXml);
			// pw.flush();
			// pw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	



}
