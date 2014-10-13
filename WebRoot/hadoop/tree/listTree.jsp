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
<script type="text/javascript" src="<%=request.getContextPath() %>/hadoop/tree/js/rMenu.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/hadoop/tree/js/tree.js"></script>

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
	width: 247px;
	height:500px;
	z-index: 1;
	background: #FFF;
	border: 1px solid #000;
	margin-top: -100px;
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
	left: -17px;
	top: 100px;
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
	function getValue(obj, str, id) {
		var input = window.document.getElementById(obj);
		input.value = str;
		input.setAttribute("node_id",id);
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
			url : "hadoop_asynForTree.do",
			autoParam : [ "id=obj.id","type=obj.node_type","name=obj.name","uuid=obj.uuid","parent_id=obj.parent_id"]
		}
	};
	
	function onClick(event, treeId, treeNode, clickFlag) {
		var url = "hadoop_tabs.do?obj.node_type=" + treeNode.node_type + "&obj.id="+ treeNode.id+"&obj.uuid="
				+treeNode.uuid+"&obj.name="+treeNode.name+"&obj.parent_id="+treeNode.parent_id+"&obj.service_type="+treeNode.service_type;
		$("#right_iframe").attr("src",url);
		setDivVisProp('iframe');
		hideRMenu();
	}
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
		var type = treeNode.node_type;
		var vtype=treeNode.vtype;
		var state = treeNode.state;
		var oper = treeNode.oper;
		if (type == null) {
			showRm("");
		} else if (type == 0) {//安徽移动云管理平台
			showRm('0:2');
		}else if(type==8){//hdfs
			showRm('0:1');
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
	});

	//控制隐藏div
	var flag = 0;
	function hide() {
		$(".left").toggle('slide', {
			direction : 'left'
		}, 100);
		if (flag == 0) {
			$("#img").attr("src",
					"sxcloud/cresources/ztree/zTreeStyle/img/diy/icon2.gif");
			flag = 1;
		} else if (flag == 1) {
			$("#img").attr("src",
					"sxcloud/cresources/ztree/zTreeStyle/img/diy/icon.gif");
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
		if(treeNode.node_type == '4'){
			//针对节点是虚拟机类型的进行重命名操作
			return saveRenameVM(treeNode,newName,treeNode.name);
		}
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
		var node_id = $("#txt").attr("node_id");
		var url = "hadoop_getExpandNodes.do?labelName="+encodeURI(encodeURI(name))+"&type="+type+"&id="+node_id;
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
	/* 	if (node.type == 0) {
			return true;
		} */
		return true;
	}
	//根绝节点类型查询节点列表
	function appp() {
			var name = $('#txt').val();
			var type = $("#type").val();
			$.getJSON("hadoop_queryTreeNodeByName.do?labelName="+encodeURI(encodeURI(name))+"&type="+type,{'time':new Date().toString()},function(data){
				$('#queryul').empty();
				if (data!=null &&　data.length > 0) {
					for (i=0;i<data.length;i++) {
						$('#queryul').append("<li onmousedown=\"getValue(\'txt\',\'"+data[i].name+"\',\'"+data[i].id+"\');showAndHide(\'List1\',\'hide\');triggerExpandNodes()\" >"+data[i].name+"---"+data[i].service_name+"</li>");
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
		}
		return type;
	}
</script>
</head>
<body style="overflow-y:auto">
<s:form action="" method="post" id="theForm">
	<div class="content_wrap">
		<div class="left">
			<ul class="div-list">
				<div>
					<input type="text" id="txt" name="txt" style="padding-left: 4px;" node_id=""
						onfocus="showAndHide('List1','show');"
						onblur="showAndHide('List1','hide');"/>
						 <div class="select js_select">
			 	 		 	<div class="sel-tt js_seltt"></div>
			 	 			<div class="sel-list js_selList">
			 	 		 		<ul class="js_selectDls">
			 	 		 			<li id="10" class="dl3-on">
			 	 		 				服务
			 	 		 			</li>
			 	 		 			<li id="11">
			 	 		 				服务实例
			 	 		 			</li>
			 	 		 		</ul>
			 	 		 	</div>
						</div>
						<input id="hideText" type="text" style="display:none"/>
				</div>
				<!--列表1-->
				<div class="Menu" id="List1">
					<div class="Menu2 ">
						<ul id="queryul">
						</ul>
					</div>
				</div>
			<ul id="treeDemo" class="ztree"></ul>
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
				<iframe id="right_iframe" src="hadoopstatistics_statisticsRootNode.do?obj.node_type=0" name="xxxx" width="100%" frameborder="0" ></iframe>
			</div>
			<!-- -->
		</div>
	</div>
	<div id="rMenu">
		<ul>
			<!-- 针对根节点的菜单 -->
			<li id="addChild" onclick="addChild()" class="hide" >添加子节点 </li>
			<li id="remoteVisit" onclick="remoteVisit()" class="hide" >远程访问 </li>
			<li id="listTopo" onclick="listTopo()" class="hide" >拓扑图</li>
		</ul>
	</div>
	
</s:form>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/dateformat/jquery.formDataOper.js"></script>
</body>
