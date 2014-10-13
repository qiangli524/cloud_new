<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../sxcloud/common/taglib.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
<%@ page import="com.sitech.basd.sxcloud.rsmu.domain.system.TbSysUserinfoObj"%>
<%@ page import="com.sitech.basd.sxcloud.rsmu.config.Constant" %>
<head>
	<title></title>
	<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" href="sxcloud/cresources/ztree/zTree.css"
		type="text/css" />
	<link rel="stylesheet"
		href="sxcloud/cresources/ztree/zTreeStyle/zTreeStyle.css"
		type="text/css" />
	<script type="text/javascript"
		src="sxcloud/cjs/ztree/jquery.ztree.core-3.5.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
	<script type="text/javascript" src="gx/huaweientitytree/js/listHuaWeiServerTree.js"></script>
	<script type="text/javascript" src="gx/huaweientitytree/js/rMenu.js"></script>
	<script type="text/javascript"
		src="sxcloud/cjs/ztree/jquery.ztree.excheck-3.5.js"></script>
	<script type="text/javascript"
		src="sxcloud/cjs/ztree/jquery.ztree.exedit-3.5.js"></script>
	<style type="text/css">
div.complexmenu {
	position: absolute;
	visibility: hidden;
	background-color: #9dbcd9;
	text-align: left;
	padding: 0 1px 1px 1px;

}
div.complexmenu ul li {
    background-color: white;
    cursor: pointer;
    list-style: none outside none;
    margin: 1px 0 0 1px;
    padding: 2px 5px 2px 12px;
}

div.complexmenu ul li:hover {
    background-color: #dfecf9;
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
			edit: {
				enable: true,
				showRemoveBtn: false,
				showRenameBtn: false
			},
			callback: {
				onRightClick: OnRightClick,
				onAsyncSuccess: onAsyncSuccess,
				onClick: onClick,
				onDblClick: zTreeOnDblClick,
				beforeExpand: zTreeBeforeExpand
			},
			data: {
				simpleData: {
					enable: true
				}				
			},
			async: {
				enable: true,
				url:"huaweientitytree_asyncForHuaweiTree.do",
				autoParam:["id","type","entityId", "name=n"]
			}
		};
		
		function onAsyncSuccess(event, treeId, treeNode, msg) {
		}
		
		function OnRightClick(event, treeId, treeNode) {
			rightSNode = treeNode;
			if(treeNode ==null){
					showRMenu(treeNode, event.clientX, event.clientY);	
			}else if(treeNode.type==1 || treeNode.type==2 || treeNode.type==3 || treeNode.type==4 || treeNode.type==5){
					showRMenu(treeNode, event.clientX, event.clientY);			
			}
		}
		
		function showRMenu(treeNode, x, y) {
			$("#rMenu ul").show();
			if(treeNode){
				var type = treeNode.type;
				var state = treeNode.state;
				var auth = treeNode.authority;
			}
			if(type == null){
				showRm('0');
			}else{
				showRm('');
			}
			
			//如果从鼠标位置到容器下边的空间小于菜单的高度，就定位菜单的上坐标（Top）为当前鼠标位置向上一个菜单高度
			if(document.getElementById("content_wrap_ztree").clientHeight-y<document.getElementById('rMenu').offsetHeight ){
				y=document.body.scrollTop + y - document.getElementById('rMenu').offsetHeight;
			}
			rMenu.css({"top":y+"px", "left":x+"px", "visibility":"visible"});

			$("body").bind("mousedown", onBodyMouseDown);
		}
		//刷新节点
		function asyncNode() {
			var nodes = rightSNode;
			var parentNode = nodes.getParentNode().getParentNode();
			zTree.reAsyncChildNodes(parentNode, "refresh",true);
		}
		
		
		//隐藏右键菜单
		function hideRMenu() {
			if (rMenu) rMenu.css({"visibility": "hidden"});
			$(".complexmenu").css({"visibility": "hidden"});
			$("body").unbind("mousedown", onBodyMouseDown);
		}
		
		function onBodyMouseDown(event){
			if (!(event.target.css == "complexmenu" || $(event.target).parents(".complexmenu").length>0)) {
				$(".complexmenu").css({"visibility" : "hidden"});
			}
		}
		var zTree, rMenu;
		$(document).ready(function(){
			$.fn.zTree.init($("#treeDemo"), setting);
			zTree = $.fn.zTree.getZTreeObj("treeDemo");
			rMenu = $("#rMenu");
		});
		
		function onClick(event, treeId, treeNode, clickFlag) {
			if(treeNode.type==1){//数据中心信息
				$("#right_iframe").attr("src","huaweientitytree_vmtabs.do?type="+treeNode.type + "&id="+treeNode.id);
				setDivVisProp('iframe');
			}
			if(treeNode.type==2){//集群信息
				$("#right_iframe").attr("src","huaweientitytree_vmtabs.do?type="+treeNode.type + "&id="+treeNode.id);
				setDivVisProp('iframe');
			}
			if(treeNode.type==3){	//主机信息
				$("#right_iframe").attr("src","huaweientitytree_vmtabs.do?type="+treeNode.type + "&id="+treeNode.id);
				setDivVisProp('iframe');
			}
			if(treeNode.type==4){	//虚拟机信息
				$("#right_iframe").attr("src","huaweientitytree_vmtabs.do?type="+treeNode.type + "&id="+treeNode.id);
				setDivVisProp('iframe');
			}
			if(treeNode.type==5){	//模板信息
				$("#right_iframe").attr("src","huaweientitytree_vmtabs.do?type="+treeNode.type + "&id="+treeNode.id);
				setDivVisProp('iframe');
			}
			hideRMenu();
		}
		
		
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
		//双击展开树节点
		function zTreeOnDblClick(event, treeId, treeNode){
			expandNodes(treeNode);
		}
		function expandNodes(nodes) {
			if (!nodes) return;
			for (var i=0, l=nodes.length; i<l; i++) {
				if(nodes[i].type==3 || nodes[i].type==6 || nodes[i].type==1){
					zTree.expandNode(nodes[i], true, false, false);
						if (nodes[i].isParent && nodes[i].zAsync) {
						expandNodes(nodes[i].children);
					}
				}
			}
		}
		
		//同步数据
		function synchroData(){
			hideRMenu();
			var url = "huaweientitytree_synchroData.do";
			if(confirm("确定要重新同步数据吗?")==true){
				$.getJSON(url,{'time': new Date().toString()},function(data){
					if(data.result==1){
						alert("同步数据成功!");
						zTree.reAsyncChildNodes(null, "refresh",true);
					}else{
						alert("同步数据失败!");
					}
				});
			}
		}
	</script>
</head>
<body>
	<s:form action="huaweientitytree_listhuaweientitytree" method="post" id="theForm">
		<div class="content_wrap" id="content_wrap_ztree">
			<div class="left">
				<ul id="treeDemo" class="ztree"></ul>
			</div>
			<div style="float: left; margin-top: 150px">
				<img id="img"
					src="sxcloud/cresources/ztree/zTreeStyle/img/diy/icon.gif"
					onclick="hide()" />
			</div>
			<div class="right">
				<div id="iframe">
					<iframe id="right_iframe" src="" name="xxxx" height="100%" width="76%" frameborder="0" style="position:fixed;left:259px;right:0px;top:0px;bottom:0px;overflow-x:auto;"></iframe>
				</div>
			</div>
		</div>
		<div id="rMenu" class="complexmenu">
			<ul>
				<li	id="synchroData" onclick="synchroData();" class="hide">
					同步数据
				</li>
			</ul>
		</div>
	</s:form>
</body>
