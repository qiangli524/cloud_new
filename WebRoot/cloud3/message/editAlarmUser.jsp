<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../sxcloud/common/taglib.jsp"%>
<html:html locale="true">
<%@ include file="../../sxcloud/common/link.jsp"%>
<head>
	<title></title>

	<script type="text/javascript">
    $(function(){
		$("#datepicker1").datepicker({showOn: 'button', buttonImage: '../cresources/default/images/date-icon.gif', buttonImageOnly: true});
		$("#datepicker2").datepicker({showOn: 'button', buttonImage: '../cresources/default/images/date-icon.gif', buttonImageOnly: true});

	})
	
    function Trim(str){
	return str.replace(/^\s+|\s+$/g,"");
	}
	function submitRequest(thisForm){    
	    if(thisForm.name.value.length ==0){
	     alert("姓名不能为空！");
	     return false  ;
	    }
	    if($("#email").val().length==0){
	    	alert("email不能为空");
	    	return false;
	    }else{
	    	if(!isValidMail($("#email").val())){
		    	alert("email格式不正确");
		    	return false;
	    	}
	    }
	    if($("#telephone").val().length==0){
	    	alert("手机号不能为空");
	    	return false;
	    }else{
	    	if(!checkMobile($("#telephone").val())){
	    		alert("请输入正确的手机号");
		    	return false;
	    	}
	    }
	    thisForm.submit();
	}
	/* 
	用途：检查输入手机号码是否正确 
	输入： 
	s：字符串 
	返回： 
	如果通过验证返回true,否则返回false 

	*/ 
	function checkMobile( s ){   
		var regu =/^[1][0-9][0-9]{9}$/; 
		var re = new RegExp(regu); 
		if (re.test(s)) { 
			return true;	 
		}else{ 
			return false; 
		} 
	} 
	
	function isValidMail(str) {            
		　　var Regex = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;;            
		　　if (Regex.test(str)){   
			return true;  
		　　}            
		　　else {                
			　　	return false;                
		　　}        
		}    

	


</script>
</head>
<body class="pop-body scrollbody" onLoad="self.focus();document.theForm.name.focus()">
	<s:form action="message_saveAlarmUser" method="post" cssStyle="theForm" id="theForm">
		<s:hidden name="theForm.id" id="id"></s:hidden>
		<input type="hidden" name="FUNCSID" value="<%=(String)request.getParameter("FUNCSID") %>">
			<table width="100%" border="0" cellspacing="0" class="pop-table nosize">
				<tr>
				    <td class="til">
						姓名:<font color="red">*</font>
					</td>
					<td>
						<s:if test="theForm.id==null||theForm.id==''">
						<s:textfield size="30" name="theForm.name" cssStyle="txt" id="name" maxlength="30"/>
					   </s:if>
					   <s:elseif test="theForm.id!=null&&theForm.id!=''">
					    <s:textfield size="30" name="theForm.name" cssStyle="txt" id="name" maxlength="30"/>
<%--					     readonly="true"--%>
					      
					   </s:elseif>
					</td>
					<td class="til">
						Email <font color="red">*</font>
					</td>
					<td>
					<s:textfield size="30" name="theForm.email" cssStyle="txt" id="email"/>
					</td>
				</tr>

				<tr>
				    <td class="til">
						手机号 <font color="red">*</font>
					</td>
					<td colspan="3">
					<s:textfield size="30" name="theForm.telephone" cssStyle="txt" id="telephone"/>
					</td>
				</tr>
				<tr>
					<td colspan="4" class="btnCenter">
						<input type="button" class="thickbox btn-style02" value="确定"
							onclick="javascript:submitRequest(document.getElementById('theForm'));return false;" />
						<input type="button" class="thickbox btn-style02" value="重置"
							onclick="javascript:void(document.getElementById('theForm').reset());return false;" />
						<input type="button" class="thickbox btn-style02" value="返回"
							onclick="window.history.back()"/>
					</td>
				</tr>
			</table>
	</s:form>
</body>

</html:html>
