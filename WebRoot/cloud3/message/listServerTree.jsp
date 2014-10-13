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
	<script type="text/javascript" src="yicloud/entitytree/js/listServerTree.js"></script>
	<script type="text/javascript" src="yicloud/entitytree/js/rMenu.js"></script>
	<script type="text/javascript"
		src="sxcloud/cjs/ztree/jquery.ztree.excheck-3.5.js"></script>
	<script type="text/javascript"
		src="sxcloud/cjs/ztree/jquery.ztree.exedit-3.5.js"></script>
	<style type="text/css">
div.complexmenu {
	position: absolute;
	visibility: hidden;
	background-color: #AAAAAA;
	text-align: left;
	padding: 1px;
}
div.complexmenu ul li {
	margin: 1px 0;
	padding: 0 5px;
	cursor: pointer;
	list-style: none outside none;
	background-color: white;
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
				//beforeDrop: zTreeBeforeDrop,
				onCheck: zTreeOnCheck
			},
			data: {
				simpleData: {
					enable: true
				}				
			},
			async: {
				enable: true,
				url:"message_asyncForTree.do",
				autoParam:["id","type","entityId", "name=n", "level=lv"],
				dataFilter: filter
			},
			check: {
				enable: true,
				chkStyle: "checkbox",
				chkboxType: { "Y": "", "N": "" }
			}
		};
		function zTreeOnCheck(event, treeId, treeNode) {
		    alert(treeNode.tId + ", " + treeNode.name + "," + treeNode.checked);
		};
		function wewqe(event, treeId, treeNode){
			alert(treeNode.tId + ", " + treeNode.name + "," + treeNode.checked);
		}
		function filter(treeId, parentNode, childNodes) {
			if (!childNodes) return null;
			for (var i=0, l=childNodes.length; i<l; i++) {
				childNodes[i].name = childNodes[i].name.replace(/\.n/g, '.');
			}
			return childNodes;
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
		
		
		function dropPrev(treeId, nodes, targetNode){
			
		};
		function dropInner(treeId, nodes, targetNode){
			if(targetNode.type==3){
				return true;
			}else{
				return false;
			}
		};
		function dropNext(treeId, nodes, targetNode){
		};
		var zNodes =[
  	 	];
		
		
		//刷新节点
		function asyncNode() {
			var nodes = rightSNode;
			var parentNode = nodes.getParentNode().getParentNode();
			zTree.reAsyncChildNodes(parentNode, "refresh",true);
		}
		//保存成功后刷新
		function refreshNode(){
			var nodes = rightSNode;
			zTree.reAsyncChildNodes(nodes, "refresh",true);
		}
		//刷新父节点 
		function refreshParentNode(){
			var nodes = rightSNode;
			if(nodes){
				zTree.reAsyncChildNodes(nodes.getParentNode(), "refresh",true);				
			}
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
		function  healthState(){
				var nodes = rightSNode;
				if (nodes) {
					var treeNode = nodes;
				$("#health_state_iframe").attr("src","yvm_listHealthState.do?ID="+oper+"&virtualId="+treeNode.id);
				setDivVisProp('health_state');
			}
		}
		
		function onClick(event, treeId, treeNode, clickFlag) {
			if(treeNode.type==8){//数据中心信息
				$("#right_iframe").attr("src","tree_vmtabs.do?type="+treeNode.type + "&id="+treeNode.id);
				setDivVisProp('iframe');
			}
			if(treeNode.type==3 || treeNode.type==21){//集群信息
				$("#right_iframe").attr("src","tree_vmtabs.do?type="+treeNode.type + "&id=" + treeNode.entityId);
				setDivVisProp('iframe');
			}
			if(treeNode.type==1 || treeNode.type==25){	//主机信息
				$("#right_iframe").attr("src","tree_vmtabs.do?type="+treeNode.type+"&id="+treeNode.entityId+"&tempId="+treeNode.id+"&name="+treeNode.name);
				setDivVisProp('iframe');
			}
			if(treeNode.type==0||treeNode.type==24){	//虚拟机信息
				$("#right_iframe").attr("src","tree_vmtabs.do?type="+treeNode.type+"&id="+treeNode.entityId+"&name="+treeNode.name+"&parentName="+treeNode.getParentNode().name);
				setDivVisProp('iframe');
			}
			if(treeNode.type==22 || treeNode.type==23){	//模板镜像信息
				$("#right_iframe").attr("src","tree_vmtabs.do?type="+treeNode.type+"&id="+treeNode.entityId);
				setDivVisProp('iframe');
			}
			if(treeNode.type==18){	//存储信息
				$("#right_iframe").attr("src","tree_vmtabs.do?type="+treeNode.type+"&id="+treeNode.entityId);
				setDivVisProp('iframe');
			}
			if(treeNode.type==19){	//网络信息
				$("#right_iframe").attr("src","tree_vmtabs.do?type="+treeNode.type+"&id="+treeNode.entityId);
				setDivVisProp('iframe');
			}
			if(treeNode.type==27){//xen虚拟机
				$("#right_iframe").attr("src","tree_vmtabs.do?type="+treeNode.type+"&id="+treeNode.entityId+"&pool_uuid="+treeNode.getParentNode().getParentNode().tree_uuid+"&host_uuid="+treeNode.getParentNode().tree_uuid);
				setDivVisProp('iframe');
			}if(treeNode.type==26){//xen主机
				$("#right_iframe").attr("src","tree_vmtabs.do?type="+treeNode.type+"&id="+treeNode.entityId+"&tempId="+treeNode.id+"&pool_uuid="+treeNode.getParentNode().tree_uuid+"&host_uuid="+treeNode.tree_uuid+"&name="+treeNode.name);
				setDivVisProp('iframe');
			}if(treeNode.type==29){//xen集群
				$("#right_iframe").attr("src","tree_vmtabs.do?type="+treeNode.type+"&id="+treeNode.id+"&pool_uuid="+treeNode.tree_uuid);
				setDivVisProp('iframe');
			}if(treeNode.type==28){//xen模板
				$("#right_iframe").attr("src","tree_vmtabs.do?type="+treeNode.type+"&id="+treeNode.entityId+"&pool_uuid="+treeNode.getParentNode().getParentNode().tree_uuid+"&host_uuid="+treeNode.getParentNode().tree_uuid);
				setDivVisProp('iframe');
			}
		}
		//定时获取虚拟机状态
		
		//控制隐藏div
		var flag = 0;
		function hide(){
			//$(".left").toggle('slide',{direction: 'left'},100); //jquery 1.3
			$(".left").toggle(100); //jquery 1.7
			if(flag==0){
				//$(".left").animate({marginLeft: '-300px'}, "slow"); //jquery 1.7
				$("#img").attr("src","sxcloud/cresources/ztree/zTreeStyle/img/diy/icon2.gif");
				flag = 1;
			}else if(flag==1){
				//$(".left").animate({marginLeft: '0px'}, "slow"); //jquery 1.7
				$("#img").attr("src","sxcloud/cresources/ztree/zTreeStyle/img/diy/icon.gif");
				flag =0;
			}
		}
		
		
		
		
	</script>
</head>
<body onload="pageOnLoad()">
	<s:form action="yvm_listVMService" method="post" id="theForm">
		<s:hidden name="theForm.VH_HOST" id="VH_HOST"></s:hidden>
		<div class="content_wrap" id="content_wrap_ztree">
		<!--  
			<div style="height:40px">
				<ul>
					<li >
						&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="text" id="inputSearch" class="txt"/>
						<button  id="search">搜索</button> <br />
						<font color="red">(可输入名称或ip地址以查询匹配的主机，虚拟机)</font>
					</li>
				</ul>
			</div>
		-->
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
		
	</s:form>
</body>
