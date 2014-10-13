<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../sxcloud/common/taglib.jsp"%>
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="cache-control" content="no-cache"/>
<meta http-equiv="expires" content="0"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
<head>
<title>IBM树</title>
<link rel="stylesheet" href="sxcloud/cresources/ztree/zTree.css" type="text/css" />
<link rel="stylesheet" href="sxcloud/cresources/ztree/zTreeStyle/zTreeStyle.css" type="text/css" />
<script type="text/javascript" src="sxcloud/cjs/ztree/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript" src="sxcloud/cjs/ztree/jquery.ztree.excheck-3.5.js"></script>
<script type="text/javascript" src="sxcloud/cjs/ztree/jquery.ztree.exedit-3.5.js"></script>
<script type="text/javascript" src="sxcloud/js/uuid.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
<script type="text/javascript"src="<%=request.getContextPath() %>/ibmmanager/js/ibmtree.js"></script>
<script type="text/javascript"src="<%=request.getContextPath() %>/ibmmanager/js/rMenu.js"></script>
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
    text-align:center;
    width: 100px;
}
div#rMenu ul li:hover {
    background-color: #dfecf9;
}
/* 搜索相关 */
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

</style>
<script type="text/javascript">
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
			onAsyncSuccess: onAsyncSuccess
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
			url : "ibmmanager_asyncForTree.do",
			autoParam : [ "id","name","uuid","type","vtype","parent_id"]
		}
	};
	//点击节点触发事件
	function onClick(event, treeId, treeNode, clickFlag) {
		if (treeNode.type == 0) {//一级节点
			$("#right_iframe").attr(
					"src",
					"ibmtab_tabs.do?type=" + treeNode.type + "&id="+ treeNode.id + "&parent_id="+ treeNode.parent_id);
		} else if (treeNode.type == 10) {//小型机
			$("#right_iframe").attr(
					"src",
					"ibmtab_tabs.do?type=" + treeNode.type + "&id="+ treeNode.id + "&parent_id="+ treeNode.parent_id);
		} else if (treeNode.type == 20) {//X86
			$("#right_iframe").attr(
					"src",
					"ibmtab_tabs.do?type=" + treeNode.type + "&id="+ treeNode.id + "&parent_id="+ treeNode.parent_id);
		} else if (treeNode.type == 1) {//hmc
			$("#right_iframe").attr(
					"src",
					"ibmtab_tabs.do?type=" + treeNode.type + "&id="+ treeNode.id + "&parent_id="+ treeNode.parent_id + "&vtype="+treeNode.vtype + "&uuid="+treeNode.uuid + "&name="+treeNode.name);
		} else if (treeNode.type == 2) { //power
			$("#right_iframe").attr(
					"src",
					"ibmtab_tabs.do?type=" + treeNode.type + "&id="+ treeNode.id + "&parent_id="+ treeNode.parent_id + "&vtype="+treeNode.vtype + "&uuid="+treeNode.uuid + "&name="+treeNode.name);
		} else if (treeNode.type == 3) { //lpar
			$("#right_iframe").attr(
					"src",
					"ibmtab_tabs.do?type=" + treeNode.type + "&id="+ treeNode.id + "&parent_id="+ treeNode.parent_id + "&vtype="+treeNode.vtype + "&uuid="+treeNode.uuid + "&name="+treeNode.name);
		} else if (treeNode.type == 4) { //集群
			$("#right_iframe").attr(
					"src",
					"ibmtab_tabs.do?type=" + treeNode.type + "&id="+ treeNode.id + "&parent_id="+ treeNode.parent_id + "&vtype="+treeNode.vtype + "&uuid="+treeNode.uuid + "&name="+treeNode.name);
		} else if (treeNode.type == 5) { //主机
			$("#right_iframe").attr(
					"src",
					"ibmtab_tabs.do?type=" + treeNode.type + "&id="+ treeNode.id + "&parent_id="+ treeNode.parent_id + "&vtype="+treeNode.vtype + "&uuid="+treeNode.uuid + "&name="+treeNode.name);
		} else if (treeNode.type == 6) { //虚拟机
			$("#right_iframe").attr(
					"src",
					"ibmtab_tabs.do?type=" + treeNode.type + "&id="+ treeNode.id + "&parent_id="+ treeNode.parent_id + "&vtype="+treeNode.vtype + "&uuid="+treeNode.uuid + "&name="+treeNode.name);
		}
		hideRMenu();
	}
	//右键点击节点触发事件
	function OnRightClick(event, treeId, treeNode) {
		rightSNode = treeNode;
		var type;
		if (rightSNode == null) {
			type = null;
		} else {
			type = treeNode.type;
		}
		showRMenu(type, event.clientX, event.clientY);
	}
	//显示右键菜单
	function showRMenu(type, x, y) {
		$("#rMenu ul").show();
		if (type == null) {
			showRm("");
		} else if (type == 0) {
			showRm('0:10:9');
		} else if (type == 1) {
			showRm('1:2:3');
		} else if (type == 2) {
			showRm('4:5:6');
		} else if (type == 3) {
			showRm('7:8');
		} else if (type == 4) {
			showRm('11:12');
		} else if (type == 5) {
			showRm('13:14:15:16:17');
		} else if (type == 6) {
			showRm('18:19:20:21');
		}
		//如果从鼠标位置到容器下边的空间小于菜单的高度，就定位菜单的上坐标（Top）为当前鼠标位置向上一个菜单高度
		if (document.documentElement.clientHeight - y < document.getElementById('rMenu').offsetHeight) {
			y = document.documentElement.scrollTop + y
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
	}
	var zTree, rMenu;
	$(document).ready(function() {
		$.fn.zTree.init($("#treeDemo"), setting);
		zTree = $.fn.zTree.getZTreeObj("treeDemo");
		rMenu = $("#rMenu");
	});


	//异步加载数据，当树节点展开时异步加载数据
	function zTreeBeforeExpand(treeId, treeNode) {
		var nodes = treeNode;
		if (nodes) {
			zTree.reAsyncChildNodes(nodes, "refresh", true);
		}
	}
	//隐藏右键菜单
	function hideRMenu() {
		if (rMenu)
			rMenu.css({
				"visibility" : "hidden"
			});
		$("body").unbind("mousedown", onBodyMouseDown);
	}
	
	function receiveValue(value){
		zTree.reAsyncChildNodes(null, "refresh",false);
	}
	
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
	function updateNodes(highlight) {
		var zTree = $.fn.zTree.getZTreeObj("treeDemo");
		for( var i=0, l=nodeList.length; i<l; i++) {
			nodeList[i].highlight = highlight;
			zTree.updateNode(nodeList[i]);
		}
	}
	
	function getValue(obj, str) {
		var input = window.document.getElementById(obj);
		input.value = str;
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
	
	function onAsyncSuccess(event, treeId, treeNode, msg) {
		if(treeNode){
			expandNodes(treeNode.children);
		}
	}
	var result = '';
	var resultArray = [];
	////展开树节点
	function expandNodes(nodes) {
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
		var type = $("#type").val();
		var url = "ibmmanager_getExpandNodes.do?name="+encodeURI(encodeURI(name))+"&type="+type;
		$.ajax({
			type : "GET",
			url : url,
			dataType : "json",
			cache:false,
			async: false,
			success : function(data){
				if(data!=null && data.length>0){
						result = data[0];
						resultArray = result.split(",");
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
			var name = $('#txt').val();
			var type = $("#type").val();
			if(name!=null && name!=""){
				$.getJSON("ibmmanager_queryTreeNodeByName.do?name="+encodeURI(encodeURI(name))+"&type="+type,{'time':new Date().toString()},function(data){
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
    function chanage(){
    	$("#txt").attr("value","");
    	$('#queryul').empty();
    	var nodes = zTree.getNodeByParam("id",0);
    	zTree.reAsyncChildNodes(nodes, "refresh",true);
    }
    
</script>
</head>
<body>
<s:form action="" method="post" id="theForm">
	<div class="content_wrap">
		<div class="left">
			<ul class="list">
				<div>
					<tr>
						<td>
					<input type="text" id="txt" name="txt"
						onfocus="showAndHide('List1','show');"
						onblur="showAndHide('List1','hide');"  style="margin-top: 20px;width: 167px;margin-left: -17px;"/><s:select list="#{'6':'虚拟机','5':'主机'}" id="type" onchange="chanage()"></s:select>
						<input id="hideText" type="text" style="display:none"/>
						</td>
					</tr>
				</div>
				<!--列表1-->
				<div class="Menu" id="List1">
					<div class="Menu2">
						<ul id="queryul">
						</ul>
					</div>
				</div>
			</ul>
			<ul id="treeDemo" class="ztree" style="margin-top: 45px"></ul>
		</div>
		<div class="right">
			<div id="iframe">
				<iframe id="right_iframe" src="ibmtab_showFirstPage.do" name="xxxx" height="100%" width="76%" frameborder="0" style="position:fixed;left:259px;right:0px;top:0px;bottom:0px;overflow-x:auto;overflow-y:hidden;border:none;background：#fff;"></iframe>
			</div>
		</div>
	</div>
	<div id="rMenu">
		<ul>
			<li id="addIBMManager" onclick="addIBMManager()" class="hide">添加IBM </li>
			<li id="addCluster" onclick="addCluster()" class="hide">添加X86 </li>	
			<li id="updateData" onclick="updateData()" class="hide">更新数据</li>
			
			<li id="updateIBMManager" onclick="updateIBMManager()"class="hide"> 修改IBM </li>
			<li id="deleteIBMManager" onclick="deleteIBMManager()" class="hide">移除IBM </li>
			<li id="addPower" onclick="addPower()" class="hide"> 添加IBM整机 </li>
			
			<li id="updatePower" onclick="updatePower()" class="hide">修改IBM整机 </li>
			<li id="delPower" onclick="delPower()" class="hide"> 移除IBM整机 </li>
			<li id="addLogicPartition" onclick="addLogicPartition()" class="hide"> 添加逻辑分区 </li>
			
			<li id="updateLogicPartition" onclick="updateLogicPartition()" class="hide">修改逻辑分区 </li>
			<li id="delLogicPartition" onclick="delLogicPartition()" class="hide"> 移除逻辑分区</li>
			
			
			<li id="delCluster" onclick="delCluster()" class="hide">移除X86 </li>
			
			<li id="addHost" onclick="addHost()" class="hide"> 添加主机 </li>
			
			<li id="addnakedVM" onclick="addnakedVM()" class="hide">创建裸机 </li>
			<li id="addVM" onclick="addVM()" class="hide">创建虚拟机机 </li>
			<li id="rebootHost" onclick="rebootHost()" class="hide">重启主机 </li>
			<li id="shutdownHost" onclick="shutdownHost()" class="hide">关闭主机电源 </li>
			<li id="delHost" onclick="delHost()" class="hide"> 移除主机 </li>
			
			<li id="migrateVM" onclick="migrateVM()" class="hide">迁移虚拟机</li>
			<li id="editVM" onclick="editVM()" class="hide">编辑设置 </li>
			<li id="cloneVM" onclick="cloneVM()" class="hide"> 克隆虚拟机</li>
			<li id="delVM" onclick="delVM()" class="hide"> 移除虚拟机</li>
			
		</ul>
	</div>
</s:form>
</body>
