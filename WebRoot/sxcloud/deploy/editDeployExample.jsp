<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<head>
<title></title>
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>

<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/portalet.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/ui/ui.js"></script>

<script type="text/javascript">
	var operType = '<%=request.getAttribute("operType")%>';
	var nodeId = '<%=request.getAttribute("nodeId")%>';
	var dialogName = '<%=request.getAttribute("dialogName")%>';
	
	 $(window).load(function(){
	 	var ID = $("#ID").val();
    	if(operType == 'tree'){
			$("#buttons").hide();
			if(ID==0){
				$("#APPID").attr("disabled",true);
			}
		}
		if(ID!=0){
			$("#APPID").attr("disabled",true);
			$("#HOSEUSERID").attr("disabled",true);
			$("#HOSTID").attr("disabled",true);
		}
		var dep_type = $("#DEPLOYEXAMPLE_TYPE option:selected").val();
		if(dep_type==1){
			$("#appVisitPathTr").hide();
		}
    });
	
	function validateForm(){
		 if(theForm.exampleName.value==''){
			alert("请输入部署实例名称后，再保存！");
			theForm.exampleName.focus;
			return false;
		}
		 if($("#exampleName").attr("nameEnabled")=='false'){
			alert("名称重复，请重新输入！");
			theForm.exampleName.focus;
			return false;
		}
	    if(theForm.APPID.value==0){
			alert("请选择应用后，再保存");
			theForm.APPID.focus;
			return false;
		}
	    if(theForm.HOSTID.value==''){
			alert("请选择部署主机后，再保存");
			theForm.HOSTID.focus;
			return false;
		}
	    if(theForm.HOSEUSERID.value==''){
			alert("请选择部署主机用户后，再保存");
			theForm.HOSEUSERID.focus;
			return false;
		}
		if(theForm.TRUST_FLAG.value==''){
			alert("请选择信任关系后，再保存");
			theForm.TRUST_FLAG.focus;
			return false;
		}
		if(theForm.DEPLOYEXAMPLE_TYPE.value==0){
			if(theForm.appVisitPath.value==''){
				alert("请输入访问地址后，再保存");
				theForm.appVisitPath.focus;
				return false;
			}
			if($("#appVisitPath").attr("appVisitPathEnabled")=='null'){
				alert("正在检测访问地址...");
				return false;
			}
			if($("#appVisitPath").attr("appVisitPathEnabled")=='false'){
				alert("该访问地址不可用，请更换!");
				theForm.appVisitPath.focus;
				return false;
			}
		}
	    if(theForm.deployPath.value==''){
			alert("请输入应用路径后，再保存");
			theForm.deployPath.focus;
			return false;
		}
	<%--    if($("#deployPath").attr("deployPathEnabled")=='null'){
	    	alert("正在检测应用路径...");
			return false;
		}
	    if($("#deployPath").attr("deployPathEnabled")=='false'){
			alert("该应用路径在基准主机上已存在，请更换!");
			theForm.deployPath.focus;
			return false;
		}
	    if(theForm.DEPLOYFILEPATH.value==''){
			alert("请输入文件路径后，再保存");
			theForm.DEPLOYFILEPATH.focus;
			return false;
		}--%>
		return true;
	}
	
	function submitForm(){
		var api = null;
		var w = null;
		if(operType=='tree'){
			api = frameElement.api;
			w = api.opener;
			api.button({id:'OK',disabled: true});
		}
		var res = validateForm();
		if(!res){
			if(operType=='tree'){
				api.button({id:'OK',disabled: false});
			}
			return false;
		}
		if(operType=='list'){
			$("input[value='保存']").attr("disabled",true);
			theForm.action = 'dep_saveDeployExample.do?operType=list';
			theForm.submit();
		}else{
			var deployExampleObj = {};
			var theFormData = $("#theForm").serialize();
			var ID = $("#ID").val();
			deployExampleObj.theFormData = theFormData;
			deployExampleObj.nodeId = nodeId;
			deployExampleObj.dialogName = dialogName;
			deployExampleObj.ID = ID;
			w.saveDeployExample(deployExampleObj);
		}
	}
	
	function queryUsersByHostId(){
		  var form=document.getElementById('theForm');
		  var html="";
		  var id=$("#HOSTID option:selected").val();
	      $.getJSON("usermanage_getUserListByHostId.do?hostId="+id,{'time':new Date().toString()},function(data){
			for(var i=0;i<data.length;i++)
			{
				html=html+"<option value='"+data[i].id+"'>"+data[i].username+"</option>";		
			}
			$("#HOSEUSERID").html(html);
		   });
		   validateDeployPath();
	}
	
	function deployTypeChange(){
		var dep_type = $("#DEPLOYEXAMPLE_TYPE option:selected").val();
		if(dep_type==0){
			$("#appVisitPathTr").show();
		}else{
			$("#appVisitPathTr").hide();
			$("#appVisitPath").attr("value","");
		}
	}
	
	function validateName(){
		var name = $("#exampleName").val();
		if(name==''){
			return false;
		}
		var id=$("#ID").val();
		var $_exampleName = $("#exampleName");
		$("#exampleName").parent().empty().append($_exampleName);
		$.getJSON('busitree_validateName.do?name=' + encodeURI(encodeURI(name))+"&id="+id,{'time':new Date().toString()}, function(data){
		if(data.nameEnabled){
			$("#exampleName").attr("nameEnabled",'true');
			var $_exampleName = $("#exampleName");
			$("#exampleName").parent().empty().append($_exampleName)
			.append('<img style="margin-left: 5px;margin-top: 5px" src="newUI/newUI/images/validate/tick.png"/>');
		}else{
			$("#exampleName").attr("nameEnabled",'false');
			var $_exampleName = $("#exampleName");
			$("#exampleName").parent().empty().append($_exampleName)
			.append('<img style="margin-left: 5px;margin-top: 5px" src="newUI/newUI/images/validate/cross.png"/>')
			.append('<br /><font color="red" style="margin-left: 10px">名称重复，请重新输入!</font>');
		}
	});
	}
	
	function validateAppVisitPath(){
		var appVisitPath = $("#appVisitPath").val();
		var id = $("#ID").val();
		if(appVisitPath==''){
			return	false;
		}
		$("#appVisitPath").attr("appVisitPathEnabled",'null');
		var $_appVisitPath = $("#appVisitPath");
		$("#appVisitPath").parent().empty().append($_appVisitPath);
		//验证访问地址是否有效 例如http://0-255.0-255.0-255.0-255:0-65535/
		var re  = /^(http\:\/\/(([0-9]\.)|([1-9][0-9]\.)|([1-2][0-4][0-9]\.)|(25[0-5]\.)){3}(([0-9])|([1-9][0-9])|([1-2][0-4][0-9])|(25[0-5])){1}\:([0-9]|([1-9]{1}([0-9]{0,3}))|([1-5][0-9]{4})|(6[1-4][0-9]{3})|(65[1-4][0-9]{2})|(655[1-2][0-9])|(6553[0-5])){1}\/)/;
		var bool = re.test(appVisitPath);
		if(bool){
			$.getJSON("dep_validateAppVisitPath.do?appVisitPath="+appVisitPath+"&id="+id,{'time':new Date().toString()}, function(data){
				if(!data.appVisitPathEnabled){
					$("#appVisitPath").attr("appVisitPathEnabled",'false');
					var $_appVisitPath = $("#appVisitPath");
					$("#appVisitPath").parent().empty().append($_appVisitPath)
					.append('<img style="margin-left: 5px;margin-top: 5px" src="newUI/newUI/images/validate/cross.png"/>')
					.append('<br /><font color="red" style="margin-left: 10px">该访问地址已存在，请更换!</font>');
				}else{
					$("#appVisitPath").attr("appVisitPathEnabled",'true');
					var $_appVisitPath = $("#appVisitPath");
					$("#appVisitPath").parent().empty().append($_appVisitPath)
					.append('<img style="margin-left: 5px;margin-top: 5px" src="newUI/newUI/images/validate/tick.png"/>');
				 }
			});
		}else{
			$("#appVisitPath").attr("appVisitPathEnabled",'false');
			var $_appVisitPath = $("#appVisitPath");
			$("#appVisitPath").parent().empty().append($_appVisitPath)
			.append('<img style="margin-left: 5px;margin-top: 5px" src="newUI/newUI/images/validate/cross.png"/>')
			.append('<br /><font color="red" style="margin-left: 10px">该访问地址格式不合法!例如：http://127.0.0.1:8080/cloud</font>');
		}
		
	}
	
	function validateDeployPath(){
		var deployPath = $("#deployPath").val();
		if(deployPath==''){
			return	false;
		}
		if($("#HOSTID").val().length ==0){
			alert("请先选择基准主机！");
			$("#HOSTID").focus();
			return false;
		}
		if($("#HOSEUSERID").val().length ==0){
			alert("请先选择基准主机用户！");
			$("#HOSEUSERID").focus();
			return false;
		}
		$("#deployPath").attr("deployPathEnabled",'null');
		var $_deployPath = $("#deployPath");
		$("#deployPath").parent().empty().append($_deployPath);
		<%--
		$.getJSON("dep_validateDeployPath.do?"+$("#theForm").serialize(),{'time':new Date().toString()}, function(data){
			if(!data.examplePathEnabled){
				$("#deployPath").attr("deployPathEnabled",'false');
				var $_deployPath = $("#deployPath");
				$("#deployPath").parent().empty().append($_deployPath)
				.append('<img style="margin-left: 5px;margin-top: 5px" src="newUI/newUI/images/validate/cross.png"/>')
				.append('<br /><font color="red" style="margin-left: 10px">该应用路径在基准主机上已存在，请更换!</font>');
			}else{
				$("#deployPath").attr("deployPathEnabled",'true');
				var $_deployPath = $("#deployPath");
				$("#deployPath").parent().empty().append($_deployPath)
				.append('<img style="margin-left: 5px;margin-top: 5px" src="newUI/newUI/images/validate/tick.png"/>');
			 }
		});
		--%>
	}
	
</script>
</head>
<body class="pop-body scrollbody">
	<s:form action="dep_saveDeployExample.do" method="post" cssStyle="theForm" id="theForm">
	<s:hidden name="theForm.ID" id="ID"></s:hidden>
	<s:hidden name="theForm.SYS_ID" id="SYS_ID"></s:hidden>
	<s:hidden name="theForm.DEPLOY_FLAG" id="DEPLOY_FLAG"></s:hidden>
		<table width="100%" border="0" cellspacing="0"
			class="pop-table nosize">
			<tr>
				<td class="til">部署实例名称 <font color="red">*</font></td>
				<td>
					<s:textfield name="theForm.exampleName" id="exampleName" cssClass="txt" onblur="validateName();" nameEnabled="true"></s:textfield>
				</td>
				<td class="til">选择基准应用 <font color="red">*</font></td>
				<td>
<%--						<s:if test="theForm.ID==0">--%>
						<s:select list="theForm.appList" listKey="ID"
							name="theForm.APPID" id="APPID" listValue="APPNAME"
							headerKey="0" headerValue="-请选择-">
						</s:select>
<%--					</s:if> --%>
<%--					<s:elseif test="theForm.ID>0">--%>
<%--						<s:text name="theForm.APPNAME" />--%>
<%--						<s:hidden name="theForm.APPID" id="APPID"></s:hidden>--%>
<%--					</s:elseif>--%>
				</td>
			</tr>
			<tr>
				<td class="til"> 部署主机 <font color="red">*</font></td>
				<td><s:select list="theForm.hostList" listKey="ID" listValue="IP"
						name="theForm.HOSTID" id="HOSTID" headerKey=""
						headerValue="-请选择-" onchange="queryUsersByHostId();">
					</s:select></td>
				<td class="til">部署主机用户 <font color="red">*</font></td>
				<td>
					<s:select headerKey="" headerValue="请选择" list="theForm.HOSEUSERLIST" name="theForm.HOSEUSERID"
						id="HOSEUSERID" listKey="id" listValue="username" onblur="validateDeployPath();">
					</s:select>
				</td>
			</tr>
			<tr>
				<td class="til"> 信任关系 <font color="red">*</font></td>
				<td><s:select list="#{'0':'不使用','1':'使用'}" name="theForm.TRUST_FLAG" id="TRUST_FLAG" 
					headerKey="" headerValue="-请选择-"></s:select></td>
				<td class="til">实例类型<font color="red">*</font></td>
				<td><s:select list="#{'0':'WEB','1':'非WEB'}" name="theForm.DEPLOYEXAMPLE_TYPE" id="DEPLOYEXAMPLE_TYPE" 
				 	onchange="deployTypeChange();"></s:select></td>
			</tr>
			<tr id="appVisitPathTr">
				<td class="til">访问地址 <font color="red">*</font></td>
				<td colspan="3">
					<s:textarea name="theForm.appVisitPath" id="appVisitPath" cssClass="txt" cols="81" rows="5"  appVisitPathEnabled="true"></s:textarea>
				</td>
			</tr>
			<tr>
				<td class="til">应用路径 <font color="red">*</font></td>
				<td colspan="3">
					<s:textarea name="theForm.deployPath" id='deployPath' cssStyle="txt" cols="81" rows="5" onblur="validateDeployPath();" deployPathEnabled="true"/>
					<br/><font color="red" size="1">例:如果部署一个web项目,应用路径为:/tomcat/webapps</font>
				</td>
			</tr>
<%--			<tr>
				<td class="til">文件路径 <font color="red">*</font></td>
				<td colspan="3"><s:textarea name="theForm.DEPLOYFILEPATH" cols="81"
						rows="5" cssStyle="txt" id="DEPLOYFILEPATH" /></td>
			</tr>--%>
			<tr id="buttons">
				<td colspan="4" class="btnCenter"><input type="button"
					class="thickbox btn-style02" value="确定"
					onclick="javascript:submitForm(document.getElementById('theForm'));return false;" />
					<input type="button" class="thickbox btn-style02" value="重置"
					onclick="javascript:void(document.getElementById('theForm').reset());return false;" />
					<input type="button" class="thickbox btn-style02" value="返回"
					onclick="window.history.back()" /></td>
			</tr>

		</table>
	</s:form>
</body>

</html:html>
