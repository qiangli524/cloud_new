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
	<script type="text/javascript" src="yicloud/vmman/js/listServerTree.js"></script>
	<script type="text/javascript" src="yicloud/vmman/js/rMenu.js"></script>
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
	padding: 1px;
}

div#rMenu ul li {
	margin: 1px 0;
	padding: 0 5px;
	cursor: pointer;
	list-style: none outside none;
	background-color: #EAEAEA;
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
				beforeExpand: zTreeBeforeExpand,
				onRename:onRename
			},
			data: {
				simpleData: {
					enable: true
				}				
			},
			async: {
				enable: true,
				url:"tree_asyncForTree.do",
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
		function onAsyncSuccess(event, treeId, treeNode, msg) {
		}
		
		
		var zNodes =[
  	 	];
		
		
		function OnRightClick(event, treeId, treeNode) {
			rightSNode = treeNode;
			if(treeNode ==null){
					showRMenu(treeNode, event.clientX, event.clientY);	
			}else if(treeNode.type==8 || treeNode.type==6 || treeNode.type==3 || treeNode.type==1 || treeNode.type==0 ||treeNode.type==22||treeNode.type==23 || treeNode.type==21 ||treeNode.type==25){
					showRMenu(treeNode.type, event.clientX, event.clientY);			
			}
		}
		//节点重命名
		function rename() {
			var nodes = rightSNode;
			if (nodes.length == 0) {
				alert("请先选择一个节点");
				return;
			}
			zTree.editName(nodes);
		}
		
		function onRename(event, treeId, treeNode) {
			$.getJSON("yvm_updateNodeName.do?id=" + treeNode.id + "&type=" + treeNode.type + "&name=" + treeNode.name, {"time":new Date().toString()}, function (data) {
				var result = data.result;
				if (result == null) {
					alert("重命名成功");
				} else {
					alert("操作失败");
				}
			});
		hideRMenu();
		}

		function showRMenu(type, x, y) {
			$("#rMenu ul").show();
			if(type==null){
				showRm('0');
			}
			if(type==8){
				showRm('1:2:23');
			}
			if(type==6){
				showRm('3:23');
			}
			if(type==3 || type==21){
				showRm('4:5:18:23');
			}
			if(type==1){
				showRm('6:8:19:21:22:23');
			}
			if(type==0){
				showRm('9:10:11:12:13:14:15:16:17:20:23');
			}
			if(type==25){
				showRm('6:8:19:21:23');
			}
			
			if(type==22 || type==23){
				showRm('6');
			}
			//如果从鼠标位置到容器下边的空间小于菜单的高度，就定位菜单的上坐标（Top）为当前鼠标位置向上一个菜单高度
			if(document.body.clientHeight-y<document.getElementById('rMenu').offsetHeight ){
				y=document.body.scrollTop + y - document.getElementById('rMenu').offsetHeight;
			}
			rMenu.css({"top":y+"px", "left":x+"px", "visibility":"visible"});

			$("body").bind("mousedown", onBodyMouseDown);
		}
		
		//隐藏右键菜单
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
			hideRMenu();
		}
		
		function onClick(event, treeId, treeNode, clickFlag) {
			if(treeNode.type==8){//数据中心信息
				$("#datacenterinfo_iframe").attr("src","yvm_vmtabs.do?type="+treeNode.type + "&id="+treeNode.id);
				setDivVisProp('datacenterinfo');
			}
			if(treeNode.type==3 || treeNode.type==21){//集群信息
				$("#clusterinfo_iframe").attr("src","yvm_vmtabs.do?type="+treeNode.type + "&id=" + treeNode.id);
				setDivVisProp('clusterinfo');
			}
			if(treeNode.type==1|| treeNode.type==25){	//主机信息
				$("#host_info_iframe").attr("src","yvm_vmtabs.do?type="+treeNode.type+"&id="+treeNode.id);
				setDivVisProp('host_info');
			}
			if(treeNode.type==0){	//虚拟机信息
				$("#vm_info_iframe").attr("src","yvm_vmtabs.do?type="+treeNode.type+"&id="+treeNode.id);
				setDivVisProp('vm_info');
			}
			if(treeNode.type==22 || treeNode.type==23){	//模板镜像信息
				$("#imageinfo_iframe").attr("src","yvm_vmtabs.do?type="+treeNode.type+"&id="+treeNode.id);
				setDivVisProp('imageinfo');
			}
			if(treeNode.type==18){	//存储信息
				$("#datastoreinfo_iframe").attr("src","yvm_vmtabs.do?type="+treeNode.type+"&id="+treeNode.id);
				setDivVisProp('datastoreinfo');
			}
			if(treeNode.type==19){	//存储信息
				$("#net_info_iframe").attr("src","yvm_vmtabs.do?type="+treeNode.type+"&id="+treeNode.id);
				setDivVisProp('net_info');
			}
			hideRMenu();
		}
		//定时获取虚拟机状态
		
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
		
	</script>
</head>
<body onload="pageOnLoad()">
	<s:form action="yvm_listVMService" method="post" id="theForm"
		cssStyle="theForm">
		<s:hidden name="theForm.VH_HOST" id="VH_HOST"></s:hidden>
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
				<div id="datastoreinfo">
					<iframe id="datastoreinfo_iframe" src="" name="xxxx" width="840"
						height="800" frameborder="0"></iframe>
				</div>
				<div id="imageinfo">
					<iframe id="imageinfo_iframe" src="" name="xxxx" width="840"
						height="800" frameborder="0"></iframe>
				</div>
				<div id="datacenterinfo">
					<iframe id="datacenterinfo_iframe" src="" name="xxxx" width="840"
						height="800" frameborder="0"></iframe>
				</div>
				<div id="clusterinfo">
					<iframe id="clusterinfo_iframe" src="" name="xxxx" width="840"
						height="800" frameborder="0"></iframe>
				</div>
				<div id="host_info">
					<iframe id="host_info_iframe" src="" name="xxxx" width="840"
						height="800" frameborder="0"></iframe>
				</div>
				<div id="vm_info">
					<iframe id="vm_info_iframe" src="" name="xxxx" width="840"
						height="800" frameborder="0"></iframe>
				</div>
				<div id="report_virtual">
					<iframe id="virtual_monitor_iframe" src="" name="xxxx" width="840"
						height="800" frameborder="0"></iframe>
				</div>
				<div id="image_list">
					<iframe id="image_list_iframe" src="" name="xxxx" width="900"
						height="1500" frameborder="0"></iframe>
				</div>
				<div id="report_host">
					<iframe id="host_monitor_iframe" src="" name="xxxx" width="840"
						height="800" frameborder="0"></iframe>
				</div>
				<div id="host_add_page">
					<iframe id="host_add_iframe" src="" name="xxxx" width="840"
						height="800" frameborder="0"></iframe>
				</div>
				<div id="virtual_lookup">
					<iframe id="virtual_lookup_iframe" src="" name="xxxx" width="840"
						height="800" frameborder="0"></iframe>
				</div>
				<div id="app_deploy_div">
					<iframe id="app_deploy_iframe" src="" name="xxxx" width="840"
						height="800" frameborder="0"></iframe>
				</div>
				<div id="virtual_conn_div">
					<iframe id="virtual_conn_iframe" src="" name="xxxx" width="840"
						height="800" frameborder="0"></iframe>
				</div>
				<div id="health_state">
					<iframe id="health_state_iframe" src="" name="xxxx" width="840"
						height="800" frameborder="0"></iframe>
				</div>
				<div id="cluster_add">
					<iframe id="cluster_add_iframe" src="" name="xxxx" width="840"
						height="800" frameborder="0"></iframe>
				</div>
				<div id="net_info">
					<iframe id="net_info_iframe" src="" name="xxxx" width="840"
						height="800" frameborder="0"></iframe>
				</div>
			</div>
		</div>
		<div id="clusterMenu">
			<ul>

			</ul>
		</div>
		<div id="rMenu">
			<ul>
				<li id="addDataCenter" onclick="addDataCenter();" class="hide">
					添加数据中心
				</li>
				<li id="delDataCenter" onclick="delDataCenter()" class="hide">
					删除数据中心
				</li>
				<li id="conDataCenter" onclick="conDataCenter();" class="hide">
					配置数据中心
				</li>
				<li id="addCluster" onclick="addCluster();" class="hide">
					添加集群
				</li>
				<li id="delCluster" onclick="delTreeNode();" class="hide">
					删除集群
				</li>
				<li id="conCluster" onclick="conCluster();" class="hide">
					修改集群配置
				</li>
				<li id="host_add" onclick="host_add();" class="hide">
					增加主机
				</li>
				<li id="host_del" onclick="delTreeNode();" class="hide">
					删除主机
				</li>
				<li id="host_conn" onclick="virtual_conn();" class="hide">
					连接主机
				</li>
				<li id="host_monitor" onclick="host_monitor();" class="hide">
					监控主机
				</li>
				<li id="virtual_add" onclick="virtual_add();" class="hide">
					部署虚拟机
				</li>
				<li id="virtual_del" onclick="put_virtual_state('destroy');" class="hide">
					删除虚拟机
				</li>
				<li id="virtual_start" onclick="put_virtual_state('create');" class="hide">
					启动虚拟机
				</li>
				<li id="virtual_stop" onclick="put_virtual_state('shutdown');" class="hide">
					关闭虚拟机
				</li>
				<li id="virtual_pause" onclick="put_virtual_state('suspend');" class="hide">
					暂停虚拟机
				</li>
				<li id="virtual_resume" onclick="put_virtual_state('resume');" class="hide">
					恢复虚拟机
				</li>
				<li id="virtual_reboot" onclick="put_virtual_state('reboot');" class="hide">
					重启虚拟机
				</li>
				<li id="virtual_view" onclick="virtual_view('0');" class="hide">
					查看虚拟机
				</li>
				<li id="virtual_setting" onclick="virtual_view('1');" class="hide">
					调整虚拟机
				</li>
				<li id="virtual_monitor" onclick="virtual_monitor();" class="hide">
					监控虚拟机
				</li>
				<li id="virtual_conn" onclick="virtual_conn();" class="hide">
					连接虚拟机
				</li>
				<li id="app_deploy" onclick="app_deploy('1');" class="hide">
					应用部署
				</li>
				<li id="add_nake" onclick="virtual_add();" class="hide">
					创建裸机
				</li>
				<li id="rename" onclick="rename();" class="hide">
					重命名
				</li>
			</ul>
		</div>
	</s:form>
</body>
</html:html>
