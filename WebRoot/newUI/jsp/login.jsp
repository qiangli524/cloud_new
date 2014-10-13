<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../common/taglib.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<html>
  <head>
    <title><bean:message key="msg.jsp.title" arg0="主页面" /></title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
	
	<link rel="stylesheet" type="text/css" href="../newUI/css/reset.css" />
    <link rel="stylesheet" type="text/css" href="../newUI/css/login.css" />
	<!--[if lt IE 8]>
    <link rel="stylesheet" type="text/css" href="../newUI/css/login-hack.css" />
    <![endif]-->
	<script type="text/javascript" src="../newUI/js/jquery-1.7.2.min.js"></script>
    <script type="text/javascript" src="../newUI/js/plugin/correctPNG.js"></script>
    <script type="text/javascript" src="../newUI/js/plugin/modernizr-min.2.6.2.js"></script>
    <script type="text/javascript" src="../newUI/js/function.js"></script>
    <script language="javascript" type="text/javascript">
	$(function(){
		$("div.loginPanel input.txt").each(function(i, n){
			$(n).attr("data-class", $(this).attr("class"));
			$(n).focus(function(){
				var _class = $(this).attr("class"),
					_username = /username/g,
					_pwd = /pwd/g,
					_txt2 = /txt21/g;
				if(_username.test(_class)){$(this).removeClass("username")}
				if(_pwd.test(_class)){$(this).removeClass("pwd")}
				if(_txt2.test(_class)){$(this).removeClass("txt21")}
			}).blur(function(){
				if($(this).val()==""){
					$(this).attr("class", $(this).attr("data-class"))
				}
			})
		})
		
		//$("input[type=reset]").click(function(){
		//	$("div.loginPanel input.txt").val("");
		//	$("div.loginPanel input.txt").attr("class", function(){return $(this).attr("data-class")})
		//})
		//找回密码
		$("#retrievePW").click(function(){
			if($("#username").val()==""){
				//alert("请输入用户名");
				//return;
			}
			$("form").attr("action","retrievePW.do");
   			$("form").submit();
   			//alert(1);
		})
		
		$("div.loginPanel input.txt").focus(function(){
			var _class = $(this).attr("class"),
				_username = /username/g,
				_pwd = /pwd/g,
				_txt2 = /txt2/g;
			if(_username.test(_class)){$(this).removeClass("username")}
		})
     
		document.onkeydown = function(e){ 
			var ev = document.all ? window.event : e; 
			if(ev.keyCode==13) { 
				userLogin();
			} 
		}

		var flag="true";
    	//获取验证码
    	var str="<img src='./random.jsp' id='random' valign='absmiddle' hspace='5'/>";
			$(".yzm").append(str);
		$(".loginBtn").click(function(){
			userLogin();
			
    	});
	})
	
	function userLogin(){
			if($("#username").val()==""){
				alert("请输入用户名");
				return;
			}
			if($("#passwd").val()==""){
				alert("请输入密码");
				return;
			}
			if($("#num").val()==""){
				alert("请输入验证码");
				return;
			}
			
			$("form").attr("action","login.do");
   			$("form").submit();
	
	}
    	
    </script>
        <!--[if IE 6]>
    <script type="text/javascript">
        window.attachEvent("onload", correctPNG);
    </script>
    <![endif]-->
    
  </head>
  
  <body>
   <div class="loginWrap">
    <div class="top"><img src="../newUI/images/index-logo.png" alt="mas"></div>
    <div class="loginPanel">
       <!-- <img src="../newUI/images/login-label.png" alt=""> -->
        <html:form action="login" method="post" styleId="theForm">
		<bean:define id="theForm" name="loginForm" />
    
        <ul>
            <li><input type="text" class="txt username" id="username" name="userCode"></li>
            <li><input class="txt pwd" type="password" data-class="txt pwd" id="passwd" name="userPassword"></li>
            <li><input type="text" class="txt txt2 txt21 fl" id="num" name="num">
            <div class="yzm">
            </div><!--  <span class="yzm"><img src="../newUI/images/pic-publish/yzm.gif"/> </span>-->
            </li>
            <li>
                <input type="button" class="loginBtn fl" value="&nbsp;" title="登录" />
             <!--    <input type="reset" class="resetBtn ml10" value="&nbsp;" title="重置" />  --> 
            	<input type="button" class="resetBtn ml10" value="&nbsp;" title="找回密码" id="retrievePW"/>
            </li>
            
        </ul>
        </html:form>
        <p class="tip">
        	<logic:messagesPresent message="true">
				<html:messages id="msg" message="true">
					<bean:write name="msg" />
				</html:messages>
			</logic:messagesPresent>
		</p>
    </div>
</div>
  </body>
</html>
