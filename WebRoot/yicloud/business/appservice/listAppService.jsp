<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" %>
<%@ include file="../../../sxcloud/common/taglib.jsp"%>
<%@ include file="../../../sxcloud/common/link.jsp"%>
<%@ include file="../../../sxcloud/common/view.jsp"%>
<head>
<title></title>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/tableSort/tableSort.js"></script>
<script type="text/javascript">
	
	$(function(){
		$("#datepicker1").datepicker({showOn: 'button', buttonImage: '../cresources/default/images/date-icon.gif', buttonImageOnly: true});
		$("#datepicker2").datepicker({showOn: 'button', buttonImage: '../cresources/default/images/date-icon.gif', buttonImageOnly: true});

	})

	function resetForm(theForm){
		theForm.NAME.value = '';
		theForm.SELECT_TYPE.value ='0';
		theForm.SELECT_UPPER_ID.value ='0';
		theForm.SELECT_BIND_DEVICE.value = '0';
	}

   function searchRequest() { 
		theForm.submit();
 	}
 	function addRequest() {
 	theForm.flag.value=0;
 	    theForm.action = 'business_addAppService.do'
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
 	    theForm.action = 'business_modAppService.do' 
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
 	    theForm.action = 'business_delAppService.do'  
		theForm.submit();
 	}
 	function checkSelect(){
		theForm.SELECT_TYPE.value;
		theForm.SELECT_UPPER_ID.value;
		theForm.SELECT_BIND_DEVICE.value;
	}
</script>
</head>
<body>
<s:form action="business_listAppService.do" id="theForm" method="post"
	cssClass="theForm">
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
			<td class="til">业务应用名称:</td>
			<td>
			    <s:textfield name="theForm.NAME" cssClass="txt" id="NAME"></s:textfield>
			</td>
			<td class="til">类型:</td>
			<td>
			    <s:select list="theForm.businessTypeList" listKey="NAME" listValue="NAME" headerKey="0" headerValue="-请选择-" name="theForm.SELECT_TYPE" id="SELECT_TYPE"></s:select>
			</td>
			<td class="til">所属上级业务:</td>
			<td>
			    <s:select list="theForm.upperList" listKey="ID" listValue="NAME" headerKey="0" headerValue="-请选择-" name="theForm.SELECT_UPPER_ID" id="SELECT_UPPER_ID"></s:select>
			</td>
			<td class="til">绑定设备主机ID:</td>
			<td>
			    <s:select list="theForm.bineDeviceList" listKey="ID" listValue="NAME_ZH" headerKey="0" headerValue="-请选择-" name="theForm.SELECT_BIND_DEVICE" id="SELECT_BIND_DEVICE"></s:select>
			</td>
		</tr>
		<tr>
			<td colspan="8" class="btns">
			<div><input type="button" class="thickbox btn-style02"
				value="查询" onclick="javascript:searchRequest()" /> <input
				type="button" class="btn-style02" value="重置"
				onclick="javascript:resetForm(document.getElementById('theForm'))" />
			<input type="button" class="thickbox btn-style02" value="返回"
				onclick="javascript:history.go(-1);" /></div>
			</td>
		</tr>
	</table>
	</div>
	<!--query-form end -->

	<div class="blue-wrap noborder">
	<div class="table-head">
    <jsp:include page="/sxcloud/inc/Pagination.jsp?formId=theForm" />
	<ul class="btns">
		<li><input type="button" class="thickbox btn-style02" value="添加"
			onclick="addRequest();return false;" /></li>
		<li><input type="button" class="thickbox btn-style02" value="修改"
			onclick="modRequest();return false;" /></li>
		<li><input type="button" class="thickbox btn-style02" value="删除"
			onclick="delRequest();return false;" /></li>

	</ul>
	</div>
	<div class="table-ct">
	<table width="100%" class="blue-table sorttable" border="0" id="theTable" 
		cellspacing="0">
		<thead>
			<tr>
				<th>选择</th>
				<th onclick="sort(theTable,1,'string')">业务应用名称</th>
				<th onclick="sort(theTable,2,'string')">类型</th>
				<th onclick="sort(theTable,3,'string')">所属上级业务</th>
				<th onclick="sort(theTable,4,'string')">绑定设备主机ID</th>
			</tr>
		</thead>
		<tbody>
			<s:iterator value="theForm.resultList" id="theBean">
				<tr>
					<td><input name="checkboxid" type="checkbox"
						value="<s:property value='#theBean.ID'/>" /></td>
					<td><s:property value="#theBean.NAME" /></td>
					<td><s:property value="#theBean.TYPE" /></td>
					<td><s:property value="#theBean.UPPER_ID" /></td>
					<td><s:property value="#theBean.BIND_DEVICE" /></td>
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
