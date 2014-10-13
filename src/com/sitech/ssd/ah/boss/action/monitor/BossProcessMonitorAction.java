package com.sitech.ssd.ah.boss.action.monitor;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.TreeSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.I0Itec.zkclient.ZkClient;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sitech.basd.component.domain.user.UserObj;
import com.sitech.basd.component.service.user.UserService;
import com.sitech.basd.sxcloud.rsmu.domain.hostmanage.TbBusiHostObj;
import com.sitech.basd.sxcloud.rsmu.service.hostmanage.BusiHostService;
import com.sitech.basd.sxcloud.rsmu.web.BaseAction;
import com.sitech.basd.sxcloud.util.Struts2Utils;
import com.sitech.ssd.ah.boss.domain.common.CommonObj;
import com.sitech.ssd.ah.boss.domain.monitor.BossMonitorObj;
import com.sitech.ssd.ah.boss.domain.monitor.BossTreeObj;
import com.sitech.ssd.ah.boss.service.monitor.BossProcessMonitorService;
import com.sitech.utils.exception.RabbitMQException;
import com.sitech.utils.properties.PropertiesUtil;
import com.sitech.utils.rabbitmq.RabbitMQUtil;
import com.sitech.utils.servlet.PrintWriterOut;
import com.sitech.utils.zookeeper.BossDynamicTreeMonitor;
import com.sitech.utils.zookeeper.BossDynamicTreeMonitor.StaticTreeLeafNode;

import enumtype.Types;

/**
 * <p>
 * Title: BossProcessMonitorAction
 * </p>
 * <p>
 * Description: Boss进程监控
 * </p>
 * <p>
 * Copyright: Copyright (c) 2014
 * </p>
 * <p>
 * Company: SI-TECH
 * </p>
 * 
 * @author qism
 * @version 1.0
 * @createtime 2014-8-4 下午4:41:14
 * 
 */
@SuppressWarnings("unchecked")
@Controller("bossProcessMonitorAction")
@Scope("prototype")
public class BossProcessMonitorAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	/** 打印日志 **/
	private static final Logger logger = Logger.getLogger(BossProcessMonitorAction.class);
	@Autowired
	BossProcessMonitorService bossProcessMonitorService;
	@Autowired
	private UserService userService;
	@Autowired
	private RabbitMQUtil rabbitmqUtil;
	private BusiHostService busiHostService;
	ZkClient zkClient;
	Properties p;
	private BossMonitorObj obj;
	private BossTreeObj tobj;
	private TbBusiHostObj hobj;
	private CommonObj coObj;
	private List<BossMonitorObj> bmObjListResult;
	private Map<String, List<UserObj>> mapResult;
	private List<TbBusiHostObj> busiHostList;
	private List<CommonObj> commonList;
	private String userIds;
	private String userNames;
	private String passWords;
	private String ips;
	private String flag;

	// 最终返回的集合
	private List<BossMonitorObj> resultList = new ArrayList<BossMonitorObj>();;

	public BossTreeObj getTobj() {
		return tobj;
	}

	public void setTobj(BossTreeObj tobj) {
		this.tobj = tobj;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public CommonObj getCoObj() {
		return coObj;
	}

	public void setCoObj(CommonObj coObj) {
		this.coObj = coObj;
	}

	public List<CommonObj> getCommonList() {
		return commonList;
	}

	public void setCommonList(List<CommonObj> commonList) {
		this.commonList = commonList;
	}

	public BusiHostService getBusiHostService() {
		return busiHostService;
	}

	public void setBusiHostService(BusiHostService busiHostService) {
		this.busiHostService = busiHostService;
	}

	public TbBusiHostObj getHobj() {
		return hobj;
	}

	public void setHobj(TbBusiHostObj hobj) {
		this.hobj = hobj;
	}

	public void setBmObjListResult(List<BossMonitorObj> bmObjListResult) {
		this.bmObjListResult = bmObjListResult;
	}

	public void setMapResult(Map<String, List<UserObj>> mapResult) {
		this.mapResult = mapResult;
	}

	public List<TbBusiHostObj> getBusiHostList() {
		return busiHostList;
	}

	public void setBusiHostList(List<TbBusiHostObj> busiHostList) {
		this.busiHostList = busiHostList;
	}

	public BossMonitorObj getObj() {
		return obj;
	}

	public void setObj(BossMonitorObj obj) {
		this.obj = obj;
	}

	public String getUserIds() {
		return userIds;
	}

	public void setUserIds(String userIds) {
		this.userIds = userIds;
	}

	public String getUserNames() {
		return userNames;
	}

	public void setUserNames(String userNames) {
		this.userNames = userNames;
	}

	public String getPassWords() {
		return passWords;
	}

	public void setPassWords(String passWords) {
		this.passWords = passWords;
	}

	public String getIps() {
		return ips;
	}

	public void setIps(String ips) {
		this.ips = ips;
	}

	public List<BossMonitorObj> getBmObjListResult() {
		return bmObjListResult;
	}

	public Map<String, List<UserObj>> getMapResult() {
		return mapResult;
	}

	public List<BossMonitorObj> getResultList() {
		return resultList;
	}

	public void setResultList(List<BossMonitorObj> resultList) {
		this.resultList = resultList;
	}

	/**
	 * @Title: queryMonitorObj
	 * @Description: 查询监控进程对象
	 * @param
	 * @return String
	 * @throws
	 * @author qism
	 * @version 1.0
	 * @throws SQLException
	 * @createtime 2014-8-4 下午5:17:11
	 */
	public String queryMonitorObjList() {
		if (obj == null) {
			obj = new BossMonitorObj();
		}
		if (tobj == null) {
			tobj = new BossTreeObj();
		}
		String type = tobj.getType();
		obj.setPagination(this.getPaginater().initPagination(Struts2Utils.getRequest()));
		if (type == null || "".equals(type) || "0".equals(type)) {
			// 查询全部进程
		} else if ("1".equals(type)) {
			// 某一集群下的进程
			obj.setCluster_id(tobj.getName());// 集群名称，即当前节点名称
		} else if ("2".equals(type)) {
			// 集群下，某池子下的进程
			obj.setCluster_id(tobj.getParent_name());// 集群名称
			obj.setProgram_name(tobj.getName() + "_");// 池子名称，即当前节点名称
		} else if ("3".equals(type)) {
			// 主机下的进程
			obj.setCluster_id(tobj.getGrand_name());
			obj.setProgram_name(tobj.getParent_name());
			obj.setHost_ip(tobj.getName());
		}
		// 得到所有的进程，不分页
		List<BossMonitorObj> bossMonitorObjList = bossProcessMonitorService
				.queryMonitorObjList(obj);
		try {
			// 得到实时的启停标识
			List<BossMonitorObj> list = this.queryMemDBByJDBC(bossMonitorObjList);
			this.bossMonitorZB(list);
			/** 实现集合分页 */
			int count = bmObjListResult.size();// 获取总数
			obj.getPagination().setTotalCount(count);
			// 获取某一页的列表
			int i = obj.getPagination().getFirstRownum();// 从第几条开始读取
			int j = obj.getPagination().getPageSize();// 一页显示个数
			int k = 0;// 已获取个数
			if (count != 0) {
				for (; i < count; i++) {
					resultList.add(bmObjListResult.get(i));
					k++;
					if (k == j) {
						break;
					}
				}
			}
		} catch (SQLException e) {
			logger.error("查询内存数据库出错！", e);
		}
		return "list";
	}

	/**
	 * @Title: operProcess
	 * @Description: 对Boss应用进程的启停操作
	 * @param
	 * @return void
	 * @throws
	 * @author qism
	 * @version 1.0
	 * @createtime 2014-8-7 下午3:52:36
	 */
	public synchronized String operProcess() {
		HttpServletRequest request = Struts2Utils.getRequest();
		HttpServletResponse response = Struts2Utils.getResponse();
		String oper = request.getParameter("oper");// start,stop
		String cluIds = request.getParameter("cluIds");
		String pnames = request.getParameter("pnames");
		List<BossMonitorObj> objList = new ArrayList<BossMonitorObj>();
		List<process.ProcessObjAgent> processObjs = new ArrayList<process.ProcessObjAgent>();
		// 先改云平台库进程标识(0允许启动、1禁止启动),主备状态设置4(即操作中…)
		logger.info("先改云平台库进程标识");
		BossMonitorObj bObj = new BossMonitorObj();
		bObj.setCluster_id(cluIds);
		bObj.setProgram_name(pnames);
		bObj.setOperator_id("start".equals(oper) ? "0" : "1");
		bossProcessMonitorService.updateMonitorObj(bObj);
		// 发送至消息队列
		objList = bossProcessMonitorService.queryMonitorObjListByObj(bObj);
		for (BossMonitorObj foo : objList) {
			process.ProcessObjAgent processObj = new process.ProcessObjAgent();
			processObj.setClusterName(foo.getCluster_id());
			processObj.setpName(foo.getProgram_name());
			if ("start".equals(oper)) {
				processObj.setStatus(Types.processOperStatus.START);
			} else {
				processObj.setStatus(Types.processOperStatus.STOP);
			}
			processObjs.add(processObj);
		}
		logger.info("开始发送至后台");
		try {
			if ("start".equals(oper)) {
				rabbitmqUtil.publishMessage("boss.process.oper.queue.exchange", "", processObjs);
			} else if ("stop".equals(oper)) {
				rabbitmqUtil.publishMessage("boss.process.oper.queue.exchange", "", processObjs);
			} else {
				logger.debug("oper传递参数错误，无此参数,内置参数为[start,stop]");
			}
			logger.info("发送成功！");
		} catch (RabbitMQException e) {
			logger.error("发送至消息队列失败!", e);
		}
		/** 修改进程状态为 处理中，代码移至后台OperBossProcessCustomerService类处理 */
		return null;
	}

	/**
	 * @Title: showHostDetailAdd
	 * @Description: 跳转到主机添加页面
	 * @param
	 * @return String
	 * @throws
	 * @author qism
	 * @version 1.0
	 * @createtime 2014-8-9 下午1:07:25
	 */
	public String showHostDetailAdd() {
		return "showHostDetailAdd";
	}

	/**
	 * @Title: checkOperPage
	 * @Description: 跳转至身份验证页面
	 * @param
	 * @return String
	 * @throws
	 * @author qism
	 * @version 2.0
	 * @createtime 2014-8-7 上午11:15:45
	 */
	public String checkOperPage() {
		mapResult = new HashMap<String, List<UserObj>>();
		// 对用户ID去重
		String[] newIDs = this.stringUtilTo(userIds);
		// 得到每个ID的用户对象，并以IP分类
		for (String id : newIDs) {
			UserObj userObj = new UserObj();
			userObj.setId(id);
			userObj = userService.queryUserObjById(userObj);
			String ip = userObj.getIp();
			if (mapResult.containsKey(ip)) {
				List<UserObj> objList = mapResult.get(ip);
				objList.add(userObj);
				mapResult.put(ip, objList);
			} else {
				List<UserObj> objList = new ArrayList<UserObj>();
				objList.add(userObj);
				mapResult.put(ip, objList);
			}
		}
		return "check";
	}

	/**
	 * @Title: checkOperToDo
	 * @Description: 验证填写是否正确
	 * @param
	 * @return void
	 * @throws
	 * @author qism
	 * @version 2.0
	 * @createtime 2014-8-7 下午2:52:12
	 */
	public void checkOperToDo() {
		String result = "";
		String[] nameArr = userNames.split(",");
		String[] passArr = passWords.split(",");
		String[] ipArr = ips.split(",");
		int i = 0;
		for (String name : nameArr) {
			UserObj userObj = new UserObj();
			String pass = passArr[i];
			String ip = ipArr[i];
			userObj.setUsername(name);
			userObj.setIp(ip);
			List<UserObj> list = userService.listForProcess(userObj);
			if (list == null || list.size() < 1) {
				result += "无" + name + "用户!" + "\n";
			} else {
				String passWord = list.get(0).getPassword();
				if (!pass.equals(passWord)) {
					result += name + "用户密码填写错误！" + "\n";
				}
			}
			++i;
		}
		JSONObject jo = new JSONObject();
		jo.put("result", result);
		PrintWriterOut.printWirter(response, jo);
	}

	/**
	 * @Title: stringUtilTo
	 * @Description: 对字符串数组去重
	 * @param
	 * @return String[]
	 * @throws
	 * @author qism
	 * @version 1.0
	 * @createtime 2014-8-7 上午11:51:12
	 */
	public String[] stringUtilTo(String str) {
		String[] idArr = str.split(",");
		TreeSet<String> set = new TreeSet<String>();
		for (int i = 0; i < idArr.length; i++) {
			set.add(idArr[i]);
		}
		String[] newIdArr = new String[set.size()];
		for (int j = 0; j < newIdArr.length; j++) {
			newIdArr[j] = set.pollFirst();
		}
		return newIdArr;
	}

	/**
	 * @Title: bossMonitorZB
	 * @Description: 将查询的数据比对动态、静态树，得到进程的主备状态
	 * @param
	 * @return void
	 * @throws
	 * @author qism
	 * @version 1.0
	 * @createtime 2014-8-14 下午4:24:27
	 */
	public void bossMonitorZB(List<BossMonitorObj> list) {
		List<BossMonitorObj> bmObjListResultQ = new ArrayList<BossMonitorObj>();
		try {
			p = PropertiesUtil.getProperties("properties/zookeeperIP.properties");
			String CONNECTION_STRING = p.getProperty("CONNECTION_STRING");
			int SESSION_TIMEOUT = Integer.parseInt(p.getProperty("SESSION_TIMEOUT"));
			zkClient = new ZkClient(CONNECTION_STRING, SESSION_TIMEOUT);
			BossDynamicTreeMonitor boss = new BossDynamicTreeMonitor(CONNECTION_STRING,
					SESSION_TIMEOUT);
			/** 获取所有程序池下，注册IP下所有的五级动态节点集合 */
			Map<String, Map<String, Map<String, List<StaticTreeLeafNode>>>> map = boss
					.monitorDynTree3();
			a: for (BossMonitorObj bmObj : list) {
				/** 获取当前进程的uid,标识、程序池、注册IP、实时进程id */
				String uid = bmObj.getUid();
				Integer status = bmObj.getStatus();// 进程当前状态
				String cluId = bmObj.getCluster_id();// 集群ID（树上对应节点名）
				String pName = bmObj.getProgram_name();// 进程标识(二进制文件名)
				String pool = bmObj.getApp_pool();// 程训池标识
				String ip = bmObj.getHost_ip();// 所在主机IP
				boolean flag = false;// 是否存在于动态树
				// 五级节点集合是否为空
				if (map == null) {
					for (BossMonitorObj bmObj2 : list) {
						if (4 == bmObj2.getStatus()) {// 操作中
							bmObjListResultQ.add(bmObj2);
						} else {
							bmObj2.setStatus(3);// 主备状态：未运行
							bmObjListResultQ.add(bmObj2);
						}
					}
					break a;
				} else {
					// 处于正在操作中…
					if (4 == bmObj.getStatus()) {
						bmObjListResultQ.add(bmObj);
						continue a;
					} else {
						// 判断该进程是否在动态树上
						// List<StaticTreeLeafNode> dNodeList
						// =map.get(cluId).get(pool).get(ip);// 获取动态树叶子节点
						// 先判断是否有该集群
						Map<String, Map<String, List<StaticTreeLeafNode>>> mapClu = map.get(cluId);
						if (mapClu == null || mapClu.size() < 1) {
							if (4 == bmObj.getStatus()) {// 操作中
								bmObjListResultQ.add(bmObj);
							} else {
								bmObj.setStatus(3);// 主备状态：未运行
								bmObjListResultQ.add(bmObj);
							}
							continue a;
						} else {
							// 判断集群下是否有该池子
							Map<String, List<StaticTreeLeafNode>> mapPool = mapClu.get(pool);
							if (mapPool == null || mapPool.size() < 1) {
								if (4 == bmObj.getStatus()) {// 操作中
									bmObjListResultQ.add(bmObj);
								} else {
									bmObj.setStatus(3);// 主备状态：未运行
									bmObjListResultQ.add(bmObj);
								}
								continue a;
							} else {
								// 判断是否有该主机
								List<StaticTreeLeafNode> dNodeList = mapPool.get(ip);
								if (dNodeList == null || dNodeList.size() < 1) {
									if (4 == bmObj.getStatus()) {// 操作中
										bmObjListResultQ.add(bmObj);
									} else {
										bmObj.setStatus(3);// 主备状态：未运行
										bmObjListResultQ.add(bmObj);
									}
									continue a;
								} else {
									/** 主机存在动态树上 */
									for (StaticTreeLeafNode dNode : dNodeList) {
										// 判断节点属性中第六个属性值是否存在等于pName(进程名或二进制文件名)
										String dataValue = dNode.getNodeData();
										if ("".equals(dataValue) || dataValue == null) {
											continue;
										} else {
											String[] dataArr = dataValue.split(";");
											if (pName.equals(dataArr[5])) {
												// 动态树上有
												flag = true;
												// 判断是否在静态树上
												if (dNode.getDynNodePathList() != null
														&& dNode.getDynNodePathList().size() >= 1) {
													// 存在静态树上
													bmObj.setStatus(1);// 主
													bmObj.setRunning_port(Integer
															.parseInt(dataArr[3]));// 端口号
													bmObjListResultQ.add(bmObj);
													break;
												} else {
													// 静态树上不存在
													bmObj.setStatus(2);// 备
													bmObj.setRunning_port(Integer
															.parseInt(dataArr[3]));
													bmObjListResultQ.add(bmObj);
													break;
												}
											}
										}
									}
								}
							}
						}

					}
				}
				if (!flag) {
					// 动态树上没有
					bmObj.setStatus(3);// 未运行
					bmObjListResultQ.add(bmObj);
				}
			}
			bmObjListResult = new ArrayList<BossMonitorObj>();
			// 列表筛选条件
			if (obj.getStatus() != null && obj.getStatus() == 1) {// 主
				for (BossMonitorObj mm : bmObjListResultQ) {
					if (mm.getStatus() == 1) {
						bmObjListResult.add(mm);
					}
				}
			} else if (obj.getStatus() != null && obj.getStatus() == 2) {// 备
				for (BossMonitorObj mm : bmObjListResultQ) {
					if (mm.getStatus() == 2) {
						bmObjListResult.add(mm);
					}
				}
			} else if (obj.getStatus() != null && obj.getStatus() == 3) {// 未运行
				for (BossMonitorObj mm : bmObjListResultQ) {
					if (mm.getStatus() == 3) {
						bmObjListResult.add(mm);
					}
				}
			} else {// 全选
				for (BossMonitorObj mm : bmObjListResultQ) {
					bmObjListResult.add(mm);
				}
			}
		} catch (Exception e) {
			logger.error("查询进程主备状态出错！", e);
		}
	}

	/**
	 * @Title: queryMemDBByJDBC
	 * @Description: 将查询的数据比对内存数据库中对应数据，得到进程启停标识
	 * @param
	 * @return List<BossMonitorObj>
	 * @throws
	 * @author qism
	 * @version 1.0
	 * @throws SQLException
	 * @createtime 2014-8-14 下午4:51:36
	 */
	public List<BossMonitorObj> queryMemDBByJDBC(List<BossMonitorObj> bmObjList)
			throws SQLException {
		List<BossMonitorObj> list = new ArrayList<BossMonitorObj>();// 返回具备启停标识的集合
		java.sql.Connection conn = null;
		java.sql.Statement state;
		try {
			// 提供驱动包路径，并注册驱动
			Class.forName("com.sitech.dmdb.Driver").newInstance();
			// Class.forName("com.mysql.jdbc.Driver");
			p = PropertiesUtil.getProperties("properties/bossDBaseConn.properties");
			String url = p.getProperty("URL");
			String username = p.getProperty("USERNAME");
			String password = p.getProperty("PASSWORD");
			conn = DriverManager.getConnection(url, username, password);
			// conn.setAutoCommit(false);// 默认自动提交为false
			// 生成一个处理sql语句的接口
			state = conn.createStatement();
			for (BossMonitorObj obj : bmObjList) {
				// 拼接sql
				String sql = "select OPERATOR_ID from PROC_START_CTL where CLUSTER_NAME='"
						+ obj.getCluster_id() + "' and PROGRAM_NAME='" + obj.getProgram_name()
						+ "'";
				ResultSet rs = state.executeQuery(sql);
				// 按行遍历，按字段取值
				while (rs.next()) {
					String opId = rs.getString(1);
					obj.setOperator_id(opId);
					list.add(obj);
				}
				if (rs != null)
					rs.close();
			}
			if (state != null)
				state.close();
		} catch (Exception ex) {
			logger.error("查询内存库出错！", ex);
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
		return list;
	}

	/*	*//**
	 * @Title: listBusiHost
	 * @Description: 返回主机列表
	 * @param
	 * @return void
	 * @throws
	 * @author qism
	 * @version 1.0
	 * @createtime 2014-8-19 下午6:19:45
	 */
	/*
	 * public String listBusiHost() { if (hobj == null) { hobj = new
	 * TbBusiHostObj(); }
	 * hobj.setPagination(this.getPaginater().initPagination(Struts2Utils
	 * .getRequest()));// 分页 busiHostList =
	 * busiHostService.queryForListByObj(hobj); return "listBusiHost"; }
	 *//**
	 * @Title: showClusterDetailAdd
	 * @Description: 跳转到集群选择页面
	 * @param
	 * @return String
	 * @throws
	 * @author qism
	 * @version 1.0
	 * @createtime 2014-8-19 下午6:51:40
	 */
	/*
	 * public String showDetailAdd() { if ("2".equals(coObj.getType())) { flag =
	 * "clu"; } else { flag = "pool"; } commonList =
	 * bossProcessMonitorService.queryCommonObjList(coObj); return
	 * "showDetailAdd"; }
	 */
	/**
	 * @Title: tabs
	 * @Description: 返回进程监控tab页
	 * @param
	 * @return String
	 * @throws
	 * @author qism
	 * @version 1.0
	 * @createtime 2014-9-1 下午7:59:50
	 */
	public String tabs() {
		return "tabs";
	}
}
