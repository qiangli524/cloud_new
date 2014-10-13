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

	function addRequest() {
		theConForm.flag.value = 0;
 	    theConForm.action = 'templetConfig_addTempletConfig.do' 
		theConForm.submit();
	}
	function modRequest() { 
 	    var couterNum = 0;
 	    var checkboxids = document.getElementsByName("checkboxid");
 	    if(checkboxids!=null&&checkboxids.length>0){
 	    for(var i=0;i<checkboxids.length;i++){
 	      if(checkboxids[i].checked){
 	      couterNum = couterNum + 1 ;
 	      theConForm.ID.value = checkboxids[i].value;
 	      }
 	    }
 	    theConForm.flag.value = 1;
 	    }
 	    if(couterNum==0){
 	    alert("请勾选需要修改信息！");
 	    return false ;
 	    }else if(couterNum>1){
 	    alert("一次只能处理单条信息");
 	    return false ;
 	    }
 	    theConForm.action = 'templetConfig_modTempletConfig.do' 
		theConForm.submit();
 	}
 	function delRequest() {
 	var couterNum = 0;
 	    var checkboxids = document.getElementsByName("checkboxid");
 	    if(checkboxids!=null&&checkboxids.length>0){
 	    for(var i=0;i<checkboxids.length;i++){
 	      if(checkboxids[i].checked){
 	      couterNum = couterNum + 1 ;
 	      theConForm.ID.value = checkboxids[i].value;
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
 	    if(confirm("确定要删除该配置项吗?")==true){
 	    	theConForm.action = 'templetConfig_delTempletConfig.do'  
			theConForm.submit();
 	    }
 	}
</script>
</head>
<body>
<s:form action="temlple_listAppMessage.do" method="post" cssClass="theForm" id="theConForm">
<s:hidden name="theConForm.flag" id="flag"></s:hidden>
<s:hidden name="theConForm.TYPE" id="TYPE"></s:hidden>
<s:hidden name="theConForm.ID" id="ID"></s:hidden>
<div class="scrollbody">
	
	<div class="blue-wrap noborder">
		<div class="table-head">
		    <ul class="btns">
				<li><input type="button" class="thickbox btn-style02" value="增加" onclick = "addRequest();return false;" /></li>
				<li><input type="button" class="thickbox btn-style02" value="修改" onclick = "modRequest();return false;" /></li>
				<li><input type="button" class="thickbox btn-style02" value="删除" onclick = "delRequest();return false;" /></li>
				<li><input type="button" class="thickbox btn-style02" value="返回" onclick="window.history.back()"/></li>
			</ul>
			<jsp:include page="/sxcloud/inc/Pagination.jsp?formId=theConForm" />
		</div>
		<div class="table-ct">
			<table id="theTable" width="100%" class="blue-table sorttable" border="0" cellspacing="0">
			  <thead>
			  <tr>
			  		<th>选择</th>
			  		<th onclick="sort(theTable,1,'string')">配置项</th>
					<th onclick="sort(theTable,2,'string')">配置值</th>
			  </tr>
			  </thead>
			  <tbody>
			  <s:iterator value="theConForm.resultList" id="theBean">
			  	<tr>
			  		<td><input name="checkboxid" type="checkbox" value="<s:property value="#theBean.ID"/>"/></td>
			  		<td><s:property value="#theBean.KEY"/></td>
			  		<td><s:property value="#theBean.VALUE"/></td>
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
