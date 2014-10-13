<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../sxcloud/common/link.jsp"%>
<%@ include file="../../sxcloud/common/taglib.jsp"%>
<html:html locale="true">
<head>
	<title></title>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/tableSort/tableSort.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
	<script type="text/javascript">
		$(function(){
			$check = $(":checkbox");
			$check.unbind().live("click",function(){
				$check.not(this).attr("checked",false);
			});
		});	
		
	 	function addRequest() {
	 		$.dialog({
				id : 'customed_install_v',
				title : '附件组配置',
				width : '680px',
				height : '530px',
				max : false,
				min : false,
				lock : true,
				content : 'url:osGroupUser_addGroupUser.do?theForm.os_host_id=' + theForm.os_host_id.value
			});
	 	}
	 	
		function modRequest() { 
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
				title : '附加组配置',
				width : '680px',
				height : '530px',
				max : false,
				min : false,
				lock : true,
				content : 'url:osGroupUser_updateGroupUser.do?theForm.id=' + theForm.id.value
			});
		}
		
		function delRequest() {
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
			    alert("一次只能删除一条信息");
			    return false ;
		    }
			if(confirm("确定要删除?")){
				$.ajax({
				type : 'post',
				url : 'osGroupUser_deleteGroupUser.do?theForm.id=' + theForm.id.value,
				success : function(msg) {
					if (msg == -1) {
						alert("保存失败");
					} else {
						reflush();
					}
				}
			});
			}
		}
		
		//返回主机列表
		function returnFunction(theForm){
			theForm.action = "autoos_listOsHost.do";
			theForm.submit();
		}
		
		function _save_button_click_event(argForm){
			$.ajax({
				type : 'post',
				url : 'osGroupUser_saveGroupUser.do?' + argForm,
				success : function(msg) {
					if (msg == -1) {
						alert("保存失败");
					} else {
						reflush();
					}
				}
			});
		}
		function _update_button_click_event(argForm){
			$.ajax({
				type : 'post',
				url : 'osGroupUser_modifyGroupUser.do?' + argForm,
				success : function(msg) {
					if (msg == -1) {
						alert("保存失败");
					} else {
						reflush();
					}
				}
			});
		}
		
		function reflush(){
			theForm.action = "osGroupUser_queryGroupUser.do";
			theForm.submit();
		}
	
		function nextStep(){
			theForm.action = "autoos_osConfigDetail.do";
			theForm.submit();
		}
	</script>
</head>
<body>
<div class="mainbody">
	<s:form action="osGroupUser_queryGroupUser.do" method="post" cssStyle="theForm" id="theForm">
	<s:hidden name="theForm.id" id="id"></s:hidden>
	<%-- <s:hidden name="theForm.template_id" id="template_id"></s:hidden> --%>
	<s:hidden name="theForm.os_host_id" id="os_host_id"></s:hidden>
		<div class="pd-20 bgcolor-1">
			<h2 class="utt-1">附加组配置</h2>
			<div class="bord-1 pd-10">
				<div class="utt-2 mgt-20">
					模版：<s:select name="theForm.template_id" id="template_id" 
						listValue="templ_name " listKey="id " 
						headerKey="" headerValue="----请选择----" list="theForm.templList" disabled="true"/>
				</div>			
				<%-- 
				<div class="utt-2 mgt-20">模版配置</div>
				--%>
				<table id="theTable" width="100%" class="blue-table sorttable" border="0" cellspacing="0">
					<thead>
						<tr>
							<th width="30%" onclick="sort(theTable,0,'string')">用户</th>   
							<th width="30%" onclick="sort(theTable,1,'string')">附加组</th>
							<th width="30%" onclick="sort(theTable,2,'string')">备注</th>
						</tr>
					</thead>
					<tbody>
					<s:iterator value="theForm.otguList" id="theBean">
						<tr>
							<td><s:property value="#theBean.user_id"/></td>
							<td><s:property value="#theBean.group_id"/></td>
							<td><s:property value="#theBean.mask"/></td>
						</tr>
					</s:iterator>		  
					</tbody>
				</table>
				
				<div class="utt-2 mgt-20">
					<a class="icon-add" href="javascript:void(0)" onclick="addRequest();return false;">新增</a>
					<a class="icon-modify" href="javascript:void(0)" onclick="modRequest();return false;" >修改</a>
					<a class="icon-del" href="javascript:void(0)" onclick="delRequest();return false;" >删除</a>
				</div>
				<table id="theTable" width="100%" class="blue-table sorttable" border="0" cellspacing="0">
					<thead>
						<tr>
							<th width="5%">编号</th>
							<th width="25%" onclick="sort(theTable,0,'string')">用户</th>   
							<th width="30%" onclick="sort(theTable,1,'string')">附加组</th>
							<th width="30%" onclick="sort(theTable,2,'string')">备注</th>
						</tr>
					</thead>
					<tbody>
					<s:iterator value="theForm.oguList" id="theBean">
						<tr>
							<td><input name="checkboxid" type="checkbox" value="<s:property value="#theBean.id"/>"/></td>
							<td><s:property value="#theBean.user_id"/></td>
							<td><s:property value="#theBean.group_id"/></td>
							<td><s:property value="#theBean.mask"/></td>
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
