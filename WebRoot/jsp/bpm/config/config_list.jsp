<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java"  contentType="text/html; charset=UTF-8"%>
<%@ include file="/sxcloud/common/taglib.jsp" %>
<%@ include file="/sxcloud/common/link.jsp"%>
<%@ include file="/sxcloud/common/view.jsp"%>
<head>
<title>工单配置</title>
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/js/checkbox.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/tableSort/tableSort.js"></script>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String excel_export_jsp = path + "/excel/excel_export.jsp";
%>
<script type="text/javascript" src="<%=request.getContextPath() %>/jsp/js/workflow.constant.js"></script>
	<style type="text/css">
	</style>
  </head>
  <script type="text/javascript">
		function searchRequest(){
			$('#theForm').action = "<%=path%>/bpm/config_list.do";
			$('#theForm').submit();
		}
		
		function del(id){
			$.ajax({
        		type : 'post',
        		async : false,
        		url : '<%=request.getContextPath()%>/bpm/config_delete.do',
       			data:{"id":id},
       			dataType: 'json',
       			success : function(data){
       				$('#config_id_'+id).remove();
       			},
       			error : function(data,textStatus){
       				console.log('error:' + data);
       			}
       		});			
		}
		$(document).ready(function(){
			$('#resourceName').change(function(){
				searchRequest();
			});
		});
  </script>
  <body>
  <div class="mainbody">
<s:form action="bpm/config_list.do" method="post" cssStyle="theForm" id="theForm">
  <s:hidden name="theForm.ID" id="ID"></s:hidden>
	 <div class="pd-20 bgcolor-1">
			<h2 class="utt-1">流程配置</h2>
			<div class="bord-1 pd-10">			
			<div class="clearfix mgt-10">
				<label class="vm">流程名称：</label>
				<select id="resourceName" class="select-1" name="config.resourceName">
					 <s:iterator value="pmbs" id="pmb">
						<option value="${pmb.ppName }" <s:if test="config.resourceName.equals(#pmb.ppName)">selected</s:if> >${pmb.ppName }</option>
					 </s:iterator>
				</select>
			</div>
			<div class="utt-2 mgt-20">
				<a class="icon-add" href="javascript:void(0)" onclick="window.location.href='<%=path%>/bpm/config_toAdd.do?config.resourceName=${config.resourceName}'" >新增</a>
			</div>

			<table id="theTable" width="100%" class="blue-table sorttable" border="0" cellspacing="0">
			  <thead>
			  <tr>
                   <th onclick="sort(theTable,1,'string')">流程名称</th>
                   <th onclick="sort(theTable,2,'string')">流程节点</th>
                   <th onclick="sort(theTable,3,'string')">执行人</th>
                   <th onclick="sort(theTable,4,'string')">执行组</th>
                   <th onclick="sort(theTable,5,'string')">派发方式</th>
                   <th onclick="sort(theTable,6,'string')">操作</th>
			  </tr>
			  </thead>
			  <tbody>
			   <s:iterator value="list" id="config">
                   <tr id="config_id_<s:property value="#config.id" />">
                   	<td ><s:property value="#config.resourceName" /></td>
                   	<td ><s:property value="#config.stepName" />(<s:property value="#config.stepId" />)</td>
                   	<td ><s:property value="#config.userName" /></td>
                   	<td ><s:property value="#config.groupName" /></td>
                   	<td >
                   		<s:if test="#config.groupName!=null">	        
                   			<s:if test="#config.callType==0">	                                					       
                   				智能
                   			</s:if>        
                   			<s:if test="#config.callType==1">	                                					       
                   				共享
                   			</s:if>           	
                   		</s:if>	
                   		<s:else>
                   			--
                   		</s:else>                                	
                   	</td>
                   	<td><a href="javascript:void(0)" onclick="del('<s:property value="#config.id" />')">删除</a></td>
                   </tr>
             </s:iterator>
			  </tbody>
			</table>
		<div class="pages">
		<jsp:include page="/jsp/inc/Pagination.jsp?formId=theForm" />
			</div>	
		 </div>
</s:form>
</div>
</html>
