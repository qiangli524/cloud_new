<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.*"%>
<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=gb2312"/>
    <meta name="Author" content="si-tech">
    <meta name="Keywords" content="">
    <meta name="Description" content="">
    <title><bean:message key="msg.jsp.title" arg0="找回密码" /></title>
     <%@ include file="../common/linkNew.jsp"%>
     <%
     	String LOGIN_PHONE=request.getAttribute("LOGIN_PHONE")==null?"":request.getAttribute("LOGIN_PHONE").toString();
      %>
    <script type="text/javascript">
        $(function(){
            UID_subAccordion("div.accordion-1");
            UID_subAccordion("div.accordion-2");
            UID_tabChange("click", "#label3");
            
            //获取验证码    
        	var str="<img src='./random.jsp' id='random' valign='absmiddle' hspace='5'/>";
				$("#yanzhengma").append(str);
        })
        function anotherPIC1(){
        	//再换一张
				document.getElementById("random").src="./random.jsp?time="+new Date();
        }
        function valiPHnum(){
        	//获取短信验证码
        	var LOGIN_PHONE=<%=LOGIN_PHONE%>
        	var userCode=$("#userCd").html();
        	$.ajax({
					type: "POST",
					cache: "false",
					async: true,
					dataType: "text",
					url: "/retrievePW4.do?LOGIN_PHONE="+ LOGIN_PHONE+"&userCode="+userCode,
					success: function(msg) {
					
					//1表示用户名存在，0表示不存在
						if(1==msg){
							alert("验证码已发送，请查收！");
						}else{
							alert("验证码获取失败，请重新获取！");
						}
					}
				});
        }
        function finishIt(){
        	if(validIt()){
        		document.getElementById("theForm").action="retrievePW5.do";
        		document.getElementById("theForm").submit();
        	}
        }
        function validIt(){
        	var phoneNum=$("#phoneNum").val();//userCode借用来做短信验证码
        	var num=$("#num").val();
        	var newPW=$("#newPW").val();
        	var REnewPW=$("#REnewPW").val();
        	if(phoneNum==null||phoneNum==""){
        		alert("请输入短信验证！");
        		return false;
        	}
        	if(newPW==null||newPW==""){
        		alert("请输入新密码！");
        		return false;
        	}
        	if(REnewPW==null||REnewPW==""){
        		alert("请重复输入新密码！");
        		return false;
        	}
        	
        	if(num==null||num==""){
        		alert("请输入验证码！");
        		return false;
        	}
        	if(REnewPW!=newPW){
        		alert("两次输入密码不一致，请重新输入！");
        		return false;
        	}
        	var a5=isnumbercode(REnewPW)
				if(!a5) {
					alert("密码不符合定义的要求，并且在密码中不能包含中文格式的输入！");
					return false;
				}
				
				var a3=isnumber(REnewPW);
				var a4=iscode(REnewPW);
				var a5=ispecialcharacter(REnewPW);
				if(a3||a4||a5) {
					alert("密码必须同时包含数字、字母和特殊字符！");
					return false;
				}  
        	return true;
        }
        /* 检测输入的字符串是否符合要求 */
			function isnumbercode(str) {
				var number_chars = "1234567890abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ`~!@#$%^&*()-_=+|\{}[]:;'?/,.<>";
			    var i;
			    for (i=0;i<str.length;i++) {
			    	if (number_chars.indexOf(str.charAt(i))==-1) return false;
			    }
			    return true;
			}
        /* 检测字符串是否为数字或者字母 */
			function isnumber(str) {
				var number_chars = "1234567890abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
			    var i;
			    for (i=0;i<str.length;i++) {
			    	if (number_chars.indexOf(str.charAt(i))==-1) return false;
			    }
			    return true;
			 }
				
			/* 检测字符串是否为字母或特殊字符 */
			function iscode(str) {
				var number_chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ`~!@#$%^&*()-_=+|\{}[]:;'?/,.<>";
				var i;
				for (i=0;i<str.length;i++) {
					if (number_chars.indexOf(str.charAt(i))==-1) return false;
				}
				return true;
			}
				
			/* 检测字符串是否为数字和特殊字符 */
			function ispecialcharacter(str) {
				var number_chars = "1234567890`~!@#$%^&*()-_=+|\{}[]:;'?/,.<>";
				var i;
				for (i=0;i<str.length;i++) {
			    	if (number_chars.indexOf(str.charAt(i))==-1) return false;
				}
				return true;
			}
    </script>
</head>
 
<body>
<html:form action="retrievePW5"  method="post" styleId="theForm">
	  <bean:define id="theForm" name="loginForm" />
	  <html:hidden property="userCode" name="theForm"></html:hidden>
<div class="wrap">
   <div class="header"> <a href="#" class="logo"><img src="../newUI/images/index-logo.png" alt="" /></a>
      <div class="option">
         <ul>
            <li><a href="#"></a></li>
            <li><a href="#"></a></li>
            <li><a href="#"></a></li>
         </ul>
      </div>
   </div>
   <div class="currentPath"> <b>当前位置：</b> <a href="">找回密码</a>
   		<div class="userinfo"><span id="clientTime"></span></div>
   </div>
   	<div class="main-2 mgt10">
       	<div class="main-2-bt_1"></div>
       	<div class="main-2-bd">
        	<div class="forPassword">
                <div class="flow_steps pb20">
                    <h2 class="retake fl">找回用户密码：</h2>
                    <div class="nesting fl">
                        <ul class="flow_nav">
                            <li class="current">1.输入用户名</li>
                            <li class="current-2">2.选择找回密码方式</li>
                            <li class="retrieve">3.找回密码</li>
                        </ul>
                    </div>
                </div>	  
                <div class="crumbs">
                    <p>通过手机（<%=LOGIN_PHONE %>）找回密码</p>
                </div>
                <div class="newPassword pb20">
                	<p class="mt15">请您按如下步骤修改密码：</p>
                    <div class="note mt10">
                    	<p class="fl ml15 mt25"><img src="../newUI/images/bluefirst.png" />&nbsp;获取短信验证码：</p>
                        <div class="freeAccess fl mt20"><a href="javascript:void(0)" onclick="valiPHnum()">免费获取</a></div>
                    </div>
                    <div class="downArrow"><img src="../newUI/images/downArrow.png" /></div>
                    <div class="recompose">
                    	<p class="fl mt10 bluetwo"><img src="../newUI/images/bluetwo.png" />&nbsp;输入刚刚获得的短信验证码信息：</p>
                        <div class="words-2 fl pl30 mt15">
                            <p>用户名：</p>
                            <span class="fb" id="userCd"><bean:write name="theForm" property="userCode" /> </span>
                            <div class="clear"></div>
                        </div>
                        <div class="words-2 fl pl30 mt15">
                            <p>短信验证码：</p>
                            <input class="shortIn" type="text" value="" name="phoneNum" id="phoneNum">
                            <div class="clear"></div>
                        </div>
                        <div class="words-2 fl pl30 mt15">
                            <p>新密码：</p>
                            <input class="longIn"  type="password" value="" name="userPassword" id="newPW">
                            <div class="clear"></div>
                        </div>
                        <p class="prompt mt5">密码长度为8-16个字符，同时包含英语字母、数字和特殊符号</p>
                        <div class="words-2 fl pl30 mt15">
                            <p>重复新密码：</p>
                            <input class="longIn"  type="password" value="" id="REnewPW">
                            <div class="clear"></div>
                        </div> 
                        <div class="words-2 fl pl30 mt15">
                            <p>验证码：</p>
                            <input class="shortIn" type="text" value="" name="num" id="num">
                            <div class="clear"></div>
                        </div>
                       	<p class="prompt mt5">不区分大小写，<a href="javascript:void(0)" onclick="anotherPIC1()">换一张</a></p><br/>
                       	<div class="words codeChart" id="yanzhengma"></div>
                      	<div class="words-2 nextStp2 mt20"><a href="javascript:void(0)" onclick="finishIt()">完&nbsp;成</a></div>
                    </div>
                </div>
                <div class="contact mt20 pb40">
                	<h6>常见问题：</h6>
                	<p>1、请确认您的手机处于信号畅通且可接收短信状态</p><br/>
                    <p>2、有时短信网关拥堵会造成短信接收延迟，请稍等片刻。</p><br/>
                    <p>3、如果无法正常获取验证码，请联系客服热线咨询。</p>
                </div>
            </div>               
        </div>
        <p class="tip">
        	<logic:messagesPresent message="true">
				<html:messages id="msg" message="true">
					<bean:write name="msg" />
				</html:messages>
			</logic:messagesPresent>
		</p>  
   </div>
   <div class="footer f14 tc"> 京ICP备05002571&nbsp;|&nbsp;中国移动通信版权所有 </div>
</div>
</html:form>	
</body>

</html>
