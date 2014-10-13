package domain.statistics;




public class StatisticsObj {
	
	private int centerCount;// 数据中心个数
	private int hadoopClusterCount;// hadoop集群个数
	private int hbaseClusterCount;// hbase集群个数
	private int hiveClusterCount;// hive集群个数
	private int zookeeperClusterCount;// zookeeper集群个数
	private int impalaClusterCount;// impala集群个数
	
	private int nameNodeCount;// nameNode服务个数
	private int nameNodeNormalCount;//
	private int nameNodeSeriousCount;//
	private int datanodeCount;// datanode总数
	private int datanodeNormalCount;
	private int datanodeSeriousCount;
	private int journalnodeCount;// journalnode总数
	private int journalnodeNormalCount;
	private int journalnodeSeriousCount;
	private int regionServerCount;// regionServer的个数
	private int regionServerNormalCount;
	private int regionServerSeriousCount;
	private int nodeManagerCount;// nodemanager数量
	private int nodeManagerNormalCount;
	private int nodeManagerSeriousCount;
	private int reduceManagerCount;// reduceManager数量
	private int reduceManagerNormalCount;
	private int reduceManagerSeriousCount;
	private int hmasterCount;// hmaster数量
	private int hmasterNormalCount;//
	private int hmasterSeriousCount;
	private int hbaseThirftServerCount;// hbase_thirftServer数量
	private int hbaseThirftServerNormalCount;
	private int hbaseThirftServerSeriousCount;
	private int hiveThirftServerCount;// hive_thirftServer数量
	private int hiveThirftServerNormalCount;
	private int hiveThirftServerSeriousCount;
	private int zookeeperServerCount;// zookeeper数量
	private int zookeeperServerNormalCount;
	private int zookeeperServerSeriousCount;
	private int impalaServerCount;// impala数量
	private int impalaServerNormalCount;
	private int impalaServerSeriousCount;
	
	private int jobsSubmittedCount;// 已提交的mr作业数
	private int jobsCompletedCount;// 已完成的mr作业数
	private int jobsFailedCount;// 失败的mr作业数
	private int jobsKilledCount;// kill的mr作业数
	private int jobsPreparingCount;// 准备提交的mr作业数
	private int jobsRunningCount;// 运行的mr作业数
	private int mapsLaunchedCount;// 启动的map任务数
	private int mapsCompletedCount;// 完成的map任务数
	private int mapsFailedCount;// 失败的map任务数
	private int mapsKilledCount;// kill的map任务数
	private int mapsRunningCount; // 运行的map任务数
	private int mapsWaitingCount;// 等待的map任务数
	private int reducesLaunchedCount; // 启动的reduce任务数
	private int reducesCompletedCount; // 完成的reduce任务数
	private int reducesFailedCount;// 失败的reduce任务数
	private int reducesKilledCount;// kill的reduce任务数
	private int reducesRunningCount;// 运行的reduce任务数
	private int reducesWaitingCount;// 等待的reduce任务数
	
	private double blockTime;// 汇报block的平均时间
	private int blockCount;// block个数
	private double blockWritten;// 写入块的个数
	private double blockWrittenAvgTime;// 写数据块的平均时间
	private double blockReadAvgTime;// 读数据块的平均时间
	private double blockCapacity;// 的总容量
	private double blockCurrentCapacity;// block的当前容量
	
	private double fileCreateTimes;// 创建文件次数/秒
	private int fileCount;// 文件数量
	private int filesCreated;// 已创建的文件个数
	private int filesDeleted;// 已删除的文件个数
	private int filesRenamed;// 重命名文件个数
	
	private double totalCapacity;// 文件系统总容量
	private double totalUsedCapacity;// 总使用量
	private double totalUsage;// 总使用率
	private double fileSystemUsedCapacity;// 文件系统已用容量
	private double fileSystemLeftCapacity;// HDFS文件系统剩余的容量
	private double fileSystemUsage;// DFS使用率
	private double fileSystemLeftage;// DFS剩余率
	private double nonDfsUsedCapacity;// 非dfs使用量
	private double nonDfsUsage;// 非dfs使用率
	
	private double fileSystemFlushTime;// 文件系统flush平均时间
	private double fileSystemFlushTimes;// 文件系统flush次数
	private String lastCheckpointTime;// 最近一次做checkpoint的时间　
	private String lastWrittenTransactionId;// 最近一次写入的transactionid　
	private String millisSinceLastLoadedEdits;// 距离上一次加载edit的时间　
	
	
	private int regionSplitFailureCount;// region split失败的次数 高
	private int regionSplitSuccessCount;// region split成功的次数 高
	private int requests;// 请求的数量 高
	private int cluster_requests;// 集群request的个数 中
	private double splitSize_avg_time;// 平均每次执行splitlog的大小 中
	private double splitSize_num_ops;// 执行splitlog次数 中
	private double splitTime_avg_time;// 平均每次执行splitlog的时间 中
	private double splitTime_num_ops;// 执行splitlog的次数 中
	
	private int blockCacheHitCount;// blockCache命中次数 中
	private double blockCacheHitRatioMax;// blockCache命中比例 中
	private double blockCacheHitRatioMin;// blockCache命中比例 中
	private double blockCacheSize;// blockCache已使用大小 中
	private double blockCacheFree;// blockCache剩余大小
	private int blockCacheCount;// RegionServer中缓存到blockcache中block的个数
	
	
	private int compactionQueueSize;// compaction操作的Queue的大小 中
	private double compactionTime_num_ops;// 执行compaction的次数 中
	private int flushQueueSize;//
	private int hlogFileCount;// hlog file的个数 中
	private double memstoreSizeMB;// RegionServer中所有HRegion中的memstore大小的总和 中
	private int regionsCount;// region个数

	private double reservedMB;// 预留内存
	private double availabledMB;// 可用内存
	private double allocatedMB;// 允许的内存
	private double totalMB;// 总内存
	private double containersAllocated;//
	private double allocatedAge;// 分配率
	
	private double fsImageLoadTime;// ： fsimage加载时间 　　高
	private double blockReportNumOps; // ：block report的次数　高
	private double blockReportAvgTime;// ： block report的平均时间　高
	private double addBlockOps;// 写入block次数　　　　　　高

	
	private double flushNanosAvgTime;// 文件系统flush平均时间　　高
	private double flushNanosNumOps;// 文件系统flush次数　　　　高
	private double blockReportsAvgTime;// 向namenode汇报block的平均时间　　高　
	private double blockReportsNumOps;// 向namenode汇报block的次数　高
	private double heartbeatsAvgTime;// 向namenode汇报的平均时间　　　高
	private double heartbeatsNumOps;// 向namenode汇报的次数　　　　　高

	private int numActiveNMs;// 活的nodemanager数----------高
	private int numDecommissionedNMs;// 退役的nodemanage数----------高
	private int numLostNMs;// 丢失的nodemanager个数------------高
	private int numUnhealthyNMs;// 不健康的nodemanager数----------高
	private int numRebootedNMs;// 重启过的nodemanager数----------高

	private String clusterName;// 集群名字
	private int clusterCount;// 集群个数
	private int hostCount;// 主机个数

	private String hadoopType;// 用于节点展示
	private String hbaseType;
	private String hiveType;
	private String zookeeperType;
	private String impalaType;
	private String stormType;

	public int getClusterCount() {
		return clusterCount;
	}

	public void setClusterCount(int clusterCount) {
		this.clusterCount = clusterCount;
	}

	public int getHostCount() {
		return hostCount;
	}

	public void setHostCount(int hostCount) {
		this.hostCount = hostCount;
	}

	public String getClusterName() {
		return clusterName;
	}

	public void setClusterName(String clusterName) {
		this.clusterName = clusterName;
	}

	public int getHbaseThirftServerCount() {
		return hbaseThirftServerCount;
	}

	public int getBlockCacheCount() {
		return blockCacheCount;
	}

	public void setBlockCacheCount(int blockCacheCount) {
		this.blockCacheCount = blockCacheCount;
	}

	public void setHbaseThirftServerCount(int hbaseThirftServerCount) {
		this.hbaseThirftServerCount = hbaseThirftServerCount;
	}

	public int getHiveThirftServerCount() {
		return hiveThirftServerCount;
	}

	public void setHiveThirftServerCount(int hiveThirftServerCount) {
		this.hiveThirftServerCount = hiveThirftServerCount;
	}

	public int getHmasterCount() {
		return hmasterCount;
	}

	public void setHmasterCount(int hmasterCount) {
		this.hmasterCount = hmasterCount;
	}

	public int getCenterCount() {
		return centerCount;
	}

	public void setCenterCount(int centerCount) {
		this.centerCount = centerCount;
	}

	public int getHadoopClusterCount() {
		return hadoopClusterCount;
	}

	public void setHadoopClusterCount(int hadoopClusterCount) {
		this.hadoopClusterCount = hadoopClusterCount;
	}

	public int getHbaseClusterCount() {
		return hbaseClusterCount;
	}

	public void setHbaseClusterCount(int hbaseClusterCount) {
		this.hbaseClusterCount = hbaseClusterCount;
	}

	public int getHiveClusterCount() {
		return hiveClusterCount;
	}

	public void setHiveClusterCount(int hiveClusterCount) {
		this.hiveClusterCount = hiveClusterCount;
	}

	public int getZookeeperClusterCount() {
		return zookeeperClusterCount;
	}

	public void setZookeeperClusterCount(int zookeeperClusterCount) {
		this.zookeeperClusterCount = zookeeperClusterCount;
	}

	public int getImpalaClusterCount() {
		return impalaClusterCount;
	}

	public void setImpalaClusterCount(int impalaClusterCount) {
		this.impalaClusterCount = impalaClusterCount;
	}

	public int getDatanodeCount() {
		return datanodeCount;
	}

	public void setDatanodeCount(int datanodeCount) {
		this.datanodeCount = datanodeCount;
	}

	public int getJournalnodeCount() {
		return journalnodeCount;
	}

	public void setJournalnodeCount(int journalnodeCount) {
		this.journalnodeCount = journalnodeCount;
	}

	public int getFileCount() {
		return fileCount;
	}

	public void setFileCount(int fileCount) {
		this.fileCount = fileCount;
	}

	public int getBlockCount() {
		return blockCount;
	}

	public void setBlockCount(int blockCount) {
		this.blockCount = blockCount;
	}

	public int getRegionServerCount() {
		return regionServerCount;
	}

	public void setRegionServerCount(int regionServerCount) {
		this.regionServerCount = regionServerCount;
	}

	public int getNameNodeCount() {
		return nameNodeCount;
	}

	public void setNameNodeCount(int nameNodeCount) {
		this.nameNodeCount = nameNodeCount;
	}

	public int getNodeManagerCount() {
		return nodeManagerCount;
	}

	public void setNodeManagerCount(int nodeManagerCount) {
		this.nodeManagerCount = nodeManagerCount;
	}

	public int getReduceManagerCount() {
		return reduceManagerCount;
	}

	public void setReduceManagerCount(int reduceManagerCount) {
		this.reduceManagerCount = reduceManagerCount;
	}

	public double getFileCreateTimes() {
		return fileCreateTimes;
	}

	public void setFileCreateTimes(double fileCreateTimes) {
		this.fileCreateTimes = fileCreateTimes;
	}

	public double getBlockTime() {
		return blockTime;
	}

	public void setBlockTime(double blockTime) {
		this.blockTime = blockTime;
	}

	public double getFileSystemUsedCapacity() {
		return fileSystemUsedCapacity;
	}

	public void setFileSystemUsedCapacity(double fileSystemUsedCapacity) {
		this.fileSystemUsedCapacity = fileSystemUsedCapacity;
	}

	public double getFileSystemFlushTime() {
		return fileSystemFlushTime;
	}

	public void setFileSystemFlushTime(double fileSystemFlushTime) {
		this.fileSystemFlushTime = fileSystemFlushTime;
	}

	public double getFileSystemFlushTimes() {
		return fileSystemFlushTimes;
	}

	public void setFileSystemFlushTimes(double fileSystemFlushTimes) {
		this.fileSystemFlushTimes = fileSystemFlushTimes;
	}

	public double getBlockWritten() {
		return blockWritten;
	}

	public void setBlockWritten(double blockWritten) {
		this.blockWritten = blockWritten;
	}

	public double getBlockWrittenAvgTime() {
		return blockWrittenAvgTime;
	}

	public void setBlockWrittenAvgTime(double blockWrittenAvgTime) {
		this.blockWrittenAvgTime = blockWrittenAvgTime;
	}

	public double getBlockReadAvgTime() {
		return blockReadAvgTime;
	}

	public void setBlockReadAvgTime(double blockReadAvgTime) {
		this.blockReadAvgTime = blockReadAvgTime;
	}

	public double getBlockCapacity() {
		return blockCapacity;
	}

	public void setBlockCapacity(double blockCapacity) {
		this.blockCapacity = blockCapacity;
	}

	public double getBlockCurrentCapacity() {
		return blockCurrentCapacity;
	}

	public void setBlockCurrentCapacity(double blockCurrentCapacity) {
		this.blockCurrentCapacity = blockCurrentCapacity;
	}

	public int getJobsSubmittedCount() {
		return jobsSubmittedCount;
	}

	public void setJobsSubmittedCount(int jobsSubmittedCount) {
		this.jobsSubmittedCount = jobsSubmittedCount;
	}

	public int getJobsCompletedCount() {
		return jobsCompletedCount;
	}

	public void setJobsCompletedCount(int jobsCompletedCount) {
		this.jobsCompletedCount = jobsCompletedCount;
	}

	public int getJobsFailedCount() {
		return jobsFailedCount;
	}

	public void setJobsFailedCount(int jobsFailedCount) {
		this.jobsFailedCount = jobsFailedCount;
	}

	public int getJobsKilledCount() {
		return jobsKilledCount;
	}

	public void setJobsKilledCount(int jobsKilledCount) {
		this.jobsKilledCount = jobsKilledCount;
	}

	public int getJobsPreparingCount() {
		return jobsPreparingCount;
	}

	public void setJobsPreparingCount(int jobsPreparingCount) {
		this.jobsPreparingCount = jobsPreparingCount;
	}

	public int getJobsRunningCount() {
		return jobsRunningCount;
	}

	public void setJobsRunningCount(int jobsRunningCount) {
		this.jobsRunningCount = jobsRunningCount;
	}

	public int getMapsLaunchedCount() {
		return mapsLaunchedCount;
	}

	public void setMapsLaunchedCount(int mapsLaunchedCount) {
		this.mapsLaunchedCount = mapsLaunchedCount;
	}

	public int getMapsCompletedCount() {
		return mapsCompletedCount;
	}

	public void setMapsCompletedCount(int mapsCompletedCount) {
		this.mapsCompletedCount = mapsCompletedCount;
	}

	public int getMapsFailedCount() {
		return mapsFailedCount;
	}

	public void setMapsFailedCount(int mapsFailedCount) {
		this.mapsFailedCount = mapsFailedCount;
	}

	public int getMapsKilledCount() {
		return mapsKilledCount;
	}

	public void setMapsKilledCount(int mapsKilledCount) {
		this.mapsKilledCount = mapsKilledCount;
	}

	public int getMapsRunningCount() {
		return mapsRunningCount;
	}

	public void setMapsRunningCount(int mapsRunningCount) {
		this.mapsRunningCount = mapsRunningCount;
	}

	public int getMapsWaitingCount() {
		return mapsWaitingCount;
	}

	public void setMapsWaitingCount(int mapsWaitingCount) {
		this.mapsWaitingCount = mapsWaitingCount;
	}

	public int getReducesLaunchedCount() {
		return reducesLaunchedCount;
	}

	public void setReducesLaunchedCount(int reducesLaunchedCount) {
		this.reducesLaunchedCount = reducesLaunchedCount;
	}

	public int getReducesCompletedCount() {
		return reducesCompletedCount;
	}

	public void setReducesCompletedCount(int reducesCompletedCount) {
		this.reducesCompletedCount = reducesCompletedCount;
	}

	public int getReducesFailedCount() {
		return reducesFailedCount;
	}

	public void setReducesFailedCount(int reducesFailedCount) {
		this.reducesFailedCount = reducesFailedCount;
	}

	public int getReducesKilledCount() {
		return reducesKilledCount;
	}

	public void setReducesKilledCount(int reducesKilledCount) {
		this.reducesKilledCount = reducesKilledCount;
	}

	public int getReducesRunningCount() {
		return reducesRunningCount;
	}

	public void setReducesRunningCount(int reducesRunningCount) {
		this.reducesRunningCount = reducesRunningCount;
	}

	public int getReducesWaitingCount() {
		return reducesWaitingCount;
	}

	public void setReducesWaitingCount(int reducesWaitingCount) {
		this.reducesWaitingCount = reducesWaitingCount;
	}

	public int getRegionSplitFailureCount() {
		return regionSplitFailureCount;
	}

	public void setRegionSplitFailureCount(int regionSplitFailureCount) {
		this.regionSplitFailureCount = regionSplitFailureCount;
	}

	public int getRegionSplitSuccessCount() {
		return regionSplitSuccessCount;
	}

	public void setRegionSplitSuccessCount(int regionSplitSuccessCount) {
		this.regionSplitSuccessCount = regionSplitSuccessCount;
	}

	public int getRequests() {
		return requests;
	}

	public void setRequests(int requests) {
		this.requests = requests;
	}

	public int getCluster_requests() {
		return cluster_requests;
	}

	public void setCluster_requests(int cluster_requests) {
		this.cluster_requests = cluster_requests;
	}

	public double getSplitSize_avg_time() {
		return splitSize_avg_time;
	}

	public void setSplitSize_avg_time(double splitSize_avg_time) {
		this.splitSize_avg_time = splitSize_avg_time;
	}

	public double getSplitSize_num_ops() {
		return splitSize_num_ops;
	}

	public void setSplitSize_num_ops(double splitSize_num_ops) {
		this.splitSize_num_ops = splitSize_num_ops;
	}

	public double getSplitTime_avg_time() {
		return splitTime_avg_time;
	}

	public void setSplitTime_avg_time(double splitTime_avg_time) {
		this.splitTime_avg_time = splitTime_avg_time;
	}

	public double getSplitTime_num_ops() {
		return splitTime_num_ops;
	}

	public void setSplitTime_num_ops(double splitTime_num_ops) {
		this.splitTime_num_ops = splitTime_num_ops;
	}

	public int getBlockCacheHitCount() {
		return blockCacheHitCount;
	}

	public void setBlockCacheHitCount(int blockCacheHitCount) {
		this.blockCacheHitCount = blockCacheHitCount;
	}

	public double getBlockCacheSize() {
		return blockCacheSize;
	}

	public void setBlockCacheSize(double blockCacheSize) {
		this.blockCacheSize = blockCacheSize;
	}

	public double getCompactionTime_num_ops() {
		return compactionTime_num_ops;
	}

	public void setCompactionTime_num_ops(double compactionTime_num_ops) {
		this.compactionTime_num_ops = compactionTime_num_ops;
	}

	public int getHlogFileCount() {
		return hlogFileCount;
	}

	public void setHlogFileCount(int hlogFileCount) {
		this.hlogFileCount = hlogFileCount;
	}

	public double getMemstoreSizeMB() {
		return memstoreSizeMB;
	}

	public void setMemstoreSizeMB(double memstoreSizeMB) {
		this.memstoreSizeMB = memstoreSizeMB;
	}

	public double getFileSystemLeftCapacity() {
		return fileSystemLeftCapacity;
	}

	public void setFileSystemLeftCapacity(double fileSystemLeftCapacity) {
		this.fileSystemLeftCapacity = fileSystemLeftCapacity;
	}

	public int getRegionsCount() {
		return regionsCount;
	}

	public void setRegionsCount(int regionsCount) {
		this.regionsCount = regionsCount;
	}

	public int getFilesCreated() {
		return filesCreated;
	}

	public void setFilesCreated(int filesCreated) {
		this.filesCreated = filesCreated;
	}

	public int getFilesDeleted() {
		return filesDeleted;
	}

	public void setFilesDeleted(int filesDeleted) {
		this.filesDeleted = filesDeleted;
	}

	public int getFilesRenamed() {
		return filesRenamed;
	}

	public void setFilesRenamed(int filesRenamed) {
		this.filesRenamed = filesRenamed;
	}

	public double getTotalCapacity() {
		return totalCapacity;
	}

	public void setTotalCapacity(double totalCapacity) {
		this.totalCapacity = totalCapacity;
	}

	public double getTotalUsedCapacity() {
		return totalUsedCapacity;
	}

	public void setTotalUsedCapacity(double totalUsedCapacity) {
		this.totalUsedCapacity = totalUsedCapacity;
	}

	public double getTotalUsage() {
		return totalUsage;
	}

	public void setTotalUsage(double totalUsage) {
		this.totalUsage = totalUsage;
	}

	public double getFileSystemUsage() {
		return fileSystemUsage;
	}

	public void setFileSystemUsage(double fileSystemUsage) {
		this.fileSystemUsage = fileSystemUsage;
	}

	public double getFileSystemLeftage() {
		return fileSystemLeftage;
	}

	public void setFileSystemLeftage(double fileSystemLeftage) {
		this.fileSystemLeftage = fileSystemLeftage;
	}

	public double getNonDfsUsedCapacity() {
		return nonDfsUsedCapacity;
	}

	public void setNonDfsUsedCapacity(double nonDfsUsedCapacity) {
		this.nonDfsUsedCapacity = nonDfsUsedCapacity;
	}

	public double getNonDfsUsage() {
		return nonDfsUsage;
	}

	public void setNonDfsUsage(double nonDfsUsage) {
		this.nonDfsUsage = nonDfsUsage;
	}

	public double getReservedMB() {
		return reservedMB;
	}

	public void setReservedMB(double reservedMB) {
		this.reservedMB = reservedMB;
	}

	public double getAvailabledMB() {
		return availabledMB;
	}

	public void setAvailabledMB(double availabledMB) {
		this.availabledMB = availabledMB;
	}

	public double getAllocatedMB() {
		return allocatedMB;
	}

	public void setAllocatedMB(double allocatedMB) {
		this.allocatedMB = allocatedMB;
	}

	public double getTotalMB() {
		return totalMB;
	}

	public void setTotalMB(double totalMB) {
		this.totalMB = totalMB;
	}

	public double getContainersAllocated() {
		return containersAllocated;
	}

	public void setContainersAllocated(double containersAllocated) {
		this.containersAllocated = containersAllocated;
	}

	public double getFlushNanosAvgTime() {
		return flushNanosAvgTime;
	}

	public void setFlushNanosAvgTime(double flushNanosAvgTime) {
		this.flushNanosAvgTime = flushNanosAvgTime;
	}

	public double getFlushNanosNumOps() {
		return flushNanosNumOps;
	}

	public void setFlushNanosNumOps(double flushNanosNumOps) {
		this.flushNanosNumOps = flushNanosNumOps;
	}

	public int getNumActiveNMs() {
		return numActiveNMs;
	}

	public void setNumActiveNMs(int numActiveNMs) {
		this.numActiveNMs = numActiveNMs;
	}

	public int getNumDecommissionedNMs() {
		return numDecommissionedNMs;
	}

	public void setNumDecommissionedNMs(int numDecommissionedNMs) {
		this.numDecommissionedNMs = numDecommissionedNMs;
	}

	public int getNumLostNMs() {
		return numLostNMs;
	}

	public void setNumLostNMs(int numLostNMs) {
		this.numLostNMs = numLostNMs;
	}

	public int getNumUnhealthyNMs() {
		return numUnhealthyNMs;
	}

	public void setNumUnhealthyNMs(int numUnhealthyNMs) {
		this.numUnhealthyNMs = numUnhealthyNMs;
	}

	public int getNumRebootedNMs() {
		return numRebootedNMs;
	}

	public void setNumRebootedNMs(int numRebootedNMs) {
		this.numRebootedNMs = numRebootedNMs;
	}

	public int getCompactionQueueSize() {
		return compactionQueueSize;
	}

	public void setCompactionQueueSize(int compactionQueueSize) {
		this.compactionQueueSize = compactionQueueSize;
	}

	public int getFlushQueueSize() {
		return flushQueueSize;
	}

	public void setFlushQueueSize(int flushQueueSize) {
		this.flushQueueSize = flushQueueSize;
	}

	public String getLastCheckpointTime() {
		return lastCheckpointTime;
	}

	public void setLastCheckpointTime(String lastCheckpointTime) {
		this.lastCheckpointTime = lastCheckpointTime;
	}

	public String getLastWrittenTransactionId() {
		return lastWrittenTransactionId;
	}

	public void setLastWrittenTransactionId(String lastWrittenTransactionId) {
		this.lastWrittenTransactionId = lastWrittenTransactionId;
	}

	public String getMillisSinceLastLoadedEdits() {
		return millisSinceLastLoadedEdits;
	}

	public void setMillisSinceLastLoadedEdits(String millisSinceLastLoadedEdits) {
		this.millisSinceLastLoadedEdits = millisSinceLastLoadedEdits;
	}

	public double getFsImageLoadTime() {
		return fsImageLoadTime;
	}

	public void setFsImageLoadTime(double fsImageLoadTime) {
		this.fsImageLoadTime = fsImageLoadTime;
	}

	public double getBlockReportNumOps() {
		return blockReportNumOps;
	}

	public void setBlockReportNumOps(double blockReportNumOps) {
		this.blockReportNumOps = blockReportNumOps;
	}

	public double getBlockReportAvgTime() {
		return blockReportAvgTime;
	}

	public void setBlockReportAvgTime(double blockReportAvgTime) {
		this.blockReportAvgTime = blockReportAvgTime;
	}

	public double getAddBlockOps() {
		return addBlockOps;
	}

	public void setAddBlockOps(double addBlockOps) {
		this.addBlockOps = addBlockOps;
	}

	public double getBlockReportsAvgTime() {
		return blockReportsAvgTime;
	}

	public void setBlockReportsAvgTime(double blockReportsAvgTime) {
		this.blockReportsAvgTime = blockReportsAvgTime;
	}

	public double getBlockReportsNumOps() {
		return blockReportsNumOps;
	}

	public void setBlockReportsNumOps(double blockReportsNumOps) {
		this.blockReportsNumOps = blockReportsNumOps;
	}

	public double getHeartbeatsAvgTime() {
		return heartbeatsAvgTime;
	}

	public void setHeartbeatsAvgTime(double heartbeatsAvgTime) {
		this.heartbeatsAvgTime = heartbeatsAvgTime;
	}

	public double getHeartbeatsNumOps() {
		return heartbeatsNumOps;
	}

	public void setHeartbeatsNumOps(double heartbeatsNumOps) {
		this.heartbeatsNumOps = heartbeatsNumOps;
	}

	public double getBlockCacheFree() {
		return blockCacheFree;
	}

	public void setBlockCacheFree(double blockCacheFree) {
		this.blockCacheFree = blockCacheFree;
	}

	public int getNameNodeNormalCount() {
		return nameNodeNormalCount;
	}

	public void setNameNodeNormalCount(int nameNodeNormalCount) {
		this.nameNodeNormalCount = nameNodeNormalCount;
	}

	public int getNameNodeSeriousCount() {
		return nameNodeSeriousCount;
	}

	public void setNameNodeSeriousCount(int nameNodeSeriousCount) {
		this.nameNodeSeriousCount = nameNodeSeriousCount;
	}

	public int getDatanodeNormalCount() {
		return datanodeNormalCount;
	}

	public void setDatanodeNormalCount(int datanodeNormalCount) {
		this.datanodeNormalCount = datanodeNormalCount;
	}

	public int getDatanodeSeriousCount() {
		return datanodeSeriousCount;
	}

	public void setDatanodeSeriousCount(int datanodeSeriousCount) {
		this.datanodeSeriousCount = datanodeSeriousCount;
	}

	public int getJournalnodeNormalCount() {
		return journalnodeNormalCount;
	}

	public void setJournalnodeNormalCount(int journalnodeNormalCount) {
		this.journalnodeNormalCount = journalnodeNormalCount;
	}

	public int getJournalnodeSeriousCount() {
		return journalnodeSeriousCount;
	}

	public void setJournalnodeSeriousCount(int journalnodeSeriousCount) {
		this.journalnodeSeriousCount = journalnodeSeriousCount;
	}

	public int getRegionServerNormalCount() {
		return regionServerNormalCount;
	}

	public void setRegionServerNormalCount(int regionServerNormalCount) {
		this.regionServerNormalCount = regionServerNormalCount;
	}

	public int getRegionServerSeriousCount() {
		return regionServerSeriousCount;
	}

	public void setRegionServerSeriousCount(int regionServerSeriousCount) {
		this.regionServerSeriousCount = regionServerSeriousCount;
	}

	public int getNodeManagerNormalCount() {
		return nodeManagerNormalCount;
	}

	public void setNodeManagerNormalCount(int nodeManagerNormalCount) {
		this.nodeManagerNormalCount = nodeManagerNormalCount;
	}

	public int getNodeManagerSeriousCount() {
		return nodeManagerSeriousCount;
	}

	public void setNodeManagerSeriousCount(int nodeManagerSeriousCount) {
		this.nodeManagerSeriousCount = nodeManagerSeriousCount;
	}

	public int getReduceManagerNormalCount() {
		return reduceManagerNormalCount;
	}

	public void setReduceManagerNormalCount(int reduceManagerNormalCount) {
		this.reduceManagerNormalCount = reduceManagerNormalCount;
	}

	public int getReduceManagerSeriousCount() {
		return reduceManagerSeriousCount;
	}

	public void setReduceManagerSeriousCount(int reduceManagerSeriousCount) {
		this.reduceManagerSeriousCount = reduceManagerSeriousCount;
	}

	public int getHmasterNormalCount() {
		return hmasterNormalCount;
	}

	public void setHmasterNormalCount(int hmasterNormalCount) {
		this.hmasterNormalCount = hmasterNormalCount;
	}

	public int getHmasterSeriousCount() {
		return hmasterSeriousCount;
	}

	public void setHmasterSeriousCount(int hmasterSeriousCount) {
		this.hmasterSeriousCount = hmasterSeriousCount;
	}

	public int getHbaseThirftServerNormalCount() {
		return hbaseThirftServerNormalCount;
	}

	public void setHbaseThirftServerNormalCount(int hbaseThirftServerNormalCount) {
		this.hbaseThirftServerNormalCount = hbaseThirftServerNormalCount;
	}

	public int getHbaseThirftServerSeriousCount() {
		return hbaseThirftServerSeriousCount;
	}

	public void setHbaseThirftServerSeriousCount(int hbaseThirftServerSeriousCount) {
		this.hbaseThirftServerSeriousCount = hbaseThirftServerSeriousCount;
	}

	public int getHiveThirftServerNormalCount() {
		return hiveThirftServerNormalCount;
	}

	public void setHiveThirftServerNormalCount(int hiveThirftServerNormalCount) {
		this.hiveThirftServerNormalCount = hiveThirftServerNormalCount;
	}

	public int getHiveThirftServerSeriousCount() {
		return hiveThirftServerSeriousCount;
	}

	public void setHiveThirftServerSeriousCount(int hiveThirftServerSeriousCount) {
		this.hiveThirftServerSeriousCount = hiveThirftServerSeriousCount;
	}

	public double getBlockCacheHitRatioMax() {
		return blockCacheHitRatioMax;
	}

	public void setBlockCacheHitRatioMax(double blockCacheHitRatioMax) {
		this.blockCacheHitRatioMax = blockCacheHitRatioMax;
	}

	public double getBlockCacheHitRatioMin() {
		return blockCacheHitRatioMin;
	}

	public void setBlockCacheHitRatioMin(double blockCacheHitRatioMin) {
		this.blockCacheHitRatioMin = blockCacheHitRatioMin;
	}

	public int getZookeeperServerCount() {
		return zookeeperServerCount;
	}

	public void setZookeeperServerCount(int zookeeperServerCount) {
		this.zookeeperServerCount = zookeeperServerCount;
	}

	public int getZookeeperServerNormalCount() {
		return zookeeperServerNormalCount;
	}

	public void setZookeeperServerNormalCount(int zookeeperServerNormalCount) {
		this.zookeeperServerNormalCount = zookeeperServerNormalCount;
	}

	public int getZookeeperServerSeriousCount() {
		return zookeeperServerSeriousCount;
	}

	public void setZookeeperServerSeriousCount(int zookeeperServerSeriousCount) {
		this.zookeeperServerSeriousCount = zookeeperServerSeriousCount;
	}

	public int getImpalaServerCount() {
		return impalaServerCount;
	}

	public void setImpalaServerCount(int impalaServerCount) {
		this.impalaServerCount = impalaServerCount;
	}

	public int getImpalaServerNormalCount() {
		return impalaServerNormalCount;
	}

	public void setImpalaServerNormalCount(int impalaServerNormalCount) {
		this.impalaServerNormalCount = impalaServerNormalCount;
	}

	public int getImpalaServerSeriousCount() {
		return impalaServerSeriousCount;
	}

	public void setImpalaServerSeriousCount(int impalaServerSeriousCount) {
		this.impalaServerSeriousCount = impalaServerSeriousCount;
	}

	public double getAllocatedAge() {
		return allocatedAge;
	}

	public void setAllocatedAge(double allocatedAge) {
		this.allocatedAge = allocatedAge;
	}

	public String getHadoopType() {
		return hadoopType;
	}

	public void setHadoopType(String hadoopType) {
		this.hadoopType = hadoopType;
	}

	public String getHbaseType() {
		return hbaseType;
	}

	public void setHbaseType(String hbaseType) {
		this.hbaseType = hbaseType;
	}

	public String getHiveType() {
		return hiveType;
	}

	public void setHiveType(String hiveType) {
		this.hiveType = hiveType;
	}

	public String getZookeeperType() {
		return zookeeperType;
	}

	public void setZookeeperType(String zookeeperType) {
		this.zookeeperType = zookeeperType;
	}

	public String getImpalaType() {
		return impalaType;
	}

	public void setImpalaType(String impalaType) {
		this.impalaType = impalaType;
	}

	public String getStormType() {
		return stormType;
	}

	public void setStormType(String stormType) {
		this.stormType = stormType;
	}
	
}
