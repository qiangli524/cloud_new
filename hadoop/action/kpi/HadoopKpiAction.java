package action.kpi;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import service.kpi.HadoopKpiService;
import service.relation.ServiceKpiRelationService;
import util.HadoopConstant;

import com.sitech.basd.sxcloud.rsmu.web.BaseAction;
import com.sitech.utils.randomid.RandomUUID;
import com.sitech.utils.servlet.PrintWriterOut;

import dao.kpi.HadoopKpiDao;
import domain.kpi.HadoopKpiObj;
import domain.relation.ServiceKpiRelationObj;

@Controller("hadoopKpiAction")
@Scope("prototype")
public class HadoopKpiAction extends BaseAction {

	private String id;

	private HadoopKpiObj kpiObj;

	private List<HadoopKpiObj> resultList;

	private String oper;// edit or add

	private List serviceList;// 服务名称

	private String serviceId;

	private String result;

	private String file;

	private HadoopKpiObj obj = new HadoopKpiObj();

	@Autowired
	private HadoopKpiDao hadoopKpiDao;
	@Autowired
	private HadoopKpiService hadoopKpiService;

	@Autowired
	private ServiceKpiRelationService serviceKpiRelationService;

	public String saveHadoopKpi() {
		if (oper != null && "add".equals(oper)) {
			String uuid = RandomUUID.getUuid();
			kpiObj.setId(uuid);
			hadoopKpiDao.insertHadoopKpi(kpiObj);
		} else {
			if (kpiObj.getId() != null) {
				hadoopKpiDao.updateHadoopKpi(kpiObj);
			}
		}
		return null;
	}

	public String listAllHadoopKpis() {
		// 查询服务名称集合
		ServiceKpiRelationObj relationObj = new ServiceKpiRelationObj();
		relationObj.setNodeType(HadoopConstant.serviceNode);
		serviceList = serviceKpiRelationService.queryServiceNameList(relationObj);

		obj.setPagination(this.getPaginater().initPagination(request));// 分页
		resultList = hadoopKpiDao.queryHadoopKpiLists(obj);
		return "list_all_hadoop_kpis";
	}

	public String deleteHadoopKpi() {
		HadoopKpiObj obj = new HadoopKpiObj();
		obj.setId(id);
		if (id != null) {
			hadoopKpiDao.deleteByObj(obj);
		}
		return null;
	}

	public String addNewKpi() {
		if (oper != null && "edit".equals(oper)) {
			if (id != null) {
				HadoopKpiObj temp = new HadoopKpiObj();
				temp.setId(id);
				List<HadoopKpiObj> objs = hadoopKpiDao.queryHadoopKpiLists(temp);
				kpiObj = objs.get(0);
			}
		}
		return "add_kpi_jps";
	}

	/**
	 * 
	 * @Title: importiKpi
	 * @Description: 进入选择导入文件界面
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-4-24 下午6:01:41
	 */
	public String importiKpi() {
		return "importiKpi";
	}

	/**
	 * 
	 * @Title: importFromXls
	 * @Description: 从excel模板导入监控指标配置数据
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-4-25 上午9:21:12
	 */
	public String importFromXls() throws Exception {
		/** 用js获取不到绝对路径，xxx */
		this.result = hadoopKpiService.importFromXls(file);
		String callback = "<script>parent.importCallBack('" + result
				+ "')</script>";
		PrintWriterOut.printWirter(response, callback);
		return null;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public HadoopKpiObj getKpiObj() {
		return kpiObj;
	}

	public void setKpiObj(HadoopKpiObj kpiObj) {
		this.kpiObj = kpiObj;
	}

	public List<HadoopKpiObj> getResultList() {
		return resultList;
	}

	public void setResultList(List<HadoopKpiObj> resultList) {
		this.resultList = resultList;
	}

	public String getOper() {
		return oper;
	}

	public void setOper(String oper) {
		this.oper = oper;
	}

	public List getServiceList() {
		return serviceList;
	}

	public void setServiceList(List serviceList) {
		this.serviceList = serviceList;
	}

	public String getServiceId() {
		return serviceId;
	}

	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}

	public HadoopKpiObj getObj() {
		return obj;
	}

	public void setObj(HadoopKpiObj obj) {
		this.obj = obj;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}


}
