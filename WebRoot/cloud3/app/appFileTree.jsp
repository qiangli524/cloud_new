<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../sxcloud/common/taglib.jsp"%>
<script type="text/javascript"
	src="../../sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript"
	src="../../yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
<%@ page
	import="com.sitech.basd.sxcloud.rsmu.domain.system.TbSysUserinfoObj"%>
<head>
<title></title>
<link rel="stylesheet" href="../../sxcloud/cresources/ztree/zTree.css"
	type="text/css" />
<link rel="stylesheet"
	href="../../sxcloud/cresources/ztree/zTreeStyle/zTreeStyle.css"
	type="text/css" />
<script type="text/javascript"
	src="../../sxcloud/cjs/ztree/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript"
	src="../../yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
<script type="text/javascript"
	src="../../sxcloud/cjs/ztree/jquery.ztree.excheck-3.5.js"></script>
<script type="text/javascript"
	src="../../sxcloud/cjs/ztree/jquery.ztree.exedit-3.5.js"></script>
<script type="text/javascript">
	var appId = '<%=request.getAttribute("appId")%>';
	var setting = {
		check : {
			enable : true
		},
		data : {
			simpleData : {
				enable : true
			}
		},
		async: {
			enable: true,
			url:"appFileTree_asyncFileTree.do?appId=app1",
			autoParam:["id","fileName","filePath","parentId","appId"],
			dataFilter: filter
		}
	};
	function filter(treeId, parentNode, childNodes) {
		if (!childNodes) return null;
		for (var i=0, l=childNodes.length; i<l; i++) {
			childNodes[i].name = childNodes[i].name.replace(/\.n/g, '.');
		}
		return childNodes;
	}

	$(document).ready(function() {
		$.fn.zTree.init($("#appFileTree"), setting);
	});
</script>
</head>
<body>
	<div class="content_wrap">
		<div class="left">
			<ul id="appFileTree" class="ztree"></ul>
		</div>
	</div>
</body>
