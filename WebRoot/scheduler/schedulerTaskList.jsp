<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ page import="com.sitech.basd.sxcloud.rsmu.web.util.page.css.CssUtil" %>
<%@ page import="com.sitech.basd.sxcloud.rsmu.web.util.page.js.JsUtil" %>
<%@ page import="com.sitech.basd.sxcloud.rsmu.web.util.page.image.ImageUtil" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> 
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">

<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/thickbox.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/ui.datepicker.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/portalet.css" rel="stylesheet" type="text/css" />
<%!
public String getImageTag(HttpServletRequest request, String path) {
	return ImageUtil.getImageTag(request, path);
}
%>
<html:html locale="true">
<head>
<title></title>
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/thickbox.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/contextmenu.r2.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/ui/ui.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/ui16rc5.packed.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/ui/portal.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/ui.datepicker.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/js/alai_tree.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/js/alai_tree_wx.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/js/alai_tree_check.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/tableSort/tableSort.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
<script language="javascript" type="text/javascript" src="${pageContext.request.contextPath }/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
	$(function() {
		$("#datepicker1").datepicker({showOn : 'button',buttonImage : './cresources/default/images/date-icon.gif',buttonImageOnly : true});
		$("#datepicker2").datepicker({showOn : 'button',buttonImage : './cresources/default/images/date-icon.gif',buttonImageOnly : true});
		
		
		var str = $("#theForm").serialize();
		//弹出设置任务对话框
	    $("#setJob").click(function() {
	 	   $.dialog({
				id:'addTask',
				title:'添加任务',
				height:'500px',
				width:'1000px',
				content:'url:quartzScheduler_setjob.do?'+str
			});
	 	    
	    });
		
		//删除任务
		$("#deleteJob").click(function() {
			var couterNum = 0;
			var checkboxids = document.getElementsByName("theForm.taskId");
			if (checkboxids != null && checkboxids.length > 0) {
				for ( var i = 0; i < checkboxids.length; i++) {
					if (checkboxids[i].checked) {
						couterNum = couterNum + 1;
					}
				}
			}
			if (couterNum == 0) {
				$.dialog({title: '提示',content: '请勾选一条信息',width:200,height:80,ok:true,min:false,max:false});
			}else{
				$.dialog.confirm('确定要删除?', function(){
					theForm.action = 'quartzScheduler_deleteSchedulerTask.do?deleteType=1'
					theForm.submit();
				});
			}
		});
		

	    //重置表单
		$("#resetButton").click(function() {
			$("#vhName").val("");
			$("#vhIp").val("");
			$("#executeStartDate").val("");
			$("#executeEndDate").val("");
			$("#executeAction").val("");
			$("#executeState").val("");
			$("#vmType").val("");
		});

		//全选 
		$("#selectall").click(function() { 
			$("input[type='checkbox']").attr("checked", true);
		});
	});
	
	function addTaskList(url){
		 $.ajax({
			type:"POST",
	      	url:url,
	     	async: true,
	      	cache: false,
	       	success: function(msg){
	       		listTask();
	       }
		});
	}
	
	function listTask(){
		theForm.submit();
	}
	
	function searchRequest() {
		theForm.submit();
	}
</script>
</head>
<body>
<s:form action="quartzScheduler_schedulerTaskList.do" method="post" cssStyle="theForm" id="theForm">
<div class="scrollbody">
	<div class="query">
		<div class="title"><%=getImageTag(request,"query-icon.gif")%></div>
	</div>
	<div class="box on">
        <div class="query-form">
                <table width="100%" class="querytable" border="0">
                  <tr>
                    <td class="til">虚拟机名称:</td>
                    <td><s:textfield name="theForm.vhName" id="vhName" maxlength="30" cssStyle="txt"/></td>
                    <td class="til">虚拟机IP:</td>
                    <td><s:textfield name="theForm.vhIp" id="vhIp" maxlength="30" cssStyle="txt"/></td>
	                <td class="til">任务开始时间:</td>
                    <td><s:textfield name="theForm.executeStartDate" id="executeStartDate" maxlength="30" cssStyle="txt"  onFocus="WdatePicker({minDate:'1900-01-01',maxDate:'2100-10-01',dateFmt:'yyyy-MM-dd '})"/></td>
                    <td class="til">任务结束时间:</td>
                    <td><s:textfield name="theForm.executeEndDate" id="executeEndDate" maxlength="30" cssStyle="txt" onFocus="WdatePicker({minDate:$('#executeStartDate').val(),maxDate:'2100-10-01',dateFmt:'yyyy-MM-dd '})"/></td>
                  </tr><%--
                   <tr>
                    <td class="til">执行状态:</td>
                    <td ><s:select id="executeState" name="theForm.executeState"  style="width: 145px"  list="#{'':'--请选择--','0':'待执行','1':'已执行'}" /></td>
                    <td class="til">设备类型:</td>
                    <td ><s:select id="vmType" name="theForm.vmType"   style="width: 145px"    list="#{'':'--请选择--','0':'kvm','1':'vmvare','2':'ibm','3':'xen'}" /></td>
                    <td class="til">执行动作:</td>
                    <td><s:select id="executeAction" name="theForm.executeAction"    style="width: 145px"   list="#{'':'--请选择--','4':'关机','3':'开机'}" /></td>
                  </tr>                  
                  --%><tr>
                    <td colspan="8" class="btns" >
                        <div>
							<input type="button" class="thickbox btn-style02" value = "查询"  onclick="searchRequest();"  />
							<input id="resetButton" type = "button" class="btn-style02" value = "重置"   />
                        </div>
                    </td>
                  </tr>
                </table>
        </div><!--query-form end -->
	
	<div class="blue-wrap noborder">
		<div class="table-head">
		    <ul class="btns">
				<li><input id="setJob"  type="button" class="thickbox btn-style02"  value="添加任务"  /></li>
				<li><input id="deleteJob"  type="button" class="thickbox btn-style02" value="删除任务"/></li>
			</ul>
			<jsp:include page="/sxcloud/inc/Pagination.jsp?formId=theForm" />
		</div>
		<div class="table-ct">
			<table id="theTable" width="100%" class="blue-table sorttable" border="0" cellspacing="0">
			  <thead>
			  <tr>
				   <th id="selectall">全选</th>
				   <th onclick="sort(theTable,0,'string')">虚拟机名称</th>
				   <th onclick="sort(theTable,1,'string')">设备类型</th>
                   <th onclick="sort(theTable,2,'string')">虚拟机IP</th>
                   <th onclick="sort(theTable,3,'string')">执行动作</th>
                   <th onclick="sort(theTable,4,'string')">任务开始时间</th>
                   <th onclick="sort(theTable,5,'string')">任务结束时间</th>
                   <th onclick="sort(theTable,6,'string')">执行时间</th>
	               <th onclick="sort(theTable,7,'string')">执行状态</th>
			  </tr>
			  </thead>
			  <tbody>
			  <s:iterator value="theForm.resultList"   var="theBean"  >
						<tr>
							<td>
									<input  name="theForm.taskId"  type="checkbox"   value="${theBean.taskId}_${theBean.vhUuid}"/>	
							</td>
							<td><s:property value="#theBean.vhName"/></td>
							<td>
								<s:if test="#theBean.vmType==0">kvm</s:if>
							    <s:elseif test="#theBean.vmType==1">vmvare</s:elseif>
							    <s:elseif test="#theBean.vmType==2">ibm</s:elseif>
							    <s:elseif test="#theBean.vmType==3">xen</s:elseif>
							    <s:elseif test="#theBean.vmType==4">template</s:elseif>
							    <s:elseif test="#theBean.vmType==5">vmvareImage</s:elseif>
							    <s:elseif test="#theBean.vmType==6">xen 模版</s:elseif>
							    <s:else>其他</s:else>
							</td>
							<td><s:property value="#theBean.vhIp"/></td>
							<td>
								<s:if test="#theBean.executeAction==3">开机</s:if>
							    <s:elseif test="#theBean.executeAction==4">关机</s:elseif>							
							</td>
							<td><s:date name="#theBean.executeStartDate"  format="yyyy年MM月dd日"/></td>
							<td><s:date name="#theBean.executeEndDate"  format="yyyy年MM月dd日"/></td>
 							<td><s:property value="#theBean.executeCronExpression"/></td>
                            <td>
                                <s:if test="#theBean.executeState==0">待执行</s:if>
                                <s:elseif test="#theBean.executeState==1">已执行</s:elseif>
                                <s:else>执行失败</s:else>
                            </td>
						</tr>
				</s:iterator>		  
			  </tbody>
			</table>
		</div>
	</div><!--blue-wrap end -->
    </div><!--box end -->
</div>
</s:form>
</body>
</html:html>
