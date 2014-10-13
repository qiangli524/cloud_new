<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.*"%>
<%@ include file="../../common/taglib.jsp" %>
<%@ include file="../../common/link.jsp"%>
<%@ include file="../../common/view.jsp"%>
<head>
<title></title>
<link href="../cjs/ui2/nresources/common/css/default.css" rel="stylesheet" type="text/css" />
<link href="../cjs/ui2/nresources/common/css/location_tj.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/tableSort/tableSort.js"></script>
<script type="text/javascript">
	function dealAlarmRequest(){
		var couterNum = 0;
   		var checkboxids = document.getElementsByName("checkboxid");
  		var subNum = '';
   		if(checkboxids!=null&&checkboxids.length>0){
     		for(var i=0;i<checkboxids.length;i++){
       			if(checkboxids[i].checked){
       				couterNum = couterNum + 1 ;
		   			subNum += checkboxids[i].value + ",";
     			}
    		}
		}
		theForm.EVENT_ID.value = subNum;
		if(couterNum==0){
			alert("请选择要处理的告警信息");
			return false;
		}
		theForm.action = 'alarm_dealMonitorAlarm.do';
		theForm.submit();
	}
	function hisAlarmRequest(){
		var couterNum = 0;
   		var checkboxids = document.getElementsByName("checkboxid");
  		var subNum = '';
   		if(checkboxids!=null&&checkboxids.length>0){
     		for(var i=0;i<checkboxids.length;i++){
       			if(checkboxids[i].checked){
       				couterNum = couterNum + 1 ;
		   			subNum += checkboxids[i].value + ",";
     			}
    		}
		}
		theForm.EVENT_ID.value = subNum;
		if(couterNum==0){
			alert("请选择要放入历史的告警信息");
			return false;
		}
		theForm.action = 'alarm_hisMonitorAlarm.do';
		theForm.submit();
	}
</script>
</head>
<body>
<s:form action="" method="post" id="theForm" cssClass="theForm">
<s:hidden name="theForm.EVENT_ID" id="EVENT_ID"></s:hidden>
<div class="scrollbody">
<div class="box on">
  <div class="blue-wrap noborder">
		<div class="table-head">
		    <ul class="btns">
				<li><input type="button" class="thickbox btn-style02-75" value="告警处理" onclick = "javascript:dealAlarmRequest()" /></li>
				<li><input type="button" class="thickbox btn-style02-75" value="告警消除" onclick = "javascript:hisAlarmRequest()" /></li>
			</ul>
			<jsp:include page="/sxcloud/inc/Pagination.jsp?formId=theForm" />
		</div>
 <div class="table-ct">
			<table width="100%" class="blue-table sorttable" border="0" cellspacing="0" id="theTable">
			  <thead>
			  <tr>
					<th>选择</th>
					<th onclick="sort(theTable,1,'string')">告警标题</th> 
					<th onclick="sort(theTable,2,'string')">告警类型</th> 
					<th onclick="sort(theTable,3,'string')">告警内容</th>
					<th onclick="sort(theTable,4,'string')">告警级别</th>
					<th onclick="sort(theTable,5,'string')">当前状态</th>
					<th onclick="sort(theTable,6,'string')">告警位置</th>
					<th onclick="sort(theTable,7,'date')">告警产生时间</th>
			  </tr>
			  </thead>
			  <tbody>
			  <s:iterator value="theForm.resultList" id="theBean">
			  	<tr>
			  		<td><input name="checkboxid" type="checkbox" value="<s:property value="#theBean.EVENT_ID"/>"/></td>
			  		<td><s:property value="#theBean.TITILE"/></td>
			  		<td>
			  			<s:if test="#theBean.EVENT_TYPE==0">应用告警</s:if>
			  			<s:elseif test="#theBean.EVENT_TYPE==1">虚拟机告警</s:elseif>
			  			<s:elseif test="#theBean.EVENT_TYPE==2">物理主机告警</s:elseif>
			  			<s:elseif test="#theBean.EVENT_TYPE==3">机房告警</s:elseif>
			  			<s:elseif test="#theBean.EVENT_TYPE==4">进程告警</s:elseif>
			  		</td>
			  		<td><s:property value="#theBean.CONTENT"/></td>
			  		<td>
			  			<s:if test="#theBean.EVENT_LEVEL==0">严重告警</s:if>
			  			<s:elseif test="#theBean.EVENT_LEVEL==1">主要告警</s:elseif>
			  			<s:elseif test="#theBean.EVENT_LEVEL==2">次要告警</s:elseif>
			  			<s:elseif test="#theBean.EVENT_LEVEL==3">不确定告警</s:elseif>
			  		</td>
			  		<td>
			  			<s:if test="#theBean.EVENT_STAT==0">未处理</s:if>
			  			<s:elseif test="#theBean.EVENT_STAT==1">已处理</s:elseif>
			  		</td>
			  		<td><s:property value="#theBean.EVENT_LOCATION"/></td>
			  		<td><s:property value="#theBean.ALARM_TIME"/></td>
			  	</tr>
			  </s:iterator>
			  </tbody>
			</table>
		</div>
		</div>
		</div>
		</div>
</s:form>
</body>
