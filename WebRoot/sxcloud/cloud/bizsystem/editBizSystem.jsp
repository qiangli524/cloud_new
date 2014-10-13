<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ include file="../../common/taglib.jsp"%>
<head>
	<title></title>
	<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
	<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
	<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/portalet.css" rel="stylesheet" type="text/css" />
	
	<script type="text/javascript">
	var createType = '<%=request.getAttribute("createType")%>';
	var nodeId = '<%=request.getAttribute("nodeId")%>';
	
	var api = frameElement.api;
	var w = api.opener;
	
	 api.button({
	     id:'Ok',
	     callback:submitRequest,
	     name: '确定',
	     focus: true
	 },
	 {
	     id:'cancle',
	     name: '取消'
	 });
	
	$(function(){
		if(createType == 'tree'){
			$("#busiCenterId").attr("disabled",true);
		}
	});
	
	var busiSystemObj = {};
	
	var xmlHttp; 
	var check;
	function createXmlHttp(){
    	if (window.XMLHttpRequest) {
       		xmlHttp = new XMLHttpRequest();               
    	} else {
       		xmlHttp = new ActiveXObject("Microsoft.XMLHTTP"); 
    	}
	}
	function getSelect() {
    	createXmlHttp();
    	var APP_TYPE=document.getElementById("APP_TYPE").value;
    	xmlHttp.open("GET", "ajax_getAppIp.do?APP_TYPE="+APP_TYPE, false);     
    	xmlHttp.setRequestHeader("If-Modified-Since","0");     
    	xmlHttp.send(null);
    	if (xmlHttp.readyState == 4) {
    		var pageInfo = eval("("+xmlHttp.responseText+")");     
			var  SelectNode = document.getElementById("VH_ID");
     		SelectNode.length=0;
      		SelectNode.appendChild(createSelect("","-请选择-"));
     		for(var o in pageInfo){
      			SelectNode.appendChild(createSelect(o,pageInfo[o]));
      		}
    	}
	}
	function createSelect(value,text){
  		var opt=document.createElement("option");
  		opt.setAttribute("value",value);
  		opt.appendChild(document.createTextNode(text));
  		if(check == value){
    	opt.selected=true;
  		}
  		return opt;
	}
	
    function Trim(str){
	return str.replace(/^\s+|\s+$/g,"");
	}
	
	function submitRequest(){
		api.button({ id:'Ok',disabled: true});
		if($("#SYS_NAME").val().length == 0){
			alert("请输入系统名称！");
			api.button({id:'Ok',disabled: false});
			return false;
		}
		if($("#SYS_NAME").attr("enabled")=="false"){
			alert("名称重复，请重新输入");
			api.button({id:'Ok',disabled: false});
			return false;
		}
		if($("#REGION_ID option:selected").val() == ''){
			alert("请选择接入域！");
			api.button({id:'Ok',disabled: false});
			return false;
		}
		if($("#busiCenterId option:selected").val() == ''){
			alert("请选择业务中心！");
			api.button({id:'Ok',disabled: false});
			return false;
		}
		var theForm = $("#theForm").serialize();
		busiSystemObj.theForm = theForm;
		if(createType=='list'){
			w.saveBizSystem(busiSystemObj);
		}else if(createType=='tree'){
			var flag = $("#flag").val();
			busiSystemObj.nodeId = nodeId;
			busiSystemObj.flag = flag;
			w.saveBizsys(busiSystemObj);
		}
	}
	
	function validateName(){
		var name = $("#SYS_NAME").val();
		var id = $("#SYS_ID").val();
		$.getJSON('busitree_validateName.do?name=' + encodeURI(encodeURI(name))+"&id="+id,{'time':new Date().toString()}, function(data){
		if(data.nameEnabled){
			$("#SYS_NAME").attr("enabled",true);
			var $_sysName = $("#SYS_NAME");
			$("#SYS_NAME").parent().empty().append($_sysName)
			.append('<img style="margin-left: 5px;margin-top: 5px" src="newUI/newUI/images/validate/tick.png"/>');
		}else{
			$("#SYS_NAME").attr("enabled",false);
			var $_sysName = $("#SYS_NAME");
			$("#SYS_NAME").parent().empty().append($_sysName)
			.append('<img style="margin-left: 5px;margin-top: 5px" src="newUI/newUI/images/validate/cross.png"/>')
			.append($('<br /><font color="red" style="margin-left: 10px">名称重复，请重新输入!</font>'));
		}
	});
	}
</script>
</head>
<body class="mainbody" onLoad="self.focus();document.theForm.SYS_NAME.focus()">
<s:form action="bizsystem_saveBizSystem.do" method="post" cssClass="theForm" id="theForm">
<s:hidden name="theForm.flag" id="flag"></s:hidden>
<s:hidden name="theForm.SYS_ID" id="SYS_ID"></s:hidden>
			<table width="100%" border="0" cellspacing="0" class="pop-table nosize">
				<tr>
					<td class="til">
						系统名称 <font color="red" >*</font>
					</td>
					<td>
					    <s:textfield name="theForm.SYS_NAME" cssClass="txt" id="SYS_NAME" onblur="validateName();" enabled="true"></s:textfield>                 
					</td>
				</tr>
				<tr>
					<td class="til">
						接入域<font color="red">*</font>
					</td>
					<td>
							<s:select list="theForm.regionList" name="theForm.REGION_ID" id="REGION_ID" listKey="REGION_ID" listValue="REGION_NAME" headerKey="" headerValue="-请选择-"></s:select>
					</td>
				</tr>
				<tr>
					<td class="til">
						业务中心<font color="red">*</font>
					</td>
					<td>
						<s:select list="theForm.busiCenterList" name="theForm.busiCenterId" id="busiCenterId" listKey="id" listValue="name" headerKey="" headerValue="-请选择-"></s:select>
					</td>
				</tr>
				<tr>
					<td class="til">
						状态
					</td>
					<td>
						<s:select list="#{'':'-请选择-','0':'正常','1':'异常告警','2':'已停止'}" name="theForm.STATU" id="STATU"></s:select>
					</td>
				</tr>
				<tr>
					<td class="til">
						描述<font color="red"></font>
					</td>
					 <td colspan="3">
						<s:textarea name="theForm.SYS_DESC" id="SYS_DESC" cols="60" rows="4"></s:textarea>
					</td>          
				</tr>
			</table>
</s:form>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/operHTML/jquery.operHTML.js"></script>
</body>
