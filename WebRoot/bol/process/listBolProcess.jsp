<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../sxcloud/common/taglib.jsp" %>
<%@ include file="../../sxcloud/common/view.jsp"%>
<head>
<title></title>
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common_bol.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework_bol.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/js/checkbox.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=iblue"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/tableSort/tableSort.js"></script>
<script type="text/javascript">
	
	$(function(){
		$("#datepicker1").datepicker({showOn: 'button', buttonImage: '../cresources/default/images/date-icon.gif', buttonImageOnly: true});
		$("#datepicker2").datepicker({showOn: 'button', buttonImage: '../cresources/default/images/date-icon.gif', buttonImageOnly: true});
	});

	function resetForm(theForm){
		theForm.queryStatus.value = -1;
		theForm.queryName.value = '';
	}

	function searchRequest() { 
		theForm.submit();
	}

	function addRequest(){
		$.dialog({
			id:'applyRequest',
			title:'添加进程',
			width: '450px',
			height: '250px',
			lock:true,
			content: 'url:bolprocess_addRequest.do?oper=add'
		});
	}

	function updateRequest(){
		var id = '';
		var couterNum = 0;
		var checkboxids = document.getElementsByName("checkboxid");
		if(checkboxids!=null&&checkboxids.length>0){
			for(var i=0;i<checkboxids.length;i++){
				if(checkboxids[i].checked){
					couterNum = couterNum + 1 ;
					id = checkboxids[i].value;
				}
			}
		}
		if(couterNum==0){
			alert("请勾选需要更新的进程！");
			return false ;
		}
		$.dialog({
			id:'updateRequest',
			title:'修改进程',
			width: '450px',
			height: '250px',
			lock:true,
			content: 'url:bolprocess_updateRequest.do?oper=update&id='+id
		});
	}

	function saveBolProcess(processVO,oper){
		if(oper=='add'){
			closeDialog("applyRequest");
		}else{
			closeDialog("updateRequest");
		}
		var theForm = document.getElementById("theForm");
		theForm.action = "bolprocess_saveBolProcess.do?processVO.name="+ processVO.name + "&processVO.status=" + processVO.status 
				+"&processVO.descrip="+ processVO.descrip + "&processVO.nodeId=" + processVO.nodeId + "&processVO.programId=" + processVO.programId
				+ "&processVO.id=" +  processVO.id + "&oper=" + oper;
		theForm.submit();
	}

	function deleteRequest(){
		if(confirm("确认要删除进程？")){
			var id = '';
			var couterNum = 0;
			var checkboxids = document.getElementsByName("checkboxid");
			if(checkboxids!=null&&checkboxids.length>0){
				for(var i=0;i<checkboxids.length;i++){
					if(checkboxids[i].checked){
						couterNum = couterNum + 1 ;
						id = checkboxids[i].value;
					}
				}
			}
			if(couterNum==0){
				alert("请勾选需要删除的进程！");
				return false ;
			}
			var theForm = document.getElementById("theForm");
			theForm.action = "bolprocess_delBolProcess.do?processVO.id="+ id;
			theForm.submit();
		}
	}

	function refresh(){
		location.replace(location.href);
	}

	function closeDialog(dialogName){
		$.dialog.list[dialogName].close();
	}
</script>
<style type="text/css">
.font-more{ width:200px;height:20px;line-height:20px;overflow: hidden;
white-space: nowrap;
display: block;
-o-text-overflow: ellipsis; 
text-overflow: ellipsis;}
</style>
</head>
<body>
<s:form action="bolprocess_listBolProcess.do" id="theForm" method="post" cssClass="theForm">
	<div class="scrollbody">
	<div class="query">
	<div class="title"><%=getImageTag(request, "query-icon.gif")%></div>
	</div>
	<div class="box on">
	<div class="query-form">
	<table width="100%" class="querytable" border="0">
		<tr>
			<td class="til">应用程序名:</td>
			<td>
				<s:textfield name="queryName" id="queryName" maxlength="100" style="width:150px;   height:18px;"></s:textfield>
			</td>
			<td class="til">应用状态:</td>
			<td>
				<s:select name="queryStatus" list="#{'-1':'-请选择-','1':'正常','0':'异常'}"  id="queryStatus" style="width:150px;   height:20px;"></s:select>
			</td>
		</tr>
		<tr>
			<td colspan="8" class="btns">
			<div><input type="button" class="thickbox btn-style02"
				value="查询" onclick="javascript:searchRequest()" /> <input
				type="button" class="btn-style02" value="重置"
				onclick="javascript:resetForm(document.getElementById('theForm'))" />
			</div>
			</td>
		</tr>
	</table>
	</div>
	<!--query-form end -->
	
	<div class="blue-wrap noborder">
	<div class="table-head">
	<ul class="btns">
		<li><input type="button" class="thickbox btn-style02-75" value="增加"
			onclick="addRequest();return false;" /></li>
		<li><input type="button" class="thickbox btn-style02-75" value="修改"
			onclick="updateRequest();return false;" /></li>
		<li><input type="button" class="thickbox btn-style02" value="删除"
			onclick=" deleteRequest();return false;" /></li>
		<li><input type="button" class="thickbox btn-style02-75" value="刷新"
			onclick="refresh();return false;" /></li>
	</ul>
	<jsp:include page="/sxcloud/inc/Pagination.jsp?formId=theForm" /></div>

	<div class="table-ct">
	<table width="100%" class="blue-table sorttable" border="0" id="theTable"
		cellspacing="0">
		<thead>
			<tr>
				<th>选择</th>
				<th onclick="sort(theTable,1,'string')">进程标识</th>
				<th onclick="sort(theTable,2,'string')">进程名</th>
				<th onclick="sort(theTable,3,'string')">进程状态</th>
				<th onclick="sort(theTable,4,'string')">节点名</th>
				<th onclick="sort(theTable,5,'string')">应用名</th>
				<th onclick="sort(theTable,6,'string')">状态日期</th>
				<th onclick="sort(theTable,7,'string')">进程描述</th>
			</tr>
		</thead>
		<tbody>
			<s:iterator value="resultList" id="theBean">
				<tr>
					<td><input name="checkboxid" type="checkbox" value="<s:property value='#theBean.id'/>"/>
					</td>
					<td><s:property value="#theBean.id" /></td>
					<td><s:property value="#theBean.name" /></td>
					<td>
						<s:if test="#theBean.status==1">
							正常
						</s:if>
						<s:else>
							<font color="red">异常</font>
						</s:else>
					</td>
					<td><s:property value="#theBean.nodeName" /></td>
					<td><s:property value="#theBean.programName" /></td>
					<td><s:property value="#theBean.lastupdate" /></td>
					<td style="width: 200px">
						<a style="color: black;" class="font-more" title="<s:property value="#theBean.descrip"/>">
							<s:property value="#theBean.descrip"/>
						</a>
					</td>
				</tr>
			</s:iterator>
		</tbody>
	</table>
	</div>

	</div>
	<!--blue-wrap end --></div>
	<!--box end --></div>
</s:form>
</body>
