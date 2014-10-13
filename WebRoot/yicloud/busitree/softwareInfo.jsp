<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" %>
<%@ include file="../../sxcloud/common/taglib.jsp"%>
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="cache-control" content="no-cache"/>
<meta http-equiv="expires" content="0"/>


<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />


<head>
<title></title>
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/tableSort/tableSort.js"></script>
<script type="text/javascript">
	var id = '<%=request.getAttribute("id")%>';
	function uploadSoftware(){
		$.dialog({
			title:'软件包上传',
			width:'700px',
			height:'400px',
			id:'uploadSoftware',
			content:'url:software_uploadSoftUnBusi.do?id='+id,
		});
	}
	
	function catchSoftware(){
		$.dialog({
			title:'软件包捕获',
			width:'530px',
			height:'320px',
			id:'catchSoftware',
			content:'url:software_catchSoftware.do?id='+id
		});
	}
</script>
</head>
<body>
<s:form action="" method="post" id="theForm">
<div class="scrollbody">
	<div>
		<input type = "button" class="thickbox btn-style02" value = "上传" onclick = "javascript:uploadSoftware()" />
		<input type = "button" class="thickbox btn-style02" value = "捕获" onclick = "javascript:catchSoftware()" />
    </div>
    
	<div class="blue-wrap noborder">
		<div class="table-ct">
			<table width="100%" class="blue-table sorttable" border="0" cellspacing="0" id="theTable">
			  <thead>
			  <tr>
				   <th onclick="sort(theTable,0,'string')">软件名称</th>                
                   <th onclick="sort(theTable,1,'string')">软件大小</th>
                   <th onclick="sort(theTable,2,'string')">软件提供者</th>
                   <th onclick="sort(theTable,3,'string')">软件制作厂家</th>
                   <th onclick="sort(theTable,4,'date')">上传时间</th>
                   
			  </tr>
			  </thead>
			  <tbody>
			  	<s:iterator value="theForm.resultList" id="theBean">
			  		<tr>
			  			<td><s:property value="#theBean.NAME"/></td>
			  			<td><s:property value="#theBean.SOFTWARE_SIZE"/>K</td>
			  			<td><s:property value="#theBean.PROVIDERS"/></td>
			  			<td><s:property value="#theBean.MANUFACTURERS"/></td>
			  			<td><s:property value="#theBean.UPDATETIME"/></td>
			  		</tr>
			  	</s:iterator>
			  </tbody>
			</table>
		</div>
</div>
</s:form>
</body>
