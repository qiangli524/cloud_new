<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../sxcloud/common/taglib.jsp"%>
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="cache-control" content="no-cache"/>
<meta http-equiv="expires" content="0"/>


<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />


<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>

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
	<script type="text/javascript" src="yicloud/busitree/rMenu.js"></script>
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
				selectedMulti: false
			},
			callback: {
				onRightClick: OnRightClick,
				onClick: onClick,
				beforeExpand: zTreeBeforeExpand
			},
			data: {
				simpleData: {
					enable: true
				}				
			},
			check: {
				enable: true
			},
			async: {
				enable: true,
				url:"busitree_asyncForTree.do",
				autoParam:["id", "name=n", "level=lv"],
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
		function onClick(event, treeId, treeNode, clickFlag) {
			if(treeNode.type==3){//点击应用时的信息
				$("#right_iframe").attr("src","busitree_busitabs.do?type="+treeNode.type + "&id="+treeNode.entityId);
			}else if(treeNode.type ==2){//点击业务系统的信息
				$("#right_iframe").attr("src","busitree_busitabs.do?type="+treeNode.type + "&id="+treeNode.entityId);
			}
		}
		function OnRightClick(event, treeId, treeNode) {
			rightSNode = treeNode;
			if(treeNode ==null){
					showRMenu(treeNode, event.clientX, event.clientY);	
			}else if(treeNode.type==1 || treeNode.type==2 || treeNode.type==3){
					showRMenu(treeNode.type, event.clientX, event.clientY);			
			}
		}
		function showRMenu(type, x, y) {
			$("#rMenu ul").show();
			if(type==null){
				hideMenuDiv();
			}
			if(type==1){
				showRm('0');
			}else if(type==2){
				showRm('1');
			}else if(type==3){
				showRm('2:3');
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
			$(".left").toggle('slide',{direction: 'left'},100);
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
		//添加业务系统
		function addBizsys(){
			var treeNode = rightSNode;
			$("#right_iframe").attr("src","bizsystem_addBizSystem.do?id="+treeNode.entityId);
			hideRMenu();
		}
		//添加应用
		function addBizsysApp(){
			var treeNode = rightSNode;
			$("#right_iframe").attr("src","busitree_addBizsysApp.do?id="+treeNode.entityId + "&name=" + encodeURI(encodeURI(treeNode.name)));
			hideRMenu();
		}
		//隐藏右键菜单
		function hideRMenu() {
			if (rMenu) rMenu.css({"visibility": "hidden"});
			$("body").unbind("mousedown", onBodyMouseDown);
		}
		//部署应用
		function deployApp(){
			var treeNode = rightSNode;
			var nodes = zTree.getCheckedNodes(true),
			hostId = "";
			for (var i=0, l=nodes.length; i<l; i++) {
				hostId += nodes[i].entityId + ",";
			}
			if (hostId.length > 0 ) hostId = hostId.substring(0, hostId.length-1);
			$("#right_iframe").attr("src","busitree_addDeployApp.do?id="+treeNode.entityId + "&hostId=" + hostId);
			hideRMenu();
		}
	</script>
</head>
<body>
	<s:form action="busitree_listBusiTree" method="post" id="theForm">
		<div class="content_wrap">
			<div class="left">
				<ul id="treeDemo" class="ztree"></ul>
			</div>
			<div style="float: left; margin-top: 200px">
				<img id="img"
					src="sxcloud/cresources/ztree/zTreeStyle/img/diy/icon.gif"
					onclick="hide()" />
			</div>
			<div class="right">
				<div id="iframe">
					<iframe id="right_iframe" src="" name="xxxx" width="870"
						height="820" frameborder="0"></iframe>
				</div>
			</div>
		</div>
		<div id="rMenu">
			<ul>
				<li id="addBizsys" onclick="addBizsys()" class="hide">
					添加业务系统
				</li>
				<li id="addBizsysApp" onclick="addBizsysApp()" class="hide">
					添加应用
				</li>
				<li id="deployApp" onclick="deployApp()" class="hide">
					部署
				</li>
				<li id="updateApp" onclick="updateApp()" class="hide">
					升级
				</li>
			</ul>	
		</div>
	</s:form>
</body>
