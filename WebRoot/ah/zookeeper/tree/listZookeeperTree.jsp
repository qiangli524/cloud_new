<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/sxcloud/common/taglib.jsp"%>
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
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/js/uuid.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/ah/zookeeper/tree/js/zookeeperTree.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/ah/zookeeper/tree/js/rMenu.js"></script>

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
</style>
<script type="text/javascript">

	function getValue(obj, str) {
		var input = window.document.getElementById(obj);
		input.value = str;
	}
	//右键选择节点
	var rightSNode;
	var setting = {
		view : {
			dblClickExpand : false,//双击节点时，是否自动展开父节点的标识
			selectedMulti : false,//设置是否允许同时选中多个节点
			fontCss: getFontCss//个性化文字样式，只针对 zTree 在节点上显示的<A>对象
		},
		callback : {
			onRightClick : OnRightClick,
			onClick : onClick,
			beforeExpand : zTreeBeforeExpand,//用于捕获父节点展开之前的事件回调函数，并且根据返回值确定是否允许展开操作
			onAsyncSuccess: onAsyncSuccess,//用于捕获异步加载正常结束的事件回调函数
			beforeRename : zTreeBeforeRename
		},
		data : {
			simpleData : {
				enable : true//rue / false 分别表示 使用 / 不使用 简单数据模式(不需要用户再把数据库中取出的 List 强行转换为复杂的 JSON 嵌套格式)
			}
		},
		check : {
			enable : false//设置 zTree 的节点上是否显示 checkbox / radio
		},
		async : {
			enable : true,
			dataType: "json",
			url : "zookeeperTree_asyncForTree.do",//异步加载树
			autoParam : [ "name","fullPath","dataValue","isParent"]
		}
	};
	/* zTreeNodes = [
		{"name":"网站导航", "node_type":"0",open:true, children: [
			{ "name":"google", "node_type":"1"},
			{ "name":"baidu", "node_type":"1"},
			{ "name":"sina", "node_type":"1"}
			]
		}
	]; */
	//点击右侧弹出tab页
	function onClick(event, treeId, treeNode, clickFlag) {
		var node_type = treeNode.node_type;
		$("#right_iframe").attr(
					"src",
					"zookeeperTree_tabs.do?currentTreeObj.name=" + treeNode.name+"&currentTreeObj.fullPath="+treeNode.fullPath+"&currentTreeObj.dataValue="+treeNode.dataValue+"&currentTreeObj.hiddName="+treeNode.hiddName+"&currentTreeObj.flag=show");
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
		var fullPath = treeNode.fullPath;
		var arr = fullPath.split("/");
		var falg = treeNode.isParent;
		if(fullPath == "/"){//根节点
			showRm('0:1');
		}else if(arr.length < 7){
			showRm('0:1:2');
		}else{
			showRm('1:2');
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
		$.fn.zTree.init($("#treeDemo"), setting/*,zTreeNodes*/);
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

	//异步加载数据，当树节点展开前异步加载数据
	function zTreeBeforeExpand(treeId, treeNode) {
		var nodes = treeNode;
		if (nodes) {
			//异步加载父节点的子节点 :参数1   指定需要异步加载的父节点对象的JSON 数据
			//参数2 reloadType = "refresh" 表示清空后重新加载
			//参数3 isSilent = true 时，不展开父节点，其他值或缺省状态都自动展开
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
		//parentNode = null 时，相当于从根节点 Root 进行异步加载
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
		return (!!treeNode.highlight) ? {color:"black", "font-weight":"normal"} : {color:"#333", "font-weight":"normal"};
	}
	//////////////////start 2013年11月5日 新增ztree节点重命名执行之前回调函数
	function zTreeBeforeRename(treeId, treeNode, newName, isCancel) {
			//针对节点是虚拟机类型的进行重命名操作
		return saveRenameVM(treeNode,newName,treeNode.name,treeNode.node_type);
		return true;
	}
	//////////////////end 2013年11月5日 新增ztree节点重命名执行之前回调函数
	
	//默认展开第一个节点
	function onAsyncSuccess(event, treeId, treeNode, msg) {
		var selectedNode = zTree.getSelectedNodes();  //获取被选中的当前节点                  
		var nodes = zTree.getNodes(); //获取 zTree 的全部节点数据  
		if(nodes.length>0){
			zTree.expandNode(nodes[0], true);//展开(true) / 折叠(false) 指定的节点
		}
		if(treeNode){
			expandNodes(treeNode.children);//节点的子节点数据集合
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
	/* function val(){
		alert(1);
	} */
</script>
</head>
<body style="overflow-y:auto;height: 2000px;" >
<s:form action="" method="post" id="theForm">
	<div class="content_wrap">
		<div class="left">
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
				<iframe id="right_iframe" src="zookeeperTree_showNodeDetail.do" name="xxxx" height="100%" width="80%" frameborder="0" style="position:fixed;left:259px;right:0px;top:0px;bottom:0px;overflow-x:auto;border:none;background：#fff;"></iframe>
			</div>
		</div>
	</div>
	<div id="rMenu">
		<ul>
			<!-- 针对安徽移动云管理平台节点的菜单 -->
			<li id="addChild" onclick="addChild()" class="hide">添加子节点</li>		
			<li id="delSelf" onclick="delSelfNode()" class="hide">移除节点</li>
			<li id="editSelf" onclick="editSelfNode()" class="hide">编辑节点</li>
			
		</ul>
	</div>
</s:form>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/dateformat/jquery.formDataOper.js"></script>
</body>
