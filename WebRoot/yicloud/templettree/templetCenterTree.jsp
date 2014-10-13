<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<head>
	<title></title>
	<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
	<link rel="stylesheet" href="<%=request.getContextPath() %>/sxcloud/cresources/ztree/zTree.css" type="text/css" />
	<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" href="<%=request.getContextPath() %>/sxcloud/cresources/ztree/zTreeStyle/zTreeStyle.css" type="text/css" />
	<script type="text/javascript" src="<%=request.getContextPath() %>/sxcloud/cjs/ztree/jquery.ztree.core-3.5.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/sxcloud/cjs/ztree/jquery.ztree.excheck-3.5.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/sxcloud/cjs/ztree/jquery.ztree.exedit-3.5.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/common.js"></script>
	
	<script type="text/javascript" src="yicloud/templettree/rMenu.js"></script>
<style type="text/css">
div#rMenu {
	position: absolute;
	visibility: hidden;
	background-color: #555;
	text-align: left;
	padding: 1px;
}

div#rMenu ul li {
	margin: 1px 0;
	padding: 0 5px;
	cursor: pointer;
	list-style: none outside none;
	background-color: #F8F8F8;
}
</style>
	<script type="text/javascript">
		//右键选择节点
		var rightSNode;
		var setting = {
			view: {
				dblClickExpand: false,
				selectedMulti: false,
				fontCss: getFontCss
			},
			edit: {
				drag: {
					autoExpandTrigger: true,
					prev: dropPrev,
					inner: dropInner,
					next: dropNext
				},
				enable: true,
				showRemoveBtn: false,
				showRenameBtn: false
			},
			callback: {
				onRightClick: OnRightClick,
				onAsyncSuccess: onAsyncSuccess,
				onClick: onClick,
				beforeExpand: zTreeBeforeExpand,
				beforeDrag: zTreeBeforeDrag,
				beforeDrop: zTreeBeforeDrop 
			},
			data: {
				simpleData: {
					enable: true
				}				
			},
			async: {
				enable: true,
				url:"templettree_asyncForTree.do",
				autoParam:["id", "name=n", "level=lv"],
				dataFilter: filter
			}
		};
		function onClick(event, treeId, treeNode, clickFlag) {
			if(treeNode.type==2){//模板信息信息
				$("#right_iframe").attr("src","templettree_imageInfo.do?id="+treeNode.entityId);
			}
		}
		function zTreeBeforeDrag(treeId, treeNodes) {
			for (var i=0,l=treeNodes.length; i<l; i++) {
				if (treeNodes[i].type == 2) {
					return true;
				} else {
					return false;
				}
			}
		};
		function onAsyncSuccess(event, treeId, treeNode, msg) {
			var zTree = $.fn.zTree.getZTreeObj("treeDemo");
			expandNodes(zTree.getNodes());
		}
		function expandNodes(nodes) {
			if (!nodes) return;
			for (var i=0, l=nodes.length; i<l; i++) {
				if(nodes[i].type==1){
					zTree.expandNode(nodes[i], true, false, false);
						if (nodes[i].isParent && nodes[i].zAsync) {
						expandNodes(nodes[i].children);
					}
				}
			}
		}
		function zTreeBeforeDrop(treeId, treeNodes, targetNode, moveType, isCopy){
			for (var i=0,l=treeNodes.length; i<l; i++) {
				if (treeNodes[i].type == 2) {
					var id = treeNodes[i].id;
					var targetId = targetNode.id;
					var targetName = targetNode.name;
					var  url = "templettree_moveintoMenu.do?id="+id+"&targetId="+targetId;
					$.getJSON(url,{'time':new Date().toString()},function(data){
							if(data.result == 1){
                 				alert("移到目录" + targetName + "成功");
								return true;
							}else{
								alert("移至目录" +　targetName　+ "失败" );
								return false;
							}
					});
				}else{
					return false;
				}
			}
		};
		var nodeList = [];
		function updateNodes(highlight) {
			var zTree = $.fn.zTree.getZTreeObj("treeDemo");
			for( var i=0, l=nodeList.length; i<l; i++) {
				nodeList[i].highlight = highlight;
				zTree.updateNode(nodeList[i]);
			}
		}
		function updateNodes(highlight) {
			var zTree = $.fn.zTree.getZTreeObj("treeDemo");
			for( var i=0, l=nodeList.length; i<l; i++) {
				nodeList[i].highlight = highlight;
				if(nodeList[i].type == 2){
					zTree.updateNode(nodeList[i]);		
				}
			}
		}
		function getFontCss(treeId, treeNode) {
			return (!!treeNode.highlight) ? {color:"red", "font-weight":"bold"} : {color:"#333", "font-weight":"normal"};
		}
		function dropPrev(treeId, nodes, targetNode){};
		function dropInner(treeId, nodes, targetNode){
			if(targetNode.type==1){
				return true;
			}else{
				return false;
			}
		}
		function dropNext(treeId, nodes, targetNode){}
		
		
		function filter(treeId, parentNode, childNodes) {
			if (!childNodes) return null;
			for (var i=0, l=childNodes.length; i<l; i++) {
				childNodes[i].name = childNodes[i].name.replace(/\.n/g, '.');
			}
			return childNodes;
		}
		function OnRightClick(event, treeId, treeNode) {
			rightSNode = treeNode;
			if(treeNode ==null){
					showRMenu(treeNode, event.clientX, event.clientY);	
			}else if(treeNode.type==1 || treeNode.type==2){
					showRMenu(treeNode, event.clientX, event.clientY);			
			}
		}
		function showRMenu(treeNode, x, y) {
			$("#rMenu ul").show();
			if(treeNode){
				var type = treeNode.type;
				var state = treeNode.state;
			}
			if(type==null){
				showRm('4');
			}
			if(type==1){
				showRm('2:3');
			}else if(type==2){
				showRm('1');
			}
			//如果从鼠标位置到容器下边的空间小于菜单的高度，就定位菜单的上坐标（Top）为当前鼠标位置向上一个菜单高度
			if(document.body.clientHeight-y<document.getElementById('rMenu').offsetHeight ){
				y=document.body.scrollTop + y - document.getElementById('rMenu').offsetHeight;
			}
			rMenu.css({"top":y+"px", "left":x+"px", "visibility":"visible"});

			$("body").bind("mousedown", onBodyMouseDown);
		}
		function onBodyMouseDown(event){
			if (!(event.target.id == "rMenu" || $(event.target).parents("#rMenu").length>0)) {
				rMenu.css({"visibility" : "hidden"});
			}
		}
		var zTree, rMenu;
		$(document).ready(function(){
			$.fn.zTree.init($("#treeDemo"), setting);
			zTree = $.fn.zTree.getZTreeObj("treeDemo");
			rMenu = $("#rMenu");
		});
		
		//控制隐藏div
		var flag = 0;
		function hide(){
			$(".left").toggle(100); //jquery 1.7
			if(flag==0){
				$("#img").attr("src","sxcloud/cresources/ztree/zTreeStyle/img/diy/icon2.gif");
				flag = 1;
			}else if(flag==1){
				$("#img").attr("src","sxcloud/cresources/ztree/zTreeStyle/img/diy/icon.gif");
				flag =0;
			}
		}
		
		//异步加载数据，当树节点展开时异步加载数据
		function zTreeBeforeExpand(treeId, treeNode) {
			var nodes = treeNode;
			if (nodes) {
				zTree.reAsyncChildNodes(nodes, "refresh",true);
			}
		}
		
		//隐藏右键菜单
		function hideRMenu() {
			if (rMenu) rMenu.css({"visibility": "hidden"});
			$("body").unbind("mousedown", onBodyMouseDown);
		}
		//刷新节点
		function asyncNode() {
			var nodes = rightSNode;
			nodes.isParent = true;
			zTree.reAsyncChildNodes(nodes, "refresh",true);
		}
		//刷新树
		function asyncAllNode() {
			zTree.reAsyncChildNodes(null, "refresh",true);
		}
		//创建子目录
		function creatMenu(){
			var treeNode = rightSNode;
				if(treeNode){
					if (treeNode.type==1) {//创建子目录
						$("#right_iframe").attr("src", "templettree_creatMenu.do?id=" +treeNode.id+"&flag="+0);
					}
				}	
			hideRMenu();
		}
		
		//创建根目录
		function creatMainMenu(){
			$("#right_iframe").attr("src", "templettree_creatMainMenu.do?flag="+1);
			hideRMenu();
		}
		//删除目录
		function deleteMenu(){
			var nodes = rightSNode;
			if(nodes){
				var treeNode = nodes;
				var parentNode = treeNode.getParentNode();
				if(confirm("是否要删除该目录?")==true){
					hideRMenu();
					var url = "templettree_deleteMenu.do?id=" + treeNode.id;
					$.getJSON(url,{'time':new Date().toString()},function(data){
						if(data.result==1){
							alert("删除成功！");
							zTree.reAsyncChildNodes(parentNode, "refresh",true);
						}else{
							alert("删除失败！\u000d" + data.reason);
						}
					});
				}
			}
		}
		//设置模板与脚本关联
		function scriptRelation(){
			var templet_id = rightSNode.entityId;
			var templet_name = rightSNode.name;
			$("#right_iframe").attr("src", "templettree_listRelationScript.do?templet_id="+templet_id+"&templet_name="+encodeURI(encodeURI(templet_name)));
			hideRMenu();
		}
		
		//搜索的相关方法
		var nodeList = [];
		function searchNode() {
			var value = $.trim(key.get(0).value);
			var zTree = $.fn.zTree.getZTreeObj("treeDemo");
			var keyType = "name";
			updateNodes(false);
			nodeList = zTree.getNodesByParamFuzzy(keyType, value);
			updateNodes(true);
		}
		
		function selectNodes(){
			var value = $.trim(key.get(0).value);
			var zTree = $.fn.zTree.getZTreeObj("treeDemo");
			var keyType = "name";
			nodeList = zTree.getNodesByParamFuzzy(keyType, value);
			var reList = new Array();
			for( var i=0, l=nodeList.length; i<l; i++) {
				if(nodeList[i].type == 2){
					reList.push(nodeList[i]);
				}
			}
			return reList;
		}
		
		$(document).ready(function(){
			key = $("#key");
			key.bind("propertychange", searchNode)
			.bind("input", searchNode);
		});
		
		window.document.onkeydown = showMsg;
		function showMsg() {
		  var keycode = event.keyCode;
		  if (keycode == 13) {
		  	if(window.event.srcElement.tagName == "INPUT"){
		  		if(window.event.srcElement.type == "text"){
					var list = selectNodes();
					if(list.length<1){
						alert("没有符合项");
					}else if(list.length>1){
						alert("请正确搜索单一模板");
					}else{
						$("#right_iframe").attr("src","templettree_imageInfo.do?id="+list[0].entityId);
						return false;
					}
		  		}
		 	 }
		  }
		}
	</script>
</head>
<body>
	<s:form action="" method="post" id="theForm">
		<div class="content_wrap">
			<div class="left">
				<ul class="list">
					搜索<br/>模板名称：<input type="text" id="key" value="" /><br/>
				</ul>
				<ul id="treeDemo" class="ztree" style="margin-top: 45px"></ul>
			</div>
			<div style="float: left; margin-top: 250px">
				<img id="img"
					src="sxcloud/cresources/ztree/zTreeStyle/img/diy/icon.gif"
					onclick="hide()" />
			</div>
			<div class="right">
				<div id="iframe">
					<iframe id="right_iframe" src="" name="xxxx" height="100%" width="77%" frameborder="0" style="position:fixed;left:259px;right:0px;top:0px;bottom:0px;overflow-x:auto;"></iframe>
				</div>
			</div>
		</div>
		<div id="rMenu">
			<ul>
				<li id="scriptRelation" onclick="scriptRelation();" class="hide">
					与脚本关联
				</li>
				<li id="creatMenu" onclick="creatMenu()" class="hide">
					创建子目录
				</li>
				<li id="creatMainMenu" onclick="creatMainMenu()" class="hide">
					创建目录
				</li>
				<li id="deleteMenu" onclick="deleteMenu()" class="hide">
					删除目录
				</li>
			</ul>	
		</div>
	</s:form>
</body>
