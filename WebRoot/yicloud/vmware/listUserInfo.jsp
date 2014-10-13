<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.*"%>
<%@ include file="../../sxcloud/common/taglib.jsp" %>
<%@ include file="../../sxcloud/common/view.jsp"%>
<head>
<title></title>
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/tableSort/tableSort.js"></script>
<script type="text/javascript">
	

	function resetForm(theForm){
		theForm.ACCOUNT.value = '';
		theForm.NAME.value = '';
	}

   function searchRequest() { 
		theForm.submit();
 	}
 	
 	$(function(){
 		$(".query").click(function(){
    			if($(".query-form").is(":visible")){
    				$(".query-form").slideUp("slow");
    			}else{
    				$(".query-form").slideDown("slow");
    			}
    		});
         $("[name='hostauth']").click(function(){
         	$td=$(this).parent();
            var USERID=$td.attr("USERID");
            var ACCOUNT=$td.attr("ACCOUNT");
    	 	$.dialog({
    			id:'add',
    			title:'权限设置',
    			width: '700px',
				height: '400px',
				fixed: true,
				lock:true,
    			max: true,
    			min: true,
    			content: 'url:vmauth_editUserVirAuthority.do?USERID='+USERID+'&ACCOUNT='+ACCOUNT+'&flag=host'
    	 	});
         });
         $("[name='vmauth']").click(function(){
         	$td=$(this).parent();
            var USERID=$td.attr("USERID");
            var ACCOUNT=$td.attr("ACCOUNT");
    	 	$.dialog({
    			id:'add',
    			title:'权限设置',
    			width: '700px',
				height: '400px',
				fixed: true,
				lock:true,
    			max: true,
    			min: true,
    			content: 'url:vmauth_editUserVirAuthority.do?USERID='+USERID+'&ACCOUNT='+ACCOUNT+'&flag=vm'
    	 	});
         });
    });
</script>
</head>
<body onLoad="self.focus();document.theForm.ACCOUNT.focus()" class="pop-body scrollbody">
<div class="mainbody">
<s:form action="vmauth_listUserInfo" method="post" cssStyle="theForm" id="theForm">
 <div class="pd-20 bgcolor-1">
			<h2 class="utt-1">资源权限管理</h2>
			<div class="bord-1 pd-10">			
			<div class="clearfix mgt-10">
				<label class="vm">账号：</label>
				<s:textfield name="userForm.ACCOUNT" id="ACCOUNT" cssClass="inpt-1 vm" maxlength="30"></s:textfield>
				<label class="mgl-20 vm">姓名：</label>
				<s:textfield name="userForm.NAME" id="NAME" maxlength="30" cssClass="inpt-1 vm" ></s:textfield>
				<span class="ubtn-1 mgl-20"><input type="button" onclick="javascript:searchRequest()" value="查询" /></span>
				<span class="ubtn-2 mgl-20"><input type="button" onclick="javascript:resetForm(document.getElementById('theForm'))" value="重置" /></span>
			</div>
			<div class="utt-2 mgt-20">
			</div>
			<table width="100%" class="blue-table sorttable" border="0" cellspacing="0" id="theTable">
			  <thead>
			  <tr>
				   <th onclick="sort(theTable,0,'string')">账号</th>                
                   <th onclick="sort(theTable,2,'string')">姓名</th>
                   <th onclick="sort(theTable,3,'string')">状态</th>
                   <th>操作</th>
			  </tr>
			  </thead>
			  <tbody>
			  <s:iterator value="userForm.resultList" id="theBean">
						<tr>
							<td><s:text name="#theBean.ACCOUNT"/></td>
							<td><s:text name="#theBean.NAME"/></td>
							<td>
							<s:if test="#theBean.STATUS==0">
							冻结
							</s:if>
							<s:elseif test="#theBean.STATUS==1">
							活跃
							</s:elseif>
							<s:elseif test="#theBean.STATUS==2">
							锁定
							</s:elseif>
							</td>
							<td USERID='<s:text name="#theBean.ID"/>'  ACCOUNT='<s:text name="#theBean.ACCOUNT"/>'>
							<a href="javascript:;" name="hostauth">
								主机权限配置
							</a>
							<a href="javascript:;" name="vmauth">
								虚拟机权限配置
							</a>
							</td>
						</tr>
				</s:iterator>		  
			  </tbody>
			</table>
			<div class="pages">
			</div>	
		 </div>
	</s:form>
</body>
