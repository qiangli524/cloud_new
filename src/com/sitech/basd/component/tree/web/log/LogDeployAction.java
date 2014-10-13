package com.sitech.basd.component.tree.web.log;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONArray;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sitech.basd.component.domain.log.LogObj;
import com.sitech.basd.component.service.log.LogService;
import com.sitech.basd.component.tree.domain.relation.ExampleRelationObj;
import com.sitech.basd.component.tree.service.relation.ExampleRelationService;
import com.sitech.basd.component.web.log.form.LogForm;
import com.sitech.basd.sxcloud.rsmu.web.CRUDBaseAction;
import com.sitech.basd.sxcloud.util.Struts2Utils;
import com.sitech.utils.idformat.DeployIdFormat;
import com.sitech.utils.randomid.RandomUUID;
import com.sitech.utils.servlet.PrintWriterOut;

@Controller("logDeployAction")
@Scope("prototype")
public class LogDeployAction extends CRUDBaseAction {
	@Resource
	private LogService logService;
	@Resource
	private ExampleRelationService exampleRelationService;
	private LogForm theForm;

	public LogForm getTheForm() {
		return theForm;
	}

	public void setTheForm(LogForm theForm) {
		this.theForm = theForm;
	}

	/**
	 * 
	 * @Title: listLogDeploy
	 * @Description:应用部署查询日志信息
	 * @author duangh
	 * @date May 28, 2013 4:57:38 PM
	 * @return
	 */
	public String listLogDeploy() {
		String appId = request.getParameter("entityId");// 应用部署中的树id,后续CRUD都需要这个id
		String hostIP = request.getParameter("hostIP");// 所在主机IP
		String type = request.getParameter("type");// 2为基准应用，3为实例
		LogObj obj = new LogObj();
		List<LogObj> resultList = null;
		if (type != null && type.equals("3")) {
			obj.setAppId(DeployIdFormat.generateAppMapKey(Integer.parseInt(type),
					Integer.parseInt(appId)));// 存入关系表中的ID);
			resultList = logService.queryLogDeploy(obj);
		} else if (type != null && type.equals("2")) {
			obj.setAppId(request.getParameter("id"));
			obj.setPagination(this.getPaginater().initPagination(request));
			resultList = logService.queryLogAppDeploy(obj);
		}
		request.setAttribute("resultList", resultList);
		request.setAttribute("id", appId);
		request.setAttribute("hostIP", hostIP);
		request.setAttribute("type", type);
		return LIST;
	}

	/**
	 * 
	 * @Title: addLog
	 * @Description:进入增加配置日志信息界面
	 * @author duangh
	 * @date May 22, 2013 10:59:49 AM
	 * @return
	 */
	public String addLog() {
		String hostIP = request.getParameter("hostIP");
		String treeId = request.getParameter("treeId");// 应用部署树中的实体ID,即基准应用或实例的ID
		String type = request.getParameter("type");// 2为基准应用，3为实例
		request.setAttribute("treeId", treeId);
		request.setAttribute("hostIP", hostIP);
		request.setAttribute("type", type);
		return ADD;
	}

	/**
	 * 
	 * @Title: updateLog
	 * @Description:进入修改配置日志信息界面
	 * @author duangh
	 * @date May 22, 2013 11:00:37 AM
	 * @return
	 */
	public String updateLog() {
		String id = request.getParameter("id");
		LogObj obj = new LogObj();
		obj.setId(id);
		obj = logService.queryForObj(obj);
		if (theForm == null) {
			theForm = new LogForm();
		}
		if (obj != null) {
			theForm.setHostIP(obj.getHostIP());
			theForm.setHostUser(obj.getHostUser());
			theForm.setType(obj.getType());
			theForm.setId(obj.getId());
			theForm.setName(obj.getName());
			theForm.setPath(obj.getPath());
			theForm.setDescription(obj.getDescription());
			theForm.setFlag(1);
			theForm.setExtension(obj.getExtension());
		}
		return MODIFY;
	}

	/**
	 * 
	 * @Title: saveLogInfo
	 * @Description:保存日志信息
	 * @author duangh
	 * @date May 22, 2013 4:34:30 PM
	 * @return
	 * @throws IOException
	 */
	public String saveLogInfo() throws IOException {

		int flag = theForm.getFlag();
		if (flag == 0) {
			String hostids = request.getParameter("hostids");
			String example_ids = request.getParameter("example_ids");
			String type = request.getParameter("type");

			if (hostids != null && !"".equals(hostids)) {
				String[] ids = hostids.split(",");
				String[] exampleId = example_ids.split(",");
				JSONArray array = new JSONArray();
				for (int i = 0; i < ids.length; i++) {
					LogObj obj = new LogObj();
					String uuid = RandomUUID.getUuid();
					obj.setId(uuid);
					obj.setUserId(ids[i]);
					// obj.setName(theForm.getName());
					obj.setType(theForm.getType());
					obj.setExtension(theForm.getExtension().trim());
					obj.setCategory("1");// 应用部署类型
					String path = theForm.getPath().trim();
					if (path.indexOf("/") != -1) {
						obj.setName(path.substring(path.lastIndexOf("/") + 1, path.length()));
					} else {
						obj.setName(path);
					}
					obj.setPath(path);
					obj.setDescription(theForm.getDescription());
					int ret = logService.insertByObj(obj);
					if (ret != -1) {// 向关系表中插入数据
						ExampleRelationObj erObj = new ExampleRelationObj();
						String trueId = null;
						if (type != null) {
							trueId = DeployIdFormat.generateAppMapKey(3,
									Integer.parseInt(exampleId[i]));// 存入关系表中的ID
						}
						erObj.setExample_id(trueId);
						erObj.setEntity_id(uuid);
						erObj.setType("2");
						exampleRelationService.inserByObj(erObj);
						array.add(obj);
					}
				}
				response.setCharacterEncoding("utf-8");
				response.setContentType("text/html");
				// PrintWriter p = response.getWriter();
				// p.print(array);
				// p.flush();
				// p.close();
				PrintWriterOut.printWirter(response, array);
			}
		} else if (flag == 1) {
			JSONArray array = new JSONArray();
			LogObj obj = new LogObj();
			obj.setId(theForm.getId());
			obj.setType(theForm.getType());
			obj.setExtension(theForm.getExtension().trim());
			obj.setCategory("1");// 应用部署类型
			String path = theForm.getPath().trim();
			if (path.indexOf("/") != -1) {
				obj.setName(path.substring(path.lastIndexOf("/") + 1, path.length()));
			} else {
				obj.setName(path);
			}
			obj.setPath(path);
			obj.setDescription(theForm.getDescription());
			array.add(obj);
			int ret = logService.updateByObj(obj);
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/html");
			// PrintWriter p = response.getWriter();
			if (ret != -1) {
				// p.print(array);
				PrintWriterOut.printWirter(response, array);
				// p.flush();
				// p.close();
			} else {
				// p.print(ret);
				// p.flush();
				// p.close();
				PrintWriterOut.printWirter(response, ret);
			}
		}

		return null;
	}

	/**
	 * 
	 * @Title: deleteLogDeploy
	 * @Description:应用部署删除配置日志信息
	 * @author duangh
	 * @date May 22, 2013 11:01:31 AM
	 * @return
	 * @throws IOException
	 */
	public String deleteLogDeploy() throws IOException {

		// 先删除配置的日志信息
		LogObj obj = new LogObj();
		obj.setId(theForm.getId());
		int ret = logService.deleteByObj(obj);
		if (ret != -1) {
			// 再删除关系
			ExampleRelationObj erObj = new ExampleRelationObj();
			erObj.setType("2");
			erObj.setEntity_id(theForm.getId());
			erObj.setExample_id(DeployIdFormat.generateAppMapKey(3,
					Integer.parseInt(theForm.getTreeId())));
			exampleRelationService.deleteByObj(erObj);
			PrintWriter p = Struts2Utils.getResponse().getWriter();
			p.print(ret);
			p.flush();
			p.close();
		}
		return null;
	}
}
