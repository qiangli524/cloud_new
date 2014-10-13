<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../common/taglib.jsp"%>
<%@ include file="../../common/link_export.jsp"%>
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="cache-control" content="no-cache"/>
<meta http-equiv="expires" content="0"/>
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/ui/ui.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/tableSort/tableSort.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/js/checkbox.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/common.js"></script>
<%@ page import="com.sitech.basd.sxcloud.rsmu.web.util.page.image.ImageUtil" %>
<%!public String getImageTag(HttpServletRequest request, String path) {
		return ImageUtil.getImageTag(request, path);
	}%>
<script type="text/javascript"> 
	$(function(){  
          $("[name='add']").unbind().live("click",function(){ 
          		var flag = theForm.flag.value;

          		if (flag == 'host') {
          			var hostid = theForm.eq_id.value;
          			var api = frameElement.api;
					var w = api.opener;
		 			w.$.dialog({
		 				id:'add',
		 				title:'添加主机用户',
		 				lock:true,
		 				width: '700px',
		 				height: '300px',
		 				content: 'url:resource_addHostConfig.do?HOSTID='+hostid+'&flag=host'
		 	 		}); 
	 	 		} else {
	 	 			$.dialog({
		 				id:'add',
		 				title:'添加主机用户',
		 				lock:true,
		 				width: '700px',
		 				height: '300px',
		 				content: 'url:resource_addHostConfig.do'
		 	 		}); 
	 	 		}
 		   }); 
 		   $("[name='mod']").unbind().live("click",function(){ 
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
          		var flag = theForm.flag.value;

          		if (flag == 'host') {
          			var hostid = theForm.eq_id.value;
          			var api = frameElement.api;
					var w = api.opener;
		 			w.$.dialog({
		 				id:'mod',
		 				title:'修改主机用户',
		 				lock:true,
		 				width: '700px',
		 				height: '300px',
		 				content: 'url:resource_modHostConfig.do?'+$("#theForm").serialize()
		 	 		}); 
	 	 		} else {
	 	 			$.dialog({
		 				id:'mod',
		 				title:'修改主机用户',
		 				lock:true,
		 				width: '700px',
		 				height: '300px',
		 				content: 'url:resource_modHostConfig.do?'+$("#theForm").serialize()
		 	 		}); 
	 	 		}
 		   });   
      }); 
      
	function resetForm(theForm){
		theForm.eq_hostname.value='';
		theForm.hostUserName.value = '';
	}

   function searchRequest(hostid) {
   		var hostusername=document.theForm.hostUserName.value;
   		if(checkusername(hostusername)){
   			alert("主机用户名输入不合法，只能为字符和数字");
   			document.theForm.hostUserName.value='';
	     	document.theForm.hostUserName.focus;
   			return false;
   		}
   		theForm.action="resource_listHostConfig.do?eq_id="+hostid;
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
 	    /*
 	    $.get("resource_queryAjx_type.do?userid="+theForm.ID.value,{'time':new Date().toString()}, function(data, textStatus){
			   if(data=='ok'){
			      alert('超级管理员用户不能被删除！');
			      return false;
			   }else{
 	    		
			   }	
		});*/
		theForm.action = 'resource_delHostConfig.do'  
		theForm.submit();
 	}
 	
 	function checkusername(username){
  		var pattern=/^([a-zA-Z0-9]|[\uFE30-\uFFA0|V:])*$/gi;
    	if(pattern.test(username)) {
	     return false;
    	}
    	 return true;
 	  } 
</script>
</head>
<body onLoad="self.focus();document.theForm.eq_hostname.focus()" class="mainbody">
<s:form action="resource_listHostConfig.do" method="post" id="theForm" cssClass="theForm">
 <s:hidden name="theForm.eq_id" id="eq_id" />
 <s:hidden name="theForm.ID" id="ID" />
 <s:hidden name="theForm.TYPE" id="TYPE" />
 <s:hidden name="theForm.HOSTUSERNAME_BF" id="HOSTUSERNAME_BF" />
<s:hidden name="theForm.TYPE_BF" id="TYPE_BF" />
<s:hidden name="theForm.flag" id="flag" />
	<div class="pd-20 bgcolor-1">
		<div class="bord-1 pd-10">
			<div class="clearfix mgt-10">

<div>
	<div class="box on">
        <div class="query-form">
               <label class="vm">主机名称:</label>
			<s:textfield name="theForm.eq_hostname" id="eq_hostname"/>
			<label class="vm">主机用户名:</label>
			<s:textfield name="theForm.hostUserName" id="hostUserName" />
               <span class="ubtn-1 mgl-20"><input type="button" value="查询" onclick="javascript:searchRequest()" />
			<span class="ubtn-1 mgl-20"><input type="button" value="重置" onclick="javascript:resetForm(document.getElementById('theForm'))" />
		</div>
        <div  class="utt-2 mgt-20">
			<a class="icon-add" href="javascript:void(0)"  name="add" />增加</a>
			<a class="icon-modify" href="javascript:void(0)" name="mod" />修改</a>
			<a class="icon-del" href="javascript:delRequest()" name = "del" />删除</a>
		</div>       
        <table id="theTable" width="100%" class="blue-table sorttable" border="0" cellspacing="0">
			<thead>
				<tr>
			       <th>选择</th>
				   <th onclick="sort(theTable,1,'string')">主机编号</th>
				   <th onclick="sort(theTable,2,'string')">主机名称</th>
				   <th onclick="sort(theTable,3,'string')">主机IP</th>
				   <th onclick="sort(theTable,4,'string')">主机用户名</th>                
                   <th onclick="sort(theTable,5,'string')">主机密码</th>
                   <th onclick="sort(theTable,6,'string')">磁盘空间</th>
                   <th onclick="sort(theTable,7,'string')">用户状态</th>
			  </tr>
			</thead>
			 <tbody>
			 	<s:iterator id="theBean" value="theForm.userInfoList" >
					<tr>
						<td><input name="checkboxid" type="checkbox" value="<s:text name="#theBean.ID" />"/></td>
						<td><s:text name="#theBean.HOSTID" /></td>
						<td><s:text name="#theBean.HOSTNAME" /></td>
						<td><s:text name="#theBean.IP" /></td>
						<td><s:text name="#theBean.HOSTUSERNAME" /></td>
						<td>******</td>
						<td><s:text name="#theBean.SPACE" /></td>
						<td>
						<s:if test="#theBean.STATUS==0">正在创建</s:if>
						<s:if test="#theBean.STATUS==1">已创建</s:if>
						<s:if test="#theBean.STATUS==2">正在删除</s:if>
						<s:if test="#theBean.STATUS==3">删除失败</s:if>
						</td>
					</tr>
				</s:iterator>
			  </tbody>
		</table>
		<div class="pages mgb-10">
			<jsp:include page="/sxcloud/inc/Pagination.jsp?formId=theForm" />
		</div>
	</div>
</div>
</s:form>
</body>
</html:html>
