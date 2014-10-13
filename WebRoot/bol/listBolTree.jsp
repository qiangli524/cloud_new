<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> 
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />
<link
	href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common_bol.css"
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
<script type="text/javascript"
	src="sxcloud/js/uuid.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=iblue"></script>
<script type="text/javascript"
	src="bol/js/boltree.js"></script>
<script type="text/javascript">
	var setting = {
		view : {
			dblClickExpand : false,
			selectedMulti : false,
			fontCss: setFontCss
		},
		callback : {
			onClick : onClick,
			beforeExpand : zTreeBeforeExpand
		},
		data : {
			simpleData : {
				enable : true
			}
		},
		check : {
			enable : true
		},
		async : {
			enable : true,
			url : "boltree_loadingTreeAsync.do",
			autoParam : [ "id" ]
		}
	};
	
	function setFontCss(treeId, treeNode) {
		if(treeNode.state == 1){
			return {color:"orange"};
		}if(treeNode.state == 2){
			return {color:"green"};
		}if(treeNode.state == 3){
			return {color:"red"};
		}else {
			return {};
		}
	};
	

	function onClick(event, treeId, treeNode, clickFlag) {
		if (treeNode.type == 0) {//资源视图根节点
			$("#right_iframe").attr(
					"src",
					"boltree_tabs.do?type=" + treeNode.type);
			setDivVisProp('iframe');
		} else if (treeNode.type == 1) {//主机
			$("#right_iframe").attr(
					"src",
					"boltree_tabs.do?type=" + treeNode.type + "&id="
							+ treeNode.id+"&uuid="+treeNode.uuid +"&name=" + treeNode.name);
			setDivVisProp('iframe');
		} 
	}
	
	var zTree;
	$(document).ready(function() {
		$.fn.zTree.init($("#treeDemo"), setting);
		zTree = $.fn.zTree.getZTreeObj("treeDemo");
	});

	//异步加载数据，当树节点展开时异步加载数据
	function zTreeBeforeExpand(treeId, treeNode) {
		var nodes = treeNode;
		if (nodes) {
			zTree.reAsyncChildNodes(nodes, "refresh", true);
		}
	}
	function pageOnLoad() {
		$("#iframe").hide();
	}

	// 设置DIV显示隐藏属性
	function setDivVisProp(divName) {
		pageOnLoad();
		$("#" + divName + "").show();
	}
</script>
</head>
<body>
<s:form action="" method="post" id="theForm">
	<div class="content_wrap">
		<div class="left">
			<ul id="treeDemo" class="ztree"></ul>
		</div>
		<div class="right">
			<div id="iframe">
				<iframe id="right_iframe" src="bolresource_showInfo.do" name="xxxx" height="100%" width="76%" frameborder="0" style="position:fixed;left:259px;right:0px;top:0px;bottom:0px;overflow-x:auto;border:none;background：#fff;"></iframe>
			</div>
		</div>
	</div>
</s:form>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/dateformat/jquery.formDataOper.js"></script>
</body>
