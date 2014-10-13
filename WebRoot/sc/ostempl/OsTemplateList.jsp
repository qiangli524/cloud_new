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
		theForm.templ_name.value = '';
		theForm.os_version.value = '';
	}

	function searchRequest() { 
		theForm.submit();
	}
 	function addRequest() {
 	    theForm.action = 'osTemplate_addOsTemplate.do' 
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
		theForm.action = 'osTemplate_updateOsTemplate.do' 
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
			theForm.action = 'osTemplate_deleteOsTemplate.do'  
			theForm.submit();
		}
	}
	function showOsTemplateDetail(id){
		theForm.action = 'osTemplate_showOsTemplateDetail.do?theForm.id=' + id;
		theForm.submit();
	}
	function filesystem_href_handler(templ_id){
		theForm.action = 'osTemplComp_showOsTemplateFileSystem.do?theForm.template_id=' + templ_id;  
		theForm.submit();
	}
	function group_href_handler(templ_id){
		theForm.action = 'osTemplComp_showOsTemplateGroup.do?theForm.template_id=' + templ_id;  
		theForm.submit();
	}
	function user_href_handler(templ_id){
		theForm.action = 'osTemplComp_showOsTemplateUser.do?theForm.template_id=' + templ_id;   
		theForm.submit();
	}
	function groupuser_href_handler(templ_id){
		theForm.action = 'osTemplComp_showOsTemplateGroupUser.do?theForm.template_id=' + templ_id;  
		theForm.submit();
	}
	function comp_href_handler(id){
		theForm.action = 'osTemplate_configOsTemplate.do?theForm.id=' + id;
		theForm.submit();
	}	
</script>
</head>
<body>
<div class="mainbody">
<s:form action="osTemplate_showOsTemplate.do" method="post" cssStyle="theForm" id="theForm">
<s:hidden name="theForm.id" id="id"></s:hidden>
		 <div class="pd-20 bgcolor-1">
			<h2 class="utt-1">安装模版管理</h2>
			<div class="bord-1 pd-10">			
			<div class="clearfix mgt-10">
				<label class="vm">模版名称：</label>
				<s:textfield name="theForm.templ_name" id="templ_name" cssClass="inpt-1 vm" maxlength="30"></s:textfield>
				<label class="mgl-20 vm">系统镜像：</label>
				<s:select name="theForm.os_version" id="os_version" cssClass="select-1" headerKey="" headerValue="---请选择---"
								listValue="dictname " listKey="dictcode " list="theForm.sdList" />
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
				   <th>编号</th>
				   <th onclick="sort(theTable,0,'string')">模版名称</th>   
                   <th onclick="sort(theTable,1,'string')">系统镜像</th>
                   <th onclick="sort(theTable,2,'string')">备注</th>
                   <th onclick="sort(theTable,3,'string')">操作</th>
			  </tr>
			  </thead>
			  <tbody>
			  <s:iterator value="theForm.templList" id="theBean">
				<tr>
					<td><input name="checkboxid" type="checkbox" value="<s:property value="#theBean.id"/>"/></td>
					<td>
						<a name="otplDetail" id="otplDetail" href="javascript:void(0)" value="#theBean.id" 
							onclick="showOsTemplateDetail(<s:property value="#theBean.id"/>)"><s:property value="#theBean.templ_name"/></a>
					</td>
					<td><s:property value="#theBean.os_version"/></td>
					<td><s:property value="#theBean.templ_desc"/></td>
					<td>	
						<%--			
						<a href='javascript:void(0);' onclick='filesystem_href_handler(<s:property value="#theBean.id"/>)' name='filesystem_href' style="text-decoration:underline">文件系统</a>
						<a href='javascript:void(0);' onclick="group_href_handler(<s:property value="#theBean.id"/>)" name='group_href' style="text-decoration:underline">用户组</a>
						<a href='javascript:void(0);' onclick="user_href_handler(<s:property value="#theBean.id"/>)" name='user_href' style="text-decoration:underline">用户</a>
						<a href='javascript:void(0);' onclick="groupuser_href_handler(<s:property value="#theBean.id"/>)" name='groupuser_href' style="text-decoration:underline">附加组</a>					
						--%>
						<a href='javascript:void(0);return false;' onclick="comp_href_handler(<s:property value="#theBean.id"/>)" name='comp_href' style="text-decoration:underline">配置</a>
					</td>
				</tr>
				</s:iterator>		  
			  </tbody>
			</table>
			<div class="pages mgb-10"><!-- 分页 -->
				<%-- 
				<jsp:include page="/sxcloud/inc/Pagination.jsp?formId=theForm" />--%>
			</div>
		</div>
</s:form>
</div>
</body>
</html:html>
