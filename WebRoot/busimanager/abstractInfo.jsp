<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="../../sxcloud/common/taglib.jsp" %>
<%@ include file="../../sxcloud/common/view.jsp" %>
<%@ include file="../../sxcloud/common/link.jsp"%>
<head>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
	
<script type="text/javascript">
   $(function(){
	   var operType=$("#operType").val();
	   if("first_info"==operType){
		   $("[name='first_info']").hide();
	   }else if("second_info"==operType){
		   $("[name='first_info']").hide();
		   $("[name='second_info']").hide();
	   }else if("resource_info"==operType){
		   $("[name='first_info']").hide();
		   $("[name='second_info']").hide();
		   $("[name='resource_info']").hide();
	   }
	   
   });
</script>
</head>
<body>
<s:form action="" method="post" id="theForm">
<s:hidden name="operType" id="operType"></s:hidden>
		<div class="table-ct">
		 <table width="100%" border="0" cellspacing="0" class="blue-table sorttable">
			<tr>
				<td class="til" width="20%" align="right">
					名称
				</td>
				<td  align="left">
					<s:property value="treeObj.name" />
				</td>
			</tr>
			<tr>
				<td class="til" width="20%" align="right">
					简称
				</td>
				<td  align="left">
					<s:property value="treeObj.number" />
				</td>
			</tr>
			<tr>
				<td class="til" width="20%" align="right">
					集成商
				</td>
				<td  align="left">
					<s:property value="treeObj.oem" />
				</td>
			</tr>
			<tr name="second_info">
				<td class="til" width="20%"  align="right">
					子系统个数
				</td>
				<td  align="left">
					<s:property value="secondCount"/>
				</td>
			</tr>
		    <tr name="resource_info">
				<td class="til" width="20%"  align="right">
					资源个数
				</td>
				<td  align="left">
					<s:property value="resourceCount"/>
				</td>
			</tr>
			<tr>
				<td class="til" width="20%"  align="right">
					描述
				</td>
				<td  align="left">
					<s:property value="treeObj.desc"/>
				</td>
			</tr>
			
			</table>
		</div>
</s:form>
</body>
