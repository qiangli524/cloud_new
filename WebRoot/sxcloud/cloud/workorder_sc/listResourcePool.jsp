<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../../sxcloud/common/taglib.jsp"%>
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />
<link
	href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css"
	rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
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

	var api = frameElement.api;
	var w = api.opener;

	api.button({
		id : 'OkAnd',
		name : '确定',
		callback : submitRequest,
		focus : true
	}, {
		id : 'cancle',
		name : '取消'
	});
	function submitRequest() {
		if (confirm("申请资源确定划入该业务池?") == true) {
			var nodes = zTree.getCheckedNodes(true);
			if(nodes.length==0){
			alert("请选择申请资源划入的业务池集群");
			return false;
			}
			if(nodes.length>1){
			alert("只能选择一个业务池集群");
			return false;
			}
			var id = nodes[0].id;
			var name = nodes[0].name;
			api.get("addResource").choosePool(id,name);
		} else {
			return false;
		}
	}
	var setting = {
		view : {
			dblClickExpand : false,
			selectedMulti : false
		},
		callback : {
			onClick : onClick,
			beforeExpand : zTreeBeforeExpand,
			onAsyncSuccess : onAsyncSuccess
		},
		data : {
			simpleData : {
				enable : true,
				idKey : "id",
				pIdKey : "PId",
				rootPId : "-1",
			}
		},
		check : {
			enable : true
		},
		async : {
			enable : false,
			//url : "busisys_asyncForLimitTree.do",
			autoParam : [ "id", "name=n", "level=lv" ],
			dataFilter : filter
		}
	};
	function onAsyncSuccess(event, treeId, treeNode, msg) {
		if (treeNode) {
		}
	}
	function filter(treeId, parentNode, childNodes) {
		if (childNodes[0].error != undefined) {
			alert(childNodes[0].error);
			return;
		}
		if (!childNodes)
			return null;
		for ( var i = 0, l = childNodes.length; i < l; i++) {
			childNodes[i].name = childNodes[i].name.replace(/\.n/g, '.');
		}
		return childNodes;
	}
	function onClick(event, treeId, treeNode, clickFlag) {
	}
	var zTree;
	$(document).ready(function() {
		var nodes = <s:property value="#nodes" escapeHtml="false"/>;
		$.fn.zTree.init($("#treeDemo"), setting, nodes);
		zTree = $.fn.zTree.getZTreeObj("treeDemo");
	});

	//异步加载数据，当树节点展开时异步加载数据
	function zTreeBeforeExpand(treeId, treeNode) {
		var nodes = treeNode;
		if (nodes) {
			zTree.reAsyncChildNodes(nodes, "refresh", true);
		}
	}
	
	
</script>
</head>
<body>
	<s:form action="busitree_listBusiTree" method="post" id="theForm">
		<div class="content_wrap">
			<div class="center">
				<ul id="treeDemo" class="ztree"></ul>
			</div>
			<%--
			<div class="right">
				 	<iframe id="right_iframe" src="resourcestatistics_showFirstPage.do" name="right_iframe" height="100%" width="76%" frameborder="0" style="position:fixed;left:259px;right:0px;top:0px;bottom:0px;overflow-x:auto;border:none;background：#fff;"></iframe>
			
			</div>
			--%>
		</div>
	</s:form>
</body>
