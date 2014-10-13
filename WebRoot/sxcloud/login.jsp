<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ include file="common/taglib.jsp"%>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>登录</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/sxcloud/nresources/default/css/basiCss/reset.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/sxcloud/nresources/default/css/basiCss/common.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/sxcloud/nresources/default/css/pageCss/login.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/js/Base64.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>

<script type="text/javascript">
//解决session过期不能跳出Iframe的问题。
if (window != top) 
	top.location.href = location.href; 
function check(obj){
	if(obj.ACCOUNT.value == ''){
		alert("请输入账号！");
		return;
	}	
	if(obj.PASSWORD.value.length<8) {
		alert("密码长度不能小于8位！");
		return ;
	}
	$("#PASSWORD").attr("value",str_encode($("#PASSWORD").val()));
	obj.submit();
}


</script>
</head>

<body class="login">
	<s:form action="login_login.do" id="theForm" method="post">
	<div class="login-wrap">
		 <div class="logo"><img class="pngfix" src="<%=request.getContextPath()%>/sxcloud/nresources/default/images/pageImg/logo.png" /> </div> 
    	<div class="login-panel">
            <form class="clearfix">
                <div class="login-area clearfix">
                    <div class="login-account">
                        <span class="username"><s:textfield class="js_loginAccount" type="text" value="账号/邮箱/手机号" onfocus="if(value==defaultValue){value=''};" onblur="if(!value)value=defaultValue" id="ACCOUNT" name="theForm.ACCOUNT"/></span>
                      <!-- <span class="error-tip">用户名不存在或输入有误，请重新输入！</span>  -->  
                        <span class="icon-dropdown js_dropdown"></span>
                        <div class="drop-input js_dropInput">
                        	<input class="" type="text" value="请输入企业名称" onfocus="if(value==defaultValue){value=''};" onblur="if(!value)value=defaultValue"/>
                            <span class="icon-dropup js_dropup"></span>
                        </div>
                    </div>
                    <div class="login-pwd mgl-25">
                        <span class="password"><s:password showPassword="true"  type="password" value="" onfocus="if(value==defaultValue){value=''};" onblur="if(!value)value=defaultValue" id="PASSWORD" name="theForm.PASSWORD" onclick="javascript:if(event.keyCode==13){check(document.getElementById('theForm'));return false;}"/></span>
                     <%--    <span class="error-tip">密码不匹配或大写键盘锁定，请重新输入！</span>  --%>
                    </div>
                </div>
                
                <div class="avoid-login">
                <%-- 
                    <input type="checkbox" class="inp-chb" checked="checked"><b class="b-chb"></b>全天免登陆
					  <span>
						<b class="b-zhmm1"></b>
					    <a class="a-repwd1" href="">重置密码</a> 
					  </span>
					 --%>
                </div>
                
                <div class="log-btn">
                    <button type="button"  onclick="javascript:check(document.getElementById('theForm'));return false;" >登&nbsp;录</button>
                   <%--  <button type="button" class="mgl-20">登&nbsp;录</button> --%>
                </div>
            </form>
            <div class="copyright">
               <%--  © 2011 北京思特奇信息技术股份有限公司 --%>
            </div>
        </div>
    </div>
    </s:form>
<script src="<%=request.getContextPath()%>/sxcloud/njs/jquery/jquery-1.7.2.min.js"></script>
<script src="<%=request.getContextPath()%>/sxcloud/njs/ui/ui.js"></script>
<!--[if IE 6]>
<script src="<%=request.getContextPath()%>/sxcloud/njs/plugin/DD_belatedPNG_0.0.8a-min.js"></script>
<![endif]-->
</body>
</html>