<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../sxcloud/common/taglib.jsp"%>
<%@ include file="../../sxcloud/common/link.jsp"%>
<%@ include file="../../sxcloud/common/view.jsp"%>
<html:html locale="true">
<head>
	<title></title>
	<link rel="stylesheet" href="sxcloud/cresources/ztree/zTree.css"
		type="text/css" />
	<link rel="stylesheet"
		href="sxcloud/cresources/ztree/zTreeStyle/zTreeStyle.css"
		type="text/css" />
	<script type="text/javascript"
		src="sxcloud/cjs/ztree/jquery.ztree.core-3.1.js"></script>
	<script type="text/javascript"
		src="sxcloud/cjs/ztree/jquery.ztree.excheck-3.1.js"></script>
	<script type="text/javascript"
		src="sxcloud/cjs/ztree/jquery.ztree.exedit-3.1.js"></script>
	<style type="text/css">
div#rMenu {
	position: absolute;
	visibility: hidden;
	background-color: #555;
	text-align: left;
	padding: 2px;
}

div#rMenu ul li {
	margin: 1px 0;
	padding: 0 5px;
	cursor: pointer;
	list-style: none outside none;
	background-color: #DFDFDF;
}
</style>
</head>
<body>
	<s:form action="yvm_listVMService" method="post" cssStyle="theForm" id="theForm">
		<script type="text/javascript">
		var hostId = <%=request.getAttribute("hostId")%>;
		var setting = {
			view: {
				dblClickExpand: false
			},
			callback: {
				onRightClick: OnRightClick,
				onClick: onClick
			},
			data: {
				simpleData: {
					enable: true
				}				
			}	
		};
		
		
		var zNodes =[
			<%=request.getAttribute("imgTreeStr")%>
  	 	];
  	 	
  	 	function onClick(event, treeId, treeNode, clickFlag) {
  	 		lookupImage('0');
		}

		function OnRightClick(event, treeId, treeNode) {
			if ((!treeNode && event.target.tagName.toLowerCase() != "button" && $(event.target).parents("a").length == 0) || (treeNode.id == 0120)) {
				zTree.cancelSelectedNode();
				showRMenu("root", event.clientX, event.clientY);
			} else if ((treeNode && !treeNode.noR && (treeNode.children == null) && (treeNode.getParentNode() == null)) || (treeNode && !treeNode.noR && treeNode.getParentNode().id == 0120)) {
				zTree.selectNode(treeNode);
				showRMenu("parent", event.clientX, event.clientY);
			} else if (treeNode && !treeNode.noR && !treeNode.isParent && (treeNode.getParentNode().id != 0120)) {
				zTree.selectNode(treeNode);
				showRMenu("node", event.clientX, event.clientY);
			}
		}


		function showRMenu(type, x, y) {
			$("#rMenu ul").show();
			if (type=="root") {
				$("#image_type_add").show();
				$("#image_view").hide();
				$("#image_deploy").hide();
				$("#image_edit").hide();
				$("#image_del").hide();
				$("#image_add").hide();
			} else if (type=="node") {
				$("#image_type_add").hide();
				$("#image_view").show();
				$("#image_deploy").show();
				$("#image_edit").show();
				$("#image_del").show();
				$("#image_add").hide();
			} else if (type=="parent") {
				$("#image_type_add").hide();
				$("#image_view").hide();
				$("#image_deploy").hide();
				$("#image_edit").hide();
				$("#image_del").hide();
				$("#image_add").show();
			}
			rMenu.css({"top":y+"px", "left":x+"px", "visibility":"visible"});

			$("body").bind("mousedown", onBodyMouseDown);
		}
		function hideRMenu() {
			if (rMenu) rMenu.css({"visibility": "hidden"});
			$("body").unbind("mousedown", onBodyMouseDown);
		}
		function onBodyMouseDown(event){
			if (!(event.target.id == "rMenu" || $(event.target).parents("#rMenu").length>0)) {
				rMenu.css({"visibility" : "hidden"});
			}
		}
		var zTree, rMenu;
		$(document).ready(function(){
			$.fn.zTree.init($("#treeDemo"), setting, zNodes);
			zTree = $.fn.zTree.getZTreeObj("treeDemo");
			rMenu = $("#rMenu");
		});
	//查看或者部署虚拟机
	function lookupImage(oper){
		var nodes = zTree.getSelectedNodes();
		if (nodes && nodes.length>0) {
			var treeNode = nodes[0];
			if(treeNode && !treeNode.noR && !treeNode.isParent && (treeNode.getParentNode().id != 0120)){
				$("#image_lookup_iframe").attr("src","yvm_lookupImage.do?IM_ID="+treeNode.id+"&hostId="+hostId+"&oper="+oper);
				$("#report_virtual").hide();
				$("#image_lookup").show();
				$("#host_add_page").hide();
			}
		}
		hideRMenu();
 	}
 	var flag = 0;
 	function test(){
		$(".left").toggle('slide',{direction: 'left'},100);
		if(flag==0){
			$("#img").attr("src","sxcloud/cresources/ztree/zTreeStyle/img/diy/icon2.gif");
			flag = 1;
		}else if(flag==1){
			$("#img").attr("src","sxcloud/cresources/ztree/zTreeStyle/img/diy/icon.gif");
			flag =0;
		}
	}
	</script>
		<div class="content_wrap">
			<div class="left">
				<ul id="treeDemo" class="ztree"></ul>
			</div>
			<div style="float:left; margin-top:200px"><img id="img" src="sxcloud/cresources/ztree/zTreeStyle/img/diy/icon.gif" onclick="test()"/></div>
			<div class="right">
			<div id="image_lookup">
					<iframe id="image_lookup_iframe" src="" name = "xxxx" width="840" height="800" frameborder="0"></iframe>
				</div>
			</div>
		</div>
		<div id="rMenu">
			<ul>
				<li id="image_view" onclick="lookupImage('0');">
					查看镜像
				</li>
				<li id="image_deploy" onclick="lookupImage('1');">
					部署镜像
				</li>
				<li id="image_edit" onclick="openNewWindows()">
					修改镜像
				</li>
				<li id="image_del" onclick="checkTreeNode(false);">
					删除镜像
				</li>
				<li id="image_add" onclick="checkTreeNode(false);">
					添加镜像
				</li>
				<li id="image_type_add" onclick="checkTreeNode(false);">
					添加镜像类型
				</li>
			</ul>
		</div>
	</s:form>
</body>
</html:html>
