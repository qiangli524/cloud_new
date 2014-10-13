package action.jvm;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import service.host.HadoopHostInfoService;
import service.jvm.HadoopJvmService;

import com.sitech.basd.fusioncharts.util.JacksonUtil;
import com.sitech.basd.fusioncharts.vo.FusionCharts;
import com.sitech.basd.sxcloud.rsmu.web.BaseAction;
import com.sitech.utils.servlet.PrintWriterOut;

import domain.host.HadoopHostInfoObj;
import domain.jvm.HadoopJvmObj;

/**
 * 
 * <p>
 * Title: HadoopJvmAction
 * </p>
 * <p>
 * Description: jvm相关操作
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
 * @createtime 2014-1-18 下午5:22:08
 * 
 */
@Controller("hadoopJvmAction")
@Scope("prototype")
public class HadoopJvmAction extends BaseAction {
	@Autowired
	private HadoopJvmService hadoopJvmService;
	@Autowired
	private HadoopHostInfoService hadoopHostInfoService;

	private HadoopJvmObj obj;

	private HadoopHostInfoObj hostInfoObj;

	private List<HadoopHostInfoObj> resultList;

	private int errorCount;

	private int fatalCount;

	public HadoopJvmObj getObj() {
		return obj;
	}

	public void setObj(HadoopJvmObj obj) {
		this.obj = obj;
	}

	public HadoopHostInfoObj getHostInfoObj() {
		return hostInfoObj;
	}

	public void setHostInfoObj(HadoopHostInfoObj hostInfoObj) {
		this.hostInfoObj = hostInfoObj;
	}

	public int getErrorCount() {
		return errorCount;
	}

	public void setErrorCount(int errorCount) {
		this.errorCount = errorCount;
	}

	public int getFatalCount() {
		return fatalCount;
	}

	public void setFatalCount(int fatalCount) {
		this.fatalCount = fatalCount;
	}

	public List<HadoopHostInfoObj> getResultList() {
		return resultList;
	}

	public void setResultList(List<HadoopHostInfoObj> resultList) {
		this.resultList = resultList;
	}

	/**
	 * 
	 * @Title: showJvm
	 * @Description: jvm页面
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-1-17 上午10:09:50
	 */
	public String showJvm() {
		hostInfoObj = hadoopHostInfoService.queryForHadoopHostAndClusterInfoObj(hostInfoObj);// 查询
		return "showJvm";
	}

	/**
	 * 
	 * @Title: queryHadoopJvm
	 * @Description: 展示图标
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-1-17 上午10:08:38
	 */
	public String queryHadoopJvm() {
		FusionCharts fusionCharts = new FusionCharts();
		fusionCharts = hadoopJvmService.queryHadoopJvm(obj);
		String fuString = JacksonUtil.toJson(fusionCharts);
		printJson(fuString);
		return null;
	}

	/**
	 * 
	 * @Title: queryLogHostCount
	 * @Description: Log中输出ERROR,FATAL的主机个数
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @throws Exception
	 * @createtime 2014-1-18 上午11:31:56
	 */
	public String queryLogHostCount() throws Exception {
		obj.setKpi_id("jvm.JvmMetrics.LogError");
		errorCount = hadoopJvmService.queryLogHostCount(obj);
		obj.setKpi_id("jvm.JvmMetrics.LogFatal");
		fatalCount = hadoopJvmService.queryLogHostCount(obj);
		return "logJvm";
	}

	/**
	 * 
	 * @Title: showHostList
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-1-18 下午4:16:19
	 */
	public String showHostList() throws Exception {
		obj.setPagination(this.getPaginater().initPagination(request));
		resultList = hadoopJvmService.showHostList(obj);
		return "hostInfo";
	}

	/**
	 * 
	 * @Title: printJson
	 * @Description: 传输Json数据到界面
	 * @param
	 * @return void
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-9-14 上午10:41:00
	 */
	private synchronized void printJson(String... params) {
		// response.setCharacterEncoding("UTF-8");
		// out = response.getWriter();
		if (params != null) {
			for (int i = 0; i < params.length; i++) {
				// out.println(params[i]);
				PrintWriterOut.printWirter(response, params[i]);
			}
		}
		// out.close();
	}
}
