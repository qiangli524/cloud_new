<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../sxcloud/common/taglib.jsp"%>
<%@ include file="../../sxcloud/common/view.jsp"%>
 <head>
	<title></title>
	<link
		href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css"
		rel="stylesheet" type="text/css" />
	<link
		href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css"
		rel="stylesheet" type="text/css" />
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
	<script type="text/javascript">
	    var api = frameElement.api;
			w = api.opener;
	    $(function(){
			 api.button({
			     id:'OkAnd',
			     name: '确定',
			     callback:commitScript,
			     focus: true
			 },
			 {
			     id:'cancle',
			     name: '取消'
			 });
			 
			
			
			
		});
		
			
	    function commitScript() {
			
		    if (theForm.APPNAME.value.length == 0) {
		    	alert("版本所属的应用不存在，请确认！");
		    	return false;
		    }
		    if (theForm.DESCRIBTION.value.length == 0) {
		    	alert("请输入版本描述！");
		    	return false;
		    }
		    $("#NO").attr("disabled",false);
	    	$("#LOCATION").attr("disabled",false);
	    	$("#DESCRIBTION").attr("disabled",false); 
	    	
	    	$.ajax({
				type:"GET",
				url:"fileversion_saveFileVersion.do?" + $("#theForm").serialize(),
				dataType:"json",
				success:function(data){
					if (data.result == 1)
					{
						alert("更新版本信息出错！"); 
						return false;
					} else if (data.result == 2) {
						alert("版本主机IP串配置信息有误，请确认！"); 
						return false;
					} else if (data.result == 3) {
						alert("新增插入版本信息表出错！"); 
						return false;
					} else if (data.result == 4) {
						alert("软件版本包不存在！"); 
						return false;
					} else {
						alert("保存成功！");
			            w.listForm();
					}
				}  
			});
			return false;
		}
		
</script>
</head>
<body class="pop-body scrollbody">
	<s:form action="" method="post" id="theForm">
		<table width="100%" border="0" cellspacing="0"
			class="pop-table nosize">
			<s:hidden name="theForm.flag" id="flag"></s:hidden>
	<s:hidden name="theForm.ID" id="ID"></s:hidden> 
	<s:hidden name="theForm.SOFRTPARTH" id="SOFRTPARTH"></s:hidden> 
	<s:hidden name="theForm.SYSID" id="SYSID"></s:hidden>
	<s:hidden name="theForm.SOFTID" id="SOFTID"></s:hidden>
       <s:hidden name="theForm.APPID" id="APPID"></s:hidden>           
                    <tr>
                    <td class="til">所属应用</td>
                    <td>
						<s:textfield name="theForm.APPNAME" cssClass="txt" id="APPNAME" readonly="true" disabled="true"></s:textfield>
                    </td>
                    </tr> 
                    <tr>
                    <td class="til">版本号</td>
                   <td>
						<s:textfield name="theForm.NO" cssClass="txt" id="NO" readonly="true" disabled="true"></s:textfield>
                    </td>
                    </tr>
                    <tr>
                     <td class="til">版本包路径</td>
                   <td colspan="3"><s:textarea name="theForm.LOCATION" cols="77" 
						rows="2" cssStyle="txt" id="LOCATION" readonly="true" disabled="true"></s:textarea></td>
                    </tr>
                    <tr>
                    <td class="til">版本描述<font color="red">*</font></td>
					<td colspan="3"><s:textarea name="theForm.DESCRIBTION" cols="77" 
						rows="3" cssStyle="txt" id="DESCRIBTION"></s:textarea></td>
                    </tr>
                   <tr>
                  
				</tr>
             
	</s:form>
</body>

