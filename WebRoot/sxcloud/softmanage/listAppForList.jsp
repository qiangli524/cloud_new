<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ include file="../common/taglib.jsp" %>
<%@ include file="../common/link.jsp"%>
<%@ include file="../common/view.jsp"%>
<html:html locale="true">
<head>
<title></title>


<link href="<%=request.getContextPath() %>/sxcloud/cjs/ui2/nresources/common/css/default.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath() %>/sxcloud/cjs/ui2/nresources/common/css/location_tj.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/tableSort/tableSort.js"></script>
<%--<script type="text/javascript" src="../cjs/ui2/njs/ui/ui.js"></script>--%>


<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
<script type="text/javascript">
	/*
	$(function(){
		$("#datepicker1").datepicker({showOn: 'button', buttonImage: '../cresources/default/images/date-icon.gif', buttonImageOnly: true});
		$("#datepicker2").datepicker({showOn: 'button', buttonImage: '../cresources/default/images/date-icon.gif', buttonImageOnly: true});

	})
*/
	function resetForm(theForm){
		theForm.APPNAME.value = '';
		theForm.STATUS.value = '';
	}

   function searchRequest() { 
		theForm.submit();
 	}
 	function addRequest() {
 	    theForm.action = 'app_addApp.do' 
		theForm.submit();
 	}
 	function modRequest(id) { 
 	    theForm.ID.value=id;
 	    $.getJSON("app_AppFlagStatus.do?ID="+theForm.ID.value,{'time':new Date().toString()},function(data){
			if(data=='2' || data=='3' || data=='4' || data=='6' || data=='7'){
			  alert("应用状态为 '已注册' 或 '已注销' 的状态才能被修改！");
			}else{
			  theForm.action = 'app_modApp.do' 
		      theForm.submit();
			}
		});
 	}
 	function delRequest(id) {
 		
 	    theForm.ID.value=id;
 	    $.getJSON("app_AppFlagStatus.do?ID="+theForm.ID.value,{'time':new Date().toString()},function(data){
			if(data=='2' || data=='3' || data=='4'|| data=='6' || data=='7'){
			  alert("应用状态为 '已注册' 或 '已注销' 的状态才能被删除！");
			}else{
			  theForm.action = 'app_delApp.do'
			     if(confirm("确定要删除该应用吗！")==true)
		         {
		            theForm.submit();
			     }  
			}
		});
 	}
 	
 	
 	
 	//timer();
 	function timer(){
        setStatus();
        setTimeout("timer()",20000);    
    }
    function setStatus(){
        var str="";
        var checkboxids = document.getElementsByName("checkboxid");
        if(checkboxids!=null&&checkboxids.length>0){
          for(var i=0;i<checkboxids.length;i++){
            if(i==checkboxids.length-1){
              str+=checkboxids[i].value.split("|")[0];
            }else{
              str+=checkboxids[i].value.split("|")[0]+',';
            }
          }
          $.getJSON("appStatus.do?ID="+str,{'time':new Date().toString()},function(data){
			for(j=0;j<data.length;j++){
			  $("#div"+data[j].ID).html(data[j].DEPLOY_FLAG_NAME);
			}
		   });          
        }
    }
    
    
    $(function(){
		 $("[name='example']").unbind().live("click",function(){
        	currentEdit=$(this);
        	var SYS_ID = $(this).attr("SYS_ID");
        	alert(SYS_ID);
    		$.dialog({
    			id:'vdi',
    			title:'部署实例',
    			width: '650px',
    			height: '330px',
    			max: true,
    		    min: true,
    			content: 'url:bizsystem_getHostByBusi.do?SYS_ID='+SYS_ID
    			});
              });
            });
</script>
</head>
<body>
<div class="mainbody">
<s:form action="app_listApp.do" method="post" styleId="theForm" id="theForm">
 <!-- <bean:define id="theForm" name="appForm"/> -->
 <s:hidden name="theForm.ID" id="ID"></s:hidden>
 		<div class="pd-20 bgcolor-1">
			<h2 class="utt-1">用户组管理</h2>
			<div class="bord-1 pd-10">		
			<table id="theTable" width="100%" class="blue-table sorttable" border="0" cellspacing="0">
			  <thead>
			  <tr>
			  		<th onclick="sort(theTable,1,'string')">应用名称</th>
					<th onclick="sort(theTable,2,'string')">部署策略</th>
					<th onclick="sort(theTable,3,'string')">应用状态</th>
					<th onclick="sort(theTable,4,'string')">部署主机用户</th>
					<th onclick="sort(theTable,5,'string')">基准主机部署路径</th>
			  </tr>
			  </thead>
			  <tbody>
			      <s:iterator value="theForm.appList" id="theBean">
				     <tr>
				     	<td><s:text name="#theBean.base_name"/></td>
     				    <td><s:if test="#theBean.stategy_type==1">录像部署</s:if>
							<s:elseif test="#theBean.stategy_type==2">基准部署</s:elseif>
						</td>
						<td>
							<s:if test="#theBean.status==1">已注册</s:if>
							<s:elseif test="#theBean.status==2">正在部署</s:elseif>
							<s:elseif test="#theBean.status==3">已部署</s:elseif>
							<s:elseif test="#theBean.status==4">正在注销</s:elseif>
							<s:elseif test="#theBean.status==5">已注销</s:elseif>
						</td>    
		            	<td><s:text name="#theBean.hostusername"/> </td>
		            	<td><s:text name="#theBean.deploypath"/></td>
            		</tr>
				</s:iterator>
				</tbody>
			</table>
			<div class="pages mgb-10">
				<jsp:include page="/sxcloud/inc/Pagination.jsp?formId=theForm" />
			</div>	
		 </div>
	</s:form>
</div>	
</body>
</html:html>
