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
            
            
        })
        //换个用户名
        function anotherU(){
        	document.getElementById("theForm").action="retrievePW.do";
        	document.getElementById("theForm").submit();
        }
        //通过手机
        function byPhoneNum(){
        	document.getElementById("theForm").action="retrievePW3.do";
        	document.getElementById("theForm").submit();
        }
    </script>
</head>
 
<body>
<html:form action="retrievePW"  method="post" styleId="theForm">
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
                            <li class="current current-2">1.输入用户名</li>
                            <li class="current_prev">2.选择找回密码方式</li>
                            <li class="last">3.找回密码</li>
                        </ul>
                    </div>
                </div>	  
                <div class="crumbs">
                    <p>你正在找回用户名是<span><bean:write name="theForm" property="userCode" />  </span>的密码<a href="javascript:void(0)" onclick="anotherU()">[换一个用户名]</a></p>
                </div>
                <div class="throughDesign">
                	<img src="../newUI/images/cellphone.png" />
                    <div class="designWord fl mt20">
                        <a href="javascript:void(0)" onclick="byPhoneNum()" class="fb">通过手机</a><br/>
                        
                    </div>
                   <div class="clear"></div>
                </div>
                
            </div>               
        </div>
   </div>
   <div class="footer f14 tc"> 京ICP备05002571&nbsp;|&nbsp;中国移动通信版权所有 </div>
</div>
</html:form>	
</body>

</html>
