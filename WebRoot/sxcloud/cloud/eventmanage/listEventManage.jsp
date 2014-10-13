<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../common/taglib.jsp" %>
<%@ include file="../../common/link.jsp"%>
<%@ include file="../../common/view.jsp"%>
<head>
<title></title>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/tableSort/tableSort.js"></script>
<script type="text/javascript">
	
	$(function(){
		$("#datepicker1").datepicker({showOn: 'button', buttonImage: 'sxcloud/cresources/default/images/date-icon.gif', buttonImageOnly: true});
		$("#datepicker2").datepicker({showOn: 'button', buttonImage: 'sxcloud/cresources/default/images/date-icon.gif', buttonImageOnly: true});

	})

	function resetForm(theForm){
		theForm.datepicker1.value = '';
		theForm.datepicker2.value = '';
	}

	function searchRequest() { 
		theForm.submit();
 	}
</script>
</head>
<body>
<s:form action="event_listEventManage" method="post" cssClass="theForm" id="theForm">
<div class="scrollbody">
	<div class="query">
		<div class="title"><%=getImageTag(request,"query-icon.gif")%></div>
	</div>
	<div class="box on">
        <div class="query-form">
                <table width="100%" class="querytable" border="0">
                  <tr>
                    <td class="til">开始日期:</td>
                    <td>
                    	<s:textfield name="theForm.start_time" size="10" readonly="true" cssClass="txt" id="datepicker1"></s:textfield>
                    </td>
                    <td class="til">结束日期:</td>
                    <td>
                    <s:textfield name="theForm.end_time" size="10" readonly="true" cssClass="txt" id="datepicker2"></s:textfield>
                    </td>
                  </tr>
                  <tr>
                    <td colspan="8" class="btns">
                        <div>
							<input type = "button" class="thickbox btn-style02" value = "查询" onclick = "javascript:searchRequest()" />
							<input type = "button" class="btn-style02" value = "重置" onclick = "javascript:resetForm(document.getElementById('theForm'))" />
                        </div>
                    </td>
                  </tr>
                </table>
        </div><!--query-form end -->
        <div class="table-head">
			<jsp:include page="/sxcloud/inc/Pagination.jsp?formId=theForm" />
		</div>
		<div class="table-ct">
			<table id="theTable" width="100%" class="blue-table sorttable" border="0" cellspacing="0">
			  <thead>
			  <tr>
					<th onclick="sort(theTable,0,'string')">事件编号</th>
					<th onclick="sort(theTable,1,'string')">严重性</th> 
					<th onclick="sort(theTable,2,'string')">事件名称</th>
					<th onclick="sort(theTable,3,'string')">原发站</th>               
					<th onclick="sort(theTable,4,'date')">发生时间</th>
			  </tr>
			  </thead>
			  <tbody>
			  <s:iterator value="theForm.resultList" id="theBean">
			  	<tr>
			  		<td><s:property value="#theBean.EVENT_ID"/></td>
			  		<td>
			  			<s:if test="#theBean.SERIOUS==0">信息</s:if>
			  			<s:elseif test="#theBean.SERIOUS==1">警告</s:elseif>
			  			<s:elseif test="#theBean.SERIOUS==2">严重</s:elseif>
			  		</td>
			  		<td><s:property value="#theBean.EVENT_INFO"/></td>
			  		<td><s:property value="#theBean.SOURCE_HAPPEN"/></td>
			  		<td><s:property value="#theBean.INS_DATE"/></td>
			  	</tr>
			  </s:iterator>
			  </tbody>
			</table>
		</div>
	</div><!--blue-wrap end -->
    </div><!--box end -->
</s:form>
</body>
