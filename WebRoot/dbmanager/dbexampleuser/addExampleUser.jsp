<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="cache-control" content="no-cache"/>
<meta http-equiv="expires" content="0"/>
<head>
	<title></title>
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/jQuery-Timepicker-Addon/jquery-ui.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/jQuery-Timepicker-Addon/jquery-ui-timepicker-addon.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-ui.min.js"></script>
<script language="javascript" type="text/javascript" src="${pageContext.request.contextPath }/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
    //创建配置文件
	$(function(){
		 var api = frameElement.api;
		 var w = api.opener;
		 api.button({
			     id:'OkAnd',
			     name: '确定',
			     callback:updateConfig,
			     focus: true
			 },
			 {
			     id:'cancle',
			     name: '取消'
			 });
		function updateConfig(){
	    	var example_username =$("#example_username").val();   //名称
			var example_password =$("#example_password").val();  //密码
			if(example_username==null || example_username==''){
				alert('请填写用户名称');
					return false;
			}
			if(example_password==null || example_password ==''){
				alert('请填写用户密码');
					return false;
			}
			var form = $("#theForm");
			$.ajax({
			         type: "get",
			         url: "dbexample_saveExampleUser.do?"+form.serialize(),
			         dataType: "json",
			         success : function(data){
			        }
			});
			api.get("addexampleuser").list();
	 	}
	});
</script>
</head>
<body class="pop-body scrollbody">
	<s:form action="" method="post" styleId="theForm" id="theForm">
	    <input type="hidden" id="id" name="dbExampleUserObj.id" value="<s:property value='dbExampleUserObj.id'/>"/>
		  	<input type="hidden" id="example_id" name="dbExampleUserObj.example_id" value="<s:property value='dbExampleUserObj.example_id'/>"/>
		  	<input type="hidden"  name="dbExampleUserObj.is_lock" value="<s:property value='dbExampleUserObj.is_lock'/>"/>
		  	<input type="hidden"  name="dbExampleUserObj.is_submit" value="<s:property value='dbExampleUserObj.is_submit'/>"/>
		<table width="100%" border="0" cellspacing="0" class="pop-table nosize">
				<tr>
				    <td class="til" width="20%" align="left">
				               用户名<font color="red">*</font>
				    </td>
				    <td colspan="5" name="ips">
				    	<s:if test="dbExampleUserObj.id == null">
				    		<s:textfield name="dbExampleUserObj.example_username" id="example_username" style="width:150px;   height:20px;"></s:textfield>
				    	</s:if>
				    	<s:else>
				    		<s:textfield name="dbExampleUserObj.example_username" id="example_username" id="example_username" style="width:150px;   height:20px;" disabled="true"></s:textfield>
				    	</s:else>
				    </td>
				</tr>	
			 <tr>
				<td class="til" width="20%" align="left">
					密&nbsp;码<span style="color:red">*</span>
				</td>
				<td>
					<s:textfield name="dbExampleUserObj.example_password" id="example_password" style="width:150px;   height:20px;"></s:textfield>
				</td>
			</tr>
		</table>
	</s:form>
</body>

</html:html>
