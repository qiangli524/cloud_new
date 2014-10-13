<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../../sxcloud/common/taglib.jsp"%>
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="expires" content="0" />
<head>
<title></title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/sxcloud/cresources/ztree/zTree.css" type="text/css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/sxcloud/cresources/ztree/zTreeStyle/zTreeStyle.css" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript"src="<%=request.getContextPath()%>/sxcloud/cjs/ztree/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/ztree/jquery.ztree.excheck-3.5.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/ztree/jquery.ztree.exedit-3.5.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/home/ui/njs/ui/ued_ui.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/js/uuid.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/ah/paas/tree/js/paasTree.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/ah/paas/tree/js/rMenu.js"></script>

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
<%--根节点不动--%>
.ztree li span.button.switch.level0 {visibility:hidden; width:1px;}
.ztree li ul.level0 {padding:0; background:none;}
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
			key:{
				title:"title"
			},
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
			url : "paasTree_asyncForTree.do",
			autoParam : [ "id","name","entity_id","parent_id","node_type","server_type","table"]
		}
	};
	
	//点击右侧弹出tab页
	function onClick(event, treeId, treeNode, clickFlag) {
		var node_type = treeNode.node_type;
		var table =  treeNode.table;
		if (table == 'hadoop') {
			var url = "hadoop_tabs.do?obj.node_type=" + node_type + "&obj.id="+ treeNode.id+"&obj.uuid="
				+treeNode.entity_id+"&obj.name="+treeNode.name+"&obj.parent_id="+treeNode.parent_id+"&obj.service_type="+treeNode.server_type;
			$("#right_iframe").attr("src",url);
		} else {
			$("#right_iframe").attr(
					"src",
					"paasTree_tabs.do?id=" + treeNode.id + "&entity_id="+ treeNode.entity_id 
							+ "&parent_id=" + treeNode.parent_id + "&node_type=" + treeNode.node_type + "&server_type=" + treeNode.server_type);
		}
		setDivVisProp('iframe');
		hideRMenu();
	}
	//触发鼠标右键
	function OnRightClick(event, treeId, treeNode) {
		rightSNode = treeNode;
		var type;
		if (rightSNode == null) {
			type = null;
		} else {
			zTree.selectNode(rightSNode);
			type = treeNode.node_type;
		}
		showRMenu(treeNode, event.clientX, event.clientY);
	}
	//显示右键菜单
	function showRMenu(treeNode, x, y) {
		$("#rMenu ul").show();
		if(treeNode == null){
			return false;
		}
		var node_type = treeNode.node_type;
		var server_type =  treeNode.server_type;
		var table =  treeNode.table;
		if (table == 'hadoop') {
			if (node_type == null || node_type == 11) {
				showRm("");
			} else {
				showRm('100:102');
			}
		} else {
			if (node_type == null) {
				showRm("");
			} else if(node_type==-1){//根节点
				//showRm('0');
				showRm('');
			} else if (node_type == 0) {//数据中心
				//showRm('1:2:3');
				showRm('');
			} else if (node_type == 10) {//Caas
				//showRm('4:5:6');
				showRm('6');
			} else if (node_type == 11) {//缓存
				showRm('7:8:9');
			} else if (node_type == 12) {//业务
				showRm('10:11:12');
			}else if (node_type == 13) {//实例
				showRm('13:14:15');
			}else if (node_type == 14) {//业务
				showRm('13:14:15');
			}else if(node_type == 20){//Daas
				showRm('20');
			}else if(node_type == 21){//数据库类型
				showRm('21:22:32');
			}else if(node_type == 22 && server_type != 6){//业务
				showRm('23:24:33');
			}else if(node_type == 22 && server_type == 6){//GP业务
				showRm('23:30');
			}else if(node_type == 23 && server_type != 6){//数据库
				showRm('25:26');
			}else if(node_type == 23 && server_type == 6){//GP服务
				showRm('31:28');
			}else if(node_type == 24 && server_type != 6){//数据库实体
				showRm('27');
			}else if(node_type == 24 && server_type == 6){//Gp主机实体
				showRm('29');
			}else if(node_type == 30){//Maas
				showRm('36');
			}else if(node_type == 31){//数据库类型
				showRm('37:38');
			}else if(node_type == 32){//业务
				showRm('39:40');
			}else if(node_type == 33){//中间件
				showRm('41:42');
			}else if(node_type == 34){//实体
				showRm('43');
			}else{
				showRm("");
			}
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
	//鼠标按下的事件
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
	//////////////////ztree节点重命名执行之前回调函数
	function zTreeBeforeRename(treeId, treeNode, newName, isCancel) {
		
		return saveRenameBusi(treeNode,newName,treeNode.name,treeNode.node_type);
		return true;
	}
	//////////////////end 新增ztree节点重命名执行之前回调函数
	
	//默认展开第一个节点
	function onAsyncSuccess(event, treeId, treeNode, msg) {
		var selectedNode = zTree.getSelectedNodes();                    
		var nodes = zTree.getNodes();   
		if(nodes.length>0){
			zTree.expandNode(nodes[0], true);
		}
		if(treeNode){
			expandNodes(treeNode.children);
		}
	}
	var result = '';
	var resultArray = [];
	////展开树节点
	function expandNodes(nodes) {
		result = '0,'+result;
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
		var url = "paasTree_getExpandNodes.do?name="+encodeURI(encodeURI(name))+"&node_type="+type;
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
		if (node.node_type == 0) {
			return true;
		}
		return false;
	}
	//根绝节点类型查询节点列表
	function appp() {
			var name = $("#txt").val();
			var type =  getDivLiType();
			$.getJSON("paasTree_queryTreeNodeByName.do?name="+encodeURI(encodeURI(name))+"&node_type="+type,{'time':new Date().toString()},function(data){
				$('#queryul').empty();
				if (data!=null &&　data.length > 0) {
					for (i=0;i<data.length;i++) {
						$('#queryul').append("<li onmousedown=\"getValue(\'txt\',\'"+data[i].name+"\');showAndHide(\'List1\',\'hide\');triggerExpandNodes()\" >"+data[i].name+"</li>");
					}
				} else {
					alert("未搜索到数据，请重试！");
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
		if (operclass == 'query_bui') {
			type='3';
		} else if (operclass == 'query_instance') {
			type='2';
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
			 	 		 			<li id="query_bui" class="dl3-on">
			 	 		 				资源池
			 	 		 			</li>
			 	 		 			<li id="query_instance">
			 	 		 				实例
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
			 <ul id="treeDemo" class="ztree"></ul>
		</div>
		<div class="toggle-collapse js_toggleLeft">
             <img id="img" src="sxcloud/cresources/ztree/zTreeStyle/img/diy/icon-left.png" onclick="hide()">
        </div>
		<div class="right">
			<div id="iframe">
				<iframe id="right_iframe" src="oracleTab_showPaas.do" name="xxxx" height="100%" width="100%" frameborder="0" style="right:0px;top:0px;bottom:0px;overflow-x:auto;border:none;background：#fff;"></iframe>
			</div>
		</div>
	</div>
	<div id="rMenu">
		<ul>
			<!-- 针对安徽移动云管理平台节点的菜单 -->
			<li id="addDataCenter" onclick="addNode()" class="hide" >添加数据中心 </li>
			<li id="delDataCenter" onclick="delDataCenter()" class="hide">移除数据中心</li>
			<li id="editDataCenter" onclick="editDataCenter()" class="hide">编辑数据中心</li>
			<li id="addService" onclick="addNode()" class="hide">添加服务</li>
			<li id="delService" onclick="delService()" class="hide">移除服务</li>
			<li id="editService" onclick="editService()" class="hide">编辑服务</li>
			
			<!-- Caas节点 -->
			<li id="addCache" onclick="addCache()" class="hide">添加缓存</li>
			<li id="delCache" onclick="delCache()" class="hide">移除缓存</li>
			<li id="editCache" onclick="editCache()" class="hide">编辑缓存</li>
			<li id="addCacheBusiness" onclick="addCacheBusiness()" class="hide">添加业务</li>
			<li id="delCacheBusiness" onclick="delCacheBusiness()" class="hide">删除业务</li>
			<li id="editCacheBusiness" onclick="editCacheBusiness()" class="hide">编辑业务</li>
			<li id="addCacheExamples" onclick="addCacheExamples()" class="hide">添加实例</li>
			<li id="delCacheExamples" onclick="delCacheExamples()" class="hide">删除实例</li>
			<li id="editCacheExamples" onclick="editCacheExamples()" class="hide">编辑实例</li>
			<li id="addCacheHost" onclick="addCacheHost()" class="hide">添加主机</li>
			<li id="delCacheHost" onclick="delCacheHost()" class="hide">删除主机</li>
			<li id="editCacheHost" onclick="editCacheHost()" class="hide">编辑主机</li>
			
			<!-- Daas节点 -->
			<li id="addDBType" onclick="addDBType()" class="hide">添加数据库类型</li>
			<li id="delDBType" onclick="delDBType()" class="hide">删除数据库类型</li>
			<li id="addDBBusiness" onclick="addDBBusiness()" class="hide">添加业务</li>
			<li id="autoGeneration" onclick="autoGeneration()" class="hide">自动生成节点</li>
			<li id="delDBBusiness" onclick="delDBBusiness()" class="hide">删除业务</li>
			<li id="addDB" onclick="addDB()" class="hide">添加数据库</li>
			<li id="renameBusi" onclick="renameBusi()" class="hide">重命名</li>
			<li id="delDB" onclick="delDB()" class="hide">删除数据库</li>
			<li id="addDBEntity" onclick="addDBEntity()" class="hide">添加实体</li>
			<li id="delDBEntity" onclick="delDBEntity()" class="hide">删除实体</li>
			<li id="addServerNode" onclick="addDB()" class="hide">添加服务节点</li>
			<li id="delServerNode" onclick="delDB()" class="hide">删除服务节点</li>
			<li id="addServerHost" onclick="addDBEntity()" class="hide">添加服务主机</li>
			<li id="delServerHost" onclick="delDBEntity()" class="hide">删除服务主机</li>
			
			
			<!-- Maas节点 -->
			<li id="addMiddlewareType" onclick="addMiddlewareType()" class="hide">添加中间件类型</li>
			<li id="delMiddlewareType" onclick="delMiddlewareType()" class="hide">删除中间件类型</li>
			<li id="addMiddlewareBusiness" onclick="addMiddlewareBusiness()" class="hide">添加业务</li>
			<li id="delMiddlewareBusiness" onclick="delMiddlewareBusiness()" class="hide">删除业务</li>
			<li id="addMiddleware" onclick="addMiddleware()" class="hide">添加中间件</li>
			<li id="delMiddleware" onclick="delMiddleware()" class="hide">删除中间件</li>
			<li id="addMiddlewareEntity" onclick="addMiddlewareEntity()" class="hide">添加实体</li>
			<li id="delMiddlewareEntity" onclick="delMiddlewareEntity()" class="hide">删除实体</li>
			
			<!-- 针对Hadoop根节点的菜单 -->
			<li id="addChild" onclick="addChild()" class="hide" >添加子节点 </li>
			<li id="remoteVisit" onclick="remoteVisit()" class="hide" >远程访问 </li>
			<li id="listTopo" onclick="listTopo()" class="hide" >拓扑图</li>
		</ul>
	</div>
</s:form>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/dateformat/jquery.formDataOper.js"></script>
</body>
