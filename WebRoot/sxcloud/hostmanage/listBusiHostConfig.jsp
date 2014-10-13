<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ include file="../common/taglib.jsp" %>
<%@ include file="../common/link.jsp"%>
<%@ include file="../common/view.jsp"%>
<html:html locale="true">
<head>
<title></title>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/tableSort/tableSort.js"></script>
<script type="text/javascript">
function cutString(text, canvasWidth) 
 {
 	var suffix = "...";
    var tempText = text.substring(0, canvasWidth);
    if(canvasWidth >= text.length) return text;
    return tempText+suffix
}	
	$(function(){
		$("#datepicker1").datepicker({showOn: 'button', buttonImage: '../cresources/default/images/date-icon.gif', buttonImageOnly: true});
		$("#datepicker2").datepicker({showOn: 'button', buttonImage: '../cresources/default/images/date-icon.gif', buttonImageOnly: true});

	})

	function resetForm(theForm){
		theForm.HOSTNAME.value='';
		theForm.HOSTUSERNAME.value= '';
	}

   function searchRequest() {
   
   		var hostusername=theForm.HOSTUSERNAME.value;
   		
   		if(checkusername(hostusername)){
   		
   			alert("主机用户名输入不合法，只能为字符和数字");
   			document.theForm.HOSTUSERNAME.value='';
	     	document.theForm.HOSTUSERNAME.focus;
   			return false;
   		}
		theForm.submit();
 	}
 	function addRequest(hostid) {
 	var HOSTID = hostid;
 	    theForm.action = "hostconfig_addBusiHostConfig.do?HOSTID="+HOSTID;
		theForm.submit();
 	}
 	function modRequest() { 
 	    var couterNum = 0;
 	    var checkboxids = document.getElementsByName("checkboxid");
 	    if(checkboxids!=null&&checkboxids.length>0){
 	    for(var i=0;i<checkboxids.length;i++){
 	      if(checkboxids[i].checked){
 	      couterNum = couterNum + 1 ;
 	      theForm.ID.value = checkboxids[i].value;
 	      }
 	    }
 	    }
 	    if(couterNum==0){
 	    alert("请勾选需要修改主机配置！");
 	    return false ;
 	    }else if(couterNum>1){
 	    alert("一次只能处理单条主机配置信息");
 	    return false ;
 	    }
 	    theForm.action = "hostconfig_modBusiHostConfig.do"; 
		theForm.submit();
 	}
 	function delRequest() {
 	var couterNum = 0;
 	    var checkboxids = document.getElementsByName("checkboxid");
 	    if(checkboxids!=null&&checkboxids.length>0){
 	    for(var i=0;i<checkboxids.length;i++){
 	      if(checkboxids[i].checked){
 	      couterNum = couterNum + 1 ;
 	      theForm.ID.value = checkboxids[i].value;
 	      
 	      }
 	    }
 	    }
 	    if(couterNum==0){
 	    alert("请勾选需要删除主机配置信息！");
 	    return false ;
 	    }else if(couterNum>1){
 	    alert("一次只能删除单条主机配置信息");
 	    return false ;
 	    }
 	    $.get("hostconfig_queryAjx_type.do?userid="+theForm.ID.value,{'time':new Date().toString()}, function(data, textStatus){
			   if(data=='ok'){
			      alert('超级管理员用户不能被删除！');
			      return false;
			   }else{
			   		if(confirm("确定删除该用户吗?")==true){
			   			theForm.action = "hostconfig_delBusiHostConfig.do"; 
						theForm.submit();
			   		}
			   }	
		});
 	}
 	
 	function checkusername(username){
  		var pattern=/^([a-zA-Z0-9]|[\uFE30-\uFFA0|V:])*$/gi;
    	if(pattern.test(username)) {
	     return false;
    	}
    	 return true;
 	  }
 	  
 	  
$(document).ready(function(){
  $(".passwd").mouseover(function(){ 
  $(this).next(".longpasswd").show();
  });
  $(".passwd").mouseout(function(){
  $(this).next(".longpasswd").hide();
  });
  $(".longpasswd").mouseover(function(){
  $(this).show();
  });
  $(".longpasswd").mouseout(function(){
  $(this).hide();
  });
  $(".path").mouseover(function(){ 
  $(this).next(".longpath").show();
  });
  $(".path").mouseout(function(){
  $(this).next(".longpath").hide();
  });
  $(".longpath").mouseover(function(){
  $(this).show();
  });
  $(".longpath").mouseout(function(){
  $(this).hide();
  });
});
</script>
<style type="text/css">
.longpasswd,.longpath{
display:none;position:absolute;left:0;bottom:0;background:gray;height: 100%;rgba(0,0,0,0.8);
}
</style>
</head>
<body>
<div class="mainbody">
<s:form action="hostconfig_listBusiHostConfig" method="post" cssStyle="theForm" id="theForm">
<input type="hidden" name="FUNCSID" value="<%=(String)request.getParameter("FUNCSID") %>"/>
 <s:hidden name="theForm.ID"  id="ID"/>
 <s:hidden name="theForm.IP"  id="IP"/>
 <s:hidden name="theForm.TYPE" id="TYPE" />
 	<div class="pd-20 bgcolor-1">
			<h2 class="utt-1">主机配置</h2>
			<div class="bord-1 pd-10">			
			<div class="clearfix mgt-10">
				<label class="vm">主机用户名：</label>
				<s:textfield name="theForm.HOSTUSERNAME" id="HOSTUSERNAME"  cssClass="inpt-1 vm"/>
						<s:hidden name="theForm.HOSTID" id="HOSTID"/>
				<label class="mgl-20 vm">主机名称：</label>
				<s:textfield name="theForm.HOSTNAME" id="HOSTNAME"   cssClass="inpt-1 vm"/>
				<span class="ubtn-1 mgl-20"><input type="button" onclick="javascript:searchRequest()" value="查询" /></span>
				<span class="ubtn-2 mgl-20"><input type="button" onclick="javascript:resetForm(document.getElementById('theForm'))" value="重置" /></span>
				<span class="ubtn-2 mgl-20"><input type = "button" class="btn-style02" value = "返回" onclick = "window.history.back()" /></span>
			</div>
			<div class="utt-2 mgt-20">
				<a class="icon-add" href="javascript:void(0)" onclick="addRequest();return false;">新增</a>
				<a class="icon-modify" href="javascript:void(0)" onclick="modRequest();return false;" >修改</a>
				<a class="icon-del" href="javascript:void(0)" onclick="delRequest();return false;" >删除</a>
			</div>
			<table id="theTable" width="100%" class="blue-table sorttable" border="0" cellspacing="0">
			  <thead>
			  <tr>
<%--			       <th>选择</th>--%>
				   <th onclick="sort(theTable,0,'string')">主机名称</th>
				   <th onclick="sort(theTable,1,'string')">主机IP</th> 
                   <th onclick="sort(theTable,2,'string')">应用名称</th>
                   <th onclick="sort(theTable,3,'string')">业务用户名</th>
                   <th onclick="sort(theTable,4,'string')">部署路径</th>
                   <th onclick="sort(theTable,5,'string')">文件路径</th>
                   <th onclick="sort(theTable,6,'string')">日志路径</th>
                   <th onclick="sort(theTable,7,'string')">访问路径</th>
<%--                   <th>例外文件路径</th> --%>
<%--				   <th>信任关系用户名</th>--%>
<%--                   <th>主机密码</th>--%>
<%--                   <th>磁盘空间</th>--%>
<%--                   <th>用户状态</th>--%>
			  </tr>
			  </thead>
			  <tbody>
			   <s:iterator id="theBean" value="theForm.resultList">
						<tr>
<%--							<td><input name="checkboxid" type="checkbox" value="<s:text name="#theBean.ID"/>"/></td>--%>
							<td><s:property value="#theBean.HOSTNAME" /></td>
							<td><s:property value="#theBean.IP" /></td>
							<td><s:property value="#theBean.EXAMPLE_NAME" /></td>
							<td><s:property value="#theBean.HOSTUSERNAME" /></td>
							
							<td  style="width: 100px"><a style="color: black;" class="font-more" title='<s:property value="#theBean.base_path"/>' >
							<s:property value="#theBean.base_path" />
							</a></td>
							<td  style="width: 100px"><a style="color: black;" class="font-more" title='<s:property value="#theBean.deploy_path"/>' >
							<s:property value="#theBean.deploy_path" />
							</a></td>
							<td  style="width: 100px"><a style="color: black;" class="font-more" title='<s:property value="#theBean.logPath"/>' >
							<s:property value="#theBean.logPath" />
							</a></td>
							<td  style="width: 100px"><a style="color: black;" class="font-more" title='<s:property value="#theBean.appPath"/>' >
							<s:property value="#theBean.appPath" />
							</a></td>
							
<%--							<td><div style="position:relative;z-index: 51"><span class="path"><script type="text/javascript">document.write(cutString('<s:property value="#theBean.SPECIALPATH" />', 6))</script></span><span class="longpath"><s:property value="#theBean.SPECIALPATH" /></span></div></td>--%>
<%--							<td><s:property value="#theBean.CREDITUSER" /></td>--%>
<%--						<td><div style="position:relative;z-index: 50"><span class="passwd"><script type="text/javascript">document.write(cutString('<s:property value="#theBean.HOSRPWD" />', 6))</script></span><span class="longpasswd"><s:property value="#theBean.HOSRPWD" /></span></div></td>--%>
<%--							<td><s:property value="#theBean.SPACE" /></td>--%>
<%--							<td>--%>
<%--							<s:if test="#theBean.STATUS==0">正在创建</s:if>--%>
<%--							<s:if test="#theBean.STATUS==1">已创建</s:if>--%>
<%--							<s:if test="#theBean.STATUS==2">正在删除</s:if>--%>
<%--							<s:if test="#theBean.STATUS==4">删除失败</s:if>--%>
<%--							</td>--%>
						</tr>
				</s:iterator>
				
						  
			  </tbody>
			</table>
			<div class="pages">
				<jsp:include page="/sxcloud/inc/Pagination.jsp?formId=theForm" />
			</div>	
		 </div>
	</s:form>
</div>	
</body>
</html:html>
