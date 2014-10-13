<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<%@ include file="../common/link.jsp"%>
<%@ include file="../common/view.jsp"%>
<html:html locale="true">
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
        start_time=theForm.datepicker1.value;  
		pre_endtime=theForm.datepicker2.value;  
		
		if(start_time==''&&pre_endtime!=''){
		alert("请输入异常查询开始时间！！！");
		return false;
		}
		if(start_time!=''&&pre_endtime==''){
		alert("请输入异常查询结束时间！！！");
		return false;
		}
       if(start_time!=''&&pre_endtime!=''){
		year=start_time.substr(0,4);
        month=start_time.substr(5,2);
        day=start_time.substr(8,2); 
        date=new Date(year,month-1,day); 

        year1=pre_endtime.substr(0,4);
        month1=pre_endtime.substr(5,2);
        day1=pre_endtime.substr(8,2); 
        date1=new Date(year1,month1-1,day1); 
        
    if(date.valueOf()>date1.valueOf()){
             alert("查询结束时间不能大于查询开始时间!");
             return false;
       }
    }
		theForm.submit();
 	}
</script>
</head>
<body>
	<s:form action="alarm_upgradeAlarmList.do" method="post" id="theForm">
		<s:hidden name="theForm.ID" />
		<div class="scrollbody">
			<div class="query">
				<div class="title"><%=getImageTag(request, "query-icon.gif")%></div>
			</div>
			<div class="box on">
				<div class="query-form">
					<table width="100%" class="querytable" border="0">
						<tr>
							<td class="til">
								异常起止时间:从
							</td>
							<td>
								<s:textfield name="theForm.start_time" 
									readonly="true" id="datepicker1" />

							</td>
							<td class="til">
								至
							</td>
							<td>

								<s:textfield name="theForm.end_time" 
									readonly="true"  id="datepicker2" />
							</td>
						</tr>
						<tr>
							<td colspan="8" class="btns">
								<div>
									<input type="button" class="thickbox btn-style02" value="查询"
										onclick="javascript:searchRequest()" />
									<input type="button" class="btn-style02" value="重置"
										onclick="javascript:resetForm(document.getElementById('theForm'))" />
								</div>
							</td>
						</tr>
					</table>
				</div>
				<!--query-form end -->

				<div class="blue-wrap noborder">
					<div class="table-head">
						<jsp:include page="/sxcloud/inc/Pagination.jsp?formId=theForm" />
					</div>
					<div class="table-ct">
						<table id="theTable" width="100%" class="blue-table sorttable" border="0"
							cellspacing="0">
							<thead>
								<tr>
									<th>
										编号
									</th>
									<th onclick="sort(theTable,1,'string')">
										应用编号
									</th>
									<th onclick="sort(theTable,2,'string')">
										主机编号
									</th>
									<th onclick="sort(theTable,3,'date')">
										告警时间
									</th>
									<th onclick="sort(theTable,4,'string')">
										操作命令
									</th>
									<th onclick="sort(theTable,5,'string')">
										操作结果
									</th>
									<th onclick="sort(theTable,6,'string')">
										类型
									</th>
								</tr>
							</thead>
							<tbody>
									<s:iterator id="theBean" value="theForm.resultList">
										<tr>
											<td>
												<input name="checkboxid" type="checkbox"
													value="<s:property value="#theBean.ID"/>" />
											</td>
											<td>
												<s:text name="#theBean.APPID" />
											</td>
											<td>
												<s:text name="#theBean.HOSTID" />
											</td>
											<td>
												<s:text name="#theBean.INSERTTIME" />
											</td>
											<td>
												<s:text name="#theBean.COMMAND" />
											</td>
											<td>
												<s:text name="#theBean.RESULT"/>
											</td>
											<td>
												<s:if test="#theBean.ALARM_LEVEL==1">严重告警</s:if>
												<s:if test="#theBean.ALARM_LEVEL==2">主要告警</s:if>
												<s:if test="#theBean.ALARM_LEVEL==3">次要告警</s:if>
												<s:if test="#theBean.ALARM_LEVEL==4">警告告警</s:if>
												<s:if test="#theBean.ALARM_LEVEL==5">不确定告警</s:if>
											</td>
										</tr>
									</s:iterator>
							</tbody>
						</table>
					</div>
				</div>
				<!--blue-wrap end -->
			</div>
			<!--box end -->
		</div>
	</s:form>
</body>
</html:html>
