package com.sitech.ssd.ah.zookeeper.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sitech.ssd.ah.boss.domain.common.CommonObj;
import com.sitech.ssd.ah.boss.service.monitor.BossProcessMonitorService;
import com.sitech.ssd.ah.zookeeper.domain.ZookeeperMonitorObj;
import com.sitech.ssd.ah.zookeeper.domain.ZookeeperTreeObj;
import com.sitech.utils.properties.PropertiesUtil;
import com.sitech.utils.zookeeper.BossDynamicTreeMonitor;
import com.sitech.utils.zookeeper.BossDynamicTreeMonitor.StaticTreeLeafNode;

@Controller("zookeeperMonitorAction")
@Scope("prototype")
@SuppressWarnings("all")
public class ZookeeperMonitorAction {
	/** 打印日志 **/
	private static final org.slf4j.Logger logger = LoggerFactory
			.getLogger(ZookeeperMonitorAction.class);
	Properties p;
	@Autowired
	private BossProcessMonitorService bossProcessMonitorService;
	Map<String, List> mapResult = new HashMap<String, List>();
	List<CommonObj> clusterList;
	List<CommonObj> poolList;
	private ZookeeperTreeObj currentTreeObj;// 当前被选中的节点
	CommonObj comObj;
	private String pp;
	private String ff;
	private String cluster_id;
	private String pool_name;

	public List<CommonObj> getPoolList() {
		return poolList;
	}

	public void setPoolList(List<CommonObj> poolList) {
		this.poolList = poolList;
	}

	public String getPool_name() {
		return pool_name;
	}

	public void setPool_name(String pool_name) {
		this.pool_name = pool_name;
	}

	public String getCluster_id() {
		return cluster_id;
	}

	public void setCluster_id(String cluster_id) {
		this.cluster_id = cluster_id;
	}

	public CommonObj getComObj() {
		return comObj;
	}

	public void setComObj(CommonObj comObj) {
		this.comObj = comObj;
	}

	public String getFf() {
		return ff;
	}

	public void setFf(String ff) {
		this.ff = ff;
	}

	public String getPp() {
		return pp;
	}

	public void setPp(String pp) {
		this.pp = pp;
	}

	public ZookeeperTreeObj getCurrentTreeObj() {
		return currentTreeObj;
	}

	public void setCurrentTreeObj(ZookeeperTreeObj currentTreeObj) {
		this.currentTreeObj = currentTreeObj;
	}

	public Map<String, List> getMapResult() {
		return mapResult;
	}

	public void setMapResult(Map<String, List> mapResult) {
		this.mapResult = mapResult;
	}

	public List<CommonObj> getClusterList() {
		return clusterList;
	}

	public void setClusterList(List<CommonObj> clusterList) {
		this.clusterList = clusterList;
	}

	/**
	 * @Title: monitorTree
	 * @Description: 展现监控结果
	 * @param
	 * @return String
	 * @throws
	 * @author qism
	 * @version 1.0
	 * @createtime 2014-7-24 上午8:12:16
	 */
	public String monitorTree() {
		try {
			p = PropertiesUtil.getProperties("properties/zookeeperIP.properties");
			String CONNECTION_STRING = p.getProperty("CONNECTION_STRING");
			int SESSION_TIMEOUT = Integer.parseInt(p.getProperty("SESSION_TIMEOUT"));
			BossDynamicTreeMonitor monitor = new BossDynamicTreeMonitor(CONNECTION_STRING,
					SESSION_TIMEOUT);
			if (comObj == null) {
				comObj = new CommonObj();
			}
			comObj.setType("2");
			clusterList = bossProcessMonitorService.queryCommonObjList(comObj);// 查询集群
			comObj.setType("3");
			poolList = bossProcessMonitorService.queryCommonObjList(comObj);// 查询程训池
			String dataValue = "";
			if (cluster_id == null) {
				cluster_id = clusterList.get(0).getNodeId();
			}
			if (currentTreeObj == null || currentTreeObj.getFullPath().startsWith("/BOSS_S")
					|| "S".equals(pp)) {
				pp = "S";
				// 静态树监控
				// key 指静态节点
				Map<String, List<StaticTreeLeafNode>> map = monitor.monitorDynTree();
				Set<Entry<String, List<StaticTreeLeafNode>>> entrySet = map.entrySet();
				// 通过迭代entrySet变通的迭代Map集合, Map不能直接迭代
				for (Entry<String, List<StaticTreeLeafNode>> e : entrySet) {
					String key = e.getKey();
					/** 指定集群 */
					if (key.contains(cluster_id)) {
						/** 是否指定池 */
						if (!"".equals(pool_name) && pool_name != null) {
							String[] arr = key.split("/");
							if (key.contains(pool_name) && "APP".equals(arr[4])) {
								List<ZookeeperMonitorObj> zookMonitorList = new ArrayList<ZookeeperMonitorObj>();
								logger.info("四级父路径：" + key);
								if (e.getValue() != null && e.getValue().size() >= 1) {
									logger.info("子节点个数：" + e.getValue().size());
									String sbff = "";
									// 迭代该父节点下的每一个五级节点
									for (StaticTreeLeafNode staicNode : e.getValue()) {
										ZookeeperMonitorObj zookMonObj = new ZookeeperMonitorObj();
										if (staicNode.getDynNodePathList() != null
												&& staicNode.getDynNodePathList().size() >= 1) {
											// 节点正常
											zookMonObj.setSiPath(key);
											zookMonObj.setNodeName(staicNode.getNodeName());
											zookMonObj.setFlag(true);// 该五级节点正常
											// 获取对应动态节点的属性
											for (StaticTreeLeafNode dnode : staicNode
													.getDynNodePathList()) {
												dataValue = dnode.getNodeData();
												String[] valueArr = dataValue.split(";");
												sbff = "动态节点名" + dnode.getNodeName() + "进程所在主机IP："
														+ valueArr[2] + ";端口号：" + valueArr[3]
														+ ";进程PID：" + valueArr[4];
												zookMonObj.setDataValue(sbff);
											}
										} else {
											zookMonObj.setSiPath(key);
											zookMonObj.setNodeName(staicNode.getNodeName());
											zookMonObj.setFlag(false);
										}
										zookMonitorList.add(zookMonObj);
									}
								}
								mapResult.put(key, zookMonitorList);
							} else {
								if (pool_name.equals(arr[4])) {
									List<ZookeeperMonitorObj> zookMonitorList = new ArrayList<ZookeeperMonitorObj>();
									logger.info("四级父路径：" + key);
									if (e.getValue() != null && e.getValue().size() >= 1) {
										logger.info("子节点个数：" + e.getValue().size());
										String sbff = "";
										// 迭代该父节点下的每一个五级节点
										for (StaticTreeLeafNode staicNode : e.getValue()) {
											ZookeeperMonitorObj zookMonObj = new ZookeeperMonitorObj();
											if (staicNode.getDynNodePathList() != null
													&& staicNode.getDynNodePathList().size() >= 1) {
												// 不为空，说明节点正常
												zookMonObj.setSiPath(key);
												zookMonObj.setNodeName(staicNode.getNodeName());
												zookMonObj.setFlag(true);// 该五级节点正常
												// 获取对应动态节点的属性
												for (StaticTreeLeafNode dnode : staicNode
														.getDynNodePathList()) {
													dataValue = dnode.getNodeData();
													String[] valueArr = dataValue.split(";");
													sbff = "动态节点名" + dnode.getNodeName()
															+ "进程所在主机IP：" + valueArr[2] + ";端口号："
															+ valueArr[3] + ";进程PID：" + valueArr[4];
													zookMonObj.setDataValue(sbff);
												}
											} else {
												zookMonObj.setSiPath(key);
												zookMonObj.setNodeName(staicNode.getNodeName());
												zookMonObj.setFlag(false);
											}
											zookMonitorList.add(zookMonObj);
										}
									}
									mapResult.put(key, zookMonitorList);
								}
							}
						} else {
							/** 没有指定池 */
							List<ZookeeperMonitorObj> zookMonitorList = new ArrayList<ZookeeperMonitorObj>();
							logger.info("四级父路径：" + key);
							if (e.getValue() != null && e.getValue().size() >= 1) {
								logger.info("子节点个数：" + e.getValue().size());
								String sbff = "";
								// 迭代该父节点下的每一个五级节点
								for (StaticTreeLeafNode staicNode : e.getValue()) {
									ZookeeperMonitorObj zookMonObj = new ZookeeperMonitorObj();
									if (staicNode.getDynNodePathList() != null
											&& staicNode.getDynNodePathList().size() >= 1) {
										// 不为空，说明节点正常
										zookMonObj.setSiPath(key);
										zookMonObj.setNodeName(staicNode.getNodeName());
										zookMonObj.setFlag(true);// 该五级节点正常
										// 获取对应动态节点的属性
										for (StaticTreeLeafNode dnode : staicNode
												.getDynNodePathList()) {
											dataValue = dnode.getNodeData();
											String[] valueArr = dataValue.split(";");
											sbff = "动态节点名:" + dnode.getNodeName() + "; 主机IP："
													+ valueArr[2] + "; 端口号：" + valueArr[3]
													+ "; 进程PID：" + valueArr[4];
											zookMonObj.setDataValue(sbff);
										}
									} else {
										zookMonObj.setSiPath(key);
										zookMonObj.setNodeName(staicNode.getNodeName());
										zookMonObj.setFlag(false);
									}
									zookMonitorList.add(zookMonObj);
								}
							}
							mapResult.put(key, zookMonitorList);
						}
					}
				}
			} else {
				pp = "D";
				// 动态树监控
				// key 指动态节点
				Map<String, List<StaticTreeLeafNode>> map = monitor.monitorDynTree2();
				Set<Entry<String, List<StaticTreeLeafNode>>> entrySet = map.entrySet();
				// 通过迭代entrySet变通的迭代Map集合, Map不能直接迭代
				for (Entry<String, List<StaticTreeLeafNode>> e : entrySet) {
					String key = e.getKey();
					/** 指定集群 */
					if (key.contains(cluster_id)) {
						/** 是否指定池 */
						if (!"".equals(pool_name) && pool_name != null) {
							String[] arr = key.split("/");
							if (key.contains(pool_name) && "APP".equals(arr[4])) {
								List<ZookeeperMonitorObj> zookMonitorList = new ArrayList<ZookeeperMonitorObj>();
								logger.info("四级父路径：" + key);
								if (e.getValue() != null && e.getValue().size() >= 1) {
									logger.info("子节点个数：" + e.getValue().size());
									String sbff = "";
									// 迭代该父节点下的每一个五级节点
									for (StaticTreeLeafNode staicNode : e.getValue()) {
										ZookeeperMonitorObj zookMonObj = new ZookeeperMonitorObj();
										if (staicNode.getDynNodePathList() != null
												&& staicNode.getDynNodePathList().size() >= 1) {
											// 不为空，说明节点正常
											zookMonObj.setSiPath(key);
											zookMonObj.setNodeName(staicNode.getNodeName());
											zookMonObj.setFlag(true);// 该五级节点正常
											String nodeSelf = staicNode.getNodeData() == null ? ""
													: staicNode.getNodeData();
											String[] selfArr = nodeSelf.split(";");
											// 获取对应静态节点的属性
											for (StaticTreeLeafNode dnode : staicNode
													.getDynNodePathList()) {
												dataValue = dnode.getNodeData();
												String[] valueArr = dataValue.split(";");
												sbff = "主机IP：" + selfArr[2] + "; 进程二进制名："
														+ selfArr[5] + "; 进程启停标识：" + valueArr[1];
												zookMonObj.setDataValue(sbff);
											}
										} else {
											zookMonObj.setSiPath(key);
											zookMonObj.setNodeName(staicNode.getNodeName());
											zookMonObj.setFlag(false);
										}
										zookMonitorList.add(zookMonObj);
									}
								}
								mapResult.put(key, zookMonitorList);
							} else {
								if (pool_name.equals(arr[4])) {
									List<ZookeeperMonitorObj> zookMonitorList = new ArrayList<ZookeeperMonitorObj>();
									logger.info("四级父路径：" + key);
									if (e.getValue() != null && e.getValue().size() >= 1) {
										logger.info("子节点个数：" + e.getValue().size());
										String sbff = "";
										// 迭代该父节点下的每一个五级节点
										for (StaticTreeLeafNode staicNode : e.getValue()) {
											ZookeeperMonitorObj zookMonObj = new ZookeeperMonitorObj();
											if (staicNode.getDynNodePathList() != null
													&& staicNode.getDynNodePathList().size() >= 1) {
												// 不为空，说明节点正常
												zookMonObj.setSiPath(key);
												zookMonObj.setNodeName(staicNode.getNodeName());
												zookMonObj.setFlag(true);// 该五级节点正常
												String nodeSelf = staicNode.getNodeData() == null ? ""
														: staicNode.getNodeData();
												String[] selfArr = nodeSelf.split(";");
												// 获取对应静态节点的属性
												for (StaticTreeLeafNode dnode : staicNode
														.getDynNodePathList()) {
													dataValue = dnode.getNodeData();
													String[] valueArr = dataValue.split(";");
													sbff = "主机IP：" + selfArr[2] + "; 进程二进制名："
															+ selfArr[5] + "; 进程启停标识："
															+ valueArr[1];
													zookMonObj.setDataValue(sbff);
												}
											} else {
												zookMonObj.setSiPath(key);
												zookMonObj.setNodeName(staicNode.getNodeName());
												zookMonObj.setFlag(false);
											}
											zookMonitorList.add(zookMonObj);
										}
									}
									mapResult.put(key, zookMonitorList);
								}
							}
						} else {
							/** 没有指定池 */
							List<ZookeeperMonitorObj> zookMonitorList = new ArrayList<ZookeeperMonitorObj>();
							logger.info("四级父路径：" + key);
							if (e.getValue() != null && e.getValue().size() >= 1) {
								logger.info("子节点个数：" + e.getValue().size());
								String sbff = "";
								// 迭代该父节点下的每一个五级节点
								for (StaticTreeLeafNode staicNode : e.getValue()) {
									ZookeeperMonitorObj zookMonObj = new ZookeeperMonitorObj();
									if (staicNode.getDynNodePathList() != null
											&& staicNode.getDynNodePathList().size() >= 1) {
										// 不为空，说明节点正常
										zookMonObj.setSiPath(key);
										zookMonObj.setNodeName(staicNode.getNodeName());
										zookMonObj.setFlag(true);// 该五级节点正常
										String nodeSelf = staicNode.getNodeData() == null ? ""
												: staicNode.getNodeData();
										String[] selfArr = nodeSelf.split(";");
										// 获取对应静态节点的属性
										for (StaticTreeLeafNode dnode : staicNode
												.getDynNodePathList()) {
											dataValue = dnode.getNodeData();
											String[] valueArr = dataValue.split(";");
											sbff = "主机IP：" + selfArr[2] + "; 进程二进制名：" + selfArr[5]
													+ "; 进程启停标识：" + valueArr[1];
											zookMonObj.setDataValue(sbff);
										}
									} else {
										zookMonObj.setSiPath(key);
										zookMonObj.setNodeName(staicNode.getNodeName());
										zookMonObj.setFlag(false);
									}
									zookMonitorList.add(zookMonObj);
								}
							}
							mapResult.put(key, zookMonitorList);
						}
					}
				}
			}
		} catch (Exception e) {
			logger.error("监控过程出错！", e);
		}
		return "list";
	}
}
