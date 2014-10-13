<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<head>
<title></title>
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/portalet.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript">
	var operType = '<%=request.getAttribute("operType")%>';
	var nodeId = '<%=request.getAttribute("nodeId")%>';
	var dialogName = '<%=request.getAttribute("dialogName")%>';
	//0校验未进行 1校验失败2校验成功
	var checkResult = '0';
	var api;
	var w;
	
    $(window).load(function(){
    	var theForm = document.getElementById('theForm');
		var ID = theForm.ID.value;
    	if(operType == 'tree'){
			$("#operButtons").hide();
			if(ID==0){
				$("#SYS_ID").attr("disabled",true);
			}
		}
		if(ID != 0){
			$("#HOSEUSERID").attr("disabled",true);
			$("#STRATEGY").attr("disabled",true);
			$("#SYS_ID").attr("disabled",true);
		}
		try {
			api = frameElement.api;
			w = api.opener;
			api.button({id:'OK',disabled: true});
		} catch(e) {
			
		}
    });
    	
    
    function Trim(str){
		return str.replace(/^\s+|\s+$/g,"");
	}
	
	//验证表单
	function verifyForm(){
		var thisForm = document.getElementById("theForm");
		 if(thisForm.APPNAME.value.length ==0){
		     alert("应用名称不能为空！");
		     thisForm.APPNAME.focus;
		     return false;
	    }
	    if($("#APPNAME").attr("nameEnabled")=="false"){
	    	alert("名称重复，请重新输入!");
		     thisForm.APPNAME.focus;
		     return false;
	    }
	    if(thisForm.STRATEGYTYPE.value.length==0){
	    	alert("部署策略类型不能为空！");
	     	thisForm.STRATEGYTYPE.focus;
	     	return false;
	    }
	    if(thisForm.APP_IDENTIFY.value.length==0){
	      thisForm.APP_IDENTIFY.focus;
	      alert("应用英文标识不能为空！");
	      return false;
	    }
	    if($("#APP_IDENTIFY").attr("appIdentifyEnabled")=='false'){
	      thisForm.APP_IDENTIFY.focus;
	      alert("应用英文标识已经存在,请从新输入!");
	      return false;
	    }
	    if(thisForm.APP_IDENTIFY.value.length>10){
	      thisForm.APP_IDENTIFY.focus;
	      alert("应用英文标识长度不能大于10！");
	      return false;
	    }
	    if(checkStr(thisForm.APP_IDENTIFY.value)){
	       thisForm.APP_IDENTIFY.focus;
	      alert("英文标识输入不合法,只能为字母和数字！");
	      return false;
	    }
	     if(thisForm.STRATEGY.value.length ==0){
	     alert("基准主机不能为空！");
	     thisForm.STRATEGY.focus;
	     return false  ;
	    }
	     if(thisForm.HOSEUSERID.value.length ==0){
	     alert("部署主机用户不能为空！");
	     thisForm.HOSEUSERID.focus;
	     return false  ;
	    }
	    if(thisForm.SYS_ID.value.length ==0){
	     alert("所属业务系统不能为空！");
	     thisForm.SYS_ID.focus;
	     return false ;
	    }
	    if(thisForm.BASEPATH.value.length ==0){
	     alert("基准主机应用路径不能为空！");
	     thisForm.BASEPATH.focus;
	     return false ;
	    }
	    <%--  检测路径无意义，不要再检测 --duangh
	    if($("#BASEPATH").attr("basepathEnabled") == "null"){
	     	 alert("正在检测基准主机应用路径...");
		     return false ;
	    }
	    if($("#BASEPATH").attr("basepathEnabled") == "false"){
	     alert("该路径在基准主机上已存在，请更换!");
	     thisForm.BASEPATH.focus;
	     return false ;
	    }
	   --%>
	    return true;
	}
	
	function submitRequest(thisForm){
	  	var result = verifyForm();
	  	if(!result){
	  		return false;
	  	}
	    var appidentify=thisForm.APP_IDENTIFY.value;
	    var appidentifybf=thisForm.APP_IDENTIFY_BF.value;
        thisForm.action='app_saveApp.do?operType=list';  
        thisForm.submit();
	}
	
	function addSysAppOperateFromTree(){
		api.button({id:'OK',disabled: true});
		var result = verifyForm();
		if(!result){
			api.button({id:'OK',disabled: false});
			return false;
		}
		var busiAppObj = {};
		busiAppObj.nodeId = nodeId;
		busiAppObj.dialogName = dialogName;
		busiAppObj.ID = $("#ID").val();
		var appidentify=$("#APP_IDENTIFY").val();
	    var appidentifybf=$("#APP_IDENTIFY_BF").val();
        var theFormData = $("#theForm").serialize();
		busiAppObj.theFormData = theFormData;
		w.saveApp(busiAppObj);
	}
	
	
	/* 检测输入的字符串是否符合要求 */
	function isnumber(str)
	 {
	  var number_chars = "1234567890";
	        var i;
	        for (i=0;i<str.length;i++)
	        {
	            if (number_chars.indexOf(str.charAt(i))==-1) 
	            return false;
	        }
	        return true;
	}
	
	function checkStr(str){
 	  var   pattern   =/^([a-zA-Z0-9])*$/gi;   
    if(pattern.test(str)) {
         return false
    }
    else{  
       return true;
    } 
	  
	}
	/*检测应用标识是否唯一*/
	function checkAPP_IDENTIFY(str){
		 $.get("/queryAjx_app_identify.do?appIdentify="+str,{'time':new Date().toString()}, function(data, textStatus){
			   if(data=='no'){
			      return false;
			   }else{
			      return true;
			   }
	      });
	}
	
	function queryUsersByHostId(){
		  var form=document.getElementById('theForm');
		  var html="";
		  var id=form.STRATEGY.value;
	      $.getJSON("usermanage_getUserListByHostId.do?hostId="+id,{'time':new Date().toString()},function(data){
	    	if(data.length<1){
	    		html="<option value=''></option>";
	    		$("#HOSEUSERID").html(html);
	    	}else{
	    		for(var i=0;i<data.length;i++)
				{
					html=html+"<option value='"+data[i].id+"'>"+data[i].username+"</option>";		
				}
	    		$("#HOSEUSERID").html(html);
				validateBasepath();
	    	}
		   });
	}
	
	function validateName(){
		var name = $("#APPNAME").val();
		if(name==''){
			return false;
		}
		var id=$("#ID").val();
		var $_APPNAME = $("#APPNAME");
		$("#APPNAME").parent().empty().append($_APPNAME);
		$.getJSON('busitree_validateName.do?name=' + encodeURI(encodeURI(name))+"&id="+id,{'time':new Date().toString()}, function(data){
		if(data.nameEnabled){
			$("#APPNAME").attr("nameEnabled",'true');
			var $_APPNAME = $("#APPNAME");
			$("#APPNAME").parent().empty().append($_APPNAME)
			.append('<img style="margin-left: 5px;margin-top: 5px" src="newUI/newUI/images/validate/tick.png"/>');
		}else{
			$("#APPNAME").attr("nameEnabled",'false');
			var $_APPNAME = $("#APPNAME");
			$("#APPNAME").parent().empty().append($_APPNAME)
			.append('<img style="margin-left: 5px;margin-top: 5px" src="newUI/newUI/images/validate/cross.png"/>')
			.append('<br /><font color="red" style="margin-left: 10px">名称重复，请重新输入!</font>');
		}
	});
	}
	
	function validateAppIdentify(){
		 var appidentify=$("#APP_IDENTIFY").val();
		 var id=$("#ID").val();
		 if(appidentify==''){
		 	return false;
		 }
		 var $_APP_IDENTIFY = $("#APP_IDENTIFY");
		 $("#APP_IDENTIFY").parent().empty().append($_APP_IDENTIFY);
		 $.getJSON("app_queryAjx_app_identify.do?appIdentify="+appidentify+"&id="+id,{'time':new Date().toString()}, function(data){
			   if(data.enabled == 'false'){
					$("#APP_IDENTIFY").attr("appIdentifyEnabled",'false');
					var $_APP_IDENTIFY = $("#APP_IDENTIFY");
					$("#APP_IDENTIFY").parent().empty().append($_APP_IDENTIFY)
					.append('<img style="margin-left: 5px;margin-top: 5px" src="newUI/newUI/images/validate/cross.png"/>')
					.append('<br /><font color="red" style="margin-left: 10px">应用英文标识已经存在,请从新输入!</font>');
			   }else{
			   		$("#APP_IDENTIFY").attr("appIdentifyEnabled",'true');
					var $_APP_IDENTIFY = $("#APP_IDENTIFY");
					$("#APP_IDENTIFY").parent().empty().append($_APP_IDENTIFY)
					.append('<img style="margin-left: 5px;margin-top: 5px" src="newUI/newUI/images/validate/tick.png"/>');
			   }
	      });
	}
	
	function validateBasepath(){
		<%-- --%>
		var basepath = $("#BASEPATH").val();
		if(basepath==''){
			return	false;
		}
		if($("#STRATEGY").val().length ==0){
			alert("请先选择基准主机！");
			$("#STRATEGY").focus();
			return false;
		}
		if($("#HOSEUSERID").val().length ==0){
			alert("请先选择基准主机用户！");
			$("#HOSEUSERID").focus();
			return false;
		}
		$("#BASEPATH").attr("basepathEnabled",'null');
		var $_BASEPATH = $("#BASEPATH");
		$("#BASEPATH").parent().empty().append($_BASEPATH);
		//mask("路径检测中.....");
		$.ajax({
  			type:"POST",
  			url:"app_validateBasepath.do?"+$("#theForm").serialize(),
  			async: false,
  			cache:false,
  			dataType:"json",
  			success: function(data){
   				if(data=="sshException"){
   					changeBasepathMess(false,"检测路径异常！");
   				}else if(data=="paraException"){
   					changeBasepathMess(false,"检测路径异常！");
   				}else if(data=="invalidNameOrPassException"){
   					changeBasepathMess(false,"用户名或密码错误,请检查用户名、密码配置！");
   				}else if(data=="unkonwnHostException"){
   					changeBasepathMess(false,"未知主机，请检测主机ip地址是否存在！");
   				}else if(data=="unableConnectException"){
   					changeBasepathMess(false,"不能连接该主机，请检查！");
   				}else if(data=="checkPath"){
   					changeBasepathMess(false,"请检查路径合法性！如：/root/etc");
   				}else if(data=="existFalse"){
   					changeBasepathMess(false,"该路径在主机上不存在，请更换或创建!");
   				}else{
   					if(data=="repeatTrue"){
   						changeBasepathMess(false,"该路径已使用，请更换!!");
   					}else{
   						changeBasepathMess(true);
   					 }
   				}
  			}
		});
	}
	
	function changeBasepathMess(enable,mess){
		if(!enable){
			checkResult = '1';
			$("#BASEPATH").attr("basepathEnabled",'false');
			var $_BASEPATH = $("#BASEPATH");
			$("#BASEPATH").parent().empty().append($_BASEPATH)
			.append('<img style="margin-left: 5px;margin-top: 5px" src="newUI/newUI/images/validate/cross.png"/>')
			.append('<br /><font color="red" style="margin-left: 10px">'+mess+'</font>');
		}else{
			$("#BASEPATH").attr("basepathEnabled",'true');
			var $_BASEPATH = $("#BASEPATH");
			$("#BASEPATH").parent().empty().append($_BASEPATH)
			.append('<img style="margin-left: 5px;margin-top: 5px" src="newUI/newUI/images/validate/tick.png"/>');
			checkResult = '2';
			api.button({id:'OK',disabled: false});
		}
		//removeMask();
	}
	
</script>
</head>
<body class="pop-body scrollbody">
	<s:form action="app_saveApp.do" method="post" cssStyle="theForm"
		id="theForm">
		<s:hidden name="theForm.STRATEGYTYPE" id="STRATEGYTYPE" value="2"></s:hidden>
		<s:hidden name="theForm.STATUS" id="STATUS" value="1"></s:hidden>
		<s:hidden name="theForm.ID" id="ID"></s:hidden>
		<s:hidden name="theForm.APP_IDENTIFY_BF" id="APP_IDENTIFY_BF"></s:hidden>
		<s:hidden name="theForm.oldSysId" id="oldSysId"></s:hidden>
		<table width="100%" border="0" cellspacing="0"
			class="pop-table nosize">
			<tr>
				<td class="til">应用名称 <font color="red">*</font></td>
				<td><s:textfield name="theForm.APPNAME" cssStyle="txt"
						id="APPNAME" onblur="validateName();" nameEnabled="true"/>
				</td>
				<td class="til">应用英文标识 <font color="red">*</font></td>
				<td><s:textfield name="theForm.APP_IDENTIFY" cssStyle="txt"
						id="APP_IDENTIFY" onblur="validateAppIdentify();" appIdentifyEnabled="true"/>
				</td> 
			</tr>
			<tr>
				<td class="til">
					<div id="tfire">基准主机<font color="red">*</font></div></td>
				<td><s:select list="theForm.busiHostList" listKey="ID" listValue="IP"
						name="theForm.STRATEGY" id="STRATEGY" headerKey=""
						headerValue="-请选择-" onchange="queryUsersByHostId();">
					</s:select></td>
				<td class="til">基准主机用户 <font color="red">*</font></td>
				<td>
					<s:select headerKey="" headerValue="请选择" list="theForm.HOSEUSERLIST" name="theForm.HOSEUSERID"
						id="HOSEUSERID" listKey="id" listValue="username" onchange="validateBasepath();">
					</s:select>
				</td>
			</tr>
			<tr>
				<td class="til">所属业务系统 <font color="red">*</font></td>
				<td><s:select list="theForm.bizList" listKey="SYS_ID"
						name="theForm.SYS_ID" id="SYS_ID" listValue="SYS_NAME"
						headerKey="" headerValue="-请选择-">
					</s:select></td>
				<td class="til">&nbsp;</td>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td class="til">基准主机应用路径 <font color="red">*</font></td>
				<td colspan="3"><s:if test="theForm.STRATEGYTYPE==1">
						<span id="path_jz" style="display:none;"><s:textarea
								name="theForm.BASEPATH" cols="77" rows="5" cssStyle="txt"
								id="BASEPATH" onblur="validateBasepath();" basepathEnabled="true"/> </span>
						</s:if> <s:else>
						<span id="path_jz" style="display:block;"><s:textarea
								name="theForm.BASEPATH" cols="77" rows="5" cssStyle="txt"
								id="BASEPATH" onblur="validateBasepath();" basepathEnabled="true"/> </span>
					</s:else><font color="red" size="1">例:如果部署一个名字为cloud的web项目,基准主机应用路径为:/tomcat/webapps/cloud</font>
				</td>
			</tr>
			<tr>
				<td class="til">备注</td>
				<td colspan="3"><s:textarea name="theForm.REMARK" cols="77"
						rows="5" cssStyle="txt" id="REMARK" /></td>
			</tr>
			<tr id="operButtons">
				<td colspan="4" class="btnCenter"><input type="button"
					class="thickbox btn-style02" value="确定"
					onclick="javascript:submitRequest(document.getElementById('theForm'));return false;" />
					<input type="button" class="thickbox btn-style02" value="重置"
					onclick="javascript:void(document.getElementById('theForm').reset());return false;" />
					<input type="button" class="thickbox btn-style02" value="返回"
					onclick="window.history.back()" /></td>
			</tr>

		</table>
	</s:form>
</body>

</html:html>
