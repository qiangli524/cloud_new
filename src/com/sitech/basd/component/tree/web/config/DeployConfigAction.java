package com.sitech.basd.component.tree.web.config;

import java.io.PrintWriter;
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

import service.tree.HadoopTreeService;
import util.HadoopConstant;

import com.sitech.basd.component.domain.config.ConfigInfoObj;
import com.sitech.basd.component.domain.user.UserObj;
import com.sitech.basd.component.service.config.ConfigService;
import com.sitech.basd.component.service.user.UserService;
import com.sitech.basd.component.tree.domain.relation.ExampleRelationObj;
import com.sitech.basd.component.tree.service.relation.ExampleRelationService;
import com.sitech.basd.component.web.config.form.ConfigForm;
import com.sitech.basd.sxcloud.rsmu.web.BaseAction;
import com.sitech.basd.sxcloud.rsmu.web.util.exception.BaseException;
import com.sitech.basd.sxcloud.util.Struts2Utils;
import com.sitech.basd.yicloud.domain.busisystree.TbBusiSysTree;
import com.sitech.basd.yicloud.service.busisystree.TbBusiSysTreeService;
import com.sitech.utils.encrypt.DoubleEncryptUtils;
import com.sitech.utils.idformat.DeployIdFormat;
import com.sitech.utils.randomid.RandomUUID;
import com.sitech.utils.servlet.PrintWriterOut;

import domain.tree.HadoopTreeObj;

@Controller("deployConfigAction")
@Scope("prototype")
public class DeployConfigAction extends BaseAction {
	@Autowired
	private ConfigService configService;
	@Autowired
	private UserService userService;
	@Autowired
	private ExampleRelationService exampleRelationService;
	@Autowired
	private TbBusiSysTreeService tbBusiSysTreeService;
	@Autowired
	private HadoopTreeService hadoopTreeService;

	private ConfigForm theForm;

	private String example_id;// 节点ID

	private String hostIP;// 主机IP

	private String type;// 节点类型

	public String getExample_id() {
		return example_id;
	}

	public void setExample_id(String example_id) {
		this.example_id = example_id;
	}

	public String getHostIP() {
		return hostIP;
	}

	public void setHostIP(String hostIP) {
		this.hostIP = hostIP;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public ConfigForm getTheForm() {
		return theForm;
	}

	public void setTheForm(ConfigForm theForm) {
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
			theForm = new ConfigForm();
		}
		HttpServletRequest request = Struts2Utils.getRequest();
		String example_id = request.getParameter("id");
		String hostIP = request.getParameter("hostIP");
		String type = request.getParameter("type");
		ConfigInfoObj obj = new ConfigInfoObj();
		obj.setPagination(this.getPaginater().initPagination(Struts2Utils.getRequest()));
		if (theForm.getName() != null && !"".equals(theForm.getName())) {
			obj.setName(theForm.getName());
		}
		if (theForm.getType() != null && !"".equals(theForm.getType())) {
			obj.setType(theForm.getType());
		}
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
			list = configService.queryForAppConfig(obj);

		} else if ("3".equals(type)) {
			String example_change = DeployIdFormat.generateAppMapKey(Integer.parseInt(type),
					Integer.parseInt(example_id));
			obj.setExample_id(example_change);
			list = configService.queryForDeployList(obj);
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
		request.setAttribute("type", type);
		request.setAttribute("hostIP", hostIP);
		return "add";
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
			theForm = new ConfigForm();
		}

		HttpServletRequest request = Struts2Utils.getRequest();
		HttpServletResponse response = Struts2Utils.getResponse();
		String id = request.getParameter("id");// 判断是更新还是插入
		String str = request.getParameter("selectUsers");// 所选的用户
		String example_ids = request.getParameter("example_ids");// 部署实例或基准应用的序列id
		String type = request.getParameter("type");
		ConfigInfoObj obj = new ConfigInfoObj();
		// TbSysUserinfoObj user = (TbSysUserinfoObj)
		// request.getSession().getAttribute(Constant.USER_SESSION_KEY);
		String account = session.get("account").toString();
		JSONArray array = new JSONArray();
		try {
			BeanUtils.copyProperties(obj, theForm);
			obj.setMod_user(account);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		if (id != null && !"".equals(id)) {
			obj.setId(id);
			configService.updateConfig(obj);
		} else {
			String example_id = "";
			ExampleRelationObj relation = new ExampleRelationObj();
			// 对于部署实例及基准应用的序列id（example_id）进行转化，因为二者可能会出现冲突
			relation.setType("1");
			obj.setUpload_user(account);
			String[] users = str.split(",");
			String[] examples = example_ids.split(",");
			for (int i = 0; i < users.length; i++) {
				obj.setUser_id(users[i]);
				String config_uuid = RandomUUID.getUuid();
				obj.setId(config_uuid);
				example_id = DeployIdFormat.generateAppMapKey(3, Integer.parseInt(examples[i]));
				configService.insertConfig(obj);
				relation.setEntity_id(config_uuid);
				relation.setExample_id(example_id);
				exampleRelationService.inserByObj(relation);

				array.add(obj);
			}

		}
		PrintWriter out = null;
		try {
			// out = response.getWriter();
			// out.println(array);
			// out.close();
			PrintWriterOut.printWirter(response, array);
		} catch (Exception e2) {
			e2.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * @Title: mod
	 * @Description: 修改配置文件
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime May 23, 2013 8:54:47 AM
	 */
	public String mod() {
		if (theForm == null) {
			theForm = new ConfigForm();
		}
		HttpServletRequest request = Struts2Utils.getRequest();
		Map map = new HashMap();
		String id = request.getParameter("id");
		ConfigInfoObj obj = new ConfigInfoObj();
		obj.setId(id);
		List<ConfigInfoObj> list = configService.queryConfigInfoList(obj);
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
		return "mod";
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
		ConfigInfoObj obj = new ConfigInfoObj();
		obj.setId(id);
		configService.deleteConfig(obj);
		ExampleRelationObj e = new ExampleRelationObj();
		e.setEntity_id(id);
		e.setExample_id(example_id);
		int ret = exampleRelationService.deleteByObj(e);
		// PrintWriter out = null;
		try {
			// out = response.getWriter();
			// out.println(ret);
			// out.close();
			PrintWriterOut.printWirter(response, ret);
		} catch (Exception e2) {
			e2.printStackTrace();
		}
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
			theForm = new ConfigForm();
		}
		HttpServletRequest request = Struts2Utils.getRequest();
		String id = request.getParameter("id");
		ConfigInfoObj obj = new ConfigInfoObj();
		obj.setId(id);
		Map map = new HashMap();
		List<ConfigInfoObj> list = configService.queryConfigInfoList(obj);

		// 去掉密码中的换行符 update by lipengpeng
		if (list != null && list.size() > 0) {
			for (ConfigInfoObj scriptObj : list) {
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
		request.setAttribute("port", obj.getPort());
		theForm.setResultList(list);
		return "content";
	}

	/**
	 * 
	 * @Title: listHadoopConfig
	 * @Description: 查询Hadoop中配置文件列表
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-1-10 上午9:52:24
	 */
	public String listHadoopConfig() {
		if (theForm == null) {
			theForm = new ConfigForm();
		}
		ConfigInfoObj obj = new ConfigInfoObj();
		if (theForm.getName() != null && !"".equals(theForm.getName())) {
			obj.setName(theForm.getName());
		}
		if (theForm.getType() != null && !"".equals(theForm.getType())) {
			obj.setType(theForm.getType());
		}
		if (theForm.getDescription() != null && !"".equals(theForm.getDescription())) {
			obj.setDescription(theForm.getDescription());
		}
		HttpServletRequest request = Struts2Utils.getRequest();
		List list = new ArrayList();

		/*------------update by lipengpeng   查询节点下服务实例的配置文件----------*/
		// 查询当前节点所关联的配置文件
		if (HadoopConstant.hostNode.equals(type)) {// 如果是服务实例节点
			obj.setExample_id(example_id);
		} else {// 查询当前节点下的服务实例节点
			HadoopTreeObj hadoopTreeObj = new HadoopTreeObj();
			hadoopTreeObj.setId(example_id);
			List<HadoopTreeObj> treeList = hadoopTreeService.acquireChildNode(
					HadoopConstant.hostNode, null, hadoopTreeObj, new ArrayList<HadoopTreeObj>());
			List<String> exampleIdList = new ArrayList<String>();
			for (HadoopTreeObj treeObj : treeList) {
				exampleIdList.add(treeObj.getId());
			}
			obj.setExampleIdList(exampleIdList);
		}
		obj.setPagination(this.getPaginater().initPagination(request));
		list = configService.queryHadoopConfigInfoList(obj);
		theForm.setResultList(list);
		return "listHadoopConfig";
	}

	/**
	 * 
	 * @Title: addHadoopConfig
	 * @Description: 添加页面
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-1-10 下午7:33:38
	 */
	public String addHadoopConfig() {
		return "addHadoopConfig";
	}

	/**
	 * 
	 * @Title: modConfig
	 * @Description: 修改页面
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-1-10 下午7:33:59
	 */
	public String modConfig() {
		if (theForm == null) {
			theForm = new ConfigForm();
		}
		HttpServletRequest request = Struts2Utils.getRequest();
		Map map = new HashMap();
		String id = request.getParameter("id");
		ConfigInfoObj obj = new ConfigInfoObj();
		obj.setId(id);
		List<ConfigInfoObj> list = configService.queryConfigInfoList(obj);
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
		return "modConfig";
	}

	/**
	 * 
	 * @Title: saveHadoopConfig
	 * @Description: 保存Hadoop配置文件
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-1-10 上午10:09:00
	 */
	public String saveHadoopConfig() throws BaseException, UnsupportedEncodingException {
		if (theForm == null) {
			theForm = new ConfigForm();
		}
		HttpServletRequest request = Struts2Utils.getRequest();
		HttpServletResponse response = Struts2Utils.getResponse();
		String id = request.getParameter("id");// 判断是更新还是插入
		String selectUsers = request.getParameter("selectUsers");// 所选的用户
		String example_id = request.getParameter("example_id");
		ConfigInfoObj obj = new ConfigInfoObj();
		String account = session.get("account").toString();
		JSONArray array = new JSONArray();
		try {
			BeanUtils.copyProperties(obj, theForm);
			obj.setMod_user(account);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		if (id != null && !"".equals(id)) {
			obj.setId(id);
			configService.updateConfig(obj);
		} else {
			ExampleRelationObj relation = new ExampleRelationObj();
			relation.setType("1");
			obj.setUpload_user(account);
			String[] users = selectUsers.split(",");
			for (int i = 0; i < users.length; i++) {
				obj.setUser_id(users[i]);
				String config_uuid = RandomUUID.getUuid();
				obj.setId(config_uuid);
				configService.insertConfig(obj);
				relation.setEntity_id(config_uuid);
				relation.setExample_id(example_id);
				exampleRelationService.inserByObj(relation);
				array.add(obj);
			}
		}
		// PrintWriter out = null;
		try {
			// out = response.getWriter();
			// out.println(array);
			// out.close();
			PrintWriterOut.printWirter(response, array);
		} catch (Exception e2) {
			e2.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * @Title: deleteConfig
	 * @Description: 删除一条信息
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-1-10 下午8:37:10
	 */
	public String deleteConfig() {
		HttpServletRequest request = Struts2Utils.getRequest();
		String id = request.getParameter("id");
		String example_id = request.getParameter("example_id");
		ConfigInfoObj obj = new ConfigInfoObj();
		obj.setId(id);
		configService.deleteConfig(obj);
		ExampleRelationObj e = new ExampleRelationObj();
		e.setEntity_id(id);
		e.setExample_id(example_id);
		int ret = exampleRelationService.deleteByObj(e);
		return null;
	}

	/**
	 * @Title: checkTactics
	 * @Description: 检查策略
	 * @param
	 * @return void
	 * @throws
	 * @author lipp
	 * @version 1.0
	 * @createtime 2014-2-28 上午10:08:47
	 */
	public void checkTactics() {
		if (theForm == null) {
			theForm = new ConfigForm();
		}
		ConfigInfoObj configInfoObj = new ConfigInfoObj();
		configInfoObj.setTactics(theForm.getTactics());
		List<ConfigInfoObj> retList = configService.queryConfigInfoList(configInfoObj);
		String ret = "";
		if (retList.size() > 0) {
			ret = "-1";
		}
		try {
			// PrintWriter pw = response.getWriter();
			// pw.print(ret);
			// pw.flush();
			// pw.close();
			PrintWriterOut.printWirter(response, ret);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
