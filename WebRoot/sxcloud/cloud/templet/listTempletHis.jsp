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
		theForm.TEM_ID.value = '';
		theForm.TEM_NAME.value = '';
		theForm.TYPE.value='0';
	}
	
   function searchRequest() { 
		theForm.submit();
 	}

</script>
</head>
<body>
<s:form action="templet_listTempletHis.do" method="post" id="theForm" cssClass="theForm">
<div class="scrollbody">
	<div class="query">
		<div class="title"><%=getImageTag(request,"query-icon.gif")%></div>
	</div>
	<div class="box on">
        <div class="query-form">
                <table width="100%" class="querytable" border="0">
                  <tr>
                    <td class="til">服务编号:</td>
                    <td>
						<s:textfield name="theForm.TEM_ID" id="TEM_ID" cssClass="txt"></s:textfield>
                    </td>
                    <td class="til">服务名称:</td>
                    <td>
						<s:textfield name="theForm.TEM_NAME" id="TEM_NAME" cssClass="txt"></s:textfield>
                    </td>
                    <td class="til">服务类型:</td>
                    <td>
						<s:select list="theForm.typeList" listKey="TYPE" listValue="TYPE_NAME" id="TYPE" headerKey="0" headerValue="-请选择-" name="theForm.TYPE"></s:select>
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
			<jsp:include page="/sxcloud/inc/Pagination.jsp?formId=theForm" />
		</div>
		<div class="table-ct">
			<table id="theTable" width="100%" class="blue-table sorttable" border="0" cellspacing="0">
			  <thead>
			  <tr>
				   <th onclick="sort(theTable,0,'string')">服务编号</th>
				   <th onclick="sort(theTable,1,'string')">服务名称</th>
				   <th onclick="sort(theTable,2,'string')">服务类型</th>
				   <th onclick="sort(theTable,3,'string')">服务状态</th>
				   <th onclick="sort(theTable,4,'string')">服务描述</th>
				   <th onclick="sort(theTable,5,'string')">操作类别</th>
				   <th onclick="sort(theTable,6,'string')">操作信息</th>
				   <th onclick="sort(theTable,7,'date')">操作时间</th>
             </tr>
			  </thead>
			  <tbody>
			  	<s:iterator value="theForm.resultList" id="theBean">
			  		<tr>
			  			<td> <s:property value="#theBean.TEM_ID"/> </td>
			  			<td> <s:property value="#theBean.TEM_NAME"/> </td>
			  			<td> <s:property value="#theBean.TYPE_NAME"/> </td>
			  			<td> 
			  				<s:if test="#theBean.RELEASE_FLAG==0">未发布</s:if>
			  				<s:elseif test="#theBean.RELEASE_FLAG==1">已发布</s:elseif>
			  				<s:else>废弃</s:else>
			  				<input name="release_flag" type="hidden" value="<s:property value="#theBean.RELEASE_FLAG"/>"/>	
			  			 </td>
			  			<td> <s:property value="#theBean.TEM_DESC"/> </td>
			  			<td>
			  				<s:if test="#theBean.OPERTYPE==0">增加模板</s:if>
			  				<s:elseif test="#theBean.OPERTYPE==1">修改模板</s:elseif>
			  				<s:elseif test="#theBean.OPERTYPE==2">删除模板</s:elseif>
			  				<s:elseif test="#theBean.OPERTYPE==3">发布模板</s:elseif>
			  				<s:elseif test="#theBean.OPERTYPE==4">废弃模板</s:elseif>
			  				<s:else>取消发布模板</s:else>
			  			</td>
			  			<td> <s:property value="#theBean.MESSAGE"/> </td>
			  			<td> <s:property value="#theBean.OPERTIME"/> </td>
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
