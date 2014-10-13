<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ include file="../../common/taglib.jsp" %>
<%@ include file="../../common/link.jsp"%>
<html:html locale="true">
<head>
	<title></title>

	<script type="text/javascript">
	var api = frameElement.api; 
	var w = api.opener;
 	api.button({
		id:'OkAnd',
		name: '确定',
		type: 'submit',
		callback:submitRequest,
		focus: true
	},
	{
    	id:'cancle',
		name: '取消'
	}); 
	
	
    function Trim(str){
		return str.replace(/^\s+|\s+$/g,"");
	}
	
	
	function checknumber(str){
		var pattern1=/^([1-9])([0-9])*.([0-9])+G$/;
		var pattern2=/^([1-9])([0-9])*G$/;
		if(!pattern1.test(str)){
			if(!pattern2.test(str)){
				return false;
			}
		}
		return true;
	}
	
	function submitRequest(){   
	  	
	  	if(theForm.hostUserName.value.length ==0){
	     		alert("主机用户名不能为空！");
	     		return false  ;
	   		}
	    if(theForm.password.value.length ==0){
	     alert("主机密码不能为空！");
	     return false  ;
	    }
	 
	    if(theForm.SPACE.value.length ==0){
	     alert("磁盘空间不能为空！");
	     return false  ;
	    }
	    
	    var hostpwd=document.theForm.password.value;
   		var hostusername = document.theForm.hostUserName.value;
   		
   		var hostid=document.theForm.eq_id.value;
   		var space=document.theForm.SPACE.value;
   		if(!checknumber(space)){
   			alert("磁盘空间输入不合法,如1.1G!");
   			document.theForm.SPACE.value='';
   			document.theForm.SPACE.focus;
   			return false;
   		}
   		
	    var hostusername_bf=theForm.HOSTUSERNAME_BF.value;
	    var usertype_bf=theForm.TYPE_BF.value;
	  
	    /*判断是增加还是修改，如果是增加则需进行用户名，用户类型判断，修改则不需要*/
	    if(hostusername_bf==null||hostusername_bf==""){
	    
	    	
		    var usertype=theForm.TYPE.value;
		    
	    	if(checkusername(hostusername)){
   			alert("主机用户名输入不合法，只能为数字和字符字符！");
   			document.theForm.hostUserName.value='';
	     	document.theForm.hostUserName.focus;
   			return false;
   			}
	    	if(hostusername.length ==0){
	     		alert("主机用户名不能为空！");
	     		return false  ;
	   		}
	   		if(usertype==0){
	   			alert("用户类型不能为空！");
	     		return false  ;
	   		}
	   		if(usertype==2||usertype=='2'){
	   			
	   		}else{ 
	    		$.get("resource_queryAjx_usertype.do?usertype="+usertype+"&&hostid="+hostid,{'time':new Date().toString()}, function(data, textStatus){
					if(data=='no'){
			    	  alert('超级管理员已经存在,请重新选择');
			      	  return false;
			   		}
			      });
	   		}
	   
	    }else {
	    	if(usertype==1){
	    		if(!unique_userType()) {
	    			return;
	    		}
	    	}
	    }
	    theForm.action='resource_saveHostConfig.do?HOSTID='+hostid;
	    theForm.submit();
	    if (theForm.flag.value == 'host') {
	    	api.get("listHostConfig").searchRequest(hostid);
	    } else {
	    	w.searchRequest();
	    }
	}

	function checkValid(obj){		
		var port = Trim(obj.PORT.value);
		var bool1 = isnumber(port);
		if(!bool1)
		{
			alert("端口不符合要求");
			return false;
		}	
		
		return true;
	}
	
	
	
	/* 检测输入的字符串是否符合要求 */
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
	function checkusername(username){
  		var pattern=/^([a-zA-Z0-9]|[\uFE30-\uFFA0|V:])*$/gi;
  		
    	if(pattern.test(username)) {
	     return false;
    	}
    	 return true;
 	  }
 function unique_username(){
	 	var hostusername=document.theForm.hostUserName.value;
		var hostid = theForm.eq_id.value;
		if(hostusername.length!=0) {
		 	$.get("resource_queryAjx_username.do?hostusername="+hostusername+"&&hostid="+hostid,{'time':new Date().toString()}, function(data, textStatus){
				if(data=='no'){
					alert('用户名已经存在,请重新输入');
					document.theForm.hostUserName.value='';
			     	document.theForm.hostUserName.focus;
				}	
			}); 
		}
	 }

//判断是否是管理员
	 function unique_userType(){
	 	var tag = 0;
	 	var usertype=document.theForm.TYPE.value;
		var hostusername=document.theForm.hostUserName.value;
		var hostid = theForm.eq_id.value;
		if (usertype==1) {
		 	$.get("resource_queryAjx_usertype.do?usertype="+usertype+"&&hostid="+hostid,{'time':new Date().toString()}, function(data, textStatus){
				if(data=='no'){
					alert('超级管理员已经存在,请重新选择');
					document.theForm.TYPE.value=-1;
			     	document.theForm.TYPE.focus;
			     	tag = 1;
			     	return false;
			     }
			});
		}
		if (tag==1) {
			return false;
		}
	 }
	 $(function(){
	 	var pwd = document.getElementById("password");
	 	if('<%=request.getAttribute("password")%>' != 'null'){
	 		pwd.value='<%=request.getAttribute("password")%>';	
	 	}
	 })
</script>
</head>
<body class="pop-body" onLoad="self.focus();document.theForm.hostUserName.focus()">
	<div class="pd-20 bgcolor-1">
		<div class="bord-1 pd-10">
			<s:form action="resource_saveHostConfig.do" method="post" id="theForm" cssStyle="theForm">
				<s:hidden name="theForm.ID" id="ID"/>
				<s:hidden name="theForm.eq_id" id="eq_id"/>
				<s:hidden name="theForm.HOSTUSERNAME_BF" id="HOSTUSERNAME_BF"/>
				<s:hidden name="theForm.TYPE_BF" id="TYPE_BF"/>
				<s:hidden name="theForm.flag" id="flag"/>
		     	<table  id=mytable width="100%" border="0" cellspacing="0" class="pop-table nosize">
		       		<tr>
					    <td class="til">
							主机编号 
						</td>
					<td >
						<s:text name="theForm.eq_id" />
					</td>
					<td class="til">
						主机名称
					</td>
					<td>
                        <s:text name="theForm.eq_hostname" />
					</td>
				</tr>
				<tr>
				    <td class="til">
						主机用户名 <font color="red">*</font>
					</td>
					<td >
						<s:if test="theForm.hostUserName==null || theForm.hostUserName==''">
							<s:textfield name="theForm.hostUserName" onblur="unique_username();" id="hostUserName" cssClass="inpt-2"/>
						</s:if>
						<s:elseif test="theForm.hostUserName!=null ">
							<s:textfield name="theForm.hostUserName" readonly="true" cssClass="disable" id="hostUserName" />
						</s:elseif>
					</td>
					<td class="til">
						主机密码<font color="red">*</font>
					</td>
					<td>
                        <s:password name="theForm.password" id="password" cssClass="inpt-2"/>
					</td>
				</tr>
				<tr>
				    <td class="til">
						磁盘空间<font color="red">*</font>
					</td>
					<td>
					<s:textfield name="theForm.SPACE" id="SPACE" cssClass="inpt-2"/>
					</td>
					<td class="til">
						用户类型<font color="red">*</font>
					</td>
					<td>
						<s:if test="theForm.hostUserName==null || theForm.hostUserName==''">
					    	<s:select name="theForm.TYPE" headerKey="0" headerValue="请选择" list="#{'1':'超级用户','2':'普通用户'}" id="TYPE" cssClass="select-1">
					    	</s:select>
					 	</s:if>
					 	<s:elseif
						test="theForm.hostUserName!=null && theForm.hostUserName!=''">
						<s:select headerKey="-1" headerValue="请选择"
							list="#{'1':'超级用户','2':'普通用户'}" name="theForm.TYPE" id="TYPE" cssClass="select-1">
						</s:select>
					</s:elseif>
					</td>
				</tr> 
<%--				<tr>--%>
<%--					<td colspan="4" class="btnCenter">--%>
<%--						<span class="ubtn-green"><input type="button" value="确定"--%>
<%--							onclick="javascript:submitRequest(document.getElementById('theForm'));return false;" />--%>
<%--						</span>--%>
<%--						<span class="ubtn-orange mgl-20"><input type="button" value="重置"--%>
<%--							onclick="javascript:void(document.getElementById('theForm').reset());return false;" />--%>
<%--						</span>--%>
<%--						<span class="ubtn-blue mgl-20"><input type="button" value="返回"--%>
<%--				 			onclick="javascript:backup();return false;" />--%>
<%--				 		</span>--%>
<%--					</td>--%>
<%--				</tr>--%>
		     </table>
	</s:form>
	 </div>
    </div>
</body>
</html:html>
