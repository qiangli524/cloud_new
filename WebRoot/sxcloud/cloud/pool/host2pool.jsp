<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../common/taglib.jsp"%>
<%@ include file="../../common/view.jsp"%>
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="cache-control" content="no-cache"/>
<meta http-equiv="expires" content="0"/>

<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/ui/ui.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/tableSort/tableSort.js"></script>
<head>
	<script type="text/javascript">
		function searchRequest(){ 
			var poolid = hostForm.ID.value;
			var flag = hostForm.flag.value;
			var pool_type = hostForm.pool_type.value;
			hostForm.action = "resourcepool_host2pool.do?flag=" + flag +"&id=" +poolid + "&pool_type=" + pool_type;
			hostForm.submit();
		}
		function resetForm(){
			hostForm.eq_name.value = "";
			hostForm.eq_ip.value = "";
			hostForm.hasvertual.value = "-1";
			hostForm.type.value="-1";
		}
		$(function(){
			$("#checkall").click(function(){
				var flag = false;
					if($(this).attr('checked')){
						flag = true;
					}
				 $(':checkbox[id!=checkall]').attr('checked',flag);
			});
		})
	</script>
</head>
<body onLoad="self.focus();document.hostForm.eq_name.focus()">
	<s:form action="resourcepool_host2pool.do" method="post" cssClass="hostForm" id="hostForm">
	<s:hidden name="hostForm.HOST_POOL_ID" value="%{#request.pool_id}" id="ID"></s:hidden>
	<s:hidden name="hostForm.Flag" value="%{#request.flag}" id="flag"></s:hidden>
	<s:hidden name="hostForm.POOL_TYPE" value="%{#request.pool_type}" id="pool_type"></s:hidden>
		<div class="scrollbody">
			<div class="query">
				<div class="title"><%=getImageTag(request, "query-icon.gif")%></div>
			</div>
			<div class="box on">
				<div class="query-form">
					<table width="100%" class="querytable" border="0">
						<tr>
							<td class="til">
								主机名称:
							</td>
							<td>
								<s:textfield name="hostForm.eq_name" cssClass="txt" id="eq_name"></s:textfield>
							</td>
							<td class="til">
								主机IP地址:
							</td>
							<td>
								<s:textfield name="hostForm.eq_ip" cssClass="txt" id="eq_ip"></s:textfield>
							</td>
							<td class="til">
								主机类型:
							</td>
							<td>
								<s:select list="#{'-1':'--请选择--','1':'IBM小型机','2':'刀片'}" name="hostForm.eq_type" id="type"></s:select>
							</td>
							<td class="til">
								虚拟化类型:
							</td>
							<td>
								<s:select list="#{'-1':'--请选择--','3':'XEN','4':'VMWARE','5':'IBM','6':'KVM'}" name="hostForm.hasvertual" id="hasvertual"></s:select>
							</td>
						</tr>
						<tr>
							<td colspan="8" class="btns">
								<div>
									<input type="button" class="thickbox btn-style02" value="查询"
										onclick="javascript:searchRequest()" />
									<input type="button" class="btn-style02" value="重置"
										onclick="javascript:resetForm(document.getElementById('hostForm'))" />
								</div>
							</td>
						</tr>
					</table>
				</div>
				<div class="blue-wrap noborder" style="position:absolute;bottom:2px;top:100px;left:5px;right:5px;overflow-y:auto;">
					    <div class="table-head">
						<jsp:include page="/sxcloud/inc/Pagination.jsp?formId=hostForm" /></div>
					<div class="table-ct"> 
						<table id="theTable" width="100%" class="blue-table sorttable" border="0"
							cellspacing="0">
							<thead>
								<tr>
									<th>
										选择
									</th>
									<th onclick="sort(theTable,1,'string')">
										主机名称
									</th>
									<th onclick="sort(theTable,2,'string')">
										主机类型
									</th>
									<th onclick="sort(theTable,3,'string')">主机IP地址</th>
									<th onclick="sort(theTable,4,'string')">虚拟化类型</th>
								</tr>
							</thead>
							<tbody>
								<s:iterator value="#request.checkedList" id="hasChecked">
									<tr>
										<td>
											<input name="checkboxid" type="checkbox" id="checkall"
												value="<s:property value="#hasChecked.id"/>"/>
										</td>
										<td>
											<s:property value="#hasChecked.eq_name" />
										</td>
										<td>
											<s:if test="#hasChecked.eq_type == 1">
												IBM小型机
											</s:if>
											<s:elseif test="#hasChecked.eq_type == 2">
												刀片
											</s:elseif>
										</td>
										<td>
											<s:property value="#hasChecked.eq_ip" />
										</td>
										<td>
											<s:if test="#hasChecked.hasvertual == 3">
												XEN
											</s:if>
											<s:elseif test="#hasChecked.hasvertual==4">
												VMWARE	
											</s:elseif>
											<s:elseif test="#hasChecked.hasvertual==1">
												PowerVM
											</s:elseif>
											<s:elseif test="#hasChecked.hasvertual==0">
												无
											</s:elseif>
										</td>
									</tr>
								</s:iterator>
								<s:iterator value="#request.resultList" id="theBean">
									<tr>
										<td>
											<input name="checkboxid" type="checkbox" id="checkall"
												value="<s:property value="#theBean.id"/>" />
										</td>
										<td>
											<s:property value="#theBean.eq_name" />
										</td>
										<td>
											<s:if test="#theBean.eq_type == 1">
												IBM小型机
											</s:if>
											<s:elseif test="#theBean.eq_type == 2">
												刀片
											</s:elseif>
										</td>
										<td>
											<s:property value="#theBean.eq_ip" />
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
											<s:elseif test="#theBean.hasvertual==0">
												无
											</s:elseif>
										</td>
									</tr>
								</s:iterator>
							</tbody>
						</table>
						<div style="height:20px;"></div>
						<div style="position:absolute; display:none; background-color:#eee; width:200px; height:300px;" id="mdiv" ></div>
					</div>
				</div>
			</div>
		</div>
	</s:form>
</body>
