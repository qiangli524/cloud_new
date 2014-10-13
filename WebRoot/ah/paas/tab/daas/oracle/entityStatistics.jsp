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
	<s:if test="node_type == 24 && server_type == 32">
		<div class="mainboxBus" style="width: 100%;border: solid 0px #FFFFFF;">
			<div class="title" style="margin-top: 18px;"><font color="blue" size="3" id="entity_name">
				<s:property value="daasInstanceObj.entity_name"/>
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
						<font color="orange" size="3">响应</font>
						<font color="orange" size="2" style="margin-left: 212px;">健康度:</font>
						<font color="orange" size="2" id="health_score_response"><s:property value="daasInstanceObj.health_score_response"/></font>
					</div>
				</div>
				<div>
					<div class="left testimg"></div>
					<div class="right">
						<table>
							<tr>
								<td width="140px;">
									<div>平均文件读取时间:</div>
								</td>
								<td width="60px;">
									<div><span class="value" id="fileRead_cs"><s:property value="daasInstanceObj.fileRead_cs"/></span></div>
								</td>
								<td>
									<div>健康度:</div>
								</td>
								<td>
									<div>&nbsp;<span class="value" id="fileRead_cs_score"><s:property value="daasInstanceObj.fileRead_cs_score"/></span></div>
								</td>
							</tr>
							<tr>
								<td>
									<div>平均文件写入时间: </div>
								</td>
								<td>
									<div><span class="value" id="fileWrite_cs"><s:property value="daasInstanceObj.fileWrite_cs"/></span></div>
								</td>
								<td>
									<div>健康度:</div>
								</td>
								<td>
									<div>&nbsp;<span class="value" id="fileWrite_cs_score"><s:property value="daasInstanceObj.fileWrite_cs_score"/></span></div>
								</td>
							</tr>
							<tr>
								<td>
									<div>当前SQL平均响应时间:</div>
								</td>
								<td>
									<div><span class="value" id="sql_responseTime"><s:property value="daasInstanceObj.sql_responseTime"/></span></div>
								</td>
								<td>
									<div>健康度:</div>
								</td>
								<td>
									<div>&nbsp;<span class="value" id="sql_responseTime_score"><s:property value="daasInstanceObj.sql_responseTime_score"/></span></div>
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
						<font color="orange" size="2" style="margin-left: 178px;">健康度:</font>
						<font color="orange" size="2" id="health_score_availability"><s:property value="daasInstanceObj.health_score_availability"/></font>
					</div>
				</div>
				<div>
					<div class="left testimg"></div>
					<div class="right" style="margin-top: 10px;">
						<table>
							<tr>
								<td width="100px;">
									<div>实例状态:</div>
								</td>
								<td width="80px;">
									<div><span class="value" id="responseStatus"><s:property value="daasInstanceObj.responseStatus"/></span></div>
								</td>
								<td>
									<div>健康度:</div>
								</td>
								<td>
									<div>&nbsp;<span class="value" id="responseStatus_score"><s:property value="daasInstanceObj.responseStatus_score"/></span></div>
								</td>
							</tr>
							<tr>	
								<td>
									<div>归档状态:</div>
								</td>
								<td>
									<div><span class="value" id="ha_infoLog_mode"><s:property value="daasInstanceObj.ha_infoLog_mode"/></span></div>
								</td>
								<td>
									<div>健康度:</div>
								</td>
								<td>
									<div>&nbsp;<span class="value" id="ha_infoLog_mode_score"><s:property value="daasInstanceObj.ha_infoLog_mode_score"/></span></div>
								</td>
							</tr>
						</table>
					</div>
					<div style="height: 69px;"></div>
				</div>
			</div>
			<div class="item" style="width:47.1%;">
				<div class="top">
					<div class="ml5">
						<font color="orange" size="3">负载</font>
						<font color="orange" size="2" style="margin-left: 212px;">健康度:</font>
						<font color="orange" size="2" id="health_score_efficiency"><s:property value="daasInstanceObj.health_score_efficiency"/></font>
					</div>
				</div>
				<div>
					<div class="left testimg"></div>
					<div class="right" style="margin-top: 10px;">
						<table>
							<tr>
								<td width="140px;">
									<div>解析次数(每秒):</div>
								</td>
								<td width="60px;">
									<div> <span class="value" id="instance_throughputParses_ps"><s:property value="daasInstanceObj.instance_throughputParses_ps"/></span></div>
								</td>
								<td>
									<div>健康度: </div>
								</td>
								<td>
									<div>&nbsp;<span class="value" id="instance_throughputParses_ps_score"><s:property value="daasInstanceObj.instance_throughputParses_ps_score"/></span></div>
								</td>
							</tr>
							<tr>
								<td>
									<div>最大阻塞会话数: </div>
								</td>
								<td>
									<div><span class="value" id="userBlockCount"><s:property value="daasInstanceObj.userBlockCount"/></span></div>
								</td>
								<td>
									<div>健康度:</div>
								</td>
								<td>
									<div>&nbsp;<span class="value" id="userBlockCount_score"><s:property value="daasInstanceObj.userBlockCount_score"/></span></div>
								</td>
							</tr>
						</table>
					</div>
				</div>
			</div>
			<div class="item" style="width:47.1%;">
				<div class="top">
					<div class="ml5">
						<font color="orange" size="3">容量</font>
						<font color="orange" size="2" style="margin-left: 190px;">健康度:</font>
						<font color="orange" size="2" id="health_score_capacity"><s:property value="daasInstanceObj.health_score_capacity"/></font>
					</div>
				</div>
				<div>
					<div class="left testimg"></div>
					<div class="right" style="margin-top: 10px;">
						<table>
							<tr>
								<td width="100px;">
									<div>SCN健康值: </div>
								</td>
								<td width="80px;">
									<div><span class="value" id="scn_growth_statisticsScn_health"><s:property value="daasInstanceObj.scn_growth_statisticsScn_health"/></span></div>
								</td>
								<td>
									<div>健康度:</div>
								</td>
								<td>
									<div>&nbsp;<span class="value" id="scn_growth_statisticsScn_health_score"><s:property value="daasInstanceObj.scn_growth_statisticsScn_health_score"/></span></div>
								</td>
							</tr>
							<tr>
								<td>
									<div>表空间占用率: </div>
								</td>
								<td>
									<div><span class="value" id="tbspAllocationSpaceAllocated"><s:property value="daasInstanceObj.tbspAllocationSpaceAllocated"/></span></div>
								</td>
								<td>
									<div>健康度:</div>
								</td>
								<td>
									<div>&nbsp;<span class="value" id="tbspAllocationSpaceAllocated_score"><s:property value="daasInstanceObj.tbspAllocationSpaceAllocated_score"/></span></div>
								</td>
							</tr>
						</table>
					</div>
				</div>
			</div>
			<div class="item" style="width:47.1%;">
				<div class="top">
					<div class="ml5">
						<font color="orange" size="3">资源</font>
						<font color="orange" size="2" style="margin-left: 212px;">健康度:</font>
						<font color="orange" size="2" id="health_score_resource"><s:property value="daasInstanceObj.health_score_resource"/></font>
					</div>
				</div>
				<div>
					<div class="left testimg"></div>
					<div class="right" style="margin-top: 10px;">
						<table>
							<tr>
								<td width="140px;">
									<div>CPU占用量: </div>
								</td>
								<td width="60px;">
									<div><span class="value" id="instance_efficiencyCpuusage_ps"><s:property value="daasInstanceObj.instance_efficiencyCpuusage_ps"/></span></div>
								</td>
								<td>
									<div>健康度: </div>
								</td>
								<td>
									<div>&nbsp;<span class="value" id="instance_efficiencyCpuusage_ps_score"><s:property value="daasInstanceObj.instance_efficiencyCpuusage_ps_score"/></span></div>
								</td>
							</tr>
							<tr>
								<td>
									<div>内存使用量:</div>
								</td>
								<td>
									<div> <span class="value" id="memory_usageTotal_memory"><s:property value="daasInstanceObj.memory_usageTotal_memory"/></span></div>
								</td>
								<td>
									<div>健康度: </div>
								</td>
								<td>
									<div>&nbsp;<span class="value" id="memory_usageTotal_memory_score"><s:property value="daasInstanceObj.memory_usageTotal_memory_score"/></span></div>
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
						<font color="orange" size="2" style="margin-left: 190px;">健康度:</font>
						<font color="orange" size="2" id="health_score_exception"><s:property value="daasInstanceObj.health_score_exception"/></font>
					</div>
				</div>
				<div>
					<div class="left testimg"></div>
					<div class="right" style="margin-top: 20px;">
						<table>
							<tr>
								<td width="100px;">
									<div>无效对象总数:</div>
								</td>
								<td width="80px;">
									<div><span class="value" id="invalid_objectsCount"><s:property value="daasInstanceObj.invalid_objectsCount"/></span></div>
								</td>
								<td>
									<div>健康度:</div>
								</td>
								<td>
									<div>&nbsp;<span class="value" id="invalid_objectsCount_score"><s:property value="daasInstanceObj.invalid_objectsCount_score"/></span></div>
								</td>
							</tr>
						</table>
					</div>
				</div>
			</div>
		</div>
	</s:if>
	<s:elseif test="node_type == 24 && server_type == 33">
		<div class="mainboxBus" style="width:100%;border: solid 0px #FFFFFF;">
			<div class="title" style="margin-top: 18px;"><font color="blue" size="4" id="entity_name"><s:property value="daasServiceObj.entity_name"/></font></div>
			<div class="item" style="width:47.1%">
				<div class="top">
					<div class="ml5">
						<font color="orange" size="3">负载</font>
						<font color="orange" size="2" style="margin-left: 245px;">健康度:</font>
						<font color="orange" size="2" id="health_score_efficiency"><s:property value="daasServiceObj.health_score_efficiency"/></font>
					</div>
				</div>
				<div>
					<div class="left testimg"></div>
					<div class="right">
						<table>
							<tr>
								<td width="130px;">
									<div>执行计数:</div>
								</td>
								<td width="100px;">
									<div><span class="value" id="dbServiceExecs_delta"><s:property value="daasServiceObj.dbServiceExecs_delta"/></span></div>
								</td>
								<td>
									<div>健康度:</div>
								</td>
								<td>
									<div>&nbsp;<span class="value" id="dbServiceExecs_delta_score"><s:property value="daasServiceObj.dbServiceExecs_delta_score"/></span></div>
								</td>
							</tr>
							<tr>
								<td>
									<div>数据库时间:</div>
								</td>
								<td>
									<div><span class="value" id="dbServiceDBTime_delta"><s:property value="daasServiceObj.dbServiceDBTime_delta"/></span></div>
								</td>
								<td>
									<div>健康度:</div>
								</td>
								<td>
									<div>&nbsp;<span class="value" id="dbServiceDBTime_delta_score"><s:property value="daasServiceObj.dbServiceDBTime_delta_score"/></span></div>
								</td>
							</tr>
							<tr>
								<td>
									<div>物理写入数:</div>
								</td>
								<td>
									<div><span class="value" id="dbServicePhyswrts_delta"><s:property value="daasServiceObj.dbServicePhyswrts_delta"/></span></div>
								</td>
								<td>
									<div>健康度:</div>
								</td>
								<td>
									<div>&nbsp;<span class="value" id="dbServicePhyswrts_delta_score"><s:property value="daasServiceObj.dbServicePhyswrts_delta_score"/></span></div>
								</td>
							</tr>
							<tr>
								<td>
									<div>数据库CPU:</div>
								</td>
								<td>
									<div><span class="value" id="dbServiceDBcpu_delta"><s:property value="daasServiceObj.dbServiceDBcpu_delta"/></span></div>
								</td>
								<td>
									<div>健康度:</div>
								</td>
								<td>
									<div>&nbsp;<span class="value" id="dbServiceDBcpu_delta_score"><s:property value="daasServiceObj.dbServiceDBcpu_delta_score"/></span></div>
								</td>
							</tr>
							<tr>
								<td>
									<div>事物处理数:</div>
								</td>
								<td>
									<div><span class="value" id="dbServiceTxns_delta"><s:property value="daasServiceObj.dbServiceTxns_delta"/></span></div>
								</td>
								<td>
									<div>健康度:</div>
								</td>
								<td>
									<div>&nbsp;<span class="value" id="dbServiceTxns_delta_score"><s:property value="daasServiceObj.dbServiceTxns_delta_score"/></span></div>
								</td>
							</tr>
							<tr>
								<td>
									<div>服务call总数:</div>
								</td>
								<td>
									<div><span class="value" id="dbServiceCalls_delta"><s:property value="daasServiceObj.dbServiceCalls_delta"/></span></div>
								</td>
								<td>
									<div>健康度:</div>
								</td>
								<td>
									<div>&nbsp;<span class="value" id="dbServiceCalls_delta_score"><s:property value="daasServiceObj.dbServiceCalls_delta_score"/></span></div>
								</td>
							</tr>
							<tr>
								<td>
									<div>服务物理读数:</div>
								</td>
								<td>
									<div><span class="value" id="dbServicePhysrds_delta"><s:property value="daasServiceObj.dbServicePhysrds_delta"/></span></div>
								</td>
								<td>
									<div>健康度:</div>
								</td>
								<td>
									<div>&nbsp;<span class="value" id="dbServicePhysrds_delta_score"><s:property value="daasServiceObj.dbServicePhysrds_delta_score"/></span></div>
								</td>
							</tr>
							<tr>
								<td>
									<div>集群等待时间:</div>
								</td>
								<td>
									<div><span class="value" id="dbServiceClus_wtime_delta"><s:property value="daasServiceObj.dbServiceClus_wtime_delta"/></span></div>
								</td>
								<td>
									<div>健康度:</div>
								</td>
								<td>
									<div>&nbsp;<span class="value" id="dbServiceClus_wtime_delta_score"><s:property value="daasServiceObj.dbServiceClus_wtime_delta_score"/></span></div>
								</td>
							</tr>
							<tr>
								<td>
									<div>并行等待时间:</div>
								</td>
								<td>
									<div><span class="value" id="dbServiceConc_wtime_delta"><s:property value="daasServiceObj.dbServiceConc_wtime_delta"/></span></div>
								</td>
								<td>
									<div>健康度:</div>
								</td>
								<td>
									<div>&nbsp;<span class="value" id="dbServiceConc_wtime_delta_score"><s:property value="daasServiceObj.dbServiceConc_wtime_delta_score"/></span></div>
								</td>
							</tr>
							<tr>
								<td>
									<div>用户I/O等待时间:</div>
								</td>
								<td>
									<div><span class="value" id="dbServiceIO_wtime_delta"><s:property value="daasServiceObj.dbServiceIO_wtime_delta"/></span></div>
								</td>
								<td>
									<div>健康度:</div>
								</td>
								<td>
									<div>&nbsp;<span class="value" id="dbServiceIO_wtime_delta_score"><s:property value="daasServiceObj.dbServiceIO_wtime_delta_score"/></span></div>
								</td>
							</tr>
						</table>
					</div>
				</div>
			</div>
			<div class="item" style="width:47.1%">
				<div class="top">
					<div class="ml5">
						<font color="orange" size="3">响应</font>
						<font color="orange" size="2" style="margin-left: 275px;">健康度:</font>
						<font color="orange" size="2" id="health_score_response"><s:property value="daasServiceObj.health_score_response"/></font>
					</div>
				</div>
				<div>
					<div class="left testimg"></div>
					<div class="right" style="margin-top: 25px;">
						<table>
							<tr>
								<td width="180px;">
									<div>每次用户调用的服务响应时间:</div>
								</td>
								<td width="80px;">
									<div><span class="value" id="dbServiceElapsed_time_delta"><s:property value="daasServiceObj.dbServiceElapsed_time_delta"/></span></div>
								</td>
								<td>
									<div>健康度:</div>
								</td>
								<td>
									<div>&nbsp;<span class="value" id="dbServiceElapsed_time_delta_score"><s:property value="daasServiceObj.dbServiceElapsed_time_delta_score"/></span></div>
								</td>
							</tr>
						</table>
					</div>
				</div>
				<div style="height: 94px;"></div>
			</div>
			<div class="item" style="width:47.1%">
				<div class="top">
					<div class="ml5">
						<font color="orange" size="3">资源</font>
						<font color="orange" size="2" style="margin-left: 275px;">健康度:</font>
						<font color="orange" size="2" id="health_score_resource"><s:property value="daasServiceObj.health_score_resource"/></font>
					</div>
				</div>
				<div>
					<div class="left testimg"></div>
					<div class="right" style="margin-top: 25px;">
						<table>
							<tr>
								<td width="180px;">
									<div>每次用户调用的服务CPU时间:</div>
								</td>
								<td width="80px;">
									<div><span class="value" id="dbServiceCpuUtil"><s:property value="daasServiceObj.dbServiceCpuUtil"/></span></div>
								</td>
								<td>
									<div>健康度:</div>
								</td>
								<td>
									<div>&nbsp;<span class="value" id="dbServiceCpuUtil_score"><s:property value="daasServiceObj.dbServiceCpuUtil_score"/></span></div>
								</td>
							</tr>
						</table>
					</div>
				</div>
				<div style="height: 94px;"></div>
			</div>
		</div>
	</s:elseif>
	</s:form>
</body>