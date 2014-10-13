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
$(function(){
	 var api = frameElement.api;
	 var w = api.opener;
			api.button({
			     id:'OkAnd',
			     name: '添加',
			     callback:addHosts,
			     focus: true
			 },
			 {
			     id:'cancle',
			     name: '取消'
			 });
		
	 //添加
	 function addHosts(){
	 	var host_eq_ids="";
	 	var host_names="";
	 	var server_type = $("#server_type").val();
	 	var parentId = $("#uuid").val();
	 	var lenth=0;
	 	$('[name=checkboxid]:checkbox:checked').each(function(){
    		host_eq_ids = host_eq_ids+$(this).val()+",";
    		host_names = host_names+$(this).attr("eqName")+",";
    		lenth +=1;
    	});
    	if(lenth == 0){
    		alert("请勾选一项！");
    		return false;
    	}
		w.saveHosts(parentId,host_eq_ids,host_names,server_type);
		return true;
	}
});
	//查询
	function searchRequest(){
		mask('正在查询,请稍后....','0.5','0px');
		theForm.submit();
	}
	//重置
	function resetForm(){
			theForm.eq_name.value = "";
			theForm.eq_ip.value = "";
		}
</script>
</head>
<body style="height: 800px;" class="pop-body scrollbody">
	<s:form action="paasBusiTree_addHosts.do" method="post" styleId="theForm" id="theForm">
		<s:hidden name="uuid" id="uuid"></s:hidden>
		<s:hidden name="server_type" id="server_type"></s:hidden>
				<div class="query-form">
					<table width="100%" class="querytable" border="0">
						<tr>
							<td class="til">
								主机名称:
							</td>
							<td>
								<s:textfield name="greenPlumHostInfoObj.eq_name" cssClass="txt" id="eq_name"></s:textfield>
							</td>
							<td class="til">
								主机IP地址:
							</td>
							<td>
								<s:textfield name="greenPlumHostInfoObj.eq_ip" cssClass="txt" id="eq_ip"></s:textfield>
							</td>
						</tr>
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
									<!-- <th>虚拟化类型</th> -->
									<!-- <!--<th>性能</th> -->
									<th>状态</th>
									<!-- <th>时间</th> --> 
								</tr>
							</thead>
							<tbody>
								<s:iterator value="hostInforesultList" id="theBean">
									<tr>
										<td>
											<input name="checkboxid" type="checkbox" value="<s:property value='#theBean.eq_id'/>" eqName="<s:property value='#theBean.eq_name'/>"/>
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
										<td>
											<s:if test="#theBean.status == 0">
												未采集到
											</s:if>
											<s:elseif test="#theBean.status == 1">
												正常启动
											</s:elseif>
											<s:elseif test="#theBean.status == 2">
												关闭
											</s:elseif>
											<s:else>
												异常
											</s:else>
										</td>
									</tr>
								</s:iterator>
							</tbody>
						</table>
					</div>
				</div>
	</s:form>
</body>
</html>