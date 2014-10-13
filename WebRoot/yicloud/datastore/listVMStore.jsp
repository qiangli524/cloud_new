<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../sxcloud/common/taglib.jsp"%>
<%@ include file="../../sxcloud/common/view.jsp"%>
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="cache-control" content="no-cache"/>
<meta http-equiv="expires" content="0"/>


<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
<head>
<title></title>
<%
	String id = (String)request.getAttribute("id");
	String type = (String)request.getAttribute("type");
	String name = (String)request.getAttribute("name");
%>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/tableSort/tableSort.js"></script>
<script type="text/javascript">
	function addNFS(){
		var id = '<%=request.getAttribute("id")%>';
		window.open('datastore_addNFSPage.do?id='+id,'addNFS','height=400, width=600');
	}
	
	$(function(){
	var id = '<%=request.getAttribute("id")%>';
		 $("[name='add']").click(function(){
        	currentEdit=$(this);
    		$.dialog({
    			id:'vdi',
    			title:'添加网络存储',
    			width: '700px',
    			height: '480px',
    			max: true,
    		    min: true,
    			content: 'url:datastore_addNFSPage.do?id='+id
    			});
              });
			});
</script>
</head>
<body class="pop-body scrollbody">
<s:form action="datastore_listDataStore.do" method="post" id="theForm"
	cssStyle="theForm">
	
	<div class="blue-wrap noborder">
		<div class="table-ct">
			<table width="100%" border="0" cellspacing="0" class="blue-table sorttable" id="theTable">
				<thead>
					<tr>
						<th  onclick="sort(theTable,0,'string')">名称</th>
						<th  onclick="sort(theTable,1,'string')">容量(GB)</th>
						<th  onclick="sort(theTable,2,'string')">空闲(GB)</th>
						<th  onclick="sort(theTable,3,'string')">类型</th>
					</tr>
				</thead>
				<tbody>
					<s:iterator value="theForm.resultList" id="theBean">
						<tr>
							<td><s:property value = "#theBean.NAME"/></td>
							<td><s:property value = "#theBean.CAPACITY"/></td>
							<td><s:property value = "#theBean.FREE_SPACE"/></td>
							<td><s:property value = "#theBean.TYPE"/></td>
						</tr>
					</s:iterator>
				</tbody>
			</table>
		</div>
	</div>
</s:form>
</body>
