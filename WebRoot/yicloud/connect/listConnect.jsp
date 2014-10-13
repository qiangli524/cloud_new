<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../sxcloud/common/taglib.jsp"%>
<%@ include file="../../sxcloud/common/link.jsp"%>
<%@ include file="../../sxcloud/common/view.jsp"%>
<head>
<title></title>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/tableSort/tableSort.js"></script>
<script type="text/javascript">
	
	$(function(){
		$("#datepicker1").datepicker({showOn: 'button', buttonImage: '../cresources/default/images/date-icon.gif', buttonImageOnly: true});
		$("#datepicker2").datepicker({showOn: 'button', buttonImage: '../cresources/default/images/date-icon.gif', buttonImageOnly: true});

	})

	function resetForm(theForm){
		theForm.INTERFACE_A.value = "0";
		theForm.INTERFACE_Z.value = "0";
		theForm.STATUS.value = "0";
	}

	function searchRequest() { 
	 	 theForm.action = 'connectinfo_listConnect.do' 
		theForm.submit();
 	}
 	
	function addRequest() {
 	    theForm.action = 'connectinfo_addConnect.do' 
		theForm.submit();
	}
	function modRequest() { 
 	    var couterNum = 0;
 	    var checkboxids = document.getElementsByName("checkboxid");
 	    if(checkboxids!=null&&checkboxids.length>0){
 	    for(var i=0;i<checkboxids.length;i++){
 	      if(checkboxids[i].checked){
 	      couterNum = couterNum + 1 ;
 	      theForm.ID.value = checkboxids[i].value;
 	      }
 	    }
 	    theForm.flag.value = 1;
 	    }
 	    if(couterNum==0){
 	    alert("请勾选需要修改信息！");
 	    return false ;
 	    }else if(couterNum>1){
 	    alert("一次只能处理单条信息");
 	    return false ;
 	    }
 	    theForm.action = 'connectinfo_modConnect.do';
		theForm.submit();
 	}
 	function delRequest() {
 	var couterNum = 0;
 	    var checkboxids = document.getElementsByName("checkboxid");
 	    if(checkboxids!=null&&checkboxids.length>0){
 	    for(var i=0;i<checkboxids.length;i++){
 	      if(checkboxids[i].checked){
 	      couterNum = couterNum + 1 ;
 	      theForm.ID.value = checkboxids[i].value;
 	      }
 	    }
 	    }
 	    if(couterNum==0){
 	    alert("请勾选需要删除功能信息！");
 	    return false ;
 	    }else if(couterNum>1){
 	    alert("一次只能删除单条功能信息");
 	    return false ;
 	    }
 	    theForm.action = 'connectinfo_delConnect.do'  
		theForm.submit();
 	}

</script>
</head>
<body>
<s:form action="connectinfo_listConnect.do" method="post" id="theForm"
	cssStyle="theForm">
	<s:hidden name="theForm.ID" id="ID"></s:hidden>
	<s:hidden name="theForm.flag" id="flag"></s:hidden>
	<div class="scrollbody">
	<div class="query">
	<div class="title"><%=getImageTag(request, "query-icon.gif")%></div>
	</div>
	<div class="box on">
	<div class="query-form">
	<table width="100%" class="querytable" border="0">
		<tr>
			<td class="til">A端外围接口:</td>
			<td>
			    <s:select list="theForm.interfaceList" listKey="ID" listValue="INTERFACE_A_NAME" headerKey="0" headerValue="-请选择-" name="theForm.INTERFACE_A" id="INTERFACE_A"></s:select>
			</td>
			<td class="til">Z端外围接口:</td>
			<td>
			    <s:select list="theForm.interfaceList" listKey="ID" listValue="INTERFACE_A_NAME" headerKey="0" headerValue="-请选择-" name="theForm.INTERFACE_Z" id="INTERFACE_Z"></s:select>
			</td>
			<td class="til">连通状态:</td>
			<td>
			    <s:select list="theForm.connectstatusList" listKey="CODE" listValue="NAME" headerKey="0" headerValue="-请选择-" name="theForm.STATUS" id="STATUS"></s:select>
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
		<li><input type="button" class="thickbox btn-style02" value="添加"
			onclick="addRequest();return false;" /></li>
		<li><input type="button" class="thickbox btn-style02" value="修改"
			onclick="modRequest();return false;" /></li>
		<li><input type="button" class="thickbox btn-style02" value="删除"
			onclick="delRequest();return false;" /></li>
	</ul>
	<jsp:include page="/sxcloud/inc/Pagination.jsp?formId=theForm" /></div>
	<div class="table-ct">
	<table width="100%" class="blue-table sorttable" border="0" id="theTable"
		cellspacing="0">
		<thead>
			<tr>
				<th>选择</th>
				<th onclick="sort(theTable,1,'string')">序号</th>
				<th onclick="sort(theTable,2,'string')">A端外围接口</th>
				<th onclick="sort(theTable,3,'string')">Z端外围接口</th>
				<th onclick="sort(theTable,4,'string')">连通状态</th>
			</tr>
		</thead>
		<tbody>
			<s:iterator value="theForm.resultList" id="theBean">
				<tr>
					<td><input name="checkboxid" type="checkbox"
						value="<s:property value='#theBean.ID'/>" /></td>
					<td><s:property value="#theBean.ID" /></td>
					<td><s:property value="#theBean.INTERFACE_A_NAME" /></td>
					<td><s:property value="#theBean.INTERFACE_Z_NAME" /></td>
					<td><s:property value="#theBean.STATUSNAME" /></td>
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
