<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/sxcloud/common/taglib.jsp"%>
<%@ include file="/sxcloud/common/view.jsp"%>
<html>
	<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
	<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
	<title></title>
<script type="text/javascript">

$(function(){
	 var api = frameElement.api;
	 var w = api.opener;
			api.button(
			 {
			     id:'cancle',
			     callback:saveProcedure,
			     name: '取消'
			 });
	//获取父页面的结果
	var result = w.document.getElementById("resultMessage").value;
	$("#runResult").val(result);
	//保存操作
	function saveProcedure(){
		w.searchRequest();
	}
});
	
	
	
</script>
</head>
<body >
	<s:textarea cols="72" rows="20" id="runResult"></s:textarea>
</body>
</html>		