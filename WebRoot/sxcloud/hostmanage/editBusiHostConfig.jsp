<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />

<link
	href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css"
	rel="stylesheet" type="text/css" />
<link
	href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css"
	rel="stylesheet" type="text/css" />

<script type="text/javascript"
	src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
<head>
<title></title>

<script type="text/javascript">
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
	
	function submitRequest(thisForm){ 
	    if($("#HOSTUSERNAME").val().length==0){
	    	alert("主机用户名不能为空");
	    	return false;
	    }
	   /* if(thisForm.CREDITUSER.value.length==0){
	    	alert("信任关系用户名不能为空");
	    	return false;
	    }*/
	    
	    if(thisForm.APPID.value==""||thisForm.APPID.value==null){
	    	alert("请选择此用户对应的应用");
	    	return false;
	    }
<%--	   	--%>
<%--	    if(thisForm.HOSRPWD.value.length ==0){--%>
<%--	     alert("主机密码不能为空！");--%>
<%--	     return false  ;--%>
<%--	    }--%>
	    if(thisForm.SPACE.value.length ==0){
	     alert("磁盘空间不能为空！");
	     return false  ;
	    }
<%--	    var hostpwd=document.theForm.HOSRPWD.value;--%>
   		if(thisForm.TYPE.value==-1){
	    	alert("请选择用户类型");
	    	return false;
	    }
   		var hostid=document.theForm.HOSTID.value;
   		var space=document.theForm.SPACE.value;
   		var ifexample = $("#ifexample").find("option:selected").val();
<%--	    if(thisForm.HOSRPWD.value != thisForm.reHOSRPWD.value){--%>
<%--			alert("两次密码输入不一致！");--%>
<%--	     	return false  ;--%>
<%--		}--%>
<%--   		if(checkusername(hostpwd)){--%>
<%--   			alert("主机密码输入不合法，只能为数字和字符字符！");--%>
<%--   			document.theForm.HOSRPWD.value='';--%>
<%--	     	document.theForm.HOSRPWD.focus;--%>
<%--   			return false;--%>
<%--   		}--%>
   		if(!checknumber(space)){
   			alert("磁盘空间输入不合法,如1.1G!");
   			document.theForm.SPACE.value='';
   			document.theForm.SPACE.focus;
   			return false;
   		}
	    var hostusername_bf=thisForm.HOSTUSERNAME_BF.value;
	    var usertype_bf=thisForm.TYPE_BF.value;
	    /*判断是增加还是修改，如果是增加则需进行用户名，用户类型判断，修改则不需要*/
	    if(hostusername_bf==null||hostusername_bf==""){
	    	var hostusername=document.theForm.HOSTUSERNAME.value;
		    var usertype=thisForm.TYPE.value;
		   
	    	if(checkusername(hostusername)){
   			alert("主机用户名输入不合法，只能为数字和字符字符！");
   			document.theForm.HOSTUSERNAME.value='';
	     	document.theForm.HOSTUSERNAME.focus;
   			return false;
   			} 
	    	if(thisForm.HOSTUSERNAME.value.length ==0){
	     		alert("主机用户名不能为空！");
	     		return false  ;
	   		}
	   		
	   		if(usertype==2||usertype=='2'){
	   		if(document.getElementById('nameexisterror').innerHTML == '用户名已存在'){
	   		 alert('用户名已经存在,请重新输入');alert(document.getElementById('nameexisterror').innerHTML);
			      return false;
	   		}else{
	   			if(ifexample == '0'){
	   				if(confirm("确定生成部署实例吗?")){
	   					thisForm.submit();
	   				}
	   			}else if(ifexample == '1'){
	   				if(confirm("确定不生成部署实例吗?")){
	   					thisForm.submit();
	   				}
	   			}
			   		}
	   		}else{
	    		$.get("hostconfig_queryAjx_usertype.do?usertype="+usertype+"&&hostid="+hostid,{'time':new Date().toString()}, function(data, textStatus){
					if(data=='no'){
			    	  alert('超级管理员已经存在,请重新选择');
			      	  return false;
			   		}else{
			   		$.get("hostconfig_queryAjx_username.do?hostusername="+hostusername+"&&hostid="+hostid,{'time':new Date().toString()}, function(data, textStatus){
			   if(data=='no'){
			      alert('用户名已经存在,请重新输入');
			      return false;
			   }else{
			   		 if(ifexample == '0'){
	   				if(confirm("确定生成部署实例吗?")){
	   					thisForm.submit();
	   				}
	   			}else if(ifexample == '1'){
	   				if(confirm("确定不生成部署实例吗?")){
	   					thisForm.submit();
	   				}
	   			}
			   		}	
			   		 }); 
			   		 }		      
			      });
	   		}
	    }else{
	    	if(ifexample == '0'){
	   				if(confirm("确定生成部署实例吗?")){
	   					thisForm.submit();
	   				}
	   			}else if(ifexample == '1'){
	   				if(confirm("确定不生成部署实例吗?")){
	   					thisForm.submit();
	   				}
	   			}
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
	
	function checkPWD(thisForm){
		if(thisForm.HOSRPWD.value != thisForm.reHOSRPWD.value){
			document.getElementById('error').innerHTML = '两次密码不一致';
		}else{
			document.getElementById('error').innerHTML = '';
		}	
	}
	function UnameCheck(obj){
	 if("" != obj.value  && null !=obj.value && obj.value.length != 0){
		$.get("hostconfig_queryAjx_username.do?hostusername="+obj.value+"&&hostid="+document.theForm.HOSTID.value,{'time':new Date().toString()}, function(data, textStatus){
			   if(data=='no'){
			      document.getElementById('nameexisterror').innerHTML = '用户名已存在';
			   }else{
			   		  document.getElementById('nameexisterror').innerHTML = '用户名可以使用';
			   		}	
			   		 }); 	
	}else{
		document.getElementById('nameexisterror').innerHTML = '';	   		 
	}
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
	 	var hostusername=document.theForm.HOSTUSERNAME.value;
		var hostid = theForm.HOSTID.value;
	 	$.get("hostconfig_queryAjx_username.do?hostusername="+hostusername+"&&hostid="+hostid,{'time':new Date().toString()}, function(data, textStatus){
			if(data=='no'){
				alert('用户名已经存在,请重新输入');
				document.theForm.HOSTUSERNAME.value='';
		     	document.theForm.HOSTUSERNAME.focus;
			}	
		}); 
	 }
	 //判断是否是管理员
	 function unique_userType(){
	 	var tag = 0;
	 	var usertype=document.theForm.TYPE.value;
		var hostusername=document.theForm.HOSTUSERNAME.value;
		var hostid = theForm.HOSTID.value;
		if (usertype==1) {
		 	$.get("hostconfig_queryAjx_usertype.do?usertype="+usertype+"&&hostid="+hostid,{'time':new Date().toString()}, function(data, textStatus){
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
	 //是否显示部署路径等td
	 function filePathDivIsShow() {
		 var ifexample = $("#ifexample").find("option:selected").val();
		 if(ifexample == 1) {
			 $("tr.none").hide();
		 } else {
			 $("tr.none").show();
		 }
	 }
	 //是否信任关系等td
	 function hideNotCredituser() {
		 var isCredituser = $("#isCredituser").find("option:selected").val();
		 if(isCredituser == 1) {
			 $("#notCredituserTr").hide();
			 $("#isCredituserTr").show();
		 } else {
			 $("#isCredituserTr").hide();
			 $("#notCredituserTr").show();
		 }
	 }
	 
	 function pageOnLoad() {
		 filePathDivIsShow();
		 hideNotCredituser();
	 }
</script>
<style>
.area {
	width: 800px;
	height: 20px;
	vertical-align: middle;
	overflow-y: hidden;
	line-height: 20px;
	padding: 0px;
	border: none;
	border: 1px solid #999;
}

.path {
	width: 800px;
	vertical-align: middle;
}
</style>
</head>
<body class="pop-body scrollbody" onload="pageOnLoad()">
	<s:form action="hostconfig_saveBusiHostConfig.do" method="post"
		id="theForm">
		<input type="hidden" name="FUNCSID"
			value="<%=(String) request.getParameter("FUNCSID")%>" />
		<s:hidden name="theForm.IP" id="IP" />
		<s:hidden name="theForm.HOSTUSERNAME_BF" id="HOSTUSERNAME_BF" />
		<s:hidden name="theForm.TYPE_BF" id="TYPE_BF" />
		<s:hidden name="theForm.HOSTID" id="HOSTID" />
		<%-- 	<s:hidden name="theForm.HOSTNAME" id="HOSTNAME" /> --%>
		<s:hidden name="theForm.ID" id="ID" />
		<table id=mytable width="100%" border="0" cellspacing="0"
			class="pop-table nosize">
			<tr>
				<td class="til">主机编号</td>
				<td><s:text name="theForm.HOSTID" />
				</td>
				<td class="til">主机IP</td>
				<td><s:text name="theForm.IP" />
				</td>
			</tr>
			<tr>
				<td class="til">主机名称</td>
				<td><s:text name="theForm.HOSTNAME" />
				</td>
				<td class="til">是否信任关系<font style="color:red">*</font>
				</td>
				<td><s:select list="#{'0':'否','1':'是'}"
						name="theForm.isCredituser" id="isCredituser" onchange="hideNotCredituser()"></s:select>
				</td>
			</tr>
			<tr id="notCredituserTr">
				<td class="til">主机用户名 <font color="red">*</font>
				</td>
				<td style="width: 330px">
					<!--  
						<s:if test="theForm.HOSTUSERNAME !=null">
							<s:property value="theForm.HOSTUSERNAME"/>
						</s:if>
					
						 --> <s:textfield name="theForm.HOSTUSERNAME" id="HOSTUSERNAME"
						onblur="UnameCheck(this)" cssClass="txt"></s:textfield> <span id="nameexisterror"
					style="color: red"></span>
				</td>
				<td class="til">ssh用户密码</td>
				<td><s:password name="theForm.sshPwd" cssClass="txt"
						showPassword="true"></s:password>
				</td>
			</tr>
			<tr id="isCredituserTr">
				<td class="til">信任关系用户名 <font color="red">*</font>
					</td>
					<td style="width: 330px"><s:textfield name="theForm.CREDITUSER"
							id="CREDITUSER" cssClass="txt"></s:textfield>
					</td>
				<td class="til">&nbsp;</td>
				<td>&nbsp;
				</td>
			</tr>
			<%-- 	<tr>
				<td class="til">
					信任关系用户名
					<font color="red">*</font>
				</td>
				<td>
					<s:textfield name="theForm.CREDITUSER" id="CREDITUSER" cssClass="txt"/>
				</td>
				<td class="til">
					主机用户名
					<font color="red">*</font>
				</td>
				<td>
					<s:textfield name="theForm.HOSTUSERNAME" id="HOSTUSERNAME" cssClass="txt" onblur="UnameCheck(this)"/>
					<span id = "nameexisterror" style="color: red"></span>
				</td>
			</tr>
			--%>
			<tr>
				<td class="til">ssh端口号</td>
				<td><s:textfield name="theForm.sshPort" cssClass="txt"></s:textfield>
				</td>
				<td class="til">&nbsp;</td>
				<td>&nbsp;
				</td>
			</tr>
			<tr>
				<td class="til">磁盘空间 <font color="red">*</font>
				</td>
				<td><s:textfield name="theForm.SPACE" id="SPACE" cssClass="txt" />
				</td>
				<td class="til">用户类型 <font color="red">*</font>
				</td>
				<td><s:if test="theForm.TYPE==0">
						<s:select headerKey="-1" headerValue="-请选择-"
							list="#{'1':'超级用户','2':'普通用户'}" name="theForm.TYPE" id="TYPE"
							onblur="unique_userType();">
						</s:select>
					</s:if> <s:elseif test="theForm.TYPE>0">
						<s:select disabled="disabled" list="#{'1':'超级用户','2':'普通用户'}"
							name="theForm.TYPE" id="TYPE">
						</s:select>
					</s:elseif>
				</td>
			</tr>
			<tr>
				<td class="til">基准应用</td>
				<td><s:select id="ID" list="theForm.appList" listKey="ID"
						name="theForm.APPID" id="APPID" listValue="APPNAME" headerKey="0"
						headerValue="-请选择-">
					</s:select>
				</td>
				<td class="til">是否生成部署实例 <font style="color:red">*</font>
				</td>
				<td><s:select list="#{'1':'否','0':'是'}"
						name="theForm.ifexample" id="ifexample" onchange="filePathDivIsShow()"></s:select>
				</td>
			</tr>
			<tr>
				<td class="til">访问应用地址</td>
				<td colspan="3"><s:textarea name="theForm.appPath"
						cssClass="area" cssStyle="text-decoration:underline;"></s:textarea>
				</td>
<%--			</tr>--%>
<%--			<tr>--%>
<%--				<td class="til">启动日志文件路径--%>
<%--				</br>--%>
<%--				<font color="red">(多个路径用英文逗号分割)</font>--%>
<%--				</td>--%>
<%--				--%>
<%--				<td colspan="3"><s:textarea name="theForm.logPath"--%>
<%--						cssClass="path"></s:textarea>--%>
<%--				</td>--%>
<%--			</tr>--%>
<%--			<tr class="none">--%>
<%--				<td class="til">例外文件路径</td>--%>
<%--				<td colspan="3"><s:textarea name="theForm.SPECIALPATH"--%>
<%--						id="SPECIALPATH" cssClass="path">--%>
<%--					</s:textarea>--%>
<%--				</td>--%>
<%--			</tr>--%>
			<tr class="none">
				<td class="til">应用部署路径<font color="red">*</td>
				<td colspan="3"><s:textarea name="theForm.base_path"
						cssClass="path"></s:textarea>
				</td>
			</tr>
<%--			<tr class="none">--%>
<%--				<td class="til">部署文件路径	</br>--%>
<%--				<font color="red">(注意：若为部署功能，则内容同部署路径)</font></td>--%>
<%--				<td colspan="3"><s:textarea name="theForm.deploy_path"--%>
<%--						cssClass="path"></s:textarea>--%>
<%--				</td>--%>
<%--			</tr>--%>
<%--			<tr class="none">--%>
<%--				<td class="til">启动脚本路径</td>--%>
<%--				<td colspan="3"><s:textarea name="theForm.start_shell"--%>
<%--						cssClass="path"></s:textarea>--%>
<%--				</td>--%>
<%--			</tr>--%>
<%--			<tr class="none">--%>
<%--				<td class="til">停止脚本路径</td>--%>
<%--				<td colspan="3"><s:textarea name="theForm.shutdown_shell"--%>
<%--						cssClass="path"></s:textarea>--%>
<%--				</td>--%>
<%--			</tr>--%>
			<tr>
				<td colspan="4" class="btnCenter"><input type="button"
					class="thickbox btn-style02" value="确定"
					onclick="javascript:submitRequest(document.getElementById('theForm'));return false;" />
					<input type="button" class="thickbox btn-style02" value="重置"
					onclick="javascript:void(document.getElementById('theForm').reset());return false;" />
					<input type="button" class="thickbox btn-style02" value="返回"
					onclick="window.history.back()" />
				</td>
			</tr>
		</table>
	</s:form>
</body>
