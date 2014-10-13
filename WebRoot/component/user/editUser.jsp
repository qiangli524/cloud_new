<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="../../sxcloud/common/taglib.jsp" %>
<head>
    <title></title>
    <link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
	<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
  	<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
  	<script type="text/javascript" src="<%=request.getContextPath()%>/js/formvalidate/validate.js"></script>
    <script type="text/javascript">
    var flag=0;//用于判断在提交时表单内容是否完全正确，以确定是否可以提交
   	var id= '<%=request.getAttribute("id")%>';
   	var api = frameElement.api;
		w = api.opener;
		 api.button({
		     id:'OkAnd',
		     name: '确定',
		     callback:createConfig,
		     focus: true
		 },
		 {
		     id:'cancle',
		     name: '返回'
		 });
		
	function createConfig(){
 		var flag = false;
		var ip =$("#ip").val();
		var username = $("#username").val();
		var password = $("#password").val();
		var pattern = /^(25[0-5]|2[0-4]\d|[01]?\d\d?)\.(25[0-5]|2[0-4]\d|[01]?\d\d?)\.(25[0-5]|2[0-4]\d|[01]?\d\d?)\.(25[0-5]|2[0-4]\d|[01]?\d\d?)$/;//IP校验
		if(ip==""){
			$("#ip").attr("class","required");
			$("#ip").focus();
			return false;
		}
		if(ip!=""&&!pattern.exec(ip)){
			$("#ip").attr("class","ipAddress");
			$("#ip").select();
			$("#ip").focus();
			return false;
		}
		if(username==""){
			 $("#username").attr("class","required");
			$("#username").focus();
			return false;
		}
		if(password==""){
			$("#password").attr("class","required");
			$("#password").focus();
			return false;
		}
		/*
		 if(password.length<6){
			 alert("密码不能小于6位！");
			 $("#password").select();
			 $("#password").focus();
		    return false  ;
		}
		*/
	   	if(id == null||id.length==0){
	   		var param = "theForm.username="+username+"&theForm.ip="+ip;
	   	}else{
	   		var param = "id="+id+"&theForm.username="+username+"&theForm.ip="+ip;
	   	}
	  	var url = 'usermanage_validateUserName.do';
		 $.ajax({
			  type:"post",
              url:url,
              data : param,
			  dataType : "html",
              async: false,
              cache: false,
	          success: function(data){
	          	  if(data==""){
					theForm.action='usermanage_saveUser.do?id='+ id;
	    			theForm.submit();
	    			w.searchRequest();
	    			flag = true;
				}else{
					$("#username_span").html(data);
				}
              }
		});
		return flag;
	}
    //验证用户名的唯一性
    function validateUserName(){
    var url = "usermanage_validateUserName.do?id="+ id +"&"+$("#theForm").serialize();
    var ip = $("#ip").val();
    if(ip!=null && ip!=''){
	    $.ajax({
				type : "get",
				url : url,
				dataType : "html",
				cache:false,
	     	    processData: false,
				success : function(data){
					$("#username_span").html(data);
				}
			});	
		}
    }
    function barEnd(idstr,contents){
	$.dialog.list[idstr].content(contents,false,false);
	$.dialog.list[idstr].time(2);
	}

	function bar(idstr,contents){
	$.dialog({
			id:idstr,
		    title: '提示',
		    width: 200,
		    height: 100,
		    left: '100%',
		    top: '100%',
		    fixed: true,
		    max:false,
		    content:contents
		});
}

    </script>
</head>
<body class="pop-body scrollbody">
    <s:form action="" method="post" id="theForm" cssStyle="theForm">
  	<div class="table-ct" >
			<table width="100%" border="0" cellspacing="0" class="pop-table nosize">
				<tr align="left">
					<td class="til">IP地址 <font color="red">*</font></td>
					<td>
						<s:textfield name="theForm.ip" id="ip" style="width:150px;   height:20px;" maxlength="30"  cssClass="ipAddress required"></s:textfield>
					</td>
				</tr> 
				<tr align="left">
					<td class="til">用户名 <font color="red">*</font></td>
					<td>
					<s:textfield name="theForm.username" id="username" style="width:150px;   height:20px;" maxlength="30" onblur="validateUserName()" cssClass="required"></s:textfield>
					<span id="span"><font color="red" id="username_span"></font></span>
					</td>
				</tr> 
				<tr align="left">
					<td class="til">密码 <font color="red">*</font></td>
					<td>
					<s:password type="password" name="theForm.password" id="password" style="width:150px;   height:20px;" maxlength="16" showPassword="true" cssClass="required"></s:password>
					</td>
				</tr> 
				<tr align="left">
					<td class="til">用户类型</td>
					<td>
					<s:select list="#{'0':'普通用户','1':'管理员用户','2':'信任关系用户','3':'ORACLE安装用户','4':'监控用户'}" cssClass="select-1" style="width:150px;" id="type" name="theForm.type"></s:select>
					</td>
				</tr> 
				<tr align="left">
					<td class="til">
						用户用途
					</td>
					<td>
						<s:select list="#{'0':'其他','1':'hadoop系统用户' }" cssClass="select-1" style="width:150px;" name="theForm.purpose" id="purpose"></s:select>
					</td>
				</tr>
			</table>
		</div>
    </s:form>
</body>