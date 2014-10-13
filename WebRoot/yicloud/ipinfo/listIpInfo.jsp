<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../sxcloud/common/taglib.jsp"%>
<%@ include file="../../sxcloud/common/link.jsp"%>
<%@ include file="../../sxcloud/common/view.jsp"%>
<head>
<title></title>
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/js/checkbox.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/tableSort/tableSort.js"></script>
<script type="text/javascript">
	
	$(function(){
		$("#datepicker1").datepicker({showOn: 'button', buttonImage: '../cresources/default/images/date-icon.gif', buttonImageOnly: true});
		$("#datepicker2").datepicker({showOn: 'button', buttonImage: '../cresources/default/images/date-icon.gif', buttonImageOnly: true});

	})
	
   function searchRequest() { 
   		theForm.action = 'ipinfo_listIpinfo.do' 
		theForm.submit();
 	}
 	function addRequest() {
 	    theForm.action = 'ipinfo_addIpinfo.do' 
		theForm.submit();
 	}
 	function modRequest() { 
 	    var couterNum = 0;
 	    var checkboxids = document.getElementsByName("checkboxid");
 	    if(checkboxids!=null&&checkboxids.length>0){
 	    for(var i=0;i<checkboxids.length;i++){
 	      if(checkboxids[i].checked){
 	      couterNum = couterNum + 1 ;
 	      theForm.IP_ID.value = checkboxids[i].value;
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
 	    theForm.action = 'ipinfo_modIpinfo.do' 
		theForm.submit();
 	}
 	function delRequest() {
 	var couterNum = 0;
 	    var checkboxids = document.getElementsByName("checkboxid");
 	    if(checkboxids!=null&&checkboxids.length>0){
 	    for(var i=0;i<checkboxids.length;i++){
 	      if(checkboxids[i].checked){
 	      couterNum = couterNum + 1 ;
 	      theForm.IP_ID.value = checkboxids[i].value;
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
 	    
 	    theForm.action = 'ipinfo_delIpinfo.do'  
		theForm.submit();
 	}
</script>
</head>
<body>
<s:form action="ipinfo_listIpinfo.do" id="theForm" method="post"
	cssClass="theForm">
	<s:hidden name="theForm.flag" id="flag"></s:hidden>
	<s:hidden name="theForm.IP_ID" id="IP_ID"></s:hidden>
	<div class="scrollbody">
	<div class="query">
	<div class="title"><%=getImageTag(request, "query-icon.gif")%></div>
	</div>
	<div class="box on">
	<div class="query-form">
	<table width="100%" class="querytable" border="0">
		<tr>
			<td class="til">是否使用:</td>
			<td><s:select list="theForm.usedList" listKey="CODE"
				listValue="NAME" headerKey="0" headerValue="-请选择-" name="theForm.ISUSED" id="ISUSED"></s:select>
			</td>
			<td class="til">是否阻塞:</td>
			<td><s:select list="theForm.blockList" listKey="CODE"
				listValue="NAME" headerKey="0" headerValue="-请选择-" name="theForm.ISBLOCKED" id="ISBLOCKED"></s:select>
			</td>
			<td class="til">IP地址:</td>
			<td><s:textfield name="theForm.IPADDRESS" cssClass="txt"
				id="IPADDRESS"></s:textfield></td>
			<td class="til">IP状态:</td>
			<td><s:select list="theForm.typeList" listKey="CODE"
				listValue="NAME" headerKey="0" headerValue="-请选择-" name="theForm.IP_TYPE" id="IP_TYPE"></s:select>
			</td>
		</tr>
		<tr>
			<td colspan="8" class="btns">
			<div><input type="button" class="thickbox btn-style02"
				value="查询" onclick="javascript:searchRequest()" /> <input
				type="button" class="btn-style02" value="重置"
				onclick="javascript:void(document.getElementById('theForm').reset());return false;" />
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
				<th onclick="sort(theTable,2,'string')">是否使用</th>
				<th onclick="sort(theTable,3,'string')">是否阻塞</th>
				<th onclick="sort(theTable,4,'string')">IP地址</th>
				<th onclick="sort(theTable,5,'date')">时间</th>
				<th onclick="sort(theTable,6,'string')">IP状态</th>
				<th onclick="sort(theTable,7,'string')">网络ID</th>
			</tr>
		</thead>
		<tbody>
			<s:iterator value="theForm.resultList" id="theBean">
				<tr>
					<td><input name="checkboxid" type="checkbox"
						value="<s:property value='#theBean.IP_ID'/>" /></td>
					<td><s:property value="#theBean.IP_ID" /></td>
					<td><s:property value="#theBean.ISUSEDNAME" /></td>
					<td><s:property value="#theBean.ISBLOCKEDNAME" /></td>
					<td><s:property value="#theBean.IPADDRESS" /></td>
					<td><s:property value="#theBean.INS_DATE" /></td>
					<td><s:property value="#theBean.IP_TYPENAME" /></td>
					<td><s:property value="#theBean.NET_ID" /></td>
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
