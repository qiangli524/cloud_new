<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<head>
	<title></title>
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
<script src="<%=request.getContextPath() %>/yicloud/xen/js/common.js"></script>
	<script type="text/javascript">
	var ID =<%=request.getAttribute("ID")%>;
	var entity_id ='<%=request.getAttribute("entity_id")%>';
	var pool_uuid = '<%=request.getAttribute("pool_uuid")%>';
	var host_uuid = '<%=request.getAttribute("host_uuid")%>';
	var flag = '<%=request.getAttribute("flag")%>';
	var msg = '<%=request.getAttribute("msg")%>';

	 var api = frameElement.api;
	 var w = api.opener;
	 if(flag=='false'){
			 api.button({
			     id:'cancle1',
			     name: '取消'
			 });
		}else{
			 api.button({
			     id:'Ok1',
			     name: '确定删除',
			     callback:ok,
			     focus: true
			 },
			 {
			     id:'cancle1',
			     name: '取消'
			 });
		}

	 function ok(){
		 mask('正在删除主机...','1','0px');
		 w.delHost(ID,entity_id,pool_uuid,host_uuid);
	     return false;
	}
	 $(function(){
        if(flag=='false'){
            $("#text").empty().append(msg).css({"color":"red"});
        }
	    });
</script>
</head>
<body class="pop-body scrollbody">
	<s:form action="" method="post" id="theForm">
		<table width="100%" border="0" cellspacing="0"
			class="pop-table nosize">
			<tr>
				<td align="left" colspan="2">
					<span id="text">删除主机后，此主机上的虚拟机有可能无法在使用，确定要删除吗？</span>
				</td>
			</tr>
		</table>
	</s:form>
</body>
