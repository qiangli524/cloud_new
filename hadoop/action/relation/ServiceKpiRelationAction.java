package action.relation;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import service.relation.ServiceKpiRelationService;
import util.HadoopConstant;

import com.sitech.basd.sxcloud.rsmu.web.BaseAction;
import com.sitech.utils.servlet.PrintWriterOut;

import domain.relation.ServiceKpiRelationObj;

/**
 * 
 * <p>
 * Title: ServiceKpiRelationAction
 * </p>
 * <p>
 * Description: 服务与KPI的关系相关操作
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
 * @createtime 2014-1-7 下午7:50:47
 * 
 */
@Controller("serviceKpiRelationAction")
@Scope("prototype")
public class ServiceKpiRelationAction extends BaseAction {
	@Autowired
	private ServiceKpiRelationService serviceKpiRelationService;

	private ServiceKpiRelationObj obj;
	
	private ServiceKpiRelationObj relationObj;

	private List<ServiceKpiRelationObj> resultList;

	private List<ServiceKpiRelationObj> serviceNameList;// 服务名称

	private List<ServiceKpiRelationObj> kpiList;// kpi名称

	private String kpi_ids;// kpi_id

	private String result;

	private String file;

	public ServiceKpiRelationObj getRelationObj() {
		return relationObj;
	}

	public void setRelationObj(ServiceKpiRelationObj relationObj) {
		this.relationObj = relationObj;
	}

	public List<ServiceKpiRelationObj> getServiceNameList() {
		return serviceNameList;
	}

	public void setServiceNameList(List<ServiceKpiRelationObj> serviceNameList) {
		this.serviceNameList = serviceNameList;
	}

	public List<ServiceKpiRelationObj> getKpiList() {
		return kpiList;
	}

	public void setKpiList(List<ServiceKpiRelationObj> kpiList) {
		this.kpiList = kpiList;
	}

	public ServiceKpiRelationObj getObj() {
		return obj;
	}

	public void setObj(ServiceKpiRelationObj obj) {
		this.obj = obj;
	}

	public List<ServiceKpiRelationObj> getResultList() {
		return resultList;
	}

	public void setResultList(List<ServiceKpiRelationObj> resultList) {
		this.resultList = resultList;
	}

	public String getKpi_ids() {
		return kpi_ids;
	}

	public void setKpi_ids(String kpi_ids) {
		this.kpi_ids = kpi_ids;
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

	/**
	 * 
	 * @Title: queryServiceKpiRelationList
	 * @Description: 获取服务与KPI相关列表
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-1-7 下午7:51:31
	 */
	public String queryServiceKpiRelationList() {
		if (obj == null) {
			obj = new ServiceKpiRelationObj();
		}
		obj.setPagination(this.getPaginater().initPagination(request));
		resultList = serviceKpiRelationService.queryServiceKpiRelatonList(obj);
		return "list";
	}

	/**
	 * 
	 * @Title: addServiceKpiRelation
	 * @Description: 显示添加页面同时初始化服务和KPI
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-1-7 下午7:52:19
	 */
	public String addServiceKpiRelation() {
		if (obj == null) {
			obj = new ServiceKpiRelationObj();
		}
		obj.setNodeType(HadoopConstant.serviceNode);
		serviceNameList = serviceKpiRelationService.queryServiceNameList(obj);
		return "add";
	}

	/**
	 * 
	 * @Title: saveServiceKpiRelation
	 * @Description: 插入操作
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-1-7 下午7:52:59
	 */
	public String saveServiceKpiRelation(){
		if (obj == null) {
			obj = new ServiceKpiRelationObj();
		}
		// 插入服务名字与服务kpi的关系表中
		if (kpi_ids != null && !"".equals(kpi_ids)) {
			String string[] = kpi_ids.split(",");
			for (int i = 0; i < string.length; i++) {
				obj.setKpi_id(string[i]);
				serviceKpiRelationService.saveServiceNameKpiRelation(obj);
			}
		}
		return null;
	}

	/**
	 * 
	 * @Title: delServiceKpiRelation
	 * @Description: 删除操作
	 * @param
	 * @return void
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-1-7 下午7:53:17
	 */
	public void delServiceKpiRelation(){
		if (obj == null) {
			obj = new ServiceKpiRelationObj();
		}
		if (obj.getKpi_id() != null && !"".equals(obj.getKpi_id())) {
			serviceKpiRelationService.delServiceNameKpiRelation(obj);
		}
	}

	/**
	 * 
	 * @Title: updServiceKpiRelation
	 * @Description: 显示修改页面
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-1-7 下午7:53:33
	 */
	public String updServiceKpiRelation(){
		if (obj == null) {
			obj = new ServiceKpiRelationObj();
		}
		ServiceKpiRelationObj kpiobj = new ServiceKpiRelationObj();
		relationObj = serviceKpiRelationService.queryServiceKpiRelaton(obj);
		kpiList = serviceKpiRelationService.queryKpiNameList(kpiobj);
		return "upd";
	}

	/**
	 * 
	 * @Title: editServiceKpiRelation
	 * @Description: 修改操作，先删除之前的关系，在插入修改完的关系
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-1-7 下午7:53:57
	 */
	public String editServiceKpiRelation() {
		if (obj == null) {
			obj = new ServiceKpiRelationObj();
		}
		if (obj.getOld_kpiId() != null && !"".equals(obj.getOld_kpiId())) {
			if (obj.getOld_kpiId() != kpi_ids) {
				obj.setKpi_id(obj.getOld_kpiId());
				serviceKpiRelationService.delServiceNameKpiRelation(obj);

				// 插入服务名字与服务kpi的关系表中
				if (kpi_ids != null && !"".equals(kpi_ids)) {
					String string[] = kpi_ids.split(",");
					for (int i = 0; i < string.length; i++) {
						obj.setKpi_id(string[i]);
						serviceKpiRelationService
								.saveServiceNameKpiRelation(obj);
					}
				}
			}
		}
		return null;
	}

	/**
	 * 
	 * @Title: queryKpi_id
	 * @Description: 查询KPI_ID
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-5-9 下午4:44:36
	 */
	public String queryKpi_id() {
		kpiList = serviceKpiRelationService.queryKpiNameList(obj);
		return "list_kpi_id";
	}

	/**
	 * 
	 * @Title: importServiceKpi
	 * @Description: 进入选择导入文件界面
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-5-12 上午11:17:08
	 */
	public String importServiceKpi() {
		return "import_servicekpi";
	}

	/**
	 * 
	 * @Title: importFromXls
	 * @Description: 从excel模板导入服务与KPI的关系
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-5-12 上午11:28:19
	 */
	public String importFromXls() throws Exception {
		/** 用js获取不到绝对路径，xxx */
		InputStream is = new FileInputStream(file);
		this.result = serviceKpiRelationService.importFromXls(is);
		String callback = "<script>parent.importCallBack('" + result
				+ "')</script>";
		PrintWriterOut.printWirter(response, callback);
		return null;
	}

}
