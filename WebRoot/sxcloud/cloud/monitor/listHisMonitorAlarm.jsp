<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../common/taglib.jsp" %>
<%@ include file="../../common/link.jsp"%>
<%@ include file="../../common/view.jsp"%>
<head>
<title></title>
<link href="../cjs/ui2/nresources/common/css/default.css" rel="stylesheet" type="text/css" />
<link href="../cjs/ui2/nresources/common/css/location_tj.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/tableSort/tableSort.js"></script>
<script type="text/javascript">
	
	$(function(){
		$("#datepicker1").datepicker({showOn: 'button', buttonImage: '../cresources/default/images/date-icon.gif', buttonImageOnly: true});
		$("#datepicker2").datepicker({showOn: 'button', buttonImage: '../cresources/default/images/date-icon.gif', buttonImageOnly: true});

	})
	
	//初始化ENENT_TYPE下拉框
	$(document).ready(function(){
		var eventSys = $("#EVENT_SYS").val();// 告警系统
		var eventTypeTemp = $("#eventTypeTemp").val();// 告警类型
		if(eventSys != ''){
			var eventType=document.getElementById("EVENT_TYPE");
			eventType.options.length=0;
			eventType.add(new Option("-请选择-",""));
			eventType.add(new Option("话费量告警","21"));
			eventType.add(new Option("目录文件积压量告警","22"));
			eventType.add(new Option("错误日志告警","23"));
			eventType.add(new Option("流量查询服务告警","24"));
			eventType.add(new Option("端口收发告警","25"));
			eventType.add(new Option("提醒服务告警","26"));
			eventType.add(new Option("boss业务进程告警","27"));
			$("#EVENT_TYPE").attr("value",eventTypeTemp);}
	});
	
	function searchRequest(){
		theForm.submit();
	}
	function resetForm(theForm){
		theForm.EVENT_TYPE.value = '';
		theForm.TITILE.value = '';
		theForm.CONTENT.value = '';
	}
	function gotoBack(theForm){
		theForm.action='alarm_listMonitorAlarm.do';
		theForm.submit();
	}

	//根据告警系统修改告警类型 add by lizqd 20140904
	function changeEventType(eventSys){
		if(eventSys == ''){
			var eventType=document.getElementById("EVENT_TYPE");
			eventType.options.length=0;
			eventType.add(new Option("-请选择-",""));
			eventType.add(new Option("应用告警","0"));
			eventType.add(new Option("虚拟机告警","1"));
			eventType.add(new Option("物理主机告警","2"));
			eventType.add(new Option("机房告警","3"));
			eventType.add(new Option("进程告警","4"));
			eventType.add(new Option("BOMC工单告警","5"));
			eventType.add(new Option("HADOOP告警","6"));
			return;
			}
		if(eventSys == '1_BOSS'){
			var eventType=document.getElementById("EVENT_TYPE");
			eventType.options.length=0;
			eventType.add(new Option("-请选择-",""));
			eventType.add(new Option("话费量告警","21"));
			eventType.add(new Option("目录文件积压量告警","22"));
			eventType.add(new Option("错误日志告警","23"));
			eventType.add(new Option("流量查询服务告警","24"));
			eventType.add(new Option("端口收发告警","25"));
			eventType.add(new Option("提醒服务告警","26"));
			eventType.add(new Option("boss业务进程告警","27"));
			}
		}
</script>
</head>
<body>
<s:form action="alarm_listHisMonitorAlarm.do" method="post" cssClass="theForm" id="theForm">
	<div class="mainbody">
	<s:hidden name="theForm.EVENT_ID" id="EVENT_ID"></s:hidden>
	<s:hidden name="eventTypeTemp" id="eventTypeTemp"></s:hidden>
	<div class="pd-20 bgcolor-1">
			<h2 class="utt-1">告警历史</h2>
		<div class="bord-1 pd-10">
			<div class="clearfix filtrate-area">
			<div class="filtrate-field">
					<label class="fl">告警系统:</label>
					<s:select cssClass="select-1 fl" list="#{'':'-请选择-','1_BOSS':'BOSS系统'}" name="theForm.EVENT_SYS" id="EVENT_SYS" onchange="changeEventType(this.value)"></s:select>
				</div>
				<div class="filtrate-field">
					<label class="fl">告警类型:</label>
					<s:select cssClass="select-1 fl" list="#{'':'-请选择-','0':'应用告警','1':'虚拟机告警','2':'物理主机告警','3':'机房告警','4':'进程告警','5':'BOMC工单告警','6':'HADOOP告警'}" name="theForm.EVENT_TYPE" id="EVENT_TYPE"></s:select>
				</div>
				<div class="filtrate-field">
					<label class="fl">告警标题：</label>
					<s:textfield name="theForm.TITILE" cssClass="inpt-1 fl"
								id="TITILE" maxlength="30"></s:textfield>
				</div>
				<div class="filtrate-field">
					<label class="fl">告警内容：</label>
					<s:textfield name="theForm.CONTENT" cssClass="inpt-1 fl"
								id="CONTENT" maxlength="30"></s:textfield>
				</div>
				<div class="filtrate-field">
					<span class="ubtn-1 mgl-20"><input type="button" onclick="javascript:searchRequest()" value="查询" /></span>
					<span class="ubtn-2 mgl-20"><input type="button" onclick="javascript:resetForm(document.getElementById('theForm'))" value="重置" /></span>
				</div>
			</div>
			<div class="mgt-20">
			</div>

			<table id="theTable" width="100%" class="blue-table sorttable" border="0" cellspacing="0">
			  <thead>
			  <tr>
					<th onclick="sort(theTable,0,'string')">告警编号</th>
					<th onclick="sort(theTable,1,'string')">告警类型</th> 
					<th onclick="sort(theTable,2,'string')">告警级别</th>
					<th onclick="sort(theTable,3,'string')">当前状态</th>
					<th onclick="sort(theTable,4,'string')">告警标题</th>               
					<th onclick="sort(theTable,5,'string')">告警内容</th>
					<th onclick="sort(theTable,6,'string')">告警位置</th>
					<th onclick="sort(theTable,7,'date')">第一次告警产生时间</th>
<%--					<th onclick="sort(theTable,8,'date')">最后一次告警产生时间</th>--%>
					<th onclick="sort(theTable,9,'date')">处理时间</th>
			  </tr>
			  </thead>
			  <tbody>
			  <s:iterator value="theForm.resultList" id="theBean">
			  	<tr>
			  		<td><s:property value="#theBean.EVENT_NUM"/></td>
			  		<td>
							<s:if test="#theBean.EVENT_TYPE==0">应用告警</s:if>
				  			<s:elseif test="#theBean.EVENT_TYPE==1">虚拟机告警</s:elseif>
				  			<s:elseif test="#theBean.EVENT_TYPE==2">物理主机告警</s:elseif>
				  			<s:elseif test="#theBean.EVENT_TYPE==3">机房告警</s:elseif>
				  			<s:elseif test="#theBean.EVENT_TYPE==4">进程告警</s:elseif>
				  			<s:elseif test="#theBean.EVENT_TYPE==5">BOMC工单告警</s:elseif>
				  			<s:elseif test="#theBean.EVENT_TYPE==6">HADOOP告警</s:elseif>
				  			<s:elseif test="#theBean.EVENT_TYPE==21">话务量告警</s:elseif>
				  			<s:elseif test="#theBean.EVENT_TYPE==22">目录文件积压告警</s:elseif>
				  			<s:elseif test="#theBean.EVENT_TYPE==23">错误日志告警</s:elseif>
				  			<s:elseif test="#theBean.EVENT_TYPE==24">流量查询服务告警</s:elseif>
				  			<s:elseif test="#theBean.EVENT_TYPE==25">端口收发告警</s:elseif>
				  			<s:elseif test="#theBean.EVENT_TYPE==26">提醒服务告警</s:elseif>
				  			<s:elseif test="#theBean.EVENT_TYPE==27">boss业务进程告警</s:elseif>
			  		</td>
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
			  		<td>
			  			<span class="js_ByEllipsis" title='<s:property value="#theBean.TITILE"/>'><s:property value="#theBean.TITILE" default="无"/>
			  			</span>
			  		</td>
			  		<td>
			  			<span class="js_ByEllipsis" title='<s:property value="#theBean.CONTENT"/>'><s:property value="#theBean.CONTENT" default="无"/>
			  			</span>
			  		</td>
			  		<td><s:property value="#theBean.EVENT_LOCATION" default="无"/></td>
			  		<td><s:property value="#theBean.ALARM_TIME"/></td>
<%--			  		<td><s:property value="#theBean.INS_DATE"/></td>--%>
			  		<td><s:property value="#theBean.PRO_TIME"/></td>
			  	</tr>
			  </s:iterator>
			  </tbody>
			</table>
		<div class="pages">
		<jsp:include page="/sxcloud/inc/Pagination.jsp?formId=theForm" />
			</div>	
		 </div>
</s:form>
</div>
</body>
