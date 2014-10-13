package action.users;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import service.users.HadoopAuthorityService;
import service.users.HadoopGroupMemberService;
import service.users.HadoopUserDealTaskService;
import service.users.HadoopUserGroupService;
import service.users.HadoopUserService;
import service.users.HadoopUserServiceRelationService;
import ch.ethz.ssh2.Connection;

import com.sitech.basd.sxcloud.rsmu.web.BaseAction;
import com.sitech.utils.randomid.RandomUUID;
import com.sitech.utils.servlet.PrintWriterOut;
import com.sitech.utils.ssh.ganymed.GanymedSshUtil;

import domain.cluster.HadoopClusterObj;
import domain.users.HadoopAuthorityObj;
import domain.users.HadoopGroupMember;
import domain.users.HadoopUserDealTaskObj;
import domain.users.HadoopUserGroup;
import domain.users.HadoopUserObj;
import domain.users.HadoopUserServiceRelationObj;

/**
 * <p>
 * Title: HadoopApplyUserAction
 * </p>
 * <p>
 * Description: hadoop应用用户管理类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2014
 * </p>
 * <p>
 * Company: SI-TECH
 * </p>
 * 
 * @author lipp
 * @version 1.0
 * @createtime 2014-2-19 下午2:18:26
 * 
 */
@Controller("hadoopApplyUserAction")
@Scope("prototype")
@SuppressWarnings("serial")
public class HadoopApplyUserAction extends BaseAction {

	private HadoopUserObj hadoopUserObj;

	private List<HadoopUserObj> resultList;

	private List<HadoopClusterObj> clusterList;

	private List<HadoopAuthorityObj> auList;// 权限结果集

	private String oper;// 操作

	private String authPath;// 权限路径

	private String autho;// 权限

	private String auto1;// 属主权限

	private String auto2;// 属组全选

	private String auto3;// 他人权限

	private HadoopAuthorityObj hadoopAuthorityObj;

	@Autowired
	private HadoopUserService hadoopUserService;

	@Autowired
	private HadoopUserServiceRelationService hadoopUserServiceRelationService;

	@Autowired
	private HadoopUserDealTaskService hadoopUserDealTaskService;

	@Autowired
	private HadoopGroupMemberService hadoopGroupMemberService;

	@Autowired
	private HadoopUserGroupService hadoopUserGroupService;

	@Autowired
	private HadoopAuthorityService hadoopAuthorityService;

	/**
	 * @Title: list
	 * @Description: 展示hadoop应用用户列表
	 * @param
	 * @return String
	 * @throws
	 * @author lipp
	 * @version 1.0
	 * @createtime 2014-2-19 下午2:21:56
	 */
	public String list() {
		if (hadoopUserObj == null) {
			hadoopUserObj = new HadoopUserObj();
		}
		if (hadoopUserObj.getService_type() != null
				&& -1 == hadoopUserObj.getService_type()) {
			hadoopUserObj.setService_type(null);
		}
		if ("-1".equals(hadoopUserObj.getCluster_id())) {
			hadoopUserObj.setCluster_id(null);
		}
		if (hadoopUserObj.getState() != null && -1 == hadoopUserObj.getState()) {
			hadoopUserObj.setStatus(null);
		} else {
			if (hadoopUserObj.getState() != null
					&& hadoopUserObj.getState() == 0) {
				hadoopUserObj.setDeal_type(0);
				hadoopUserObj.setStatus(0);
			} else if (hadoopUserObj.getState() != null
					&& hadoopUserObj.getState() == 1) {
				hadoopUserObj.setDeal_type(0);
				hadoopUserObj.setStatus(1);
			} else if (hadoopUserObj.getState() != null
					&& hadoopUserObj.getState() == 2) {
				hadoopUserObj.setDeal_type(0);
				hadoopUserObj.setStatus(2);
			} else if (hadoopUserObj.getState() != null
					&& hadoopUserObj.getState() == 3) {
				hadoopUserObj.setDeal_type(0);
				hadoopUserObj.setStatus(3);
			} else if (hadoopUserObj.getState() != null
					&& hadoopUserObj.getState() == 4) {
				hadoopUserObj.setDeal_type(1);
				hadoopUserObj.setStatus(0);
			} else if (hadoopUserObj.getState() != null
					&& hadoopUserObj.getState() == 5) {
				hadoopUserObj.setDeal_type(1);
				hadoopUserObj.setStatus(1);
			} else if (hadoopUserObj.getState() != null
					&& hadoopUserObj.getState() == 6) {
				hadoopUserObj.setDeal_type(1);
				hadoopUserObj.setStatus(3);
			}
		}
		hadoopUserObj.setEntity_type(0);
		hadoopUserObj.setPagination(getPaginater().initPagination(request));
		resultList = hadoopUserService.queryForListByObj(hadoopUserObj);
		HadoopUserObj userObj = new HadoopUserObj();
		userObj.setEntity_type(0);
		clusterList = hadoopUserService.queryForClusterList(userObj);
		return "list";
	}

	/**
	 * @Title: add
	 * @Description: 添加一条记录
	 * @param
	 * @return String
	 * @throws
	 * @author lipp
	 * @version 1.0
	 * @createtime 2014-2-19 下午2:21:01
	 */
	public String add() {
		HadoopUserGroup userGroup = new HadoopUserGroup();
		clusterList = hadoopUserGroupService.queryWholeClusterList(userGroup);
		return "add";
	}

	/**
	 * @Title: checkIfExsit
	 * @Description: 检测是否存在同名用户
	 * @param
	 * @return void
	 * @throws
	 * @author lipp
	 * @version 1.0
	 * @createtime 2014-2-24 下午5:26:36
	 */
	public void checkIfExsit() {
		if (hadoopUserObj == null) {
			hadoopUserObj = new HadoopUserObj();
		}
		String[] serviceTypeArr = hadoopUserObj.getServiceArr().split(",");
		List<Integer> serviceList = new ArrayList<Integer>();
		for (String string : serviceTypeArr) {
			serviceList.add(Integer.parseInt(string));
		}
		hadoopUserObj.setService_type(null);
		hadoopUserObj.setTypeList(serviceList);
		resultList = hadoopUserService.queryForListByObj(hadoopUserObj);
		String ret = "0";
		if (resultList.size() > 0) {
			ret = "-1";
		}
		JSONObject jo = new JSONObject();
		jo.put("ret", ret);
		try {
			response.setCharacterEncoding("UTF-8");
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
	 * @Title: save
	 * @Description: 保存用户
	 * @param
	 * @return void
	 * @throws
	 * @author lipp
	 * @version 1.0
	 * @createtime 2014-2-24 下午5:30:14
	 */
	public void save() {
		int ret = 0;
		String msg = "";
		if ("add".equals(oper)) {// 添加操作
			hadoopUserObj.setId(RandomUUID.getUuid());
			ret = hadoopUserService.insertByObj(hadoopUserObj);
			if (ret == -1) {
				msg = "插入用户:" + hadoopUserObj.getUsername() + "失败";
			} else {
				HadoopUserServiceRelationObj hadoopUserServiceRelationObj = new HadoopUserServiceRelationObj();
				hadoopUserServiceRelationObj
						.setEntity_id(hadoopUserObj.getId());
				hadoopUserServiceRelationObj.setEntity_type(0);
				hadoopUserServiceRelationObj.setStatus(0);
				hadoopUserServiceRelationObj.setDeal_type(0);
				String[] serviceArr = hadoopUserObj.getServiceArr().split(",");
				String[] serviceName = hadoopUserObj.getServiceTypeStr().split(
						",");
				int count1 = 0;
				int count2 = 0;
				String serviceNamearr = "";
				for (int i = 0; i < serviceArr.length; i++) {
					hadoopUserServiceRelationObj.setId(RandomUUID.getUuid());
					hadoopUserServiceRelationObj.setService_type(Integer
							.parseInt(serviceArr[i]));
					ret = hadoopUserServiceRelationService
							.insertByObj(hadoopUserServiceRelationObj);
					if (ret == -1) {
						count1++;
					} else {
						HadoopUserDealTaskObj taskObj = new HadoopUserDealTaskObj();
						taskObj.setId(RandomUUID.getUuid());
						taskObj.setEntity_id(hadoopUserObj.getId());
						taskObj.setService_type(hadoopUserServiceRelationObj
								.getService_type());
						taskObj.setStatus(0);
						taskObj.setDeal_count(0);
						taskObj.setType(0);
						ret = hadoopUserDealTaskService.insertByObj(taskObj);
						if (ret == -1) {
							count2++;
							serviceNamearr += serviceName[i] + ",";
						}
					}
				}
				if (count1 > 0) {
					msg = "部分服务（--）添加用户:" + hadoopUserObj.getUsername()
							+ "失败，请删除相应记录重新添加;";
				}
				if (count2 > 0) {
					msg += serviceNamearr + "服务添加用户："
							+ hadoopUserObj.getUsername() + "任务失败，请删除相应记录重新添加";
				}
			}
		}
		try {
			JSONObject jo = new JSONObject();
			jo.put("msg", msg);
			response.setCharacterEncoding("UTF-8");
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
	 * @Title: delete
	 * @Description: 回收用户
	 * @param
	 * @return String
	 * @throws
	 * @author lipp
	 * @version 1.0
	 * @createtime 2014-2-24 下午5:49:35
	 */
	public String delete() {
		if (hadoopUserObj == null) {
			hadoopUserObj = new HadoopUserObj();
		}
		// 更新关系表状态
		HadoopUserServiceRelationObj hadoopUserServiceRelationObj = new HadoopUserServiceRelationObj();
		hadoopUserServiceRelationObj.setEntity_id(hadoopUserObj.getId());
		hadoopUserServiceRelationObj.setEntity_type(0);
		hadoopUserServiceRelationObj.setService_type(hadoopUserObj
				.getService_type());
		hadoopUserServiceRelationObj.setDeal_type(1);
		hadoopUserServiceRelationObj.setStatus(0);
		int ret = hadoopUserServiceRelationService
				.updateStatus(hadoopUserServiceRelationObj);
		String msg = "";
		if (ret == -1) {
			msg = "更改用户组状态失败，请重新添加回收任务";
		} else {
			HadoopUserDealTaskObj taskObj = new HadoopUserDealTaskObj();
			taskObj.setId(RandomUUID.getUuid());
			taskObj.setEntity_id(hadoopUserObj.getId());
			taskObj.setService_type(hadoopUserObj.getService_type());
			taskObj.setStatus(0);
			taskObj.setType(1);
			taskObj.setDeal_count(0);
			ret = hadoopUserDealTaskService.insertByObj(taskObj);
			if (ret == -1) {
				msg = "添加回收任务失败，请联系管理员解决";
			}
		}
		try {
			response.setCharacterEncoding("UTF-8");
			// PrintWriter pw = response.getWriter();
			// pw.print(msg);
			// pw.flush();
			// pw.close();
			PrintWriterOut.printWirter(response, msg);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * @Title: checkIfRecoverable
	 * @Description: 检测用户是否可以被回收
	 * @param
	 * @return void
	 * @throws
	 * @author lipp
	 * @version 1.0
	 * @createtime 2014-2-24 下午5:53:57
	 */
	public void checkIfRecoverable() {
		if (hadoopUserObj == null) {
			hadoopUserObj = new HadoopUserObj();
		}
		HadoopGroupMember hadoopGroupMember = new HadoopGroupMember();
		hadoopGroupMember.setUser_id(hadoopUserObj.getId());
		hadoopGroupMember.setService_type(hadoopUserObj.getService_type());
		hadoopGroupMember.setStatus(2);
		List<HadoopGroupMember> list = hadoopGroupMemberService
				.queryUnDoneList(hadoopGroupMember);
		int ret = 0;
		if (list.size() > 0) {
			ret = -1;
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

	/**
	 * @Title: addAuthority
	 * @Description: 添加权限
	 * @param
	 * @return String
	 * @throws
	 * @author lipp
	 * @version 1.0
	 * @createtime 2014-3-5 下午3:35:16
	 */
	public String addAuthority() {
		return "addAuthority";
	}

	/**
	 * @Title: checkPath
	 * @Description: 检测路径
	 * @param
	 * @return void
	 * @throws
	 * @author lipp
	 * @version 1.0
	 * @createtime 2014-3-6 下午3:00:29
	 */
	public void checkPath() {
		if (hadoopUserObj == null) {
			hadoopUserObj = new HadoopUserObj();
		}
		// 查询集群下的所有用户
		List<HadoopUserObj> list = hadoopUserService
				.queryForList(hadoopUserObj);
		List<String> entityIdList = new ArrayList<String>();
		for (HadoopUserObj userObj : list) {
			entityIdList.add(userObj.getId());
		}
		HadoopAuthorityObj authorityObj = new HadoopAuthorityObj();
		authorityObj.setService_type(hadoopUserObj.getService_type());
		authorityObj.setEntityIdList(entityIdList);
		authorityObj.setPath(authPath);
		List<HadoopAuthorityObj> autList = hadoopAuthorityService
				.queryForListByObj(authorityObj);
		String ret = "";
		if (autList.size() > 0) {
			ret = "-1";
		}
		try {
			PrintWriter pw = response.getWriter();
			pw.print(ret);
			pw.flush();
			pw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @Title: savePath
	 * @Description: 保存路径
	 * @param
	 * @return void
	 * @throws
	 * @author lipp
	 * @version 1.0
	 * @createtime 2014-3-5 下午4:08:01
	 */
	public void savePath() {
		if (hadoopUserObj == null) {
			hadoopUserObj = new HadoopUserObj();
		}
		// 第一步，获取该服务主节点的ip、系统用户名、密码
		hadoopUserObj.setEntity_type(0);
		hadoopUserObj.setPurpose(1);
		List<HadoopUserObj> groupList = hadoopUserService
				.queryForSystemUser(hadoopUserObj);
		HadoopUserObj userObj = new HadoopUserObj();
		if (groupList.size() > 0) {
			userObj = groupList.get(0);
		}
		String systemUserName = userObj.getUsername();
		String password = userObj.getPassword();
		String host = userObj.getIp();
		String shellCmd = "";

		// 第二步：登陆主机创建目录
		shellCmd = "/e3base/hadoop/bin/hadoop fs -mkdir " + authPath;
		String ret = executeLinuxCmd(shellCmd, host, systemUserName, password);
		if (!GanymedSshUtil.ReturnFlag.equals(ret)) {
			// 第三步：改变目录属主
			List<HadoopUserObj> list = hadoopUserService
					.queryForList(hadoopUserObj);
			if (list.size() > 0) {
				userObj = list.get(0);
			}
			shellCmd = "/e3base/hadoop/bin/hadoop fs -chown "
					+ userObj.getUsername() + " " + authPath;
			ret = executeLinuxCmd(shellCmd, host, systemUserName, password);
		}

		if (!GanymedSshUtil.ReturnFlag.equals(ret)) {
			// 第四步：授权
			shellCmd = "/e3base/hadoop/bin/hadoop fs -chmod " + autho + " "
					+ authPath;
			ret = executeLinuxCmd(shellCmd, host, systemUserName, password);
		}

		if (!GanymedSshUtil.ReturnFlag.equals(ret)) {
			// 第五步：入库
			HadoopAuthorityObj authorityObj = new HadoopAuthorityObj();
			authorityObj.setId(RandomUUID.getUuid());
			authorityObj.setEntity_id(hadoopUserObj.getId());
			authorityObj.setPath(authPath);
			authorityObj.setService_type(hadoopUserObj.getService_type());
			authorityObj.setAuthority(autho);
			hadoopAuthorityService.insertByObj(authorityObj);
		}
	}

	/**
	 * @Title: executeLinuxCmd
	 * @Description: 执行linux命令
	 * @param
	 * @return void
	 * @throws
	 * @author lipp
	 * @version 1.0
	 * @throws Exception
	 * @createtime 2014-3-5 下午6:26:43
	 */
	private String executeLinuxCmd(String shellCmd, String host,
			String systemUserName, String password) {
		Connection conn = null;
		String ret = null;
		try {
			conn = GanymedSshUtil.getConnection(host, systemUserName, password,22);
			ret = GanymedSshUtil.executeShellCmd(conn, shellCmd);
		} catch (Exception e) {
			ret = GanymedSshUtil.ReturnFlag;
		} finally {
			GanymedSshUtil.closeConnection(conn);
		}
		return ret;
	}

	/**
	 * @Title: viewAuthority
	 * @Description: 查看权限
	 * @param
	 * @return String
	 * @throws
	 * @author lipp
	 * @version 1.0
	 * @createtime 2014-3-6 下午6:57:09
	 */
	public String viewAuthority() {
		if (hadoopUserObj == null) {
			hadoopUserObj = new HadoopUserObj();
		}
		HadoopAuthorityObj authorityObj = new HadoopAuthorityObj();
		authorityObj.setEntity_id(hadoopUserObj.getId());
		authorityObj.setService_type(hadoopUserObj.getService_type());
		authorityObj.setPath(authPath);
		authorityObj.setPagination(getPaginater().initPagination(request));
		auList = hadoopAuthorityService.queryForListByObj(authorityObj);
		return "viewAuthority";
	}

	/**
	 * @Title: editAuthority
	 * @Description: 进入权限变更页面
	 * @param
	 * @return String
	 * @throws
	 * @author lipp
	 * @version 1.0
	 * @createtime 2014-3-7 上午10:18:43
	 */
	public String editAuthority() {
		HadoopAuthorityObj authorityObj = new HadoopAuthorityObj();
		authorityObj.setId(authPath);
		auList = hadoopAuthorityService.queryForListByObj(authorityObj);
		hadoopAuthorityObj = auList.get(0);
		autho = hadoopAuthorityObj.getAuthority();
		auto1 = autho.charAt(0) + "";
		auto2 = autho.charAt(1) + "";
		auto3 = autho.charAt(2) + "";
		authPath = hadoopAuthorityObj.getPath();
		return "editAuthority";
	}

	/**
	 * @Title: delAuthority
	 * @Description: 回收权限
	 * @param
	 * @return void
	 * @throws
	 * @author lipp
	 * @version 1.0
	 * @createtime 2014-3-7 上午10:00:47
	 */
	public void delAuthority() {
		if (hadoopUserObj == null) {
			hadoopUserObj = new HadoopUserObj();
		}

		// 第一步，获取该服务主节点的ip、系统用户名、密码
		hadoopUserObj.setEntity_type(0);
		hadoopUserObj.setPurpose(1);
		List<HadoopUserObj> groupList = hadoopUserService
				.queryForSystemUser(hadoopUserObj);
		HadoopUserObj userObj = new HadoopUserObj();
		if (groupList.size() > 0) {
			userObj = groupList.get(0);
		}
		String systemUserName = userObj.getUsername();
		String password = userObj.getPassword();
		String host = userObj.getIp();
		String shellCmd = "";

		// 第二步，查询路径
		HadoopAuthorityObj authorityObj = new HadoopAuthorityObj();
		authorityObj.setId(authPath);
		auList = hadoopAuthorityService.queryForListByObj(authorityObj);
		authPath = auList.get(0).getPath();

		// 第三步：登陆主机变更权限
		shellCmd = "/e3base/hadoop/bin/hadoop fs -chmod " + autho + " "
				+ authPath;
		String ret = executeLinuxCmd(shellCmd, host, systemUserName, password);
		if (!GanymedSshUtil.ReturnFlag.equals(ret)) {
			// 第四步：改库
			authorityObj.setAuthority(autho);
			hadoopAuthorityService.updateByObj(authorityObj);
		}
	}

	public HadoopUserObj getHadoopUserObj() {
		return hadoopUserObj;
	}

	public void setHadoopUserObj(HadoopUserObj hadoopUserObj) {
		this.hadoopUserObj = hadoopUserObj;
	}

	public List<HadoopUserObj> getResultList() {
		return resultList;
	}

	public void setResultList(List<HadoopUserObj> resultList) {
		this.resultList = resultList;
	}

	public List<HadoopClusterObj> getClusterList() {
		return clusterList;
	}

	public void setClusterList(List<HadoopClusterObj> clusterList) {
		this.clusterList = clusterList;
	}

	public String getOper() {
		return oper;
	}

	public void setOper(String oper) {
		this.oper = oper;
	}

	public String getAuthPath() {
		return authPath;
	}

	public void setAuthPath(String authPath) {
		this.authPath = authPath;
	}

	public String getAutho() {
		return autho;
	}

	public void setAutho(String autho) {
		this.autho = autho;
	}

	public List<HadoopAuthorityObj> getAuList() {
		return auList;
	}

	public void setAuList(List<HadoopAuthorityObj> auList) {
		this.auList = auList;
	}

	public HadoopAuthorityObj getHadoopAuthorityObj() {
		return hadoopAuthorityObj;
	}

	public void setHadoopAuthorityObj(HadoopAuthorityObj hadoopAuthorityObj) {
		this.hadoopAuthorityObj = hadoopAuthorityObj;
	}

	public String getAuto1() {
		return auto1;
	}

	public void setAuto1(String auto1) {
		this.auto1 = auto1;
	}

	public String getAuto2() {
		return auto2;
	}

	public void setAuto2(String auto2) {
		this.auto2 = auto2;
	}

	public String getAuto3() {
		return auto3;
	}

	public void setAuto3(String auto3) {
		this.auto3 = auto3;
	}

}
