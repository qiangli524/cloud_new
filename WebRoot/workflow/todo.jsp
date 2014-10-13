<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ include file="/sxcloud/common/taglib.jsp" %>
<%@ include file="/sxcloud/common/link.jsp"%>
<%@ include file="/sxcloud/common/view.jsp"%>
<html:html locale="true">
<title></title>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/tableSort/tableSort.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/js/checkbox.js"></script>
<script type="text/javascript">
	function bindingImageRequest(){
		var couterNum = 0;
		var resourceName = "";
 	    var checkboxids = document.getElementsByName("checkboxid");
 	    if(checkboxids!=null&&checkboxids.length>0){
 	    for(var i=0;i<checkboxids.length;i++){
 	      if(checkboxids[i].checked){
 	      couterNum = couterNum + 1 ;
 	     resourceName = checkboxids[i].value;
 	      }
 	    }
 	    }
 	    if(couterNum==0){
 	    alert("请勾选需要绑定的信息！");
 	    return false ;
 	    }else if(couterNum>1){
 	    alert("一次只能处理单条信息");
 	    return false ;
 	    }
 	    theForm.action = 'workflow_newWorkFlow.do?resourceName='+resourceName;
		theForm.submit();
	}
</script>
</head>
<body>
<s:form action="workflow_newWorkFlow.do"  cssStyle="theForm" id="theForm">
<div class="scrollbody">
	<div class="blue-wrap noborder">
<%--		<div class="table-head">
			<jsp:include page="/sxcloud/inc/Pagination.jsp?formId=theForm" />
		</div>--%>
		<div class="table-ct">
			<table id="theTable" width="100%" class="blue-table sorttable" border="0" cellspacing="0">
			  <thead>
			  <tr>
			  		<th>选择</th>
			  		<th onclick="sort(theTable,1,'string')">流程名称</th>
					<th onclick="sort(theTable,2,'string')">流程描述</th>
					<th onclick="sort(theTable,3,'string')">流程发起人</th>
					<th onclick="sort(theTable,4,'string')">版本号</th>
			  </tr>
			  </thead>
			  <tbody>
			  <s:iterator value="bpmWorkFlowList" id="theBean">
			  	<tr>
			  		<td><input name="checkboxid" type="checkbox" value="<s:property value='#theBean.name'/>"/></td>
			  		<td><s:property value="#theBean.name"/></td>
			  		<td><s:property value="#theBean.desc"/></td>
			  		<td><s:property value="#theBean.author"/></td>
			  		<td><s:property value="#theBean.version"/></td>
			  	</tr>
			  </s:iterator>
			  </tbody>
			</table>
			
<%--			<table width="100%" border="0" cellspacing="0" class="pop-table nosize">	--%>
<%--			<tr>--%>
<%--				<td colspan="4" class="btnCenter">--%>
<%--					<input type="button" class="thickbox btn-style02" value="发起流程"--%>
<%--						onclick="javascript:bindingImageRequest();return false;" />--%>
<%--				</td>--%>
<%--			</tr>--%>
		</table>
		</div>
	</div><!--blue-wrap end -->
    </div><!--box end -->
</div>
</s:form>
</body>
