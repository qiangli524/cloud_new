<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../common/taglib.jsp" %>
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="cache-control" content="no-cache"/>
<meta http-equiv="expires" content="0"/>

<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/tableSort/tableSort.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/js/checkbox.js"></script>

<%@ page import="com.sitech.basd.sxcloud.rsmu.web.util.page.image.ImageUtil" %>
<%!
public String getImageTag(HttpServletRequest request, String path) {
	return ImageUtil.getImageTag(request, path);
}%>

<head>
<title></title>
<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
<script type="text/javascript">
	
	function resetForm(theForm){
		theForm.SYS_NAME.value = '';
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
 		
 		$("a[name='listapp']").click(function(){ 
 			var $td = $(this).parent();
	        var sysid = $td.attr("sysid"); 
 			$.dialog({
	 		 	id:'listapp',
	 			title:'基准应用信息',
	 			width: '1000px',
	 			height: '500px',
	 			content: 'url:app_listApp.do?sys_id='+sysid
	 	 	}); 
 		});
 		
 		$("a[name='listdeploy']").click(function(){
 			var $td = $(this).parent();
	        var sysid = $td.attr("sysid"); 
 			$.dialog({
	 		 	id:'listdeploy',
	 			title:'部署实例信息',
	 			width: '1000px',
	 			height: '500px',
	 			content: 'url:dep_listDeployExample.do?sys_id='+sysid
	 	 	}); 
 		});
 	});
 	
	function addRequest() {
		theForm.flag.value = 0;
 	    $.dialog({
			id:'goAddBizsysPage',
			title:'添加业务系统',
			height:'300px',
			width:'534px',
			content:'url:bizsystem_addBizSystem.do?createType=list'
		});
	}
	
	function saveBizSystem(busiSystemObj){
		var formData = $.formDataFormat(busiSystemObj.theForm);
		theForm.action = "bizsystem_saveBizSystem.do?" + formData + "&createType=list";
		theForm.submit();
	}
	
	function modRequest() { 
 	    var couterNum = 0;
 	    var SYS_ID = '';
 	    var checkboxids = document.getElementsByName("checkboxid");
 	    if(checkboxids!=null&&checkboxids.length>0){
 	    for(var i=0;i<checkboxids.length;i++){
 	      if(checkboxids[i].checked){
 	      couterNum = couterNum + 1 ;
 	      SYS_ID = checkboxids[i].value;
 	      }
 	    }
 	    
 	    }
 	    if(couterNum==0){
 	    alert("请勾选需要修改信息！");
 	    return false ;
 	    }else if(couterNum>1){
 	    alert("一次只能处理单条信息");
 	    return false ;
 	    }
 	    $.dialog({
			id:'modifyBizsys',
			title:'修改业务系统',
			height:'300px',
			width:'534px',
			lock:true,
			content:'url:bizsystem_modBizSystem.do?createType=list' + "&SYS_ID=" +SYS_ID
		});
 	}
 	function delRequest() {
 		var couterNum = 0;
 	    var checkboxids = document.getElementsByName("checkboxid");
 	    var countApp = 0;
 	    if(checkboxids!=null&&checkboxids.length>0){
 	    for(var i=0;i<checkboxids.length;i++){
 	      if(checkboxids[i].checked){
 	      couterNum = couterNum + 1 ;
 	      theForm.SYS_ID.value = checkboxids[i].value;
 	     countApp = $(checkboxids[i]).attr("countApp");
 	      }
 	    }
 	    }
 	    if(couterNum==0){
 	    alert("请勾选需要删除信息！");
 	    return false ;
 	    }else if(couterNum>1){
 	    alert("一次只能删除单条信息");
 	    return false ;
 	    }
 	    if(countApp>0){
 	    	alert("不能删除不为空的业务系统！");
 	    	return false;
 	    }
 	    if(confirm("确定要删除该应用吗?")==true){
 	    	theForm.action = 'bizsystem_delBizSystem.do?operType='+"list";
			theForm.submit();
 	    }
 	}
 	
</script>

</head>
<body onLoad="self.focus();document.theForm.SYS_NAME.focus()"  class="scrollbody">
<s:form action="bizsystem_busiSystemInfo.do" method="post" cssClass="theForm" id="theForm">
<s:hidden name="theForm.flag" id="flag"></s:hidden>
<s:hidden name="theForm.SYS_ID"id="SYS_ID"></s:hidden>
<div>
	<div class="query">
		<div class="title"><%=getImageTag(request,"query-icon.gif")%></div>
	</div>
	<div class="box on">
        <div class="query-form">
                <table width="100%" class="querytable" border="0">
                  <tr>
                    <td class="til">系统名称:</td>
                    <td>
						<s:textfield name="theForm.SYS_NAME" id="SYS_NAME" cssClass="txt"></s:textfield>
                    </td>
                  </tr>
                  <tr>
                    <td colspan="8" class="btns">
                        <div>
							<input type = "button" class="thickbox btn-style02" value = "查询" onclick = "javascript:searchRequest()" />
							<input type = "button" class="btn-style02" value = "重置" onclick = "javascript:resetForm(document.getElementById('theForm'))" />
                        </div>
                    </td>
                  </tr>
                </table>
        </div><!--query-form end -->
	
	<div class="blue-wrap noborder">
		<div class="table-head">
		    <ul class="btns">
				<li><input type="button" class="thickbox btn-style02" value="增加" onclick = "addRequest();return false;" /></li>
				<li><input type="button" class="thickbox btn-style02" value="修改" onclick = "modRequest();return false;" /></li>
				<li><input type="button" class="thickbox btn-style02" value="删除" onclick = "delRequest();return false;" /></li>
			</ul>
			<jsp:include page="/sxcloud/inc/Pagination.jsp?formId=theForm" />
		</div>
		<div class="table-ct">
			<table id="theTable" width="100%" class="blue-table sorttable" border="0" cellspacing="0">
			  <thead>
			  <tr>
			  		<th>选择</th>
					<th onclick="sort(theTable,1,'string')">系统名称</th>
					<!-- <th>系统区域</th> -->
					
					<th onclick="sort(theTable,2,'string')">状态</th>
					<!--  <th>描述</th>-->
					<!-- 
					<th>插入时间</th>
					<th>修改时间</th>
					 -->
					<th onclick="sort(theTable,3,'string')">基准应用</th>
					<th onclick="sort(theTable,4,'string')">部署实例</th>
			  </tr>
			  </thead>
			  <tbody>
			  <s:iterator value="theForm.resultList" id="theBean">
			  	<tr >
			  		<td><input name="checkboxid" type="checkbox" value="<s:property value="#theBean.SYS_ID"/>" countApp="<s:property value="#theBean.countApp"/>"/></td>
			  		<td><s:property value="#theBean.SYS_NAME"/></td>
			  		<!--  <td><s:property value="#theBean.REGION_ID"/></td> -->
			  		<!--  
			  		<td>
			  			<s:if test="#theBean.REGION_ID==01">核心域BOSS</s:if>
			  			<s:elseif test="#theBean.REGION_ID==02">内部接入域</s:elseif>
			  			<s:elseif test="#theBean.REGION_ID==03">互联网接入域</s:elseif>
			  		</td>
			  		-->
			  		<td>
			  			<s:if test="#theBean.STATU==0">正常</s:if>
			  			<s:elseif test="#theBean.STATU==1">异常告警</s:elseif>
			  			<s:elseif test="#theBean.STATU==2">已停止</s:elseif>
			  		</td>
			  		<!--  
			  		<td><s:property value="#theBean.SYS_DESC"/></td>
			  		-->
			  		<!-- 
			  		<td><s:property value="#theBean.INSERT_DATE"/></td>
			  		<td><s:property value="#theBean.UPDATE_DATE"/></td>
			  		 -->
			  		
			  		 <td sysid='<s:property value="#theBean.SYS_ID" />'>
			  			 <s:if test="#theBean.countApp != 0">
			  		 		<a href="javascript:;" name="listapp"><s:property value="#theBean.countApp"/></a>
			  		 	 </s:if>
			  		 	 <s:else>
			  		 	 	<s:property value="#theBean.countApp"/>
			  		 	 </s:else>
			  		 	 &nbsp;	(个)
			  		 </td>
			  		 <td sysid='<s:property value="#theBean.SYS_ID" />'>
			  		 	<s:if test="#theBean.countDeploy != 0">
			  		 		<a href="javascript:;" name="listdeploy"><s:property value="#theBean.countDeploy"/> </a>
			  		 	</s:if>
			  		 	<s:else><s:property value="#theBean.countDeploy"/></s:else>
			  		 	&nbsp;(个)
			  		 </td>
			  	</tr>
			  </s:iterator>
			  </tbody>
			</table>
		</div>
	</div><!--blue-wrap end -->
    </div><!--box end -->
</div>
</s:form>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/dateformat/jquery.formDataOper.js"></script>
</body>
