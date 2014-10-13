<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page contentType="text/html; charset=UTF-8" %>
<%@ include file="/sxcloud/common/taglib.jsp"%>
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="cache-control" content="no-cache"/>
<meta http-equiv="expires" content="0"/>
<head>
<title>无标题文档</title>
<link href="<%=request.getContextPath()%>/ah/paas/statistics/css/statistics.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/images/nresources/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=iblue"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/js/checkbox.js"></script>
<script type="text/javascript">
</script>
</head>
<body>
	<s:form action="" method="post"  id="theForm">
	<s:if test="node_type == 24 && server_type == 4">
		<div class="mainboxBus" style="width: 100%;">
			<div class="title" style="margin-top: 18px;"><font color="blue" size="3" id="entity_name">
				<s:property value="mysqlObj.entity_name"/>
				</font>
				<font color="blue" size="2" >
					<s:if test="coll_time != null && coll_time !='' ">
						(<s:property value="coll_time"/>)
					</s:if>
				</font>
			</div>
			<div class="item" style="width:47.1%;">
				<div class="top">
					<div class="ml5">
						<font color="orange" size="3">基本</font>
					</div>
				</div>
				<div>
					<div class="right">
						<table>
							<tr>
								<td width="180px;">
									<div>数据库版本:</div>
								</td>
								<td width="80px;">
									<div><span class="value" id="version"><s:property value="mysqlObj.version"/></span></div>
								</td>
								<td>
									<div>健康度:</div>
								</td>
								<td>
									<div>&nbsp;<span class="value" id="fileRead_cs_score"><s:property value="mysqlObj.fileRead_cs_score"/></span></div>
								</td>
							</tr>
							<tr>
								<td>
									<div>数据库状态: </div>
								</td>
								<td>
									<div><span class="value" id="status"><s:property value="mysqlObj.status"/></span></div>
								</td>
								<td>
									<div>健康度:</div>
								</td>
								<td>
									<div>&nbsp;<span class="value" id="fileWrite_cs_score"><s:property value="mysqlObj.fileWrite_cs_score"/></span></div>
								</td>
							</tr>
							<tr>
								<td>
									<div>健康状态:</div>
								</td>
								<td>
									<div><span class="value" id="slave_running"><s:property value="mysqlObj.slave_running"/></span></div>
								</td>
								<td>
									<div>健康度:</div>
								</td>
								<td>
									<div>&nbsp;<span class="value" id="sql_responseTime_score"><s:property value="mysqlObj.sql_responseTime_score"/></span></div>
								</td>
							</tr>
							<tr>
								<td>
									<div>连接数:</div>
								</td>
								<td>
									<div><span class="value" id="full_process"><s:property value="mysqlObj.full_process"/></span></div>
								</td>
								<td>
									<div>健康度:</div>
								</td>
								<td>
									<div>&nbsp;<span class="value" id="sql_responseTime_score"><s:property value="mysqlObj.sql_responseTime_score"/></span></div>
								</td>
							</tr>
							<tr>
								<td>
									<div>全库数据量:</div>
								</td>
								<td>
									<div><span class="value" id="db_total"><s:property value="mysqlObj.db_total"/></span></div>
								</td>
								<td>
									<div>健康度:</div>
								</td>
								<td>
									<div>&nbsp;<span class="value" id="sql_responseTime_score"><s:property value="mysqlObj.sql_responseTime_score"/></span></div>
								</td>
							</tr>
							<tr>
								<td>
									<div>当前客户端已连接的数量:</div>
								</td>
								<td>
									<div><span class="value" id="threads_connected"><s:property value="mysqlObj.threads_connected"/></span></div>
								</td>
								<td>
									<div>健康度:</div>
								</td>
								<td>
									<div>&nbsp;<span class="value" id="sql_responseTime_score"><s:property value="mysqlObj.sql_responseTime_score"/></span></div>
								</td>
							</tr>
							<tr>
								<td>
									<div>创建用来处理连接的线程数:</div>
								</td>
								<td>
									<div><span class="value" id="threads_created"><s:property value="mysqlObj.threads_created"/></span></div>
								</td>
								<td>
									<div>健康度:</div>
								</td>
								<td>
									<div>&nbsp;<span class="value" id="sql_responseTime_score"><s:property value="mysqlObj.sql_responseTime_score"/></span></div>
								</td>
							</tr>
						<%--<tr>
								<td>
									<div>缓冲池总大小:</div>
								</td>
								<td>
									<div><span class="value" id="innodb_buffer_pool_pages_total"><s:property value="mysqlObj.innodb_buffer_pool_pages_total"/></span></div>
								</td>
								<td>
									<div>健康度:</div>
								</td>
								<td>
									<div>&nbsp;<span class="value" id="sql_responseTime_score"><s:property value="mysqlObj.sql_responseTime_score"/></span></div>
								</td>
							</tr>
							<tr>
								<td>
									<div>编译的InnoDB页大小:</div>
								</td>
								<td>
									<div><span class="value" id="innodb_page_size"><s:property value="mysqlObj.innodb_page_size"/></span></div>
								</td>
								<td>
									<div>健康度:</div>
								</td>
								<td>
									<div>&nbsp;<span class="value" id="sql_responseTime_score"><s:property value="mysqlObj.sql_responseTime_score"/></span></div>
								</td>
							</tr>
							<tr>
								<td>
									<div>创建的页数:</div>
								</td>
								<td>
									<div><span class="value" id="innodb_pages_created"><s:property value="mysqlObj.innodb_pages_created"/></span></div>
								</td>
								<td>
									<div>健康度:</div>
								</td>
								<td>
									<div>&nbsp;<span class="value" id="sql_responseTime_score"><s:property value="mysqlObj.sql_responseTime_score"/></span></div>
								</td>
							</tr>
							<tr>
								<td>
									<div>客户端被异常中断的数值:</div>
								</td>
								<td>
									<div><span class="value" id="aborted_clients"><s:property value="mysqlObj.aborted_clients"/></span></div>
								</td>
								<td>
									<div>健康度:</div>
								</td>
								<td>
									<div>&nbsp;<span class="value" id="sql_responseTime_score"><s:property value="mysqlObj.sql_responseTime_score"/></span></div>
								</td>
							</tr>
							<tr>
								<td>
									<div>执行全表搜索查询的数量:</div>
								</td>
								<td>
									<div><span class="value" id="select_scan"><s:property value="mysqlObj.select_scan"/></span></div>
								</td>
								<td>
									<div>健康度:</div>
								</td>
								<td>
									<div>&nbsp;<span class="value" id="sql_responseTime_score"><s:property value="mysqlObj.sql_responseTime_score"/></span></div>
								</td>
							</tr> --%>
						</table>
					</div>
				</div>
			</div>
			<div class="item" style="width:47.1%;">
				<div class="top">
					<div class="ml5">
						<font color="orange" size="3">重要</font>
					</div>
				</div>
				<div>
					<div class="right">
						<table>
							<tr>
								<td width="180px;">
									<div>运行时间:</div>
								</td>
								<td width="80px;">
									<div><span class="value" id="uptime"><s:property value="mysqlObj.uptime"/></span></div>
								</td>
								<td>
									<div>健康度:</div>
								</td>
								<td>
									<div>&nbsp;<span class="value" id="fileRead_cs_score"><s:property value="mysqlObj.fileRead_cs_score"/></span></div>
								</td>
							</tr>
							<tr>
								<td>
									<div>读表索引的第一行: </div>
								</td>
								<td>
									<div><span class="value" id="handler_read_first"><s:property value="mysqlObj.handler_read_first"/></span></div>
								</td>
								<td>
									<div>健康度:</div>
								</td>
								<td>
									<div>&nbsp;<span class="value" id="fileWrite_cs_score"><s:property value="mysqlObj.fileWrite_cs_score"/></span></div>
								</td>
							</tr>
							<tr>
								<td>
									<div>等待实例数:</div>
								</td>
								<td>
									<div><span class="value" id="innodb_buffer_pool_wait_free"><s:property value="mysqlObj.innodb_buffer_pool_wait_free"/></span></div>
								</td>
								<td>
									<div>健康度:</div>
								</td>
								<td>
									<div>&nbsp;<span class="value" id="sql_responseTime_score"><s:property value="mysqlObj.sql_responseTime_score"/></span></div>
								</td>
							</tr>
							<tr>
								<td>
									<div>最大连接数:</div>
								</td>
								<td>
									<div><span class="value" id="max_used_connections"><s:property value="mysqlObj.max_used_connections"/></span></div>
								</td>
								<td>
									<div>健康度:</div>
								</td>
								<td>
									<div>&nbsp;<span class="value" id="sql_responseTime_score"><s:property value="mysqlObj.sql_responseTime_score"/></span></div>
								</td>
							</tr>
							<tr>
								<td>
									<div>全连接的查询数目:</div>
								</td>
								<td>
									<div><span class="value" id="select_full_join"><s:property value="mysqlObj.select_full_join"/></span></div>
								</td>
								<td>
									<div>健康度:</div>
								</td>
								<td>
									<div>&nbsp;<span class="value" id="sql_responseTime_score"><s:property value="mysqlObj.sql_responseTime_score"/></span></div>
								</td>
							</tr>
							<tr>
								<td>
									<div>慢查询的数目:</div>
								</td>
								<td>
									<div><span class="value" id="threads_connected"><s:property value="mysqlObj.slow_queries"/></span></div>
								</td>
								<td>
									<div>健康度:</div>
								</td>
								<td>
									<div>&nbsp;<span class="value" id="sql_responseTime_score"><s:property value="mysqlObj.sql_responseTime_score"/></span></div>
								</td>
							</tr>
							<tr>
								<td>
									<div>持续查询的语句:</div>
								</td>
								<td>
									<div><span class="value" id="threads_running"><s:property value="mysqlObj.threads_running"/></span></div>
								</td>
								<td>
									<div>健康度:</div>
								</td>
								<td>
									<div>&nbsp;<span class="value" id="sql_responseTime_score"><s:property value="mysqlObj.sql_responseTime_score"/></span></div>
								</td>
							</tr>
						</table>
					</div>
				</div>
			</div>
			<div class="item" style="width:47.1%;">
				<div class="top">
					<div class="ml5">
						<font color="orange" size="3">QPS</font>
					</div>
				</div>
				<div>
					<div class="right" style="margin-top: 10px;">
						<table>
							<tr>
								<td width="180px;">
									<div>每秒钟获得的查询数量:</div>
								</td>
								<td width="80px;">
									<div><span class="value" id="QPS"><s:property value="mysqlObj.QPS"/></span></div>
								</td>
								<td>
									<div>健康度:</div>
								</td>
								<td>
									<div>&nbsp;<span class="value" id="responseStatus_score"><s:property value="mysqlObj.responseStatus_score"/></span></div>
								</td>
							</tr>
						</table>
					</div>
				</div>
			</div>
			<div class="item" style="width:47.1%;">
				<div class="top">
					<div class="ml5">
						<font color="orange" size="3">TPS</font>
					</div>
				</div>
				<div>
					<div class="right" style="margin-top: 10px;">
						<table>
							<tr>
								<td width="180px;">
									<div>每秒传输的事物处理数:</div>
								</td>
								<td width="80px;">
									<div><span class="value" id="TPS"><s:property value="mysqlObj.TPS"/></span></div>
								</td>
								<td>
									<div>健康度:</div>
								</td>
								<td>
									<div>&nbsp;<span class="value" id="responseStatus_score"><s:property value="mysqlObj.responseStatus_score"/></span></div>
								</td>
							</tr>
						</table>
					</div>
				</div>
			</div>
			<div class="item" style="width:47.1%;">
				<div class="top">
					<div class="ml5">
						<font color="orange" size="3">Query Cache</font>
					</div>
				</div>
				<div>
					<div class="right" style="margin-top: 10px;">
						<table>
							<tr>
								<td width="180px;">
									<div>查询缓存被访问的次数:</div>
								</td>
								<td width="80px;">
									<div><span class="value" id="qcache_hits"><s:property value="mysqlObj.qcache_hits"/></span></div>
								</td>
								<td>
									<div>健康度:</div>
								</td>
								<td>
									<div>&nbsp;<span class="value" id="scn_growth_statisticsScn_health_score"><s:property value="mysqlObj.scn_growth_statisticsScn_health_score"/></span></div>
								</td>
							</tr>
							<tr>
								<td>
									<div>加入到缓存的查询数量: </div>
								</td>
								<td>
									<div><span class="value" id="qcache_inserts"><s:property value="mysqlObj.qcache_inserts"/></span></div>
								</td>
								<td>
									<div>健康度:</div>
								</td>
								<td>
									<div>&nbsp;<span class="value" id="tbspAllocationSpaceAllocated_score"><s:property value="mysqlObj.tbspAllocationSpaceAllocated_score"/></span></div>
								</td>
							</tr>
						</table>
					</div>
				</div>
			</div>
			<div class="item" style="width:47.1%;">
				<div class="top">
					<div class="ml5">
						<font color="orange" size="3">Key Buffer</font>
					</div>
				</div>
				<div>
					<div class="right" style="margin-top: 10px;">
						<table>
							<tr>
								<td width="180px;">
									<div>读索引的次数: </div>
								</td>
								<td width="80px;">
									<div><span class="value" id="key_reads"><s:property value="mysqlObj.key_reads"/></span></div>
								</td>
								<td>
									<div>健康度:</div>
								</td>
								<td>
									<div>&nbsp;<span class="value" id="scn_growth_statisticsScn_health_score"><s:property value="mysqlObj.scn_growth_statisticsScn_health_score"/></span></div>
								</td>
							</tr>
							<tr>
								<td>
									<div>从缓存读键的数据块的请求数: </div>
								</td>
								<td>
									<div><span class="value" id="key_read_requests"><s:property value="mysqlObj.key_read_requests"/></span></div>
								</td>
								<td>
									<div>健康度:</div>
								</td>
								<td>
									<div>&nbsp;<span class="value" id="tbspAllocationSpaceAllocated_score"><s:property value="mysqlObj.tbspAllocationSpaceAllocated_score"/></span></div>
								</td>
							</tr>
							<tr>
								<td>
									<div>将键的数据块写入缓存的请求数: </div>
								</td>
								<td>
									<div><span class="value" id="key_write_requests"><s:property value="mysqlObj.key_write_requests"/></span></div>
								</td>
								<td>
									<div>健康度:</div>
								</td>
								<td>
									<div>&nbsp;<span class="value" id="tbspAllocationSpaceAllocated_score"><s:property value="mysqlObj.tbspAllocationSpaceAllocated_score"/></span></div>
								</td>
							</tr>
							<tr>
								<td>
									<div>向硬盘写入将键的数据块的物理写操作的次数: </div>
								</td>
								<td>
									<div><span class="value" id="key_writes"><s:property value="mysqlObj.key_writes"/></span></div>
								</td>
								<td>
									<div>健康度:</div>
								</td>
								<td>
									<div>&nbsp;<span class="value" id="tbspAllocationSpaceAllocated_score"><s:property value="mysqlObj.tbspAllocationSpaceAllocated_score"/></span></div>
								</td>
							</tr>
						</table>
					</div>
				</div>
			</div>
			<div class="item" style="width:47.1%;">
				<div class="top">
					<div class="ml5">
						<font color="orange" size="3">Table Cache状态量</font>
					</div>
				</div>
				<div>
					<div class="right" style="margin-top: 10px;">
						<table>
							<tr>
								<td width="180px;">
									<div>已经打开的表的数量: </div>
								</td>
								<td width="60px;">
									<div><span class="value" id="opened_tables"><s:property value="mysqlObj.opened_tables"/></span></div>
								</td>
								<td>
									<div>健康度: </div>
								</td>
								<td>
									<div>&nbsp;<span class="value" id="instance_efficiencyCpuusage_ps_score"><s:property value="mysqlObj.instance_efficiencyCpuusage_ps_score"/></span></div>
								</td>
							</tr>
							<tr>
								<td width="180px;">
									<div>当前打开的表的数目: </div>
								</td>
								<td width="60px;">
									<div><span class="value" id="open_tables"><s:property value="mysqlObj.open_tables"/></span></div>
								</td>
								<td>
									<div>健康度: </div>
								</td>
								<td>
									<div>&nbsp;<span class="value" id="instance_efficiencyCpuusage_ps_score"><s:property value="mysqlObj.instance_efficiencyCpuusage_ps_score"/></span></div>
								</td>
							</tr>
							<tr>
								<td width="180px;">
									<div>打开的文件的数目: </div>
								</td>
								<td width="60px;">
									<div><span class="value" id="open_files"><s:property value="mysqlObj.open_files"/></span></div>
								</td>
								<td>
									<div>健康度: </div>
								</td>
								<td>
									<div>&nbsp;<span class="value" id="instance_efficiencyCpuusage_ps_score"><s:property value="mysqlObj.instance_efficiencyCpuusage_ps_score"/></span></div>
								</td>
							</tr>
							<tr>
								<td width="180px;">
									<div>打开的流的数量: </div>
								</td>
								<td width="60px;">
									<div><span class="value" id="open_streams"><s:property value="mysqlObj.open_streams"/></span></div>
								</td>
								<td>
									<div>健康度: </div>
								</td>
								<td>
									<div>&nbsp;<span class="value" id="instance_efficiencyCpuusage_ps_score"><s:property value="mysqlObj.instance_efficiencyCpuusage_ps_score"/></span></div>
								</td>
							</tr>
							<tr>
								<td width="180px;">
									<div>被缓存的FRM文件数量: </div>
								</td>
								<td width="60px;">
									<div><span class="value" id="open_table_definitions"><s:property value="mysqlObj.open_table_definitions"/></span></div>
								</td>
								<td>
									<div>健康度: </div>
								</td>
								<td>
									<div>&nbsp;<span class="value" id="instance_efficiencyCpuusage_ps_score"><s:property value="mysqlObj.instance_efficiencyCpuusage_ps_score"/></span></div>
								</td>
							</tr>
							<tr>
								<td width="180px;">
									<div>已经创建的临时文件的数量: </div>
								</td>
								<td width="60px;">
									<div><span class="value" id="opened_files"><s:property value="mysqlObj.opened_files"/></span></div>
								</td>
								<td>
									<div>健康度: </div>
								</td>
								<td>
									<div>&nbsp;<span class="value" id="instance_efficiencyCpuusage_ps_score"><s:property value="mysqlObj.instance_efficiencyCpuusage_ps_score"/></span></div>
								</td>
							</tr>
							<tr>
								<td width="180px;">
									<div>被缓存过的FRM文件的数量: </div>
								</td>
								<td width="60px;">
									<div><span class="value" id="opened_table_definitions"><s:property value="mysqlObj.opened_table_definitions"/></span></div>
								</td>
								<td>
									<div>健康度: </div>
								</td>
								<td>
									<div>&nbsp;<span class="value" id="instance_efficiencyCpuusage_ps_score"><s:property value="mysqlObj.instance_efficiencyCpuusage_ps_score"/></span></div>
								</td>
							</tr>
						</table>
					</div>
				</div>
			</div>
			<div class="item" style="width:47.1%;">
				<div class="top">
					<div class="ml5">
						<font color="orange" size="3">Binlog Cache 使用状况</font>
					</div>
				</div>
				<div>
					<div class="right" style="margin-top: 20px;">
						<table>
							<tr>
								<td width="200px;">
									<div>全索引的扫描次数:</div>
								</td>
								<td width="80px;">
									<div><span class="value" id="binlog_cache_disk_use"><s:property value="mysqlObj.binlog_cache_disk_use"/></span></div>
								</td>
								<td>
									<div>健康度:</div>
								</td>
								<td>
									<div>&nbsp;<span class="value" id="invalid_objectsCount_score"><s:property value="mysqlObj.invalid_objectsCount_score"/></span></div>
								</td>
							</tr>
							<tr>
								<td width="200px;">
									<div>日志缓存的事务数量:</div>
								</td>
								<td width="80px;">
									<div><span class="value" id="binlog_cache_use"><s:property value="mysqlObj.binlog_cache_use"/></span></div>
								</td>
								<td>
									<div>健康度:</div>
								</td>
								<td>
									<div>&nbsp;<span class="value" id="invalid_objectsCount_score"><s:property value="mysqlObj.invalid_objectsCount_score"/></span></div>
								</td>
							</tr>
							<tr>
								<td width="200px;">
									<div>非事务语句使用二进制日志缓存:</div>
								</td>
								<td width="80px;">
									<div><span class="value" id="binlog_stmt_cache_disk_use"><s:property value="mysqlObj.binlog_stmt_cache_disk_use"/></span></div>
								</td>
								<td>
									<div>健康度:</div>
								</td>
								<td>
									<div>&nbsp;<span class="value" id="invalid_objectsCount_score"><s:property value="mysqlObj.invalid_objectsCount_score"/></span></div>
								</td>
							</tr>
							<tr>
								<td width="200px;">
									<div>临时文件缓存的非事务数量:</div>
								</td>
								<td width="80px;">
									<div><span class="value" id="binlog_stmt_cache_use"><s:property value="mysqlObj.binlog_stmt_cache_use"/></span></div>
								</td>
								<td>
									<div>健康度:</div>
								</td>
								<td>
									<div>&nbsp;<span class="value" id="invalid_objectsCount_score"><s:property value="mysqlObj.invalid_objectsCount_score"/></span></div>
								</td>
							</tr>
						</table>
					</div>
				</div>
			</div>
			<div class="item" style="width:47.1%;">
				<div class="top">
					<div class="ml5">
						<font color="orange" size="3">锁定状态</font>
					</div>
				</div>
				<div>
					<div class="right" style="margin-top: 20px;">
						<table>
							<tr>
								<td width="180px;">
									<div>当前等待的待锁定的行数:</div>
								</td>
								<td width="80px;">
									<div><span class="value" id="innodb_row_lock_current_waits"><s:property value="mysqlObj.innodb_row_lock_current_waits"/></span></div>
								</td>
								<td>
									<div>健康度:</div>
								</td>
								<td>
									<div>&nbsp;<span class="value" id="invalid_objectsCount_score"><s:property value="mysqlObj.invalid_objectsCount_score"/></span></div>
								</td>
							</tr>
							<tr>
								<td width="180px;">
									<div>行锁定花费的总时间:</div>
								</td>
								<td width="80px;">
									<div><span class="value" id="innodb_row_lock_time"><s:property value="mysqlObj.innodb_row_lock_time"/></span></div>
								</td>
								<td>
									<div>健康度:</div>
								</td>
								<td>
									<div>&nbsp;<span class="value" id="invalid_objectsCount_score"><s:property value="mysqlObj.invalid_objectsCount_score"/></span></div>
								</td>
							</tr>
							<tr>
								<td width="180px;">
									<div>行锁定的平均时间:</div>
								</td>
								<td width="80px;">
									<div><span class="value" id="innodb_row_lock_time_avg"><s:property value="mysqlObj.innodb_row_lock_time_avg"/></span></div>
								</td>
								<td>
									<div>健康度:</div>
								</td>
								<td>
									<div>&nbsp;<span class="value" id="invalid_objectsCount_score"><s:property value="mysqlObj.invalid_objectsCount_score"/></span></div>
								</td>
							</tr>
							<tr>
								<td width="180px;">
									<div>行锁定的最长时间:</div>
								</td>
								<td width="80px;">
									<div><span class="value" id="innodb_row_lock_time_max"><s:property value="mysqlObj.innodb_row_lock_time_max"/></span></div>
								</td>
								<td>
									<div>健康度:</div>
								</td>
								<td>
									<div>&nbsp;<span class="value" id="invalid_objectsCount_score"><s:property value="mysqlObj.invalid_objectsCount_score"/></span></div>
								</td>
							</tr>
							<tr>
								<td width="180px;">
									<div>一行锁定必须等待的时间数:</div>
								</td>
								<td width="80px;">
									<div><span class="value" id="innodb_row_lock_waits"><s:property value="mysqlObj.innodb_row_lock_waits"/></span></div>
								</td>
								<td>
									<div>健康度:</div>
								</td>
								<td>
									<div>&nbsp;<span class="value" id="invalid_objectsCount_score"><s:property value="mysqlObj.invalid_objectsCount_score"/></span></div>
								</td>
							</tr>
						</table>
					</div>
				</div>
			</div>
			<div class="item" style="width:47.1%;">
				<div class="top">
					<div class="ml5">
						<font color="orange" size="3">Tmp Table状况</font>
					</div>
				</div>
				<div>
					<div class="right" style="margin-top: 20px;">
						<table>
							<tr>
								<td width="200px;">
									<div>服务器执行语句时在硬盘上自动创建的临时表的数量:</div>
								</td>
								<td width="80px;">
									<div><span class="value" id="created_tmp_disk_tables"><s:property value="mysqlObj.created_tmp_disk_tables"/></span></div>
								</td>
								<td>
									<div>健康度:</div>
								</td>
								<td>
									<div>&nbsp;<span class="value" id="invalid_objectsCount_score"><s:property value="mysqlObj.invalid_objectsCount_score"/></span></div>
								</td>
							</tr>
							<tr>
								<td width="200px;">
									<div>服务器执行语句时自动创建的内存中的临时表的数量:</div>
								</td>
								<td width="80px;">
									<div><span class="value" id="created_tmp_tables"><s:property value="mysqlObj.created_tmp_tables"/></span></div>
								</td>
								<td>
									<div>健康度:</div>
								</td>
								<td>
									<div>&nbsp;<span class="value" id="invalid_objectsCount_score"><s:property value="mysqlObj.invalid_objectsCount_score"/></span></div>
								</td>
							</tr>
							<tr>
								<td width="200px;">
									<div>mysqld已经创建的临时文件的数量:</div>
								</td>
								<td width="80px;">
									<div><span class="value" id="created_tmp_files"><s:property value="mysqlObj.created_tmp_files"/></span></div>
								</td>
								<td>
									<div>健康度:</div>
								</td>
								<td>
									<div>&nbsp;<span class="value" id="invalid_objectsCount_score"><s:property value="mysqlObj.invalid_objectsCount_score"/></span></div>
								</td>
							</tr>
						</table>
					</div>
				</div>
			</div>
		</div>
	</s:if>
	</s:form>
</body>