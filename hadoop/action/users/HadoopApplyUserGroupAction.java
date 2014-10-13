package action.users;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import service.tree.HadoopTreeService;
import service.users.HadoopAuthorityService;
import service.users.HadoopGroupMemberService;
import service.users.HadoopUserDealTaskService;
import service.users.HadoopUserGroupService;
import service.users.HadoopUserService;
import service.users.HadoopUserServiceRelationService;
import util.HadoopConstant;
import ch.ethz.ssh2.Connection;

import com.sitech.basd.sxcloud.rsmu.web.BaseAction;
import com.sitech.utils.randomid.RandomUUID;
import com.sitech.utils.servlet.PrintWriterOut;
import com.sitech.utils.ssh.ganymed.GanymedSshUtil;

import domain.cluster.HadoopClusterObj;
import domain.service.ServiceObj;
import domain.tree.HadoopTreeObj;
import domain.users.HadoopAuthorityObj;
import domain.users.HadoopGroupMember;
import domain.users.HadoopUserDealTaskObj;
import domain.users.HadoopUserGroup;
import domain.users.HadoopUserObj;
import domain.users.HadoopUserServiceRelationObj;

/**
 * <p>
 * Title: HadoopApplyUserGroupAction
 * </p>
 * <p>
 * Description: hadoop应用用户组管理
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
 * @createtime 2014-2-19 下午2:30:44
 * 
 */
@Controller("hadoopApplyUserGroupAction")
@Scope("prototype")
@SuppressWarnings("serial")
public class HadoopApplyUserGroupAction extends BaseAction {

	private HadoopUserGroup hadoopUserGroup;

	private String oper;// 操作

	private List<HadoopUserGroup> resultList;// 结果集

	private List<HadoopClusterObj> clusterList;// 集群结果集

	private List<ServiceObj> serviceList;// 服务结果集

	private List<HadoopUserObj> userList;// 用户结果集

	private List<HadoopAuthorityObj> auList;// 权限结果集

	private HadoopAuthorityObj hadoopAuthorityObj;

	private String authPath;// 授权路径

	private String autho;// 权限

	private String auto1;// 属主权限

	private String auto2;// 属组全选

	private String auto3;// 他人权限

	@Autowired
	private HadoopUserGroupService hadoopUserGroupService;

	@Autowired
	private HadoopTreeService hadoopTreeService;

	@Autowired
	private HadoopUserServiceRelationService hadoopUserServiceRelationService;

	@Autowired
	private HadoopUserDealTaskService hadoopUserDealTaskService;

	@Autowired
	private HadoopGroupMemberService hadoopGroupMemberService;

	@Autowired
	private HadoopUserService hadoopUserService;

	@Autowired
	private HadoopAuthorityService hadoopAuthorityService;

	/**
	 * @Title: list
	 * @Description: 展示应用用户组列表
	 * @param
	 * @return String
	 * @throws
	 * @author lipp
	 * @version 1.0
	 * @createtime 2014-2-19 下午2:31:38
	 */
	public String list() {
		if (hadoopUserGroup == null) {
			hadoopUserGroup = new HadoopUserGroup();
		}
		if (hadoopUserGroup.getService_type() != null
				&& -1 == hadoopUserGroup.getService_type()) {
			hadoopUserGroup.setService_type(null);
		}
		if ("-1".equals(hadoopUserGroup.getCluster_id())) {
			hadoopUserGroup.setCluster_id(null);
		}
		if (hadoopUserGroup.getState() != null
				&& -1 == hadoopUserGroup.getState()) {
			hadoopUserGroup.setStatus(null);
		} else {
			if (hadoopUserGroup.getState() != null
					&& hadoopUserGroup.getState() == 0) {
				hadoopUserGroup.setDeal_type(0);
				hadoopUserGroup.setStatus(0);
			} else if (hadoopUserGroup.getState() != null
					&& hadoopUserGroup.getState() == 1) {
				hadoopUserGroup.setDeal_type(0);
				hadoopUserGroup.setStatus(1);
			} else if (hadoopUserGroup.getState() != null
					&& hadoopUserGroup.getState() == 2) {
				hadoopUserGroup.setDeal_type(0);
				hadoopUserGroup.setStatus(2);
			} else if (hadoopUserGroup.getState() != null
					&& hadoopUserGroup.getState() == 3) {
				hadoopUserGroup.setDeal_type(0);
				hadoopUserGroup.setStatus(3);
			} else if (hadoopUserGroup.getState() != null
					&& hadoopUserGroup.getState() == 4) {
				hadoopUserGroup.setDeal_type(1);
				hadoopUserGroup.setStatus(0);
			} else if (hadoopUserGroup.getState() != null
					&& hadoopUserGroup.getState() == 5) {
				hadoopUserGroup.setDeal_type(1);
				hadoopUserGroup.setStatus(1);
			} else if (hadoopUserGroup.getState() != null
					&& hadoopUserGroup.getState() == 6) {
				hadoopUserGroup.setDeal_type(1);
				hadoopUserGroup.setStatus(3);
			}
		}
		hadoopUserGroup.setEntity_type(1);
		hadoopUserGroup.setPagination(getPaginater().initPagination(request));
		resultList = hadoopUserGroupService.queryForListByObj(hadoopUserGroup);
		HadoopUserGroup userGroup = new HadoopUserGroup();
		userGroup.setEntity_type(1);
		clusterList = hadoopUserGroupService.queryForClusterList(userGroup);
		return "list";
	}

	/**
	 * @Title: add
	 * @Description: 添加一个应用用户组
	 * @param
	 * @return String
	 * @throws
	 * @author lipp
	 * @version 1.0
	 * @createtime 2014-2-20 上午10:03:13
	 */
	public String add() {
		HadoopUserGroup userGroup = new HadoopUserGroup();
		clusterList = hadoopUserGroupService.queryWholeClusterList(userGroup);
		return "add";
	}

	/**
	 * @Title: selectService
	 * @Description: 选择服务
	 * @param
	 * @return String
	 * @throws
	 * @author lipp
	 * @version 1.0
	 * @createtime 2014-2-20 下午12:00:14
	 */
	public String selectService() {
		String cluster_id = hadoopUserGroup.getCluster_id();
		HadoopTreeObj hadoopTreeObj = new HadoopTreeObj();
		hadoopTreeObj.setUuid(cluster_id);
		List<HadoopTreeObj> treeList = hadoopTreeService
				.queryForListByObj(hadoopTreeObj);
		if (treeList.size() > 0) {
			hadoopTreeObj = treeList.get(0);
		}

		treeList.clear();
		try {
			treeList = acquireChildNode(HadoopConstant.serviceNode, null,
					hadoopTreeObj, new ArrayList<HadoopTreeObj>());
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 查询各种服务下服务实例的个数
		List<String> idList = new ArrayList<String>();
		for (HadoopTreeObj treeObj : treeList) {
			idList.add(treeObj.getId());
		}
		HadoopTreeObj treeObj = new HadoopTreeObj();
		treeObj.setParentIdList(idList);
		serviceList = hadoopTreeService.queryForServiceList(treeObj);
		return "selectService";
	}

	/**
	 * @Title: checkIfExsit
	 * @Description: 检查是否存在同名用户组
	 * @param
	 * @return void
	 * @throws
	 * @author lipp
	 * @version 1.0
	 * @createtime 2014-2-20 下午5:01:08
	 */
	public void checkIfExsit() {
		if (hadoopUserGroup == null) {
			hadoopUserGroup = new HadoopUserGroup();
		}
		String[] serviceTypeArr = hadoopUserGroup.getServiceArr().split(",");
		List<Integer> serviceList = new ArrayList<Integer>();
		for (String string : serviceTypeArr) {
			serviceList.add(Integer.parseInt(string));
		}
		hadoopUserGroup.setService_type(null);
		hadoopUserGroup.setTypeList(serviceList);
		resultList = hadoopUserGroupService.queryForListByObj(hadoopUserGroup);
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
	 * @Title: acquireChildNode
	 * @Description: 递归查询子节点
	 * @param
	 * @return List<HadoopTreeObj>
	 * @throws
	 * @author lipp
	 * @version 1.0
	 * @createtime 2014-2-20 下午3:13:06
	 */
	private List<HadoopTreeObj> acquireChildNode(String childNodeType,
			String parentNodeServiceType, HadoopTreeObj hadoopTreeObj,
			List<HadoopTreeObj> retList) throws Exception {
		try {
			HadoopTreeObj treeObj = new HadoopTreeObj();
			treeObj.setParent_id(hadoopTreeObj.getId());
			List<HadoopTreeObj> treeList = hadoopTreeService
					.queryForListByObj(treeObj);
			for (HadoopTreeObj htObj : treeList) {
				if (childNodeType.equals(htObj.getNode_type())) {
					if (parentNodeServiceType != null
							&& !"".equals(parentNodeServiceType)) {
						if (parentNodeServiceType.equals(htObj
								.getService_type())) {
							retList.add(htObj);
						}
					} else {
						retList.add(htObj);
					}
				} else {
					acquireChildNode(childNodeType, parentNodeServiceType,
							htObj, retList);
				}
			}
			return retList;
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("递归查询子级节点错误，原因： ", e);
		}
	}

	/**
	 * @Title: edit
	 * @Description: 修改一个应用用户组
	 * @param
	 * @return String
	 * @throws
	 * @author lipp
	 * @version 1.0
	 * @createtime 2014-2-20 上午10:04:10
	 */
	public String edit() {
		if (hadoopUserGroup == null) {
			hadoopUserGroup = new HadoopUserGroup();
		}
		// 如果该用户组正在关联用户，不可修改，待修改>>>>>>>>>>>>>>>>
		resultList = hadoopUserGroupService.queryForListByObj(hadoopUserGroup);
		if (resultList.size() > 0) {
			hadoopUserGroup = resultList.get(0);
		}
		hadoopUserGroup.setServiceArr(hadoopUserGroup.getService_type() + "");
		return "edit";
	}

	/**
	 * @Title: save
	 * @Description: 保存更改信息
	 * @param
	 * @return void
	 * @throws
	 * @author lipp
	 * @version 1.0
	 * @createtime 2014-2-21 上午10:58:03
	 */
	public void save() {
		int ret = 0;
		String msg = "";
		if ("add".equals(oper)) {// 添加操作
			hadoopUserGroup.setId(RandomUUID.getUuid());
			ret = hadoopUserGroupService.insertByObj(hadoopUserGroup);
			if (ret == -1) {
				msg = "插入用户组:" + hadoopUserGroup.getName() + "失败";
			} else {
				HadoopUserServiceRelationObj hadoopUserServiceRelationObj = new HadoopUserServiceRelationObj();
				hadoopUserServiceRelationObj.setEntity_id(hadoopUserGroup
						.getId());
				hadoopUserServiceRelationObj.setEntity_type(1);
				hadoopUserServiceRelationObj.setStatus(0);
				hadoopUserServiceRelationObj.setDeal_type(0);
				String[] serviceArr = hadoopUserGroup.getServiceArr()
						.split(",");
				String[] serviceName = hadoopUserGroup.getServiceTypeStr()
						.split(",");
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
						taskObj.setEntity_id(hadoopUserGroup.getId());
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
					msg = "部分服务（--）添加用户组:" + hadoopUserGroup.getName()
							+ "失败，请删除相应记录重新添加;";
				}
				if (count2 > 0) {
					msg += serviceNamearr + "服务添加用户组："
							+ hadoopUserGroup.getName() + "任务失败，请删除相应记录重新添加";
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
	 * @Description: 删除应用用户组，添加回收任务
	 * @param
	 * @return String
	 * @throws
	 * @author lipp
	 * @version 1.0
	 * @createtime 2014-2-19 下午5:30:54
	 */
	public String delete() {
		if (hadoopUserGroup == null) {
			hadoopUserGroup = new HadoopUserGroup();
		}
		// 更新关系表状态
		HadoopUserServiceRelationObj hadoopUserServiceRelationObj = new HadoopUserServiceRelationObj();
		hadoopUserServiceRelationObj.setEntity_id(hadoopUserGroup.getId());
		hadoopUserServiceRelationObj.setEntity_type(1);
		hadoopUserServiceRelationObj.setService_type(hadoopUserGroup
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
			taskObj.setEntity_id(hadoopUserGroup.getId());
			taskObj.setService_type(hadoopUserGroup.getService_type());
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
	 * @Description: 检测用户组是否满足回收条件
	 * @param
	 * @return void
	 * @throws
	 * @author lipp
	 * @version 1.0
	 * @createtime 2014-2-24 上午10:02:16
	 */
	public void checkIfRecoverable() {
		if (hadoopUserGroup == null) {
			hadoopUserGroup = new HadoopUserGroup();
		}
		HadoopGroupMember hadoopGroupMember = new HadoopGroupMember();
		hadoopGroupMember.setGroup_id(hadoopUserGroup.getId());
		hadoopGroupMember.setService_type(hadoopUserGroup.getService_type());
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
	 * @Title: listUserForGroup
	 * @Description: 列出用户组下的用户
	 * @param
	 * @return String
	 * @throws
	 * @author lipp
	 * @version 1.0
	 * @createtime 2014-3-3 下午4:46:37
	 */
	public String listUserForGroup() {
		if (hadoopUserGroup == null) {
			hadoopUserGroup = new HadoopUserGroup();
		}
		// 查询用户组下已经关联的用户
		HadoopUserObj userObj = new HadoopUserObj();
		userObj.setGroupId(hadoopUserGroup.getId());
		userObj.setService_type(hadoopUserGroup.getService_type());
		// userObj.setStatus(2);
		// userObj.setDeal_type(0);
		userObj.setPagination(getPaginater().initPagination(request));
		userList = hadoopUserService.queryForListByObj(userObj);
		return "listUserForGroup";
	}

	/**
	 * @Title: assoUsersForGroup
	 * @Description: 关联用户
	 * @param
	 * @return String
	 * @throws
	 * @author lipp
	 * @version 1.0
	 * @createtime 2014-3-3 下午7:08:18
	 */
	public String assoUsersForGroup() {
		if (hadoopUserGroup == null) {
			hadoopUserGroup = new HadoopUserGroup();
		}
		HadoopUserObj userObj = new HadoopUserObj();
		userObj.setGroupId(hadoopUserGroup.getId());
		userObj.setService_type(hadoopUserGroup.getService_type());
		userObj.setCluster_id(hadoopUserGroup.getCluster_id());
		userObj.setStatus(2);
		userObj.setDeal_type(0);
		userObj.setPagination(getPaginater().initPagination(request));
		userList = hadoopUserService.queryUserListUnAssoed(userObj);
		return "listUserForAsso";
	}

	/**
	 * @Title: saveMembers
	 * @Description: 保存关联关系
	 * @param
	 * @return void
	 * @throws
	 * @author lipp
	 * @version 1.0
	 * @createtime 2014-3-4 上午9:22:32
	 */
	public void saveMembers() {
		if (hadoopUserGroup == null) {
			hadoopUserGroup = new HadoopUserGroup();
		}
		String userIds = hadoopUserGroup.getUserIds();
		String[] arr = userIds.split(",");
		HadoopGroupMember member = new HadoopGroupMember();
		member.setGroup_id(hadoopUserGroup.getId());
		member.setService_type(hadoopUserGroup.getService_type());
		member.setStatus(0);

		HadoopUserDealTaskObj taskObj = new HadoopUserDealTaskObj();
		taskObj.setDeal_count(0);
		taskObj.setType(0);
		taskObj.setService_type(hadoopUserGroup.getService_type());
		taskObj.setStatus(0);
		for (String string : arr) {
			String memberId = RandomUUID.getUuid();
			member.setId(memberId);
			member.setUser_id(string);
			hadoopGroupMemberService.insertByObj(member);
			taskObj.setId(RandomUUID.getUuid());
			taskObj.setEntity_id(memberId);
			hadoopUserDealTaskService.insertByObj(taskObj);
		}
		try {
			// PrintWriter pw = response.getWriter();
			// pw.print("");
			// pw.flush();
			// pw.close();
			PrintWriterOut.printWirter(response, "");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @Title: deleteMembers
	 * @Description: 解除关系
	 * @param
	 * @return void
	 * @throws
	 * @author lipp
	 * @version 1.0
	 * @createtime 2014-3-4 上午9:52:31
	 */
	public void deleteMembers() {
		if (hadoopUserGroup == null) {
			hadoopUserGroup = new HadoopUserGroup();
		}
		HadoopGroupMember member = new HadoopGroupMember();
		member.setStatus(0);
		HadoopUserDealTaskObj taskObj = new HadoopUserDealTaskObj();
		taskObj.setStatus(0);
		taskObj.setService_type(hadoopUserGroup.getService_type());
		taskObj.setType(1);
		taskObj.setDeal_count(0);

		String memberIds = hadoopUserGroup.getUserIds();
		String[] arr = memberIds.split(",");
		List<String> idList = new ArrayList<String>();

		for (String string : arr) {
			idList.add(string);
		}
		// 将原来task删除，避免出现重复记录
		HadoopUserDealTaskObj taskObj1 = new HadoopUserDealTaskObj();
		taskObj1.setUuidList(idList);
		hadoopUserDealTaskService.deleteByObj(taskObj1);

		for (String string : arr) {
			taskObj.setEntity_id(string);
			taskObj.setId(RandomUUID.getUuid());
			hadoopUserDealTaskService.insertByObj(taskObj);
		}
		member.setIdList(idList);
		hadoopGroupMemberService.updateByObj(member);

		try {
			// PrintWriter pw = response.getWriter();
			// pw.print("");
			// pw.flush();
			// pw.close();
			PrintWriterOut.printWirter(response, "");
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
		if (hadoopUserGroup == null) {
			hadoopUserGroup = new HadoopUserGroup();
		}
		// 查询集群下的所有组
		List<HadoopUserGroup> list = hadoopUserGroupService
				.queryForList(hadoopUserGroup);
		List<String> entityIdList = new ArrayList<String>();
		for (HadoopUserGroup userGroup : list) {
			entityIdList.add(userGroup.getId());
		}
		HadoopAuthorityObj authorityObj = new HadoopAuthorityObj();
		authorityObj.setService_type(hadoopUserGroup.getService_type());
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
		if (hadoopUserGroup == null) {
			hadoopUserGroup = new HadoopUserGroup();
		}
		// 第一步，获取该服务主节点的ip、系统用户名、密码
		hadoopUserGroup.setEntity_type(0);
		hadoopUserGroup.setPurpose(1);
		List<HadoopUserGroup> groupList = hadoopUserGroupService
				.queryForSystemUser(hadoopUserGroup);
		HadoopUserGroup userGroup = new HadoopUserGroup();
		if (groupList.size() > 0) {
			userGroup = groupList.get(0);
		}
		String systemUserName = userGroup.getSystemUserName();
		String password = userGroup.getPassword();
		String host = userGroup.getIp();
		String shellCmd = "";

		// 第二步：登陆主机创建目录
		shellCmd = "/e3base/hadoop/bin/hadoop fs -mkdir " + authPath;
		String ret = executeLinuxCmd(shellCmd, host, systemUserName, password);
		if (!GanymedSshUtil.ReturnFlag.equals(ret)) {
			// 第三步：改变目录属主
			List<HadoopUserGroup> list = hadoopUserGroupService
					.queryForList(hadoopUserGroup);
			if (list.size() > 0) {
				userGroup = list.get(0);
			}
			shellCmd = "/e3base/hadoop/bin/hadoop fs -chown :"
					+ userGroup.getName() + " " + authPath;
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
			authorityObj.setEntity_id(hadoopUserGroup.getId());
			authorityObj.setPath(authPath);
			authorityObj.setService_type(hadoopUserGroup.getService_type());
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
			conn = GanymedSshUtil.getConnection(host, systemUserName, password);
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
	 * @createtime 2014-3-6 下午5:03:57
	 */
	public String viewAuthority() {
		if (hadoopUserGroup == null) {
			hadoopUserGroup = new HadoopUserGroup();
		}
		HadoopAuthorityObj authorityObj = new HadoopAuthorityObj();
		authorityObj.setEntity_id(hadoopUserGroup.getId());
		authorityObj.setService_type(hadoopUserGroup.getService_type());
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
	 * @createtime 2014-3-7 上午10:03:05
	 */
	public void delAuthority() {
		if (hadoopUserGroup == null) {
			hadoopUserGroup = new HadoopUserGroup();
		}
		// 第一步，获取该服务主节点的ip、系统用户名、密码
		hadoopUserGroup.setEntity_type(0);
		hadoopUserGroup.setPurpose(1);
		List<HadoopUserGroup> groupList = hadoopUserGroupService
				.queryForSystemUser(hadoopUserGroup);
		HadoopUserGroup userGroup = new HadoopUserGroup();
		if (groupList.size() > 0) {
			userGroup = groupList.get(0);
		}
		String systemUserName = userGroup.getSystemUserName();
		String password = userGroup.getPassword();
		String host = userGroup.getIp();
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

	public HadoopUserGroup getHadoopUserGroup() {
		return hadoopUserGroup;
	}

	public void setHadoopUserGroup(HadoopUserGroup hadoopUserGroup) {
		this.hadoopUserGroup = hadoopUserGroup;
	}

	public String getOper() {
		return oper;
	}

	public void setOper(String oper) {
		this.oper = oper;
	}

	public List<HadoopUserGroup> getResultList() {
		return resultList;
	}

	public void setResultList(List<HadoopUserGroup> resultList) {
		this.resultList = resultList;
	}

	public List<HadoopClusterObj> getClusterList() {
		return clusterList;
	}

	public void setClusterList(List<HadoopClusterObj> clusterList) {
		this.clusterList = clusterList;
	}

	public List<ServiceObj> getServiceList() {
		return serviceList;
	}

	public void setServiceList(List<ServiceObj> serviceList) {
		this.serviceList = serviceList;
	}

	public List<HadoopUserObj> getUserList() {
		return userList;
	}

	public void setUserList(List<HadoopUserObj> userList) {
		this.userList = userList;
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

	public HadoopAuthorityObj getHadoopAuthorityObj() {
		return hadoopAuthorityObj;
	}

	public void setHadoopAuthorityObj(HadoopAuthorityObj hadoopAuthorityObj) {
		this.hadoopAuthorityObj = hadoopAuthorityObj;
	}

}
