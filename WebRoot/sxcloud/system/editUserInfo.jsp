<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<html:html locale="true">
<%@ include file="../common/link.jsp"%>
<head>
	<title></title>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/formvalidate/validate.js" />
	<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
	<script type="text/javascript">
	//校验表单
    function Trim(str){
		return str.replace(/^\s+|\s+$/g,"");
	}
	function submitRequest(thisForm){  
		var mobile = $("#MOBILE").val(); 
		var email =  $("#EMAIL").val();
		var name = $("#NAME").val();
		var account = $("#ACCOUNT").val();
		var patrn = /^((\(\d{3}\))|(\d{3}\-))?[12][03458]\d{9}$/;//手机验证
		var patrn1 = /^[a-z0-9_+.-]+\@([a-z0-9-]+\.)+[a-z0-9]{2,4}$/;//邮箱验证
	    if(account==""&&account.length ==0){
		 $("#ACCOUNT").attr("class","required");
	     $("#ACCOUNT").focus();
	     return false  ;
	    }
	    if(name==""&&name.length==0){
	    	$("#NAME").attr("class","required");
	    	$("#NAME").focus();
	    	return false;
	    }
	    if(mobile!=""&&!patrn.exec(mobile)){
			$("#MOBILE").attr("class","allCellPhone");	
			$("#MOBILE").select();
			$("#MOBILE").focus();
			return false;
		}
		if(email!=""&&!patrn1.exec(email)){
				$("EMAIL").attr("class","email");
				$("#EMAIL").select();
				$("#EMAIL").focus();
				return false;
		}
	    if( thisForm.PASSWORD.value.length >= 8 ){	　　　   
	    		thisForm.submit();
	    		return ;
		}else if(thisForm.PASSWORD.value.length==0){
		  	$("#PASSWORD").attr("class","required");
		  	$("#PASSWORD").focus();
		  	return false;
		}else{
			 $("#PASSWORD").attr("class","pwlength");
			 $("#PASSWORD").select();
			 $("#PASSWORD").focus();
		    return false  ;
		}
	}
	

	function checkValid(obj){		
		var password = Trim(obj.PASSWORD.value);
		var bool1 = 	isnumbercode(password);
		if(!bool1)
		{
			alert("密码不符合要求，并且在密码中不能包含中文格式的输入！");
			return false;
		}	
		var bool2=isnumber(password);
		var bool3=iscode(password);
		var bool4=ispecialcharacter(password);
		if(bool2||bool3||bool4)
		{
				alert("密码必须同时包含数字、字母和特殊字符！");
				return false;
		}                                      
		return true;
	}
	
	
	
	/* 检测输入的字符串是否符合要求 */
	function isnumbercode(str)
	 {
	  var number_chars = "1234567890abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ`~!@#$%^&*()-_=+|\{}[]:;'?/,.<>";
	        var i;
	        for (i=0;i<str.length;i++)
	   {
	            if (number_chars.indexOf(str.charAt(i))==-1) return false;
	        }
	        return true;
	}
	
	/* 检测字符串是否为数字或者字母 */
	function isnumber(str)
	 {
	  var number_chars = "1234567890abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	    var i;
	    for (i=0;i<str.length;i++)
	      {
	          if (number_chars.indexOf(str.charAt(i))==-1) return false;
	      }
	      return true;
	 }
	
	/* 检测字符串是否为字母或特殊字符 */
	function iscode(str)
	 {
	  var number_chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ`~!@#$%^&*()-_=+|\{}[]:;'?/,.<>";
	    var i;
	    for (i=0;i<str.length;i++)
	      {
	          if (number_chars.indexOf(str.charAt(i))==-1) return false;
	      }
	      return true;
	 }
	
	/* 检测字符串是否为数字和特殊字符 */
	function ispecialcharacter(str)
	 {
	  var number_chars = "1234567890`~!@#$%^&*()-_=+|\{}[]:;'?/,.<>";
	    var i;
	    for (i=0;i<str.length;i++)
	      {
	          if (number_chars.indexOf(str.charAt(i))==-1) return false;
	      }
	      return true;
	 }

<% 
String msg = request.getAttribute("msg")==null ? "": (String)request.getAttribute("msg");
%>
</script>
</head>
<body class="pop-body scrollbody">
	<div class="pd-20 bgcolor-1">
      <h2 class="utt-1">用户管理</h2>
         <div class="bord-1 pd-10">
			<s:form action="user_saveUserInfo" method="post" cssStyle="theForm" id="theForm">
				<s:hidden name="theForm.ID" id="ID"></s:hidden>
				<input type="hidden" name="FUNCSID" value="<%=(String)request.getParameter("FUNCSID") %>">
					<table width="100%" border="0" cellspacing="0" class="pop-table nosize">
						<tr>
						    <td class="til">
								账号 <font color="red">*</font>
							</td>
							<td>
								<s:if test="theForm.ID==0">
								<s:textfield name="theForm.ACCOUNT" id="ACCOUNT" maxlength="30" cssClass="required inpt-2"/>
							   </s:if>
							   <s:elseif test="theForm.ID!=0">
							     <s:textfield name="theForm.ACCOUNT"  readonly="true" disabled="true" id="ACCOUNT"  cssClass="inpt-2"/>
							   </s:elseif>
							  <font color="red"> <%=msg%> </font>               
							</td>
							<td class="til">
								姓名 <font color="red">*</font>
							</td>
							<td>
							<s:textfield name="theForm.NAME" id="NAME" maxlength="30" cssClass="required inpt-2"/>
							</td>
							 <td class="til">
								密码 <font color="red">*</font>
							</td>
							<td > 
							<input type="password" name="theForm.PASSWORD" id="PASSWORD" maxlength="30" class="inpt-2 required" value="${theForm.PASSWORD}"/>
							</td>
						</tr>
		
						<tr>
							<td class="til">
								手机号
							</td>
							<td>
							<s:textfield name="theForm.MOBILE" id="MOBILE" maxlength="11" cssClass="allCellPhone inpt-2"/>
							</td>
							 <td class="til">
								邮箱
							</td>
							<td >
							<s:textfield name="theForm.EMAIL" id="EMAIL" maxlength="30" cssClass="email inpt-2"/>
							</td>
							 <td class="til">
								所属部门
							</td>
							<td >
							<s:textfield name="theForm.DATAAUTHORITY" cssClass="inpt-2" id="DATAAUTHORITY" />
							</td>
						</tr>
						<tr>
							<td class="til">
								账号
							</td>
							<td>
							<s:select list="#{'1':'活跃','0':'冻结','2':'锁定'}" name="theForm.STATUS" id="STATUS" cssClass="select-1"></s:select>
							</td>
							<td class="til">
								所属域
							</td>
							<td >
								<s:select  list="theForm.domainList" listKey="code"  cssClass="select-1" name="theForm.domain" id="domain" listValue="name" headerKey="0" headerValue="-请选择-">
								</s:select>  
							</td>
						</tr>
						<tr>
							<td colspan="6" class="btnCenter">
								<span class="ubtn-green"><input type="button"  value="确定" onclick="javascript:submitRequest(document.getElementById('theForm'));return false;" /></span>
								<span class="ubtn-orange mgl-20"><input type="button"  value="重置" onclick="javascript:void(document.getElementById('theForm').reset());return false;"></span>
								<span class="ubtn-blue mgl-20"><input type="button"  value="返回" onclick="window.history.back()"/></span>
							</td>
						</tr>
					</table>
			</s:form>
	    	</div>
        </div>
    </div>
</body>

</html:html>
