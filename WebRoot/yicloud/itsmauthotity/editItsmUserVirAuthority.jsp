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
		callback : {
			onClick : onClick
		},
		data : {
			simpleData : {
				enable : true
			}
		},
		async : {
			enable : true,
			dataType: "json",
			url : "vmauth_itsmVmTree.do",
			autoParam : [ "id","uuid","type"],
			otherParam: ["userId", "<%=request.getAttribute("userId")%>","flag", "<%=request.getAttribute("flag")%>"]
		}
	};
	  
	var zTree;
	$(document).ready(function() {
		$.fn.zTree.init($("#treeDemo"), setting);
		zTree = $.fn.zTree.getZTreeObj("treeDemo");
	});
	 
	function onClick(event, treeId, treeNode, clickFlag) {
		var userId = '<%=request.getAttribute("userId")%>';
		/*
		if (treeNode.type == 3 || treeNode.type == 4) { //主机或虚拟机
			$("#right_iframe").attr(
					"src",
					"vmauth_getOperAuthority.do?type=" + treeNode.type + "&uuid="+treeNode.uuid+"&connect_id="+treeNode.connect_id+"&userid="+userId+"&name=" + treeNode.name + "&vtype=" +treeNode.vtype);
			setDivVisProp('iframe');
		}
		treeNode.checked = true;
		*/
	}
	function expandNodes(nodes) {
		if (!nodes) return;
		for (var i=0, l=nodes.length; i<l; i++) {
				zTree.expandNode(nodes[i], true, false, false);
				if (nodes[i].isParent && nodes[i].zAsync) {
					expandNodes(nodes[i].children);
				}
		}
	}
	 
	//获取所有勾选状态被改变的节点，(包括原来已勾选的节点，现取消勾选;原来未被勾选的节点，现已勾选)
	var change = "";
	function getChange(){
		var rootNodes = zTree.getNodes();
		var nodes = zTree.getChangeCheckedNodes(false);
			
		if(nodes){
			for (var i=0, l=nodes.length; i<l; i++) {
				var nameandtype = nodes[i].name + "," + nodes[i].type+"," +nodes[i].uuid + ":";
				change +=  nameandtype;
			}
		}
	}	
	
	function submitRequest(){
		var rootNodes = zTree.getNodes();
		getChange();
		var nodes = zTree.getCheckedNodes(true);
		var names="";
		if(nodes){
			for (var i=0, l=nodes.length; i<l; i++) {
				var nameandtype = nodes[i].name + "," + nodes[i].type+"," +nodes[i].uuid + ":";
				names +=  nameandtype;
			}
		}
			
		var userId = '<%=request.getAttribute("userId")%>';
		var flag = '<%=request.getAttribute("flag")%>';
 		var childHtml = document.getElementById("right_iframe").contentWindow; 
		var oper = childHtml.$("input[name='vmAuthorityObj.OPERAUTHORITY']:checked").val(); 
		var url = "vmauth_saveItsmUserVirAuthority.do?names=" + encodeURI(encodeURI(names)) + "&userId=" + userId + "&flag=" + flag+"&oper="+oper+"&change="+encodeURI(encodeURI(change)) ;
		if(confirm("确定保存吗?")==true){
			$.ajax({
				url:url,
				async: false,
      			cache: false,
      			contentType:"application/x-www-form-urlencoded",
      			success: function(data) {
      				var json = eval("("+ data+")");
        			if(json.result==-1){
						alert("保存失败!");
						//zTree.reAsyncChildNodes(null, "refresh",true);
					}else{
						alert("保存成功!");
						//zTree.reAsyncChildNodes(null, "refresh",true);
					}
      			}
		   });
		}
	} 
	function refresh(userId,uuid,type,name) {
		$("#right_iframe").attr(
					"src",
					"vmauth_getItsmOperAuthority.do?type=" + type + "&uuid="+uuid + "&userid="+userId+"&name=" +name);
		setDivVisProp('iframe');
	}
</script>
</head>
<body>
<s:form action="" method="post" id="theForm">
	<div class="content_wrap">
		<div class="left">
			<ul id="treeDemo" class="ztree"></ul>
		</div>
		<div style="float: left; margin-top: 200px">
			<img id="img"
				src="sxcloud/cresources/ztree/zTreeStyle/img/diy/icon.gif"
				onclick="hide()" />
		</div> 
		<div class="right">
			<div id="iframe">
				<iframe id="right_iframe" src="vmauth_authotitySelectItsmPage.do" name="xxxx" height="100%" width="67%;" frameborder="0" style="position:fixed;left:259px;right:0px;top:0px;bottom:0px;overflow-x:auto;"></iframe>
			</div> 
		</div> 
	</div>
</s:form>
</body>
