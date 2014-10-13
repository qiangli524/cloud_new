package util;

public class HadoopConstant {
	// 节点类型
	public static final String root = "0";// 根节点
	public static final String hadoop_dc = "1";// 大数据中心
	public static final String hadoop_cluster = "2";// hadoop集群
	public static final String hbase_cluster = "3";// hbase集群
	public static final String hive = "4";// hive
	public static final String zookeeper = "5";
	public static final String impala = "6";
	public static final String storm = "7";
	public static final String hdfs = "8";// hdfs节点
	public static final String mapReduce = "9";// mapReduce节点
	public static final String serviceNode = "10";// 服务节点,包括namenode,dataNode等服务
	public static final String hostNode = "11";// 主机节点

	// 服务类型
	public static final String nameNode = "1";
	public static final String dataNode = "2";
	public static final String journalNode = "3";
	public static final String nodeManager = "4";
	public static final String reduceManager = "5";
	public static final String hmaster = "6";
	public static final String regionServer = "7";
	public static final String hbase_thirftServer = "8";
	public static final String znode = "9";
	public static final String hive_thirftServer = "10";
	public static final String impalaxx = "11";
	public static final String DFSzkFailoverController = "12";// 用于进行namenode主备机切换

	// KPI表中是否有效,图例,级别
	public static final String EFFECTIVE = "有效";
	public static final String INVALID = "无效";
	public static final String LINEGRAPH = "线图";
	public static final String PLANEGRAPH = "面图";
	public static final String HIGHT = "高";
	public static final String MIDDLE = "中";
	public static final String LOW = "低";

	public static final String effective = "1";// 有效
	public static final String invalid = "2";// 无效
	public static final String linegraph = "1";// 线图
	public static final String planegraph = "2";// 面图
	public static final String hight = "2";// 级别 高
	public static final String middle = "1";// 级别 中
	public static final String low = "0";// 级别 低

}
