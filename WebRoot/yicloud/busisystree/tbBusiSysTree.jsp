<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../sxcloud/common/taglib.jsp"%>
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />
<link
	href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css"
	rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
<head>
<title></title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/sxcloud/cresources/ztree/zTree.css"
	type="text/css" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/sxcloud/cresources/ztree/zTreeStyle/zTreeStyle.css"
	type="text/css" />
<script type="text/javascript"
	src="<%=request.getContextPath()%>/sxcloud/cjs/ztree/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/sxcloud/cjs/ztree/jquery.ztree.excheck-3.5.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/sxcloud/cjs/ztree/jquery.ztree.exedit-3.5.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/yicloud/busisystree/js/rMenu.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/sxcloud/js/uuid.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/yicloud/busisystree/js/busisystree.js"></script>
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
}

div#rMenu ul li:hover {
    background-color: #dfecf9;
}
</style>
<script type="text/javascript">
	//右键选择节点
	var rightSNode;
	var setting = {
		view : {
			dblClickExpand : false,
			selectedMulti : false
		},
		callback : {
			onRightClick : OnRightClick,
			onClick : onClick,
			beforeExpand : zTreeBeforeExpand
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
			url : "busisys_asyncForTree.do",
			autoParam : [ "id", "name=n", "level=lv" ]
		}
	};
	
	function onClick(event, treeId, treeNode, clickFlag) {
		if (treeNode.type == 0) {//业务系统父节点
			$("#right_iframe").attr(
					"src",
					"busisys_treeTabs.do?type=" + treeNode.type + "&id="
							+ treeNode.id);
			setDivVisProp('iframe');
		} else if (treeNode.type == 1) {//业务系统
			$("#right_iframe").attr(
					"src",
					"busisys_treeTabs.do?type=" + treeNode.type + "&id="
							+ treeNode.id+"&entityId="+treeNode.entityId);
			setDivVisProp('iframe');
		} else if (treeNode.type == 2) { //基准应用
			$("#right_iframe").attr(
					"src",
					"busisys_treeTabs.do?type=" + treeNode.type + "&id="
							+ treeNode.id+"&entityId="+treeNode.entityId+"&hostIP="+treeNode.hostIP);
			setDivVisProp('iframe');
		} else if (treeNode.type == 3) { //部署实例
			$("#right_iframe").attr(
					"src",
					"busisys_treeTabs.do?type=" + treeNode.type + "&id="
							+ treeNode.id+"&entityId="+treeNode.entityId+"&hostIP="+treeNode.hostIP);
			setDivVisProp('iframe');
		} else if (treeNode.type == 4) { //模板镜像信息
			$("#right_iframe").attr(
					"src",
					"busisys_treeTabs.do?type=" + treeNode.type + "&id="
							+ treeNode.id);
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
			showRm('2:10');
		} else if (type == 1) {
			showRm('3:5:11');
		} else if (type == 2) {
			showRm('6:8:12:15:16');
		} else if (type == 3) {
			showRm('9:13');
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
/**********************************************自定义方法****************************************************************************/	
	//增加根节点-业务中心
	function addBusiSysCenter(){
		var dialog = $.dialog({
			id:'datacenter',
			title:'添加业务中心',
			height:'120px',
			content:'url:yicloud/busisystree/addBusiSysCenter.jsp',
			button:[{name: '确定',focus :true, callback: function () {
				this.button({name: '确定',disabled: true});
				var name = this.content.document.getElementById("name").value;
				if($.trim(name) == ""){
					$(this.content.document.getElementById("showValidateMess")).children().empty().append($('<font color="red">业务中心名称不能为空!</font>'));
					$(this.content.document.getElementById("showValidateMess")).show();
					this.button({name: '确定',disabled: false});
					return false;
				}
				validateName(this,name,"busiCenter");
				return false;
			}}, {name: '取消'}]
		});
		hideRMenu();
	}

	//删除根节点
	function deleteBusiSysCenter() {
		var treeNode = rightSNode;
		var nodeId = treeNode.id;
		var nodeType = treeNode.type;
		if (confirm('确认要删除业务中心节点吗？')) {
			$.getJSON('busisys_validateDelete.do?id=' + nodeId+'&type='+nodeType,{'time':new Date().toString()}, function(data){
				if(data=='true'){
					$.getJSON('busisys_deleteBusiSysCenter.do?id=' + nodeId,{'time':new Date().toString()}, function(data){
						if(data.result == 1){
							alert("删除业务中心成功");
							receiveValue(name);
						}else{
							alert("删除业务中心失败");
						}
					});
				}else{
					alert("该节点不为空不能删除！");
				}
			});
		}
		hideRMenu();
	}
	/** 从Excel导入主机用户并生成实例 */
	function importFormXls(){
		$.dialog({
			id:"browse",
			title:'浏览文件',
			width: '400px',
			height: '225px',
			content: 'url:xlsSupport_browse.do'
			});
		hideRMenu();
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
	
</script>
</head>
<body>
<s:form action="busitree_listBusiTree" method="post" id="theForm">
	<div class="content_wrap">
		<div class="left">
			<ul id="treeDemo" class="ztree"></ul>
			<div class="toggle-collapse js_toggleLeft">
             	<img id="img" src="sxcloud/cresources/ztree/zTreeStyle/img/diy/icon-left.png" onclick="hide()">
       		 </div>
		</div>
		<div class="right">
		 
			<div id="iframe">
				<iframe id="right_iframe" src="busiareainfo_listBusiArea.do" name="xxxx" height="100%" width="100%" frameborder="0" style=""></iframe>
			</div>
			<!-- -->
		</div>
	</div>
	<div id="rMenu">
		<ul>
			<li id="importFormXls" onclick="importFormXls()" class="hide">从excel导入</li>
			<li id="addBusiSysCenter" onclick="addBusiSysCenter()" class="hide">添加业务中心 </li>
			<li id="updateBusiSysCenter" onclick="updateBusiSysCenter()"class="hide"> 更新名称 </li>
			<li id="deleteBusiSysCenter" onclick="deleteBusiSysCenter()" class="hide">删除业务中心 </li>
			
			<li id="addBizsys" onclick="addBizsys()" class="hide"> 添加业务系统 </li>
			<li id="modifyBizsys" onclick="modifyBizsys()" class="hide">修改业务系统 </li>
			<li id="delBizsys" onclick="delBizsys()" class="hide"> 删除业务系统 </li>
			<li id="updateBizsys" onclick="updateBizsys()" class="hide">更新名称 </li>
			
			<li id="addSysApp" onclick="addSysApp()" class="hide"> 添加基准应用 </li>
			<li id="batchDealProcessData" onclick="batchDealProcessData()" class="hide">批量操作进程 </li>
			<li id="modifySysApp" onclick="modifySysApp()" class="hide">修改基准应用 </li>
			<li id="delSysApp" onclick="delSysApp()" class="hide"> 删除基准应用 </li>
<%--			<li id="batchDeployExample" onclick="batchDeployExample()" class="hide"> 批量部署 </li>--%>
			<li id="updateSysApp" onclick="updateSysApp()" class="hide"> 更新名称</li>
		
			<li id="addDeployExample" onclick="addDeployExample()" class="hide">添加部署实例 </li>
			<li id="modifyDeployExample" onclick="modifyDeployExample()" class="hide">修改部署实例 </li>
			<li id="delDeployExample" onclick="delDeployExample()" class="hide">删除 </li>
			
			
			<li id="createFileObjTree" onclick="createFileObjTree()" class="hide">生成文件实例树 </li>
<%--			<li id="deploy" onclick="deploy()" class="hide">部署 </li>--%>
<%--			<li id="upgradeDeployExample" onclick="upgradeDeployExample()" class="hide">升级 </li>--%>
		</ul>
	</div>
</s:form>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/dateformat/jquery.formDataOper.js"></script>
</body>
