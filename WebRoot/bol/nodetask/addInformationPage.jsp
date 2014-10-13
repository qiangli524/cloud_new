<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<import />
<head>
	<title></title>
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common_bol.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/formvalidate/validate.js"></script>
<script type="text/javascript">
	 $(function(){
		 var taskType = '<%=request.getAttribute("taskType") %>';
		 var  SelectNode = document.getElementById("state");
		 if(taskType==1 || taskType ==2){
			SelectNode.appendChild(createSelect(4,'申请成功'));
			SelectNode.appendChild(createSelect(5,'申请失败'));
		 }else if(taskType==3){
			SelectNode.appendChild(createSelect(21,'升级成功'));
			SelectNode.appendChild(createSelect(22,'升级失败'));
		 }
	 });
	 var api = frameElement.api;
	 var w = api.opener;
	 
	 var taskId = '<%=request.getAttribute("taskId") %>';
	 api.button({
	     id:'Ok',
	     name: '确定',
	     callback:addInformation,
	     focus: true
	 },
	 {
	     id:'cancle',
	     name: '取消'
	 });
	 
	 function createSelect(value,text){
	  		var opt=document.createElement("option");
	  		opt.setAttribute("value",value);
	  		opt.appendChild(document.createTextNode(text));
	  		return opt;
		}
	 
	 function addInformation(){
		 var state = $("#state").val();
		 var reason = $("#reason").val();
		 w.addInformation(state,reason,taskId);
	 }
</script>
<style type="text/css">
</style>
</head>
<body class="pop-body scrollbody">
	<s:form action="" method="post" id="theForm">
	<div>
		<table width="100%" border="0" cellspacing="0"
				class="pop-table nosize">
				<tr>
					<td class="til" width="30%">
						任务状态
					</td>
					<td>
						<s:select list="#{}" 
						name="state" id="state"/>
					</td>
				</tr>
				<tr>
					<td class="til">
						备注
					</td>
					<td>
						<s:textarea name="reason" id="reason" cssStyle="width:240px;height:100px"></s:textarea>
					</td>
			</tr>
		</table>
	</div>
	</s:form>
</body>
