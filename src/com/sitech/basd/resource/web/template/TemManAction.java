package com.sitech.basd.resource.web.template;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sitech.basd.resource.domain.template.TemManObj;
import com.sitech.basd.resource.domain.united.UnitedTreeObj;
import com.sitech.basd.resource.service.template.TemManService;
import com.sitech.basd.resource.service.united.UnitedTaskThread;
import com.sitech.basd.resource.service.united.UnitedTreeService;
import com.sitech.basd.sxcloud.cloud.domain.vmhost.VMHostObj;
import com.sitech.basd.sxcloud.cloud.service.vmhost.VMHostService;
import com.sitech.basd.sxcloud.config.Constant;
import com.sitech.basd.sxcloud.rsmu.web.CRUDBaseAction;
import com.sitech.basd.sxcloud.util.Struts2Utils;
import com.sitech.basd.yicloud.domain.globaltask.GlobalTaskObj;
import com.sitech.utils.exception.HttpClientException;
import com.sitech.utils.randomid.RandomUUID;
import com.sitech.utils.servlet.PrintWriterOut;
import com.sitech.vo.united.VirtualMachineUnitedVO;
import com.sitech.vo.util.UnitedConstant;

@Controller("temManAction")
@Scope("prototype")
public class TemManAction extends CRUDBaseAction {
	@Autowired
	private TemManService temManService;
	@Autowired
	private VMHostService vmHostService;
	@Autowired
	private UnitedTreeService unitedTreeService;
	private List resultList;
	private TemManObj obj;
	private String result;
	private VirtualMachineUnitedVO virtualMachineUnitedVO;
	@Resource
	private UnitedTaskThread unitedTaskThread;

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public TemManObj getObj() {
		return obj;
	}

	public void setObj(TemManObj obj) {
		this.obj = obj;
	}

	public List getResultList() {
		return resultList;
	}

	public void setResultList(List resultList) {
		this.resultList = resultList;
	}

	public VirtualMachineUnitedVO getVirtualMachineUnitedVO() {
		return virtualMachineUnitedVO;
	}

	public void setVirtualMachineUnitedVO(VirtualMachineUnitedVO virtualMachineUnitedVO) {
		this.virtualMachineUnitedVO = virtualMachineUnitedVO;
	}

	/**
	 * 
	 * @Title: list
	 * @Description: 查询模板列表
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime May 10, 2013 10:39:18 AM
	 */
	public String list() {
		if (obj == null) {
			obj = new TemManObj();
		}
		if (obj.getId() != null && !"".equals(obj.getId())) {
			obj = new TemManObj();
		}
		
		String domain = session.get(Constant.USER_DOMAIN)==null ? "" : session.get(Constant.USER_DOMAIN).toString();
		obj.setDomain(domain);
		obj.setPagination(this.getPaginater().initPagination(Struts2Utils.getRequest()));
		resultList = temManService.queryForList(obj);
		return "list";
	}

	/**
	 * 
	 * @Title: add
	 * @Description: 创建模板
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime May 10, 2013 11:33:19 AM
	 */
	public String add() {
		// 获取主机列表
		UnitedTreeObj obj = new UnitedTreeObj();
		obj.setType(UnitedConstant.HOST);
		obj.setVtype(UnitedConstant.VMWARE);
		try {
			resultList = unitedTreeService.queryForTreeList(obj);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "vmtree";
	}

	/**
	 * 
	 * @Title: getVMList
	 * @Description:获取虚拟机列表
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @createtime May 14, 2013 2:40:35 PM
	 */
	public String getVMList() {
		if (obj == null) {
			obj = new TemManObj();
		}
		VMHostObj vmhost = new VMHostObj();
		vmhost.setVH_TYPE(obj.getType());
		vmhost.setVH_NAME(obj.getName());
		// vmhost.setPagination(this.getPaginater().initPagination(Struts2Utils.getRequest()));
		resultList = vmHostService.queryForListByObj(vmhost);
		return "vmList";
	}

	/**
	 * 
	 * @Title: createTem
	 * @Description: 创建模板
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime May 16, 2013 8:50:04 AM
	 */
	public String createTem() throws UnsupportedEncodingException {
		String result = "";
		final String taskUUID = RandomUUID.getUuid();
		String[] user = new String[] { session.get("id").toString(),
				session.get("account").toString(), session.get("name").toString() };
		final String createrName = user[1];
		GlobalTaskObj taskObj = new GlobalTaskObj();
		taskObj.setId(taskUUID);

		Thread taskThread = new Thread(new Runnable() {
			@Override
			public void run() {
				GlobalTaskObj taskObj = new GlobalTaskObj();
				taskObj.setName("创建模板");
				taskObj.setCreaterName(createrName);
				taskObj.setId(taskUUID);
				taskObj.setContent("正在创建模板" + obj.getName());
				taskObj.setType(enumtype.Types.GlobalTaskType.CLONEVM.toString());
				unitedTaskThread.updateTaskProgress(taskObj);
			}
		});
		taskThread.start();// 更新任务线程

		try {
			result = temManService.createTem(obj);
			taskThread.interrupt();
			taskObj.setContent("创建模板完成!");
			unitedTaskThread.endTask(taskObj);

		} catch (HttpClientException e) {
			taskThread.interrupt();
			taskObj.setContent("创建模板失败!原因：" + e.getMessage());
			unitedTaskThread.endTask(taskObj);
			result = "fail:" + e.getMessage();
			e.printStackTrace();
		}
		return "result";
	}

	/**
	 * 
	 * @Title: temRemark
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime May 13, 2013 11:39:36 AM
	 */
	public String temInfo() {
		return "tem_info";
	}

	/**
	 * 
	 * @Title: modify
	 * @Description: 修改
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime May 13, 2013 4:02:55 PM
	 */
	public String modify() {
		resultList = temManService.queryForList(obj);
		if (resultList != null && resultList.size() > 0) {
			obj = (TemManObj) resultList.get(0);
		}
		return "mod";
	}

	/**
	 * 
	 * @Title: saveTem
	 * @Description: 保存模板信息
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @throws UnsupportedEncodingException
	 * @createtime May 14, 2013 10:06:39 AM
	 */
	public String saveTem() {
		int ret = temManService.updateByObj(obj);
		result = ret + "";
		return "result";
	}

	/**
	 * 
	 * @Title: deleteTem
	 * @Description:删除一条记录
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @throws UnsupportedEncodingException
	 * @createtime May 14, 2013 10:53:11 AM
	 */
	public String deleteTem() {
		String result = "";
		try {
			result = temManService.delTem(obj);
		} catch (HttpClientException e) {
			result = "fail:" + e.getMessage();
			e.printStackTrace();
		}
		return "result";
	}

	/**
	 * 
	 * @Title: listTemInfo
	 * @Description: 获取模板cpu,内存等信息
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime May 17, 2013 4:29:57 PM
	 */
	public String listTemInfo() {
		DecimalFormat nf = new DecimalFormat("0");
		List<TemManObj> list = temManService.queryForList(obj);
		if (list != null && list.size() > 0) {
			obj = list.get(0);
			obj.setStore(Double.parseDouble(nf.format(obj.getStore() / 1024)));
		}
		return "detail";

	}

	/**
	 * 
	 * @Title: listTemInfo
	 * @Description: 获取模板cpu,内存等信息
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime May 17, 2013 4:29:57 PM
	 */
	public String listTemInfoForVM() {
		HttpServletRequest request = Struts2Utils.getRequest();
		HttpServletResponse response = Struts2Utils.getResponse();
		DecimalFormat nf = new DecimalFormat("0");
		String tem_id = request.getParameter("tem_id");
		String connect_id = request.getParameter("connect_id");
		TemManObj obj = new TemManObj();
		obj.setTemplateCode(tem_id);
		obj.setConnectId(connect_id);
		List<TemManObj> list = temManService.queryForList(obj);
		if (list != null && list.size() > 0) {
			obj = list.get(0);
			// obj.setStore(nf.format(Double.parseDouble(obj.getStore()) /
			// 1024));
		}
		JSONObject js = JSONObject.fromObject(obj);
		response.setContentType("type/html;charset=UTF-8");
		// out = response.getWriter();
		// out.println(js);
		// out.close();
		PrintWriterOut.printWirter(response, js);
		return null;

	}

	/**
	 * 
	 * @Title: listVMInfo
	 * @Description: 列示虚拟机的详细信息
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime May 20, 2013 2:21:55 PM
	 */
	public String listVMInfo() {
		VMHostObj v = new VMHostObj();
		v.setVH_UUID(obj.getTemplateCode());
		v.setConnectId(obj.getConnectId());
		v = vmHostService.queryByObj(v);
		String store = v.getVH_STORAGE();
		DecimalFormat nf = new DecimalFormat("0");
		store = nf.format(Double.parseDouble(store) / 1024);
		v.setVH_STORAGE(store);
		JSONObject js = JSONObject.fromObject(v);
		response.setContentType("type/html;charset=UTF-8");
		// out = response.getWriter();
		// out.println(js);
		// out.close();
		PrintWriterOut.printWirter(response, js);
		return null;
	}

	/**
	 * 
	 * @Title: getTemList
	 * @Description: 获取模板列表
	 * @param
	 * @return List
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime May 21, 2013 11:17:39 AM
	 */
	public List getTemList() {
		HttpServletRequest request = Struts2Utils.getRequest();
		String type = request.getParameter("type");
		TemManObj tem = new TemManObj();
		tem.setType(type);
		List list = temManService.queryForList(tem);
		return list;
	}

}
