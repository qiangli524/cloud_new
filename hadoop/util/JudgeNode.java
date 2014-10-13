package util;

/**
 * <p>Title: JudgeNode</p>
 * <p>Description: 判断节点类型、服务类型</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: SI-TECH </p>
 * @author lipengpeng
 * @version 1.0
 * @createtime 2014-1-7 下午7:45:49
 *
 */
public class JudgeNode {

	/**
	 * @Title: hadoopClusterChildNode
	 * @Description: 判断是否是hadoop集群的子节点，是则返回true，否则false
	 * @param
	 * @return boolean
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2014-1-7 下午7:47:15
	 */
	public static boolean hadoopClusterChildNode(String node_type){
		boolean flag = false;
		if (HadoopConstant.mapReduce.equals(node_type)) {
			flag = true;
		} else if (HadoopConstant.hdfs.equals(node_type)) {
			flag = true;
		}
		return flag;
	}
	
	/**
	 * @Title: hadoopClusterGrandChildNode
	 * @Description: 判断是否是hadoop集群下的服务节点，是则返回true，否则false
	 * @param
	 * @return boolean
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2014-1-7 下午7:49:14
	 */
	public static boolean hadoopClusterGrandChildNode(String node_type,String service_type){
		boolean flag = false;
		if (HadoopConstant.serviceNode.equals(node_type)) {
			if (hadoopClusterGrandChildNode(service_type)) {
				flag = true;
			}
		}
		return  flag;
	}
	
	/**
	 * @Title: hadoopClusterGrandChildNode
	 * @Description: 判断是否是hadoop集群下的服务节点，是则返回true，否则false
	 * @param
	 * @return boolean
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2014-1-7 下午7:49:14
	 */
	public static boolean hadoopClusterGrandChildNode(String service_type) {
		boolean flag = false;
		if (HadoopConstant.nameNode.equals(service_type)) {
			flag = true;
		} else if (HadoopConstant.dataNode.equals(service_type)) {
			flag = true;
		} else if (HadoopConstant.journalNode.equals(service_type)) {
			flag = true;
		} else if (HadoopConstant.nodeManager.equals(service_type)) {
			flag = true;
		} else if (HadoopConstant.reduceManager.equals(service_type)) {
			flag = true;
		}
		return flag;
	}
}
