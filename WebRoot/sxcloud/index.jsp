<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="com.sitech.basd.sxcloud.rsmu.web.util.page.css.CssUtil" %>
<%!
public String getCssTag(HttpServletRequest request, String path) {
	return CssUtil.getCssTag(request, path);
}%>
<%@ include file="common/taglib.jsp"%>
<head>
<%=getCssTag(request, "common.css")%>
<%=getCssTag(request, "login.css")%>
<title>云计算管理平台</title>
<%--<title>重庆移动云资源池管理</title>--%>
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/js/Base64.js"></script>
<script language="javascript" type="text/javascript">
//解决session过期不能跳出Iframe的问题。
if (window != top) 
	top.location.href = location.href; 
	//登陆
	function check(obj){
		if(obj.ACCOUNT.value == ''){
			alert("请输入账号！");
			return;
		}	
		if(obj.PASSWORD.value.length<8) {
			alert("密码长度不能小于8位！");
			return ;
		}
		$("#PASSWORD").val(str_encode($("#PASSWORD").val()));
		obj.submit();
	}
	
	//重置
	function cz(theForm){	
		theForm.ACCOUNT.value="";
		theForm.PASSWORD.value="";
	}
	

	
	if (document.addEventListener)   
    {//如果是Firefox    
        document.addEventListener("keypress", fireFoxHandler, true);    
    } else  
    {    
        document.attachEvent("onkeypress", ieHandler);    
    }    
    function fireFoxHandler(evt)   
    {    
        //alert("firefox");    
        if (evt.keyCode == 13)   
        {    
            check(document.getElementById('theForm'));
        }    
    }    
    function ieHandler(evt)   
    {    
        //alert("IE");    
        if (evt.keyCode == 13)   
        {    
            check(document.theForm);
            
        }    
    }  
	function load() {
		theForm.ACCOUNT.focus();
	}
	
	
	
	/////验证码相关
	var code ; //在全局定义验证码   
	//产生验证码  
	window.onload = function createCode(){  
     code = "";   
     var codeLength = 4;//验证码的长度  
     var checkCode = document.getElementById("code");   
     var random = new Array(0,1,2,3,4,5,6,7,8,9,'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R',  
     'S','T','U','V','W','X','Y','Z');//随机数  
     for(var i = 0; i < codeLength; i++) {//循环操作  
        var index = Math.floor(Math.random()*36);//取得随机数的索引（0~35）  
        code += random[index];//根据索引取得随机数加到code上  
    }  
    	checkCode.value = code;//把code值赋给验证码  
}  
//校验验证码  
function validate(){  
    var inputCode = document.getElementById("input").value.toUpperCase(); //取得输入的验证码并转化为大写        
    if(inputCode.length <= 0) { //若输入的验证码长度为0  
        alert("请输入验证码！"); //则弹出请输入验证码  
    }         
    else if(inputCode != code ) { //若输入的验证码与产生的验证码不一致时  
        alert("验证码输入错误！@_@"); //则弹出验证码输入错误  
        createCode();//刷新验证码  
        document.getElementById("input").value = "";//清空文本框  
    }         
    else { //输入正确时  
        alert("^-^"); //弹出^-^  
    }             
}  
</script>
<%
String msg = request.getAttribute("msg")==null?"":(String)request.getAttribute("msg");
 %>
</head>
<body onload="load()">
	<div class="loginPanel">
		<s:form action="login_login" method="post" cssStyle="theForm" id="theForm">
			<div class="txta">
			<s:textfield id="ACCOUNT" name="theForm.ACCOUNT" ></s:textfield>
			<s:password id="PASSWORD" name="theForm.PASSWORD" onclick="javascript:if(event.keyCode==13){check(document.getElementById('theForm'));return false;}"></s:password>
           <!-- 
            <tr>
            		<td>
            			<input type = "text" value = "验证" onblur = "validate()" style="width: 40px;" id="input"/> 
            			<input type = "button" id="code" onclick="createCode()" style="width: 40px;"/>  
            		</td>
            </tr>
             -->	
		    </div>
		    <div >
		    	
		    </div>
			<div class="btn-area" >
		      <input  type="button"  class="dl" onclick="javascript:check(document.getElementById('theForm'));return false;"/>
		      <input  type="button" class="cz" onclick="javascript:cz(document.getElementById('theForm'));" tabindex="1"/>
		    </div>
		   	<span class="msg-area" style="width: 200px;"><font color="#FF0000"><%=msg %></font></span>
		</s:form>
		<div class="icon"></div>
	</div>
</body>
