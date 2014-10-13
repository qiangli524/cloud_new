<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="/sxcloud/common/taglib.jsp"%>
<%@ include file="/sxcloud/common/view.jsp"%>

<head>
<title>验证身份</title>
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=iblue"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/formvalidate/validate.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/common.js"></script>
<script type="text/javascript">
	$(function(){
	 var api = frameElement.api;
	 var w = api.opener;
			api.button({
			     id:'check',
			     name: '验证',
			     callback:checkOper,
			     focus: true
			 },
			 {
			     id:'cancle',
			     name: '取消'
			 });
	function checkOper(){
		var usernames = "";
		var passs = "";
		var ips = "";
		var flag = false;
		$("input[name='pass']").each(function(){
			if($(this).val() != ""){
				passs+=$(this).val()+",";
			}else{
				flag = true;
			}    
		});
		$("input[name='username']").each(function(){   
				usernames+=$(this).val()+",";
		});
		$("input[name='ip']").each(function(){
				ips+=$(this).val()+",";
		});
		if(flag){
		  	alert("您有未填写的表单！");
		  	return false;
		  }
		mask('正在验证,请稍后....','0.5','0px');
		//检测用户输入是否正确	
		$.ajax({
            type: "post",
            url: "bossProcessMonitor_checkOperToDo.do?userNames="+usernames+"&passWords="+passs+"&ips="+ips,
            dataType: "json",
			async:false,//是否异步
			cache:false,//是否保留缓存
            success : function(msg){
					if(msg.result == ""){
						removeMask();
						alert("验证正确！");
						w.operProcess();
					}else{
						alert(msg.result);
						return false;						
					}
				}
          });
		}
	});

	//去空
	function Trim(str){
		return str.replace(/^\s+|\s+$/g,"");
	}
	
</script>
</head>
<html>
	<body class="pop-body scrollbody">
		<s:hidden name="ips" id="ips"></s:hidden>		
		<s:form action="" method="post" styleId="theForm" id="theForm">
			<table width="100%" border="0" cellspacing="0"class="pop-table nosize">
				<s:iterator value="mapResult">	
				 <tr>
					<td colspan="10" align="left" name="total">
						IP: <s:property value="key"/>
					</td>
				 </tr>
				 <s:iterator value="value" id="st">	
					 <tr>
					 	<td class="til">用户名:</td>
					 	<td><s:property value="#st.username"/></td>	
					 	<td class="til">密码:</td>
					 	<td name="state">
					 		<s:textfield type="password" name="pass" cssStyle="txt" cssClass="required" style="width: 70%"/>
					 	</td>
					 	<td style="display: none;">
					 		<s:textfield name="ip" />
					 		<s:textfield name="username"/>
					 	</td>	
					 </tr>
				 </s:iterator>	
		 		</s:iterator>
			</table>
		</s:form>
	</body>
</html>
