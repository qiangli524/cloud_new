<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ include file="../common/taglib.jsp" %>
<%@ include file="../common/link.jsp"%>
<%@ include file="../common/view.jsp"%>
<head>
<title></title>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/tableSort/tableSort.js"></script>
<script type="text/javascript">
	
	$(function(){
		$("#datepicker1").datepicker({showOn: 'button', buttonImage: '../cresources/default/images/date-icon.gif', buttonImageOnly: true});
		$("#datepicker2").datepicker({showOn: 'button', buttonImage: '../cresources/default/images/date-icon.gif', buttonImageOnly: true});

	})

	function resetForm(theForm){
		theForm.start_time.value = '';
		theForm.end_time.value = '';
	}

   function searchRequest() { 
		theForm.submit();
 	}
 	function addRequest() { 
 	    
 	    theForm.action = 'time_addDeployTimeConfig.do'
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
 	    alert("请勾选需要修改信息！");
 	    return false ;
 	    }else if(couterNum>1){
 	    alert("一次只能处理单条信息");
 	    return false ;
 	    }
 	    theForm.action = 'time_modDeployTimeConfig.do'
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
 	    alert("请勾选需要删除的部署时间配置信息！");
 	    return false ;
 	    }else if(couterNum>1){
 	    alert("一次只能删除单条部署时间配置信息");
 	    return false ;
 	    }
 	    theForm.action = 'time_delDeployTimeConfig.do';  
		theForm.submit();
 	}
 	
</script>
</head>
<body>
<s:form action="time_listDeployTimeConfig.do" method="post" id="theForm">
<s:hidden name="theForm.ID" id="ID"></s:hidden>
<div class="scrollbody">
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
				   <th onclick="sort(theTable,1,'string')">部署时间配置名称</th>  
				   <th onclick="sort(theTable,2,'string')">应用名称</th>              
                   <th onclick="sort(theTable,3,'date')">调度时间</th>
                   <th onclick="sort(theTable,4,'date')">更新时间</th>
                   <th onclick="sort(theTable,5,'string')">状态</th>
			  </tr>
			  </thead>
			  <tbody>
			  <s:iterator value="theForm.resultList" id="theBean">
			  	<tr>
			  		<td><input name="checkboxid" type="checkbox" value="<s:property value="#theBean.ID"/>"/></td>
			  		<td><s:property value="#theBean.NAME"/></td>
							<td><s:property value="#theBean.APPNAME"/></td>
			  		<td><s:property value="#theBean.EXPRESSION"/></td>
			  		<td><s:property value="#theBean.UPDATETIME"/></td>
			  		<td>
			  			<s:if test="#theBean.STATUS==1">启动</s:if>
			  			<s:elseif test="#theBean.STATUS==0">停止</s:elseif>
			  		</td>
			  	</tr>
			  </s:iterator>
			  </tbody>
			</table>
		</div>
	</div><!--blue-wrap end -->
    </div><!--box end -->
</s:form>
</body>
