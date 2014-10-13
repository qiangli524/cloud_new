<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<head>
	<title></title>
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/common.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=iblue"></script>
	<title></title>
<script type="text/javascript">

	//查询
	function searchRequest(){
		mask('正在查询,请稍后....','0.5','0px');
		theForm.submit();
	}
	//重置
	function resetForm(){
			theForm.eq_name.value = "";
			theForm.eq_ip.value = "";
			theForm.allocated.value = "";
			theForm.STATUS.value="";
			theForm.hasvertual.value = "-1";
		}
</script>
</head>
<body style="height: 800px;">
	<s:form action="paasBusiTree_addHost.do" method="post" styleId="theForm" id="theForm">
		<s:hidden name="uuid" id="uuid"></s:hidden>
			<div class="box on">
				<div class="query-form">
					<table width="100%" class="querytable" border="0">
						<tr>
							<td class="til">
								主机名称:
							</td>
							<td>
								<s:textfield name="obj.eq_name" cssClass="txt" id="eq_name"></s:textfield>
							</td>
							<td class="til">
								主机IP地址:
							</td>
							<td>
								<s:textfield name="obj.eq_ip" cssClass="txt" id="eq_ip"></s:textfield>
							</td>
						</tr>
						<%-- <tr>
							<td class="til">
								是否已分配:
							</td>
							<td>
								<s:select list="#{'':'---请选择--','0':'未分配','1':'已分配'}" name="obj.allocated" id="allocated" />
							</td>
							<td class="til">
								主机状态:
							</td>
							<td>
								<s:select list="#{'':'---请选择--','0':'未采集到','1':'正常启动','2':'关闭','3':'异常'}" name="obj.STATUS" id="STATUS"></s:select>
							</td>
							
							<td class="til">
								虚拟化类型:
							</td>
							<td>
								<s:select list="#{'-1':'---请选择--','3':'XEN','4':'VMWARE','1':'PowerVM','2':'KVM','0':'非虚拟化'}" name="obj.hasvertual" id="hasvertual"></s:select>
							</td> --%>
						</tr>
						<tr>
							<td colspan="8" class="btns">
								<div>
									<input type="button" class="thickbox btn-style02" value="查询"
										onclick="javascript:searchRequest()" />
									<input type="button" class="btn-style02" value="重置"
										onclick="javascript:resetForm(document.getElementById('theForm'))" />
								</div>
							</td>
						</tr>
					</table>
				</div>
				<div class="blue-wrap noborder" style="bottom:2px;top:100px;left:5px;right:5px;overflow-y:auto;">
					<div class="table-head">			
						<jsp:include page="/sxcloud/inc/Pagination.jsp?formId=theForm" />
					</div>
					<div class="table-ct">
						<table id="theTable" width="100%" class="blue-table sorttable" border="0"
							cellspacing="0">
							<thead>
								<tr>
									<th>
										选择
									</th>
									<th>
										主机名称
									</th>
									<th>
										主机类型
									</th>
									<th>主机IP地址</th>
									<!--<th>虚拟机(个)</th>-->
									<!-- <th>CPU(核)</th>
									<th>LUN(块)</th>
									<th>存储设备</th>
									<th>内存(G)</th>-->
									<th>是否已分配</th>
									<th>虚拟化类型</th>
									<!--<th>性能</th>
									<th>状态</th>
									<th>时间</th> -->
								</tr>
							</thead>
							<tbody>
								<s:iterator value="hostInforesultList" id="theBean">
									<tr>
										<td>
											<input name="checkboxid" type="checkbox" value="<s:property value='#theBean.h_uuid'/>" />
										</td>
										<td>
											<s:property value="#theBean.eq_name" />
										</td>
										<td>
											<s:if test="#theBean.eq_type == 1">
												IBM小型机
											</s:if>
											<s:elseif test="#theBean.eq_type == 2">
												IBM刀片
											</s:elseif>
											<s:elseif test="#theBean.eq_type == 3">
												IBM普通刀片
											</s:elseif>
											<s:elseif test="#theBean.eq_type == 4">
												HPx86刀片
											</s:elseif>
											<s:elseif test="#theBean.eq_type == 5">
												机架服务器
											</s:elseif>
										</td>
										<td>
											<s:property value="#theBean.eq_ip" />
										</td>
										<%-- <td eid='<s:property value="#theBean.eq_id" />'>
										<s:if test="#theBean.vm_num!=0">
											<a href='javascript:;' name='vm_list'><s:property value = "#theBean.vm_num"/></a>
										</s:if>
										<s:else>
											<s:property value = "#theBean.vm_num"/>
										</s:else>
										</td>
										<td>
											<s:property value="#theBean.cpu_cl"/>
										</td>
										<td eid='<s:property value="#theBean.eq_id" />'>
											<s:if test="#theBean.storage_num!=0">
												<a href='javascript:;' name='storage_list'><s:property value="#theBean.storage_num"/></a>
											</s:if>
											<s:else>
												<s:property value="#theBean.storage_num"/>
											</s:else>
										</td>
										<td deviceid='<s:property value="#theBean.deviceId" />'>
											<s:if test="#theBean.storage_num!=0">
												<a href="javascript:;" name="viewDevice">
													<s:property value="#theBean.deviceName"/>
												</a>
											</s:if>
											<s:else>
												--
											</s:else>
										</td>
										<td width="50px;">
											<s:if test='#theBean.mem != null'>						
												<s:property value="@java.lang.Math@round(#theBean.mem/1024*100) / 100.0"/>
											</s:if>
										</td>--%>
										<td>
											<s:if test="#theBean.allocated == 0">
												未分配
											</s:if>
											<s:elseif test="#theBean.allocated==1">
												已分配	
											</s:elseif>
										</td>
										<td>
											<s:if test="#theBean.hasvertual == 3">
												XEN
											</s:if>
											<s:elseif test="#theBean.hasvertual==4">
												VMWARE	
											</s:elseif>
											<s:elseif test="#theBean.hasvertual==1">
												PowerVM
											</s:elseif>
											<s:elseif test="#theBean.hasvertual==2">
												KVM
											</s:elseif>
											<s:elseif test="#theBean.hasvertual==0">
												非虚拟化
											</s:elseif>
										</td>
										<%--<td>
											<a href='javascript:;' name='vm_motion'>性能</a>
										</td>
										<td>
											<s:if test="#theBean.STATUS == 0">
												未采集到
											</s:if>
											<s:elseif test="#theBean.STATUS == 1">
												正常启动
											</s:elseif>
											<s:elseif test="#theBean.STATUS == 2">
												关闭
											</s:elseif>
											<s:else>
												异常
											</s:else>
										</td>
										<td>
											<s:property value="#theBean.ins_date"/>
										</td> --%>
									</tr>
								</s:iterator>
							</tbody>
						</table>
					</div>
				</div>
			</div>
	</s:form>
</body>
</html>