<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../sxcloud/common/link.jsp"%>
<%@ include file="../../sxcloud/common/taglib.jsp"%>

<html:html locale="true">
<head>
	<title></title>
	
	<script type="text/javascript" src="<%=request.getContextPath()%>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
	<script type="text/javascript">
		function resetForm(theForm){
			theForm.host_name.value = '';
			theForm.os_template_id.value = '';
		}
		
		//响应修改超链接	
		function modRequest(){
			var couterNum = 0;
			var checkboxids = document.getElementsByName("checkboxid");
			if(checkboxids!=null&&checkboxids.length>0){
				for(var i=0;i<checkboxids.length;i++){
					if(checkboxids[i].checked){
						couterNum = couterNum + 1 ;
						theForm.id.value = checkboxids[i].value;
					}
				}
			}
			if(couterNum==0){
				alert("请勾选一条信息");
				return false ;
			}else if(couterNum>1){
				alert("一次只能修改一条信息");
				return false ;
			}		
			$.dialog({
				id : 'customed_install_v',
				title : '网络配置',
				width : '680px',
				height : '530px',
				max : false,
				min : false,
				lock : true,
				content : 'url:hostAdapter_updateHostAdapter.do?theForm.id=' + theForm.id.value
			});
		}
		
		function _update_button_click_event(argForm){
			$.ajax({
				type : 'post',
				url : 'hostAdapter_modifyHostAdapter.do?' + argForm,
				success : function(msg) {
					if (msg == -1) {
						alert("保存失败");
					} else {
						theForm.action = "hostAdapter_queryHostAdapter.do";
						theForm.submit();
					}
				}
			});
		}
			
		function nextStep(theForm){
			theForm.action = "osFileSystem_queryFileSystem.do";
			theForm.submit();
		}	
	</script>
</head>
<body>
<div class="mainbody">
	<s:form action="hostAdapter_queryHostAdapter.do" method="post" cssStyle="theForm" id="theForm">
	<s:hidden name="theForm.id" id="id"></s:hidden>
	<s:hidden name="theForm.template_id" id="template_id"></s:hidden>
	<s:hidden name="theForm.os_host_id" id="os_host_id"></s:hidden>
		<div class="pd-20 bgcolor-1">
			<h2 class="utt-1">网络配置</h2>
			<div class="bord-1 pd-10">
				<div class="clearfix mgt-10">
					<%-- 
					<s:select name="theForm.os_template" id="os_template" cssClass="select-1 vm"
						listValue="templ_name " listKey="id " list="theForm.templList" disabled="true"/>
					--%>
				</div>
				<div class="utt-2">
					<a class="icon-modify" href="javascript:void(0)" onclick="modRequest();return false;" >修改</a>
				</div>
				<table id="theTable" width="100%" class="blue-table sorttable" border="0" cellspacing="0">
					<thead>
						<tr>
							<th width="5%">编号</th>
							<th width="5%" onclick="sort(theTable,0,'string')">网卡</th> 
							<th width="20%" onclick="sort(theTable,1,'string')">MAC</th>   
							<th width="20%" onclick="sort(theTable,2,'string')">IP地址</th>
							<th width="20%" onclick="sort(theTable,3,'string')">子网掩码</th>
							<th width="20%" onclick="sort(theTable,4,'string')">默认网关</th>
							<th width="10%" onclick="sort(theTable,5,'string')">备注</th>
						</tr>
					</thead>
					<tbody>
					<s:iterator value="theForm.adapterList" id="theBean">
						<tr>
							<td><input name="checkboxid" type="checkbox" value="<s:property value="#theBean.id"/>"/></td>
							<td><s:property value="#theBean.nic_order"/></td>
							<td><s:property value="#theBean.mac"/></td>
							<td><s:property value="#theBean.ip_address"/></td>
							<td><s:property value="#theBean.subnet_mask"/></td>
							<td><s:property value="#theBean.default_gateway"/></td>
							<td><s:property value="#theBean.purpose"/></td>							
						</tr>
					</s:iterator>		  
					</tbody>
				</table>
			</div>
		</div>	

		<div class="footbuttons mgb-20" align="center"><!-- 分页 -->
			<span class="ubtn-1 mgl-20"><input type="button" onclick="javascript:nextStep(document.getElementById('theForm'))" value="下一步" /></span>
		</div>
		
	</s:form>
</div>
</body>
</html:html>

