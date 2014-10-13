<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../../sxcloud/common/taglib.jsp"%>
<%@ include file="../../../sxcloud/common/view.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
  <head>
    <title></title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
	<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript">
function delDatastore(name,host_id,connect_id){
	var f = document.forms[0];
	var url ="unitedTab_deleHostStorage.do?dataStoreName="+name+"&uuid="+host_id+"&connect_id="+connect_id;
	if(confirm("确认要删除存储吗")){
		$.ajax({
			type:"GET",
	        url:url,
			data:"text",
	        async: true,
	        cache: false,
		    dataType:"json",
		    success: function(data){
		    	if(data!=-1){
		    		//更新虚拟机状态
		    		window.location.reload();
		    	}
	        }
		});
		}
}

</script>
  </head>
  
  <body class="pop-body scrollbody">
  <form name="frm">
  	<div class="table-ct">
			<table width="100%" class="blue-table sorttable" border="0" cellspacing="0" >
				<thead>
					 	<tr>
							<th width="20%" align="center">名称</th>
							<th width="20%" align="center">容量(GB)</th>
							<th width="20%" align="center">空闲(GB)</th>
							<th width="20%" align="center">类型</th>
							<th width="20%" align="center">操作</th>
						</tr>
			  	</thead>
				<tbody>
				<s:iterator id="dataStore" value="dataStoreList">
					<tr>
						<td align="center"><s:property value="#dataStore.NAME"/></td>
		  				<td align="center"><s:property value="#dataStore.CAPACITY"/></td>
		  				<td align="center"><s:property value="#dataStore.FREE_SPACE"/></td>
		  				<td align="center"><s:property value="#dataStore.TYPE"/></td>
		  				<td align="center">
		  					<a href="javascript:delDatastore('<s:property value="#dataStore.NAME" />','<s:property value="#dataStore.HOST_ID" />','<s:property value="#dataStore.connectId" />')" name="detail" >卸载</a>
		  				</td>
		  			</tr>
				</s:iterator>
				</tbody>
			</table>
		</div>
	</form>	
  </body>
</html>
