<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../sxcloud/common/taglib.jsp" %>
<%@ include file="../../sxcloud/common/view.jsp"%>
<head>
<title></title>
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common_bol.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework_bol.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/js/checkbox.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=iblue"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/tableSort/tableSort.js"></script>
<script type="text/javascript">
	
	$(function(){
		$("#datepicker1").datepicker({showOn: 'button', buttonImage: '../cresources/default/images/date-icon.gif', buttonImageOnly: true});
		$("#datepicker2").datepicker({showOn: 'button', buttonImage: '../cresources/default/images/date-icon.gif', buttonImageOnly: true});

	})
	
	function resetForm(theForm){
		theForm.queryState.value = 0;
		theForm.queryType.value = 0;
	}

   function searchRequest() { 
		theForm.submit();
 	}
	
   function applyRequest(){
	   $.dialog({
			id:'applyRequest',
			title:'申请资源',
			width: '400px',
			height: '225px',
			lock:true,
			content: 'url:bolnodetask_applyRequest.do'
			});
   }
   
   function applyResource(HOST_NAME,NODE_TYPE,NODE_IP,NODE_CAPABILITY,USER,PASSWORD){
	   closeDialog("applyRequest");
	   var theForm = document.getElementById("theForm");
	   theForm.action = "bolnodetask_applyResource.do?HOST_NAME="+HOST_NAME +"&NODE_TYPE="+NODE_TYPE
			   			+"&NODE_IP="+NODE_IP+"&NODE_CAPABILITY="+NODE_CAPABILITY+"&USER="+USER+"&PASSWORD="+PASSWORD;
	   theForm.submit();
   }
	   
	function addInformationRequest(){
		var taskId = '';
		var taskType = 0;
		var couterNum = 0;
 	    var checkboxids = document.getElementsByName("checkboxid");
 	    if(checkboxids!=null&&checkboxids.length>0){
 	    for(var i=0;i<checkboxids.length;i++){
 	      if(checkboxids[i].checked){
 	     	couterNum = couterNum + 1 ;
 	     	taskId = checkboxids[i].value;
 	     	taskType = $(checkboxids[i]).attr("taskType");
 	      }
 	    }
 	    }
 	    if(couterNum==0){
	 	    alert("请勾选需要删除的任务！");
	 	    return false ;
 	    }
		$.dialog({
			id:'addInformationRequest',
			title:'补录信息',
			width: '500px',
			height: '225px',
			lock:true,
			content: 'url:bolnodetask_addInformationRequest.do?taskId='+taskId +"&taskType=" + taskType
			});
   }
   
	function addInformation(state,reason,taskId){
		closeDialog("addInformationRequest");
		var theForm = document.getElementById("theForm");
		theForm.action = "bolnodetask_addInformation.do?state="+state +"&reason="+reason+"&taskId="+taskId;
		theForm.submit();
	}
	
	function upgradeRequest(){
		$.dialog({
			id:'upgradeRequest',
			title:'升级',
			width: '400px',
			height: '225px',
			lock:true,
			content: 'url:bolnodetask_upgradeRequest.do'
			});
	}
	
	function upgradeResource(NODE_IP,HOST_NAME,UPDATE_VERSION,UPDATE_MODE,programName){
	   closeDialog("upgradeRequest");
	   var theForm = document.getElementById("theForm");
	   theForm.action = "bolnodetask_upgradeResource.do?NODE_IP="+NODE_IP +"&HOST_NAME=" + HOST_NAME + "&UPDATE_VERSION=" + UPDATE_VERSION + "&UPDATE_MODE=" + UPDATE_MODE+ "&programName=" + programName;
	   theForm.submit();
	}
	
	function releaseRequest(){
		$.dialog({
			id:'releaseRequest',
			title:'升级',
			width: '400px',
			height: '225px',
			lock:true,
			content: 'url:bolnodetask_releaseRequest.do'
			});
	}
	
	function releaseResource(NODE_IP,NUMBER){
		closeDialog("releaseRequest");
		var theForm = document.getElementById("theForm");
		theForm.action = "bolnodetask_releaseResource.do?NODE_IP="+NODE_IP +"&NUMBER="+NUMBER;
		theForm.submit();
	}
	
	function refresh(){
		 location.replace(location.href);
	}
	
	function changeList(){
		var  SelectNode = $("#queryState")[0];
		var queryState = $("#queryType").val();
		if(queryState==1 || queryState==2){
			SelectNode.length=0;
			SelectNode.appendChild(createSelect(0,'--请选择--'));
			SelectNode.appendChild(createSelect(7,'申请成功提交'));
			SelectNode.appendChild(createSelect(8,'申请失败提交'));
		}else if(queryState==3){
			SelectNode.length=0;
			SelectNode.appendChild(createSelect(0,'--请选择--'));
			SelectNode.appendChild(createSelect(21,'升级成功'));
			SelectNode.appendChild(createSelect(22,'升级失败'));
			SelectNode.appendChild(createSelect(23,'申请状态切换'));
			SelectNode.appendChild(createSelect(24,'状态切换成功'));
			SelectNode.appendChild(createSelect(25,'状态切换失败'));
		}
	}
	
	function createSelect(value,text){
  		var opt=document.createElement("option");
  		opt.setAttribute("value",value);
  		opt.appendChild(document.createTextNode(text));
  		return opt;
	}
   function closeDialog(dialogName){
		$.dialog.list[dialogName].close();
	}
</script>
<style type="text/css">
.font-more{ width:400px;height:20px;line-height:20px;overflow: hidden;
white-space: nowrap;
display: block;
-o-text-overflow: ellipsis; 
text-overflow: ellipsis;}
.font-more1{ width:150px;height:20px;line-height:20px;overflow: hidden;
white-space: nowrap;
display: block;
-o-text-overflow: ellipsis; 
text-overflow: ellipsis;}
</style>
</head>
<body>
<s:form action="bolnodetask_listNodeTask.do" id="theForm" method="post" cssClass="theForm">
	<div class="scrollbody">
	<div class="query">
	<div class="title"><%=getImageTag(request, "query-icon.gif")%></div>
	</div>
	<div class="box on">
	<div class="query-form">
	<table width="100%" class="querytable" border="0">
		<tr>
			<td class="til">任务类型:</td>
			<td><s:select list="#{'1':'申请资源','2':'释放资源','3':'升级'}" name="queryType" id="queryType"
				headerKey="0" headerValue="--请选择--" onchange="changeList()"></s:select>
			</td>
			<td class="til">任务状态:</td>
			<td><s:select list="#{'7':'申请成功提交','8':'申请失败提交','21':'升级成功','22':'升级失败','23':'申请状态切换','24':'状态切换成功','25':'状态切换失败'}" 
				name="queryState" id="queryState" headerKey="0" headerValue="--请选择--"></s:select>
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
		<li><input type="button" class="thickbox btn-style02-75" value="申请资源"
			onclick="applyRequest();return false;" /></li>
		<li><input type="button" class="thickbox btn-style02-75" value="释放资源"
			onclick="releaseRequest();return false;" /></li>
		<li><input type="button" class="thickbox btn-style02" value="升级"
			onclick=" upgradeRequest();return false;" /></li>
		<li><input type="button" class="thickbox btn-style02-75" value="补录信息"
			onclick="addInformationRequest();return false;" /></li>
		<li><input type="button" class="thickbox btn-style02-75" value="刷新"
			onclick="refresh();return false;" /></li>
	</ul>
	<jsp:include page="/sxcloud/inc/Pagination.jsp?formId=theForm" /></div>

	<div class="table-ct">
	<table width="100%" class="blue-table sorttable" border="0" id="theTable"
		cellspacing="0">
		<thead>
			<tr>
				<th>选择</th>
				<th onclick="sort(theTable,1,'string')">序号</th>
				<th onclick="sort(theTable,2,'string')">任务类型</th>
				<th onclick="sort(theTable,3,'string')">任务状态</th>
				<th onclick="sort(theTable,4,'string')">任务轨迹</th>
				<th onclick="sort(theTable,5,'string')">任务描述</th>
			</tr>
		</thead>
		<tbody>
			<s:iterator value="resultList" id="theBean">
				<tr>
					<td><input name="checkboxid" type="checkbox"
						value="<s:property value='#theBean.taskId'/>" taskType ="<s:property value='#theBean.taskType'/>" />
					</td>
					<td><s:property value="#theBean.taskId" /></td>
					<td>
						<s:if test="#theBean.taskType==1">
							申请资源
						</s:if>
						<s:elseif test="#theBean.taskType==2">
							释放资源
						</s:elseif>
						<s:elseif test="#theBean.taskType==3">
							 升级
						</s:elseif>
						<s:elseif test="#theBean.taskType==4">
							 注册设备
						</s:elseif>
						<s:elseif test="#theBean.taskType==6 || #theBean.taskType==7">
							 激活
						</s:elseif>
						<s:else>
							无
						</s:else>
					</td>
					<td style="width: 150px">
						<s:if test="#theBean.taskState==1">
							申请
						</s:if>
						<s:elseif test="#theBean.taskState==2">
							提交成功
						</s:elseif>
						<s:elseif test="#theBean.taskState==3">
							提交失败
						</s:elseif>
						<s:elseif test="#theBean.taskState==4">
							<a style="color: black;" class="font-more1" title="备注:<s:property value="#theBean.taskAddMess"/>">
								申请成功
							</a>
						</s:elseif>
						<s:elseif test="#theBean.taskState==5">
							<a style="color: black;" class="font-more1" title="备注:<s:property value="#theBean.taskAddMess"/>">
								申请失败
							</a>
						</s:elseif>
						<s:elseif test="#theBean.taskState==6">
							申请超时失效
						</s:elseif>
						<s:elseif test="#theBean.taskState==7">
							申请成功提交
						</s:elseif>
						<s:elseif test="#theBean.taskState==8">
							申请失败提交
						</s:elseif>
						
						<s:elseif test="#theBean.taskState==20">
							升级
						</s:elseif>
						<s:elseif test="#theBean.taskState==21">
							<a style="color: black;" class="font-more1" title="备注:<s:property value="#theBean.taskAddMess"/>" >
								升级成功
							</a>
						</s:elseif>
						<s:elseif test="#theBean.taskState==22">
							<a style="color: black;" class="font-more1" title="备注:<s:property value="#theBean.taskAddMess"/>">
								升级失败
							</a>
						</s:elseif>
						<s:elseif test="#theBean.taskState==23">
							申请状态切换 
						</s:elseif>
						<s:elseif test="#theBean.taskState==24">
							<font color="#00CD00">状态切换成功</font>
						</s:elseif>
						<s:elseif test="#theBean.taskState==25">
							<font color="red">状态切换失败 </font>
						</s:elseif>
						
						<s:elseif test="#theBean.taskState==26">
							进程无积压状态 
						</s:elseif>
						<s:elseif test="#theBean.taskState==27">
							申请进程停用
						</s:elseif>
						<s:elseif test="#theBean.taskState==28">
							停用切换成功
						</s:elseif>
						<s:elseif test="#theBean.taskState==29">
							停用切换失败 
						</s:elseif>
						<s:else>
							无
						</s:else>
					</td>
					<td style="width: 150px">
						<a style="color: black;" class="font-more1" title="<s:property value="#theBean.taskTrack"/>">
							<s:property value="#theBean.taskTrack"/>
						</a>
					</td>
					<td style="width: 400px">
						<a style="color: black;" class="font-more" title="<s:property value="#theBean.taskDesc"/>">
							<s:property value="#theBean.taskDesc"/>
						</a>
					</td>
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
