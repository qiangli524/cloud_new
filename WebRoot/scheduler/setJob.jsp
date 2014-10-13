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
<%!public String getImageTag(HttpServletRequest request, String path) {
		return ImageUtil.getImageTag(request, path);
	}%>
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
	
	
		function addVm(url){
			 $.ajax({
				type:"POST",
		     	url:url,
		    	async: true,
		     	cache: false,
		      	success: function(msg){
		      		alert("添加成功");
		        	listVm();
		      }
			});
		}
		function listVm(){
			theForm.submit();
		}
		
		var api = frameElement.api;
		w = api.opener;
		$(function() {
			 api.button({
			     id:'andJob',
			     name: '添加',
			     callback:submitRequest,
			     focus: true
			 },
			 {
			     id:'cancle',
			     name: '返回'
			 });
			 
			 var str = $("#theForm").serialize();
				//添加设备
				$("#addEquipment").click(function() {
					w.$.dialog({
						id:'addEquipment',
						title:'添加设备',
						min : false,
						max : false,
						height:'500px',
						width:'1000px',
						lock:true,
						content:'url:showvm_listvm2.do?'+str
					});
				});
				
				//全选 
				$("#selectall").click(function() {
					$("input[type='checkbox']").attr("checked", true);
				});
				  //删除任务
				$("#delete").click(function() {
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
							alert("至少勾选一条信息");
							return false;
						}else{
							w.$.dialog.confirm('确定要删除?', function(){
								theForm.action = 'quartzScheduler_deleteSchedulerTask.do?deleteType=2'
								theForm.submit();
							});
						}
					});
			});
		//开始执行任务
		function submitRequest(){
			var executeStartDate=$("#executeStartDate").val();
			var executeEndDate=$("#executeEndDate").val();
			var executeAction=$("#executeAction").val();
			var executeCronExpression=$("#executeCronExpression").val();
			
			if(executeStartDate==''){
				alert("请选择任务轮询开始时间");
				return false;
			}
			if(executeEndDate==''){
				alert("请选择任务轮询结束时间");
				return false;
			}
			if(executeAction==''){
				alert("请选择执行动作");
				return false;
			}
			if(executeCronExpression==''){
				alert("请选择执行时间");
				return false;
			}
			
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
				alert("至少勾选一条信息！");
				return false;
			}else{
				var url = "quartzScheduler_setExecuteJob.do?"+$("#theForm").serialize();
				w.addTaskList(url);
			}
		}
	
</script>
</head>
<body>
<s:form action="quartzScheduler_setjob.do" method="post" cssStyle="theForm" id="theForm">
<div class="scrollbody">
	<div class="query">
		<div class="title"><%=getImageTag(request, "query-icon.gif")%></div>
	</div>
	<div class="box on">
        <div class="query-form">
                <table width="100%" class="querytable" border="0">
                  <tr>
	                <td class="til">任务轮询开始时间:<font color="red">*</font></td>
                    <td><input id="executeStartDate"name="executeStartDate"  readonly type="text" onFocus="WdatePicker({minDate:'1900-01-01',maxDate:'2100-10-01',dateFmt:'yyyy-MM-dd'})"/> </td>
                    <td class="til">任务轮询结束时间:<font color="red">*</font></td>
                    <td><input id="executeEndDate"  name="executeEndDate"  readonly  type="text" onFocus="WdatePicker({minDate:$('#executeStartDate').val(),maxDate:'2100-10-01',dateFmt:'yyyy-MM-dd'})"/> </td>
                  </tr>
                  <tr>
                    <td class="til">任务执行动作:<font color="red">*</font></td>
                    <td><s:select id="executeAction" name="executeAction"  style="width: 145px"   list="#{'':'--请选择--','4':'关机','3':'开机'}" /></td>
                    <td class="til">任务启动时间(每天)<font color="red">*</font></td>
                    <td><input id="executeCronExpression"  name="executeCronExpression"  readonly  type="text" onFocus="WdatePicker({dateFmt:'HH:mm:ss'})"/></td>
                  </tr>
                </table>
        </div><!--query-form end -->

	<div class="blue-wrap noborder">
		<div class="table-head">
		    <ul class="btns">
				<li><input id="addEquipment"  type="button" class="thickbox btn-style02"  value="添加设备"  /></li>
				<li><input id="delete"  type="button" class="thickbox btn-style02" value="删除设备"/></li>
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
