package com.sitech.basd.component.tree.web.script;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sitech.basd.component.domain.script.ScriptObj;
import com.sitech.basd.component.domain.user.UserObj;
import com.sitech.basd.component.service.script.ScriptsService;
import com.sitech.basd.component.service.user.UserService;
import com.sitech.basd.component.tree.domain.relation.ExampleRelationObj;
import com.sitech.basd.component.tree.service.relation.ExampleRelationService;
import com.sitech.basd.component.web.script.form.ScriptForm;
import com.sitech.basd.sxcloud.rsmu.web.BaseAction;
import com.sitech.basd.sxcloud.rsmu.web.util.exception.BaseException;
import com.sitech.basd.sxcloud.util.Struts2Utils;
import com.sitech.basd.yicloud.domain.busisystree.TbBusiSysTree;
import com.sitech.basd.yicloud.service.busisystree.TbBusiSysTreeService;
import com.sitech.utils.encrypt.DoubleEncryptUtils;
import com.sitech.utils.exception.RabbitMQException;
import com.sitech.utils.idformat.DeployIdFormat;
import com.sitech.utils.rabbitmq.RabbitMQUtil;
import com.sitech.utils.randomid.RandomUUID;
import com.sitech.utils.servlet.PrintWriterOut;

@SuppressWarnings("all")
@Controller("deployScriptAction")
@Scope("prototype")
public class DeployScriptAction extends BaseAction {
	@Autowired
	private ScriptsService scriptsService;
	@Autowired
	private UserService userService;
	@Autowired
	private ExampleRelationService exampleRelationService;
	@Autowired
	private TbBusiSysTreeService tbBusiSysTreeService;
	@Autowired
	private RabbitMQUtil rabbitmqUtil;
	private ScriptForm theForm;

	public ScriptForm getTheForm() {
		return theForm;
	}

	public void setTheForm(ScriptForm theForm) {
		this.theForm = theForm;
	}

	/**
	 * 
	 * @Title: ConfigInfoList
	 * @Description: 查询配置文件列表
	 * @param
	 * @return List
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime May 22, 2013 10:04:03 AM
	 */
	public String list() {
		if (theForm == null) {
			theForm = new ScriptForm();
		}
		HttpServletRequest request = Struts2Utils.getRequest();
		String example_id = request.getParameter("id");
		String hostIP = request.getParameter("hostIP");
		String type = request.getParameter("type");
		ScriptObj obj = new ScriptObj();
		obj.setPagination(this.getPaginater().initPagination(Struts2Utils.getRequest()));
		// 对于部署实例及基准应用的序列id（example_id）进行转化，因为二者可能会出现冲突

		List list = new ArrayList();
		if ("2".equals(type)) {// 基准应用
			TbBusiSysTree tree = new TbBusiSysTree();
			tree.setBusiId(example_id);
			List<TbBusiSysTree> treeList = tbBusiSysTreeService.queryForTree(tree);
			if (treeList != null && treeList.size() > 0) {
				tree = treeList.get(0);
			}
			obj.setExample_id(tree.getId());
			list = scriptsService.queryForAppScript(obj);

		} else if ("3".equals(type)) {
			String example_change = DeployIdFormat.generateAppMapKey(Integer.parseInt(type),
					Integer.parseInt(example_id));
			obj.setExample_id(example_change);
			list = scriptsService.queryForDeployList(obj);
		}

		theForm.setResultList(list);
		request.setAttribute("example_id", example_id);
		request.setAttribute("hostIP", hostIP);
		request.setAttribute("type", type);
		return "list";
	}

	/**
	 * 
	 * @Title: add
	 * @Description: 添加配置文件
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime May 22, 2013 3:20:57 PM
	 */
	public String add() {
		HttpServletRequest request = Struts2Utils.getRequest();
		String example_id = request.getParameter("example_id");
		String type = request.getParameter("type");
		String hostIP = request.getParameter("hostIP");
		request.setAttribute("example_id", example_id);
		request.setAttribute("node_type", type);
		request.setAttribute("hostIP", hostIP);
		return "edit";
	}

	/**
	 * 
	 * @Title: save
	 * @Description: 保存配置文件
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime May 30, 2013 11:02:18 AM
	 */
	public String save() throws BaseException, UnsupportedEncodingException {
		if (theForm == null) {
			theForm = new ScriptForm();
		}
		String id = request.getParameter("id");// 判断是更新还是插入
		String str = request.getParameter("selectUsers");// 所选的用户
		// String example_id = request.getParameter("example_id");//
		// 部署实例或基准应用的序列id
		String type = request.getParameter("type");
		String example_ids = request.getParameter("example_ids");
		ScriptObj obj = new ScriptObj();
		// TbSysUserinfoObj user = (TbSysUserinfoObj)
		// request.getSession().getAttribute(
		// Constant.USER_SESSION_KEY);
		String account = session.get("account").toString();
		JSONArray array = new JSONArray();
		try {
			BeanUtils.copyProperties(obj, theForm);
			obj.setUpdate_person(account);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		if (id != null && !"".equals(id) && !"null".equals(id)) {
			obj.setId(id);
			scriptsService.updateByObj(obj);
		} else {
			String example_id = "";
			ExampleRelationObj relation = new ExampleRelationObj();
			relation.setType("4");
			obj.setUpload_person(account);
			String[] users = str.split(",");
			String[] examples = example_ids.split(",");
			for (int i = 0; i < users.length; i++) {
				String config_uuid = RandomUUID.getUuid();
				// 关系表中插入数据
				example_id = DeployIdFormat.generateAppMapKey(3, Integer.parseInt(examples[i]));
				relation.setExample_id(example_id);
				relation.setEntity_id(config_uuid);
				exampleRelationService.inserByObj(relation);
				obj.setUser_id(users[i]);
				obj.setId(config_uuid);
				obj.setInterval(countTime(theForm.getInterval(), theForm.getUnit()));
				scriptsService.insertByObj(obj);

				array.add(obj);
			}

		}
		// out = response.getWriter();
		// out.println(array);
		// out.close();
		PrintWriterOut.printWirter(response, array);
		return null;
	}

	/**
	 * 
	 * @Title: mod
	 * @Description: 修改脚本
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime May 23, 2013 8:54:47 AM
	 */
	public String mod() {
		if (theForm == null) {
			theForm = new ScriptForm();
		}
		HttpServletRequest request = Struts2Utils.getRequest();
		Map map = new HashMap();
		String id = request.getParameter("id");
		String example_id = request.getParameter("example_id");
		String type = request.getParameter("type");
		request.setAttribute("example_id", example_id);
		request.setAttribute("node_type", type);
		ScriptObj obj = new ScriptObj();
		obj.setId(id);
		List<ScriptObj> list = scriptsService.queryForList(obj);
		if (list != null && list.size() > 0) {
			obj = list.get(0);
		}
		try {
			BeanUtils.copyProperties(theForm, obj);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		UserObj u = new UserObj();
		u.setId(obj.getUser_id());
		List<UserObj> userList = userService.list(u);
		if (userList != null && userList.size() > 0) {
			u = userList.get(0);
		}
		theForm.setIp(u.getIp());
		theForm.setUsername(u.getUsername());
		theForm.setPassword(u.getPassword());
		request.setAttribute("id", id);
		// request.setAttribute("ip", u.getIp());
		// request.setAttribute("username", u.getUsername());
		// request.setAttribute("password", u.getPassword());
		// request.setAttribute("path", obj.getPath());
		return "edit";
	}

	/**
	 * 
	 * @Title: delete
	 * @Description: 删除一条记录
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime May 23, 2013 4:45:23 PM
	 */
	public String delete() {
		HttpServletRequest request = Struts2Utils.getRequest();
		String id = request.getParameter("id");
		String example_id = request.getParameter("example_id");
		String type = request.getParameter("type");
		example_id = DeployIdFormat.generateAppMapKey(Integer.parseInt(type),
				Integer.parseInt(example_id));
		ScriptObj obj = new ScriptObj();
		obj.setId(id);
		scriptsService.deleteByObj(obj);
		ExampleRelationObj e = new ExampleRelationObj();
		e.setEntity_id(id);
		e.setExample_id(example_id);
		int ret = exampleRelationService.deleteByObj(e);
		// out = response.getWriter();
		// out.println(ret);
		// out.close();
		PrintWriterOut.printWirter(response, ret);
		return null;
	}

	/**
	 * 
	 * @Title: listConfigDetail
	 * @Description: 获取文件内容
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime May 24, 2013 1:17:14 PM
	 */
	public String listConfigContent() {
		if (theForm == null) {
			theForm = new ScriptForm();
		}
		HttpServletRequest request = Struts2Utils.getRequest();
		String id = request.getParameter("id");
		ScriptObj obj = new ScriptObj();
		obj.setId(id);
		List<ScriptObj> list = scriptsService.queryForList(obj);
		if (list != null && list.size() > 0) {
			obj = list.get(0);
		}
		request.setAttribute("ip", obj.getIp());
		request.setAttribute("name", obj.getUsername());
		request.setAttribute("pwd", obj.getPassword());
		theForm.setResultList(list);
		return "content";
	}

	/**
	 * 
	 * @Title: filterGrade
	 * @Description: 过滤同一类型脚本级别
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Jun 22, 2013 10:27:03 AM
	 */
	public String filterGrade() {
		HttpServletRequest request = Struts2Utils.getRequest();
		HttpServletResponse response = Struts2Utils.getResponse();
		String node_type = request.getParameter("node_type");
		String type = request.getParameter("type");
		String example_id = request.getParameter("example_id");
		String grade = request.getParameter("grade");
		example_id = DeployIdFormat.generateAppMapKey(Integer.parseInt(node_type),
				Integer.parseInt(example_id));
		Map map = new HashMap();
		map.put("example_id", example_id);
		map.put("type", type);
		map.put("grade", grade);
		List<ScriptObj> list = exampleRelationService.filterGrade(map);
		JSONArray array = new JSONArray();
		if (list != null && list.size() > 0) {
			array.add(list);
		}
		// out = response.getWriter();
		// out.println(array);
		// out.close();
		PrintWriterOut.printWirter(response, array);
		return null;

	}

	/**
	 * 
	 * @Title: goStart
	 * @Description: 进入执行脚本页面
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Jun 24, 2013 8:25:42 PM
	 */
	public String goStart() {
		HttpServletRequest request = Struts2Utils.getRequest();
		String id = request.getParameter("id");
		String example_id = request.getParameter("example_id");
		String type = request.getParameter("type");
		request.setAttribute("script_id", id);
		request.setAttribute("example_id", example_id);
		request.setAttribute("type", type);
		return "execute";
	}

	/**
	 * @Title: executeScript
	 * @Description: 执行脚本，没有依赖关系，只执行当前选中的脚本，选谁执行谁，后台貌似不支持按级别执行
	 * @param
	 * @return void
	 * @throws
	 * @author lipp
	 * @version 1.0
	 * @createtime 2013-12-31 下午5:02:21
	 */
	public void executeScript() {
		// 查询要执行的脚本
		String scriptid = request.getParameter("id");
		ScriptObj obj = new ScriptObj();
		obj.setId(scriptid);
		obj = scriptsService.queryByObj(obj);

		// 设置重要信息
		obj.setCount(obj.getCount() + 1);
		String example_id = request.getParameter("example_id");
		String nodeType = request.getParameter("type");
		String deploy_example = request.getParameter("deploy_example");
		String example_exchange = "";
		if ("2".equals(nodeType)) {
			example_exchange = DeployIdFormat.generateAppMapKey(DeployIdFormat.DEPLOY_EXAMPLE,
					Integer.parseInt(deploy_example));
		} else {
			example_exchange = DeployIdFormat.generateAppMapKey(DeployIdFormat.DEPLOY_EXAMPLE,
					Integer.parseInt(example_id));
		}
		obj.setExample_id(DeployIdFormat.analysisAppMapKey(DeployIdFormat.DEPLOY_EXAMPLE,
				example_exchange) + "");

		String account = session.get("account").toString();
		obj.setUpdate_person(account);
		List<ScriptObj> list = new ArrayList<ScriptObj>();
		list.add(obj);
		scriptsService.updateByObj(obj);

		// 向后台推送消息
		try {
			rabbitmqUtil.publishMessage("", "app.deploy.script.queue", list);
		} catch (RabbitMQException e) {
			e.printStackTrace();
		}

		JSONArray array = JSONArray.fromObject(list);
		// out = response.getWriter();
		// out.println(array);
		// out.close();
		PrintWriterOut.printWirter(response, array);
	}

	/**
	 * 
	 * @Title: start
	 * @Description: 通过SSH工具执行脚本
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @throws RabbitMQException
	 * @createtime Jun 24, 2013 5:55:00 PM
	 */
	public String start() throws RabbitMQException {
		// TbSysUserinfoObj user = (TbSysUserinfoObj)
		// request.getSession().getAttribute(
		// Constant.USER_SESSION_KEY);
		String account = session.get("account").toString();
		// String id = request.getParameter("script_id");
		String node_type = request.getParameter("type");
		// node_type=2时，为基准应用ID，node_type=3时，为部署实例ID，
		String example_id = request.getParameter("example_id");
		String type = request.getParameter("script_type");
		String grade = request.getParameter("script_grade");
		String deploy_example = request.getParameter("deploy_example");
		ScriptObj obj = new ScriptObj();
		String example_exchange = "";
		if ("2".equals(node_type)) {
			example_exchange = DeployIdFormat.generateAppMapKey(DeployIdFormat.DEPLOY_EXAMPLE,
					Integer.parseInt(deploy_example));
		} else {
			example_exchange = DeployIdFormat.generateAppMapKey(DeployIdFormat.DEPLOY_EXAMPLE,
					Integer.parseInt(example_id));
		}
		obj.setExample_id(example_exchange);
		obj.setType(type);
		obj.setGrade(Integer.parseInt(grade));
		List<ScriptObj> list = scriptsService.queryLessGradeScript(obj);
		if (list != null && list.size() > 0) {
			for (ScriptObj s : list) {
				s.setExample_id(DeployIdFormat.analysisAppMapKey(DeployIdFormat.DEPLOY_EXAMPLE,
						example_exchange) + "");
				s.setCount(s.getCount() + 1);
				s.setUpdate_person(account);
				scriptsService.updateByObj(s);
			}
			rabbitmqUtil.publishMessage("", "app.deploy.script.queue", list);
		}
		JSONArray array = JSONArray.fromObject(list);
		// out = response.getWriter();
		// out.println(array);
		// out.close();
		PrintWriterOut.printWirter(response, array);
		return null;

	}

	/**
	 * 
	 * @Title: countTime
	 * @Description: 将时间统一为秒
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Jun 22, 2013 5:57:07 PM
	 */
	private String countTime(String interval, String unit) {
		long intervals = Integer.parseInt(interval) * Integer.parseInt(unit);
		if ("1".equals(unit)) {
			intervals = intervals;
		} else if ("2".equals(unit)) {
			intervals = intervals * 60;
		} else {
			intervals = intervals * 60 * 60;
		}
		return String.valueOf(intervals);
	}

	/**
	 * 
	 * @Title: listConfigContent
	 * @Description: 查看脚本内容
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Jul 3, 2013 9:08:20 PM
	 */
	public String listContent() {
		if (theForm == null) {
			theForm = new ScriptForm();
		}
		HttpServletRequest request = Struts2Utils.getRequest();
		String id = request.getParameter("id");
		ScriptObj obj = new ScriptObj();
		obj.setId(id);
		List<ScriptObj> list = scriptsService.queryForDeployList(obj);
		// 去掉密码中的换行符 update by lipengpeng
		if (list != null && list.size() > 0) {
			for (ScriptObj scriptObj : list) {
				String password = DoubleEncryptUtils.trimEnter(scriptObj.getPassword());
				scriptObj.setPassword(password);
			}
		}
		if (list != null && list.size() > 0) {
			obj = list.get(0);
		}
		request.setAttribute("ip", obj.getIp());
		request.setAttribute("name", obj.getUsername());
		request.setAttribute("pwd", obj.getPassword());
		theForm.setResultList(list);
		return "content";
	}

}
