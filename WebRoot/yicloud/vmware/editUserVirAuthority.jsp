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
			url : "vmauth_vmTree.do",
			autoParam : [ "id","connect_id","uuid","type","vtype"],
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
				var nameandtype = nodes[i].name + "," + nodes[i].type+"," +nodes[i].uuid+"," +nodes[i].vtype+"," +nodes[i].connect_id+ ":";
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
				var nameandtype = nodes[i].name + "," + nodes[i].type+"," +nodes[i].uuid+"," +nodes[i].vtype+"," +nodes[i].connect_id+ ":";
				names +=  nameandtype;
			}
		}
		//获取层ID		
		var operclass = '';
		$("div.js_selectDls").each(function(){
			operclass = $(this).children("dl.dl3-on").attr("id");						
		})
		
		var operchinese = '';
		if (operclass == 'all') {
			operchinese = '全部权限';
		} else if (operclass == 'oper') {
			operchinese = '操作权限';
		} else if (operclass == 'view') {
			operchinese = '查看权限';
		}
		
		var userId = '<%=request.getAttribute("userId")%>';
		var flag = '<%=request.getAttribute("flag")%>';
		var url = "vmauth_saveUserVirAuthority.do?userId=" + userId + "&flag=" + flag+"&oper="+operclass ;
		var _form = $("#theForm");
		if(confirm("确定保存该用户对资源的"+operchinese+"吗?")==true){
			$("#changeText").val(change);
			$("#namesText").val(names);
			$.ajax({
				url:url,
				type:"post",
				data: $("#theForm").serialize(),
				async: false,
      			cache: false,
      			//contentType:"application/x-www-form-urlencoded",
      			success: function(data) {
      				var json = eval("("+ data+")");
        			if(json.result==-1){
						alert("保存失败!");
						//zTree.reAsyncChildNodes(null, "refresh",true);
					}else{
						alert("保存成功!");
						//zTree.reAsyncChildNodes(null, "refresh",true);
					}
      			},
      			error: function (data){
      				alert("保存失败!");
      			}
		   });
		}
	} 
	function refresh(userId,connectid,uuid,type,vtype,name) {
		$("#right_iframe").attr(
					"src",
					"vmauth_getOperAuthority.do?type=" + type + "&uuid="+uuid+"&connect_id="+connectid+"&userid="+userId+"&name=" +name + "&vtype=" +vtype);
		setDivVisProp('iframe');
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
           	<div class="pd-20 select-dls js_selectDls">
              	<dl id="all" class="dl-3 dl3-on" >
               		<dt>全部权限</dt>
             		<dd>选择此项，则具有操作系统的全部权限！</dd>
                </dl>
               	<dl id="oper" class="dl-3 mgt-20">
                  	<dt>操作权限</dt>
                   	<dd>选择此项，则具有操作系统的操作权限！</dd>
              	</dl>
            	<dl id="view" class="dl-3 mgt-20">
                 	<dt>查看权限</dt>
                  	<dd>选择此项，则具有操作系统的查看权限！</dd>
              	</dl>
          	</div>
       	</div>
		</div>
		<input type="hidden" id="changeText"  name="change"/>
		<input type="hidden" id="namesText"  name="names"/>
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