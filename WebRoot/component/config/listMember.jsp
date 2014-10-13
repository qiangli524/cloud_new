<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../sxcloud/common/taglib.jsp"%>
<html:html locale="true">
<%@ include file="../../sxcloud/common/link.jsp"%>
<head>
	<title></title>
	<style type="text/css">
		div.hidden{
		width:50px;
		height:30px;
		overflow:hidden;
		white-space:nowrap;
		text-overflow:ellipsis;
		text-overflow: ellipsis;/* IE/Safari */
		-ms-text-overflow: ellipsis;
		-o-text-overflow: ellipsis;/* Opera */
		-moz-binding: url("ellipsis.xml#ellipsis");/*FireFox*/
	}
  </style>
	<script type="text/javascript" src="<%=request.getContextPath()%>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/tableSort/tableSort.js"></script>
	<script type="text/javascript">
</script>
</head>
<body class="pop-body scrollbody">
<s:form action="" method="post" cssStyle="theForm" id="theForm">
			 <div class="table-ct">
            <table id="theTable" width="100%" border="0" cellspacing="0" class="blue-table sorttable">
				<thead>
					<tr>
						<th onclick="sort(theTable,0,'string')">配置文件名称</th>
						<th onclick="sort(theTable,1,'string')">主机/用户</th>
						<!-- 
						<th>用户名</th>
						 -->
						
						<th onclick="sort(theTable,2,'string')">类型</th>
						<th onclick="sort(theTable,3,'string')">类别</th>
						<th onclick="sort(theTable,4,'string')" width="10%">路径</th>
						<th onclick="sort(theTable,5,'string')">描述</th>
					</tr>
				</thead>
                <tbody>
                <s:iterator value="theForm.selectedList" id="selectedList">
                		<tr>
                			<!-- 
                			<td><input type="checkbox" value="<s:property value="#selectedList.id"/>" name="theForm.id" id="id" checked="checked"/></td>
                			 -->
                			<td><a name="detail" href="#" value='<s:property value="#selectedList.id"/>'><s:property value="#selectedList.name"/> </a></td>
                			<td><s:property value="#selectedList.ip"/>/<s:property value="#selectedList.username"/></td>
                			<!--  
                			<td><s:property value="#theBean.username"/></td>
                			-->
                			
                			<td><s:if test="#selectedList.type==0">xml</s:if>
                				<s:elseif test="#selectedList.type==1">properties</s:elseif>
                				<s:else>其他</s:else>
                			</td>
                			<td>
								<s:if test="#selectedList.category==0">通用</s:if>
								<s:elseif test="#selectedList.category==1">部署使用</s:elseif>
								<s:else>其他</s:else>
                			</td> 
                			<td align="center"><div class="hidden" title='<s:property value="#selectedList.path"/>'>
                				<s:property value="#selectedList.path"/></div></td>
                			<td><s:property value="#selectedList.description"/></td>
                		</tr>
                	</s:iterator>
                </tbody>
			</table>
			</div>
	</s:form>
</body>

</html:html>
