<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ include file="../common/taglib.jsp" %>
<%@ include file="../common/view.jsp"%>
<html:html locale="true">
<head>
<title></title>
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/tableSort/tableSort.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/js/checkbox.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/ui/ui.js"></script>
<script type="text/javascript">

	function resetForm(theForm){
		theForm.FUNCID.value='';
		theForm.FUNNAME.value = '';
		theForm.STATUS.value = '';
	}

   function searchRequest() { 
		theForm.submit();
 	}
 	function addRequest() {
 		theForm.action = 'function_addFunctions.do';
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
 	    alert("请勾选一条信息");
 	    return false ;
 	    }else if(couterNum>1){
 	    alert('只能选择一项进行修改');
 	    return false ;
 	    }
 	    theForm.action ='function_modFunctions.do';
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
 	   alert("请勾选一条信息");
 	    return false ;
 	    }else if(couterNum>1){
 	    alert('只能选择一项进行删除');
 	    return false ;
 	    }
 	   if(confirm('确认要删除该功能吗？')) { 
 		  theForm.action = 'function_delFunctions.do'  
 		  theForm.submit();
		}
 	}
</script>
</head>
<body>
<div class="mainbody">
<s:form action="function_listFunctions" method="post" cssStyle="theForm" id="theForm">
<s:hidden name="theForm.ID" id="ID"/>
	 <div class="pd-20 bgcolor-1">
			<h2 class="utt-1">功能管理</h2>
			<div class="bord-1 pd-10">			
			<div class="clearfix mgt-10">
				<label class="vm">功能名称：</label>
				<s:textfield name="theForm.FUNNAME" cssClass="inpt-1 vm" id="FUNNAME" maxlength="30"></s:textfield>
				<label class="vm">功能编号：</label>
				<s:textfield name="theForm.FUNCID" cssClass="inpt-1 vm" id="FUNCID" maxlength="30"></s:textfield>
				<label class="mgl-20 vm">状态：</label>
				<s:select cssClass="select-1 vm" list="#{'':'-请选择-','0':'-失效-','1':'-生效-'}" name="theForm.STATUS" id="STATUS">
                </s:select>
				<span class="ubtn-1 mgl-20"><input type="button" onclick="javascript:searchRequest()" value="查询" /></span>
				<span class="ubtn-2 mgl-20"><input type="button" onclick="javascript:resetForm(document.getElementById('theForm'))" value="重置" /></span>
			</div>
			<div class="utt-2 mgt-20">
				<a class="icon-add" href="javascript:void(0)" onclick="addRequest();return false;">新增</a>
				<a class="icon-modify" href="javascript:void(0)" onclick="modRequest();return false;" >修改</a>
				<a class="icon-del" href="javascript:void(0)" onclick="delRequest();return false;" >删除</a>
			</div>

			<table id="theTable" width="100%" class="blue-table sorttable" border="0" cellspacing="0">
			  <thead>
			  <tr>
				   <th>选择</th>
				   <th onclick="sort(theTable,1,'string')">功能编号</th>
				   <th onclick="sort(theTable,2,'string')">功能名称</th> 
                   <th onclick="sort(theTable,3,'string')">功能状态</th>
                   <th onclick="sort(theTable,2,'string')">链接地址</th>
                   <!--  
                   <th>类型:</th>
                   <th>连接地址:</th>
                   <th>重复加载模式:</th>
                   -->
			  </tr>
			  </thead>
			  <tbody>
			  <s:iterator value="theForm.resultList" id="theBean">
			  	<tr>
					<td><input name="checkboxid" type="checkbox" value="<s:text name="#theBean.ID"/>"/></td>
					<td><s:text name="#theBean.FUNCID"/></td>
					<td><s:text name="#theBean.FUNNAME"/></td>
					<td><s:if test="#theBean.STATUS==1">
					生效
					</s:if>
					<s:elseif test="#theBean.STATUS==0">
					失效
					</s:elseif>
					<td><s:text name="#theBean.FUNCREQUEST"/></td>
					</td>
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
