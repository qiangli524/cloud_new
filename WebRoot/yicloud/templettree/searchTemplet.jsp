<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../sxcloud/common/taglib.jsp"%>
<%@ include file="../../sxcloud/common/view.jsp"%>
<head>
<title></title>
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/thickbox.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/ui.datepicker.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/portalet.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/js/jqueryui/jquery-1.7.2.js"></script>
<script type="text/javascript">
	
	function searchNode() {
			//var value = document.getElementById("key").value;
			var value = $.trim(key.get(0).value);
			window.parent.searchNode(value);
		}
		function updateNodes(highlight) {
			var zTree = $.fn.zTree.getZTreeObj("treeDemo");
			for( var i=0, l=nodeList.length; i<l; i++) {
				nodeList[i].highlight = highlight;
				zTree.updateNode(nodeList[i]);
			}
		}
		
		$(document).ready(function(){
			key = $("#key");
			key.bind("propertychange", searchNode)
			.bind("input", searchNode);
		});
</script>
</head>
<body class="pop-body scrollbody">

<s:form action="" id="theForm" method="post"
	cssClass="theForm">
	<div class="right">
		<ul class="list">
			<li><p>搜索试试看：<br/>
					模板名称：<input type="text" id="key" value=""/><br/>
				</p>
			</li>
		</ul>
	</div>
	</s:form>
</body>