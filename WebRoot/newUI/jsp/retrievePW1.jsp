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
    <script type="text/javascript">
        $(function(){
            UID_subAccordion("div.accordion-1");
            UID_subAccordion("div.accordion-2");
            UID_tabChange("click", "#label3");
            
          
        	//获取验证码    
        	var str="<img src='./random.jsp' id='random' valign='absmiddle' hspace='5'/>";
				$("#yanzhengma").append(str);
				
			$("#username").focusout(function() {
        	
        		var username=$("#username").val();
        		
					
					 $.ajax({
					type: "POST",
					cache: "false",
					async: true,
					dataType: "text",
					url: "/validRetriPw.do?username="+ username,
					success: function(msg) {
					
					//1表示用户名存在，0表示不存在
						if(1==msg){
							$("#msg").html("");
							$("#msg").append("<img src='../newUI/images/accept.png'  />");
						}else{
							$("#msg").html("");
							$("#msg").append("<img src='../newUI/images/delete.png'  /><font size=2 >用户名不存在，请重新输入！</font>");
							//$("#msg").after("<span >用户名不存在，请重新输入！</span>");
						}
					}
				});
		});	
			
        })
        function anotherPIC(){
        	//再换一张
        	//$("#random").remove();  
        	//var str="<img src='./random.jsp' id='random' valign='absmiddle' hspace='5'/>";
				//$("#yanzhengma").append(str);
				document.getElementById("random").src="./random.jsp?time="+new Date();
        }
        function retrievePW2(){
        	//下一步
        	var username=$("#username").val();
        	if(username==""||username==null){
        		alert("用户名不能为空！");
        	}
        	document.getElementById("theForm").action="retrievePW2.do";
        	document.getElementById("theForm").submit();
        }
        
    </script>
</head>
 
<body>
<html:form action="retrievePW4"  method="post" styleId="theForm">
	  <bean:define id="theForm" name="loginForm" />
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
   <div class="main-2 mgt10 overflowh">
       	<div class="main-2-bt_1"></div>
       	<div class="main-2-bd">
        	<div class="forPassword">
                <div class="flow_steps pb50">
                    <h2 class="retake fl">找回用户密码：</h2>
                    <div class="nesting fl">
                        <ul class="flow_nav">
                            <li class="current_prev">1.输入用户名</li>
                            <li class="current">2.选择找回密码方式</li>
                            <li class="last">3.找回密码</li>
                        </ul>
                    </div>
                </div>	  
                <div class="words">
                    <p>用户名：</p>
                    <input class="" type="text"  id="username" name="userCode" >
                    <span id="msg">*</span>
                    <div class="clear"></div>
                </div>
                <div class="words">
                    <p>验证码：</p>
                    <input class="shortIn" type="text" value="" id="num" name="num">
                    <div class="clear"></div>
                </div> 
                <div class="words checkbox">不区分大小写，<a href="javascript:void(0)" onclick="anotherPIC()">换一张</a></div> 
                <div class="words codeChart" id="yanzhengma"></div>
                <div class="words nextStp pb50"><a href="javascript:void(0)" onclick="retrievePW2()">下一步</a></div>
           </div> 
           <p class="tip">
        	<logic:messagesPresent message="true">
				<html:messages id="msg" message="true">
					<bean:write name="msg" />
				</html:messages>
			</logic:messagesPresent>
		</p>                 
        </div>
   </div>
   <div class="footer f14 tc"> 京ICP备05002571&nbsp;|&nbsp;中国移动通信版权所有 </div>
</div>
</html:form>	
</body>

</html>
