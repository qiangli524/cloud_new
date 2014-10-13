<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.*"%>
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
		theForm.DEVICE_ID.value = "0";
		theForm.CODE.value = '';
		theForm.STATUS.value = "0";
		theForm.TYPE.value = "0";
	}

	function searchRequest() { 
		theForm.action = 'interfacesinfo_listInterface.do' 
		theForm.submit();
 	}
 	
	function addRequest() {
 	    theForm.action = 'interfacesinfo_addInterface.do' 
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
 	    theForm.action = 'interfacesinfo_modInterface.do';
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
 	    theForm.action = 'interfacesinfo_delInterface.do'  
		theForm.submit();
 	}
</script>
</head>
<body>
<s:form action="interfacesinfo_listInterface.do" id="theForm"
	method="post" cssClass="theForm">
	<s:hidden name="theForm.flag" id="flag"></s:hidden>
	<s:hidden name="theForm.ID" id="ID"></s:hidden>
	<div class="scrollbody">
	<div class="query">
	<div class="title"><%=getImageTag(request, "query-icon.gif")%></div>
	</div>
	<div class="box on">
	<div class="query-form">
	<table width="100%" class="querytable" border="0">
		<tr>
			<td class="til">设备主机ID:</td>
		    <td>
			    <s:select list="theForm.deviceList" listKey="ID" listValue="NAME_ZH" headerKey="0" headerValue="-请选择-" name="theForm.DEVICE_ID" id="DEVICE_ID"></s:select>
			</td>
			<td class="til">设备外围编号:</td>
			<td>
			    <s:textfield name="theForm.CODE" cssClass="txt" id="CODE"></s:textfield>
			</td>
			<td class="til">端口状态:</td>
			<td>
			    <s:select list="theForm.statusList" listKey="CODE" listValue="NAME" headerKey="0" headerValue="-请选择-" name="theForm.STATUS" id="STATUS"></s:select>
			</td>
			<td class="til">外围接口类型:</td>
			<td>
			    <s:select list="theForm.typeList" listKey="CODE" listValue="NAME" headerKey="0" headerValue="-请选择-" name="theForm.TYPE" id="TYPE"></s:select>
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
				<th onclick="sort(theTable,2,'string')">设备主机ID</th>
				<th onclick="sort(theTable,3,'string')">设备外围编号</th>
				<th onclick="sort(theTable,4,'string')">端口状态</th>
				<th onclick="sort(theTable,5,'string')">外围端口类型</th>
			</tr>
		</thead>
		<tbody>
			<s:iterator value="theForm.resultList" id="theBean">
				<tr>
					<td><input name="checkboxid" type="checkbox"
						value="<s:property value='#theBean.ID'/>" /></td>
					<td><s:property value="#theBean.ID" /></td>
					<td><s:property value="#theBean.DEVICENAME" /></td>
					<td><s:property value="#theBean.CODE" /></td>
					<td><s:property value="#theBean.STATUSNAME" /></td>
					<td><s:property value="#theBean.TYPENAME" /></td>
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
