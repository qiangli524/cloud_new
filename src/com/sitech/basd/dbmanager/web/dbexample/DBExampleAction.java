package com.sitech.basd.dbmanager.web.dbexample;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sitech.basd.component.domain.user.UserObj;
import com.sitech.basd.component.service.user.UserService;
import com.sitech.basd.dbmanager.CommonUtil;
import com.sitech.basd.dbmanager.domain.dbexample.DBExampleObj;
import com.sitech.basd.dbmanager.domain.dbexampleuser.DBExampleUserObj;
import com.sitech.basd.dbmanager.domain.dbexampleuser.DBPowerObj;
import com.sitech.basd.dbmanager.domain.dbexampleuser.PowerUserRelationObj;
import com.sitech.basd.dbmanager.domain.dbtablespace.DBTableSpaceObj;
import com.sitech.basd.dbmanager.service.dbexample.DBExampleService;
import com.sitech.basd.dbmanager.service.dbexampleuser.DBExampleUserService;
import com.sitech.basd.dbmanager.service.dbtablespace.DBTableSpaceService;
import com.sitech.basd.sxcloud.rsmu.web.BaseAction;
import com.sitech.basd.sxcloud.util.Struts2Utils;
import com.sitech.utils.properties.PropertiesUtil;
import com.sitech.utils.randomid.RandomUUID;
import com.sitech.utils.servlet.PrintWriterOut;

/**
 * 数据库管理
 * 
 * @author lipengpeng
 * 
 */
@SuppressWarnings("serial")
@Controller("dBExampleAction")
@Scope("prototype")
public class DBExampleAction extends BaseAction {

	private DBExampleObj dbExampleObj;

	private DBExampleUserObj dbExampleUserObj;

	private DBTableSpaceObj dbTableSpaceObj;

	private List<DBExampleObj> resultList;// 实例列表

	private List<DBExampleUserObj> dbExampleUserList;// 实例用户列表

	private List<DBTableSpaceObj> dbTableSpaceList;// 表空间 列表

	private List<DBPowerObj> powerList;

	@Autowired
	private DBExampleService dBExampleService;

	@Autowired
	private DBExampleUserService dBExampleUserService;

	@Autowired
	private DBTableSpaceService dBTableSpaceService;

	@Autowired
	private UserService userService;

	private String exampleUserId;// 实例用户id

	private String tableSpaceId;// 表空间id

	private String dbExampleId;// 实例ID

	private String isComplete;// 实例是否创建标识 1：已创建 0：未创建

	public DBExampleObj getDbExampleObj() {
		return dbExampleObj;
	}

	public void setDbExampleObj(DBExampleObj dbExampleObj) {
		this.dbExampleObj = dbExampleObj;
	}

	public DBExampleUserObj getDbExampleUserObj() {
		return dbExampleUserObj;
	}

	public void setDbExampleUserObj(DBExampleUserObj dbExampleUserObj) {
		this.dbExampleUserObj = dbExampleUserObj;
	}

	public DBTableSpaceObj getDbTableSpaceObj() {
		return dbTableSpaceObj;
	}

	public void setDbTableSpaceObj(DBTableSpaceObj dbTableSpaceObj) {
		this.dbTableSpaceObj = dbTableSpaceObj;
	}

	public List<DBExampleObj> getResultList() {
		return resultList;
	}

	public void setResultList(List<DBExampleObj> resultList) {
		this.resultList = resultList;
	}

	public List<DBPowerObj> getPowerList() {
		return powerList;
	}

	public void setPowerList(List<DBPowerObj> powerList) {
		this.powerList = powerList;
	}

	public List<DBExampleUserObj> getDbExampleUserList() {
		return dbExampleUserList;
	}

	public void setDbExampleUserList(List<DBExampleUserObj> dbExampleUserList) {
		this.dbExampleUserList = dbExampleUserList;
	}

	public List<DBTableSpaceObj> getDbTableSpaceList() {
		return dbTableSpaceList;
	}

	public void setDbTableSpaceList(List<DBTableSpaceObj> dbTableSpaceList) {
		this.dbTableSpaceList = dbTableSpaceList;
	}

	public String getExampleUserId() {
		return exampleUserId;
	}

	public void setExampleUserId(String exampleUserId) {
		this.exampleUserId = exampleUserId;
	}

	public String getTableSpaceId() {
		return tableSpaceId;
	}

	public void setTableSpaceId(String tableSpaceId) {
		this.tableSpaceId = tableSpaceId;
	}

	/**
	 * 展示所有的数据库实例
	 * 
	 * @author lipengpeng
	 * @return
	 */
	public String listDBExample() {
		try {
			if (dbExampleObj == null) {
				dbExampleObj = new DBExampleObj();
			}
			dbExampleObj.setPagination(this.getPaginater().initPagination(request));// 分页
			resultList = dBExampleService.queryExampleList(dbExampleObj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "listdbexample";
	}

	/**
	 * 添加数据库实例
	 * 
	 * @author lipengpeng
	 * @return
	 */
	public String addDBExample() {
		if (dbExampleObj == null) {
			dbExampleObj = new DBExampleObj();
		}
		String oper = request.getParameter("oper");
		if ("edit".equals(oper)) {
			String eid = request.getParameter("eid");
			dbExampleObj.setId(eid);
			List<DBExampleObj> list = dBExampleService.queryExampleList(dbExampleObj);
			if (list != null && list.size() > 0) {
				dbExampleObj = list.get(0);
			}
			request.setAttribute("eid", eid);
		}
		request.setAttribute("oper", oper);
		return "addbexample";
	}

	/**
	 * 删除数据库实例
	 * 
	 * @author lipengpeng
	 * @return
	 */
	public String delDBExample() {
		try {
			DBExampleObj obj = new DBExampleObj();
			String eid = request.getParameter("eid");
			/**
			 * 请求接口
			 */
			DBExampleObj obj1 = new DBExampleObj();
			obj1.setId(eid);
			obj1 = dBExampleService.queryExampleAndUserManagerBy(obj1).get(0);
			int isComplete = obj1.getIscomplete();
			if (1 == isComplete) {
				String ip = obj1.getExample_ip();
				String username = obj1.getUsername();
				String password = obj1.getPassword();
				String exampleName = obj1.getExample_name();
				String path = PropertiesUtil.getString("properties/script_path", "drop_db");
				String shellCmd = "nohup " + path + " " + exampleName + " &";
				String result = CommonUtil.executeCommands(ip, 22, username, password, shellCmd);
			}

			obj.setId(eid);
			// 删除实例
			int flag = dBExampleService.deleteExample(obj);
			// 删除实例下的用户
			DBExampleUserObj objUser = new DBExampleUserObj();
			objUser.setExample_id(eid);
			dBExampleUserService.deleteExampleUser(objUser);
			// 删除实例下的表空间
			DBTableSpaceObj objTable = new DBTableSpaceObj();
			objTable.setExample_id(eid);
			dBTableSpaceService.deleteTableSpace(objTable);

			JSONObject json = new JSONObject();
			json.put("result", flag);
			// PrintWriter out = response.getWriter();
			// out.println(json);
			// out.close();
			PrintWriterOut.printWirter(response, json);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 保存数据库实例
	 * 
	 * @author lipengpeng
	 * @return
	 */
	public String saveExample() {
		if (dbExampleObj == null) {
			dbExampleObj = new DBExampleObj();
		}
		JSONObject json = new JSONObject();
		String oper = request.getParameter("oper");
		int ent = -1;
		if ("add".equals(oper)) {
			dbExampleObj.setId(RandomUUID.getUuid());
			ent = dBExampleService.insertExample(dbExampleObj);
		} else {
			ent = dBExampleService.updateExample(dbExampleObj);
		}
		try {
			// PrintWriter out = response.getWriter();
			if (ent >= 0) {
				json.put("obj", dbExampleObj);
			}
			// out.println(json);
			// out.close();
			PrintWriterOut.printWirter(response, json);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 执行或者取消执行数据库实例
	 * 
	 * @author lipengpeng
	 */
	public void deployExample() {
		String eid = request.getParameter("eid");
		String iscompletestr = request.getParameter("iscomplete");
		int iscomplete = 0;
		if (iscompletestr != null && !"".equals(iscompletestr) && !"null".equals(iscompletestr)) {
			iscomplete = Integer.parseInt(iscompletestr);
		}

		DBExampleObj obj = new DBExampleObj();
		obj.setId(eid);
		obj.setIscomplete(iscomplete);
		int ret = dBExampleService.updateExample(obj);

		if (1 == iscomplete) {
			DBExampleObj obj1 = new DBExampleObj();
			obj1.setId(eid);
			obj1 = dBExampleService.queryExampleAndUserManagerBy(obj1).get(0);
			String ip = obj1.getExample_ip();
			String username = obj1.getUsername();
			String password = obj1.getPassword();
			String exampleName = obj1.getExample_name();
			String dataFilePath = obj1.getData_file_path();
			String sysPass = obj1.getSys_pass();
			String path = PropertiesUtil.getString("properties/script_path", "creat_db");
			String shellCmd = "nohup " + path + " " + exampleName + " " + dataFilePath + " "
					+ sysPass + " &";
			String result = CommonUtil.executeCommands(ip, 22, username, password, shellCmd);
		}
		JSONObject jo = new JSONObject();
		jo.put("result", ret);
		try {
			// PrintWriter pw = response.getWriter();
			// pw.print(jo);
			// pw.flush();
			// pw.close();
			PrintWriterOut.printWirter(response, jo);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getExampleUserList() {
		if (dbExampleUserObj == null) {
			dbExampleUserObj = new DBExampleUserObj();
		}
		DBExampleUserObj obj = new DBExampleUserObj();
		HttpServletRequest request = Struts2Utils.getRequest();
		String temp = request.getParameter("id");
		if (temp != null && !"".equals(temp)) {
			dbExampleId = temp;
		}
		if (dbExampleId != null && !"".equals(dbExampleId)) {
			obj.setExample_id(dbExampleId);
			dbExampleUserObj.setExample_id(dbExampleId);
		}
		obj.setPagination(this.getPaginater().initPagination(Struts2Utils.getRequest()));
		if (!"".equals(dbExampleUserObj.getExample_username())
				&& dbExampleUserObj.getExample_username() != null) {
			obj.setExample_username(dbExampleUserObj.getExample_username());
		}
		if (dbExampleUserObj.getIs_lock() != 0) {
			obj.setIs_lock(dbExampleUserObj.getIs_lock());
		}
		dbExampleUserList = dBExampleUserService.queryExampleUserList(obj);
		return "listexampleuser";
	}

	/**
	 * 在创建前判断用户是否已创建
	 * 
	 * @return
	 */
	public String isSubmit() {
		HttpServletRequest request = Struts2Utils.getRequest();
		HttpServletResponse response = Struts2Utils.getResponse();
		String example_id = request.getParameter("example_id");
		DBExampleObj eObj = new DBExampleObj();
		if (example_id != null && !"".equals(example_id)) {
			eObj.setId(example_id);
			dbExampleObj = dBExampleService.queryExampleAndUserManagerBy(eObj).get(0);
		}
		JSONObject jo = new JSONObject();
		jo.put("result", dbExampleObj.getIscomplete());
		// out = response.getWriter();
		// out.println(jo);
		// out.close();
		PrintWriterOut.printWirter(response, jo);
		return null;
	}

	/**
	 * 在锁定前判断用户是否已创建
	 * 
	 * @return
	 */
	public String getARecord() {
		HttpServletRequest request = Struts2Utils.getRequest();
		HttpServletResponse response = Struts2Utils.getResponse();
		String id = request.getParameter("id");
		DBExampleUserObj obj = new DBExampleUserObj();
		if (!"".equals(id) && id != null) {
			obj.setId(id);
		}
		DBExampleUserObj obj1 = new DBExampleUserObj();
		obj1 = dBExampleUserService.queryExampleUserList(obj).get(0);
		JSONObject jo = new JSONObject();
		jo.put("result", obj1.getIs_submit());
		// out = response.getWriter();
		// out.println(jo);
		// out.close();
		PrintWriterOut.printWirter(response, jo);
		return null;
	}

	/**
	 * 
	 * @Title: addVlan
	 * @Description: 插入前获取example的id
	 * @param
	 * @return String
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @createtime Jul23, 2013
	 */
	public String addExampleUser() {
		if (dbExampleUserObj == null) {
			dbExampleUserObj = new DBExampleUserObj();
		}
		HttpServletRequest request = Struts2Utils.getRequest();
		String id = request.getParameter("id");
		if (!"".equals(id) && id != null) {
			dbExampleUserObj.setExample_id(id);
		}
		return "addExampleUser";
	}

	/**
	 * 修改实例用户前
	 * 
	 * @return
	 */
	public String modExampleUser() {
		if (dbExampleUserObj == null) {
			dbExampleUserObj = new DBExampleUserObj();
		}
		HttpServletRequest request = Struts2Utils.getRequest();
		String id = request.getParameter("id");
		DBExampleUserObj obj = new DBExampleUserObj();
		obj.setId(id);
		if (!"".equals(id) && id != null) {
			dbExampleUserObj = dBExampleUserService.queryExampleUserList(obj).get(0);
		} else {
			dbExampleUserObj = null;
		}
		return "modExampleUser";
	}

	/**
	 * 增加或修改实例用户
	 * 
	 * @return
	 */
	public String saveExampleUser() {
		if (dbExampleUserObj == null) {
			dbExampleUserObj = new DBExampleUserObj();
		}
		DBExampleUserObj obj = new DBExampleUserObj();
		HttpServletRequest request = Struts2Utils.getRequest();
		String id = dbExampleUserObj.getId();
		try {
			BeanUtils.copyProperties(obj, dbExampleUserObj);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		if ((id != null && !"".equals(id)) || request.getParameter("id") != null) {
			// update
			// 以下参数用以接受页面上锁定与解锁、执行与取消执行的值

			obj.setExample_password(dbExampleUserObj.getExample_password());
			obj.setIs_lock(dbExampleUserObj.getIs_lock());
			String id1 = request.getParameter("id");
			String is_lock = request.getParameter("is_lock");
			String is_submit = request.getParameter("is_submit");
			if (id1 != null) {
				obj.setId(id1);
			}
			if (is_lock != null && !"".equals(is_lock)) {
				int isLock = Integer.parseInt(is_lock);
				obj.setIs_lock(Integer.parseInt(is_lock));
				if (2 == isLock) {
					DBExampleUserObj obj11 = new DBExampleUserObj();
					obj11.setId(id1);
					obj11 = dBExampleUserService.queryExampleUserList(obj11).get(0);
					int isSubmit = obj11.getIs_submit();
					if (1 == isSubmit) {
						DBExampleObj obj1 = new DBExampleObj();
						obj1.setId(obj11.getExample_id());
						obj1 = dBExampleService.queryExampleAndUserManagerBy(obj1).get(0);
						String ip = obj1.getExample_ip();
						String username = obj1.getUsername();
						String password = obj1.getPassword();
						String exampleName = obj1.getExample_name();
						String exampleUsername = obj11.getExample_username();
						String path = PropertiesUtil.getString("properties/script_path",
								"lock_user");
						String shellCmd = "nohup " + path + " " + exampleName + " "
								+ exampleUsername + " &";
						String result = CommonUtil.executeCommands(ip, 22, username, password,
								shellCmd);
					}
				} else if (1 == isLock) {
					DBExampleUserObj obj11 = new DBExampleUserObj();
					obj11.setId(id1);
					obj11 = dBExampleUserService.queryExampleUserList(obj11).get(0);
					int isSubmit = obj11.getIs_submit();
					if (1 == isSubmit) {
						DBExampleObj obj1 = new DBExampleObj();
						obj1.setId(obj11.getExample_id());
						obj1 = dBExampleService.queryExampleAndUserManagerBy(obj1).get(0);
						String ip = obj1.getExample_ip();
						String username = obj1.getUsername();
						String password = obj1.getPassword();
						String exampleName = obj1.getExample_name();
						String exampleUsername = obj11.getExample_username();
						String path = PropertiesUtil.getString("properties/script_path",
								"unlock_user");
						String shellCmd = "nohup " + path + " " + exampleName + " "
								+ exampleUsername + " &";
						String result = CommonUtil.executeCommands(ip, 22, username, password,
								shellCmd);
					}
				}
			}
			if (is_submit != null && !"".equals(is_submit)) {
				int isSubmit = Integer.parseInt(is_submit);
				obj.setIs_submit(isSubmit);
				/**
				 * 执行
				 */
				if (1 == isSubmit) {
					DBExampleUserObj obj11 = new DBExampleUserObj();
					obj11.setId(id1);
					obj11 = dBExampleUserService.queryExampleUserList(obj11).get(0);
					DBExampleObj obj1 = new DBExampleObj();
					obj1.setId(obj11.getExample_id());
					obj1 = dBExampleService.queryExampleAndUserManagerBy(obj1).get(0);
					String ip = obj1.getExample_ip();
					String username = obj1.getUsername();
					String password = obj1.getPassword();
					String exampleName = obj1.getExample_name();
					String exampleUserPass = obj11.getExample_password();
					String exampleUsername = obj11.getExample_username();
					String path = PropertiesUtil.getString("properties/script_path", "creat_user");
					String shellCmd = "nohup  " + path + " " + exampleName + " " + exampleUsername
							+ " " + exampleUserPass + " &";
					String result = CommonUtil
							.executeCommands(ip, 22, username, password, shellCmd);
				}
			}

			/**
			 * 修改密码
			 */
			if (obj.getExample_password() != null) {
				String oemPassword = obj.getExample_password().trim();
				DBExampleUserObj obj11 = new DBExampleUserObj();
				obj11.setId(obj.getId());
				obj11 = dBExampleUserService.queryExampleUserList(obj11).get(0);
				String newPassword = obj11.getExample_password();
				if (!oemPassword.equals(newPassword)) {
					int isSubmit = obj11.getIs_submit();
					if (1 == isSubmit) {
						DBExampleObj obj1 = new DBExampleObj();
						obj1.setId(obj11.getExample_id());
						obj1 = dBExampleService.queryExampleAndUserManagerBy(obj1).get(0);
						String ip = obj1.getExample_ip();
						String username = obj1.getUsername();
						String password = obj1.getPassword();
						String exampleName = obj1.getExample_name();
						String exampleUsername = obj11.getExample_username();
						String path = PropertiesUtil.getString("properties/script_path",
								"alter_user");
						String shellCmd = "nohup " + path + " " + exampleName + " "
								+ exampleUsername + " " + newPassword + " &";
						String result = CommonUtil.executeCommands(ip, 22, username, password,
								shellCmd);
					}
				}
			}

			dBExampleUserService.updateExampleUser(obj);
		} else {
			// insert
			String uuid = RandomUUID.getUuid();
			obj.setId(uuid);
			obj.setIs_lock(1);
			obj.setIs_submit(2);
			dBExampleUserService.insertExampleUser(obj);
		}
		return "saveExampleUser";
	}

	/**
	 * 删除实例用户
	 * 
	 * @return
	 */
	public String delExampleUser() {
		HttpServletRequest request = Struts2Utils.getRequest();
		String id = request.getParameter("id");

		DBExampleUserObj obj11 = new DBExampleUserObj();
		obj11.setId(id);
		obj11 = dBExampleUserService.queryExampleUserList(obj11).get(0);
		/**
		 * 执行
		 */
		if (1 == obj11.getIs_submit()) {
			DBExampleObj obj1 = new DBExampleObj();
			obj1.setId(obj11.getExample_id());
			obj1 = dBExampleService.queryExampleAndUserManagerBy(obj1).get(0);
			String ip = obj1.getExample_ip();
			String username = obj1.getUsername();
			String password = obj1.getPassword();
			String exampleName = obj1.getExample_name();
			String exampleUsername = obj11.getExample_username();
			String path = PropertiesUtil.getString("properties/script_path", "drop_user");
			String shellCmd = "nohup " + path + " " + exampleName + " " + exampleUsername + " &";
			String result = CommonUtil.executeCommands(ip, 22, username, password, shellCmd);
		}

		if (!"".equals(id) && id != null) {
			DBExampleUserObj obj = new DBExampleUserObj();
			obj.setId(id);
			dBExampleUserService.deleteExampleUser(obj);
		}
		return "delExampleUser";
	}

	public String editExampleUser() {
		try {
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "editexampleuser";
	}

	/**
	 * 
	 * @Title: 查询获取表空间列表
	 * @Copyright:Copyright (c) Jul 25, 2013
	 * @Company: si-tech
	 * @author: caowx
	 * @version: 1.0
	 */
	public String getTableSpaceList() {
		if (dbTableSpaceObj == null) {
			dbTableSpaceObj = new DBTableSpaceObj();
		}
		try {
			dbTableSpaceObj.setPagination(this.getPaginater().initPagination(request));// 分页
			dbTableSpaceObj.setExample_id(dbExampleId);
			dbTableSpaceList = dBTableSpaceService.queryTableSpaceList(dbTableSpaceObj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "listtablespace";
	}

	/**
	 * 
	 * @Title: 新增修改表空间信息
	 * @Copyright:Copyright (c) Jul 25, 2013
	 * @Company: si-tech
	 * @author: caowx
	 * @version: 1.0
	 */
	public String editTableSpace() {
		if (dbTableSpaceObj == null) {
			dbTableSpaceObj = new DBTableSpaceObj();
		}
		String oper = request.getParameter("oper");
		String eid = request.getParameter("example_id");

		if ("edit".equals(oper)) {
			String tablespaceid = request.getParameter("tableSpaceId");
			dbTableSpaceObj.setId(tablespaceid);
			List<DBTableSpaceObj> list = dBTableSpaceService.queryTableSpaceList(dbTableSpaceObj);
			if (list != null && list.size() > 0) {
				dbTableSpaceObj = list.get(0);
			}
			request.setAttribute("tablespaceid", tablespaceid);
		}
		request.setAttribute("example_id", eid);
		request.setAttribute("oper", oper);

		return "edittablespace";
	}

	/**
	 * 
	 * @Title: 保存表空间
	 * @Copyright:Copyright (c) Jul 25, 2013
	 * @Company: si-tech
	 * @author: caowx
	 * @version: 1.0
	 */
	public String saveTableSpace() {
		if (dbTableSpaceObj == null) {
			dbTableSpaceObj = new DBTableSpaceObj();
		}
		JSONObject json = new JSONObject();
		String oper = request.getParameter("oper");
		String exampleid = request.getParameter("dbExampleId");
		int ent = -1;
		if ("add".equals(oper)) {
			dbTableSpaceObj.setId(RandomUUID.getUuid());
			dbTableSpaceObj.setExample_id(exampleid);
			ent = dBTableSpaceService.insertTableSpace(dbTableSpaceObj);
		} else {
			// 获取表空间的ID
			String tablespaceid = request.getParameter("tablespaceid");

			// 查询获取该条表空间的信息
			DBTableSpaceObj obj11 = new DBTableSpaceObj();
			obj11.setId(tablespaceid);
			obj11 = dBTableSpaceService.queryTableSpaceList(obj11).get(0);

			// 获取该表空间是否已创建
			int isSubmit = obj11.getIs_execute();
			// 获取该表空间是否可扩展
			int isExpand = obj11.getIs_expand();
			// 原表空间大小
			String spaceSizeOld = obj11.getSpace_size();
			// 新表空间大小
			String spaceSize = dbTableSpaceObj.getSpace_size();
			// 若不一致，则进行空间大小调整
			if (1 == isSubmit && isExpand == 1 && !spaceSizeOld.equals(spaceSize)) {
				// 若是已创建且是可扩展的，则执行shell脚本进行表空间大小扩展
				DBExampleObj obj1 = new DBExampleObj();
				obj1.setId(obj11.getExample_id());
				// 查询表空间所属实例的账号、密码、IP等信息
				obj1 = dBExampleService.queryExampleAndUserManagerBy(obj1).get(0);
				String ip = obj1.getExample_ip();
				String username = obj1.getUsername();
				String password = obj1.getPassword();
				String exampleName = obj1.getExample_name();
				// 获取数据库表空间名、大小、文件路径信息
				String spaceName = obj11.getSpace_name();
				String filePathName = obj11.getData_file_path();

				String path = PropertiesUtil.getString("properties/script_path", "alter_ts");
				String shellCmd = "sh " + path + " " + exampleName + " " + spaceName + " "
						+ filePathName + " " + spaceSize;
				String result = CommonUtil.executeCommands(ip, 22, username, password, shellCmd);
			}
			// 更新表数据

			dbTableSpaceObj.setId(tablespaceid);
			ent = dBTableSpaceService.updateTableSpace(dbTableSpaceObj);
		}
		try {
			// PrintWriter out = response.getWriter();
			if (ent >= 0) {
				json.put("obj", dbExampleObj);
				json.put("result", ent);
			}
			// out.println(json);
			// out.close();
			PrintWriterOut.printWirter(response, json);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * @Title: 删除表空间
	 * @Copyright:Copyright (c) Jul 25, 2013
	 * @Company: si-tech
	 * @author: caowx
	 * @version: 1.0
	 */
	public String delTableSpace() {
		int flag = 0;
		try {
			String tablespaceid = request.getParameter("tablespaceid");

			DBTableSpaceObj obj11 = new DBTableSpaceObj();
			obj11.setId(tablespaceid);
			obj11 = dBTableSpaceService.queryTableSpaceList(obj11).get(0);
			int isSubmit = obj11.getIs_execute();
			if (1 == isSubmit) {
				DBExampleObj obj1 = new DBExampleObj();
				obj1.setId(obj11.getExample_id());
				obj1 = dBExampleService.queryExampleAndUserManagerBy(obj1).get(0);
				String ip = obj1.getExample_ip();
				String username = obj1.getUsername();
				String password = obj1.getPassword();
				String exampleName = obj1.getExample_name();
				String spaceName = obj11.getSpace_name();
				String path = PropertiesUtil.getString("properties/script_path", "drop_ts");
				String shellCmd = "nohup " + path + " " + exampleName + " " + spaceName + " &";
				String result = CommonUtil.executeCommands(ip, 22, username, password, shellCmd);
			}

			DBTableSpaceObj obj = new DBTableSpaceObj();
			obj.setId(tablespaceid);
			flag = dBTableSpaceService.deleteTableSpace(obj);
			JSONObject json = new JSONObject();
			json.put("result", flag);
			// PrintWriter out = response.getWriter();
			// out.println(json);
			// out.close();
			PrintWriterOut.printWirter(response, json);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * @Title: 执行创建数据库表空间脚本
	 * @Copyright:Copyright (c) Jul 25, 2013
	 * @Company: si-tech
	 * @author: caowx
	 * @version: 1.0
	 */
	public String excuteCreateSpace() {
		// 获取入参：实例ID和表空间ID
		// String example_id = request.getParameter("example_id");
		String tablespaceid = request.getParameter("tablespaceid");
		try {
			DBTableSpaceObj obj11 = new DBTableSpaceObj();
			obj11.setId(tablespaceid);
			obj11 = dBTableSpaceService.queryTableSpaceList(obj11).get(0);
			int isSubmit = obj11.getIs_execute();
			if (2 == isSubmit) {
				DBExampleObj obj1 = new DBExampleObj();
				obj1.setId(obj11.getExample_id());
				obj1 = dBExampleService.queryExampleAndUserManagerBy(obj1).get(0);
				String ip = obj1.getExample_ip();
				String username = obj1.getUsername();
				String password = obj1.getPassword();
				String exampleName = obj1.getExample_name();
				String spaceName = obj11.getSpace_name();
				int is_expand = obj11.getIs_expand();
				String flag = "ON";
				if (2 == is_expand) {
					flag = "OFF";
				}
				String size = obj11.getSpace_size();
				String dataFilePath = obj11.getData_file_path();
				String path = PropertiesUtil.getString("properties/script_path", "creat_ts");
				String shellCmd = "nohup " + path + " " + exampleName + " " + spaceName + " "
						+ dataFilePath + " " + size + " " + flag + " &";
				String result = CommonUtil.executeCommands(ip, 22, username, password, shellCmd);
			}
			// 进行表空间是否执行创建的标识
			DBTableSpaceObj obj = new DBTableSpaceObj();
			obj.setId(tablespaceid);
			obj.setIs_execute(1);
			// 更新成已创建
			int flag = dBTableSpaceService.updateTableSpaceExecute(obj);
			JSONObject json = new JSONObject();
			json.put("result", flag);
			// PrintWriter out = response.getWriter();
			// out.println(json);
			// out.close();
			PrintWriterOut.printWirter(response, json);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * @Title: 更新表空间是否创建标识
	 * @Copyright:Copyright (c) Jul 25, 2013
	 * @Company: si-tech
	 * @author: caowx
	 * @version: 1.0
	 */
	public int updateTableSpaceExecute() {
		// 更新成功FLAG
		int flag = 0;
		try {
			int is_execute = Integer.parseInt(request.getParameter("is_execute"));
			// 赋值
			DBTableSpaceObj obj = new DBTableSpaceObj();
			obj.setId(tableSpaceId);
			obj.setIs_execute(is_execute);
			// 执行更新
			flag = dBTableSpaceService.updateTableSpaceExecute(obj);
			// 返回信息
			JSONObject json = new JSONObject();
			json.put("result", flag);
			// PrintWriter out = response.getWriter();
			// out.println(json);
			// out.close();
			PrintWriterOut.printWirter(response, json);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	public String getDbExampleId() {
		return dbExampleId;
	}

	public void setDbExampleId(String dbExampleId) {
		this.dbExampleId = dbExampleId;
	}

	/**
	 * 添加用户权限
	 * 
	 * @author lipengpeng
	 * @return
	 */
	public String addExampleUserPower() {
		try {
			String id = request.getParameter("id");
			PowerUserRelationObj obj = new PowerUserRelationObj();
			obj.setEXAMPLE_USER_ID(exampleUserId);
			obj.setPOWER_ID(id);
			int ret = dBExampleUserService.insertPowerUserRealation(obj);
			if (ret != -1) {
				DBExampleUserObj userObj = new DBExampleUserObj();
				userObj.setId(exampleUserId);
				List<DBExampleUserObj> list = dBExampleUserService.queryExampleUserList(userObj);
				if (list.size() > 0) {
					userObj = list.get(0);
					int isSubmit = userObj.getIs_submit();
					int isLock = userObj.getIs_lock();
					if (1 == isSubmit && 1 == isLock) {
						DBExampleObj eobj = new DBExampleObj();
						eobj.setId(userObj.getExample_id());
						List<DBExampleObj> elist = dBExampleService
								.queryExampleAndUserManagerBy(eobj);
						if (elist.size() > 0) {
							eobj = elist.get(0);
							String ip = eobj.getExample_ip();
							String username = eobj.getUsername();
							String password = eobj.getPassword();
							String exampleName = eobj.getExample_name();
							String exampleUserName = userObj.getExample_username();
							DBPowerObj pobj = new DBPowerObj();
							List<String> pidlist = new ArrayList<String>();
							pidlist.add(id);
							pobj.setPidlist(pidlist);
							List<DBPowerObj> plist = dBExampleUserService.queryAllPowerSelf(pobj);
							if (plist != null && plist.size() > 0) {
								pobj = plist.get(0);
								if (pobj != null) {
									String powerName = pobj.getPOWER_NAME();
									this.executeUserPower(ip, username, password, exampleName,
											exampleUserName, powerName);
								} /*
								 * else { ret = -1; }
								 */
							} /*
							 * else { ret = -1; }
							 */
						} /*
						 * else { ret = -1; }
						 */
					} /*
					 * else { ret = -1; }
					 */
				} /*
				 * else { ret = -1; }
				 */
			}
			JSONObject jo = new JSONObject();
			jo.put("result", ret);
			// PrintWriter pw = response.getWriter();
			// pw.print(jo);
			// pw.flush();
			// pw.close();
			PrintWriterOut.printWirter(response, jo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * @author lipengpeng
	 * @return
	 */
	// public String listExampleUserPower() {
	// try {
	// powerList = dBExampleUserService.queryAllPower();
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// return "listpower";
	// }
	/**
	 * 添加或查看用户权限
	 * 
	 * @author lipengpeng
	 * @return
	 */
	public String listExampleUserPower() {
		// exampleUserId
		String oper = request.getParameter("caozuo");
		PowerUserRelationObj purObj = new PowerUserRelationObj();
		purObj.setEXAMPLE_USER_ID(exampleUserId);
		List<PowerUserRelationObj> list = dBExampleUserService.queryAllRelation(purObj);
		List<String> pidlist = new ArrayList<String>();
		for (PowerUserRelationObj powerUserRelationObj : list) {
			pidlist.add(powerUserRelationObj.getPOWER_ID());
		}
		DBPowerObj dbPowerObj = new DBPowerObj();
		List<DBPowerObj> plist = new ArrayList<DBPowerObj>();
		if (pidlist.size() > 0) {
			dbPowerObj.setPidlist(pidlist);
			plist = dBExampleUserService.queryAllPowerSelf(dbPowerObj);
		}
		if ("view".equals(oper)) {
			powerList = plist;
		} else {
			List<DBPowerObj> alllist = dBExampleUserService.queryAllPower();
			alllist.removeAll(plist);
			powerList = alllist;
		}
		request.setAttribute("caozuo", oper);
		return "listpower";
	}

	/**
	 * 删除用户权限
	 * 
	 * @author lipengpeng
	 */
	public void delUserPower() {
		PowerUserRelationObj purObj = new PowerUserRelationObj();
		purObj.setEXAMPLE_USER_ID(exampleUserId);
		String powerid = request.getParameter("powerid");
		purObj.setPOWER_ID(powerid);
		int ret = dBExampleUserService.delUserPower(purObj);
		try {
			JSONObject jo = new JSONObject();
			jo.put("result", ret);
			// PrintWriter pw = response.getWriter();
			// pw.print(jo);
			// pw.flush();
			// pw.close();
			PrintWriterOut.printWirter(response, jo);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 判断路径是否合法
	 * 
	 * @author lipengpeng
	 */
	public void checkPath() {
		String ip = request.getParameter("ip");
		String data_file_path = request.getParameter("data_file_path");
		String userid = request.getParameter("userid");
		UserObj obj = new UserObj();
		obj.setId(userid);
		obj = userService.queryUserObjById(obj);
		String username = obj.getUsername();
		String password = obj.getPassword();
		boolean flag = this.pathIsExist(ip, username, password, data_file_path);
		try {
			JSONObject jo = new JSONObject();
			jo.put("result", flag);
			// PrintWriter pw = response.getWriter();
			// pw.print(jo);
			// pw.flush();
			// pw.close();
			PrintWriterOut.printWirter(response, jo);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getIsComplete() {
		return isComplete;
	}

	public void setIsComplete(String isComplete) {
		this.isComplete = isComplete;
	}

	/**
	 * 执行创建用户权限脚本
	 * 
	 * @param ip
	 * @param username
	 * @param password
	 * @param exampleName
	 * @param exampleUser
	 * @param powerName
	 * @return
	 */
	private String executeUserPower(String ip, String username, String password,
			String exampleName, String exampleUser, String powerName) {
		String path = PropertiesUtil.getString("properties/script_path", "grant_pm");
		String shellCmd = "nohup " + path + " " + exampleName + " " + exampleUser + " " + powerName
				+ " &";
		String result = CommonUtil.executeCommands(ip, 22, username, password, shellCmd);
		return result;
	}

	/**
	 * 判断指定主机上的路径是否存在
	 * 
	 * @author lipengpeng
	 * @param ip
	 * @param username
	 * @param password
	 * @param path
	 * @return
	 */
	private boolean pathIsExist(String ip, String username, String password, String path) {
		// String result = CommonUtil.executeCommands(ip, 22, username,
		// password, path);
		// if (result != null) {
		// return !(result.contains("No") && result.contains("such") &&
		// result.contains("file"));
		// }
		return true;
	}

	public String e3base() {
		return "e3base";
	}
}
