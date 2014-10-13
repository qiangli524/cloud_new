<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="../../sxcloud/common/taglib.jsp"%>
<%@ include file="../../sxcloud/common/view.jsp"%>

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
		var names = "";
		var passs = "";
		var flag = false;
		var ips = $("#ips").val();
		$("input[name='username']").each(function(){   
			if($(this).val() != ""){
				names+=$(this).val()+",";
			}else{
				flag = true;
				return false;
			}
		});
		$("input[name='password']").each(function(){
			if($(this).val() != ""){
					passs+=$(this).val()+",";
			}else{
				flag = true;
				return false;
			}    
		});
		if(flag){
		  	alert("您有未填写的表单！");
		  	return false;
		  }
		mask('正在验证,请稍后....','0.5','0px');
		//检测用户输入是否正确	
		$.ajax({
            type: "post",
            url: "treeprocess_checkOperDo.do?userNames="+names+"&passWords="+passs+"&ips="+ips,
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
	//表单置空
	function resert(){
		$("#nam").attr("value","");
		$("#pass").attr("value","");
	}
	
</script>
</head>
<html>
	<body class="pop-body scrollbody">
		<s:hidden name="ips" id="ips"></s:hidden>		
		<s:form action="" method="post" styleId="theForm" id="theForm">
			<table width="100%" border="0" cellspacing="0"class="pop-table nosize">
				<s:iterator value="ipList" id="theBean">
					<tr align="left">
						<td colspan="2" style="border-bottom: silver;">
							<font size="2px">主机IP:<s:property value="#theBean.ip"/></font>
						</td>
					</tr>
					<tr >
						<td class="til"> 
							用户名:<font color="red">*</font>
						</td>
						<td>
							<s:textfield type="text" name="username" cssClass="required" style="width: 50%"/>
						</td>
					</tr>
					<tr>
						<td class="til">
							密码:<font color="red">*</font>
						</td>
						<td>
							<s:textfield type="password" name="password" cssClass="required" style="width: 50%"/>				
						</td>
					</tr>
				</s:iterator>
			</table>
		</s:form>
	</body>
</html>
