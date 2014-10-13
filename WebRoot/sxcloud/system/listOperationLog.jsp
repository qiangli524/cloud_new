<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<%@ include file="../common/link.jsp"%>
<%@ include file="../common/view.jsp"%>
<html:html locale="true">
<head>
<title></title>
<link
	href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/jQuery-Timepicker-Addon/jquery-ui.css"
	rel="stylesheet" type="text/css" />
<link
	href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/jQuery-Timepicker-Addon/jquery-ui-timepicker-addon.css"
	rel="stylesheet" type="text/css" />
<script language="javascript" type="text/javascript"
	src="<%=request.getContextPath()%>/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/tableSort/tableSort.js"></script>
<script type="text/javascript">
	
	//输起始时间，不输终止时间时，终止时间为当前时间；
	function change(){
		var d = new Date(); //获取当前时间
		var curYear = d.getFullYear()//获取当前年份
		var curMon = d.getMonth() + 1 //获取当前月份
		var curDay = d.getDate();	  //获取当前日期
		var now = curYear+"-"+(curMon<10 ? "0"+curMon:curMon)+"-"+(curDay<10 ? "0"+ curDay : curDay); 
		//var startTime = $("#start_time").val();
		var str_Date = $("#start_time").val().split("-");
		var d1 = new Date(str_Date[0],str_Date[1],str_Date[2]);// 登陆时间
		var d2 = new Date(curYear,curMon,curDay);//当前时间
		if(d1<=d2){
			$("#end_time").attr("value",now);
		}else{
			$("#end_time").attr("value","");
		}
	}
	function resetForm(theForm){
		theForm.ACCOUNT.value = '';
		theForm.NAME.value = '';
		theForm.start_time.value = '';
		theForm.end_time.value = '';
		theForm.resoursePoolObject.value = '';
	}

   function searchRequest() { 
		theForm.submit();
 	}
</script>
</head>
<body>
	<div class="mainbody">
		<s:form action="log_listOperationLogList" method="post"
			cssStyle="theForm" id="theForm">
			<s:hidden name="theForm.ID" id="ID" />
			<div class="pd-20 bgcolor-1">
				<h2 class="utt-1">操作日志</h2>
				<div class="bord-1 pd-10">
					<div class="clearfix filtrate-area">
						<div class="filtrate-field">
							<label class="vm">账号：</label>
							<s:textfield name="theForm.ACCOUNT" id="ACCOUNT"
								cssClass="inpt-1 vm" maxlength="30"></s:textfield>
						</div>
						<div class="filtrate-field">
							<label class="vm">用户名称：</label>
							<s:textfield name="theForm.NAME" id="NAME" cssClass="inpt-1 vm"
								maxlength="30"></s:textfield>
						</div>
						<div class="filtrate-field">
							<label class="vm">操作信息 ：</label>
							<s:textfield name="theForm.resoursePoolObject"
								id="resoursePoolObject" cssStyle="txt" maxlength="30"></s:textfield>
						</div>
						<div class="filtrate-field">
							<label class="vm">登入时间:从</label>
							<s:textfield name="theForm.start_time" id="start_time"
								readonly="true" onchange="change()" class="Wdate inpt-1 vm"
								cssStyle="txt"
								onFocus="WdatePicker({maxDate:'2100-10-01',dateFmt:'yyyy-MM-dd HH:mm:ss'})"></s:textfield>
							-
							<s:textfield name="theForm.end_time" id="end_time"
								readonly="true" class="Wdate inpt-1 vm" cssStyle="txt"
								onFocus="WdatePicker({maxDate:'2100-10-01',dateFmt:'yyyy-MM-dd HH:mm:ss'})"></s:textfield>
						</div>
						<div class="filtrate-field">
							<span class="ubtn-1 mgl-20"><input type="button"
								onclick="javascript:searchRequest()" value="查询" />
							</span> <span class="ubtn-2 mgl-20"><input type="button"
								onclick="javascript:resetForm(document.getElementById('theForm'))"
								value="重置" />
							</span>
						</div>
					</div>
					<div class="mgt-20"></div>
					<table id="theTable" width="100%" class="blue-table sorttable"
						border="0" cellspacing="0">
						<thead>
							<tr>
								<th onclick="sort(theTable,0,'string')">用户账号</th>
								<th onclick="sort(theTable,1,'string')">用户名称</th>
								<th onclick="sort(theTable,2,'string')">模块ID</th>
								<th onclick="sort(theTable,3,'string')">模块名称</th>
								<th onclick="sort(theTable,4,'string')">操作类别</th>
								<th onclick="sort(theTable,5,'string')">操作信息</th>
								<th onclick="sort(theTable,6,'string')">操作结果</th>
								<th onclick="sort(theTable,7,'string')">备注</th>
								<th onclick="sort(theTable,8,'date')">操作时间</th>
							</tr>
						</thead>
						<tbody>
							<s:iterator value="theForm.resultList" id="theBean">
								<tr>
									<td><s:text name="#theBean.ACCOUNT" /></td>
									<td><s:text name="#theBean.NAME" /></td>
									<td><s:text name="#theBean.FUNCID_STR" /></td>
									<td><s:text name="#theBean.FUNNAME" /></td>
									<td><s:if test="#theBean.OPERTYPE==1">
												增加
												</s:if> <s:elseif test="#theBean.OPERTYPE==2">
													删除
												</s:elseif> <s:elseif test="#theBean.OPERTYPE==3">
												修改
												</s:elseif> <s:elseif test="#theBean.OPERTYPE==4">
												查询
												</s:elseif></td>
									<td><s:text name="#theBean.MESSAGE" /></td>
									<td><s:if test="#theBean.RESULT==0">
												失败
											</s:if> <s:elseif test="#theBean.RESULT==1">
												成功
											</s:elseif></td>
									<td><s:if test="theBean.REMARK!=null">
											<s:text name="#theBean.REMARK" />
										</s:if> <s:else>
												无
											</s:else></td>
									<td><s:text name="#theBean.OPERTIME" /></td>
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
</html:html>
