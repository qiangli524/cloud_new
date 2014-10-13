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
	var basePath  = '<%=request.getScheme()%>' +"://" + '<%=request.getServerName()%>' +":"+ '<%=request.getServerPort()%>' + '<%=request.getContextPath()%>' +"/";
	
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
				onDblClick: zTreeOnDblClick,
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
				url:"tree_asyncForTree.do",
				autoParam:["id","type","entityId", "name=n", "level=lv"],
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
				//	expandNodes(treeNode.children);
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
			}else if(treeNode.type==8 || treeNode.type==6 || treeNode.type==3 || treeNode.type==1 || treeNode.type==0 ||treeNode.type==22||treeNode.type==23 || treeNode.type==24 ||treeNode.type==21 || treeNode.type==25|| treeNode.type==29 || treeNode.type==26 || treeNode.type==27 || treeNode.type==28){
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
				zTree.reAsyncChildNodes(nodes.getParentNode(), "refresh",true);				
			}
		}
		function showRMenu(treeNode, x, y) {
			$("#rMenu ul").show();
			if(treeNode){
				var type = treeNode.type;
				var state = treeNode.state;
				var auth = treeNode.authority;
			}
			if(type==null){
				<% 	
				//TbSysUserinfoObj account = (TbSysUserinfoObj)request.getSession().getAttribute(Constant.USER_SESSION_KEY);  
				String user = (String)request.getSession().getAttribute("account"); 
				%>
			var users = '<%=user%>';
				if(users=='admin'){
				showRm('0:46');
				}else{
				showRm('');	
				}
			}
			if(type==8){//数据中心
				if(auth==0 ||auth==1){
					showRm('1:2:3');
				}else{
					showRm('');
				}
			}
			
			if(type==3 ){//集群（kvm和vmware）
				if(auth==0 ||auth==1){
				showRm('4:5:18:37');
				}else{
					showRm('');
				}
			}
			
			if(type==1){//vmware主机
				if(auth==0){	
					if(state == 0){ //主机进入维护模式
					showRm('22:35:38:45:48');
				}else if(state == 1){ //主机退出维护模式
					showRm('22:35:36:45:48');
				}else if(state==2){
					showRm('50');
				}
			}else if(auth==1){
				if(state == 0){ //主机进入维护模式
					showRm('22:35:38:45:48');
				}else if(state == 1){ //主机退出维护模式
					showRm('22:35:36:45:48');
				}else if(state==2){
					showRm('50');
				}
			}else{
				showRm('');
			}
				
			}
			if(type==0){//vmware虚拟机
				if(auth==1 || auth==0){
					if(state==0){
						showRm('11:14:17:23:24:26:32:33:39:44:49');
					}else if(state==1){
						showRm('9:10:17:23:24:32:33:49');
					}else if (state==2){
						showRm('9:10:17:23:24:32:33:49');
					}else {
						showRm('');
					}
				}else{
					showRm('');
				}
				/*
					if(state==0){
						showRm('11:14:17:23:24:26:32:33:39:44:49');
					}else if(state==1){
						showRm('9:10:17:23:24:32:33:49');
					}else if (state==2){
						showRm('9:10:17:23:24:32:33:49');
					}
				
				if(auth==2){
					
				}
				)*/
			}
			if(type==25){//kvm主机
				showRm('8:19:21:27');
			}
			if(type==22){//kvm镜像
				showRm('6:27');
			}
			if(type==23){//vmvarw镜像
				showRm('24:34');
			}
			if(type==24){//KVM虚拟机
				showRm('7:9:10:11:12:13:14:15:16:17:20:27');
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
			hideRMenu();
		}
		
		
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
		var win;

		var basePath  = '<%=request.getScheme()%>' +"://" + '<%=request.getServerName()%>' +":" 
		+ '<%=request.getServerPort()%>' + '<%=request.getContextPath()%>' +"/";

		//增加父节点，数据中心
		function addDataCenter(){
			var dialog = $.dialog({
				id:'datacenter',
				title:'添加数据中心',
				height:'120px',
				content:'url:'+basePath+'yicloud/dataCenter/addDataCenter.jsp',
				button:[{name: '确定', callback: function () {
					var name = this.content.document.getElementById("NAME").value;
					if($.trim(name) == ""){
						alert("数据中心名称不能为空");
						return false;
					}
					$.getJSON('datacenter_saveDataCenter.do?name=' + encodeURI(encodeURI(name)),{'time':new Date().toString()}, function(data){
						if(data.result ==1){
							alert("添加数据中心成功");
			 			//   window.returnValue = theForm.NAME.value;
		    				receiveVallue(name);
						}else{
							alert("添加数据中心失败");
						}
					});
				}}, {name: '取消'}]
			});
			//win = window.open ('yicloud/dataCenter/addDataCenter.jsp','newwindow','height=200,width=400,top=250,left=500,toolbar=no,menubar=no,scrollbars=no, resizable=no,location=no, status=no') ;
			hideRMenu();
		}
		
		function receiveVallue(value){
			//var newNode = {name:value,icon:"sxcloud/images/virtual/Datacenter.png"};
			//newNode = zTree.addNodes(null, newNode);
			//win.close();
			zTree.reAsyncChildNodes(null, "refresh",false);
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
			$.getJSON("tree_updateNodeName.do?id=" + treeNode.id + "&type=" + treeNode.type + "&name=" + encodeURI(encodeURI(treeNode.name)), {"time":new Date().toString()}, function (data) {
				var result = data.result;
				if (result == null) {
					alert("重命名成功");
				} else {
					alert("操作失败");
				}
			});
		hideRMenu();
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
			$("#div_snapshot").css({"top":positionY+"px", "left":positionX +"px", "visibility":"visible"});
			$("#div_templet").css({"visibility": "hidden"});
			$("#div_clone").css({"visibility": "hidden"});
			$("#div_operatingSystem").css({"visibility": "hidden"});
		}
		//转化为模板的悬停事件的处理
		function onmouseOverTemplet(){
			isMouseDownDoing = false;
		    doMouseDownTimmer = setTimeout(executeTempletMethod,300);
		}
		function onmouseOutTemplet(){
			 if (!isMouseDownDoing){
        		clearTimeout(doMouseDownTimmer); 
    			}
		}
		function executeTempletMethod(){
			isMouseDownDoing = true;
			$("#cloneToTem").show();
			$("#virtualToTem").show();
			
			var odiv=document.getElementById('rMenu');
			positionX = odiv.getBoundingClientRect().right;
			//positionY = odiv.getBoundingClientRect().top+307;
			positionY = document.getElementById('toTemplet').getBoundingClientRect().top;
			$("#div_templet").css({"top":positionY+"px", "left":positionX +"px", "visibility":"visible"});
			$("#div_snapshot").css({"visibility": "hidden"});
			$("#div_operatingSystem").css({"visibility": "hidden"});
			$("#div_clone").css({"visibility": "hidden"});
		}
		
		//克隆虚拟机的悬停事件的处理
		function onmouseOverClone(){
			isMouseDownDoing = false;
		    doMouseDownTimmer = setTimeout(executeCloneMethod,300);
		}
		function onmouseOutClone(){
			 if (!isMouseDownDoing){
        		clearTimeout(doMouseDownTimmer); 
    			}
		}
		function executeCloneMethod(){
			isMouseDownDoing = true;
			$("#cloneToHost").show();
			$("#cloneToCluster").show();
			
			var odiv=document.getElementById('rMenu');
			positionX = odiv.getBoundingClientRect().right;
			positionY = document.getElementById('clone').getBoundingClientRect().top;
			$("#div_clone").css({"top":positionY+"px", "left":positionX +"px", "visibility":"visible"});
			$("#div_snapshot").css({"visibility": "hidden"});
			$("#div_templet").css({"visibility": "hidden"});
			$("#div_operatingSystem").css({"visibility": "hidden"});
		}
		
		//操作系统的悬停事件处理
		function onmouseOverOperatingSystem(){
			isMouseDownDoing = false;
		    doMouseDownTimmer = setTimeout(executeOperatingSystemMethod,300);
		}
		function onmouseOutOperatingSystem(){
			 if (!isMouseDownDoing){
        		clearTimeout(doMouseDownTimmer); 
    			}
		}
		function executeOperatingSystemMethod(){
			isMouseDownDoing = true;
			$("#restartSysttem").show();
			$("#closeSystem").show();
			
			var odiv=document.getElementById('rMenu');
			positionX = odiv.getBoundingClientRect().right;
			positionY = document.getElementById('operatingSystem').getBoundingClientRect().top;
			$("#div_operatingSystem").css({"top":positionY+"px", "left":positionX +"px", "visibility":"visible"});
			$("#div_snapshot").css({"visibility": "hidden"});
			$("#div_templet").css({"visibility": "hidden"});
			$("#div_clone").css({"visibility": "hidden"});
		}
		$(function(){
			$("#search").click(function(){
				var input = $.trim($("#inputSearch").val());
				if(input.length == 0){
					alert("输入的值为空!");
					return false;
				}
				var url = "tree_searchNodes.do?param=" + input;
				$.getJSON(url,{'time':new Date().toString()},function(data){
					searchSuccess(data);
					var nodes = zTree.getNodesByFilter(find,false,false,data);
				});
				return false;
			});
		});
		function find(node,data) {
		}
		function searchSuccess(data) {
			var nodes = zTree.getNodes();
			expandSearchNodes(nodes);
		}
		function expandSearchNodes(nodes) {
			for (var i=0, l=nodes.length; i<l; i++) {
				if(nodes[i].isParent){
					zTree.Node(nodes[i], true, false, false);
					if (nodes[i].isParent && nodes[i].zAsync) {
						SearchNodes(nodes[i].children);
					}
				}
			}
		}
		$(function(){
		  	//为右键菜单增加鼠标划过效果
			$(".complexmenu > ul > li").mouseenter(function(){
				  $(this).css("background-color","#C1CDCD");
			});
			$(".complexmenu > ul > li").mouseleave(function(){
				  $(this).css("background-color","white");
			});
		});
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
		<div id="clusterMenu">
			<ul>
			</ul>
		</div>
		<div id="rMenu" class="complexmenu">
			<ul>
				<li id="addDataCenter" onclick="addDataCenter();" class="hide">
					添加数据中心
				</li>
				
				<li id="delDataCenter" onclick="delDataCenter();" class="hide">
					移除
				</li>
				
				<!--  
				<li id="conDataCenter" onclick="conDataCenter();" class="hide">
					配置数据中心
				</li>
				-->
				<li id="addCluster" onclick="addCluster();" class="hide">
					新建群集
				</li>
				<li id="delCluster" onclick="delCluster();" class="hide">
					移除
				</li>
				<li id="conCluster" onclick="conCluster();" class="hide" >
					编辑设置
				</li>
				<li id="host_add" onclick="host_add();" class="hide">
					添加主机
				</li>
				<li id="host_del" onclick="delTreeNode();" class="hide">
					移除
				</li>
				<li id="host_conn" onclick="virtual_conn();" class="hide">
					连接主机
				</li>
				<li id="host_monitor" onclick="host_monitor();" class="hide">
					监控主机
				</li>
				<li id="virtual_add" onclick="virtual_add();" class="hide">
					部署
				</li>
				<li id="virtual_del" onclick="put_virtual_state('destroy');" class="hide">
					从磁盘删除
				</li>
				<li id="virtual_start" onclick="put_virtual_state('create');" class="hide">
					启动
				</li>
				<li id="virtual_stop" onclick="put_virtual_state('shutdown');" class="hide" >
					关闭
				</li>
				<li id="virtual_pause" onclick="put_virtual_state('suspend');" class="hide">
					暂停
				</li>
				<li id="virtual_resume" onclick="put_virtual_state('resume');" class="hide">
					恢复
				</li>
				<li id="suspend" onclick="put_virtual_state('suspend');" class="hide">
					挂起
				</li>
				<li id="virtual_reboot" onclick="put_virtual_state('reboot');" class="hide">
					重启
				</li>
				<li id="virtual_view" onclick="virtual_view('0');" class="hide">
					查看
				</li>
				<li id="virtual_setting" onclick="virtual_view('1');" class="hide">
					编辑设置
				</li>
				<li id="virtual_monitor" onclick="virtual_monitor();" class="hide">
					监控
				</li>
				<li id="virtual_conn" onclick="virtual_conn();" class="hide">
					连接
				</li>
				<li id="app_deploy" onclick="app_deploy('1');" class="hide">
					应用部署
				</li>
				<%-- 
				<li id="add_nake" class="hide" onmouseover="onmouseOverVir()" onmouseout="onmouseoutVir()" onclick="virtual_add();">
					创建虚拟机
				</li>
				--%>
				<li id="add_nake" class="hide" onclick="virtual_add();">
					新建虚拟机
				</li>
				<%-- 在主机节点使用 --%>
				<li	id="clone_vm" onclick="cloneVMByTem();" class="hide">
					克隆虚拟机
				</li>
				<li id="remove" onclick="virtual_migrate();" class="hide">
					迁移
				</li>
				<li id="clone" class="hide" onmouseover="onmouseOverClone()" onmouseout="onmouseOutClone()">
					克隆<font style="font-size:15px;vertical-align:center;position:absolute;right:5px;color:#AAAAAA;"> ▶</font>
				</li>
				<li id="templet" onclick="virtual_clone();" class="hide">
					从模板部署
				</li>
				<li id="rename" onclick="rename();" class="hide">
					重命名
				</li>
				<li id="login" onclick="login();" class="hide">
					登录
				</li>
				<li id="snapshot" class="hide" onmouseover="onmouseOverSnapshot()" onmouseout="onmouseOutSnapshot()">
					快照<font style="font-size:15px;vertical-align:center;position:absolute;right:5px;color:#AAAAAA;"> ▶</font>
				</li>
				<li id="toTemplet" class="hide" onmouseover="onmouseOverTemplet()" onmouseout="onmouseOutTemplet()">
					模板      <font style="font-size:15px;vertical-align:center;position:absolute;right:5px;color:#AAAAAA;"> ▶</font>
				</li>
				<li id="removeTem" onclick="templet_remove();" class="hide">
					从磁盘删除
				</li>
				<li id="host_reboot" onclick="host_reboot();" class="hide">
					重新引导
				</li>
				<li id="host_shutdown" onclick="host_shutdown();" class="hide">
					关机
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
				<li id="creatVirtualMachine" onclick="creatVirtualMachine();" class="hide">
					新建虚拟机
				</li>
				<li id="console" onclick="console();" class="hide">
					打开控制台
				</li>
				<li id="deleteData" onclick="deleteData();" class="hide">
					删除数据
				</li>
				<li id="wakeup" onclick="wakeup();" class="hide">
					唤醒主机
				</li>
				<li id="operatingSystem" class="hide" onmouseover="onmouseOverOperatingSystem()" onmouseout="onmouseOutOperatingSystem()">
					操作系统	<font style="font-size:15px;vertical-align:center;position:absolute;right:5px;color:#AAAAAA;"> ▶</font>
				</li>
				<li	id="synchroData" onclick="synchroData();" class="hide">
					同步数据
				</li>
				<li	id="addnic" onclick="addnic();" class="hide">
					添加网卡
				</li>
			</ul>
		</div>
		<div id="div_templet" class="complexmenu">
			<ul>
				<li id="cloneToTem" class="hide" onclick="cloneToTem();">
					克隆为模板
				</li>
				<li id="virtualToTem" class="hide" onclick="virtualToTem();">
					转化成模板
				</li>
			</ul>
		</div>
		<div id="div_virtual" class="complexmenu">
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
		<div id="div_snapshot" class="complexmenu">
			<ul>
				<li id="executeSnapshot" onclick="executeSnapshot()" class="hide" >
					执行快照
				</li>
				<li id="snapshotManager" onclick="snapshotManager()" class="hide">
					快照管理器
				</li>
			</ul>
		</div>
		<div id="div_clone" class="complexmenu" >
			<ul>
				<li id="cloneToHost" class="hide" onclick="virtual_clone();">
					克隆到宿主机
				</li>
				<li id="cloneToCluster" class="hide" onclick="cloneToCluster();">
					克隆到集群
				</li>
			</ul>
		</div>
		<div id="div_operatingSystem" class="complexmenu">
			<ul>
				<li id="restartSysttem" class="hide" onclick="restartSysttem();">
					重启操作系统
				</li>
				<li id="closeSystem" class="hide" onclick="closeSystem();">
					关闭操作系统
				</li>
			</ul>
		</div>
	</s:form>
</body>
