<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page
	import="com.sitech.basd.sxcloud.workflow.domain.appdeployworkflow.TaskInfoObj"%>
<%@ page import="com.sitech.basd.sxcloud.workflow.domain.engine.FlowConstant"%>
<%@page
	import="com.sitech.basd.sxcloud.workflow.web.resworkflow.form.ResourceWorkflowForm"%>
<%@ include file="../../../common/taglib.jsp"%>
<%@ include file="../../../common/link.jsp"%>
<%@ include file="../../../common/view.jsp"%>
<html:html locale="true">
<head>
	<script type="text/javascript">
			$(function(){
				$("#datepicker1").datepicker({showOn: 'button', buttonImage: 'sxcloud/cresources/default/images/date-icon.gif', buttonImageOnly: true});
				$("#datepicker2").datepicker({showOn: 'button', buttonImage: 'sxcloud/cresources/default/images/date-icon.gif', buttonImageOnly: true});
			})

		</script>
</head>
<body>
		<div class="scrollbody">
			<div>
					<div  class="tit-zzi">
						<div id="zi">
							工单基本信息
						</div>
						<div id="zi"></div>
					</div>
					<div>
						<table width="100%" border="0" cellspacing="0"
							class="pop-table nosize">
							<tr>
								<td class="til">
									工单编号
								</td>
								<td>
									<s:property value="vmHost.workOrderID" default="无"/>
								</td>
								<td class="til">
									虚拟机服务子表编码
								</td>
								<td>
									<s:property value="vmHost.vmSubTableNo" default="无"/>
								</td>
							</tr>
							<tr>
								<td class="til">
									服务编码
								</td>
								<td id="detail">
									<s:property value="vmHost.serverNo" default="无"/>
								</td>
								<td class="til">
									申请类型
								</td>
								<td>
							        <s:if test="vmHost.applyType==0">
								                                    新增
								    </s:if>
								    <s:elseif test="vmHost.applyType==1">
								                                    修改
								    </s:elseif>
								    <s:elseif test="vmHost.applyType==2">
								                                      删除
								    </s:elseif>
								    <s:else>
								                                      无
								    </s:else>
								</td>
							</tr>
							<tr>
								<td class="til">
									资源模板编码
								</td>
								<td>
									<s:property value="vmHost.resourceTemNo" default="无"/>
								</td>
								<td class="til">
									虚拟机名称
								</td>
								<td>
									<s:property value="vmHost.vmName" default="无"/>
								</td>
							</tr>
							<tr>
								<td class="til">
									虚拟机标识
								</td>
								<td id="detail">
									<s:property value="vmHost.vmId" default="无"/>
								</td>
								<td class="til">
									详细描述
								</td>
								<td>
									<s:property value="vmHost.description" default="无"/>
								</td>
							</tr>
							<tr>
								<td class="til">
									CPU主频
								</td>
								<td>
									<s:property value="vmHost.cpuFrequency" default="无"/>
								</td>
								<td class="til">
									CPU个数
								</td>
								<td>
									<s:property value="vmHost.cpuCount" default="无"/>
								</td>
							</tr>
							<tr>
								<td class="til">
									内存大小
								</td>
								<td id="detail">
									<s:property value="vmHost.memSize" default="无"/>
								</td>
								<td class="til">
									存储大小
								</td>
								<td>
									<s:property value="vmHost.storageSize" default="无"/>
								</td>
							</tr>
							<tr>
								<td class="til">
									操作系统类型
								</td>
								<td>
									<s:property value="vmHost.osType" default="无"/>
								</td>
								<td class="til">
									操作系统版本
								</td>
								<td>
									<s:property value="vmHost.osVersion" default="无"/>
								</td>
							</tr>
							<tr>
								<td class="til">
									中间件类型
								</td>
								<td id="detail">
									<s:property value="vmHost.mwType" default="无"/>
								</td>
								<td class="til">
									中间件版本
								</td>
								<td>
									<s:property value="vmHost.mwVersion" default="无"/>
								</td>
							</tr>
							<tr>
								<td class="til">
									数据库类型
								</td>
								<td>
									<s:property value="vmHost.dbType" default="无"/>
								</td>
								<td class="til">
									数据库版本
								</td>
								<td>
									<s:property value="vmHost.dbVersion" default="无"/>
								</td>
							</tr>
							<tr>
								<td class="til">
									IP地址
								</td>
								<td id="detail">
									<s:property value="vmHost.ipAddress" default="无"/>
								</td>
								<td class="til">
									MAC地址
								</td>
								<td>
									<s:property value="vmHost.macAddress" default="无"/>
								</td>
							</tr>
						</table>
					</div>
		</div>
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td colspan="4" class="btnCenter">
									<input type="button" class="thickbox btn-style02" value="返回"
										onclick="history.go(-1)" />
								</td>
							</tr>
						</table>
					</div>
				</div>
			</div>
</body>
</html:html>