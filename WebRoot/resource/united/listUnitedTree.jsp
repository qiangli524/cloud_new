<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../sxcloud/common/taglib.jsp"%>
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />
<head>
<title></title>
<link rel="stylesheet" href="sxcloud/cresources/ztree/zTree.css" type="text/css" />
<link rel="stylesheet" href="sxcloud/cresources/ztree/zTreeStyle/zTreeStyle.css" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript"src="sxcloud/cjs/ztree/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript" src="sxcloud/cjs/ztree/jquery.ztree.excheck-3.5.js"></script>
<script type="text/javascript" src="sxcloud/cjs/ztree/jquery.ztree.exedit-3.5.js"></script>
<script type="text/javascript" src="yicloud/busisystree/js/rMenu.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/home/ui/njs/ui/ued_ui.js"></script>
<script type="text/javascript" src="sxcloud/js/uuid.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resource/united/js/rMenu.js?d1"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resource/united/js/unitedTree.js?e1"></script>

<style type="text/css">
div#rMenu {
	position: absolute;
	visibility: hidden;
	background-color: #9dbcd9;
	text-align: left;
	padding: 0 1px 1px 1px;
}

div#rMenu ul li {
	background-color: white;
    cursor: pointer;
    list-style: none outside none;
    margin: 1px 0 0 1px;
    padding: 2px 5px 2px 12px;
    width: 100px;
}
div#div_snapshot {
	position: absolute;
	visibility: hidden;
	background-color: #9dbcd9;
	text-align: left;
	padding: 1px;
}
div#div_snapshot ul li {
	background-color: white;
    cursor: pointer;
    list-style: none outside none;
    margin: 1px 0 0 1px;
    padding: 2px 5px 2px 12px;
}

div#rMenu ul li:hover {
    background-color: #dfecf9;
}
<%--下拉框--%>
<!--
body {
	background: #fff
}

.Menu {
	position: relative;
	width: 210px;
	height:550px;
	z-index: 1;
	background: #FFF;
	border: 1px solid #000;
	margin-top: -10px;
	display: none;
	overflow-y:auto;
}

.Menu2 {
	position: absolute;
	left: 0;
	top: 0;
	width: 100%;
	height: auto;
	overflow: hidden;
	z-index: 1;
}

.Menu2 ul {
	margin: 0;
	padding: 0
}

.Menu2 ul li {
	width: 100%;
	height: 25px;
	line-height: 25px;
	text-indent: 15px;
	border-bottom: 1px dashed #ccc;
	color: #0A0A0A;
	cursor: pointer;
	change: expression(
		this.onmouseover = function(){ 
    		this.style.background="#F2F5EF";
		}
		,
		this.onmouseout =function(){
			this.style.background="";
		}
	)
}
input {
	width: 200px
}

.form {
	width: 200px;
	height: auto;
}

.form div {
	position: relative;
	top: 0;
	left: 0;
	margin-bottom: 5px
}

#List1,#List2,#List3 {
	left: 0px;
	top: 0px;
}
-->
<%--下拉框--%>
</style>
<script type="text/javascript">
	//下拉框
	function showAndHide(obj, types) {
		var Layer = window.document.getElementById(obj);
		switch (types) {
		case "show":
			Layer.style.display = "block";
			break;
		case "hide":
			Layer.style.display = "none";
			break;
		}
	}
	function getValue(obj, str) {
		var input = window.document.getElementById(obj);
		input.value = str;
	}
	//右键选择节点
	var rightSNode;
	var setting = {
		view : {
			dblClickExpand : false,
			selectedMulti : false,
			fontCss: getFontCss
		},
		callback : {
			onRightClick : OnRightClick,
			onClick : onClick,
			beforeExpand : zTreeBeforeExpand,
			onAsyncSuccess: onAsyncSuccess,
			beforeRename : zTreeBeforeRename
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
			dataType: "json",
			url : "united_asyncForTree.do",
			autoParam : [ "id","connect_id","uuid","type","vtype"]
		}
	};
	
	function onClick(event, treeId, treeNode, clickFlag) {
		if (treeNode.type == 0) {//安徽移动私有云管理平台
			$("#right_iframe").attr(
					"src",
					"united_tabs.do?type=" + treeNode.type + "&id="
							+ treeNode.id);
			setDivVisProp('iframe');
		} else if (treeNode.type == -1) {
			$("#right_iframe").attr(
					"src",
					"united_tabs.do?type=" + treeNode.type + "&id="
							+ treeNode.id);
			setDivVisProp('iframe');
		} else if (treeNode.type == 1) {//数据中心
			$("#right_iframe").attr(
					"src",
					"united_tabs.do?type=" + treeNode.type + "&id="
							+ treeNode.id+"&entityId="+treeNode.entityId+"&vtype="+treeNode.vtype + "&connect_id=" + treeNode.connect_id);
			setDivVisProp('iframe');
		} else if (treeNode.type == 2) { //集群
			$("#right_iframe").attr(
					"src",
					"united_tabs.do?uuid=" + treeNode.uuid + "&connect_id=" + treeNode.connect_id +"&monitor_id="+treeNode.connect_id+"_"+treeNode.uuid+"&type=" + treeNode.type+"&vtype="+treeNode.vtype+"&id="+treeNode.id);
			setDivVisProp('iframe');
		} else if (treeNode.type == 3) { //主机
			$("#right_iframe").attr(
					"src",
					"united_tabs.do?type=" + treeNode.type + "&uuid="+treeNode.uuid+"&connect_id="+treeNode.connect_id+"&monitor_id="+treeNode.connect_id+"_"+treeNode.uuid+"&vtype="+treeNode.vtype+"&id="+treeNode.id);
			setDivVisProp('iframe');
		} else if (treeNode.type == 4) { //虚拟机
			$("#right_iframe").attr(
					"src",
					"united_tabs.do?type=" + treeNode.type + "&uuid="+treeNode.uuid+"&connect_id="+treeNode.connect_id+"&monitor_id="+treeNode.connect_id+"_"+treeNode.uuid+"&vtype="+treeNode.vtype+"&parent_uuid="+treeNode.getParentNode().uuid);
			setDivVisProp('iframe');
		}else if (treeNode.type == 6) {//网络域
			
			$("#right_iframe").attr(
					"src",
					"united_tabs.do?type=" + treeNode.type + "&id="
							+ treeNode.id+"&vtype="+treeNode.vtype);
			setDivVisProp('iframe');
			
		}else if (treeNode.type == 7) {//子网络域
			$("#right_iframe").attr(
					"src",
					"united_tabs.do?type=" + treeNode.type + "&id="
							+ treeNode.id+"&vtype="+treeNode.vtype);
			setDivVisProp('iframe');
		}else if (treeNode.type == 8) {//vlan
			$("#right_iframe").attr(
					"src",
					"united_tabs.do?type=" + treeNode.type + "&id="
							+ treeNode.id+"&vtype="+treeNode.vtype);
			setDivVisProp('iframe');
		}else if (treeNode.type == 9) {//存储设备
				 
			$("#right_iframe").attr(
					"src",
					"united_tabs.do?type=" + treeNode.type + "&id="
							+ treeNode.id+"&vtype="+treeNode.vtype+"&uuid="+treeNode.uuid);
			setDivVisProp('iframe');
		}
		hideRMenu();
	}
	function OnRightClick(event, treeId, treeNode) {
		rightSNode = treeNode;
		var type;
		if (rightSNode == null) {
			type = null;
		} else {
			zTree.selectNode(rightSNode);
			type = treeNode.type;
		}
		showRMenu(treeNode, event.clientX, event.clientY);
	}
	//显示右键菜单
	function showRMenu(treeNode, x, y) {
		$("#rMenu ul").show();
		if(treeNode == null){
			return false;
		}
		var type = treeNode.type;
		var vtype=treeNode.vtype;
		var state = treeNode.state;
		var oper = treeNode.oper;
		if (type == null) {
			showRm("");
		} else if (type == 0) {//安徽移动云管理平台
			showRm('19:0');
		} else if(type==-1){//地域节点
			showRm('0');
		}else if (type == 1 && (vtype==1)) {//数据中心
			showRm('2:3:48');
		}else if(type == 1 && vtype==2){
			showRm('2:3:45:48');
		}else if (type == 2 && (vtype==1||vtype==2)) {//集群
			showRm('4:5:48:57');
		} else if (type == 3) {//主机
			//	alert(type+"_"+vtype+"_"+state+"_"+oper);
			//此处type==3的判断，影响了后续的操作；不明此处的业务判断，顾将后续操作提前
			if(vtype==7 && type==3){//空闲资源池 主机节点
				//showRm('44:8');
				showRm('44');
			}else if(vtype==8 && type==3){//非虚拟化资源池 主机节点
				showRm('40:64');
			}else{
				if(state==0){//断开链接
					showRm("");
				}else{//已链接
					if(oper==0 && vtype==1){//具备全部权限和操作权限
						showRm('6:7:8:21:56:53:54:58:59:60');
					}else if(oper==0 && vtype==2){//具备全部权限和操作权限
						showRm('6:7:8:21:51:55:56:59:60');
					}else if(oper==1){///具备操作权限
						showRm("");
					}else if(oper==0 && vtype==7){ //具备移除主机权限
						showRm('8');
					}else{//只具备查看权限
						showRm("");
					}
				}
			}
		}else if(type==4 && (vtype==1||vtype==2)){//虚拟机
			showRm('9:10:11:12:14:15:16:17:18:48');
			if(state==1){//关机状态
				if(oper==0 ){//全部权限
					showRm('9:14:15:16:17:20:46:48');
				}else if(oper==1){//具备操作权限
					showRm("9:14:17:18:48");
				}else{///只具备查看权限
					showRm("");
				}
			}else if(state==2){//开机状态
				if(oper==0 ){///具备全部权限
					showRm('10:11:13:62:63:14:15:16:17:18:46:48');
				}else if(oper==1){///具备操作权限
					showRm("10:14:17:18:48");						
				}else{
					showRm("");
				}
				
			}else if(state==3){//挂起状态
				if(oper==0 ||oper==1){
					showRm('12:14:15:16:17:48');
				}else{
					showRm("");
				}
			}else{
				showRm("");
			}
		}else if(vtype==5 && type==1){//网络数据中心
			showRm('32:23');
		}else if(vtype==5 && type==6){//网络域
			showRm('28:24');
		}else if(vtype==5 && type==7){//子网络域
			showRm('29:25');
		}else if(vtype==5 && type==8){
			showRm('42:30');
		}else if(vtype==6 && type==1){//存储数据中心
			showRm('35:26');
		}else if(vtype==6 && type==9){//存储设备
			showRm('43:31');
		}else if(vtype==7 && type==1){//未分配资源数据中心
			//showRm('33:3'); 未分配资源数据中心 更名为 空闲资源池，在空闲资源池中没有集群，直接存放主机			
			showRm('33:39');
		}else if(vtype==7 && type==2){//集群
			showRm('34:5');
		}else if(vtype==7 && type==3){//主机
			showRm('44:8');
		}else if(vtype==8 && type==1){//非虚拟化资源数据中心
			showRm('36:37');
		}else if(vtype==8 && type==2){//集群
			//showRm('38:39');
			showRm('38:5');
		}else if(vtype==8 && type==3){//主机
			showRm('40:41');
		}
		//如果从鼠标位置到容器下边的空间小于菜单的高度，就定位菜单的上坐标（Top）为当前鼠标位置向上一个菜单高度
		if (document.body.clientHeight - y < document.getElementById('rMenu').offsetHeight) {
			y = document.body.scrollTop + y
					- document.getElementById('rMenu').offsetHeight;
		}
		rMenu.css({
			"top" : y + "px",
			"left" : x + "px",
			"visibility" : "visible"
		});
		$("body").bind("mousedown", onBodyMouseDown);
	}
	function onBodyMouseDown(event) {
		if (!(event.target.id == "rMenu" || $(event.target).parents("#rMenu").length > 0)) {
			rMenu.css({
				"visibility" : "hidden"
			});
		}
		if (!(event.target.id == "div_snapshot" || $(event.target).parents("#div_snapshot").length>0)) {
			$("#div_snapshot").css({"visibility" : "hidden"});
		}
	}
	var zTree, rMenu;
	$(document).ready(function() {
		$.fn.zTree.init($("#treeDemo"), setting);
		zTree = $.fn.zTree.getZTreeObj("treeDemo");
		rMenu = $("#rMenu");
		//为查询框添加样式
		$("ul.js_selectDls").each(function(){
			$(this).children("li").click(function(){
				$(this).addClass("dl3-on").siblings().removeClass("dl3-on");
			})								
		})
	});

	//控制隐藏div
	var flag = 0;
	function hide() {
		$(".left").toggle(100);
		if (flag == 0) {
			$("#img").attr("src",
					"sxcloud/cresources/ztree/zTreeStyle/img/diy/icon-right.png");
			$("#right_iframe").attr("width","100%");
			flag = 1;
		} else if (flag == 1) {
			$("#img").attr("src",
					"sxcloud/cresources/ztree/zTreeStyle/img/diy/icon-left.png");
			$("#right_iframe").attr("width","100%");
			flag = 0;
		}
	}

	//异步加载数据，当树节点展开时异步加载数据
	function zTreeBeforeExpand(treeId, treeNode) {
		var nodes = treeNode;
		if (nodes) {
			zTree.reAsyncChildNodes(nodes, "refresh", true);
		}
	}
	//隐藏右键菜单
	function hideRMenu() {
		if (rMenu){
			rMenu.css({"visibility" : "hidden"});
			rMenu.css({"div_snapshot" : "hidden"});
		}
		$("#div_snapshot").css({"visibility": "hidden"});
		$("body").unbind("mousedown", onBodyMouseDown);
	}
	
	function receiveValue(value){
		zTree.reAsyncChildNodes(null, "refresh",false);
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
		positionY = document.getElementById("snapshotVM").getBoundingClientRect().top;
		$("#div_snapshot").css({"top":positionY+"px", "left":positionX +"px","visibility":"visible"});
	}
	
	$(function(){
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
	
	
	function updateNodes(highlight) {
		var zTree = $.fn.zTree.getZTreeObj("treeDemo");
		for( var i=0, l=nodeList.length; i<l; i++) {
			nodeList[i].highlight = highlight;
			zTree.updateNode(nodeList[i]);
		}
	}
	//搜索的相关方法
	var nodeList = [];
	function searchNode() {
		var value = $.trim(key.get(0).value);
		var zTree = $.fn.zTree.getZTreeObj("treeDemo");
		var keyType = "name";
		if(value==""){
			updateNodes(false);
			nodeList = [];
		}else{
			updateNodes(false);
			nodeList = zTree.getNodesByParamFuzzy(keyType, value);
			updateNodes(true);
		}
	}
	
	$(document).ready(function(){
		key = $("#key");
		key.bind("focus", focusKey)
		.bind("blur", blurKey)
		.bind("propertychange", searchNode)
		.bind("input", searchNode);
	});
	
	function focusKey(e) {
		if (key.hasClass("empty")) {
			key.removeClass("empty");
		}
	}
	function blurKey(e) {
		if (key.get(0).value === "") {
			key.addClass("empty");
		}
	}
	
	function getFontCss(treeId, treeNode) {
		return (!!treeNode.highlight) ? {color:"red", "font-weight":"bold"} : {color:"#333", "font-weight":"normal"};
	}
	//////////////////start 2013年11月5日 新增ztree节点重命名执行之前回调函数
	function zTreeBeforeRename(treeId, treeNode, newName, isCancel) {
			//针对节点是虚拟机类型的进行重命名操作
		return saveRenameVM(treeNode,newName,treeNode.name,treeNode.type);
		return true;
	}
	//////////////////end 2013年11月5日 新增ztree节点重命名执行之前回调函数
	
	function onAsyncSuccess(event, treeId, treeNode, msg) {
		if(treeNode){
			expandNodes(treeNode.children);
		}
	}
	var result = '';
	var resultArray = [];
	////展开树节点
	function expandNodes(nodes) {
		result = '1,'+result;
		if (!nodes) return;
		if (nodes.length == undefined) {
			zTree.reAsyncChildNodes(nodes, "refresh");
			zTree.expandNode(nodes, true, false, false);
		} else {
			for (var i=0, l=nodes.length; i<l; i++) {
				if(result.search(nodes[i].id) != -1 && nodes[i].id != resultArray[0]){
					zTree.expandNode(nodes[i], true, false, false);
					if (nodes[i].isParent && nodes[i].zAsync) {
						if(!node[i].open){
							expandNodes(nodes[i].children);
						}
					}
				} else {
					if (nodes[i].id == resultArray[0]) {
						nodes[i].highlight = true;
						zTree.updateNode(nodes[i]);
						zTree.selectNode(nodes[i]);
						//查询到节点后，清空查询数据
						result = '';
						resultArray = [];
					}
				}
			}
		}
	}
	function triggerExpandNodes() {
		resultArray=[];
		var name = $("#txt").val();
		var type =  getDivLiType();
		var url = "united_getExpandNodes.do?name="+encodeURI(encodeURI(name))+"&type="+type;
		$.ajax({
			type : "GET",
			url : url,
			dataType : "json",
			cache:false,
			async: false,
			success : function(data){
				if(data!=null && data.length>0){
						result = data[0];
						resultArray = result.split(',');
						var node = zTree.getNodesByFilter(nodeFilter,true);
						expandNodes(node);
				}
			}
		});	
	}
	
	//取到最上层节点
	function nodeFilter(node) {
		if (node.type == 0) {
			return true;
		}
		return false;
	}
	//根绝节点类型查询节点列表
	function appp() {
		var operclass = '';
		var type = getDivLiType();
		
		var name = $('#txt').val();
		$.getJSON("united_queryTreeNodeByName.do?name="+encodeURI(encodeURI(name))+"&type="+type,{'time':new Date().toString()},function(data){
			$('#queryul').empty();
			if (data!=null &&　data.length > 0) {
				for (i=0;i<data.length;i++) {
					$('#queryul').append("<li onmousedown=\"getValue(\'txt\',\'"+data[i].name+"\');showAndHide(\'List1\',\'hide\');triggerExpandNodes()\" >"+data[i].name+"</li>");
				}
			} else {
				//alert("未搜索到数据，请重试！");
			}
		});
	}
	
	if (document.addEventListener) {//如果是Firefox    
        document.addEventListener("keypress", fireFoxHandler, true);    
    } else {    
        document.attachEvent("onkeypress", ieHandler);    
    }    
    function fireFoxHandler(evt) {    
        //alert("firefox");    
        if (evt.keyCode == 13) {    
           appp();
        }    
    }    
    function ieHandler(evt) {    
        //alert("IE");    
        if (evt.keyCode == 13) {    
        	appp();
        }    
    }
	$(function(){ 
		var _iframe = window.parent.document.getElementById("mainIframe");
		var iframeH = window.parent.document.documentElement.clientHeight-120;
		// $(_iframe).attr("height",0);
		$(_iframe).attr("height",iframeH);
		$("div.js_toggleLeft").height(iframeH);
		$("#right_iframe").attr("height",iframeH);
		$("#treeDemo").css("height",iframeH-44);
		$(window).resize(function(){
			iframeH = window.parent.document.documentElement.clientHeight-120;
			$(_iframe).attr("height",iframeH);
			$("div.js_toggleLeft").height(iframeH);
			$("#right_iframe").attr("height",iframeH);	
			$("#treeDemo").css("height",iframeH-44);
		})
	})
	//获取查询框选择类型Type
	function getDivLiType() {
		var type = '';
		$("ul.js_selectDls").each(function(){
			operclass = $(this).children("li.dl3-on").attr("id");						
		})
		if (operclass == 'query_vm') {
			type='4';
		} else if (operclass == 'query_host') {
			type='3';
		} else if (operclass == 'query_buss') {
			type='2';
		}  else if (operclass == 'query_vmAndHost') {//查询宿主机及虚拟机
			type='43';
		}
		return type;
	}

</script>
</head>
<body >
<s:form action="" method="post" id="theForm">
	
	<div class="content_wrap">
		<div class="left">
			 <div class="div-list">
			 	 <div>
			 	 	<input type="text" id="txt" name="txt" style="padding-left: 4px;"
						onfocus="showAndHide('List1','show');"
						onblur="showAndHide('List1','hide');" />
			 	 		 <div class="select js_select">
			 	 		 	<div class="sel-tt js_seltt"></div>
			 	 			<div class="sel-list js_selList">
			 	 		 		<ul class="js_selectDls">
			 	 		 			<li id="query_vmAndHost" class="dl3-on">
			 	 		 				所有资源
			 	 		 			</li>
			 	 		 			<li id="query_vm">
			 	 		 				虚拟机
			 	 		 			</li>
			 	 		 			<li id="query_host">
			 	 		 				主机
			 	 		 			</li>
			 	 		 			<li id="query_buss">
			 	 		 				业务系统
			 	 		 			</li>
			 	 		 		</ul>
			 	 		 	</div>
						</div>
						<input id="hideText" type="text" style="display:none"/>
				</div>
			</div>
				<!--列表1-->
				<div class="Menu" id="List1">
					<div class="Menu2 ">
						<ul id="queryul">
						</ul>
					</div>
				</div>
		<!--	<ul id="treeDemo" class="ztree" style="margin-top: 45px"></ul> -->
			 <ul id="treeDemo" class="ztree"></ul>
		<!-- </div> -->
		<!--
		<div style="float: left; margin-top: 200px">
			<img id="img"
				src="sxcloud/cresources/ztree/zTreeStyle/img/diy/icon.gif"
				onclick="hide()" />
		</div>
		-->
		</div>
		<div class="toggle-collapse js_toggleLeft">
             <img id="img" src="sxcloud/cresources/ztree/zTreeStyle/img/diy/icon-left.png" onclick="hide()">
        </div>
		
		<div class="right">
			<div id="iframe">
				<iframe id="right_iframe" src="resourcestatistics_showFirstPage.do" name="xxxx"  width="100%" frameborder="0"></iframe>
			</div>
		</div>
	</div>
	<div id="rMenu">
		<ul>
			<!-- 针对安徽移动云管理平台节点的菜单 -->
			<li id="addDataCenter" onclick="addDataCenter()" class="hide" >添加数据中心 </li>
			<li id="updateData" onclick="updateData()" class="hide">更新数据</li>
			<li id="searchNode" onclick="searchNode()" class="hide">搜索</li>
			<li id="addArea" onclick="addArea()" class="hide">添加地域</li>
			
			<!-- 针对数据中心的菜单 -->			
			<li id="editDataCenter" onclick="editDataCenter()" class="hide">编辑数据中心</li>
			<li id="delDataCenter" onclick="delDataCenter()" class="hide">移除数据中心</li>
			<li id="delDataCenterResource" onclick="delOperate()" class="hide">移除数据中心</li>
			<li id="delClusterResource" onclick="delOperate()" class="hide">移除集群</li>
			<li id="addCluster" onclick="addCluster()" class="hide">添加集群</li>
			<li id="syncClusterNotVir" onclick="syncCluster()" class="hide">同步集群</li>
			<li id="updHostRes" onclick="updHost()" class="hide">修改主机</li>
			<li id="addXenCluster" onclick="addXenCluster()" class="hide">添加集群</li>
			<!-- 针对集群的菜单 -->
			<li id="delCluster" onclick="deleteCluster()" class="hide">移除集群</li>
			<li id="addHost" onclick="addHost('3')" class="hide">添加主机</li>
			<li id="editCluster" onclick="editClusterPage()" class="hide">编辑设置</li>
			
			<!-- 针对主机的菜单 -->
			<li id="addnakedVM" onclick="addnakedVM()" class="hide">创建裸机</li>
			<li id="addVM" onclick="addVM()" class="hide">新建虚拟机</li>
			<li id="rebootHost" onclick="rebootHost()" class="hide">重新引导 </li>
			<li id="bootHost" onclick="bootHost()" class="hide">开机</li>
			<li id="shutdownHost" onclick="shutdownHost()" class="hide">关机</li>
			<li id="delHost" onclick="delHost()" class="hide">移除主机</li>
			<li id="addStore" onclick="addStore()" class="hide">添加存储</li>
			<li id="enterMaintenanceMode" onclick="enterMaintenanceMode()" class="hide">进入维护模式</li>
			<li id="exitMaintenanceMode" onclick="exitMaintenanceMode()" class="hide">退出维护模式</li>
<%--			<li id="enterAwaitMode" onclick="enterAwaitMode()" class="hide">进入待机模式</li>--%>
<%--			<li id="exitAwaitMode" onclick="enterAwaitMode()" class="hide">退出待机模式</li>--%>
			<li id="disConnectHost" onclick="disConnectHost()" class="hide">断开主机</li>
		<%--	<li id="connectHost" onclick="connectHost()" class="hide">连接主机</li> --%>
			<li id="importOvf" onclick="importOvf()" class="hide">导入OVF模板</li>
			
			<!-- 针对虚拟机菜单 -->
			<li id="startVM" onclick="putVMState('powerOn')" class="hide">启动</li>
			<li id="shutdownVM" onclick="putVMState('powerOff')" class="hide">关闭</li>
			<li id="hangupVM" onclick="putVMState('suspended')" class="hide">挂起</li>
			<li id="recoverVM" onclick="putVMState('resume')" class="hide">恢复</li>
			<li id="resetVM" onclick="putVMState('reset')" class="hide">重置</li>
			<li id="rebootVM" onclick="putVMState('restart')" class="hide">重启</li>
			<li id="reboot_guest" onclick="putVMState('reboot_guest')" class="hide">重启客户机</li>
			<li id="destroyVM" onclick="destroyVM()" class="hide">从磁盘删除</li>
			<li id="migrateVM" onclick="migrateVM()" class="hide">迁移</li>
			<li id="relocateVM" onclick="relocateVM()" class="hide">重定位</li>
			<li id="editVM" onclick="editVM()" class="hide">编辑设置</li>
			<li id="cloneVM" onclick="cloneVMByVM()" class="hide">克隆</li>
			<li id="snapshotVM" class="hide" onmouseover="onmouseOverSnapshot()" onclick="onmouseOverSnapshot()" onmouseout="onmouseOutSnapshot()">
				快照<font style="font-size:15px;vertical-align:center;position:absolute;right:5px;color:#AAAAAA;"> ▶</font>
			</li>
			<li id="console" onclick="console()" class="hide">控制台</li>
			<li id="markAsTemplate" onclick="markAsTemplate()" class="hide">转化为模板</li>
			<!-- ////////////start liudan 2013年11月5日  增加针对虚拟机的重命名操作菜单-->
			<li id="renameVM" onclick="renameVM()" class="hide">重命名</li>
			<!-- ////////////end liudan 2013年11月5日  增加针对虚拟机的重命名操作菜单-->
			<li id="installOrUpdateTool" onclick="installOrUpdateTool()" class="hide">安装/升级VMware Tools</li>
			
			<!-- 针对网络节点的菜单 -->
			<li id="delByTypeNet" onclick="delOperate()" class="hide">移除数据中心</li>
			<li id="addDomain" onclick="addDomain('6')" class="hide">添加网络域</li>
			<li id="delDomain" onclick="delOperate()" class="hide">移除网络域</li>
			<li id="addSubDomain" onclick="addSubDomain('7')" class="hide">添加子网络域</li>
			<li id="delSubDomain" onclick="delOperate()" class="hide">移除子网络域</li>
			<li id="addVlan" onclick="addVlan('8')" class="hide">添加Vlan</li>
			<li id="updVlan" onclick="editVlan()" class="hide">修改Vlan</li>
			<li id="delVlan" onclick="delVlan()" class="hide">移除Vlan</li>
			<!-- 针对存储节点的菜单 -->
			<li id="delDataStorage" onclick="delOperate();" class="hide">移除数据中心</li>
			<li id="addStorageDevice" onclick="addStorageDevice('9');" class="hide">添加存储设备</li>
			<li id="updStorageDevice" onclick="editStorageDevice()" class="hide">修改存储设备</li>
			<li id="delStorageDevice" onclick="delStorageDevice();" class="hide">移除存储设备</li>
			
			<!-- 针对未分配资源节点的菜单 -->
			<!-- 针对非虚拟化节点的菜单 -->
			<li id="delDataCenterNotVir" onclick="delOperate()" class="hide">移除数据中心</li>
			<li id="addClusterNotVir" onclick="addCluster()" class="hide">添加集群</li>
			<li id="delClusterNotVir" onclick="delOperate()" class="hide">移除集群</li>
			<li id="addHostNotVir" onclick="addHost('3')" class="hide">添加主机</li>
			<li id="updHostNotVir" onclick="updHost()" class="hide">修改主机</li>
			<li id="delHostNotVir" onclick="delHost()" class="hide">移除主机</li>
			<li id="recycleHostNotVir" onclick="recycleHostNotVir()" class="hide">释放主机</li>
		</ul>
	</div>
	<div id="div_snapshot">
			<ul>
				<li id="executeSnapshot" onclick="goAddSnapshot()" class="hide" >
					执行快照
				</li>
				<li id="snapshotManager" onclick="snapshotManage()" class="hide">
					快照管理器
				</li>
			</ul>
	</div>
	
</s:form>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/dateformat/jquery.formDataOper.js"></script>
</body>

