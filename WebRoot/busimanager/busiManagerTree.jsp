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
<script type="text/javascript" src="<%=request.getContextPath() %>/busimanager/js/rMenu.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/home/ui/njs/ui/ued_ui.js"></script>
<script type="text/javascript"
	src="sxcloud/js/uuid.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
<script type="text/javascript"src="<%=request.getContextPath() %>/busimanager/js/busisystree.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resource/united/js/unitedTree.js"></script>
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
    width: 80px;
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

div#rMenu ul li:hover {
    background-color: #dfecf9;
}
/* 搜索相关 */
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
			url : "busimanager_asyncForTree.do",
			autoParam : [ "id", "name", "type","entity_id","connect_id"]
		}
	};
	
	function onClick(event, treeId, treeNode, clickFlag) {
		if (treeNode.type == 0) {//一级节点
			$("#right_iframe").attr(
					"src",
					"abstractinfo_treeTabs.do?type=" + treeNode.type + "&id="+ treeNode.id);
<%--			setDivVisProp('iframe');--%>
		} else if (treeNode.type == 1) {//业务系统
			$("#right_iframe").attr(
					"src",
					"abstractinfo_treeTabs.do?type=" + treeNode.type + "&id="+ treeNode.id);
<%--			setDivVisProp('iframe');--%>
		} else if (treeNode.type == 2) { //子系统
			$("#right_iframe").attr(
					"src",
					"abstractinfo_treeTabs.do?type=" + treeNode.type + "&id="+ treeNode.id);
<%--			setDivVisProp('iframe');--%>
		} else if (treeNode.type == 3) { //虚拟机
			$("#right_iframe").attr(
					"src",
					"abstractinfo_treeTabs.do?type=" + treeNode.type + "&id="+ treeNode.id+"&entity_id="+treeNode.entity_id+"&connect_id="+ treeNode.connect_id);
<%--			setDivVisProp('iframe');--%>
		} else if (treeNode.type == 4) { //承载业务
			$("#right_iframe").attr(
					"src",
					"abstractinfo_treeTabs.do?type=" + treeNode.type + "&id="+ treeNode.id);
<%--			setDivVisProp('iframe');--%>
		}
		else if (treeNode.type == 5) { //物理机
			$("#right_iframe").attr(
					"src",
					"abstractinfo_treeTabs.do?type=" + treeNode.type + "&id="+ treeNode.id);
<%--			setDivVisProp('iframe');--%>
		}		
		hideRMenu();
	}
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
			showRm('0:14');
		} else if (type == 0) {
			showRm('1:2:10:16');
		} else if (type == 1) {
			showRm('3:5:11:16');
		} else if (type == 2) {
			//showRm('6:8:13:12:16');
			showRm('6:8:13:17:12:16');
		} else if (type == 3) {
			showRm('9');
		}else if(type == 4){
			//showRm('14:15:8');
			showRm('14:17:15:8');
		}else if(type == 5){
			showRm('18');
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
		var type =  getDivLiType();
		var parent = $("#parent").val();
		var url = "busimanager_getExpandNodes.do?name="+encodeURI(encodeURI(name))+"&type="+type+"&id="+parent;
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
			var type = getDivLiType();
			$.getJSON("busimanager_queryTreeNodeByName.do?name="+encodeURI(encodeURI(name))+"&type="+type,{'time':new Date().toString()},function(data){
				$('#queryul').empty();
				if (data!=null &&　data.length > 0) {
					for (i=0;i<data.length;i++) {
						$('#queryul').append("<li onmousedown=\"getValue(\'txt\',\'"+data[i].name+"\');getValue(\'parent\',\'"+data[i].parent_id+"\');showAndHide(\'List1\',\'hide\');triggerExpandNodes()\" >"+data[i].name+"</li>");
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
		$("#right_iframe").css("height",iframeH);
		$("#treeDemo").css("height",iframeH-44);
		$(window).resize(function(){
			iframeH = window.parent.document.documentElement.clientHeight-120;
			$(_iframe).attr("height",iframeH);
			$("div.js_toggleLeft").height(iframeH);
			$("#right_iframe").css("height",iframeH);	
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
			type='3';
		} else if (operclass == 'query_buss') {
			type='2';
		} else if (operclass == 'query_vmAndBuss') {//查询虚拟机和业务系统
			type='32';
		}
		return type;
	}
</script>
</head>
<body>
<s:form action="busitree_listBusiTree" method="post" id="theForm">
	<s:hidden id="parent"/>
	<div class="content_wrap">
		<div class="left">
			<div class="div-list">
				<div>
					<input type="text" id="txt" name="txt"
						onfocus="showAndHide('List1','show');"
						onblur="showAndHide('List1','hide');" />
						<!-- <input id="hideText" type="text" style="display:none"/> -->
				 	<div class="select js_select">
			 	 		 <div class="sel-tt js_seltt"></div>
			 	 			<div class="sel-list js_selList">
				 			<ul class="js_selectDls">
			 	 		 		<!--
			 	 		 			<li>
			 	 		 				<s:select list="#{'3':'虚拟机','2':'业务系统'}" id="type" ></s:select>
			 	 		 			</li>
			 	 		 			-->
			 	 		 			<li id="query_vmAndBuss" class="dl3-on">
			 	 		 				所有资源
			 	 		 			</li>
			 	 		 			<li id="query_vm">
			 	 		 				虚拟机
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
				<!-- 
			<ul class="list">
				<div>
					<tr>
						<td>
					<input type="text" id="txt" name="txt"
						onfocus="showAndHide('List1','show');"
						onblur="showAndHide('List1','hide');"  style="margin-top: 20px;width: 167px;margin-left: -17px;"/><s:select list="#{'3':'虚拟机','2':'业务系统'}" id="type" ></s:select>
						<input id="hideText" type="text" style="display:none"/>
						</td>
					</tr>
				</div>
			</ul>
				-->
				<!--列表1-->
				<div class="Menu" id="List1">
					<div class="Menu2">
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
				<iframe id="right_iframe" src="abstractinfo_getAllChartInfo.do" name="xxxx"  width="100%" frameborder="0" ></iframe>
			</div>
		</div>
	</div>
	<div id="rMenu">
		<ul>
			<li id="addBusiSysCenter" onclick="addBusiSysCenter()" class="hide">添加业务中心 </li>
			<li id="updateBusiSysCenter" onclick="updateSystemCenter()"class="hide"> 修改业务中心 </li>
			<li id="deleteBusiSysCenter" onclick="deleteBusiSysCenter()" class="hide">删除业务中心 </li>
			
			<li id="addBizsys" onclick="addBizsys()" class="hide"> 添加业务系统 </li>
			<li id="modifyBizsys" onclick="updateBizsys()" class="hide">修改业务系统 </li>
			<li id="delBizsys" onclick="delBizsys()" class="hide"> 删除业务系统 </li>
			<li id="updateBizsys" onclick="updateBizsys()" class="hide">更新名称 </li>
			
			<li id="addSysApp" onclick="addSysApp()" class="hide"> 添加子系统 </li>
			<li id="modifySysApp" onclick="modifySysApp()" class="hide">修改子系统 </li>
			<li id="delSysApp" onclick="delSysApp()" class="hide"> 删除子系统 </li>
<%--			<li id="batchDeployExample" onclick="batchDeployExample()" class="hide"> 批量部署 </li>--%>
			<li id="updateSysApp" onclick="updateSysApp()" class="hide"> 更新名称</li>
			
			<li id="addDeployExample" onclick="addDeployExample()" class="hide">添加虚拟机 </li>
			<li id="delDeployExample" onclick="delDeployExample()" class="hide">删除虚拟机 </li>
<%--			<li id="deploy" onclick="deploy()" class="hide">部署 </li>--%>
<%--			<li id="upgradeDeployExample" onclick="upgradeDeployExample()" class="hide">升级 </li>--%>
			
			<li id="addbusiness" onclick="addbusiness()" class="hide">添加承载业务 </li>
			<li id="modbusiness" onclick="modBusiness()" class="hide">修改承载业务 </li>
			<li id="delbusiness" onclick="delBusiness()" class="hide">删除承载业务 </li>
			<li id="exportExcel" onclick="exportExcel()" class="hide">导出虚拟机</li>
<!-- 			<li id="addDeployExample" onclick="addDeployExample()" class="hide">添加虚拟机 </li> -->
<!-- 			<li id="delDeployExample" onclick="delDeployExample()" class="hide">删除虚拟机 </li> -->
			<li id="addHostToBusi" onclick="addHostToBusi()" class="hide">添加物理机 </li>
			<li id="deleteHostToBusi" onclick="deleteHostToBusi()" class="hide">删除物理机</li> 
		</ul>
	</div>
</s:form>
<form id="excelForm" method="post">
</form>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/dateformat/jquery.formDataOper.js"></script>
</body>
