package service.tree;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import util.HadoopConstant;
import util.HadoopExampleStatus;

import com.sitech.basd.component.dao.process.ProcessDao;
import com.sitech.basd.component.domain.process.ProcessObj;
import com.sitech.basd.util.PropertyUtil;
import com.sitech.utils.properties.PropertiesUtil;
import com.sitech.utils.randomid.RandomUUID;
import com.thoughtworks.xstream.XStream;

import dao.cluster.HadoopClusterDao;
import dao.host.HadoopHostInfoDao;
import dao.service.HadoopServiceNodeDao;
import dao.tree.HadoopTreeDao;
import domain.cluster.HadoopClusterObj;
import domain.host.HadoopHostInfoObj;
import domain.service.HadoopServiceNodeObj;
import domain.service.ServiceObj;
import domain.tree.Edge;
import domain.tree.HadoopTreeObj;

@Service("hadoopTreeService")
public class HadoopTreeServiceImpl implements HadoopTreeService {
	private static Logger logger = Logger.getLogger(HadoopTreeService.class);
	@Autowired
	private HadoopTreeDao hadoopTreeDao;
	@Autowired
	private PropertyUtil unitedTreeIconProp;
	@Autowired
	private HadoopServiceNodeDao hadoopServiceNodeDao;
	@Autowired
	private HadoopClusterDao hadoopClusterDao;
	@Autowired
	private HadoopHostInfoDao hadoopHostInfoDao;
	@Autowired
	private ProcessDao processDao;

	/**
	 * 
	 * @Title: queryForTree
	 * @Description: 查询树
	 * @param
	 * @return List
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime 2014-1-4 下午8:56:56
	 */
	@Override
	public List queryForTree(HadoopTreeObj obj) {
		List<HadoopTreeObj> list = new ArrayList<HadoopTreeObj>();
		String id = obj.getId();
		HadoopTreeObj ha = new HadoopTreeObj();
		if (id == null || "".equals(id)) {
			ha.setNode_type(HadoopConstant.root);
		} else {
			ha.setParent_id(id);
		}
		List<HadoopTreeObj> resultList = hadoopTreeDao.queryForListByObj(ha);
		resultList = this.setStatus(resultList);
		for (HadoopTreeObj u : resultList) {
			// 判断是不是父节点
			HadoopTreeObj tree = new HadoopTreeObj();
			tree.setParent_id(u.getId());
			List<HadoopTreeObj> lst = hadoopTreeDao.queryForListByObj(tree);
			if (lst == null || lst.size() == 0) {
				u.setIsParent(false);
			}

			// 设置图标 update by lipengpeng
			String type = u.getNode_type();
			if (HadoopConstant.root.equals(type)) {
				u.setIcon(unitedTreeIconProp.getString("anhui"));
				u.setTitle("根节点");
			} else if (HadoopConstant.hadoop_dc.equals(type)) {
				u.setIcon(unitedTreeIconProp.getString("datacenter"));
				u.setTitle("数据中心");
			} else if (HadoopConstant.serviceNode.equals(type)) {
				u.setIcon(unitedTreeIconProp.getString("servicenode"));
				u.setTitle("服务节点");
			} else if (HadoopConstant.hostNode.equals(type)) {
				// 服务实例节点需要判断服务是否正常
				if (HadoopExampleStatus.NORMAL.equals(u.getStatus())) {
					u.setIcon(unitedTreeIconProp.getString("hostnode"));
					u.setTitle("运行正常");
				} else if (HadoopExampleStatus.SERIOUS.equals(u.getStatus())) {
					u.setIcon(unitedTreeIconProp.getString("vm_noexist"));
					/** 查询节点下不正常的进程,如果存在则拼接不正常信息  add by qism @20170702*/
					List<ProcessObj> processObj = processDao.querySeriousHadoopProcess(u);
					if(processObj != null){
						logger.info("异常进程的个数是：["+processObj.size()+"]个");
						String message="";
						for(ProcessObj pro : processObj){
							switch(pro.getPROCESS_STATE()){
								case 0: {message=message+pro.getPROCESS()+":无运行状态"+"\n";}
									break;
					            case 1: {message=message+pro.getPROCESS()+":异常停止 "+"\n";}
					                break;
					            case 3: {message=message+pro.getPROCESS()+":启动失败 "+"\n";}
					                break;
					            case 4: {message=message+pro.getPROCESS()+":停止失败 "+"\n";}
				                	break;
					            case 6: {message=message+pro.getPROCESS()+":实际个数与配置个数不符 "+"\n";}
			                		break;
					            case 7: {message=message+pro.getPROCESS()+":服务器无法ping通 "+"\n";}
			                		break;
					            case 8: {message=message+pro.getPROCESS()+":服务器登录失败 "+"\n";}
			                		break;
					            default:
					                break;
							}
						}
						if(message != "" && message != null){
							u.setTitle(message);
						}else{
							u.setTitle("运行正常");
						}
					}else{
						logger.info("无异常进程");
					}
				}
			} else {
				u.setIcon(unitedTreeIconProp.getString("cluster"));
				u.setTitle("集群");
			}
			list.add(u);
		}
		return list;
	}

	/**
	 * @Title: setStatus
	 * @Description: 设置节点状态
	 * @param
	 * @return List<HadoopTreeObj>
	 * @throws
	 * @author lipp
	 * @version 1.0
	 * @createtime 2014-2-26 下午3:45:40
	 */
	public List<HadoopTreeObj> setStatus(List<HadoopTreeObj> resultList) {
		List<String> uuidList = new ArrayList<String>();
		for (HadoopTreeObj hadoopTreeObj : resultList) {
			hadoopTreeObj.setStatus(HadoopExampleStatus.NORMAL);
			if (HadoopConstant.hostNode.equals(hadoopTreeObj.getNode_type())) {// 是服务实例
				uuidList.add(hadoopTreeObj.getId());
			}
		}

		ProcessObj processObj = new ProcessObj();
		processObj.setNodeIdList(uuidList);
		List<ProcessObj> processList = processDao
				.queryHadoopServiceNodeStatus(processObj);
		w : for (HadoopTreeObj hadoopTreeObj : resultList) {
			for (ProcessObj pObj : processList) {
				if (hadoopTreeObj.getId().equals(pObj.getENTITY_ID())) {
					if (pObj.getAllcount() != pObj.getNormalcount()) {
						hadoopTreeObj.setStatus(HadoopExampleStatus.SERIOUS);
					}
					continue w;
				}
			}
		}
		return resultList;
	}

	/**
	 * @Title: insertByObj
	 * @Description: 插入一条记录
	 * @param
	 * @return int
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2014-1-5 下午2:07:36
	 */
	@Override
	public int insertByObj(HadoopTreeObj hadoopTreeObj) {
		int ret = 0;
		String uuid = null;
		if (HadoopConstant.serviceNode.equals(hadoopTreeObj.getNode_type())) {// 如果是服务节点，需要插入服务表
			uuid = RandomUUID.getUuid();
			HadoopServiceNodeObj hadoopServiceNodeObj = new HadoopServiceNodeObj();
			hadoopServiceNodeObj.setId(uuid);
			hadoopServiceNodeObj.setService_node_name(hadoopTreeObj.getName());
			hadoopServiceNodeObj.setService_type(hadoopTreeObj
					.getService_type());
			hadoopServiceNodeDao.insertByObj(hadoopServiceNodeObj);
			hadoopTreeObj.setUuid(uuid);
			hadoopTreeDao.insertByObj(hadoopTreeObj);
		} else if (HadoopConstant.hostNode.equals(hadoopTreeObj.getNode_type())) {// 主机节点插入主机表中
			String[] hostInfoId = hadoopTreeObj.getHostId().split(",");
			String[] nodeName = hadoopTreeObj.getName().split(",");
			for (int i = 0; i < hostInfoId.length; i++) {
				HadoopHostInfoObj hostObj = new HadoopHostInfoObj();
				String clusterName = null;
				try {
					clusterName = acquireClusterName(hadoopTreeObj, 0);
				} catch (Exception e) {
					e.printStackTrace();
				}
				hostObj.setCluster_id(clusterName);
				hostObj.setId(hostInfoId[i]);
				// hostObj.setService(hadoopTreeObj.getParent_serviceType());
				hadoopTreeObj.setUuid(hostInfoId[i]);
				hadoopTreeObj.setName(nodeName[i]);
				hadoopTreeObj.setService_type(hadoopTreeObj
						.getParent_serviceType());
				hadoopHostInfoDao.updateByObj(hostObj);
				hadoopTreeDao.insertByObj(hadoopTreeObj);
			}
		} else if (HadoopConstant.hdfs.equals(hadoopTreeObj.getNode_type())
				|| HadoopConstant.mapReduce
						.equals(hadoopTreeObj.getNode_type())
				|| HadoopConstant.hadoop_dc
						.equals(hadoopTreeObj.getNode_type())) {// mapReduce和hdfs节点需要把插入到集群表中
			HadoopClusterObj clusterObj = new HadoopClusterObj();
			uuid = RandomUUID.getUuid();
			hadoopTreeObj.setUuid(uuid);
			clusterObj.setId(uuid);
			clusterObj.setCluster_name("");
			clusterObj.setCluster_id(hadoopTreeObj.getCluster_id());
			hadoopClusterDao.insertByObj(clusterObj);// 插入集群表中
			hadoopTreeDao.insertByObj(hadoopTreeObj);
		} else {
			hadoopTreeObj.setUuid(uuid);
			hadoopTreeDao.insertByObj(hadoopTreeObj);
		}
		return ret;
	}

	/**
	 * @Title: acquireClusterName
	 * @Description: 通过服务节点获取集群名称
	 * @param
	 * @return String
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @throws Exception
	 * @createtime 2014-1-9 下午3:49:30
	 */
	private String acquireClusterName(HadoopTreeObj hadoopTreeObj, int count)
			throws Exception {
		HadoopTreeObj treeObj = new HadoopTreeObj();
		count = count + 1;
		try {
			treeObj.setId(hadoopTreeObj.getParent_id());
			treeObj = (HadoopTreeObj) (hadoopTreeDao.queryForListByObj(treeObj)
					.get(0));
			if (HadoopConstant.hadoop_dc.equals(treeObj.getNode_type())) {
				return treeObj.getUuid();
			} else {
				if (count >= 6) {// 不能进入死循环，一定次数后强制终止
					return null;
				}
				return acquireClusterName(treeObj, count);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("递归获取集群名称出错，错误原因：", e);
		}
	}

	/**
	 * @Title: updateByObj
	 * @Description: 更新一条记录
	 * @param
	 * @return int
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2014-1-5 下午2:07:59
	 */
	@Override
	public int updateByObj(HadoopTreeObj hadoopTreeObj) {
		return hadoopTreeDao.updateByObj(hadoopTreeObj);
	}

	/**
	 * @Title: deleteByObj
	 * @Description: 删除一条记录
	 * @param
	 * @return int
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2014-1-5 下午2:08:14
	 */
	@Override
	public int deleteByObj(HadoopTreeObj hadoopTreeObj) {
		return hadoopTreeDao.deleteByObj(hadoopTreeObj);
	}

	/**
	 * 
	 * @Title: recursiveTree
	 * @Description: 用于生成拓扑图，返回特殊obj
	 * @param
	 * @return List
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime 2014-1-7 上午8:38:43
	 */

	@Override
	public HadoopTreeObj recursiveTree(String parentId,
			HadoopTreeDao hadoopTreeDao) throws SQLException {
		HadoopTreeObj node = new HadoopTreeObj();

		node.setId(parentId);
		HadoopTreeObj nodeResult = (HadoopTreeObj) hadoopTreeDao
				.queryForListByObj(node).get(0);
		HadoopTreeObj childNode = new HadoopTreeObj();
		childNode.setParent_id(parentId);
		List<HadoopTreeObj> childTreeNodes = hadoopTreeDao
				.queryForListByObj(childNode);
		// 遍历子节点
		for (HadoopTreeObj treeNode : childTreeNodes) {
			HadoopTreeObj n = recursiveTree(treeNode.getId(), hadoopTreeDao); // 递归
			if (nodeResult.getNodeList() != null) {
				nodeResult.getNodeList().add(n);
			} else {
				nodeResult.setNodeList(new ArrayList<HadoopTreeObj>());
				nodeResult.getNodeList().add(n);
			}

		}
		return nodeResult;
	}

	/**
	 * @Title: queryForListByObj
	 * @Description: 根据条件查询集合
	 * @param
	 * @return List<HadoopTreeObj>
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2014-1-7 下午9:29:51
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<HadoopTreeObj> queryForListByObj(HadoopTreeObj hadoopTreeObj) {
		List<HadoopTreeObj> list = new ArrayList<HadoopTreeObj>();
		list = hadoopTreeDao.queryForListByObj(hadoopTreeObj);
		for (HadoopTreeObj obj : list) {
			if (obj.getService_type() != null && !"".equals(obj.getService_type())) {
				obj.setService_name(PropertiesUtil.getString(
						"properties/hadoopServiceType", obj.getService_type()));
			}
		}
		return list;
	}

	/**
	 * @Title: getExpandNodes
	 * @Description: 查询扩展节点，获取节点id
	 * @param
	 * @return List<String>
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @createtime 2014-1-7 下午9:29:51
	 */
	public List<String> getExpandNodes(HadoopTreeObj hadoopTreeObj) {
		String ids = "";
		// 首先找到目标节点的id
		HadoopTreeObj obj = getExpandNodesId(hadoopTreeObj);
		while (true) {
			if (obj != null && !"1".equals(obj.getId())) {
				if ("1".equals(obj.getParent_id())) {// 判断是下一个父节点是否为根节点
					ids = ids + obj.getId();
				} else {
					ids = ids + obj.getId() + ",";
				}
				HadoopTreeObj obj1 = new HadoopTreeObj();
				obj1.setId(obj.getParent_id());
				obj = getExpandNodesId(obj1);
			} else {
				break;
			}
		}
		List<String> idsList = new ArrayList<String>();
		idsList.add(ids);
		return idsList;
	}

	/**
	 * @Title: getExpandNodes
	 * @Description: 查询扩展节点，获取节点id
	 * @param
	 * @return List<String>
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @createtime 2014-1-7 下午9:29:51
	 */
	private HadoopTreeObj getExpandNodesId(HadoopTreeObj obj) {
		List<HadoopTreeObj> list = new ArrayList<HadoopTreeObj>();
		list = hadoopTreeDao.queryForListByObj(obj);
		if (list != null && list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}

	/**
	 * @Title: queryForServiceList
	 * @Description: 查询服务集合
	 * @param
	 * @return List<ServiceNodeObj>
	 * @throws
	 * @author lipp
	 * @version 1.0
	 * @createtime 2014-2-20 下午4:08:59
	 */
	@Override
	public List<ServiceObj> queryForServiceList(HadoopTreeObj treeObj) {
		return hadoopTreeDao.queryForServiceList(treeObj);
	}

	/**
	 * @Title: acquireChildNode
	 * @Description: 递归获取某种类型的子节点集合
	 * @param
	 * @return List<HadoopTreeObj>
	 * @throws
	 * @author lipp
	 * @version 1.0
	 * @createtime 2014-2-26 下午7:21:40
	 */
	public List<HadoopTreeObj> acquireChildNode(String childNodeType,
			String parentNodeServiceType, HadoopTreeObj hadoopTreeObj,
			List<HadoopTreeObj> retList) {
		try {
			HadoopTreeObj treeObj = new HadoopTreeObj();
			treeObj.setParent_id(hadoopTreeObj.getId());
			@SuppressWarnings("unchecked")
			List<HadoopTreeObj> treeList = hadoopTreeDao
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
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retList;
	}
	
	/**
	 * 
	 * @Title: queryTopoData
	 * @Description: 获取拓扑图数据
	 * @param
	 * @return String
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime Apr 23, 2014 4:14:16 PM
	 */
	public String queryTopoData(String currentNodeId,String showType){
		HadoopTreeObj treeObj = new HadoopTreeObj();
		List<HadoopTreeObj> lst = hadoopTreeDao.queryForListByObj(treeObj);
		lst = this.setStatus(lst);
		Map<String, List<HadoopTreeObj>> map = analysisData(lst);
		StringBuffer sb = new StringBuffer();
		sb.append("<Graph>");
		XStream xStream = initXStream();
		getCurrentNodeData(currentNodeId, map, sb, xStream,showType);
		constructeData(map,currentNodeId,sb,xStream,showType);
		sb.append("</Graph>");
		return sb.toString();
	}
	
	/**
	 * 
	 * @Title: getCurrentNodeData
	 * @Description: 获取当前节点数据
	 * @param
	 * @return void
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime May 5, 2014 4:13:47 PM
	 */
	public void getCurrentNodeData(String currentNodeId,Map<String, List<HadoopTreeObj>> map,StringBuffer sb,XStream xstream,String showType){
		HadoopTreeObj treeObj = new HadoopTreeObj();
		treeObj.setParent_id(currentNodeId);
		treeObj = hadoopTreeDao.queryByObj(treeObj);
		String parentId = "";
		if(treeObj.getParent_id()==null){
			parentId = "0";
		}else{
			parentId = treeObj.getParent_id();
		}
		treeObj = constructeHadoopXml(treeObj, parentId, sb);
        String s = xstream.toXML(treeObj);
        sb.append(s);
       
        List<HadoopTreeObj> sonList = map.get(currentNodeId);
       
        if(sonList!=null && sonList.size()>0){
    	    for (HadoopTreeObj hadoopTreeObj2 : sonList) {
    		   Edge edge = null;
    		   if(showType.equals("all")){
    			   edge = constructeEdgeXml(treeObj, hadoopTreeObj2);
    			   String se = xstream.toXML(edge);
        		   sb.append("\n");
        		   sb.append(se);
    		   }else{
    			   if(HadoopConstant.hostNode.equals(hadoopTreeObj2.getNode_type()) || hadoopTreeObj2.getNode_type().equals("host")){
    				   if(HadoopExampleStatus.SERIOUS.equals(hadoopTreeObj2.getStatus())){
    					   edge = constructeEdgeXml(treeObj, hadoopTreeObj2);
            			   String se = xstream.toXML(edge);
                		   sb.append("\n");
                		   sb.append(se);
    				   }
        		   }else{
        			   edge = constructeEdgeXml(treeObj, hadoopTreeObj2);
        			   String se = xstream.toXML(edge);
            		   sb.append("\n");
            		   sb.append(se);
        		   }
    		   } 
    	    }
        }
	}
	
	/**
	 * 
	 * @Title: analysisData
	 * @Description: 解析拓扑图数据
	 * @param
	 * @return Map<String,List<HadoopTreeObj>>
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime Apr 23, 2014 4:14:33 PM
	 */
	private Map<String, List<HadoopTreeObj>> analysisData(List<HadoopTreeObj> lst){
		Map<String,List<HadoopTreeObj>> map = new HashMap<String, List<HadoopTreeObj>>();
		List<HadoopTreeObj> list = null;
		for (HadoopTreeObj hadoopTreeObj : lst) {
			String parentId = hadoopTreeObj.getParent_id()==null?"0":hadoopTreeObj.getParent_id();
			if(map.get(parentId)==null){
				list = new ArrayList<HadoopTreeObj>();
				list.add(hadoopTreeObj);
				map.put(parentId, list);
			}else{
				list = map.get(parentId);
				list.add(hadoopTreeObj);
			}
		}
		return map;
	}
	
	/**
	 * 
	 * @Title: initXStream
	 * @Description: 初始化XStream对象
	 * @param
	 * @return XStream
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime May 5, 2014 3:55:12 PM
	 */
	public XStream initXStream(){
		XStream xstream = new XStream();
		//修改元素名称
		xstream.alias("Node", HadoopTreeObj.class);
        xstream.autodetectAnnotations(true);
        
        xstream.useAttributeFor(HadoopTreeObj.class, "id");
        xstream.useAttributeFor(HadoopTreeObj.class, "node_type");
        xstream.useAttributeFor(HadoopTreeObj.class, "name");
        //修改属性的name
        xstream.aliasAttribute("nodeIcon", "node_type");
	        
        xstream.alias("Edge", Edge.class);
		xstream.useAttributeFor(Edge.class, "fromID");
	    xstream.useAttributeFor(Edge.class, "toID");
	    xstream.useAttributeFor(Edge.class, "color");    
		return xstream;
	}
	
	/**
	 * 
	 * @Title: constructeData
	 * @Description: 构造图谱图数据
	 * @param
	 * @return void
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime Apr 23, 2014 4:28:38 PM
	 */
	private void constructeData(Map<String, List<HadoopTreeObj>> map,String parentId,StringBuffer sb,XStream xstream,String showType){
		List<HadoopTreeObj> list = map.get(parentId);
		if(list!=null && list.size()>0){
			for (HadoopTreeObj hadoopTreeObj : list) {
				String id = hadoopTreeObj.getId();
				List<HadoopTreeObj> sonList = map.get(id);
				try {        
				   hadoopTreeObj = constructeHadoopXml(hadoopTreeObj, parentId, sb);
			       String s = xstream.toXML(hadoopTreeObj);
			       sb.append(s);
			       if(sonList!=null && sonList.size()>0){
			    	   for (HadoopTreeObj hadoopTreeObj2 : sonList) {
			    		   Edge edge = null;
			    		   if(showType.equals("all")){
			    			   edge = constructeEdgeXml(hadoopTreeObj, hadoopTreeObj2);
			    			   String se = xstream.toXML(edge);
			        		   sb.append("\n");
			        		   sb.append(se);
			    		   }else{
			    			   if(HadoopConstant.hostNode.equals(hadoopTreeObj2.getNode_type()) || hadoopTreeObj2.getNode_type().equals("host")){
			    				   if(HadoopExampleStatus.SERIOUS.equals(hadoopTreeObj2.getStatus())){
			    					   edge = constructeEdgeXml(hadoopTreeObj, hadoopTreeObj2);
			            			   String se = xstream.toXML(edge);
			                		   sb.append("\n");
			                		   sb.append(se);
			    				   }
			        		   }else{
			        			   edge = constructeEdgeXml(hadoopTreeObj, hadoopTreeObj2);
			        			   String se = xstream.toXML(edge);
			            		   sb.append("\n");
			            		   sb.append(se);
			        		   }
			    		   }
			    		   constructeData(map,id,sb,xstream,showType);
			    	   }
			       }
			    } catch (Exception e) {
			        e.printStackTrace();
			    }
			}
		}
		map.remove(parentId);
	}
	
	/**
	 * 
	 * @Title: constructeHadoopXml
	 * @Description: 将hadoopTreeObj转化为xml 
	 * @param
	 * @return HadoopTreeObj
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime May 5, 2014 4:07:53 PM
	 */
	public HadoopTreeObj constructeHadoopXml(HadoopTreeObj hadoopTreeObj,String parentId,StringBuffer sb){
		 int nodeType = Integer.parseInt( hadoopTreeObj.getNode_type());
	        switch (nodeType) {
			case 0:
				hadoopTreeObj.setNode_type("cloud");
				break;
			case 1:
				hadoopTreeObj.setNode_type("dc");
				break;
			case 2:
				hadoopTreeObj.setNode_type("hadoop-cluster");
				break;
			case 3:
				hadoopTreeObj.setNode_type("hbase-cluster");
				break;
			case 4:
				hadoopTreeObj.setNode_type("hive-cluster");
				break;
			case 5:
				hadoopTreeObj.setNode_type("zookeeper-cluster");
				break;
			case 6:
				hadoopTreeObj.setNode_type("impala-cluster");
				break;
			case 7:
				hadoopTreeObj.setNode_type("storm-cluster");
				break;
			case 8:
				hadoopTreeObj.setNode_type("hdfs");
				break;
			case 9:
				hadoopTreeObj.setNode_type("mapreduce");
				break;
			case 10:
				hadoopTreeObj.setNode_type("service");
				break;
			case 11:
				hadoopTreeObj.setNode_type("host");
				break;

			default:
				hadoopTreeObj.setNode_type("bad");
				break;
			}
	        
	        StringBuffer bf = new StringBuffer();
	        if(!parentId.equals("0")){
	    	   sb.append("\n");
	       }else{
	    	   hadoopTreeObj.setId("1");
	       }
	       return hadoopTreeObj;
	}
	
	/**
	 * 
	 * @Title: constructeEdgeXml
	 * @Description: 将Edge转化为xml
	 * @param
	 * @return Edge
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime May 5, 2014 4:08:37 PM
	 */
	public Edge constructeEdgeXml(HadoopTreeObj hadoopTreeObj,HadoopTreeObj hadoopTreeObj2){
		 Edge edge = new Edge();
		 edge.setFromID(hadoopTreeObj.getId());
		 edge.setToID(hadoopTreeObj2.getId());
		   
		 if (HadoopConstant.hostNode.equals(hadoopTreeObj2.getNode_type()) || hadoopTreeObj2.getNode_type().equals("host")) {
			// 服务实例节点需要判断服务是否正常
 			if (HadoopExampleStatus.NORMAL.equals(hadoopTreeObj2.getStatus())) {
 				edge.setColor("0x008B45");
 			} else if (HadoopExampleStatus.SERIOUS.equals(hadoopTreeObj2.getStatus())) {
 				edge.setColor("0xFF0000");
 			}
		 }else{
			edge.setColor("0x008B45");
		 }
		 return edge;
	}
	

	/**
	 * 
	 * @Title: queryForHostNodeList
	 * @Description: 查询主机节点(用于服务类型是namenode的节点)
	 * @param
	 * @return List<HadoopTreeObj>
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-4-14 下午5:48:25
	 */
	@Override
	public List<HadoopTreeObj> queryForHostNodeList(HadoopTreeObj obj) {
		return hadoopTreeDao.queryForListByObj(obj);
	}

	/**
	 * 
	 * @Title: queryNodeType
	 * @Description: 查询节点类型判断资源池有哪些节点
	 * @param
	 * @return Map<String,String>
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-4-29 下午4:13:25
	 */
	public Map<String, String> queryNodeType(HadoopTreeObj obj) {
		Map<String, String> map = new HashMap<String, String>();
		HadoopTreeObj treeObj = new HadoopTreeObj();
		treeObj.setParent_id(obj.getId());
		List<HadoopTreeObj> list = hadoopTreeDao.queryForListByObj(treeObj);
		if (list != null && list.size() > 0) {
			for (HadoopTreeObj hadObj : list) {
				if (hadObj.getNode_type().equals(HadoopConstant.hadoop_cluster)) {
					map.put("hadoop", hadObj.getNode_type());
				} else if (hadObj.getNode_type().equals(
						HadoopConstant.hbase_cluster)) {
					map.put("hbase", hadObj.getNode_type());
				} else if (hadObj.getNode_type().equals(HadoopConstant.hive)) {
					map.put("hive", hadObj.getNode_type());
				} else if (hadObj.getNode_type().equals(
						HadoopConstant.zookeeper)) {
					map.put("zookeeper", hadObj.getNode_type());
				} else if (hadObj.getNode_type().equals(HadoopConstant.impala)) {
					map.put("impala", hadObj.getNode_type());
				} else if (hadObj.getNode_type().equals(HadoopConstant.storm)) {
					map.put("storm", hadObj.getNode_type());
				} else if (hadObj.getNode_type().equals(HadoopConstant.hdfs)) {
					map.put("hdfs", hadObj.getNode_type());
				} else if (hadObj.getNode_type().equals(
						HadoopConstant.mapReduce)) {
					map.put("mapReduce", hadObj.getNode_type());
				} else if (hadObj.getNode_type().equals(HadoopConstant.serviceNode)) {
					if (hadObj.getService_type().equals(HadoopConstant.znode)) {
						map.put("znode", hadObj.getService_type());
					} else if (hadObj.getService_type().equals(
							HadoopConstant.hive_thirftServer)) {
						map.put("hive_thirftServer", hadObj.getService_type());
					} else if (hadObj.getService_type().equals(
							HadoopConstant.impalaxx)) {
						map.put("impalaxx", hadObj.getService_type());
					}
				}
			}
		}
		return map;
	}
}
