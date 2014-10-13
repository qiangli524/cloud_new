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
	<s:if test="node_type == 34 && server_type == 51">
		<div class="mainboxBus" style="width: 100%;">
			<div class="title" style="margin-top: 18px;"><font color="blue" size="4" id="entity_name">标识:<s:property value="maasDataSourceObj.entity_name"/></font></div>
			<div class="item" style="width:47.1%;">
				<div class="top">
					<div class="ml5">
						<font color="orange" size="3">容量</font>
						<font color="orange" size="2" style="margin-left: 212px;">健康度:</font>
						<font color="orange" size="2" id="health_score_capacity"><s:property value="maasDataSourceObj.health_score_capacity"/></font>
					</div>
				</div>
				<div>
					<div class="left testimg"></div>
					<div class="right" style="margin-top: 10px;">
						<table>
							<tr>
								<td width="140px;">
									<div>数据源连接池大小:</div>
								</td>
								<td width="60px;">
									<div><span class="value" id="datasource_connectionPoolSize_active"><s:property value="maasDataSourceObj.datasource_connectionPoolSize_active"/></span></div>
								</td>
								<td>
									<div>健康度:</div>
								</td>
								<td>
									<div>&nbsp;<span class="value" id="datasource_connectionPoolSize_active_score"><s:property value="maasDataSourceObj.datasource_connectionPoolSize_active_score"/></span></div>
								</td>
							</tr>
							<tr>
								<td>
									<div>数据源可用连接数: </div>
								</td>
								<td>
									<div><span class="value" id="datasource_connectionAvailable_active"><s:property value="maasDataSourceObj.datasource_connectionAvailable_active"/></span></div>
								</td>
								<td>
									<div>健康度:</div>
								</td>
								<td>
									<div>&nbsp;<span class="value" id="datasource_connectionAvailable_active_score"><s:property value="maasDataSourceObj.datasource_connectionAvailable_active_score"/></span></div>
								</td>
							</tr>
						</table>
					</div>
				</div>
			</div>
			<div class="item" style="width:47.1%;">
				<div class="top">
					<div class="ml5">
						<font color="orange" size="3">异常</font>
						<font color="orange" size="2" style="margin-left: 212px;">健康度:</font>
						<font color="orange" size="2" id="health_score_exception"><s:property value="maasDataSourceObj.health_score_exception"/></font>
					</div>
				</div>
				<div>
					<div class="left testimg"></div>
					<div class="right" style="margin-top: 10px;">
						<table>
							<tr>	
								<td>
									<div>数据源不可用连接数:</div>
								</td>
								<td>
									<div><span class="value" id="datasource_connectionUnavailable_active"><s:property value="maasDataSourceObj.datasource_connectionUnavailable_active"/></span></div>
								</td>
								<td>
									<div>健康度:</div>
								</td>
								<td>
									<div>&nbsp;<span class="value" id="datasource_connectionUnavailable_active_score"><s:property value="maasDataSourceObj.datasource_connectionUnavailable_active_score"/></span></div>
								</td>
							</tr>
							<tr>
								<td width="140px;">
									<div>数据源连接请求失败数:</div>
								</td>
								<td width="60px;">
									<div><span class="value" id="datasource_connectionRequestFailures_throughput"><s:property value="maasDataSourceObj.datasource_connectionRequestFailures_throughput"/></span></div>
								</td>
								<td>
									<div>健康度:</div>
								</td>
								<td>
									<div>&nbsp;<span class="value" id="datasource_connectionRequestFailures_throughput_score"><s:property value="maasDataSourceObj.datasource_connectionRequestFailures_throughput_score"/></span></div>
								</td>
							</tr>
						</table>
					</div>
				</div>
			</div>
			<div class="item" style="width:47.1%;">
				<div class="top">
					<div class="ml5">
						<font color="orange" size="3">可用性</font>
						<font color="orange" size="2" style="margin-left: 196px;">健康度:</font>
						<font color="orange" size="2" id="health_score_availability"><s:property value="maasDataSourceObj.health_score_availability"/></font>
					</div>
				</div>
				<div>
					<div class="left testimg"></div>
					<div class="right" style="margin-top: 20px;">
						<table>
							<tr>
								<td width="140px;">
									<div>数据源状态: </div>
								</td>
								<td width="60px;">
									<div><span class="value" id="datasource_state_state"><s:property value="maasDataSourceObj.datasource_state_state"/></span></div>
								</td>
								<td>
									<div>健康度:</div>
								</td>
								<td>
									<div>&nbsp;<span class="value" id="datasource_state_state_score"><s:property value="maasDataSourceObj.datasource_state_state_score"/></span></div>
								</td>
							</tr>
						</table>
					</div>
				</div>
			</div>
			<div class="item" style="width:47.1%;">
				<div class="top">
					<div class="ml5">
						<font color="orange" size="3">负载</font>
						<font color="orange" size="2" style="margin-left: 212px;">健康度:</font>
						<font color="orange" size="2" id="health_score_efficiency"><s:property value="maasDataSourceObj.health_score_efficiency"/></font>
					</div>
				</div>
				<div>
					<div class="left testimg"></div>
					<div class="right" style="margin-top: 20px;">
						<table>
							<tr>
								<td width="140px;">
									<div>数据源连接请求数:</div>
								</td>
								<td width="60px;">
									<div> <span class="value" id="datasource_connectionRequests_throughput"><s:property value="maasDataSourceObj.datasource_connectionRequests_throughput"/></span></div>
								</td>
								<td>
									<div>健康度: </div>
								</td>
								<td>
									<div>&nbsp;<span class="value" id="datasource_connectionRequests_throughput_score"><s:property value="maasDataSourceObj.datasource_connectionRequests_throughput_score"/></span></div>
								</td>
							</tr>
						</table>
					</div>
				</div>
			</div>
			<div class="item" style="width:47.1%;">
				<div class="top">
					<div class="ml5">
						<font color="orange" size="3">响应</font>
						<font color="orange" size="2" style="margin-left: 212px;">健康度:</font>
						<font color="orange" size="2" id="health_score_response"><s:property value="maasDataSourceObj.health_score_response"/></font>
					</div>
				</div>
				<div>
					<div class="left testimg"></div>
					<div class="right" style="margin-top: 20px;">
						<table>
							<tr>
								<td width="140px;">
									<div>数据源连接创建时间: </div>
								</td>
								<td width="60px;">
									<div><span class="value" id="datasource_connectionCreated_time"><s:property value="maasDataSourceObj.datasource_connectionCreated_time"/></span></div>
								</td>
								<td>
									<div>健康度: </div>
								</td>
								<td>
									<div>&nbsp;<span class="value" id="datasource_connectionCreated_time_score"><s:property value="maasDataSourceObj.datasource_connectionCreated_time_score"/></span></div>
								</td>
							</tr>
						</table>
					</div>
				</div>
			</div>
		</div>
	</s:if>
	<s:elseif test="node_type == 34 && server_type == 52">
		<div class="mainboxBus" style="width: 100%;">
			<div class="title" style="margin-top: 18px;"><font color="blue" size="3" id="entity_name">标识:<s:property value="maasServiceObj.entity_name"/></font>
				<font color="blue" size="2" >
					<s:if test="coll_time != null && coll_time !='' ">
						(<s:property value="coll_time"/>)
					</s:if>
				</font>
			</div>
			<div class="item" style="width:47%;">
				<div class="top">
					<div class="ml5">
						<font color="orange" size="3">负载</font>
						<font color="orange" size="2" style="margin-left: 212px;">健康度:</font>
						<font color="orange" size="2" id="health_score_response"><s:property value="maasServiceObj.health_score_efficiency"/></font>
					</div>
				</div>
				<div>
					<div class="left testimg" style="margin-top: 25px;"></div>
					<div class="right" style="margin-top: 25px;">
						<table>
							<tr>
								<td width="140px;">
									<div> GC调用数:</div>
								</td>
								<td width="60px;">
									<div><span class="value" id="jvm_garbage_collectors_invocationsPerMin"><s:property value="maasServiceObj.jvm_garbage_collectors_invocationsPerMin"/></span></div>
								</td>
								<td>
									<div>健康度:</div>
								</td>
								<td>
									<div>&nbsp;<span class="value" id="jvm_garbage_collectors_invocationsPerMin_score"><s:property value="maasServiceObj.jvm_garbage_collectors_invocationsPerMin_score"/></span></div>
								</td>
							</tr>
							<tr>
								<td>
									<div>页面请求处理数:</div>
								</td>
								<td>
									<div><span class="value" id="server_servlet_jsp_service_throughput"><s:property value="maasServiceObj.server_servlet_jsp_service_throughput"/></span></div>
								</td>
								<td>
									<div>健康度:</div>
								</td>
								<td>
									<div>&nbsp;<span class="value" id="server_servlet_jsp_service_throughput_score"><s:property value="maasServiceObj.server_servlet_jsp_service_throughput_score"/></span></div>
								</td>
							</tr>
							<tr>
								<td>
									<div>线程池线程总数:</div>
								</td>
								<td>
									<div><span class="value" id="thread_pool_totalThreads_active"><s:property value="maasServiceObj.thread_pool_totalThreads_active"/></span></div>
								</td>
								<td>
									<div>健康度:</div>
								</td>
								<td>
									<div>&nbsp;<span class="value" id="thread_pool_totalThreads_active_score"><s:property value="maasServiceObj.thread_pool_totalThreads_active_score"/></span></div>
								</td>
							</tr>
							<tr>
								<td>
									<div>线程池暂挂请求数: </div>
								</td>
								<td>
									<div><span class="value" id="thread_pool_pendingRequests_active"><s:property value="maasServiceObj.thread_pool_pendingRequests_active"/></span></div>
								</td>
								<td>
									<div>健康度:</div>
								</td>
								<td>
									<div>&nbsp;<span class="value" id="thread_pool_pendingRequests_active_score"><s:property value="maasServiceObj.thread_pool_pendingRequests_active_score"/></span></div>
								</td>
							</tr>
							<tr>
								<td>
									<div>工作管理器请求数:</div>
								</td>
								<td>
									<div><span class="value" id="server_level_work_manager_wmProcessed_throughput"><s:property value="maasServiceObj.server_level_work_manager_wmProcessed_throughput"/></span></div>
								</td>
								<td>
									<div>健康度:</div>
								</td>
								<td>
									<div>&nbsp;<span class="value" id="server_level_work_manager_wmProcessed_throughput_score"><s:property value="maasServiceObj.server_level_work_manager_wmProcessed_throughput_score"/></span></div>
								</td>
							</tr>
						</table>
					</div>
					<div style="height:171px;"></div>
				</div>
			</div>
			<div class="item" style="width:47%;">
				<div class="top">
					<div class="ml5">
						<font color="orange" size="3">资源</font>
						<font color="orange" size="2" style="margin-left: 212px;">健康度:</font>
						<font color="orange" size="2" id="health_score_availability"><s:property value="maasServiceObj.health_score_resource"/></font>
					</div>
				</div>
				<div>
					<div class="left testimg"></div>
					<div class="right" style="margin-top: 10px;">
						<table>
							<tr>	
								<td width="140px;">
									<div>CPU占用率:</div>
								</td>
								<td width="60px;">
									<div><span class="value" id="jvm_cpuUsage_percentage"><s:property value="maasServiceObj.jvm_cpuUsage_percentage"/></span></div>
								</td>
								<td>
									<div>健康度:</div>
								</td>
								<td>
									<div>&nbsp;<span class="value" id="jvm_cpuUsage_percentage_score"><s:property value="maasServiceObj.jvm_cpuUsage_percentage_score"/></span></div>
								</td>
							</tr>
							<tr>	
								<td>
									<div>GC时间成本:</div>
								</td>
								<td>
									<div><span class="value" id="jvm_garbage_collectors_percentTimeInGc"><s:property value="maasServiceObj.jvm_garbage_collectors_percentTimeInGc"/></span></div>
								</td>
								<td>
									<div>健康度:</div>
								</td>
								<td>
									<div>&nbsp;<span class="value" id="jvm_garbage_collectors_percentTimeInGc_score"><s:property value="maasServiceObj.jvm_garbage_collectors_percentTimeInGc_score"/></span></div>
								</td>
							</tr>
							<tr>
								<td>
									<div>活动套接字数:</div>
								</td>
								<td>
									<div><span class="value" id="server_1_sockets_active"><s:property value="maasServiceObj.server_1_sockets_active"/></span></div>
								</td>
								<td>
									<div>健康度:</div>
								</td>
								<td>
									<div>&nbsp;<span class="value" id="server_1_sockets_active_score"><s:property value="maasServiceObj.server_1_sockets_active_score"/></span></div>
								</td>
							</tr>
							<tr>	
								<td>
									<div>JVM堆使用率:</div>
								</td>
								<td>
									<div><span class="value" id="jvm_heapUsedPercentage_value"><s:property value="maasServiceObj.jvm_heapUsedPercentage_value"/></span></div>
								</td>
								<td>
									<div>健康度:</div>
								</td>
								<td>
									<div>&nbsp;<span class="value" id="jvm_heapUsedPercentage_value_score"><s:property value="maasServiceObj.jvm_heapUsedPercentage_value_score"/></span></div>
								</td>
							</tr>
							<tr>	
								<td>
									<div>JVM线程峰值数:</div>
								</td>
								<td>
									<div><span class="value" id="jvm_threads_peakThreadCount_value"><s:property value="maasServiceObj.jvm_threads_peakThreadCount_value"/></span></div>
								</td>
								<td>
									<div>健康度:</div>
								</td>
								<td>
									<div>&nbsp;<span class="value" id="jvm_threads_peakThreadCount_value_score"><s:property value="maasServiceObj.jvm_threads_peakThreadCount_value_score"/></span></div>
								</td>
							</tr>
							<tr>	
								<td>
									<div>JVM堆内存使用量:</div>
								</td>
								<td>
									<div><span class="value" id="jvm_memory_usage_heapMemoryUsed_value"><s:property value="maasServiceObj.jvm_memory_usage_heapMemoryUsed_value"/></span></div>
								</td>
								<td>
									<div>健康度:</div>
								</td>
								<td>
									<div>&nbsp;<span class="value" id="jvm_memory_usage_heapMemoryUsed_value_score"><s:property value="maasServiceObj.jvm_memory_usage_heapMemoryUsed_value_score"/></span></div>
								</td>
							</tr>
							<tr>	
								<td>
									<div>JVM非堆内存使用量:</div>
								</td>
								<td>
									<div><span class="value" id="jvm_memory_usage_nonHeapMemoryUsed_value"><s:property value="maasServiceObj.jvm_memory_usage_nonHeapMemoryUsed_value"/></span></div>
								</td>
								<td>
									<div>健康度:</div>
								</td>
								<td>
									<div>&nbsp;<span class="value" id="jvm_memory_usage_nonHeapMemoryUsed_value_score"><s:property value="maasServiceObj.jvm_memory_usage_nonHeapMemoryUsed_value_score"/></span></div>
								</td>
							</tr>
						</table>
					</div>
					<div style="height: 69px;"></div>
				</div>
			</div>
			<div class="item" style="width:47%;">
				<div class="top">
					<div class="ml5">
						<font color="orange" size="3">响应</font>
						<font color="orange" size="2" style="margin-left: 212px;">健康度:</font>
						<font color="orange" size="2" id="health_score_efficiency"><s:property value="maasServiceObj.health_score_response"/></font>
					</div>
				</div>
				<div>
					<div class="left testimg"></div>
					<div class="right" style="margin-top: 10px;">
						<table>
							<tr>
								<td>
									<div>GC调用时间: </div>
								</td>
								<td>
									<div><span class="value" id="jvm_garbage_collectors_msecsPerInvocation"><s:property value="maasServiceObj.jvm_garbage_collectors_msecsPerInvocation"/></span></div>
								</td>
								<td>
									<div>健康度:</div>
								</td>
								<td>
									<div>&nbsp;<span class="value" id="jvm_garbage_collectors_msecsPerInvocation_score"><s:property value="maasServiceObj.jvm_garbage_collectors_msecsPerInvocation_score"/></span></div>
								</td>
							</tr>
							<tr>
								<td width="140px;">
									<div>页面请求处理时间:</div>
								</td>
								<td width="60px;">
									<div> <span class="value" id="server_servlet_jsp_service_time"><s:property value="maasServiceObj.server_servlet_jsp_service_time"/></span></div>
								</td>
								<td>
									<div>健康度: </div>
								</td>
								<td>
									<div>&nbsp;<span class="value" id="server_servlet_jsp_service_time_score"><s:property value="maasServiceObj.server_servlet_jsp_service_time_score"/></span></div>
								</td>
							</tr>
						</table>
					</div>
				</div>
			</div>
			<div class="item" style="width:47%;">
				<div class="top">
					<div class="ml5">
						<font color="orange" size="3">异常</font>
						<font color="orange" size="2" style="margin-left: 212px;">健康度:</font>
						<font color="orange" size="2" id="health_score_capacity"><s:property value="maasServiceObj.health_score_exception"/></font>
					</div>
				</div>
				<div>
					<div class="left testimg"></div>
					<div class="right" style="margin-top: 10px;">
						<table>
							
							<tr>
								<td width="140px;">
									<div>JVM死锁线程数: </div>
								</td>
								<td width="60px;">
									<div><span class="value" id="jvm_threads_deadlockedThreadCount_value"><s:property value="maasServiceObj.jvm_threads_deadlockedThreadCount_value"/></span></div>
								</td>
								<td>
									<div>健康度:</div>
								</td>
								<td>
									<div>&nbsp;<span class="value" id="jvm_threads_deadlockedThreadCount_value_score"><s:property value="maasServiceObj.jvm_threads_deadlockedThreadCount_value_score"/></span></div>
								</td>
							</tr>
							<tr>
								<td>
									<div>工作管理器粘滞线程数: </div>
								</td>
								<td>
									<div><span class="value" id="server_level_work_manager_wmStuck_active"><s:property value="maasServiceObj.server_level_work_manager_wmStuck_active"/></span></div>
								</td>
								<td>
									<div>健康度:</div>
								</td>
								<td>
									<div>&nbsp;<span class="value" id="server_level_work_manager_wmStuck_active_score"><s:property value="maasServiceObj.server_level_work_manager_wmStuck_active_score"/></span></div>
								</td>
							</tr>
						</table>
					</div>
				</div>
			</div>
			<div class="item" style="width:47%;">
				<div class="top">
					<div class="ml5">
						<font color="orange" size="3">可用性</font>
						<font color="orange" size="2" style="margin-left: 196px;">健康度:</font>
						<font color="orange" size="2" id="health_score_resource"><s:property value="maasServiceObj.health_score_availability"/></font>
					</div>
				</div>
				<div>
					<div class="left testimg"></div>
					<div class="right" style="margin-top: 20px;">
						<table>
							<tr>
								<td width="140px;">
									<div>实例状态: </div>
								</td>
								<td width="60px;">
									<div><span class="value" id="server_state_state"><s:property value="maasServiceObj.server_state_state"/></span></div>
								</td>
								<td>
									<div>健康度: </div>
								</td>
								<td>
									<div>&nbsp;<span class="value" id="server_state_state_score"><s:property value="maasServiceObj.server_state_state_score"/></span></div>
								</td>
							</tr>
						</table>
					</div>
				</div>
			</div>
			<div class="item" style="width:47%;">
				<div class="top">
					<div class="ml5">
						<font color="orange" size="3">容量</font>
						<font color="orange" size="2" style="margin-left: 212px;">健康度:</font>
						<font color="orange" size="2" id="health_score_exception"><s:property value="maasServiceObj.health_score_capacity"/></font>
					</div>
				</div>
				<div>
					<div class="left testimg"></div>
					<div class="right" style="margin-top: 20px;">
						<table>
							<tr>
								<td width="140px;">
									<div>线程池空闲线程数:</div>
								</td>
								<td width="60px;">
									<div><span class="value" id="thread_pool_idleThreads_active"><s:property value="maasServiceObj.thread_pool_idleThreads_active"/></span></div>
								</td>
								<td>
									<div>健康度:</div>
								</td>
								<td>
									<div>&nbsp;<span class="value" id="thread_pool_idleThreads_active_score"><s:property value="maasServiceObj.thread_pool_idleThreads_active_score"/></span></div>
								</td>
							</tr>
						</table>
					</div>
				</div>
			</div>
		</div>
	</s:elseif>
	</s:form>
</body>