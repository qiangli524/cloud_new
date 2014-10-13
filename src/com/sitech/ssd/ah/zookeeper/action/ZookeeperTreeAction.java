package com.sitech.ssd.ah.zookeeper.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sitech.basd.sxcloud.rsmu.web.BaseAction;
import com.sitech.basd.util.PropertyUtil;
import com.sitech.ssd.ah.zookeeper.domain.ZookeeperTreeObj;
import com.sitech.ssd.ah.zookeeper.service.ZookeeperTreeService;
import com.sitech.utils.exception.HttpClientException;
import com.sitech.utils.properties.PropertiesUtil;
import com.sitech.utils.servlet.PrintWriterOut;
import com.sitech.utils.zookeeper.BossZkClient;

@Controller("zookeeperTreeAction")
@Scope("prototype")
@SuppressWarnings("all")
public class ZookeeperTreeAction extends BaseAction {
	/** 打印日志 **/
	private static final Logger logger = LoggerFactory.getLogger(ZookeeperTreeAction.class);
	private BossZkClient zkClient;
	@Autowired
	private ZookeeperTreeService zookeeperTreeService;
	@Autowired
	private PropertyUtil unitedTreeIconProp;
	private Properties p;

	private ZookeeperTreeObj currentTreeObj;// 当前被选中的节点

	private ZookeeperTreeObj treeObj;

	private List<ZookeeperTreeObj> zookeeperTreeObjList = new ArrayList<ZookeeperTreeObj>();

	private String result;// 删除是否成功

	private String currPath;// 当前节点路径

	private String newPath;// 需要新添的节点路径

	private String newDirName;// 修改后的目录名

	private String newDirValue;// 修改后的数据值

	private String flag;

	private String isPare;

	public String getIsPare() {
		return isPare;
	}

	public void setIsPare(String isPare) {
		this.isPare = isPare;
	}

	public String getNewDirValue() {
		return newDirValue;
	}

	public void setNewDirValue(String newDirValue) {
		this.newDirValue = newDirValue;
	}

	public String getNewDirName() {
		return newDirName;
	}

	public void setNewDirName(String newDirName) {
		this.newDirName = newDirName;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getCurrPath() {
		return currPath;
	}

	public void setCurrPath(String currPath) {
		this.currPath = currPath;
	}

	public String getNewPath() {
		return newPath;
	}

	public void setNewPath(String newPath) {
		this.newPath = newPath;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public List<ZookeeperTreeObj> getZookeeperTreeObjList() {
		return zookeeperTreeObjList;
	}

	public void setZookeeperTreeObjList(List<ZookeeperTreeObj> zookeeperTreeObjList) {
		this.zookeeperTreeObjList = zookeeperTreeObjList;
	}

	public ZookeeperTreeObj getCurrentTreeObj() {
		return currentTreeObj;
	}

	public void setCurrentTreeObj(ZookeeperTreeObj currentTreeObj) {
		this.currentTreeObj = currentTreeObj;
	}

	public ZookeeperTreeObj getTreeObj() {
		return treeObj;
	}

	public void setTreeObj(ZookeeperTreeObj treeObj) {
		this.treeObj = treeObj;
	}

	public String tabs() {
		return "tabs";
	}

	/**
	 * @Title: listPaasTree
	 * @Description: 跳到Zookeeper资源树页面
	 * @param
	 * @return String
	 * @throws
	 * @author qism
	 * @version 1.0
	 * @createtime 2014-7-17 上午11:09:11
	 */
	public String listZookeeperTree() {
		return "list";
	}

	/**
	 * @Title: asyncForTree
	 * @Description: 展开树节点
	 * @param
	 * @return void
	 * @throws
	 * @author qism
	 * @version 1.0
	 * @createtime 2014-7-17 下午12:25:09
	 */
	public String asyncForTree() throws HttpClientException {
		/** 通过接口获取zookeeper树结构数据 **/
		try {
			p = PropertiesUtil.getProperties("properties/zookeeperIP.properties");
			String CONNECTION_STRING = p.getProperty("CONNECTION_STRING");
			int SESSION_TIMEOUT = Integer.parseInt(p.getProperty("SESSION_TIMEOUT"));
			zkClient = new BossZkClient(CONNECTION_STRING, SESSION_TIMEOUT);

			String path = request.getParameter("fullPath");
			if ("".equals(path) || path == null) {
				ZookeeperTreeObj zookObj = new ZookeeperTreeObj();
				zookObj.setName("安徽");
				zookObj.setHiddName("安徽");
				zookObj.setDataValue("");
				zookObj.setFullPath("/");
				zookObj.setIcon(unitedTreeIconProp.getString("anhui"));
				/* 判断目录下是否还有子目录 */
				List<String> isNull = zkClient.exists("/") ? zkClient.getChildren("/") : null;// 无节点则报异常
				if (isNull == null || (isNull != null && isNull.size() < 1)) {
					zookObj.setIsParent(false);
				}
				zookeeperTreeObjList.add(zookObj);
			} else {
				try {
					List<String> list = zkClient.exists(path) ? zkClient.getChildren(path)
							: new ArrayList<String>(0);// 获取子目录
					for (String directory : list) {
						ZookeeperTreeObj zookObj = new ZookeeperTreeObj();
						String fullPath;// 节点完整路径
						if (path.equals("/")) {
							fullPath = path + directory;
						} else {
							fullPath = path + "/" + directory;
						}
						logger.info("节点完整路径:" + fullPath);
						String dataValue = "";
						try {
							dataValue = zkClient.readData(fullPath, true);// 没有目录报异常,不能读根目录，目录无数据值OK
						} catch (Exception ex) {
						}
						logger.info("子目录：" + directory + ";dataValue:" + dataValue);
						zookObj.setName(directory);
						zookObj.setHiddName(directory);
						zookObj.setDataValue(dataValue);
						zookObj.setFullPath(fullPath);
						String[] arr = fullPath.split("/");
						int len = arr.length;
						if (len == 2) {
							zookObj.setIcon(unitedTreeIconProp.getString("center"));// 一级节点动静分类
						} else if (zookObj.getName().startsWith("OCS")) {
							zookObj.setIcon(unitedTreeIconProp.getString("online"));// 二级节点在线
						} else if (zookObj.getName().startsWith("OFCS")) {
							zookObj.setIcon(unitedTreeIconProp.getString("downline"));// 二级节点离线
						} else if (len == 3) {
							zookObj.setIcon(unitedTreeIconProp.getString("online"));// 不合规则节点，暂定图标
						} else if (len == 4) {
							zookObj.setIcon(unitedTreeIconProp.getString("cluster"));// 三级节点集群
						} else if (len == 5) {
							zookObj.setIcon(unitedTreeIconProp.getString("database"));// 四级节点功能大类（池子类别）
						} else if (len == 6) {
							zookObj.setIcon(unitedTreeIconProp.getString("server"));// 五级节点池子
						} else {
							if (!"LOCK".equals(zookObj.getName().toUpperCase())) {
								// 判断启停标识
								String[] arrs = dataValue.split(";");
								if ("1".equals(arrs[1]) && fullPath.startsWith("/BOSS_S")) {
									zookObj.setIcon(unitedTreeIconProp.getString("no"));
								} else {
									zookObj.setIcon(unitedTreeIconProp.getString("process"));// 六级节点线程
								}
							}
						}
						if ("LOCK".equals(zookObj.getName().toUpperCase())) {// 如果是lock节点，需要设置特殊图表
							zookObj.setIcon(unitedTreeIconProp.getString("lock"));
						}
						/* 判断目录下是否还有子目录 */
						List<String> isNull = zkClient.exists(fullPath) ? zkClient
								.getChildren(fullPath) : new ArrayList<String>(0);// 无节点则报异常
						if (isNull == null || (isNull != null && isNull.size() < 1)) {
							zookObj.setIsParent(false);
						}
						if (zookObj.getName().equals("zookeeper")
								|| zookObj.getName().equals("storm")) {// 忽略掉这两个节点
						} else {
							zookeeperTreeObjList.add(zookObj);
						}
					}
				} catch (Exception e) {
					logger.error("获取子目录出错", e);
				}
			}
		} catch (Exception e) {
			logger.error("实例化连接工具出错！", e);
		} finally {
			if (zkClient != null)
				zkClient.close();
		}
		return "tree";
	}

	/**
	 * @Title: showNodeDetail
	 * @Description: 跳转到显示页面
	 * @param
	 * @return String
	 * @throws
	 * @author qism
	 * @version 1.0
	 * @createtime 2014-7-17 下午2:11:43
	 */
	public String showNodeDetail() {
		try {
			p = PropertiesUtil.getProperties("properties/zookeeperIP.properties");
			String CONNECTION_STRING = p.getProperty("CONNECTION_STRING");
			int SESSION_TIMEOUT = Integer.parseInt(p.getProperty("SESSION_TIMEOUT"));
			zkClient = new BossZkClient(CONNECTION_STRING, SESSION_TIMEOUT);

			if (currentTreeObj == null) {
				currentTreeObj = new ZookeeperTreeObj();
				// 第一次打开页面显示根节点
				// String dataValue = zkClient.readData("/");
				currentTreeObj.setName("安徽");
				currentTreeObj.setDataValue("");
				currentTreeObj.setFullPath("/");
				currentTreeObj.setFlagT("root");
			} else {
				String path = currentTreeObj.getFullPath();
				String name = currentTreeObj.getName();
				// 获取新路径
				int a = path.lastIndexOf("/");
				String parentPath = path.substring(0, a);// 获取父路径
				String newPath;
				if ("".equals(parentPath) && !"/".equals(path)) {// 根目录
					newPath = "/" + name;
				} else if (!"".equals(parentPath) && !"/".equals(path)) {
					newPath = parentPath + "/" + name;
				} else {
					newPath = "/";
				}
				String value = zkClient.readData(newPath, true);
				if (value == null) {
					value = "";
				}
				String[] valArr = value.split(";");
				try {
					if (path.startsWith("/BOSS_D") && path.split("/").length == 7
							&& !"LOCK".equals(name.toUpperCase())) {
						// 动态树叶子节点
						currentTreeObj.setFlagT("d");
						currentTreeObj.setCpuUse(valArr[0]);
						currentTreeObj.setMenUse(valArr[1]);
						currentTreeObj.setIp(valArr[2]);
						currentTreeObj.setPort(valArr[3]);
						currentTreeObj.setPid(valArr[4]);
						currentTreeObj.setProcessName(valArr[5]);
					} else if (path.startsWith("/BOSS_S") && path.split("/").length == 7
							&& !"LOCK".equals(name.toUpperCase())) {
						// 静态树叶子节点
						currentTreeObj.setFlagT("s");
						currentTreeObj.setDdName(valArr[0]);
						currentTreeObj.setStaSto(valArr[1]);
						currentTreeObj.setProcessID(valArr[2]);
						currentTreeObj.setDirMessage(valArr[3].replace(" ", "\n"));
						currentTreeObj.setHickyMessage(valArr[4].replace(" ", "\n"));
					} else if (path.split("/").length == 4 && !"LOCK".equals(name.toUpperCase())) {
						// 集群节点
						currentTreeObj.setFlagT("c");
						currentTreeObj.setIpAndYm(valArr[0]);
						currentTreeObj.setAreaName(valArr[1]);
					} else if (path.split("/").length == 6 && !"LOCK".equals(name.toUpperCase())) {
						// 池子节点
						currentTreeObj.setFlagT("p");
						currentTreeObj.setMlbID(valArr[0]);
						currentTreeObj.setServerPortArr(valArr[1]);
					} else {
						currentTreeObj.setFlagT("root");
						currentTreeObj.setDataValue(value);
						if ("/".equals(path)) {
							currentTreeObj.setName("安徽");
							currentTreeObj.setDataValue("");
						}
					}
				} catch (Exception e) {
				}
			}
		} catch (Exception e) {
			logger.info("实例化连接工具出错！");
			e.printStackTrace();
		} finally {
			if (zkClient != null)
				zkClient.close();
		}
		return "showNodeDetail";
	}

	/**
	 * @Title: showSelfDetail
	 * @Description: 点击取消后跳转至显示页面
	 * @param
	 * @return String
	 * @throws
	 * @author qism
	 * @version 1.0
	 * @createtime 2014-7-21 上午8:44:56
	 */
	public String cancleToSelfDetail() {
		try {
			p = PropertiesUtil.getProperties("properties/zookeeperIP.properties");
			String CONNECTION_STRING = p.getProperty("CONNECTION_STRING");
			int SESSION_TIMEOUT = Integer.parseInt(p.getProperty("SESSION_TIMEOUT"));
			zkClient = new BossZkClient(CONNECTION_STRING, SESSION_TIMEOUT);
			// 点的是取消,根据老路径查
			if ("/".equals(currentTreeObj.getFullPath())) {
				currentTreeObj.setDataValue("");
				currentTreeObj.setName("安徽");
			} else {
				String dataValue = zkClient.exists(currentTreeObj.getFullPath()) ? String.class
						.cast(zkClient.readData(currentTreeObj.getFullPath(), null)) : "";
				currentTreeObj.setDataValue(dataValue);
				currentTreeObj.setName(currentTreeObj.getHiddName());
			}

		} catch (Exception e) {
			logger.info("实例化连接工具出错！");
			e.printStackTrace();
		} finally {
			if (zkClient != null)
				zkClient.close();
		}
		return "showNodeDetail";
	}

	/**
	 * @Title: addChildNode
	 * @Description: 跳转到添加页面
	 * @param
	 * @return String
	 * @throws
	 * @author qism
	 * @version 1.0
	 * @createtime 2014-7-17 下午2:14:38
	 */
	public String addChildNode() {
		String path = currentTreeObj.getFullPath();
		String value = currentTreeObj.getDataValue();
		String[] valArr = value.split(";");
		if (path.startsWith("/BOSS_D") && path.split("/").length == 6) {
			// 动态树叶子节点
			currentTreeObj.setFlagT("d");
		} else if (path.startsWith("/BOSS_S") && path.split("/").length == 6) {
			// 静态树叶子节点
			currentTreeObj.setFlagT("s");
		} else if (path.split("/").length == 5) {
			currentTreeObj.setFlagT("p");
		} else if (path.split("/").length == 3) {
			currentTreeObj.setFlagT("c");
		} else {
			currentTreeObj.setFlagT("root");
		}

		return "addChildNode";
	}

	/**
	 * @Title: saveChildNode
	 * @Description: 保存节点，并刷新显示父节点信息,Ajax验证目录下是否已存在此节点
	 * @param
	 * @return String
	 * @throws
	 * @author qism
	 * @version 1.0
	 * @createtime 2014-7-17 下午2:17:58
	 */
	public String saveChildNode() {
		try {
			p = PropertiesUtil.getProperties("properties/zookeeperIP.properties");
			String CONNECTION_STRING = p.getProperty("CONNECTION_STRING");
			int SESSION_TIMEOUT = Integer.parseInt(p.getProperty("SESSION_TIMEOUT"));
			zkClient = new BossZkClient(CONNECTION_STRING, SESSION_TIMEOUT);
			// 获取当前路径
			String path = currentTreeObj.getFullPath();
			logger.info(path);
			// 获取新目录名，数据属性值
			String newName = treeObj.getName();
			String newDataValue = treeObj.getDataValue();
			String newPath;
			if (path.equals("/")) {
				newPath = path + newName;
			} else {
				newPath = path + "/" + newName;
			}
			try {
				// 添加到静态树上
				zkClient.createPersistent(newPath, newDataValue.trim());
				// zkClient.create(newPath, newDataValue, null);
				// zkClient.cr
				// 入库
				// zookeeperTreeService.
				result = "添加成功!";
			} catch (Exception e) {
				result = "添加失败!";
				logger.debug("创建节点失败！", e);
			} finally {
			}

		} catch (Exception e) {
			logger.info("实例化连接工具出错！");
			e.printStackTrace();
		} finally {
			if (zkClient != null)
				zkClient.close();
		}
		return "results";
	}

	/**
	 * @Title: isExistsContent
	 * @Description: 增加节点前，需要判断目录是否存在
	 * @param
	 * @return void
	 * @throws
	 * @author qism
	 * @version 1.0
	 * @createtime 2014-7-18 上午11:49:33
	 */
	public void isExistsContent() {
		int ret = 0;// 默认新添目录不存在
		try {
			p = PropertiesUtil.getProperties("properties/zookeeperIP.properties");
			String CONNECTION_STRING = p.getProperty("CONNECTION_STRING");
			int SESSION_TIMEOUT = Integer.parseInt(p.getProperty("SESSION_TIMEOUT"));
			zkClient = new BossZkClient(CONNECTION_STRING, SESSION_TIMEOUT);
			if ("add".equals(flag)) {
				// 添加功能验证
				boolean statCurr = zkClient.exists(currPath);
				if (statCurr) {
					logger.info("检查新添目录是否存在：" + newPath);
					boolean statNew = zkClient.exists(newPath);
					if (statNew) {
						// 目录已存在
						ret = 2;
					}
				} else {
					ret = 1;// 当前节点目录不存在
				}
			} else {
				// 编辑功能验证
				boolean statCurr = zkClient.exists(currPath);// 当前目录是否存在
				if (statCurr) {
					ret = 1;
				} else {
					ret = -1;
					logger.debug("当前节点不存在，请刷新");
				}
			}
		} catch (Exception e) {
			logger.info("实例化连接工具出错！");
			e.printStackTrace();
		} finally {
			if (zkClient != null)
				zkClient.close();
		}
		PrintWriterOut.printWirter(response, ret);
	}

	/**
	 * @Title: delChildNode
	 * @Description: 删除子节点,页面js已判断是否存在子节点,需要判断该节点是否存在
	 * @param
	 * @return void
	 * @throws
	 * @author qism
	 * @version 1.0
	 * @createtime 2014-7-18 下午2:42:28
	 */
	public String delSelfNode() {
		try {
			p = PropertiesUtil.getProperties("properties/zookeeperIP.properties");
			String CONNECTION_STRING = p.getProperty("CONNECTION_STRING");
			int SESSION_TIMEOUT = Integer.parseInt(p.getProperty("SESSION_TIMEOUT"));
			zkClient = new BossZkClient(CONNECTION_STRING, SESSION_TIMEOUT);
			boolean stat = zkClient.exists(currPath);// 不为空就是存在
			if (stat) {
				// 目录存在
				zkClient.delete(currPath);
				result = "移除成功!";
			} else {
				// 目录不存在
				result = "不存在该目录，移除失败!";
			}
		} catch (Exception e) {
			logger.info("实例化连接工具出错！");
			e.printStackTrace();
		} finally {
			if (zkClient != null)
				zkClient.close();
		}
		return "results";
	}

	/**
	 * @Title: editSelfNode
	 * @Description: 转发至编辑页面
	 * @param
	 * @return String
	 * @throws
	 * @author qism
	 * @version 1.0
	 * @createtime 2014-7-19 下午1:13:20
	 */
	public String editSelfNode() {
		if (currentTreeObj == null) {
			currentTreeObj = new ZookeeperTreeObj();
			// 第一次打开页面显示根节点
			// String dataValue = zkClient.readData("/");
			currentTreeObj.setName("根节点");
			currentTreeObj.setDataValue("");
			currentTreeObj.setFullPath("/");
			currentTreeObj.setFlagT("root");
		} else {
			String path = currentTreeObj.getFullPath();
			String value = currentTreeObj.getDataValue();
			String name = currentTreeObj.getName();
			String[] valArr = value.split(";");
			try {
				if (path.startsWith("/BOSS_D") && path.split("/").length == 7
						&& !"LOCK".equals(name.toUpperCase())) {
					// 动态树叶子节点
					currentTreeObj.setFlagT("d");
					currentTreeObj.setCpuUse(valArr[0]);
					currentTreeObj.setMenUse(valArr[1]);
					currentTreeObj.setIp(valArr[2]);
					currentTreeObj.setPort(valArr[3]);
					currentTreeObj.setPid(valArr[4]);
					currentTreeObj.setProcessName(valArr[5]);
				} else if (path.startsWith("/BOSS_S") && path.split("/").length == 7
						&& !"LOCK".equals(name.toUpperCase())) {
					// 静态树叶子节点
					currentTreeObj.setFlagT("s");
					currentTreeObj.setDdName(valArr[0]);
					currentTreeObj.setStaSto(valArr[1]);
					currentTreeObj.setProcessID(valArr[2]);
					currentTreeObj.setDirMessage(valArr[3].replace(" ", "\n"));
					currentTreeObj.setHickyMessage(valArr[4].replace(" ", "\n"));
				} else if (path.split("/").length == 4 && !"LOCK".equals(name.toUpperCase())) {
					// 集群节点
					currentTreeObj.setFlagT("c");
					currentTreeObj.setIpAndYm(valArr[0]);
					currentTreeObj.setAreaName(valArr[1]);
				} else if (path.split("/").length == 6 && !"LOCK".equals(name.toUpperCase())) {
					// 池子节点
					currentTreeObj.setFlagT("p");
					currentTreeObj.setMlbID(valArr[0]);
					currentTreeObj.setServerPortArr(valArr[1]);
				} else {
					currentTreeObj.setFlagT("root");
				}
			} catch (Exception e) {
			}
		}
		return "editSelfNode";
	}

	/**
	 * @Title: saveAfterEdit
	 * @Description: 编辑后保存
	 * @param
	 * @return String
	 * @throws
	 * @author qism
	 * @version 1.0
	 * @createtime 2014-7-19 下午1:24:24
	 */
	public String saveAfterEdit() {
		try {
			p = PropertiesUtil.getProperties("properties/zookeeperIP.properties");
			String CONNECTION_STRING = p.getProperty("CONNECTION_STRING");
			int SESSION_TIMEOUT = Integer.parseInt(p.getProperty("SESSION_TIMEOUT"));
			zkClient = new BossZkClient(CONNECTION_STRING, SESSION_TIMEOUT);
			if ("false".equals(isPare)) {
				/** 无子节点 **/
				int a = currPath.lastIndexOf("/");
				String parentPath = currPath.substring(0, a);// 获取父路径
				String newDirPath;
				if ("".equals(parentPath)) {// 根目录
					newDirPath = "/" + newDirName;
				} else {
					newDirPath = parentPath + "/" + newDirName;
				}
				// 先删除，后添加
				try {
					/*
					 * zkClient.delete(currPath);
					 * zkClient.createPersistent(newDirPath,
					 * newDirValue.trim());
					 */
					zkClient.writeData(currPath, newDirValue.trim());
					result = "编辑成功!";
					logger.debug("编辑成功！");
				} catch (Exception e) {
					result = "编辑节点失败！";
					logger.debug("编辑节点失败", e);
				}
			} else {
				/** 有子节点，只更新数据 **/
				try {
					zkClient.writeData(currPath, newDirValue.trim());
					result = "编辑成功!";
					logger.debug("编辑成功！");
				} catch (Exception e) {
					result = "编辑节点失败！";
					logger.debug("编辑节点失败", e);
				}
			}
		} catch (Exception e) {
			logger.info("实例化连接工具出错！");
			e.printStackTrace();
		} finally {
			if (zkClient != null)
				zkClient.close();
		}
		return "results";
	}
}
