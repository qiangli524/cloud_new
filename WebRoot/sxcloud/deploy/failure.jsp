<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<%@ include file="../common/link.jsp"%>
<head>
	<title></title>
	<script type="text/javascript">
	//登陆
	function check(obj){
		if(kong(obj.ip,"ip")){
			return ;
		}
		if(kong(obj.port,"端口号")){
			return ;
		}
		//端口号
		if(!isnumber(obj.port.value)){
			alert("端口号只能输入数字");
			obj.port.focus();
			return;
		}
		if(kong(obj.user,"用户名")){
			return ;
		}
		if(kong(obj.pwd,"密码")){
			return ;
		}
		obj.submit();
	}
	//重置
	function cz(){
		
		theForm.ip.value="";
		theForm.port.value="";
		theForm.user.value="";
		theForm.pwd.value="";
	}
	//为空检查
	function kong(theForm,title){
		
		if(theForm.value == ""){
			alert(title+"不能为空!");
			theForm.focus();
			return true;
		}
		return false;
	}
	function isnumber(str)
	 {
	  var number_chars = "1234567890";
	    var i;
	    for (i=0;i<str.length;i++)
	      {
	          if (number_chars.indexOf(str.charAt(i))==-1) return false;
	      }
	      return true;
	 }
	
    </script>
</head>
<body class="pop-body scrollbody">
<s:form action="depvideo_makeDeployVideo" method="post" id="theForm">
			<table width="100%" border="0" cellspacing="0" class="pop-table nosize">
				    <td class="til">
						连接失败
					</td>
				</tr>
				
			</table>
</s:form>
</body>
