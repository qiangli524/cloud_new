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
<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resource/united/js/unitedTree.js"></script>

<script type="text/javascript">
	var api = frameElement.api;
    var w = api.opener;

    api.button({
		id:'OkAnd',
		name: '确定',
		callback:submitRequest,
		focus: true
	},
	{
    	id:'cancle',
		name: '取消'
	});
	
	var setting = {
		view : {
			dblClickExpand : false,
			selectedMulti : false
		},
		check: {
				enable: true,
				chkboxType: { "Y": "ps", "N": "s" }
		},
		data : {
			simpleData : {
				enable : true
			}
		},
		async : {
			enable : true,
			dataType: "json",
			url : "entitychart_asyncForTree.do",
			autoParam : [ "id","connect_id","uuid","type","vtype"],
			otherParam: ["userId", "<%=request.getAttribute("userId")%>","flag", "<%=request.getAttribute("flag")%>"]
		}
	};
	  
	var zTree;
	$(document).ready(function() {
		$.fn.zTree.init($("#treeDemo"), setting);
		zTree = $.fn.zTree.getZTreeObj("treeDemo");
	});
	 
	function expandNodes(nodes) {
		if (!nodes) return;
		for (var i=0, l=nodes.length; i<l; i++) {
				zTree.expandNode(nodes[i], true, false, false);
				if (nodes[i].isParent && nodes[i].zAsync) {
					expandNodes(nodes[i].children);
				}
		}
	}
	 
	//获取所有勾选状态的节点，(包括原来已勾选的节点，现取消勾选;原来未被勾选的节点，现已勾选)
	var change = "";
	function getChange(){
		var result = '';
		var rootNodes = zTree.getNodes();
		var nodes = zTree.getCheckedNodes(true);
			
		if(nodes){
			for (var i=0, l=nodes.length; i<l; i++) {
				//var nameandtype = nodes[i].name + "," + nodes[i].type+"," +nodes[i].uuid+"," +nodes[i].vtype+"," +nodes[i].connect_id+ ":";
				var nameandtype = '';
				if (nodes[i].type == '3' || nodes[i].type == '4') {//主机或虚拟机
					nameandtype = nodes[i].connect_id + "_" + nodes[i].uuid + ","
				}
				result +=  nameandtype;
			}
		}
		return result;
	}	
	
	function submitRequest(){
		var vmids = getChange();
		if (vmids == '') {
			alert('请选择实体！');
		} else {
			w.initData(vmids);
		}
	} 
</script>
</head>
<body>
<div class="bord-1">
	<s:form action="" method="post" id="theForm">
	<div class="content_wrap" style="height:390px">
		<div class="left">
			<ul id="treeDemo" class="ztree"></ul>
		</div>
        <div class="right">
<%--           	<div class="pd-20 select-dls js_selectDls">--%>
<%--              	<dl class="dl-3 dl3-on">--%>
<%--               		<dt>全部权限</dt>--%>
<%--             		<dd>选择此项，则具有操作系统的全部权限！</dd>--%>
<%--                </dl>--%>
<%--               	<dl class="dl-3 mgt-20">--%>
<%--                  	<dt>操作权限</dt>--%>
<%--                   	<dd>选择此项，则具有操作系统的操作权限！</dd>--%>
<%--              	</dl>--%>
<%--            	<dl class="dl-3 mgt-20" onclick="test();return false;">--%>
<%--                 	<dt>查看权限</dt>--%>
<%--                  	<dd>选择此项，则具有操作系统的查看权限！</dd>--%>
<%--              	</dl>--%>
<%--          	</div>--%>
       	</div>
		</div>
	</s:form>
</div>
<script>
		$(function(){
			//权限设置
			$("div.js_selectDls").each(function(){
				$(this).children("dl").click(function(){
					$(this).addClass("dl3-on").siblings().removeClass("dl3-on");
				})								
			})
			//高度设置
			var contWrapH = $("div.content_wrap").height();
			$("div.js_toggleLeft").height(contWrapH)
			$("#treeDemo").css("height",contWrapH-11);
		})
	</script>
</body>