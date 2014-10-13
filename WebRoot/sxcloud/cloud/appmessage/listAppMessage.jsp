<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../common/taglib.jsp" %>
<%@ include file="../../common/link.jsp"%>
<%@ include file="../../common/view.jsp"%>
<head>
<title></title>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/tableSort/tableSort.js"></script>
<script type="text/javascript">
	
	$(function(){
		$("#datepicker1").datepicker({showOn: 'button', buttonImage: '../cresources/default/images/date-icon.gif', buttonImageOnly: true});
		$("#datepicker2").datepicker({showOn: 'button', buttonImage: '../cresources/default/images/date-icon.gif', buttonImageOnly: true});

	})

	function resetForm(theForm){
		theForm.APP_ID.value = '';
		theForm.APP_NAME.value = '';
	}

	function searchRequest() { 
		theForm.submit();
 	}
 	
	function addRequest() {
		theForm.flag.value = 0;
 	    theForm.action = 'appmess_addAppMessage.do' 
		theForm.submit();
	}
	function modRequest() { 
 	    var couterNum = 0;
 	    var checkboxids = document.getElementsByName("checkboxid");
 	    if(checkboxids!=null&&checkboxids.length>0){
 	    for(var i=0;i<checkboxids.length;i++){
 	      if(checkboxids[i].checked){
 	      couterNum = couterNum + 1 ;
 	      theForm.APP_ID.value = checkboxids[i].value;
 	      }
 	    }
 	    theForm.flag.value = 1;
 	    }
 	    if(couterNum==0){
 	    alert("请勾选需要修改信息！");
 	    return false ;
 	    }else if(couterNum>1){
 	    alert("一次只能处理单条信息");
 	    return false ;
 	    }
 	    theForm.action = 'appmess_modAppMessage.do' 
		theForm.submit();
 	}
 	function delRequest() {
 	var couterNum = 0;
 	    var checkboxids = document.getElementsByName("checkboxid");
 	    if(checkboxids!=null&&checkboxids.length>0){
 	    for(var i=0;i<checkboxids.length;i++){
 	      if(checkboxids[i].checked){
 	      couterNum = couterNum + 1 ;
 	      theForm.APP_ID.value = checkboxids[i].value;
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
 	    if(confirm("确定要删除该应用吗?")==true){
 	    	theForm.action = 'appmess_delAppMessage.do'  
			theForm.submit();
 	    }
 	}
</script>
</head>
<body>
<s:form action="appmess_listAppMessage.do" method="post" cssClass="theForm" id="theForm">
<s:hidden name="theForm.flag" id="flag"></s:hidden>
<div class="scrollbody">
	<div class="query">
		<div class="title"><%=getImageTag(request,"query-icon.gif")%></div>
	</div>
	<div class="box on">
        <div class="query-form">
                <table width="100%" class="querytable" border="0">
                  <tr>
                    <td class="til">应用编号:</td>
                    <td>
						<s:textfield name="theForm.APP_ID" id="APP_ID" cssClass="txt"></s:textfield>
                    </td>
                    <td class="til">应用名称:</td>
                    <td>
						<s:textfield name="theForm.APP_NAME" id="APP_NAME" cssClass="txt"></s:textfield>
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
					<th onclick="sort(theTable,1,'string')">应用编号</th>
					<th onclick="sort(theTable,2,'string')">应用名称</th>
					<th onclick="sort(theTable,3,'string')">进程名1</th>
					<th onclick="sort(theTable,4,'string')">进程名2</th>
					<th onclick="sort(theTable,5,'string')">协议类型</th>
					<th onclick="sort(theTable,6,'string')">所占内存</th>
					<th onclick="sort(theTable,7,'string')">状态</th> 
					<th onclick="sort(theTable,8,'string')">所属主机</th>
					<th onclick="sort(theTable,9,'string')">所属系统名称</th>
					<th onclick="sort(theTable,10,'string')">应用类型</th> 
					<th onclick="sort(theTable,11,'string')">描述</th>  
			  </tr>
			  </thead>
			  <tbody>
			  <s:iterator value="theForm.resultList" id="theBean">
			  	<tr>
			  		<td><input name="checkboxid" type="checkbox" value="<s:property value="#theBean.APP_ID"/>"/></td>
			  		<td><s:property value="#theBean.APP_ID"/></td>
			  		<td><s:property value="#theBean.APP_NAME"/></td>
			  		<td><s:property value="#theBean.PROCESS"/></td>
			  		<td><s:property value="#theBean.PROCESS_AUX"/></td>
			  		<td>
			  			<s:if test="#theBean.PROTOCOL==0">ssh</s:if>
			  			<s:elseif test="#theBean.PROTOCOL==1">telnet</s:elseif>
			  		</td>
			  		<td><s:property value="#theBean.MEM"/></td>
			  		<td>
			  			<s:if test="#theBean.APP_STAT==0">down</s:if>
			  			<s:elseif test="#theBean.APP_STAT==1">up</s:elseif>
			  			<s:elseif test="#theBean.APP_STAT==2">异常</s:elseif>
			  		</td>
			  		<td><s:property value="#theBean.VH_NAME"/></td>
			  		<td><s:property value="#theBean.SYS_NAME"/></td>
			  		<td>
			  			<s:if test="#theBean.APP_TYPE==0">物理主机</s:if>
			  			<s:elseif test="#theBean.APP_TYPE==1">虚拟机主机</s:elseif>
			  		</td>
			  		<td><s:property value="#theBean.APP_DESC"/></td>
			  	</tr>
			  </s:iterator>
			  </tbody>
			</table>
		</div>
	</div><!--blue-wrap end -->
    </div><!--box end -->
</div>
</s:form>
</body>
