<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../sxcloud/common/taglib.jsp"%>
<%@ include file="../../sxcloud/common/link.jsp"%>
<%@ include file="../../sxcloud/common/view.jsp"%>
<head>
	<title></title>
	<link rel="stylesheet" href="sxcloud/cresources/ztree/zTree.css"
		type="text/css" />
	<link rel="stylesheet"
		href="sxcloud/cresources/ztree/zTreeStyle/zTreeStyle.css"
		type="text/css" />
	<script type="text/javascript"
		src="sxcloud/cjs/ztree/jquery.ztree.core-3.5.js"></script>
	<script type="text/javascript"
		src="sxcloud/cjs/ztree/jquery.ztree.excheck-3.5.js"></script>
	<script type="text/javascript"
		src="sxcloud/cjs/ztree/jquery.ztree.exedit-3.5.js"></script>
	<script type="text/javascript">
	var oper = '<%=request.getParameter("oper")%>';//模板管理使用
		var setting = {
			view: {
				dblClickExpand: false,
				selectedMulti: false
			},
			check: {
				enable: true,
				chkboxType: { "Y": "", "N": "" }
			},
			callback: {
<%--				onAsyncSuccess: onAsyncSuccess--%>
			},
			data: {
				simpleData: {
					enable: true
				}				
			},
			async: {
				enable: true,
				url:"message_vmTree.do",
				autoParam:["id"],
				otherParam: ["userId", "<%=request.getAttribute("USERID")%>","oper",oper],
				dataFilter: filter
			}
		};
		
			function onAsyncSuccess(event, treeId, treeNode, msg) {
				if(treeNode){
					expandNodes(treeNode.children);
				}
		}
		function expandNodes(nodes) {
			if (!nodes) return;
			for (var i=0, l=nodes.length; i<l; i++) {
					zTree.expandNode(nodes[i], true, false, false);
						if (nodes[i].isParent && nodes[i].zAsync) {
						expandNodes(nodes[i].children);
					}
			}
		}
		function filter(treeId, parentNode, childNodes) {
			if (!childNodes) return null;
			for (var i=0, l=childNodes.length; i<l; i++) {
				childNodes[i].name = childNodes[i].name.replace(/\.n/g, '.');
			}
			return childNodes;
		}
		var zTree;
		$(document).ready(function(){
			$.fn.zTree.init($("#treeDemo"), setting);
			zTree = $.fn.zTree.getZTreeObj("treeDemo");
		});
		
	</script>
</head>
<body>
<s:form action="vmauth_vmTree">
<div class="content_wrap">
			<div class="left" align="left">
				<ul id="treeDemo" class="ztree"></ul>
			</div>
</div>
</s:form>
</body>
