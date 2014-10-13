<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" %>
<%@ include file="../../sxcloud/common/taglib.jsp"%>
<%@ include file="../../sxcloud/common/link.jsp"%>
<head>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/tableSort/tableSort.js"></script>
<script type="text/javascript">
	
	$(function(){
		var size ='<%=request.getAttribute("size")%>';
		var div = window.parent.document.getElementById("deployAlarm");
		var h =  105+size*30;
		$(div).attr("style","height: "+h+"px");
	});
	
	function addRequest(){
		var theForm = document.getElementById("theForm");
 	    theForm.action = 'busitree_addDataBase.do';
	    theForm.submit();
	}
	
	function modRequest(){
		var couterNum = 0;
 	    var checkboxids = document.getElementsByName("checkboxid");
 	    if(checkboxids!=null&&checkboxids.length>0){
	 	    for(var i=0;i<checkboxids.length;i++){
	 	      if(checkboxids[i].checked){
		 	      couterNum = couterNum + 1 ;
		 	      theForm.id.value = checkboxids[i].value;
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
 	    theForm.action = 'busitree_modDataBase.do' 
		theForm.submit();
	}
	
	function delRequest(){
		var couterNum = 0;
 	    var checkboxids = document.getElementsByName("checkboxid");
 	    if(checkboxids!=null&&checkboxids.length>0){
 	    for(var i=0;i<checkboxids.length;i++){
 	      if(checkboxids[i].checked){
 	      couterNum = couterNum + 1 ;
 	      theForm.id.value = checkboxids[i].value;
 	      }
 	    }
 	    }
 	    if(couterNum==0){
 	    alert("请勾选需要删除功能信息！");
 	    return false ;
 	    }else if(couterNum>1){
 	    alert("一次只能删除单条功能信息");
 	    return false ;
 	    }
 	    theForm.action = 'busitree_delDataBase.do';  
		theForm.submit();
	}
</script>
<style type="text/css">
</style>
</head>
<body>
<s:form action="busitree_listDataBase.do" method="post" id="theForm">
<s:hidden name="theForm.id" id="id"></s:hidden>
		<div class="scrollbody">	
			<div class="utt-2 mgt-20">
				<a class="icon-add" href="javascript:void(0)" onclick="addRequest();return false;">新增</a>
				<a class="icon-modify" href="javascript:void(0)" onclick="modRequest();return false;" >修改</a>
				<a class="icon-del" href="javascript:void(0)" onclick="delRequest();return false;" >删除</a>
			</div>
			<table width="100%" border="0" cellspacing="0" class="blue-table sorttable" id="theTable">
			<thead>
				<tr>
					<th>选择</th>
					<th onclick="sort(theTable,1,'string')">用户名</th>
<%--					<th>命名空间</th>--%>
					<th onclick="sort(theTable,2,'string')">IP地址</th>
					<th onclick="sort(theTable,3,'int')">端口</th>
					<th onclick="sort(theTable,4,'string')">服务名</th>
				</tr>
			</thead>
			<tbody>
				<s:iterator value="theForm.resultList" id="theBean">
					<tr>
						<td><input name="checkboxid" type="checkbox"
						value="<s:property value='#theBean.id'/>" /></td>
						<td>
							<s:property value="#theBean.usrname"/>
						</td>
<%--						<td>
							<s:property value="#theBean.tabale_space"/>
						</td>--%>
						<td>
							<s:property value="#theBean.ipaddr"/>
						</td>
						<td>
							<s:property value="#theBean.port"/>
						</td>
						<td>
							<s:property value="#theBean.service_name"/>
						</td>
					</tr>
				</s:iterator>				
			</tbody>
			</table>
			<div class="pages mgb-10">
						<jsp:include page="../../sxcloud/inc/Pagination.jsp?formId=theForm" />
					</div>
	</div>
</s:form>
</body>
