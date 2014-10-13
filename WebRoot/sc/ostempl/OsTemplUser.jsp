<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../sxcloud/common/link.jsp"%>
<%@ include file="../../sxcloud/common/taglib.jsp"%>
<html:html locale="true">
<head>
<title></title>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/tableSort/tableSort.js"></script>
<script type="text/javascript">
	$(function(){
		$check = $(":checkbox");
		$check.unbind().live("click",function(){
			$check.not(this).attr("checked",false);
		});
	});	
	
	function resetForm(theForm){
		theForm.user_name.value = '';
		theForm.primary_group.value = '';
	}

	function searchRequest() { 
		theForm.submit();
	}
 	function addRequest() {
 	    theForm.action = 'osTemplComp_addOsTemplateUser.do' 
		theForm.submit();
 	}
 	
	function modRequest() { 
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
			alert("请勾选一条信息");
			return false ;
		}else if(couterNum>1){
			alert("一次只能修改一条信息");
			return false ;
		}
		theForm.action = 'osTemplComp_updateOsTemplateUser.do' 
		theForm.submit();
	}
	
	function delRequest() {
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
		    alert("请勾选一条信息");
		    return false ;
	    }else if(couterNum>1){
		    alert("一次只能删除一条信息");
		    return false ;
	    }
		if(confirm("确定要删除?")){
			theForm.action = 'osTemplComp_deleteOsTemplateUser.do'  
			theForm.submit();
		}
	}
	
	function returnOsTemplate(){
		theForm.action = 'osTemplate_showOsTemplate.do';
		theForm.submit();
	}
</script>
</head>
<body>
<div class="mainbody">
<s:form action="osTemplComp_showOsTemplateUser.do" method="post" cssStyle="theForm" id="theForm">
<s:hidden name="theForm.id" id="id"></s:hidden>
<s:hidden name="theForm.template_id" id="template_id"></s:hidden>
		 <div class="pd-20 bgcolor-1">
			<h2 class="utt-1">用户配置</h2>
			<div class="bord-1 pd-10">			
			<div class="clearfix mgt-10">
				<label class="vm">用户：</label>
				<s:textfield name="theForm.user_name" id="user_name" cssClass="inpt-2" maxlength="30"></s:textfield>
				<label class="mgl-20 vm">主组：</label>
				<s:textfield name="theForm.primary_group" id="primary_group" maxlength="30" cssClass="inpt-2" ></s:textfield>
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
				   <th width="5%">编号</th>
				   <th width="10%" onclick="sort(theTable,0,'string')">ID</th>
				   <th width="15%" onclick="sort(theTable,5,'string')">用户名</th>   
                   <th width="15%" onclick="sort(theTable,1,'string')">口令</th>
                   <th width="15%" onclick="sort(theTable,2,'string')">主组</th>
                   <th width="15%" onclick="sort(theTable,3,'string')">家目录</th>
                   <th width="25%" onclick="sort(theTable,4,'string')">备注</th>
			  </tr>
			  </thead>
			  <tbody>
			  <s:iterator value="theForm.userList" id="theBean">
				<tr>
					<td><input name="checkboxid" type="checkbox" value="<s:property value="#theBean.id"/>"/></td>
					<td><s:property value="#theBean.user_id"/></td>
					<td><s:property value="#theBean.user_name"/></td>
					<td><s:property value="#theBean.pass_word"/></td>
					<td><s:property value="#theBean.primary_group"/></td>
					<td><s:property value="#theBean.home_dir"/></td>
					<td><s:property value="#theBean.user_desc"/></td>
				</tr>
				</s:iterator>		  
			  </tbody>
			</table>
			<%--
			<div class="pages mgb-10"><!-- 分页 -->
				<jsp:include page="/sxcloud/inc/Pagination.jsp?formId=theForm" />
			</div>
			--%>
			<%--
			<div class="btnCenter">
				<span class="ubtn-blue mgl-20"><input type="button"  value="返回" onclick="returnOsTemplate()"/></span>
			</div>
			--%>
		</div>
</s:form>
</div>
</body>
</html:html>
