package com.sitech.basd.tasklist.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sitech.basd.sxcloud.rsmu.domain.system.TbSysUserinfoObj;
import com.sitech.basd.sxcloud.rsmu.service.system.UserInfoService;
import com.sitech.basd.sxcloud.rsmu.web.BaseAction;
import com.sitech.basd.sxcloud.util.Struts2Utils;
import com.sitech.basd.tasklist.dao.TaskListDAO;
import com.sitech.basd.tasklist.domain.TaskListObj;
import com.sitech.basd.tasklist.domain.TaskRecordObj;
import com.sitech.basd.tasklist.service.TaskListService;
import com.sitech.utils.randomid.RandomUUID;
import com.sitech.utils.servlet.PrintWriterOut;

@Controller("taskListAction")
@Scope("prototype")
public class TaskListAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	@Autowired
	private TaskListService taskListService;
	@Autowired
	private TaskListDAO taskListDAO;
	@Autowired
	private UserInfoService userInfoService;
	private List<TaskListObj> tasklistObj;
	private TaskListObj obj = new TaskListObj();
	private String oper;
	private String id;
	private TaskListObj taskobj;
	//add by qism
	private TaskRecordObj taskRecordObj;
	private List<TaskRecordObj> taskRecordListObj;
	private String task_id;
	private List resultlist;
	private short tasktype;
	private String flag;
	private String result;

	private TbSysUserinfoObj userObj = new TbSysUserinfoObj();
	
	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
	
	public List getResultlist() {
		return resultlist;
	}

	public void setResultlist(List resultlist) {
		this.resultlist = resultlist;
	}

	public TaskRecordObj getTaskRecordObj() {
		return taskRecordObj;
	}

	public void setTaskRecordObj(TaskRecordObj taskRecordObj) {
		this.taskRecordObj = taskRecordObj;
	}
	public String getTask_id() {
		return task_id;
	}

	public void setTask_id(String task_id) {
		this.task_id = task_id;
	}
	public List<TaskRecordObj> getTaskRecordListObj() {
		return taskRecordListObj;
	}

	public void setTaskRecordListObj(List<TaskRecordObj> taskRecordListObj) {
		this.taskRecordListObj = taskRecordListObj;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public TbSysUserinfoObj getUserObj() {
		return userObj;
	}

	public void setUserObj(TbSysUserinfoObj userObj) {
		this.userObj = userObj;
	}

	public short getTasktype() {
		return tasktype;
	}

	public void setTasktype(short tasktype) {
		this.tasktype = tasktype;
	}

	public TaskListObj getTaskobj() {
		return taskobj;
	}

	public void setTaskobj(TaskListObj taskobj) {
		this.taskobj = taskobj;
	}

	public String getOper() {
		return oper;
	}

	public void setOper(String oper) {
		this.oper = oper;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public TaskListService getTasklistService() {
		return taskListService;
	}

	public void setTasklistService(TaskListService taskListService) {
		this.taskListService = taskListService;
	}

	public List<TaskListObj> getTasklistObj() {
		return tasklistObj;
	}

	public void setTasklistObj(List<TaskListObj> tasklistObj) {
		this.tasklistObj = tasklistObj;
	}

	public TaskListObj getObj() {
		return obj;
	}

	public void setObj(TaskListObj obj) {
		this.obj = obj;
	}

	public TaskListService getTaskListService() {
		return taskListService;
	}

	public void setTaskListService(TaskListService taskListService) {
		this.taskListService = taskListService;
	}

	public TaskListDAO getTaskListDAO() {
		return taskListDAO;
	}

	public void setTaskListDAO(TaskListDAO taskListDAO) {
		this.taskListDAO = taskListDAO;
	}

	public String queryUserInfoList() {

		String account = session.get("account").toString();
		if (!"admin".equals(account)) {
			userObj.setACCOUNT(account);
		}
		userObj.setPagination(this.getPaginater().initPagination(Struts2Utils.getRequest()));// 分页
		resultlist = userInfoService.queryLikeForListByObj(userObj);
		return "user";
	}
	
	public String queryTaskList() {
		obj.setPagination(this.getPaginater().initPagination(request));
		tasklistObj = taskListDAO.queryTaskList(obj);
		TaskListObj temp = new TaskListObj();
		temp.setId(id);
		List<TaskListObj> objs = taskListDAO.queryTaskList(temp);
		if(objs!=null && objs.size() >= 1 ){
			taskobj = objs.get(0);
		}	
		return "list";
	}
	
	public String saveTaskList() {
		TbSysUserinfoObj tObj = new TbSysUserinfoObj();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd ");
		String date = sdf.format(new Date());
		if (oper != null && "add".equals(oper)) {
			if(taskobj.getComplete_rate() == null){
				taskobj.setComplete_rate(0);
			}
			if(taskobj.getComplete_rate() == 100){
				taskobj.setTask_status(1);
			}else{
				taskobj.setTask_status(0);
			}
			// 获取当前登陆用户信息
			String submit_persion = session.get("account").toString();
			String uuid = RandomUUID.getUuid();
			taskobj.setId(uuid);
			taskobj.setSubmit_persion(submit_persion);
			taskobj.setComplete_date(date.toString());
			// taskobj.setPlan_complete_date(date.toString());
			taskobj.setSubmit_date(date.toString());
			taskListDAO.insertTaskList(taskobj);
		} else if (oper != null && "edit".equals(oper)) {
			if(taskobj.getComplete_rate() == 100){
				taskobj.setTask_status(1);
			}else{
				taskobj.setTask_status(0);
			}
			taskobj.setSubmit_date(date.toString());
			if (taskobj.getId() != null) {
				taskListDAO.updateTaskList(taskobj);
			}

			/*
			 * tObj.setID(Integer.valueOf(taskobj.getUser_id()));
			 * List<TbSysUserinfoObj> userList =
			 * userInfoService.queryLikeForListByObj(tObj); if (userList.size()
			 * == 1) { taskobj.setUserName(userList.get(0).getNAME()); }
			 * 
			 * flag = "update";
			 */
		}
		return null;
	}

	public String addTaskList() {
		//查询责任人
		if("".equals(taskobj) || taskobj == null ){
			taskobj = new TaskListObj();
		}
		taskobj.setResponPersonList(taskListDAO.queryResponPersonList());
		return "add";
	}
	//修改任务页面 add by qism
	public String updateTaskList(){
		if (id != null && !"".equals(id)) {
			TaskListObj temp = new TaskListObj();
			temp.setId(id);
			List<TaskListObj> objs = taskListDAO.queryTaskList(temp);
			taskobj = objs.get(0);
		}
		taskobj.setResponPersonList(taskListDAO.queryResponPersonList());
		return "update";
	}
	//删除任务 --只支持单个删除
	public String delTaskList() {
		TaskListObj obja = new TaskListObj();
		obja.setId(id);
		if (id != null) {
			taskListDAO.delTaskList(obja);
		}
		return null;
	}
	/**
	 * 
	 * @Title: queryTaskRecords
	 * @Description: 查询具体一个任务的记录
	 * @param
	 * @return String
	 * @throws
	 * @author qism
	 * @version 1.0
	 * @createtime 2014-6-16 下午3:24:02
	 */
	public String queryTaskRecords(){
		obj.setPagination(this.getPaginater().initPagination(request));
		if(StringUtils.isNotEmpty(task_id)){
			obj.setId(task_id);
		}else{
			obj.setId(id);
		}
		taskRecordListObj = taskListDAO.queryRecordList(obj);
		return "recordlist";
	}
	/**
	 * 
	 * @Title: addRecordList
	 * @Description: 添加记录
	 * @param
	 * @return String
	 * @throws
	 * @author qism
	 * @version 1.0
	 * @createtime 2014-6-17 上午11:45:15
	 */
	public String addRecordList(){
		return "addRecord";
	}
	/**
	 * 
	 * @Title: delTaskList
	 * @Description: 删除记录，支持单个或多个删除
	 * @param
	 * @return String
	 * @throws
	 * @author qism
	 * @version 1.0
	 * @createtime 2014-6-16 下午4:40:57
	 */
	public String delRecordList() {
		TaskRecordObj obja = new TaskRecordObj();
		if(StringUtils.isNotEmpty(id)){
			String[] arr = id.split(",");
			List<String> resultList = new ArrayList<String>();
			for (String str : arr) {
				resultList.add(str);
			}
			obja.setResultList(resultList);
			taskListDAO.delRecordList(obja);
		}
		return null;
	}
	/**
	 * 
	 * @Title: saveRecordList
	 * @Description: 保存记录
	 * @param
	 * @return void
	 * @throws
	 * @author qism
	 * @version 1.0
	 * @createtime 2014-6-16 下午6:01:24
	 */
	public void saveRecordList(){
		TbSysUserinfoObj tObj = new TbSysUserinfoObj();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date = sdf.format(new Date());
		if (oper != null && "add".equals(oper)) {
			// 获取当前登陆用户信息
			String submit_persion = session.get("account").toString();
			String uuid = RandomUUID.getUuid();
			taskRecordObj.setID(uuid);
			taskRecordObj.setSUBMIT_PERSION(submit_persion);//提交人
			taskRecordObj.setSUBMIT_DATE(date);
			taskRecordObj.setTASK_ID(task_id);
			if(Integer.parseInt(taskRecordObj.getCOMPLETE_RATE())<100){
				taskRecordObj.setTASK_STATUS(0);//未完成
			}else{
				taskRecordObj.setTASK_STATUS(1);//已完成
			}
			taskListDAO.insertTaskRecordList(taskRecordObj);
		} 
	}
	/**
	 * @Title: updateRecordList
	 * @Description: 转发到修改页面
	 * @param
	 * @return String
	 * @throws
	 * @author qism
	 * @version 1.0
	 * @createtime 2014-6-18 上午9:15:32
	 */
	public String updateRecordList(){
		if(StringUtils.isNotEmpty(id)){
			TaskRecordObj temp = new TaskRecordObj();
			temp.setID(id);
			List<TaskRecordObj> objs = taskListDAO.queryRecordById(temp);
			taskRecordObj = objs.get(0);		
		}
		return "updateRecord";
	}
	/**
	 * @Title: update
	 * @Description: 实际修改操作
	 * @param
	 * @return void
	 * @throws
	 * @author qism
	 * @version 1.0
	 * @createtime 2014-6-18 上午9:16:35
	 */
	public void updateToDo(){
		if(StringUtils.isNotEmpty(id)){
			taskRecordObj.setID(id);
			if(Integer.parseInt(taskRecordObj.getCOMPLETE_RATE()) != 100){
				taskRecordObj.setTASK_STATUS(0);
			}else{
				taskRecordObj.setTASK_STATUS(1);
			}
			taskListDAO.updateRecordById(taskRecordObj);
		}
	}
	/**
	 * @Title: checkIsAdmin
	 * @Description: 检测是否为admin用户
	 * @param
	 * @return void
	 * @throws
	 * @author qism
	 * @version 1.0
	 * @createtime 2014-6-18 下午3:59:47
	 */
	public void checkIsAdmin(){
		String personType = session.get("account").toString();
		 if("admin".equals(personType)){
			 result ="yes";
		 }else{
			 result ="no";
		 }
		 JSONObject jo = new JSONObject();
		 jo.put("result", result);
		 this.printToScreen(jo.toString());
	}
	/**
	 * @Title: printToScreen
	 * @Description: 将内容打回屏幕
	 * @param
	 * @return void
	 * @throws
	 * @author qism
	 * @version 1.0
	 * @createtime 2014-6-18 下午4:44:47
	 */
	private void printToScreen(String value) {
		try {
			response.setCharacterEncoding("UTF-8");
			// PrintWriter pw = response.getWriter();
			// pw.print(value);
			// pw.flush();
			// pw.close();
			PrintWriterOut.printWirter(response, value);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
