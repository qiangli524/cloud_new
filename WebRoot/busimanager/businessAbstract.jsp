<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="../../sxcloud/common/taglib.jsp" %>
<%@ include file="../../sxcloud/common/view.jsp" %>
<%@ include file="../../sxcloud/common/link.jsp"%>
<head>
	<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
	
<script type="text/javascript">
   $(function(){
	   
   });
</script>
</head>
<body>
<s:form action="" method="post" id="theForm">
<s:hidden name="operType" id="operType"></s:hidden>
		<div class="table-ct"> <table width="100%" border="0" cellspacing="0"  class="blue-table sorttable">
			<tr>
				<td class="til" width="20%" align="left">
					名称
				</td>
				<td  align="left">
					<s:property value="treeObj.name" />
				</td>
			</tr>
		    <tr name="resource_info">
				<td class="til" width="20%"  align="left">
					资源个数
				</td>
				<td  align="left">
					<s:property value="resourceCount"/>
				</td>
			</tr>
			<tr>
				<td class="til" width="20%"  align="left">
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
