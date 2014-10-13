<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="com.sitech.basd.sxcloud.rsmu.domain.system.TbSysUserinfoObj"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page import="com.sitech.basd.sxcloud.rsmu.config.Constant" %>
<head>
	<title></title>
	<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
	<link rel="stylesheet" href="<%=request.getContextPath() %>/sxcloud/cresources/ztree/zTree.css" type="text/css" />
	<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" href="<%=request.getContextPath() %>/sxcloud/cresources/ztree/zTreeStyle/zTreeStyle.css" type="text/css" />
	<script type="text/javascript" src="<%=request.getContextPath() %>/sxcloud/cjs/ztree/jquery.ztree.core-3.5.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/listXenTree.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/xenMenu.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/sxcloud/cjs/ztree/jquery.ztree.excheck-3.5.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/sxcloud/cjs/ztree/jquery.ztree.exedit-3.5.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/common.js"></script>
	<style type="text/css">
div#rMenu {
	position: absolute;
	visibility: hidden;
	background-color:  #9dbcd9;
	text-align: left;
	padding: 0 1px 1px 1px;
}

div#div_virtual {
	position: absolute;
	visibility: hidden;
	background-color: #9dbcd9;
	text-align: left;
	padding: 1px;
}
div#div_snapshot {
	position: absolute;
	visibility: hidden;
	background-color: #9dbcd9;
	text-align: left;
	padding: 1px;
}
div#rMenu ul li {
	background-color: white;
    cursor: pointer;
    list-style: none outside none;
    margin: 1px 0 0 1px;
    padding: 2px 5px 2px 12px;
}
div#div_virtual ul li {
	background-color: white;
    cursor: pointer;
    list-style: none outside none;
    margin: 1px 0 0 1px;
    padding: 2px 5px 2px 12px;
}
div#div_snapshot ul li {
	background-color: white;
    cursor: pointer;
    list-style: none outside none;
    margin: 1px 0 0 1px;
    padding: 2px 5px 2px 12px;
}

div#div_operatingSystem ul li {
	background-color: white;
    cursor: pointer;
    list-style: none outside none;
    margin: 1px 0 0 1px;
    padding: 2px 5px 2px 12px;
}
div#rMenu ul li:hover {
	background:#dfecf9;
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
				onRightClick: OnRightClick,
				onAsyncSuccess: onAsyncSuccess,
				onClick: onClick,
				beforeExpand: zTreeBeforeExpand,
				onRename:onRename,
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
				url:"xentree_asyncForTree.do",
				autoParam:["id","type","entityId","tree_uuid","name=n", "level=lv"],
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
				if(treeNode){
					expandNodes(treeNode.children);
				}
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
		function zTreeBeforeDrag(treeId, treeNodes) {
			for (var i=0,l=treeNodes.length; i<l; i++) {
				if (treeNodes[i].type == 1) {
					var parentNode = treeNodes[i].getParentNode();
					if(parentNode.type==8){
						return true;
					}else{
						return false;
					}
				} else {
					return false;
				}
			}
		};
		function zTreeBeforeDrop(treeId, treeNodes, targetNode, moveType, isCopy){
			for (var i=0,l=treeNodes.length; i<l; i++) {
				if (treeNodes[i].type == 1) {
					var parentNode = treeNodes[i].getParentNode();
					var clusterName = targetNode.name;//集群名称
					var clCode = targetNode.entityId;
					var dcName = parentNode.entityId;//数据中心名称
					var targetId = targetNode.id;
					
					var id = treeNodes[i].id;
					var name = treeNodes[i].name;
					var entityId = treeNodes[i].entityId;
					$.ajax({
			  			type:"GET",
              			url:"tree_moveintoCluster.do?dcName="+dcName+"&clusterName="+clusterName+"&id="+id+"&name="+name+"&entityId="+entityId+"&targetId="+targetId+"&clCode="+clCode,
              			async: false,
              			cache:false,
	          			success: function(data){
	          				var jsondata = eval("("+ data+")");
                 			if(jsondata.result==1){
                 				alert("移到集群" + clusterName + "成功");
								return true;
							}else{
								alert("移至集群" +　clusterName　+ "失败，可能的原因" + jsondata.result);
								return false;
							}
              			}
					});
				}else{
					return false;
				}
			}
		};
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
		
		
		function OnRightClick(event, treeId, treeNode) {
			rightSNode = treeNode;
			if(treeNode ==null){
					showRMenu(treeNode, event.clientX, event.clientY);	
			}else if(treeNode.type==34 || treeNode.type==6 || treeNode.type==3 || treeNode.type==1 || treeNode.type==0 ||treeNode.type==22||treeNode.type==23 || treeNode.type==24 ||treeNode.type==21 || treeNode.type==25|| treeNode.type==29 || treeNode.type==26 || treeNode.type==27 || treeNode.type==28 || treeNode.type==35){
					showRMenu(treeNode, event.clientX, event.clientY);			
			}
		}
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
				zTree.reAsyncChildNodes(nodes.getParentNode(), "refresh",false);				
			}
		}
		function refreshClusterNode(){
			var nodes = rightSNode;
			if(nodes){
				zTree.reAsyncChildNodes(nodes.getParentNode().getParentNode(), "refresh",false);				
			}
		}
		function showRMenu(treeNode, x, y) {
			$("#rMenu ul").show();
			if(treeNode){
				var type = treeNode.type;
				var state = treeNode.state;
				var auth = treeNode.authority;
				
				
			}
			if(type==34){//xen数据中心
				if(auth==0 ||auth==1){
				showRm('42:51');
				}else{
					showRm(null);
				}
			}
		
			if(type==null ){
			<% 
			//TbSysUserinfoObj account = (TbSysUserinfoObj)request.getSession().getAttribute(Constant.USER_SESSION_KEY);  
				String user = (String)request.getSession().getAttribute("account"); 
			%>
			var users = '<%=user%>';
				if(users=='admin'){
				showRm('41');
				}else{
				showRm(null);	
				}
				
			}
			if(type==29){//xen集群
				if(auth==0 ||auth==1){
				if(state==1){
					showRm('4:43:54');
				}
				if(state==0){
					showRm('4:5:18:37:54');
				}
				if(state==2){
					//后台接口连接有问题
				}
			 }
			 else{
			 	showRm(null);
			 }
			}
		    if(type==28){//xen模版
				showRm('53:34');
			}
			if(type==26){//xen主机
				if(auth==0 ||auth==1){
				showRm('22:47:48:45:19');
				}else{
					showRm(null);
				}
			}if(type==27){//xen虚拟机
			var operation = treeNode.operation;
				if((auth==0 ||auth==1)&& operation==false){
				if(state==0){//虚拟机开启状态
					showRm('11:14:23:26:32:52');
				}else if(state==1){//虚拟机关闭状态
					showRm('9:10:60:32:33:17:24');
				}else if(state==2){//虚拟机挂起状态
					showRm('9:13');
				}else  if(state==3){
					showRm('100');
				}else{
					showRm('9:10:11:14:17:24:26:32:33');
				}
				}else{
					showRm(null);
				}
			}
			if(type==35){
				if(state=='connection'){
					showRm('55:56:57');
				}else if(state=='detach'){
					showRm('56:58');
				}else if(state=='destroy'){
					showRm('59');
				}else if(state=='localStore'){
					showRm('');
				}
			}
			//如果从鼠标位置到容器下边的空间小于菜单的高度，就定位菜单的上坐标（Top）为当前鼠标位置向上一个菜单高度
			if(document.getElementById("content_wrap_ztree").clientHeight-y<document.getElementById('rMenu').offsetHeight ){
				y=document.body.scrollTop + y - document.getElementById('rMenu').offsetHeight;
			}
			rMenu.css({"top":y+"px", "left":x+"px", "visibility":"visible"});

			$("body").bind("mousedown", onBodyMouseDown);
		}
		
		//隐藏右键菜单
		function hideRMenu() {
			if (rMenu) rMenu.css({"visibility": "hidden"});
			$("#div_virtual").css({"visibility": "hidden"});
			$("#div_snapshot").css({"visibility": "hidden"});
			$("#div_operatingSystem").css({"visibility": "hidden"});
			$("body").unbind("mousedown", onBodyMouseDown);
		}
		function onBodyMouseDown(event){
			if (!(event.target.id == "rMenu" || $(event.target).parents("#rMenu").length>0)) {
				rMenu.css({"visibility" : "hidden"});
			}
			if (!(event.target.id == "div_virtual" || $(event.target).parents("#div_virtual").length>0)) {
				$("#div_virtual").css({"visibility" : "hidden"});
			}
			if (!(event.target.id == "div_snapshot" || $(event.target).parents("#div_snapshot").length>0)) {
				$("#div_snapshot").css({"visibility" : "hidden"});
			}
			if (!(event.target.id == "div_operatingSystem" || $(event.target).parents("#div_operatingSystem").length>0)) {
				$("#div_operatingSystem").css({"visibility" : "hidden"});
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
			if(treeNode.type==34){//数据中心信息
				$("#right_iframe").attr("src","xentree_vmtabs.do?type="+treeNode.type + "&id="+treeNode.id);
				setDivVisProp('iframe');
			}
			if(treeNode.type==27){//xen虚拟机
				$("#right_iframe").attr("src","xentree_vmtabs.do?type="+treeNode.type+"&id="+treeNode.entityId+"&pool_uuid="+treeNode.getParentNode().getParentNode().tree_uuid+"&host_uuid="+treeNode.getParentNode().tree_uuid+"&vm_uuid="+treeNode.tree_uuid);
				setDivVisProp('iframe');
			}if(treeNode.type==26){//xen主机
				$("#right_iframe").attr("src","xentree_vmtabs.do?type="+treeNode.type+"&id="+treeNode.entityId+"&tempId="+treeNode.id+"&pool_uuid="+treeNode.getParentNode().tree_uuid+"&host_uuid="+treeNode.tree_uuid+"&name="+treeNode.name);
				setDivVisProp('iframe');
			}if(treeNode.type==29){//xen集群
				$("#right_iframe").attr("src","xentree_vmtabs.do?type="+treeNode.type+"&id="+treeNode.id+"&pool_uuid="+treeNode.tree_uuid);
				setDivVisProp('iframe');
			}if(treeNode.type==28){//xen模板
				$("#right_iframe").attr("src","xentree_vmtabs.do?type="+treeNode.type+"&id="+treeNode.entityId+"&pool_uuid="+treeNode.getParentNode().getParentNode().tree_uuid+"&host_uuid="+treeNode.getParentNode().tree_uuid);
				setDivVisProp('iframe');
			}if(treeNode.type==35){//xen存储
				$("#right_iframe").attr("src","xentree_vmtabs.do?type="+treeNode.type+"&id="+treeNode.tree_uuid+"&pool_uuid="+treeNode.getParentNode().getParentNode().tree_uuid+"&host_uuid="+treeNode.getParentNode().tree_uuid);
				setDivVisProp('iframe');
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
		var win;
		//增加父节点，数据中心
		function addXenDataCenter(){
		    $.dialog({
				id:'dataCenter',
				title:'数据中心',
				resize:false,
				width: '430px',
				height: '250px',
				lock:true,
				content: 'url:yicloud/xen/addDataCenter.jsp'});
			hideRMenu();
		}

		//添加数据中心
		function addDataCenter(name){
			$.getJSON('xen_saveDataCenter.do?name=' + encodeURI(encodeURI(name)),{'time':new Date().toString()}, function(data){
				if(data.result ==1){
					zTree.reAsyncChildNodes(null, "refresh",false);
				}else{
					alert("添加数据中心失败");
				}
			});
		}
		
		//节点重命名
		function rename() {
			var nodes = rightSNode;
			if (nodes.length == 0) {
				alert("请先选择一个节点");
				return;
			}
			zTree.editName(nodes);
			hideRMenu();
		}
		
		function onRename(event, treeId, treeNode) {
			$.getJSON("xentree_updateNodeName.do?id=" + treeNode.id + "&type=" + treeNode.type + "&name=" + encodeURI(encodeURI(treeNode.name)), {"time":new Date().toString()}, function (data) {
				var result = data.result;
				if (result == null) {
					alert("重命名成功");
				} else {
					alert("操作失败");
				}
			});
		}
		//创建虚拟机鼠标悬停事件的处理
		var doMouseDownTimmer = null;
		var isMouseDownDoing = false;
		function onmouseOverVir(){
			isMouseDownDoing = false;
		    doMouseDownTimmer = setTimeout(doSomething,300);
		}
		function onmouseoutVir(){
			 if (!isMouseDownDoing){
        		clearTimeout(doMouseDownTimmer); 
    			}
		}
		function doSomething(){
			isMouseDownDoing = true;

			$("#fromModel").show();
			$("#creatBareMachine").show();
			$("#cloneVirtualMechine").show();
			
			var odiv=document.getElementById('rMenu');
			positionX = odiv.getBoundingClientRect().right;
			positionY = odiv.getBoundingClientRect().top+67;
			$("#div_virtual").css({"top":positionY+"px", "left":positionX +"px", "visibility":"visible"});
		}
		//快照鼠标悬停事件的处理
		function onmouseOverSnapshot(){
			isMouseDownDoing = false;
		    doMouseDownTimmer = setTimeout(executeMethod,300);
		}
		function onmouseOutSnapshot(){
			 if (!isMouseDownDoing){
        		clearTimeout(doMouseDownTimmer); 
    			}
		}
		
		function executeMethod(){
			isMouseDownDoing = true;
			$("#executeSnapshot").show();
			$("#snapshotManager").show();
			
			var odiv=document.getElementById('rMenu');
			positionX = odiv.getBoundingClientRect().right;
			//positionY = odiv.getBoundingClientRect().top+287;
			positionY = document.getElementById("snapshot").getBoundingClientRect().top;
			$("#div_snapshot").css({"top":positionY+"px", "left":positionX +"px","visibility":"visible"});
		}

		//转化为模板的悬停事件的处理
		$(function(){
		$("#toTemplet").click(function(){
			cloneToTem();
			});
	    });
		//克隆虚拟机的悬停事件的处理
		$(function(){
		$("#clone").click(function(){
			virtual_clone();
			});
	    });
		$(function(){
			$("#createVMByTem").click(function(){
				create_virtual_clone_by_tem();
				});
		    
		  	//为右键菜单增加鼠标划过效果
			$("#rMenu > ul > li").mouseenter(function(){
				  $(this).css("background-color","#C1CDCD");
			});

			$("#div_snapshot > ul > li").mouseenter(function(){
				  $(this).css("background-color","#C1CDCD");
			});

			$("#rMenu > ul > li").mouseleave(function(){
				  $(this).css("background-color","white");
			});

			$("#div_snapshot > ul > li").mouseleave(function(){
				  $(this).css("background-color","white");
			});
		});
	</script>
</head>
<body>
	<s:form action="yvm_listVMService" method="post" id="theForm" cssStyle="theForm">
		<s:hidden name="theForm.VH_HOST" id="VH_HOST"></s:hidden>
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
					<iframe id="right_iframe"  src="xentree_vmtabs.do?type=34&id=<s:property value="theForm.firstDC_Id"/>" name="xxxx" height="100%" width="77%" frameborder="0" style="position:fixed;left:259px;right:0px;top:0px;bottom:0px;overflow-x:auto;"></iframe>
				</div>
			</div>
		</div>
		<div id="clusterMenu">
			<ul>
			</ul>
		</div>
		<div id="rMenu">
			<ul>
<%--				<li id="addDataCenter" onclick="addDataCenter();" class="hide">--%>
<%--					添加数据中心--%>
<%--				</li>--%>
				<li id="addXenDataCenter" onclick="addXenDataCenter();" class="hide">
					添加数据中心
				</li>
				<li id="conXenCluster" onclick="conXenCluster();" class="hide">
					添加主机
				</li>
				<li id="connectCluster" onclick="connectCluster();" class="hide">
					连接
				</li>
				
<%--				<li id="delDataCenter" onclick="delDataCenter();" class="hide">--%>
<%--					删除数据中心--%>
<%--				</li>--%>
				<li id="delXenDataCenter" onclick="delXenDataCenter();" class="hide">
					移除
				</li>
				<li id="conDataCenter" onclick="conDataCenter();" class="hide">
					配置数据中心
				</li>
				<li id="addCluster" onclick="addCluster();" class="hide">
					添加集群
				</li>
<%--				<li id="delCluster" onclick="delCluster();" class="hide">--%>
<%--					删除集群--%>
<%--				</li>--%>
<%--				<li id="conCluster" onclick="conCluster();" class="hide" >--%>
<%--					修改集群配置--%>
<%--				</li>--%>
				<li id="host_add" onclick="host_add1();" class="hide">
					添加主机
				</li>
				<li id="host_conn" onclick="virtual_conn();" class="hide">
					连接主机
				</li>
				<li id="host_monitor" onclick="host_monitor();" class="hide">
					监控主机
				</li>
				<li id="add_nake" class="hide" onclick="virtual_add();">
					新建虚拟机
				</li>
				<li id="add_sr" onclick="addNFS();" class="hide">
					新建 SR
				</li>
				<li id="virtual_add" onclick="virtual_add();" class="hide">
					部署虚拟机
				</li>
				
				<li id="virtual_start" onclick="put_virtual_state('create');" class="hide">
					启动  
				</li>
				<li id="move" class="hide"  onclick="virtual_move()">
					在服务器上启动
				</li>
				<li id="virtual_stop" onclick="put_virtual_state('shutdown');" class="hide">
					关闭  
				</li>
<%--				<li id="virtual_pause" onclick="put_virtual_state('suspend');" class="hide">--%>
<%--					暂停--%>
<%--				</li>--%>
				<li id="virtual_resume" onclick="put_virtual_state('resume');" class="hide">
					恢复
				</li>
				<li id="suspend" onclick="put_virtual_state('suspend');" class="hide">
					挂起  
				</li>
<%--				<li id="virtual_reboot" onclick="put_virtual_state('reboot');" class="hide">--%>
<%--					重新启动--%>
<%--				</li>--%>
				<li id="virtual_console" onclick="virtual_console('console');" class="hide">
					打开控制台  
				</li>
				<li id="virtual_view" onclick="virtual_view('0');" class="hide">
					查看虚拟机
				</li>
				<li id="virtual_setting" onclick="virtual_edit('1');" class="hide">
					编辑设置
				</li>
				<li id="virtual_del" onclick="put_virtual_state('destroy');" class="hide">
					从磁盘删除  
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
				<li id="migrate" class="hide"  onclick="virtual_migrate()">
					迁移
				</li>
				<li id="clone" class="hide">
					复制
				</li>
				<li id="createVMByTem" class="hide">
					新建虚拟机
				</li>
				<!-- 在主机节点上通过模板来克隆虚拟机 -->
				<li id="createVMOnHost" class="hide" onclick="cloneVMByTemOnHost();">
					克隆虚拟机
				</li>
				<li id="templet" onclick="virtual_clone();" class="hide">
					从模板部署
				</li>
				<li id="host_reboot" onclick="host_reboot();" class="hide">
					重新引导
				</li>
				<li id="host_shutdown" onclick="host_shutdown();" class="hide">
					关机
				</li>
				<li id="rename" onclick="rename();" class="hide">
					重命名
				</li>
				<li id="xendata" onclick="xenDataCompare();" class="hide">
					更新池数据
				</li>
				<li id="host_del" onclick="delTreeNode();" class="hide">
					移除
				</li>
				<li id="login" onclick="login();" class="hide">
					登录
				</li>
				<li id="snapshot" class="hide" onmouseover="onmouseOverSnapshot()" onclick="onmouseOverSnapshot()" onmouseout="onmouseOutSnapshot()">
					快照<font style="font-size:15px;vertical-align:center;position:absolute;right:5px;color:#AAAAAA;"> ▶</font>
				</li>
				<li id="toTemplet" class="hide">
					复制为模板
				</li>
				<li id="removeTem" onclick="templet_remove();" class="hide">
					移除
				</li>
				<li id=enterMaintenanceMode onclick="enterMaintenanceMode();" class="hide">
					进入维护模式
				</li>
				<li id=exitMaintenanceMode onclick="exitMaintenanceMode();" class="hide">
					退出维护模式
				</li>
				<li id="moveToDataCenter" onclick="moveToDateCenter();" class="hide">
					移到数据中心
				</li>
<%--				集群上面的创建虚拟机--%>
<%--				<li id="creatVirtualMachine" onclick="creatVirtualMachine();" class="hide">--%>
<%--					创建虚拟机--%>
<%--				</li>--%>
				<li id="deleteData" onclick="deleteData();" class="hide">
					删除数据
				</li>
				<li id="wakeup" onclick="wakeup();" class="hide">
					唤醒主机
				</li>
				<li id="operatingSystem" class="hide" onmouseover="onmouseOverOperatingSystem()" onmouseout="onmouseOutOperatingSystem()">
					操作系统
				</li>
				<li	id="synchroData" onclick="synchroData();" class="hide">
					同步数据
				</li>
				<li	id="addnic" onclick="addnic();" class="hide">
					添加虚拟网卡
				</li>
				<li	id="operting" class="hide">
					操作中...
				</li>
				<li	id="detachStore" onclick="changeStoreState('1');" class="hide">
					分离
				</li>
				<li	id="forgetStore" onclick="changeStoreState('2');" class="hide">
					忘记
				</li>
				<li	id="destroyStore" onclick="changeStoreState('3');" class="hide">
					销毁
				</li>
				<li	id="reConnect" onclick="reConnectSrPage();" class="hide">
					重新连接
				</li>
				<li	id="fixStore" onclick="fixStore();" class="hide">
					修复
				</li>
				<li	id="export" onclick="export();" class="hide">
					导出
				</li>
			</ul>
		</div>
		<div id="div_virtual">
			<ul>
				<li id="fromModel"  class="hide" style="top:20px" onclick="virtual_add();">
					从模板
				</li>
				<li id="creatBareMachine"  class="hide" onclick="virtual_add();" >
					创建裸机
				</li>
				<li id="cloneVirtualMechine" class="hide">
					克隆新虚拟机
				</li>
			</ul>
		</div>
		<div id="div_snapshot">
			<ul>
				<li id="executeSnapshot" onclick="executeSnapshot()" class="hide" >
					执行快照
				</li>
				<li id="snapshotManager" onclick="snapshotManager()" class="hide">
					快照管理器
				</li>
			</ul>
		</div>
	</s:form>
</body>
